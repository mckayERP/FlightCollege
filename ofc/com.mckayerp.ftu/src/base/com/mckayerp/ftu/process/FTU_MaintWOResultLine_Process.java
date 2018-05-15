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
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.adempiere.engine.IDocumentLine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoLog;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.eevolution.service.dsl.ProcessBuilder;

import com.mckayerp.ftu.model.MFTUAircraft;
import com.mckayerp.ftu.model.MFTUDefectLog;
import com.mckayerp.ftu.model.MFTUMaintNextAction;
import com.mckayerp.ftu.model.MFTUMaintRequirement;
import com.mckayerp.ftu.model.MFTUMaintWORLDetail;
import com.mckayerp.ftu.model.MFTUMaintWOResultLine;
import com.mckayerp.ftu.model.MFTUMaintWorkOrder;
import com.mckayerp.model.ComponentModelValidator;
import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentHistory;

/** Generated Process for (Process the Result Line)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public class FTU_MaintWOResultLine_Process extends FTU_MaintWOResultLine_ProcessAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		Properties ctx = getCtx();
		String trxName = get_TrxName();
		
		int ftu_maintWOResultLine_id = this.getRecord_ID();
		
		MFTUMaintWOResultLine mrl = new MFTUMaintWOResultLine(ctx, ftu_maintWOResultLine_id, trxName);
		if (mrl == null || mrl.getFTU_MaintWOResultLine_ID() <= 0)
			return "";
		
		if (mrl.isProcessed())
			return "";
		
		//  Process the details
		List<MFTUMaintWORLDetail> details = mrl.getDetails();
		for(MFTUMaintWORLDetail mrld : details)
		{
			ProcessInfo processInfo = ProcessBuilder.create(ctx)
			.process(com.mckayerp.ftu.process.FTU_MaintWORL_Detail_Process.class)
			.withTitle("Process Maintenance WO Result Line Details")
			.withRecordId(MFTUMaintWORLDetail.Table_ID, mrld.getFTU_MaintWORL_Detail_ID())
			.execute();
			
			if (processInfo.getLogList() != null)
			{
				for (ProcessInfoLog log : processInfo.getLogList())
				{
					this.addLog(log.getP_Msg());
				}
			}
		}

		// Process the maintenance result line independently of the 
		// maintenance result document.  The lines should be processed
		// in order.
		String where = MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintWOResult_ID + "=?"
				+ " AND " + MFTUMaintWOResultLine.COLUMNNAME_Line + "< ?"
				+ " AND COALESCE(" + MFTUMaintWOResultLine.COLUMNNAME_Processed + ",'N') != 'Y'";  // Assume Null == No

		int numUnprocessedLines = new Query(mrl.getCtx(), MFTUMaintWOResultLine.Table_Name, where, null)
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.setParameters(mrl.getFTU_MaintWOResult_ID(), mrl.getLine())
						.count();
		
		if (numUnprocessedLines > 0)
			return "@Error@: Process lines in sequence.";

		//  If the result type is blank, assume the result will be deferred to another work order.
		//  Simply set the result type to deferred and add a note to the action taken and move on.
		if (mrl.getFTU_MaintResultType() == null || mrl.getFTU_MaintResultType().isEmpty())
		{
			mrl.setFTU_MaintResultType(MFTUMaintWOResultLine.FTU_MAINTRESULTTYPE_Deferred);
			mrl.setFTU_MaintActionTaken(Msg.translate(ctx, "Deferred"));
			
		}
		
		// If a fault was found, create a defect record, add it to the result.
		// The defect record will require some manual updates.
		if (MFTUMaintWOResultLine.FTU_MAINTRESULTTYPE_CompletedWithFaultFound.equals(mrl.getFTU_MaintResultType()))
		{
			//  TODO Translate
			String description = "Found during maintenance action '" + mrl.getFTU_Action() + "'";
			
			if (mrl.getParent().getFTU_MaintWorkOrder_ID() > 0)
			{
				description += " on Work Order " + mrl.getParent().getFTU_MaintWorkOrder().getDocumentNo();
			}
			
			// Find the aircraft from the work order
			int ftu_aircraft_id = 0;
			int ct_component_id = 0;
			int m_product_id = mrl.getM_Product_ID();
			int m_attributeSetInstance_id = mrl.getM_AttributeSetInstance_ID();
			MCTComponent component = null;
			
			MFTUMaintWorkOrder mwo = (MFTUMaintWorkOrder) mrl.getFTU_MaintWOResult().getFTU_MaintWorkOrder();
			if (mwo != null && mwo.getFTU_Aircraft_ID() > 0)
			{
				ftu_aircraft_id = mwo.getFTU_Aircraft_ID();
				ct_component_id = mwo.getFTU_Aircraft().getCT_Component_ID();
				component = (MCTComponent) mwo.getFTU_Aircraft().getCT_Component();
			}
			else
			{
				// Try the root component identified in the result line
				if (mrl.getCT_Component_ID() > 0)
				{
					MFTUAircraft ac = MFTUAircraft.getByCT_Component_ID(ctx, ((MCTComponent) mrl.getCT_Component()).getRoot_Component_ID(), trxName);
					if (ac != null)
						ftu_aircraft_id = ac.getFTU_Aircraft_ID();
					ct_component_id = mrl.getCT_Component_ID();
					component = (MCTComponent) mrl.getCT_Component();
				}
			}
			
			if (component != null)
			{
				m_product_id = component.getM_Product_ID();
				m_attributeSetInstance_id = component.getM_AttributeSetInstance_ID();
			}

			// Create a defect record
			MFTUDefectLog defect = new MFTUDefectLog(ctx, 0, trxName);
			defect.setAD_Org_ID(mrl.getAD_Org_ID());
			defect.setFTU_MaintWOResultLine_ID(mrl.getFTU_MaintWOResultLine_ID());
			defect.setDefect(mrl.getFTU_MaintActionTaken());
			defect.setDateDoc(mrl.getParent().getDateDoc());
			defect.setCT_Component_ID(ct_component_id);
			defect.setM_Product_ID(m_product_id);
			defect.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
			defect.setC_BPartner_ID(mrl.getParent().getC_BPartner_ID());
			defect.setDefectDate(mrl.getDefectDate());
			defect.setDescription(description);
			defect.setEnteredBy(Env.getAD_User_ID(ctx));
			defect.setIdentifiedDate(mrl.getParent().getDateDoc());
			defect.setFTU_Aircraft_ID(ftu_aircraft_id);
			defect.saveEx();  // Get the ID - not assigned until it is saved.
			defect.enterIt();  // Creates the Maintenance Requirement
			defect.saveEx();
			
			// Find the maint requirement and update it a bit.
			MFTUMaintRequirement mr = defect.getFTU_MaintRequirement();
			if (mr != null)
			{
				mr.setFTU_Action(mrl.getFTU_MaintActionTaken()); 
				mr.setFTU_ResolutionTemplate("(Please complete defect resolution template.)");
				mr.setFTU_ResolutionFFTemplate("(Please complete defect resolution with fault found template.)");
				mr.saveEx();
						
				// As a default, add the defect maint requirement to the maintenance result lines.
				// This assumes that faults found will be fixed in the same work order/result.
				where = MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintWOResult_ID + "=?";
				int lineNo = (new Query(ctx, MFTUMaintWOResultLine.Table_Name, where, null)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(mrl.getFTU_MaintWOResult_ID())
								.aggregate(MFTUMaintWOResultLine.Table_Name + "." + MFTUMaintWOResultLine.COLUMNNAME_Line,
								   Query.AGGREGATE_MAX)).intValue()+10;
				MFTUMaintWOResultLine rLine = new MFTUMaintWOResultLine(ctx, 0, trxName);
				rLine.setFTU_MaintWOResult_ID(mrl.getFTU_MaintWOResult_ID());
				rLine.setFTU_Action(mr.getFTU_Action());
				rLine.setFTU_MaintRequirement_ID(mr.getFTU_MaintRequirement_ID());
				rLine.setFTU_MaintRequirementLine_ID(0);
				rLine.setLine(lineNo);
							
				if (mrl.getCT_Component_ID() > 0)
				{
					rLine.setCT_Component_ID(mrl.getCT_Component_ID());
					rLine.setM_Product_ID(mrl.getCT_Component().getM_Product_ID());
					rLine.setM_AttributeSetInstance_ID(mrl.getCT_Component().getM_AttributeSetInstance_ID());
					rLine.setCT_ComponentLifeAtAction(mrl.getCT_Component().getLifeUsed());
					rLine.setLifeUsageUOM_ID(mrl.getCT_Component().getLifeUsageUOM_ID());
				}
				rLine.saveEx();
			}
		}
		
		// Update the Maintenance Requirement
		if (mrl.isMaintReqCompleted()) // Only set if the result type is "Completed".
		{
			if (mrl.getFTU_DateCompleted() == null)
			{
				return "@Error@ @FTU_DateCompleted@ @FillMandatory@"; 
			}

			// Mark defect logs repaired/rectified
			if (mrl.getFTU_MaintRequirement().getFTU_DefectLog_ID() > 0)
			{
				MFTUDefectLog defect = (MFTUDefectLog) (mrl.getFTU_MaintRequirement().getFTU_DefectLog());
				defect.setFTU_MaintWOResultLine_ID(mrl.getFTU_MaintWOResultLine_ID());
				defect.setIsRepaired(true);
				defect.setRectification(mrl.getFTU_MaintActionTaken());
				defect.setRectifiedBy(getAD_User_ID());
				defect.setRepairedDate(mrl.getFTU_DateCompleted());
				if (!defect.getDocStatus().equals(MFTUDefectLog.DOCSTATUS_Rectified) && !defect.rectifyIt())  // If the document is already rectified, just update it.
					return "@Error@ Unable to rectify defect related to Result Line " + mrl.getLine();
				defect.saveEx();
			}
			
			// Set the next actions
			MFTUMaintNextAction na = MFTUMaintNextAction.getByComponentAndMaintRequirement(ctx, mrl.getCT_Component_ID(), mrl.getFTU_MaintRequirement_ID(), trxName);
			if (na == null)
			{
				log.severe("No Next Maintenance Action for component & maint requirement:" 
						+ mrl.getCT_Component_ID() + " - " + mrl.getFTU_MaintRequirement_ID());
				// TODO deal with it
			}
			else
			{
				// Time tolerances are in days here.
				long dateToleranceApplied = 0;
				if (na.getFTU_DateNextDue() != null)  // na.getFTU_DateNextDue could be null.  mr.getDateCompleted shouldn't be.
				{
					if (mrl.getFTU_DateNextDue() != null)
					{
						dateToleranceApplied = TimeUnit.DAYS
							.convert(mrl.getFTU_DateCompleted().getTime() - mrl.getFTU_DateNextDue().getTime(), 
									TimeUnit.MILLISECONDS);
					}
					else
					{
						dateToleranceApplied = TimeUnit.DAYS
								.convert(mrl.getFTU_DateCompleted().getTime() - na.getFTU_DateNextDue().getTime(), 
										TimeUnit.MILLISECONDS);						
					}
				}
				
				// Usage intervals are in the component usage UOM
				BigDecimal usageTolApplied = Env.ZERO;
				BigDecimal usageNextDue = Env.ZERO;
				BigDecimal compLife = Env.ZERO;
				
				if (!mrl.getFTU_UsageNextDue().equals(Env.ZERO))
				{
					usageNextDue = mrl.getFTU_UsageNextDue();
				}
				else 
				{
					usageNextDue = na.getFTU_UsageNextDue();
				}

				if (mrl.getCT_ComponentLifeAtAction().equals(Env.ZERO))
				{
					compLife = mrl.getCT_ComponentLifeAtAction();
				}
				else if (mrl.getCT_Component_ID() > 0)
				{
					compLife = mrl.getCT_Component().getLifeUsed();
				}

				usageTolApplied = usageNextDue.subtract(compLife);
				
				
				na.setFTU_DateLastDone(mrl.getFTU_DateCompleted());
				na.setFTU_UsageLastDone(compLife);

				// Don't bother recording tolerances applied if there is only one result for this action.
				// The first result is assumed to be correct and sets the base for any repetitive actions.
				boolean recordTolerancesApplied = MFTUMaintWOResultLine.countByNextAction(ctx, na.getFTU_MaintNextAction_ID(), trxName) > 1;
				if (recordTolerancesApplied)
				{
					na.setFTU_UsageTolApplied(usageTolApplied);
					na.setFTU_DateTolApplied(BigDecimal.valueOf(dateToleranceApplied));
				}

				na.recalc();  //  Important! Or the due dates will remain the same.
				na.saveEx();  //  Will update the next due dates
				
				// Link the next action with the result line, if not done already
				if (mrl.getFTU_MaintNextAction_ID() != na.getFTU_MaintNextAction_ID())
					mrl.setFTU_MaintNextAction_ID(na.getFTU_MaintNextAction_ID());

				//  Record the applied tolerances in the result line (this will give a record
				//  or any overruns experienced.
				mrl.setFTU_DateToleranceApplied(BigDecimal.valueOf(dateToleranceApplied));
				mrl.setFTU_UsageTolApplied(usageTolApplied);
				
			}
			
			// Update the component history
			// Here, the Component Action type is determined by the type of maintenance 
			// requirement: AD, Defect or Scheduled Maintenance
			// Another possibility is to add the Action Type to the WO Result Line
			// Ovehrauls are set in the details line when the component life is reset.
			String actionType = MCTComponentHistory.CT_COMPONENTACTIONTYPE_Inspected;
			if (mrl.getFTU_DefectLog_ID() > 0)
			{
				actionType = MCTComponentHistory.CT_COMPONENTACTIONTYPE_Repaired;
			}
			else if (mrl.getFTU_MaintRequirement().getFTU_AirworthinessDirective_ID() > 0)
			{
				actionType = MCTComponentHistory.CT_COMPONENTACTIONTYPE_Inspected;
			}
			
			MCTComponent comp = (MCTComponent) mrl.getCT_Component();
			ComponentModelValidator.addHistoryFromDocLine(ctx, 
					(MCTComponent) mrl.getCT_Component(), 
					(IDocumentLine) mrl, 
					comp.getRoot_Locator_ID(), // Locator 
					comp.getParentComponent_ID(), // Parent - if there is only one
					comp.getRoot_Component_ID(), // Root 
					actionType, null, trxName);
		}
		
		mrl.setProcessed(true);
		mrl.saveEx();
		
		return "";
	}
}