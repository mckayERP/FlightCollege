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

/** Generated Model for FTU_AVDoc
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_AVDoc extends PO implements I_FTU_AVDoc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160808L;

    /** Standard Constructor */
    public X_FTU_AVDoc (Properties ctx, int FTU_AVDoc_ID, String trxName)
    {
      super (ctx, FTU_AVDoc_ID, trxName);
      /** if (FTU_AVDoc_ID == 0)
        {
			setAVDOC_BookletNo (null);
			setAVDOC_FileNo (null);
			setC_BPartner_ID (0);
			setFTU_AVDoc_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_AVDoc (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_AVDoc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Booklet Number.
		@param AVDOC_BookletNo 
		Aviation Document Booklet Number
	  */
	public void setAVDOC_BookletNo (String AVDOC_BookletNo)
	{
		set_Value (COLUMNNAME_AVDOC_BookletNo, AVDOC_BookletNo);
	}

	/** Get Booklet Number.
		@return Aviation Document Booklet Number
	  */
	public String getAVDOC_BookletNo () 
	{
		return (String)get_Value(COLUMNNAME_AVDOC_BookletNo);
	}

	/** Set Date of Expiry.
		@param AVDOC_Date_Expiry Date of Expiry	  */
	public void setAVDOC_Date_Expiry (Timestamp AVDOC_Date_Expiry)
	{
		set_Value (COLUMNNAME_AVDOC_Date_Expiry, AVDOC_Date_Expiry);
	}

	/** Get Date of Expiry.
		@return Date of Expiry	  */
	public Timestamp getAVDOC_Date_Expiry () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AVDOC_Date_Expiry);
	}

	/** Set Date Issued.
		@param AVDOC_Date_Issued Date Issued	  */
	public void setAVDOC_Date_Issued (Timestamp AVDOC_Date_Issued)
	{
		set_Value (COLUMNNAME_AVDOC_Date_Issued, AVDOC_Date_Issued);
	}

	/** Get Date Issued.
		@return Date Issued	  */
	public Timestamp getAVDOC_Date_Issued () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AVDOC_Date_Issued);
	}

	/** Set File Number.
		@param AVDOC_FileNo 
		Aviation Document File Number
	  */
	public void setAVDOC_FileNo (String AVDOC_FileNo)
	{
		set_Value (COLUMNNAME_AVDOC_FileNo, AVDOC_FileNo);
	}

	/** Get File Number.
		@return Aviation Document File Number
	  */
	public String getAVDOC_FileNo () 
	{
		return (String)get_Value(COLUMNNAME_AVDOC_FileNo);
	}

	/** Set Date of Birth.
		@param BirthDate 
		Date of Birth
	  */
	public void setBirthDate (Timestamp BirthDate)
	{
		set_Value (COLUMNNAME_BirthDate, BirthDate);
	}

	/** Get Date of Birth.
		@return Date of Birth
	  */
	public Timestamp getBirthDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BirthDate);
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_AD_Tab getLicense_Tab() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Tab)MTable.get(getCtx(), org.compiere.model.I_AD_Tab.Table_Name)
			.getPO(getLicense_Tab_ID(), get_TrxName());	}

	/** Set License Info Tab.
		@param License_Tab_ID 
		License information
	  */
	public void setLicense_Tab_ID (int License_Tab_ID)
	{
		if (License_Tab_ID < 1) 
			set_Value (COLUMNNAME_License_Tab_ID, null);
		else 
			set_Value (COLUMNNAME_License_Tab_ID, Integer.valueOf(License_Tab_ID));
	}

	/** Get License Info Tab.
		@return License information
	  */
	public int getLicense_Tab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_License_Tab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Tab getMedical_Tab() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Tab)MTable.get(getCtx(), org.compiere.model.I_AD_Tab.Table_Name)
			.getPO(getMedical_Tab_ID(), get_TrxName());	}

	/** Set Medical Info Tab.
		@param Medical_Tab_ID 
		Medical Information
	  */
	public void setMedical_Tab_ID (int Medical_Tab_ID)
	{
		if (Medical_Tab_ID < 1) 
			set_Value (COLUMNNAME_Medical_Tab_ID, null);
		else 
			set_Value (COLUMNNAME_Medical_Tab_ID, Integer.valueOf(Medical_Tab_ID));
	}

	/** Get Medical Info Tab.
		@return Medical Information
	  */
	public int getMedical_Tab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Medical_Tab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}