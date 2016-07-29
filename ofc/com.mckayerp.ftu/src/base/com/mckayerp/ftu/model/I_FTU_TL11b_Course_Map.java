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

/** Generated Interface for FTU_TL11b_Course_Map
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_TL11b_Course_Map 
{

    /** TableName=FTU_TL11b_Course_Map */
    public static final String Table_Name = "FTU_TL11b_Course_Map";

    /** AD_Table_ID=1000044 */
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

    /** Column name CRA_Course_Type */
    public static final String COLUMNNAME_CRA_Course_Type = "CRA_Course_Type";

	/** Set Course Type.
	  * The type of flight training course followed.
	  */
	public void setCRA_Course_Type (String CRA_Course_Type);

	/** Get Course Type.
	  * The type of flight training course followed.
	  */
	public String getCRA_Course_Type();

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

    /** Column name FlightCourseType */
    public static final String COLUMNNAME_FlightCourseType = "FlightCourseType";

	/** Set Course Type.
	  * The type of flight training course (flight type)
	  */
	public void setFlightCourseType (String FlightCourseType);

	/** Get Course Type.
	  * The type of flight training course (flight type)
	  */
	public String getFlightCourseType();

    /** Column name FTU_TL11b_Course_Map_ID */
    public static final String COLUMNNAME_FTU_TL11b_Course_Map_ID = "FTU_TL11b_Course_Map_ID";

	/** Set TL11B_Course_Map ID	  */
	public void setFTU_TL11b_Course_Map_ID (int FTU_TL11b_Course_Map_ID);

	/** Get TL11B_Course_Map ID	  */
	public int getFTU_TL11b_Course_Map_ID();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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

    /** Column name MaxDualTimeAllowed */
    public static final String COLUMNNAME_MaxDualTimeAllowed = "MaxDualTimeAllowed";

	/** Set Max Dual Time Allowed.
	  * The maximum dual time claimable by CRA & Transport Canada.
	  */
	public void setMaxDualTimeAllowed (BigDecimal MaxDualTimeAllowed);

	/** Get Max Dual Time Allowed.
	  * The maximum dual time claimable by CRA & Transport Canada.
	  */
	public BigDecimal getMaxDualTimeAllowed();

    /** Column name MaxSimTimeAllowed */
    public static final String COLUMNNAME_MaxSimTimeAllowed = "MaxSimTimeAllowed";

	/** Set Max Sim Time Allowed.
	  * The maximum simulator time claimable by CRA & Transport Canada.
	  */
	public void setMaxSimTimeAllowed (BigDecimal MaxSimTimeAllowed);

	/** Get Max Sim Time Allowed.
	  * The maximum simulator time claimable by CRA & Transport Canada.
	  */
	public BigDecimal getMaxSimTimeAllowed();

    /** Column name MaxSoloTimeAllowed */
    public static final String COLUMNNAME_MaxSoloTimeAllowed = "MaxSoloTimeAllowed";

	/** Set Max Solo Time Allowed.
	  * The maximum solo time claimable by CRA & Transport Canada.
	  */
	public void setMaxSoloTimeAllowed (BigDecimal MaxSoloTimeAllowed);

	/** Get Max Solo Time Allowed.
	  * The maximum solo time claimable by CRA & Transport Canada.
	  */
	public BigDecimal getMaxSoloTimeAllowed();

    /** Column name MaxTotalTimeAllowed */
    public static final String COLUMNNAME_MaxTotalTimeAllowed = "MaxTotalTimeAllowed";

	/** Set Max Total Time Allowed.
	  * The maximum total time (dual plus solo) claimable by CRA & Transport Canada.
	  */
	public void setMaxTotalTimeAllowed (BigDecimal MaxTotalTimeAllowed);

	/** Get Max Total Time Allowed.
	  * The maximum total time (dual plus solo) claimable by CRA & Transport Canada.
	  */
	public BigDecimal getMaxTotalTimeAllowed();

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
}
