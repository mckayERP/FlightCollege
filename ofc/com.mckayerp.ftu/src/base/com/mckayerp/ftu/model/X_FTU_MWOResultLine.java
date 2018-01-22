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

/** Generated Model for FTU_MWOResultLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MWOResultLine extends PO implements I_FTU_MWOResultLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180107L;

    /** Standard Constructor */
    public X_FTU_MWOResultLine (Properties ctx, int FTU_MWOResultLine_ID, String trxName)
    {
      super (ctx, FTU_MWOResultLine_ID, trxName);
      /** if (FTU_MWOResultLine_ID == 0)
        {
			setFTU_MaintWOResult_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_MWOResultLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MWOResultLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public com.mckayerp.ftu.model.I_FTU_MaintWOResult getFTU_MaintWOResult() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintWOResult)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintWOResult.Table_Name)
			.getPO(getFTU_MaintWOResult_ID(), get_TrxName());	}

	/** Set Maintenance Work Order Result ID.
		@param FTU_MaintWOResult_ID Maintenance Work Order Result ID	  */
	public void setFTU_MaintWOResult_ID (int FTU_MaintWOResult_ID)
	{
		if (FTU_MaintWOResult_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResult_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResult_ID, Integer.valueOf(FTU_MaintWOResult_ID));
	}

	/** Get Maintenance Work Order Result ID.
		@return Maintenance Work Order Result ID	  */
	public int getFTU_MaintWOResult_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWOResult_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}