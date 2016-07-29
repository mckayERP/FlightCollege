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

/** Generated Model for FTU_Flags
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Flags extends PO implements I_FTU_Flags, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160721L;

    /** Standard Constructor */
    public X_FTU_Flags (Properties ctx, int FTU_Flags_ID, String trxName)
    {
      super (ctx, FTU_Flags_ID, trxName);
      /** if (FTU_Flags_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_FTU_Flags (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Flags[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Flag Name.
		@param FlagName 
		The unique name for the flag
	  */
	public void setFlagName (String FlagName)
	{
		set_ValueNoCheck (COLUMNNAME_FlagName, FlagName);
	}

	/** Get Flag Name.
		@return The unique name for the flag
	  */
	public String getFlagName () 
	{
		return (String)get_Value(COLUMNNAME_FlagName);
	}

	/** Set OFC_IsProcessing.
		@param OFC_IsProcessing 
		A boolean.  If true the process is currently running.
	  */
	public void setOFC_IsProcessing (boolean OFC_IsProcessing)
	{
		set_Value (COLUMNNAME_OFC_IsProcessing, Boolean.valueOf(OFC_IsProcessing));
	}

	/** Get OFC_IsProcessing.
		@return A boolean.  If true the process is currently running.
	  */
	public boolean isOFC_IsProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_OFC_IsProcessing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}