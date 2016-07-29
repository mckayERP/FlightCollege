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

/** Generated Interface for FTU_Journey_Log
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Journey_Log 
{

    /** TableName=FTU_Journey_Log */
    public static final String Table_Name = "FTU_Journey_Log";

    /** AD_Table_ID=1000037 */
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
	public void setNumberLegs (BigDecimal NumberLegs);

	/** Get Number Legs	  */
	public BigDecimal getNumberLegs();

    /** Column name NumOps */
    public static final String COLUMNNAME_NumOps = "NumOps";

	/** Set Number of Ops.
	  * The number of operations conducted during this flight entry.
	  */
	public void setNumOps (String NumOps);

	/** Get Number of Ops.
	  * The number of operations conducted during this flight entry.
	  */
	public String getNumOps();

    /** Column name Registration */
    public static final String COLUMNNAME_Registration = "Registration";

	/** Set AC Registration	  */
	public void setRegistration (String Registration);

	/** Get AC Registration	  */
	public String getRegistration();

    /** Column name S_Resource_ID */
    public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

	/** Set Resource.
	  * Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID);

	/** Get Resource.
	  * Resource
	  */
	public int getS_Resource_ID();

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException;

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
