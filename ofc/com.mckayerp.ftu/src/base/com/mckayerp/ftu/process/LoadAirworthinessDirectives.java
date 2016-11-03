package com.mckayerp.ftu.process;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.compiere.Adempiere;
import org.compiere.impexp.ImpFormat;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MResourceAssignment;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.service.dsl.ProcessBuilder;

import HtmlGet.HtmlGet;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.mckayerp.ftu.model.MFTUAirworthinessDirective;
import com.mckayerp.ftu.model.X_FTU_AirworthinessDirective;

public class LoadAirworthinessDirectives extends SvrProcess {
	
	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (LoadAirworthinessDirectives.class);

	private static long mLock = 123567; // a number to use for an advisory lock.  Postgresql specific.
	
	/** The Berkley telegraph HtmlGet class */ 
	private HtmlGet mh;

	/** The primary TeSS Parameter Vector */
	private Vector<String> mTessParams = new Vector<String>();
	
	/** The secondary login TeSS Parameter Vector */
	private Vector<String> mSessionParams = new Vector<String>();
	
	/** The result vector from the screen scraper */
	private Vector<Object[]> mSessionResults = new Vector<Object[]>();
	private Vector<Object[]> results = new Vector<Object[]>();
	private Object[] rowResults;

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 1000000;
	
	/** Org to be imported to	  		*/
	private int				m_AD_Org_ID = 1000000;

	/** User doing the importing		*/
	private int				m_AD_User_ID = 0;

//	/** Warehouse to use				*/
//	private int				m_Warehouse_ID = 1000000;
	
//	/**	Delete old Imported				*/
//	private boolean			m_deleteOldImported = false;
	
//	/** Login first */
//	private boolean			m_loginFirst = false;
//
//	/** Login URL - the URL of the initial login */
//	private String			m_loginURL;
//
//	/** Session URL - the URL of where the session ID is identified. */
//	private String			m_sessionURL;
//	
//	private ImpFormat 		m_format;
//	
//	private String			m_clientCheck;
//
//	private String			m_tableName = "I_OFC_Flightsheet";
//	private String			m_tablePK = "i_ofc_flightsheet_id";
//	
//	private String 			m_jsessionid;
	
	private Properties		m_ctx;

	private String linkURL = null;
	private Page linkPage = null;

