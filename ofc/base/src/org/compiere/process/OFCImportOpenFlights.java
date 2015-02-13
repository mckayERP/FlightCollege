package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.impexp.ImpFormat;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MResourceAssignment;
import org.compiere.util.DB;
import org.compiere.util.Env;

import HtmlGet.HtmlGet;

public class OFCImportOpenFlights extends SvrProcess {

	private static long mLock = 123567; // a number to use for an advisory lock.  Postgresql specific.
	
	/** The Berkley telegraph HtmlGet class */ 
	private HtmlGet mh;

	/** The primary TeSS Parameter Vector */
	private Vector<String> mTessParams = new Vector<String>();
	
	/** The secondary login TeSS Parameter Vector */
	private Vector<String> mSessionParams = new Vector<String>();
	
	/** The result vector from the screen scraper */
	private Vector<Object[]> mSessionResults = new Vector<Object[]>();
	private Vector<Object[]> mTessResults = new Vector<Object[]>();

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	
	/** Org to be imported to	  		*/
	private int				m_AD_Org_ID = 0;

	/** User doing the importing		*/
	private int				m_AD_User_ID = 0;

	/** Warehouse to use				*/
	private int				m_Warehouse_ID = 1000000;
	
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;
	
	/** Login first */
	private boolean			m_loginFirst = false;

	/** Login URL - the URL of the initial login */
	private String			m_loginURL;

	/** Session URL - the URL of where the session ID is identified. */
	private String			m_sessionURL;
	
	private ImpFormat 		m_format;
	
	private String			m_clientCheck;

	private String			m_tableName = "I_OFC_Flightsheet";
	private String			m_tablePK = "i_ofc_flightsheet_id";
	
	private String 			m_jsessionid;
	
	private Properties		m_ctx;
	
	
	@Override
	protected void prepare() {
				
		// Get any parameters and set the local variables

		if(mTessParams == null)
			mTessParams=new Vector<String>();
		mTessParams.clear();
		
		if(mTessResults == null)
			mTessResults=new Vector<Object[]>();		
		mTessResults.clear();

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_User_ID"))
				m_AD_User_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("LoginFirst"))
				m_loginFirst = "Y".equals(para[i].getParameter());
			else if (name.equals("LoginURL"))
				m_loginURL = para[i].getParameter().toString();
			else if (name.startsWith("Tess"))
				if (para[i].getParameter().toString().startsWith("@S:="))  // The scheduler will not strip this.
					mTessParams.add(para[i].getParameter().toString().substring(4));
				else
					mTessParams.add(para[i].getParameter().toString());
			else if (name.startsWith("Session"))
				if (para[i].getParameter().toString().startsWith("@S:="))  // The scheduler will not strip this.
					mSessionParams.add(para[i].getParameter().toString().substring(4));
				else
					mSessionParams.add(para[i].getParameter().toString());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		// Override the context - specifically if we are running on the server.
		// Otherwise, the server process won't be able to save the order to the
		// correct client.  Use the passed parameters.
		m_ctx = Env.getCtx();  // Make a copy of the context
		Env.setContext(m_ctx, "#AD_Client_ID", m_AD_Client_ID);  // Replace the client
		Env.setContext(m_ctx, "#AD_Org_ID", m_AD_Org_ID);		// Replace the org
		Env.setContext(m_ctx, "#AD_User_ID", m_AD_User_ID);		// Replace the user
		
