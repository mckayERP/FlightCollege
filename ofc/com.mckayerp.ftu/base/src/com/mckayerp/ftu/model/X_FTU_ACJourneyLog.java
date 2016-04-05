/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for FTU_ACJourneyLog
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_ACJourneyLog extends PO implements I_FTU_ACJourneyLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160222L;

    /** Standard Constructor */
    public X_FTU_ACJourneyLog (Properties ctx, int FTU_ACJourneyLog_ID, String trxName)
    {
      super (ctx, FTU_ACJourneyLog_ID, trxName);
      /** if (FTU_ACJourneyLog_ID == 0)
        {
			setFTU_ACJourneyLog_ID (0);
			setFTU_Aircraft_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_ACJourneyLog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_ACJourneyLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Air Time.
		@param AirTime 
		The time intervale measured in hours from the moment the aircraft leaves the ground to the moment it contacts the ground again.
	  */
	public void setAirTime (BigDecimal AirTime)
	{
		set_ValueNoCheck (COLUMNNAME_AirTime, AirTime);
	}

	/** Get Air Time.
		@return The time intervale measured in hours from the moment the aircraft leaves the ground to the moment it contacts the ground again.
	  */
	public BigDecimal getAirTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AirTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Entry Date.
		@param EntryDate 
		The date and time of the last journey logbook entry.
	  */
	public void setEntryDate (Timestamp EntryDate)
	{
		set_Value (COLUMNNAME_EntryDate, EntryDate);
	}

	/** Get Entry Date.
		@return The date and time of the last journey logbook entry.
	  */
	public Timestamp getEntryDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EntryDate);
	}

	/** Set Flight Date.
		@param FlightDate 
		The date of the start of the flight.
	  */
	public void setFlightDate (Timestamp FlightDate)
	{
		set_ValueNoCheck (COLUMNNAME_FlightDate, FlightDate);
	}

	/** Get Flight Date.
		@return The date of the start of the flight.
	  */
	public Timestamp getFlightDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FlightDate);
	}

	/** Set Flight Time.
		@param FlightTime 
		The time intervale from the moment the aircraft first moves under its own power for the purposes of taking off until the moment it comes to rest.
	  */
	public void setFlightTime (BigDecimal FlightTime)
	{
		set_ValueNoCheck (COLUMNNAME_FlightTime, FlightTime);
	}

	/** Get Flight Time.
		@return The time intervale from the moment the aircraft first moves under its own power for the purposes of taking off until the moment it comes to rest.
	  */
	public BigDecimal getFlightTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlightTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Aircraft Journey Log.
		@param FTU_ACJourneyLog_ID Aircraft Journey Log	  */
	public void setFTU_ACJourneyLog_ID (int FTU_ACJourneyLog_ID)
	{
		if (FTU_ACJourneyLog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_ACJourneyLog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_ACJourneyLog_ID, Integer.valueOf(FTU_ACJourneyLog_ID));
	}

	/** Get Aircraft Journey Log.
		@return Aircraft Journey Log	  */
	public int getFTU_ACJourneyLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ACJourneyLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_Aircraft getFTU_Aircraft() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Aircraft)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Aircraft.Table_Name)
			.getPO(getFTU_Aircraft_ID(), get_TrxName());	}

	/** Set Aircraft.
		@param FTU_Aircraft_ID Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID)
	{
		if (FTU_Aircraft_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Aircraft_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Aircraft_ID, Integer.valueOf(FTU_Aircraft_ID));
	}

	/** Get Aircraft.
		@return Aircraft	  */
	public int getFTU_Aircraft_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Aircraft_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Intended Flight.
		@param IntendedFlight Intended Flight	  */
	public void setIntendedFlight (String IntendedFlight)
	{
		set_ValueNoCheck (COLUMNNAME_IntendedFlight, IntendedFlight);
	}

	/** Get Intended Flight.
		@return Intended Flight	  */
	public String getIntendedFlight () 
	{
		return (String)get_Value(COLUMNNAME_IntendedFlight);
	}

	/** Set Number Legs.
		@param NumberLegs Number Legs	  */
	public void setNumberLegs (int NumberLegs)
	{
		set_ValueNoCheck (COLUMNNAME_NumberLegs, Integer.valueOf(NumberLegs));
	}

	/** Get Number Legs.
		@return Number Legs	  */
	public int getNumberLegs () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumberLegs);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of Ops.
		@param NumOps 
		The number of operations conducted during this flight entry.
	  */
	public void setNumOps (int NumOps)
	{
		set_ValueNoCheck (COLUMNNAME_NumOps, Integer.valueOf(NumOps));
	}

	/** Get Number of Ops.
		@return The number of operations conducted during this flight entry.
	  */
	public int getNumOps () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumOps);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AC Registration.
		@param Registration AC Registration	  */
	public void setRegistration (String Registration)
	{
		set_ValueNoCheck (COLUMNNAME_Registration, Registration);
	}

	/** Get AC Registration.
		@return AC Registration	  */
	public String getRegistration () 
	{
		return (String)get_Value(COLUMNNAME_Registration);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_ValueNoCheck (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
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

	/** Set Total Airframe Time.
		@param TotalAirframeTime Total Airframe Time	  */
	public void setTotalAirframeTime (BigDecimal TotalAirframeTime)
	{
		set_ValueNoCheck (COLUMNNAME_TotalAirframeTime, TotalAirframeTime);
	}

	/** Get Total Airframe Time.
		@return Total Airframe Time	  */
	public BigDecimal getTotalAirframeTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAirframeTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Wheels Down.
		@param WheelsDown Wheels Down	  */
	public void setWheelsDown (Timestamp WheelsDown)
	{
		set_ValueNoCheck (COLUMNNAME_WheelsDown, WheelsDown);
	}

	/** Get Wheels Down.
		@return Wheels Down	  */
	public Timestamp getWheelsDown () 
	{
		return (Timestamp)get_Value(COLUMNNAME_WheelsDown);
	}

	/** Set Wheels Up.
		@param WheelsUp Wheels Up	  */
	public void setWheelsUp (Timestamp WheelsUp)
	{
		set_ValueNoCheck (COLUMNNAME_WheelsUp, WheelsUp);
	}

	/** Get Wheels Up.
		@return Wheels Up	  */
	public Timestamp getWheelsUp () 
	{
		return (Timestamp)get_Value(COLUMNNAME_WheelsUp);
	}
}