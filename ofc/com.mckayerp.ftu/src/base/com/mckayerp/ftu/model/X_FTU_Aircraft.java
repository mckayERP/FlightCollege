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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for FTU_Aircraft
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Aircraft extends PO implements I_FTU_Aircraft, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170116L;

    /** Standard Constructor */
    public X_FTU_Aircraft (Properties ctx, int FTU_Aircraft_ID, String trxName)
    {
      super (ctx, FTU_Aircraft_ID, trxName);
      /** if (FTU_Aircraft_ID == 0)
        {
			setC_UOM_ID (0);
// 1000001
			setCallSign (null);
			setFTU_Aircraft_ID (0);
			setS_Resource_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Aircraft (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_Aircraft[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_Name)
			.getPO(getA_Asset_ID(), get_TrxName());	}

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ACMaintQtyType AD_Reference_ID=1000045 */
	public static final int ACMAINTQTYTYPE_AD_Reference_ID=1000045;
	/** FMFL 3-Tiere Lease Terms = FMFL-3TierLease */
	public static final String ACMAINTQTYTYPE_FMFL3_TiereLeaseTerms = "FMFL-3TierLease";
	/** Monthly Fixed = Monthly */
	public static final String ACMAINTQTYTYPE_MonthlyFixed = "Monthly";
	/** Airtime in period = Airtime */
	public static final String ACMAINTQTYTYPE_AirtimeInPeriod = "Airtime";
	/** Flight time in period = Flight Time */
	public static final String ACMAINTQTYTYPE_FlightTimeInPeriod = "Flight Time";
	/** Set Maintenance Qty Type.
		@param ACMaintQtyType 
		The type of hours to use in calculating the maintenance numbers
	  */
	public void setACMaintQtyType (String ACMaintQtyType)
	{

		set_Value (COLUMNNAME_ACMaintQtyType, ACMaintQtyType);
	}

	/** Get Maintenance Qty Type.
		@return The type of hours to use in calculating the maintenance numbers
	  */
	public String getACMaintQtyType () 
	{
		return (String)get_Value(COLUMNNAME_ACMaintQtyType);
	}

	/** Set Maint Rate ($/hr).
		@param ACMaintRate 
		Maintenance rate in cost per dollars/hour.
	  */
	public void setACMaintRate (BigDecimal ACMaintRate)
	{
		set_Value (COLUMNNAME_ACMaintRate, ACMaintRate);
	}

	/** Get Maint Rate ($/hr).
		@return Maintenance rate in cost per dollars/hour.
	  */
	public BigDecimal getACMaintRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ACMaintRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set AC Manufacturer.
		@param ACManufacturer 
		The aircraft manufacturer
	  */
	public void setACManufacturer (String ACManufacturer)
	{
		set_Value (COLUMNNAME_ACManufacturer, ACManufacturer);
	}

	/** Get AC Manufacturer.
		@return The aircraft manufacturer
	  */
	public String getACManufacturer () 
	{
		return (String)get_Value(COLUMNNAME_ACManufacturer);
	}

	/** Set Max Gross Weight.
		@param ACMaxGrossWeight Max Gross Weight	  */
	public void setACMaxGrossWeight (BigDecimal ACMaxGrossWeight)
	{
		set_Value (COLUMNNAME_ACMaxGrossWeight, ACMaxGrossWeight);
	}

	/** Get Max Gross Weight.
		@return Max Gross Weight	  */
	public BigDecimal getACMaxGrossWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ACMaxGrossWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Model.
		@param ACModel 
		The model of the aircraft
	  */
	public void setACModel (String ACModel)
	{
		set_Value (COLUMNNAME_ACModel, ACModel);
	}

	/** Get Model.
		@return The model of the aircraft
	  */
	public String getACModel () 
	{
		return (String)get_Value(COLUMNNAME_ACModel);
	}

	/** Set Next Maint Date.
		@param ACNextMaintDate 
		The date the next maintenance action is due.
	  */
	public void setACNextMaintDate (Timestamp ACNextMaintDate)
	{
		set_Value (COLUMNNAME_ACNextMaintDate, ACNextMaintDate);
	}

	/** Get Next Maint Date.
		@return The date the next maintenance action is due.
	  */
	public Timestamp getACNextMaintDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ACNextMaintDate);
	}

	/** Set Next Maint Hours.
		@param ACNextMaintHrs 
		The Airframe hours when the next maintenance action is due.
	  */
	public void setACNextMaintHrs (BigDecimal ACNextMaintHrs)
	{
		set_Value (COLUMNNAME_ACNextMaintHrs, ACNextMaintHrs);
	}

	/** Get Next Maint Hours.
		@return The Airframe hours when the next maintenance action is due.
	  */
	public BigDecimal getACNextMaintHrs () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ACNextMaintHrs);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Oil Grade.
		@param ACOilGrade 
		The grade of oil used in the engine.
	  */
	public void setACOilGrade (String ACOilGrade)
	{
		set_Value (COLUMNNAME_ACOilGrade, ACOilGrade);
	}

	/** Get Oil Grade.
		@return The grade of oil used in the engine.
	  */
	public String getACOilGrade () 
	{
		return (String)get_Value(COLUMNNAME_ACOilGrade);
	}

	/** Set Registration.
		@param ACRegistration 
		The national registration marks for the aircraft
	  */
	public void setACRegistration (String ACRegistration)
	{
		set_Value (COLUMNNAME_ACRegistration, ACRegistration);
	}

	/** Get Registration.
		@return The national registration marks for the aircraft
	  */
	public String getACRegistration () 
	{
		return (String)get_Value(COLUMNNAME_ACRegistration);
	}

	/** Set Serial Number.
		@param ACSerialNumber 
		The serial number of the plane.
	  */
	public void setACSerialNumber (String ACSerialNumber)
	{
		set_Value (COLUMNNAME_ACSerialNumber, ACSerialNumber);
	}

	/** Get Serial Number.
		@return The serial number of the plane.
	  */
	public String getACSerialNumber () 
	{
		return (String)get_Value(COLUMNNAME_ACSerialNumber);
	}

	/** ACStatus AD_Reference_ID=1000075 */
	public static final int ACSTATUS_AD_Reference_ID=1000075;
	/** Servicable = Serv */
	public static final String ACSTATUS_Servicable = "Serv";
	/** Unservicable = U/S */
	public static final String ACSTATUS_Unservicable = "U/S";
	/** Set Aircraft Status.
		@param ACStatus Aircraft Status	  */
	public void setACStatus (String ACStatus)
	{

		set_ValueNoCheck (COLUMNNAME_ACStatus, ACStatus);
	}

	/** Get Aircraft Status.
		@return Aircraft Status	  */
	public String getACStatus () 
	{
		return (String)get_Value(COLUMNNAME_ACStatus);
	}

	/** AircraftLeaseType AD_Reference_ID=1000079 */
	public static final int AIRCRAFTLEASETYPE_AD_Reference_ID=1000079;
	/** Capital = Capital */
	public static final String AIRCRAFTLEASETYPE_Capital = "Capital";
	/** Expense = Expense */
	public static final String AIRCRAFTLEASETYPE_Expense = "Expense";
	/** Set Lease Type.
		@param AircraftLeaseType 
		Capital or Expense
	  */
	public void setAircraftLeaseType (String AircraftLeaseType)
	{

		set_Value (COLUMNNAME_AircraftLeaseType, AircraftLeaseType);
	}

	/** Get Lease Type.
		@return Capital or Expense
	  */
	public String getAircraftLeaseType () 
	{
		return (String)get_Value(COLUMNNAME_AircraftLeaseType);
	}

	/** Set Airframe Time.
		@param AirframeTime 
		The total time on the airframe.
	  */
	public void setAirframeTime (BigDecimal AirframeTime)
	{
		set_ValueNoCheck (COLUMNNAME_AirframeTime, AirframeTime);
	}

	/** Get Airframe Time.
		@return The total time on the airframe.
	  */
	public BigDecimal getAirframeTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AirframeTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Avg Fuel Consumption.
		@param AvgFuelConsumption 
		Avg Fuel Consumption during flight operations in the aicraft unit of measure.
	  */
	public void setAvgFuelConsumption (BigDecimal AvgFuelConsumption)
	{
		set_Value (COLUMNNAME_AvgFuelConsumption, AvgFuelConsumption);
	}

	/** Get Avg Fuel Consumption.
		@return Avg Fuel Consumption during flight operations in the aicraft unit of measure.
	  */
	public BigDecimal getAvgFuelConsumption () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AvgFuelConsumption);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Call Sign.
		@param CallSign 
		The unique aircraft call sign or registration marks.
	  */
	public void setCallSign (String CallSign)
	{
		set_Value (COLUMNNAME_CallSign, CallSign);
	}

	/** Get Call Sign.
		@return The unique aircraft call sign or registration marks.
	  */
	public String getCallSign () 
	{
		return (String)get_Value(COLUMNNAME_CallSign);
	}

	/** Set Lease Expiry Date.
		@param DateExpiryLease 
		The date the lease expires.
	  */
	public void setDateExpiryLease (Timestamp DateExpiryLease)
	{
		set_Value (COLUMNNAME_DateExpiryLease, DateExpiryLease);
	}

	/** Get Lease Expiry Date.
		@return The date the lease expires.
	  */
	public Timestamp getDateExpiryLease () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateExpiryLease);
	}

	/** Set Start Date.
		@param DateStartLease 
		The start date of the lease
	  */
	public void setDateStartLease (Timestamp DateStartLease)
	{
		set_Value (COLUMNNAME_DateStartLease, DateStartLease);
	}

	/** Get Start Date.
		@return The start date of the lease
	  */
	public Timestamp getDateStartLease () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStartLease);
	}

	/** Set Days to Inspection.
		@param DaysToInspection 
		The number of calendar days until the inspection is due.
	  */
	public void setDaysToInspection (String DaysToInspection)
	{
		throw new IllegalArgumentException ("DaysToInspection is virtual column");	}

	/** Get Days to Inspection.
		@return The number of calendar days until the inspection is due.
	  */
	public String getDaysToInspection () 
	{
		return (String)get_Value(COLUMNNAME_DaysToInspection);
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

	/** Set ELT Beacon ID Code.
		@param ELTBeaconIDCode 
		Emergency Locator Trasmitter Beacon ID Code
	  */
	public void setELTBeaconIDCode (String ELTBeaconIDCode)
	{
		set_Value (COLUMNNAME_ELTBeaconIDCode, ELTBeaconIDCode);
	}

	/** Get ELT Beacon ID Code.
		@return Emergency Locator Trasmitter Beacon ID Code
	  */
	public String getELTBeaconIDCode () 
	{
		return (String)get_Value(COLUMNNAME_ELTBeaconIDCode);
	}

	/** ELTType AD_Reference_ID=1000052 */
	public static final int ELTTYPE_AD_Reference_ID=1000052;
	/** 406 MHz = 406 */
	public static final String ELTTYPE_406MHz = "406";
	/** 121.5 243 MHz = 121.5/243 */
	public static final String ELTTYPE_1215243MHz = "121.5/243";
	/** Set ELT Type.
		@param ELTType 
		The type of ELT installed in the aircraft.
	  */
	public void setELTType (String ELTType)
	{

		set_Value (COLUMNNAME_ELTType, ELTType);
	}

	/** Get ELT Type.
		@return The type of ELT installed in the aircraft.
	  */
	public String getELTType () 
	{
		return (String)get_Value(COLUMNNAME_ELTType);
	}

	/** Set Flight Log Open Date.
		@param FltLogOpenDate 
		The date the electronic flight log was started.
	  */
	public void setFltLogOpenDate (Timestamp FltLogOpenDate)
	{
		set_Value (COLUMNNAME_FltLogOpenDate, FltLogOpenDate);
	}

	/** Get Flight Log Open Date.
		@return The date the electronic flight log was started.
	  */
	public Timestamp getFltLogOpenDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FltLogOpenDate);
	}

	/** Set Flight Log Open Airframe Time.
		@param FltLogOpenTime 
		The total airframe time at the start of the electronic log.
	  */
	public void setFltLogOpenTime (BigDecimal FltLogOpenTime)
	{
		set_Value (COLUMNNAME_FltLogOpenTime, FltLogOpenTime);
	}

	/** Get Flight Log Open Airframe Time.
		@return The total airframe time at the start of the electronic log.
	  */
	public BigDecimal getFltLogOpenTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FltLogOpenTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

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

	/** Set Fuel Capacity.
		@param FuelCapacity 
		The amount of fuel that can be carried on the plane.
	  */
	public void setFuelCapacity (BigDecimal FuelCapacity)
	{
		set_Value (COLUMNNAME_FuelCapacity, FuelCapacity);
	}

	/** Get Fuel Capacity.
		@return The amount of fuel that can be carried on the plane.
	  */
	public BigDecimal getFuelCapacity () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FuelCapacity);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_Charge getFuelCharg() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getFuelChargeID(), get_TrxName());	}

	/** Set Fuel Charge.
		@param FuelChargeID 
		Charge to use for internal use of fuel
	  */
	public void setFuelChargeID (int FuelChargeID)
	{
		set_Value (COLUMNNAME_FuelChargeID, Integer.valueOf(FuelChargeID));
	}

	/** Get Fuel Charge.
		@return Charge to use for internal use of fuel
	  */
	public int getFuelChargeID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FuelChargeID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fuel Grade.
		@param FuelGrade 
		The fuel grade used by the aircraft
	  */
	public void setFuelGrade (String FuelGrade)
	{
		set_Value (COLUMNNAME_FuelGrade, FuelGrade);
	}

	/** Get Fuel Grade.
		@return The fuel grade used by the aircraft
	  */
	public String getFuelGrade () 
	{
		return (String)get_Value(COLUMNNAME_FuelGrade);
	}

	public org.compiere.model.I_M_Product getFuelProduc() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getFuelProductID(), get_TrxName());	}

	/** Set Fuel Product.
		@param FuelProductID 
		The product for the fuel consummed by the aircraft
	  */
	public void setFuelProductID (int FuelProductID)
	{
		set_Value (COLUMNNAME_FuelProductID, Integer.valueOf(FuelProductID));
	}

	/** Get Fuel Product.
		@return The product for the fuel consummed by the aircraft
	  */
	public int getFuelProductID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FuelProductID);
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

	/** Set Leased.
		@param IsACLeased 
		Is the aircraft leased on an operating lease?
	  */
	public void setIsACLeased (boolean IsACLeased)
	{
		set_Value (COLUMNNAME_IsACLeased, Boolean.valueOf(IsACLeased));
	}

	/** Get Leased.
		@return Is the aircraft leased on an operating lease?
	  */
	public boolean isACLeased () 
	{
		Object oo = get_Value(COLUMNNAME_IsACLeased);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Generic.
		@param IsGeneric 
		If selected, the record is a generic place holder used when a specific resource or item is not available.
	  */
	public void setIsGeneric (boolean IsGeneric)
	{
		set_Value (COLUMNNAME_IsGeneric, Boolean.valueOf(IsGeneric));
	}

	/** Get Generic.
		@return If selected, the record is a generic place holder used when a specific resource or item is not available.
	  */
	public boolean isGeneric () 
	{
		Object oo = get_Value(COLUMNNAME_IsGeneric);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set On Tolerance.
		@param IsOnTolerance 
		Is the maintenance due date/hours on tolerance?
	  */
	public void setIsOnTolerance (boolean IsOnTolerance)
	{
		set_Value (COLUMNNAME_IsOnTolerance, Boolean.valueOf(IsOnTolerance));
	}

	/** Get On Tolerance.
		@return Is the maintenance due date/hours on tolerance?
	  */
	public boolean isOnTolerance () 
	{
		Object oo = get_Value(COLUMNNAME_IsOnTolerance);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Simulator.
		@param isSim 
		Is the entry related to a simulator.
	  */
	public void setisSim (boolean isSim)
	{
		set_Value (COLUMNNAME_isSim, Boolean.valueOf(isSim));
	}

	/** Get Simulator.
		@return Is the entry related to a simulator.
	  */
	public boolean isSim () 
	{
		Object oo = get_Value(COLUMNNAME_isSim);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Last Flight Down.
		@param Last_Flight_Down 
		Landing time of the last flight.
	  */
	public void setLast_Flight_Down (Timestamp Last_Flight_Down)
	{
		set_ValueNoCheck (COLUMNNAME_Last_Flight_Down, Last_Flight_Down);
	}

	/** Get Last Flight Down.
		@return Landing time of the last flight.
	  */
	public Timestamp getLast_Flight_Down () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Last_Flight_Down);
	}

	/** Set Last Max Airframe Time.
		@param Last_Max_Airframe_Time 
		The total time on the airframe of the last entry in the Journey Log.
	  */
	public void setLast_Max_Airframe_Time (BigDecimal Last_Max_Airframe_Time)
	{
		set_ValueNoCheck (COLUMNNAME_Last_Max_Airframe_Time, Last_Max_Airframe_Time);
	}

	/** Get Last Max Airframe Time.
		@return The total time on the airframe of the last entry in the Journey Log.
	  */
	public BigDecimal getLast_Max_Airframe_Time () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Last_Max_Airframe_Time);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Is Tax Applied?.
		@param Lease_IsTaxApplied 
		Is tax applied to the lease payments?
	  */
	public void setLease_IsTaxApplied (boolean Lease_IsTaxApplied)
	{
		set_Value (COLUMNNAME_Lease_IsTaxApplied, Boolean.valueOf(Lease_IsTaxApplied));
	}

	/** Get Is Tax Applied?.
		@return Is tax applied to the lease payments?
	  */
	public boolean isLease_IsTaxApplied () 
	{
		Object oo = get_Value(COLUMNNAME_Lease_IsTaxApplied);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Lease_QtyType AD_Reference_ID=1000045 */
	public static final int LEASE_QTYTYPE_AD_Reference_ID=1000045;
	/** FMFL 3-Tiere Lease Terms = FMFL-3TierLease */
	public static final String LEASE_QTYTYPE_FMFL3_TiereLeaseTerms = "FMFL-3TierLease";
	/** Monthly Fixed = Monthly */
	public static final String LEASE_QTYTYPE_MonthlyFixed = "Monthly";
	/** Airtime in period = Airtime */
	public static final String LEASE_QTYTYPE_AirtimeInPeriod = "Airtime";
	/** Flight time in period = Flight Time */
	public static final String LEASE_QTYTYPE_FlightTimeInPeriod = "Flight Time";
	/** Set Quantity Type.
		@param Lease_QtyType 
		The type of lease payment and the quantity to use. Fixed, hourly etc.
	  */
	public void setLease_QtyType (String Lease_QtyType)
	{

		set_Value (COLUMNNAME_Lease_QtyType, Lease_QtyType);
	}

	/** Get Quantity Type.
		@return The type of lease payment and the quantity to use. Fixed, hourly etc.
	  */
	public String getLease_QtyType () 
	{
		return (String)get_Value(COLUMNNAME_Lease_QtyType);
	}

	/** Set Max Hours.
		@param LeaseMaxHours 
		The total hours which, when billed, will conclude the lease agreement.
	  */
	public void setLeaseMaxHours (BigDecimal LeaseMaxHours)
	{
		set_Value (COLUMNNAME_LeaseMaxHours, LeaseMaxHours);
	}

	/** Get Max Hours.
		@return The total hours which, when billed, will conclude the lease agreement.
	  */
	public BigDecimal getLeaseMaxHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseMaxHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min Monthly Hours.
		@param LeaseMinMonthlyHours 
		The minimum hours in the period that must be billed regardless of the hours that were actually flown.
	  */
	public void setLeaseMinMonthlyHours (BigDecimal LeaseMinMonthlyHours)
	{
		set_Value (COLUMNNAME_LeaseMinMonthlyHours, LeaseMinMonthlyHours);
	}

	/** Get Min Monthly Hours.
		@return The minimum hours in the period that must be billed regardless of the hours that were actually flown.
	  */
	public BigDecimal getLeaseMinMonthlyHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseMinMonthlyHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Lease Rate.
		@param LeaseRateFixed 
		The $ lease rate to use.
	  */
	public void setLeaseRateFixed (BigDecimal LeaseRateFixed)
	{
		set_Value (COLUMNNAME_LeaseRateFixed, LeaseRateFixed);
	}

	/** Get Lease Rate.
		@return The $ lease rate to use.
	  */
	public BigDecimal getLeaseRateFixed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseRateFixed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tier-1 Rate.
		@param LeaseRateTier1 
		For multi-tier lease rates
	  */
	public void setLeaseRateTier1 (BigDecimal LeaseRateTier1)
	{
		set_Value (COLUMNNAME_LeaseRateTier1, LeaseRateTier1);
	}

	/** Get Tier-1 Rate.
		@return For multi-tier lease rates
	  */
	public BigDecimal getLeaseRateTier1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseRateTier1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tier-1 Max Hours.
		@param LeaseRateTier1_MaxHours 
		The maximum hours in a period where the Tier 1 rate applies.
	  */
	public void setLeaseRateTier1_MaxHours (BigDecimal LeaseRateTier1_MaxHours)
	{
		set_Value (COLUMNNAME_LeaseRateTier1_MaxHours, LeaseRateTier1_MaxHours);
	}

	/** Get Tier-1 Max Hours.
		@return The maximum hours in a period where the Tier 1 rate applies.
	  */
	public BigDecimal getLeaseRateTier1_MaxHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseRateTier1_MaxHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tier-2 Rate.
		@param LeaseRateTier2 
		For multi-tier lease rates
	  */
	public void setLeaseRateTier2 (BigDecimal LeaseRateTier2)
	{
		set_Value (COLUMNNAME_LeaseRateTier2, LeaseRateTier2);
	}

	/** Get Tier-2 Rate.
		@return For multi-tier lease rates
	  */
	public BigDecimal getLeaseRateTier2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseRateTier2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tier-2 Max Hours.
		@param LeaseRateTier2_MaxHours 
		The maximum hours in a period where the Tier 2 rate applies.
	  */
	public void setLeaseRateTier2_MaxHours (BigDecimal LeaseRateTier2_MaxHours)
	{
		set_Value (COLUMNNAME_LeaseRateTier2_MaxHours, LeaseRateTier2_MaxHours);
	}

	/** Get Tier-2 Max Hours.
		@return The maximum hours in a period where the Tier 2 rate applies.
	  */
	public BigDecimal getLeaseRateTier2_MaxHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseRateTier2_MaxHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tier-3 Rate.
		@param LeaseRateTier3 
		For multi-tier lease rates
	  */
	public void setLeaseRateTier3 (BigDecimal LeaseRateTier3)
	{
		set_Value (COLUMNNAME_LeaseRateTier3, LeaseRateTier3);
	}

	/** Get Tier-3 Rate.
		@return For multi-tier lease rates
	  */
	public BigDecimal getLeaseRateTier3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseRateTier3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Roll Over Hours.
		@param LeaseRollOverHours 
		The limit of hours that must be paid each month.
	  */
	public void setLeaseRollOverHours (BigDecimal LeaseRollOverHours)
	{
		set_Value (COLUMNNAME_LeaseRollOverHours, LeaseRollOverHours);
	}

	/** Get Roll Over Hours.
		@return The limit of hours that must be paid each month.
	  */
	public BigDecimal getLeaseRollOverHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaseRollOverHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product getLeaseTier1Produc() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getLeaseTier1ProductID(), get_TrxName());	}

	/** Set Tier 1 Product.
		@param LeaseTier1ProductID 
		The product to be used when generating the invoice for lease payments.
	  */
	public void setLeaseTier1ProductID (int LeaseTier1ProductID)
	{
		set_Value (COLUMNNAME_LeaseTier1ProductID, Integer.valueOf(LeaseTier1ProductID));
	}

	/** Get Tier 1 Product.
		@return The product to be used when generating the invoice for lease payments.
	  */
	public int getLeaseTier1ProductID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeaseTier1ProductID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getLeaseTier2Produc() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getLeaseTier2ProductID(), get_TrxName());	}

	/** Set Tier 2 Product.
		@param LeaseTier2ProductID 
		The product to be used when generating the invoice for lease payments.
	  */
	public void setLeaseTier2ProductID (int LeaseTier2ProductID)
	{
		set_Value (COLUMNNAME_LeaseTier2ProductID, Integer.valueOf(LeaseTier2ProductID));
	}

	/** Get Tier 2 Product.
		@return The product to be used when generating the invoice for lease payments.
	  */
	public int getLeaseTier2ProductID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeaseTier2ProductID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getLeaseTier3Produc() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getLeaseTier3ProductID(), get_TrxName());	}

	/** Set Tier 3 Product.
		@param LeaseTier3ProductID 
		The product to be used when generating the invoice for lease payments.
	  */
	public void setLeaseTier3ProductID (int LeaseTier3ProductID)
	{
		set_Value (COLUMNNAME_LeaseTier3ProductID, Integer.valueOf(LeaseTier3ProductID));
	}

	/** Get Tier 3 Product.
		@return The product to be used when generating the invoice for lease payments.
	  */
	public int getLeaseTier3ProductID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeaseTier3ProductID);
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

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException
    {
		return (org.compiere.model.I_S_Resource)MTable.get(getCtx(), org.compiere.model.I_S_Resource.Table_Name)
			.getPO(getS_Resource_ID(), get_TrxName());	}

	/** Set Resource.
		@param S_Resource_ID 
		Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_Resource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Resource.
		@return Resource
	  */
	public int getS_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Taxi Fuel Consumption.
		@param TaxiFuelConsumption 
		The avg quantity of fuel consummed in taxi operations prior and after a flight, in the aircraft unit of measure.
	  */
	public void setTaxiFuelConsumption (BigDecimal TaxiFuelConsumption)
	{
		set_Value (COLUMNNAME_TaxiFuelConsumption, TaxiFuelConsumption);
	}

	/** Get Taxi Fuel Consumption.
		@return The avg quantity of fuel consummed in taxi operations prior and after a flight, in the aircraft unit of measure.
	  */
	public BigDecimal getTaxiFuelConsumption () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxiFuelConsumption);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Time to Inspection.
		@param TimeToInspection 
		The airtime hours until inspection is due.
	  */
	public void setTimeToInspection (BigDecimal TimeToInspection)
	{
		set_Value (COLUMNNAME_TimeToInspection, TimeToInspection);
	}

	/** Get Time to Inspection.
		@return The airtime hours until inspection is due.
	  */
	public BigDecimal getTimeToInspection () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TimeToInspection);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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