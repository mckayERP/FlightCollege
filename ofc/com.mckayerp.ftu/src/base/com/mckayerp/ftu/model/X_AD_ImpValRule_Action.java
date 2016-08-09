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

/** Generated Model for AD_ImpValRule_Action
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_AD_ImpValRule_Action extends PO implements I_AD_ImpValRule_Action, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160808L;

    /** Standard Constructor */
    public X_AD_ImpValRule_Action (Properties ctx, int AD_ImpValRule_Action_ID, String trxName)
    {
      super (ctx, AD_ImpValRule_Action_ID, trxName);
      /** if (AD_ImpValRule_Action_ID == 0)
        {
			setAD_ImpVal_Rule_ID (0);
			setAD_ImpValRule_Action_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_ImpValRule_Action (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ImpValRule_Action[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Column getAD_Column_F() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Column)MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
			.getPO(getAD_Column_From(), get_TrxName());	}

	/** Set Column From.
		@param AD_Column_From 
		Column in the table to take the value from
	  */
	public void setAD_Column_From (int AD_Column_From)
	{
		set_Value (COLUMNNAME_AD_Column_From, Integer.valueOf(AD_Column_From));
	}

	/** Get Column From.
		@return Column in the table to take the value from
	  */
	public int getAD_Column_From () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_From);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Column)MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
			.getPO(getAD_Column_ID(), get_TrxName());	}

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1) 
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_AD_ImpVal_Rule getAD_ImpVal_Rule() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_AD_ImpVal_Rule)MTable.get(getCtx(), com.mckayerp.ftu.model.I_AD_ImpVal_Rule.Table_Name)
			.getPO(getAD_ImpVal_Rule_ID(), get_TrxName());	}

	/** Set Import Validator Rule.
		@param AD_ImpVal_Rule_ID Import Validator Rule	  */
	public void setAD_ImpVal_Rule_ID (int AD_ImpVal_Rule_ID)
	{
		if (AD_ImpVal_Rule_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ImpVal_Rule_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ImpVal_Rule_ID, Integer.valueOf(AD_ImpVal_Rule_ID));
	}

	/** Get Import Validator Rule.
		@return Import Validator Rule	  */
	public int getAD_ImpVal_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ImpVal_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Import Validator Rule Action.
		@param AD_ImpValRule_Action_ID Import Validator Rule Action	  */
	public void setAD_ImpValRule_Action_ID (int AD_ImpValRule_Action_ID)
	{
		if (AD_ImpValRule_Action_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ImpValRule_Action_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ImpValRule_Action_ID, Integer.valueOf(AD_ImpValRule_Action_ID));
	}

	/** Get Import Validator Rule Action.
		@return Import Validator Rule Action	  */
	public int getAD_ImpValRule_Action_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ImpValRule_Action_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set New Value.
		@param NewValue 
		New field value
	  */
	public void setNewValue (String NewValue)
	{
		set_Value (COLUMNNAME_NewValue, NewValue);
	}

	/** Get New Value.
		@return New field value
	  */
	public String getNewValue () 
	{
		return (String)get_Value(COLUMNNAME_NewValue);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}