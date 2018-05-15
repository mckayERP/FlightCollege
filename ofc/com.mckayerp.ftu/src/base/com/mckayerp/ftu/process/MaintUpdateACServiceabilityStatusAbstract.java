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

import org.compiere.process.SvrProcess;
/** Generated Process for (Update Aircraft Serviceability Status)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public abstract class MaintUpdateACServiceabilityStatusAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "MaintUpdateACServiceabilityStatus";
	/** Process Name 	*/
	private static final String NAME = "Update Aircraft Serviceability Status";
	/** Process Id 	*/
	private static final int ID = 1000059;
 
	/**	Parameter Name for FTU_Aircraft_ID	*/
	public static final String FTU_Aircraft_ID = "FTU_Aircraft_ID";

	/**	Parameter Name for FTU_MaintWOResult_ID	*/
	public static final String FTU_MaintWOResult_ID = "FTU_MaintWOResult_ID";

	/**	Parameter Value for aircraftId	*/
	private int aircraftId;
 
	/**	Parameter Value for ftu_maintWOResult_id	*/
	private int ftu_maintWOResult_id;

	@Override
	protected void prepare()
	{
		aircraftId = getParameterAsInt(FTU_Aircraft_ID);
	}

	/**	 Getter Parameter Value for aircraftId	*/
	protected int getAircraftId() {
		return aircraftId;
	}

	/**
	 * @return the ftu_maintWOResult_id
	 */
	public int getFTU_MaintWOResult_ID() {
		return ftu_maintWOResult_id;
	}

	/**
	 * Setter for the ftu_maintWOResult_id
	 * @param i
	 */
	public void setFTU_MaintWOResult_ID(int ftu_maintWOResult_id) {
		this.ftu_maintWOResult_id = ftu_maintWOResult_id;
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