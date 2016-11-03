package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.DB;

public class MFTUAirworthinessDirective extends X_FTU_AirworthinessDirective {

	public MFTUAirworthinessDirective(Properties ctx,
			int FTU_AirworthinessDirective_ID, String trxName) {
		super(ctx, FTU_AirworthinessDirective_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUAirworthinessDirective(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MFTUAirworthinessDirective get(Properties ctx,
			String adNumber, String trxName) {
		
		if (adNumber == null || adNumber.length() == 0) {
			return null;
		}
	
		String where = X_FTU_AirworthinessDirective.COLUMNNAME_FTU_ADNumber + "=" + DB.TO_STRING(adNumber); 		
		MFTUAirworthinessDirective ad = new Query(ctx,X_FTU_AirworthinessDirective.Table_Name, where, trxName)
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.firstOnly();
		
		if (ad == null)
		{
			
			ad = new MFTUAirworthinessDirective(ctx, 0, trxName);
			ad.setFTU_ADNumber(adNumber);
			
		}

		return ad;
	}

}
