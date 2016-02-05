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

/** Generated Model for FTU_License_Type
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_License_Type extends PO implements I_FTU_License_Type, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160131L;

    /** Standard Constructor */
    public X_FTU_License_Type (Properties ctx, int FTU_License_Type_ID, String trxName)
    {
      super (ctx, FTU_License_Type_ID, trxName);
      /** if (FTU_License_Type_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_FTU_License_Type (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_License_Type[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Licenses Type.
		@param FTU_License_Type_ID Licenses Type	  */
	public void setFTU_License_Type_ID (int FTU_License_Type_ID)
	{
		if (FTU_License_Type_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_License_Type_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_License_Type_ID, Integer.valueOf(FTU_License_Type_ID));
	}

	/** Get Licenses Type.
		@return Licenses Type	  */
	public int getFTU_License_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_License_Type_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_Medical_Cat getFTU_Medical_Cat() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Medical_Cat)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Medical_Cat.Table_Name)
			.getPO(getFTU_Medical_Cat_ID(), get_TrxName());	}

	/** Set Medical Category ID.
		@param FTU_Medical_Cat_ID Medical Category ID	  */
	public void setFTU_Medical_Cat_ID (int FTU_Medical_Cat_ID)
	{
		if (FTU_Medical_Cat_ID < 1) 
			set_Value (COLUMNNAME_FTU_Medical_Cat_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Medical_Cat_ID, Integer.valueOf(FTU_Medical_Cat_ID));
	}

	/** Get Medical Category ID.
		@return Medical Category ID	  */
	public int getFTU_Medical_Cat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Medical_Cat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** LicenseType AD_Reference_ID=1000023 */
	public static final int LICENSETYPE_AD_Reference_ID=1000023;
	/** SPP = SPP */
	public static final String LICENSETYPE_SPP = "SPP";
	/** PPU = PPU */
	public static final String LICENSETYPE_PPU = "PPU";
	/** PPG = PPG */
	public static final String LICENSETYPE_PPG = "PPG";
	/** PPRA = PPRA */
	public static final String LICENSETYPE_PPRA = "PPRA";
	/** PLG = PLG */
	public static final String LICENSETYPE_PLG = "PLG";
	/** PLB = PLB */
	public static final String LICENSETYPE_PLB = "PLB";
	/** PPLA = PPLA */
	public static final String LICENSETYPE_PPLA = "PPLA";
	/** PPLH = PPLH */
	public static final String LICENSETYPE_PPLH = "PPLH";
	/** CPLA = CPLA */
	public static final String LICENSETYPE_CPLA = "CPLA";
	/** CPLH = CPLH */
	public static final String LICENSETYPE_CPLH = "CPLH";
	/** ATPLA = ATPLA */
	public static final String LICENSETYPE_ATPLA = "ATPLA";
	/** ATPLH = ATPLH */
	public static final String LICENSETYPE_ATPLH = "ATPLH";
	/** FEL = FEL */
	public static final String LICENSETYPE_FEL = "FEL";
	/** Set License Type.
		@param LicenseType 
		The license, permit or rating type
	  */
	public void setLicenseType (String LicenseType)
	{

		set_Value (COLUMNNAME_LicenseType, LicenseType);
	}

	/** Get License Type.
		@return The license, permit or rating type
	  */
	public String getLicenseType () 
	{
		return (String)get_Value(COLUMNNAME_LicenseType);
	}

	/** Set Validity Break Age.
		@param MedCatValidityBreakAge 
		The age at which the validity period changes - typically 40 years.
	  */
	public void setMedCatValidityBreakAge (int MedCatValidityBreakAge)
	{
		set_Value (COLUMNNAME_MedCatValidityBreakAge, Integer.valueOf(MedCatValidityBreakAge));
	}

	/** Get Validity Break Age.
		@return The age at which the validity period changes - typically 40 years.
	  */
	public int getMedCatValidityBreakAge () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MedCatValidityBreakAge);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Validity Period Above Break Age.
		@param ValidityAboveBreak 
		The length of time, in months, the medical certificate is valid based on the age of the pilot and medical category.
	  */
	public void setValidityAboveBreak (int ValidityAboveBreak)
	{
		set_Value (COLUMNNAME_ValidityAboveBreak, Integer.valueOf(ValidityAboveBreak));
	}

	/** Get Validity Period Above Break Age.
		@return The length of time, in months, the medical certificate is valid based on the age of the pilot and medical category.
	  */
	public int getValidityAboveBreak () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ValidityAboveBreak);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Validity Period Below Break Age.
		@param ValidityBelowBreak 
		The length of time, in months, the medical certificate is valid based on the age of the pilot and medical category.
	  */
	public void setValidityBelowBreak (int ValidityBelowBreak)
	{
		set_Value (COLUMNNAME_ValidityBelowBreak, Integer.valueOf(ValidityBelowBreak));
	}

	/** Get Validity Period Below Break Age.
		@return The length of time, in months, the medical certificate is valid based on the age of the pilot and medical category.
	  */
	public int getValidityBelowBreak () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ValidityBelowBreak);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}