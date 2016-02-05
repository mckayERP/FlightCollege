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
import org.compiere.util.KeyNamePair;

/** Generated Model for FTU_Advanced_Inst
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_Advanced_Inst extends PO implements I_FTU_Advanced_Inst, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160131L;

    /** Standard Constructor */
    public X_FTU_Advanced_Inst (Properties ctx, int FTU_Advanced_Inst_ID, String trxName)
    {
      super (ctx, FTU_Advanced_Inst_ID, trxName);
      /** if (FTU_Advanced_Inst_ID == 0)
        {
			setFTU_Advanced_Inst_ID (0);
			setIsIntro (false);
// N
			setIsNoShow (false);
// N
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Advanced_Inst (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Advanced_Inst[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** FlightCourseType AD_Reference_ID=1000033 */
	public static final int FLIGHTCOURSETYPE_AD_Reference_ID=1000033;
	/** (pending) = (pending) */
	public static final String FLIGHTCOURSETYPE_Pending = "(pending)";
	/** Cancelled = Cancelled */
	public static final String FLIGHTCOURSETYPE_Cancelled = "Cancelled";
	/** CPL = CPL */
	public static final String FLIGHTCOURSETYPE_CPL = "CPL";
	/** Instructor = Instructor */
	public static final String FLIGHTCOURSETYPE_Instructor = "Instructor";
	/** Intro = Intro */
	public static final String FLIGHTCOURSETYPE_Intro = "Intro";
	/** Multi Engine = Multi Engine */
	public static final String FLIGHTCOURSETYPE_MultiEngine = "Multi Engine";
	/** Multi IFR = Multi IFR */
	public static final String FLIGHTCOURSETYPE_MultiIFR = "Multi IFR";
	/** Night = Night */
	public static final String FLIGHTCOURSETYPE_Night = "Night";
	/** No-Show = No-Show */
	public static final String FLIGHTCOURSETYPE_No_Show = "No-Show";
	/** Other = Other */
	public static final String FLIGHTCOURSETYPE_Other = "Other";
	/** PPL = PPL */
	public static final String FLIGHTCOURSETYPE_PPL = "PPL";
	/** Rental = Rental */
	public static final String FLIGHTCOURSETYPE_Rental = "Rental";
	/** RFP = RFP */
	public static final String FLIGHTCOURSETYPE_RFP = "RFP";
	/** Single IFR = Single IFR */
	public static final String FLIGHTCOURSETYPE_SingleIFR = "Single IFR";
	/** Tour = Tour */
	public static final String FLIGHTCOURSETYPE_Tour = "Tour";
	/** VFR OTT = VFR OTT */
	public static final String FLIGHTCOURSETYPE_VFROTT = "VFR OTT";
	/** Other Tuition = Other Tuition */
	public static final String FLIGHTCOURSETYPE_OtherTuition = "Other Tuition";
	/** Set Course Type.
		@param FlightCourseType 
		The type of flight training course (flight type)
	  */
	public void setFlightCourseType (String FlightCourseType)
	{

		set_Value (COLUMNNAME_FlightCourseType, FlightCourseType);
	}

	/** Get Course Type.
		@return The type of flight training course (flight type)
	  */
	public String getFlightCourseType () 
	{
		return (String)get_Value(COLUMNNAME_FlightCourseType);
	}

	/** Set Advanced Instruction Courses ID.
		@param FTU_Advanced_Inst_ID Advanced Instruction Courses ID	  */
	public void setFTU_Advanced_Inst_ID (int FTU_Advanced_Inst_ID)
	{
		if (FTU_Advanced_Inst_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Advanced_Inst_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Advanced_Inst_ID, Integer.valueOf(FTU_Advanced_Inst_ID));
	}

	/** Get Advanced Instruction Courses ID.
		@return Advanced Instruction Courses ID	  */
	public int getFTU_Advanced_Inst_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Advanced_Inst_ID);
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

	public org.compiere.model.I_M_Product getIntro_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getIntro_Product_ID(), get_TrxName());	}

	/** Set Intro Product.
		@param Intro_Product_ID 
		The product to charge for an intro flight.
	  */
	public void setIntro_Product_ID (int Intro_Product_ID)
	{
		if (Intro_Product_ID < 1) 
			set_Value (COLUMNNAME_Intro_Product_ID, null);
		else 
			set_Value (COLUMNNAME_Intro_Product_ID, Integer.valueOf(Intro_Product_ID));
	}

	/** Get Intro Product.
		@return The product to charge for an intro flight.
	  */
	public int getIntro_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Intro_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Advanced.
		@param IsAdvanced 
		Is the course/flight advanced?
	  */
	public void setIsAdvanced (boolean IsAdvanced)
	{
		set_Value (COLUMNNAME_IsAdvanced, Boolean.valueOf(IsAdvanced));
	}

	/** Get Advanced.
		@return Is the course/flight advanced?
	  */
	public boolean isAdvanced () 
	{
		Object oo = get_Value(COLUMNNAME_IsAdvanced);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Intro.
		@param IsIntro 
		Is the flight course an intro flight?
	  */
	public void setIsIntro (boolean IsIntro)
	{
		set_Value (COLUMNNAME_IsIntro, Boolean.valueOf(IsIntro));
	}

	/** Get Intro.
		@return Is the flight course an intro flight?
	  */
	public boolean isIntro () 
	{
		Object oo = get_Value(COLUMNNAME_IsIntro);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set No-Show.
		@param IsNoShow 
		Is the flight course type one that should be charged a No-Show fee?
	  */
	public void setIsNoShow (boolean IsNoShow)
	{
		set_Value (COLUMNNAME_IsNoShow, Boolean.valueOf(IsNoShow));
	}

	/** Get No-Show.
		@return Is the flight course type one that should be charged a No-Show fee?
	  */
	public boolean isNoShow () 
	{
		Object oo = get_Value(COLUMNNAME_IsNoShow);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	public org.compiere.model.I_M_Product getNoShow_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getNoShow_Product_ID(), get_TrxName());	}

	/** Set No-Show Product.
		@param NoShow_Product_ID 
		The product associated with the No-Show for this course type.
	  */
	public void setNoShow_Product_ID (int NoShow_Product_ID)
	{
		if (NoShow_Product_ID < 1) 
			set_Value (COLUMNNAME_NoShow_Product_ID, null);
		else 
			set_Value (COLUMNNAME_NoShow_Product_ID, Integer.valueOf(NoShow_Product_ID));
	}

	/** Get No-Show Product.
		@return The product associated with the No-Show for this course type.
	  */
	public int getNoShow_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NoShow_Product_ID);
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