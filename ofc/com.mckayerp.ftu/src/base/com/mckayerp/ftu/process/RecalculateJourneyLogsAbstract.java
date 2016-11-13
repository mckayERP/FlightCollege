/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package com.mckayerp.ftu.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
/** Generated Process for (Recalculate Journey Logs)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public abstract class RecalculateJourneyLogsAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "FTU_RecalculateJourneyLogs";
	/** Process Name 	*/
	private static final String NAME = "Recalculate Journey Logs";
	/** Process Id 	*/
	private static final int ID = 1000050;
 
	/**	Parameter Name for FTU_Aircraft_ID	*/
	public static final String FTU_Aircraft_ID = "FTU_Aircraft_ID";
	/**	Parameter Name for DateFrom	*/
	public static final String DateFrom = "DateFrom";

	/**	Parameter Value for aircraftId	*/
	protected int ftu_aircraft_id;
	/**	Parameter Value for dateFrom	*/
	protected Timestamp dateFrom;
 

	@Override
	protected void prepare()
	{
		ftu_aircraft_id = getParameterAsInt(FTU_Aircraft_ID);
		dateFrom = getParameterAsTimestamp(DateFrom);
	}

	/**	 Getter Parameter Value for aircraftId	*/
	protected int getAircraftId() {
		return ftu_aircraft_id;
	}

	/**	 Getter Parameter Value for dateFrom	*/
	protected Timestamp getDateFrom() {
		return dateFrom;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}