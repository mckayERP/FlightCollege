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

/** Generated Model for FTU_MaintSchedule
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintSchedule extends PO implements I_FTU_MaintSchedule, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170425L;

    /** Standard Constructor */
    public X_FTU_MaintSchedule (Properties ctx, int FTU_MaintSchedule_ID, String trxName)
    {
      super (ctx, FTU_MaintSchedule_ID, trxName);
      /** if (FTU_MaintSchedule_ID == 0)
        {
			setFTU_AppliesToProduct_ID (0);
			setFTU_MaintSchedule_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintSchedule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintSchedule[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
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

	public org.compiere.model.I_M_Product getFTU_AppliesToProduct() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getFTU_AppliesToProduct_ID(), get_TrxName());	}

	/** Set Applies to Product.
		@param FTU_AppliesToProduct_ID 
		The product to which the maintenance requirement or schedule applies.
	  */
	public void setFTU_AppliesToProduct_ID (int FTU_AppliesToProduct_ID)
	{
		if (FTU_AppliesToProduct_ID < 1) 
			set_Value (COLUMNNAME_FTU_AppliesToProduct_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_AppliesToProduct_ID, Integer.valueOf(FTU_AppliesToProduct_ID));
	}

	/** Get Applies to Product.
		@return The product to which the maintenance requirement or schedule applies.
	  */
	public int getFTU_AppliesToProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_AppliesToProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maintenance Schedule ID.
		@param FTU_MaintSchedule_ID Maintenance Schedule ID	  */
	public void setFTU_MaintSchedule_ID (int FTU_MaintSchedule_ID)
	{
		if (FTU_MaintSchedule_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintSchedule_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintSchedule_ID, Integer.valueOf(FTU_MaintSchedule_ID));
	}

	/** Get Maintenance Schedule ID.
		@return Maintenance Schedule ID	  */
	public int getFTU_MaintSchedule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintSchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}