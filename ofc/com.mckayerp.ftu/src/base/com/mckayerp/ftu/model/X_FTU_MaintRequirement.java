/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for FTU_MaintRequirement
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintRequirement extends PO implements I_FTU_MaintRequirement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180107L;

    /** Standard Constructor */
    public X_FTU_MaintRequirement (Properties ctx, int FTU_MaintRequirement_ID, String trxName)
    {
      super (ctx, FTU_MaintRequirement_ID, trxName);
      /** if (FTU_MaintRequirement_ID == 0)
        {
			setFTU_MaintRequirement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintRequirement (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_FTU_MaintRequirement[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public com.mckayerp.model.I_CT_Component getCT_Component() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getCT_Component_ID(), get_TrxName());	}

	/** Set Component.
		@param CT_Component_ID 
		A component of an assembly or asset.
	  */
	public void setCT_Component_ID (int CT_Component_ID)
	{
		if (CT_Component_ID < 1) 
			set_Value (COLUMNNAME_CT_Component_ID, null);
		else 
			set_Value (COLUMNNAME_CT_Component_ID, Integer.valueOf(CT_Component_ID));
	}

	/** Get Component.
		@return A component of an assembly or asset.
	  */
	public int getCT_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Action.
		@param FTU_Action 
		The action that must be taken to address the maintenance requirement
	  */
	public void setFTU_Action (String FTU_Action)
	{
		set_Value (COLUMNNAME_FTU_Action, FTU_Action);
	}

	/** Get Action.
		@return The action that must be taken to address the maintenance requirement
	  */
	public String getFTU_Action () 
	{
		return (String)get_Value(COLUMNNAME_FTU_Action);
	}

	public com.mckayerp.ftu.model.I_FTU_AirworthinessDirective getFTU_AirworthinessDirective() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_AirworthinessDirective)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_AirworthinessDirective.Table_Name)
			.getPO(getFTU_AirworthinessDirective_ID(), get_TrxName());	}

	/** Set Airworthiness Directive.
		@param FTU_AirworthinessDirective_ID Airworthiness Directive	  */
	public void setFTU_AirworthinessDirective_ID (int FTU_AirworthinessDirective_ID)
	{
		if (FTU_AirworthinessDirective_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_AirworthinessDirective_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_AirworthinessDirective_ID, Integer.valueOf(FTU_AirworthinessDirective_ID));
	}

	/** Get Airworthiness Directive.
		@return Airworthiness Directive	  */
	public int getFTU_AirworthinessDirective_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_AirworthinessDirective_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getFTU_AppliesToProduct() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getFTU_AppliesToProduct_ID(), get_TrxName());	}

	/** Set Applies to Product.
		@param FTU_AppliesToProduct_ID 
		The product to which the maintenance requirement or schedule applies.
	  */
	public void setFTU_AppliesToProduct_ID (int FTU_AppliesToProduct_ID)
	{
		if (FTU_AppliesToProduct_ID < 1) 
			set_Value (COLUMNNAME_FTU_AppliesToProduct_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_AppliesToProduct_ID, Integer.valueOf(FTU_AppliesToProduct_ID));
	}

	/** Get Applies to Product.
		@return The product to which the maintenance requirement or schedule applies.
	  */
	public int getFTU_AppliesToProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_AppliesToProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FTU_ComplianceType AD_Reference_ID=53879 */
	public static final int FTU_COMPLIANCETYPE_AD_Reference_ID=53879;
	/** Once (Within next ...) = Once */
	public static final String FTU_COMPLIANCETYPE_OnceWithinNext = "Once";
	/** Repeat (every ...) = Repeat */
	public static final String FTU_COMPLIANCETYPE_RepeatEvery = "Repeat";
	/** Before further flight or use = BeforeUse */
	public static final String FTU_COMPLIANCETYPE_BeforeFurtherFlightOrUse = "BeforeUse";
	/** Before (deadline date) = BeforeDate */
	public static final String FTU_COMPLIANCETYPE_BeforeDeadlineDate = "BeforeDate";
	/** Set Compliance Type.
		@param FTU_ComplianceType 
		The compliance type of the requirement, either once or repetitive.
	  */
	public void setFTU_ComplianceType (String FTU_ComplianceType)
	{

		set_Value (COLUMNNAME_FTU_ComplianceType, FTU_ComplianceType);
	}

	/** Get Compliance Type.
		@return The compliance type of the requirement, either once or repetitive.
	  */
	public String getFTU_ComplianceType () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ComplianceType);
	}

	/** Set After Date.
		@param FTU_DateAfter 
		The date after which the interval applies or the starting date of the interval
	  */
	public void setFTU_DateAfter (Timestamp FTU_DateAfter)
	{
		set_Value (COLUMNNAME_FTU_DateAfter, FTU_DateAfter);
	}

	/** Get After Date.
		@return The date after which the interval applies or the starting date of the interval
	  */
	public Timestamp getFTU_DateAfter () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FTU_DateAfter);
	}

	/** Set Deadline.
		@param FTU_DeadlineDate 
		The deadline date for compliance
	  */
	public void setFTU_DeadlineDate (Timestamp FTU_DeadlineDate)
	{
		set_Value (COLUMNNAME_FTU_DeadlineDate, FTU_DeadlineDate);
	}

	/** Get Deadline.
		@return The deadline date for compliance
	  */
	public Timestamp getFTU_DeadlineDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FTU_DeadlineDate);
	}

	public com.mckayerp.ftu.model.I_FTU_DefectLog getFTU_DefectLog() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_DefectLog)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_DefectLog.Table_Name)
			.getPO(getFTU_DefectLog_ID(), get_TrxName());	}

	/** Set Defect.
		@param FTU_DefectLog_ID Defect	  */
	public void setFTU_DefectLog_ID (int FTU_DefectLog_ID)
	{
		if (FTU_DefectLog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_DefectLog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_DefectLog_ID, Integer.valueOf(FTU_DefectLog_ID));
	}

	/** Get Defect.
		@return Defect	  */
	public int getFTU_DefectLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_DefectLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maintenance Requirement.
		@param FTU_MaintRequirement_ID 
		A requirement to perform some maintenance action due to a snag, preventive maintenance or other corrective action.
	  */
	public void setFTU_MaintRequirement_ID (int FTU_MaintRequirement_ID)
	{
		if (FTU_MaintRequirement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirement_ID, Integer.valueOf(FTU_MaintRequirement_ID));
	}

	/** Get Maintenance Requirement.
		@return A requirement to perform some maintenance action due to a snag, preventive maintenance or other corrective action.
	  */
	public int getFTU_MaintRequirement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintRequirement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Process.
		@param FTU_Process 
		A text description of the process to follow.
	  */
	public void setFTU_Process (String FTU_Process)
	{
		set_Value (COLUMNNAME_FTU_Process, FTU_Process);
	}

	/** Get Process.
		@return A text description of the process to follow.
	  */
	public String getFTU_Process () 
	{
		return (String)get_Value(COLUMNNAME_FTU_Process);
	}

	/** Set Resolution Template.
		@param FTU_ResolutionTemplate 
		A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action.
	  */
	public void setFTU_ResolutionTemplate (String FTU_ResolutionTemplate)
	{
		set_Value (COLUMNNAME_FTU_ResolutionTemplate, FTU_ResolutionTemplate);
	}

	/** Get Resolution Template.
		@return A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action.
	  */
	public String getFTU_ResolutionTemplate () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ResolutionTemplate);
	}

	/** Set Time Interval.
		@param FTU_TimeInterval 
		The time interval limit for the action. Leave blank/null for no time limit. UOM is standard, typically days.  Set programmatically.
	  */
	public void setFTU_TimeInterval (BigDecimal FTU_TimeInterval)
	{
		set_Value (COLUMNNAME_FTU_TimeInterval, FTU_TimeInterval);
	}

	/** Get Time Interval.
		@return The time interval limit for the action. Leave blank/null for no time limit. UOM is standard, typically days.  Set programmatically.
	  */
	public BigDecimal getFTU_TimeInterval () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_TimeInterval);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Time Interval.
		@param FTU_TimeInterval_Entered 
		The time interval limit for the action. Leave blank/null for no time limit. Select the associated Unit of Measure.
	  */
	public void setFTU_TimeInterval_Entered (BigDecimal FTU_TimeInterval_Entered)
	{
		set_Value (COLUMNNAME_FTU_TimeInterval_Entered, FTU_TimeInterval_Entered);
	}

	/** Get Time Interval.
		@return The time interval limit for the action. Leave blank/null for no time limit. Select the associated Unit of Measure.
	  */
	public BigDecimal getFTU_TimeInterval_Entered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_TimeInterval_Entered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Time Interval Tol (+/-).
		@param FTU_TimeIntervalTol 
		The Tolerance in the Time Interval in the same units as the Interval
	  */
	public void setFTU_TimeIntervalTol (BigDecimal FTU_TimeIntervalTol)
	{
		set_Value (COLUMNNAME_FTU_TimeIntervalTol, FTU_TimeIntervalTol);
	}

	/** Get Time Interval Tol (+/-).
		@return The Tolerance in the Time Interval in the same units as the Interval
	  */
	public BigDecimal getFTU_TimeIntervalTol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_TimeIntervalTol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Time Interval Tol (+/-).
		@param FTU_TimeIntervalTol_Entered 
		The Tolerance in the Time Interval in the same units as the Interval
	  */
	public void setFTU_TimeIntervalTol_Entered (BigDecimal FTU_TimeIntervalTol_Entered)
	{
		set_Value (COLUMNNAME_FTU_TimeIntervalTol_Entered, FTU_TimeIntervalTol_Entered);
	}

	/** Get Time Interval Tol (+/-).
		@return The Tolerance in the Time Interval in the same units as the Interval
	  */
	public BigDecimal getFTU_TimeIntervalTol_Entered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_TimeIntervalTol_Entered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_UOM getFTU_TimeIntervalUOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getFTU_TimeIntervalUOM_ID(), get_TrxName());	}

	/** Set Time Interval UOM.
		@param FTU_TimeIntervalUOM_ID 
		The Unit of Measure (UOM) of the Time interval.  Typically days.
	  */
	public void setFTU_TimeIntervalUOM_ID (int FTU_TimeIntervalUOM_ID)
	{
		if (FTU_TimeIntervalUOM_ID < 1) 
			set_Value (COLUMNNAME_FTU_TimeIntervalUOM_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_TimeIntervalUOM_ID, Integer.valueOf(FTU_TimeIntervalUOM_ID));
	}

	/** Get Time Interval UOM.
		@return The Unit of Measure (UOM) of the Time interval.  Typically days.
	  */
	public int getFTU_TimeIntervalUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_TimeIntervalUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FTU_TimeToleranceType AD_Reference_ID=1000083 */
	public static final int FTU_TIMETOLERANCETYPE_AD_Reference_ID=1000083;
	/** Fixed = F */
	public static final String FTU_TIMETOLERANCETYPE_Fixed = "F";
	/** Cummulative = C */
	public static final String FTU_TIMETOLERANCETYPE_Cummulative = "C";
	/** Not Extended = NE */
	public static final String FTU_TIMETOLERANCETYPE_NotExtended = "NE";
	/** Set Time Tolerance Type.
		@param FTU_TimeToleranceType 
		Determines how the next time/date based scheduled maintenance requirement is determined.
	  */
	public void setFTU_TimeToleranceType (String FTU_TimeToleranceType)
	{

		set_Value (COLUMNNAME_FTU_TimeToleranceType, FTU_TimeToleranceType);
	}

	/** Get Time Tolerance Type.
		@return Determines how the next time/date based scheduled maintenance requirement is determined.
	  */
	public String getFTU_TimeToleranceType () 
	{
		return (String)get_Value(COLUMNNAME_FTU_TimeToleranceType);
	}

	/** Set Usage Interval.
		@param FTU_UsageInterval 
		The usage or Time in Service (TIS) interval limit for the action. Uses standard Unit of Measure - typically hours.
	  */
	public void setFTU_UsageInterval (BigDecimal FTU_UsageInterval)
	{
		set_Value (COLUMNNAME_FTU_UsageInterval, FTU_UsageInterval);
	}

	/** Get Usage Interval.
		@return The usage or Time in Service (TIS) interval limit for the action. Uses standard Unit of Measure - typically hours.
	  */
	public BigDecimal getFTU_UsageInterval () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UsageInterval);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Usage Interval.
		@param FTU_UsageInterval_Entered 
		The usage or Time in Service (TIS) interval limit for the action. Leave blank/null for no usage limit. Select the associated Unit of Measure.
	  */
	public void setFTU_UsageInterval_Entered (BigDecimal FTU_UsageInterval_Entered)
	{
		set_Value (COLUMNNAME_FTU_UsageInterval_Entered, FTU_UsageInterval_Entered);
	}

	/** Get Usage Interval.
		@return The usage or Time in Service (TIS) interval limit for the action. Leave blank/null for no usage limit. Select the associated Unit of Measure.
	  */
	public BigDecimal getFTU_UsageInterval_Entered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UsageInterval_Entered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Usage Interval Tol (+/-).
		@param FTU_UsageIntervalTol 
		The Tolerance in the Usage Interval in the same units as the Usage Interval
	  */
	public void setFTU_UsageIntervalTol (BigDecimal FTU_UsageIntervalTol)
	{
		set_Value (COLUMNNAME_FTU_UsageIntervalTol, FTU_UsageIntervalTol);
	}

	/** Get Usage Interval Tol (+/-).
		@return The Tolerance in the Usage Interval in the same units as the Usage Interval
	  */
	public BigDecimal getFTU_UsageIntervalTol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UsageIntervalTol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Usage Interval Tol (+/-).
		@param FTU_UsageIntervalTol_Entered 
		The Tolerance in the Usage Interval in the same units as the Usage Interval
	  */
	public void setFTU_UsageIntervalTol_Entered (BigDecimal FTU_UsageIntervalTol_Entered)
	{
		set_Value (COLUMNNAME_FTU_UsageIntervalTol_Entered, FTU_UsageIntervalTol_Entered);
	}

	/** Get Usage Interval Tol (+/-).
		@return The Tolerance in the Usage Interval in the same units as the Usage Interval
	  */
	public BigDecimal getFTU_UsageIntervalTol_Entered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UsageIntervalTol_Entered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_UOM getFTU_UsageIntervalUOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getFTU_UsageIntervalUOM_ID(), get_TrxName());	}

	/** Set Usage Interval UOM.
		@param FTU_UsageIntervalUOM_ID 
		The Unit of Measure (UOM) of the usage interval.  Typically hours.
	  */
	public void setFTU_UsageIntervalUOM_ID (int FTU_UsageIntervalUOM_ID)
	{
		if (FTU_UsageIntervalUOM_ID < 1) 
			set_Value (COLUMNNAME_FTU_UsageIntervalUOM_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_UsageIntervalUOM_ID, Integer.valueOf(FTU_UsageIntervalUOM_ID));
	}

	/** Get Usage Interval UOM.
		@return The Unit of Measure (UOM) of the usage interval.  Typically hours.
	  */
	public int getFTU_UsageIntervalUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_UsageIntervalUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FTU_UsageToleranceType AD_Reference_ID=1000083 */
	public static final int FTU_USAGETOLERANCETYPE_AD_Reference_ID=1000083;
	/** Fixed = F */
	public static final String FTU_USAGETOLERANCETYPE_Fixed = "F";
	/** Cummulative = C */
	public static final String FTU_USAGETOLERANCETYPE_Cummulative = "C";
	/** Not Extended = NE */
	public static final String FTU_USAGETOLERANCETYPE_NotExtended = "NE";
	/** Set Usage Tolerance Type.
		@param FTU_UsageToleranceType 
		Determines how the next usage based scheduled maintenance requirement is determined.
	  */
	public void setFTU_UsageToleranceType (String FTU_UsageToleranceType)
	{

		set_Value (COLUMNNAME_FTU_UsageToleranceType, FTU_UsageToleranceType);
	}

	/** Get Usage Tolerance Type.
		@return Determines how the next usage based scheduled maintenance requirement is determined.
	  */
	public String getFTU_UsageToleranceType () 
	{
		return (String)get_Value(COLUMNNAME_FTU_UsageToleranceType);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}