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

/** Generated Interface for FTU_Component
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Component 
{

    /** TableName=FTU_Component */
    public static final String Table_Name = "FTU_Component";

    /** AD_Table_ID=54197 */
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

    /** Column name IsLifeExtended */
    public static final String COLUMNNAME_IsLifeExtended = "IsLifeExtended";

	/** Set Is Life Extended.
	  * Is the life of this component extended beyound the maximum life limit
	  */
	public void setIsLifeExtended (boolean IsLifeExtended);

	/** Get Is Life Extended.
	  * Is the life of this component extended beyound the maximum life limit
	  */
	public boolean isLifeExtended();

    /** Column name IsLifeExtensionPossible */
    public static final String COLUMNNAME_IsLifeExtensionPossible = "IsLifeExtensionPossible";

	/** Set Is Life Extension Possible.
	  * Can the life be extended beyond the maximum life?
	  */
	public void setIsLifeExtensionPossible (boolean IsLifeExtensionPossible);

	/** Get Is Life Extension Possible.
	  * Can the life be extended beyond the maximum life?
	  */
	public boolean isLifeExtensionPossible();

    /** Column name LifeUsageSource */
    public static final String COLUMNNAME_LifeUsageSource = "LifeUsageSource";

	/** Set Life Usage Source.
	  * How the life usage is determined for the component.
	  */
	public void setLifeUsageSource (String LifeUsageSource);

	/** Get Life Usage Source.
	  * How the life usage is determined for the component.
	  */
	public String getLifeUsageSource();

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

    /** Column name LifeUsed */
    public static final String COLUMNNAME_LifeUsed = "LifeUsed";

	/** Set Life Used.
	  * The life used for this component.
	  */
	public void setLifeUsed (BigDecimal LifeUsed);

	/** Get Life Used.
	  * The life used for this component.
	  */
	public BigDecimal getLifeUsed();

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

    /** Column name MaxLifeUsage */
    public static final String COLUMNNAME_MaxLifeUsage = "MaxLifeUsage";

	/** Set Max Life Usage.
	  * The maximum life use expected in the life usage units.
	  */
	public void setMaxLifeUsage (BigDecimal MaxLifeUsage);

	/** Get Max Life Usage.
	  * The maximum life use expected in the life usage units.
	  */
	public BigDecimal getMaxLifeUsage();

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
