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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for FTU_Pilot_Quals
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Pilot_Quals extends PO implements I_FTU_Pilot_Quals, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160417L;

    /** Standard Constructor */
    public X_FTU_Pilot_Quals (Properties ctx, int FTU_Pilot_Quals_ID, String trxName)
    {
      super (ctx, FTU_Pilot_Quals_ID, trxName);
      /** if (FTU_Pilot_Quals_ID == 0)
        {
			setFTU_License_Type_ID (0);
			setFTU_Pilot_Quals_ID (0);
			setLIC_IssueDate (new Timestamp( System.currentTimeMillis() ));
			setLIC_Licence_No (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Pilot_Quals (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Pilot_Quals[")
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
		throw new IllegalArgumentException ("C_BPartner_ID is virtual column");	}

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

	public com.mckayerp.ftu.model.I_FTU_License_Type getFTU_License_Type() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_License_Type)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_License_Type.Table_Name)
			.getPO(getFTU_License_Type_ID(), get_TrxName());	}

	/** Set Licenses and Permits ID.
		@param FTU_License_Type_ID Licenses and Permits ID	  */
	public void setFTU_License_Type_ID (int FTU_License_Type_ID)
	{
		if (FTU_License_Type_ID < 1) 
			set_Value (COLUMNNAME_FTU_License_Type_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_License_Type_ID, Integer.valueOf(FTU_License_Type_ID));
	}

	/** Get Licenses and Permits ID.
		@return Licenses and Permits ID	  */
	public int getFTU_License_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_License_Type_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pilot Qualifications ID.
		@param FTU_Pilot_Quals_ID Pilot Qualifications ID	  */
	public void setFTU_Pilot_Quals_ID (int FTU_Pilot_Quals_ID)
	{
		if (FTU_Pilot_Quals_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Pilot_Quals_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Pilot_Quals_ID, Integer.valueOf(FTU_Pilot_Quals_ID));
	}

	/** Get Pilot Qualifications ID.
		@return Pilot Qualifications ID	  */
	public int getFTU_Pilot_Quals_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Pilot_Quals_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** LIC_ABI_Class AD_Reference_ID=1000042 */
	public static final int LIC_ABI_CLASS_AD_Reference_ID=1000042;
	/** Class 1 = 1 */
	public static final String LIC_ABI_CLASS_Class1 = "1";
	/** Class 2 = 2 */
	public static final String LIC_ABI_CLASS_Class2 = "2";
	/** Class 3 = 3 */
	public static final String LIC_ABI_CLASS_Class3 = "3";
	/** Class 4 = 4 */
	public static final String LIC_ABI_CLASS_Class4 = "4";
	/** Set ABI Class.
		@param LIC_ABI_Class 
		The Class of aerobatic Instructor
	  */
	public void setLIC_ABI_Class (String LIC_ABI_Class)
	{

		set_Value (COLUMNNAME_LIC_ABI_Class, LIC_ABI_Class);
	}

	/** Get ABI Class.
		@return The Class of aerobatic Instructor
	  */
	public String getLIC_ABI_Class () 
	{
		return (String)get_Value(COLUMNNAME_LIC_ABI_Class);
	}

	/** Set ABI Expiry Date.
		@param LIC_ABI_ExpDate 
		The date the rating expires
	  */
	public void setLIC_ABI_ExpDate (Timestamp LIC_ABI_ExpDate)
	{
		set_Value (COLUMNNAME_LIC_ABI_ExpDate, LIC_ABI_ExpDate);
	}

	/** Get ABI Expiry Date.
		@return The date the rating expires
	  */
	public Timestamp getLIC_ABI_ExpDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LIC_ABI_ExpDate);
	}

	/** LIC_FI_Class AD_Reference_ID=1000042 */
	public static final int LIC_FI_CLASS_AD_Reference_ID=1000042;
	/** Class 1 = 1 */
	public static final String LIC_FI_CLASS_Class1 = "1";
	/** Class 2 = 2 */
	public static final String LIC_FI_CLASS_Class2 = "2";
	/** Class 3 = 3 */
	public static final String LIC_FI_CLASS_Class3 = "3";
	/** Class 4 = 4 */
	public static final String LIC_FI_CLASS_Class4 = "4";
	/** Set FI Class.
		@param LIC_FI_Class 
		The Class of Flight Instructor Rating
	  */
	public void setLIC_FI_Class (String LIC_FI_Class)
	{

		set_Value (COLUMNNAME_LIC_FI_Class, LIC_FI_Class);
	}

	/** Get FI Class.
		@return The Class of Flight Instructor Rating
	  */
	public String getLIC_FI_Class () 
	{
		return (String)get_Value(COLUMNNAME_LIC_FI_Class);
	}

	/** Set FI Expiry Date.
		@param LIC_FI_ExpDate 
		The expiry date of the instructor rating.
	  */
	public void setLIC_FI_ExpDate (Timestamp LIC_FI_ExpDate)
	{
		set_Value (COLUMNNAME_LIC_FI_ExpDate, LIC_FI_ExpDate);
	}

	/** Get FI Expiry Date.
		@return The expiry date of the instructor rating.
	  */
	public Timestamp getLIC_FI_ExpDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LIC_FI_ExpDate);
	}

	/** Set IR Expiry Date.
		@param LIC_IR_ExpDate IR Expiry Date	  */
	public void setLIC_IR_ExpDate (Timestamp LIC_IR_ExpDate)
	{
		set_Value (COLUMNNAME_LIC_IR_ExpDate, LIC_IR_ExpDate);
	}

	/** Get IR Expiry Date.
		@return IR Expiry Date	  */
	public Timestamp getLIC_IR_ExpDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LIC_IR_ExpDate);
	}

	/** LIC_IR_Group AD_Reference_ID=1000043 */
	public static final int LIC_IR_GROUP_AD_Reference_ID=1000043;
	/** Group 1 = 1 */
	public static final String LIC_IR_GROUP_Group1 = "1";
	/** Group 2 = 2 */
	public static final String LIC_IR_GROUP_Group2 = "2";
	/** Group 3 = 3 */
	public static final String LIC_IR_GROUP_Group3 = "3";
	/** Set IR Group.
		@param LIC_IR_Group 
		The group of the Instrument Rating
	  */
	public void setLIC_IR_Group (String LIC_IR_Group)
	{

		set_Value (COLUMNNAME_LIC_IR_Group, LIC_IR_Group);
	}

	/** Get IR Group.
		@return The group of the Instrument Rating
	  */
	public String getLIC_IR_Group () 
	{
		return (String)get_Value(COLUMNNAME_LIC_IR_Group);
	}

	/** Set Aerobatic Instructor.
		@param LIC_IsABI 
		Is the flight rating for an Aerobatic Instructor?
	  */
	public void setLIC_IsABI (boolean LIC_IsABI)
	{
		set_Value (COLUMNNAME_LIC_IsABI, Boolean.valueOf(LIC_IsABI));
	}

	/** Get Aerobatic Instructor.
		@return Is the flight rating for an Aerobatic Instructor?
	  */
	public boolean isLIC_IsABI () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsABI);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set C.
		@param LIC_IsCenterlineEng 
		Does the class rating cover centerline-engine aircraft?
	  */
	public void setLIC_IsCenterlineEng (boolean LIC_IsCenterlineEng)
	{
		set_Value (COLUMNNAME_LIC_IsCenterlineEng, Boolean.valueOf(LIC_IsCenterlineEng));
	}

	/** Get C.
		@return Does the class rating cover centerline-engine aircraft?
	  */
	public boolean isLIC_IsCenterlineEng () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsCenterlineEng);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set FI.
		@param LIC_IsFI 
		Flight Instructor Rating
	  */
	public void setLIC_IsFI (boolean LIC_IsFI)
	{
		set_Value (COLUMNNAME_LIC_IsFI, Boolean.valueOf(LIC_IsFI));
	}

	/** Get FI.
		@return Flight Instructor Rating
	  */
	public boolean isLIC_IsFI () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsFI);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Instrument Rating.
		@param LIC_IsIR 
		Does the licence include an Instrument Rating?
	  */
	public void setLIC_IsIR (boolean LIC_IsIR)
	{
		set_Value (COLUMNNAME_LIC_IsIR, Boolean.valueOf(LIC_IsIR));
	}

	/** Get Instrument Rating.
		@return Does the licence include an Instrument Rating?
	  */
	public boolean isLIC_IsIR () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsIR);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Land.
		@param LIC_IsLand 
		Does the class rating cover Land aircraft.
	  */
	public void setLIC_IsLand (boolean LIC_IsLand)
	{
		set_Value (COLUMNNAME_LIC_IsLand, Boolean.valueOf(LIC_IsLand));
	}

	/** Get Land.
		@return Does the class rating cover Land aircraft.
	  */
	public boolean isLIC_IsLand () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsLand);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set M.
		@param LIC_IsMultiEng 
		Does the class rating cover multi-engine aircraft?
	  */
	public void setLIC_IsMultiEng (boolean LIC_IsMultiEng)
	{
		set_Value (COLUMNNAME_LIC_IsMultiEng, Boolean.valueOf(LIC_IsMultiEng));
	}

	/** Get M.
		@return Does the class rating cover multi-engine aircraft?
	  */
	public boolean isLIC_IsMultiEng () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsMultiEng);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Night Rating.
		@param LIC_IsNight 
		Does the Licence allow flights at night?
	  */
	public void setLIC_IsNight (boolean LIC_IsNight)
	{
		set_Value (COLUMNNAME_LIC_IsNight, Boolean.valueOf(LIC_IsNight));
	}

	/** Get Night Rating.
		@return Does the Licence allow flights at night?
	  */
	public boolean isLIC_IsNight () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsNight);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Passenger Rating?.
		@param LIC_IsPax 
		Does the licence allow for carrying Passengers?
	  */
	public void setLIC_IsPax (boolean LIC_IsPax)
	{
		set_Value (COLUMNNAME_LIC_IsPax, Boolean.valueOf(LIC_IsPax));
	}

	/** Get Passenger Rating?.
		@return Does the licence allow for carrying Passengers?
	  */
	public boolean isLIC_IsPax () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsPax);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sea.
		@param LIC_IsSea 
		Does the Class Rating cover seaplanes
	  */
	public void setLIC_IsSea (boolean LIC_IsSea)
	{
		set_Value (COLUMNNAME_LIC_IsSea, Boolean.valueOf(LIC_IsSea));
	}

	/** Get Sea.
		@return Does the Class Rating cover seaplanes
	  */
	public boolean isLIC_IsSea () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsSea);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set S.
		@param LIC_IsSingleEng 
		Is the class rating cover single engine aircraft.
	  */
	public void setLIC_IsSingleEng (boolean LIC_IsSingleEng)
	{
		set_Value (COLUMNNAME_LIC_IsSingleEng, Boolean.valueOf(LIC_IsSingleEng));
	}

	/** Get S.
		@return Is the class rating cover single engine aircraft.
	  */
	public boolean isLIC_IsSingleEng () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsSingleEng);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Date of Issue.
		@param LIC_IssueDate 
		The date the licence was issued or approved.
	  */
	public void setLIC_IssueDate (Timestamp LIC_IssueDate)
	{
		set_Value (COLUMNNAME_LIC_IssueDate, LIC_IssueDate);
	}

	/** Get Date of Issue.
		@return The date the licence was issued or approved.
	  */
	public Timestamp getLIC_IssueDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LIC_IssueDate);
	}

	/** Set Temporary?.
		@param LIC_IsTemporary 
		Is the licence temporary?
	  */
	public void setLIC_IsTemporary (boolean LIC_IsTemporary)
	{
		set_Value (COLUMNNAME_LIC_IsTemporary, Boolean.valueOf(LIC_IsTemporary));
	}

	/** Get Temporary?.
		@return Is the licence temporary?
	  */
	public boolean isLIC_IsTemporary () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_IsTemporary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Licence #.
		@param LIC_Licence_No 
		The Aviation licence number.
	  */
	public void setLIC_Licence_No (String LIC_Licence_No)
	{
		set_Value (COLUMNNAME_LIC_Licence_No, LIC_Licence_No);
	}

	/** Get Licence #.
		@return The Aviation licence number.
	  */
	public String getLIC_Licence_No () 
	{
		return (String)get_Value(COLUMNNAME_LIC_Licence_No);
	}

	/** Set Passenger Limit.
		@param LIC_PaxLimit 
		The maximum number of passengers allowed.  Leave blank for unlimited.
	  */
	public void setLIC_PaxLimit (int LIC_PaxLimit)
	{
		set_Value (COLUMNNAME_LIC_PaxLimit, Integer.valueOf(LIC_PaxLimit));
	}

	/** Get Passenger Limit.
		@return The maximum number of passengers allowed.  Leave blank for unlimited.
	  */
	public int getLIC_PaxLimit () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LIC_PaxLimit);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set VFR Over the Top.
		@param LIC_VFROTT 
		Does the licence permit VFR Over The Top (OTT)?
	  */
	public void setLIC_VFROTT (boolean LIC_VFROTT)
	{
		set_Value (COLUMNNAME_LIC_VFROTT, Boolean.valueOf(LIC_VFROTT));
	}

	/** Get VFR Over the Top.
		@return Does the licence permit VFR Over The Top (OTT)?
	  */
	public boolean isLIC_VFROTT () 
	{
		Object oo = get_Value(COLUMNNAME_LIC_VFROTT);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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