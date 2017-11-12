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

/** Generated Interface for FTU_ADApplication
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_ADApplication 
{

    /** TableName=FTU_ADApplication */
    public static final String Table_Name = "FTU_ADApplication";

    /** AD_Table_ID=54205 */
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

    /** Column name FTU_ADApplication_ID */
    public static final String COLUMNNAME_FTU_ADApplication_ID = "FTU_ADApplication_ID";

	/** Set AD Application ID	  */
	public void setFTU_ADApplication_ID (int FTU_ADApplication_ID);

	/** Get AD Application ID	  */
	public int getFTU_ADApplication_ID();

    /** Column name FTU_AirworthinessDirective_ID */
    public static final String COLUMNNAME_FTU_AirworthinessDirective_ID = "FTU_AirworthinessDirective_ID";

	/** Set Airworthiness Directive	  */
	public void setFTU_AirworthinessDirective_ID (int FTU_AirworthinessDirective_ID);

	/** Get Airworthiness Directive	  */
	public int getFTU_AirworthinessDirective_ID();

	public com.mckayerp.ftu.model.I_FTU_AirworthinessDirective getFTU_AirworthinessDirective() throws RuntimeException;

    /** Column name FTU_CAWIS_Applicability */
    public static final String COLUMNNAME_FTU_CAWIS_Applicability = "FTU_CAWIS_Applicability";

	/** Set Applicability.
	  * The Applicability of this Airworthiness Directive
	  */
	public void setFTU_CAWIS_Applicability (String FTU_CAWIS_Applicability);

	/** Get Applicability.
	  * The Applicability of this Airworthiness Directive
	  */
	public String getFTU_CAWIS_Applicability();

    /** Column name FTU_CAWIS_Manufacturer_ID */
    public static final String COLUMNNAME_FTU_CAWIS_Manufacturer_ID = "FTU_CAWIS_Manufacturer_ID";

	/** Set CAWIS Manufacturer.
	  * The CAWIS manufacturer
	  */
	public void setFTU_CAWIS_Manufacturer_ID (int FTU_CAWIS_Manufacturer_ID);

	/** Get CAWIS Manufacturer.
	  * The CAWIS manufacturer
	  */
	public int getFTU_CAWIS_Manufacturer_ID();

	public com.mckayerp.ftu.model.I_FTU_CAWIS_Manufacturer getFTU_CAWIS_Manufacturer() throws RuntimeException;

    /** Column name FTU_CAWIS_Model_ID */
    public static final String COLUMNNAME_FTU_CAWIS_Model_ID = "FTU_CAWIS_Model_ID";

	/** Set CAWIS Model.
	  * The CAWIS Model
	  */
	public void setFTU_CAWIS_Model_ID (int FTU_CAWIS_Model_ID);

	/** Get CAWIS Model.
	  * The CAWIS Model
	  */
	public int getFTU_CAWIS_Model_ID();

	public com.mckayerp.ftu.model.I_FTU_CAWIS_Model getFTU_CAWIS_Model() throws RuntimeException;

    /** Column name FTU_IsADApplies */
    public static final String COLUMNNAME_FTU_IsADApplies = "FTU_IsADApplies";

	/** Set AD Applies.
	  * Does the AD apply to this component?
	  */
	public void setFTU_IsADApplies (boolean FTU_IsADApplies);

	/** Get AD Applies.
	  * Does the AD apply to this component?
	  */
	public boolean isFTU_IsADApplies();

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
