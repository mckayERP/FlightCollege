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

/** Generated Interface for FTU_MaintNextAction
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_MaintNextAction 
{

    /** TableName=FTU_MaintNextAction */
    public static final String Table_Name = "FTU_MaintNextAction";

    /** AD_Table_ID=1000092 */
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

    /** Column name FTU_DateLastDone */
    public static final String COLUMNNAME_FTU_DateLastDone = "FTU_DateLastDone";

	/** Set Last Done (Date).
	  * The date when the maintenance requirement was last performed.
	  */
	public void setFTU_DateLastDone (Timestamp FTU_DateLastDone);

	/** Get Last Done (Date).
	  * The date when the maintenance requirement was last performed.
	  */
	public Timestamp getFTU_DateLastDone();

    /** Column name FTU_DateNextDue */
    public static final String COLUMNNAME_FTU_DateNextDue = "FTU_DateNextDue";

	/** Set Next Due (Date).
	  * The date when the maintenance requirement is next due. (Calculated)
	  */
	public void setFTU_DateNextDue (Timestamp FTU_DateNextDue);

	/** Get Next Due (Date).
	  * The date when the maintenance requirement is next due. (Calculated)
	  */
	public Timestamp getFTU_DateNextDue();

    /** Column name FTU_DateTolApplied */
    public static final String COLUMNNAME_FTU_DateTolApplied = "FTU_DateTolApplied";

	/** Set Date Tolerance Applied.
	  * The amount of date/time tolerance applied on the last maintenance action
	  */
	public void setFTU_DateTolApplied (BigDecimal FTU_DateTolApplied);

	/** Get Date Tolerance Applied.
	  * The amount of date/time tolerance applied on the last maintenance action
	  */
	public BigDecimal getFTU_DateTolApplied();

    /** Column name FTU_DaysRemaining */
    public static final String COLUMNNAME_FTU_DaysRemaining = "FTU_DaysRemaining";

	/** Set Days Remaining.
	  * The number of days remaining before the next maintenance action is due.
	  */
	public void setFTU_DaysRemaining (BigDecimal FTU_DaysRemaining);

	/** Get Days Remaining.
	  * The number of days remaining before the next maintenance action is due.
	  */
	public BigDecimal getFTU_DaysRemaining();

    /** Column name FTU_MaintNextAction_ID */
    public static final String COLUMNNAME_FTU_MaintNextAction_ID = "FTU_MaintNextAction_ID";

	/** Set Next Maintenance Action	  */
	public void setFTU_MaintNextAction_ID (int FTU_MaintNextAction_ID);

	/** Get Next Maintenance Action	  */
	public int getFTU_MaintNextAction_ID();

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

	public com.mckayerp.ftu.model.I_FTU_MaintRequirement getFTU_MaintRequirement() throws RuntimeException;

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

    /** Column name FTU_UsageLastDone */
    public static final String COLUMNNAME_FTU_UsageLastDone = "FTU_UsageLastDone";

	/** Set Last Done (Use).
	  * The usage or life of the component when the maintenance requirement was last performed.
	  */
	public void setFTU_UsageLastDone (BigDecimal FTU_UsageLastDone);

	/** Get Last Done (Use).
	  * The usage or life of the component when the maintenance requirement was last performed.
	  */
	public BigDecimal getFTU_UsageLastDone();

    /** Column name FTU_UsageNextDue */
    public static final String COLUMNNAME_FTU_UsageNextDue = "FTU_UsageNextDue";

	/** Set Next Due (Usage).
	  * The usage/life when the maintenance requirement is next due. (Calculated)
	  */
	public void setFTU_UsageNextDue (BigDecimal FTU_UsageNextDue);

	/** Get Next Due (Usage).
	  * The usage/life when the maintenance requirement is next due. (Calculated)
	  */
	public BigDecimal getFTU_UsageNextDue();

    /** Column name FTU_UsageTolApplied */
    public static final String COLUMNNAME_FTU_UsageTolApplied = "FTU_UsageTolApplied";

	/** Set Usage Tolerance Applied.
	  * The amount of usage/life tolerance applied on the last maintenance action
	  */
	public void setFTU_UsageTolApplied (BigDecimal FTU_UsageTolApplied);

	/** Get Usage Tolerance Applied.
	  * The amount of usage/life tolerance applied on the last maintenance action
	  */
	public BigDecimal getFTU_UsageTolApplied();

    /** Column name FTU_UseRemaining */
    public static final String COLUMNNAME_FTU_UseRemaining = "FTU_UseRemaining";

	/** Set Use Remaining.
	  * The amount of use/life remaining before the next maintenance action is due.
	  */
	public void setFTU_UseRemaining (BigDecimal FTU_UseRemaining);

	/** Get Use Remaining.
	  * The amount of use/life remaining before the next maintenance action is due.
	  */
	public BigDecimal getFTU_UseRemaining();

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

    /** Column name Root_Component_ID */
    public static final String COLUMNNAME_Root_Component_ID = "Root_Component_ID";

	/** Set Root Component.
	  * The Root Component of the component BOM tree.
	  */
	public void setRoot_Component_ID (int Root_Component_ID);

	/** Get Root Component.
	  * The Root Component of the component BOM tree.
	  */
	public int getRoot_Component_ID();

	public com.mckayerp.model.I_CT_Component getRoot_Component() throws RuntimeException;

    /** Column name RowStatus */
    public static final String COLUMNNAME_RowStatus = "RowStatus";

	/** Set Row Status.
	  * A code that indicates the status of a row.
	  */
	public void setRowStatus (BigDecimal RowStatus);

	/** Get Row Status.
	  * A code that indicates the status of a row.
	  */
	public BigDecimal getRowStatus();

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
