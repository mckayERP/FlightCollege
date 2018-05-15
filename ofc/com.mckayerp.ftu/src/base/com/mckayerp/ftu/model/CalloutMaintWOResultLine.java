package com.mckayerp.ftu.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class CalloutMaintWOResultLine extends CalloutEngine {

	/**
	 * Called from the Maint Result Detail tab when the FTU_MaintResultType is changed.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String applyResultTemplate(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (isCalloutActive())	//	prevent recursive
			return "";
		
		if (mTab.getValueAsBoolean(MFTUMaintWOResultLine.COLUMNNAME_Processed))  // Shouldn't happen
			return "";

		if (value == null || ((String) value).isEmpty())
			return "";

		int ftu_maintRequirement_id = 0;
		int ftu_maintRequirementLine_id = 0;
		
		Integer id = (Integer) mTab.getValue(MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintRequirement_ID);
		if (id != null)
			ftu_maintRequirement_id = id.intValue();

		id = (Integer) mTab.getValue(MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintRequirementLine_ID);
		if (id != null)
			ftu_maintRequirementLine_id = id.intValue();
		
		if (ftu_maintRequirement_id <= 0)
			return "";
		
		MFTUMaintRequirement mr = new MFTUMaintRequirement(ctx, ftu_maintRequirement_id, null);
		
		if (mr == null)
			return "";

		if (MFTUMaintWOResultLine.FTU_MAINTRESULTTYPE_Completed.equals((String) value))
		{
			mTab.setValue(MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintActionTaken, mr.getFTU_ResolutionTemplate());			
			mTab.setValue(MFTUMaintWOResultLine.COLUMNNAME_IsMaintReqCompleted, "Y");
		}
		else if (MFTUMaintWOResultLine.FTU_MAINTRESULTTYPE_CompletedWithFaultFound.equals((String) value))
		{
			mTab.setValue(MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintActionTaken, mr.getFTU_ResolutionFFTemplate());						
			mTab.setValue(MFTUMaintWOResultLine.COLUMNNAME_IsMaintReqCompleted, "N");
		}
		else if (MFTUMaintWOResultLine.FTU_MAINTRESULTTYPE_Deferred.equals((String) value))
		{
			mTab.setValue(MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintActionTaken, Msg.translate(Env.getCtx(), MFTUMaintWOResultLine.FTU_MAINTRESULTTYPE_Deferred));
			mTab.setValue(MFTUMaintWOResultLine.COLUMNNAME_IsMaintReqCompleted, "N");
		}

		return "";		
	}
}
