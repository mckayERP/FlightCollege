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

/** Generated Model for FTU_ComponentBOM
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_ComponentBOM extends PO implements I_FTU_ComponentBOM, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170423L;

    /** Standard Constructor */
    public X_FTU_ComponentBOM (Properties ctx, int FTU_ComponentBOM_ID, String trxName)
    {
      super (ctx, FTU_ComponentBOM_ID, trxName);
      /** if (FTU_ComponentBOM_ID == 0)
        {
			setFTU_Component_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_ComponentBOM (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_ComponentBOM[")
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

	public com.mckayerp.ftu.model.I_FTU_Component getFTU_Component() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Component)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Component.Table_Name)
			.getPO(getFTU_Component_ID(), get_TrxName());	}

	/** Set Component.
		@param FTU_Component_ID 
		A component of an assembly or asset.
	  */
	public void setFTU_Component_ID (int FTU_Component_ID)
	{
		if (FTU_Component_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Component_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Component_ID, Integer.valueOf(FTU_Component_ID));
	}

	/** Get Component.
		@return A component of an assembly or asset.
	  */
	public int getFTU_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Component BOM.
		@param FTU_ComponentBOM_ID 
		The list of subcomponents in this assembly
	  */
	public void setFTU_ComponentBOM_ID (int FTU_ComponentBOM_ID)
	{
		if (FTU_ComponentBOM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_ComponentBOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_ComponentBOM_ID, Integer.valueOf(FTU_ComponentBOM_ID));
	}

	/** Get Component BOM.
		@return The list of subcomponents in this assembly
	  */
	public int getFTU_ComponentBOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ComponentBOM_ID);
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

	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Product_BOM)MTable.get(getCtx(), org.eevolution.model.I_PP_Product_BOM.Table_Name)
			.getPO(getPP_Product_BOM_ID(), get_TrxName());	}

	/** Set BOM & Formula.
		@param PP_Product_BOM_ID 
		BOM & Formula
	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID)
	{
		if (PP_Product_BOM_ID < 1) 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, Integer.valueOf(PP_Product_BOM_ID));
	}

	/** Get BOM & Formula.
		@return BOM & Formula
	  */
	public int getPP_Product_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Product_BOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}