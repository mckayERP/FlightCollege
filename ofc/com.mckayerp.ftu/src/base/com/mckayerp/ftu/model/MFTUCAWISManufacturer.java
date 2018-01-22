package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

import com.mckayerp.ftu.model.X_FTU_CAWIS_Manufacturer;

public class MFTUCAWISManufacturer extends X_FTU_CAWIS_Manufacturer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2301481659021842300L;

	public MFTUCAWISManufacturer(Properties ctx,
			int FTU_CAWIS_Manufacturer_ID, String trxName) {
		super(ctx, FTU_CAWIS_Manufacturer_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUCAWISManufacturer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MFTUCAWISManufacturer getByName(Properties ctx,
			String FTU_CAWIS_Manufacturer, String trxName) {
		
		String where = MFTUCAWISManufacturer.COLUMNNAME_FTU_CAWIS_Manufacturer + "=?";
				
		return new Query(ctx, MFTUCAWISManufacturer.Table_Name, where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(FTU_CAWIS_Manufacturer)
				.firstOnly();
	}

}
