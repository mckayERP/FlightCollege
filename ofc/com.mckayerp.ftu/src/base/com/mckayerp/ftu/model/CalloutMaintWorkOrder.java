package com.mckayerp.ftu.model;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.Callout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class CalloutMaintWorkOrder extends CalloutEngine {

	public String aircraft (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		if (isCalloutActive())	//	prevent recursive
			return "";
		
		Integer ftu_aircraft_id = (Integer)value;
		if (ftu_aircraft_id == null || ftu_aircraft_id.intValue() == 0)
			return "";

		MFTUAircraft ac = new MFTUAircraft(ctx, ftu_aircraft_id, null);
		
		if (ac == null)
			return "";

		mTab.setValue(I_FTU_MaintWorkOrder.COLUMNNAME_CT_Component_ID, ac.getCT_Component_ID());

		return "";
	}

	public String component (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		if (isCalloutActive())	//	prevent recursive
			return "";
		
		Integer ct_component_id = (Integer)value;
		if (ct_component_id == null || ct_component_id.intValue() == 0)
			return "";

		MFTUAircraft ac = MFTUAircraft.getByCT_Component_ID(ctx, ct_component_id, null);
		
		if (ac == null)
		{
			mTab.setValue(I_FTU_MaintWorkOrder.COLUMNNAME_FTU_Aircraft_ID, null);
		}
		else
		{
			mTab.setValue(I_FTU_MaintWorkOrder.COLUMNNAME_FTU_Aircraft_ID, ac.getFTU_Aircraft_ID());
		}
		return "";
	}

}
