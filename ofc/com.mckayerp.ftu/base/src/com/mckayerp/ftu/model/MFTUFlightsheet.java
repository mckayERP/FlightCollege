/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2016 McKayERP Inc. All Rights Reserved.                *
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
 * via info@adev.org or http://www.adev.org/license.html                      *
 */
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MColumn;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMigration;
import org.compiere.model.MMigrationData;
import org.compiere.model.MMigrationStep;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MResourceAssignment;
import org.compiere.model.MUOMConversion;
import org.compiere.model.Query;
import org.compiere.model.MOrder;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author mckayERP
 *
 */
public class MFTUFlightsheet extends X_FTU_Flightsheet {

	private static final long serialVersionUID = -2365898440532283687L;
	
	/** Warehouse to use */
	private int	m_Warehouse_ID = 1000000;
	
	private boolean processing = false;

	/** Standard Constructor */
	public MFTUFlightsheet(Properties ctx, int FTU_Flightsheet_ID, String trxName) {
		super(ctx, FTU_Flightsheet_ID, trxName);
	}

    /** Load Constructor */
    public MFTUFlightsheet (Properties ctx, ResultSet rs, String trxName) {
      super (ctx, rs, trxName);
    }

    /**
     * Get the Flightsheet entry based on the Flightsheet Flight ID
     * @param ctx
     * @param flightID - from the Flightsheet system
     * @param trxName
     * @return MFlightsheet or a new entry if not found.
     */
	public static MFTUFlightsheet getByFlightID(Properties ctx, BigDecimal flightID, String trxName) {
		
		if (flightID == null)
			flightID = Env.ZERO;
		
		String whereClause = "FlightID = " + flightID.intValue();
		MFTUFlightsheet flight = (MFTUFlightsheet) new Query(ctx, MFTUFlightsheet.Table_Name, whereClause, trxName)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.first();
		
		if (flight == null) {
			flight = new MFTUFlightsheet(ctx,0,trxName);
			flight.setFlightID(flightID.intValue());
		}
		
		return flight;
	}
	
	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		// Check that the essentials have been set
		if (this.getFlightID() == 0) {
			log.saveError("Error", "FlightID cannot be zero/null");
			return false;
		}
		
