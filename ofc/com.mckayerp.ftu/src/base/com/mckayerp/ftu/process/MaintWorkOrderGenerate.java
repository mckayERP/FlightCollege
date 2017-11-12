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
import org.compiere.util.Msg;

import com.mckayerp.ftu.model.MFTUMaintRequirement;
import com.mckayerp.ftu.model.MFTUMaintWorkOrder;
import com.mckayerp.ftu.model.MFTUMaintWorkOrderLine;

/** Generated Process for (Generate Maintenance Work Orders)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public class MaintWorkOrderGenerate extends MaintWorkOrderGenerateAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		if (this.getComponentId() <= 0)
		{
			return Msg.parseTranslation(getCtx(), "@FTU_Component_ID@ @Mandatory@");
		}
		
		if (getFTU_MaintRequirement_ids() == null || getFTU_MaintRequirement_ids().size() == 0)
		{
			return "Empty list";
		}
		
		MFTUMaintWorkOrder wo = new MFTUMaintWorkOrder(getCtx(), 0, get_TrxName());
		
		wo.setDateDoc(Env.getContextAsDate(getCtx(), "#Date"));
		wo.setC_BPartner_ID(getAMO_ID());
		wo.setFTU_Component_ID(getComponentId());
		wo.setDescription("Complete the maintenance actions specified for the identified aircraft/component.");
		wo.saveEx();
		
		for (Integer maintReq_id : this.getFTU_MaintRequirement_ids())
		{
			MFTUMaintRequirement mr = new MFTUMaintRequirement(getCtx(), maintReq_id.intValue(), get_TrxName());
			MFTUMaintWorkOrderLine wol = new MFTUMaintWorkOrderLine(getCtx(), 0, get_TrxName());
			wol.setFTU_MaintWorkOrder_ID(wo.getFTU_MaintWorkOrder_ID());
			wol.setFTU_Component_ID(getComponentId());
			wol.setFTU_MaintRequirement_ID(mr.getFTU_MaintRequirement_ID());
			wol.setFTU_Action(mr.getFTU_Action());
			wol.setFTU_Process(mr.getFTU_Process());
			wol.saveEx();
		}
		
		return "";
	}
}