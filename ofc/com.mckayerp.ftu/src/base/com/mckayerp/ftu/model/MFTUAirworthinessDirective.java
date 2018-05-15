package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.DB;

import com.mckayerp.model.MCTComponent;

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

	/**
	 * Get an array list of MCTComponent objects that are affected by this AD.  The 
	 * component products must be on the list of application and the product component
	 * must have the "Is AD Applies" field selected.
	 * @param ctx
	 * @param ad_id
	 * @param trxName
	 * @return an Array List of MCTComponents to which this AD applies. If none, an empty list.
	 */
	public static List<MCTComponent> getAffectedComponents(Properties ctx,
			int ad_id, String trxName) {
		
		List<MCTComponent> comps = new ArrayList<MCTComponent>();
		
		String where = X_FTU_ADApplication.COLUMNNAME_FTU_AirworthinessDirective_ID + "=" + ad_id
				+ " AND " + X_FTU_ADApplication.COLUMNNAME_FTU_IsADApplies + "='Y'";
		
		List<MFTUADApplication> adApps = new Query(ctx, X_FTU_ADApplication.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.list();
		
		for (MFTUADApplication adApp : adApps)
		{
			List<MFTUADApplicability> appList = MFTUADApplicability.getByADApplication(ctx, adApp.getFTU_ADApplication_ID(), trxName);
			for (MFTUADApplicability app : appList)
			{
				if (app.isFTU_IsADApplies())
				{
					comps.add((MCTComponent) app.getCT_Component());
				}
					
			}
		}
		return comps;
	}

}
