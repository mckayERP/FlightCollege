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

/** Generated Model for FTU_Course_Grade
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_Course_Grade extends PO implements I_FTU_Course_Grade, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160131L;

    /** Standard Constructor */
    public X_FTU_Course_Grade (Properties ctx, int FTU_Course_Grade_ID, String trxName)
    {
      super (ctx, FTU_Course_Grade_ID, trxName);
      /** if (FTU_Course_Grade_ID == 0)
        {
			setCourseGrade (null);
// "IF"
			setFTU_Course_Grade_ID (0);
			setFTU_Registered_Courses_ID (0);
			setIsGradeSubmitted (false);
// N
        } */
    }

    /** Load Constructor */
    public X_FTU_Course_Grade (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Course_Grade[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** CourseGrade AD_Reference_ID=1000015 */
	public static final int COURSEGRADE_AD_Reference_ID=1000015;
	/** A+ = A+ */
	public static final String COURSEGRADE_APlus = "A+";
	/** A = A */
	public static final String COURSEGRADE_A = "A";
	/** A- = A- */
	public static final String COURSEGRADE_A_ = "A-";
	/** B+ = B+ */
	public static final String COURSEGRADE_BPlus = "B+";
	/** B = B */
	public static final String COURSEGRADE_B = "B";
	/** B- = B- */
	public static final String COURSEGRADE_B_ = "B-";
	/** C+ = C+ */
	public static final String COURSEGRADE_CPlus = "C+";
	/** C = C */
	public static final String COURSEGRADE_C = "C";
	/** C- = C- */
	public static final String COURSEGRADE_C_ = "C-";
	/** D+ = D+ */
	public static final String COURSEGRADE_DPlus = "D+";
	/** D = D */
	public static final String COURSEGRADE_D = "D";
	/** D- = D- */
	public static final String COURSEGRADE_D_ = "D-";
	/** F = F */
	public static final String COURSEGRADE_F = "F";
	/** IF = IF */
	public static final String COURSEGRADE_IF = "IF";
	/** P = P */
	public static final String COURSEGRADE_P = "P";
	/** ID- = ID- */
	public static final String COURSEGRADE_ID_ = "ID-";
	/** W = W */
	public static final String COURSEGRADE_W = "W";
	/** EX = EX */
	public static final String COURSEGRADE_EX = "EX";
	/** Set Grade.
		@param CourseGrade 
		The grade or mark for that course.
	  */
	public void setCourseGrade (String CourseGrade)
	{

		set_Value (COLUMNNAME_CourseGrade, CourseGrade);
	}

	/** Get Grade.
		@return The grade or mark for that course.
	  */
	public String getCourseGrade () 
	{
		return (String)get_Value(COLUMNNAME_CourseGrade);
	}

	/** Set Course Grade ID.
		@param FTU_Course_Grade_ID Course Grade ID	  */
	public void setFTU_Course_Grade_ID (int FTU_Course_Grade_ID)
	{
		if (FTU_Course_Grade_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Course_Grade_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Course_Grade_ID, Integer.valueOf(FTU_Course_Grade_ID));
	}

	/** Get Course Grade ID.
		@return Course Grade ID	  */
	public int getFTU_Course_Grade_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Course_Grade_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_Registered_Courses getFTU_Registered_Courses() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Registered_Courses)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Registered_Courses.Table_Name)
			.getPO(getFTU_Registered_Courses_ID(), get_TrxName());	}

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

	/** Set Grade Submitted.
		@param IsGradeSubmitted Grade Submitted	  */
	public void setIsGradeSubmitted (boolean IsGradeSubmitted)
	{
		set_Value (COLUMNNAME_IsGradeSubmitted, Boolean.valueOf(IsGradeSubmitted));
	}

	/** Get Grade Submitted.
		@return Grade Submitted	  */
	public boolean isGradeSubmitted () 
	{
		Object oo = get_Value(COLUMNNAME_IsGradeSubmitted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}