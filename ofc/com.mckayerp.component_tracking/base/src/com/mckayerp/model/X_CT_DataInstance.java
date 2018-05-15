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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for CT_DataInstance
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_CT_DataInstance extends PO implements I_CT_DataInstance, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_CT_DataInstance (Properties ctx, int CT_DataInstance_ID, String trxName)
    {
      super (ctx, CT_DataInstance_ID, trxName);
      /** if (CT_DataInstance_ID == 0)
        {
			setCT_DataInstance_ID (0);
        } */
    }

    /** Load Constructor */
    public X_CT_DataInstance (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CT_DataInstance[")
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

	public com.mckayerp.model.I_CT_DataElementValue getCT_DataElementValue() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_DataElementValue)MTable.get(getCtx(), com.mckayerp.model.I_CT_DataElementValue.Table_Name)
			.getPO(getCT_DataElementValue_ID(), get_TrxName());	}

	/** Set Data Element Value.
		@param CT_DataElementValue_ID 
		A predefined value of a data element.
	  */
	public void setCT_DataElementValue_ID (int CT_DataElementValue_ID)
	{
		if (CT_DataElementValue_ID < 1) 
			set_Value (COLUMNNAME_CT_DataElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_CT_DataElementValue_ID, Integer.valueOf(CT_DataElementValue_ID));
	}

	/** Get Data Element Value.
		@return A predefined value of a data element.
	  */
	public int getCT_DataElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Data Instance ID.
		@param CT_DataInstance_ID Data Instance ID	  */
	public void setCT_DataInstance_ID (int CT_DataInstance_ID)
	{
		if (CT_DataInstance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_DataInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_DataInstance_ID, Integer.valueOf(CT_DataInstance_ID));
	}

	/** Get Data Instance ID.
		@return Data Instance ID	  */
	public int getCT_DataInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.model.I_CT_DataSetInstance getCT_DataSetInstance() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_DataSetInstance)MTable.get(getCtx(), com.mckayerp.model.I_CT_DataSetInstance.Table_Name)
			.getPO(getCT_DataSetInstance_ID(), get_TrxName());	}

	/** Set Data Set Instance.
		@param CT_DataSetInstance_ID 
		A data point within a data set.
	  */
	public void setCT_DataSetInstance_ID (int CT_DataSetInstance_ID)
	{
		if (CT_DataSetInstance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_DataSetInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_DataSetInstance_ID, Integer.valueOf(CT_DataSetInstance_ID));
	}

	/** Get Data Set Instance.
		@return A data point within a data set.
	  */
	public int getCT_DataSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value.
		@param ValueDateTime 
		Date/time value
	  */
	public void setValueDateTime (Timestamp ValueDateTime)
	{
		set_Value (COLUMNNAME_ValueDateTime, ValueDateTime);
	}

	/** Get Value.
		@return Date/time value
	  */
	public Timestamp getValueDateTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValueDateTime);
	}

	/** Set Value.
		@param ValueInteger 
		Integer Value
	  */
	public void setValueInteger (int ValueInteger)
	{
		set_Value (COLUMNNAME_ValueInteger, Integer.valueOf(ValueInteger));
	}

	/** Get Value.
		@return Integer Value
	  */
	public int getValueInteger () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ValueInteger);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value.
		@param ValueNumber 
		Numeric Value
	  */
	public void setValueNumber (BigDecimal ValueNumber)
	{
		set_Value (COLUMNNAME_ValueNumber, ValueNumber);
	}

	/** Get Value.
		@return Numeric Value
	  */
	public BigDecimal getValueNumber () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValueNumber);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ValueString.
		@param ValueString 
		The value of the data as a string
	  */
	public void setValueString (String ValueString)
	{
		set_Value (COLUMNNAME_ValueString, ValueString);
	}

	/** Get ValueString.
		@return The value of the data as a string
	  */
	public String getValueString () 
	{
		return (String)get_Value(COLUMNNAME_ValueString);
	}

	/** Set Value.
		@param ValueYN 
		Yes-No Value
	  */
	public void setValueYN (boolean ValueYN)
	{
		set_Value (COLUMNNAME_ValueYN, Boolean.valueOf(ValueYN));
	}

	/** Get Value.
		@return Yes-No Value
	  */
	public boolean isValueYN () 
	{
		Object oo = get_Value(COLUMNNAME_ValueYN);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}