		m_clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		m_format = ImpFormat.load ("OFC Import Open Flightsheets");
		if (m_format == null)
		{
				// Do something about it.
		}


	} // Prepare

	@Override
	protected String doIt() throws Exception {

		int count=0;
		int read = 0;
		int imported = 0;

		// Grab the lock
		if (!grabLock(get_TrxName()))
		{
			releaseLock(get_TrxName());
			return "Unable to capture lock.  Try again later.";
		}
		// Don't run at night. 11 pm to 7 am.
		Calendar currentTime = Calendar.getInstance(); // gets the current time
		if (currentTime.get(Calendar.HOUR_OF_DAY) < 6) // Don't run at night.
		{
			releaseLock(get_TrxName());
			return "Flight sheet import aborted - night";
		}

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			StringBuffer sql = new StringBuffer ("DELETE ").append(m_tableName)
				.append(" WHERE I_IsImported='Y'").append(m_clientCheck);
			try {
				int no = DB.executeUpdate(sql.toString(), get_TrxName());
				DB.commit(false, get_TrxName());
				log.info("Deleted the old imported data = " + no);
			}
			catch (Exception e) {
				releaseLock(get_TrxName());
				log.info("Tried to delete the old imported data but had a problem");				
			}			
		}

		Vector<String> tessargs = new Vector<String>();		
		Vector<String> sessionargs = new Vector<String>();
		StringBuffer sql = null;
		int no = 0;
		
		// Set the Session defaults
		sessionargs.clear();
		// defaults 1 to 3 are the login info.  Keep those.
		for (int i=0; i< mSessionParams.size();i++){
			if (mSessionParams.get(i).startsWith("default"))
				if (mSessionParams.get(i).startsWith("default1="))
					sessionargs.add(0,mSessionParams.get(i).substring(9));
				else if (mSessionParams.get(i).startsWith("default2="))
					sessionargs.add(1,mSessionParams.get(i).substring(9));
				else if (mSessionParams.get(i).startsWith("default3="))
					sessionargs.add(2,mSessionParams.get(i).substring(9));
		}

		// Initialize the screen scraper HTML Get
		boolean isDebug =false;
		String jscdir=null;
		String cookiefile="/tmp/cookies";
		String historyfile="/tmp/history";
			
		try {
			mh = new HtmlGet(cookiefile, historyfile,
						jscdir,
						isDebug);
		} catch (Exception e) {
			releaseLock(get_TrxName());
			log.log(Level.SEVERE, "Unable to initialize HtmlGet.",e);
			return "Unable to initialize HtmlGet.";
		}

		if(mh != null){
			if (m_loginFirst){
				// Login
				boolean encodeValues=true;
				boolean isPost=true;
				mh.processUrl(isPost, m_loginURL, null, encodeValues);
			}
		}
		else
		{
			releaseLock(get_TrxName());
			log.log(Level.SEVERE, "HtmlGet is null.");
			return "Unable to start. HtmlGet is null.";
		}

		// Get the session data.
		mSessionResults.clear();
		count = mh.runTess(null, mSessionResults, mSessionParams, sessionargs);
		log.info("Search for Session ID returned count: " + Integer.toString(count));
		if (count==0)
			m_jsessionid = "";
		else {
			String[] sesResults = (String[]) mSessionResults.get(0);
			if (sesResults[0] == "?")
				m_jsessionid = "";
			else
				m_jsessionid = sesResults[0];
				m_jsessionid = m_jsessionid.substring(0,m_jsessionid.length()-1);
		}
		log.finest("OFCImportOpenFlights: Session ID found: " + m_jsessionid);
		
		// Set the TeSS defaults
		tessargs.clear();
		// defaults 1 has login info.
		for (int i=0; i< mTessParams.size();i++){
			if (mTessParams.get(i).startsWith("default"))
				if (mTessParams.get(i).startsWith("default1="))
					tessargs.add(0,mTessParams.get(i).substring(9));
				else if (mTessParams.get(i).startsWith("default2="))
					tessargs.add(1,mTessParams.get(i).substring(9));
				else if (mTessParams.get(i).startsWith("default3="))
					tessargs.add(2,mTessParams.get(i).substring(9));
				else if (mTessParams.get(i).startsWith("default4="))
					tessargs.add(3,mTessParams.get(i).substring(9));
			else
				if (mTessParams.get(i).startsWith("url"))
					mTessParams.set(i, mTessParams.get(i) + m_jsessionid);
		}
		
		// Get the data.
		count = mh.runTess(null, mTessResults, mTessParams, tessargs);
		log.finest("OFCImportOpenFlights: TeSS imported record count: " + Integer.toString(count));
		

		//	For all rows - update/insert DB table
		int row = 0;
		for (row = 0; row < mTessResults.size(); row++)
		{
			read++;
			if (updateDB(m_ctx, (String[])mTessResults.get(row), null))
				imported++;
		}
		mTessResults.clear();
	//}		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// Go through the imported records and convert any fields
		// that require it.
		try {
			// Convert i_ofc_client_id to c_bpartner_id
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
				+ "SET C_BPartner_ID = bp.C_BPartner_ID "
				+ "FROM C_BPartner bp "
				+ "WHERE ifs.I_OFC_Client_ID = bp.\"value\"")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Client ID updated=" + no);
			
			// Convert the plane to the Resource ID
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
					+ "SET OFC_AC_Resource_ID = sr.S_Resource_ID "
					+ "FROM S_Resource sr "
					+ "WHERE (regexp_replace(ifs.i_ofc_call_marks, '-', '') = sr.\"name\" "
					+ "OR substring(ifs.i_ofc_call_marks from '\\((.+)\\)') = sr.\"name\")")
					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND sr.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Aircraft Resource IDs updated=" + no);
			
			// Find the instructor Resource ID
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
					+ "SET OFC_Inst_Resource_ID = sr.S_Resource_ID "
					+ "FROM S_Resource sr "
					+ "WHERE (  i_ofc_student_pax <> '' AND "
					+ "lower(regexp_replace(substring(\"name\" from ', (.+)') || substring(\"name\" from '(.+), '), ' ','')) = " 
					+ "lower(i_ofc_flightsheet.i_ofc_captain_pic))")
					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND sr.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Instructor Resource ID updated=" + no);
			
			// Remove the Flight number from the air time and fuel time for flights that 
			// have not yet been authorized.  This is an artifact of the screen scraper and
			// the impacts of the buttons on the open flight sheets.
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
					+ "SET i_ofc_invoice_number = ifs.i_ofc_air_time, "
					+ "i_ofc_air_time = '' "
					+ "where ifs.i_ofc_air_time like '(Flt%'")
					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("I_OFC_Airtime updated=" + no);
	
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
					+ "SET i_ofc_invoice_number = ifs.i_ofc_fuel, "
					+ "i_ofc_fuel = '' "
					+ "where ifs.i_ofc_fuel like '(Flt%'")
					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("I_OFC_Airtime updated=" + no);
	
			// Find the Invoice ID
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
					+ "SET OFC_Flightsheet_Line_Status = 'Closed', OFC_Invoice_ID = substring(i_ofc_flightsheet.i_ofc_invoice_number from '([0-9]+).Flt #')::Numeric "
					+ "WHERE (substring(ifs.i_ofc_invoice_number from '([0-9]+).Flt #') IS NOT NULL "
					+ "AND substring(i_ofc_flightsheet.i_ofc_invoice_number from '([0-9]+).Flt #') <> 'DNCO')")
					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Invoice ID updated=" + no);
	
			// Clear the error text
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
					+ "SET I_ErrorMsg = NULL ")
					.append("WHERE ifs.AD_Client_ID=" + m_AD_Client_ID)
					.append(" AND ifs.Processed<>'Y'")
					.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Error messages cleared =" + no);
			
			// Check for blank flight id
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
				+ "SET I_ErrorMsg = \'I_OFC_Flight_ID is blank.  \' "
				+ "WHERE (ifs.I_OFC_Flight_ID IS NULL)")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Blank flight ID marked as errors =" + no);
	
			// Check for blank c_bpartner_id
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
				+ "SET I_ErrorMsg = \'C_BPartner_ID is blank.  \' "
				+ "WHERE (ifs.C_BPartner_ID IS NULL)")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Unknown Clients marked as errors =" + no);
	
			// Mark all as processed
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs ")
				.append("SET Processed = 'Y'")
				.append(" WHERE ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.I_ErrorMsg is null")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Records marked as processed =" + no);
	
			commitEx();

			// Now copy all the records to the main table.
			// TODO Convert this to java using models
			log.info("Processing the Import");
			sql = new StringBuffer ("SELECT ofc_import_flightsheet()");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			log.info("Import processing completed.");
	
			commitEx();
			
			DB.close(rs, pstmt);
		}
		catch(SQLException e)
		{
			releaseLock(get_TrxName());
			DB.close(rs, pstmt);
			log.log(Level.SEVERE, "Error importing data: " + sql.toString(), e);
			return "SQL Execption: Please try again later";
		}

		// Create Orders.
		try
		{
			createOrders(m_ctx, null);
		}
		catch (Exception e)
		{
			releaseLock(get_TrxName());
			log.log(Level.SEVERE, "Error creating orders.", e);
			return "Error creating orders. Please try again later.";
		}
		
		releaseLock(get_TrxName());
		return "Flight Sheet Import R/I " + read + " / " + imported + "#";
		
	}

	public static boolean grabLock(String txName) {		
		
		StringBuffer sql = new StringBuffer();
		
		// Try to grab an advisory lock.  Return immediately with the result.
		String sqlfunc = "SELECT pg_try_advisory_lock(" + mLock + ")";
		PreparedStatement pstmt = DB.prepareStatement(sqlfunc, txName);
		try {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (!rs.getBoolean(1))
					releaseLock(txName);
				return rs.getBoolean(1);  // True if we have the lock. Otherwise false.
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			releaseLock(txName);
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static boolean releaseLock(String txName) {		
		
		String sqlfunc = "SELECT pg_advisory_unlock(" + mLock + ")";
		//String sqlfunc = "SELECT pg_advisory_unlock_all()";
		PreparedStatement pstmt = DB.prepareStatement(sqlfunc, txName);
		try {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);  // True if we have released the lock. Otherwise false.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
		return false;	
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
			.append(m_tablePK).append(") FROM ").append(m_tableName)
			.append(" WHERE (i_ofc_flight_id = ")
			.append(line[0]).append(")").append(m_clientCheck);
		//
		int count = 0;
		int ID = 0;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				count = rs.getInt(1);
				if (count == 1)
					ID = rs.getInt(2);
			}
			rs.close();
			pstmt.close();	
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			return false;
		}

		//	Insert Basic Record -----------------------------------------------
		if (ID == 0)
		{
			ID = DB.getNextID(ctx, m_tableName, null);		//	get ID
			sql = new StringBuffer("INSERT INTO ")
				.append(m_tableName).append("(").append(m_tablePK).append(",")
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
			.append(m_tableName).append(" SET ");
		for (int i = 0; i < line.length; i++) {
			sql.append(m_format.getRow(i).getColumnName()).append(" = ");
			if(m_format.getRow(i).isString())
				sql.append("'").append(line[i]).append("'");
			else if(m_format.getRow(i).isNumber())
				sql.append(line[i]);
			sql.append(",");
		}
		sql.append("IsActive='Y',Processed='N',I_IsImported='N',Updated=SysDate,UpdatedBy=").append(UpdatedBy);
		sql.append(" WHERE ").append(m_tablePK).append("=").append(ID);
		//  Update Cmd
		int no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 1)
		{
			log.log(Level.SEVERE, m_tablePK + "=" + ID + " - rows updated=" + no);
			return false;
		}
		return true;
	}	//	updateDB

	private boolean createOrders(Properties ctx, String trxName)
	{
		log.fine("OFCImportOpenFlight - creating orders");
		int inst_id, inst_ra_id, ac_id, ac_ra_id, order_id;
		long timediff;
		BigDecimal flightTime, hours, brief, sim, fuel;
		
		Timestamp engStart = new Timestamp(0);
		Timestamp engStop = new Timestamp(0);

		// find the flight sheet lines that have not been ordered
		String sql = 

			"SELECT fs.*, ac.name AS AC, inst.M_Product_ID AS inst_Prod_ID, inst.name as Inst, ac.M_Product_ID AS ac_Prod_ID, " +
			"ac.fsc_id AS fsc_id, ac.fsc_desc AS fsc_desc, " +
			"ai.adv_prod_id AS adv_prod_id, ai.adv_prod_desc AS adv_prod_desc, " +
			"ns.ns_prod_id AS ns_prod_id, ns.ns_prod_desc AS ns_prod_desc, " +
			"intro.intro_prod_id as intro_prod_id, intro.intro_prod_desc AS intro_prod_desc " +
			"FROM adempiere.OFC_Flightsheet fs " +
			"LEFT OUTER JOIN (SELECT sr.*, mp.M_Product_ID " +
			    "FROM adempiere.S_Resource sr, adempiere.M_Product mp " +
				"WHERE sr.S_Resource_ID = mp.S_Resource_ID) inst " +
			"ON inst.S_Resource_ID = fs.OFC_Inst_Resource_ID " +
			"LEFT OUTER JOIN (SELECT oai.ofc_flight_course_type, oai.M_Product_ID AS adv_prod_id, mp.Description AS adv_prod_desc " +
				"FROM adempiere.ofc_advanced_inst oai, adempiere.M_Product mp " +
				"WHERE oai.M_Product_ID = mp.M_Product_ID) ai " +
			"ON fs.ofc_course_type = ai.ofc_flight_course_type " +
			"LEFT OUTER JOIN (SELECT ons.ofc_flight_course_type, ons.OFC_NoShow_Product_ID AS ns_prod_id, mp.Description AS ns_prod_desc " +
				"FROM adempiere.ofc_advanced_inst ons, adempiere.M_Product mp " +
				"WHERE ons.OFC_NoShow_Product_ID = mp.M_Product_ID) ns " +
				"ON fs.ofc_course_type = ns.ofc_flight_course_type " +
			"LEFT OUTER JOIN (SELECT int.ofc_flight_course_type, int.OFC_Intro_Product_ID AS intro_prod_id, mp.Description AS intro_prod_desc " +
				"FROM adempiere.ofc_advanced_inst int, adempiere.M_Product mp " +
				"WHERE int.OFC_Intro_Product_ID = mp.M_Product_ID) intro " +
				"ON fs.ofc_course_type = intro.ofc_flight_course_type " +
			"LEFT OUTER JOIN (SELECT sr.*, mp.M_Product_ID, fsc.M_Product_ID AS fsc_id, fsc.description AS fsc_desc " +
			    "FROM adempiere.S_Resource sr, adempiere.M_Product mp " +
				"LEFT OUTER JOIN adempiere.M_Product fsc " +
				"ON 'FSC' || mp.classification = fsc.value " +
				"WHERE sr.S_Resource_ID = mp.S_Resource_ID) ac " +
			"ON ac.S_Resource_ID = fs.OFC_AC_Resource_ID " +
			"WHERE fs.OFC_Flightsheet_Line_Status is null AND " +
			    "fs.OFC_Course_Type <> 'Cancelled' AND " +
				"fs.C_Order_ID is null";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
	    	rs = pstmt.executeQuery();
	    	while(rs.next())
		    {
	    		log.fine("Creating orders - Flight ID: " + rs.getString("OFC_Flight_ID") + " Type: " + rs.getString("OFC_Course_Type"));

	    		// if the selected flight is cancelled or pending, do nothing
	    		if (rs.getString("OFC_Course_Type").matches("Cancelled") || 
	    			rs.getString("OFC_Course_Type").matches("\\(pending\\)"))
	    			continue;

	    		flightTime = rs.getBigDecimal("OFC_Flight_Time_Dual")
	    			.add(rs.getBigDecimal("OFC_Flight_Time_Solo"))
	    			.add(rs.getBigDecimal("OFC_Flight_Time_Rental"))
	    			.add(rs.getBigDecimal("OFC_Flight_Time_Intro"))
	    			.add(rs.getBigDecimal("OFC_Flight_Time_Charter"))
	    			.add(rs.getBigDecimal("OFC_Flight_Time_Nonrev"));
	    		brief = rs.getBigDecimal("OFC_Briefing");
	    		sim = rs.getBigDecimal("OFC_Simulator");
	    		fuel = rs.getBigDecimal("OFC_Fuel");

	    		// Look for flightsheet lines that have billable time or a No-Show.
	    		// If there is no time or no-show, then the line is not ready for billing 
	    		// yet.
	    		if (!(flightTime.add(brief).add(sim).add(fuel).doubleValue()>0.0 || 
	    				rs.getString("OFC_Course_type").matches("No-Show")))
	    			continue;

	    		log.fine("Creating orders - Billable time: " + flightTime.add(brief).add(sim).add(fuel).toString());
	    		
				order_id = 0;
	    		
	    		// If we have passed the above tests, the flight sheet line is valid
	    		// and ready to be converted to an order.
	    		// Check for any open orders with same date, C_BPartner_ID that
	    		// are draft and already referenced from the flight sheet.
	    		// If we find one, add the flight to it. Otherwise, create a new
	    		// order.
	    		final String sql2 = 
					"SELECT co.* " +
					"FROM adempiere.C_Order co " +
					"JOIN adempiere.OFC_Flightsheet fs " +
					"ON fs.C_Order_ID = co.C_Order_ID " +
					"JOIN adempiere.C_BPartner bp " +
					"ON bp.c_bpartner_id = co.C_BPartner_ID " +
					"WHERE co.DocStatus = 'DR' AND " +
					"co.DateOrdered=? AND " +
					"co.C_BPartner_ID=? AND " +
					"bp.name <> 'Valued Customer'"; // Hardcoded - This is a rough kludge to ignore valued customer
	    			// We want the Valued Customer accounts to appear in their own orders rather than
	    			// combining them.
		     	PreparedStatement pstmt2 = null;
				ResultSet rso = null;
				

				try
				{
				     pstmt2 = DB.prepareStatement(sql2, trxName);
				     DB.setParameters(pstmt2, new Object[]{rs.getTimestamp("OFC_Flight_Date"),rs.getInt("C_BPartner_ID")});
				     rso = pstmt2.executeQuery();
				     while(rso.next())
				     {
				    	 // An order exists.
				    	 order_id = rso.getInt("C_Order_ID");
				    	 log.fine("  Order exists - Order ID=" + order_id);

				     }
				}  //try
				// catch errors
				catch (SQLException e)
				{
				     throw new DBException(e, sql);
				}
				// Close the ResultSet in a finally statement
				finally
				{
				     DB.close(rso, pstmt2);
				     rso = null; pstmt2 = null;
				}

	    		log.fine("Creating orders - Order ID: " + order_id);

				// Create or open the order. order_id will be valid or zero.
				// If it is zero, create a new order.
				MOrder order = new MOrder(ctx,order_id,null);
				if (order == null)
				{
					log.log(Level.SEVERE, "OFCImportOpenFlights: After create: can't create order for flight: " + rs.getString("OFC_Flight_ID"));
					continue;
				}


				// Check to see if the business partner is set
				if ((order.getC_BPartner_ID()==0))
				{
					order.setC_BPartner_ID(rs.getInt("C_BPartner_ID"));
					order.setDateOrdered(rs.getTimestamp("OFC_Flight_Date"));
					order.setM_Warehouse_ID(m_Warehouse_ID); //Use default
					order.setC_DocTypeTarget_ID("WI"); //Credit Order
					
					// Trap errors here.
					if (!order.save())
					{
						log.log(Level.SEVERE, "OFCImportOpenFlights: After save: can't create order for flight: " + 
								rs.getString("OFC_Flight_ID") + ", C_BPartner_ID " + rs.getInt("C_BPartner_ID") + 
								" and flight date " + rs.getTimestamp("OFC_Flight_Date"));
						continue;
					}
				}

				timediff = 0;
	        	inst_id = rs.getInt("OFC_Inst_Resource_ID");
	        	ac_id = rs.getInt("OFC_AC_Resource_ID");
	        	String fltDate = new SimpleDateFormat("dd MMM yy").format(rs.getTimestamp("OFC_Flight_Date"));

	        	// Deal with No-Shows
	     		// Add order line for AC No Show
	     		if ((rs.getString("OFC_Course_Type").matches("No-Show")) && !(rs.getInt("ns_prod_id")==0))
	     		{
		    		log.fine("   There is a no-show charge to add.");
	     			if (!(ac_id==0))
	     			{
			        	StringBuilder description = new StringBuilder("No-Show fee for Flt ID: " + rs.getInt("OFC_Flight_ID"))
			        	 	.append(" Date: ")
			        	 	.append(fltDate)
			        	 	.append(" A/C: ")
			        	 	.append(rs.getString("ac"));
		     			MOrderLine line = new MOrderLine(order);
		     			line.setM_Product_ID(rs.getInt("ns_prod_id"));
		     			line.setDescription(description.toString());
		     			line.setQty(new BigDecimal(2));
		     			line.save();	     				
	     			}
	     			if (!(inst_id==0))
	     			{
			        	StringBuilder description = new StringBuilder("No-Show fee for Flt ID: " + rs.getInt("OFC_Flight_ID"))
			        	 	.append(" Date: ")
			        	 	.append(fltDate)
			        	 	.append(" Instructor: ")
			        	 	.append(rs.getString("Inst"));
		     			MOrderLine line = new MOrderLine(order);
		     			line.setM_Product_ID(rs.getInt("ns_prod_id"));
		     			line.setDescription(description.toString());
		     			line.setQty(new BigDecimal(2));
		     			line.save();	     				
	     			}
		        	 
	     		}
	     		else
	     		{  // not a now show
	        	 
		        	 // Figure out the times of use
		        	 // First check to see if the engine start time is valid
		        	 if (!(rs.getTimestamp("OFC_Engine_Start")==null))
		        	 {
		        		// if it is, check if the engine stop time is valid
		        		engStart = rs.getTimestamp("OFC_Engine_Start");
		        	 	if (!(rs.getTimestamp("OFC_Engine_Stop")==null))
		        	 	{
		        	 		// it is valid, now calculate the time difference and check 
		        	 		// with the flightTime calculated above
		        	 		engStop = rs.getTimestamp("OFC_Engine_Stop");
		        	 		timediff = engStop.getTime() - engStart.getTime();
			        	 	hours = new BigDecimal(((double) timediff)/(60*60*1000));
			        	 	
			        	 	if (!(hours.compareTo(flightTime)==0)){
			        	 		log.fine("Flight Time = " + flightTime.toString() +
			        	 				 " Eng Stop - Eng Start = " + hours.toString());
			        	 	}
		        	 	}
		        	 	else
		        	 	{
		        	 		// The engine start is valid but the stop time isn't valid.
		        	 		// Use the flight time.  Convert from hours to milliseconds
		        	 		engStop.setTime(engStart.getTime() + 
		        	 				flightTime.intValue()*60*60*1000);
		        	 	}
		        	 }
		        	 else 
		        	 {
		        		 // The engine start is invalid. Assume there is no
		        		 // flight time.  Use the flight date & sim + brief time;
		        		 engStart = rs.getTimestamp("OFC_Flight_Date");
		        		 engStop.setTime(engStart.getTime() + sim.add(brief).intValue()*60*60*1000);
		        		 log.finest("Engine start invalid. Flight Time = " + flightTime.toString());
		        	 }
	
		        	 // AC resource assignment
		        	 if (!(ac_id==0) && !(flightTime.doubleValue()==0.0))
		        	 {
		 	    		 log.finest("Creating orders - AC: " + rs.getString("AC"));
			        	 String engStartTime = new SimpleDateFormat("HH:mm").format(engStart);
			        	 String engStopTime = new SimpleDateFormat("HH:mm").format(engStop);
			        	 String wheelsUpTime = new SimpleDateFormat("HH:mm").format(rs.getTimestamp("OFC_Wheels_Up"));
			        	 String wheelsDownTime = new SimpleDateFormat("HH:mm").format(rs.getTimestamp("OFC_Wheels_Down"));
	
		        		 StringBuilder name = new StringBuilder("Flt ID: ")
			        	 	.append(rs.getInt("OFC_Flight_ID"))
			        	 	.append(" PIC: ")
			        	 	.append(rs.getString("OFC_Captain_PIC"))
			        	 	.append(" Student/Pax: ")
			        	 	.append(rs.getString("OFC_student_pax"));
			        	 StringBuilder description = new StringBuilder("Flt ID: " + rs.getInt("OFC_Flight_ID"))
			        	 	.append(" Date: ")
			        	 	.append(fltDate)
			        	 	.append(" A/C: ")
			        	 	.append(rs.getString("ac"))
			        	 	.append(" Type: ")
			        	 	.append(rs.getString("OFC_Course_Type"))
			        	 	.append("\n")
			        	 	.append("PIC: ")
			        	 	.append(rs.getString("OFC_Captain_PIC"))
			        	 	.append(" Pax: ")
			        	 	.append(rs.getString("OFC_student_pax"))
			        	 	.append("\n")
			        	 	.append("Exercizes: ")
			        	 	.append(rs.getString("OFC_Intended_Flight"));
			        	 if (!(rs.getInt("OFC_Num_Legs")==1))
			        	 {
			        		 description.append(" # Legs: ")
			        		 .append(rs.getInt("OFC_Num_Legs"));
			        	 }
			        	 description.append("\n")
			        	 	.append("Start: ")
			        		.append(engStartTime)
			        		.append(" Stop: ")
			        		.append(engStopTime)
			        		.append(" Flight Time: ")
			        		.append(flightTime.setScale(2).toPlainString())
			        		.append("\n")
			        		.append("Up: ")
			        		.append(wheelsUpTime)
			        		.append(" Down: ")
			        		.append(wheelsDownTime)
			        		.append(" Air Time: ")
			        		.append(rs.getBigDecimal("OFC_Air_Time").setScale(2).toPlainString());
	 
			        	 MResourceAssignment ra = new MResourceAssignment(ctx,0,null);
			        	 ra.setS_Resource_ID(ac_id);
			        	 ra.setAssignDateFrom(engStart);
			        	 ra.setAssignDateTo(engStop);
			        	 // only use flight time and sim.  The sim appears as an aircraft.
			        	 ra.setQty(flightTime.add(sim));
			        	 ra.setIsConfirmed(true);
			        	 ra.setName(name.toString());
			        	 ra.setDescription(description.toString());
			        	 ra.save();		        	 
	
			        	 if ((rs.getInt("intro_prod_id")==0))  // ignore intro flights
			        	 {
				        	// Add order line for Aircraft
				     		MOrderLine line = new MOrderLine(order);
				     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
				     		line.setM_Product_ID(rs.getInt("AC_Prod_ID"));
				     		line.setDescription(description.toString());
				     		line.setQty(flightTime);
				     		line.save();
			        	 
				     		// Add order line for fuel surcharge
				     		if (!(rs.getInt("fsc_id")==0))
				     		{
				     			line = new MOrderLine(order);
				     			line.setM_Product_ID(rs.getInt("fsc_id"));
				     			line.setDescription("");
				     			line.setQty(flightTime);
				     			line.save();
				     		}
			        	 }
			        	 else  // Add the intro flight
			        	 {
				        	// Add order line for Intro Flight
			        		description = new StringBuilder("Introductory Flight ")
			        			.append("Flt ID: " + rs.getInt("OFC_Flight_ID"))
				        	 	.append(" Date: ")
				        	 	.append(fltDate)
				        	 	.append(" A/C: ")
				        	 	.append(rs.getString("ac"))
				        	 	.append("\n")
				        	 	.append("PIC: ")
				        	 	.append(rs.getString("OFC_Captain_PIC"))
				        	 	.append(" Pax: ")
				        	 	.append(rs.getString("OFC_student_pax"));
				     		MOrderLine line = new MOrderLine(order);
				     		line.setM_Product_ID(rs.getInt("Intro_Prod_ID"));
				     		line.setDescription(description.toString());
				     		line.setQty(new BigDecimal(1));
				     		line.save();

				        	// Add order line for Aircraft
				     		line = new MOrderLine(order);
				     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
				     		line.setM_Product_ID(rs.getInt("AC_Prod_ID"));
				     		line.setDescription(description.toString());
				     		line.setQty(flightTime);
				     		line.setPrice(BigDecimal.ZERO);
				     		line.save();

			        	 }
		        	 } // Aircraft
	
		        	 // Instructor resource assignment
		        	 if (!(inst_id==0))
		        	 {
		 	    		 log.finest("Creating orders - Inst: " + rs.getString("Inst"));
	
		 	    		 StringBuilder name = new StringBuilder("Flight ID ")
			        	 	.append(rs.getInt("OFC_Flight_ID"))
			        	 	.append(" PIC: ")
			        	 	.append(rs.getString("OFC_Captain_PIC"))
			        	 	.append(" Student/Pax: ")
			        	 	.append(rs.getString("OFC_student_pax"));
			        	 StringBuilder description = new StringBuilder("Flt ID " + rs.getInt("OFC_Flight_ID"))
			        	 	.append("\nFlight Type: ")
			        	 	.append(rs.getString("OFC_Course_Type"))
			        	 	.append(" Exercises: ")
			        	 	.append(rs.getString("OFC_Intended_Flight"))
			        	 	.append("\n")
			        	 	.append("PIC: ")
			        	 	.append(rs.getString("OFC_Captain_PIC"))
			        	 	.append(" Student/Pax: ")
			        	 	.append(rs.getString("OFC_student_pax"))
			        	 	.append("\n");
	
			        	 StringBuilder resDesc = new StringBuilder();
			        	 resDesc.append(description);
			        	 if (!(flightTime.doubleValue()==0.0))
			        		 resDesc.append(" Flight Instruction");
			        	 if (!(sim.doubleValue()==0.0))
			        		 resDesc.append(" Sim Instruction");
			        	 if (!(brief.doubleValue()==0.0))
			        		 resDesc.append(" and Ground Brief");
	
			        	 MResourceAssignment ra = new MResourceAssignment(ctx,0,null);
			        	 ra.setS_Resource_ID(inst_id);
			        	 ra.setAssignDateFrom(engStart);
			        	 ra.setAssignDateTo(engStop);
			        	 // add any sim and brief time to the instructor time.
			        	 ra.setQty(flightTime.add(sim).add(brief));
			        	 ra.setIsConfirmed(true);
			        	 ra.setDescription(resDesc.toString());
			        	 ra.save();		        	 
	
			        	 // Ignore any flight instruction charges for Intro flights.
			        	 if ((rs.getInt("intro_prod_id")==0))
			        	 {
				        	 // Add order line for flight training
				        	 if(!(flightTime.doubleValue()==0.0))
		        			 {
					        	StringBuilder fltDesc = new StringBuilder("Flight Instruction for ");
					        		fltDesc.append(description);
					     		MOrderLine line = new MOrderLine(order);
					     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
					     		line.setM_Product_ID(rs.getInt("inst_Prod_ID"));
					     		line.setDescription(fltDesc.toString());
					     		line.setQty(flightTime);
					     		line.save(); 
		        			 }
				        	 
				        	 // Add order line for ground briefing
				        	 if(!(brief.doubleValue()==0.0))
		        			 {
				        		StringBuilder grndDesc = new StringBuilder("Ground briefing for ");
				        		grndDesc.append("Flt ID: ")
				        			.append(rs.getString("OFC_Flight_ID"));
					     		MOrderLine line = new MOrderLine(order);
					     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
					     		line.setM_Product_ID(rs.getInt("inst_Prod_ID"));
					     		line.setDescription(grndDesc.toString());
					     		line.setQty(brief);
					     		line.save(); 
		        			 }
				        	 // Add order line(s) for sim
				        	 if(!(sim.doubleValue()==0.0))
		        			 {
				        		StringBuilder simDesc = new StringBuilder("Sim Instruction for ");
				        		simDesc.append("Flt ID: ")
				        			.append(rs.getString("OFC_Flight_ID"))
					        	 	.append("\nSim Type: ")
					        	 	.append(rs.getString("OFC_Course_Type"))
					        	 	.append(" Exercises: ")
					        	 	.append(rs.getString("OFC_Intended_Flight"))
					        	 	.append("\n")
					        	 	.append("PIC: ")
					        	 	.append(rs.getString("OFC_Captain_PIC"))
					        	 	.append(" Student/Pax: ")
					        	 	.append(rs.getString("OFC_student_pax"));
				        		
					     		MOrderLine line = new MOrderLine(order);
					     		line.setM_Product_ID(rs.getInt("AC_Prod_ID"));
					     		line.setDescription(simDesc.toString());
					     		line.setQty(sim);
					     		line.save(); 
		
				        		simDesc = new StringBuilder("Sim Instruction for ");
				        		simDesc.append("Flt ID: ")
				        			.append(rs.getString("OFC_Flight_ID"));
					     		line = new MOrderLine(order);
					     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
					     		line.setM_Product_ID(rs.getInt("inst_Prod_ID"));
					     		line.setDescription(simDesc.toString());
					     		line.setQty(sim);
					     		line.save(); 
		
		        			 }  //sim
				        	 
				        	 // Add order line for advanced flight training
				        	 if(!(rs.getInt("adv_prod_id")==0))
				        	 {
				        		StringBuilder adv_desc = new StringBuilder(rs.getString("adv_prod_desc"));
				        		adv_desc.append(" for Flt ID ")
				        			.append(rs.getString("ofc_flight_id"));
				        		MOrderLine line = new MOrderLine(order);
					     		line.setM_Product_ID(rs.getInt("adv_prod_id"));
					     		line.setDescription(adv_desc.toString());
					     		line.setQty(flightTime.add(brief).add(sim));
					     		line.save();
				        	 }
			        	 } // Not Intro
			        	 else // Its an Intro
			        	 {
				        	 // Add order line f
				        	 if(!(flightTime.doubleValue()==0.0))
		        			 {
					        	StringBuilder fltDesc = new StringBuilder("Instructor for intro");
					        		fltDesc.append(description);
					     		MOrderLine line = new MOrderLine(order);
					     		line.setS_ResourceAssignment_ID(ra.getS_ResourceAssignment_ID());
					     		line.setM_Product_ID(rs.getInt("inst_Prod_ID"));
					     		line.setDescription(fltDesc.toString());
					     		line.setQty(flightTime);
					     		line.setPrice(BigDecimal.ZERO);
					     		line.save(); 
		        			 }

			        	 }
		        	 } // Instructor

	     		} // No-show
	     		
	    		log.fine("  Order Created.  Order ID=" + order.getC_Order_ID());

	        	 // Update the flight sheet line with the Order ID
	        	 sql = "UPDATE adempiere.ofc_flightsheet SET " +
	        	 		"OFC_Flightsheet_Line_Status = 'Closed', " +
	        	 		"C_Order_ID = " + order.getC_Order_ID() + " " +
	        	 		"WHERE ofc_flightsheet.OFC_Flightsheet_ID = " + rs.getInt("OFC_Flightsheet_ID"); 
	     		//PreparedStatement pstmt3 = null;
	    		int no = 0;
	    		try
	    		{
	 	    		 log.finest("Creating orders - Updating flightsheet Flightsheet ID: " + rs.getInt("OFC_Flightsheet_ID") + " Order: " + order.getC_Order_ID() + " " + sql.toString());
	 	    		 no = DB.executeUpdate(sql.toString(), trxName);
	 	    		 //pstmt3 = DB.prepareStatement(sql, null);
	    		     //DB.setParameters(pstmt3, new Object[] {rs.getInt("OFC_Flightsheet_ID")});
	    		     //no = pstmt3.executeUpdate();
	    		}
				catch (SQLException e)
				{
				     throw new DBException(e, sql);
				}
				// Close the ResultSet in a finally statement
				finally
				{
					//DB.close(pstmt3);
				     //rs3 = null; pstmt3 = null;
				}
				
				// commit the order save for the next loop
			    commitEx(); 
    	 		
		     }// next flightsheet rs
		}  //try
		// catch exceptions
		catch (SQLException e)
		{
		     throw new DBException(e, sql);
		}
		// Close the ResultSet in a finally statement
		finally
		{

			DB.close(rs, pstmt);
		    rs = null; pstmt = null;
		}

        // TODO update the Flight sheet with the invoice ID.
		
		//List<GridTab> incTabList = mTab.getIncludedTabs();
		//for (GridTab ttab : incTabList)
		//{
			// TODO Figure out how to refresh the included tab so it shows the data.
			//ttab.dataRefresh();
		//}
		return true;
	} // openOrders

}
