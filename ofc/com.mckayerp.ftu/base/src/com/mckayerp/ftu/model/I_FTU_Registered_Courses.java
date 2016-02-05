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

/** Generated Interface for FTU_Registered_Courses
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public interface I_FTU_Registered_Courses 
{

    /** TableName=FTU_Registered_Courses */
    public static final String Table_Name = "FTU_Registered_Courses";

    /** AD_Table_ID=1000014 */
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

    /** Column name CourseCode */
    public static final String COLUMNNAME_CourseCode = "CourseCode";

	/** Set Course Code	  */
	public void setCourseCode (String CourseCode);

	/** Get Course Code	  */
	public String getCourseCode();

    /** Column name CourseName */
    public static final String COLUMNNAME_CourseName = "CourseName";

	/** Set Course Name.
	  * The name of the course.
	  */
	public void setCourseName (String CourseName);

	/** Get Course Name.
	  * The name of the course.
	  */
	public String getCourseName();

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

    /** Column name FTU_Course_Inst_ID */
    public static final String COLUMNNAME_FTU_Course_Inst_ID = "FTU_Course_Inst_ID";

	/** Set Course Instance ID	  */
	public void setFTU_Course_Inst_ID (int FTU_Course_Inst_ID);

	/** Get Course Instance ID	  */
	public int getFTU_Course_Inst_ID();

	public com.mckayerp.ftu.model.I_FTU_Course_Inst getFTU_Course_Inst() throws RuntimeException;

    /** Column name FTU_Registered_Courses_ID */
    public static final String COLUMNNAME_FTU_Registered_Courses_ID = "FTU_Registered_Courses_ID";

	/** Set Registered Courses ID	  */
	public void setFTU_Registered_Courses_ID (int FTU_Registered_Courses_ID);

	/** Get Registered Courses ID	  */
	public int getFTU_Registered_Courses_ID();

    /** Column name FTU_Student_ID */
    public static final String COLUMNNAME_FTU_Student_ID = "FTU_Student_ID";

	/** Set Student ID	  */
	public void setFTU_Student_ID (int FTU_Student_ID);

	/** Get Student ID	  */
	public int getFTU_Student_ID();

	public com.mckayerp.ftu.model.I_FTU_Student getFTU_Student() throws RuntimeException;

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

    /** Column name LastGrade */
    public static final String COLUMNNAME_LastGrade = "LastGrade";

	/** Set Last Grade.
	  * The last grade submitted for this student in this course.
	  */
	public void setLastGrade (String LastGrade);

	/** Get Last Grade.
	  * The last grade submitted for this student in this course.
	  */
	public String getLastGrade();

    /** Column name Program_Code */
    public static final String COLUMNNAME_Program_Code = "Program_Code";

	/** Set Program Code	  */
	public void setProgram_Code (String Program_Code);

	/** Get Program Code	  */
	public String getProgram_Code();

    /** Column name Registration_Status */
    public static final String COLUMNNAME_Registration_Status = "Registration_Status";

	/** Set Registration Status	  */
	public void setRegistration_Status (String Registration_Status);

	/** Get Registration Status	  */
	public String getRegistration_Status();

    /** Column name SemesterCode */
    public static final String COLUMNNAME_SemesterCode = "SemesterCode";

	/** Set Semester	  */
	public void setSemesterCode (String SemesterCode);

	/** Get Semester	  */
	public String getSemesterCode();

    /** Column name StudentName */
    public static final String COLUMNNAME_StudentName = "StudentName";

	/** Set Student Name	  */
	public void setStudentName (String StudentName);

	/** Get Student Name	  */
	public String getStudentName();

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
