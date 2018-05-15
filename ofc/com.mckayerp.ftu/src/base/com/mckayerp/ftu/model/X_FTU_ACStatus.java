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

/** Generated Model for FTU_ACStatus
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_ACStatus extends PO implements I_FTU_ACStatus, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_ACStatus (Properties ctx, int FTU_ACStatus_ID, String trxName)
    {
      super (ctx, FTU_ACStatus_ID, trxName);
      /** if (FTU_ACStatus_ID == 0)
        {
			setFTU_ACStatus_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_ACStatus (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_FTU_ACStatus[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Aircraft Status.
		@param ACStatus Aircraft Status	  */
	public void setACStatus (Object ACStatus)
	{
		set_Value (COLUMNNAME_ACStatus, ACStatus);
	}

	/** Get Aircraft Status.
		@return Aircraft Status	  */
	public Object getACStatus () 
	{
				return get_Value(COLUMNNAME_ACStatus);
	}

	/** Set FTU_ACStatus ID.
		@param FTU_ACStatus_ID FTU_ACStatus ID	  */
	public void setFTU_ACStatus_ID (int FTU_ACStatus_ID)
	{
		if (FTU_ACStatus_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_ACStatus_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_ACStatus_ID, Integer.valueOf(FTU_ACStatus_ID));
	}

	/** Get FTU_ACStatus ID.
		@return FTU_ACStatus ID	  */
	public int getFTU_ACStatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ACStatus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}