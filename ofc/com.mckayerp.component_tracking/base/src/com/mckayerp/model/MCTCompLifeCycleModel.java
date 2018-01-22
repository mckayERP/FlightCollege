package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.model.POWrapper;
import org.compiere.model.MProduct;
import org.compiere.model.Query;

public class MCTCompLifeCycleModel extends X_CT_CompLifeCycleModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4215155059419644595L;

	public MCTCompLifeCycleModel(Properties ctx,
			int M_CompLifeCycleModel_ID, String trxName) {
		super(ctx, M_CompLifeCycleModel_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTCompLifeCycleModel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MCTCompLifeCycleModel getByProduct(Properties ctx,
			MProduct product, String trxName) {

		if (product == null)
			return null;
		
		
		// Try to match the product ID first, then the product group
		String where = MCTCompLifeCycleModel.COLUMNNAME_M_Product_ID + "=?";
		
		MCTCompLifeCycleModel model = new Query(ctx, MCTCompLifeCycleModel.Table_Name, where, trxName)
										.setClient_ID()
										.setOnlyActiveRecords(true)
										.setParameters(product.getM_Product_ID())
										.first();
		
		if (model == null )  // Try to match the group
		{
			where = MCTCompLifeCycleModel.COLUMNNAME_M_Product_Group_ID + "=?";
		
			model = new Query(ctx, MCTCompLifeCycleModel.Table_Name, where, trxName)
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.setParameters(product.getM_Product_Group_ID())
							.first();

	
		}
		
		return model;
	}

}
