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

/** Generated Interface for FTU_ACJourneyLog
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public interface I_FTU_ACJourneyLog 
{

    /** TableName=FTU_ACJourneyLog */
    public static final String Table_Name = "FTU_ACJourneyLog";

    /** AD_Table_ID=1000035 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

    /** Column name EntryDate */
    public static final String COLUMNNAME_EntryDate = "EntryDate";

	/** Set Entry Date.
	  * The date and time of the last journey logbook entry.
	  */
	public void setEntryDate (Timestamp EntryDate);

	/** Get Entry Date.
	  * The date and time of the last journey logbook entry.
	  */
	public Timestamp getEntryDate();

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

    /** Column name FlightTime */
    public static final String COLUMNNAME_FlightTime = "FlightTime";

	/** Set Flight Time.
	  * The time intervale from the moment the aircraft first moves under its own power for the purposes of taking off until the moment it comes to rest.
	  */
	public void setFlightTime (BigDecimal FlightTime);

	/** Get Flight Time.
	  * The time intervale from the moment the aircraft first moves under its own power for the purposes of taking off until the moment it comes to rest.
	  */
	public BigDecimal getFlightTime();

    /** Column name FTU_ACJourneyLog_ID */
    public static final String COLUMNNAME_FTU_ACJourneyLog_ID = "FTU_ACJourneyLog_ID";

	/** Set Aircraft Journey Log	  */
	public void setFTU_ACJourneyLog_ID (int FTU_ACJourneyLog_ID);

	/** Get Aircraft Journey Log	  */
	public int getFTU_ACJourneyLog_ID();

    /** Column name FTU_Aircraft_ID */
    public static final String COLUMNNAME_FTU_Aircraft_ID = "FTU_Aircraft_ID";

	/** Set Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID);

	/** Get Aircraft	  */
	public int getFTU_Aircraft_ID();

	public com.mckayerp.ftu.model.I_FTU_Aircraft getFTU_Aircraft() throws RuntimeException;

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

    /** Column name NumberLegs */
    public static final String COLUMNNAME_NumberLegs = "NumberLegs";

	/** Set Number Legs	  */
	public void setNumberLegs (int NumberLegs);

	/** Get Number Legs	  */
	public int getNumberLegs();

    /** Column name NumOps */
    public static final String COLUMNNAME_NumOps = "NumOps";

	/** Set Number of Ops.
	  * The number of operations conducted during this flight entry.
	  */
	public void setNumOps (int NumOps);

	/** Get Number of Ops.
	  * The number of operations conducted during this flight entry.
	  */
	public int getNumOps();

    /** Column name Registration */
    public static final String COLUMNNAME_Registration = "Registration";

	/** Set AC Registration	  */
	public void setRegistration (String Registration);

	/** Get AC Registration	  */
	public String getRegistration();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name TotalAirframeTime */
    public static final String COLUMNNAME_TotalAirframeTime = "TotalAirframeTime";

	/** Set Total Airframe Time	  */
	public void setTotalAirframeTime (BigDecimal TotalAirframeTime);

	/** Get Total Airframe Time	  */
	public BigDecimal getTotalAirframeTime();

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
