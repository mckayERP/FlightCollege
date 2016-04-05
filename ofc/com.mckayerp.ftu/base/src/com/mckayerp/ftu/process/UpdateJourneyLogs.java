package com.mckayerp.ftu.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import oracle.sql.DATE;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.impexp.ImpFormat;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MResourceAssignment;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.mckayerp.ftu.model.MFTUACJourneyLog;
import com.mckayerp.ftu.model.MFTUAircraft;
import com.mckayerp.ftu.model.MFTUFlightsheet;

import HtmlGet.HtmlGet;

public class UpdateJourneyLogs extends SvrProcess {
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	} // Prepare

	@Override
	protected String doIt() throws Exception {
		
		String sql = "UPDATE " + MFTUFlightsheet.Table_Name 
			+ " SET " + MFTUFlightsheet.COLUMNNAME_FTU_ACJourneyLog_ID + " = NULL";
		
		int no = DB.executeUpdate(sql, get_TrxName());
		log.fine("Reset journey log link on flightsheet lines: " + no);
		
		sql = "DELETE FROM " + MFTUACJourneyLog.Table_Name;
		no = DB.executeUpdate(sql, get_TrxName());
		log.fine("Deleted journey logs: " + no);
		
		List<MFTUAircraft> fleet = new Query(getCtx(), MFTUAircraft.Table_Name, "", get_TrxName())
									.setClient_ID()
									.list();
		
		for (MFTUAircraft ac : fleet) {
			
			if (ac.isSim() || ac.isGeneric())
				// TODO log the sim activity as well.
				continue;
			
			if (ac.getFltLogOpenDate() == null) {
				log.config("No FLightlog Open Date for :" + ac.getACRegistration());
				continue;
			}
			
			Timestamp openDate = ac.getFltLogOpenDate();
			BigDecimal openTime = ac.getFltLogOpenTime();
			
			log.info("Generating flight logs for " + ac.getACRegistration() 
					+ " from " + openDate.toString() + " starting airframe time: " + openTime.toString());
						
			String where = MFTUACJourneyLog.COLUMNNAME_FTU_Aircraft_ID 
					+ "=" + ac.getFTU_Aircraft_ID();

			MFTUACJourneyLog jlog = new Query(getCtx(), MFTUACJourneyLog.Table_Name, where, get_TrxName())
										.setClient_ID()
										.setOrderBy(MFTUACJourneyLog.COLUMNNAME_FlightDate + ", " 
												+ MFTUACJourneyLog.COLUMNNAME_WheelsUp)
											.first();
			
			// Set opening balance
			if (jlog != null) {
				log.severe("Delete failed.  Journey log entries still exist!");
				throw new AdempiereException("Delete failed. Journey log entries still exist!");
			}
			else {
				jlog = new MFTUACJourneyLog(getCtx(), 0, get_TrxName());
				jlog.setFTU_Aircraft_ID(ac.getFTU_Aircraft_ID());
				jlog.setFlightDate(openDate);
				jlog.setTotalAirframeTime(openTime);
				jlog.setIntendedFlight("Opening Balance");
				jlog.setAirTime(openTime);
				jlog.setSeqNo(10);
				jlog.saveEx();
			}
			
			where = MFTUFlightsheet.COLUMNNAME_FTU_Aircraft_ID 
					+ "=" + ac.getFTU_Aircraft_ID() + " AND "
					+ MFTUFlightsheet.COLUMNNAME_FlightDate 
					+ ">=" + DB.TO_STRING(openDate.toString())
					+ " AND " + MFTUFlightsheet.COLUMNNAME_AirTime + " > 0"
					+ " AND " + MFTUFlightsheet.COLUMNNAME_CourseType + "!=" + DB.TO_STRING("Cancelled");

			String orderBy = MFTUFlightsheet.COLUMNNAME_FlightDate + ", "
								+ MFTUFlightsheet.COLUMNNAME_WheelsUp;
			
			List<MFTUFlightsheet> flights = new Query(getCtx(),MFTUFlightsheet.Table_Name,where,get_TrxName())
										.setOrderBy(orderBy)
										.setOnlyActiveRecords(true)
										.setClient_ID()
										.list();
			
			for (MFTUFlightsheet flight : flights) {
				
				if (flight.getFTU_ACJourneyLog_ID() > 0 )
					continue;

				flight.setIsDirectLoad(true); // Prevent afterSave() actions.
				flight.setFTU_ACJourneyLog_ID(MFTUACJourneyLog.updateLog(getCtx(), flight, get_TrxName()));
				flight.saveEx();
				
			}
		}
		
		return "Aircraft Journey Logs updated.";
	}
}