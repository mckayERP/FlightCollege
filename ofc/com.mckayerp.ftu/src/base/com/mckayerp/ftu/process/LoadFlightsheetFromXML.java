package com.mckayerp.ftu.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.eevolution.service.dsl.ProcessBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mckayerp.ftu.model.MFTUACJourneyLog;
import com.mckayerp.ftu.model.MFTUAircraft;
import com.mckayerp.ftu.model.MFTUFlightsheet;
import com.mckayerp.ftu.model.MFTUMaintWORLDetail;

public class LoadFlightsheetFromXML extends SvrProcess {

    private static long lockCode = 123567; // a number to use for an advisory lock.  Postgresql specific.

	private static final String SYSCONFIG_MFTU_FLIGHTSHEET_LOGIN_URL = "MFTU_FLIGHTSHEET_LOGIN_URL";
	private static final String SYSCONFIG_MFTU_FLIGHTSHEET_UPDATE_URL = "MFTU_FLIGHTSHEET_UPDATE_URL";
	private static String loginURL="https://www.flight-sheets.com/servlet/OFC-FlightSheets?login=551&password=4291b061581b0db2514a7ec371043ac2";
	private static String updateURL="https://www.flight-sheets.com/servlet/OFC-FlightSheets?enteredby=551&enteredbypassword=4291b061581b0db2514a7ec371043ac2";
	private Timestamp startTime = null;
	private Timestamp stopTime = null;

	/** System Date */
	private Calendar		startDate = Calendar.getInstance();
	private Calendar		stopDate = Calendar.getInstance();

	public LoadFlightsheetFromXML() {
		//  Check the configuration for the URL of the client flightsheet
		loginURL = MSysConfig.getValue(SYSCONFIG_MFTU_FLIGHTSHEET_LOGIN_URL,loginURL,Env.getAD_Client_ID(Env.getCtx()));
	}

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (name.equals("FlightDate")){   // Range
				if (para[i].getParameter() == null) {
					//	Get the date of the last import ----------------------------
					String where = "FlightID IS NOT NULL";
					startTime = (Timestamp) new Query(getCtx(), MFTUFlightsheet.Table_Name, where, null)
								.setOnlyActiveRecords(true)
								.setClient_ID()
								.aggregate("FlightDate", "Max", Timestamp.class);
					
				}							
				else {
					startTime = (Timestamp) para[i].getParameter();  // this is the start date of the import
				}
				if (para[i].getParameter_To() != null)  // end today
				{
					stopTime = (Timestamp) para[i].getParameter_To();  // this is the end date of the import
				}
			}
			else if (name.equals("LoginURL")) {
				if (para[i].getParameter().toString().isEmpty() && (loginURL == null || loginURL.length() == 0))
					log.severe("LoginURL is mandatory");
				else {
					loginURL = para[i].getParameter().toString();
				}
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		if (startTime == null) {
			startTime = new Timestamp(System.currentTimeMillis()); // convert from Timestamp to Calendar			
		}
		startDate.setTimeInMillis(startTime.getTime()); // convert from Timestamp to Calendar

		if (stopTime == null) {
			stopTime = new Timestamp(System.currentTimeMillis()); // convert from Timestamp to Calendar			
		}
		stopDate.setTimeInMillis(stopTime.getTime()); // convert from Timestamp to Calendar
		
		if (stopTime.before(startTime))
			log.log(Level.SEVERE, "Start date (date from) after stop date (date to)! Only the stop date (date to) will be used.");
	}

	@Override
	protected String doIt() throws Exception {

		// Grab the lock
		if (!grabLock(get_TrxName()))
		{
			releaseLock(get_TrxName());
			return "Unable to capture lock.  Try again later.";
		}
		// Don't run at night. 11 pm to 7 am. - added to scheduler
//		Calendar currentTime = Calendar.getInstance(); // gets the current time
//		if (onlyOpenFlights && currentTime.get(Calendar.HOUR_OF_DAY) < 1) // Don't run at night.
//		{
//			releaseLock(get_TrxName());
//			return "Flight sheet import aborted - night";
//		}

		// https://www.flight-sheets.com/servlet/OFC-FlightSheets?login=551&password=4291b061581b0db2514a7ec371043ac2
		// &useraction=26&bookingDate=2016-02-21  
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try 
		{
			
			while (!startDate.after(stopDate)) 
			{

				String url = loginURL + "&useraction=26&bookingDate=" + dateFormat.format(startDate.getTime());
				log.fine("Loading flights on " + dateFormat.format(startDate.getTime()) + " from " + url);
				
				Document flightSheet = getXMLDocument(url);
				
				NodeList flights = flightSheet.getDocumentElement().getElementsByTagName("flight");
				log.fine("Read " + flights.getLength() + " flights from URL.");
				for (int i = 0; i < flights.getLength(); i++ ) {

					Element flightElement = (Element) flights.item(i);

					MFTUFlightsheet flight = null;
					try {
							flight = MFTUFlightsheet.fromXmlNode(getCtx(), flightElement, startDate, get_TrxName());
							if (flight == null) {
								log.log(Level.CONFIG, "No flight data found. Skipping.");
							}
							else 
								flight.saveEx();
					} catch (AdempiereException e) {
						String msg = "Error importing flight";
						if (flight != null)
							msg += " " + flight.toString();
						msg += " " + e.getMessage();
						log.severe(msg);
					}
				}
				startDate.add(Calendar.DATE, 1);
			}
			
			String jlogUpdate = MFTUACJourneyLog.recalculateLog(getCtx(), 0, startTime, get_TrxName());
			log.fine(jlogUpdate);
			
		}
		catch ( ParserConfigurationException
				| SAXException 
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			releaseLock(get_TrxName());
		}
		
		return "Load Flightsheets completed.";
	}

