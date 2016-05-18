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

/** Generated Model for FTU_DefectLog
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_DefectLog extends PO implements I_FTU_DefectLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160417L;

    /** Standard Constructor */
    public X_FTU_DefectLog (Properties ctx, int FTU_DefectLog_ID, String trxName)
    {
      super (ctx, FTU_DefectLog_ID, trxName);
      /** if (FTU_DefectLog_ID == 0)
        {
			setFTU_DefectLog_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_DefectLog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_DefectLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Defect Date.
		@param DefectDate 
		The date the defect was entered in the log
	  */
	public void setDefectDate (Timestamp DefectDate)
	{
		set_Value (COLUMNNAME_DefectDate, DefectDate);
	}

	/** Get Defect Date.
		@return The date the defect was entered in the log
	  */
	public Timestamp getDefectDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DefectDate);
	}

	/** Set Defect.
		@param DefectDesc 
		The defect description
	  */
	public void setDefectDesc (String DefectDesc)
	{
		set_Value (COLUMNNAME_DefectDesc, DefectDesc);
	}

	/** Get Defect.
		@return The defect description
	  */
	public String getDefectDesc () 
	{
		return (String)get_Value(COLUMNNAME_DefectDesc);
	}

	/** DefectStatus AD_Reference_ID=1000040 */
	public static final int DEFECTSTATUS_AD_Reference_ID=1000040;
	/** Unservicable = U/S */
	public static final String DEFECTSTATUS_Unservicable = "U/S";
	/** Deferred = Deferred */
	public static final String DEFECTSTATUS_Deferred = "Deferred";
	/** Servicable = Servicable */
	public static final String DEFECTSTATUS_Servicable = "Servicable";
	/** Inspection = Inspection */
	public static final String DEFECTSTATUS_Inspection = "Inspection";
	/** Set Defect Status.
		@param DefectStatus Defect Status	  */
	public void setDefectStatus (String DefectStatus)
	{

		set_Value (COLUMNNAME_DefectStatus, DefectStatus);
	}

	/** Get Defect Status.
		@return Defect Status	  */
	public String getDefectStatus () 
	{
		return (String)get_Value(COLUMNNAME_DefectStatus);
	}

	/** DefectType AD_Reference_ID=1000039 */
	public static final int DEFECTTYPE_AD_Reference_ID=1000039;
	/** Lights and Electrical = Lights */
	public static final String DEFECTTYPE_LightsAndElectrical = "Lights";
	/** Avionics and Instruments = Avionics */
	public static final String DEFECTTYPE_AvionicsAndInstruments = "Avionics";
	/** Engines, fuel and associate parts. = Engines */
	public static final String DEFECTTYPE_EnginesFuelAndAssociateParts = "Engines";
	/** Airframe, wheels, and other stuff. = Airframe */
	public static final String DEFECTTYPE_AirframeWheelsAndOtherStuff = "Airframe";
	/** Routine Inspection = Inspection */
	public static final String DEFECTTYPE_RoutineInspection = "Inspection";
	/** Set Defect Type.
		@param DefectType 
		The type of the defect
	  */
	public void setDefectType (String DefectType)
	{

		set_Value (COLUMNNAME_DefectType, DefectType);
	}

	/** Get Defect Type.
		@return The type of the defect
	  */
	public String getDefectType () 
	{
		return (String)get_Value(COLUMNNAME_DefectType);
	}

	/** Set Deferred Date.
		@param DeferredDate 
		The date the defect was deferred.
	  */
	public void setDeferredDate (Timestamp DeferredDate)
	{
		set_Value (COLUMNNAME_DeferredDate, DeferredDate);
	}

	/** Get Deferred Date.
		@return The date the defect was deferred.
	  */
	public Timestamp getDeferredDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DeferredDate);
	}

	public com.mckayerp.ftu.model.I_FTU_Aircraft getFTU_Aircraft() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Aircraft)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Aircraft.Table_Name)
			.getPO(getFTU_Aircraft_ID(), get_TrxName());	}

	/** Set Aircraft.
		@param FTU_Aircraft_ID Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID)
	{
		if (FTU_Aircraft_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Aircraft_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Aircraft_ID, Integer.valueOf(FTU_Aircraft_ID));
	}

	/** Get Aircraft.
		@return Aircraft	  */
	public int getFTU_Aircraft_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Aircraft_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Aircraft Defect Log ID.
		@param FTU_DefectLog_ID Aircraft Defect Log ID	  */
	public void setFTU_DefectLog_ID (int FTU_DefectLog_ID)
	{
		if (FTU_DefectLog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_DefectLog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_DefectLog_ID, Integer.valueOf(FTU_DefectLog_ID));
	}

	/** Get Aircraft Defect Log ID.
		@return Aircraft Defect Log ID	  */
	public int getFTU_DefectLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_DefectLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rectification.
		@param Rectification 
		Description of how the defect was rectified.
	  */
	public void setRectification (String Rectification)
	{
		set_Value (COLUMNNAME_Rectification, Rectification);
	}

	/** Get Rectification.
		@return Description of how the defect was rectified.
	  */
	public String getRectification () 
	{
		return (String)get_Value(COLUMNNAME_Rectification);
	}

	/** Set Repaired Date.
		@param RepairedDate 
		The date the defect was repaired.
	  */
	public void setRepairedDate (Timestamp RepairedDate)
	{
		set_Value (COLUMNNAME_RepairedDate, RepairedDate);
	}

	/** Get Repaired Date.
		@return The date the defect was repaired.
	  */
	public Timestamp getRepairedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_RepairedDate);
	}
}