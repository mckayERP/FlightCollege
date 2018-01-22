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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for CT_CompLifeCycleModel
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_CT_CompLifeCycleModel extends PO implements I_CT_CompLifeCycleModel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180103L;

    /** Standard Constructor */
    public X_CT_CompLifeCycleModel (Properties ctx, int CT_CompLifeCycleModel_ID, String trxName)
    {
      super (ctx, CT_CompLifeCycleModel_ID, trxName);
      /** if (CT_CompLifeCycleModel_ID == 0)
        {
			setCT_CompLifeCycleModel_ID (0);
			setLifeUsageSource (null);
        } */
    }

    /** Load Constructor */
    public X_CT_CompLifeCycleModel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CT_CompLifeCycleModel[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Component Life Cycle Model.
		@param CT_CompLifeCycleModel_ID 
		The component life cycle model to use when creating new components for this product.
	  */
	public void setCT_CompLifeCycleModel_ID (int CT_CompLifeCycleModel_ID)
	{
		if (CT_CompLifeCycleModel_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_CompLifeCycleModel_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_CompLifeCycleModel_ID, Integer.valueOf(CT_CompLifeCycleModel_ID));
	}

	/** Get Component Life Cycle Model.
		@return The component life cycle model to use when creating new components for this product.
	  */
	public int getCT_CompLifeCycleModel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_CompLifeCycleModel_ID);
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
	/** Count of Installations = Count */
	public static final String LIFEUSAGESOURCE_CountOfInstallations = "Count";
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

	public org.compiere.model.I_M_Product_Group getM_Product_Group() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Group)MTable.get(getCtx(), org.compiere.model.I_M_Product_Group.Table_Name)
			.getPO(getM_Product_Group_ID(), get_TrxName());	}

	/** Set Product Group.
		@param M_Product_Group_ID 
		Group of a Product
	  */
	public void setM_Product_Group_ID (int M_Product_Group_ID)
	{
		if (M_Product_Group_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Group_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Group_ID, Integer.valueOf(M_Product_Group_ID));
	}

	/** Get Product Group.
		@return Group of a Product
	  */
	public int getM_Product_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Group_ID);
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