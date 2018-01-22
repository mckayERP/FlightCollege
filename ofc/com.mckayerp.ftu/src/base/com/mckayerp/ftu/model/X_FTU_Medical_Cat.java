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

/** Generated Model for FTU_Medical_Cat
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Medical_Cat extends PO implements I_FTU_Medical_Cat, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180107L;

    /** Standard Constructor */
    public X_FTU_Medical_Cat (Properties ctx, int FTU_Medical_Cat_ID, String trxName)
    {
      super (ctx, FTU_Medical_Cat_ID, trxName);
      /** if (FTU_Medical_Cat_ID == 0)
        {
			setFTU_Medical_Cat_ID (0);
			setMedCatPriority (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_Medical_Cat (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Medical_Cat[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Medical Category ID.
		@param FTU_Medical_Cat_ID Medical Category ID	  */
	public void setFTU_Medical_Cat_ID (int FTU_Medical_Cat_ID)
	{
		if (FTU_Medical_Cat_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Medical_Cat_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Medical_Cat_ID, Integer.valueOf(FTU_Medical_Cat_ID));
	}

	/** Get Medical Category ID.
		@return Medical Category ID	  */
	public int getFTU_Medical_Cat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Medical_Cat_ID);
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

	/** MedCategory AD_Reference_ID=1000024 */
	public static final int MEDCATEGORY_AD_Reference_ID=1000024;
	/** Cat 1 = C1 */
	public static final String MEDCATEGORY_Cat1 = "C1";
	/** Cat 2 = C2 */
	public static final String MEDCATEGORY_Cat2 = "C2";
	/** Cat 3 = C3 */
	public static final String MEDCATEGORY_Cat3 = "C3";
	/** Cat 4 = C4 */
	public static final String MEDCATEGORY_Cat4 = "C4";
	/** Set Medical Category.
		@param MedCategory 
		Medical Category as defined by Transport Canada
	  */
	public void setMedCategory (String MedCategory)
	{

		set_Value (COLUMNNAME_MedCategory, MedCategory);
	}

	/** Get Medical Category.
		@return Medical Category as defined by Transport Canada
	  */
	public String getMedCategory () 
	{
		return (String)get_Value(COLUMNNAME_MedCategory);
	}

	/** Set Priority.
		@param MedCatPriority 
		The priority of the medical category.  Low numbers trump high numbers.
	  */
	public void setMedCatPriority (int MedCatPriority)
	{
		set_Value (COLUMNNAME_MedCatPriority, Integer.valueOf(MedCatPriority));
	}

	/** Get Priority.
		@return The priority of the medical category.  Low numbers trump high numbers.
	  */
	public int getMedCatPriority () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MedCatPriority);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}