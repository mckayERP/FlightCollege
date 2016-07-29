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

/** Generated Interface for FTU_Pilot_Quals
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Pilot_Quals 
{

    /** TableName=FTU_Pilot_Quals */
    public static final String Table_Name = "FTU_Pilot_Quals";

    /** AD_Table_ID=1000025 */
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

    /** Column name FTU_AVDoc_ID */
    public static final String COLUMNNAME_FTU_AVDoc_ID = "FTU_AVDoc_ID";

	/** Set Aviation Document	  */
	public void setFTU_AVDoc_ID (int FTU_AVDoc_ID);

	/** Get Aviation Document	  */
	public int getFTU_AVDoc_ID();

	public com.mckayerp.ftu.model.I_FTU_AVDoc getFTU_AVDoc() throws RuntimeException;

    /** Column name FTU_License_Type_ID */
    public static final String COLUMNNAME_FTU_License_Type_ID = "FTU_License_Type_ID";

	/** Set Licenses and Permits ID	  */
	public void setFTU_License_Type_ID (int FTU_License_Type_ID);

	/** Get Licenses and Permits ID	  */
	public int getFTU_License_Type_ID();

	public com.mckayerp.ftu.model.I_FTU_License_Type getFTU_License_Type() throws RuntimeException;

    /** Column name FTU_Pilot_Quals_ID */
    public static final String COLUMNNAME_FTU_Pilot_Quals_ID = "FTU_Pilot_Quals_ID";

	/** Set Pilot Qualifications ID	  */
	public void setFTU_Pilot_Quals_ID (int FTU_Pilot_Quals_ID);

	/** Get Pilot Qualifications ID	  */
	public int getFTU_Pilot_Quals_ID();

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

    /** Column name LIC_ABI_Class */
    public static final String COLUMNNAME_LIC_ABI_Class = "LIC_ABI_Class";

	/** Set ABI Class.
	  * The Class of aerobatic Instructor
	  */
	public void setLIC_ABI_Class (String LIC_ABI_Class);

	/** Get ABI Class.
	  * The Class of aerobatic Instructor
	  */
	public String getLIC_ABI_Class();

    /** Column name LIC_ABI_ExpDate */
    public static final String COLUMNNAME_LIC_ABI_ExpDate = "LIC_ABI_ExpDate";

	/** Set ABI Expiry Date.
	  * The date the rating expires
	  */
	public void setLIC_ABI_ExpDate (Timestamp LIC_ABI_ExpDate);

	/** Get ABI Expiry Date.
	  * The date the rating expires
	  */
	public Timestamp getLIC_ABI_ExpDate();

    /** Column name LIC_FI_Class */
    public static final String COLUMNNAME_LIC_FI_Class = "LIC_FI_Class";

	/** Set FI Class.
	  * The Class of Flight Instructor Rating
	  */
	public void setLIC_FI_Class (String LIC_FI_Class);

	/** Get FI Class.
	  * The Class of Flight Instructor Rating
	  */
	public String getLIC_FI_Class();

    /** Column name LIC_FI_ExpDate */
    public static final String COLUMNNAME_LIC_FI_ExpDate = "LIC_FI_ExpDate";

	/** Set FI Expiry Date.
	  * The expiry date of the instructor rating.
	  */
	public void setLIC_FI_ExpDate (Timestamp LIC_FI_ExpDate);

	/** Get FI Expiry Date.
	  * The expiry date of the instructor rating.
	  */
	public Timestamp getLIC_FI_ExpDate();

    /** Column name LIC_IR_ExpDate */
    public static final String COLUMNNAME_LIC_IR_ExpDate = "LIC_IR_ExpDate";

	/** Set IR Expiry Date	  */
	public void setLIC_IR_ExpDate (Timestamp LIC_IR_ExpDate);

	/** Get IR Expiry Date	  */
	public Timestamp getLIC_IR_ExpDate();

    /** Column name LIC_IR_Group */
    public static final String COLUMNNAME_LIC_IR_Group = "LIC_IR_Group";

	/** Set IR Group.
	  * The group of the Instrument Rating
	  */
	public void setLIC_IR_Group (String LIC_IR_Group);

	/** Get IR Group.
	  * The group of the Instrument Rating
	  */
	public String getLIC_IR_Group();

    /** Column name LIC_IsABI */
    public static final String COLUMNNAME_LIC_IsABI = "LIC_IsABI";

	/** Set Aerobatic Instructor.
	  * Is the flight rating for an Aerobatic Instructor?
	  */
	public void setLIC_IsABI (boolean LIC_IsABI);

	/** Get Aerobatic Instructor.
	  * Is the flight rating for an Aerobatic Instructor?
	  */
	public boolean isLIC_IsABI();

    /** Column name LIC_IsCenterlineEng */
    public static final String COLUMNNAME_LIC_IsCenterlineEng = "LIC_IsCenterlineEng";

	/** Set C.
	  * Does the class rating cover centerline-engine aircraft?
	  */
	public void setLIC_IsCenterlineEng (boolean LIC_IsCenterlineEng);

	/** Get C.
	  * Does the class rating cover centerline-engine aircraft?
	  */
	public boolean isLIC_IsCenterlineEng();

    /** Column name LIC_IsFI */
    public static final String COLUMNNAME_LIC_IsFI = "LIC_IsFI";

	/** Set FI.
	  * Flight Instructor Rating
	  */
	public void setLIC_IsFI (boolean LIC_IsFI);

	/** Get FI.
	  * Flight Instructor Rating
	  */
	public boolean isLIC_IsFI();

    /** Column name LIC_IsIR */
    public static final String COLUMNNAME_LIC_IsIR = "LIC_IsIR";

	/** Set Instrument Rating.
	  * Does the licence include an Instrument Rating?
	  */
	public void setLIC_IsIR (boolean LIC_IsIR);

	/** Get Instrument Rating.
	  * Does the licence include an Instrument Rating?
	  */
	public boolean isLIC_IsIR();

    /** Column name LIC_IsLand */
    public static final String COLUMNNAME_LIC_IsLand = "LIC_IsLand";

	/** Set Land.
	  * Does the class rating cover Land aircraft.
	  */
	public void setLIC_IsLand (boolean LIC_IsLand);

	/** Get Land.
	  * Does the class rating cover Land aircraft.
	  */
	public boolean isLIC_IsLand();

    /** Column name LIC_IsMultiEng */
    public static final String COLUMNNAME_LIC_IsMultiEng = "LIC_IsMultiEng";

	/** Set M.
	  * Does the class rating cover multi-engine aircraft?
	  */
	public void setLIC_IsMultiEng (boolean LIC_IsMultiEng);

	/** Get M.
	  * Does the class rating cover multi-engine aircraft?
	  */
	public boolean isLIC_IsMultiEng();

    /** Column name LIC_IsNight */
    public static final String COLUMNNAME_LIC_IsNight = "LIC_IsNight";

	/** Set Night Rating.
	  * Does the Licence allow flights at night?
	  */
	public void setLIC_IsNight (boolean LIC_IsNight);

	/** Get Night Rating.
	  * Does the Licence allow flights at night?
	  */
	public boolean isLIC_IsNight();

    /** Column name LIC_IsPax */
    public static final String COLUMNNAME_LIC_IsPax = "LIC_IsPax";

	/** Set Passenger Rating?.
	  * Does the licence allow for carrying Passengers?
	  */
	public void setLIC_IsPax (boolean LIC_IsPax);

	/** Get Passenger Rating?.
	  * Does the licence allow for carrying Passengers?
	  */
	public boolean isLIC_IsPax();

    /** Column name LIC_IsSea */
    public static final String COLUMNNAME_LIC_IsSea = "LIC_IsSea";

	/** Set Sea.
	  * Does the Class Rating cover seaplanes
	  */
	public void setLIC_IsSea (boolean LIC_IsSea);

	/** Get Sea.
	  * Does the Class Rating cover seaplanes
	  */
	public boolean isLIC_IsSea();

    /** Column name LIC_IsSingleEng */
    public static final String COLUMNNAME_LIC_IsSingleEng = "LIC_IsSingleEng";

	/** Set S.
	  * Is the class rating cover single engine aircraft.
	  */
	public void setLIC_IsSingleEng (boolean LIC_IsSingleEng);

	/** Get S.
	  * Is the class rating cover single engine aircraft.
	  */
	public boolean isLIC_IsSingleEng();

    /** Column name LIC_IssueDate */
    public static final String COLUMNNAME_LIC_IssueDate = "LIC_IssueDate";

	/** Set Date of Issue.
	  * The date the licence was issued or approved.
	  */
	public void setLIC_IssueDate (Timestamp LIC_IssueDate);

	/** Get Date of Issue.
	  * The date the licence was issued or approved.
	  */
	public Timestamp getLIC_IssueDate();

    /** Column name LIC_IsTemporary */
    public static final String COLUMNNAME_LIC_IsTemporary = "LIC_IsTemporary";

	/** Set Temporary?.
	  * Is the licence temporary?
	  */
	public void setLIC_IsTemporary (boolean LIC_IsTemporary);

	/** Get Temporary?.
	  * Is the licence temporary?
	  */
	public boolean isLIC_IsTemporary();

    /** Column name LIC_Licence_No */
    public static final String COLUMNNAME_LIC_Licence_No = "LIC_Licence_No";

	/** Set Licence #.
	  * The Aviation licence number.
	  */
	public void setLIC_Licence_No (String LIC_Licence_No);

	/** Get Licence #.
	  * The Aviation licence number.
	  */
	public String getLIC_Licence_No();

    /** Column name LIC_PaxLimit */
    public static final String COLUMNNAME_LIC_PaxLimit = "LIC_PaxLimit";

	/** Set Passenger Limit.
	  * The maximum number of passengers allowed.  Leave blank for unlimited.
	  */
	public void setLIC_PaxLimit (int LIC_PaxLimit);

	/** Get Passenger Limit.
	  * The maximum number of passengers allowed.  Leave blank for unlimited.
	  */
	public int getLIC_PaxLimit();

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

    /** Column name LIC_VFROTT */
    public static final String COLUMNNAME_LIC_VFROTT = "LIC_VFROTT";

	/** Set VFR Over the Top.
	  * Does the licence permit VFR Over The Top (OTT)?
	  */
	public void setLIC_VFROTT (boolean LIC_VFROTT);

	/** Get VFR Over the Top.
	  * Does the licence permit VFR Over The Top (OTT)?
	  */
	public boolean isLIC_VFROTT();

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
