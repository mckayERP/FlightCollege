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

    /** Column name FTU_Component_ID */
    public static final String COLUMNNAME_FTU_Component_ID = "FTU_Component_ID";

	/** Set Component.
	  * A component of an assembly or asset.
	  */
	public void setFTU_Component_ID (int FTU_Component_ID);

	/** Get Component.
	  * A component of an assembly or asset.
	  */
	public int getFTU_Component_ID();

	public com.mckayerp.ftu.model.I_FTU_Component getFTU_Component() throws RuntimeException;

    /** Column name FTU_ComponentActionType */
    public static final String COLUMNNAME_FTU_ComponentActionType = "FTU_ComponentActionType";

	/** Set Action Type.
	  * The type of action performed on the component
	  */
	public void setFTU_ComponentActionType (String FTU_ComponentActionType);

	/** Get Action Type.
	  * The type of action performed on the component
	  */
	public String getFTU_ComponentActionType();

    /** Column name FTU_ComponentLifeAtAction */
    public static final String COLUMNNAME_FTU_ComponentLifeAtAction = "FTU_ComponentLifeAtAction";

	/** Set Component Life.
	  * The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public void setFTU_ComponentLifeAtAction (BigDecimal FTU_ComponentLifeAtAction);

	/** Get Component Life.
	  * The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public BigDecimal getFTU_ComponentLifeAtAction();

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

	/** Set Maintenance Requirement Line ID	  */
	public void setFTU_MaintRequirementLine_ID (int FTU_MaintRequirementLine_ID);

	/** Get Maintenance Requirement Line ID	  */
	public int getFTU_MaintRequirementLine_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintRequirementLine getFTU_MaintRequirementLine() throws RuntimeException;

    /** Column name FTU_MaintWOResult_ID */
    public static final String COLUMNNAME_FTU_MaintWOResult_ID = "FTU_MaintWOResult_ID";

	/** Set Maintenance Work Order Result ID	  */
	public void setFTU_MaintWOResult_ID (int FTU_MaintWOResult_ID);

	/** Get Maintenance Work Order Result ID	  */
	public int getFTU_MaintWOResult_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWOResult getFTU_MaintWOResult() throws RuntimeException;

    /** Column name FTU_MaintWOResultLine_ID */
    public static final String COLUMNNAME_FTU_MaintWOResultLine_ID = "FTU_MaintWOResultLine_ID";

	/** Set Maintenance Work Order Result Line	  */
	public void setFTU_MaintWOResultLine_ID (int FTU_MaintWOResultLine_ID);

	/** Get Maintenance Work Order Result Line	  */
	public int getFTU_MaintWOResultLine_ID();

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

	/** Set Completed.
	  * Is the maintenance requirement completed.
	  */
	public void setIsMaintReqCompleted (boolean IsMaintReqCompleted);

	/** Get Completed.
	  * Is the maintenance requirement completed.
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
