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

/** Generated Model for FTU_MaintWOResultLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintWOResultLine extends PO implements I_FTU_MaintWOResultLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_MaintWOResultLine (Properties ctx, int FTU_MaintWOResultLine_ID, String trxName)
    {
      super (ctx, FTU_MaintWOResultLine_ID, trxName);
      /** if (FTU_MaintWOResultLine_ID == 0)
        {
			setFTU_MaintResultType (null);
			setFTU_MaintWOResult_ID (0);
			setFTU_MaintWOResultLine_ID (0);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintWOResultLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintWOResultLine[")
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

	/** Set Component Life.
		@param CT_ComponentLifeAtAction 
		The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public void setCT_ComponentLifeAtAction (BigDecimal CT_ComponentLifeAtAction)
	{
		set_Value (COLUMNNAME_CT_ComponentLifeAtAction, CT_ComponentLifeAtAction);
	}

	/** Get Component Life.
		@return The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public BigDecimal getCT_ComponentLifeAtAction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CT_ComponentLifeAtAction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public com.mckayerp.model.I_CT_DataSet getCT_DataSet() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_DataSet)MTable.get(getCtx(), com.mckayerp.model.I_CT_DataSet.Table_Name)
			.getPO(getCT_DataSet_ID(), get_TrxName());	}

	/** Set Data Set.
		@param CT_DataSet_ID 
		A definition of a set of data.
	  */
	public void setCT_DataSet_ID (int CT_DataSet_ID)
	{
		if (CT_DataSet_ID < 1) 
			set_Value (COLUMNNAME_CT_DataSet_ID, null);
		else 
			set_Value (COLUMNNAME_CT_DataSet_ID, Integer.valueOf(CT_DataSet_ID));
	}

	/** Get Data Set.
		@return A definition of a set of data.
	  */
	public int getCT_DataSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Data Set Instance.
		@param CT_DataSetInstance_ID 
		A data point within a data set.
	  */
	public void setCT_DataSetInstance_ID (Object CT_DataSetInstance_ID)
	{
		set_Value (COLUMNNAME_CT_DataSetInstance_ID, CT_DataSetInstance_ID);
	}

	/** Get Data Set Instance.
		@return A data point within a data set.
	  */
	public Object getCT_DataSetInstance_ID () 
	{
				return get_Value(COLUMNNAME_CT_DataSetInstance_ID);
	}

	/** Set Root Component Life.
		@param CT_RootLifeAtAction 
		The Life of the Root Component at the time of the action.
	  */
	public void setCT_RootLifeAtAction (BigDecimal CT_RootLifeAtAction)
	{
		set_Value (COLUMNNAME_CT_RootLifeAtAction, CT_RootLifeAtAction);
	}

	/** Get Root Component Life.
		@return The Life of the Root Component at the time of the action.
	  */
	public BigDecimal getCT_RootLifeAtAction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CT_RootLifeAtAction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Defect Date.
		@param DefectDate 
		The date and time the defect was entered in the log.
	  */
	public void setDefectDate (Timestamp DefectDate)
	{
		set_Value (COLUMNNAME_DefectDate, DefectDate);
	}

	/** Get Defect Date.
		@return The date and time the defect was entered in the log.
	  */
	public Timestamp getDefectDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DefectDate);
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

	/** Set Date Completed.
		@param FTU_DateCompleted 
		The date on which the action was completed. 
	  */
	public void setFTU_DateCompleted (Timestamp FTU_DateCompleted)
	{
		set_Value (COLUMNNAME_FTU_DateCompleted, FTU_DateCompleted);
	}

	/** Get Date Completed.
		@return The date on which the action was completed. 
	  */
	public Timestamp getFTU_DateCompleted () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FTU_DateCompleted);
	}

	/** Set Next Due (Date).
		@param FTU_DateNextDue 
		The date when the maintenance requirement is next due. (Calculated)
	  */
	public void setFTU_DateNextDue (Timestamp FTU_DateNextDue)
	{
		set_Value (COLUMNNAME_FTU_DateNextDue, FTU_DateNextDue);
	}

	/** Get Next Due (Date).
		@return The date when the maintenance requirement is next due. (Calculated)
	  */
	public Timestamp getFTU_DateNextDue () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FTU_DateNextDue);
	}

	/** Set Date Tolerance Applied.
		@param FTU_DateToleranceApplied 
		The amount of a date tolerance applied at the time of the maintenance action
	  */
	public void setFTU_DateToleranceApplied (BigDecimal FTU_DateToleranceApplied)
	{
		set_Value (COLUMNNAME_FTU_DateToleranceApplied, FTU_DateToleranceApplied);
	}

	/** Get Date Tolerance Applied.
		@return The amount of a date tolerance applied at the time of the maintenance action
	  */
	public BigDecimal getFTU_DateToleranceApplied () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_DateToleranceApplied);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Action Taken.
		@param FTU_MaintActionTaken 
		The maintenance action taken to satisfy the maintenance requirement.
	  */
	public void setFTU_MaintActionTaken (String FTU_MaintActionTaken)
	{
		set_Value (COLUMNNAME_FTU_MaintActionTaken, FTU_MaintActionTaken);
	}

	/** Get Action Taken.
		@return The maintenance action taken to satisfy the maintenance requirement.
	  */
	public String getFTU_MaintActionTaken () 
	{
		return (String)get_Value(COLUMNNAME_FTU_MaintActionTaken);
	}

	public com.mckayerp.ftu.model.I_FTU_MaintNextAction getFTU_MaintNextAction() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintNextAction)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintNextAction.Table_Name)
			.getPO(getFTU_MaintNextAction_ID(), get_TrxName());	}

	/** Set Next Maintenance Action.
		@param FTU_MaintNextAction_ID Next Maintenance Action	  */
	public void setFTU_MaintNextAction_ID (int FTU_MaintNextAction_ID)
	{
		if (FTU_MaintNextAction_ID < 1) 
			set_Value (COLUMNNAME_FTU_MaintNextAction_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_MaintNextAction_ID, Integer.valueOf(FTU_MaintNextAction_ID));
	}

	/** Get Next Maintenance Action.
		@return Next Maintenance Action	  */
	public int getFTU_MaintNextAction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintNextAction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintRequirement getFTU_MaintRequirement() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintRequirement)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintRequirement.Table_Name)
			.getPO(getFTU_MaintRequirement_ID(), get_TrxName());	}

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

	public com.mckayerp.ftu.model.I_FTU_MaintRequirementLine getFTU_MaintRequirementLine() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintRequirementLine)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintRequirementLine.Table_Name)
			.getPO(getFTU_MaintRequirementLine_ID(), get_TrxName());	}

	/** Set Maintenance Requirement Line.
		@param FTU_MaintRequirementLine_ID Maintenance Requirement Line	  */
	public void setFTU_MaintRequirementLine_ID (int FTU_MaintRequirementLine_ID)
	{
		if (FTU_MaintRequirementLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirementLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirementLine_ID, Integer.valueOf(FTU_MaintRequirementLine_ID));
	}

	/** Get Maintenance Requirement Line.
		@return Maintenance Requirement Line	  */
	public int getFTU_MaintRequirementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintRequirementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FTU_MaintResultType AD_Reference_ID=1000087 */
	public static final int FTU_MAINTRESULTTYPE_AD_Reference_ID=1000087;
	/** Completed = Completed */
	public static final String FTU_MAINTRESULTTYPE_Completed = "Completed";
	/** Completed with Fault Found = Fault Found */
	public static final String FTU_MAINTRESULTTYPE_CompletedWithFaultFound = "Fault Found";
	/** Deferred = Deferred */
	public static final String FTU_MAINTRESULTTYPE_Deferred = "Deferred";
	/** Set Resolution Type.
		@param FTU_MaintResultType 
		The maintenance action can be completed or completed with a fault found.
	  */
	public void setFTU_MaintResultType (String FTU_MaintResultType)
	{

		set_Value (COLUMNNAME_FTU_MaintResultType, FTU_MaintResultType);
	}

	/** Get Resolution Type.
		@return The maintenance action can be completed or completed with a fault found.
	  */
	public String getFTU_MaintResultType () 
	{
		return (String)get_Value(COLUMNNAME_FTU_MaintResultType);
	}

	public com.mckayerp.ftu.model.I_FTU_MaintWOResult getFTU_MaintWOResult() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintWOResult)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintWOResult.Table_Name)
			.getPO(getFTU_MaintWOResult_ID(), get_TrxName());	}

	/** Set Maintenance Work Order Result.
		@param FTU_MaintWOResult_ID Maintenance Work Order Result	  */
	public void setFTU_MaintWOResult_ID (int FTU_MaintWOResult_ID)
	{
		if (FTU_MaintWOResult_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResult_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResult_ID, Integer.valueOf(FTU_MaintWOResult_ID));
	}

	/** Get Maintenance Work Order Result.
		@return Maintenance Work Order Result	  */
	public int getFTU_MaintWOResult_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWOResult_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maintenance Work Order Result Line.
		@param FTU_MaintWOResultLine_ID Maintenance Work Order Result Line	  */
	public void setFTU_MaintWOResultLine_ID (int FTU_MaintWOResultLine_ID)
	{
		if (FTU_MaintWOResultLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResultLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResultLine_ID, Integer.valueOf(FTU_MaintWOResultLine_ID));
	}

	/** Get Maintenance Work Order Result Line.
		@return Maintenance Work Order Result Line	  */
	public int getFTU_MaintWOResultLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWOResultLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Next Due (Usage).
		@param FTU_UsageNextDue 
		The usage/life when the maintenance requirement is next due. (Calculated)
	  */
	public void setFTU_UsageNextDue (BigDecimal FTU_UsageNextDue)
	{
		set_Value (COLUMNNAME_FTU_UsageNextDue, FTU_UsageNextDue);
	}

	/** Get Next Due (Usage).
		@return The usage/life when the maintenance requirement is next due. (Calculated)
	  */
	public BigDecimal getFTU_UsageNextDue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UsageNextDue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Usage Tolerance Applied.
		@param FTU_UsageTolApplied 
		The amount of usage/life tolerance applied on the last maintenance action
	  */
	public void setFTU_UsageTolApplied (BigDecimal FTU_UsageTolApplied)
	{
		set_Value (COLUMNNAME_FTU_UsageTolApplied, FTU_UsageTolApplied);
	}

	/** Get Usage Tolerance Applied.
		@return The amount of usage/life tolerance applied on the last maintenance action
	  */
	public BigDecimal getFTU_UsageTolApplied () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UsageTolApplied);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Maint Requirement Completed.
		@param IsMaintReqCompleted 
		Is the maintenance requirement completed?
	  */
	public void setIsMaintReqCompleted (boolean IsMaintReqCompleted)
	{
		set_Value (COLUMNNAME_IsMaintReqCompleted, Boolean.valueOf(IsMaintReqCompleted));
	}

	/** Get Maint Requirement Completed.
		@return Is the maintenance requirement completed?
	  */
	public boolean isMaintReqCompleted () 
	{
		Object oo = get_Value(COLUMNNAME_IsMaintReqCompleted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_C_UOM getLifeUsageUOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getLifeUsageUOM_ID(), get_TrxName());	}

	/** Set Life Use UOM.
		@param LifeUsageUOM_ID 
		The unit of measure (UOM) for the life usage.  Example, hours, km.
	  */
	public void setLifeUsageUOM_ID (int LifeUsageUOM_ID)
	{
		if (LifeUsageUOM_ID < 1) 
			set_Value (COLUMNNAME_LifeUsageUOM_ID, null);
		else 
			set_Value (COLUMNNAME_LifeUsageUOM_ID, Integer.valueOf(LifeUsageUOM_ID));
	}

	/** Get Life Use UOM.
		@return The unit of measure (UOM) for the life usage.  Example, hours, km.
	  */
	public int getLifeUsageUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LifeUsageUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Overhaul Count.
		@param OverhaulCount 
		The number of life cycles completed or underway.
	  */
	public void setOverhaulCount (int OverhaulCount)
	{
		set_Value (COLUMNNAME_OverhaulCount, Integer.valueOf(OverhaulCount));
	}

	/** Get Overhaul Count.
		@return The number of life cycles completed or underway.
	  */
	public int getOverhaulCount () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OverhaulCount);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public com.mckayerp.model.I_CT_Component getRoot_Component() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getRoot_Component_ID(), get_TrxName());	}

	/** Set Root Component.
		@param Root_Component_ID 
		The Root Component of the component BOM tree.
	  */
	public void setRoot_Component_ID (int Root_Component_ID)
	{
		if (Root_Component_ID < 1) 
			set_Value (COLUMNNAME_Root_Component_ID, null);
		else 
			set_Value (COLUMNNAME_Root_Component_ID, Integer.valueOf(Root_Component_ID));
	}

	/** Get Root Component.
		@return The Root Component of the component BOM tree.
	  */
	public int getRoot_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Root_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}