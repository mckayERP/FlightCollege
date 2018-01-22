package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Msg;
import org.eevolution.model.MPPProductBOMLine;

public class MCTComponentBOM extends X_CT_ComponentBOM {

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
	 * 	Get BOM Lines for Component BOM
	 *  @param p_productBOMLine_id
	 *  				The ID of the PP_ProductBOMLine upon which the component BOM lines of interest are based
	 * 	@return a List of Component BOM Lines which have the given PP_ProductBOMLine_ID 
	 */
	public List<MCTComponentBOMLine> getLines(int pp_productBOMLine_id)
	{
		
		final String whereClause = MCTComponentBOMLine.COLUMNNAME_CT_ComponentBOM_ID+"=?"
										+ " AND " + MCTComponentBOMLine.COLUMNNAME_PP_Product_BOMLine_ID + "=?";

		List<MCTComponentBOMLine> lines = new Query(getCtx(), MCTComponentBOMLine.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{getCT_ComponentBOM_ID(), pp_productBOMLine_id})
											.setClient_ID()
											.setOnlyActiveRecords(true)
											.setOrderBy(MCTComponentBOMLine.COLUMNNAME_Line)
											.list();
		return lines;

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
		
		return true;
	}	//	beforeSave

}