	protected void prepare() {
				
		// Get any parameters and set the local variables

//		if(mTessParams == null)
//			mTessParams=new Vector<String>();
//		mTessParams.clear();
		
		if(results == null)
			results=new Vector<Object[]>();		
		results.clear();

//		ProcessInfoParameter[] para = getParameter();
//		for (int i = 0; i < para.length; i++)
//		{
//			String name = para[i].getParameterName();
//			if (name.equals("AD_Client_ID"))
//				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
//			else if (name.equals("AD_Org_ID"))
//				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
//			else if (name.equals("AD_User_ID"))
//				m_AD_User_ID = ((BigDecimal)para[i].getParameter()).intValue();
//			else if (name.equals("DeleteOldImported"))
//				m_deleteOldImported = "Y".equals(para[i].getParameter());
//			else if (name.equals("LoginFirst"))
//				m_loginFirst = "Y".equals(para[i].getParameter());
//			else if (name.equals("LoginURL"))
//				m_loginURL = para[i].getParameter().toString();
//			else if (name.startsWith("Tess"))
//				if (para[i].getParameter().toString().startsWith("@S:="))  // The scheduler will not strip this.
//					mTessParams.add(para[i].getParameter().toString().substring(4));
//				else
//					mTessParams.add(para[i].getParameter().toString());
//			else if (name.startsWith("Session"))
//				if (para[i].getParameter().toString().startsWith("@S:="))  // The scheduler will not strip this.
//					mSessionParams.add(para[i].getParameter().toString().substring(4));
//				else
//					mSessionParams.add(para[i].getParameter().toString());
//			else
//				log.log(Level.SEVERE, "Unknown Parameter: " + name);
//		}

		// Override the context - specifically if we are running on the server.
		// Otherwise, the server process won't be able to save the order to the
		// correct client.  Use the passed parameters.
		m_ctx = Env.getCtx();  // Make a copy of the context
		Env.setContext(m_ctx, "#AD_Client_ID", m_AD_Client_ID);  // Replace the client
		Env.setContext(m_ctx, "#AD_Org_ID", m_AD_Org_ID);		// Replace the org
		Env.setContext(m_ctx, "#AD_User_ID", m_AD_User_ID);		// Replace the user
//		
//		m_clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

//		m_format = ImpFormat.load ("OFC Import Open Flightsheets");
//		if (m_format == null)
//		{
//				// Do something about it.
//		}
//
//		mTessParams.add("host=wwwapps3.tc.gc.ca/Saf-Sec-Sur/2/cawis-swimn/");
//		mTessParams.add("url=awd-lv-cs1401.asp?rand=");
//		mTessParams.add("method=get");
//		mTessParams.add("arguments=VIEW_MODE,TRANSACTION_TYPE,SEARCH_BY_MARK");
//		mTessParams.add("default1=");
//		mTessParams.add("default2=U");
//		mTessParams.add("default3=GFTM");
//		mTessParams.add("columns=new,prodType,country,adNumber,link,pdf,adSubject,reference,repeat");
//		mTessParams.add("prefix=Directives Pertaining to Aircraft Model:");
//		mTessParams.add("rowprefix=<tr class[^>]*?Inside[^>]*?>");
//		mTessParams.add("rowterm=</tr>");
//		mTessParams.add("new_prefix=<td[^>]*>");
//		mTessParams.add("new_term=</td>");
//		mTessParams.add("prodType_prefix=<td[^>]*>");
//		mTessParams.add("prodType_term=</td>");
//		mTessParams.add("country_prefix=<td[^>]*>");
//		mTessParams.add("country_term=</td>");
//		mTessParams.add("adNumber_prefix=<td[^>]*>");
//		mTessParams.add("adNumber_term=</td>");
//		mTessParams.add("link_prefix=<a href=.javascript:");
//		mTessParams.add("link_term=..style=[^>]*?>");
//		mTessParams.add("pdf_prefix=<a href=.javascript:");
//		mTessParams.add("pdf_term=..style=[^>]*?>");
//		mTessParams.add("adSubject_prefix=<td[^>]*>");
//		mTessParams.add("adSubject_term=</td>");
//		mTessParams.add("reference_prefix=<td[^>]*>");
//		mTessParams.add("reference_term=</td>");
//		mTessParams.add("repeat_prefix=<td[^>]*>");
//		mTessParams.add("repeat_term=</td>");
//		mTessParams.add("new_nullifmissing");
//		mTessParams.add("link_nullifmissing");
//		mTessParams.add("pdf_nullifmissing");
//		mTessParams.add("reference_nullifmissing");
//		mTessParams.add("termination=<tr class[^>]*?Outside.>");
////		mTessParams.add("dump");
//		mTessParams.add("end");

	} // Prepare

