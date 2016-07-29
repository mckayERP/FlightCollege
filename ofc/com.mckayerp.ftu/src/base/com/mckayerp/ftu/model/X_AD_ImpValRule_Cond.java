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

/** Generated Model for AD_ImpValRule_Cond
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_AD_ImpValRule_Cond extends PO implements I_AD_ImpValRule_Cond, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160721L;

    /** Standard Constructor */
    public X_AD_ImpValRule_Cond (Properties ctx, int AD_ImpValRule_Cond_ID, String trxName)
    {
      super (ctx, AD_ImpValRule_Cond_ID, trxName);
      /** if (AD_ImpValRule_Cond_ID == 0)
        {
			setAD_ImpVal_Rule_ID (0);
			setAD_ImpValRule_Cond_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_ImpValRule_Cond (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ImpValRule_Cond[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Import Validation Rule Condition.
		@param AD_ImpValRule_Cond_ID Import Validation Rule Condition	  */
	public void setAD_ImpValRule_Cond_ID (int AD_ImpValRule_Cond_ID)
	{
		if (AD_ImpValRule_Cond_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ImpValRule_Cond_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ImpValRule_Cond_ID, Integer.valueOf(AD_ImpValRule_Cond_ID));
	}

	/** Get Import Validation Rule Condition.
		@return Import Validation Rule Condition	  */
	public int getAD_ImpValRule_Cond_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ImpValRule_Cond_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** AndOr AD_Reference_ID=204 */
	public static final int ANDOR_AD_Reference_ID=204;
	/** And = A */
	public static final String ANDOR_And = "A";
	/** Or = O */
	public static final String ANDOR_Or = "O";
	/** Set And/Or.
		@param AndOr 
		Logical operation: AND or OR
	  */
	public void setAndOr (String AndOr)
	{

		set_Value (COLUMNNAME_AndOr, AndOr);
	}

	/** Get And/Or.
		@return Logical operation: AND or OR
	  */
	public String getAndOr () 
	{
		return (String)get_Value(COLUMNNAME_AndOr);
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

	/** LeftBracket AD_Reference_ID=1000063 */
	public static final int LEFTBRACKET_AD_Reference_ID=1000063;
	/** 1 = ( */
	public static final String LEFTBRACKET_1 = "(";
	/** 2 = (( */
	public static final String LEFTBRACKET_2 = "((";
	/** 3 = ((( */
	public static final String LEFTBRACKET_3 = "(((";
	/** Set Left Bracket.
		@param LeftBracket 
		The left bracket(s) to apply to the expression
	  */
	public void setLeftBracket (String LeftBracket)
	{

		set_Value (COLUMNNAME_LeftBracket, LeftBracket);
	}

	/** Get Left Bracket.
		@return The left bracket(s) to apply to the expression
	  */
	public String getLeftBracket () 
	{
		return (String)get_Value(COLUMNNAME_LeftBracket);
	}

	/** Operator AD_Reference_ID=1000065 */
	public static final int OPERATOR_AD_Reference_ID=1000065;
	/** = = = */
	public static final String OPERATOR_Eq = "=";
	/** != = != */
	public static final String OPERATOR_NotEq = "!=";
	/** ~ = ~ */
	public static final String OPERATOR_Like = "~";
	/** !~ = !~ */
	public static final String OPERATOR_NotLike = "!~";
	/** > = > */
	public static final String OPERATOR_Gt = ">";
	/** >= = >= */
	public static final String OPERATOR_GtEq = ">=";
	/** < = < */
	public static final String OPERATOR_Le = "<";
	/** <= = <= */
	public static final String OPERATOR_LeEq = "<=";
	/** >-< = >-< */
	public static final String OPERATOR__ = ">-<";
	/** Set Operator.
		@param Operator 
		Operators used in logic expressions ("=", "<", ...)
	  */
	public void setOperator (String Operator)
	{

		set_Value (COLUMNNAME_Operator, Operator);
	}

	/** Get Operator.
		@return Operators used in logic expressions ("=", "<", ...)
	  */
	public String getOperator () 
	{
		return (String)get_Value(COLUMNNAME_Operator);
	}

	/** RightBracket AD_Reference_ID=1000064 */
	public static final int RIGHTBRACKET_AD_Reference_ID=1000064;
	/** 1 = ) */
	public static final String RIGHTBRACKET_1 = ")";
	/** 2 = )) */
	public static final String RIGHTBRACKET_2 = "))";
	/** 3 = ))) */
	public static final String RIGHTBRACKET_3 = ")))";
	/** Set Right Bracket.
		@param RightBracket Right Bracket	  */
	public void setRightBracket (String RightBracket)
	{

		set_Value (COLUMNNAME_RightBracket, RightBracket);
	}

	/** Get Right Bracket.
		@return Right Bracket	  */
	public String getRightBracket () 
	{
		return (String)get_Value(COLUMNNAME_RightBracket);
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

	/** Set Value 1.
		@param Value1 
		Value or value from is comparator is between ">-<"
	  */
	public void setValue1 (String Value1)
	{
		set_Value (COLUMNNAME_Value1, Value1);
	}

	/** Get Value 1.
		@return Value or value from is comparator is between ">-<"
	  */
	public String getValue1 () 
	{
		return (String)get_Value(COLUMNNAME_Value1);
	}

	/** Set Value To.
		@param Value2 
		Value To
	  */
	public void setValue2 (String Value2)
	{
		set_Value (COLUMNNAME_Value2, Value2);
	}

	/** Get Value To.
		@return Value To
	  */
	public String getValue2 () 
	{
		return (String)get_Value(COLUMNNAME_Value2);
	}
}