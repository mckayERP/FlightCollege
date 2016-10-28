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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for I_Flightsheet_Audit_Trail
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_I_Flightsheet_Audit_Trail extends PO implements I_I_Flightsheet_Audit_Trail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161023L;

    /** Standard Constructor */
    public X_I_Flightsheet_Audit_Trail (Properties ctx, int I_Flightsheet_Audit_Trail_ID, String trxName)
    {
      super (ctx, I_Flightsheet_Audit_Trail_ID, trxName);
      /** if (I_Flightsheet_Audit_Trail_ID == 0)
        {
			setI_Flightsheet_Audit_Trail_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_Flightsheet_Audit_Trail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_Flightsheet_Audit_Trail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Action Performed.
		@param ActionPerformed 
		The action performed by the flight sheet user
	  */
	public void setActionPerformed (String ActionPerformed)
	{
		set_Value (COLUMNNAME_ActionPerformed, ActionPerformed);
	}

	/** Get Action Performed.
		@return The action performed by the flight sheet user
	  */
	public String getActionPerformed () 
	{
		return (String)get_Value(COLUMNNAME_ActionPerformed);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getActionPerformed());
    }

	/** Set Audit Reference.
		@param AuditReference 
		The Booking Audit Reference number
	  */
	public void setAuditReference (BigDecimal AuditReference)
	{
		set_Value (COLUMNNAME_AuditReference, AuditReference);
	}

	/** Get Audit Reference.
		@return The Booking Audit Reference number
	  */
	public BigDecimal getAuditReference () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AuditReference);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Client Name.
		@param Flightsheet_Client 
		The Flightsheet client
	  */
	public void setFlightsheet_Client (String Flightsheet_Client)
	{
		set_Value (COLUMNNAME_Flightsheet_Client, Flightsheet_Client);
	}

	/** Get Client Name.
		@return The Flightsheet client
	  */
	public String getFlightsheet_Client () 
	{
		return (String)get_Value(COLUMNNAME_Flightsheet_Client);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Import Flightsheet Audit Trail ID.
		@param I_Flightsheet_Audit_Trail_ID Import Flightsheet Audit Trail ID	  */
	public void setI_Flightsheet_Audit_Trail_ID (int I_Flightsheet_Audit_Trail_ID)
	{
		if (I_Flightsheet_Audit_Trail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_Flightsheet_Audit_Trail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_Flightsheet_Audit_Trail_ID, Integer.valueOf(I_Flightsheet_Audit_Trail_ID));
	}

	/** Get Import Flightsheet Audit Trail ID.
		@return Import Flightsheet Audit Trail ID	  */
	public int getI_Flightsheet_Audit_Trail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_Flightsheet_Audit_Trail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Import Audit Trail Button.
		@param Import_Audit_Trail_B Import Audit Trail Button	  */
	public void setImport_Audit_Trail_B (String Import_Audit_Trail_B)
	{
		set_Value (COLUMNNAME_Import_Audit_Trail_B, Import_Audit_Trail_B);
	}

	/** Get Import Audit Trail Button.
		@return Import Audit Trail Button	  */
	public String getImport_Audit_Trail_B () 
	{
		return (String)get_Value(COLUMNNAME_Import_Audit_Trail_B);
	}

	/** Set Performed At.
		@param PerformedAt Performed At	  */
	public void setPerformedAt (String PerformedAt)
	{
		set_Value (COLUMNNAME_PerformedAt, PerformedAt);
	}

	/** Get Performed At.
		@return Performed At	  */
	public String getPerformedAt () 
	{
		return (String)get_Value(COLUMNNAME_PerformedAt);
	}

	/** Set Year Performed.
		@param PerformedAt_Yr 
		The year the action was performed at.
	  */
	public void setPerformedAt_Yr (int PerformedAt_Yr)
	{
		set_Value (COLUMNNAME_PerformedAt_Yr, Integer.valueOf(PerformedAt_Yr));
	}

	/** Get Year Performed.
		@return The year the action was performed at.
	  */
	public int getPerformedAt_Yr () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PerformedAt_Yr);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Performed By.
		@param PerformedBy 
		The action was performed by this person.
	  */
	public void setPerformedBy (String PerformedBy)
	{
		set_Value (COLUMNNAME_PerformedBy, PerformedBy);
	}

	/** Get Performed By.
		@return The action was performed by this person.
	  */
	public String getPerformedBy () 
	{
		return (String)get_Value(COLUMNNAME_PerformedBy);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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