	@Override
	protected String doIt() throws Exception {

		int count=0;
		int read = 0;
		int imported = 0;
		
		final WebClient webClient = new WebClient();
        final HtmlPage page1 = webClient.getPage("http://wwwapps3.tc.gc.ca/Saf-Sec-Sur/2/cawis-swimn/awd-lv-cs1401.asp?rand=");
        
        webClient.addWebWindowListener(new WebWindowListener() {

			@Override
			public void webWindowClosed(WebWindowEvent wwe) {
				// TODO Auto-generated method stub
				log.config(wwe.toString());				
			}

			@Override
			public void webWindowContentChanged(WebWindowEvent wwe) {
				linkPage = wwe.getNewPage();
				linkURL = linkPage.getUrl().toString();
			}

			@Override
			public void webWindowOpened(WebWindowEvent wwe) {
				log.config(wwe.toString());
			}
        });	  

        // Get the form that we are dealing with and within that form, 
        // find the submit button and the field that we want to change.
        final HtmlForm form = page1.getFormByName("F_SEARCH_BY_MARK");

        final HtmlSubmitInput button = form.getInputByName("cmdSearchByMark");
        final HtmlTextInput markText = form.getInputByName("SEARCH_BY_MARK");

        // Change the value of the text field
        markText.setValueAttribute("GFTM");

        // Now submit the form by clicking the button and get back the second page.
        final HtmlPage page2 = button.click();
        
		DomNodeList<DomElement> tables = page2.getElementsByTagName("table");
		
		for (Object table : tables)
		{
			if (table instanceof HtmlTable)
			{
				int rowCount = 0;
				for (final HtmlTableRow row : ((HtmlTable) table).getRows()) {
					if (row.getAttribute("class").equals("Outside"))
						continue;
					
				    rowCount ++;
					int cellCount = 0;
					rowResults = new Object[9];
				    for (final HtmlTableCell cell : row.getCells()) {
				    	
				    	switch (cellCount) 
				    	{
				    		
				    		case 0: case 1: case 2: case 3: case 6: case 7: case 8:
				    			rowResults[cellCount] = cell.asText();
				    			break;
				    			
				    		case 4: case 5:
				    			linkURL = "";
				        		DomNode link = cell.getFirstChild().getNextSibling();
				        		if (link instanceof HtmlAnchor) 
				        		{
				        			try {
				        				((HtmlAnchor) link).click();  // Triggers a new page and causes and event.
				        			}
				        			catch (RuntimeException e) {
				        				log.severe("Couldn't follow link!");
				        				linkURL = "";
				        			}
				        			
				        		}
				        		rowResults[cellCount] = linkURL;
				        		
				        	default:
				        		break;
				    	
				    	}
				    	
				    	cellCount++;
				    }
				    
				    results.add(rowResults);

				}
			}
		}

		//	For all rows - update/insert DB table
		log.config("Found " + results.size() + " rows.");
		for (int row = 0; row < results.size(); row++)
		{
			updateDB(m_ctx, results.get(row), get_TrxName());
		}
		results.clear();
//	}		
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		// Go through the imported records and convert any fields
//		// that require it.
//		try {
//			// Convert i_ofc_client_id to c_bpartner_id
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//				+ "SET C_BPartner_ID = bp.C_BPartner_ID "
//				+ "FROM C_BPartner bp "
//				+ "WHERE ifs.I_OFC_Client_ID = bp.\"value\"")
//				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
//				.append(" AND ifs.Processed<>'Y'")
//				.append(" AND ifs.I_IsImported<>'Y'");
//			
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Client ID updated=" + no);
//			
//			// Convert the plane to the Resource ID
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//					+ "SET OFC_AC_Resource_ID = sr.S_Resource_ID "
//					+ "FROM S_Resource sr "
//					+ "WHERE (regexp_replace(ifs.i_ofc_call_marks, '-', '') = sr.\"name\" "
//					+ "OR substring(ifs.i_ofc_call_marks from '\\((.+)\\)') = sr.\"name\")")
//					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
//					.append(" AND sr.AD_Client_ID=" + m_AD_Client_ID)
//					.append(" AND ifs.Processed<>'Y'")
//					.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Aircraft Resource IDs updated=" + no);
//			
//			// Find the instructor Resource ID
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//					+ "SET OFC_Inst_Resource_ID = sr.S_Resource_ID "
//					+ "FROM S_Resource sr "
//					+ "WHERE (  i_ofc_student_pax <> '' AND "
//					+ "lower(regexp_replace(substring(\"name\" from ', (.+)') || substring(\"name\" from '(.+), '), ' ','')) = " 
//					+ "lower(i_ofc_flightsheet.i_ofc_captain_pic))")
//					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
//					.append(" AND sr.AD_Client_ID=" + m_AD_Client_ID)
//					.append(" AND ifs.Processed<>'Y'")
//					.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Instructor Resource ID updated=" + no);
//			
//			// Remove the Flight number from the air time and fuel time for flights that 
//			// have not yet been authorized.  This is an artifact of the screen scraper and
//			// the impacts of the buttons on the open flight sheets.
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//					+ "SET i_ofc_invoice_number = ifs.i_ofc_air_time, "
//					+ "i_ofc_air_time = '' "
//					+ "where ifs.i_ofc_air_time like '(Flt%'")
//					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
//					.append(" AND ifs.Processed<>'Y'")
//					.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("I_OFC_Airtime updated=" + no);
//	
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//					+ "SET i_ofc_invoice_number = ifs.i_ofc_fuel, "
//					+ "i_ofc_fuel = '' "
//					+ "where ifs.i_ofc_fuel like '(Flt%'")
//					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
//					.append(" AND ifs.Processed<>'Y'")
//					.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("I_OFC_Airtime updated=" + no);
//	
//			// Find the Invoice ID
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//					+ "SET OFC_Flightsheet_Line_Status = 'Closed', OFC_Invoice_ID = substring(i_ofc_flightsheet.i_ofc_invoice_number from '([0-9]+).Flt #')::Numeric "
//					+ "WHERE (substring(ifs.i_ofc_invoice_number from '([0-9]+).Flt #') IS NOT NULL "
//					+ "AND substring(i_ofc_flightsheet.i_ofc_invoice_number from '([0-9]+).Flt #') <> 'DNCO')")
//					.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
//					.append(" AND ifs.Processed<>'Y'")
//					.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Invoice ID updated=" + no);
//	
//			// Clear the error text
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//					+ "SET I_ErrorMsg = NULL ")
//					.append("WHERE ifs.AD_Client_ID=" + m_AD_Client_ID)
//					.append(" AND ifs.Processed<>'Y'")
//					.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Error messages cleared =" + no);
//			
//			// Check for blank flight id
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//				+ "SET I_ErrorMsg = \'I_OFC_Flight_ID is blank.  \' "
//				+ "WHERE (ifs.I_OFC_Flight_ID IS NULL)")
//				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
//				.append(" AND ifs.Processed<>'Y'")
//				.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Blank flight ID marked as errors =" + no);
//	
//			// Check for blank c_bpartner_id
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs "
//				+ "SET I_ErrorMsg = \'C_BPartner_ID is blank.  \' "
//				+ "WHERE (ifs.C_BPartner_ID IS NULL)")
//				.append(" AND ifs.AD_Client_ID=" + m_AD_Client_ID)
//				.append(" AND ifs.Processed<>'Y'")
//				.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Unknown Clients marked as errors =" + no);
//	
//			// Mark all as processed
//			sql = new StringBuffer ("UPDATE I_OFC_Flightsheet ifs ")
//				.append("SET Processed = 'Y'")
//				.append(" WHERE ifs.AD_Client_ID=" + m_AD_Client_ID)
//				.append(" AND ifs.I_ErrorMsg is null")
//				.append(" AND ifs.I_IsImported<>'Y'");
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Records marked as processed =" + no);
//	
//			commitEx();
//
//			// Now copy all the records to the main table.
//			// TODO Convert this to java using models
//			log.info("Processing the Import");
//			sql = new StringBuffer ("SELECT ofc_import_flightsheet()");
//
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			rs = pstmt.executeQuery();
//			log.info("Import processing completed.");
//	
//			commitEx();
//			
//			DB.close(rs, pstmt);
//		}
//		catch(SQLException e)
//		{
//			releaseLock(get_TrxName());
//			DB.close(rs, pstmt);
//			log.log(Level.SEVERE, "Error importing data: " + sql.toString(), e);
//			return "SQL Execption: Please try again later";
//		}
//
//		// Create Orders.
//		try
//		{
//			createOrders(m_ctx, null);
//		}
//		catch (Exception e)
//		{
//			releaseLock(get_TrxName());
//			log.log(Level.SEVERE, "Error creating orders.", e);
//			return "Error creating orders. Please try again later.";
//		}
//		
//		releaseLock(get_TrxName());
//		return "Flight Sheet Import R/I " + read + " / " + imported + "#";

		webClient.close();

		return "done";
		
	}

