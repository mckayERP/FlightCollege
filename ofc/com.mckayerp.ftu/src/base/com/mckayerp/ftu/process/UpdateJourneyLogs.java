package com.mckayerp.ftu.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import com.mckayerp.ftu.model.MFTUACJourneyLog;
import com.mckayerp.ftu.model.MFTUAircraft;
import com.mckayerp.ftu.model.MFTUFlightsheet;

public class UpdateJourneyLogs extends SvrProcess {
	
	private int ftu_aircraft_id = 0;
	
	@Override
	protected void prepare() {
		ftu_aircraft_id = getParameterAsInt("FTU_Aircraft_ID");
	} // Prepare

	@Override
	protected String doIt() throws Exception {
		
		String where = "";
		if (ftu_aircraft_id > 0)
		{
			where = MFTUFlightsheet.COLUMNNAME_FTU_Aircraft_ID + "=" + ftu_aircraft_id;
		}

//		String sql = "UPDATE " + MFTUFlightsheet.Table_Name 
//			+ " SET " + MFTUFlightsheet.COLUMNNAME_FTU_ACJourneyLog_ID + " = NULL";
		
//		if (ftu_aircraft_id > 0)
//			sql += " WHERE " + where;
		
//		int no = DB.executeUpdate(sql, get_TrxName());
//		log.fine("Reset journey log link on flightsheet lines: " + no);
		
//		sql = "DELETE FROM " + MFTUACJourneyLog.Table_Name + " WHERE COALESCE(" + MFTUACJourneyLog.COLUMNNAME_FTU_DefectLog_ID + ",0) = 0";
//		if (ftu_aircraft_id > 0)
//			sql += " AND " + where;
		
//		no = DB.executeUpdate(sql, get_TrxName());
//		log.fine("Deleted journey logs: " + no);
		
		List<MFTUAircraft> fleet = new Query(getCtx(), MFTUAircraft.Table_Name, where, get_TrxName())
									.setClient_ID()
									.setOnlyActiveRecords(true)
									.list();
		
		for (MFTUAircraft ac : fleet) {
			
			if (ac.isGeneric())
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
						
			where = MFTUACJourneyLog.COLUMNNAME_FTU_Aircraft_ID + "=" + ac.getFTU_Aircraft_ID()
					+ " AND " + MFTUACJourneyLog.COLUMNNAME_EntryDate + "=" + openDate
					+ " AND " + MFTUACJourneyLog.COLUMNNAME_IntendedFlight + "=" + "Opening Balance";

			MFTUACJourneyLog jlog = new Query(getCtx(), MFTUACJourneyLog.Table_Name, where, get_TrxName())
										.setClient_ID()
										.setOrderBy(MFTUACJourneyLog.COLUMNNAME_FlightDate + ", " 
												+ MFTUACJourneyLog.COLUMNNAME_WheelsUp)
											.firstOnly();
			
			// Set opening balance
			if (jlog != null) {
				log.fine("Journey log opening balance entry exists.");
			}
			else {
				log.fine("Creating new opening balance entry");
				jlog = new MFTUACJourneyLog(getCtx(), 0, get_TrxName());
			}
			// Update the entry
			jlog.setFTU_Aircraft_ID(ac.getFTU_Aircraft_ID());
			jlog.setFlightDate(openDate);
			jlog.setEntryDate(openDate);
			jlog.setTotalAirframeTime(openTime);
			jlog.setIntendedFlight("Opening Balance");
			jlog.setAirTime(openTime);
			jlog.setSeqNo(10);
			jlog.saveEx();
			
			where = MFTUFlightsheet.COLUMNNAME_FTU_Aircraft_ID 
					+ "=" + ac.getFTU_Aircraft_ID() + " AND "
					+ MFTUFlightsheet.COLUMNNAME_FlightDate 
					+ ">=" + DB.TO_DATE(openDate)
					+ " AND (" + MFTUFlightsheet.COLUMNNAME_AirTime + " > 0"
					+ "      OR " + MFTUFlightsheet.COLUMNNAME_Simulator + " > 0)"
					+ " AND " + MFTUFlightsheet.COLUMNNAME_CourseType + "!=" + DB.TO_STRING("Cancelled");

			String orderBy = MFTUFlightsheet.COLUMNNAME_FlightDate + ", "
								+ MFTUFlightsheet.COLUMNNAME_WheelsUp;
			
			List<MFTUFlightsheet> flights = new Query(getCtx(),MFTUFlightsheet.Table_Name,where,get_TrxName())
										.setOrderBy(orderBy)
										.setOnlyActiveRecords(true)
										.setClient_ID()
										.list();
			
			for (MFTUFlightsheet flight : flights) {
				
//				if (flight.getFTU_ACJourneyLog_ID() > 0 )
//					continue;

				flight.setIsDirectLoad(true); // Prevent afterSave() actions.
				flight.setFTU_ACJourneyLog_ID(MFTUACJourneyLog.updateLog(getCtx(), flight, get_TrxName()));
				flight.saveEx();
				
			}
		}
		
		return "Aircraft Journey Logs updated.";
	}
}