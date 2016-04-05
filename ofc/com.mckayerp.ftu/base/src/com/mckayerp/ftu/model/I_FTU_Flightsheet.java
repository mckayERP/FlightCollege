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
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FTU_Flightsheet
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public interface I_FTU_Flightsheet 
{

    /** TableName=FTU_Flightsheet */
    public static final String Table_Name = "FTU_Flightsheet";

    /** AD_Table_ID=1000019 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AcknowledgedBy */
    public static final String COLUMNNAME_AcknowledgedBy = "AcknowledgedBy";

	/** Set Acknowledged By	  */
	public void setAcknowledgedBy (String AcknowledgedBy);

	/** Get Acknowledged By	  */
	public String getAcknowledgedBy();

    /** Column name AcknowledgedByID */
    public static final String COLUMNNAME_AcknowledgedByID = "AcknowledgedByID";

	/** Set Acknowledged By.
	  * The flight authorization was acknowledged by this business partner
	  */
	public void setAcknowledgedByID (int AcknowledgedByID);

	/** Get Acknowledged By.
	  * The flight authorization was acknowledged by this business partner
	  */
	public int getAcknowledgedByID();

	public org.compiere.model.I_C_BPartner getAcknowledgedB() throws RuntimeException;

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AirTime */
    public static final String COLUMNNAME_AirTime = "AirTime";

	/** Set Air Time.
	  * The time intervale measured in hours from the moment the aircraft leaves the ground to the moment it contacts the ground again.
	  */
	public void setAirTime (BigDecimal AirTime);

	/** Get Air Time.
	  * The time intervale measured in hours from the moment the aircraft leaves the ground to the moment it contacts the ground again.
	  */
	public BigDecimal getAirTime();

    /** Column name AuthorizedBy */
    public static final String COLUMNNAME_AuthorizedBy = "AuthorizedBy";

	/** Set Autorized By	  */
	public void setAuthorizedBy (String AuthorizedBy);

	/** Get Autorized By	  */
	public String getAuthorizedBy();

    /** Column name AuthorizedByID */
    public static final String COLUMNNAME_AuthorizedByID = "AuthorizedByID";

	/** Set Authorized By.
	  * The flight was authorized by this business partner
	  */
	public void setAuthorizedByID (int AuthorizedByID);

	/** Get Authorized By.
	  * The flight was authorized by this business partner
	  */
	public int getAuthorizedByID();

	public org.compiere.model.I_C_BPartner getAuthorizedB() throws RuntimeException;

    /** Column name Briefing */
    public static final String COLUMNNAME_Briefing = "Briefing";

	/** Set Briefing.
	  * Time spent in briefings
	  */
	public void setBriefing (BigDecimal Briefing);

	/** Get Briefing.
	  * Time spent in briefings
	  */
	public BigDecimal getBriefing();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException;

    /** Column name Captain_PIC */
    public static final String COLUMNNAME_Captain_PIC = "Captain_PIC";

	/** Set Captain or PIC.
	  * The Captain or Pilot in Command
	  */
	public void setCaptain_PIC (String Captain_PIC);

	/** Get Captain or PIC.
	  * The Captain or Pilot in Command
	  */
	public String getCaptain_PIC();

    /** Column name CaptainID */
    public static final String COLUMNNAME_CaptainID = "CaptainID";

	/** Set Captain.
	  * Captain or PIC for the flight.
	  */
	public void setCaptainID (int CaptainID);

	/** Get Captain.
	  * Captain or PIC for the flight.
	  */
	public int getCaptainID();

	public org.compiere.model.I_C_BPartner getCaptai() throws RuntimeException;

    /** Column name ContactPhone */
    public static final String COLUMNNAME_ContactPhone = "ContactPhone";

	/** Set Contact/Phone.
	  * The Contact/Phone number of a resonsible person for the flight.
	  */
	public void setContactPhone (String ContactPhone);

	/** Get Contact/Phone.
	  * The Contact/Phone number of a resonsible person for the flight.
	  */
	public String getContactPhone();

    /** Column name CourseType */
    public static final String COLUMNNAME_CourseType = "CourseType";

	/** Set Course Type	  */
	public void setCourseType (String CourseType);

	/** Get Course Type	  */
	public String getCourseType();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EngineStart */
    public static final String COLUMNNAME_EngineStart = "EngineStart";

	/** Set Engine Start.
	  * The start time of the engine.
	  */
	public void setEngineStart (Timestamp EngineStart);

	/** Get Engine Start.
	  * The start time of the engine.
	  */
	public Timestamp getEngineStart();

    /** Column name EngineStop */
    public static final String COLUMNNAME_EngineStop = "EngineStop";

	/** Set Engine Stop.
	  * The stop time of the engine.
	  */
	public void setEngineStop (Timestamp EngineStop);

	/** Get Engine Stop.
	  * The stop time of the engine.
	  */
	public Timestamp getEngineStop();

    /** Column name Exercises */
    public static final String COLUMNNAME_Exercises = "Exercises";

	/** Set Exercises.
	  * Flight training exercises performed
	  */
	public void setExercises (String Exercises);

	/** Get Exercises.
	  * Flight training exercises performed
	  */
	public String getExercises();

    /** Column name FlightDate */
    public static final String COLUMNNAME_FlightDate = "FlightDate";

	/** Set Flight Date.
	  * The date of the start of the flight.
	  */
	public void setFlightDate (Timestamp FlightDate);

	/** Get Flight Date.
	  * The date of the start of the flight.
	  */
	public Timestamp getFlightDate();

    /** Column name FlightID */
    public static final String COLUMNNAME_FlightID = "FlightID";

	/** Set Flight ID.
	  * The ID number of the Flightsheet
	  */
	public void setFlightID (int FlightID);

	/** Get Flight ID.
	  * The ID number of the Flightsheet
	  */
	public int getFlightID();

    /** Column name Flightsheet_ClientID */
    public static final String COLUMNNAME_Flightsheet_ClientID = "Flightsheet_ClientID";

	/** Set Client ID.
	  * The member number or ID from the Flightsheet
	  */
	public void setFlightsheet_ClientID (String Flightsheet_ClientID);

	/** Get Client ID.
	  * The member number or ID from the Flightsheet
	  */
	public String getFlightsheet_ClientID();

    /** Column name Flightsheet_InvoiceNo */
    public static final String COLUMNNAME_Flightsheet_InvoiceNo = "Flightsheet_InvoiceNo";

	/** Set Invoice Number	  */
	public void setFlightsheet_InvoiceNo (String Flightsheet_InvoiceNo);

	/** Get Invoice Number	  */
	public String getFlightsheet_InvoiceNo();

    /** Column name FlightTime_Charter */
    public static final String COLUMNNAME_FlightTime_Charter = "FlightTime_Charter";

	/** Set Charter Time.
	  * The time spent performing charter operations.
	  */
	public void setFlightTime_Charter (BigDecimal FlightTime_Charter);

	/** Get Charter Time.
	  * The time spent performing charter operations.
	  */
	public BigDecimal getFlightTime_Charter();

    /** Column name FlightTime_Dry */
    public static final String COLUMNNAME_FlightTime_Dry = "FlightTime_Dry";

	/** Set Dry Time.
	  * Flight time where the fuel was purchased by the pilot.
	  */
	public void setFlightTime_Dry (BigDecimal FlightTime_Dry);

	/** Get Dry Time.
	  * Flight time where the fuel was purchased by the pilot.
	  */
	public BigDecimal getFlightTime_Dry();

    /** Column name FlightTime_Dual */
    public static final String COLUMNNAME_FlightTime_Dual = "FlightTime_Dual";

	/** Set Dual Time.
	  * The time spent under instruction while training.
	  */
	public void setFlightTime_Dual (BigDecimal FlightTime_Dual);

	/** Get Dual Time.
	  * The time spent under instruction while training.
	  */
	public BigDecimal getFlightTime_Dual();

    /** Column name FlightTime_Intro */
    public static final String COLUMNNAME_FlightTime_Intro = "FlightTime_Intro";

	/** Set Intro Time.
	  * Flight time spent performing and Introductory Flight
	  */
	public void setFlightTime_Intro (BigDecimal FlightTime_Intro);

	/** Get Intro Time.
	  * Flight time spent performing and Introductory Flight
	  */
	public BigDecimal getFlightTime_Intro();

    /** Column name FlightTime_NonRev */
    public static final String COLUMNNAME_FlightTime_NonRev = "FlightTime_NonRev";

	/** Set NonRev Time.
	  * Flight time spent performing non-revenue generating work.
	  */
	public void setFlightTime_NonRev (BigDecimal FlightTime_NonRev);

	/** Get NonRev Time.
	  * Flight time spent performing non-revenue generating work.
	  */
	public BigDecimal getFlightTime_NonRev();

    /** Column name FlightTime_Rental */
    public static final String COLUMNNAME_FlightTime_Rental = "FlightTime_Rental";

	/** Set Rental Time.
	  * Flight time spent by rental of the aircraft.
	  */
	public void setFlightTime_Rental (BigDecimal FlightTime_Rental);

	/** Get Rental Time.
	  * Flight time spent by rental of the aircraft.
	  */
	public BigDecimal getFlightTime_Rental();

    /** Column name FlightTime_Solo */
    public static final String COLUMNNAME_FlightTime_Solo = "FlightTime_Solo";

	/** Set Solo Time.
	  * Flight time spent as a solo pilot. Includes flight tests.
	  */
	public void setFlightTime_Solo (BigDecimal FlightTime_Solo);

	/** Get Solo Time.
	  * Flight time spent as a solo pilot. Includes flight tests.
	  */
	public BigDecimal getFlightTime_Solo();

    /** Column name FTU_ACJourneyLog_ID */
    public static final String COLUMNNAME_FTU_ACJourneyLog_ID = "FTU_ACJourneyLog_ID";

	/** Set Aircraft Journey Log	  */
	public void setFTU_ACJourneyLog_ID (int FTU_ACJourneyLog_ID);

	/** Get Aircraft Journey Log	  */
	public int getFTU_ACJourneyLog_ID();

	public com.mckayerp.ftu.model.I_FTU_ACJourneyLog getFTU_ACJourneyLog() throws RuntimeException;

    /** Column name FTU_Aircraft_ID */
    public static final String COLUMNNAME_FTU_Aircraft_ID = "FTU_Aircraft_ID";

	/** Set Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID);

	/** Get Aircraft	  */
	public int getFTU_Aircraft_ID();

	public com.mckayerp.ftu.model.I_FTU_Aircraft getFTU_Aircraft() throws RuntimeException;

    /** Column name FTU_Flightsheet_ID */
    public static final String COLUMNNAME_FTU_Flightsheet_ID = "FTU_Flightsheet_ID";

	/** Set OFC_Flightsheet ID	  */
	public void setFTU_Flightsheet_ID (int FTU_Flightsheet_ID);

	/** Get OFC_Flightsheet ID	  */
	public int getFTU_Flightsheet_ID();

    /** Column name FTU_Instructor_ID */
    public static final String COLUMNNAME_FTU_Instructor_ID = "FTU_Instructor_ID";

	/** Set Flight Instructor	  */
	public void setFTU_Instructor_ID (int FTU_Instructor_ID);

	/** Get Flight Instructor	  */
	public int getFTU_Instructor_ID();

	public com.mckayerp.ftu.model.I_FTU_Instructor getFTU_Instructor() throws RuntimeException;

    /** Column name FTU_Syllabus_Details_ID */
    public static final String COLUMNNAME_FTU_Syllabus_Details_ID = "FTU_Syllabus_Details_ID";

	/** Set Syllabus Details ID	  */
	public void setFTU_Syllabus_Details_ID (int FTU_Syllabus_Details_ID);

	/** Get Syllabus Details ID	  */
	public int getFTU_Syllabus_Details_ID();

	public com.mckayerp.ftu.model.I_FTU_Syllabus_Details getFTU_Syllabus_Details() throws RuntimeException;

    /** Column name Fuel */
    public static final String COLUMNNAME_Fuel = "Fuel";

	/** Set Fuel.
	  * Fuel listed on the Flightsheet
	  */
	public void setFuel (BigDecimal Fuel);

	/** Get Fuel.
	  * Fuel listed on the Flightsheet
	  */
	public BigDecimal getFuel();

    /** Column name Inst_Resource_ID */
    public static final String COLUMNNAME_Inst_Resource_ID = "Inst_Resource_ID";

	/** Set Instructor	  */
	public void setInst_Resource_ID (int Inst_Resource_ID);

	/** Get Instructor	  */
	public int getInst_Resource_ID();

	public org.compiere.model.I_S_Resource getInst_Resource() throws RuntimeException;

    /** Column name IntendedFlight */
    public static final String COLUMNNAME_IntendedFlight = "IntendedFlight";

	/** Set Intended Flight	  */
	public void setIntendedFlight (String IntendedFlight);

	/** Get Intended Flight	  */
	public String getIntendedFlight();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsDNCO */
    public static final String COLUMNNAME_IsDNCO = "IsDNCO";

	/** Set DNCO.
	  * Did Not Complete Operation
	  */
	public void setIsDNCO (boolean IsDNCO);

	/** Get DNCO.
	  * Did Not Complete Operation
	  */
	public boolean isDNCO();

    /** Column name IsNoShow */
    public static final String COLUMNNAME_IsNoShow = "IsNoShow";

	/** Set No-Show.
	  * Is the flight course type one that should be charged a No-Show fee?
	  */
	public void setIsNoShow (boolean IsNoShow);

	/** Get No-Show.
	  * Is the flight course type one that should be charged a No-Show fee?
	  */
	public boolean isNoShow();

    /** Column name Lesson_Plan_Status */
    public static final String COLUMNNAME_Lesson_Plan_Status = "Lesson_Plan_Status";

	/** Set Lesson Plan Status.
	  * The lesson plan status. 
	  */
	public void setLesson_Plan_Status (String Lesson_Plan_Status);

	/** Get Lesson Plan Status.
	  * The lesson plan status. 
	  */
	public String getLesson_Plan_Status();

    /** Column name Line_Status */
    public static final String COLUMNNAME_Line_Status = "Line_Status";

	/** Set Status.
	  * The status of the flight sheet line item.
	  */
	public void setLine_Status (String Line_Status);

	/** Get Status.
	  * The status of the flight sheet line item.
	  */
	public String getLine_Status();

    /** Column name M_Inventory_ID */
    public static final String COLUMNNAME_M_Inventory_ID = "M_Inventory_ID";

	/** Set Phys.Inventory.
	  * Parameters for a Physical Inventory
	  */
	public void setM_Inventory_ID (int M_Inventory_ID);

	/** Get Phys.Inventory.
	  * Parameters for a Physical Inventory
	  */
	public int getM_Inventory_ID();

	public org.compiere.model.I_M_Inventory getM_Inventory() throws RuntimeException;

    /** Column name NumLegs */
    public static final String COLUMNNAME_NumLegs = "NumLegs";

	/** Set Num Legs	  */
	public void setNumLegs (int NumLegs);

	/** Get Num Legs	  */
	public int getNumLegs();

    /** Column name OtherPax */
    public static final String COLUMNNAME_OtherPax = "OtherPax";

	/** Set Other Pax.
	  * Other passengers on the flight
	  */
	public void setOtherPax (String OtherPax);

	/** Get Other Pax.
	  * Other passengers on the flight
	  */
	public String getOtherPax();

    /** Column name ReturingAt */
    public static final String COLUMNNAME_ReturingAt = "ReturingAt";

	/** Set Returning At	  */
	public void setReturingAt (Timestamp ReturingAt);

	/** Get Returning At	  */
	public Timestamp getReturingAt();

    /** Column name Simulator */
    public static final String COLUMNNAME_Simulator = "Simulator";

	/** Set Simulator	  */
	public void setSimulator (BigDecimal Simulator);

	/** Get Simulator	  */
	public BigDecimal getSimulator();

    /** Column name StudentID */
    public static final String COLUMNNAME_StudentID = "StudentID";

	/** Set Student.
	  * The student if the flight is an instructional flight.
	  */
	public void setStudentID (int StudentID);

	/** Get Student.
	  * The student if the flight is an instructional flight.
	  */
	public int getStudentID();

	public org.compiere.model.I_C_BPartner getStuden() throws RuntimeException;

    /** Column name StudentPAX */
    public static final String COLUMNNAME_StudentPAX = "StudentPAX";

	/** Set Student/PAX.
	  * The student of passengers on the flight
	  */
	public void setStudentPAX (String StudentPAX);

	/** Get Student/PAX.
	  * The student of passengers on the flight
	  */
	public String getStudentPAX();

    /** Column name TimesEnteredByID */
    public static final String COLUMNNAME_TimesEnteredByID = "TimesEnteredByID";

	/** Set Times Entered By.
	  * The times for this flight were entered by this business partner.
	  */
	public void setTimesEnteredByID (int TimesEnteredByID);

	/** Get Times Entered By.
	  * The times for this flight were entered by this business partner.
	  */
	public int getTimesEnteredByID();

	public org.compiere.model.I_C_BPartner getTimesEnteredB() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name WheelsDown */
    public static final String COLUMNNAME_WheelsDown = "WheelsDown";

	/** Set Wheels Down	  */
	public void setWheelsDown (Timestamp WheelsDown);

	/** Get Wheels Down	  */
	public Timestamp getWheelsDown();

    /** Column name WheelsUp */
    public static final String COLUMNNAME_WheelsUp = "WheelsUp";

	/** Set Wheels Up	  */
	public void setWheelsUp (Timestamp WheelsUp);

	/** Get Wheels Up	  */
	public Timestamp getWheelsUp();

    /** Column name XCDeparture */
    public static final String COLUMNNAME_XCDeparture = "XCDeparture";

	/** Set XCDeparture.
	  * Cross country departure location.
	  */
	public void setXCDeparture (String XCDeparture);

	/** Get XCDeparture.
	  * Cross country departure location.
	  */
	public String getXCDeparture();

    /** Column name XCDestination */
    public static final String COLUMNNAME_XCDestination = "XCDestination";

	/** Set XCDestination.
	  * Cross country destination
	  */
	public void setXCDestination (String XCDestination);

	/** Get XCDestination.
	  * Cross country destination
	  */
	public String getXCDestination();
}
