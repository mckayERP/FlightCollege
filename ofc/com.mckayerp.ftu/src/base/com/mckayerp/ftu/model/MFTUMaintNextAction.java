package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.Env;

import com.mckayerp.model.I_CT_Component;
import com.mckayerp.model.MCTComponent;

public class MFTUMaintNextAction extends X_FTU_MaintNextAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5748070655074690087L;
	private int m_startingMR_id;

	public MFTUMaintNextAction(Properties ctx, int FTU_MaintNextAction_ID,
			String trxName) {
		super(ctx, FTU_MaintNextAction_ID, trxName);
	}

	public MFTUMaintNextAction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Get the next maintenance action for a component and maintenance requirement.
	 * @param ctx
	 * @param c_component_id
	 * @param ftu_maintRequirement_id
	 * @param trxName
	 * @return
	 */
	public static MFTUMaintNextAction getByComponentAndMaintRequirement(Properties ctx, int c_component_id
					, int ftu_maintRequirement_id, String trxName) {
		
		String where = MFTUMaintNextAction.COLUMNNAME_CT_Component_ID + "=?"
				+ " AND " + MFTUMaintNextAction.COLUMNNAME_FTU_MaintRequirement_ID + "=?";
		
		MFTUMaintNextAction na =  new Query(ctx, I_FTU_MaintNextAction.Table_Name,where,trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(c_component_id, ftu_maintRequirement_id)
					.firstOnly();
		
		if (na == null)
		{
			na = new MFTUMaintNextAction(ctx, 0, trxName);
			na.setCT_Component_ID(c_component_id);
			na.setFTU_MaintRequirement_ID(ftu_maintRequirement_id);
		}

		return na;
	}
	
	/**
	 * 	Called before Save for Pre-Save Operation. 
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		if (getCT_Component_ID() <= 0 || getFTU_MaintRequirement_ID() <=0)
			return false;
		
		MFTUMaintRequirement mr = this.getFTU_MaintRequirement();	
		String complianceType = mr.getFTU_ComplianceType();
		
		MCTComponent ct = this.getCT_Component();
		
		// Update the interval tolerances
		this.setFTU_TimeIntervalTol(mr.getFTU_TimeIntervalTol());
		this.setFTU_UsageIntervalTol(mr.getFTU_UsageIntervalTol());
		
		//  Determine if tolerances are to be applied to the next due time/use.  
		//  Where there is no record of a prior maintenance action, assume the
		//  first record sets the starting point for future maintenance actions.
		//  In other words, for repetitive actions, the 2nd next due date/use will
		//  ignore any tolerances applied to the first action and will be based 
		//  solely on the interval between the actions.  From then on, tolerance
		//  rules will apply.
		boolean ignoreAppliedTolerances = MFTUMaintWOResultLine.countByNextAction(this.getCtx(), this.getFTU_MaintNextAction_ID(), this.get_TrxName()) <= 1;

		if (MFTUMaintRequirement.FTU_COMPLIANCETYPE_BeforeDeadlineDate.equals(complianceType)) 
		{
			// Only set the next due date if there is no last done date
			if (this.getFTU_DateLastDone() == null)  
			{
				this.setFTU_DateNextDue(mr.getFTU_DeadlineDate());
			}
		}
		else if (MFTUMaintRequirement.FTU_COMPLIANCETYPE_BeforeFurtherFlightOrUse.equals(complianceType)) 
		{
			// Only set the usage if there is no last usage done time
			// Don't update the usage next due time if its already been set once
			if (newRecord && this.getFTU_UsageLastDone().compareTo(Env.ZERO) == 0)
			{
				this.setFTU_UsageNextDue(ct.getLifeUsed());
			}
			// Only set the next due date if there is no last done date
			if (this.getFTU_DateLastDone() == null)  
			{
				this.setFTU_DateNextDue(mr.getFTU_DeadlineDate());
			}
		}
		else if (MFTUMaintRequirement.FTU_COMPLIANCETYPE_OnceWithinNext.equals(complianceType))
		{
			if (mr.getFTU_TimeInterval().compareTo(Env.ZERO) > 0 && this.getFTU_DateLastDone() == null)
			{
				// Assumes interval has been converted to days.
				Calendar cal = Calendar.getInstance();
				cal.setTime(mr.getFTU_DateAfter());
				cal.add(Calendar.DAY_OF_WEEK, mr.getFTU_TimeInterval().intValue());
				this.setFTU_DateNextDue(new Timestamp(cal.getTime().getTime()));
			}
			if (mr.getFTU_UsageInterval().compareTo(Env.ZERO) > 0 && this.getFTU_UsageLastDone().compareTo(Env.ZERO) == 0)
			{
				// Assumes similar UOM
				this.setFTU_UsageNextDue(ct.getLifeUsed().add(mr.getFTU_UsageInterval()));
			}
		}
		else if (MFTUMaintRequirement.FTU_COMPLIANCETYPE_RepeatEvery.equals(complianceType))
		{
			
			// A simple repetitive schedule - unless there is a phase relationship with another
			// maint req.  In this case, the next due time is that minimum of the simple calculation
			// or the phase time from the next due of the related requirement.
			// The phase interval only applies to usage
			
			if (mr.getFTU_TimeInterval().compareTo(Env.ZERO) > 0)
			{
				// TODO - check the start date of repetitive actions
				Timestamp lastDone = this.getFTU_DateLastDone();
				if (lastDone == null)
					lastDone = mr.getFTU_DateAfter();
				
				if (lastDone == null)
					lastDone = ct.getStartOfLifeDate();
				
				if (lastDone == null)
				{
					// Fall back to today - immediately due
					this.setFTU_DateNextDue(Env.getContextAsDate(getCtx(), "#Date"));
				}
				else
				{
					BigDecimal dateInterval = mr.getFTU_TimeInterval();	
					BigDecimal dateTolApplied = this.getFTU_DateTolApplied();
		
					BigDecimal tolToApply = Env.ZERO;
					String timeTolType = mr.getFTU_TimeToleranceType();
					
					if (X_FTU_MaintRequirement.FTU_TIMETOLERANCETYPE_Cummulative.equals(timeTolType))
					{
						tolToApply = Env.ZERO;
					}
					else if (X_FTU_MaintRequirement.FTU_TIMETOLERANCETYPE_Fixed.equals(timeTolType))
					{
						tolToApply = dateTolApplied.negate();
					}
					else if (X_FTU_MaintRequirement.FTU_TIMETOLERANCETYPE_NotExtended.equals(timeTolType))
					{
						if (dateTolApplied.compareTo(Env.ZERO)>0)
						tolToApply = dateTolApplied.negate();
					}
					// Assumes interval has been converted to days. Add the tolerance in days to the next due date
					long nextDueMillies = lastDone.getTime();
					nextDueMillies += TimeUnit.MILLISECONDS.convert(dateInterval.longValue(), TimeUnit.DAYS);
					if (!ignoreAppliedTolerances)
						nextDueMillies += TimeUnit.MILLISECONDS.convert(tolToApply.longValue(), TimeUnit.DAYS);
					this.setFTU_DateNextDue(new Timestamp(nextDueMillies));
				}
			}
			
			if (mr.getFTU_UsageInterval().compareTo(Env.ZERO) > 0)
			{
				BigDecimal interval = mr.getFTU_UsageInterval();
				
				// TODO - check the start date of repetitive actions
				BigDecimal lastDone = this.getFTU_UsageLastDone();
				if (lastDone.compareTo(Env.ZERO) == 0)
					lastDone = ct.getLifeUsed();
				
				BigDecimal usageTolApplied = this.getFTU_UsageTolApplied();
	
				BigDecimal tolToApply = Env.ZERO;
				String usageTolType = mr.getFTU_UsageToleranceType();
				
				if (X_FTU_MaintRequirement.FTU_USAGETOLERANCETYPE_Cummulative.equals(usageTolType))
				{
					tolToApply = Env.ZERO;
				}
				else if (X_FTU_MaintRequirement.FTU_USAGETOLERANCETYPE_Fixed.equals(usageTolType))
				{
					tolToApply = usageTolApplied.negate();
				}
				else if (X_FTU_MaintRequirement.FTU_USAGETOLERANCETYPE_NotExtended.equals(usageTolType))
				{
					if (usageTolApplied.compareTo(Env.ZERO)>0)
					tolToApply = usageTolApplied.negate();
				}
				// Assumes interval has been converted to component usage UOM.
				BigDecimal nextDue = lastDone.add(interval);
				if (!ignoreAppliedTolerances)
					nextDue = nextDue.add(tolToApply);
				
				// Check for a phase relationship. A phase interval = 0 means in phase/sync.
				if (mr.getFTU_PhaseFromMaintReq_ID() > 0 && mr.getFTU_PhaseInterval().compareTo(Env.ZERO) >= 0)
				{
					MFTUMaintNextAction phaseNA = 
							MFTUMaintNextAction.getByComponentAndMaintRequirement(getCtx(), getCT_Component_ID(), 
									mr.getFTU_PhaseFromMaintReq_ID(), get_TrxName());
					
					if (phaseNA == null && this.getCT_Component().getRoot_Component_ID() > 0)
					{
						// Try to link through the root component
						int root_id = this.getCT_Component().getRoot_Component_ID();
						phaseNA = MFTUMaintNextAction.getByComponentAndMaintRequirement(getCtx(), root_id, 
								mr.getFTU_PhaseFromMaintReq_ID(), get_TrxName());

					}
					
					if (phaseNA != null)
					{
						// The phase next due usage is the phase related MR next due usage plus the phase interval
						BigDecimal phaseNextDue = phaseNA.getFTU_UsageNextDue().add(mr.getFTU_PhaseInterval());
						
						// Pick the minimum - to be conservative and preserve the phase.
						if (nextDue.compareTo(phaseNextDue) > 0)
						{
							nextDue = phaseNextDue;
						}
					}
					else
					{
						log.warning("Unable to link with phase maintenance requirement.  No similar component.");
					}
				}
				
				this.setFTU_UsageNextDue(nextDue);
			}
		}
		return true;
	}
	
	public MFTUMaintRequirement getFTU_MaintRequirement() throws RuntimeException
    {
		return (MFTUMaintRequirement) MTable.get(getCtx(), I_FTU_MaintRequirement.Table_Name)
			.getPO(getFTU_MaintRequirement_ID(), get_TrxName());	}

	public MCTComponent getCT_Component() throws RuntimeException
    {
		return (MCTComponent) MTable.get(getCtx(), I_CT_Component.Table_Name)
			.getPO(getCT_Component_ID(), get_TrxName());	}

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{		
		
		if (!newRecord && this.is_ValueChanged(COLUMNNAME_FTU_UsageNextDue))
		{
			// See if this next action is has a phase relationship with other actions
			// and update the phase relationship.  Doesn't apply to new records as 
			// the ID wouldn't have been available for use as a phase from maint req.
			
			// Check for circular relationships - stop if the same maint requirement is hit twice
			if (m_startingMR_id == 0)
				m_startingMR_id = getFTU_MaintRequirement_ID();
			
			List<MFTUMaintRequirement> phaseList = MFTUMaintRequirement.getByFTU_PhaseFromMaintReq(getCtx(), getFTU_MaintRequirement_ID(), get_TrxName());
			
			for (MFTUMaintRequirement mr : phaseList)
			{
				if (mr.getFTU_MaintRequirement_ID() == m_startingMR_id)
					break;
				
				MFTUMaintNextAction na = MFTUMaintNextAction.getByComponentAndMaintRequirement(getCtx(), getCT_Component_ID(), mr.getFTU_MaintRequirement_ID(), get_TrxName());
				na.m_startingMR_id = m_startingMR_id;
				na.recalc();
				na.saveEx();
			}
		}
		return success;
	}	//	afterSave

	
	public void recalc() {
		// Clear the next due date and usage to trigger a beforeSave action.
		// Changes will be made during beforeSave()
		this.setFTU_DateNextDue(null);
		this.setFTU_UsageNextDue(null);
	}

	public static MFTUMaintNextAction getNextActionByDate(Properties ctx,
			int ct_component_id, String trxName) {
		
		String where = "(" + MFTUMaintNextAction.COLUMNNAME_CT_Component_ID + "= ?"
				+ " OR " + MFTUMaintNextAction.COLUMNNAME_CT_Component_ID + " IN "
				+ " (SELECT CT_Component_ID FROM CT_Component "
				+ " WHERE CT_Component.Root_Component_ID = ?))"
				+ " AND " + MFTUMaintNextAction.COLUMNNAME_FTU_DateNextDue + " IS NOT NULL ";
		
		String order = MFTUMaintNextAction.COLUMNNAME_FTU_DateNextDue 
				+ " + COALESCE(" + MFTUMaintNextAction.COLUMNNAME_FTU_TimeIntervalTol + ",0)";
		
		return new Query(ctx, MFTUMaintNextAction.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ct_component_id, ct_component_id)
					.setOrderBy(order)
					.first();

	}

	/** Find the next due maintenance requirement by usage. 
	 * @param ctx
	 * @param ct_component_id
	 * @param trxName
	 * @return the next maintenance action.
	 */
	public static MFTUMaintNextAction getNextActionByUsage(Properties ctx,
			int ct_component_id, String trxName) {
		
		String where = "(" + MFTUMaintNextAction.COLUMNNAME_CT_Component_ID + "= ?"
				+ " OR " + MFTUMaintNextAction.COLUMNNAME_CT_Component_ID + " IN "
				+ " (SELECT CT_Component_ID FROM CT_Component "
				+ " WHERE CT_Component.Root_Component_ID = ?))"
				+ " AND " + MFTUMaintNextAction.COLUMNNAME_FTU_UsageNextDue + " IS NOT NULL "
				+ " AND " + MFTUMaintNextAction.COLUMNNAME_FTU_UsageNextDue + "> 0";
		
		String order = "(SELECT CT.LifeUsed"
				+ " FROM CT_Component CT WHERE CT.CT_Component_ID = FTU_MaintNextAction.CT_Component_ID)"
				+ " - " + MFTUMaintNextAction.COLUMNNAME_FTU_UsageNextDue
				+ " + COALESCE(" + MFTUMaintNextAction.COLUMNNAME_FTU_UsageIntervalTol + ",0)";
		
		return new Query(ctx, MFTUMaintNextAction.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ct_component_id, ct_component_id)
					.setOrderBy(order)
					.first();

	}

//	public static void updateRootComponent(Properties ctx, int ct_component_id,
//			int root_component_ID, String trxName) {
//		
//		List<MFTUMaintNextAction> actions = getByComponent(ctx, ct_component_id, trxName);
//		
//		for (MFTUMaintNextAction action : actions)
//		{
//			action.setRoot_Component_ID(root_component_ID);
//			action.saveEx();
//		}
//		
//	}

	public static List<MFTUMaintNextAction> getByComponent(Properties ctx,
			int ct_component_id, String trxName) {

		String where = MFTUMaintNextAction.COLUMNNAME_CT_Component_ID + "= ?";
		
		return new Query(ctx, MFTUMaintNextAction.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ct_component_id)
					.list();

	}

}
