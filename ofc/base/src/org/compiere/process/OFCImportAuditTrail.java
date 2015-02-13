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

import org.compiere.impexp.ImpFormat;
import org.compiere.util.DB;
import org.compiere.util.Env;

import HtmlGet.HtmlGet;

public class OFCImportAuditTrail extends SvrProcess {

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
	
	/** The start date of the import */
	private Calendar		m_startDate = Calendar.getInstance();
	
	/** Calendar date formater */
	private DateFormat		m_dateFormatter;

	/** A temporary Timestamp used to convert parameters to Calendar objects */
	private Timestamp		m_time;
	
	/** Effective						*/
	private Timestamp		m_DateValue = null;

	private ImpFormat 		m_format;
	
	private String			m_clientCheck;

	private String			m_tableName = "i_ofc_audit_trail";
	private String			m_tablePK = "i_ofc_audit_trail_id";
	
	@Override
	protected void prepare() {
		
		// Get any parameters and set the local variables

		if(mTessParams == null)
			mTessParams=new Vector<String>();
		mTessParams.clear();
		
		if(mTessResults == null)
			mTessResults=new Vector<Object[]>();		
		mTessResults.clear();

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
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("LoginFirst"))
				m_loginFirst = "Y".equals(para[i].getParameter());
			else if (name.equals("SysTime")){
				if (para[i].getParameter() == null)
					m_time = new Timestamp(System.currentTimeMillis());
				else
					m_time = (Timestamp) para[i].getParameter();
				m_sysDate.setTimeInMillis(m_time.getTime());
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

		m_clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	Get the date of the last import ----------------------------
		StringBuffer sql = new StringBuffer ("SELECT MAX(Created) ")
			.append(" FROM ofc_booking_audit_trail")
			.append(" WHERE (ofc_booking_audit_trail_id IS NOT NULL)")
			.append(m_clientCheck);
		//

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_startDate.setTimeInMillis(rs.getTimestamp(1).getTime());
			}
			rs.close();
			pstmt.close();	
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			return;
		}

		m_format = ImpFormat.load ("OFC FS Audit Trail Import");
		if (m_format == null)
		{
				// Do something about it.
		}

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
		int row = 0;
		int read = 0;
		int imported = 0;
		int no = 0;

		Vector<String> tessargs = new Vector<String>();		
		StringBuffer sql = null;

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_Ofc_Audit_Trail "
				+ "WHERE I_IsImported='Y'").append(m_clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.info("Deleted the old imported data = " + no);
			DB.commit(false, get_TrxName());
		}

		
		// Change the TeSS defaults
		tessargs.clear();
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
		tessargs.add(5,"");
		tessargs.add(6,"");
		tessargs.add(7,"");

		if(mh != null){
			if (m_loginFirst){
				// Login
				boolean encodeValues=true;
				boolean isPost=true;
				mh.processUrl(isPost, m_loginURL, null, encodeValues);
			}
		}
		// m_sysDate.add(Calendar.DAY_OF_MONTH, -1); // Yesterday
	    Calendar daycount = Calendar.getInstance();
	    daycount.setLenient(true);
	    int xx = 0;
		for(daycount=m_startDate; xx < 1 ; daycount.add(Calendar.DAY_OF_MONTH, 1)){
			xx += 1;
			if (!(daycount == null)) {
				tessargs.set(2,Integer.toString(daycount.get(Calendar.MONTH))); //tess uses month #s starting at 0
				tessargs.set(3,Integer.toString(daycount.get(Calendar.DAY_OF_MONTH)));
				tessargs.set(4,Integer.toString(daycount.get(Calendar.YEAR)));
				tessargs.set(5,Integer.toString(daycount.get(Calendar.MONTH)));
				tessargs.set(6,Integer.toString(daycount.get(Calendar.DAY_OF_MONTH)));
				tessargs.set(7,Integer.toString(daycount.get(Calendar.YEAR)));
				log.info("TeSS importing data for: " + m_dateFormatter.format(daycount.getTime()));
			}
		
			// Get the data.
			count = mh.runTess(null, mTessResults, mTessParams, tessargs);
			log.info("TeSS imported record count: " + Integer.toString(count));

			// Update the database

			//	For all rows - update/insert DB table
			for (row = 0; row < mTessResults.size(); row++)
			{
				read++;
				if (updateDB(Env.getCtx(), (String[])mTessResults.get(row), daycount.get(Calendar.YEAR), null))
					imported++;
			}

			mTessResults.clear();
		} // Loop for the next day

		// TODO Convert this to java
		log.info("Processing the Import");
		String sqlfunc = "SELECT ofc_import_booking_audit()";
		PreparedStatement pstmt = DB.prepareStatement(sqlfunc, get_TrxName());
		ResultSet rs = pstmt.executeQuery();
		log.info("Import processing completed.");

		return "Audit Import R/I " + read + " / " + imported + "#";
	}

	// Save the Tess Results to the database
	private boolean updateDB (Properties ctx, String[] line, int Yr, String trxName)
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
			.append(" WHERE (ofc_auditreference = ")
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
			ID = DB.getNextID(ctx, "I_OFC_Audit_Trail", null);		//	get ID
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
		sql.append("ofc_performedat_yr = ").append(Yr).append(",");
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

}
