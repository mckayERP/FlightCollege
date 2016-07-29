package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.DB;

public class MFTUAircraft extends X_FTU_Aircraft {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5290911219260845457L;

	public MFTUAircraft(Properties ctx, int FTU_Aircraft_ID, String trxName) {
		super(ctx, FTU_Aircraft_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUAircraft(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MFTUAircraft get(Properties ctx, int FTU_Aircraft_ID, String trxName) {
		if (FTU_Aircraft_ID == 0)
			return null;
		return new MFTUAircraft(ctx, FTU_Aircraft_ID, trxName);
	}
	
    /**
     * Get the Aircraft entry based on the Call Sign.  Will throw a DBException if there are 
     * more than one aircraft that match that call sign.
     * @param ctx
     * @param callSign - from the Flightsheet system
     * @param trxName
     * @return MFTUAircraft or null if not found.
     */
	public static MFTUAircraft getByCallSign(Properties ctx, String callSign, String trxName) {
		
		if (callSign == null)
			return null;
		
		String whereClause = MFTUAircraft.COLUMNNAME_CallSign + " = " + DB.TO_STRING(callSign);
		MFTUAircraft ac = (MFTUAircraft) new Query(ctx, MFTUAircraft.Table_Name, whereClause, trxName)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.firstOnly();
		return ac;
	}

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success) {
		
		// TODO - test aircraft stats for maintenance triggers
		
		return success;
	}

}
