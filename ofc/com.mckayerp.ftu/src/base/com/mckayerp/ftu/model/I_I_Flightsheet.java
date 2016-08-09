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
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_Flightsheet
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_I_Flightsheet 
{

    /** TableName=I_Flightsheet */
    public static final String Table_Name = "I_Flightsheet";

    /** AD_Table_ID=1000018 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AcknowledgedByText */
    public static final String COLUMNNAME_AcknowledgedByText = "AcknowledgedByText";

	/** Set Acknowledged By	  */
	public void setAcknowledgedByText (String AcknowledgedByText);

	/** Get Acknowledged By	  */
	public String getAcknowledgedByText();

    /** Column name Activate */
    public static final String COLUMNNAME_Activate = "Activate";

	/** Set Activate.
	  * If this field contains the word "Activate", then the flight has not been activated yet.  Status should be waiting.
	  */
	public void setActivate (String Activate);

	/** Get Activate.
	  * If this field contains the word "Activate", then the flight has not been activated yet.  Status should be waiting.
	  */
	public String getActivate();

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

    /** Column name Authorize */
    public static final String COLUMNNAME_Authorize = "Authorize";

	/** Set Authorize.
	  * If this field contains the text "Authorize", the flight has been activated but not authorized.
	  */
	public void setAuthorize (String Authorize);

	/** Get Authorize.
	  * If this field contains the text "Authorize", the flight has been activated but not authorized.
	  */
	public String getAuthorize();

    /** Column name AuthorizedByText */
    public static final String COLUMNNAME_AuthorizedByText = "AuthorizedByText";

	/** Set Autorized By	  */
	public void setAuthorizedByText (String AuthorizedByText);

	/** Get Autorized By	  */
	public String getAuthorizedByText();

    /** Column name Briefing */
    public static final String COLUMNNAME_Briefing = "Briefing";

	/** Set Briefing.
	  * Time spent in briefings
	  */
	public void setBriefing (String Briefing);

	/** Get Briefing.
	  * Time spent in briefings
	  */
	public String getBriefing();

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
	public void setFlightID (BigDecimal FlightID);

	/** Get Flight ID.
	  * The ID number of the Flightsheet
	  */
	public BigDecimal getFlightID();

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
	public void setFlightTime_Charter (String FlightTime_Charter);

	/** Get Charter Time.
	  * The time spent performing charter operations.
	  */
	public String getFlightTime_Charter();

    /** Column name FlightTime_Dual */
    public static final String COLUMNNAME_FlightTime_Dual = "FlightTime_Dual";

	/** Set Dual Time.
	  * The time spent under instruction while training.
	  */
	public void setFlightTime_Dual (String FlightTime_Dual);

	/** Get Dual Time.
	  * The time spent under instruction while training.
	  */
	public String getFlightTime_Dual();

    /** Column name FlightTime_Intro */
    public static final String COLUMNNAME_FlightTime_Intro = "FlightTime_Intro";

	/** Set Intro Time.
	  * Flight time spent performing and Introductory Flight
	  */
	public void setFlightTime_Intro (String FlightTime_Intro);

	/** Get Intro Time.
	  * Flight time spent performing and Introductory Flight
	  */
	public String getFlightTime_Intro();

    /** Column name FlightTime_NonRev */
    public static final String COLUMNNAME_FlightTime_NonRev = "FlightTime_NonRev";

	/** Set NonRev Time.
	  * Flight time spent performing non-revenue generating work.
	  */
	public void setFlightTime_NonRev (String FlightTime_NonRev);

	/** Get NonRev Time.
	  * Flight time spent performing non-revenue generating work.
	  */
	public String getFlightTime_NonRev();

    /** Column name FlightTime_Rental */
    public static final String COLUMNNAME_FlightTime_Rental = "FlightTime_Rental";

	/** Set Rental Time.
	  * Flight time spent by rental of the aircraft.
	  */
	public void setFlightTime_Rental (String FlightTime_Rental);

	/** Get Rental Time.
	  * Flight time spent by rental of the aircraft.
	  */
	public String getFlightTime_Rental();

    /** Column name FlightTime_Solo */
    public static final String COLUMNNAME_FlightTime_Solo = "FlightTime_Solo";

	/** Set Solo Time.
	  * Flight time spent as a solo pilot. Includes flight tests.
	  */
	public void setFlightTime_Solo (String FlightTime_Solo);

	/** Get Solo Time.
	  * Flight time spent as a solo pilot. Includes flight tests.
	  */
	public String getFlightTime_Solo();

    /** Column name FTU_Aircraft_ID */
    public static final String COLUMNNAME_FTU_Aircraft_ID = "FTU_Aircraft_ID";

	/** Set Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID);

	/** Get Aircraft	  */
	public int getFTU_Aircraft_ID();

	public com.mckayerp.ftu.model.I_FTU_Aircraft getFTU_Aircraft() throws RuntimeException;

    /** Column name FTU_Instructor_ID */
    public static final String COLUMNNAME_FTU_Instructor_ID = "FTU_Instructor_ID";

	/** Set Flight Instructor	  */
	public void setFTU_Instructor_ID (int FTU_Instructor_ID);

	/** Get Flight Instructor	  */
	public int getFTU_Instructor_ID();

	public com.mckayerp.ftu.model.I_FTU_Instructor getFTU_Instructor() throws RuntimeException;

    /** Column name Fuel */
    public static final String COLUMNNAME_Fuel = "Fuel";

	/** Set Fuel.
	  * Fuel listed on the Flightsheet
	  */
	public void setFuel (int Fuel);

	/** Get Fuel.
	  * Fuel listed on the Flightsheet
	  */
	public int getFuel();

    /** Column name I_Acknowledged_By */
    public static final String COLUMNNAME_I_Acknowledged_By = "I_Acknowledged_By";

	/** Set Acknowledged By	  */
	public void setI_Acknowledged_By (String I_Acknowledged_By);

	/** Get Acknowledged By	  */
	public String getI_Acknowledged_By();

    /** Column name I_AirTime */
    public static final String COLUMNNAME_I_AirTime = "I_AirTime";

	/** Set Air Time	  */
	public void setI_AirTime (String I_AirTime);

	/** Get Air Time	  */
	public String getI_AirTime();

    /** Column name I_AuthorizedBy */
    public static final String COLUMNNAME_I_AuthorizedBy = "I_AuthorizedBy";

	/** Set Authorized By	  */
	public void setI_AuthorizedBy (String I_AuthorizedBy);

	/** Get Authorized By	  */
	public String getI_AuthorizedBy();

    /** Column name I_Briefing */
    public static final String COLUMNNAME_I_Briefing = "I_Briefing";

	/** Set Briefing	  */
	public void setI_Briefing (String I_Briefing);

	/** Get Briefing	  */
	public String getI_Briefing();

    /** Column name I_CallMarks */
    public static final String COLUMNNAME_I_CallMarks = "I_CallMarks";

	/** Set Call Marks	  */
	public void setI_CallMarks (String I_CallMarks);

	/** Get Call Marks	  */
	public String getI_CallMarks();

    /** Column name I_Captain_PIC */
    public static final String COLUMNNAME_I_Captain_PIC = "I_Captain_PIC";

	/** Set Captain or PIC	  */
	public void setI_Captain_PIC (String I_Captain_PIC);

	/** Get Captain or PIC	  */
	public String getI_Captain_PIC();

    /** Column name I_ClientID */
    public static final String COLUMNNAME_I_ClientID = "I_ClientID";

	/** Set Client ID.
	  * The Flighsheet Client ID
	  */
	public void setI_ClientID (String I_ClientID);

	/** Get Client ID.
	  * The Flighsheet Client ID
	  */
	public String getI_ClientID();

    /** Column name I_CourseType */
    public static final String COLUMNNAME_I_CourseType = "I_CourseType";

	/** Set Course Type.
	  * The flightsheet course type
	  */
	public void setI_CourseType (String I_CourseType);

	/** Get Course Type.
	  * The flightsheet course type
	  */
	public String getI_CourseType();

    /** Column name I_EngineStart */
    public static final String COLUMNNAME_I_EngineStart = "I_EngineStart";

	/** Set Engine Start.
	  * The time that engines were started.  Also the start of Flight Time.
	  */
	public void setI_EngineStart (String I_EngineStart);

	/** Get Engine Start.
	  * The time that engines were started.  Also the start of Flight Time.
	  */
	public String getI_EngineStart();

    /** Column name I_EngineStop */
    public static final String COLUMNNAME_I_EngineStop = "I_EngineStop";

	/** Set Engine Stop	  */
	public void setI_EngineStop (String I_EngineStop);

	/** Get Engine Stop	  */
	public String getI_EngineStop();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_FlightDay */
    public static final String COLUMNNAME_I_FlightDay = "I_FlightDay";

	/** Set Flight Day	  */
	public void setI_FlightDay (BigDecimal I_FlightDay);

	/** Get Flight Day	  */
	public BigDecimal getI_FlightDay();

    /** Column name I_FlightID */
    public static final String COLUMNNAME_I_FlightID = "I_FlightID";

	/** Set Flight ID.
	  * The Flightsheet ID number.
	  */
	public void setI_FlightID (BigDecimal I_FlightID);

	/** Get Flight ID.
	  * The Flightsheet ID number.
	  */
	public BigDecimal getI_FlightID();

    /** Column name I_FlightMonth */
    public static final String COLUMNNAME_I_FlightMonth = "I_FlightMonth";

	/** Set Flight Month	  */
	public void setI_FlightMonth (BigDecimal I_FlightMonth);

	/** Get Flight Month	  */
	public BigDecimal getI_FlightMonth();

    /** Column name I_Flightsheet_ID */
    public static final String COLUMNNAME_I_Flightsheet_ID = "I_Flightsheet_ID";

	/** Set I_Flightsheet ID	  */
	public void setI_Flightsheet_ID (int I_Flightsheet_ID);

	/** Get I_Flightsheet ID	  */
	public int getI_Flightsheet_ID();

    /** Column name I_FlightTime_Charter */
    public static final String COLUMNNAME_I_FlightTime_Charter = "I_FlightTime_Charter";

	/** Set Charter Time	  */
	public void setI_FlightTime_Charter (String I_FlightTime_Charter);

	/** Get Charter Time	  */
	public String getI_FlightTime_Charter();

    /** Column name I_FlightTime_Dual */
    public static final String COLUMNNAME_I_FlightTime_Dual = "I_FlightTime_Dual";

	/** Set Dual Time	  */
	public void setI_FlightTime_Dual (String I_FlightTime_Dual);

	/** Get Dual Time	  */
	public String getI_FlightTime_Dual();

    /** Column name I_FlightTime_Intro */
    public static final String COLUMNNAME_I_FlightTime_Intro = "I_FlightTime_Intro";

	/** Set Intro Time	  */
	public void setI_FlightTime_Intro (String I_FlightTime_Intro);

	/** Get Intro Time	  */
	public String getI_FlightTime_Intro();

    /** Column name I_FlightTime_Nonrev */
    public static final String COLUMNNAME_I_FlightTime_Nonrev = "I_FlightTime_Nonrev";

	/** Set Nonrev Time	  */
	public void setI_FlightTime_Nonrev (String I_FlightTime_Nonrev);

	/** Get Nonrev Time	  */
	public String getI_FlightTime_Nonrev();

    /** Column name I_FlightTime_Rental */
    public static final String COLUMNNAME_I_FlightTime_Rental = "I_FlightTime_Rental";

	/** Set Rental Time	  */
	public void setI_FlightTime_Rental (String I_FlightTime_Rental);

	/** Get Rental Time	  */
	public String getI_FlightTime_Rental();

    /** Column name I_FlightTime_Solo */
    public static final String COLUMNNAME_I_FlightTime_Solo = "I_FlightTime_Solo";

	/** Set Solo Time	  */
	public void setI_FlightTime_Solo (String I_FlightTime_Solo);

	/** Get Solo Time	  */
	public String getI_FlightTime_Solo();

    /** Column name I_FlightYear */
    public static final String COLUMNNAME_I_FlightYear = "I_FlightYear";

	/** Set Flight Year	  */
	public void setI_FlightYear (BigDecimal I_FlightYear);

	/** Get Flight Year	  */
	public BigDecimal getI_FlightYear();

    /** Column name I_Fuel */
    public static final String COLUMNNAME_I_Fuel = "I_Fuel";

	/** Set Fuel	  */
	public void setI_Fuel (String I_Fuel);

	/** Get Fuel	  */
	public String getI_Fuel();

    /** Column name I_IntendedFlight */
    public static final String COLUMNNAME_I_IntendedFlight = "I_IntendedFlight";

	/** Set Intended Flight	  */
	public void setI_IntendedFlight (String I_IntendedFlight);

	/** Get Intended Flight	  */
	public String getI_IntendedFlight();

    /** Column name I_InvoiceNumber */
    public static final String COLUMNNAME_I_InvoiceNumber = "I_InvoiceNumber";

	/** Set Invoice Number	  */
	public void setI_InvoiceNumber (String I_InvoiceNumber);

	/** Get Invoice Number	  */
	public String getI_InvoiceNumber();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name I_NumLegs */
    public static final String COLUMNNAME_I_NumLegs = "I_NumLegs";

	/** Set Number Legs	  */
	public void setI_NumLegs (int I_NumLegs);

	/** Get Number Legs	  */
	public int getI_NumLegs();

    /** Column name I_ReturningAt */
    public static final String COLUMNNAME_I_ReturningAt = "I_ReturningAt";

	/** Set Returning At	  */
	public void setI_ReturningAt (String I_ReturningAt);

	/** Get Returning At	  */
	public String getI_ReturningAt();

    /** Column name I_SimulatorTime */
    public static final String COLUMNNAME_I_SimulatorTime = "I_SimulatorTime";

	/** Set Simulator Time	  */
	public void setI_SimulatorTime (String I_SimulatorTime);

	/** Get Simulator Time	  */
	public String getI_SimulatorTime();

    /** Column name I_StudentPax */
    public static final String COLUMNNAME_I_StudentPax = "I_StudentPax";

	/** Set Student or Passenger	  */
	public void setI_StudentPax (String I_StudentPax);

	/** Get Student or Passenger	  */
	public String getI_StudentPax();

    /** Column name I_WheelsDown */
    public static final String COLUMNNAME_I_WheelsDown = "I_WheelsDown";

	/** Set Wheels Down	  */
	public void setI_WheelsDown (String I_WheelsDown);

	/** Get Wheels Down	  */
	public String getI_WheelsDown();

    /** Column name I_WheelsUp */
    public static final String COLUMNNAME_I_WheelsUp = "I_WheelsUp";

	/** Set Wheels Up	  */
	public void setI_WheelsUp (String I_WheelsUp);

	/** Get Wheels Up	  */
	public String getI_WheelsUp();

    /** Column name Import_Flightsheet_B */
    public static final String COLUMNNAME_Import_Flightsheet_B = "Import_Flightsheet_B";

	/** Set Import Flightsheet Button	  */
	public void setImport_Flightsheet_B (String Import_Flightsheet_B);

	/** Get Import Flightsheet Button	  */
	public String getImport_Flightsheet_B();

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

    /** Column name NumLegs */
    public static final String COLUMNNAME_NumLegs = "NumLegs";

	/** Set Num Legs	  */
	public void setNumLegs (int NumLegs);

	/** Get Num Legs	  */
	public int getNumLegs();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name ReturingAt */
    public static final String COLUMNNAME_ReturingAt = "ReturingAt";

	/** Set Returning At	  */
	public void setReturingAt (Timestamp ReturingAt);

	/** Get Returning At	  */
	public Timestamp getReturingAt();

    /** Column name Simulator */
    public static final String COLUMNNAME_Simulator = "Simulator";

	/** Set Simulator	  */
	public void setSimulator (String Simulator);

	/** Get Simulator	  */
	public String getSimulator();

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
}
