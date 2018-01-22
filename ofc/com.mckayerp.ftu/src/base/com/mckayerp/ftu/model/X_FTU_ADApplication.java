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

/** Generated Model for FTU_ADApplication
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_ADApplication extends PO implements I_FTU_ADApplication, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180107L;

    /** Standard Constructor */
    public X_FTU_ADApplication (Properties ctx, int FTU_ADApplication_ID, String trxName)
    {
      super (ctx, FTU_ADApplication_ID, trxName);
      /** if (FTU_ADApplication_ID == 0)
        {
			setFTU_ADApplication_ID (0);
			setFTU_AirworthinessDirective_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_ADApplication (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_ADApplication[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AD Application ID.
		@param FTU_ADApplication_ID AD Application ID	  */
	public void setFTU_ADApplication_ID (int FTU_ADApplication_ID)
	{
		if (FTU_ADApplication_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_ADApplication_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_ADApplication_ID, Integer.valueOf(FTU_ADApplication_ID));
	}

	/** Get AD Application ID.
		@return AD Application ID	  */
	public int getFTU_ADApplication_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ADApplication_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_AirworthinessDirective getFTU_AirworthinessDirective() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_AirworthinessDirective)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_AirworthinessDirective.Table_Name)
			.getPO(getFTU_AirworthinessDirective_ID(), get_TrxName());	}

	/** Set Airworthiness Directive.
		@param FTU_AirworthinessDirective_ID Airworthiness Directive	  */
	public void setFTU_AirworthinessDirective_ID (int FTU_AirworthinessDirective_ID)
	{
		if (FTU_AirworthinessDirective_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_AirworthinessDirective_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_AirworthinessDirective_ID, Integer.valueOf(FTU_AirworthinessDirective_ID));
	}

	/** Get Airworthiness Directive.
		@return Airworthiness Directive	  */
	public int getFTU_AirworthinessDirective_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_AirworthinessDirective_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CAWIS Applicability.
		@param FTU_CAWIS_Applicability 
		The applicability statement on the CAWIS website
	  */
	public void setFTU_CAWIS_Applicability (String FTU_CAWIS_Applicability)
	{
		set_Value (COLUMNNAME_FTU_CAWIS_Applicability, FTU_CAWIS_Applicability);
	}

	/** Get CAWIS Applicability.
		@return The applicability statement on the CAWIS website
	  */
	public String getFTU_CAWIS_Applicability () 
	{
		return (String)get_Value(COLUMNNAME_FTU_CAWIS_Applicability);
	}

	/** Set AD Applies.
		@param FTU_IsADApplies 
		Does the AD apply to this component?
	  */
	public void setFTU_IsADApplies (boolean FTU_IsADApplies)
	{
		set_Value (COLUMNNAME_FTU_IsADApplies, Boolean.valueOf(FTU_IsADApplies));
	}

	/** Get AD Applies.
		@return Does the AD apply to this component?
	  */
	public boolean isFTU_IsADApplies () 
	{
		Object oo = get_Value(COLUMNNAME_FTU_IsADApplies);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}