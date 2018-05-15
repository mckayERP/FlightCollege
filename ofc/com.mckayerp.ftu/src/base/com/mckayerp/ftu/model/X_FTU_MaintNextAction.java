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

/** Generated Model for FTU_MaintNextAction
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintNextAction extends PO implements I_FTU_MaintNextAction, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_MaintNextAction (Properties ctx, int FTU_MaintNextAction_ID, String trxName)
    {
      super (ctx, FTU_MaintNextAction_ID, trxName);
      /** if (FTU_MaintNextAction_ID == 0)
        {
			setCT_Component_ID (0);
			setFTU_MaintNextAction_ID (0);
			setFTU_MaintRequirement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintNextAction (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintNextAction[")
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
			set_ValueNoCheck (COLUMNNAME_CT_Component_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_Component_ID, Integer.valueOf(CT_Component_ID));
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

	/** Set Last Done (Date).
		@param FTU_DateLastDone 
		The date when the maintenance requirement was last performed.
	  */
	public void setFTU_DateLastDone (Timestamp FTU_DateLastDone)
	{
		set_Value (COLUMNNAME_FTU_DateLastDone, FTU_DateLastDone);
	}

	/** Get Last Done (Date).
		@return The date when the maintenance requirement was last performed.
	  */
	public Timestamp getFTU_DateLastDone () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FTU_DateLastDone);
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
		@param FTU_DateTolApplied 
		The amount of date/time tolerance applied on the last maintenance action
	  */
	public void setFTU_DateTolApplied (BigDecimal FTU_DateTolApplied)
	{
		set_Value (COLUMNNAME_FTU_DateTolApplied, FTU_DateTolApplied);
	}

	/** Get Date Tolerance Applied.
		@return The amount of date/time tolerance applied on the last maintenance action
	  */
	public BigDecimal getFTU_DateTolApplied () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_DateTolApplied);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Days Remaining.
		@param FTU_DaysRemaining 
		The number of days remaining before the next maintenance action is due.
	  */
	public void setFTU_DaysRemaining (BigDecimal FTU_DaysRemaining)
	{
		throw new IllegalArgumentException ("FTU_DaysRemaining is virtual column");	}

	/** Get Days Remaining.
		@return The number of days remaining before the next maintenance action is due.
	  */
	public BigDecimal getFTU_DaysRemaining () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_DaysRemaining);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Next Maintenance Action.
		@param FTU_MaintNextAction_ID Next Maintenance Action	  */
	public void setFTU_MaintNextAction_ID (int FTU_MaintNextAction_ID)
	{
		if (FTU_MaintNextAction_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintNextAction_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintNextAction_ID, Integer.valueOf(FTU_MaintNextAction_ID));
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

	/** Set Last Done (Use).
		@param FTU_UsageLastDone 
		The usage or life of the component when the maintenance requirement was last performed.
	  */
	public void setFTU_UsageLastDone (BigDecimal FTU_UsageLastDone)
	{
		set_Value (COLUMNNAME_FTU_UsageLastDone, FTU_UsageLastDone);
	}

	/** Get Last Done (Use).
		@return The usage or life of the component when the maintenance requirement was last performed.
	  */
	public BigDecimal getFTU_UsageLastDone () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UsageLastDone);
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

	/** Set Use Remaining.
		@param FTU_UseRemaining 
		The amount of use/life remaining before the next maintenance action is due.
	  */
	public void setFTU_UseRemaining (BigDecimal FTU_UseRemaining)
	{
		throw new IllegalArgumentException ("FTU_UseRemaining is virtual column");	}

	/** Get Use Remaining.
		@return The amount of use/life remaining before the next maintenance action is due.
	  */
	public BigDecimal getFTU_UseRemaining () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UseRemaining);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
		throw new IllegalArgumentException ("Root_Component_ID is virtual column");	}

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

	/** Set Row Status.
		@param RowStatus 
		A code that indicates the status of a row.
	  */
	public void setRowStatus (BigDecimal RowStatus)
	{
		throw new IllegalArgumentException ("RowStatus is virtual column");	}

	/** Get Row Status.
		@return A code that indicates the status of a row.
	  */
	public BigDecimal getRowStatus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RowStatus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}