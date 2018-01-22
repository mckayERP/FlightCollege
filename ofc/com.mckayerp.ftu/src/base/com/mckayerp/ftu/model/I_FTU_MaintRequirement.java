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

/** Generated Interface for FTU_MaintRequirement
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_MaintRequirement 
{

    /** TableName=FTU_MaintRequirement */
    public static final String Table_Name = "FTU_MaintRequirement";

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

    /** Column name CT_Component_ID */
    public static final String COLUMNNAME_CT_Component_ID = "CT_Component_ID";

	/** Set Component.
	  * A component of an assembly or asset.
	  */
	public void setCT_Component_ID (int CT_Component_ID);

	/** Get Component.
	  * A component of an assembly or asset.
	  */
	public int getCT_Component_ID();

	public com.mckayerp.model.I_CT_Component getCT_Component() throws RuntimeException;

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

    /** Column name FTU_Action */
    public static final String COLUMNNAME_FTU_Action = "FTU_Action";

	/** Set Action.
	  * The action that must be taken to address the maintenance requirement
	  */
	public void setFTU_Action (String FTU_Action);

	/** Get Action.
	  * The action that must be taken to address the maintenance requirement
	  */
	public String getFTU_Action();

    /** Column name FTU_AirworthinessDirective_ID */
    public static final String COLUMNNAME_FTU_AirworthinessDirective_ID = "FTU_AirworthinessDirective_ID";

	/** Set Airworthiness Directive	  */
	public void setFTU_AirworthinessDirective_ID (int FTU_AirworthinessDirective_ID);

	/** Get Airworthiness Directive	  */
	public int getFTU_AirworthinessDirective_ID();

	public com.mckayerp.ftu.model.I_FTU_AirworthinessDirective getFTU_AirworthinessDirective() throws RuntimeException;

    /** Column name FTU_AppliesToProduct_ID */
    public static final String COLUMNNAME_FTU_AppliesToProduct_ID = "FTU_AppliesToProduct_ID";

	/** Set Applies to Product.
	  * The product to which the maintenance requirement or schedule applies.
	  */
	public void setFTU_AppliesToProduct_ID (int FTU_AppliesToProduct_ID);

	/** Get Applies to Product.
	  * The product to which the maintenance requirement or schedule applies.
	  */
	public int getFTU_AppliesToProduct_ID();

	public org.compiere.model.I_M_Product getFTU_AppliesToProduct() throws RuntimeException;

    /** Column name FTU_ComplianceType */
    public static final String COLUMNNAME_FTU_ComplianceType = "FTU_ComplianceType";

	/** Set Compliance Type.
	  * The compliance type of the requirement, either once or repetitive.
	  */
	public void setFTU_ComplianceType (String FTU_ComplianceType);

	/** Get Compliance Type.
	  * The compliance type of the requirement, either once or repetitive.
	  */
	public String getFTU_ComplianceType();

    /** Column name FTU_DateAfter */
    public static final String COLUMNNAME_FTU_DateAfter = "FTU_DateAfter";

	/** Set After Date.
	  * The date after which the interval applies or the starting date of the interval
	  */
	public void setFTU_DateAfter (Timestamp FTU_DateAfter);

	/** Get After Date.
	  * The date after which the interval applies or the starting date of the interval
	  */
	public Timestamp getFTU_DateAfter();

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

    /** Column name FTU_DefectLog_ID */
    public static final String COLUMNNAME_FTU_DefectLog_ID = "FTU_DefectLog_ID";

	/** Set Defect	  */
	public void setFTU_DefectLog_ID (int FTU_DefectLog_ID);

	/** Get Defect	  */
	public int getFTU_DefectLog_ID();

	public com.mckayerp.ftu.model.I_FTU_DefectLog getFTU_DefectLog() throws RuntimeException;

    /** Column name FTU_MaintRequirement_ID */
    public static final String COLUMNNAME_FTU_MaintRequirement_ID = "FTU_MaintRequirement_ID";

	/** Set Maintenance Requirement.
	  * A requirement to perform some maintenance action due to a snag, preventive maintenance or other corrective action.
	  */
	public void setFTU_MaintRequirement_ID (int FTU_MaintRequirement_ID);

	/** Get Maintenance Requirement.
	  * A requirement to perform some maintenance action due to a snag, preventive maintenance or other corrective action.
	  */
	public int getFTU_MaintRequirement_ID();

    /** Column name FTU_Process */
    public static final String COLUMNNAME_FTU_Process = "FTU_Process";

	/** Set Process.
	  * A text description of the process to follow.
	  */
	public void setFTU_Process (String FTU_Process);

	/** Get Process.
	  * A text description of the process to follow.
	  */
	public String getFTU_Process();

    /** Column name FTU_ResolutionTemplate */
    public static final String COLUMNNAME_FTU_ResolutionTemplate = "FTU_ResolutionTemplate";

	/** Set Resolution Template.
	  * A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action.
	  */
	public void setFTU_ResolutionTemplate (String FTU_ResolutionTemplate);

	/** Get Resolution Template.
	  * A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action.
	  */
	public String getFTU_ResolutionTemplate();

    /** Column name FTU_TimeInterval */
    public static final String COLUMNNAME_FTU_TimeInterval = "FTU_TimeInterval";

	/** Set Time Interval.
	  * The time interval limit for the action. Leave blank/null for no time limit. UOM is standard, typically days.  Set programmatically.
	  */
	public void setFTU_TimeInterval (BigDecimal FTU_TimeInterval);

	/** Get Time Interval.
	  * The time interval limit for the action. Leave blank/null for no time limit. UOM is standard, typically days.  Set programmatically.
	  */
	public BigDecimal getFTU_TimeInterval();

    /** Column name FTU_TimeInterval_Entered */
    public static final String COLUMNNAME_FTU_TimeInterval_Entered = "FTU_TimeInterval_Entered";

	/** Set Time Interval.
	  * The time interval limit for the action. Leave blank/null for no time limit. Select the associated Unit of Measure.
	  */
	public void setFTU_TimeInterval_Entered (BigDecimal FTU_TimeInterval_Entered);

	/** Get Time Interval.
	  * The time interval limit for the action. Leave blank/null for no time limit. Select the associated Unit of Measure.
	  */
	public BigDecimal getFTU_TimeInterval_Entered();

    /** Column name FTU_TimeIntervalTol */
    public static final String COLUMNNAME_FTU_TimeIntervalTol = "FTU_TimeIntervalTol";

	/** Set Time Interval Tol (+/-).
	  * The Tolerance in the Time Interval in the same units as the Interval
	  */
	public void setFTU_TimeIntervalTol (BigDecimal FTU_TimeIntervalTol);

	/** Get Time Interval Tol (+/-).
	  * The Tolerance in the Time Interval in the same units as the Interval
	  */
	public BigDecimal getFTU_TimeIntervalTol();

    /** Column name FTU_TimeIntervalTol_Entered */
    public static final String COLUMNNAME_FTU_TimeIntervalTol_Entered = "FTU_TimeIntervalTol_Entered";

	/** Set Time Interval Tol (+/-).
	  * The Tolerance in the Time Interval in the same units as the Interval
	  */
	public void setFTU_TimeIntervalTol_Entered (BigDecimal FTU_TimeIntervalTol_Entered);

	/** Get Time Interval Tol (+/-).
	  * The Tolerance in the Time Interval in the same units as the Interval
	  */
	public BigDecimal getFTU_TimeIntervalTol_Entered();

    /** Column name FTU_TimeIntervalUOM_ID */
    public static final String COLUMNNAME_FTU_TimeIntervalUOM_ID = "FTU_TimeIntervalUOM_ID";

	/** Set Time Interval UOM.
	  * The Unit of Measure (UOM) of the Time interval.  Typically days.
	  */
	public void setFTU_TimeIntervalUOM_ID (int FTU_TimeIntervalUOM_ID);

	/** Get Time Interval UOM.
	  * The Unit of Measure (UOM) of the Time interval.  Typically days.
	  */
	public int getFTU_TimeIntervalUOM_ID();

	public org.compiere.model.I_C_UOM getFTU_TimeIntervalUOM() throws RuntimeException;

    /** Column name FTU_TimeToleranceType */
    public static final String COLUMNNAME_FTU_TimeToleranceType = "FTU_TimeToleranceType";

	/** Set Time Tolerance Type.
	  * Determines how the next time/date based scheduled maintenance requirement is determined.
	  */
	public void setFTU_TimeToleranceType (String FTU_TimeToleranceType);

	/** Get Time Tolerance Type.
	  * Determines how the next time/date based scheduled maintenance requirement is determined.
	  */
	public String getFTU_TimeToleranceType();

    /** Column name FTU_UsageInterval */
    public static final String COLUMNNAME_FTU_UsageInterval = "FTU_UsageInterval";

	/** Set Usage Interval.
	  * The usage or Time in Service (TIS) interval limit for the action. Uses standard Unit of Measure - typically hours.
	  */
	public void setFTU_UsageInterval (BigDecimal FTU_UsageInterval);

	/** Get Usage Interval.
	  * The usage or Time in Service (TIS) interval limit for the action. Uses standard Unit of Measure - typically hours.
	  */
	public BigDecimal getFTU_UsageInterval();

    /** Column name FTU_UsageInterval_Entered */
    public static final String COLUMNNAME_FTU_UsageInterval_Entered = "FTU_UsageInterval_Entered";

	/** Set Usage Interval.
	  * The usage or Time in Service (TIS) interval limit for the action. Leave blank/null for no usage limit. Select the associated Unit of Measure.
	  */
	public void setFTU_UsageInterval_Entered (BigDecimal FTU_UsageInterval_Entered);

	/** Get Usage Interval.
	  * The usage or Time in Service (TIS) interval limit for the action. Leave blank/null for no usage limit. Select the associated Unit of Measure.
	  */
	public BigDecimal getFTU_UsageInterval_Entered();

    /** Column name FTU_UsageIntervalTol */
    public static final String COLUMNNAME_FTU_UsageIntervalTol = "FTU_UsageIntervalTol";

	/** Set Usage Interval Tol (+/-).
	  * The Tolerance in the Usage Interval in the same units as the Usage Interval
	  */
	public void setFTU_UsageIntervalTol (BigDecimal FTU_UsageIntervalTol);

	/** Get Usage Interval Tol (+/-).
	  * The Tolerance in the Usage Interval in the same units as the Usage Interval
	  */
	public BigDecimal getFTU_UsageIntervalTol();

    /** Column name FTU_UsageIntervalTol_Entered */
    public static final String COLUMNNAME_FTU_UsageIntervalTol_Entered = "FTU_UsageIntervalTol_Entered";

	/** Set Usage Interval Tol (+/-).
	  * The Tolerance in the Usage Interval in the same units as the Usage Interval
	  */
	public void setFTU_UsageIntervalTol_Entered (BigDecimal FTU_UsageIntervalTol_Entered);

	/** Get Usage Interval Tol (+/-).
	  * The Tolerance in the Usage Interval in the same units as the Usage Interval
	  */
	public BigDecimal getFTU_UsageIntervalTol_Entered();

    /** Column name FTU_UsageIntervalUOM_ID */
    public static final String COLUMNNAME_FTU_UsageIntervalUOM_ID = "FTU_UsageIntervalUOM_ID";

	/** Set Usage Interval UOM.
	  * The Unit of Measure (UOM) of the usage interval.  Typically hours.
	  */
	public void setFTU_UsageIntervalUOM_ID (int FTU_UsageIntervalUOM_ID);

	/** Get Usage Interval UOM.
	  * The Unit of Measure (UOM) of the usage interval.  Typically hours.
	  */
	public int getFTU_UsageIntervalUOM_ID();

	public org.compiere.model.I_C_UOM getFTU_UsageIntervalUOM() throws RuntimeException;

    /** Column name FTU_UsageToleranceType */
    public static final String COLUMNNAME_FTU_UsageToleranceType = "FTU_UsageToleranceType";

	/** Set Usage Tolerance Type.
	  * Determines how the next usage based scheduled maintenance requirement is determined.
	  */
	public void setFTU_UsageToleranceType (String FTU_UsageToleranceType);

	/** Get Usage Tolerance Type.
	  * Determines how the next usage based scheduled maintenance requirement is determined.
	  */
	public String getFTU_UsageToleranceType();

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

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
}
