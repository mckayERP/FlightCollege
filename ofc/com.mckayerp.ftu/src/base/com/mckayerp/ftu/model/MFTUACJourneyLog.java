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
		
		String orderBy = MFTUACJourneyLog.COLUMNNAME_WheelsUp + " DESC";

		MFTUACJourneyLog jlog = new Query(ctx, MFTUACJourneyLog.Table_Name, where, trxName)
									.setClient_ID()
									.setOrderBy(orderBy)
									.first();
		
		if (jlog != null && jlog.getNumberLegs() == 1)  //  A summary has only one leg.
			return jlog;
		
		return null;
	}

	public static BigDecimal getTotalAirframeTime(Properties ctx,
			int ftu_Aircraft_ID, String trxName) {
		
		/**	Logger							*/
		final CLogger			log = CLogger.getCLogger (MFTUACJourneyLog.class);
		
		BigDecimal totalAirframeTime = Env.ZERO;
		
		String sql = "SELECT MAX(" + MFTUACJourneyLog.COLUMNNAME_TotalAirframeTime + ")"
				+ " FROM " + MFTUACJourneyLog.Table_Name 
				+ " WHERE " + MFTUACJourneyLog.COLUMNNAME_FTU_Aircraft_ID 
				+ "=" + ftu_Aircraft_ID;
		
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

		if (flight.getFTU_Aircraft_ID() == 0 || flight.getAirTime().equals(Env.ZERO) )
			return 0; // nothing to log
		
		if (flight.getFTU_ACJourneyLog_ID() > 0)
			return flight.getFTU_ACJourneyLog_ID(); // already logged
		
		log.info("Logging " + flight.toString());
		
		// Find the current totalAirframeTime from the journey log
		BigDecimal totalAirframeTime = getTotalAirframeTime(ctx,flight.getFTU_Aircraft_ID(), trxName);
		// Add the current flight to it to get the new total.
		totalAirframeTime = totalAirframeTime.add(flight.getAirTime());
		
		MFTUACJourneyLog jlog = null;
				
		if (flight.getNumLegs() > 1) {
			jlog = new MFTUACJourneyLog(ctx, 0, trxName);
			jlog.setFTU_Aircraft_ID(flight.getFTU_Aircraft_ID());
			jlog.setFlightDate(flight.getFlightDate());
			jlog.setSeqNo(20);
			jlog.setNumOps(1); 
			jlog.setIntendedFlight(flight.getIntendedFlight());
			jlog.setNumberLegs(flight.getNumLegs());
			jlog.setWheelsUp(flight.getWheelsUp());
			jlog.setWheelsDown(flight.getWheelsDown());
			jlog.setAirTime(flight.getAirTime());
			jlog.setFlightTime(flight.getFlightTime());
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
				jlog.setSeqNo(20);
				jlog.setNumOps(1);
				jlog.setIntendedFlight("CYOW Local");  // TODO get this from database
				jlog.setNumberLegs(1);
				jlog.setWheelsUp(flight.getWheelsUp());
				jlog.setWheelsDown(flight.getWheelsDown());
				jlog.setAirTime(flight.getAirTime());
				jlog.setFlightTime(flight.getFlightTime());
				jlog.setTotalAirframeTime(totalAirframeTime);					
				jlog.saveEx();					
				log.info("  Added new summary log entry. TotalAirframeTime=" + totalAirframeTime);
			}
			else {
				jlog.setNumOps(jlog.getNumOps() + 1);
				//jlog.setIntendedFlight("CYOW Local");  // TODO get this from database
				//jlog.setNumberLegs(1);
				if (flight.getWheelsUp().before(jlog.getWheelsUp()))
					jlog.setWheelsUp(flight.getWheelsUp());
				if (flight.getWheelsDown().after(jlog.getWheelsDown()))
					jlog.setWheelsDown(flight.getWheelsDown());
				jlog.setAirTime(jlog.getAirTime().add(flight.getAirTime()));
				jlog.setFlightTime(jlog.getFlightTime().add(flight.getFlightTime()));
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

}
