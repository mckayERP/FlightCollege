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

/** Generated Interface for FTU_MaintWorkOrderLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_MaintWorkOrderLine 
{

    /** TableName=FTU_MaintWorkOrderLine */
    public static final String Table_Name = "FTU_MaintWorkOrderLine";

    /** AD_Table_ID=1000086 */
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

    /** Column name FTU_MaintWorkOrder_ID */
    public static final String COLUMNNAME_FTU_MaintWorkOrder_ID = "FTU_MaintWorkOrder_ID";

	/** Set Maintenance Work Order ID	  */
	public void setFTU_MaintWorkOrder_ID (int FTU_MaintWorkOrder_ID);

	/** Get Maintenance Work Order ID	  */
	public int getFTU_MaintWorkOrder_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWorkOrder getFTU_MaintWorkOrder() throws RuntimeException;

    /** Column name FTU_MaintWorkOrderLine_ID */
    public static final String COLUMNNAME_FTU_MaintWorkOrderLine_ID = "FTU_MaintWorkOrderLine_ID";

	/** Set Maintenance Work Order Line ID	  */
	public void setFTU_MaintWorkOrderLine_ID (int FTU_MaintWorkOrderLine_ID);

	/** Get Maintenance Work Order Line ID	  */
	public int getFTU_MaintWorkOrderLine_ID();

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
