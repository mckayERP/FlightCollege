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

import org.compiere.util.Env;

import com.mckayerp.ftu.model.MFTUFlightsheet;

/** Generated Process for (Cancel Flight)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public class BatchCancelFlights extends BatchCancelFlightsAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		for (Integer flight_id : flights) 
		{
			if (flight_id == null || flight_id.compareTo(0) <= 0)
				continue;
			
			MFTUFlightsheet flight = new MFTUFlightsheet(getCtx(), flight_id.intValue(), get_TrxName());
			if (flight.getLine_Status() == null || flight.getLine_Status().length() == 0
					|| (!flight.getLine_Status().equals(MFTUFlightsheet.LINE_STATUS_Cancelled)
						&& !flight.getLine_Status().equals(MFTUFlightsheet.LINE_STATUS_Closed)))
			{
				// Only cancel a flight is there are no billable times
				if (flight.getFlightTime().compareTo(Env.ZERO) == 0 
						&& flight.getBriefing().compareTo(Env.ZERO) == 0 
						&& flight.getSimulator().compareTo(Env.ZERO) == 0) {
					
					flight.setLine_Status(MFTUFlightsheet.LINE_STATUS_Cancelled);
					flight.saveEx();
					addLog("@FTU_Flightsheet_ID@: " + flight.getFlightID() + " cancelled.");
				}
				else
				{
					addLog("@FTU_Flightsheet_ID@: " + flight.getFlightID() + " has billable items. Not cancelled.");
				}
			}
		}
		return "Selected flights cancelled successfully.";
	}
}