		if (this.getCourseType() == null) {
			log.saveError("Error", "Course Type cannot be null. Flightsheet FlightID = " + this.getFlightID());
			return false;
		}	
		return true;
	}	//	beforeSave

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success) {
		// Generate an order for each billable flightsheet line.
		
		if (processing)
			return success;
		
		processing = true;
		// Ignore cancelled and pending flightsheet entries
		if (newRecord && (this.getCourseType().matches("Cancelled") || 
	    			this.getCourseType().matches("\\(pending\\)"))) {
			return success;
		}
		
		success = generateOrder(newRecord, success);
		success = consumeFuel(newRecord, success);
		success = updateJourneyLog(newRecord, success);
		
		return success;
	}
	
	private boolean updateJourneyLog(boolean newRecord, boolean success) {

		if (getFTU_ACJourneyLog_ID() > 0 )
			return success;

		setFTU_ACJourneyLog_ID(MFTUACJourneyLog.updateLog(getCtx(), this, get_TrxName()));
		saveEx();

		return success;
	}

	private boolean consumeFuel(boolean newRecord, boolean success) {
		
		// Ignore entries that already have an internal use inventory associated with them
		if (!newRecord && this.getM_Inventory_ID() > 0) {
			return success;
		}
		
		BigDecimal flightTime = getFlightTime_Dual()
				.add(getFlightTime_Solo())
    			.add(getFlightTime_Rental())
    			.add(getFlightTime_Intro())
    			.add(getFlightTime_Charter())
    			.add(getFlightTime_NonRev());
    	BigDecimal fuel = getFuel();

		// Look for flightsheet lines that have Flight time or fuel
		if (flightTime.equals(Env.ZERO) && fuel.equals(Env.ZERO)) 
			return success;

		// Determine the quantity of fuel consumed.
		MFTUAircraft ac = new MFTUAircraft(getCtx(),getFTU_Aircraft_ID(),get_TrxName());
		
		if (ac.isGeneric() || ac.isSim()) // Ignore placeholder and sim entries.
			return success;
		
		int fuelProductID = ac.getFuelProductID();
		int	fuelChargeID = ac.getFuelChargeID();
		int locatorID = MProduct.get(getCtx(), fuelProductID).getM_Locator_ID();
		int c_UOM_ID = ac.getC_UOM_ID();
		int product_UOM_ID = MProduct.get(getCtx(), fuelProductID).getC_UOM_ID();
		BigDecimal avgConsumption = ac.getAvgFuelConsumption();
		
		// Get the quantity of fuel in the aircraft UOM - typically Gallons(US)
		BigDecimal qtyFuel = flightTime.multiply(avgConsumption).add(fuel);
		
		if (qtyFuel == null || qtyFuel.equals(Env.ZERO) || fuelProductID == 0) // Nothing to record
			return success;

		// Convert to product UOM
		qtyFuel = MUOMConversion.convertProductFrom(getCtx(), fuelProductID, c_UOM_ID, qtyFuel);

		MInventory inv = new MInventory(getCtx(),0,get_TrxName());
		inv.setMovementDate(getFlightDate());
		inv.setM_Warehouse_ID(m_Warehouse_ID);
		inv.setDescription("Fuel consummed by flight ID: " + getFlightID() + ". AC: " 
							+ ac.getCallSign() + ", Flight Time: " + flightTime.toString());
		inv.saveEx();
		
		MInventoryLine il = new MInventoryLine(getCtx(),0,get_TrxName());
		il.setM_Inventory_ID(inv.getM_Inventory_ID());
		il.setInventoryType(MInventoryLine.INVENTORYTYPE_ChargeAccount);
		il.setM_Product_ID(fuelProductID);
		il.setM_Locator_ID(locatorID);
		il.setQtyInternalUse(qtyFuel);
		il.setC_Charge_ID(fuelChargeID);
		il.saveEx();
		
		inv.processIt(DocAction.ACTION_Complete);
		
		this.setM_Inventory_ID(inv.getM_Inventory_ID());
		this.saveEx();
		
		return success;
	}
	
	private boolean generateOrder(boolean newRecord, boolean success) {
		
		// Ignore entries that already have an order associated with them
		// TODO check if the order is completed and should be closed.
		if (!newRecord && this.getC_Order_ID() > 0) {
			return success;
		}
		
		BigDecimal flightTime = getFlightTime_Dual()
				.add(getFlightTime_Solo())
    			.add(getFlightTime_Rental())
    			.add(getFlightTime_Intro())
    			.add(getFlightTime_Charter())
    			.add(getFlightTime_NonRev());
    	BigDecimal brief = getBriefing();
    	BigDecimal sim = getSimulator();
    	BigDecimal fuel = getFuel();

		// Look for flightsheet lines that have billable time or a No-Show.
		// If there is no time or no-show, then the line is not ready for billing 
		// yet.
		if (!(flightTime.add(brief).add(sim).add(fuel).doubleValue()>0.0 || 
				getCourseType().matches("No-Show")))
			return success;
    	
    	log.fine("Creating order for Flight ID: " + this.getFlightID() + " Type: " + this.getCourseType());
		
		int order_id = 0;
		
		// If we have passed the above tests, the flight sheet line is valid
		// and ready to be converted to an order.
		// Check for any open orders with same date, C_BPartner_ID that
		// are draft and already referenced from the flight sheet.
		// If we find one, add the flight to it. Otherwise, create a new
		// order.
		String whereClause = 	"DocStatus = 'DR' AND " +
								"DateOrdered=? AND " +
								"C_BPartner_ID=? AND " +
								"EXISTS (SELECT 1 FROM FTU_Flightsheet fs WHERE fs.C_Order_ID = C_Order.C_Order_ID) AND " +
								"C_BPartner_ID NOT IN (SELECT C_BPartner_ID FROM C_BPartner WHERE Upper(Name) IN ('INTRO CUSTOMER', 'TOUR CUSTOMER'))";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(getFlightDate());
		parameters.add(getC_BPartner_ID());
		MOrder order = new Query(getCtx(), MOrder.Table_Name, whereClause, get_TrxName())
											.setClient_ID()
											.setOnlyActiveRecords(true)
											.setOrderBy("C_Order_ID DESC")
											.setParameters(parameters)
										.first();
		
		if (order == null) {
			order = new MOrder(getCtx(), 0, get_TrxName());
		}

		// Check to see if the business partner is set
		if ((order.getC_BPartner_ID()==0))
		{
			order.setC_BPartner_ID(getC_BPartner_ID());
			order.setDateOrdered(getFlightDate());
			order.setM_Warehouse_ID(m_Warehouse_ID); //Use default
			order.setC_DocTypeTarget_ID("WI"); //Credit Order
			order.saveEx();
		}

		log.fine("Creating orders - Order ID: " + order_id);

    	String fltDate = new SimpleDateFormat("dd MMM yy").format(getFlightDate());
		MFTUAircraft ac = MFTUAircraft.get(getCtx(),getFTU_Aircraft_ID(),get_TrxName());
		MFTUInstructor inst = MFTUInstructor.get(getCtx(),getFTU_Instructor_ID(),get_TrxName());
		MProduct noShowProduct = MFTUAdvancedInst.getNoShowProduct(getCtx(),getCourseType(), get_TrxName());
		MProduct introProduct = MFTUAdvancedInst.getIntroProduct(getCtx(),getCourseType(), get_TrxName());
		MProduct advancedInstProduct = MFTUAdvancedInst.getAdvancedInstProduct(getCtx(),getCourseType(), get_TrxName());
		MProduct acProduct = null;
		if (ac != null) {
			if (ac.isGeneric()) { // Ignore placeholder entries.
				ac = null;
			}
			else {
	
				 String where = MProduct.COLUMNNAME_S_Resource_ID + "=" + ac.getS_Resource_ID();
				 acProduct = new Query(getCtx(), MProduct.Table_Name, where, get_TrxName())
				 						.setClient_ID()
				 						.setOnlyActiveRecords(true)
				 						.firstOnly();
				 if (acProduct == null )
				 	throw new AdempiereException("No AC Product for Aircraft " + ac.getACRegistration());
			}
		}

		MProduct instProduct = null;
		MBPartner instBP = null;
		if (inst != null) {
			instBP = (MBPartner) inst.getC_BPartner();
			if (instBP == null || instBP.getC_BPartner_ID() == 0)
			 	throw new AdempiereException("No Instructor Business Partner for Instructor ID " + inst.getFTU_Instructor_ID());

			String where = MProduct.COLUMNNAME_S_Resource_ID + "=" + inst.getS_Resource_ID();
			 instProduct = new Query(getCtx(), MProduct.Table_Name, where, get_TrxName())
			 						.setClient_ID()
			 						.setOnlyActiveRecords(true)
			 						.firstOnly();
			 if (instProduct == null)
			 	throw new AdempiereException("No Instructor Product for Instructor " + inst.getC_BPartner().getName());
		}

    	// Deal with No-Shows
 		// Add order line for AC No Show
 		if (noShowProduct != null && (ac != null || inst != null) ) {
    		log.fine("   There is a no-show charge to add.");
 			if (ac != null) {
	        	StringBuilder description = new StringBuilder("No-Show fee for Flt ID: " + getFlightID())
	        	 	.append(" Date: ")
	        	 	.append(fltDate)
	        	 	.append(" A/C: ")
	        	 	.append(ac.getACRegistration());
     			MOrderLine line = new MOrderLine(order);
     			line.setM_Product_ID(noShowProduct.getM_Product_ID());
     			line.setDescription(description.toString());
     			line.setQty(new BigDecimal(2));
     			line.save();	     				
 			}
 			if (inst != null) {
	        	StringBuilder description = new StringBuilder("No-Show fee for Flt ID: " + getFlightID())
	        	 	.append(" Date: ")
	        	 	.append(fltDate)
	        	 	.append(" Instructor: ")
	        	 	.append(instBP.getName());
     			MOrderLine line = new MOrderLine(order);
     			line.setM_Product_ID(noShowProduct.getM_Product_ID());
     			line.setDescription(description.toString());
     			line.setQty(new BigDecimal(2));
     			line.save();	     				
 			}
 		}
 		else
 		{  // not a now show
    	 
 			Timestamp engStart = null;
 			Timestamp engStop = null;
 			long timeDiff;
 			BigDecimal hours = Env.ZERO;
 			BigDecimal millisecPerHour = new BigDecimal(60*60*1000);
 			
        	 // Figure out the times of use
        	 // First check to see if the engine start time is valid
        	 if (getEngineStart() != null)
        	 {
        		 engStart = getEngineStart();
        		// if it is, check if the engine stop time is valid
        	 	if (getEngineStop() != null)
        	 	{
        	 		// it is valid, now calculate the time difference and check 
        	 		// with the flightTime calculated above
        	 		engStop = getEngineStop();
        	 		timeDiff = engStop.getTime() - engStart.getTime();
	        	 	hours = new BigDecimal(timeDiff).divide(millisecPerHour, 1, BigDecimal.ROUND_HALF_UP);
	        	 	
	        	 	if (!(hours.compareTo(flightTime)==0)){
	        	 		log.fine("Mismatch of flight time and engine start/stop. Flight Time = " + flightTime.toString() +
	        	 				 " Eng Stop - Eng Start = " + hours.toString());
	        	 	}
        	 	}
        	 	else
        	 	{
        	 		// The engine start is valid but the stop time isn't valid.
        	 		// Use the flight time.  Convert from hours to milliseconds
        	 		engStop = new Timestamp(engStart.getTime() + 
        	 				flightTime.multiply(millisecPerHour).intValue());
        	 	}
        	 }
        	 else 
        	 {
        		 // The engine start is invalid. Assume there is no
        		 // flight time.  Use the flight date & sim + brief time;
        		 engStart = getFlightDate();
        		 engStop = new Timestamp(engStart.getTime() + sim.add(brief).multiply(millisecPerHour).intValue());
        		 log.fine("Engine start invalid. Flight Time = " + flightTime.toString());
        	 }

        	 // AC resource assignment
        	 if (ac != null && !(flightTime.equals(Env.ZERO)))
        	 {
 	    		 log.finest("Creating orders - AC: " + ac.getACRegistration());
	        	 String engStartTime = new SimpleDateFormat("HH:mm").format(engStart);
	        	 String engStopTime = new SimpleDateFormat("HH:mm").format(engStop);
	        	 String wheelsUpTime = new SimpleDateFormat("HH:mm").format(getWheelsUp());
	        	 String wheelsDownTime = new SimpleDateFormat("HH:mm").format(getWheelsDown());

        		 StringBuilder name = new StringBuilder("Flt ID: ")
	        	 	.append(getFlightID())
	        	 	.append(" PIC: ")
	        	 	.append(getCaptain_PIC());
        		 if (getStudentPAX() != null && !getStudentPAX().isEmpty()) {
	        	 	name.append(" Student/Pax: ")
	        	 		.append(getStudentPAX());
        		 }
	        	 StringBuilder description = new StringBuilder("Flt ID: ")
	        	 	.append(getFlightID())
	        	 	.append(" Date: ")
	        	 	.append(fltDate)
	        	 	.append(" A/C: ")
	        	 	.append(ac.getCallSign())
	        	 	.append(" Type: ")
	        	 	.append(getCourseType())
	        	 	.append("\n")
	        	 	.append(" PIC: ")
	        	 	.append(getCaptain_PIC());
    	 		if (getStudentPAX() != null && !getStudentPAX().isEmpty()) {
		        	description.append(" Student/Pax: ")
		        		.append(getStudentPAX());
        	 	}
    	 		if (getIntendedFlight() != null && !getIntendedFlight().isEmpty())
		        	description.append("\n")
		        	 	.append("Exercizes: ")
		        	 	.append(getIntendedFlight());
	        	 if (!(getNumLegs()==1))
	        	 {
	        		 description.append(" # Legs: ")
	        		 	.append(getNumLegs());
	        	 }
	        	 description.append("\n")
	        	 	.append("Start: ")
	        		.append(engStartTime)
	        		.append(" Stop: ")
	        		.append(engStopTime)
	        		.append(" Flight Time: ")
	        		.append(flightTime.setScale(1).toPlainString())
	        		.append("\n")
	        		.append("Up: ")
	        		.append(wheelsUpTime)
	        		.append(" Down: ")
	        		.append(wheelsDownTime)
	        		.append(" Air Time: ")
	        		.append(getAirTime().setScale(1).toPlainString());
 
		        	 MResourceAssignment ra = new MResourceAssignment(getCtx(),0,get_TrxName());
		        	 ra.setS_Resource_ID(ac.getS_Resource_ID());
		        	 ra.setAssignDateFrom(engStart);
		        	 ra.setAssignDateTo(engStop);
		        	 // only use flight time and sim.  The sim appears as an aircraft.
	        	 ra.setQty(flightTime.add(sim));
	        	 ra.setIsConfirmed(true);
	        	 ra.setName(name.toString());
	        	 ra.setDescription(description.toString());
	        	 ra.saveEx();		        	 

	        	 if (introProduct == null)  // ignore intro flights
	        	 {	        		 
		        	// Add order line for Aircraft
		     		MOrderLine line = new MOrderLine(order);
		     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
		     		line.setM_Product_ID(acProduct.getM_Product_ID());
		     		line.setDescription(description.toString());
		     		line.setQty(flightTime);
		     		line.saveEx();
	        	 
		     		// Fuel surcharge product Value is defined as 'FSC' + product classification string
		     		String where = MProduct.COLUMNNAME_Value + "=" + DB.TO_STRING("FSC" + acProduct.getClassification());
		     		MProduct fuelSurcharge = new Query(getCtx(), MProduct.Table_Name, where, get_TrxName())
		     									.setClient_ID()
		     									.setOnlyActiveRecords(true)
		     									.firstOnly();
		     		
		     		// Add order line for fuel surcharge
		     		if (fuelSurcharge != null)
		     		{
		     			line = new MOrderLine(order);
		     			line.setM_Product_ID(fuelSurcharge.getM_Product_ID());
		     			line.setDescription("");
		     			line.setQty(flightTime);
		     			line.saveEx();
		     		}
	        	 }
	        	 else  // Add the intro flight
	        	 {
		        	// Add order line for Intro Flight
	        		description = new StringBuilder("Introductory Flight ")
	        			.append("Flt ID: " + getFlightID())
		        	 	.append(" Date: ")
		        	 	.append(fltDate)
		        	 	.append(" A/C: ")
		        	 	.append(ac.getACRegistration())
		        	 	.append("\n")
		        	 	.append("PIC: ")
		        	 	.append(getCaptain_PIC());
	        	 	if (getStudentPAX() != null && !getStudentPAX().isEmpty())
		        	 	description.append(" Pax: ")
		        	 		.append(getStudentPAX());
		     		MOrderLine line = new MOrderLine(order);
		     		line.setM_Product_ID(introProduct.getM_Product_ID());
		     		line.setDescription(description.toString());
		     		line.setQty(new BigDecimal(1));
		     		line.saveEx();

		        	// Add order line for Aircraft
		     		line = new MOrderLine(order);
		     		line.setS_ResourceAssignment_ID(ac.getS_Resource_ID());
		     		line.setM_Product_ID(acProduct.getM_Product_ID());
		     		line.setDescription(description.toString());
		     		line.setQty(flightTime);
		     		line.setPrice(BigDecimal.ZERO);
		     		line.saveEx();

	        	 }
        	 } // Aircraft

        	 // Instructor resource assignment
        	 if (inst != null)
        	 {
 	    		 log.finest("Creating orders - Inst: " + inst.getC_BPartner().getName());

 	    		 StringBuilder name = new StringBuilder("Flight ID ")
	        	 	.append(getFlightID())
	        	 	.append(" PIC: ")
	        	 	.append(getCaptain_PIC());
 	    		 if (getStudentPAX() != null && !getStudentPAX().isEmpty())
	        	 	name.append(" Student/Pax: ")
	        	 		.append(getStudentPAX());
	        	 StringBuilder description = new StringBuilder("Flight ID ")
	        	 	.append(getFlightID())
	        	 	.append("\nFlight Type: ")
	        	 	.append(getCourseType());
	        	 if (getIntendedFlight() != null && !getIntendedFlight().isEmpty())
	        	 	description.append(" Exercises: ")
		        	 	.append(getIntendedFlight());
	        	 description.append("\n")
	        	 	.append("PIC: ")
		        	 .append(getCaptain_PIC());
 	    		 if (getStudentPAX() != null && !getStudentPAX().isEmpty())
	        	 	description.append(" Student/Pax: ")
	        	 		.append(getStudentPAX());
        	 	description.append("\n");

	        	 StringBuilder resDesc = new StringBuilder();
	        	 resDesc.append(description);
	        	 if (!(flightTime.doubleValue()==0.0))
	        		 resDesc.append(" Flight Instruction");
	        	 if (!(sim.doubleValue()==0.0))
	        		 resDesc.append(" Sim Instruction");
	        	 if (!(brief.doubleValue()==0.0))
	        		 resDesc.append(" and Ground Brief");

	        	 MResourceAssignment ra = new MResourceAssignment(getCtx(),0,get_TrxName());
	        	 ra.setS_Resource_ID(inst.getS_Resource_ID());
	        	 ra.setAssignDateFrom(engStart);
	        	 ra.setAssignDateTo(engStop);
	        	 // add any sim and brief time to the instructor time.
	        	 ra.setQty(flightTime.add(sim).add(brief));
	        	 ra.setIsConfirmed(true);
	        	 ra.setDescription(resDesc.toString());
	        	 ra.saveEx();		        	 

	        	 // Ignore any flight instruction charges for Intro flights.
	        	 if (introProduct == null)
	        	 {
		        	 // Add order line for flight training
		        	 if(!(flightTime.equals(Env.ZERO)))
        			 {
			        	StringBuilder fltDesc = new StringBuilder("Flight Instruction for ");
			        		fltDesc.append(description);
			     		MOrderLine line = new MOrderLine(order);
			     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
			     		line.setM_Product_ID(instProduct.getM_Product_ID());
			     		line.setDescription(fltDesc.toString());
			     		line.setQty(flightTime);
			     		line.saveEx(); 
        			 }
		        	 
		        	 // Add order line for ground briefing
		        	 if(!(brief.equals(Env.ZERO)))
        			 {
		        		StringBuilder grndDesc = new StringBuilder("Ground briefing for ");
		        		grndDesc.append("Flt ID: ")
		        			.append(getFlightID());
			     		MOrderLine line = new MOrderLine(order);
			     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
			     		line.setM_Product_ID(instProduct.getM_Product_ID());
			     		line.setDescription(grndDesc.toString());
			     		line.setQty(brief);
			     		line.saveEx(); 
        			 }
		        	 // Add order line(s) for sim
		        	 if(!(sim.equals(Env.ZERO)))
        			 {
		        		StringBuilder simDesc = new StringBuilder("Sim Instruction for ");
		        		simDesc.append("Flt ID: ")
		        			.append(getFlightID())
			        	 	.append("\nSim Type: ")
			        	 	.append(getCourseType())
			        	 	.append(" Exercises: ")
			        	 	.append(this.getIntendedFlight())
			        	 	.append("\n")
			        	 	.append("PIC: ")
			        	 	.append(this.getCaptain_PIC())
			        	 	.append(" Student/Pax: ")
			        	 	.append(this.getStudentPAX());
		        		
			     		MOrderLine line = new MOrderLine(order);
			     		line.setM_Product_ID(acProduct.getM_Product_ID());
			     		line.setDescription(simDesc.toString());
			     		line.setQty(sim);
			     		line.saveEx(); 

		        		simDesc = new StringBuilder("Sim Instruction for ");
		        		simDesc.append("Flt ID: ")
		        			.append(getFlightID());
			     		line = new MOrderLine(order);
			     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
			     		line.setM_Product_ID(instProduct.getM_Product_ID());
			     		line.setDescription(simDesc.toString());
			     		line.setQty(sim);
			     		line.saveEx(); 

        			 }  //sim
		        	 
		        	 // Add order line for advanced flight training
		        	 if(advancedInstProduct != null)
		        	 {
		        		StringBuilder adv_desc = new StringBuilder(advancedInstProduct.getDescription());
		        		adv_desc.append(" for Flt ID ")
		        			.append(getFlightID());
		        		MOrderLine line = new MOrderLine(order);
			     		line.setM_Product_ID(advancedInstProduct.getM_Product_ID());
			     		line.setDescription(adv_desc.toString());
			     		line.setQty(flightTime.add(brief).add(sim));
			     		line.save();
		        	 }
	        	 } // Not Intro
	        	 else // Its an Intro
	        	 {
		        	 // Add an order line for the instructor
		        	 if(!flightTime.equals(Env.ZERO))
        			 {
			        	StringBuilder fltDesc = new StringBuilder("Instructor for intro ")
			        		.append(description);
			     		MOrderLine line = new MOrderLine(order);
			     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
			     		line.setM_Product_ID(instProduct.getM_Product_ID());
			     		line.setDescription(fltDesc.toString());
			     		line.setQty(flightTime);
			     		line.setPrice(Env.ZERO);
			     		line.saveEx(); 
        			 }
	        	 }
        	 } // Instructor
 		} // No-show
	     		
 		if (order != null && order.getC_Order_ID() > 0) {
 			this.setC_Order_ID(order.getC_Order_ID());
 			this.setLine_Status("Closed");
 			this.saveEx();
 		}

 		return success;
	}	//	afterSave

	public BigDecimal getFlightTime() {
		return getFlightTime_Charter()
				.add(getFlightTime_Dual())
				.add(getFlightTime_Intro())
				.add(getFlightTime_NonRev())
				.add(getFlightTime_Rental())
				.add(getFlightTime_Solo());
	}
	
	public String toString() {
		return  "MFTUFlightsheet ["+ this.getFTU_Flightsheet_ID() + "]" +
				" Flight ID: " 	+ getFlightID() +
				" Date: "		+ getFlightDate() +
				" Course: "		+ getCourseType() +
				" Captian/PIC: "+ this.getCaptain_PIC() +
				" Student/Pax: "+ this.getStudentPAX() +
				" Air time: "	+ this.getAirTime().setScale(1) +
				" Flight time: "+ this.getFlightTime().setScale(1) +
				" Sim time: " 	+ this.getSimulator().setScale(1) +
				" Briefing: "	+ this.getBriefing().setScale(1);
	}

	public static MFTUFlightsheet fromXmlNode(Properties ctx, Element flightData, Calendar flightDate,
			String trxName) {
		
		if (flightData == null)
			return null;
		
		if ( !"flight".equals(flightData.getNodeName() ) )
			return null;

		MFTUFlightsheet flight = null;
		
		
	    // <flightNumber>195781</flightNumber> [ed: this is the (Flt #xxxxxx) 
		// in the 'Invoice' column of the Daily Flight Sheet]
		String flightIDString = flightData.getElementsByTagName("flightNumber").item(0).getTextContent();
		if (isNull(flightIDString)) {
			throw new AdempiereException("Flight Number appears to be null: " + flightIDString);
		}
		
		BigDecimal flightID = Env.ZERO;
		try {
			flightID = new BigDecimal(flightIDString);
		}
		catch (NumberFormatException e) {
			throw new AdempiereException("Flight Number not in form of number: " + flightIDString + ". " + e.getMessage());
		}
		if (flightID == null || flightID.equals(Env.ZERO)) {
			throw new AdempiereException("Flight Number is null or zero: " + flightIDString);
		}
		
		flight = MFTUFlightsheet.getByFlightID(ctx, flightID, trxName);
		
		if ( flight == null ) {  // New entry
			throw new AdempiereException("Flight not found or created for flight number: " + flightIDString);
		}
		
		// <flightDate>2016-02-21</flightDate>
		String flightDateString = flightData.getElementsByTagName("flightDate").item(0).getTextContent();	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (isNull(flightDateString)) {
			throw new AdempiereException("FlightDate is null or empty: " + flightIDString);
		}
		else {
			Date d;
			try {
				d = dateFormat.parse(flightDateString);
				flight.setFlightDate(new Timestamp(d.getTime()));;
			} catch (ParseException e) {
				throw new AdempiereException("Couldn't parse and set FlightDate: " + flightDateString);
			}
		}

		// <isNoShow>0</isNoShow>	[ed: 1 if this *is* a no-show, 0 if not]  
		flight.setIsNoShow(flightData.getElementsByTagName("isNoShow").item(0).getTextContent().equals("1"));
		
		// <isDNCO>0</isDNCO>      	[ed: 1 if this *is* a 'did not complete op', 0 if not] TODO
		flight.setIsDNCO(flightData.getElementsByTagName("isDNCO").item(0).getTextContent().equals("1"));

		// <courseType>PPL</courseType>
		String courseType = flightData.getElementsByTagName("courseType").item(0).getTextContent();
		if (isNull(courseType) || flight.isNoShow() || flight.isDNCO()) {
			// Check for No-show or cancelled flights.
			if (flight.isNoShow()) {
				courseType = "No-Show";  // TODO Hardcoded from reference
			}
			else if (flight.isDNCO()) {
				courseType = "Cancelled"; // TODO Hardcoded from reference
			}
		}
		if (isNull(courseType)) {
			throw new AdempiereException("Course Type can't be null or empty.");
		}
		flight.setCourseType(courseType);	

		int C_BPartner_ID;
		MBPartner bp = null;
		String value = "";
		String name = "";

		// <clientID>1542</clientID>  Represents the customer paying for the flight.
		value = flightData.getElementsByTagName("clientID").item(0).getTextContent();
		if (!isNull(value)) {
			C_BPartner_ID = 0;
			bp = MBPartner.get(ctx, value);
			if (bp != null && bp.getC_BPartner_ID() > 0) {
				C_BPartner_ID = bp.getC_BPartner_ID();
				name = bp.getName();
			}
			else {
				throw new AdempiereException("Business Partner (clientID) is not known: " + flightData.getElementsByTagName("clientID").item(0).getTextContent());
			}
			flight.setC_BPartner_ID(C_BPartner_ID);
			flight.setFlightsheet_ClientID(value);
		}
		else {
			throw new AdempiereException("Business Partner (clientID) cannot be null: " + flightData.getElementsByTagName("clientID").item(0).getTextContent());			
		}

		// <aircraft>CGVLM</aircraft>
		MFTUAircraft ac = MFTUAircraft.getByCallSign(ctx, flightData.getElementsByTagName("aircraft").item(0).getTextContent(), trxName);
		if (ac != null)
			flight.setFTU_Aircraft_ID(ac.getFTU_Aircraft_ID());

		// <captainID>64</captainID>
		value = flightData.getElementsByTagName("captainID").item(0).getTextContent();
		if (!isNull(value)) {
			C_BPartner_ID = 0;
			bp = MBPartner.get(ctx, value);
			if (bp != null && bp.getC_BPartner_ID() > 0) {
				C_BPartner_ID = bp.getC_BPartner_ID();
				name = bp.getName();
			}
			else {
				throw new AdempiereException("Business Partner (captianID) is not known: " + flightData.getElementsByTagName("captainID"));
			}
			flight.setCaptainID(C_BPartner_ID);
			flight.setCaptain_PIC(name);
		}

		// <studentID>1542</studentID>
		value = flightData.getElementsByTagName("studentID").item(0).getTextContent();
		if (!isNull(value)) {
			C_BPartner_ID = 0;
			bp = MBPartner.get(ctx, value);
			if (bp != null && bp.getC_BPartner_ID() > 0) {
				C_BPartner_ID = bp.getC_BPartner_ID();
				name = bp.getName();
			}
			else {
				throw new AdempiereException("Business Partner (studentID) is not known: " + flightData.getElementsByTagName("studentID"));
			}
			flight.setStudentID(C_BPartner_ID);
			flight.setStudentPAX(name);
		}

		// Set instructor
		if (flight.getCaptainID() > 0 
				&& flight.getCaptainID() != flight.getC_BPartner_ID() 
				&& flight.getStudentID() > 0) {
			MFTUInstructor inst = MFTUInstructor.getByBPartnerID(ctx, flight.getCaptainID());
			if (inst != null) {
				flight.setFTU_Instructor_ID(inst.getFTU_Instructor_ID());
			}
		}

		//<otherPax/>
		value = flightData.getElementsByTagName("otherPax").item(0).getTextContent();
		if (!isNull(value)) {
			flight.setOtherPax(value);
			if (flight.getStudentPAX() != null && flight.getStudentPAX().length() > 0)
				flight.setStudentPAX(flight.getStudentPAX() + ", " + value);
		}
		
		// <contactPhone/>
		flight.setContactPhone(flightData.getElementsByTagName("contactPhone").item(0).getTextContent());
		
		// <exercises>16, 17, 18</exercises>
		flight.setExercises(flightData.getElementsByTagName("exercises").item(0).getTextContent());
		
		// <xcDeparture/>
		flight.setXCDeparture(flightData.getElementsByTagName("xcDeparture").item(0).getTextContent());
		
		// <xcDestination/>
		flight.setXCDestination(flightData.getElementsByTagName("xcDestination").item(0).getTextContent());
		
		// <xcLegCount/>
		Integer numLegs = new Integer(1);
		value = flightData.getElementsByTagName("xcLegCount").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				numLegs = Integer.parseUnsignedInt(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("xcLegCount entry is not in form of number: " + value);
			}
		}
		flight.setNumLegs(numLegs.intValue());
		
		// <authorizedByID>64</authorizedByID>
		value = flightData.getElementsByTagName("authorizedByID").item(0).getTextContent();
		if (!isNull(value)) {
			C_BPartner_ID = 0;
			bp = MBPartner.get(ctx, value);
			if (bp != null && bp.getC_BPartner_ID() > 0) {
				C_BPartner_ID = bp.getC_BPartner_ID();
				name = bp.getName();
			}
			else {
				throw new AdempiereException("Business Partner (authorizedByID) is not known: " + flightData.getElementsByTagName("authorizedByID"));
			}
			flight.setAuthorizedByID(C_BPartner_ID);
			flight.setAuthorizedBy(name);
		}

		// <acknowledgedByID>1542</acknowledgedByID>
		value = flightData.getElementsByTagName("acknowledgedByID").item(0).getTextContent();
		if (!isNull(value)) {
			C_BPartner_ID = 0;
			bp = MBPartner.get(ctx, value);
			if (bp != null && bp.getC_BPartner_ID() > 0) {
				C_BPartner_ID = bp.getC_BPartner_ID();
				name = bp.getName();
			}
			else {
				throw new AdempiereException("Business Partner (acknowledgedByID) is not known: " + flightData.getElementsByTagName("acknowledgedByID"));
			}
			flight.setAcknowledgedByID(C_BPartner_ID);
			flight.setAcknowledgedBy(name);
		}

		// For times
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		long oneDay = (long) 1000*60*60*24; // One day in milliseconds. 

		// <engineStart>10:00</engineStart>
		String timeString = flightData.getElementsByTagName("engineStart").item(0).getTextContent();
		if (!isNull(timeString)) {
			Date d;
			Timestamp time = null;
			try {
				d = timeFormat.parse(timeString);
				time = new Timestamp(flight.getFlightDate().getTime() + d.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				time = null;
			}
			flight.setEngineStart(time);
		}

		// <engineStop>11:00</engineStop>
		timeString = flightData.getElementsByTagName("engineStop").item(0).getTextContent();
		if (!isNull(timeString)) {
			Date d = null;
			Timestamp time = null;
			try {
				d = timeFormat.parse(timeString);
				time = new Timestamp(flight.getFlightDate().getTime() + d.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				time = null;
			}
			// Check for next day
			if (flight.getEngineStart() != null && time != null  
					&& flight.getEngineStart().after(time)) {
				time = new Timestamp(time.getTime() + oneDay);
			}
			flight.setEngineStop(time);
		}
		
		// <wheelsUp>10:10</wheelsUp>
		timeString = flightData.getElementsByTagName("wheelsUp").item(0).getTextContent();
		if (!isNull(timeString)) {
			Date d;
			Timestamp time = null;
			try {
				d = timeFormat.parse(timeString);
				time = new Timestamp(flight.getFlightDate().getTime() + d.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				time = null;
			}
			flight.setWheelsUp(time);
		}
	    	    
		// <wheelsDown>10:50</wheelsDown>
		timeString = flightData.getElementsByTagName("wheelsDown").item(0).getTextContent();
		if (!isNull(timeString)) {
			Date d;
			Timestamp time = null;
			try {
				d = timeFormat.parse(timeString);
				time = new Timestamp(flight.getFlightDate().getTime() + d.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				time = null;
			}
			// Check for next day
			if (flight.getWheelsUp() != null && time != null 
					&& flight.getWheelsUp().after(time)) {
				time = new Timestamp(time.getTime() + oneDay);
			}
			flight.setWheelsDown(time);
		}

		BigDecimal flightTime = Env.ZERO;
		// <flightTimeDual>1.0</flightTimeDual>
		value = flightData.getElementsByTagName("flightTimeDual").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				flightTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("flightTimeDual entry is not in form of number: " + value);
			}
		}
		flight.setFlightTime_Dual(flightTime);
		
		// <flightTimeSolo/>
		flightTime = Env.ZERO;
		value = flightData.getElementsByTagName("flightTimeSolo").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				flightTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("flightTimeSolo entry is not in form of number: " + value);
			}
		}
		flight.setFlightTime_Solo(flightTime);

		// <flightTimeRental/>
		flightTime = Env.ZERO;
		value = flightData.getElementsByTagName("flightTimeRental").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				flightTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("flightTimeRental entry is not in form of number: " + value);
			}
		}
		flight.setFlightTime_Rental(flightTime);

		// <flightTimeIntro/>
		flightTime = Env.ZERO;
		value = flightData.getElementsByTagName("flightTimeIntro").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				flightTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("flightTimeIntro entry is not in form of number: " + value);
			}
		}
		flight.setFlightTime_Intro(flightTime);

		// <flightTimeTour/>
		flightTime = Env.ZERO;
		value = flightData.getElementsByTagName("flightTimeTour").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				flightTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("flightTimeTour entry is not in form of number: " + value);
			}
		}
		flight.setFlightTime_Charter(flightTime);
		
		// <flightTimeNonRev/>
		flightTime = Env.ZERO;
		value = flightData.getElementsByTagName("flightTimeNonRev").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				flightTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("flightTimeNonRev entry is not in form of number: " + value);
			}
		}
		flight.setFlightTime_NonRev(flightTime);

		// <flightTimeDry/>
		flightTime = Env.ZERO;
		value = flightData.getElementsByTagName("flightTimeDry").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				flightTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("flightTimeDry entry is not in form of number: " + value);
			}
		}
		flight.setFlightTime_Dry(flightTime);

		// <airTime>0.7</airTime>
		BigDecimal airTime = Env.ZERO;
		value = flightData.getElementsByTagName("airTime").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				airTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("airTime entry is not in form of number: " + value);
			}
		}
		flight.setAirTime(airTime);
		
		// <simulatorTime/>
		BigDecimal simulatorTime = Env.ZERO;
		value = flightData.getElementsByTagName("simulatorTime").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				simulatorTime = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("simulatorTime entry is not in form of number: " + value);
			}
		}
		flight.setSimulator(simulatorTime);

		// <groundBrief>0.2</groundBrief>
		BigDecimal groundBrief = Env.ZERO;
		value = flightData.getElementsByTagName("groundBrief").item(0).getTextContent();
		if (!isNull(value)) {
			try {
				groundBrief = new BigDecimal(value);
			}
			catch (NumberFormatException e) {
				throw new AdempiereException("groundBrief entry is not in form of number: " + value);
			}
		}
		flight.setBriefing(groundBrief);

	    // <timesEnteredByID>64</timesEnteredByID>
		value = flightData.getElementsByTagName("timesEnteredByID").item(0).getTextContent();
		if (!isNull(value)) {
			C_BPartner_ID = 0;
			bp = MBPartner.get(ctx, value);
			if (bp != null && bp.getC_BPartner_ID() > 0) {
				C_BPartner_ID = bp.getC_BPartner_ID();
			}
			else {
				throw new AdempiereException("Business Partner (timesEnteredByID) is not known: " + flightData.getElementsByTagName("timesEnteredByID"));
			}
			flight.setTimesEnteredByID(C_BPartner_ID);
		}

		// <flightComments/>
		flight.setDescription(flightData.getElementsByTagName("flightComments").item(0).getTextContent());
		
		// Set IntendedFlight comment
		StringBuffer intendedFlight = new StringBuffer("");
		if (!isNull(flight.getExercises()))
			intendedFlight.append(flight.getExercises());
		if (!isNull(flight.getXCDeparture())) {
			if (intendedFlight.length() > 0)
				intendedFlight.append(" ");
			intendedFlight.append("[").append(flight.getXCDeparture())
				.append(" - ").append(flight.getXCDestination())
				.append("] ").append("(Flight date: ")
				.append(dateFormat.format(flight.getFlightDate()))
				.append(")");
		}
		if (!isNull(flight.getDescription())) {
			if (intendedFlight.length() > 0)
				intendedFlight.append(" ");
			intendedFlight.append(flight.getDescription());
		}
		if (!isNull(intendedFlight.toString()))
			flight.setIntendedFlight(intendedFlight.toString());

		return flight;
	}

	private static boolean isNull(String field) {
		if (field == null 
				|| field.trim().toUpperCase().equals("NULL")
				|| field.trim().equals("-") 
				|| field.trim().isEmpty()) {
			return true;
		}
		return false;
	}
}
