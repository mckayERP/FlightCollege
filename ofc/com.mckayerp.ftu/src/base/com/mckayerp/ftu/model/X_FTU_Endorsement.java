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

/** Generated Model for FTU_Endorsement
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Endorsement extends PO implements I_FTU_Endorsement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160808L;

    /** Standard Constructor */
    public X_FTU_Endorsement (Properties ctx, int FTU_Endorsement_ID, String trxName)
    {
      super (ctx, FTU_Endorsement_ID, trxName);
      /** if (FTU_Endorsement_ID == 0)
        {
			setFTU_Endorsement_ID (0);
			setFTU_License_Type_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Endorsement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Endorsement[")
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

	/** Set Endorsements and Ratings ID.
		@param FTU_Endorsement_ID Endorsements and Ratings ID	  */
	public void setFTU_Endorsement_ID (int FTU_Endorsement_ID)
	{
		if (FTU_Endorsement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Endorsement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Endorsement_ID, Integer.valueOf(FTU_Endorsement_ID));
	}

	/** Get Endorsements and Ratings ID.
		@return Endorsements and Ratings ID	  */
	public int getFTU_Endorsement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Endorsement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_License_Type getFTU_License_Type() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_License_Type)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_License_Type.Table_Name)
			.getPO(getFTU_License_Type_ID(), get_TrxName());	}

	/** Set Licenses and Permits ID.
		@param FTU_License_Type_ID Licenses and Permits ID	  */
	public void setFTU_License_Type_ID (int FTU_License_Type_ID)
	{
		if (FTU_License_Type_ID < 1) 
			set_Value (COLUMNNAME_FTU_License_Type_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_License_Type_ID, Integer.valueOf(FTU_License_Type_ID));
	}

	/** Get Licenses and Permits ID.
		@return Licenses and Permits ID	  */
	public int getFTU_License_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_License_Type_ID);
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