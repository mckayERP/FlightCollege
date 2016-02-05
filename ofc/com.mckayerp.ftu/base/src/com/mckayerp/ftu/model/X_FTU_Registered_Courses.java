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
/** Generated Model - DO NOT CHANGE */
package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for FTU_Registered_Courses
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_Registered_Courses extends PO implements I_FTU_Registered_Courses, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160131L;

    /** Standard Constructor */
    public X_FTU_Registered_Courses (Properties ctx, int FTU_Registered_Courses_ID, String trxName)
    {
      super (ctx, FTU_Registered_Courses_ID, trxName);
      /** if (FTU_Registered_Courses_ID == 0)
        {
			setFTU_Course_Inst_ID (0);
			setFTU_Registered_Courses_ID (0);
			setRegistration_Status (null);
// Registered
        } */
    }

    /** Load Constructor */
    public X_FTU_Registered_Courses (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_FTU_Registered_Courses[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Course Code.
		@param CourseCode Course Code	  */
	public void setCourseCode (String CourseCode)
	{
		throw new IllegalArgumentException ("CourseCode is virtual column");	}

	/** Get Course Code.
		@return Course Code	  */
	public String getCourseCode () 
	{
		return (String)get_Value(COLUMNNAME_CourseCode);
	}

	/** Set Course Name.
		@param CourseName 
		The name of the course.
	  */
	public void setCourseName (String CourseName)
	{
		throw new IllegalArgumentException ("CourseName is virtual column");	}

	/** Get Course Name.
		@return The name of the course.
	  */
	public String getCourseName () 
	{
		return (String)get_Value(COLUMNNAME_CourseName);
	}

	public com.mckayerp.ftu.model.I_FTU_Course_Inst getFTU_Course_Inst() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Course_Inst)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Course_Inst.Table_Name)
			.getPO(getFTU_Course_Inst_ID(), get_TrxName());	}

	/** Set Course Instance ID.
		@param FTU_Course_Inst_ID Course Instance ID	  */
	public void setFTU_Course_Inst_ID (int FTU_Course_Inst_ID)
	{
		if (FTU_Course_Inst_ID < 1) 
			set_Value (COLUMNNAME_FTU_Course_Inst_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Course_Inst_ID, Integer.valueOf(FTU_Course_Inst_ID));
	}

	/** Get Course Instance ID.
		@return Course Instance ID	  */
	public int getFTU_Course_Inst_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Course_Inst_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Registered Courses ID.
		@param FTU_Registered_Courses_ID Registered Courses ID	  */
	public void setFTU_Registered_Courses_ID (int FTU_Registered_Courses_ID)
	{
		if (FTU_Registered_Courses_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Registered_Courses_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Registered_Courses_ID, Integer.valueOf(FTU_Registered_Courses_ID));
	}

	/** Get Registered Courses ID.
		@return Registered Courses ID	  */
	public int getFTU_Registered_Courses_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Registered_Courses_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_Student getFTU_Student() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Student)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Student.Table_Name)
			.getPO(getFTU_Student_ID(), get_TrxName());	}

	/** Set Student ID.
		@param FTU_Student_ID Student ID	  */
	public void setFTU_Student_ID (int FTU_Student_ID)
	{
		if (FTU_Student_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Student_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Student_ID, Integer.valueOf(FTU_Student_ID));
	}

	/** Get Student ID.
		@return Student ID	  */
	public int getFTU_Student_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Student_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Last Grade.
		@param LastGrade 
		The last grade submitted for this student in this course.
	  */
	public void setLastGrade (String LastGrade)
	{
		throw new IllegalArgumentException ("LastGrade is virtual column");	}

	/** Get Last Grade.
		@return The last grade submitted for this student in this course.
	  */
	public String getLastGrade () 
	{
		return (String)get_Value(COLUMNNAME_LastGrade);
	}

	/** Set Program Code.
		@param Program_Code Program Code	  */
	public void setProgram_Code (String Program_Code)
	{
		throw new IllegalArgumentException ("Program_Code is virtual column");	}

	/** Get Program Code.
		@return Program Code	  */
	public String getProgram_Code () 
	{
		return (String)get_Value(COLUMNNAME_Program_Code);
	}

	/** Registration_Status AD_Reference_ID=1000017 */
	public static final int REGISTRATION_STATUS_AD_Reference_ID=1000017;
	/** Registered = Registered */
	public static final String REGISTRATION_STATUS_Registered = "Registered";
	/** Dropped = Dropped */
	public static final String REGISTRATION_STATUS_Dropped = "Dropped";
	/** Passed = Passed */
	public static final String REGISTRATION_STATUS_Passed = "Passed";
	/** Failed = Failed */
	public static final String REGISTRATION_STATUS_Failed = "Failed";
	/** Set Registration Status.
		@param Registration_Status Registration Status	  */
	public void setRegistration_Status (String Registration_Status)
	{

		set_Value (COLUMNNAME_Registration_Status, Registration_Status);
	}

	/** Get Registration Status.
		@return Registration Status	  */
	public String getRegistration_Status () 
	{
		return (String)get_Value(COLUMNNAME_Registration_Status);
	}

	/** Set Semester.
		@param SemesterCode Semester	  */
	public void setSemesterCode (String SemesterCode)
	{
		throw new IllegalArgumentException ("SemesterCode is virtual column");	}

	/** Get Semester.
		@return Semester	  */
	public String getSemesterCode () 
	{
		return (String)get_Value(COLUMNNAME_SemesterCode);
	}

	/** Set Student Name.
		@param StudentName Student Name	  */
	public void setStudentName (String StudentName)
	{
		throw new IllegalArgumentException ("StudentName is virtual column");	}

	/** Get Student Name.
		@return Student Name	  */
	public String getStudentName () 
	{
		return (String)get_Value(COLUMNNAME_StudentName);
	}

	/** Set Student Number.
		@param StudentNum 
		The ID of the Student at the FTU
	  */
	public void setStudentNum (String StudentNum)
	{
		throw new IllegalArgumentException ("StudentNum is virtual column");	}

	/** Get Student Number.
		@return The ID of the Student at the FTU
	  */
	public String getStudentNum () 
	{
		return (String)get_Value(COLUMNNAME_StudentNum);
	}
}