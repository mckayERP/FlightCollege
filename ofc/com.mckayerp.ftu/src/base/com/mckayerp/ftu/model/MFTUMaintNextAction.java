package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

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
		MFTUMaintRequirement mr = this.getFTU_MaintRequirement();
		MCTComponent ct = this.getCT_Component();
		
		String complianceType = mr.getFTU_ComplianceType();
		
		// Clear the next due date and usage
		this.setFTU_DateNextDue(null);
		this.setFTU_UsageNextDue(null);

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
			if (this.getFTU_UsageLastDone() == null)
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
			if (mr.getFTU_UsageInterval().compareTo(Env.ZERO) > 0 && this.getFTU_UsageLastDone() == null)
			{
				// Assumes similar UOM
				this.setFTU_UsageNextDue(ct.getLifeUsed().add(mr.getFTU_UsageInterval()));
			}
		}
		else if (MFTUMaintRequirement.FTU_COMPLIANCETYPE_RepeatEvery.equals(complianceType))
		{
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
						
					BigDecimal dateTolApplied = this.getFTU_DateTolApplied();
					if (dateTolApplied == null)
						dateTolApplied = Env.ZERO;
		
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
					// Assumes interval has been converted to days.
					Calendar cal = Calendar.getInstance();
					cal.setTime(lastDone);
					cal.add(Calendar.DAY_OF_WEEK, tolToApply.intValue());
					this.setFTU_DateNextDue(new Timestamp(cal.getTime().getTime()));
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
				this.setFTU_UsageNextDue(lastDone.add(interval).add(tolToApply));
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
	
}