    public static Document getXMLDocument(String url) 
    		throws ParserConfigurationException, SAXException, IOException {    	
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setIgnoringComments(true);
        return factory.newDocumentBuilder().parse(url);
    }

	public String getLoginURL() {
		return loginURL;
	}

	/**
	 * Set the Flightsheet invoice/order number to link back to the ADemiere data
	 * @param flightID
	 * @param invoiceNumber
	 * @return
	 */
	public static boolean setFlightsheetInvoice(int flightID, String invoiceNumber) {
		if (flightID == 0)
			return false;
		
		if (invoiceNumber == null || invoiceNumber.isEmpty())
			invoiceNumber = "-"; // null string for flightsheet. See MFTUFlightsheet.isNull().
		
		//  Check the configuration for the URL of the client flightsheet
		updateURL = MSysConfig.getValue(SYSCONFIG_MFTU_FLIGHTSHEET_UPDATE_URL,updateURL,Env.getAD_Client_ID(Env.getCtx()));
		
		// Add actions
		updateURL = updateURL + "&useraction=27&flightnumber=" + flightID
				+ "&invoicenumber=" + invoiceNumber;
		
		try {
			Document response = getXMLDocument(updateURL);
			NodeList statusNodeList = response.getDocumentElement().getElementsByTagName("updateInvoiceNumberReturnStatus");
			for (int i = 0; i < statusNodeList.getLength(); i++ ) {

				Element statusElement = (Element) statusNodeList.item(i);

				try {
					
					// Check the response flight ID to see if it matches
					String flightIDString = statusElement.getElementsByTagName("flightNumber").item(0).getTextContent();
					if (MFTUFlightsheet.isNull(flightIDString)) {
						return false;
					}
					
					BigDecimal responseFlightID = Env.ZERO;
					try {
						responseFlightID = new BigDecimal(flightIDString);
					}
					catch (NumberFormatException e) {
						throw new AdempiereException("Response Flight Number not in form of number: " + flightIDString + ". " + e.getMessage());
					}
					
					if (responseFlightID == null || responseFlightID.equals(Env.ZERO)) {
						return false;
					}
					
					if (responseFlightID.intValue() != flightID) {
						throw new AdempiereException("Response flight ID does not match. Sent " + flightID + ". Received " + flightIDString);
					}

					// Test the invoice number to see if there is a match
					String invoiceNumberString = statusElement.getElementsByTagName("invoiceNumber").item(0).getTextContent();
					if (MFTUFlightsheet.isNull(invoiceNumberString) && !MFTUFlightsheet.isNull(invoiceNumber)) {
						return false;
					}
					
					if (!invoiceNumberString.equals(invoiceNumber)) {
						return false;
					}
					
					return true;
					
				} catch (AdempiereException e) {
					String msg = "Unable to set Invoice/Order for flight ID " + flightID;
					msg += " " + e.getMessage();
					throw new AdempiereException(msg);
				}
			}
			
		} catch (ParserConfigurationException
				| SAXException 
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public static boolean grabLock(String txName) {		
		
		// Try to grab an advisory lock.  Return immediately with the result.
		String sqlfunc = "SELECT pg_try_advisory_lock(" + lockCode + ")";
		PreparedStatement pstmt = DB.prepareStatement(sqlfunc, txName);
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (!rs.getBoolean(1))
					releaseLock(txName);
				return rs.getBoolean(1);  // True if we have the lock. Otherwise false.
			}
		} catch (SQLException e) {
			releaseLock(txName);
			e.printStackTrace();
		}
		finally {
			DB.close(rs, pstmt);
		}
		return false;
	}

	public static boolean releaseLock(String txName) {		
		
		String sqlfunc = "SELECT pg_advisory_unlock(" + lockCode + ")";
		//String sqlfunc = "SELECT pg_advisory_unlock_all()";
		PreparedStatement pstmt = DB.prepareStatement(sqlfunc, txName);
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);  // True if we have released the lock. Otherwise false.
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			DB.close(rs, pstmt);
		}
		return false;	
	}

	/**
	 * Post process actions (outside trx).
	 * Please note that at this point the transaction is committed so
	 * you can't rollback.
	 * This method is useful if you need to do some custom work when 
	 * the process complete the work (e.g. open some windows).
	 *  
	 * @param success true if the process was success
	 * @since 3.1.4
	 */
	protected void postProcess(boolean success) {

		if (success)
		{
			ProcessInfo processInfo = ProcessBuilder.create(this.getCtx())
			.process(com.mckayerp.ftu.process.MaintUpdateACServiceabilityStatus.class)
			.withTitle("Update AC Serviceability Status")
			.withParameter(MaintUpdateACServiceabilityStatus.FTU_Aircraft_ID, 0)
			.withParameter(MaintUpdateACServiceabilityStatus.FTU_MaintWOResult_ID, 0)
			.execute();
		}
	}

}
