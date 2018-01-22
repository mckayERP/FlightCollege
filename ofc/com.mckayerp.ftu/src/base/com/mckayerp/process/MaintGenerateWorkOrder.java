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

package com.mckayerp.process;

import java.util.List;

import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.mckayerp.ftu.model.MFTUMaintNextAction;
import com.mckayerp.ftu.model.MFTUMaintRequirement;
import com.mckayerp.ftu.model.MFTUMaintRequirementLine;
import com.mckayerp.ftu.model.MFTUMaintWorkOrder;
import com.mckayerp.ftu.model.MFTUMaintWorkOrderLine;
import com.mckayerp.ftu.model.X_FTU_MaintRequirementLine;

/** Generated Process for (Generate Maintenance Work Orders)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public class MaintGenerateWorkOrder extends MaintGenerateWorkOrderAbstract
{
	private StringBuffer processMsg = new StringBuffer();

	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		if (getFTU_MaintNextAction_ids() == null || getFTU_MaintNextAction_ids().size() == 0)
		{
			return "Empty list";
		}

		
		int root_component_id = 0;
		int mftu_maintWorkOrder_id = 0;
		int lineCounter = 1;
		
		for (Integer nextAction_id : getFTU_MaintNextAction_ids())
		{
			MFTUMaintNextAction na = new MFTUMaintNextAction(getCtx(), nextAction_id, get_TrxName());

			// Create a new header for each root component.  Assumes the list is sorted by root components
			if (root_component_id != na.getCT_Component().getRoot_Component_ID())
			{			
				root_component_id = na.getCT_Component().getRoot_Component_ID();
				
				MFTUMaintWorkOrder wo = new MFTUMaintWorkOrder(getCtx(), 0, get_TrxName());
				
				wo.setDateDoc(Env.getContextAsDate(getCtx(), "#Date"));
				wo.setC_BPartner_ID(getAMO_ID());
				wo.setCT_Component_ID(root_component_id);
				//  TODO Translate
				wo.setDescription("Complete the maintenance actions specified for the identified aircraft/component.");
				wo.saveEx();
				mftu_maintWorkOrder_id = wo.getFTU_MaintWorkOrder_ID();
				
				lineCounter = 1;
				
				processMsg .append("<br>Created Work Order " + wo.toString());
			}
			
			if (mftu_maintWorkOrder_id==0)
				return "@ERROR@ Could not create a work order for Next Action " + na.toString();
			
			int component_id = na.getCT_Component_ID();
			int maintReq_id = na.getFTU_MaintRequirement_ID();

			MFTUMaintRequirement mr = new MFTUMaintRequirement(getCtx(), maintReq_id, get_TrxName());
			MFTUMaintWorkOrderLine wol = new MFTUMaintWorkOrderLine(getCtx(), 0, get_TrxName());
			wol.setFTU_MaintWorkOrder_ID(mftu_maintWorkOrder_id);
			wol.setCT_Component_ID(component_id);
			wol.setFTU_MaintRequirement_ID(na.getFTU_MaintRequirement_ID());
			wol.setFTU_Action(mr.getFTU_Action());
			wol.setFTU_Process(mr.getFTU_Process());
			wol.setLine((lineCounter++)*10);
			wol.saveEx();
			
			if (mr.hasLines())
			{
				List<MFTUMaintRequirementLine> lines = mr.getLines(false);
				
				for (MFTUMaintRequirementLine mrl : lines)
				{
					
					wol = new MFTUMaintWorkOrderLine(getCtx(), 0, get_TrxName());
					wol.setFTU_MaintWorkOrder_ID(mftu_maintWorkOrder_id);
					wol.setCT_Component_ID(component_id);
					wol.setFTU_MaintRequirement_ID(na.getFTU_MaintRequirement_ID());
					wol.setFTU_MaintRequirementLine_ID(mrl.getFTU_MaintRequirementLine_ID());
					wol.setFTU_Action(mrl.getFTU_Action());
					wol.setFTU_Process(mrl.getFTU_Process());
					wol.setLine((lineCounter++)*10);
					wol.saveEx();
					
				}
				
			}			
		}
		
		return processMsg.toString();
	}
}