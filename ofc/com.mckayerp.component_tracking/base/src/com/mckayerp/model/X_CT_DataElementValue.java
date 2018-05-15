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

/** Generated Model for CT_DataElementValue
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_CT_DataElementValue extends PO implements I_CT_DataElementValue, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_CT_DataElementValue (Properties ctx, int CT_DataElementValue_ID, String trxName)
    {
      super (ctx, CT_DataElementValue_ID, trxName);
      /** if (CT_DataElementValue_ID == 0)
        {
			setCT_DataElementValue_ID (0);
        } */
    }

    /** Load Constructor */
    public X_CT_DataElementValue (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CT_DataElementValue[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public com.mckayerp.model.I_CT_DataElement getCT_DataElement() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_DataElement)MTable.get(getCtx(), com.mckayerp.model.I_CT_DataElement.Table_Name)
			.getPO(getCT_DataElement_ID(), get_TrxName());	}

	/** Set Data Element.
		@param CT_DataElement_ID 
		Definition of a data element that forms part of a data set.
	  */
	public void setCT_DataElement_ID (int CT_DataElement_ID)
	{
		if (CT_DataElement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_DataElement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_DataElement_ID, Integer.valueOf(CT_DataElement_ID));
	}

	/** Get Data Element.
		@return Definition of a data element that forms part of a data set.
	  */
	public int getCT_DataElement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataElement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Data Element Value ID.
		@param CT_DataElementValue_ID Data Element Value ID	  */
	public void setCT_DataElementValue_ID (int CT_DataElementValue_ID)
	{
		if (CT_DataElementValue_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_DataElementValue_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_DataElementValue_ID, Integer.valueOf(CT_DataElementValue_ID));
	}

	/** Get Data Element Value ID.
		@return Data Element Value ID	  */
	public int getCT_DataElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataElementValue_ID);
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
}