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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.APanel;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.w3c.dom.Element;

import com.mckayerp.process.LoadFlightsheetFromXML;

/**
 * @author mckayERP
 *
 */
public class MFTUFlightsheet extends X_FTU_Flightsheet {

	private static final long serialVersionUID = -2365898440532283687L;
	
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
								.setOnlyActiveRecords(false)
								.firstOnly();
		
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
		// Check if the flight as been made inactive
		if (!newRecord && this.get_ValueOld(COLUMNNAME_IsActive).equals("Y") 
				&&this.get_Value(COLUMNNAME_IsActive).equals("N") ) {
			log.warning("The Aircraft Journey Log will need to be regenerated.");
		}
		
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
				
		return success;
	}	
	
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
		
		CLogger log = CLogger.getCLogger("com.mckayerp.ftu.model.MFTUFLightsheet");
		
	    // <flightNumber>195781</flightNumber> [ed: this is the (Flt #xxxxxx) 
		// in the 'Invoice' column of the Daily Flight Sheet]
		String flightIDString = flightData.getElementsByTagName("flightNumber").item(0).getTextContent();
		if (isNull(flightIDString)) {
			throw new AdempiereException("Flight Number appears to be null: " + flightIDString);
		}
		log.fine("Loading flightsheet flight: " + flightIDString);
		
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
		log.fine("    Able to parse flight ID: " + flightID);
		
		flight = MFTUFlightsheet.getByFlightID(ctx, flightID, trxName);
		
		if ( flight == null ) {  // New entry
			throw new AdempiereException("Flight not found or created for flight number: " + flightIDString);
		}
		log.fine("    Created flight log entry.");
		
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
				log.fine("    Set flight date to: " + flight.getFlightDate());
			} catch (ParseException e) {
				throw new AdempiereException("Couldn't parse and set FlightDate: " + flightDateString + " for flight ID " + flightIDString);
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
			throw new AdempiereException("Course Type can't be null or empty for flight ID " + flightIDString);
		}
		flight.setCourseType(courseType);	
		log.fine("    Set course type to: " + courseType);
		
		int C_BPartner_ID;
		MBPartner bp = null;
		String value = "";
		String name = "";

		// <clientID>1542</clientID>  Represents the customer paying for the flight.
		value = flightData.getElementsByTagName("clientID").item(0).getTextContent();
		if (!isNull(value)) {
			if (courseType.equals("Intro")) {
				value = "Intro";
			}
			else if (courseType.equals("Tour")) {
				value = "Tour";
			}
			C_BPartner_ID = 0;
			bp = MBPartner.get(ctx, value);
			if (bp != null && bp.getC_BPartner_ID() > 0) {
				C_BPartner_ID = bp.getC_BPartner_ID();
				name = bp.getName();
			}
			else {
				throw new AdempiereException("Business Partner (clientID) is not known: " 
							+ flightData.getElementsByTagName("clientID").item(0).getTextContent() 
							+ " for flight ID " + flightIDString);
			}
			flight.setC_BPartner_ID(C_BPartner_ID);
			flight.setFlightsheet_ClientID(value);
			log.fine("    Set client to: " + value + " " + bp.getName());
		}
		else {
			throw new AdempiereException("Business Partner (clientID) cannot be null: " 
						+ flightData.getElementsByTagName("clientID").item(0).getTextContent() 
						+ " for flight ID " + flightIDString);			
		}

		// <aircraft>CGVLM</aircraft>
		MFTUAircraft ac = MFTUAircraft.getByCallSign(ctx, flightData.getElementsByTagName("aircraft").item(0).getTextContent(), trxName);
		if (ac != null) {
			flight.setFTU_Aircraft_ID(ac.getFTU_Aircraft_ID());
			log.fine("    Set the aircraft to: " + ac.getName());
		}
		else {
			log.fine("    No aircraft identified.");
		}

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
				throw new AdempiereException("Business Partner (captianID) is not known: " + value + " for flight ID " + flightIDString);
			}
			flight.setCaptain_BPartner_ID(C_BPartner_ID);
			flight.setCaptain_PIC(name);
			log.fine("    Set captain to: " + value + " " + name);
		}
		else {
			log.fine("    No captain identified.");
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
				throw new AdempiereException("Business Partner (studentID) is not known: " 
							+ flightData.getElementsByTagName("studentID") 
							+ " for flight ID " + flightIDString);
			}
			flight.setStudent_BPartner_ID(C_BPartner_ID);
			flight.setStudentPAX(name);
			log.fine("    Set student to: " + value + " " + name);
		}
		else {
			log.fine("    No student identified.");
		}

		// Set instructor
		if (flight.getCaptain_BPartner_ID() > 0 
				&& flight.getCaptain_BPartner_ID() != flight.getC_BPartner_ID() 
				&& (flight.getCaptain_BPartner_ID() > 0 
					|| flight.getCourseType().equals(MFTUFlightsheet.COURSETYPE_Intro)
					|| flight.getCourseType().equals(MFTUFlightsheet.COURSETYPE_Tour))) {
			MFTUInstructor inst = MFTUInstructor.getByBPartnerID(ctx, flight.getCaptain_BPartner_ID());
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
				throw new AdempiereException("xcLegCount entry is not in form of number: " + value + " for flight ID " + flightIDString);
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
				throw new AdempiereException("Business Partner (authorizedByID) is not known: " 
						+ flightData.getElementsByTagName("authorizedByID")
						+ " for flight ID " + flightIDString);
			}
			flight.setAuthorizedBy(C_BPartner_ID);
			flight.setAuthorizedByText(name);
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
				throw new AdempiereException("Business Partner (acknowledgedByID) is not known: " 
							+ flightData.getElementsByTagName("acknowledgedByID")
							+ " for flight ID " + flightIDString);
			}
			flight.setAcknowledgedBy(C_BPartner_ID);
			flight.setAcknowledgedByText(name);
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
			flight.setTimesEnteredBy(C_BPartner_ID);
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

	public static boolean isNull(String field) {
		if (field == null 
				|| field.trim().toUpperCase().equals("NULL")
				|| field.trim().equals("-") 
				|| field.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean isBeingModelValidated() {
		return processing;
	}

	public void setBeingModelValidated(boolean state) {
		processing = state;
	}
	
	/**
	 * Set the invoice number on the Flightsheet system
	 * @return true if successful
	 */
	public boolean setFlightsheetInvoice() {
		if (this.getFlightID() == 0)
			return false;
		
		String orderDocumentNo = "";
		
		try {
			String hostname = InetAddress.getLocalHost().getHostName();
			if (hostname != null && hostname.equals("MHIW947"))  // TODO Hardcoded - make configurable
				return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MOrder order = (MOrder) this.getC_Order();
		if (order != null) {
			orderDocumentNo = order.getDocumentNo();
		}

		return LoadFlightsheetFromXML.setFlightsheetInvoice(getFlightID(), orderDocumentNo);
	}
	
	/** Set Order.
	* @param C_Order_ID 
	* Order
	*/
	public void setC_Order_ID (int C_Order_ID)
	{
		super.setC_Order_ID(C_Order_ID);
		setFlightsheetInvoice();
	}

}
