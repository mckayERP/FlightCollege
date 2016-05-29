package com.mckayerp.ftu.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mckayerp.ftu.model.MFTUFlightsheet;

public class LoadFlightsheetFromXML extends SvrProcess {

	private String loginURL;
	private Timestamp startTime = null;
	private Timestamp stopTime = null;

	/** System Date */
	private Calendar		startDate = Calendar.getInstance();
	private Calendar		stopDate = Calendar.getInstance();

	public LoadFlightsheetFromXML() {
		// TODO Auto-generated constructor stub
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
				loginURL = para[i].getParameter().toString();
				if (loginURL.isEmpty())
					log.severe("LoginURL is mandatory");
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
		if (!ImportFlightSheets.grabLock(get_TrxName()))
		{
			ImportFlightSheets.releaseLock(get_TrxName());
			return "Unable to capture lock.  Try again later.";
		}
		// Don't run at night. 11 pm to 7 am.
//		Calendar currentTime = Calendar.getInstance(); // gets the current time
//		if (onlyOpenFlights && currentTime.get(Calendar.HOUR_OF_DAY) < 1) // Don't run at night.
//		{
//			releaseLock(get_TrxName());
//			return "Flight sheet import aborted - night";
//		}

		// https://www.flight-sheets.com/servlet/OFC-FlightSheets?login=551&password=4291b061581b0db2514a7ec371043ac2
		// &useraction=26&bookingDate=2016-02-21  
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			while (!startDate.after(stopDate)) {

				String url = loginURL + "&useraction=26&bookingDate=" + dateFormat.format(startDate.getTime());
				log.fine("Loading flights on " + dateFormat.format(startDate.getTime()) + " from " + url);
				
				Document flightSheet = loadFlightsheetDocument(url);
				
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

		}
		catch ( ParserConfigurationException
				| SAXException 
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ImportFlightSheets.releaseLock(get_TrxName());
		}
		return "Load Flightsheets completed.";
	}

    private static Document loadFlightsheetDocument(String url) throws Exception {    	
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setIgnoringComments(true);
        return factory.newDocumentBuilder().parse(url);
    }
}
