package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import oracle.sql.DATE;

import org.compiere.impexp.ImpFormat;
import org.compiere.util.DB;
import org.compiere.util.Env;

import HtmlGet.HtmlGet;
import org.compiere.process.OFCImportOpenFlights;

public class OFCImportFlightSheets extends SvrProcess {

	/** The Berkley telegraph HtmlGet class */ 
	private HtmlGet mh;

	/** The TeSS Parameter Vector */
	private Vector<String> mTessParams = new Vector<String>();
	
	/** The result vector from the screen scraper */
	private Vector<Object[]> mTessResults = new Vector<Object[]>();

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;
	
	/** Login first */
	private boolean			m_loginFirst = false;

	/** Login URL */
	private String			m_loginURL;
	
	/** System Date */
	private Calendar		m_sysDate = Calendar.getInstance();
	private Calendar		m_startDate = Calendar.getInstance();
	private Calendar		m_stopDate = Calendar.getInstance();
	private Timestamp		m_time;
	private DateFormat		m_dateFormatter;
	
	/** Effective						*/
	private Timestamp		m_DateValue = null;

	private ImpFormat 		m_format;
	
	private String			m_clientCheck;

	private String			m_tableName = "I_OFC_Flightsheet";
	private String			m_tablePK = "i_ofc_flightsheet_id";
	
	@Override
	protected void prepare() {
		
		// Get any parameters and set the local variables

		if(mTessParams == null)
			mTessParams=new Vector<String>();
		mTessParams.clear();
		
		if(mTessResults == null)
			mTessResults=new Vector<Object[]>();		
		mTessResults.clear();

		// Is this used?
		if (m_DateValue == null)
			m_DateValue = new Timestamp (System.currentTimeMillis());
		
		m_dateFormatter = new SimpleDateFormat("dd-MMM-yy");
		m_sysDate.setLenient(true);  // Set the date to lenient
		m_startDate.setLenient(true);
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
			{
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
				m_clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
			}
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("LoginFirst"))
				m_loginFirst = "Y".equals(para[i].getParameter());
			else if (name.equals("DateRange")){
				if (para[i].getParameter() == null)  // start at the date of last import
				{
					//	Get the date of the last import ----------------------------
					StringBuffer sql = new StringBuffer ("SELECT MAX(OFC_Flight_Date) ")
						.append(" FROM ofc_flightsheet")
						.append(" WHERE (ofc_flight_id IS NOT NULL)")
						.append(m_clientCheck);
					
					try
					{
						PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
						ResultSet rs = pstmt.executeQuery();
						if (rs.next())
						{
							// Go back a day
							m_startDate.setTimeInMillis(rs.getTimestamp(1).getTime() - 24*60*60*1000);
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
					m_time = (Timestamp) para[i].getParameter();  // this is the start date of the import
					m_startDate.setTimeInMillis(m_time.getTime()); // convert from Timestamp to Calendar
				}
				if (para[i].getParameter_To() == null)  // end today
				{
				    m_sysDate.setTimeInMillis(new Timestamp(System.currentTimeMillis()).getTime()); // convert from Timestamp to Calendar
				    m_stopDate = (Calendar) m_sysDate.clone();

				}	
				else
				{
					m_time = (Timestamp) para[i].getParameter_To();  // this is the end date of the import
					m_stopDate.setTimeInMillis(m_time.getTime()); // convert from Timestamp to Calendar
				}
			}
			else if (name.equals("LoginURL"))
				m_loginURL = para[i].getParameter().toString();
			else if (name.startsWith("Tess"))
				if (para[i].getParameter().toString().startsWith("@S:="))  // The scheduler will not strip this.
					mTessParams.add(para[i].getParameter().toString().substring(4));
				else
					mTessParams.add(para[i].getParameter().toString());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		m_format = ImpFormat.load ("OFC Flightsheet Import");
		if (m_format == null)
		{
				// Do something about it.
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
				log.info("Tried to delete the old imported data but had a problem");				
			}
			
		}

		// Initialize Html Get
		boolean isDebug =false;
		String jscdir=null;
		String cookiefile="/tmp/cookies";
		String historyfile="/tmp/history";
			
		try {
			mh = new HtmlGet(cookiefile, historyfile,
						jscdir,
						isDebug);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Unable to initialize HtmlGet.",e);
		}

	} // Prepare

