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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for FTU_Course_Inst
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Course_Inst extends PO implements I_FTU_Course_Inst, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_Course_Inst (Properties ctx, int FTU_Course_Inst_ID, String trxName)
    {
      super (ctx, FTU_Course_Inst_ID, trxName);
      /** if (FTU_Course_Inst_ID == 0)
        {
			setFTU_Course_ID (0);
			setFTU_Course_Inst_ID (0);
			setFTU_Semester_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_Course_Inst (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Course_Inst[")
        .append(get_ID()).append("]");
      return sb.toString();
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

		throw new IllegalArgumentException ("CourseLevel is virtual column");	}

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

	public com.mckayerp.ftu.model.I_FTU_Course getFTU_Course() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Course)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Course.Table_Name)
			.getPO(getFTU_Course_ID(), get_TrxName());	}

	/** Set Course ID.
		@param FTU_Course_ID Course ID	  */
	public void setFTU_Course_ID (int FTU_Course_ID)
	{
		if (FTU_Course_ID < 1) 
			set_Value (COLUMNNAME_FTU_Course_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Course_ID, Integer.valueOf(FTU_Course_ID));
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

	/** Set Course Instance ID.
		@param FTU_Course_Inst_ID Course Instance ID	  */
	public void setFTU_Course_Inst_ID (int FTU_Course_Inst_ID)
	{
		if (FTU_Course_Inst_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Course_Inst_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Course_Inst_ID, Integer.valueOf(FTU_Course_Inst_ID));
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

	public com.mckayerp.ftu.model.I_FTU_Semester getFTU_Semester() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Semester)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Semester.Table_Name)
			.getPO(getFTU_Semester_ID(), get_TrxName());	}

	/** Set Sememster ID.
		@param FTU_Semester_ID Sememster ID	  */
	public void setFTU_Semester_ID (int FTU_Semester_ID)
	{
		if (FTU_Semester_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Semester_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Semester_ID, Integer.valueOf(FTU_Semester_ID));
	}

	/** Get Sememster ID.
		@return Sememster ID	  */
	public int getFTU_Semester_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Semester_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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