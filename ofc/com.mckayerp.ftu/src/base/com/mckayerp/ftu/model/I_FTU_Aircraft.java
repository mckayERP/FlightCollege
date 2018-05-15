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
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FTU_Aircraft
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Aircraft 
{

    /** TableName=FTU_Aircraft */
    public static final String Table_Name = "FTU_Aircraft";

    /** AD_Table_ID=1000034 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException;

    /** Column name ACMaintQtyType */
    public static final String COLUMNNAME_ACMaintQtyType = "ACMaintQtyType";

	/** Set Maintenance Qty Type.
	  * The type of hours to use in calculating the maintenance numbers
	  */
	public void setACMaintQtyType (String ACMaintQtyType);

	/** Get Maintenance Qty Type.
	  * The type of hours to use in calculating the maintenance numbers
	  */
	public String getACMaintQtyType();

    /** Column name ACMaintRate */
    public static final String COLUMNNAME_ACMaintRate = "ACMaintRate";

	/** Set Maint Rate ($/hr).
	  * Maintenance rate in cost per dollars/hour.
	  */
	public void setACMaintRate (BigDecimal ACMaintRate);

	/** Get Maint Rate ($/hr).
	  * Maintenance rate in cost per dollars/hour.
	  */
	public BigDecimal getACMaintRate();

    /** Column name ACManufacturer */
    public static final String COLUMNNAME_ACManufacturer = "ACManufacturer";

	/** Set AC Manufacturer.
	  * The aircraft manufacturer
	  */
	public void setACManufacturer (String ACManufacturer);

	/** Get AC Manufacturer.
	  * The aircraft manufacturer
	  */
	public String getACManufacturer();

    /** Column name ACMaxGrossWeight */
    public static final String COLUMNNAME_ACMaxGrossWeight = "ACMaxGrossWeight";

	/** Set Max Gross Weight	  */
	public void setACMaxGrossWeight (BigDecimal ACMaxGrossWeight);

	/** Get Max Gross Weight	  */
	public BigDecimal getACMaxGrossWeight();

    /** Column name ACModel */
    public static final String COLUMNNAME_ACModel = "ACModel";

	/** Set Model.
	  * The model of the aircraft
	  */
	public void setACModel (String ACModel);

	/** Get Model.
	  * The model of the aircraft
	  */
	public String getACModel();

    /** Column name ACNextMaintDate */
    public static final String COLUMNNAME_ACNextMaintDate = "ACNextMaintDate";

	/** Set Next Maint Date.
	  * The date the next maintenance action is due.
	  */
	public void setACNextMaintDate (Timestamp ACNextMaintDate);

	/** Get Next Maint Date.
	  * The date the next maintenance action is due.
	  */
	public Timestamp getACNextMaintDate();

    /** Column name ACNextMaintDateTol */
    public static final String COLUMNNAME_ACNextMaintDateTol = "ACNextMaintDateTol";

	/** Set Next Maint Date Tolerance.
	  * The tolerance in days around the next maintenance date.
	  */
	public void setACNextMaintDateTol (BigDecimal ACNextMaintDateTol);

	/** Get Next Maint Date Tolerance.
	  * The tolerance in days around the next maintenance date.
	  */
	public BigDecimal getACNextMaintDateTol();

    /** Column name ACNextMaintHrs */
    public static final String COLUMNNAME_ACNextMaintHrs = "ACNextMaintHrs";

	/** Set Next Maint Hours.
	  * The Airframe hours when the next maintenance action is due.
	  */
	public void setACNextMaintHrs (BigDecimal ACNextMaintHrs);

	/** Get Next Maint Hours.
	  * The Airframe hours when the next maintenance action is due.
	  */
	public BigDecimal getACNextMaintHrs();

    /** Column name ACNextMaintHrsTol */
    public static final String COLUMNNAME_ACNextMaintHrsTol = "ACNextMaintHrsTol";

	/** Set Next Maint Hours Tolerance.
	  * The tolerance in hours use around the next maintenance due.
	  */
	public void setACNextMaintHrsTol (BigDecimal ACNextMaintHrsTol);

	/** Get Next Maint Hours Tolerance.
	  * The tolerance in hours use around the next maintenance due.
	  */
	public BigDecimal getACNextMaintHrsTol();

    /** Column name ACOilGrade */
    public static final String COLUMNNAME_ACOilGrade = "ACOilGrade";

	/** Set Oil Grade.
	  * The grade of oil used in the engine.
	  */
	public void setACOilGrade (String ACOilGrade);

	/** Get Oil Grade.
	  * The grade of oil used in the engine.
	  */
	public String getACOilGrade();

    /** Column name ACRegistration */
    public static final String COLUMNNAME_ACRegistration = "ACRegistration";

	/** Set Registration.
	  * The national registration marks for the aircraft
	  */
	public void setACRegistration (String ACRegistration);

	/** Get Registration.
	  * The national registration marks for the aircraft
	  */
	public String getACRegistration();

    /** Column name ACSerialNumber */
    public static final String COLUMNNAME_ACSerialNumber = "ACSerialNumber";

	/** Set Serial Number.
	  * The serial number of the plane.
	  */
	public void setACSerialNumber (String ACSerialNumber);

	/** Get Serial Number.
	  * The serial number of the plane.
	  */
	public String getACSerialNumber();

    /** Column name ACStatus */
    public static final String COLUMNNAME_ACStatus = "ACStatus";

	/** Set Aircraft Status	  */
	public void setACStatus (String ACStatus);

	/** Get Aircraft Status	  */
	public String getACStatus();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AircraftLeaseType */
    public static final String COLUMNNAME_AircraftLeaseType = "AircraftLeaseType";

	/** Set Lease Type.
	  * Capital or Expense
	  */
	public void setAircraftLeaseType (String AircraftLeaseType);

	/** Get Lease Type.
	  * Capital or Expense
	  */
	public String getAircraftLeaseType();

    /** Column name AirframeTime */
    public static final String COLUMNNAME_AirframeTime = "AirframeTime";

	/** Set Airframe Time.
	  * The total time on the airframe.
	  */
	public void setAirframeTime (BigDecimal AirframeTime);

	/** Get Airframe Time.
	  * The total time on the airframe.
	  */
	public BigDecimal getAirframeTime();

    /** Column name AvgFuelConsumption */
    public static final String COLUMNNAME_AvgFuelConsumption = "AvgFuelConsumption";

	/** Set Avg Fuel Consumption.
	  * Avg Fuel Consumption during flight operations in the aicraft unit of measure.
	  */
	public void setAvgFuelConsumption (BigDecimal AvgFuelConsumption);

	/** Get Avg Fuel Consumption.
	  * Avg Fuel Consumption during flight operations in the aicraft unit of measure.
	  */
	public BigDecimal getAvgFuelConsumption();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name CallSign */
    public static final String COLUMNNAME_CallSign = "CallSign";

	/** Set Call Sign.
	  * The unique aircraft call sign or registration marks.
	  */
	public void setCallSign (String CallSign);

	/** Get Call Sign.
	  * The unique aircraft call sign or registration marks.
	  */
	public String getCallSign();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CT_Component_ID */
    public static final String COLUMNNAME_CT_Component_ID = "CT_Component_ID";

	/** Set Component.
	  * A component of an assembly or asset.
	  */
	public void setCT_Component_ID (int CT_Component_ID);

	/** Get Component.
	  * A component of an assembly or asset.
	  */
	public int getCT_Component_ID();

	public com.mckayerp.model.I_CT_Component getCT_Component() throws RuntimeException;

    /** Column name DateExpiryLease */
    public static final String COLUMNNAME_DateExpiryLease = "DateExpiryLease";

	/** Set Lease Expiry Date.
	  * The date the lease expires.
	  */
	public void setDateExpiryLease (Timestamp DateExpiryLease);

	/** Get Lease Expiry Date.
	  * The date the lease expires.
	  */
	public Timestamp getDateExpiryLease();

    /** Column name DateStartLease */
    public static final String COLUMNNAME_DateStartLease = "DateStartLease";

	/** Set Start Date.
	  * The start date of the lease
	  */
	public void setDateStartLease (Timestamp DateStartLease);

	/** Get Start Date.
	  * The start date of the lease
	  */
	public Timestamp getDateStartLease();

    /** Column name DaysToInspection */
    public static final String COLUMNNAME_DaysToInspection = "DaysToInspection";

	/** Set Days to Inspection.
	  * The number of calendar days until the inspection is due.
	  */
	public void setDaysToInspection (String DaysToInspection);

	/** Get Days to Inspection.
	  * The number of calendar days until the inspection is due.
	  */
	public String getDaysToInspection();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name ELTBeaconIDCode */
    public static final String COLUMNNAME_ELTBeaconIDCode = "ELTBeaconIDCode";

	/** Set ELT Beacon ID Code.
	  * Emergency Locator Trasmitter Beacon ID Code
	  */
	public void setELTBeaconIDCode (String ELTBeaconIDCode);

	/** Get ELT Beacon ID Code.
	  * Emergency Locator Trasmitter Beacon ID Code
	  */
	public String getELTBeaconIDCode();

    /** Column name ELTType */
    public static final String COLUMNNAME_ELTType = "ELTType";

	/** Set ELT Type.
	  * The type of ELT installed in the aircraft.
	  */
	public void setELTType (String ELTType);

	/** Get ELT Type.
	  * The type of ELT installed in the aircraft.
	  */
	public String getELTType();

    /** Column name FltLogOpenDate */
    public static final String COLUMNNAME_FltLogOpenDate = "FltLogOpenDate";

	/** Set Flight Log Open Date.
	  * The date the electronic flight log was started.
	  */
	public void setFltLogOpenDate (Timestamp FltLogOpenDate);

	/** Get Flight Log Open Date.
	  * The date the electronic flight log was started.
	  */
	public Timestamp getFltLogOpenDate();

    /** Column name FltLogOpenTime */
    public static final String COLUMNNAME_FltLogOpenTime = "FltLogOpenTime";

	/** Set Flight Log Open Airframe Time.
	  * The total airframe time at the start of the electronic log.
	  */
	public void setFltLogOpenTime (BigDecimal FltLogOpenTime);

	/** Get Flight Log Open Airframe Time.
	  * The total airframe time at the start of the electronic log.
	  */
	public BigDecimal getFltLogOpenTime();

    /** Column name FTU_Aircraft_ID */
    public static final String COLUMNNAME_FTU_Aircraft_ID = "FTU_Aircraft_ID";

	/** Set Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID);

	/** Get Aircraft	  */
	public int getFTU_Aircraft_ID();

    /** Column name FuelCapacity */
    public static final String COLUMNNAME_FuelCapacity = "FuelCapacity";

	/** Set Fuel Capacity.
	  * The amount of fuel that can be carried on the plane.
	  */
	public void setFuelCapacity (BigDecimal FuelCapacity);

	/** Get Fuel Capacity.
	  * The amount of fuel that can be carried on the plane.
	  */
	public BigDecimal getFuelCapacity();

    /** Column name FuelChargeID */
    public static final String COLUMNNAME_FuelChargeID = "FuelChargeID";

	/** Set Fuel Charge.
	  * Charge to use for internal use of fuel
	  */
	public void setFuelChargeID (int FuelChargeID);

	/** Get Fuel Charge.
	  * Charge to use for internal use of fuel
	  */
	public int getFuelChargeID();

	public org.compiere.model.I_C_Charge getFuelCharg() throws RuntimeException;

    /** Column name FuelGrade */
    public static final String COLUMNNAME_FuelGrade = "FuelGrade";

	/** Set Fuel Grade.
	  * The fuel grade used by the aircraft
	  */
	public void setFuelGrade (String FuelGrade);

	/** Get Fuel Grade.
	  * The fuel grade used by the aircraft
	  */
	public String getFuelGrade();

    /** Column name FuelProductID */
    public static final String COLUMNNAME_FuelProductID = "FuelProductID";

	/** Set Fuel Product.
	  * The product for the fuel consummed by the aircraft
	  */
	public void setFuelProductID (int FuelProductID);

	/** Get Fuel Product.
	  * The product for the fuel consummed by the aircraft
	  */
	public int getFuelProductID();

	public org.compiere.model.I_M_Product getFuelProduc() throws RuntimeException;

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsACLeased */
    public static final String COLUMNNAME_IsACLeased = "IsACLeased";

	/** Set Leased.
	  * Is the aircraft leased on an operating lease?
	  */
	public void setIsACLeased (boolean IsACLeased);

	/** Get Leased.
	  * Is the aircraft leased on an operating lease?
	  */
	public boolean isACLeased();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsGeneric */
    public static final String COLUMNNAME_IsGeneric = "IsGeneric";

	/** Set Generic.
	  * If selected, the record is a generic place holder used when a specific resource or item is not available.
	  */
	public void setIsGeneric (boolean IsGeneric);

	/** Get Generic.
	  * If selected, the record is a generic place holder used when a specific resource or item is not available.
	  */
	public boolean isGeneric();

    /** Column name IsOnTolerance */
    public static final String COLUMNNAME_IsOnTolerance = "IsOnTolerance";

	/** Set On Tolerance.
	  * Is the maintenance due date/hours on tolerance?
	  */
	public void setIsOnTolerance (boolean IsOnTolerance);

	/** Get On Tolerance.
	  * Is the maintenance due date/hours on tolerance?
	  */
	public boolean isOnTolerance();

    /** Column name isSim */
    public static final String COLUMNNAME_isSim = "isSim";

	/** Set Simulator.
	  * Is the entry related to a simulator.
	  */
	public void setisSim (boolean isSim);

	/** Get Simulator.
	  * Is the entry related to a simulator.
	  */
	public boolean isSim();

    /** Column name Last_Flight_Down */
    public static final String COLUMNNAME_Last_Flight_Down = "Last_Flight_Down";

	/** Set Last Flight Down.
	  * Landing time of the last flight.
	  */
	public void setLast_Flight_Down (Timestamp Last_Flight_Down);

	/** Get Last Flight Down.
	  * Landing time of the last flight.
	  */
	public Timestamp getLast_Flight_Down();

    /** Column name Last_Max_Airframe_Time */
    public static final String COLUMNNAME_Last_Max_Airframe_Time = "Last_Max_Airframe_Time";

	/** Set Last Max Airframe Time.
	  * The total time on the airframe of the last entry in the Journey Log.
	  */
	public void setLast_Max_Airframe_Time (BigDecimal Last_Max_Airframe_Time);

	/** Get Last Max Airframe Time.
	  * The total time on the airframe of the last entry in the Journey Log.
	  */
	public BigDecimal getLast_Max_Airframe_Time();

    /** Column name Lease_IsTaxApplied */
    public static final String COLUMNNAME_Lease_IsTaxApplied = "Lease_IsTaxApplied";

	/** Set Is Tax Applied?.
	  * Is tax applied to the lease payments?
	  */
	public void setLease_IsTaxApplied (boolean Lease_IsTaxApplied);

	/** Get Is Tax Applied?.
	  * Is tax applied to the lease payments?
	  */
	public boolean isLease_IsTaxApplied();

    /** Column name Lease_QtyType */
    public static final String COLUMNNAME_Lease_QtyType = "Lease_QtyType";

	/** Set Quantity Type.
	  * The type of lease payment and the quantity to use. Fixed, hourly etc.
	  */
	public void setLease_QtyType (String Lease_QtyType);

	/** Get Quantity Type.
	  * The type of lease payment and the quantity to use. Fixed, hourly etc.
	  */
	public String getLease_QtyType();

    /** Column name LeaseMaxHours */
    public static final String COLUMNNAME_LeaseMaxHours = "LeaseMaxHours";

	/** Set Max Hours.
	  * The total hours which, when billed, will conclude the lease agreement.
	  */
	public void setLeaseMaxHours (BigDecimal LeaseMaxHours);

	/** Get Max Hours.
	  * The total hours which, when billed, will conclude the lease agreement.
	  */
	public BigDecimal getLeaseMaxHours();

    /** Column name LeaseMinMonthlyHours */
    public static final String COLUMNNAME_LeaseMinMonthlyHours = "LeaseMinMonthlyHours";

	/** Set Min Monthly Hours.
	  * The minimum hours in the period that must be billed regardless of the hours that were actually flown.
	  */
	public void setLeaseMinMonthlyHours (BigDecimal LeaseMinMonthlyHours);

	/** Get Min Monthly Hours.
	  * The minimum hours in the period that must be billed regardless of the hours that were actually flown.
	  */
	public BigDecimal getLeaseMinMonthlyHours();

    /** Column name LeaseRateFixed */
    public static final String COLUMNNAME_LeaseRateFixed = "LeaseRateFixed";

	/** Set Lease Rate.
	  * The $ lease rate to use.
	  */
	public void setLeaseRateFixed (BigDecimal LeaseRateFixed);

	/** Get Lease Rate.
	  * The $ lease rate to use.
	  */
	public BigDecimal getLeaseRateFixed();

    /** Column name LeaseRateTier1 */
    public static final String COLUMNNAME_LeaseRateTier1 = "LeaseRateTier1";

	/** Set Tier-1 Rate.
	  * For multi-tier lease rates
	  */
	public void setLeaseRateTier1 (BigDecimal LeaseRateTier1);

	/** Get Tier-1 Rate.
	  * For multi-tier lease rates
	  */
	public BigDecimal getLeaseRateTier1();

    /** Column name LeaseRateTier1_MaxHours */
    public static final String COLUMNNAME_LeaseRateTier1_MaxHours = "LeaseRateTier1_MaxHours";

	/** Set Tier-1 Max Hours.
	  * The maximum hours in a period where the Tier 1 rate applies.
	  */
	public void setLeaseRateTier1_MaxHours (BigDecimal LeaseRateTier1_MaxHours);

	/** Get Tier-1 Max Hours.
	  * The maximum hours in a period where the Tier 1 rate applies.
	  */
	public BigDecimal getLeaseRateTier1_MaxHours();

    /** Column name LeaseRateTier2 */
    public static final String COLUMNNAME_LeaseRateTier2 = "LeaseRateTier2";

	/** Set Tier-2 Rate.
	  * For multi-tier lease rates
	  */
	public void setLeaseRateTier2 (BigDecimal LeaseRateTier2);

	/** Get Tier-2 Rate.
	  * For multi-tier lease rates
	  */
	public BigDecimal getLeaseRateTier2();

    /** Column name LeaseRateTier2_MaxHours */
    public static final String COLUMNNAME_LeaseRateTier2_MaxHours = "LeaseRateTier2_MaxHours";

	/** Set Tier-2 Max Hours.
	  * The maximum hours in a period where the Tier 2 rate applies.
	  */
	public void setLeaseRateTier2_MaxHours (BigDecimal LeaseRateTier2_MaxHours);

	/** Get Tier-2 Max Hours.
	  * The maximum hours in a period where the Tier 2 rate applies.
	  */
	public BigDecimal getLeaseRateTier2_MaxHours();

    /** Column name LeaseRateTier3 */
    public static final String COLUMNNAME_LeaseRateTier3 = "LeaseRateTier3";

	/** Set Tier-3 Rate.
	  * For multi-tier lease rates
	  */
	public void setLeaseRateTier3 (BigDecimal LeaseRateTier3);

	/** Get Tier-3 Rate.
	  * For multi-tier lease rates
	  */
	public BigDecimal getLeaseRateTier3();

    /** Column name LeaseRollOverHours */
    public static final String COLUMNNAME_LeaseRollOverHours = "LeaseRollOverHours";

	/** Set Roll Over Hours.
	  * The limit of hours that must be paid each month.
	  */
	public void setLeaseRollOverHours (BigDecimal LeaseRollOverHours);

	/** Get Roll Over Hours.
	  * The limit of hours that must be paid each month.
	  */
	public BigDecimal getLeaseRollOverHours();

    /** Column name LeaseTier1ProductID */
    public static final String COLUMNNAME_LeaseTier1ProductID = "LeaseTier1ProductID";

	/** Set Tier 1 Product.
	  * The product to be used when generating the invoice for lease payments.
	  */
	public void setLeaseTier1ProductID (int LeaseTier1ProductID);

	/** Get Tier 1 Product.
	  * The product to be used when generating the invoice for lease payments.
	  */
	public int getLeaseTier1ProductID();

	public org.compiere.model.I_M_Product getLeaseTier1Produc() throws RuntimeException;

    /** Column name LeaseTier2ProductID */
    public static final String COLUMNNAME_LeaseTier2ProductID = "LeaseTier2ProductID";

	/** Set Tier 2 Product.
	  * The product to be used when generating the invoice for lease payments.
	  */
	public void setLeaseTier2ProductID (int LeaseTier2ProductID);

	/** Get Tier 2 Product.
	  * The product to be used when generating the invoice for lease payments.
	  */
	public int getLeaseTier2ProductID();

	public org.compiere.model.I_M_Product getLeaseTier2Produc() throws RuntimeException;

    /** Column name LeaseTier3ProductID */
    public static final String COLUMNNAME_LeaseTier3ProductID = "LeaseTier3ProductID";

	/** Set Tier 3 Product.
	  * The product to be used when generating the invoice for lease payments.
	  */
	public void setLeaseTier3ProductID (int LeaseTier3ProductID);

	/** Get Tier 3 Product.
	  * The product to be used when generating the invoice for lease payments.
	  */
	public int getLeaseTier3ProductID();

	public org.compiere.model.I_M_Product getLeaseTier3Produc() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name RowStatus */
    public static final String COLUMNNAME_RowStatus = "RowStatus";

	/** Set Row Status.
	  * A code that indicates the status of a row.
	  */
	public void setRowStatus (BigDecimal RowStatus);

	/** Get Row Status.
	  * A code that indicates the status of a row.
	  */
	public BigDecimal getRowStatus();

    /** Column name S_Resource_ID */
    public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

	/** Set Resource.
	  * Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID);

	/** Get Resource.
	  * Resource
	  */
	public int getS_Resource_ID();

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException;

    /** Column name TaxiFuelConsumption */
    public static final String COLUMNNAME_TaxiFuelConsumption = "TaxiFuelConsumption";

	/** Set Taxi Fuel Consumption.
	  * The avg quantity of fuel consummed in taxi operations prior and after a flight, in the aircraft unit of measure.
	  */
	public void setTaxiFuelConsumption (BigDecimal TaxiFuelConsumption);

	/** Get Taxi Fuel Consumption.
	  * The avg quantity of fuel consummed in taxi operations prior and after a flight, in the aircraft unit of measure.
	  */
	public BigDecimal getTaxiFuelConsumption();

    /** Column name TimeToInspection */
    public static final String COLUMNNAME_TimeToInspection = "TimeToInspection";

	/** Set Time to Inspection.
	  * The airtime hours until inspection is due.
	  */
	public void setTimeToInspection (BigDecimal TimeToInspection);

	/** Get Time to Inspection.
	  * The airtime hours until inspection is due.
	  */
	public BigDecimal getTimeToInspection();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
