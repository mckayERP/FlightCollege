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

/** Generated Interface for CT_ComponentBOMLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_CT_ComponentBOMLine 
{

    /** TableName=CT_ComponentBOMLine */
    public static final String Table_Name = "CT_ComponentBOMLine";

    /** AD_Table_ID=54203 */
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

    /** Column name CompLifeAtInstall */
    public static final String COLUMNNAME_CompLifeAtInstall = "CompLifeAtInstall";

	/** Set Component life at install.
	  * The existing life usage of the component when it was installed.
	  */
	public void setCompLifeAtInstall (BigDecimal CompLifeAtInstall);

	/** Get Component life at install.
	  * The existing life usage of the component when it was installed.
	  */
	public BigDecimal getCompLifeAtInstall();

    /** Column name CompUsageSinceInstall */
    public static final String COLUMNNAME_CompUsageSinceInstall = "CompUsageSinceInstall";

	/** Set Component usage since install.
	  * The difference bewteen the current component life and its life usage at installation
	  */
	public void setCompUsageSinceInstall (BigDecimal CompUsageSinceInstall);

	/** Get Component usage since install.
	  * The difference bewteen the current component life and its life usage at installation
	  */
	public BigDecimal getCompUsageSinceInstall();

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

    /** Column name CT_ComponentBOM_ID */
    public static final String COLUMNNAME_CT_ComponentBOM_ID = "CT_ComponentBOM_ID";

	/** Set Component BOM.
	  * The list of subcomponents in this assembly
	  */
	public void setCT_ComponentBOM_ID (int CT_ComponentBOM_ID);

	/** Get Component BOM.
	  * The list of subcomponents in this assembly
	  */
	public int getCT_ComponentBOM_ID();

	public com.mckayerp.model.I_CT_ComponentBOM getCT_ComponentBOM() throws RuntimeException;

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

    /** Column name CurrentCompLife */
    public static final String COLUMNNAME_CurrentCompLife = "CurrentCompLife";

	/** Set Current Component Life.
	  * The current life usage of the component/assembly.
	  */
	public void setCurrentCompLife (BigDecimal CurrentCompLife);

	/** Get Current Component Life.
	  * The current life usage of the component/assembly.
	  */
	public BigDecimal getCurrentCompLife();

    /** Column name CurrentParentLife */
    public static final String COLUMNNAME_CurrentParentLife = "CurrentParentLife";

	/** Set Current Parent Life.
	  * The current life usage of the parent component/assembly.
	  */
	public void setCurrentParentLife (BigDecimal CurrentParentLife);

	/** Get Current Parent Life.
	  * The current life usage of the parent component/assembly.
	  */
	public BigDecimal getCurrentParentLife();

    /** Column name DateInstalled */
    public static final String COLUMNNAME_DateInstalled = "DateInstalled";

	/** Set Date Installed	  */
	public void setDateInstalled (Timestamp DateInstalled);

	/** Get Date Installed	  */
	public Timestamp getDateInstalled();

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

    /** Column name FTU_MaintWOResultLine_ID */
    public static final String COLUMNNAME_FTU_MaintWOResultLine_ID = "FTU_MaintWOResultLine_ID";

	/** Set Maintenance Work Order Result Line	  */
	public void setFTU_MaintWOResultLine_ID (int FTU_MaintWOResultLine_ID);

	/** Get Maintenance Work Order Result Line	  */
	public int getFTU_MaintWOResultLine_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWOResultLine getFTU_MaintWOResultLine() throws RuntimeException;

    /** Column name IntervalInstalled */
    public static final String COLUMNNAME_IntervalInstalled = "IntervalInstalled";

	/** Set Interval since install.
	  * The time interval since the component was installed in the assembly in days.
	  */
	public void setIntervalInstalled (int IntervalInstalled);

	/** Get Interval since install.
	  * The time interval since the component was installed in the assembly in days.
	  */
	public int getIntervalInstalled();

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

    /** Column name Master_Product_ID */
    public static final String COLUMNNAME_Master_Product_ID = "Master_Product_ID";

	/** Set Product Master.
	  * The product that appears on the Bill of Materials
	  */
	public void setMaster_Product_ID (int Master_Product_ID);

	/** Get Product Master.
	  * The product that appears on the Bill of Materials
	  */
	public int getMaster_Product_ID();

	public org.compiere.model.I_M_Product getMaster_Product() throws RuntimeException;

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

    /** Column name ParentLifeAtInstall */
    public static final String COLUMNNAME_ParentLifeAtInstall = "ParentLifeAtInstall";

	/** Set Parent Life At install.
	  * The life usage of the parent component at the time of installation
	  */
	public void setParentLifeAtInstall (BigDecimal ParentLifeAtInstall);

	/** Get Parent Life At install.
	  * The life usage of the parent component at the time of installation
	  */
	public BigDecimal getParentLifeAtInstall();

    /** Column name ParentUsageSinceInstall */
    public static final String COLUMNNAME_ParentUsageSinceInstall = "ParentUsageSinceInstall";

	/** Set Parent usage since install.
	  * The difference bewteen the current parent life and the parent life at install.
	  */
	public void setParentUsageSinceInstall (BigDecimal ParentUsageSinceInstall);

	/** Get Parent usage since install.
	  * The difference bewteen the current parent life and the parent life at install.
	  */
	public BigDecimal getParentUsageSinceInstall();

    /** Column name PP_Product_BOMLine_ID */
    public static final String COLUMNNAME_PP_Product_BOMLine_ID = "PP_Product_BOMLine_ID";

	/** Set BOM Line.
	  * BOM Line
	  */
	public void setPP_Product_BOMLine_ID (int PP_Product_BOMLine_ID);

	/** Get BOM Line.
	  * BOM Line
	  */
	public int getPP_Product_BOMLine_ID();

	public org.eevolution.model.I_PP_Product_BOMLine getPP_Product_BOMLine() throws RuntimeException;

    /** Column name QtyInstalled */
    public static final String COLUMNNAME_QtyInstalled = "QtyInstalled";

	/** Set Qty Installed.
	  * The quantity of a component currently installed
	  */
	public void setQtyInstalled (BigDecimal QtyInstalled);

	/** Get Qty Installed.
	  * The quantity of a component currently installed
	  */
	public BigDecimal getQtyInstalled();

    /** Column name QtyRequired */
    public static final String COLUMNNAME_QtyRequired = "QtyRequired";

	/** Set Qty Required.
	  * The quantity required to be installed.
	  */
	public void setQtyRequired (BigDecimal QtyRequired);

	/** Get Qty Required.
	  * The quantity required to be installed.
	  */
	public BigDecimal getQtyRequired();

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
