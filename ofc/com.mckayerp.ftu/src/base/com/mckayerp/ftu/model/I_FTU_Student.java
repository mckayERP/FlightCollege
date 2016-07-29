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

/** Generated Interface for FTU_Student
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Student 
{

    /** TableName=FTU_Student */
    public static final String Table_Name = "FTU_Student";

    /** AD_Table_ID=1000011 */
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

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

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

    /** Column name DateDeparted */
    public static final String COLUMNNAME_DateDeparted = "DateDeparted";

	/** Set Date Departed.
	  * Date the student left the program and stopped being a student.
	  */
	public void setDateDeparted (Timestamp DateDeparted);

	/** Get Date Departed.
	  * Date the student left the program and stopped being a student.
	  */
	public Timestamp getDateDeparted();

    /** Column name DateEnrolled */
    public static final String COLUMNNAME_DateEnrolled = "DateEnrolled";

	/** Set Date Enrolled.
	  * The date the student was enrolled or matriculated.
	  */
	public void setDateEnrolled (Timestamp DateEnrolled);

	/** Get Date Enrolled.
	  * The date the student was enrolled or matriculated.
	  */
	public Timestamp getDateEnrolled();

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

    /** Column name FTU_Class_ID */
    public static final String COLUMNNAME_FTU_Class_ID = "FTU_Class_ID";

	/** Set Class ID	  */
	public void setFTU_Class_ID (int FTU_Class_ID);

	/** Get Class ID	  */
	public int getFTU_Class_ID();

	public com.mckayerp.ftu.model.I_FTU_Class getFTU_Class() throws RuntimeException;

    /** Column name FTU_Student_ID */
    public static final String COLUMNNAME_FTU_Student_ID = "FTU_Student_ID";

	/** Set Student ID	  */
	public void setFTU_Student_ID (int FTU_Student_ID);

	/** Get Student ID	  */
	public int getFTU_Student_ID();

    /** Column name FundingSource */
    public static final String COLUMNNAME_FundingSource = "FundingSource";

	/** Set Funding Source.
	  * The main (>50%) funding source of the students as required by the Ministry of Training Colleges and Universities
	  */
	public void setFundingSource (String FundingSource);

	/** Get Funding Source.
	  * The main (>50%) funding source of the students as required by the Ministry of Training Colleges and Universities
	  */
	public String getFundingSource();

    /** Column name International */
    public static final String COLUMNNAME_International = "International";

	/** Set International.
	  * Is the student international or domestic.
	  */
	public void setInternational (boolean International);

	/** Get International.
	  * Is the student international or domestic.
	  */
	public boolean isInternational();

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

    /** Column name IsFullTime */
    public static final String COLUMNNAME_IsFullTime = "IsFullTime";

	/** Set Full Time?.
	  * Is the student a full-time student? No indicates a part time student.
	  */
	public void setIsFullTime (boolean IsFullTime);

	/** Get Full Time?.
	  * Is the student a full-time student? No indicates a part time student.
	  */
	public boolean isFullTime();

    /** Column name IsVocational */
    public static final String COLUMNNAME_IsVocational = "IsVocational";

	/** Set Vocational Student.
	  * Is the student a vocational student.
	  */
	public void setIsVocational (boolean IsVocational);

	/** Get Vocational Student.
	  * Is the student a vocational student.
	  */
	public boolean isVocational();

    /** Column name Primary_Inst_ID */
    public static final String COLUMNNAME_Primary_Inst_ID = "Primary_Inst_ID";

	/** Set Primary Instructor.
	  * The primary instructor for the student
	  */
	public void setPrimary_Inst_ID (int Primary_Inst_ID);

	/** Get Primary Instructor.
	  * The primary instructor for the student
	  */
	public int getPrimary_Inst_ID();

	public org.compiere.model.I_S_Resource getPrimary_Inst() throws RuntimeException;

    /** Column name Secondary_Inst_ID */
    public static final String COLUMNNAME_Secondary_Inst_ID = "Secondary_Inst_ID";

	/** Set Secondary Instructor.
	  * The secondary instructor for the student
	  */
	public void setSecondary_Inst_ID (int Secondary_Inst_ID);

	/** Get Secondary Instructor.
	  * The secondary instructor for the student
	  */
	public int getSecondary_Inst_ID();

	public org.compiere.model.I_S_Resource getSecondary_Inst() throws RuntimeException;

    /** Column name StudentNum */
    public static final String COLUMNNAME_StudentNum = "StudentNum";

	/** Set Student Number.
	  * The ID of the Student at the FTU
	  */
	public void setStudentNum (String StudentNum);

	/** Get Student Number.
	  * The ID of the Student at the FTU
	  */
	public String getStudentNum();

    /** Column name StudentStage */
    public static final String COLUMNNAME_StudentStage = "StudentStage";

	/** Set Training Stage.
	  * The stage of training the student is currently working towards.
	  */
	public void setStudentStage (String StudentStage);

	/** Get Training Stage.
	  * The stage of training the student is currently working towards.
	  */
	public String getStudentStage();

    /** Column name StudentStatus */
    public static final String COLUMNNAME_StudentStatus = "StudentStatus";

	/** Set Student Status.
	  * The status of the student.
	  */
	public void setStudentStatus (String StudentStatus);

	/** Get Student Status.
	  * The status of the student.
	  */
	public String getStudentStatus();

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
