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
/** Generated Model - DO NOT CHANGE */
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for FTU_Course
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Course extends PO implements I_FTU_Course, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_Course (Properties ctx, int FTU_Course_ID, String trxName)
    {
      super (ctx, FTU_Course_ID, trxName);
      /** if (FTU_Course_ID == 0)
        {
			setFTU_Course_ID (0);
			setFTU_Training_Unit_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Course (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Course[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Add Missing Button.
		@param AddMissingButton Add Missing Button	  */
	public void setAddMissingButton (String AddMissingButton)
	{
		set_Value (COLUMNNAME_AddMissingButton, AddMissingButton);
	}

	/** Get Add Missing Button.
		@return Add Missing Button	  */
	public String getAddMissingButton () 
	{
		return (String)get_Value(COLUMNNAME_AddMissingButton);
	}

	/** Set Hours.
		@param CourseHrs 
		Hours
	  */
	public void setCourseHrs (BigDecimal CourseHrs)
	{
		set_Value (COLUMNNAME_CourseHrs, CourseHrs);
	}

	/** Get Hours.
		@return Hours
	  */
	public BigDecimal getCourseHrs () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CourseHrs);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** CourseLevel AD_Reference_ID=1000006 */
	public static final int COURSELEVEL_AD_Reference_ID=1000006;
	/** L01 = 1 */
	public static final String COURSELEVEL_L01 = "1";
	/** L02 = 2 */
	public static final String COURSELEVEL_L02 = "2";
	/** L03 = 3 */
	public static final String COURSELEVEL_L03 = "3";
	/** L04 = 4 */
	public static final String COURSELEVEL_L04 = "4";
	/** Set Level.
		@param CourseLevel 
		Level of the course
	  */
	public void setCourseLevel (String CourseLevel)
	{

		set_Value (COLUMNNAME_CourseLevel, CourseLevel);
	}

	/** Get Level.
		@return Level of the course
	  */
	public String getCourseLevel () 
	{
		return (String)get_Value(COLUMNNAME_CourseLevel);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Course ID.
		@param FTU_Course_ID Course ID	  */
	public void setFTU_Course_ID (int FTU_Course_ID)
	{
		if (FTU_Course_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Course_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Course_ID, Integer.valueOf(FTU_Course_ID));
	}

	/** Get Course ID.
		@return Course ID	  */
	public int getFTU_Course_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Course_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_Training_Unit getFTU_Training_Unit() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Training_Unit)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Training_Unit.Table_Name)
			.getPO(getFTU_Training_Unit_ID(), get_TrxName());	}

	/** Set Training Unit ID.
		@param FTU_Training_Unit_ID 
		ID (Key) of the Training Unit
	  */
	public void setFTU_Training_Unit_ID (int FTU_Training_Unit_ID)
	{
		if (FTU_Training_Unit_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Training_Unit_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Training_Unit_ID, Integer.valueOf(FTU_Training_Unit_ID));
	}

	/** Get Training Unit ID.
		@return ID (Key) of the Training Unit
	  */
	public int getFTU_Training_Unit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Training_Unit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** GradingSystem AD_Reference_ID=1000001 */
	public static final int GRADINGSYSTEM_AD_Reference_ID=1000001;
	/** Pass / Fail = Pass/Fail */
	public static final String GRADINGSYSTEM_PassFail = "Pass/Fail";
	/** A+ Through F = A+F */
	public static final String GRADINGSYSTEM_APlusThroughF = "A+F";
	/** Percent = Percent */
	public static final String GRADINGSYSTEM_Percent = "Percent";
	/** Set Grading System.
		@param GradingSystem Grading System	  */
	public void setGradingSystem (String GradingSystem)
	{

		set_Value (COLUMNNAME_GradingSystem, GradingSystem);
	}

	/** Get Grading System.
		@return Grading System	  */
	public String getGradingSystem () 
	{
		return (String)get_Value(COLUMNNAME_GradingSystem);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Elective.
		@param IsElective 
		Is the course an elective?
	  */
	public void setIsElective (boolean IsElective)
	{
		set_Value (COLUMNNAME_IsElective, Boolean.valueOf(IsElective));
	}

	/** Get Elective.
		@return Is the course an elective?
	  */
	public boolean isElective () 
	{
		Object oo = get_Value(COLUMNNAME_IsElective);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}