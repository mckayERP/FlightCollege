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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for FTU_Semester
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_Semester extends PO implements I_FTU_Semester, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160131L;

    /** Standard Constructor */
    public X_FTU_Semester (Properties ctx, int FTU_Semester_ID, String trxName)
    {
      super (ctx, FTU_Semester_ID, trxName);
      /** if (FTU_Semester_ID == 0)
        {
			setCalendarYear (new Timestamp( System.currentTimeMillis() ));
			setFTU_Semester_ID (0);
			setFTU_Training_Unit_ID (0);
			setName (null);
			setTerm_Code (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Semester (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Semester[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Year.
		@param CalendarYear 
		Calendar Year
	  */
	public void setCalendarYear (Timestamp CalendarYear)
	{
		set_Value (COLUMNNAME_CalendarYear, CalendarYear);
	}

	/** Get Year.
		@return Calendar Year
	  */
	public Timestamp getCalendarYear () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CalendarYear);
	}

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

	/** Set Semester Code.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Semester Code.
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

	/** Term_Code AD_Reference_ID=1000000 */
	public static final int TERM_CODE_AD_Reference_ID=1000000;
	/** S = S */
	public static final String TERM_CODE_S = "S";
	/** W = W */
	public static final String TERM_CODE_W = "W";
	/** F = F */
	public static final String TERM_CODE_F = "F";
	/** Set Term Code.
		@param Term_Code 
		Season/term code
	  */
	public void setTerm_Code (String Term_Code)
	{

		set_Value (COLUMNNAME_Term_Code, Term_Code);
	}

	/** Get Term Code.
		@return Season/term code
	  */
	public String getTerm_Code () 
	{
		return (String)get_Value(COLUMNNAME_Term_Code);
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