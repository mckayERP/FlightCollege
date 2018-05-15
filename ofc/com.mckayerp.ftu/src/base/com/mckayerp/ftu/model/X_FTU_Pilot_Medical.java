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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for FTU_Pilot_Medical
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Pilot_Medical extends PO implements I_FTU_Pilot_Medical, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_Pilot_Medical (Properties ctx, int FTU_Pilot_Medical_ID, String trxName)
    {
      super (ctx, FTU_Pilot_Medical_ID, trxName);
      /** if (FTU_Pilot_Medical_ID == 0)
        {
			setFTU_Pilot_Medical_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Pilot_Medical (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Pilot_Medical[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public com.mckayerp.ftu.model.I_FTU_AVDoc getFTU_AVDoc() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_AVDoc)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_AVDoc.Table_Name)
			.getPO(getFTU_AVDoc_ID(), get_TrxName());	}

	/** Set Aviation Document.
		@param FTU_AVDoc_ID Aviation Document	  */
	public void setFTU_AVDoc_ID (int FTU_AVDoc_ID)
	{
		if (FTU_AVDoc_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_AVDoc_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_AVDoc_ID, Integer.valueOf(FTU_AVDoc_ID));
	}

	/** Get Aviation Document.
		@return Aviation Document	  */
	public int getFTU_AVDoc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_AVDoc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Certifcation ID.
		@param FTU_Pilot_Medical_ID Medical Certifcation ID	  */
	public void setFTU_Pilot_Medical_ID (int FTU_Pilot_Medical_ID)
	{
		if (FTU_Pilot_Medical_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Pilot_Medical_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Pilot_Medical_ID, Integer.valueOf(FTU_Pilot_Medical_ID));
	}

	/** Get Medical Certifcation ID.
		@return Medical Certifcation ID	  */
	public int getFTU_Pilot_Medical_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Pilot_Medical_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date of Issue.
		@param IssueDate 
		The date the document was issued.
	  */
	public void setIssueDate (Timestamp IssueDate)
	{
		set_Value (COLUMNNAME_IssueDate, IssueDate);
	}

	/** Get Date of Issue.
		@return The date the document was issued.
	  */
	public Timestamp getIssueDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_IssueDate);
	}

	/** Set Remarks.
		@param LIC_Remarks 
		Remarks on the Licence or Rating
	  */
	public void setLIC_Remarks (String LIC_Remarks)
	{
		set_Value (COLUMNNAME_LIC_Remarks, LIC_Remarks);
	}

	/** Get Remarks.
		@return Remarks on the Licence or Rating
	  */
	public String getLIC_Remarks () 
	{
		return (String)get_Value(COLUMNNAME_LIC_Remarks);
	}

	/** MedCategory AD_Reference_ID=1000024 */
	public static final int MEDCATEGORY_AD_Reference_ID=1000024;
	/** Cat 1 = C1 */
	public static final String MEDCATEGORY_Cat1 = "C1";
	/** Cat 2 = C2 */
	public static final String MEDCATEGORY_Cat2 = "C2";
	/** Cat 3 = C3 */
	public static final String MEDCATEGORY_Cat3 = "C3";
	/** Cat 4 = C4 */
	public static final String MEDCATEGORY_Cat4 = "C4";
	/** Set Medical Category.
		@param MedCategory 
		Medical Category as defined by Transport Canada
	  */
	public void setMedCategory (String MedCategory)
	{

		set_Value (COLUMNNAME_MedCategory, MedCategory);
	}

	/** Get Medical Category.
		@return Medical Category as defined by Transport Canada
	  */
	public String getMedCategory () 
	{
		return (String)get_Value(COLUMNNAME_MedCategory);
	}

	/** Set Examination Date.
		@param MedicalExam_Date 
		The date of the medical exam
	  */
	public void setMedicalExam_Date (Timestamp MedicalExam_Date)
	{
		set_Value (COLUMNNAME_MedicalExam_Date, MedicalExam_Date);
	}

	/** Get Examination Date.
		@return The date of the medical exam
	  */
	public Timestamp getMedicalExam_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MedicalExam_Date);
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