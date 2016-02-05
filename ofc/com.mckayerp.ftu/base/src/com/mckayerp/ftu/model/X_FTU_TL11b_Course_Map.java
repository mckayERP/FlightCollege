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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for FTU_TL11b_Course_Map
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_TL11b_Course_Map extends PO implements I_FTU_TL11b_Course_Map, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160131L;

    /** Standard Constructor */
    public X_FTU_TL11b_Course_Map (Properties ctx, int FTU_TL11b_Course_Map_ID, String trxName)
    {
      super (ctx, FTU_TL11b_Course_Map_ID, trxName);
      /** if (FTU_TL11b_Course_Map_ID == 0)
        {
			setFTU_TL11b_Course_Map_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_TL11b_Course_Map (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_TL11b_Course_Map[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** CRA_Course_Type AD_Reference_ID=1000046 */
	public static final int CRA_COURSE_TYPE_AD_Reference_ID=1000046;
	/** PPL = 0-PPL */
	public static final String CRA_COURSE_TYPE_PPL = "0-PPL";
	/** CPL = 1-CPL */
	public static final String CRA_COURSE_TYPE_CPL = "1-CPL";
	/** Instructor = 2-Instructor */
	public static final String CRA_COURSE_TYPE_Instructor = "2-Instructor";
	/** Helicopter category rating = 3-Heli */
	public static final String CRA_COURSE_TYPE_HelicopterCategoryRating = "3-Heli";
	/** IFR = 4-IFR */
	public static final String CRA_COURSE_TYPE_IFR = "4-IFR";
	/** Other = 5-Other */
	public static final String CRA_COURSE_TYPE_Other = "5-Other";
	/** Set CRA Course Type.
		@param CRA_Course_Type 
		The type of flight training course followed.
	  */
	public void setCRA_Course_Type (String CRA_Course_Type)
	{

		set_Value (COLUMNNAME_CRA_Course_Type, CRA_Course_Type);
	}

	/** Get CRA Course Type.
		@return The type of flight training course followed.
	  */
	public String getCRA_Course_Type () 
	{
		return (String)get_Value(COLUMNNAME_CRA_Course_Type);
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

	/** Set TL11B_Course_Map ID.
		@param FTU_TL11b_Course_Map_ID TL11B_Course_Map ID	  */
	public void setFTU_TL11b_Course_Map_ID (int FTU_TL11b_Course_Map_ID)
	{
		if (FTU_TL11b_Course_Map_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_TL11b_Course_Map_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_TL11b_Course_Map_ID, Integer.valueOf(FTU_TL11b_Course_Map_ID));
	}

	/** Get TL11B_Course_Map ID.
		@return TL11B_Course_Map ID	  */
	public int getFTU_TL11b_Course_Map_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_TL11b_Course_Map_ID);
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

	/** Set Max Dual Time Allowed.
		@param MaxDualTimeAllowed 
		The maximum dual time claimable by CRA & Transport Canada.
	  */
	public void setMaxDualTimeAllowed (BigDecimal MaxDualTimeAllowed)
	{
		set_Value (COLUMNNAME_MaxDualTimeAllowed, MaxDualTimeAllowed);
	}

	/** Get Max Dual Time Allowed.
		@return The maximum dual time claimable by CRA & Transport Canada.
	  */
	public BigDecimal getMaxDualTimeAllowed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxDualTimeAllowed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Max Sim Time Allowed.
		@param MaxSimTimeAllowed 
		The maximum simulator time claimable by CRA & Transport Canada.
	  */
	public void setMaxSimTimeAllowed (BigDecimal MaxSimTimeAllowed)
	{
		set_Value (COLUMNNAME_MaxSimTimeAllowed, MaxSimTimeAllowed);
	}

	/** Get Max Sim Time Allowed.
		@return The maximum simulator time claimable by CRA & Transport Canada.
	  */
	public BigDecimal getMaxSimTimeAllowed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxSimTimeAllowed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Max Solo Time Allowed.
		@param MaxSoloTimeAllowed 
		The maximum solo time claimable by CRA & Transport Canada.
	  */
	public void setMaxSoloTimeAllowed (BigDecimal MaxSoloTimeAllowed)
	{
		set_Value (COLUMNNAME_MaxSoloTimeAllowed, MaxSoloTimeAllowed);
	}

	/** Get Max Solo Time Allowed.
		@return The maximum solo time claimable by CRA & Transport Canada.
	  */
	public BigDecimal getMaxSoloTimeAllowed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxSoloTimeAllowed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Max Total Time Allowed.
		@param MaxTotalTimeAllowed 
		The maximum total time (dual plus solo) claimable by CRA & Transport Canada.
	  */
	public void setMaxTotalTimeAllowed (BigDecimal MaxTotalTimeAllowed)
	{
		set_Value (COLUMNNAME_MaxTotalTimeAllowed, MaxTotalTimeAllowed);
	}

	/** Get Max Total Time Allowed.
		@return The maximum total time (dual plus solo) claimable by CRA & Transport Canada.
	  */
	public BigDecimal getMaxTotalTimeAllowed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxTotalTimeAllowed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}