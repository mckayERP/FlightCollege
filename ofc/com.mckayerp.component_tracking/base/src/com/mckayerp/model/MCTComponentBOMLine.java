package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MColumn;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class MCTComponentBOMLine extends X_CT_ComponentBOMLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6697382509021814829L;

	public MCTComponentBOMLine(Properties ctx, int M_ComponentBOMLine_ID,
			String trxName) {
		super(ctx, M_ComponentBOMLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTComponentBOMLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if pre-save operation was success
	 *	@return if save was a success
	 */
	protected boolean beforeSave (boolean newRecord, boolean success) {

		return success;
	}

	/**
	 * 	Get BOM Lines for that are based on a particular product BOM Line.  Includes inactive records.
	 *  @param p_productBOMLine_id
	 *  				The ID of the PP_ProductBOMLine upon which the component BOM lines of interest are based
	 * 	@return a List of Component BOM Lines which have the given PP_ProductBOMLine_ID 

	 * @param ctx
	 * 		The context of the transaction
	 * @param pp_productBOMLine_id
	 * 		The ID of the PP_ProductBOMLine upon which the component BOM lines of interest are based				
	 * @param trxName
	 * 		The transaction name for the transaction
	 * @return a List of Component BOM Lines which have the given PP_ProductBOMLine_ID 
	 */
	public static List<MCTComponentBOMLine> getLinesByPPProductBOMLineID(Properties ctx, int pp_productBOMLine_id, String trxName)
	{
		
		final String whereClause = MCTComponentBOMLine.COLUMNNAME_PP_Product_BOMLine_ID + "=?";

		List<MCTComponentBOMLine> lines = new Query(ctx, MCTComponentBOMLine.Table_Name, whereClause, trxName)
											.setParameters(pp_productBOMLine_id)
											.setClient_ID()
											.setOrderBy(MCTComponentBOMLine.COLUMNNAME_Line)
											.list();
		return lines;

	}
}
