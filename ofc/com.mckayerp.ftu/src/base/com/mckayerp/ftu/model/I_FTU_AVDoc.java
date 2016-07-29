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

/** Generated Interface for FTU_AVDoc
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_AVDoc 
{

    /** TableName=FTU_AVDoc */
    public static final String Table_Name = "FTU_AVDoc";

    /** AD_Table_ID=1000040 */
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

    /** Column name AVDOC_BookletNo */
    public static final String COLUMNNAME_AVDOC_BookletNo = "AVDOC_BookletNo";

	/** Set Booklet Number.
	  * Aviation Document Booklet Number
	  */
	public void setAVDOC_BookletNo (String AVDOC_BookletNo);

	/** Get Booklet Number.
	  * Aviation Document Booklet Number
	  */
	public String getAVDOC_BookletNo();

    /** Column name AVDOC_Date_Expiry */
    public static final String COLUMNNAME_AVDOC_Date_Expiry = "AVDOC_Date_Expiry";

	/** Set Date of Expiry	  */
	public void setAVDOC_Date_Expiry (Timestamp AVDOC_Date_Expiry);

	/** Get Date of Expiry	  */
	public Timestamp getAVDOC_Date_Expiry();

    /** Column name AVDOC_Date_Issued */
    public static final String COLUMNNAME_AVDOC_Date_Issued = "AVDOC_Date_Issued";

	/** Set Date Issued	  */
	public void setAVDOC_Date_Issued (Timestamp AVDOC_Date_Issued);

	/** Get Date Issued	  */
	public Timestamp getAVDOC_Date_Issued();

    /** Column name AVDOC_FileNo */
    public static final String COLUMNNAME_AVDOC_FileNo = "AVDOC_FileNo";

	/** Set File Number.
	  * Aviation Document File Number
	  */
	public void setAVDOC_FileNo (String AVDOC_FileNo);

	/** Get File Number.
	  * Aviation Document File Number
	  */
	public String getAVDOC_FileNo();

    /** Column name BirthDate */
    public static final String COLUMNNAME_BirthDate = "BirthDate";

	/** Set Date of Birth.
	  * Date of Birth
	  */
	public void setBirthDate (Timestamp BirthDate);

	/** Get Date of Birth.
	  * Date of Birth
	  */
	public Timestamp getBirthDate();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

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

    /** Column name FTU_AVDoc_ID */
    public static final String COLUMNNAME_FTU_AVDoc_ID = "FTU_AVDoc_ID";

	/** Set Aviation Document	  */
	public void setFTU_AVDoc_ID (int FTU_AVDoc_ID);

	/** Get Aviation Document	  */
	public int getFTU_AVDoc_ID();

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

    /** Column name License_Tab_ID */
    public static final String COLUMNNAME_License_Tab_ID = "License_Tab_ID";

	/** Set License Info Tab.
	  * License information
	  */
	public void setLicense_Tab_ID (int License_Tab_ID);

	/** Get License Info Tab.
	  * License information
	  */
	public int getLicense_Tab_ID();

	public org.compiere.model.I_AD_Tab getLicense_Tab() throws RuntimeException;

    /** Column name Medical_Tab_ID */
    public static final String COLUMNNAME_Medical_Tab_ID = "Medical_Tab_ID";

	/** Set Medical Info Tab.
	  * Medical Information
	  */
	public void setMedical_Tab_ID (int Medical_Tab_ID);

	/** Get Medical Info Tab.
	  * Medical Information
	  */
	public int getMedical_Tab_ID();

	public org.compiere.model.I_AD_Tab getMedical_Tab() throws RuntimeException;

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
