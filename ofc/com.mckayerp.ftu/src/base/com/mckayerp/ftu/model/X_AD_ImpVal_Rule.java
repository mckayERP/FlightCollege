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

/** Generated Model for AD_ImpVal_Rule
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_AD_ImpVal_Rule extends PO implements I_AD_ImpVal_Rule, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161023L;

    /** Standard Constructor */
    public X_AD_ImpVal_Rule (Properties ctx, int AD_ImpVal_Rule_ID, String trxName)
    {
      super (ctx, AD_ImpVal_Rule_ID, trxName);
      /** if (AD_ImpVal_Rule_ID == 0)
        {
			setAD_ImpVal_Rule_ID (0);
			setAD_ImpValidatorRules_ID (0);
			setName (null);
			setValidatorTiming (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_ImpVal_Rule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ImpVal_Rule[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

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

	public com.mckayerp.ftu.model.I_AD_ImpValidatorRules getAD_ImpValidatorRules() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_AD_ImpValidatorRules)MTable.get(getCtx(), com.mckayerp.ftu.model.I_AD_ImpValidatorRules.Table_Name)
			.getPO(getAD_ImpValidatorRules_ID(), get_TrxName());	}

	/** Set Import Validator Rules.
		@param AD_ImpValidatorRules_ID Import Validator Rules	  */
	public void setAD_ImpValidatorRules_ID (int AD_ImpValidatorRules_ID)
	{
		if (AD_ImpValidatorRules_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ImpValidatorRules_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ImpValidatorRules_ID, Integer.valueOf(AD_ImpValidatorRules_ID));
	}

	/** Get Import Validator Rules.
		@return Import Validator Rules	  */
	public int getAD_ImpValidatorRules_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ImpValidatorRules_ID);
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

	/** ValidatorTiming AD_Reference_ID=1000067 */
	public static final int VALIDATORTIMING_AD_Reference_ID=1000067;
	/** Before Validation = 10 */
	public static final String VALIDATORTIMING_BeforeValidation = "10";
	/** After Validation = 20 */
	public static final String VALIDATORTIMING_AfterValidation = "20";
	/** Before Import = 30 */
	public static final String VALIDATORTIMING_BeforeImport = "30";
	/** After Import = 40 */
	public static final String VALIDATORTIMING_AfterImport = "40";
	/** Set Validator Timing.
		@param ValidatorTiming 
		The timing that determines when the rule should be run, IE, "Before Validation", "After Import"...
	  */
	public void setValidatorTiming (String ValidatorTiming)
	{

		set_Value (COLUMNNAME_ValidatorTiming, ValidatorTiming);
	}

	/** Get Validator Timing.
		@return The timing that determines when the rule should be run, IE, "Before Validation", "After Import"...
	  */
	public String getValidatorTiming () 
	{
		return (String)get_Value(COLUMNNAME_ValidatorTiming);
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