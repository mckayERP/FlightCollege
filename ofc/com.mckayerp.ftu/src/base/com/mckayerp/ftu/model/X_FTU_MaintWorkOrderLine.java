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

/** Generated Model for FTU_MaintWorkOrderLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintWorkOrderLine extends PO implements I_FTU_MaintWorkOrderLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161023L;

    /** Standard Constructor */
    public X_FTU_MaintWorkOrderLine (Properties ctx, int FTU_MaintWorkOrderLine_ID, String trxName)
    {
      super (ctx, FTU_MaintWorkOrderLine_ID, trxName);
      /** if (FTU_MaintWorkOrderLine_ID == 0)
        {
			setFTU_MaintWorkOrderLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintWorkOrderLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintWorkOrderLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public com.mckayerp.ftu.model.I_FTU_DefectLog getFTU_DefectLog() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_DefectLog)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_DefectLog.Table_Name)
			.getPO(getFTU_DefectLog_ID(), get_TrxName());	}

	/** Set Defect.
		@param FTU_DefectLog_ID Defect	  */
	public void setFTU_DefectLog_ID (int FTU_DefectLog_ID)
	{
		if (FTU_DefectLog_ID < 1) 
			set_Value (COLUMNNAME_FTU_DefectLog_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_DefectLog_ID, Integer.valueOf(FTU_DefectLog_ID));
	}

	/** Get Defect.
		@return Defect	  */
	public int getFTU_DefectLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_DefectLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintWorkOrder getFTU_MaintWorkOrder() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintWorkOrder)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintWorkOrder.Table_Name)
			.getPO(getFTU_MaintWorkOrder_ID(), get_TrxName());	}

	/** Set Maintenance Work Order ID.
		@param FTU_MaintWorkOrder_ID Maintenance Work Order ID	  */
	public void setFTU_MaintWorkOrder_ID (int FTU_MaintWorkOrder_ID)
	{
		if (FTU_MaintWorkOrder_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrder_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrder_ID, Integer.valueOf(FTU_MaintWorkOrder_ID));
	}

	/** Get Maintenance Work Order ID.
		@return Maintenance Work Order ID	  */
	public int getFTU_MaintWorkOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWorkOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maintenance Work Order Line ID.
		@param FTU_MaintWorkOrderLine_ID Maintenance Work Order Line ID	  */
	public void setFTU_MaintWorkOrderLine_ID (int FTU_MaintWorkOrderLine_ID)
	{
		if (FTU_MaintWorkOrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrderLine_ID, Integer.valueOf(FTU_MaintWorkOrderLine_ID));
	}

	/** Get Maintenance Work Order Line ID.
		@return Maintenance Work Order Line ID	  */
	public int getFTU_MaintWorkOrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWorkOrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}