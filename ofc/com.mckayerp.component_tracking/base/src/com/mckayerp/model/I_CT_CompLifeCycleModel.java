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

/** Generated Interface for CT_CompLifeCycleModel
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_CT_CompLifeCycleModel 
{

    /** TableName=CT_CompLifeCycleModel */
    public static final String Table_Name = "CT_CompLifeCycleModel";

    /** AD_Table_ID=54204 */
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

    /** Column name CT_CompLifeCycleModel_ID */
    public static final String COLUMNNAME_CT_CompLifeCycleModel_ID = "CT_CompLifeCycleModel_ID";

	/** Set Component Life Cycle Model.
	  * The component life cycle model to use when creating new components for this product.
	  */
	public void setCT_CompLifeCycleModel_ID (int CT_CompLifeCycleModel_ID);

	/** Get Component Life Cycle Model.
	  * The component life cycle model to use when creating new components for this product.
	  */
	public int getCT_CompLifeCycleModel_ID();

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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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

    /** Column name M_Product_Group_ID */
    public static final String COLUMNNAME_M_Product_Group_ID = "M_Product_Group_ID";

	/** Set Product Group.
	  * Group of a Product
	  */
	public void setM_Product_Group_ID (int M_Product_Group_ID);

	/** Get Product Group.
	  * Group of a Product
	  */
	public int getM_Product_Group_ID();

	public org.compiere.model.I_M_Product_Group getM_Product_Group() throws RuntimeException;

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
