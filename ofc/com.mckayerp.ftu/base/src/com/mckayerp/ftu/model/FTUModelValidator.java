package com.mckayerp.ftu.model;

import java.util.List;

import org.compiere.model.MClient;
import org.compiere.model.MCostDetail;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.eevolution.model.MPPMRP;

public class FTUModelValidator implements ModelValidator {

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());
	/** Client			*/
	private int		m_AD_Client_ID = -1;

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		if (client != null)
		{	
			m_AD_Client_ID = client.getAD_Client_ID();
		}
		engine.addModelChange(MOrder.Table_Name, this);
		engine.addModelChange(MInventory.Table_Name, this);
		engine.addDocValidate(MOrder.Table_Name, this);
		engine.addDocValidate(MInventory.Table_Name, this);
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
		
		if (po instanceof MOrder) {	
			log.fine(po.get_TableName() + " Type: "+type);
			boolean isChange = (TYPE_AFTER_NEW == type || TYPE_AFTER_CHANGE == type);
			boolean isDelete = (TYPE_BEFORE_DELETE == type);
			boolean isReleased = false;
			boolean isVoided = false;
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
			log.fine(po.get_TableName() + " Type: "+type);
			boolean isDelete = (TYPE_AFTER_DELETE == type);

			// If M_Inventory entries are deleted, check if these are referenced from the flightsheet and remove the reference.
			if (isDelete && ((MOrder) po).getC_Order_ID() > 0) {
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
			boolean isVoided = (this.TIMING_AFTER_REVERSECORRECT == timing || this.TIMING_AFTER_VOID == timing);
			
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
			boolean isVoided = (this.TIMING_AFTER_REVERSECORRECT == timing || this.TIMING_AFTER_VOID == timing);
			
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
			}
		}

		return null;
	}
}
