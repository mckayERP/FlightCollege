package com.mckayerp.ftu.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.impexp.ImpFormat;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import HtmlGet.HtmlGet;

import com.mckayerp.ftu.model.MFTUFlightsheet;
import com.mckayerp.ftu.model.X_I_Flightsheet;

public class ImportFlightSheets extends SvrProcess {

	private static long lockCode = 123567; // a number to use for an advisory lock.  Postgresql specific.
	
	/** The Berkley telegraph HtmlGet class */ 
	private HtmlGet hg;

	/** The primary TeSS Parameter Vector */
	private Vector<String> tessParams = new Vector<String>();
	
	/** The secondary login TeSS Parameter Vector */
	private Vector<String> sessionParams = new Vector<String>();
	
	/** The result vector from the screen scraper */
	private Vector<Object[]> sessionResults = new Vector<Object[]>();
	private Vector<Object[]> tessResults = new Vector<Object[]>();

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	
	/** Org to be imported to	  		*/
	private int				m_AD_Org_ID = 0;

	/** User doing the importing		*/
	private int				m_AD_User_ID = 0;

	/**	Delete old Imported				*/
	private boolean			deleteOldImported = false;
	
	private boolean 		onlyOpenFlights = true;
	
	/** Login first */
	private boolean			loginFirst = false;

	/** Login URL - the URL of the initial login */
	private String			loginURL;
	
	private String			msg; // result message;
	
	/** System Date */
	private Calendar		sysDate = Calendar.getInstance();
	private Calendar		startDate = Calendar.getInstance();
	private Calendar		stopDate = Calendar.getInstance();
	private Timestamp		time;
	private DateFormat		dateFormatter;
	
	/** Effective						*/
	private Timestamp		dateValue = null;

	private ImpFormat 		format;
	
	private String			clientCheck;

	private String			tableName = X_I_Flightsheet.Table_Name;
	private String			tablePK = X_I_Flightsheet.COLUMNNAME_I_Flightsheet_ID;
	
	private String 			jsessionid;
	
	private Properties		ctx;

	private int read = 0;

	private int imported = 0;

	
	@Override
	protected void prepare() {
		
		// Get any parameters and set the local variables

		if(tessParams == null)
			tessParams=new Vector<String>();
		tessParams.clear();
		
		if(tessResults == null)
			tessResults=new Vector<Object[]>();		
		tessResults.clear();

		// Is this used?
		if (dateValue == null)
			dateValue = new Timestamp (System.currentTimeMillis());
		
		dateFormatter = new SimpleDateFormat("dd-MMM-yy");
		sysDate.setLenient(true);  // Set the date to lenient
		startDate.setLenient(true);
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
			{
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
				clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
			}
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_User_ID"))
				m_AD_User_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("LoginFirst"))
				loginFirst = "Y".equals(para[i].getParameter());
			else if (name.equals("OnlyOpenFlights"))
				onlyOpenFlights = "Y".equals(para[i].getParameter());
			else if (name.equals("DateRange")){
				if (para[i].getParameter() == null)  // start at the date of last import
				{
					//	Get the date of the last import ----------------------------
					StringBuffer sql = new StringBuffer ("SELECT MAX(OFC_Flight_Date) ")
						.append(" FROM ofc_flightsheet")
						.append(" WHERE (ofc_flight_id IS NOT NULL)")
						.append(clientCheck);
					
					try
					{
						PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
						ResultSet rs = pstmt.executeQuery();
						if (rs.next())
						{
							// Go back a day
							startDate.setTimeInMillis(rs.getTimestamp(1).getTime() - 24*60*60*1000);
						}
						rs.close();
						pstmt.close();	
					}
					catch (SQLException e)
					{
						log.log(Level.SEVERE, sql.toString(), e);
						return;
					}
				}	
				else
				{
					time = (Timestamp) para[i].getParameter();  // this is the start date of the import
					startDate.setTimeInMillis(time.getTime()); // convert from Timestamp to Calendar
				}
				if (para[i].getParameter_To() == null)  // end today
				{
				    sysDate.setTimeInMillis(new Timestamp(System.currentTimeMillis()).getTime()); // convert from Timestamp to Calendar
				    stopDate = (Calendar) sysDate.clone();

				}	
				else
				{
					time = (Timestamp) para[i].getParameter_To();  // this is the end date of the import
					stopDate.setTimeInMillis(time.getTime()); // convert from Timestamp to Calendar
				}
			}
			else if (name.equals("LoginURL"))
				loginURL = para[i].getParameter().toString();
			else if (name.startsWith("Tess"))
				if (para[i].getParameter().toString().startsWith("@S:="))  // The scheduler will not strip this.
					tessParams.add(para[i].getParameter().toString().substring(4));
				else
					tessParams.add(para[i].getParameter().toString());
			else if (name.startsWith("Session"))
				if (para[i].getParameter().toString().startsWith("@S:="))  // The scheduler will not strip this.
					sessionParams.add(para[i].getParameter().toString().substring(4));
				else
					sessionParams.add(para[i].getParameter().toString());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		// Override the context - specifically if we are running on the server.
		// Otherwise, the server process won't be able to save the order to the
		// correct client.  Use the passed parameters.
		ctx = Env.getCtx();  // Make a copy of the context
		Env.setContext(ctx, "#AD_Client_ID", m_AD_Client_ID);  // Replace the client
		Env.setContext(ctx, "#AD_Org_ID", m_AD_Org_ID);		// Replace the org
		Env.setContext(ctx, "#AD_User_ID", m_AD_User_ID);		// Replace the user
		
		clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
		if (onlyOpenFlights) {
			format = ImpFormat.load ("Flightsheet Import Open");
		}
		else {
			format = ImpFormat.load ("Flightsheet Import All");
		}
		if (format == null) {
				// Do something about it.
		}


	} // Prepare

	@Override
	protected String doIt() throws Exception {

		// Grab the lock
		if (!grabLock(get_TrxName()))
		{
			releaseLock(get_TrxName());
			return "Unable to capture lock.  Try again later.";
		}
		// Don't run at night. 11 pm to 7 am.
		Calendar currentTime = Calendar.getInstance(); // gets the current time
		if (onlyOpenFlights && currentTime.get(Calendar.HOUR_OF_DAY) < 1) // Don't run at night.
		{
			releaseLock(get_TrxName());
			return "Flight sheet import aborted - night";
		}

		try {
			deleteOldImported();
		}
		catch (Exception e) {
			releaseLock(get_TrxName());
			msg = "Tried to delete the old imported data but had a problem";
			log.severe(msg);
			throw new AdempiereException(msg, e);
		}
		
		try {
			scrapeData();
		}
		catch (Exception e) {
			releaseLock(get_TrxName());
			msg = "Problem when trying to scrape the data.";
			log.severe(msg);
			throw new AdempiereException(msg, e);
		}

		// Go through the imported records and convert any fields
		// that require it.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = null;
		try {
			// Convert i_FlightID to FlightID
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs SET "
				+ " FlightID = I_FlightID, "
				+ " NumLegs = I_NumLegs, "
				+ " CourseType = I_CourseType, "
				+ " Captain_PIC = I_Captain_PIC, "
				+ " StudentPAX = I_StudentPAX, "
				+ " Flightsheet_ClientID = I_ClientID, "
				+ " IntendedFlight = I_IntendedFlight, "
				+ " AuthorizedBy = I_AuthorizedBy, "
				+ " AcknowledgedBy = I_Acknowledged_By "
				+ "WHERE ")
				.append(" ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			int no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Client ID updated=" + no);

			// Convert i_client_id to c_bpartner_id
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
				+ "SET C_BPartner_ID = bp.C_BPartner_ID "
				+ "FROM C_BPartner bp "
				+ "WHERE ifs.I_ClientID = bp.\"value\"")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Client ID updated=" + no);
			
			// Convert the plane to the Resource ID
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
					+ "SET FTU_Aircraft_ID = ac.FTU_Aircraft_ID "
					+ "FROM FTU_Aircraft ac "
					+ "WHERE (regexp_replace(ifs.I_CallMarks, '-', '') = ac.CallSign "
					+ "OR substring(ifs.I_CallMarks from '\\((.+)\\)') = ac.CallSign)")
					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ac.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Aircraft IDs updated=" + no);
			
			// Find the instructor Resource ID
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
					+ "SET FTU_Instructor_ID = ("
					+ "  SELECT inst.FTU_Instructor_ID "
					+ "  FROM FTU_Instructor inst "
					+ "       JOIN C_BPartner bp ON inst.C_BPartner_ID = bp.C_BPartner_ID"
					+ "  WHERE "
					+ "    lower(regexp_replace(substring(bp.\"name\" from ', (.+)') || substring(bp.\"name\" from '(.+), '), ' ','')) = " 
					+ "    lower(ifs.I_Captain_PIC)"
					+ "    AND ifs.AD_Client_ID= inst.AD_Client_ID)")
					.append("WHERE ifs.I_StudentPax <> ''")
					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Instructor IDs updated=" + no);
			
			// Find the Flight Date
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
					+ "SET " + X_I_Flightsheet.COLUMNNAME_FlightDate + " = "
					+ "  to_timestamp("
					+ "     to_char(" + X_I_Flightsheet.COLUMNNAME_I_FlightYear + ", '9999')"
					+ "     || to_char(" + X_I_Flightsheet.COLUMNNAME_I_FlightMonth + " + 1, '00')"
					+ "     || to_char(" + X_I_Flightsheet.COLUMNNAME_I_FlightDay + ", '00')"
					+ "   , 'YYYYMMDD')"	
					+ "  WHERE "
					+ "    ifs.AD_Client_ID= " + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Instructor IDs updated=" + no);

			
			if (onlyOpenFlights) {
				// Remove the Flight number from the air time and fuel time for flights that 
				// have not yet been authorized.  This is an artifact of the screen scraper and
				// the impacts of the buttons on the open flight sheets.
				sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
						+ "SET I_InvoiceNumber = ifs.I_AirTime, "
						+ "I_AirTime = '' "
						+ "where ifs.I_AirTime like '(Flt%'")
						.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
						.append(" AND ifs.Processed<>'Y'")
						.append(" AND ifs.I_IsImported<>'Y'");
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				if (no == -1)
					throw new AdempiereException("Couldn't update I_Flightsheet table.");
				log.fine("I_Airtime updated=" + no);
		
				sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
						+ "SET i_InvoiceNumber = ifs.i_fuel, "
						+ "i_fuel = '' "
						+ "where ifs.i_fuel like '(Flt%'")
						.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
						.append(" AND ifs.Processed<>'Y'")
						.append(" AND ifs.I_IsImported<>'Y'");
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				if (no == -1)
					throw new AdempiereException("Couldn't update I_Flightsheet table.");
				log.fine("I_Fuel updated=" + no);
			}	
			// Find the Invoice ID
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
					+ "SET Line_Status = 'Closed',Flightsheet_InvoiceNo = substring(ifs.I_InvoiceNumber from '([0-9]+).Flt #')::Numeric "
					+ "WHERE (substring(ifs.I_InvoiceNumber from '([0-9]+).Flt #') IS NOT NULL "
					+ "AND substring(ifs.I_InvoiceNumber from '([0-9]+).Flt #') <> 'DNCO')")
					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Invoice ID updated=" + no);
	
			// Clear the error text
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
					+ "SET I_ErrorMsg = NULL ")
					.append("WHERE ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Error messages cleared =" + no);
			
			// Check for blank flight id
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
				+ "SET I_ErrorMsg = \'I_FlightID is blank.  \' "
				+ "WHERE (ifs.I_FlightID IS NULL)")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Blank flight ID marked as errors =" + no);
			
			// Check for blank c_bpartner_id
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs "
				+ "SET I_ErrorMsg = \'C_BPartner_ID of ClientID is blank.  \' "
				+ "WHERE (ifs.C_BPartner_ID IS NULL "
				+ "OR ifs.I_ClientID IS NULL)")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Unknown Clients marked as errors =" + no);
		
			if (!onlyOpenFlights) {
				// Mark all as closed - non-open entries are closed by default
				sql = new StringBuffer ("UPDATE I_Flightsheet ifs ")
					.append("SET Line_Status = 'Closed' ")
					.append(" WHERE ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				if (no == -1)
					throw new AdempiereException("Couldn't update I_Flightsheet table.");
				log.fine("Records marked as closed =" + no);
			}
			
			// Mark all as processed
			sql = new StringBuffer ("UPDATE I_Flightsheet ifs ")
				.append("SET Processed = 'Y' ")
				.append("WHERE ifs.I_ErrorMsg IS NULL")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no == -1)
				throw new AdempiereException("Couldn't update I_Flightsheet table.");
			log.fine("Records marked as processed =" + no);
				
			// Now copy all the records to the main table.
			log.info("Processing the Import");
			importRecords();
