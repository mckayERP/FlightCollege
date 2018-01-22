package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MFTUMaintRequirementLine extends X_FTU_MaintRequirementLine {

	public MFTUMaintRequirementLine(Properties ctx,
			int FTU_MaintRequirementLine_ID, String trxName) {
		super(ctx, FTU_MaintRequirementLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUMaintRequirementLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		/** Prevents saving
		log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
		log.saveError("FillMandatory", Msg.getElement(getCtx(), "PriceEntered"));
		/** Issues message
		log.saveWarning(AD_Message, message);
		log.saveInfo (AD_Message, message);
		**/
		return super.beforeSave(newRecord);
	}	//	beforeSave

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		return super.afterSave(newRecord, success);
	}	//	afterSave

}
