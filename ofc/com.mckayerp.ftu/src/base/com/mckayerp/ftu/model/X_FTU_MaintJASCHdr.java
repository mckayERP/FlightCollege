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

/** Generated Model for FTU_MaintJASCHdr
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintJASCHdr extends PO implements I_FTU_MaintJASCHdr, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180107L;

    /** Standard Constructor */
    public X_FTU_MaintJASCHdr (Properties ctx, int FTU_MaintJASCHdr_ID, String trxName)
    {
      super (ctx, FTU_MaintJASCHdr_ID, trxName);
      /** if (FTU_MaintJASCHdr_ID == 0)
        {
			setFTU_MaintJASCHdr_ID (0);
			setJASCHeaderCode (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintJASCHdr (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintJASCHdr[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set JASC Code Header.
		@param FTU_MaintJASCHdr_ID 
		The summary group of the JASC code. Typically, the first two digits of the code.
	  */
	public void setFTU_MaintJASCHdr_ID (int FTU_MaintJASCHdr_ID)
	{
		if (FTU_MaintJASCHdr_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintJASCHdr_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintJASCHdr_ID, Integer.valueOf(FTU_MaintJASCHdr_ID));
	}

	/** Get JASC Code Header.
		@return The summary group of the JASC code. Typically, the first two digits of the code.
	  */
	public int getFTU_MaintJASCHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintJASCHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Header Code.
		@param JASCHeaderCode 
		The two digit code of the JASC header - typically the first two digits of the JASC code
	  */
	public void setJASCHeaderCode (String JASCHeaderCode)
	{
		set_ValueNoCheck (COLUMNNAME_JASCHeaderCode, JASCHeaderCode);
	}

	/** Get Header Code.
		@return The two digit code of the JASC header - typically the first two digits of the JASC code
	  */
	public String getJASCHeaderCode () 
	{
		return (String)get_Value(COLUMNNAME_JASCHeaderCode);
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
}