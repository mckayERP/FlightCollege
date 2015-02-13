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

import org.adempiere.exceptions.DBException;
import org.compiere.impexp.ImpFormat;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MResourceAssignment;
import org.compiere.util.DB;
import org.compiere.util.Env;

import HtmlGet.HtmlGet;

public class OFCUpdateJourneyLogs extends SvrProcess {
	
	@Override
	protected void prepare() {
		// No prep required
	} // Prepare

	@Override
	protected String doIt() throws Exception {

		String sql = "SELECT ofc_update_journey_log()";
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			log.info("Updated the journey log.");
		}
		catch (Exception e) {
			log.info("Tried to update the journey log but had a problem.");	
			return "Tried to update the journey log but had a problem.";
		}
		return "Aircraft Journey Logs updated.";
	}
}