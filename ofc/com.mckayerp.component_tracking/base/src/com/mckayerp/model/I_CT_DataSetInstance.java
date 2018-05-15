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
package com.mckayerp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CT_DataSetInstance
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_CT_DataSetInstance 
{

    /** TableName=CT_DataSetInstance */
    public static final String Table_Name = "CT_DataSetInstance";

    /** AD_Table_ID=54391 */
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

	/** Set Data Set Instance ID	  */
	public void setCT_DataSetInstance_ID (int CT_DataSetInstance_ID);

	/** Get Data Set Instance ID	  */
	public int getCT_DataSetInstance_ID();

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

    /** Column name DateRecorded */
    public static final String COLUMNNAME_DateRecorded = "DateRecorded";

	/** Set Date Recorded	  */
	public void setDateRecorded (Timestamp DateRecorded);

	/** Get Date Recorded	  */
	public Timestamp getDateRecorded();

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

    /** Column name IsComplete */
    public static final String COLUMNNAME_IsComplete = "IsComplete";

	/** Set Complete.
	  * It is complete
	  */
	public void setIsComplete (boolean IsComplete);

	/** Get Complete.
	  * It is complete
	  */
	public boolean isComplete();

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
