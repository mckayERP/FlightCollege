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

import org.compiere.model.Query;

import com.mckayerp.ftu.model.MFTUMaintWOResult;
import com.mckayerp.ftu.model.MFTUMaintWOResultLine;
import com.mckayerp.ftu.model.MFTUMaintWorkOrder;
import com.mckayerp.ftu.model.MFTUMaintWorkOrderLine;
import com.mckayerp.ftu.model.X_FTU_MaintWOResult;

/** Generated Process for (Create Maint WO Result Lines)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public class CreateWOResultLines extends CreateWOResultLinesAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		//  Get the current work order result document.
		MFTUMaintWOResult result = new MFTUMaintWOResult(getCtx(),this.getRecord_ID(),get_TrxName());
		
		//  If the document is not draft or invalid
		if (result.getDocStatus().equals(X_FTU_MaintWOResult.DOCSTATUS_Completed) 
				|| result.getDocStatus().equals(X_FTU_MaintWOResult.DOCSTATUS_Closed)
				|| result.getDocStatus().equals(X_FTU_MaintWOResult.DOCSTATUS_Voided))
		{
			return "Document already completed/closed or voided. No changes allowed"; // TODO translate
		}
		
		if (result.getFTU_MaintWorkOrder_ID() == 0)
		{
			return "Error: No work order identified!"; // TODO Translate
		}
		
		MFTUMaintWorkOrder wohdr = (MFTUMaintWorkOrder) result.getFTU_MaintWorkOrder();
		List<MFTUMaintWorkOrderLine> woLines = wohdr.getLines(false);
		
		String where = MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintWOResult_ID + "=" + result.getFTU_MaintWOResult_ID();
		Query lineQuery = new Query(getCtx(), MFTUMaintWOResultLine.Table_Name, where, get_TrxName())
								.setClient_ID()
								.setOnlyActiveRecords(true);
		
		int i = 0;
		for (MFTUMaintWorkOrderLine woLine : woLines)
		{
			int lineNo = (lineQuery
					.aggregate(MFTUMaintWOResultLine.Table_Name + "." + MFTUMaintWOResultLine.COLUMNNAME_Line,
							   Query.AGGREGATE_MAX)).intValue()+10;
			MFTUMaintWOResultLine rLine = new MFTUMaintWOResultLine(getCtx(),0,get_TrxName());
			rLine.setFTU_MaintWOResult_ID(this.getRecord_ID());
			rLine.setFTU_Action(woLine.getFTU_Action());
			rLine.setFTU_MaintRequirement_ID(woLine.getFTU_MaintRequirement_ID());
			rLine.setFTU_MaintRequirementLine_ID(woLine.getFTU_MaintRequirementLine_ID());
			rLine.setFTU_DateNextDue(woLine.getFTU_DateNextDue());
			rLine.setFTU_TimeIntervalTol(woLine.getFTU_TimeIntervalTol());
			rLine.setFTU_TimeToleranceType(woLine.getFTU_TimeToleranceType());
			rLine.setFTU_UsageNextDue(woLine.getFTU_UsageNextDue());
			rLine.setFTU_UsageIntervalTol(woLine.getFTU_UsageIntervalTol());
			rLine.setFTU_UsageToleranceType(woLine.getFTU_UsageToleranceType());

			rLine.setLine(lineNo);
			
			String actionTaken = ""; 
			if(woLine.getFTU_MaintRequirementLine_ID()>0)
			{
				actionTaken = woLine.getFTU_MaintRequirementLine().getFTU_ResolutionTemplate();
				if(woLine.getFTU_MaintRequirementLine().getCT_DataSet_ID() > 0)
					rLine.setCT_DataSet_ID(woLine.getFTU_MaintRequirementLine().getCT_DataSet_ID());
			}
			else if (woLine.getFTU_MaintRequirement_ID() > 0)
			{
				actionTaken = woLine.getFTU_MaintRequirement().getFTU_ResolutionTemplate();
				if(woLine.getFTU_MaintRequirement().getCT_DataSet_ID() > 0)
					rLine.setCT_DataSet_ID(woLine.getFTU_MaintRequirement().getCT_DataSet_ID());
			}
			rLine.setFTU_MaintActionTaken(actionTaken);
			
			if (woLine.getCT_Component_ID() > 0)
			{
				rLine.setCT_Component_ID(woLine.getCT_Component_ID());
				rLine.setM_Product_ID(woLine.getCT_Component().getM_Product_ID());
				rLine.setM_AttributeSetInstance_ID(woLine.getCT_Component().getM_AttributeSetInstance_ID());
				rLine.setCT_ComponentLifeAtAction(woLine.getCT_Component().getLifeUsed());
				rLine.setLifeUsageUOM_ID(woLine.getCT_Component().getLifeUsageUOM_ID());
				rLine.setRoot_Component_ID(woLine.getCT_Component().getRoot_Component_ID());
				if (rLine.getRoot_Component_ID() > 0)
					rLine.setCT_RootLifeAtAction(rLine.getRoot_Component().getLifeUsed());
			}
			rLine.saveEx();
			i++;
		}
		return "@Created@ " + i;
	}
}