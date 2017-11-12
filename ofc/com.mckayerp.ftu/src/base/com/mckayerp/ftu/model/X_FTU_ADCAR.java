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

/** Generated Model for FTU_ADCAR
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_ADCAR extends PO implements I_FTU_ADCAR, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170414L;

    /** Standard Constructor */
    public X_FTU_ADCAR (Properties ctx, int FTU_ADCAR_ID, String trxName)
    {
      super (ctx, FTU_ADCAR_ID, trxName);
      /** if (FTU_ADCAR_ID == 0)
        {
			setFTU_ADCAR_ID (0);
			setFTU_AirworthinessDirective_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_ADCAR (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_ADCAR[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Action.
		@param FTU_ADAction 
		The action that must be taken to address the AD
	  */
	public void setFTU_ADAction (String FTU_ADAction)
	{
		set_Value (COLUMNNAME_FTU_ADAction, FTU_ADAction);
	}

	/** Get Action.
		@return The action that must be taken to address the AD
	  */
	public String getFTU_ADAction () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADAction);
	}

	/** Set Airworthiness Directive Corrective Action Requirements ID.
		@param FTU_ADCAR_ID Airworthiness Directive Corrective Action Requirements ID	  */
	public void setFTU_ADCAR_ID (int FTU_ADCAR_ID)
	{
		if (FTU_ADCAR_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_ADCAR_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_ADCAR_ID, Integer.valueOf(FTU_ADCAR_ID));
	}

	/** Get Airworthiness Directive Corrective Action Requirements ID.
		@return Airworthiness Directive Corrective Action Requirements ID	  */
	public int getFTU_ADCAR_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ADCAR_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FTU_ADComplianceType AD_Reference_ID=53879 */
	public static final int FTU_ADCOMPLIANCETYPE_AD_Reference_ID=53879;
	/** Once (Within next ...) = Once */
	public static final String FTU_ADCOMPLIANCETYPE_OnceWithinNext = "Once";
	/** Repeat (every ...) = Repeat */
	public static final String FTU_ADCOMPLIANCETYPE_RepeatEvery = "Repeat";
	/** Before further flight or use = BeforeUse */
	public static final String FTU_ADCOMPLIANCETYPE_BeforeFurtherFlightOrUse = "BeforeUse";
	/** Before (date) = BeforeDate */
	public static final String FTU_ADCOMPLIANCETYPE_BeforeDate = "BeforeDate";
	/** Set Compliance Type.
		@param FTU_ADComplianceType 
		The compliance type of the requirement, either once or repetitive.
	  */
	public void setFTU_ADComplianceType (String FTU_ADComplianceType)
	{

		set_Value (COLUMNNAME_FTU_ADComplianceType, FTU_ADComplianceType);
	}

	/** Get Compliance Type.
		@return The compliance type of the requirement, either once or repetitive.
	  */
	public String getFTU_ADComplianceType () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADComplianceType);
	}

	/** Set Process.
		@param FTU_ADProcess 
		A text description of the process to follow.
	  */
	public void setFTU_ADProcess (String FTU_ADProcess)
	{
		set_Value (COLUMNNAME_FTU_ADProcess, FTU_ADProcess);
	}

	/** Get Process.
		@return A text description of the process to follow.
	  */
	public String getFTU_ADProcess () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADProcess);
	}

	/** Set Time Interval.
		@param FTU_ADTimeInterval 
		The time interval limit for the action. Leave blank/null for no time limit.
	  */
	public void setFTU_ADTimeInterval (BigDecimal FTU_ADTimeInterval)
	{
		set_Value (COLUMNNAME_FTU_ADTimeInterval, FTU_ADTimeInterval);
	}

	/** Get Time Interval.
		@return The time interval limit for the action. Leave blank/null for no time limit.
	  */
	public BigDecimal getFTU_ADTimeInterval () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_ADTimeInterval);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_UOM getFTU_ADTimeIntervalUOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getFTU_ADTimeIntervalUOM_ID(), get_TrxName());	}

	/** Set Time Interval UOM.
		@param FTU_ADTimeIntervalUOM_ID 
		The Unit of Measure (UOM) of the Time interval.  Typically days.
	  */
	public void setFTU_ADTimeIntervalUOM_ID (int FTU_ADTimeIntervalUOM_ID)
	{
		if (FTU_ADTimeIntervalUOM_ID < 1) 
			set_Value (COLUMNNAME_FTU_ADTimeIntervalUOM_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_ADTimeIntervalUOM_ID, Integer.valueOf(FTU_ADTimeIntervalUOM_ID));
	}

	/** Get Time Interval UOM.
		@return The Unit of Measure (UOM) of the Time interval.  Typically days.
	  */
	public int getFTU_ADTimeIntervalUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ADTimeIntervalUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Usage Interval.
		@param FTU_ADUsageInterval 
		The usage or Time in Service (TIS) interval limit for the action. Leave blank/null for no usage limit.
	  */
	public void setFTU_ADUsageInterval (BigDecimal FTU_ADUsageInterval)
	{
		set_Value (COLUMNNAME_FTU_ADUsageInterval, FTU_ADUsageInterval);
	}

	/** Get Usage Interval.
		@return The usage or Time in Service (TIS) interval limit for the action. Leave blank/null for no usage limit.
	  */
	public BigDecimal getFTU_ADUsageInterval () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_ADUsageInterval);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_UOM getFTU_ADUsageIntervalUOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getFTU_ADUsageIntervalUOM_ID(), get_TrxName());	}

	/** Set Usage Interval UOM.
		@param FTU_ADUsageIntervalUOM_ID 
		The Unit of Measure (UOM) of the usage interval.  Typically hours.
	  */
	public void setFTU_ADUsageIntervalUOM_ID (int FTU_ADUsageIntervalUOM_ID)
	{
		if (FTU_ADUsageIntervalUOM_ID < 1) 
			set_Value (COLUMNNAME_FTU_ADUsageIntervalUOM_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_ADUsageIntervalUOM_ID, Integer.valueOf(FTU_ADUsageIntervalUOM_ID));
	}

	/** Get Usage Interval UOM.
		@return The Unit of Measure (UOM) of the usage interval.  Typically hours.
	  */
	public int getFTU_ADUsageIntervalUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ADUsageIntervalUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}