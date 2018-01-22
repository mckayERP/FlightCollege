package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

import com.mckayerp.ftu.model.X_FTU_CAWIS_Model;

public class MFTUCAWISModel extends X_FTU_CAWIS_Model {

	public MFTUCAWISModel(Properties ctx, int FTU_CAWIS_Model_ID,
			String trxName) {
		super(ctx, FTU_CAWIS_Model_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUCAWISModel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MFTUCAWISModel getByManufacturerAndName(Properties ctx,
			int ftu_cawis_manufacturer_id, String ftu_cawis_model, String trxName) {
		
		String where = MFTUCAWISModel.COLUMNNAME_FTU_CAWIS_Manufacturer_ID + "=?"
				+ " AND " + MFTUCAWISModel.COLUMNNAME_FTU_CAWIS_Model + "=?";
				
				
		return new Query(ctx, MFTUCAWISModel.Table_Name, where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(ftu_cawis_manufacturer_id, ftu_cawis_model)
				.firstOnly();
	}

}
