package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.Query;

public class MFTUCompLifeCycleModel extends X_FTU_CompLifeCycleModel {

	public MFTUCompLifeCycleModel(Properties ctx,
			int FTU_CompLifeCycleModel_ID, String trxName) {
		super(ctx, FTU_CompLifeCycleModel_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUCompLifeCycleModel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MFTUCompLifeCycleModel getByProduct(Properties ctx,
			MProduct product, String trxName) {

		if (product == null)
			return null;
		
		// Try to match the product ID first, then the product group
		String where = MFTUCompLifeCycleModel.COLUMNNAME_M_Product_ID + "=?";
		
		MFTUCompLifeCycleModel model = new Query(ctx, MFTUCompLifeCycleModel.Table_Name, where, trxName)
										.setClient_ID()
										.setOnlyActiveRecords(true)
										.setParameters(product.getM_Product_ID())
										.first();
		
		if (model == null )  // Try to match the group
		{
			where = MFTUCompLifeCycleModel.COLUMNNAME_M_Product_Group_ID + "=?";
		
			model = new Query(ctx, MFTUCompLifeCycleModel.Table_Name, where, trxName)
											.setClient_ID()
											.setOnlyActiveRecords(true)
											.setParameters(product.getM_Product_Group_ID())
											.first();
	
		}
		return model;
	}

}
