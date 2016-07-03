package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MProductClass;
import org.compiere.model.I_M_Product_Category;
import org.compiere.model.I_M_Product_Class;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MCostDetail;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProductPricing;
import org.compiere.model.MResourceAssignment;
import org.compiere.model.MTax;
import org.compiere.model.MTransaction;
import org.compiere.model.MUOMConversion;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class FTUModelValidator implements ModelValidator {
	
	public FTUModelValidator() {
		super();
		String where = MTax.COLUMNNAME_Name + "= 'Exempt'";
		m_exempt_c_tax_id = new Query(Env.getCtx(), MTax.Table_Name, where, null)
		 					.setClient_ID()
		 					.setOnlyActiveRecords(true)
		 					.firstIdOnly();
	}

	private class CustomerBlockBookings {
		
		public final BigDecimal DISCOUNT_PERCENT = new BigDecimal(4.0);

		private class BlockBooking {
			public int bb_Product_ID = 0;
			public String bb_value = "";
			public double bb_balance = 0.0;
			public double bb_toApply = 0.0;
				
			public BlockBooking(int M_Product_ID, String value, double balance) {
				bb_Product_ID = M_Product_ID;
				bb_value = value;
				bb_balance = balance;
				bb_toApply = 0.0;
			}
		}

		List<BlockBooking> blockBookings = new ArrayList<BlockBooking>();
		
		public CustomerBlockBookings(int c_BPartner_ID) {
			
			if (m_blockBookingProducts == null || m_blockBookingProducts.length == 0) {
				blockBookings = null;
				return;
			}
			
			blockBookings.clear();
			
			String sql = "SELECT p.M_Product_ID, p.value, sum(il.QtyInvoiced) AS balance"
					+ " FROM c_invoice i"
					+ " JOIN c_invoiceline il on i.C_Invoice_ID = il.C_Invoice_ID"
					+ " JOIN m_product p on il.M_Product_ID = p.M_Product_ID"
					+ " JOIN m_product_category pc on pc.m_product_category_id = p.m_product_category_id"
					+ " WHERE i.C_BPartner_ID = ?"
					+ " AND pc.name::text = 'Block Booking'::text"
					+ " AND i.ad_client_id = 1000000::numeric" 
					+ " AND i.isactive = 'Y'::bpchar"
					+ " AND i.isSOTrx =  'Y'::bpchar"
					+ " AND i.DocStatus in ('CO', 'CL')"
					+ " GROUP BY p.M_Product_ID, p.value";
			
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				pstmt.setInt (1, c_BPartner_ID);
				ResultSet rs = pstmt.executeQuery ();
				while (rs.next ())
					blockBookings.add (new BlockBooking(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
				rs.close ();
				pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				pstmt = null;
			}
		}
		
		public boolean hasBlockBookings() {
			return blockBookings !=null && blockBookings.size() > 0;
		}

		public double getBalance(int m_Product_ID) {
			
			for (BlockBooking blockBooking : blockBookings) {
				if (blockBooking.bb_Product_ID == m_Product_ID)
					return blockBooking.bb_balance;
			}
			// TODO Auto-generated method stub
			return 0.0;
		}

		public BigDecimal addQuantityToBlock(String blockName, BigDecimal qtyEntered) {
			Double qtyToCover = qtyEntered.doubleValue();
			
			for (BlockBooking blockBooking : blockBookings) {
				if (blockBooking.bb_value.equals(blockName)) {
					if (blockBooking.bb_balance == 0.0) {
						return Env.ZERO;
					}
					else if (qtyToCover <= blockBooking.bb_balance) {
						blockBooking.bb_toApply += qtyToCover;
						blockBooking.bb_balance -= qtyToCover;
						return BigDecimal.valueOf(qtyToCover);
					}
					else {
						qtyToCover = blockBooking.bb_balance;
						blockBooking.bb_toApply += blockBooking.bb_balance;
						blockBooking.bb_balance = 0.0;
						return BigDecimal.valueOf(qtyToCover);
					}
				}
			}
			// TODO Auto-generated method stub
			return Env.ZERO;
		}

		public void addBlockBookingLines(MOrder order) {
			if (order == null)
				return;
			
			if (blockBookings == null)
				return;
			
			for (BlockBooking blockBooking : blockBookings) {
				if (blockBooking.bb_toApply>0) {
					
					int C_BPartner_ID = order.getC_BPartner_ID();
					boolean IsSOTrx = order.isSOTrx();
					BigDecimal qty = BigDecimal.valueOf(blockBooking.bb_toApply);
					MProductPricing pp = new MProductPricing (blockBooking.bb_Product_ID, C_BPartner_ID, qty, IsSOTrx, null);
					//
					int M_PriceList_ID = order.getM_PriceList_ID();
					pp.setM_PriceList_ID(M_PriceList_ID);
					Timestamp orderDate = order.getDateOrdered();
					/** PLV is only accurate if PL selected in header */
					int M_PriceList_Version_ID = orderGetPriceListVersion(order);
					pp.setM_PriceList_Version_ID(M_PriceList_Version_ID); 
					pp.setPriceDate(orderDate);
					//		
					MOrderLine orderLine = new MOrderLine(order);
					orderLine.setM_Product_ID(blockBooking.bb_Product_ID);
					orderLine.setC_UOM_ID(pp.getC_UOM_ID());
					orderLine.setQtyEntered(BigDecimal.valueOf(blockBooking.bb_toApply).negate());					
					orderLine.setPriceLimit(pp.getPriceLimit());
					orderLine.setPriceActual(pp.getPriceStd());
					orderLine.setPriceEntered(pp.getPriceStd());
					orderLine.setC_Currency_ID(pp.getC_Currency_ID());
					orderLine.setDiscount(pp.getDiscount());
					orderLine.setQtyOrdered(orderLine.getQtyEntered());
					if(m_hasAdvancedInstruction && m_exempt_c_tax_id > 0 )
						orderLine.setC_Tax_ID(m_exempt_c_tax_id);
					orderLine.saveEx();
				}
			}
			
		}

		private int orderGetPriceListVersion(MOrder order) {
			
			if (order == null)
				return 0;
			
			BigDecimal M_PriceList_ID = new BigDecimal(order.getM_PriceList_ID());
			
			String where = MPriceListVersion.COLUMNNAME_M_PriceList_ID + "=?"
					+ " AND " + MPriceListVersion.COLUMNNAME_ValidFrom + "<=?";
			
			return new Query(order.getCtx(), MPriceListVersion.Table_Name, where, null)
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.setParameters(M_PriceList_ID, order.getDateOrdered())
							.setOrderBy(MPriceListVersion.COLUMNNAME_ValidFrom + " DESC")
							.firstId();
		}

	}

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());
	/** Client			*/
	private int		m_AD_Client_ID = -1;
	private int[] m_blockBookingProducts;
	private boolean m_hasAdvancedInstruction = false;
	private int m_exempt_c_tax_id = 0;

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		
		// Not global
		if (client == null)
			return;
		
		if (client != null)
		{	
			m_AD_Client_ID = client.getAD_Client_ID();
		}
		engine.addModelChange(MOrder.Table_Name, this);
		engine.addModelChange(MInventory.Table_Name, this);
		engine.addDocValidate(MOrder.Table_Name, this);
		engine.addDocValidate(MInventory.Table_Name, this);
		engine.addModelChange(MFTUFlightsheet.Table_Name, this);
	}

	@Override
	public int getAD_Client_ID() {
		return m_AD_Client_ID;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		
		if (po instanceof MFTUFlightsheet 
				&& (TYPE_AFTER_NEW == type
				|| TYPE_AFTER_CHANGE == type)) {
			
			boolean isNew = (TYPE_AFTER_NEW == type);
			boolean isChange = (TYPE_AFTER_CHANGE == type);
			
			MFTUFlightsheet fs = (MFTUFlightsheet) po;
			
			// Ignore updates while the model is being validated
			if (fs.isBeingModelValidated())
				return null;

			log.fine(I_FTU_Flightsheet.Table_Name + " Flightsheet ID: " + fs.getFTU_Flightsheet_ID() + " Type: "+type);

			// Ignore pending flightsheet entries
			if (isNew && fs.getCourseType().matches("\\(pending\\)")) {
				return null;
			}

			fs.setBeingModelValidated(true);
			
			// Ignore inactive flights - void the associated entries
			if (isChange && (po.is_ValueChanged("isActive") && !fs.isActive())
					|| po.is_ValueChanged(MFTUFlightsheet.COLUMNNAME_IsNoShow) && !fs.isNoShow()) {
				MOrder order = (MOrder) fs.getC_Order();
				if (order != null && order.getC_Order_ID() > 0) {
					if (order.getDocStatus().equals(MOrder.STATUS_Drafted)) {
						order.deleteEx(true);
					}
					else {
						order.voidIt();
						order.setDocStatus(MOrder.STATUS_Voided);
						order.saveEx();
					}
					//fs.setC_Order_ID(0);  // Null - will be done by the order process. 
				}
				MInventory inv = (MInventory) fs.getM_Inventory();
				if (inv != null && inv.getM_Inventory_ID() > 0) {
					inv.voidIt();
					inv.saveEx();
					fs.setM_Inventory_ID(0);
				}
				return null;
			}
			
			if (!fs.isActive())
				return null;

			if (fs.getCourseType().matches("Cancelled")) {
				if (fs.getLine_Status() == null || !fs.getLine_Status().equals("Closed")) { 
	    			fs.setLine_Status("Closed");
	    			fs.saveEx();
				}
			}
			else {	
				// These calls will update and save the flightsheet entry, triggering
				// other validations
				fsGenerateOrder(fs);
				fsConsumeFuel(fs);
				fsUpdateJourneyLog(fs);
			}
			//
			fs.setBeingModelValidated(false);
			return null;
		}

		if (po instanceof MInventory) {	
			log.fine(po.get_TableName() + " Type: "+type);
			String where = "";

			if ((TYPE_BEFORE_DELETE == type) && ((MInventory) po).getM_Inventory_ID() > 0) {				
				MInventory inv = ((MInventory) po);
				MInventoryLine[] lines = inv.getLines(false);
				for (MInventoryLine line : lines)
				{	
					where = MCostDetail.COLUMNNAME_M_InventoryLine_ID + "=" + line.getM_InventoryLine_ID();
					List<MCostDetail> costs = new Query(po.getCtx(),MCostDetail.Table_Name, where, po.get_TrxName())
												.list();
					for (MCostDetail cost : costs)
						cost.delete(true);

					List<MTransaction> Transactions = new Query(po.getCtx(),MTransaction.Table_Name, where, po.get_TrxName())
												.list();
					for (MTransaction trx : Transactions)
						trx.delete(true);
					
					line.delete(true);

				}
			}
			// If M_Inventory entries are deleted, check if these are referenced from the flightsheet and remove the reference.
			if ((TYPE_AFTER_DELETE == type) && ((MInventory) po).getM_Inventory_ID() > 0) {
				where =  MInventory.COLUMNNAME_M_Inventory_ID + "=" + ((MInventory) po).getM_Inventory_ID();
				List<MFTUFlightsheet> flights = new Query(po.getCtx(), MFTUFlightsheet.Table_Name, where, po.get_TrxName() )
													 .setClient_ID()
													 .list();
				for (MFTUFlightsheet flight : flights) {
					flight.setM_Inventory_ID(0);
					flight.saveEx();
				}
			}
		}

		if (po instanceof MOrder) {	
			boolean isDelete = (TYPE_AFTER_DELETE == type);

			// If M_Order entries are deleted, check if these are referenced from the flightsheet and remove the reference.
			if (isDelete && ((MOrder) po).getC_Order_ID() > 0) {
				log.fine(po.get_TableName() + " Type: "+type);
				String where =  MOrder.COLUMNNAME_C_Order_ID + "=" + ((MOrder) po).getC_Order_ID();
				List<MFTUFlightsheet> flights = new Query(po.getCtx(), MFTUFlightsheet.Table_Name, where, po.get_TrxName() )
													 .setClient_ID()
													 .list();
				for (MFTUFlightsheet flight : flights) {
					flight.setC_Order_ID(0);
					flight.saveEx();
				}
			}
		}

		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {

		if (po instanceof MInventory) {	
			log.fine(po.get_TableName() + " Timing: "+ timing);
			boolean isVoided = (ModelValidator.TIMING_AFTER_REVERSECORRECT == timing || ModelValidator.TIMING_AFTER_VOID == timing);
			
			// If M_Inventory entries are voided, check if these are referenced from the flightsheet and remove the reference.
			if (isVoided) {
				String where =  MInventory.COLUMNNAME_M_Inventory_ID + "=" + ((MInventory) po).getM_Inventory_ID();
				List<MFTUFlightsheet> flights = new Query(po.getCtx(), MFTUFlightsheet.Table_Name, where, po.get_TrxName() )
													 .setClient_ID()
													 .list();
				for (MFTUFlightsheet flight : flights) {
					flight.setM_Inventory_ID(0);
					flight.saveEx();
				}
			}
		}

		if (po instanceof MOrder) {	
			log.fine(po.get_TableName() + " Timing: "+ timing);
			boolean isVoided = (ModelValidator.TIMING_AFTER_REVERSECORRECT == timing || ModelValidator.TIMING_AFTER_VOID == timing);
			boolean isPrepare = (ModelValidator.TIMING_BEFORE_PREPARE == timing);
			boolean isCompleted = (ModelValidator.TIMING_AFTER_COMPLETE == timing);
			boolean isReactivated = (ModelValidator.TIMING_AFTER_REACTIVATE == timing);
			
			// If C_Order entries are voided, check if these are referenced from the flightsheet and remove the reference.
			if (isVoided) {
				String where =  MOrder.COLUMNNAME_C_Order_ID + "=" + ((MOrder) po).getC_Order_ID();
				List<MFTUFlightsheet> flights = new Query(po.getCtx(), MFTUFlightsheet.Table_Name, where, po.get_TrxName() )
													 .setClient_ID()
													 .list();
				for (MFTUFlightsheet flight : flights) {
					flight.setC_Order_ID(0);
					flight.saveEx();
				}
			} // isVoided
			
			if (isReactivated) {
				// Delete the reference to the order lines from the invoice lines
				MOrder order = (MOrder) po;
				MOrderLine[] orderLines = order.getLines();
				String where = MInvoiceLine.COLUMNNAME_C_OrderLine_ID + "= ?";
				Query invoiceLineQuery = new Query(po.getCtx(), MInvoiceLine.Table_Name, where, po.get_TrxName())
													 .setClient_ID();
				where = MInOutLine.COLUMNNAME_C_OrderLine_ID + "= ?";
				Query inOutLineQuery = new Query(po.getCtx(), MInOutLine.Table_Name, where, po.get_TrxName())
													 .setClient_ID();
				for (MOrderLine orderLine : orderLines) {
					List<MInvoiceLine> invoiceLines = invoiceLineQuery
														.setParameters(orderLine.getC_OrderLine_ID())
														.list();
					for (MInvoiceLine line : invoiceLines) {
						line.setC_OrderLine_ID(-1);
						line.saveEx();
					}
					
					List<MInOutLine> inOutLines = inOutLineQuery
							.setParameters(orderLine.getC_OrderLine_ID())
							.list();
					for (MInOutLine line : inOutLines) {
						line.setC_OrderLine_ID(-1);
						line.saveEx();
					}
					
					MProduct product = (MProduct) orderLine.getM_Product();
					if (!product.isStocked()) {
						orderLine.setQtyDelivered(Env.ZERO);
						orderLine.setQtyReserved(Env.ZERO);
						orderLine.saveEx();
					}
				}
								
			} // isReactivated
			
			if (isPrepare) {
				setHasAdvancedInstruction(po);
				orderAddBlockBookings(po);
				setExemptTaxes(po);
			}
			
			if (isCompleted) {
				String where =  MOrder.COLUMNNAME_C_Order_ID + "=" + ((MOrder) po).getC_Order_ID();
				List<MFTUFlightsheet> flights = new Query(po.getCtx(), MFTUFlightsheet.Table_Name, where, po.get_TrxName() )
													 .setClient_ID()
													 .list();
				for (MFTUFlightsheet flight : flights) {
					flight.setLine_Status("Closed");
					flight.saveEx();
				}				
			}
		}

		return null;
	}
	
	private void setHasAdvancedInstruction( PO po) {
		// Determine if there is an advanced instruction product.
		int advancedInstructionProduct_ID = 1000248;  // Hardcoded
		m_hasAdvancedInstruction = false;
		MOrder order = (MOrder) po;
		MOrderLine[] orderLines = order.getLines();
		for (MOrderLine orderLine : orderLines) {
			int m_product_id = orderLine.getM_Product_ID();
			if (m_product_id == advancedInstructionProduct_ID) {
				m_hasAdvancedInstruction = true;
				break;
			}
		}
	}
	
	private void setExemptTaxes(PO po) {
		// Set the tax on tuition items to exempt for 
		// vocational students purchasing vocational training items
		// All tuition items and block booking items that are on an order
		// that also includes the Advanced Instruction item are tax exempt
		
		MOrder order = (MOrder) po;
		MOrderLine[] orderLines = order.getLines();
		
		if (m_hasAdvancedInstruction) {
			for (MOrderLine orderLine : orderLines) {
				if (orderLine == null)
					continue;
				
				if (orderLine.getM_Product_ID() == 0)
					continue;
				
				MProduct product = (MProduct) orderLine.getM_Product();
				if (product == null || product.get_ID() == 0)
					continue;
				
				I_M_Product_Category productCategory = product.getM_Product_Category();
				boolean isBlockBooking = (productCategory != null && productCategory.getName().equals("Block Booking"));
				//
				if (product.isTuitionFee() || isBlockBooking) {
					orderLine.setC_Tax_ID(m_exempt_c_tax_id);
					orderLine.saveEx();
				}
			}
		}
	}

	private void orderAddBlockBookings(PO po) {
		// Add block bookings if they don't exist.
		// Match for eligible product and block booking is product class = block booking product value
		// The order lines could already have block bookings added
		// Fuel surcharge is hard coded.
		
		// Load the list of block booking products
		if (!orderLoadBlockBookingProducts(po))
			return;
		
		MOrder order = (MOrder) po;
		
		// Don't proceed unless the ApplyBlockBooking flag is selected.
		if (!order.isApplyBlockBookings())
			return;
		
		MOrderLine[] orderLines = order.getLines();
		
		boolean hasBlockBookingProducts = false;
		for (MOrderLine orderLine : orderLines) {
			int M_Product_ID = orderLine.getM_Product_ID();
			
			// Does the order have any products that are eligible for BB?
			if (Arrays.binarySearch(m_blockBookingProducts, M_Product_ID) >= 0) {
				hasBlockBookingProducts = true;
				break;
			}
		}
		
		// No products in order have block bookings
		if (!hasBlockBookingProducts) {
			log.fine("The order has no products eligible for block booking discounts");
			return;
		}
		
		// Load the customer block bookings - this could be time intensive
		CustomerBlockBookings customerBB = new CustomerBlockBookings(order.getC_BPartner_ID());
		// Does the customer have any block bookings available?
		if (!customerBB.hasBlockBookings()) {
			log.fine("Customer has no block bookings available.");
			return;  		// Product in order is eligible but customer has no block bookings
		}
		
		int StdPrecision = MPriceList.getStandardPrecision(order.getCtx(), order.getM_PriceList_ID());
		// Check each product
		for (MOrderLine orderLine : orderLines) {
			MProduct product = orderLine.getProduct();
			if (product == null)
				continue;  // Could be a charge, skip the line.
			
			if (orderLine.getDiscount().compareTo(customerBB.DISCOUNT_PERCENT)>0)
				continue; // Can't apply block bookings to an already discounted sale.

			if (orderLine.getQtyEntered().compareTo(Env.ZERO)==0)
				continue; // Can't discount zero quantity.

			I_M_Product_Category productCategory = product.getM_Product_Category();
			if (productCategory != null && productCategory.getName().equals("Block Booking")) {
				if (orderLine.getQtyEntered().compareTo(Env.ZERO) <= 0)
					orderLine.setQtyReserved(Env.ZERO);
					orderLine.delete(false);
				continue;
			}
			
			I_M_Product_Class productClass = product.getM_Product_Class();
			if (productClass == null)
				continue;  // Product has no class - no match to a block booking product

			BigDecimal qtyCovered = customerBB.addQuantityToBlock(productClass.getName(),orderLine.getQtyEntered());
			// line amount = Price * (qe - qc) + Price * (1- discount) * (qc)
			// Effective price = line amount/qe
			// Effective discount = (1-effective price/price) =(qe - ((qe - qc) + (1 - discount)*qc))/qe
			// 					= (+qc -qc + discount*qc)/qe  = discount*qc/qe
			if (qtyCovered.compareTo(Env.ZERO) > 0) {
				BigDecimal Discount = customerBB.DISCOUNT_PERCENT
						.multiply(qtyCovered)
						.divide(orderLine.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP);
				BigDecimal PriceList = orderLine.getPriceList();
				BigDecimal PriceActual = orderLine.getPriceActual();
				BigDecimal PriceEntered = orderLine.getPriceEntered();
				if ( PriceList.doubleValue() != 0 )
					PriceActual = new BigDecimal ((100.0 - Discount.doubleValue()) / 100.0 * PriceList.doubleValue());
				if (PriceActual.scale() > StdPrecision)
					PriceActual = PriceActual.setScale(StdPrecision, BigDecimal.ROUND_HALF_UP);
				PriceEntered = MUOMConversion.convertProductFrom (order.getCtx(), orderLine.getM_Product_ID(), 
					orderLine.getC_UOM_ID(), PriceActual);
				if (PriceEntered == null)
					PriceEntered = PriceActual;
				orderLine.setPriceActual(PriceActual);
				orderLine.setPriceEntered(PriceEntered);
				orderLine.setDiscount(Discount);
				orderLine.saveEx();
			}
		}
		
		// All order lines have been processed, add the block bookings
		customerBB.addBlockBookingLines(order);
	}

	private boolean orderLoadBlockBookingProducts(PO po) {
		
		if (m_blockBookingProducts != null && m_blockBookingProducts.length > 0)
			return true;
		
		// Find the Block Booking products by the category "Block Booking"
		String where = MProductCategory.COLUMNNAME_Name + " = ?";
		MProductCategory pc = new Query(po.getCtx(), MProductCategory.Table_Name, where, null)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters("Block Booking")
					.firstOnly();
		
		if (pc==null) {
			log.severe("No block booking product category found!");
			m_blockBookingProducts = null;
			return false;
		}

		where = MProduct.COLUMNNAME_M_Product_Category_ID + "=?";
		List<MProduct> blockBookings = new Query(po.getCtx(), MProduct.Table_Name, where, null)
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.setParameters(pc.getM_Product_Category_ID())
						.list();
		
		// Now generate a list of category names which will be the class names on the
		// products the block bookings apply to
		String pcCatNames = "";
		for (MProduct blockBooking : blockBookings) {
			if (pcCatNames.length()>0)
				pcCatNames += ",";
			pcCatNames += DB.TO_STRING(blockBooking.getValue());
		}
		if (pcCatNames.length()>0)
			pcCatNames = "(" + pcCatNames + ")";
		else {
			log.severe("Block booking products have no name!");
			m_blockBookingProducts = null;
			return false;
		}

		// Get the list of 
		where = MProductClass.COLUMNNAME_Name + " IN " + pcCatNames;
		List<MProductClass> bbProductClasses = new Query(po.getCtx(), MProductClass.Table_Name, where, null)
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.list();
		
		String pcClassIDs = "";
		for (MProductClass bbProductClass: bbProductClasses) {
			if (pcClassIDs.length()>0)
				pcClassIDs += ",";
			pcClassIDs += bbProductClass.getM_Product_Class_ID();
		}
		if (pcClassIDs.length()>0)
			pcClassIDs = "(" + pcClassIDs + ")";
		else {
			log.severe("Block booking products have no class ids!");
			m_blockBookingProducts = null;
			return false;
		}
		
		where = MProduct.COLUMNNAME_M_Product_Class_ID + " IN " + pcClassIDs;
		m_blockBookingProducts = new Query(po.getCtx(), MProduct.Table_Name, where, null)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.getIDs();	
		
		if (m_blockBookingProducts != null && m_blockBookingProducts.length > 0)
			Arrays.sort(m_blockBookingProducts);

		return m_blockBookingProducts != null && m_blockBookingProducts.length > 0;
	}

	private boolean fsGenerateOrder(MFTUFlightsheet fs) {
		
		boolean success = true;

		// Ignore entries that already have an order associated with them
		// TODO check if the order is completed and should be closed.
		if (fs.getC_Order_ID() > 0) {
			return success;
		}
		
		// Ignore lines that are closed
		if (fs.getLine_Status() == "Closed") {
			return success;
		}
		
		BigDecimal flightTime = fs.getFlightTime();
    	BigDecimal brief = fs.getBriefing();
    	BigDecimal sim = fs.getSimulator();
    	BigDecimal fuel = fs.getFuel();

		// Look for flightsheet lines that have billable time or a No-Show.
		// If there is no time or no-show, then the line is not ready for billing 
		// yet.
		if (!(flightTime.add(brief).add(sim).add(fuel).doubleValue()>0.0 || 
				fs.getCourseType().matches("No-Show"))) {
			fs.setC_Order_ID(0);
			fs.setLine_Status("Closed");
			fs.saveEx();
			return success;
		}
    	
    	log.fine("Creating order for Flight ID: " + fs.getFlightID() + " Type: " + fs.getCourseType());
    	
    	int m_M_Warehouse_ID = Env.getContextAsInt(fs.getCtx(), "M_Warehouse_ID");
		
		int order_id = 0;
		
		// If we have passed the above tests, the flight sheet line is valid
		// and ready to be converted to an order.
		// Check for any open orders with same date, C_BPartner_ID that
		// are draft and already referenced from the flight sheet.
		// If we find one, add the flight to it. Otherwise, create a new
		// order.
		String whereClause = 	"DocStatus = 'DR' AND " +
								"DateOrdered=? AND " +
								"C_BPartner_ID=? AND " +
								"EXISTS (SELECT 1 FROM FTU_Flightsheet fs WHERE fs.C_Order_ID = C_Order.C_Order_ID) AND " +
								"C_BPartner_ID NOT IN (SELECT C_BPartner_ID FROM C_BPartner WHERE Upper(Name) IN ('INTRO CUSTOMER', 'TOUR CUSTOMER'))";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(fs.getFlightDate());
		parameters.add(fs.getC_BPartner_ID());
		MOrder order = new Query(fs.getCtx(), MOrder.Table_Name, whereClause, fs.get_TrxName())
											.setClient_ID()
											.setOnlyActiveRecords(true)
											.setOrderBy("C_Order_ID DESC")
											.setParameters(parameters)
										.first();
		
		if (order == null) {
			order = new MOrder(fs.getCtx(), 0, fs.get_TrxName());
		}

		// Check to see if the business partner is set
		if ((order.getC_BPartner_ID()==0))
		{
			order.setC_BPartner_ID(fs.getC_BPartner_ID());
			order.setDateOrdered(fs.getFlightDate());
			order.setM_Warehouse_ID(m_M_Warehouse_ID); //Use default
			order.setC_DocTypeTarget_ID("WI"); //Credit Order
			order.saveEx();
		}

		log.fine("Creating orders - Order ID: " + order_id);

    	String fltDate = new SimpleDateFormat("dd MMM yy").format(fs.getFlightDate());
		MFTUAircraft ac = MFTUAircraft.get(fs.getCtx(), fs.getFTU_Aircraft_ID(),fs.get_TrxName());
		MFTUInstructor inst = MFTUInstructor.get(fs.getCtx(), fs.getFTU_Instructor_ID(),fs.get_TrxName());
		MProduct noShowProduct = MFTUAdvancedInst.getNoShowProduct(fs.getCtx(), fs.getCourseType(), fs.get_TrxName());
		MProduct introProduct = MFTUAdvancedInst.getIntroProduct(fs.getCtx(), fs.getCourseType(), fs.get_TrxName());
		MProduct advancedInstProduct = MFTUAdvancedInst.getAdvancedInstProduct(fs.getCtx(), fs.getCourseType(), fs.get_TrxName());
		MProduct acProduct = null;
		if (ac != null) {
			if (ac.isGeneric()) { // Ignore placeholder entries.
				ac = null;
			}
			else {
	
				 String where = MProduct.COLUMNNAME_S_Resource_ID + "=" + ac.getS_Resource_ID();
				 acProduct = new Query(fs.getCtx(), MProduct.Table_Name, where, fs.get_TrxName())
				 						.setClient_ID()
				 						.setOnlyActiveRecords(true)
				 						.firstOnly();
				 if (acProduct == null )
				 	throw new AdempiereException("No AC Product for Aircraft " + ac.getACRegistration());
			}
		}

		MProduct instProduct = null;
		MBPartner instBP = null;
		if (inst != null) {
			instBP = (MBPartner) inst.getC_BPartner();
			if (instBP == null || instBP.getC_BPartner_ID() == 0)
			 	throw new AdempiereException("No Instructor Business Partner for Instructor ID " + inst.getFTU_Instructor_ID());

			String where = MProduct.COLUMNNAME_S_Resource_ID + "=" + inst.getS_Resource_ID();
			 instProduct = new Query(fs.getCtx(), MProduct.Table_Name, where, fs.get_TrxName())
			 						.setClient_ID()
			 						.setOnlyActiveRecords(true)
			 						.firstOnly();
			 if (instProduct == null)
			 	throw new AdempiereException("No Instructor Product for Instructor " + inst.getC_BPartner().getName());
		}

    	// Deal with No-Shows
 		// Add order line for AC No Show
 		if (noShowProduct != null && (ac != null || inst != null) ) {
    		log.fine("   There is a no-show charge to add.");
 			if (ac != null) {
	        	StringBuilder description = new StringBuilder("No-Show fee for Flt ID: " + fs.getFlightID())
	        	 	.append(" Date: ")
	        	 	.append(fltDate)
	        	 	.append(" A/C: ")
	        	 	.append(ac.getACRegistration());
     			MOrderLine line = new MOrderLine(order);
     			line.setM_Product_ID(noShowProduct.getM_Product_ID());
     			line.setC_UOM_ID(noShowProduct.getC_UOM_ID());
     			line.setDescription(description.toString());
     			line.setQty(new BigDecimal(2));
     			line.save();	     				
 			}
 			if (inst != null) {
	        	StringBuilder description = new StringBuilder("No-Show fee for Flt ID: " + fs.getFlightID())
	        	 	.append(" Date: ")
	        	 	.append(fltDate)
	        	 	.append(" Instructor: ")
	        	 	.append(instBP.getName());
     			MOrderLine line = new MOrderLine(order);
     			line.setM_Product_ID(noShowProduct.getM_Product_ID());
     			line.setC_UOM_ID(noShowProduct.getC_UOM_ID());
     			line.setDescription(description.toString());
     			line.setQty(new BigDecimal(2));
     			line.save();	     				
 			}
 		}
 		else
 		{  // not a now show
    	 
 			Timestamp engStart = null;
 			Timestamp engStop = null;
 			long timeDiff;
 			BigDecimal hours = Env.ZERO;
 			BigDecimal millisecPerHour = new BigDecimal(60*60*1000);
 			
        	 // Figure out the times of use
        	 // First check to see if the engine start time is valid
        	 if (fs.getEngineStart() != null)
        	 {
        		 engStart = fs.getEngineStart();
        		// if it is, check if the engine stop time is valid
        	 	if (fs.getEngineStop() != null)
        	 	{
        	 		// it is valid, now calculate the time difference and check 
        	 		// with the flightTime calculated above
        	 		engStop = fs.getEngineStop();
        	 		timeDiff = engStop.getTime() - engStart.getTime();
	        	 	hours = new BigDecimal(timeDiff).divide(millisecPerHour, 1, BigDecimal.ROUND_HALF_UP);
	        	 	
	        	 	if (!(hours.compareTo(flightTime)==0)){
	        	 		log.fine("Mismatch of flight time and engine start/stop. Flight Time = " + flightTime.toString() +
	        	 				 " Eng Stop - Eng Start = " + hours.toString());
	        	 	}
        	 	}
        	 	else
        	 	{
        	 		// The engine start is valid but the stop time isn't valid.
        	 		// Use the flight time.  Convert from hours to milliseconds
        	 		engStop = new Timestamp(engStart.getTime() + 
        	 				flightTime.multiply(millisecPerHour).intValue());
        	 	}
        	 }
        	 else 
        	 {
        		 // The engine start is invalid. Assume there is no
        		 // flight time.  Use the flight date & sim + brief time;
        		 engStart = fs.getFlightDate();
        		 engStop = new Timestamp(engStart.getTime() + sim.add(brief).multiply(millisecPerHour).intValue());
        		 log.fine("Engine start invalid. Flight Time = " + flightTime.toString());
        	 }

        	 // AC resource assignment
        	 if (ac != null && !(flightTime.equals(Env.ZERO)))
        	 {
 	    		 log.finest("Creating orders - AC: " + ac.getACRegistration());
	        	 String engStartTime = new SimpleDateFormat("HH:mm").format(engStart);
	        	 String engStopTime = new SimpleDateFormat("HH:mm").format(engStop);
	        	 String wheelsUpTime = new SimpleDateFormat("HH:mm").format(fs.getWheelsUp());
	        	 String wheelsDownTime = new SimpleDateFormat("HH:mm").format(fs.getWheelsDown());

        		 StringBuilder name = new StringBuilder("Flt ID: ")
	        	 	.append(fs.getFlightID())
	        	 	.append(" PIC: ")
	        	 	.append(fs.getCaptain_PIC());
        		 if (fs.getStudentPAX() != null && !fs.getStudentPAX().isEmpty()) {
	        	 	name.append(" Student/Pax: ")
	        	 		.append(fs.getStudentPAX());
        		 }
	        	 StringBuilder description = new StringBuilder("Flt ID: ")
	        	 	.append(fs.getFlightID())
	        	 	.append(" Date: ")
	        	 	.append(fltDate)
	        	 	.append(" A/C: ")
	        	 	.append(ac.getCallSign())
	        	 	.append(" Type: ")
	        	 	.append(fs.getCourseType())
	        	 	.append("\n")
	        	 	.append(" PIC: ")
	        	 	.append(fs.getCaptain_PIC());
    	 		if (fs.getStudentPAX() != null && !fs.getStudentPAX().isEmpty()) {
		        	description.append(" Student/Pax: ")
		        		.append(fs.getStudentPAX());
        	 	}
    	 		if (fs.getIntendedFlight() != null && !fs.getIntendedFlight().isEmpty())
		        	description.append("\n")
		        	 	.append("Exercizes: ")
		        	 	.append(fs.getIntendedFlight());
	        	 if (!(fs.getNumLegs()==1))
	        	 {
	        		 description.append(" # Legs: ")
	        		 	.append(fs.getNumLegs());
	        	 }
	        	 description.append("\n")
	        	 	.append("Start: ")
	        		.append(engStartTime)
	        		.append(" Stop: ")
	        		.append(engStopTime)
	        		.append(" Flight Time: ")
	        		.append(flightTime.setScale(1).toPlainString())
	        		.append("\n")
	        		.append("Up: ")
	        		.append(wheelsUpTime)
	        		.append(" Down: ")
	        		.append(wheelsDownTime)
	        		.append(" Air Time: ")
	        		.append(fs.getAirTime().setScale(1).toPlainString());
 
	        	 MResourceAssignment ra = new MResourceAssignment(fs.getCtx(),0,fs.get_TrxName());
	        	 ra.setS_Resource_ID(ac.getS_Resource_ID());
	        	 ra.setAssignDateFrom(engStart);
	        	 ra.setAssignDateTo(engStop);
	        	 // only use flight time and sim.  The sim appears as an aircraft.
	        	 ra.setQty(flightTime.add(sim));
	        	 ra.setIsConfirmed(true);
	        	 ra.setName(name.toString());
	        	 ra.setDescription(description.toString());
	        	 ra.saveEx();		        	 

	        	 if (introProduct == null)  // ignore intro flights
	        	 {	        		 
		        	// Add order line for Aircraft
		     		MOrderLine line = new MOrderLine(order);
		     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
		     		line.setM_Product_ID(acProduct.getM_Product_ID());
	     			line.setC_UOM_ID(acProduct.getC_UOM_ID());
		     		line.setDescription(description.toString());
		     		line.setQty(flightTime);
		     		line.saveEx();
	        	 
		     		// Fuel surcharge product Value is defined as 'FSC' + product classification string
		     		String where = MProduct.COLUMNNAME_Value + "=" + DB.TO_STRING("FSC" + acProduct.getClassification());
		     		MProduct fuelSurcharge = new Query(fs.getCtx(), MProduct.Table_Name, where, fs.get_TrxName())
		     									.setClient_ID()
		     									.setOnlyActiveRecords(true)
		     									.firstOnly();
		     		
		     		// Add order line for fuel surcharge
		     		if (fuelSurcharge != null)
		     		{
		     			line = new MOrderLine(order);
		     			line.setM_Product_ID(fuelSurcharge.getM_Product_ID());
		     			line.setC_UOM_ID(fuelSurcharge.getC_UOM_ID());
		     			line.setDescription("");
		     			line.setQty(flightTime);
		     			line.saveEx();
		     		}
	        	 }
	        	 else  // Add the intro flight
	        	 {
		        	// Add order line for Intro Flight
	        		description = new StringBuilder("Introductory Flight ")
	        			.append("Flt ID: " + fs.getFlightID())
		        	 	.append(" Date: ")
		        	 	.append(fltDate)
		        	 	.append(" A/C: ")
		        	 	.append(ac.getACRegistration())
		        	 	.append("\n")
		        	 	.append("PIC: ")
		        	 	.append(fs.getCaptain_PIC());
	        	 	if (fs.getStudentPAX() != null && !fs.getStudentPAX().isEmpty())
		        	 	description.append(" Pax: ")
		        	 		.append(fs.getStudentPAX());
		     		MOrderLine line = new MOrderLine(order);
		     		line.setM_Product_ID(introProduct.getM_Product_ID());
		     		line.setC_UOM_ID(introProduct.getC_UOM_ID());
		     		line.setDescription(description.toString());
		     		line.setQty(new BigDecimal(1));
		     		line.saveEx();

		        	// Add order line for Aircraft
		     		line = new MOrderLine(order);
		     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
		     		line.setM_Product_ID(acProduct.getM_Product_ID());
		     		line.setC_UOM_ID(acProduct.getC_UOM_ID());
		     		line.setDescription(description.toString());
		     		line.setQty(flightTime);
		     		line.setPrice(BigDecimal.ZERO);
		     		line.saveEx();

	        	 }
        	 } // Aircraft

        	 // Instructor resource assignment
        	 if (inst != null)
        	 {
 	    		 log.finest("Creating orders - Inst: " + inst.getC_BPartner().getName());

 	    		 StringBuilder name = new StringBuilder("Flight ID ")
	        	 	.append(fs.getFlightID())
	        	 	.append(" PIC: ")
	        	 	.append(fs.getCaptain_PIC());
 	    		 if (fs.getStudentPAX() != null && !fs.getStudentPAX().isEmpty())
	        	 	name.append(" Student/Pax: ")
	        	 		.append(fs.getStudentPAX());
	        	 StringBuilder description = new StringBuilder("Flight ID ")
	        	 	.append(fs.getFlightID())
	        	 	.append("\nFlight Type: ")
	        	 	.append(fs.getCourseType());
	        	 if (fs.getIntendedFlight() != null && !fs.getIntendedFlight().isEmpty())
	        	 	description.append(" Exercises: ")
		        	 	.append(fs.getIntendedFlight());
	        	 description.append("\n")
	        	 	.append("PIC: ")
		        	 .append(fs.getCaptain_PIC());
 	    		 if (fs.getStudentPAX() != null && !fs.getStudentPAX().isEmpty())
	        	 	description.append(" Student/Pax: ")
	        	 		.append(fs.getStudentPAX());
        	 	description.append("\n");

	        	 StringBuilder resDesc = new StringBuilder();
	        	 resDesc.append(description);
	        	 if (!(flightTime.doubleValue()==0.0))
	        		 resDesc.append(" Flight Instruction");
	        	 if (!(sim.doubleValue()==0.0))
	        		 resDesc.append(" Sim Instruction");
	        	 if (!(brief.doubleValue()==0.0))
	        		 resDesc.append(" and Ground Brief");

	        	 MResourceAssignment ra = new MResourceAssignment(fs.getCtx(),0,fs.get_TrxName());
	        	 ra.setS_Resource_ID(inst.getS_Resource_ID());
	        	 ra.setAssignDateFrom(engStart);
	        	 ra.setAssignDateTo(engStop);
	        	 // add any sim and brief time to the instructor time.
	        	 ra.setQty(flightTime.add(sim).add(brief));
	        	 ra.setIsConfirmed(true);
	        	 ra.setDescription(resDesc.toString());
	        	 ra.saveEx();		        	 

	        	 // Ignore any flight instruction charges for Intro flights.
	        	 if (introProduct == null)
	        	 {
		        	 // Add order line for flight training
		        	 if(!(flightTime.equals(Env.ZERO)))
        			 {
			        	StringBuilder fltDesc = new StringBuilder("Flight Instruction for ");
			        		fltDesc.append(description);
			     		MOrderLine line = new MOrderLine(order);
			     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
			     		line.setM_Product_ID(instProduct.getM_Product_ID());
			     		line.setC_UOM_ID(instProduct.getC_UOM_ID());
			     		line.setDescription(fltDesc.toString());
			     		line.setQty(flightTime);
			     		line.saveEx(); 
        			 }
		        	 
		        	 // Add order line for ground briefing
		        	 if(!(brief.equals(Env.ZERO)))
        			 {
		        		StringBuilder grndDesc = new StringBuilder("Ground briefing for ");
		        		grndDesc.append("Flt ID: ")
		        			.append(fs.getFlightID());
			     		MOrderLine line = new MOrderLine(order);
			     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
			     		line.setM_Product_ID(instProduct.getM_Product_ID());
			     		line.setC_UOM_ID(instProduct.getC_UOM_ID());
			     		line.setDescription(grndDesc.toString());
			     		line.setQty(brief);
			     		line.saveEx(); 
        			 }
		        	 // Add order line(s) for sim
		        	 if(!(sim.equals(Env.ZERO)))
        			 {
		        		StringBuilder simDesc = new StringBuilder("Sim Instruction for ");
		        		simDesc.append("Flt ID: ")
		        			.append(fs.getFlightID())
			        	 	.append("\nSim Type: ")
			        	 	.append(fs.getCourseType())
			        	 	.append(" Exercises: ")
			        	 	.append(fs.getIntendedFlight())
			        	 	.append("\n")
			        	 	.append("PIC: ")
			        	 	.append(fs.getCaptain_PIC())
			        	 	.append(" Student/Pax: ")
			        	 	.append(fs.getStudentPAX());
		        		
			     		MOrderLine line = new MOrderLine(order);
			     		line.setM_Product_ID(acProduct.getM_Product_ID());
			     		line.setC_UOM_ID(acProduct.getC_UOM_ID());
			     		line.setDescription(simDesc.toString());
			     		line.setQty(sim);
			     		line.saveEx(); 

		        		simDesc = new StringBuilder("Sim Instruction for ");
		        		simDesc.append("Flt ID: ")
		        			.append(fs.getFlightID());
			     		line = new MOrderLine(order);
			     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
			     		line.setM_Product_ID(instProduct.getM_Product_ID());
			     		line.setC_UOM_ID(instProduct.getC_UOM_ID());
			     		line.setDescription(simDesc.toString());
			     		line.setQty(sim);
			     		line.saveEx(); 

        			 }  //sim
		        	 
		        	 // Add order line for advanced flight training
		        	 if(advancedInstProduct != null)
		        	 {
		        		StringBuilder adv_desc = new StringBuilder(advancedInstProduct.getDescription());
		        		adv_desc.append(" for Flt ID ")
		        			.append(fs.getFlightID());
		        		MOrderLine line = new MOrderLine(order);
			     		line.setM_Product_ID(advancedInstProduct.getM_Product_ID());
			     		line.setC_UOM_ID(advancedInstProduct.getC_UOM_ID());
			     		line.setDescription(adv_desc.toString());
			     		line.setQty(flightTime.add(brief).add(sim));
			     		line.save();
		        	 }
	        	 } // Not Intro
	        	 else // Its an Intro
	        	 {
		        	 // Add an order line for the instructor
		        	 if(!flightTime.equals(Env.ZERO))
        			 {
			        	StringBuilder fltDesc = new StringBuilder("Instructor for intro ")
			        		.append(description);
			     		MOrderLine line = new MOrderLine(order);
			     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
			     		line.setM_Product_ID(instProduct.getM_Product_ID());
			     		line.setC_UOM_ID(instProduct.getC_UOM_ID());
			     		line.setDescription(fltDesc.toString());
			     		line.setQty(flightTime);
			     		line.setPrice(Env.ZERO);
			     		line.saveEx(); 
        			 }
	        	 }
        	 } // Instructor
 		} // No-show
	     		
 		if (order != null && order.getC_Order_ID() > 0) {
 			fs.setC_Order_ID(order.getC_Order_ID());
 			fs.saveEx();
 		}

 		return success;
	}	//	fsGenerateOrder
	
	private boolean fsUpdateJourneyLog(MFTUFlightsheet fs) {
		
		boolean success = true;

		if (fs.getFTU_ACJourneyLog_ID() > 0 )
			return success;

		int journeyLog_ID = MFTUACJourneyLog.updateLog(fs.getCtx(), fs, fs.get_TrxName());
		
		if (journeyLog_ID > 0) {
			fs.setFTU_ACJourneyLog_ID(journeyLog_ID);
			fs.saveEx();
		}

		return success;
	} // fsUpdateJourneyLog

	private boolean fsConsumeFuel(MFTUFlightsheet fs) {
		
		boolean success = true;
		
		// Ignore entries that already have an internal use inventory associated with them
		if (fs.getM_Inventory_ID() > 0) {
			return success;
		}
		
		BigDecimal airTime = fs.getAirTime();
    	BigDecimal fuel = fs.getFuel();

		// Look for flightsheet lines that have Flight time or fuel
		if (airTime.equals(Env.ZERO) && fuel.equals(Env.ZERO)) 
			return success;

		// Determine the quantity of fuel consumed.
		MFTUAircraft ac = new MFTUAircraft(fs.getCtx(),fs.getFTU_Aircraft_ID(),fs.get_TrxName());
		
    	int m_M_Warehouse_ID = Env.getContextAsInt(fs.getCtx(), "M_Warehouse_ID");
		
		if (ac.isGeneric() || ac.isSim()) // Ignore placeholder and sim entries.
			return success;
		
		int fuelProductID = ac.getFuelProductID();
		int	fuelChargeID = ac.getFuelChargeID();
		int locatorID = MProduct.get(fs.getCtx(), fuelProductID).getM_Locator_ID();
		int c_UOM_ID = ac.getC_UOM_ID();
		int product_UOM_ID = MProduct.get(fs.getCtx(), fuelProductID).getC_UOM_ID();
		BigDecimal avgConsumption = ac.getAvgFuelConsumption();
		BigDecimal taxiConsumption = ac.getTaxiFuelConsumption();
		
		// Get the quantity of fuel in the aircraft UOM - typically Gallons(US)
		// The consumption is airtime*avgConsumption + taxiConsumption
		// TODO not sure what to do with the flightsheet fuel entry.  Should it be 
		// added or used instead?  What units of measure?
		BigDecimal qtyFuel = airTime.multiply(avgConsumption).add(taxiConsumption);
		
		if (qtyFuel == null || qtyFuel.equals(Env.ZERO) || fuelProductID == 0) // Nothing to record
			return success;

		// Convert to product UOM
		qtyFuel = MUOMConversion.convertProductFrom(fs.getCtx(), fuelProductID, c_UOM_ID, qtyFuel);

		MInventory inv = new MInventory(fs.getCtx(),0,fs.get_TrxName());
		inv.setMovementDate(fs.getFlightDate());
		inv.setM_Warehouse_ID(m_M_Warehouse_ID);
		inv.setDescription("Fuel consummed by flight ID: " + fs.getFlightID() + ". AC: " 
							+ ac.getCallSign() + ", Air Time: " + airTime.toString());
		inv.saveEx();
		
		MInventoryLine il = new MInventoryLine(fs.getCtx(),0,fs.get_TrxName());
		il.setM_Inventory_ID(inv.getM_Inventory_ID());
		il.setInventoryType(MInventoryLine.INVENTORYTYPE_ChargeAccount);
		il.setM_Product_ID(fuelProductID);
		il.setM_Locator_ID(locatorID);
		il.setQtyInternalUse(qtyFuel);
		il.setC_Charge_ID(fuelChargeID);
		il.saveEx();
		
		inv.processIt(DocAction.ACTION_Complete);
		
		fs.setM_Inventory_ID(inv.getM_Inventory_ID());
		fs.saveEx();
		
		return success;
	}
	



}
