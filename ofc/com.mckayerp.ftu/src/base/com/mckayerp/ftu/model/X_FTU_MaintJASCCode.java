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

/** Generated Model for FTU_MaintJASCCode
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintJASCCode extends PO implements I_FTU_MaintJASCCode, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180107L;

    /** Standard Constructor */
    public X_FTU_MaintJASCCode (Properties ctx, int FTU_MaintJASCCode_ID, String trxName)
    {
      super (ctx, FTU_MaintJASCCode_ID, trxName);
      /** if (FTU_MaintJASCCode_ID == 0)
        {
			setFTU_MaintJASCCode_ID (0);
			setFTU_MaintJASCHdr_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintJASCCode (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintJASCCode[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_ValueNoCheck (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set JASC Code.
		@param FTU_MaintJASCCode_ID 
		The JASC Code
	  */
	public void setFTU_MaintJASCCode_ID (int FTU_MaintJASCCode_ID)
	{
		if (FTU_MaintJASCCode_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintJASCCode_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintJASCCode_ID, Integer.valueOf(FTU_MaintJASCCode_ID));
	}

	/** Get JASC Code.
		@return The JASC Code
	  */
	public int getFTU_MaintJASCCode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintJASCCode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintJASCHdr getFTU_MaintJASCHdr() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintJASCHdr)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintJASCHdr.Table_Name)
			.getPO(getFTU_MaintJASCHdr_ID(), get_TrxName());	}

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

	/** Set JASC Code.
		@param JASCCode 
		The four digit JASC Code
	  */
	public void setJASCCode (String JASCCode)
	{
		set_Value (COLUMNNAME_JASCCode, JASCCode);
	}

	/** Get JASC Code.
		@return The four digit JASC Code
	  */
	public String getJASCCode () 
	{
		return (String)get_Value(COLUMNNAME_JASCCode);
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