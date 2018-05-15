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

/** Generated Model for CT_DataSetInstance
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_CT_DataSetInstance extends PO implements I_CT_DataSetInstance, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_CT_DataSetInstance (Properties ctx, int CT_DataSetInstance_ID, String trxName)
    {
      super (ctx, CT_DataSetInstance_ID, trxName);
      /** if (CT_DataSetInstance_ID == 0)
        {
			setCT_DataSet_ID (0);
			setCT_DataSetInstance_ID (0);
			setOverhaulCount (0);
        } */
    }

    /** Load Constructor */
    public X_CT_DataSetInstance (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CT_DataSetInstance[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public com.mckayerp.model.I_CT_Component getCT_Component() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getCT_Component_ID(), get_TrxName());	}

	/** Set Component.
		@param CT_Component_ID 
		A component of an assembly or asset.
	  */
	public void setCT_Component_ID (int CT_Component_ID)
	{
		if (CT_Component_ID < 1) 
			set_Value (COLUMNNAME_CT_Component_ID, null);
		else 
			set_Value (COLUMNNAME_CT_Component_ID, Integer.valueOf(CT_Component_ID));
	}

	/** Get Component.
		@return A component of an assembly or asset.
	  */
	public int getCT_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Component Life.
		@param CT_ComponentLifeAtAction 
		The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public void setCT_ComponentLifeAtAction (BigDecimal CT_ComponentLifeAtAction)
	{
		set_Value (COLUMNNAME_CT_ComponentLifeAtAction, CT_ComponentLifeAtAction);
	}

	/** Get Component Life.
		@return The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public BigDecimal getCT_ComponentLifeAtAction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CT_ComponentLifeAtAction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			set_ValueNoCheck (COLUMNNAME_CT_DataSet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_DataSet_ID, Integer.valueOf(CT_DataSet_ID));
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

	/** Set Data Set Instance ID.
		@param CT_DataSetInstance_ID Data Set Instance ID	  */
	public void setCT_DataSetInstance_ID (int CT_DataSetInstance_ID)
	{
		if (CT_DataSetInstance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_DataSetInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_DataSetInstance_ID, Integer.valueOf(CT_DataSetInstance_ID));
	}

	/** Get Data Set Instance ID.
		@return Data Set Instance ID	  */
	public int getCT_DataSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Root Component Life.
		@param CT_RootLifeAtAction 
		The Life of the Root Component at the time of the action.
	  */
	public void setCT_RootLifeAtAction (BigDecimal CT_RootLifeAtAction)
	{
		set_Value (COLUMNNAME_CT_RootLifeAtAction, CT_RootLifeAtAction);
	}

	/** Get Root Component Life.
		@return The Life of the Root Component at the time of the action.
	  */
	public BigDecimal getCT_RootLifeAtAction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CT_RootLifeAtAction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Date Recorded.
		@param DateRecorded Date Recorded	  */
	public void setDateRecorded (Timestamp DateRecorded)
	{
		set_Value (COLUMNNAME_DateRecorded, DateRecorded);
	}

	/** Get Date Recorded.
		@return Date Recorded	  */
	public Timestamp getDateRecorded () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateRecorded);
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

	/** Set Complete.
		@param IsComplete 
		It is complete
	  */
	public void setIsComplete (boolean IsComplete)
	{
		set_Value (COLUMNNAME_IsComplete, Boolean.valueOf(IsComplete));
	}

	/** Get Complete.
		@return It is complete
	  */
	public boolean isComplete () 
	{
		Object oo = get_Value(COLUMNNAME_IsComplete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Overhaul Count.
		@param OverhaulCount 
		The number of life cycles completed or underway.
	  */
	public void setOverhaulCount (int OverhaulCount)
	{
		set_ValueNoCheck (COLUMNNAME_OverhaulCount, Integer.valueOf(OverhaulCount));
	}

	/** Get Overhaul Count.
		@return The number of life cycles completed or underway.
	  */
	public int getOverhaulCount () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OverhaulCount);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.model.I_CT_Component getRoot_Component() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getRoot_Component_ID(), get_TrxName());	}

	/** Set Root Component.
		@param Root_Component_ID 
		The Root Component of the component BOM tree.
	  */
	public void setRoot_Component_ID (int Root_Component_ID)
	{
		if (Root_Component_ID < 1) 
			set_Value (COLUMNNAME_Root_Component_ID, null);
		else 
			set_Value (COLUMNNAME_Root_Component_ID, Integer.valueOf(Root_Component_ID));
	}

	/** Get Root Component.
		@return The Root Component of the component BOM tree.
	  */
	public int getRoot_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Root_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}