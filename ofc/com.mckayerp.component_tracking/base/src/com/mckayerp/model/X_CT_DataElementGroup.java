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
package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for CT_DataElementGroup
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_CT_DataElementGroup extends PO implements I_CT_DataElementGroup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_CT_DataElementGroup (Properties ctx, int CT_DataElementGroup_ID, String trxName)
    {
      super (ctx, CT_DataElementGroup_ID, trxName);
      /** if (CT_DataElementGroup_ID == 0)
        {
			setCT_DataElementGroup_ID (0);
        } */
    }

    /** Load Constructor */
    public X_CT_DataElementGroup (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CT_DataElementGroup[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Data Group.
		@param CT_DataElementGroup_ID 
		Select the data group for this data element.  A group of elements will be shown on the same chart.
	  */
	public void setCT_DataElementGroup_ID (int CT_DataElementGroup_ID)
	{
		if (CT_DataElementGroup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_DataElementGroup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_DataElementGroup_ID, Integer.valueOf(CT_DataElementGroup_ID));
	}

	/** Get Data Group.
		@return Select the data group for this data element.  A group of elements will be shown on the same chart.
	  */
	public int getCT_DataElementGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataElementGroup_ID);
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

	/** Set Value Label.
		@param ValueLabel 
		The label to use on the value axis when the data is graphed.
	  */
	public void setValueLabel (String ValueLabel)
	{
		set_Value (COLUMNNAME_ValueLabel, ValueLabel);
	}

	/** Get Value Label.
		@return The label to use on the value axis when the data is graphed.
	  */
	public String getValueLabel () 
	{
		return (String)get_Value(COLUMNNAME_ValueLabel);
	}
}