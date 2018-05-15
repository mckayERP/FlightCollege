package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

public class MCTComponentBOM extends X_CT_ComponentBOM {

//	private CLogger log = CLogger.getCLogger(this.getClass());
	
	private List<MCTComponentBOMLine> m_lines;

	public MCTComponentBOM(Properties ctx, int CT_ComponentBOM_ID, String trxName) {
		super(ctx, CT_ComponentBOM_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTComponentBOM(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	public static List<MCTComponentBOM> getByPP_ProductBOM(Properties ctx, int pp_product_bom_id, String trxName)
	{
		String where = MCTComponentBOM.COLUMNNAME_PP_Product_BOM_ID + "=?";
		
		return new Query(ctx, MCTComponentBOM.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(pp_product_bom_id)
					.list();
	}
	
	public static MCTComponentBOM getByCT_Component_ID(Properties ctx, int ct_component_id, String trxName)
	{
		String where = MCTComponentBOM.COLUMNNAME_CT_Component_ID + "=?";
		
		return new Query(ctx, MCTComponentBOM.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ct_component_id)
					.firstOnly();
	}
	
	public List<MCTComponentBOMLine> getLines() {

		return getLines(false);
	}

	/**
	 * 	Get the Component BOM Line corresponding to a Product BOM line
	 *  @param p_productBOMLine_id
	 *  				The ID of the PP_ProductBOMLine upon which the component BOM lines of interest are based
	 * 	@return a List of Component BOM Lines which have the given PP_ProductBOMLine_ID 
	 */
	public MCTComponentBOMLine getLine(int pp_productBOMLine_id)
	{
		
		final String whereClause = MCTComponentBOMLine.COLUMNNAME_CT_ComponentBOM_ID+"=?"
										+ " AND " + MCTComponentBOMLine.COLUMNNAME_PP_Product_BOMLine_ID + "=?";

		return new Query(getCtx(), MCTComponentBOMLine.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{getCT_ComponentBOM_ID(), pp_productBOMLine_id})
											.setClient_ID()
											.setOnlyActiveRecords(true)
											.setOrderBy(MCTComponentBOMLine.COLUMNNAME_Line)
											.firstOnly();

	}
	
	/**
	 * 	Get BOM Lines for Component BOM
	 * 	@return BOM Lines
	 */
	public  List<MCTComponentBOMLine> getLines(boolean reload)
	{
		if (this.m_lines == null || reload)
		{
			final String whereClause = MCTComponentBOMLine.COLUMNNAME_CT_ComponentBOM_ID+"=?";
			this.m_lines = new Query(getCtx(), MCTComponentBOMLine.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{getCT_ComponentBOM_ID()})
											.setClient_ID()
											.setOnlyActiveRecords(true)
											.setOrderBy(MCTComponentBOMLine.COLUMNNAME_Line)
											.list();
		}
		return this.m_lines;
	}	//	getLines    		

	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{

		/** Prevents saving
		log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
		log.saveError("FillMandatory", Msg.getElement(getCtx(), "PriceEntered"));
		/** Issues message
		log.saveWarning(AD_Message, message);
		log.saveInfo (AD_Message, message);
		**/

		if (newRecord)
		{
			// Check for existing records.  There can be only on component BOM
			// as the BOM represents the physical reality
			int ct_component_id = this.getCT_Component_ID();
			
			String where = MCTComponentBOM.COLUMNNAME_CT_Component_ID + "=?";
			
			int count =  new Query(getCtx(), MCTComponentBOM.Table_Name, where, get_TrxName())
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(ct_component_id)
								.count();

			if (count > 0)
			{
				// TODO check translation
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@" + MCTComponentBOM.COLUMNNAME_CT_ComponentBOM_ID + "@ already exists"));
				return false;
			}
		}
		
		if (this.getPP_Product_BOM_ID() <= 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), MCTComponentBOM.COLUMNNAME_PP_Product_BOM_ID));
		}
		return true;
	}	//	beforeSave

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		
		if (newRecord)
		{
			return createOrUpdateBOMLines(newRecord, success);
		}
		
		return success;
	}	//	afterSave

	public void createOrUpdateBOMLines()
	{
		createOrUpdateBOMLines(this.is_new(), true);
	}
	
