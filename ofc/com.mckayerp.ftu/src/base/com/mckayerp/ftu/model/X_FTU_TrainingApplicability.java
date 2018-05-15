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

/** Generated Model for FTU_TrainingApplicability
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_TrainingApplicability extends PO implements I_FTU_TrainingApplicability, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_TrainingApplicability (Properties ctx, int FTU_TrainingApplicability_ID, String trxName)
    {
      super (ctx, FTU_TrainingApplicability_ID, trxName);
      /** if (FTU_TrainingApplicability_ID == 0)
        {
			setFTU_TrainingApplicability_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_TrainingApplicability (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_TrainingApplicability[")
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

	public org.compiere.model.I_C_JobCategory getC_JobCategory() throws RuntimeException
    {
		return (org.compiere.model.I_C_JobCategory)MTable.get(getCtx(), org.compiere.model.I_C_JobCategory.Table_Name)
			.getPO(getC_JobCategory_ID(), get_TrxName());	}

	/** Set Position Category.
		@param C_JobCategory_ID 
		Job Position Category
	  */
	public void setC_JobCategory_ID (int C_JobCategory_ID)
	{
		if (C_JobCategory_ID < 1) 
			set_Value (COLUMNNAME_C_JobCategory_ID, null);
		else 
			set_Value (COLUMNNAME_C_JobCategory_ID, Integer.valueOf(C_JobCategory_ID));
	}

	/** Get Position Category.
		@return Job Position Category
	  */
	public int getC_JobCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_JobCategory_ID);
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

	public com.mckayerp.ftu.model.I_FTU_Training getFTU_Training() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Training)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Training.Table_Name)
			.getPO(getFTU_Training_ID(), get_TrxName());	}

	/** Set Training ID.
		@param FTU_Training_ID Training ID	  */
	public void setFTU_Training_ID (int FTU_Training_ID)
	{
		if (FTU_Training_ID < 1) 
			set_Value (COLUMNNAME_FTU_Training_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Training_ID, Integer.valueOf(FTU_Training_ID));
	}

	/** Get Training ID.
		@return Training ID	  */
	public int getFTU_Training_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Training_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Training Applicability ID.
		@param FTU_TrainingApplicability_ID Training Applicability ID	  */
	public void setFTU_TrainingApplicability_ID (int FTU_TrainingApplicability_ID)
	{
		if (FTU_TrainingApplicability_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_TrainingApplicability_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_TrainingApplicability_ID, Integer.valueOf(FTU_TrainingApplicability_ID));
	}

	/** Get Training Applicability ID.
		@return Training Applicability ID	  */
	public int getFTU_TrainingApplicability_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_TrainingApplicability_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}
}