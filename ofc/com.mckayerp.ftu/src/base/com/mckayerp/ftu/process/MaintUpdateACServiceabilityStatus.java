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

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.util.Env;

import com.mckayerp.ftu.model.FTUModelValidator;
import com.mckayerp.ftu.model.MFTUAircraft;
import com.mckayerp.ftu.model.MFTUMaintNextAction;
import com.mckayerp.model.MCTComponent;

/** Generated Process for (Update Aircraft Serviceability Status)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public class MaintUpdateACServiceabilityStatus extends MaintUpdateACServiceabilityStatusAbstract
{
	@Override
	protected void prepare()
	{
		
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		
		// Grab the lock to prevent blocking conditions with the flight sheet
		if (!LoadFlightsheetFromXML.grabLock(get_TrxName()))
		{
			LoadFlightsheetFromXML.releaseLock(get_TrxName());
			log.info("Unable to capture lock. Try again later.");
			return "Unable to capture lock.  Try again later.";
		}

		String where = "";
		if (getAircraftId() > 0)
		{
			where = MFTUAircraft.COLUMNNAME_FTU_Aircraft_ID + "=" + getAircraftId();
		}
		else
		{
			//  If a single aircraft is not identified, we'll update the entire fleet.
			//  In this case, its not possible to link to a Work Order result.
			//  Ignore the parameter by setting it to zero.  Any missing BOM 
			//  components will be left as open administrative defects.
			this.setFTU_MaintWOResult_ID(0);
		}
		
		int[] aircraftIDs = MFTUAircraft.getAllIDs(MFTUAircraft.Table_Name, where, get_TrxName());
		
		for (int ftu_aircraft_id : aircraftIDs)
		{
		
			if (ftu_aircraft_id <= 0)
				continue;
			
			MFTUAircraft ac = new MFTUAircraft(getCtx(), ftu_aircraft_id, get_TrxName());
			if (ac == null || ac.getFTU_Aircraft_ID() <= 0 || ac.getCT_Component_ID() <= 0)
				continue;
			
			/* Check the aircraft component serviceability.  The aircraft is assumed to be serviceable
			 * if all components are installed and there are no maintenance requirements due.   
			 */
			
			// First check for missing components.  Non-critical components that have a deferred defect
			// entry are ignored.
			boolean createDefects = true;
			ac.checkComponentBOM(getFTU_MaintWOResult_ID(), createDefects);
			
			// Update the aircraft next maintenance dates and tolerances and test the 
			// the dates and hours to the next maintenance action.  Set the serviceability
			// status accordingly.
			ac.updateACServiceabilityStatus();
			
			ac.saveEx();
			
		}
		
		LoadFlightsheetFromXML.releaseLock(get_TrxName());
		log.info("Aircraft availability status updated.");
		return "Aircraft availability status updated.";
	}
}