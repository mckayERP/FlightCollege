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

/** Generated Model for FTU_ADApplicability
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_ADApplicability extends PO implements I_FTU_ADApplicability, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170414L;

    /** Standard Constructor */
    public X_FTU_ADApplicability (Properties ctx, int FTU_ADApplicability_ID, String trxName)
    {
      super (ctx, FTU_ADApplicability_ID, trxName);
      /** if (FTU_ADApplicability_ID == 0)
        {
			setFTU_ADApplicability_ID (0);
			setFTU_ADApplication_ID (0);
			setFTU_Component_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_ADApplicability (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_ADApplicability[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AD Applicability ID.
		@param FTU_ADApplicability_ID AD Applicability ID	  */
	public void setFTU_ADApplicability_ID (int FTU_ADApplicability_ID)
	{
		if (FTU_ADApplicability_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_ADApplicability_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_ADApplicability_ID, Integer.valueOf(FTU_ADApplicability_ID));
	}

	/** Get AD Applicability ID.
		@return AD Applicability ID	  */
	public int getFTU_ADApplicability_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ADApplicability_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_ADApplication getFTU_ADApplication() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_ADApplication)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_ADApplication.Table_Name)
			.getPO(getFTU_ADApplication_ID(), get_TrxName());	}

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
}