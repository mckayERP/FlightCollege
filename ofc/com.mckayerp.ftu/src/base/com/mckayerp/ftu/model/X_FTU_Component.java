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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for FTU_Component
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Component extends PO implements I_FTU_Component, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170423L;

    /** Standard Constructor */
    public X_FTU_Component (Properties ctx, int FTU_Component_ID, String trxName)
    {
      super (ctx, FTU_Component_ID, trxName);
      /** if (FTU_Component_ID == 0)
        {
			setFTU_Component_ID (0);
			setM_AttributeSetInstance_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_Component (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Component[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

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

	/** Set Is Life Extended.
		@param IsLifeExtended 
		Is the life of this component extended beyound the maximum life limit
	  */
	public void setIsLifeExtended (boolean IsLifeExtended)
	{
		set_Value (COLUMNNAME_IsLifeExtended, Boolean.valueOf(IsLifeExtended));
	}

	/** Get Is Life Extended.
		@return Is the life of this component extended beyound the maximum life limit
	  */
	public boolean isLifeExtended () 
	{
		Object oo = get_Value(COLUMNNAME_IsLifeExtended);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Life Extension Possible.
		@param IsLifeExtensionPossible 
		Can the life be extended beyond the maximum life?
	  */
	public void setIsLifeExtensionPossible (boolean IsLifeExtensionPossible)
	{
		set_Value (COLUMNNAME_IsLifeExtensionPossible, Boolean.valueOf(IsLifeExtensionPossible));
	}

	/** Get Is Life Extension Possible.
		@return Can the life be extended beyond the maximum life?
	  */
	public boolean isLifeExtensionPossible () 
	{
		Object oo = get_Value(COLUMNNAME_IsLifeExtensionPossible);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** LifeUsageSource AD_Reference_ID=53877 */
	public static final int LIFEUSAGESOURCE_AD_Reference_ID=53877;
	/** Inherit from parent = Parent */
	public static final String LIFEUSAGESOURCE_InheritFromParent = "Parent";
	/** Use a query = Query */
	public static final String LIFEUSAGESOURCE_UseAQuery = "Query";
	/** Not tracked = None */
	public static final String LIFEUSAGESOURCE_NotTracked = "None";
	/** Aircraft = Aircraft */
	public static final String LIFEUSAGESOURCE_Aircraft = "Aircraft";
	/** Set Life Usage Source.
		@param LifeUsageSource 
		How the life usage is determined for the component.
	  */
	public void setLifeUsageSource (String LifeUsageSource)
	{

		set_Value (COLUMNNAME_LifeUsageSource, LifeUsageSource);
	}

	/** Get Life Usage Source.
		@return How the life usage is determined for the component.
	  */
	public String getLifeUsageSource () 
	{
		return (String)get_Value(COLUMNNAME_LifeUsageSource);
	}

	public org.compiere.model.I_C_UOM getLifeUsageUOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getLifeUsageUOM_ID(), get_TrxName());	}

	/** Set Life Use UOM.
		@param LifeUsageUOM_ID 
		The unit of measure (UOM) for the life usage.  Example, hours, km.
	  */
	public void setLifeUsageUOM_ID (int LifeUsageUOM_ID)
	{
		if (LifeUsageUOM_ID < 1) 
			set_Value (COLUMNNAME_LifeUsageUOM_ID, null);
		else 
			set_Value (COLUMNNAME_LifeUsageUOM_ID, Integer.valueOf(LifeUsageUOM_ID));
	}

	/** Get Life Use UOM.
		@return The unit of measure (UOM) for the life usage.  Example, hours, km.
	  */
	public int getLifeUsageUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LifeUsageUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life Used.
		@param LifeUsed 
		The life used for this component.
	  */
	public void setLifeUsed (BigDecimal LifeUsed)
	{
		set_Value (COLUMNNAME_LifeUsed, LifeUsed);
	}

	/** Get Life Used.
		@return The life used for this component.
	  */
	public BigDecimal getLifeUsed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LifeUsed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set Max Life Usage.
		@param MaxLifeUsage 
		The maximum life use expected in the life usage units.
	  */
	public void setMaxLifeUsage (BigDecimal MaxLifeUsage)
	{
		set_Value (COLUMNNAME_MaxLifeUsage, MaxLifeUsage);
	}

	/** Get Max Life Usage.
		@return The maximum life use expected in the life usage units.
	  */
	public BigDecimal getMaxLifeUsage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxLifeUsage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}