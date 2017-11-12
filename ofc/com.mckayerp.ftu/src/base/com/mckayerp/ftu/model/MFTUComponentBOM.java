package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.PO;
import org.compiere.model.Query;
import org.eevolution.model.MPPProductBOMLine;

public class MFTUComponentBOM extends X_FTU_ComponentBOM {

	private List<MFTUComponentBOMLine> m_lines;

	public MFTUComponentBOM(Properties ctx, int M_ComponentBOM_ID, String trxName) {
		super(ctx, M_ComponentBOM_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUComponentBOM(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	public static List<MFTUComponentBOM> getByPP_ProductBOM(Properties ctx, int pp_product_bom_id, String trxName)
	{
		String where = MFTUComponentBOM.COLUMNNAME_PP_Product_BOM_ID + "=?";
		
		return new Query(ctx, MFTUComponentBOM.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(pp_product_bom_id)
					.list();
	}
	
	
	public List<MFTUComponentBOMLine> getLines() {

		return getLines(false);
	}

	/**
	 * 	Get BOM Lines for Product BOM
	 * 	@return BOM Lines
	 */
	public  List<MFTUComponentBOMLine> getLines(boolean reload)
	{
		if (this.m_lines == null || reload)
		{
			final String whereClause = MFTUComponentBOMLine.COLUMNNAME_FTU_ComponentBOM_ID+"=?";
			this.m_lines = new Query(getCtx(), MFTUComponentBOMLine.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{getFTU_ComponentBOM_ID()})
											.setClient_ID()
											.setOnlyActiveRecords(true)
											.setOrderBy(MFTUComponentBOMLine.COLUMNNAME_Line)
											.list();
		}
		return this.m_lines;
	}	//	getLines    		

}
