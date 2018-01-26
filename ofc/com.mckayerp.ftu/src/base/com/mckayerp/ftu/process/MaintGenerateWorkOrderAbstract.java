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

import java.util.List;

import org.compiere.process.SvrProcess;
/** Generated Process for (Generate Maintenance Work Orders)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public abstract class MaintGenerateWorkOrderAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "FTU_MaintGenerateWorkOrder";
	/** Process Name 	*/
	private static final String NAME = "Generate Maintenance Work Orders";
	/** Process Id 	*/
	private static final int ID = 1000054;
 
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";

	/**	Parameter Value for aMOId	*/
	private int amo_id;
 
	/** The list of selected maintenance actions */
	private List<Integer> maintNextAction_ids;
 

	@Override
	protected void prepare()
	{
		amo_id = getParameterAsInt(C_BPartner_ID);
		maintNextAction_ids = this.getSelectionKeys();
	}

	/**	 Getter Parameter Value for aMOId	*/
	protected int getAMO_ID() {
		return amo_id;
	}
	
	protected List<Integer> getFTU_MaintNextAction_ids() {
		return maintNextAction_ids;
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