//			String sqlfunc = "SELECT ofc_import_flightsheet()";
//			PreparedStatement pstmt = DB.prepareStatement(sqlfunc, get_TrxName());
//			ResultSet rs = pstmt.executeQuery();
			log.info("Import processing completed.");
			
			commitEx();
	    }
	    catch (SQLException e) {
	    	msg = "Error importing data: " + sql.toString();
			log.severe(msg);
			throw new AdempiereException(msg,e);
	    }
		finally {
	    	releaseLock(get_TrxName());
	    	DB.close(rs,pstmt);			
		}
		return "Flight Sheet Import R/I " + read + " / " + imported + "#";
	}

private void scrapeData() throws Exception {
	Vector<String> tessargs = new Vector<String>();		
	Vector<String> sessionargs = new Vector<String>();

	if (onlyOpenFlights) {
		// Set the Session defaults
		sessionargs.clear();
		// defaults 1 to 3 are the login info.  Keep those.
		for (int i=0; i< sessionParams.size();i++){
			if (sessionParams.get(i).startsWith("default"))
				if (sessionParams.get(i).startsWith("default1="))
					sessionargs.add(0,sessionParams.get(i).substring(9));
				else if (sessionParams.get(i).startsWith("default2="))
					sessionargs.add(1,sessionParams.get(i).substring(9));
				else if (sessionParams.get(i).startsWith("default3="))
					sessionargs.add(2,sessionParams.get(i).substring(9));
		}
	}
	// Initialize the screen scraper HTML Get
	boolean isDebug =false;
	String jscdir=null;
	String cookiefile="/tmp/cookies";
	String historyfile="/tmp/history";
		
	try {
		hg = new HtmlGet(cookiefile, historyfile,
					jscdir,
					isDebug);
	} catch (Exception e) {
		msg = "Unable to initialize HtmlGet.";
		log.log(Level.SEVERE, msg, e);
		throw new AdempiereException(msg, e);
	}

	if(hg != null){
		if (loginFirst){
			// Login
			boolean encodeValues=true;
			boolean isPost=true;
			hg.processUrl(isPost, loginURL, null, encodeValues);
		}
	}
	else
	{
		msg = "Unable to start. HtmlGet is null.";
		log.log(Level.SEVERE, msg);
		throw new AdempiereException(msg);
	}

	if (onlyOpenFlights) {
		// Get the session data.
		sessionResults.clear();
		int count = hg.runTess(null, sessionResults, sessionParams, sessionargs);
		log.info("Search for Session ID returned count: " + Integer.toString(count));
		if (count==0)
			jsessionid = "";
		else {
			String[] sesResults = (String[]) sessionResults.get(0);
			if (sesResults[0] == "?")
				jsessionid = "";
			else
				jsessionid = sesResults[0];
				jsessionid = jsessionid.substring(0,jsessionid.length()-1);
		}
		log.finest("ImportOpenFlights: Session ID found: " + jsessionid);
	}
	
	// Set the TeSS defaults
	tessargs.clear();
	// defaults 1 has login info.
	for (int i=0; i< tessParams.size();i++){
		if (tessParams.get(i).startsWith("default"))
			if (tessParams.get(i).startsWith("default1="))
				tessargs.add(0,tessParams.get(i).substring(9));
			else if (tessParams.get(i).startsWith("default2="))
				tessargs.add(1,tessParams.get(i).substring(9));
			else if (tessParams.get(i).startsWith("default3="))
				tessargs.add(2,tessParams.get(i).substring(9));
			else if (tessParams.get(i).startsWith("default4="))
				tessargs.add(3,tessParams.get(i).substring(9));
		else
			if (tessParams.get(i).startsWith("url") && onlyOpenFlights)
				tessParams.set(i, tessParams.get(i) + jsessionid);
	}
	try {
		if (onlyOpenFlights) {
			// Get the data.
			int count = hg.runTess(null, tessResults, tessParams, tessargs);
			log.finest("ImportOpenFlights: TeSS imported record count: " + Integer.toString(count));
			
			//	For all rows - update/insert DB table
			int row = 0;
			for (row = 0; row < tessResults.size(); row++)
			{
				read++;
				if (updateDB(ctx, (String[])tessResults.get(row), null))
					imported++;
			}
			tessResults.clear();
		}
		else {	
			tessargs.add(2,"");
			tessargs.add(3,"");
			tessargs.add(4,"");
		
		    Calendar daycount = Calendar.getInstance();
		    daycount.setLenient(true);
		
		    for(daycount=startDate; daycount.before(stopDate) ; daycount.add(Calendar.DAY_OF_MONTH, 1)){	// Go back in time
				// The next three defaults are the date - overwrite with the sysDate.
				if (!(daycount == null)) {
					tessargs.set(2,Integer.toString(daycount.get(Calendar.MONTH))); //tess uses month #s starting at 0
					tessargs.set(3,Integer.toString(daycount.get(Calendar.DAY_OF_MONTH)));
					tessargs.set(4,Integer.toString(daycount.get(Calendar.YEAR)));
					log.info("TeSS importing data for: " + dateFormatter.format(daycount.getTime()));
				}
				
				// Get the data.
				int count = hg.runTess(null, tessResults, tessParams, tessargs);
				log.info("TeSS imported record count: " + Integer.toString(count));
				if (count==0)
					continue; // No data on that date
		
				//	For all rows - update/insert DB table
				int row = 0;
				for (row = 0; row < tessResults.size(); row++)
				{
					read++;
					if (updateDB(Env.getCtx(), (String[])tessResults.get(row), null))
						imported++;
				}
				tessResults.clear();
			}
		}
	}
	catch (Exception e) {
		msg = "runTess was unable to get the open flight data.";
		log.log(Level.SEVERE, msg, e);
		throw new AdempiereException(msg, e);			
	}
}

