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

/** Generated Model for FTU_MaintWorkOrderLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintWorkOrderLine extends PO implements I_FTU_MaintWorkOrderLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_MaintWorkOrderLine (Properties ctx, int FTU_MaintWorkOrderLine_ID, String trxName)
    {
      super (ctx, FTU_MaintWorkOrderLine_ID, trxName);
      /** if (FTU_MaintWorkOrderLine_ID == 0)
        {
			setFTU_MaintWorkOrderLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintWorkOrderLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintWorkOrderLine[")
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

	/** CT_ComponentActionType AD_Reference_ID=53872 */
	public static final int CT_COMPONENTACTIONTYPE_AD_Reference_ID=53872;
	/** Shipped = Shipped */
	public static final String CT_COMPONENTACTIONTYPE_Shipped = "Shipped";
	/** Received = Received */
	public static final String CT_COMPONENTACTIONTYPE_Received = "Received";
	/** Scrapped = Scrapped */
	public static final String CT_COMPONENTACTIONTYPE_Scrapped = "Scrapped";
	/** Added to inventory = Added */
	public static final String CT_COMPONENTACTIONTYPE_AddedToInventory = "Added";
	/** Drawn from inentory = Drawn */
	public static final String CT_COMPONENTACTIONTYPE_DrawnFromInentory = "Drawn";
	/** Installed = Installed */
	public static final String CT_COMPONENTACTIONTYPE_Installed = "Installed";
	/** Uninstalled = Uninstalled */
	public static final String CT_COMPONENTACTIONTYPE_Uninstalled = "Uninstalled";
	/** Created = Created */
	public static final String CT_COMPONENTACTIONTYPE_Created = "Created";
	/** Inspected = Inspected */
	public static final String CT_COMPONENTACTIONTYPE_Inspected = "Inspected";
	/** Overhauled = Overhauled */
	public static final String CT_COMPONENTACTIONTYPE_Overhauled = "Overhauled";
	/** Repaired = Repaired */
	public static final String CT_COMPONENTACTIONTYPE_Repaired = "Repaired";
	/** Set Action Type.
		@param CT_ComponentActionType 
		The type of action performed on the component
	  */
	public void setCT_ComponentActionType (String CT_ComponentActionType)
	{

		set_Value (COLUMNNAME_CT_ComponentActionType, CT_ComponentActionType);
	}

	/** Get Action Type.
		@return The type of action performed on the component
	  */
	public String getCT_ComponentActionType () 
	{
		return (String)get_Value(COLUMNNAME_CT_ComponentActionType);
	}

	/** CT_ComponentResolutionType AD_Reference_ID=53872 */
	public static final int CT_COMPONENTRESOLUTIONTYPE_AD_Reference_ID=53872;
	/** Shipped = Shipped */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Shipped = "Shipped";
	/** Received = Received */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Received = "Received";
	/** Scrapped = Scrapped */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Scrapped = "Scrapped";
	/** Added to inventory = Added */
	public static final String CT_COMPONENTRESOLUTIONTYPE_AddedToInventory = "Added";
	/** Drawn from inentory = Drawn */
	public static final String CT_COMPONENTRESOLUTIONTYPE_DrawnFromInentory = "Drawn";
	/** Installed = Installed */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Installed = "Installed";
	/** Uninstalled = Uninstalled */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Uninstalled = "Uninstalled";
	/** Created = Created */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Created = "Created";
	/** Inspected = Inspected */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Inspected = "Inspected";
	/** Overhauled = Overhauled */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Overhauled = "Overhauled";
	/** Repaired = Repaired */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Repaired = "Repaired";
	/** Set Resolution Type.
		@param CT_ComponentResolutionType 
		The type of action performed on the component to resolve a maintenance requirement
	  */
	public void setCT_ComponentResolutionType (String CT_ComponentResolutionType)
	{

		set_Value (COLUMNNAME_CT_ComponentResolutionType, CT_ComponentResolutionType);
	}

	/** Get Resolution Type.
		@return The type of action performed on the component to resolve a maintenance requirement
	  */
	public String getCT_ComponentResolutionType () 
	{
		return (String)get_Value(COLUMNNAME_CT_ComponentResolutionType);
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
			set_Value (COLUMNNAME_FTU_MaintRequirement_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_MaintRequirement_ID, Integer.valueOf(FTU_MaintRequirement_ID));
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
			set_Value (COLUMNNAME_FTU_MaintRequirementLine_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_MaintRequirementLine_ID, Integer.valueOf(FTU_MaintRequirementLine_ID));
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

	public com.mckayerp.ftu.model.I_FTU_MaintWorkOrder getFTU_MaintWorkOrder() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintWorkOrder)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintWorkOrder.Table_Name)
			.getPO(getFTU_MaintWorkOrder_ID(), get_TrxName());	}

	/** Set Maintenance Work Order.
		@param FTU_MaintWorkOrder_ID 
		The Maintenance Work Order
	  */
	public void setFTU_MaintWorkOrder_ID (int FTU_MaintWorkOrder_ID)
	{
		if (FTU_MaintWorkOrder_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrder_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrder_ID, Integer.valueOf(FTU_MaintWorkOrder_ID));
	}

	/** Get Maintenance Work Order.
		@return The Maintenance Work Order
	  */
	public int getFTU_MaintWorkOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWorkOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maintenance Work Order Line.
		@param FTU_MaintWorkOrderLine_ID Maintenance Work Order Line	  */
	public void setFTU_MaintWorkOrderLine_ID (int FTU_MaintWorkOrderLine_ID)
	{
		if (FTU_MaintWorkOrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrderLine_ID, Integer.valueOf(FTU_MaintWorkOrderLine_ID));
	}

	/** Get Maintenance Work Order Line.
		@return Maintenance Work Order Line	  */
	public int getFTU_MaintWorkOrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWorkOrderLine_ID);
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
}