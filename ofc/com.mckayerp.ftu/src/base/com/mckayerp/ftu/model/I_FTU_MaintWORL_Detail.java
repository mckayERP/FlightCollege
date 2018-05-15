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

/** Generated Interface for FTU_MaintWORL_Detail
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_MaintWORL_Detail 
{

    /** TableName=FTU_MaintWORL_Detail */
    public static final String Table_Name = "FTU_MaintWORL_Detail";

    /** AD_Table_ID=1000094 */
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

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

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

    /** Column name CT_ComponentBOMLine_ID */
    public static final String COLUMNNAME_CT_ComponentBOMLine_ID = "CT_ComponentBOMLine_ID";

	/** Set Component BOM Line.
	  * A component included in an assembly
	  */
	public void setCT_ComponentBOMLine_ID (int CT_ComponentBOMLine_ID);

	/** Get Component BOM Line.
	  * A component included in an assembly
	  */
	public int getCT_ComponentBOMLine_ID();

	public com.mckayerp.model.I_CT_ComponentBOMLine getCT_ComponentBOMLine() throws RuntimeException;

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

    /** Column name DateAction */
    public static final String COLUMNNAME_DateAction = "DateAction";

	/** Set Action Date.
	  * The date the action took place
	  */
	public void setDateAction (Timestamp DateAction);

	/** Get Action Date.
	  * The date the action took place
	  */
	public Timestamp getDateAction();

    /** Column name FTU_MaintWOResultLine_ID */
    public static final String COLUMNNAME_FTU_MaintWOResultLine_ID = "FTU_MaintWOResultLine_ID";

	/** Set Maintenance Work Order Result Line	  */
	public void setFTU_MaintWOResultLine_ID (int FTU_MaintWOResultLine_ID);

	/** Get Maintenance Work Order Result Line	  */
	public int getFTU_MaintWOResultLine_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWOResultLine getFTU_MaintWOResultLine() throws RuntimeException;

    /** Column name FTU_MaintWORL_Detail_ID */
    public static final String COLUMNNAME_FTU_MaintWORL_Detail_ID = "FTU_MaintWORL_Detail_ID";

	/** Set Maintenance Work Order Result Line Detail ID	  */
	public void setFTU_MaintWORL_Detail_ID (int FTU_MaintWORL_Detail_ID);

	/** Get Maintenance Work Order Result Line Detail ID	  */
	public int getFTU_MaintWORL_Detail_ID();

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

    /** Column name IsResetComponentLife */
    public static final String COLUMNNAME_IsResetComponentLife = "IsResetComponentLife";

	/** Set Reset Component Life.
	  * Reset the Component Life used to zero and increment the life cycle count.
	  */
	public void setIsResetComponentLife (boolean IsResetComponentLife);

	/** Get Reset Component Life.
	  * Reset the Component Life used to zero and increment the life cycle count.
	  */
	public boolean isResetComponentLife();

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

	public com.mckayerp.model.I_CT_Component getParentComponent() throws RuntimeException;

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

    /** Column name Target_ComponentBOMLine_ID */
    public static final String COLUMNNAME_Target_ComponentBOMLine_ID = "Target_ComponentBOMLine_ID";

	/** Set Target BOM Line.
	  * The target component BOM line for the install operation.
	  */
	public void setTarget_ComponentBOMLine_ID (int Target_ComponentBOMLine_ID);

	/** Get Target BOM Line.
	  * The target component BOM line for the install operation.
	  */
	public int getTarget_ComponentBOMLine_ID();

	public com.mckayerp.model.I_CT_ComponentBOMLine getTarget_ComponentBOMLine() throws RuntimeException;

    /** Column name Target_ParentComponent_ID */
    public static final String COLUMNNAME_Target_ParentComponent_ID = "Target_ParentComponent_ID";

	/** Set Target Parent Component.
	  * The target parent component into which the component will be installed.
	  */
	public void setTarget_ParentComponent_ID (int Target_ParentComponent_ID);

	/** Get Target Parent Component.
	  * The target parent component into which the component will be installed.
	  */
	public int getTarget_ParentComponent_ID();

	public com.mckayerp.model.I_CT_Component getTarget_ParentComponent() throws RuntimeException;

    /** Column name Target_RootComponent_ID */
    public static final String COLUMNNAME_Target_RootComponent_ID = "Target_RootComponent_ID";

	/** Set Target Root Component.
	  * The target root component or assembly into which the component will be installed.
	  */
	public void setTarget_RootComponent_ID (int Target_RootComponent_ID);

	/** Get Target Root Component.
	  * The target root component or assembly into which the component will be installed.
	  */
	public int getTarget_RootComponent_ID();

	public com.mckayerp.model.I_CT_Component getTarget_RootComponent() throws RuntimeException;

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
