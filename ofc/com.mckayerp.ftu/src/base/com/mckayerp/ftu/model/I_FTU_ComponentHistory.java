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

/** Generated Interface for FTU_ComponentHistory
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_ComponentHistory 
{

    /** TableName=FTU_ComponentHistory */
    public static final String Table_Name = "FTU_ComponentHistory";

    /** AD_Table_ID=54198 */
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

    /** Column name ComponentLifeAtAction */
    public static final String COLUMNNAME_ComponentLifeAtAction = "ComponentLifeAtAction";

	/** Set Component Life at Action.
	  * The life usage of the component at the time of the action
	  */
	public void setComponentLifeAtAction (BigDecimal ComponentLifeAtAction);

	/** Get Component Life at Action.
	  * The life usage of the component at the time of the action
	  */
	public BigDecimal getComponentLifeAtAction();

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

    /** Column name DateAction */
    public static final String COLUMNNAME_DateAction = "DateAction";

	/** Set Action Date	  */
	public void setDateAction (Timestamp DateAction);

	/** Get Action Date	  */
	public Timestamp getDateAction();

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

    /** Column name FTU_ComponentHistory_ID */
    public static final String COLUMNNAME_FTU_ComponentHistory_ID = "FTU_ComponentHistory_ID";

	/** Set Component History ID	  */
	public void setFTU_ComponentHistory_ID (int FTU_ComponentHistory_ID);

	/** Get Component History ID	  */
	public int getFTU_ComponentHistory_ID();

    /** Column name FTU_MaintWOResultLine_ID */
    public static final String COLUMNNAME_FTU_MaintWOResultLine_ID = "FTU_MaintWOResultLine_ID";

	/** Set Maintenance Work Order Result Line	  */
	public void setFTU_MaintWOResultLine_ID (int FTU_MaintWOResultLine_ID);

	/** Get Maintenance Work Order Result Line	  */
	public int getFTU_MaintWOResultLine_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWOResultLine getFTU_MaintWOResultLine() throws RuntimeException;

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

    /** Column name M_InOutLine_ID */
    public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";

	/** Set Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID);

	/** Get Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID();

	public org.compiere.model.I_M_InOutLine getM_InOutLine() throws RuntimeException;

    /** Column name M_InventoryLine_ID */
    public static final String COLUMNNAME_M_InventoryLine_ID = "M_InventoryLine_ID";

	/** Set Phys.Inventory Line.
	  * Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID);

	/** Get Phys.Inventory Line.
	  * Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID();

	public org.compiere.model.I_M_InventoryLine getM_InventoryLine() throws RuntimeException;

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

    /** Column name M_MovementLine_ID */
    public static final String COLUMNNAME_M_MovementLine_ID = "M_MovementLine_ID";

	/** Set Move Line.
	  * Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID);

	/** Get Move Line.
	  * Inventory Move document Line
	  */
	public int getM_MovementLine_ID();

	public org.compiere.model.I_M_MovementLine getM_MovementLine() throws RuntimeException;

    /** Column name MovementType */
    public static final String COLUMNNAME_MovementType = "MovementType";

	/** Set Movement Type.
	  * Method of moving the inventory
	  */
	public void setMovementType (String MovementType);

	/** Get Movement Type.
	  * Method of moving the inventory
	  */
	public String getMovementType();

    /** Column name ParentComponent_ID */
    public static final String COLUMNNAME_ParentComponent_ID = "ParentComponent_ID";

	/** Set Parent Component.
	  * The component/assembly that the current component is installed in.
	  */
	public void setParentComponent_ID (int ParentComponent_ID);

	/** Get Parent Component.
	  * The component/assembly that the current component is installed in.
	  */
	public int getParentComponent_ID();

	public com.mckayerp.ftu.model.I_FTU_Component getParentComponent() throws RuntimeException;

    /** Column name ParentLifeAtAction */
    public static final String COLUMNNAME_ParentLifeAtAction = "ParentLifeAtAction";

	/** Set Parent Life at Action.
	  * The life usage measure of the parent at the time of the action.
	  */
	public void setParentLifeAtAction (BigDecimal ParentLifeAtAction);

	/** Get Parent Life at Action.
	  * The life usage measure of the parent at the time of the action.
	  */
	public BigDecimal getParentLifeAtAction();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

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
