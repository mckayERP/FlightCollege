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
import org.compiere.util.KeyNamePair;

/** Generated Model for FTU_Grading_System
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Grading_System extends PO implements I_FTU_Grading_System, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160721L;

    /** Standard Constructor */
    public X_FTU_Grading_System (Properties ctx, int FTU_Grading_System_ID, String trxName)
    {
      super (ctx, FTU_Grading_System_ID, trxName);
      /** if (FTU_Grading_System_ID == 0)
        {
			setFTU_Grading_System_ID (0);
			setGradingSystem (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Grading_System (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Grading_System[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Grading System ID.
		@param FTU_Grading_System_ID Grading System ID	  */
	public void setFTU_Grading_System_ID (int FTU_Grading_System_ID)
	{
		if (FTU_Grading_System_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Grading_System_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Grading_System_ID, Integer.valueOf(FTU_Grading_System_ID));
	}

	/** Get Grading System ID.
		@return Grading System ID	  */
	public int getFTU_Grading_System_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Grading_System_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getGradingSystem()));
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