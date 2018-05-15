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

/** Generated Model for CT_ComponentData
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_CT_ComponentData extends PO implements I_CT_ComponentData, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_CT_ComponentData (Properties ctx, int CT_ComponentData_ID, String trxName)
    {
      super (ctx, CT_ComponentData_ID, trxName);
      /** if (CT_ComponentData_ID == 0)
        {
			setCT_ComponentData_ID (0);
        } */
    }

    /** Load Constructor */
    public X_CT_ComponentData (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CT_ComponentData[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public com.mckayerp.model.I_CT_CompLifeCycleModel getCT_CompLifeCycleModel() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_CompLifeCycleModel)MTable.get(getCtx(), com.mckayerp.model.I_CT_CompLifeCycleModel.Table_Name)
			.getPO(getCT_CompLifeCycleModel_ID(), get_TrxName());	}

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

	/** Set Component Data ID.
		@param CT_ComponentData_ID Component Data ID	  */
	public void setCT_ComponentData_ID (int CT_ComponentData_ID)
	{
		if (CT_ComponentData_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_ComponentData_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_ComponentData_ID, Integer.valueOf(CT_ComponentData_ID));
	}

	/** Get Component Data ID.
		@return Component Data ID	  */
	public int getCT_ComponentData_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_ComponentData_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.model.I_CT_DataSet getCT_DataSet() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_DataSet)MTable.get(getCtx(), com.mckayerp.model.I_CT_DataSet.Table_Name)
			.getPO(getCT_DataSet_ID(), get_TrxName());	}

	/** Set Data Set.
		@param CT_DataSet_ID 
		A definition of a set of data.
	  */
	public void setCT_DataSet_ID (int CT_DataSet_ID)
	{
		if (CT_DataSet_ID < 1) 
			set_Value (COLUMNNAME_CT_DataSet_ID, null);
		else 
			set_Value (COLUMNNAME_CT_DataSet_ID, Integer.valueOf(CT_DataSet_ID));
	}

	/** Get Data Set.
		@return A definition of a set of data.
	  */
	public int getCT_DataSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}