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

/** Generated Model for FTU_Flightsheet
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_Flightsheet extends PO implements I_FTU_Flightsheet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160131L;

    /** Standard Constructor */
    public X_FTU_Flightsheet (Properties ctx, int FTU_Flightsheet_ID, String trxName)
    {
      super (ctx, FTU_Flightsheet_ID, trxName);
      /** if (FTU_Flightsheet_ID == 0)
        {
			setFTU_Flightsheet_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_Flightsheet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Flightsheet[")
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
	public void setBriefing (BigDecimal Briefing)
	{
		set_Value (COLUMNNAME_Briefing, Briefing);
	}

	/** Get Briefing.
		@return Time spent in briefings
	  */
	public BigDecimal getBriefing () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Briefing);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_Value (COLUMNNAME_C_Order_ID, null);
		else 
			set_Value (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
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

	/** CourseType AD_Reference_ID=1000033 */
	public static final int COURSETYPE_AD_Reference_ID=1000033;
	/** (pending) = (pending) */
	public static final String COURSETYPE_Pending = "(pending)";
	/** Cancelled = Cancelled */
	public static final String COURSETYPE_Cancelled = "Cancelled";
	/** CPL = CPL */
	public static final String COURSETYPE_CPL = "CPL";
	/** Instructor = Instructor */
	public static final String COURSETYPE_Instructor = "Instructor";
	/** Intro = Intro */
	public static final String COURSETYPE_Intro = "Intro";
	/** Multi Engine = Multi Engine */
	public static final String COURSETYPE_MultiEngine = "Multi Engine";
	/** Multi IFR = Multi IFR */
	public static final String COURSETYPE_MultiIFR = "Multi IFR";
	/** Night = Night */
	public static final String COURSETYPE_Night = "Night";
	/** No-Show = No-Show */
	public static final String COURSETYPE_No_Show = "No-Show";
	/** Other = Other */
	public static final String COURSETYPE_Other = "Other";
	/** PPL = PPL */
	public static final String COURSETYPE_PPL = "PPL";
	/** Rental = Rental */
	public static final String COURSETYPE_Rental = "Rental";
	/** RFP = RFP */
	public static final String COURSETYPE_RFP = "RFP";
	/** Single IFR = Single IFR */
	public static final String COURSETYPE_SingleIFR = "Single IFR";
	/** Tour = Tour */
	public static final String COURSETYPE_Tour = "Tour";
	/** VFR OTT = VFR OTT */
	public static final String COURSETYPE_VFROTT = "VFR OTT";
	/** Other Tuition = Other Tuition */
	public static final String COURSETYPE_OtherTuition = "Other Tuition";
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
	public void setFlightID (int FlightID)
	{
		set_Value (COLUMNNAME_FlightID, Integer.valueOf(FlightID));
	}

	/** Get Flight ID.
		@return The ID number of the Flightsheet
	  */
	public int getFlightID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FlightID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Flightsheet_ClientID AD_Reference_ID=1000070 */
	public static final int FLIGHTSHEET_CLIENTID_AD_Reference_ID=1000070;
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
	public void setFlightTime_Charter (BigDecimal FlightTime_Charter)
	{
		set_Value (COLUMNNAME_FlightTime_Charter, FlightTime_Charter);
	}

	/** Get Charter Time.
		@return The time spent performing charter operations.
	  */
	public BigDecimal getFlightTime_Charter () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlightTime_Charter);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Dual Time.
		@param FlightTime_Dual 
		The time spent under instruction while training.
	  */
	public void setFlightTime_Dual (BigDecimal FlightTime_Dual)
	{
		set_Value (COLUMNNAME_FlightTime_Dual, FlightTime_Dual);
	}

	/** Get Dual Time.
		@return The time spent under instruction while training.
	  */
	public BigDecimal getFlightTime_Dual () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlightTime_Dual);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Intro Time.
		@param FlightTime_Intro 
		Flight time spent performing and Introductory Flight
	  */
	public void setFlightTime_Intro (BigDecimal FlightTime_Intro)
	{
		set_Value (COLUMNNAME_FlightTime_Intro, FlightTime_Intro);
	}

	/** Get Intro Time.
		@return Flight time spent performing and Introductory Flight
	  */
	public BigDecimal getFlightTime_Intro () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlightTime_Intro);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set NonRev Time.
		@param FlightTime_NonRev 
		Flight time spent performing non-revenue generating work.
	  */
	public void setFlightTime_NonRev (BigDecimal FlightTime_NonRev)
	{
		set_Value (COLUMNNAME_FlightTime_NonRev, FlightTime_NonRev);
	}

	/** Get NonRev Time.
		@return Flight time spent performing non-revenue generating work.
	  */
	public BigDecimal getFlightTime_NonRev () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlightTime_NonRev);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rental Time.
		@param FlightTime_Rental 
		Flight time spent by rental of the aircraft.
	  */
	public void setFlightTime_Rental (BigDecimal FlightTime_Rental)
	{
		set_Value (COLUMNNAME_FlightTime_Rental, FlightTime_Rental);
	}

	/** Get Rental Time.
		@return Flight time spent by rental of the aircraft.
	  */
	public BigDecimal getFlightTime_Rental () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlightTime_Rental);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Solo Time.
		@param FlightTime_Solo 
		Flight time spent as a solo pilot. Includes flight tests.
	  */
	public void setFlightTime_Solo (BigDecimal FlightTime_Solo)
	{
		set_Value (COLUMNNAME_FlightTime_Solo, FlightTime_Solo);
	}

	/** Get Solo Time.
		@return Flight time spent as a solo pilot. Includes flight tests.
	  */
	public BigDecimal getFlightTime_Solo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlightTime_Solo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set OFC_Flightsheet ID.
		@param FTU_Flightsheet_ID OFC_Flightsheet ID	  */
	public void setFTU_Flightsheet_ID (int FTU_Flightsheet_ID)
	{
		if (FTU_Flightsheet_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Flightsheet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Flightsheet_ID, Integer.valueOf(FTU_Flightsheet_ID));
	}

	/** Get OFC_Flightsheet ID.
		@return OFC_Flightsheet ID	  */
	public int getFTU_Flightsheet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Flightsheet_ID);
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

	public com.mckayerp.ftu.model.I_FTU_Syllabus_Details getFTU_Syllabus_Details() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Syllabus_Details)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Syllabus_Details.Table_Name)
			.getPO(getFTU_Syllabus_Details_ID(), get_TrxName());	}

	/** Set Syllabus Details ID.
		@param FTU_Syllabus_Details_ID Syllabus Details ID	  */
	public void setFTU_Syllabus_Details_ID (int FTU_Syllabus_Details_ID)
	{
		if (FTU_Syllabus_Details_ID < 1) 
			set_Value (COLUMNNAME_FTU_Syllabus_Details_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Syllabus_Details_ID, Integer.valueOf(FTU_Syllabus_Details_ID));
	}

	/** Get Syllabus Details ID.
		@return Syllabus Details ID	  */
	public int getFTU_Syllabus_Details_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Syllabus_Details_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fuel.
		@param Fuel 
		Fuel listed on the Flightsheet
	  */
	public void setFuel (BigDecimal Fuel)
	{
		set_Value (COLUMNNAME_Fuel, Fuel);
	}

	/** Get Fuel.
		@return Fuel listed on the Flightsheet
	  */
	public BigDecimal getFuel () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Fuel);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_S_Resource getInst_Resource() throws RuntimeException
    {
		return (org.compiere.model.I_S_Resource)MTable.get(getCtx(), org.compiere.model.I_S_Resource.Table_Name)
			.getPO(getInst_Resource_ID(), get_TrxName());	}

	/** Set Instructor.
		@param Inst_Resource_ID Instructor	  */
	public void setInst_Resource_ID (int Inst_Resource_ID)
	{
		if (Inst_Resource_ID < 1) 
			set_Value (COLUMNNAME_Inst_Resource_ID, null);
		else 
			set_Value (COLUMNNAME_Inst_Resource_ID, Integer.valueOf(Inst_Resource_ID));
	}

	/** Get Instructor.
		@return Instructor	  */
	public int getInst_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Inst_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Lesson_Plan_Status AD_Reference_ID=1000036 */
	public static final int LESSON_PLAN_STATUS_AD_Reference_ID=1000036;
	/** Incomplete = Incomplete */
	public static final String LESSON_PLAN_STATUS_Incomplete = "Incomplete";
	/** Complete = Complete */
	public static final String LESSON_PLAN_STATUS_Complete = "Complete";
	/** Repeat = Repeat */
	public static final String LESSON_PLAN_STATUS_Repeat = "Repeat";
	/** Set Lesson Plan Status.
		@param Lesson_Plan_Status 
		The lesson plan status. 
	  */
	public void setLesson_Plan_Status (String Lesson_Plan_Status)
	{

		set_Value (COLUMNNAME_Lesson_Plan_Status, Lesson_Plan_Status);
	}

	/** Get Lesson Plan Status.
		@return The lesson plan status. 
	  */
	public String getLesson_Plan_Status () 
	{
		return (String)get_Value(COLUMNNAME_Lesson_Plan_Status);
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

	public org.compiere.model.I_M_Inventory getM_Inventory() throws RuntimeException
    {
		return (org.compiere.model.I_M_Inventory)MTable.get(getCtx(), org.compiere.model.I_M_Inventory.Table_Name)
			.getPO(getM_Inventory_ID(), get_TrxName());	}

	/** Set Phys.Inventory.
		@param M_Inventory_ID 
		Parameters for a Physical Inventory
	  */
	public void setM_Inventory_ID (int M_Inventory_ID)
	{
		if (M_Inventory_ID < 1) 
			set_Value (COLUMNNAME_M_Inventory_ID, null);
		else 
			set_Value (COLUMNNAME_M_Inventory_ID, Integer.valueOf(M_Inventory_ID));
	}

	/** Get Phys.Inventory.
		@return Parameters for a Physical Inventory
	  */
	public int getM_Inventory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Inventory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
	public void setSimulator (BigDecimal Simulator)
	{
		set_Value (COLUMNNAME_Simulator, Simulator);
	}

	/** Get Simulator.
		@return Simulator	  */
	public BigDecimal getSimulator () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Simulator);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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