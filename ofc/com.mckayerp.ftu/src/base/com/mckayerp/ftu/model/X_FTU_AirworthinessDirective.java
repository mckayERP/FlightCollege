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

/** Generated Model for FTU_AirworthinessDirective
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_AirworthinessDirective extends PO implements I_FTU_AirworthinessDirective, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170413L;

    /** Standard Constructor */
    public X_FTU_AirworthinessDirective (Properties ctx, int FTU_AirworthinessDirective_ID, String trxName)
    {
      super (ctx, FTU_AirworthinessDirective_ID, trxName);
      /** if (FTU_AirworthinessDirective_ID == 0)
        {
			setFTU_ADNumber (null);
			setFTU_AirworthinessDirective_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_AirworthinessDirective (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_AirworthinessDirective[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** FTU_ADCountryCode AD_Reference_ID=53861 */
	public static final int FTU_ADCOUNTRYCODE_AD_Reference_ID=53861;
	/** Canada = CF */
	public static final String FTU_ADCOUNTRYCODE_Canada = "CF";
	/** United States = US */
	public static final String FTU_ADCOUNTRYCODE_UnitedStates = "US";
	/** European Union = EU */
	public static final String FTU_ADCOUNTRYCODE_EuropeanUnion = "EU";
	/** France = FR */
	public static final String FTU_ADCOUNTRYCODE_France = "FR";
	/** Set Country.
		@param FTU_ADCountryCode 
		The Country of issue
	  */
	public void setFTU_ADCountryCode (String FTU_ADCountryCode)
	{

		set_Value (COLUMNNAME_FTU_ADCountryCode, FTU_ADCountryCode);
	}

	/** Get Country.
		@return The Country of issue
	  */
	public String getFTU_ADCountryCode () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADCountryCode);
	}

	/** Set Link.
		@param FTU_ADDocumentLink 
		Link to the on-line document
	  */
	public void setFTU_ADDocumentLink (String FTU_ADDocumentLink)
	{
		set_Value (COLUMNNAME_FTU_ADDocumentLink, FTU_ADDocumentLink);
	}

	/** Get Link.
		@return Link to the on-line document
	  */
	public String getFTU_ADDocumentLink () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADDocumentLink);
	}

	/** Set PDF.
		@param FTU_ADDocumentLinkPDF 
		Link to the on-line PDF document
	  */
	public void setFTU_ADDocumentLinkPDF (String FTU_ADDocumentLinkPDF)
	{
		set_Value (COLUMNNAME_FTU_ADDocumentLinkPDF, FTU_ADDocumentLinkPDF);
	}

	/** Get PDF.
		@return Link to the on-line PDF document
	  */
	public String getFTU_ADDocumentLinkPDF () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADDocumentLinkPDF);
	}

	/** Set Is superseded.
		@param FTU_ADIsSuperseded 
		Is this AD superseded?
	  */
	public void setFTU_ADIsSuperseded (boolean FTU_ADIsSuperseded)
	{
		set_Value (COLUMNNAME_FTU_ADIsSuperseded, Boolean.valueOf(FTU_ADIsSuperseded));
	}

	/** Get Is superseded.
		@return Is this AD superseded?
	  */
	public boolean isFTU_ADIsSuperseded () 
	{
		Object oo = get_Value(COLUMNNAME_FTU_ADIsSuperseded);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set AD Number.
		@param FTU_ADNumber 
		The reference number for this AD
	  */
	public void setFTU_ADNumber (String FTU_ADNumber)
	{
		set_Value (COLUMNNAME_FTU_ADNumber, FTU_ADNumber);
	}

	/** Get AD Number.
		@return The reference number for this AD
	  */
	public String getFTU_ADNumber () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADNumber);
	}

	/** Set Reference.
		@param FTU_ADReference 
		Reference information that expands the Airworthiness Directive
	  */
	public void setFTU_ADReference (String FTU_ADReference)
	{
		set_Value (COLUMNNAME_FTU_ADReference, FTU_ADReference);
	}

	/** Get Reference.
		@return Reference information that expands the Airworthiness Directive
	  */
	public String getFTU_ADReference () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADReference);
	}

	/** Set Repeat Inspection?.
		@param FTU_ADRepeatText 
		Does the AD require repetitive inspections?
	  */
	public void setFTU_ADRepeatText (String FTU_ADRepeatText)
	{
		set_Value (COLUMNNAME_FTU_ADRepeatText, FTU_ADRepeatText);
	}

	/** Get Repeat Inspection?.
		@return Does the AD require repetitive inspections?
	  */
	public String getFTU_ADRepeatText () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADRepeatText);
	}

	/** Set AD Subject.
		@param FTU_ADSubject 
		The subject of the Airworthiness Directives
	  */
	public void setFTU_ADSubject (String FTU_ADSubject)
	{
		set_Value (COLUMNNAME_FTU_ADSubject, FTU_ADSubject);
	}

	/** Get AD Subject.
		@return The subject of the Airworthiness Directives
	  */
	public String getFTU_ADSubject () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADSubject);
	}

	/** FTU_ADType AD_Reference_ID=53860 */
	public static final int FTU_ADTYPE_AD_Reference_ID=53860;
	/** Aircraft = A */
	public static final String FTU_ADTYPE_Aircraft = "A";
	/** Engines = E */
	public static final String FTU_ADTYPE_Engines = "E";
	/** Propellers = P */
	public static final String FTU_ADTYPE_Propellers = "P";
	/** Miscellaneous Equipment = M */
	public static final String FTU_ADTYPE_MiscellaneousEquipment = "M";
	/** Set Product Type.
		@param FTU_ADType 
		The type of airworthiness directive and the product/component it pertains to.
	  */
	public void setFTU_ADType (String FTU_ADType)
	{

		set_Value (COLUMNNAME_FTU_ADType, FTU_ADType);
	}

	/** Get Product Type.
		@return The type of airworthiness directive and the product/component it pertains to.
	  */
	public String getFTU_ADType () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ADType);
	}

	/** Set Airworthiness Directives ID.
		@param FTU_AirworthinessDirective_ID Airworthiness Directives ID	  */
	public void setFTU_AirworthinessDirective_ID (int FTU_AirworthinessDirective_ID)
	{
		if (FTU_AirworthinessDirective_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_AirworthinessDirective_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_AirworthinessDirective_ID, Integer.valueOf(FTU_AirworthinessDirective_ID));
	}

	/** Get Airworthiness Directives ID.
		@return Airworthiness Directives ID	  */
	public int getFTU_AirworthinessDirective_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_AirworthinessDirective_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_AirworthinessDirective getFTU_SupersedingAD() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_AirworthinessDirective)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_AirworthinessDirective.Table_Name)
			.getPO(getFTU_SupersedingAD_ID(), get_TrxName());	}

	/** Set Superseded by.
		@param FTU_SupersedingAD_ID 
		The superseding AD.
	  */
	public void setFTU_SupersedingAD_ID (int FTU_SupersedingAD_ID)
	{
		if (FTU_SupersedingAD_ID < 1) 
			set_Value (COLUMNNAME_FTU_SupersedingAD_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_SupersedingAD_ID, Integer.valueOf(FTU_SupersedingAD_ID));
	}

	/** Get Superseded by.
		@return The superseding AD.
	  */
	public int getFTU_SupersedingAD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_SupersedingAD_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}