	private boolean updateDB (Properties ctx, Object[] line, String trxName)
	{
		
		if (line == null || line.length == 0)
		{
			log.finest("No Line");
			return false;
		}
		
		String newEntry = (String) line[0];  // Not used
		String prodType = (String) line[1];
		String country  = (String) line[2];
		String adNumber = (String) line[3];
		String link		= (String) line[4];
		String pdf		= (String) line[5];
		String adSubject= (String) line[6];
		String reference= (String) line[7];
		String repeat	= (String) line[8];

		//	Check if the record is already there ----------------------------
		MFTUAirworthinessDirective ad = MFTUAirworthinessDirective.get(ctx, adNumber, get_TrxName());
		
		if (ad.getFTU_AirworthinessDirective_ID() == 0)
		{
			ad.setFTU_ADCountryCode(country);
			ad.setFTU_ADDocumentLink(link);
			ad.setFTU_ADDocumentLinkPDF(pdf);
			ad.setFTU_ADSubject(adSubject);
			ad.setFTU_ADReference(reference);
			ad.setFTU_ADRepeatText(repeat);
			ad.setFTU_ADType(prodType);
			ad.saveEx();
			log.config("AD added: " + adNumber);
		}
		else
		{
			log.config("AD already exists: " + adNumber);
		}
				
		return true;
	}	//	updateDB


	public static void main(String[] args) {
				
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.CONFIG);
		
		if (! DB.isConnected()) 
		{
			
			log.info("No DB Connection");
			System.exit(1);
			
		}
		
		Properties context = Env.getCtx();

		try 
		{
			
			//Import Airworthiness Directives
			ProcessInfo processInfo = ProcessBuilder.create(context)
			.process(com.mckayerp.ftu.process.LoadAirworthinessDirectives.class)
			.withTitle("Import Airworthiness Directives")
			.execute();

			log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());

		} 
		catch (AdempiereException e) 
		{
			e.printStackTrace();
		}
	}

}
