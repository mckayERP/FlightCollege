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
import org.compiere.util.KeyNamePair;

/** Generated Model for FTU_Syllabus_Details
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2 - $Id$ */
public class X_FTU_Syllabus_Details extends PO implements I_FTU_Syllabus_Details, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160131L;

    /** Standard Constructor */
    public X_FTU_Syllabus_Details (Properties ctx, int FTU_Syllabus_Details_ID, String trxName)
    {
      super (ctx, FTU_Syllabus_Details_ID, trxName);
      /** if (FTU_Syllabus_Details_ID == 0)
        {
			setFTU_Syllabus_Details_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Syllabus_Details (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Syllabus_Details[")
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

	/** Set Syllabus Details ID.
		@param FTU_Syllabus_Details_ID Syllabus Details ID	  */
	public void setFTU_Syllabus_Details_ID (int FTU_Syllabus_Details_ID)
	{
		if (FTU_Syllabus_Details_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Syllabus_Details_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Syllabus_Details_ID, Integer.valueOf(FTU_Syllabus_Details_ID));
	}

	/** Get Syllabus Details ID.
		@return Syllabus Details ID	  */
	public int getFTU_Syllabus_Details_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Syllabus_Details_ID);
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

	/** Set Hour Requirement.
		@param HourReq 
		The hours (air - sim) required to complete the syllabus lesson.
	  */
	public void setHourReq (BigDecimal HourReq)
	{
		set_Value (COLUMNNAME_HourReq, HourReq);
	}

	/** Get Hour Requirement.
		@return The hours (air - sim) required to complete the syllabus lesson.
	  */
	public BigDecimal getHourReq () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HourReq);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Interphase AD_Reference_ID=1000035 */
	public static final int INTERPHASE_AD_Reference_ID=1000035;
	/** LP115 Recommendation for First Solo = LP115 */
	public static final String INTERPHASE_LP115RecommendationForFirstSolo = "LP115";
	/** LP116 First Solo = LP116 */
	public static final String INTERPHASE_LP116FirstSolo = "LP116";
	/** LP204 Solo Circuits Complete = LP204 */
	public static final String INTERPHASE_LP204SoloCircuitsComplete = "LP204";
	/** LP219 Solo Practice Area Complete = LP219 */
	public static final String INTERPHASE_LP219SoloPracticeAreaComplete = "LP219";
	/** LP226 Solo Crosscountry Complete = LP226 */
	public static final String INTERPHASE_LP226SoloCrosscountryComplete = "LP226";
	/** LP308 Recommendation for PPL Flight Test = LP308 */
	public static final String INTERPHASE_LP308RecommendationForPPLFlightTest = "LP308";
	/** LP309 PPL Flight Test Complete = LP309 */
	public static final String INTERPHASE_LP309PPLFlightTestComplete = "LP309";
	/** Set Inter-Phase.
		@param Interphase 
		The inter-phase benchmark in the syllabus training.
	  */
	public void setInterphase (String Interphase)
	{

		set_Value (COLUMNNAME_Interphase, Interphase);
	}

	/** Get Inter-Phase.
		@return The inter-phase benchmark in the syllabus training.
	  */
	public String getInterphase () 
	{
		return (String)get_Value(COLUMNNAME_Interphase);
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

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** SyllabusPhase AD_Reference_ID=1000034 */
	public static final int SYLLABUSPHASE_AD_Reference_ID=1000034;
	/** 1 = 1 */
	public static final String SYLLABUSPHASE_1 = "1";
	/** 2 = 2 */
	public static final String SYLLABUSPHASE_2 = "2";
	/** 3 = 3 */
	public static final String SYLLABUSPHASE_3 = "3";
	/** Set Phase.
		@param SyllabusPhase 
		The syllabus stage of training
	  */
	public void setSyllabusPhase (String SyllabusPhase)
	{

		set_Value (COLUMNNAME_SyllabusPhase, SyllabusPhase);
	}

	/** Get Phase.
		@return The syllabus stage of training
	  */
	public String getSyllabusPhase () 
	{
		return (String)get_Value(COLUMNNAME_SyllabusPhase);
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