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

/** Generated Interface for FTU_MaintRequirementLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_MaintRequirementLine 
{

    /** TableName=FTU_MaintRequirementLine */
    public static final String Table_Name = "FTU_MaintRequirementLine";

    /** AD_Table_ID=54228 */
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

    /** Column name CT_ComponentActionType */
    public static final String COLUMNNAME_CT_ComponentActionType = "CT_ComponentActionType";

	/** Set Action Type.
	  * The type of action performed on the component
	  */
	public void setCT_ComponentActionType (String CT_ComponentActionType);

	/** Get Action Type.
	  * The type of action performed on the component
	  */
	public String getCT_ComponentActionType();

    /** Column name CT_ComponentResolutionType */
    public static final String COLUMNNAME_CT_ComponentResolutionType = "CT_ComponentResolutionType";

	/** Set Resolution Type.
	  * The type of action performed on the component to resolve a maintenance requirement
	  */
	public void setCT_ComponentResolutionType (String CT_ComponentResolutionType);

	/** Get Resolution Type.
	  * The type of action performed on the component to resolve a maintenance requirement
	  */
	public String getCT_ComponentResolutionType();

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

    /** Column name FTU_ResolutionFFTemplate */
    public static final String COLUMNNAME_FTU_ResolutionFFTemplate = "FTU_ResolutionFFTemplate";

	/** Set Resolution Fault Found Template.
	  * A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action when a fault is found.
	  */
	public void setFTU_ResolutionFFTemplate (String FTU_ResolutionFFTemplate);

	/** Get Resolution Fault Found Template.
	  * A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action when a fault is found.
	  */
	public String getFTU_ResolutionFFTemplate();

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
