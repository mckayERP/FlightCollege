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

/** Generated Interface for FTU_MaintWOResultLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_MaintWOResultLine 
{

    /** TableName=FTU_MaintWOResultLine */
    public static final String Table_Name = "FTU_MaintWOResultLine";

    /** AD_Table_ID=54229 */
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

    /** Column name CT_ComponentLifeAtAction */
    public static final String COLUMNNAME_CT_ComponentLifeAtAction = "CT_ComponentLifeAtAction";

	/** Set Component Life.
	  * The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public void setCT_ComponentLifeAtAction (BigDecimal CT_ComponentLifeAtAction);

	/** Get Component Life.
	  * The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public BigDecimal getCT_ComponentLifeAtAction();

    /** Column name CT_DataSet_ID */
    public static final String COLUMNNAME_CT_DataSet_ID = "CT_DataSet_ID";

	/** Set Data Set.
	  * A definition of a set of data.
	  */
	public void setCT_DataSet_ID (int CT_DataSet_ID);

	/** Get Data Set.
	  * A definition of a set of data.
	  */
	public int getCT_DataSet_ID();

	public com.mckayerp.model.I_CT_DataSet getCT_DataSet() throws RuntimeException;

    /** Column name CT_DataSetInstance_ID */
    public static final String COLUMNNAME_CT_DataSetInstance_ID = "CT_DataSetInstance_ID";

	/** Set Data Set Instance.
	  * A data point within a data set.
	  */
	public void setCT_DataSetInstance_ID (Object CT_DataSetInstance_ID);

	/** Get Data Set Instance.
	  * A data point within a data set.
	  */
	public Object getCT_DataSetInstance_ID();

    /** Column name CT_RootLifeAtAction */
    public static final String COLUMNNAME_CT_RootLifeAtAction = "CT_RootLifeAtAction";

	/** Set Root Component Life.
	  * The Life of the Root Component at the time of the action.
	  */
	public void setCT_RootLifeAtAction (BigDecimal CT_RootLifeAtAction);

	/** Get Root Component Life.
	  * The Life of the Root Component at the time of the action.
	  */
	public BigDecimal getCT_RootLifeAtAction();

    /** Column name DefectDate */
    public static final String COLUMNNAME_DefectDate = "DefectDate";

	/** Set Defect Date.
	  * The date and time the defect was entered in the log.
	  */
	public void setDefectDate (Timestamp DefectDate);

	/** Get Defect Date.
	  * The date and time the defect was entered in the log.
	  */
	public Timestamp getDefectDate();

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

    /** Column name FTU_DateCompleted */
    public static final String COLUMNNAME_FTU_DateCompleted = "FTU_DateCompleted";

	/** Set Date Completed.
	  * The date on which the action was completed. 
	  */
	public void setFTU_DateCompleted (Timestamp FTU_DateCompleted);

	/** Get Date Completed.
	  * The date on which the action was completed. 
	  */
	public Timestamp getFTU_DateCompleted();

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

    /** Column name FTU_DateToleranceApplied */
    public static final String COLUMNNAME_FTU_DateToleranceApplied = "FTU_DateToleranceApplied";

	/** Set Date Tolerance Applied.
	  * The amount of a date tolerance applied at the time of the maintenance action
	  */
	public void setFTU_DateToleranceApplied (BigDecimal FTU_DateToleranceApplied);

	/** Get Date Tolerance Applied.
	  * The amount of a date tolerance applied at the time of the maintenance action
	  */
	public BigDecimal getFTU_DateToleranceApplied();

    /** Column name FTU_DefectLog_ID */
    public static final String COLUMNNAME_FTU_DefectLog_ID = "FTU_DefectLog_ID";

	/** Set Defect	  */
	public void setFTU_DefectLog_ID (int FTU_DefectLog_ID);

	/** Get Defect	  */
	public int getFTU_DefectLog_ID();

	public com.mckayerp.ftu.model.I_FTU_DefectLog getFTU_DefectLog() throws RuntimeException;

    /** Column name FTU_MaintActionTaken */
    public static final String COLUMNNAME_FTU_MaintActionTaken = "FTU_MaintActionTaken";

	/** Set Action Taken.
	  * The maintenance action taken to satisfy the maintenance requirement.
	  */
	public void setFTU_MaintActionTaken (String FTU_MaintActionTaken);

	/** Get Action Taken.
	  * The maintenance action taken to satisfy the maintenance requirement.
	  */
	public String getFTU_MaintActionTaken();

    /** Column name FTU_MaintNextAction_ID */
    public static final String COLUMNNAME_FTU_MaintNextAction_ID = "FTU_MaintNextAction_ID";

	/** Set Next Maintenance Action	  */
	public void setFTU_MaintNextAction_ID (int FTU_MaintNextAction_ID);

	/** Get Next Maintenance Action	  */
	public int getFTU_MaintNextAction_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintNextAction getFTU_MaintNextAction() throws RuntimeException;

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

    /** Column name FTU_MaintRequirementLine_ID */
    public static final String COLUMNNAME_FTU_MaintRequirementLine_ID = "FTU_MaintRequirementLine_ID";

	/** Set Maintenance Requirement Line	  */
	public void setFTU_MaintRequirementLine_ID (int FTU_MaintRequirementLine_ID);

	/** Get Maintenance Requirement Line	  */
	public int getFTU_MaintRequirementLine_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintRequirementLine getFTU_MaintRequirementLine() throws RuntimeException;

    /** Column name FTU_MaintResultType */
    public static final String COLUMNNAME_FTU_MaintResultType = "FTU_MaintResultType";

	/** Set Resolution Type.
	  * The maintenance action can be completed or completed with a fault found.
	  */
	public void setFTU_MaintResultType (String FTU_MaintResultType);

	/** Get Resolution Type.
	  * The maintenance action can be completed or completed with a fault found.
	  */
	public String getFTU_MaintResultType();

    /** Column name FTU_MaintWOResult_ID */
    public static final String COLUMNNAME_FTU_MaintWOResult_ID = "FTU_MaintWOResult_ID";

	/** Set Maintenance Work Order Result	  */
	public void setFTU_MaintWOResult_ID (int FTU_MaintWOResult_ID);

	/** Get Maintenance Work Order Result	  */
	public int getFTU_MaintWOResult_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWOResult getFTU_MaintWOResult() throws RuntimeException;

    /** Column name FTU_MaintWOResultLine_ID */
    public static final String COLUMNNAME_FTU_MaintWOResultLine_ID = "FTU_MaintWOResultLine_ID";

	/** Set Maintenance Work Order Result Line	  */
	public void setFTU_MaintWOResultLine_ID (int FTU_MaintWOResultLine_ID);

	/** Get Maintenance Work Order Result Line	  */
	public int getFTU_MaintWOResultLine_ID();

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

    /** Column name IsMaintReqCompleted */
    public static final String COLUMNNAME_IsMaintReqCompleted = "IsMaintReqCompleted";

	/** Set Maint Requirement Completed.
	  * Is the maintenance requirement completed?
	  */
	public void setIsMaintReqCompleted (boolean IsMaintReqCompleted);

	/** Get Maint Requirement Completed.
	  * Is the maintenance requirement completed?
	  */
	public boolean isMaintReqCompleted();

    /** Column name LifeUsageUOM_ID */
    public static final String COLUMNNAME_LifeUsageUOM_ID = "LifeUsageUOM_ID";

	/** Set Life Use UOM.
	  * The unit of measure (UOM) for the life usage.  Example, hours, km.
	  */
	public void setLifeUsageUOM_ID (int LifeUsageUOM_ID);

	/** Get Life Use UOM.
	  * The unit of measure (UOM) for the life usage.  Example, hours, km.
	  */
	public int getLifeUsageUOM_ID();

	public org.compiere.model.I_C_UOM getLifeUsageUOM() throws RuntimeException;

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException;

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name OverhaulCount */
    public static final String COLUMNNAME_OverhaulCount = "OverhaulCount";

	/** Set Overhaul Count.
	  * The number of life cycles completed or underway.
	  */
	public void setOverhaulCount (int OverhaulCount);

	/** Get Overhaul Count.
	  * The number of life cycles completed or underway.
	  */
	public int getOverhaulCount();

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
