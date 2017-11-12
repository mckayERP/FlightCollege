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

/** Generated Interface for FTU_ADCAR
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_ADCAR 
{

    /** TableName=FTU_ADCAR */
    public static final String Table_Name = "FTU_ADCAR";

    /** AD_Table_ID=54207 */
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

    /** Column name FTU_ADAction */
    public static final String COLUMNNAME_FTU_ADAction = "FTU_ADAction";

	/** Set Action.
	  * The action that must be taken to address the AD
	  */
	public void setFTU_ADAction (String FTU_ADAction);

	/** Get Action.
	  * The action that must be taken to address the AD
	  */
	public String getFTU_ADAction();

    /** Column name FTU_ADCAR_ID */
    public static final String COLUMNNAME_FTU_ADCAR_ID = "FTU_ADCAR_ID";

	/** Set Airworthiness Directive Corrective Action Requirements ID	  */
	public void setFTU_ADCAR_ID (int FTU_ADCAR_ID);

	/** Get Airworthiness Directive Corrective Action Requirements ID	  */
	public int getFTU_ADCAR_ID();

    /** Column name FTU_ADComplianceType */
    public static final String COLUMNNAME_FTU_ADComplianceType = "FTU_ADComplianceType";

	/** Set Compliance Type.
	  * The compliance type of the requirement, either once or repetitive.
	  */
	public void setFTU_ADComplianceType (String FTU_ADComplianceType);

	/** Get Compliance Type.
	  * The compliance type of the requirement, either once or repetitive.
	  */
	public String getFTU_ADComplianceType();

    /** Column name FTU_ADProcess */
    public static final String COLUMNNAME_FTU_ADProcess = "FTU_ADProcess";

	/** Set Process.
	  * A text description of the process to follow.
	  */
	public void setFTU_ADProcess (String FTU_ADProcess);

	/** Get Process.
	  * A text description of the process to follow.
	  */
	public String getFTU_ADProcess();

    /** Column name FTU_ADTimeInterval */
    public static final String COLUMNNAME_FTU_ADTimeInterval = "FTU_ADTimeInterval";

	/** Set Time Interval.
	  * The time interval limit for the action. Leave blank/null for no time limit.
	  */
	public void setFTU_ADTimeInterval (BigDecimal FTU_ADTimeInterval);

	/** Get Time Interval.
	  * The time interval limit for the action. Leave blank/null for no time limit.
	  */
	public BigDecimal getFTU_ADTimeInterval();

    /** Column name FTU_ADTimeIntervalUOM_ID */
    public static final String COLUMNNAME_FTU_ADTimeIntervalUOM_ID = "FTU_ADTimeIntervalUOM_ID";

	/** Set Time Interval UOM.
	  * The Unit of Measure (UOM) of the Time interval.  Typically days.
	  */
	public void setFTU_ADTimeIntervalUOM_ID (int FTU_ADTimeIntervalUOM_ID);

	/** Get Time Interval UOM.
	  * The Unit of Measure (UOM) of the Time interval.  Typically days.
	  */
	public int getFTU_ADTimeIntervalUOM_ID();

	public org.compiere.model.I_C_UOM getFTU_ADTimeIntervalUOM() throws RuntimeException;

    /** Column name FTU_ADUsageInterval */
    public static final String COLUMNNAME_FTU_ADUsageInterval = "FTU_ADUsageInterval";

	/** Set Usage Interval.
	  * The usage or Time in Service (TIS) interval limit for the action. Leave blank/null for no usage limit.
	  */
	public void setFTU_ADUsageInterval (BigDecimal FTU_ADUsageInterval);

	/** Get Usage Interval.
	  * The usage or Time in Service (TIS) interval limit for the action. Leave blank/null for no usage limit.
	  */
	public BigDecimal getFTU_ADUsageInterval();

    /** Column name FTU_ADUsageIntervalUOM_ID */
    public static final String COLUMNNAME_FTU_ADUsageIntervalUOM_ID = "FTU_ADUsageIntervalUOM_ID";

	/** Set Usage Interval UOM.
	  * The Unit of Measure (UOM) of the usage interval.  Typically hours.
	  */
	public void setFTU_ADUsageIntervalUOM_ID (int FTU_ADUsageIntervalUOM_ID);

	/** Get Usage Interval UOM.
	  * The Unit of Measure (UOM) of the usage interval.  Typically hours.
	  */
	public int getFTU_ADUsageIntervalUOM_ID();

	public org.compiere.model.I_C_UOM getFTU_ADUsageIntervalUOM() throws RuntimeException;

    /** Column name FTU_AirworthinessDirective_ID */
    public static final String COLUMNNAME_FTU_AirworthinessDirective_ID = "FTU_AirworthinessDirective_ID";

	/** Set Airworthiness Directive	  */
	public void setFTU_AirworthinessDirective_ID (int FTU_AirworthinessDirective_ID);

	/** Get Airworthiness Directive	  */
	public int getFTU_AirworthinessDirective_ID();

	public com.mckayerp.ftu.model.I_FTU_AirworthinessDirective getFTU_AirworthinessDirective() throws RuntimeException;

    /** Column name FTU_DeadlineDate */
    public static final String COLUMNNAME_FTU_DeadlineDate = "FTU_DeadlineDate";

	/** Set Deadline.
	  * The deadline date for compliance
	  */
	public void setFTU_DeadlineDate (Timestamp FTU_DeadlineDate);

	/** Get Deadline.
	  * The deadline date for compliance
	  */
	public Timestamp getFTU_DeadlineDate();

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