	private boolean createOrUpdateBOMLines(boolean newRecord, boolean success) {

		if (!success)
			return success;

		if (this.getPP_Product_BOM_ID() <= 0) // No product BOM - shouldn't happen
			return success;
		
		MPPProductBOM bomMaster = (MPPProductBOM) this.getPP_Product_BOM();
		MPPProductBOMLine[] bomMasterLines = bomMaster.getLines();

		for (MPPProductBOMLine bomLine : bomMasterLines)
		{
			//  Only add lines that have discreet quantities for products that are being
			//  tracked as components
			if (bomLine.getQtyBOM() == null 
					|| bomLine.getQtyBOM().equals(Env.ZERO)
					|| bomLine.getM_Product_ID() <= 0
					|| !bomLine.getM_Product().isTrackAsComponent())
					continue;

			
			MCTComponentBOMLine compBOMLine = this.getLine(bomLine.getPP_Product_BOMLine_ID());

			if (compBOMLine == null || compBOMLine.getCT_ComponentBOMLine_ID() == 0)  // Add the line
			{

				compBOMLine = new MCTComponentBOMLine(getCtx(), 0, get_TrxName());
				compBOMLine.setCT_ComponentBOM_ID(this.getCT_ComponentBOM_ID());
				compBOMLine.setPP_Product_BOMLine_ID(bomLine.getPP_Product_BOMLine_ID());
				compBOMLine.setDescription(bomLine.getDescription());
				compBOMLine.setLine(bomLine.getLine());
				compBOMLine.setQtyRequired(bomLine.getQty());
				compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());  // Master - could have substitutes
				compBOMLine.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
				compBOMLine.setIsActive(bomLine.isActive());
				compBOMLine.saveEx();
				
			}
			else  //  Update the lines
			{
				
				// Update the info
				// Easy changes
				compBOMLine.setDescription(bomLine.getDescription());
				compBOMLine.setQtyRequired(bomLine.getQty()); // Not percentage
										
				// Difficult changes
				if (compBOMLine.getMaster_Product_ID() > 0 
					&& compBOMLine.getMaster_Product_ID() != bomLine.getM_Product_ID() 
					&& compBOMLine.getCT_Component_ID() > 0)
				{
					// Problem. The product BOM has changed product but the component BOM has a 
					// sub component installed in that spot.  Check if the installed component 
					// is a valid substitute.
					MProduct installedProduct = (MProduct) compBOMLine.getM_Product();  // Should be non-null as a component exists.
					MProduct masterProduct = (MProduct) bomLine.getM_Product();
					if (installedProduct.isValidSubstitueFor(masterProduct))
					{
						// Just update the master product.  The installed product is valid
						compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());
					}
					else
					{
						// Need to uninstall the component.  Throw error to prevent changes.
						String errorMsg = this.toString() + " has sub-component " + compBOMLine.getCT_Component().toString();
						errorMsg += " installed on line " + compBOMLine.getLine() + ".  BOM Master Product has changed to " + bomLine.getM_Product().toString();
						errorMsg += " Please uninstall the component and attempt the update again.";
						throw new AdempiereException(errorMsg);
					}
				}
				compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());

				// Update the ASI if there is no component
				if (compBOMLine.getCT_Component_ID() <= 0)
				{
					compBOMLine.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
				}
				compBOMLine.saveEx();								

			}
		}
		
		return success;
	}
	
	/**
	 * Add a BOM skeleton to the Component.  Does not install component parts
	 * in the BOM.  The BOM added should represent the physical reality of the
	 * component as assembled.
	 * 
	 * @param ctx
	 * 			context properties
	 * @param bom
	 * 			the Product BOM to add
	 * @param trxName
	 * 			Transaction Name
	 * @return the Component BOM added.  Returns null if the Product BOM is null or the BOM
	 * 				is not a valid Master. If new, the object is saved.
	 */
	public static MCTComponentBOM createOrUpdateComponentBOM(Properties ctx, int ct_component_id, String trxName) {

		if (ct_component_id <= 0)
		{
			throw new IllegalArgumentException("CT_Component_ID <= 0");
		}
		
		//  Find the parent component
		MCTComponent comp = new MCTComponent(ctx, ct_component_id, trxName);
		
		//  Find the parent product
		MProduct product =  (MProduct) comp.getM_Product();
		
		if (!product.isBOM())
		{
			return null;
		}

		//  Get the component BOM - there should only be one as the BOM represents a physical reality where the product BOM
		//  can represent one of a number of design specs.  Limit of one BOM per component is in the MCTComponentBOM beforeSave().
		//  TODO - deal with engineering changes to components that require multiple BOMs. Not sure this is required.
		MCTComponentBOM componentBOM = MCTComponentBOM.getByCT_Component_ID(ctx, ct_component_id, trxName);
		
		//  Check if the BOM needs to be added.  This can happen if the component was defined BEFORE
		//  the product BOM was created.
		if (componentBOM == null)
		{

			MPPProductBOM bomMaster = null;
			
			List<MPPProductBOM> bomList = MPPProductBOM.getProductBOMs(product);
			for (MPPProductBOM bom : bomList)
			{
				//  Find a valid master BOM to use as the master.
				if (bom.getBOMUse().equals(MPPProductBOM.BOMUSE_Master) 
						&& bom.isValidFromTo(Env.getContextAsDate(ctx, "#Date")))
				{
					bomMaster = bom;
					break;
				}
			}
			
			if (bomMaster == null)
			{
				return null;
			}
			
			// We should have a valid BOM master

			// TODO In a production environment, the BOM may be created
			// at the time of production using a different BOM Use type.  
			// The component then should retain its production configuration.
				
			// Create a new BOM.  The check that the component has only one BOM is performed
			// in the MCTComponentBOM model beforeSave(). 
			componentBOM = new MCTComponentBOM(ctx, 0, trxName);
			componentBOM.setCT_Component_ID(ct_component_id);
			componentBOM.setPP_Product_BOM_ID(bomMaster.getPP_Product_BOM_ID());
			componentBOM.setName(bomMaster.getName());
			componentBOM.setDescription(bomMaster.getDescription());
			componentBOM.saveEx();
		}	

		componentBOM.createOrUpdateBOMLines();
		
		return componentBOM;	
	}		
}
