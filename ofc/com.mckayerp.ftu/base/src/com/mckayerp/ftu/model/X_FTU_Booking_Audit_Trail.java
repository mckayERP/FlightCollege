/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for FTU_Booking_Audit_Trail
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_Booking_Audit_Trail extends PO implements I_FTU_Booking_Audit_Trail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160222L;

    /** Standard Constructor */
    public X_FTU_Booking_Audit_Trail (Properties ctx, int FTU_Booking_Audit_Trail_ID, String trxName)
    {
      super (ctx, FTU_Booking_Audit_Trail_ID, trxName);
      /** if (FTU_Booking_Audit_Trail_ID == 0)
        {
			setFTU_Booking_Audit_Trail_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_Booking_Audit_Trail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Booking_Audit_Trail[")
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

	/** Set Assign From.
		@param AssignDateFrom 
		Assign resource from
	  */
	public void setAssignDateFrom (Timestamp AssignDateFrom)
	{
		set_Value (COLUMNNAME_AssignDateFrom, AssignDateFrom);
	}

	/** Get Assign From.
		@return Assign resource from
	  */
	public Timestamp getAssignDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssignDateFrom);
	}

	/** Set Assign To.
		@param AssignDateTo 
		Assign resource until
	  */
	public void setAssignDateTo (Timestamp AssignDateTo)
	{
		set_Value (COLUMNNAME_AssignDateTo, AssignDateTo);
	}

	/** Get Assign To.
		@return Assign resource until
	  */
	public Timestamp getAssignDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssignDateTo);
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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Booking Audit Trail ID.
		@param FTU_Booking_Audit_Trail_ID Booking Audit Trail ID	  */
	public void setFTU_Booking_Audit_Trail_ID (int FTU_Booking_Audit_Trail_ID)
	{
		if (FTU_Booking_Audit_Trail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Booking_Audit_Trail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Booking_Audit_Trail_ID, Integer.valueOf(FTU_Booking_Audit_Trail_ID));
	}

	/** Get Booking Audit Trail ID.
		@return Booking Audit Trail ID	  */
	public int getFTU_Booking_Audit_Trail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Booking_Audit_Trail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException
    {
		return (org.compiere.model.I_S_Resource)MTable.get(getCtx(), org.compiere.model.I_S_Resource.Table_Name)
			.getPO(getS_Resource_ID(), get_TrxName());	}

	/** Set Resource.
		@param S_Resource_ID 
		Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1) 
			set_Value (COLUMNNAME_S_Resource_ID, null);
		else 
			set_Value (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Resource.
		@return Resource
	  */
	public int getS_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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