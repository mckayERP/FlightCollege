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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for FTU_Training_Record
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_Training_Record extends PO implements I_FTU_Training_Record, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160222L;

    /** Standard Constructor */
    public X_FTU_Training_Record (Properties ctx, int FTU_Training_Record_ID, String trxName)
    {
      super (ctx, FTU_Training_Record_ID, trxName);
      /** if (FTU_Training_Record_ID == 0)
        {
			setFTU_Training_Record_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_Training_Record (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Training_Record[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Personnel Training Record ID.
		@param FTU_Training_Record_ID Personnel Training Record ID	  */
	public void setFTU_Training_Record_ID (int FTU_Training_Record_ID)
	{
		if (FTU_Training_Record_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Training_Record_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Training_Record_ID, Integer.valueOf(FTU_Training_Record_ID));
	}

	/** Get Personnel Training Record ID.
		@return Personnel Training Record ID	  */
	public int getFTU_Training_Record_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Training_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_Training_Session getFTU_Training_Session() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Training_Session)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Training_Session.Table_Name)
			.getPO(getFTU_Training_Session_ID(), get_TrxName());	}

	/** Set Training Session ID.
		@param FTU_Training_Session_ID Training Session ID	  */
	public void setFTU_Training_Session_ID (int FTU_Training_Session_ID)
	{
		if (FTU_Training_Session_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Training_Session_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Training_Session_ID, Integer.valueOf(FTU_Training_Session_ID));
	}

	/** Get Training Session ID.
		@return Training Session ID	  */
	public int getFTU_Training_Session_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Training_Session_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TrainingResult AD_Reference_ID=1000054 */
	public static final int TRAININGRESULT_AD_Reference_ID=1000054;
	/** Satisfactory = S */
	public static final String TRAININGRESULT_Satisfactory = "S";
	/** Unsatisfactory = U */
	public static final String TRAININGRESULT_Unsatisfactory = "U";
	/** Set Training Result.
		@param TrainingResult 
		Was the training result satisfactory or unsatisfactory?
	  */
	public void setTrainingResult (String TrainingResult)
	{

		set_Value (COLUMNNAME_TrainingResult, TrainingResult);
	}

	/** Get Training Result.
		@return Was the training result satisfactory or unsatisfactory?
	  */
	public String getTrainingResult () 
	{
		return (String)get_Value(COLUMNNAME_TrainingResult);
	}
}