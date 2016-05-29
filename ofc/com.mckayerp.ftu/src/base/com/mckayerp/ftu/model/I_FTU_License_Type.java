/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FTU_License_Type
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_License_Type 
{

    /** TableName=FTU_License_Type */
    public static final String Table_Name = "FTU_License_Type";

    /** AD_Table_ID=1000020 */
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

    /** Column name FTU_License_Type_ID */
    public static final String COLUMNNAME_FTU_License_Type_ID = "FTU_License_Type_ID";

	/** Set Licenses and Permits ID	  */
	public void setFTU_License_Type_ID (int FTU_License_Type_ID);

	/** Get Licenses and Permits ID	  */
	public int getFTU_License_Type_ID();

    /** Column name FTU_Medical_Cat_ID */
    public static final String COLUMNNAME_FTU_Medical_Cat_ID = "FTU_Medical_Cat_ID";

	/** Set Medical Category ID	  */
	public void setFTU_Medical_Cat_ID (int FTU_Medical_Cat_ID);

	/** Get Medical Category ID	  */
	public int getFTU_Medical_Cat_ID();

	public com.mckayerp.ftu.model.I_FTU_Medical_Cat getFTU_Medical_Cat() throws RuntimeException;

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

    /** Column name LicenseType */
    public static final String COLUMNNAME_LicenseType = "LicenseType";

	/** Set License Type.
	  * The license, permit or rating type
	  */
	public void setLicenseType (String LicenseType);

	/** Get License Type.
	  * The license, permit or rating type
	  */
	public String getLicenseType();

    /** Column name OMedCatValidityBreakAge */
    public static final String COLUMNNAME_OMedCatValidityBreakAge = "OMedCatValidityBreakAge";

	/** Set Validity Break Age.
	  * The age at which the validity period changes - typically 40 years.
	  */
	public void setOMedCatValidityBreakAge (int OMedCatValidityBreakAge);

	/** Get Validity Break Age.
	  * The age at which the validity period changes - typically 40 years.
	  */
	public int getOMedCatValidityBreakAge();

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

    /** Column name ValidityAboveBreak */
    public static final String COLUMNNAME_ValidityAboveBreak = "ValidityAboveBreak";

	/** Set Validity Period Above Break Age.
	  * The length of time, in months, the medical certificate is valid based on the age of the pilot and medical category.
	  */
	public void setValidityAboveBreak (int ValidityAboveBreak);

	/** Get Validity Period Above Break Age.
	  * The length of time, in months, the medical certificate is valid based on the age of the pilot and medical category.
	  */
	public int getValidityAboveBreak();

    /** Column name ValidityBelowBreak */
    public static final String COLUMNNAME_ValidityBelowBreak = "ValidityBelowBreak";

	/** Set Validity Period Below Break Age.
	  * The length of time, in months, the medical certificate is valid based on the age of the pilot and medical category.
	  */
	public void setValidityBelowBreak (int ValidityBelowBreak);

	/** Get Validity Period Below Break Age.
	  * The length of time, in months, the medical certificate is valid based on the age of the pilot and medical category.
	  */
	public int getValidityBelowBreak();
}
