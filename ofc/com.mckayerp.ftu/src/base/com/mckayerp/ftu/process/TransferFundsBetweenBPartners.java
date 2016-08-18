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
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MCharge;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Transfer funds between business partners.
 * @author Michael McKay (michael.mckay@mckayerp.com)
 * @version 0
 */
public class TransferFundsBetweenBPartners extends SvrProcess
	{
		private int ad_org_id = 0;
		private int c_charge_id = 0;
		private int transfer_from_id = 0;
		private int transfer_to_id = 0;
		private BigDecimal transfer_amount = Env.ZERO;
		
		private static String MSG_sourceTransferDescription = "TransferFundsBetweenBPartners_SourceTransferDescription";
		private static String MSG_targetTransferDescription = "TransferFundsBetweenBPartners_TargetTransferDescription";
		private static String MSG_bothBPsMustBeCustomers = "TransferFundsBetweenBPartners_BothBPsMustBeCustomers";
		private static String MSG_insufficientFunds = "TransferFundsBetweenBPartners_InsufficientFunds";
		private static String MSG_CouldNotCompleteInvoice = "TransferFundsBetweenBPartners_CouldNotCompleteInvoice";

		protected void prepare()
		{
			ProcessInfoParameter[] para = getParameter();
			for (int i = 0; i < para.length; i++)
			{
				String name = para[i].getParameterName();
				if (para[i].getParameter() == null)
					;
				else if (name.equals("AD_Org_ID"))
					ad_org_id = ((BigDecimal)para[i].getParameter()).intValue();
				else if (name.equals("C_Charge_ID"))
					c_charge_id = ((BigDecimal)para[i].getParameter()).intValue();
				else if (name.equals("Transfer_From_ID"))
					transfer_from_id = ((BigDecimal)para[i].getParameter()).intValue();
				else if (name.equals("Transfer_To_ID"))
					transfer_to_id = ((BigDecimal)para[i].getParameter()).intValue();
				else if (name.equals("Amount"))
					transfer_amount  = (BigDecimal)para[i].getParameter();
				else
					log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
			}
		}	//	prepare

		/**
		 *  Perform process.
		 *  @return Message (clear text)
		 *  @throws Exception if not successful
		 */
		protected String doIt() throws Exception
		{
			
			MCharge charge = null;
			MBPartner transfer_from = null; 
			MBPartner transfer_to = null; 
			
			// Validate parameters
			// AD_Org_ID - shouldn't be zero.
			// TODO - inter-org transfers?
			if (ad_org_id == 0) {
				ad_org_id = Env.getAD_Org_ID(getCtx());
				if (ad_org_id == 0) {
					// TODO what to do in this case?
					log.fine("Transfer with AD_Org_ID undefined");
				}
			}
			
			if (c_charge_id == 0) {
				return "@FillMandatory@ @C_Charge_ID@";
			}
			else {
				charge = MCharge.get(getCtx(), c_charge_id);
				if (charge == null)
					return "@Error@ @C_Charge_ID@ @NotFound@";
			}

			if (transfer_from_id == 0) {
				return "@FillMandatory@ @Transfer_From_ID@";
			}
			else {
				transfer_from = MBPartner.get(getCtx(), transfer_from_id);
				if (transfer_from == null)
					return "@Error@ @TRANSFER_FROM_ID@ @NotFound@";
			}

			if (transfer_to_id == 0) {
				return "@FillMandatory@ @Transfer_To_ID@";
			}
			else {
				transfer_to = MBPartner.get(getCtx(), transfer_to_id);
				if (transfer_to == null)
					return "@Error@ @TRANSFER_FROM_ID@ @NotFound@";
			}

			// Assume this is only for customers
			if (!transfer_from.isCustomer() || !transfer_to.isCustomer())
				return "@Error@ " + Msg.translate(getCtx(), MSG_bothBPsMustBeCustomers);
			
			// Check balances - need a positive balance (credit) for customers
			BigDecimal max_transfer_amount = transfer_from.getTotalOpenBalance(true).negate();
			if (max_transfer_amount.compareTo(Env.ZERO) <= 0)
				return "@Error@ " + Msg.translate(getCtx(), MSG_insufficientFunds);
			
			if (max_transfer_amount.compareTo(transfer_amount) < 0) {
				transfer_amount = max_transfer_amount;
				log.fine("Transfer amount limited to: " + max_transfer_amount);
			}
			
		
			String sTransfer_amount = DisplayType.getNumberFormat(DisplayType.CostPrice, Env.getLanguage(getCtx()), "#,##0.00").format(transfer_amount);
			
			MInvoice source = new MInvoice(getCtx(), 0, get_TrxName());
			source.setC_BPartner_ID(transfer_from_id);
			source.setAD_Org_ID(ad_org_id);
			source.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice);
			source.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			source.saveEx();
			
			MInvoiceLine sourceLine = new MInvoiceLine(source);
			sourceLine.setC_Charge_ID(c_charge_id);
			sourceLine.setQty(Env.ONE);
			sourceLine.setPrice(transfer_amount);
			sourceLine.saveEx();
			
			Boolean ok = source.processIt(DocAction.ACTION_Complete);
			if (!ok)
				return "@Error@ " + Msg.translate(getCtx(), MSG_CouldNotCompleteInvoice) +": " + source;
			source.saveEx();

			MInvoice target = new MInvoice(getCtx(), 0, get_TrxName());
			target.setC_BPartner_ID(transfer_to_id);
			target.setAD_Org_ID(ad_org_id);
			target.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARCreditMemo);
			target.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			target.saveEx();
			
			MInvoiceLine targetLine = new MInvoiceLine(target);
			targetLine.setC_Charge_ID(c_charge_id);
			targetLine.setQty(Env.ONE);
			targetLine.setPrice(transfer_amount);
			targetLine.saveEx();
			
			ok = target.processIt(DocAction.ACTION_Complete);
			if (!ok)
				return "@Error@ " + Msg.translate(getCtx(), MSG_CouldNotCompleteInvoice) +": " + target;
			target.saveEx();

			String sourceDescription = Msg.translate(getCtx(), MSG_sourceTransferDescription) + " (" + source.getDocumentNo() + " => " + target.getDocumentNo() + ")"
					+ "\n  " + Msg.translate(getCtx(), "Transfer_To_ID") + ": " + transfer_to.getName()
					+ " " + Msg.translate(getCtx(), "Amount") + ": " + sTransfer_amount; 

			String targetDescription = Msg.translate(getCtx(), MSG_targetTransferDescription) + " (" + source.getDocumentNo() + " => " + target.getDocumentNo() + ")"
					+ "\n  " + Msg.translate(getCtx(), "Transfer_From_ID") + ": " + transfer_from.getName()
					+ " " + Msg.translate(getCtx(), "Amount") + ": " + sTransfer_amount; 

			source.setDescription(sourceDescription);
			source.saveEx();
			sourceLine.setDescription(sourceDescription);
			sourceLine.saveEx();

			target.setDescription(targetDescription);
			target.saveEx();
			targetLine.setDescription(targetDescription);
			targetLine.saveEx();

			
			return "@Success@ " + source + " " + target; //TODO translate			
		}	//	doIt

	}	//	ofcAddMissingCourses