	@Override
	protected String doIt() throws Exception {

		int count=0;
		int read = 0;
		int imported = 0;
		
		// Grab the lock
		if (!OFCImportOpenFlights.grabLock(get_TrxName()))
		{
			OFCImportOpenFlights.releaseLock(get_TrxName());
			return "Unable to capture lock.  Try again later.";
		}
		
		Vector<String> tessargs = new Vector<String>();		
		StringBuffer sql = null;
		int no = 0;

		// Change the TeSS defaults
		tessargs.clear();
		// defaults 1 and 2 are the login info.  Keep those.
		for (int i=0; i< mTessParams.size();i++){
			if (mTessParams.get(i).startsWith("default"))
				if (mTessParams.get(i).startsWith("default1="))
					tessargs.add(0,mTessParams.get(i).substring(9));
				else if (mTessParams.get(i).startsWith("default2="))
					tessargs.add(1,mTessParams.get(i).substring(9));
		}
		tessargs.add(2,"");
		tessargs.add(3,"");
		tessargs.add(4,"");

		if(mh != null){
			if (m_loginFirst){
				// Login
				boolean encodeValues=true;
				boolean isPost=true;
				mh.processUrl(isPost, m_loginURL, null, encodeValues);
			}
		}
    Calendar daycount = Calendar.getInstance();
    daycount.setLenient(true);

    for(daycount=m_startDate; daycount.before(m_stopDate) ; daycount.add(Calendar.DAY_OF_MONTH, 1)){	// Go back in time
		// The next three defaults are the date - overwrite with the sysDate.
		if (!(daycount == null)) {
			tessargs.set(2,Integer.toString(daycount.get(Calendar.MONTH))); //tess uses month #s starting at 0
			tessargs.set(3,Integer.toString(daycount.get(Calendar.DAY_OF_MONTH)));
			tessargs.set(4,Integer.toString(daycount.get(Calendar.YEAR)));
			log.info("TeSS importing data for: " + m_dateFormatter.format(daycount.getTime()));
		}
		
		// Get the data.
		count = mh.runTess(null, mTessResults, mTessParams, tessargs);
		log.info("TeSS imported record count: " + Integer.toString(count));
		if (count==0)
			continue; // No data on that date

		//	For all rows - update/insert DB table
		int row = 0;
		for (row = 0; row < mTessResults.size(); row++)
		{
			read++;
			if (updateDB(Env.getCtx(), (String[])mTessResults.get(row), null))
				imported++;
		}
		mTessResults.clear();
	}		
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
			
			// Find the Invoice ID
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
					+ "SET OFC_Invoice_ID = substring(i_ofc_flightsheet.i_ofc_invoice_number from '([0-9]+).Flt #')::Numeric "
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
			
			// Check for blank c_bpartner_id
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
				+ "SET I_ErrorMsg = \'C_BPartner_ID is blank.  \' "
				+ "WHERE (ifs.C_BPartner_ID IS NULL "
				+ "OR ifs.I_OFC_Client_ID IS NULL)")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Unknown Clients marked as errors =" + no);
	
			// Check for blank flight id
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
				+ "SET I_ErrorMsg = \'I_OFC_Flight_ID or I_OFC_Client_ID is blank.  \' "
				+ "WHERE (ifs.I_OFC_Flight_ID IS NULL "
				+ "OR ifs.I_OFC_Client_ID IS NULL)")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Unknown Clients marked as errors =" + no);
	
			// Mark all as closed - anything imported by this class is closed by default
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs ")
				.append("SET OFC_Flightsheet_Line_Status = 'Closed' ")
				.append(" WHERE ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Records marked as processed =" + no);
	
			// Mark all as processed
			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs ")
				.append("SET Processed = 'Y' ")
				.append("WHERE ifs.I_ErrorMsg IS NULL")
				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
				.append(" AND ifs.Processed<>'Y'")
				.append(" AND ifs.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Records marked as processed =" + no);
				
			// Now copy all the records to the main table.
			// TODO Convert this to java
			log.info("Processing the Import");
			String sqlfunc = "SELECT ofc_import_flightsheet()";
			PreparedStatement pstmt = DB.prepareStatement(sqlfunc, get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			log.info("Import processing completed.");
			
			commitEx();
	    }
	    catch (SQLException e) {
	    	OFCImportOpenFlights.releaseLock(get_TrxName());
			log.log(Level.SEVERE, "Error importing data: " + sql.toString(), e);
			return "SQL Execption: Please try again later";
    }

		OFCImportOpenFlights.releaseLock(get_TrxName());
		return "Flight Sheet Import R/I " + read + " / " + imported + "#";
	}

	private boolean updateDB (Properties ctx, String[] line, String trxName)
	{
		if (line == null || line[0].length() == 0)
		{
			log.finest("No Line");
			return false;
		}

		//  Standard Fields - use OFCAdmin
		int AD_Client_ID = m_AD_Client_ID;
		int AD_Org_ID = 1000000;
		int UpdatedBy = 1000000;

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
			log.fine(m_tablePK + "=" + ID + " - rows updated=" + no);
			return false;
		}
		return true;
	}	//	updateDB

}
