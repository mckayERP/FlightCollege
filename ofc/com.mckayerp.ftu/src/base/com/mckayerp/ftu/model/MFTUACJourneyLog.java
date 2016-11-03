package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MFTUACJourneyLog extends X_FTU_ACJourneyLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3533063629727808022L;

	private static long oneDayinMilliseconds = 24*60*60*1000;  // One day in timestamp long
	
	/**	Logger							*/
	protected transient CLogger			log = CLogger.getCLogger (getClass());

	public MFTUACJourneyLog(Properties ctx, int FTU_ACJourneyLog_ID,
			String trxName) {
		super(ctx, FTU_ACJourneyLog_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUACJourneyLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find the most recent summary journey log entry
	 * @param ctx
	 * @param ftu_Aircraft_ID
	 * @param flightDate
	 * @param trxName
	 * @return
	 */
	public static MFTUACJourneyLog getSummaryByAircraftID(Properties ctx,
			int ftu_Aircraft_ID, Timestamp flightDate, String trxName) {
		
		String where = MFTUACJourneyLog.COLUMNNAME_FTU_Aircraft_ID 
				+ "=" + ftu_Aircraft_ID
				+ " AND " + MFTUACJourneyLog.COLUMNNAME_FlightDate
				+ "=" + DB.TO_STRING(flightDate.toString());
		
		String orderBy = MFTUACJourneyLog.COLUMNNAME_EntryDate + " DESC"; // Same as wheels down

		MFTUACJourneyLog jlog = new Query(ctx, MFTUACJourneyLog.Table_Name, where, trxName)
									.setClient_ID()
									.setOrderBy(orderBy)
									.first();
		
		if (jlog != null && jlog.getNumberLegs() == 1 && jlog.getFTU_DefectLog_ID() == 0)  //  A summary has only one leg.
			return jlog;
		
		return null;
	}

	public static BigDecimal getTotalAirframeTime(Properties ctx,
			int ftu_aircraft_id, String trxName) {
		
		return getTotalAirframeTime(ctx, ftu_aircraft_id, null, trxName);
	}

	/**
	 * Update the journey log with this flight.  Assumes the flight is the most recent.
	 * @param ctx
	 * @param flight
	 * @param trxName
	 * @return The id of the updated log entry
	 */
	public static int updateLog(Properties ctx, MFTUFlightsheet flight, String trxName) {
		
		/**	Logger							*/
		final CLogger			log = CLogger.getCLogger (MFTUACJourneyLog.class);

		if (flight.getFTU_Aircraft_ID() == 0 || (flight.getAirTime().equals(Env.ZERO) && flight.getSimulator().equals(Env.ZERO)))
			return 0; // nothing to log
		
		if (flight.getFTU_ACJourneyLog_ID() > 0)
			return flight.getFTU_ACJourneyLog_ID(); // already logged
		
		log.info("Logging " + flight.toString());

		BigDecimal flightTime = flight.getFlightTime();
    	BigDecimal brief = flight.getBriefing();
    	BigDecimal sim = flight.getSimulator();

		Timestamp engStart = null;
		Timestamp engStop = null;
		Timestamp wheelsUp = null;
		Timestamp wheelsDown = null;
		long timeDiff;
		BigDecimal hours = Env.ZERO;
		BigDecimal millisecPerHour = new BigDecimal(60*60*1000);
			
    	 // Figure out the times of use
    	 // First check to see if the engine start time is valid
    	 if (flight.getEngineStart() != null)
    	 {
    		 engStart = flight.getEngineStart();
    		// if it is, check if the engine stop time is valid
    	 	if (flight.getEngineStop() != null)
    	 	{
    	 		// it is valid, now calculate the time difference and check 
    	 		// with the flightTime calculated above
    	 		engStop = flight.getEngineStop();
    	 	}
    	 	else
    	 	{
    	 		// The engine start is valid but the stop time isn't valid.
    	 		// Use the flight time.  Convert from hours to milliseconds
    	 		engStop = new Timestamp(engStart.getTime() + 
    	 				flightTime.multiply(millisecPerHour).intValue());
    	 	}
    	 }
    	 else 
    	 {
    		 // The engine start is invalid. Assume there is no
    		 // flight time.  Use the flight date & sim + brief time;
    		 engStart = flight.getFlightDate();
    		 engStop = new Timestamp(engStart.getTime() + sim.add(brief).multiply(millisecPerHour).intValue());
    		 log.fine("Engine start invalid. Flight Time = " + sim.add(brief).intValue());
    	 }

    	 // Check to see if the wheelsup/down times are valid
    	 if (flight.getWheelsUp() != null)
    	 {
    		 wheelsUp = flight.getWheelsUp();
    		// if it is, check if the engine stop time is valid
    	 	if (flight.getWheelsDown() != null)
    	 	{
    	 		// it is valid, now calculate the time difference and check 
    	 		// with the airTime
    	 		wheelsDown = flight.getWheelsDown();
    	 	}
    	 	else
    	 	{
    	 		// The wheels up is valid but the down time isn't valid.
    	 		// Use the air time.  Convert from hours to milliseconds
    	 		wheelsDown = new Timestamp(wheelsUp.getTime() + 
    	 				flight.getAirTime().multiply(millisecPerHour).intValue());
    	 	}
    	 }
    	 else 
    	 {
    		 // The wheelsUp time is invalid. Assume there is no
    		 // air time.  Use the Engine start & sim + brief time;
    		 wheelsUp = engStart;
    		 wheelsDown = new Timestamp(wheelsUp.getTime() + sim.add(brief).multiply(millisecPerHour).intValue());
    		 log.fine("wheels up invalid. air time = " + sim.add(brief).intValue());
    	 }

		// Find the current totalAirframeTime from the journey log.  This flight will be added to it, so 
    	// find the total at or before the wheels down time.
		BigDecimal totalAirframeTime;
		if (flight.getWheelsDown() != null)
			totalAirframeTime = getTotalAirframeTime(ctx,flight.getFTU_Aircraft_ID(), flight.getWheelsDown(), trxName);
		else
			totalAirframeTime = getTotalAirframeTime(ctx,flight.getFTU_Aircraft_ID(), new Timestamp(wheelsDown.getTime() + oneDayinMilliseconds ), trxName);

		// Add the current flight to it to get the new total.
		totalAirframeTime = totalAirframeTime.add(flight.getAirTime()).add(flight.getSimulator());
		
		MFTUACJourneyLog jlog = null;
				
		if (flight.getNumLegs() > 1) {
			jlog = new MFTUACJourneyLog(ctx, 0, trxName);
			jlog.setFTU_Aircraft_ID(flight.getFTU_Aircraft_ID());
			jlog.setFlightDate(flight.getFlightDate());
			jlog.setEntryDate(flight.getWheelsDown());
			jlog.setSeqNo(20);
			jlog.setNumOps(1); 
			jlog.setIntendedFlight(flight.getIntendedFlight());
			jlog.setNumberLegs(flight.getNumLegs());
			jlog.setWheelsUp(wheelsUp);
			jlog.setWheelsDown(wheelsDown);
			jlog.setAirTime(flight.getAirTime().add(flight.getSimulator()));
			jlog.setFlightTime(flight.getFlightTime().add(flight.getSimulator()));
			jlog.setTotalAirframeTime(totalAirframeTime);					
			jlog.saveEx();
			log.info("  Added new log entry for multiple legs. TotalAirframeTime=" + totalAirframeTime);
		}
		else {
			jlog = MFTUACJourneyLog.getSummaryByAircraftID(ctx,flight.getFTU_Aircraft_ID(), flight.getFlightDate(), trxName);
			
			if (jlog == null) {
				jlog = new MFTUACJourneyLog(ctx, 0, trxName);
				jlog.setFTU_Aircraft_ID(flight.getFTU_Aircraft_ID());
				jlog.setFlightDate(flight.getFlightDate());
				jlog.setEntryDate(wheelsDown);
				jlog.setSeqNo(20);
				jlog.setNumOps(1);
				jlog.setIntendedFlight("CYOW Local");  // TODO get this from database based on organization
				jlog.setNumberLegs(1);
				jlog.setWheelsUp(wheelsUp);
				jlog.setWheelsDown(wheelsDown);
				jlog.setAirTime(flight.getAirTime().add(flight.getSimulator()));
				jlog.setFlightTime(flight.getFlightTime().add(flight.getSimulator()));
				jlog.setTotalAirframeTime(totalAirframeTime);					
				jlog.saveEx();					
				log.info("  Added new summary log entry. TotalAirframeTime=" + totalAirframeTime);
			}
			else {
				jlog.setNumOps(jlog.getNumOps() + 1);
				//jlog.setIntendedFlight("CYOW Local");  // TODO get this from database
				//jlog.setNumberLegs(1);
				if (jlog.getWheelsUp() == null || wheelsUp.before(jlog.getWheelsUp()))
					jlog.setWheelsUp(wheelsUp);
				if (jlog.getWheelsDown() == null || wheelsDown.after(jlog.getWheelsDown()))
					jlog.setWheelsDown(wheelsDown);
				if (jlog.getEntryDate() == null || wheelsDown.after(jlog.getEntryDate()))
					jlog.setEntryDate(wheelsDown);
				jlog.setAirTime(jlog.getAirTime().add(flight.getAirTime()).add(flight.getSimulator()));
				jlog.setFlightTime(jlog.getFlightTime().add(flight.getFlightTime()).add(flight.getSimulator()));
				jlog.setTotalAirframeTime(totalAirframeTime);					
				jlog.saveEx();
				log.info("  Added flight to summary log entry. TotalAirframeTime=" + totalAirframeTime);
			}
		}

		return jlog.getFTU_ACJourneyLog_ID();
	}
	
	/**
	 * Recalculates the total Airframe time based on the current log entries
	 * @param ctx
	 * @param trxName
	 */
	public static void recalculateLog(Properties ctx, int ftu_Aircraft_ID, String trxName) {
		
		String orderBy = MFTUACJourneyLog.COLUMNNAME_FTU_Aircraft_ID + ", "
				+ MFTUACJourneyLog.COLUMNNAME_WheelsUp;
		
		String where = "";
		if (ftu_Aircraft_ID > 0)
			where = MFTUACJourneyLog.COLUMNNAME_FTU_Aircraft_ID + "=" + ftu_Aircraft_ID;
		
		List<MFTUACJourneyLog> jlogs = new Query(ctx, MFTUACJourneyLog.Table_Name, where, trxName)
									.setClient_ID()
									.setOrderBy(orderBy)
									.list();
		BigDecimal totalAirframeTime = Env.ZERO;
		for (MFTUACJourneyLog jlog : jlogs) {
			BigDecimal logAirTime = Env.ZERO;
			for (MFTUFlightsheet flight : jlog.getFlights(ctx, trxName)) {
				logAirTime = logAirTime.add(flight.getAirTime());
				totalAirframeTime = totalAirframeTime.add(flight.getAirTime());
			}
			jlog.setIsDirectLoad(true);
			jlog.setAirTime(logAirTime);
			jlog.setTotalAirframeTime(totalAirframeTime);
			jlog.saveEx();
		}
		MFTUAircraft ac = MFTUAircraft.get(ctx, ftu_Aircraft_ID, trxName);
		ac.setAirframeTime(totalAirframeTime);
		ac.saveEx();
	}

	
	public List<MFTUFlightsheet> getFlights(Properties ctx, String trxName) {
		
		String where = MFTUFlightsheet.COLUMNNAME_FTU_ACJourneyLog_ID + "=" + this.getFTU_ACJourneyLog_ID();
		return new Query(ctx, MFTUFlightsheet.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setOrderBy(MFTUFlightsheet.COLUMNNAME_WheelsUp)
					.list();
	}

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success) {

		// Assumes that only new log entries are added or updated. Old 
		// entries are never changed.  In this way, the totalAirframeTime
		// of the log entry is always the max.
		if (newRecord || this.is_ValueChanged(COLUMNNAME_AirTime)) {
			MFTUAircraft ac = (MFTUAircraft) this.getFTU_Aircraft();
			ac.setAirframeTime(this.getTotalAirframeTime());
			ac.saveEx();
		}
		
		return success;
	}

	/**
	 * Get the total airframe time on a specific date.  If the asAtDate is null
	 * the most maximum time will be returned.
	 * @param ctx
	 * @param ftu_Aircraft_ID
	 * @param asAtDate
	 * @param trxName
	 * @return A BigDecimal representing the total airframe time in hours.
	 */
	public static BigDecimal getTotalAirframeTime(Properties ctx,
			int ftu_Aircraft_ID, Timestamp asAtDate, String trxName) {
		
		/**	Logger							*/
		final CLogger			log = CLogger.getCLogger (MFTUACJourneyLog.class);
		
		BigDecimal totalAirframeTime = Env.ZERO;
		
		String sql = "SELECT MAX(" + MFTUACJourneyLog.COLUMNNAME_TotalAirframeTime + ")"
				+ " FROM " + MFTUACJourneyLog.Table_Name 
				+ " WHERE " + MFTUACJourneyLog.COLUMNNAME_FTU_Aircraft_ID 
				+ "=" + ftu_Aircraft_ID;
		
		if (asAtDate != null) {
			sql = sql  + " AND " + MFTUACJourneyLog.COLUMNNAME_EntryDate + "<=" + DB.TO_STRING(asAtDate.toString()); 
		}
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalAirframeTime = rs.getBigDecimal(1);
		}
		catch (SQLException e) {
			log.severe("SQL Error finding Total Airframe Time for AC: " + ftu_Aircraft_ID);			
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt= null;
		}
		
		if (totalAirframeTime == null)
			totalAirframeTime = Env.ZERO;
		
		return totalAirframeTime;
	}

	public static List<MFTUACJourneyLog> getByDefectLogID(Properties ctx,
			int ftu_DefectLog_ID, String trxName) {
		
		String where = MFTUACJourneyLog.COLUMNNAME_FTU_DefectLog_ID + "=" + ftu_DefectLog_ID;
		return new Query(ctx, MFTUACJourneyLog.Table_Name, where, trxName)
						.setClient_ID()
						.list();
	}

}
