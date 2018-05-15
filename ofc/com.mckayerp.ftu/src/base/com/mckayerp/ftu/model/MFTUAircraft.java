package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentBOMLine;

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
     * Get the Aircraft entry based on its component ID.  Will throw a DBException if there are 
     * more than one aircraft that match that component ID.
     * @param ctx
     * @param ct_component_id - the component ID to look for.
     * @param trxName
     * @return MFTUAircraft or null if not found.
     */
	public static MFTUAircraft getByCT_Component_ID(Properties ctx, int ct_component_id, String trxName) {
		
		if (ct_component_id <= 0)
			return null;
		
		String whereClause = MFTUAircraft.COLUMNNAME_CT_Component_ID + " = ?";
		MFTUAircraft ac = (MFTUAircraft) new Query(ctx, MFTUAircraft.Table_Name, whereClause, trxName)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(ct_component_id)
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
	
	public String toString()
	{
		return this.getACRegistration();
	}

	public void updateACServiceabilityStatus() {
			
		// Get the aircraft component
		int ct_component_id = this.getCT_Component_ID();
		Properties ctx = getCtx();
		String trxName = get_TrxName();

		// Find the next due maintenance actions by usage and time for that component.
		// The times are common in that a date is the same date for all components in 
		// the BOM tree.  Usage and component life used, are specific to the component
		// and may not agree with the root component or aircraft life used. So, for 
		// time, use the date of the next maintenance action but for usage, use the 
		// next usage interval in hours to determine the next due usage.  In other 
		// words, the next usage due for the aircraft will be the current aircraft
		// usage or airframe life in hours plus the usage interval to the next 
		// maintenance action.  The action times and intervals are the target and the
		// tolerances apply to this both positive and negative.
		MFTUMaintNextAction nextHrs = MFTUMaintNextAction.getNextActionByUsage(ctx, ct_component_id, trxName);		
		MFTUMaintNextAction nextDate = MFTUMaintNextAction.getNextActionByDate(ctx, ct_component_id, trxName);
		
		// Use Remaining is based on the component life used.  The component may be a sub component so its
		// life is not necessarily the same as the aircraft.
		BigDecimal useRemaining = nextHrs.getCT_Component().getLifeUsed().subtract(nextHrs.getFTU_UsageNextDue());
		// Update the aircraft record
		setACNextMaintHrs(getAirframeTime().add(useRemaining));
		setACNextMaintHrsTol(nextHrs.getFTU_UsageIntervalTol());
		setACNextMaintDate(nextDate.getFTU_DateNextDue());
		setACNextMaintDateTol(nextDate.getFTU_TimeIntervalTol());
		
		// Check the serviceability status
		boolean serviceable = true;
		
		// Hour target plus the tolerance
		BigDecimal nextMaintHrs = getACNextMaintHrs().add(getACNextMaintHrsTol());
		
		// Date target plus the tolerance
		// Assumes next action time interval tolerance has been converted to days.
		Timestamp nextMaintDate = null;
		if (getACNextMaintDate() != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(getACNextMaintDate());
			cal.add(Calendar.DAY_OF_WEEK, getACNextMaintDateTol().intValue());
			nextMaintDate = new Timestamp(cal.getTime().getTime());
		}
		
		if (serviceable && ((nextMaintHrs.compareTo(Env.ZERO) > 0 && this.getAirframeTime().compareTo(nextMaintHrs) >= 0)
			|| (nextMaintDate != null && nextMaintDate.before(new Timestamp(System.currentTimeMillis())))))
		{
			serviceable = false;
		}
		
		if (serviceable)
			setACStatus(MFTUAircraft.ACSTATUS_Servicable);
		else
			setACStatus(MFTUAircraft.ACSTATUS_Unservicable);
	}

	public boolean checkComponentBOM(int ftu_maintWOResult_id,
			boolean createDefects) {
		
		if (this.getCT_Component_ID() <= 0)
			return true;
		
		MCTComponent comp = (MCTComponent) this.getCT_Component();
		
		List<MCTComponentBOMLine> missingLines = new ArrayList<MCTComponentBOMLine>();
		
		if (createDefects)
		{
			missingLines = comp.getUninstalledComponentBOMlines();
			
			for (MCTComponentBOMLine bomLine : missingLines)
			{
				MFTUDefectLog.createDefectFromUninstalledBOMLine(getCtx(), getFTU_Aircraft_ID(), bomLine.getCT_ComponentBOMLine_ID(), ftu_maintWOResult_id, get_TrxName());
			}
		}
		
		return missingLines.size() == 0;  // Nothing is missing
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
		
		// Moved to a process tied to the flight sheet updates - the process triggers a save
		// of the AC record which will run this beforeSave function.
		// updateACServiceabilityStatus();
		
		//  Update the time to inspection - its a fixed calculation.  Days to inspection is an SQL
		//  statement that is based on the current time.
		if (this.getTimeToInspection().compareTo(this.getACNextMaintHrs().subtract(this.getAirframeTime())) != 0)
			setTimeToInspection(this.getACNextMaintHrs().subtract(this.getAirframeTime()));
		
		return true;
	}	//	beforeSave

//	/** Do not use.  Action performed by process tied to flight sheets.
//	 * 
//	 * @param ctx
//	 * @param trxName
//	 */
//	public static void updateFleetMaintenanceStatus(Properties ctx,
//			String trxName) {
//		
//		String where = "";
//		
//		List<MFTUAircraft> fleet = new Query(ctx, MFTUAircraft.Table_Name, where, trxName)
//								.setClient_ID()
//								.setOnlyActiveRecords(true)
//								.list();
//		
//		for (MFTUAircraft ac : fleet)
//		{
//			ac.checkComponentBOM(0, true);
//			ac.updateACServiceabilityStatus();
//			ac.saveEx();
//		}
//		
//	}

}
