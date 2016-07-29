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

/** Generated Interface for FTU_Pilot_Medical
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Pilot_Medical 
{

    /** TableName=FTU_Pilot_Medical */
    public static final String Table_Name = "FTU_Pilot_Medical";

    /** AD_Table_ID=1000041 */
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

    /** Column name FTU_AVDoc_ID */
    public static final String COLUMNNAME_FTU_AVDoc_ID = "FTU_AVDoc_ID";

	/** Set Aviation Document	  */
	public void setFTU_AVDoc_ID (int FTU_AVDoc_ID);

	/** Get Aviation Document	  */
	public int getFTU_AVDoc_ID();

	public com.mckayerp.ftu.model.I_FTU_AVDoc getFTU_AVDoc() throws RuntimeException;

    /** Column name FTU_Pilot_Medical_ID */
    public static final String COLUMNNAME_FTU_Pilot_Medical_ID = "FTU_Pilot_Medical_ID";

	/** Set Medical Certifcation ID	  */
	public void setFTU_Pilot_Medical_ID (int FTU_Pilot_Medical_ID);

	/** Get Medical Certifcation ID	  */
	public int getFTU_Pilot_Medical_ID();

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

    /** Column name IssueDate */
    public static final String COLUMNNAME_IssueDate = "IssueDate";

	/** Set Date of Issue.
	  * The date the document was issued.
	  */
	public void setIssueDate (Timestamp IssueDate);

	/** Get Date of Issue.
	  * The date the document was issued.
	  */
	public Timestamp getIssueDate();

    /** Column name LIC_Remarks */
    public static final String COLUMNNAME_LIC_Remarks = "LIC_Remarks";

	/** Set Remarks.
	  * Remarks on the Licence or Rating
	  */
	public void setLIC_Remarks (String LIC_Remarks);

	/** Get Remarks.
	  * Remarks on the Licence or Rating
	  */
	public String getLIC_Remarks();

    /** Column name MedCategory */
    public static final String COLUMNNAME_MedCategory = "MedCategory";

	/** Set Medical Category.
	  * Medical Category as defined by Transport Canada
	  */
	public void setMedCategory (String MedCategory);

	/** Get Medical Category.
	  * Medical Category as defined by Transport Canada
	  */
	public String getMedCategory();

    /** Column name MedicalExam_Date */
    public static final String COLUMNNAME_MedicalExam_Date = "MedicalExam_Date";

	/** Set Examination Date.
	  * The date of the medical exam
	  */
	public void setMedicalExam_Date (Timestamp MedicalExam_Date);

	/** Get Examination Date.
	  * The date of the medical exam
	  */
	public Timestamp getMedicalExam_Date();

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