private void deleteOldImported() throws IllegalStateException, SQLException {
	//	Delete Old Imported
	if (deleteOldImported)
	{
		StringBuffer sql = new StringBuffer ("DELETE ").append(tableName)
			.append(" WHERE I_IsImported='Y'").append(clientCheck);
		int no = DB.executeUpdate(sql.toString(), get_TrxName());
		DB.commit(false, get_TrxName());
		log.info("Deleted the old imported data = " + no);
	}
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

	private void importRecords() {
		// Find the records to import
		String whereClause = "FlightID is not NULL AND I_ErrorMsg is NULL AND I_IsImported <> 'Y'";
		List<X_I_Flightsheet> importflights = new Query(getCtx(), X_I_Flightsheet.Table_Name, whereClause, get_TrxName())
											.setClient_ID()
											.setOnlyActiveRecords(true)
										.list();
		
		for (X_I_Flightsheet iflight : importflights) {

			if (iflight.getFlightID() == null || iflight.getFlightID().equals(Env.ZERO) )
				continue;
			
			BigDecimal	flightID = iflight.getFlightID();
			
			MFTUFlightsheet flight = MFTUFlightsheet.getByFlightID(getCtx(), flightID, get_TrxName());
			flight.setDescription(iflight.getDescription());
			flight.setIsActive(iflight.isActive());
			flight.setValue(iflight.getValue());
			
			flight.setFlightDate(iflight.getFlightDate());
			
			flight.setFlightsheet_ClientID(iflight.getFlightsheet_ClientID());
			flight.setC_BPartner_ID(iflight.getC_BPartner_ID());

			flight.setCourseType(iflight.getCourseType());
			flight.setIntendedFlight(iflight.getIntendedFlight());
			flight.setNumLegs(iflight.getNumLegs());
			flight.setFTU_Aircraft_ID(iflight.getFTU_Aircraft_ID());
			flight.setFTU_Instructor_ID(iflight.getFTU_Instructor_ID());
			flight.setCaptain_PIC(iflight.getCaptain_PIC());
			flight.setStudentPAX(iflight.getStudentPAX());
			
			if (isNull(iflight.getI_ReturningAt())) {
				flight.setReturingAt(null);
			}
			else {
				// the returningAt import value has format MonDDHH24:MI
				DateFormat format = new SimpleDateFormat("YYYYMMMDDHH:mm");
				Date ra;
				Calendar ca = Calendar.getInstance();
				ca.setTimeInMillis(iflight.getFlightDate().getTime());
				try {
					ra = format.parse(ca.get(Calendar.YEAR) + iflight.getI_ReturningAt());
					if (ra.getTime() < iflight.getFlightDate().getTime())
						ra = format.parse((ca.get(Calendar.YEAR)+1) + iflight.getI_ReturningAt());
					flight.setReturingAt(new Timestamp(ra.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
					flight.setReturingAt(null);
				}
			}
			
			flight.setAuthorizedBy(iflight.getI_AuthorizedBy());
			flight.setAcknowledgedBy(iflight.getI_Acknowledged_By());

			// For times
			DateFormat format = new SimpleDateFormat("HH:mm");

			if (isNull(iflight.getI_EngineStart())) {
				flight.setEngineStart(null);
			}
			else {
				String time = iflight.getI_EngineStart();
				Date d;
				try {
					d = format.parse(time);
					flight.setEngineStart(new Timestamp(iflight.getFlightDate().getTime() + d.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
					flight.setEngineStart(null);
				}
			}
			
			if (isNull(iflight.getI_EngineStop())) {
				flight.setEngineStop(null);
			}
			else {
				String time = iflight.getI_EngineStop();
				Date d;
				try {
					d = format.parse(time);
					flight.setEngineStop(new Timestamp(iflight.getFlightDate().getTime() + d.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
					flight.setEngineStop(null);
				}
			}
			
			if (isNull(iflight.getI_WheelsUp())) {
				flight.setWheelsUp(null);
			}
			else {
				String time = iflight.getI_WheelsUp();
				Date d;
				try {
					d = format.parse(time);
					flight.setWheelsUp(new Timestamp(iflight.getFlightDate().getTime() + d.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
					flight.setWheelsUp(null);
				}
			}
			
			if (isNull(iflight.getI_WheelsDown())) {
				flight.setWheelsDown(null);
			}
			else {
				String time = iflight.getI_WheelsDown();
				Date d;
				try {
					d = format.parse(time);
					flight.setWheelsDown(new Timestamp(iflight.getFlightDate().getTime() + d.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
					flight.setWheelsDown(null);
				}
			}
			
			if (isNull(iflight.getI_FlightTime_Dual())) {
				flight.setFlightTime_Dual(null);
			}
			else {
				flight.setFlightTime_Dual(new BigDecimal(iflight.getI_FlightTime_Dual()));
			}
			
			if (isNull(iflight.getI_FlightTime_Solo())) {
				flight.setFlightTime_Solo(null);
			}
			else {
				flight.setFlightTime_Solo(new BigDecimal(iflight.getI_FlightTime_Solo()));
			}
			
			if (isNull(iflight.getI_FlightTime_Rental())) {
				flight.setFlightTime_Rental(null);
			}
			else {
				flight.setFlightTime_Rental(new BigDecimal(iflight.getI_FlightTime_Rental()));
			}
			
			if (isNull(iflight.getI_FlightTime_Intro())) {
				flight.setFlightTime_Intro(null);
			}
			else {
				flight.setFlightTime_Intro(new BigDecimal(iflight.getI_FlightTime_Intro()));
			}
			
			if (isNull(iflight.getI_FlightTime_Charter())) {
				flight.setFlightTime_Charter(null);
			}
			else {
				flight.setFlightTime_Charter(new BigDecimal(iflight.getI_FlightTime_Charter()));
			}
			
			if (isNull(iflight.getI_FlightTime_Nonrev())) {
				flight.setFlightTime_NonRev(null);
			}
			else {
				flight.setFlightTime_NonRev(new BigDecimal(iflight.getI_FlightTime_Nonrev()));
			}
			
			if (isNull(iflight.getI_AirTime())) {
				flight.setAirTime(null);
			}
			else {
				flight.setAirTime(new BigDecimal(iflight.getI_AirTime()));
			}

			if (isNull(iflight.getI_SimulatorTime())) {
				flight.setSimulator(null);
			}
			else {
				flight.setSimulator(new BigDecimal(iflight.getI_SimulatorTime()));
			}

			if (isNull(iflight.getI_Briefing())) {
				flight.setBriefing(null);
			}
			else {
				flight.setBriefing(new BigDecimal(iflight.getI_Briefing()));
			}

			if (isNull(iflight.getI_Fuel())) {
				flight.setFuel(null);
			}
			else {
				flight.setFuel(new BigDecimal(iflight.getI_Fuel()));
			}
			
			flight.setFlightsheet_InvoiceNo(iflight.getFlightsheet_InvoiceNo());
			flight.setLine_Status(iflight.getLine_Status());
			
			flight.saveEx();
			
			iflight.setI_IsImported(true);
			iflight.saveEx();
		}
		
	}

	private boolean updateDB (Properties ctx, String[] line, String trxName)
	{
		if (line == null || line[0].length() == 0)
		{
			log.finest("No Line");
			return false;
		}

		//  Standard Fields - use OFCAdmin
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		int AD_Org_ID = Env.getAD_Org_ID(ctx);
		int UpdatedBy = Env.getAD_User_ID(ctx);

		//	Check if the record is already there ----------------------------
		StringBuffer sql = new StringBuffer ("SELECT COUNT(*), MAX(")
			.append(tablePK).append(") FROM ").append(tableName)
			.append(" WHERE (I_FlightID = ")
			.append(line[0]).append(")").append(clientCheck);
		//
		int count = 0;
		int ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				count = rs.getInt(1);
				if (count == 1)
					ID = rs.getInt(2);
			}

			//	Insert Basic Record -----------------------------------------------
			if (ID == 0)
			{
				ID = DB.getNextID(ctx, tableName, null);		//	get ID
				sql = new StringBuffer("INSERT INTO ")
					.append(tableName).append("(").append(tablePK).append(",")
					.append("AD_Client_ID,AD_Org_ID,Created,CreatedBy,Updated,UpdatedBy,IsActive,")	//	StdFields
					.append("Processed,I_IsImported")
					.append(") VALUES (").append(ID).append(",")
					.append(AD_Client_ID).append(",").append(AD_Org_ID)
					.append(",SysDate,").append(UpdatedBy).append(",SysDate,").append(UpdatedBy).append(",'Y'")
					.append(",'N'").append(",'N'").append(")");
				//
				int no = DB.executeUpdate(sql.toString(), trxName);
				if (no != 1)
				{
					log.log(Level.SEVERE, "Insert records=" + no + "; SQL=" + sql.toString());
					return false;
				}
				log.finer("New ID=" + ID);
			}
			else
				log.finer("Old ID=" + ID);
	
			//	Update Info -------------------------------------------------------
			
			sql = new StringBuffer ("UPDATE ")
				.append(tableName).append(" SET ");
			for (int i = 0; i < line.length; i++) {
				sql.append(format.getRow(i).getColumnName()).append(" = ");
				if(format.getRow(i).isString())
					sql.append("'").append(line[i]).append("'");
				else if(format.getRow(i).isNumber())
					sql.append(line[i]);
				sql.append(",");
			}
			sql.append("IsActive='Y',Processed='N',I_IsImported='N',Updated=SysDate,UpdatedBy=").append(UpdatedBy);
			sql.append(" WHERE ").append(tablePK).append("=").append(ID);
			//  Update Cmd
			int no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 1)
			{
				log.log(Level.SEVERE, tablePK + "=" + ID + " - rows updated=" + no);
				return false;
			}
			return true;
		}
		catch (SQLException e)
		{
			msg = sql.toString();
			log.log(Level.SEVERE, msg, e);
			throw new AdempiereException(msg,e);
		}
		finally {
			DB.close(rs, pstmt);
		}
	}	//	updateDB

	private boolean isNull(String field) {
		if (field == null 
				|| field.trim().equals("-") 
				|| field.trim().isEmpty()) {
			return true;
		}
		return false;
	}
}
