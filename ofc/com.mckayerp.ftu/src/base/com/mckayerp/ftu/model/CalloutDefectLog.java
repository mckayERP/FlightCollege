package com.mckayerp.ftu.model;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.Callout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class CalloutDefectLog extends CalloutEngine {

	public String aircraft (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		if (isCalloutActive())	//	prevent recursive
			return "";
		
		Integer ftu_aircraft_id = (Integer)value;
		if (ftu_aircraft_id == null || ftu_aircraft_id.intValue() == 0)
			return "";

		MFTUAircraft ac = new MFTUAircraft(ctx, ftu_aircraft_id, null);
		
		if (ac == null || ac.getAirframeTime() == null)
			return "";

		mTab.setValue(I_FTU_DefectLog.COLUMNNAME_TotalAirframeTime, ac.getAirframeTime());

		return "";
	}
	
	public String isDeferred(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (value != null && value instanceof Boolean && (Boolean) value) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_DeferredDate, now);
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_DeferredBy, Env.getAD_User_ID(ctx));
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_DocAction, MFTUDefectLog.ACTION_Defer);
		}
		else { 
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_DeferredDate, null);
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_DeferredBy, null);
		}
		return "";
	}

	public String isRepaired(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (value != null && value instanceof Boolean && (Boolean) value) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_RepairedDate, now);
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_RectifiedBy, Env.getAD_User_ID(ctx));
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_DocAction, MFTUDefectLog.ACTION_Rectify);
		}
		else {
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_RepairedDate, null);
			mTab.setValue(I_FTU_DefectLog.COLUMNNAME_RectifiedBy, null);
		}
		return "";
	}
}
