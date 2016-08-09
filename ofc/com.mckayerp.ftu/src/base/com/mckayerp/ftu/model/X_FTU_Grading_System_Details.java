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

/** Generated Model for FTU_Grading_System_Details
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Grading_System_Details extends PO implements I_FTU_Grading_System_Details, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160808L;

    /** Standard Constructor */
    public X_FTU_Grading_System_Details (Properties ctx, int FTU_Grading_System_Details_ID, String trxName)
    {
      super (ctx, FTU_Grading_System_Details_ID, trxName);
      /** if (FTU_Grading_System_Details_ID == 0)
        {
			setFTU_Grading_System_Details_ID (0);
			setFTU_Grading_System_ID (Env.ZERO);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Grading_System_Details (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Grading_System_Details[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Grading System Details ID.
		@param FTU_Grading_System_Details_ID Grading System Details ID	  */
	public void setFTU_Grading_System_Details_ID (int FTU_Grading_System_Details_ID)
	{
		if (FTU_Grading_System_Details_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Grading_System_Details_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Grading_System_Details_ID, Integer.valueOf(FTU_Grading_System_Details_ID));
	}

	/** Get Grading System Details ID.
		@return Grading System Details ID	  */
	public int getFTU_Grading_System_Details_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Grading_System_Details_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Grading System ID.
		@param FTU_Grading_System_ID Grading System ID	  */
	public void setFTU_Grading_System_ID (BigDecimal FTU_Grading_System_ID)
	{
		set_ValueNoCheck (COLUMNNAME_FTU_Grading_System_ID, FTU_Grading_System_ID);
	}

	/** Get Grading System ID.
		@return Grading System ID	  */
	public BigDecimal getFTU_Grading_System_ID () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_Grading_System_ID);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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