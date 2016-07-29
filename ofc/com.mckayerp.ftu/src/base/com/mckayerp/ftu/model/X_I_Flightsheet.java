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

/** Generated Model for I_Flightsheet
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_I_Flightsheet extends PO implements I_I_Flightsheet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160721L;

    /** Standard Constructor */
    public X_I_Flightsheet (Properties ctx, int I_Flightsheet_ID, String trxName)
    {
      super (ctx, I_Flightsheet_ID, trxName);
      /** if (I_Flightsheet_ID == 0)
        {
			setI_Flightsheet_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_Flightsheet (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_I_Flightsheet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Acknowledged By.
		@param AcknowledgedBy Acknowledged By	  */
	public void setAcknowledgedBy (String AcknowledgedBy)
	{
		set_Value (COLUMNNAME_AcknowledgedBy, AcknowledgedBy);
	}

	/** Get Acknowledged By.
		@return Acknowledged By	  */
	public String getAcknowledgedBy () 
	{
		return (String)get_Value(COLUMNNAME_AcknowledgedBy);
	}

	/** Set Activate.
		@param Activate 
		If this field contains the word "Activate", then the flight has not been activated yet.  Status should be waiting.
	  */
	public void setActivate (String Activate)
	{
		set_Value (COLUMNNAME_Activate, Activate);
	}

	/** Get Activate.
		@return If this field contains the word "Activate", then the flight has not been activated yet.  Status should be waiting.
	  */
	public String getActivate () 
	{
		return (String)get_Value(COLUMNNAME_Activate);
	}

	/** Set Air Time.
		@param AirTime 
		The time intervale measured in hours from the moment the aircraft leaves the ground to the moment it contacts the ground again.
	  */
	public void setAirTime (BigDecimal AirTime)
	{
		set_Value (COLUMNNAME_AirTime, AirTime);
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

	/** Set Authorize.
		@param Authorize 
		If this field contains the text "Authorize", the flight has been activated but not authorized.
	  */
	public void setAuthorize (String Authorize)
	{
		set_Value (COLUMNNAME_Authorize, Authorize);
	}

	/** Get Authorize.
		@return If this field contains the text "Authorize", the flight has been activated but not authorized.
	  */
	public String getAuthorize () 
	{
		return (String)get_Value(COLUMNNAME_Authorize);
	}

	/** Set Autorized By.
		@param AuthorizedBy Autorized By	  */
	public void setAuthorizedBy (String AuthorizedBy)
	{
		set_Value (COLUMNNAME_AuthorizedBy, AuthorizedBy);
	}

	/** Get Autorized By.
		@return Autorized By	  */
	public String getAuthorizedBy () 
	{
		return (String)get_Value(COLUMNNAME_AuthorizedBy);
	}

	/** Set Briefing.
		@param Briefing 
		Time spent in briefings
	  */
	public void setBriefing (String Briefing)
	{
		set_Value (COLUMNNAME_Briefing, Briefing);
	}

	/** Get Briefing.
		@return Time spent in briefings
	  */
	public String getBriefing () 
	{
		return (String)get_Value(COLUMNNAME_Briefing);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Captain or PIC.
		@param Captain_PIC 
		The Captain or Pilot in Command
	  */
	public void setCaptain_PIC (String Captain_PIC)
	{
		set_Value (COLUMNNAME_Captain_PIC, Captain_PIC);
	}

	/** Get Captain or PIC.
		@return The Captain or Pilot in Command
	  */
	public String getCaptain_PIC () 
	{
		return (String)get_Value(COLUMNNAME_Captain_PIC);
	}

	/** Set Course Type.
		@param CourseType Course Type	  */
	public void setCourseType (String CourseType)
	{
		set_Value (COLUMNNAME_CourseType, CourseType);
	}

	/** Get Course Type.
		@return Course Type	  */
	public String getCourseType () 
	{
		return (String)get_Value(COLUMNNAME_CourseType);
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

	/** Set Engine Start.
		@param EngineStart 
		The start time of the engine.
	  */
	public void setEngineStart (Timestamp EngineStart)
	{
		set_Value (COLUMNNAME_EngineStart, EngineStart);
	}

	/** Get Engine Start.
		@return The start time of the engine.
	  */
	public Timestamp getEngineStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EngineStart);
	}

	/** Set Engine Stop.
		@param EngineStop 
		The stop time of the engine.
	  */
	public void setEngineStop (Timestamp EngineStop)
	{
		set_Value (COLUMNNAME_EngineStop, EngineStop);
	}

	/** Get Engine Stop.
		@return The stop time of the engine.
	  */
	public Timestamp getEngineStop () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EngineStop);
	}

	/** Set Flight Date.
		@param FlightDate 
		The date of the start of the flight.
	  */
	public void setFlightDate (Timestamp FlightDate)
	{
		set_Value (COLUMNNAME_FlightDate, FlightDate);
	}

	/** Get Flight Date.
		@return The date of the start of the flight.
	  */
	public Timestamp getFlightDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FlightDate);
	}

	/** Set Flight ID.
		@param FlightID 
		The ID number of the Flightsheet
	  */
	public void setFlightID (BigDecimal FlightID)
	{
		set_Value (COLUMNNAME_FlightID, FlightID);
	}

	/** Get Flight ID.
		@return The ID number of the Flightsheet
	  */
	public BigDecimal getFlightID () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlightID);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Client ID.
		@param Flightsheet_ClientID 
		The member number or ID from the Flightsheet
	  */
	public void setFlightsheet_ClientID (String Flightsheet_ClientID)
	{
		set_Value (COLUMNNAME_Flightsheet_ClientID, Flightsheet_ClientID);
	}

	/** Get Client ID.
		@return The member number or ID from the Flightsheet
	  */
	public String getFlightsheet_ClientID () 
	{
		return (String)get_Value(COLUMNNAME_Flightsheet_ClientID);
	}

	/** Set Invoice Number.
		@param Flightsheet_InvoiceNo Invoice Number	  */
	public void setFlightsheet_InvoiceNo (String Flightsheet_InvoiceNo)
	{
		set_Value (COLUMNNAME_Flightsheet_InvoiceNo, Flightsheet_InvoiceNo);
	}

	/** Get Invoice Number.
		@return Invoice Number	  */
	public String getFlightsheet_InvoiceNo () 
	{
		return (String)get_Value(COLUMNNAME_Flightsheet_InvoiceNo);
	}

	/** Set Charter Time.
		@param FlightTime_Charter 
		The time spent performing charter operations.
	  */
	public void setFlightTime_Charter (String FlightTime_Charter)
	{
		set_Value (COLUMNNAME_FlightTime_Charter, FlightTime_Charter);
	}

	/** Get Charter Time.
		@return The time spent performing charter operations.
	  */
	public String getFlightTime_Charter () 
	{
		return (String)get_Value(COLUMNNAME_FlightTime_Charter);
	}

	/** Set Dual Time.
		@param FlightTime_Dual 
		The time spent under instruction while training.
	  */
	public void setFlightTime_Dual (String FlightTime_Dual)
	{
		set_Value (COLUMNNAME_FlightTime_Dual, FlightTime_Dual);
	}

	/** Get Dual Time.
		@return The time spent under instruction while training.
	  */
	public String getFlightTime_Dual () 
	{
		return (String)get_Value(COLUMNNAME_FlightTime_Dual);
	}

	/** Set Intro Time.
		@param FlightTime_Intro 
		Flight time spent performing and Introductory Flight
	  */
	public void setFlightTime_Intro (String FlightTime_Intro)
	{
		set_Value (COLUMNNAME_FlightTime_Intro, FlightTime_Intro);
	}

	/** Get Intro Time.
		@return Flight time spent performing and Introductory Flight
	  */
	public String getFlightTime_Intro () 
	{
		return (String)get_Value(COLUMNNAME_FlightTime_Intro);
	}

	/** Set NonRev Time.
		@param FlightTime_NonRev 
		Flight time spent performing non-revenue generating work.
	  */
	public void setFlightTime_NonRev (String FlightTime_NonRev)
	{
		set_Value (COLUMNNAME_FlightTime_NonRev, FlightTime_NonRev);
	}

	/** Get NonRev Time.
		@return Flight time spent performing non-revenue generating work.
	  */
	public String getFlightTime_NonRev () 
	{
		return (String)get_Value(COLUMNNAME_FlightTime_NonRev);
	}

	/** Set Rental Time.
		@param FlightTime_Rental 
		Flight time spent by rental of the aircraft.
	  */
	public void setFlightTime_Rental (String FlightTime_Rental)
	{
		set_Value (COLUMNNAME_FlightTime_Rental, FlightTime_Rental);
	}

	/** Get Rental Time.
		@return Flight time spent by rental of the aircraft.
	  */
	public String getFlightTime_Rental () 
	{
		return (String)get_Value(COLUMNNAME_FlightTime_Rental);
	}

	/** Set Solo Time.
		@param FlightTime_Solo 
		Flight time spent as a solo pilot. Includes flight tests.
	  */
	public void setFlightTime_Solo (String FlightTime_Solo)
	{
		set_Value (COLUMNNAME_FlightTime_Solo, FlightTime_Solo);
	}

	/** Get Solo Time.
		@return Flight time spent as a solo pilot. Includes flight tests.
	  */
	public String getFlightTime_Solo () 
	{
		return (String)get_Value(COLUMNNAME_FlightTime_Solo);
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
			set_Value (COLUMNNAME_FTU_Aircraft_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Aircraft_ID, Integer.valueOf(FTU_Aircraft_ID));
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

	public com.mckayerp.ftu.model.I_FTU_Instructor getFTU_Instructor() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Instructor)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Instructor.Table_Name)
			.getPO(getFTU_Instructor_ID(), get_TrxName());	}

	/** Set Flight Instructor.
		@param FTU_Instructor_ID Flight Instructor	  */
	public void setFTU_Instructor_ID (int FTU_Instructor_ID)
	{
		if (FTU_Instructor_ID < 1) 
			set_Value (COLUMNNAME_FTU_Instructor_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Instructor_ID, Integer.valueOf(FTU_Instructor_ID));
	}

	/** Get Flight Instructor.
		@return Flight Instructor	  */
	public int getFTU_Instructor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Instructor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fuel.
		@param Fuel 
		Fuel listed on the Flightsheet
	  */
	public void setFuel (int Fuel)
	{
		set_Value (COLUMNNAME_Fuel, Integer.valueOf(Fuel));
	}

	/** Get Fuel.
		@return Fuel listed on the Flightsheet
	  */
	public int getFuel () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fuel);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Acknowledged By.
		@param I_Acknowledged_By Acknowledged By	  */
	public void setI_Acknowledged_By (String I_Acknowledged_By)
	{
		set_Value (COLUMNNAME_I_Acknowledged_By, I_Acknowledged_By);
	}

	/** Get Acknowledged By.
		@return Acknowledged By	  */
	public String getI_Acknowledged_By () 
	{
		return (String)get_Value(COLUMNNAME_I_Acknowledged_By);
	}

	/** Set Air Time.
		@param I_AirTime Air Time	  */
	public void setI_AirTime (String I_AirTime)
	{
		set_Value (COLUMNNAME_I_AirTime, I_AirTime);
	}

	/** Get Air Time.
		@return Air Time	  */
	public String getI_AirTime () 
	{
		return (String)get_Value(COLUMNNAME_I_AirTime);
	}

	/** Set Authorized By.
		@param I_AuthorizedBy Authorized By	  */
	public void setI_AuthorizedBy (String I_AuthorizedBy)
	{
		set_Value (COLUMNNAME_I_AuthorizedBy, I_AuthorizedBy);
	}

	/** Get Authorized By.
		@return Authorized By	  */
	public String getI_AuthorizedBy () 
	{
		return (String)get_Value(COLUMNNAME_I_AuthorizedBy);
	}

	/** Set Briefing.
		@param I_Briefing Briefing	  */
	public void setI_Briefing (String I_Briefing)
	{
		set_Value (COLUMNNAME_I_Briefing, I_Briefing);
	}

	/** Get Briefing.
		@return Briefing	  */
	public String getI_Briefing () 
	{
		return (String)get_Value(COLUMNNAME_I_Briefing);
	}

	/** Set Call Marks.
		@param I_CallMarks Call Marks	  */
	public void setI_CallMarks (String I_CallMarks)
	{
		set_Value (COLUMNNAME_I_CallMarks, I_CallMarks);
	}

	/** Get Call Marks.
		@return Call Marks	  */
	public String getI_CallMarks () 
	{
		return (String)get_Value(COLUMNNAME_I_CallMarks);
	}

	/** Set Captain or PIC.
		@param I_Captain_PIC Captain or PIC	  */
	public void setI_Captain_PIC (String I_Captain_PIC)
	{
		set_Value (COLUMNNAME_I_Captain_PIC, I_Captain_PIC);
	}

	/** Get Captain or PIC.
		@return Captain or PIC	  */
	public String getI_Captain_PIC () 
	{
		return (String)get_Value(COLUMNNAME_I_Captain_PIC);
	}

	/** Set Client ID.
		@param I_ClientID 
		The Flighsheet Client ID
	  */
	public void setI_ClientID (String I_ClientID)
	{
		set_Value (COLUMNNAME_I_ClientID, I_ClientID);
	}

	/** Get Client ID.
		@return The Flighsheet Client ID
	  */
	public String getI_ClientID () 
	{
		return (String)get_Value(COLUMNNAME_I_ClientID);
	}

	/** Set Course Type.
		@param I_CourseType 
		The flightsheet course type
	  */
	public void setI_CourseType (String I_CourseType)
	{
		set_Value (COLUMNNAME_I_CourseType, I_CourseType);
	}

	/** Get Course Type.
		@return The flightsheet course type
	  */
	public String getI_CourseType () 
	{
		return (String)get_Value(COLUMNNAME_I_CourseType);
	}

	/** Set Engine Start.
		@param I_EngineStart 
		The time that engines were started.  Also the start of Flight Time.
	  */
	public void setI_EngineStart (String I_EngineStart)
	{
		set_Value (COLUMNNAME_I_EngineStart, I_EngineStart);
	}

	/** Get Engine Start.
		@return The time that engines were started.  Also the start of Flight Time.
	  */
	public String getI_EngineStart () 
	{
		return (String)get_Value(COLUMNNAME_I_EngineStart);
	}

	/** Set Engine Stop.
		@param I_EngineStop Engine Stop	  */
	public void setI_EngineStop (String I_EngineStop)
	{
		set_Value (COLUMNNAME_I_EngineStop, I_EngineStop);
	}

	/** Get Engine Stop.
		@return Engine Stop	  */
	public String getI_EngineStop () 
	{
		return (String)get_Value(COLUMNNAME_I_EngineStop);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Flight Day.
		@param I_FlightDay Flight Day	  */
	public void setI_FlightDay (BigDecimal I_FlightDay)
	{
		set_Value (COLUMNNAME_I_FlightDay, I_FlightDay);
	}

	/** Get Flight Day.
		@return Flight Day	  */
	public BigDecimal getI_FlightDay () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_I_FlightDay);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Flight ID.
		@param I_FlightID 
		The Flightsheet ID number.
	  */
	public void setI_FlightID (BigDecimal I_FlightID)
	{
		set_Value (COLUMNNAME_I_FlightID, I_FlightID);
	}

	/** Get Flight ID.
		@return The Flightsheet ID number.
	  */
	public BigDecimal getI_FlightID () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_I_FlightID);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Flight Month.
		@param I_FlightMonth Flight Month	  */
	public void setI_FlightMonth (BigDecimal I_FlightMonth)
	{
		set_Value (COLUMNNAME_I_FlightMonth, I_FlightMonth);
	}

	/** Get Flight Month.
		@return Flight Month	  */
	public BigDecimal getI_FlightMonth () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_I_FlightMonth);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set I_Flightsheet ID.
		@param I_Flightsheet_ID I_Flightsheet ID	  */
	public void setI_Flightsheet_ID (int I_Flightsheet_ID)
	{
		if (I_Flightsheet_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_Flightsheet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_Flightsheet_ID, Integer.valueOf(I_Flightsheet_ID));
	}

	/** Get I_Flightsheet ID.
		@return I_Flightsheet ID	  */
	public int getI_Flightsheet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_Flightsheet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charter Time.
		@param I_FlightTime_Charter Charter Time	  */
	public void setI_FlightTime_Charter (String I_FlightTime_Charter)
	{
		set_Value (COLUMNNAME_I_FlightTime_Charter, I_FlightTime_Charter);
	}

	/** Get Charter Time.
		@return Charter Time	  */
	public String getI_FlightTime_Charter () 
	{
		return (String)get_Value(COLUMNNAME_I_FlightTime_Charter);
	}

	/** Set Dual Time.
		@param I_FlightTime_Dual Dual Time	  */
	public void setI_FlightTime_Dual (String I_FlightTime_Dual)
	{
		set_Value (COLUMNNAME_I_FlightTime_Dual, I_FlightTime_Dual);
	}

	/** Get Dual Time.
		@return Dual Time	  */
	public String getI_FlightTime_Dual () 
	{
		return (String)get_Value(COLUMNNAME_I_FlightTime_Dual);
	}

	/** Set Intro Time.
		@param I_FlightTime_Intro Intro Time	  */
	public void setI_FlightTime_Intro (String I_FlightTime_Intro)
	{
		set_Value (COLUMNNAME_I_FlightTime_Intro, I_FlightTime_Intro);
	}

	/** Get Intro Time.
		@return Intro Time	  */
	public String getI_FlightTime_Intro () 
	{
		return (String)get_Value(COLUMNNAME_I_FlightTime_Intro);
	}

	/** Set Nonrev Time.
		@param I_FlightTime_Nonrev Nonrev Time	  */
	public void setI_FlightTime_Nonrev (String I_FlightTime_Nonrev)
	{
		set_Value (COLUMNNAME_I_FlightTime_Nonrev, I_FlightTime_Nonrev);
	}

	/** Get Nonrev Time.
		@return Nonrev Time	  */
	public String getI_FlightTime_Nonrev () 
	{
		return (String)get_Value(COLUMNNAME_I_FlightTime_Nonrev);
	}

	/** Set Rental Time.
		@param I_FlightTime_Rental Rental Time	  */
	public void setI_FlightTime_Rental (String I_FlightTime_Rental)
	{
		set_Value (COLUMNNAME_I_FlightTime_Rental, I_FlightTime_Rental);
	}

	/** Get Rental Time.
		@return Rental Time	  */
	public String getI_FlightTime_Rental () 
	{
		return (String)get_Value(COLUMNNAME_I_FlightTime_Rental);
	}

	/** Set Solo Time.
		@param I_FlightTime_Solo Solo Time	  */
	public void setI_FlightTime_Solo (String I_FlightTime_Solo)
	{
		set_Value (COLUMNNAME_I_FlightTime_Solo, I_FlightTime_Solo);
	}

	/** Get Solo Time.
		@return Solo Time	  */
	public String getI_FlightTime_Solo () 
	{
		return (String)get_Value(COLUMNNAME_I_FlightTime_Solo);
	}

	/** Set Flight Year.
		@param I_FlightYear Flight Year	  */
	public void setI_FlightYear (BigDecimal I_FlightYear)
	{
		set_Value (COLUMNNAME_I_FlightYear, I_FlightYear);
	}

	/** Get Flight Year.
		@return Flight Year	  */
	public BigDecimal getI_FlightYear () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_I_FlightYear);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fuel.
		@param I_Fuel Fuel	  */
	public void setI_Fuel (String I_Fuel)
	{
		set_Value (COLUMNNAME_I_Fuel, I_Fuel);
	}

	/** Get Fuel.
		@return Fuel	  */
	public String getI_Fuel () 
	{
		return (String)get_Value(COLUMNNAME_I_Fuel);
	}

	/** Set Intended Flight.
		@param I_IntendedFlight Intended Flight	  */
	public void setI_IntendedFlight (String I_IntendedFlight)
	{
		set_Value (COLUMNNAME_I_IntendedFlight, I_IntendedFlight);
	}

	/** Get Intended Flight.
		@return Intended Flight	  */
	public String getI_IntendedFlight () 
	{
		return (String)get_Value(COLUMNNAME_I_IntendedFlight);
	}

	/** Set Invoice Number.
		@param I_InvoiceNumber Invoice Number	  */
	public void setI_InvoiceNumber (String I_InvoiceNumber)
	{
		set_Value (COLUMNNAME_I_InvoiceNumber, I_InvoiceNumber);
	}

	/** Get Invoice Number.
		@return Invoice Number	  */
	public String getI_InvoiceNumber () 
	{
		return (String)get_Value(COLUMNNAME_I_InvoiceNumber);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Number Legs.
		@param I_NumLegs Number Legs	  */
	public void setI_NumLegs (int I_NumLegs)
	{
		set_Value (COLUMNNAME_I_NumLegs, Integer.valueOf(I_NumLegs));
	}

	/** Get Number Legs.
		@return Number Legs	  */
	public int getI_NumLegs () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_NumLegs);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Returning At.
		@param I_ReturningAt Returning At	  */
	public void setI_ReturningAt (String I_ReturningAt)
	{
		set_Value (COLUMNNAME_I_ReturningAt, I_ReturningAt);
	}

	/** Get Returning At.
		@return Returning At	  */
	public String getI_ReturningAt () 
	{
		return (String)get_Value(COLUMNNAME_I_ReturningAt);
	}

	/** Set Simulator Time.
		@param I_SimulatorTime Simulator Time	  */
	public void setI_SimulatorTime (String I_SimulatorTime)
	{
		set_Value (COLUMNNAME_I_SimulatorTime, I_SimulatorTime);
	}

	/** Get Simulator Time.
		@return Simulator Time	  */
	public String getI_SimulatorTime () 
	{
		return (String)get_Value(COLUMNNAME_I_SimulatorTime);
	}

	/** Set Student or Passenger.
		@param I_StudentPax Student or Passenger	  */
	public void setI_StudentPax (String I_StudentPax)
	{
		set_Value (COLUMNNAME_I_StudentPax, I_StudentPax);
	}

	/** Get Student or Passenger.
		@return Student or Passenger	  */
	public String getI_StudentPax () 
	{
		return (String)get_Value(COLUMNNAME_I_StudentPax);
	}

	/** Set Wheels Down.
		@param I_WheelsDown Wheels Down	  */
	public void setI_WheelsDown (String I_WheelsDown)
	{
		set_Value (COLUMNNAME_I_WheelsDown, I_WheelsDown);
	}

	/** Get Wheels Down.
		@return Wheels Down	  */
	public String getI_WheelsDown () 
	{
		return (String)get_Value(COLUMNNAME_I_WheelsDown);
	}

	/** Set Wheels Up.
		@param I_WheelsUp Wheels Up	  */
	public void setI_WheelsUp (String I_WheelsUp)
	{
		set_Value (COLUMNNAME_I_WheelsUp, I_WheelsUp);
	}

	/** Get Wheels Up.
		@return Wheels Up	  */
	public String getI_WheelsUp () 
	{
		return (String)get_Value(COLUMNNAME_I_WheelsUp);
	}

	/** Set Import Flightsheet Button.
		@param Import_Flightsheet_B Import Flightsheet Button	  */
	public void setImport_Flightsheet_B (String Import_Flightsheet_B)
	{
		set_Value (COLUMNNAME_Import_Flightsheet_B, Import_Flightsheet_B);
	}

	/** Get Import Flightsheet Button.
		@return Import Flightsheet Button	  */
	public String getImport_Flightsheet_B () 
	{
		return (String)get_Value(COLUMNNAME_Import_Flightsheet_B);
	}

	/** Set Intended Flight.
		@param IntendedFlight Intended Flight	  */
	public void setIntendedFlight (String IntendedFlight)
	{
		set_Value (COLUMNNAME_IntendedFlight, IntendedFlight);
	}

	/** Get Intended Flight.
		@return Intended Flight	  */
	public String getIntendedFlight () 
	{
		return (String)get_Value(COLUMNNAME_IntendedFlight);
	}

	/** Line_Status AD_Reference_ID=1000027 */
	public static final int LINE_STATUS_AD_Reference_ID=1000027;
	/** Waiting = Waiting */
	public static final String LINE_STATUS_Waiting = "Waiting";
	/** Activated = Activated */
	public static final String LINE_STATUS_Activated = "Activated";
	/** Acknowledged = Acknowledged */
	public static final String LINE_STATUS_Acknowledged = "Acknowledged";
	/** Authorized = Authorized */
	public static final String LINE_STATUS_Authorized = "Authorized";
	/** Completed = Completed */
	public static final String LINE_STATUS_Completed = "Completed";
	/** Closed = Closed */
	public static final String LINE_STATUS_Closed = "Closed";
	/** Set Status.
		@param Line_Status 
		The status of the flight sheet line item.
	  */
	public void setLine_Status (String Line_Status)
	{

		set_Value (COLUMNNAME_Line_Status, Line_Status);
	}

	/** Get Status.
		@return The status of the flight sheet line item.
	  */
	public String getLine_Status () 
	{
		return (String)get_Value(COLUMNNAME_Line_Status);
	}

	/** Set Num Legs.
		@param NumLegs Num Legs	  */
	public void setNumLegs (int NumLegs)
	{
		set_Value (COLUMNNAME_NumLegs, Integer.valueOf(NumLegs));
	}

	/** Get Num Legs.
		@return Num Legs	  */
	public int getNumLegs () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumLegs);
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

	/** Set Returning At.
		@param ReturingAt Returning At	  */
	public void setReturingAt (Timestamp ReturingAt)
	{
		set_Value (COLUMNNAME_ReturingAt, ReturingAt);
	}

	/** Get Returning At.
		@return Returning At	  */
	public Timestamp getReturingAt () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ReturingAt);
	}

	/** Set Simulator.
		@param Simulator Simulator	  */
	public void setSimulator (String Simulator)
	{
		set_Value (COLUMNNAME_Simulator, Simulator);
	}

	/** Get Simulator.
		@return Simulator	  */
	public String getSimulator () 
	{
		return (String)get_Value(COLUMNNAME_Simulator);
	}

	/** Set Student/PAX.
		@param StudentPAX 
		The student of passengers on the flight
	  */
	public void setStudentPAX (String StudentPAX)
	{
		set_Value (COLUMNNAME_StudentPAX, StudentPAX);
	}

	/** Get Student/PAX.
		@return The student of passengers on the flight
	  */
	public String getStudentPAX () 
	{
		return (String)get_Value(COLUMNNAME_StudentPAX);
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

	/** Set Wheels Down.
		@param WheelsDown Wheels Down	  */
	public void setWheelsDown (Timestamp WheelsDown)
	{
		set_Value (COLUMNNAME_WheelsDown, WheelsDown);
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
		set_Value (COLUMNNAME_WheelsUp, WheelsUp);
	}

	/** Get Wheels Up.
		@return Wheels Up	  */
	public Timestamp getWheelsUp () 
	{
		return (Timestamp)get_Value(COLUMNNAME_WheelsUp);
	}
}