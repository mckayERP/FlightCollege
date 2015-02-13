/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.util.Env;

/**
 *  Post Invoice Documents.
 *  <pre>
 *  Table:              C_BankStatement (392)
 *  Document Types:     CMB
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_Bank.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 *  
 *  FR [ 1840016 ] Avoid usage of clearing accounts - subject to C_AcctSchema.IsPostIfClearingEqual 
 *  Avoid posting if both accounts BankAsset and BankInTransit are equal
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 				<li>FR [ 2520591 ] Support multiples calendar for Org 
 * 				@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 *  
 */
public class Doc_BankStatement extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_BankStatement (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MBankStatement.class, rs, DOCTYPE_BankStatement, trxName);
	}	//	Doc_Bank
	
	/** Bank Account			*/
	private int			m_C_BankAccount_ID = 0;
	private int m_AD_Org_ID;
	private int m_C_BPartner_ID;

	/**
	 *  Load Specific Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails ()
	{
		MBankStatement bs = (MBankStatement)getPO();
		setDateDoc(bs.getStatementDate());
		setDateAcct(bs.getStatementDate());	//	Overwritten on Line Level
		
		m_C_BankAccount_ID = bs.getC_BankAccount_ID();
		//	Amounts
		setAmount(AMTTYPE_Gross, bs.getStatementDifference());

		//  Set Bank Account Info (Currency)
		MBankAccount ba = MBankAccount.get (getCtx(), m_C_BankAccount_ID);
		setC_Currency_ID (ba.getC_Currency_ID());

		//	Contained Objects
		p_lines = loadLines(bs);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails

	/**
	 *	Load Invoice Line.
	 *	@param bs bank statement
	 *  4 amounts
	 *  AMTTYPE_Payment
	 *  AMTTYPE_Statement2
	 *  AMTTYPE_Charge
	 *  AMTTYPE_Interest
	 *  @return DocLine Array
	 */
	private DocLine[] loadLines(MBankStatement bs)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		MBankStatementLine[] lines = bs.getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MBankStatementLine line = lines[i];
			DocLine_Bank docLine = new DocLine_Bank(line, this);
			//	Set Date Acct
			if (i == 0)
				setDateAcct(line.getDateAcct());
			MPeriod period = MPeriod.get(getCtx(), line.getDateAcct(), line.getAD_Org_ID());
			if (period != null && period.isOpen(DOCTYPE_BankStatement, line.getDateAcct()))
				docLine.setC_Period_ID(period.getC_Period_ID());
			//
			list.add(docLine);
		}

		//	Return Array
		DocLine[] dls = new DocLine[list.size()];
		list.toArray(dls);
		return dls;
	}	//	loadLines

	
	/**************************************************************************
	 *  Get Source Currency Balance - subtracts line amounts from total - no rounding
	 *  @return positive amount, if total invoice is bigger than lines
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		StringBuffer sb = new StringBuffer (" [");
		//  Total
		retValue = retValue.add(getAmount(Doc.AMTTYPE_Gross));
		sb.append(getAmount(Doc.AMTTYPE_Gross));
		//  - Lines
		for (int i = 0; i < p_lines.length; i++)
		{
			BigDecimal lineBalance = ((DocLine_Bank)p_lines[i]).getStmtAmt();
			retValue = retValue.subtract(lineBalance);
			sb.append("-").append(lineBalance);
		}
		sb.append("]");
		//
		log.fine(toString() + " Balance=" + retValue + sb.toString());
		return retValue;
	}   //  getBalance

	/**
	 *  Create Facts (the accounting logic) for
	 *  CMB.
	 *  <pre>
	 *      BankAsset       DR      CR  (Statement)
	 *      BankInTransit   DR      CR              (Payment)
	 *      Charge          DR          (Charge)
	 *      Interest        DR      CR  (Interest)
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		// boolean isInterOrg = isInterOrg(as);

		//  Header -- there may be different currency amounts

		//  Assumptions in creating the FACTS:
		//  1. The currency of the bank account and payment may all be different
		//  2. The line currency and the statement currency are the same.
		//  3. The trxAmt is the converted value of the payment in line currency on the line dateAcct and can be calculated using 
		//     the payment information and the conversion rates in the system.
		
		FactLine fl = null;
		m_AD_Org_ID = getBank_Org_ID();	//	Bank Account Org
		
		MAccount acct_bank_asset =  getAccount(Doc.ACCTTYPE_BankAsset, as);
		MAccount acct_bank_in_transit = getAccount(Doc.ACCTTYPE_BankInTransit, as);
		
		//  Lines
		for (int i = 0; i < p_lines.length; i++)
		{
			DocLine_Bank line = (DocLine_Bank)p_lines[i];
			m_C_BPartner_ID = line.getC_BPartner_ID();
			int C_Payment_ID = line.getC_Payment_ID();
			//
			BigDecimal stmtAmt = Env.ZERO.add(line.getStmtAmt()); //  In bank currency
			BigDecimal stmtAmt_minus_trx = stmtAmt.subtract(line.getTrxAmt()); // In bank currency
			
			// Get payment details
			MPayment pmt = null;
			if (C_Payment_ID != 0)  // Don't create a payment
				pmt = new MPayment(getCtx(), C_Payment_ID, getTrxName());
										
			// Test for currency differences and add realized gains/losses, if any
			if (pmt != null)
			{
				if (getC_Currency_ID() != pmt.getC_Currency_ID())
				{
					this.setIsMultiCurrency(true);  // Don't balance source currency - a conversion is going to happen.
					
					// Test the accounted amounts 
					// The original payment amount would have been posted in the in-transit account if used.
					BigDecimal pmtOrigAcctAmt = MConversionRate.convert(getCtx(), pmt.getPayAmt(), pmt.getC_Currency_ID(), 
							as.getC_Currency_ID(), pmt.getDateAcct(), pmt.getC_ConversionType_ID(), pmt.getAD_Client_ID(), pmt.getAD_Org_ID());
					BigDecimal pmtNewAcctAmt = MConversionRate.convert(getCtx(), pmt.getPayAmt(), pmt.getC_Currency_ID(), 
							as.getC_Currency_ID(), line.getDateAcct(), pmt.getC_ConversionType_ID(), pmt.getAD_Client_ID(), pmt.getAD_Org_ID());
					BigDecimal realizedGain = pmtNewAcctAmt.subtract(pmtOrigAcctAmt); // In system currency
					//
					//  Find the new source currency amount - payment converted to bank currency on line DateAcct
					BigDecimal pmtNewSourceAmt = MConversionRate.convert(getCtx(), pmt.getPayAmt(), pmt.getC_Currency_ID(), 
							getC_Currency_ID(), line.getDateAcct(), 0, pmt.getAD_Client_ID(), pmt.getAD_Org_ID());
					
					String description = "Payment=(" + MCurrency.getISO_Code(getCtx(), pmt.getC_Currency_ID()) + ")" + 
								pmt.getPayAmt() + "/" + pmtOrigAcctAmt
								+ " - Bank=(" + MCurrency.getISO_Code(getCtx(),getC_Currency_ID()) + ")" + pmt.getPayAmt() + "/" + pmtNewAcctAmt;

					// Move the payment from the transit account to the bank asset account
					// in the payment currency, evaluated on the dateacct of the payment.
					// The transaction is the same whether clearing accounts are used or not.
					Timestamp lineDateAcct = line.getDateAcct();
					line.setDateAcct(pmt.getDateAcct());  // Fake the date of the original posting
					if (pmt.isReceipt())
					{
						//	Transit - the acct amount should match pmtOrigAcctAmt
						fl = fact.createLine(line, acct_bank_in_transit,
							pmt.getC_Currency_ID(), null, pmt.getPayAmt());
						addBPandOrg(fl, line);						
						//
						//Reset the line date
						line.setDateAcct(lineDateAcct); // Reset the line
						//  BankAsset       DR      CR  (Statement)
						//  Converted to bank currency
						fl = fact.createLine(line, acct_bank_asset,
							getC_Currency_ID(), pmtNewSourceAmt, null);
						addBPandOrg(fl, line);
					}
					else 
					{
						//	Asset - the acct amount should match pmtOrigAcctAmt
						fl = fact.createLine(line, acct_bank_in_transit,
							pmt.getC_Currency_ID(), pmt.getPayAmt(), null);
						addBPandOrg(fl, line);
						//
						//Reset the line date
						line.setDateAcct(lineDateAcct); // Reset the line
						//  BankAsset       DR      CR  (Statement)
						fl = fact.createLine(line, acct_bank_asset,
							getC_Currency_ID(), null, pmtNewSourceAmt);
						addBPandOrg(fl, line);
					}

					if (!(realizedGain.compareTo(Env.ZERO) == 0))
					{
						// Add the realized gain on the transaction so the total matches pmtNewAcctAmt
						MAccount gain = MAccount.get (as.getCtx(), as.getAcctSchemaDefault().getRealizedGain_Acct());
						MAccount loss = MAccount.get (as.getCtx(), as.getAcctSchemaDefault().getRealizedLoss_Acct());
						fl = fact.createLine (line, loss, gain, 
								as.getC_Currency_ID(), realizedGain.negate());
						fl.setDescription(description);
						addBPandOrg(fl, line);
					}
				}
				else  // Same currency
				{
					// Avoid usage of clearing accounts
					// If both accounts BankAsset and BankInTransit are equal
					// don't make any entry
					if (as.isPostIfClearingEqual() || !acct_bank_asset.equals(acct_bank_in_transit)) {
						// Using clearing accounts
						fl = fact.createLine(line, acct_bank_in_transit,
							getC_Currency_ID(), line.getTrxAmt().negate());
						addBPandOrg(fl, line);
					}
				}
			}
			else // Pmt is null
			{
				//  This shouldn't happen - see MBankStatementLine.beforeSave() Un-link Payment if TrxAmt is zero - teo_sarca BF [ 1896880 ] 
				if (line.getTrxAmt().compareTo(Env.ZERO) != 0)
					log.warning("No payment but transaction amount is not zero. Ignoring.");
			}

			if ((!as.isPostIfClearingEqual()) && acct_bank_asset.equals(acct_bank_in_transit)) {
				// Not using clearing accounts
				// just post the difference (if any) - should equal the charge and interest amounts				
				if (stmtAmt_minus_trx.compareTo(Env.ZERO) != 0) {

					//  BankAsset       DR      CR  (Statement minus Payment)
					fl = fact.createLine(line, acct_bank_asset,
						getC_Currency_ID(), stmtAmt_minus_trx);
				}	
			} else {
				//  Normal Adempiere behavior
				//  We're using clearing accounts - update the bank asset account with the full value of the 
				//  Statement.
				if (stmtAmt.compareTo(Env.ZERO)!= 0)
				{
					fl = fact.createLine(line, acct_bank_asset,
						getC_Currency_ID(), stmtAmt);
					addBPandOrg(fl, line);
				}				
			}
			
			//  Add the charge expenses
			if (line.getChargeAmt().compareTo(Env.ZERO) != 0)
			{
				if (line.getChargeAmt().compareTo(Env.ZERO) > 0) {
					fl = fact.createLine(line,
							line.getChargeAccount(as, line.getChargeAmt().negate()),
							getC_Currency_ID(), null, line.getChargeAmt());
				} else {
					fl = fact.createLine(line,
							line.getChargeAccount(as, line.getChargeAmt().negate()),
							line.getC_Currency_ID(), line.getChargeAmt().negate(), null);
				}
				addBPandOrg(fl, line);
			}

			//  Interest        DR      CR  (Interest)
			if (line.getInterestAmt().compareTo(Env.ZERO) != 0)
			{
				if (line.getInterestAmt().signum() < 0)
					fl = fact.createLine(line,
						getAccount(Doc.ACCTTYPE_InterestExp, as), getAccount(Doc.ACCTTYPE_InterestExp, as),
						getC_Currency_ID(), line.getInterestAmt().negate());
				else
					fl = fact.createLine(line,
						getAccount(Doc.ACCTTYPE_InterestRev, as), getAccount(Doc.ACCTTYPE_InterestRev, as),
						getC_Currency_ID(), line.getInterestAmt().negate());
				addBPandOrg(fl, line);
			}
			//
			//	fact.createTaxCorrection();
		}  // Next line
		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact

	/** Verify if the posting involves two or more organizations
	@return true if there are more than one org involved on the posting
	private boolean isInterOrg(MAcctSchema as) {
		MAcctSchemaElement elementorg = as.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_Organization);
		if (elementorg == null || !elementorg.isBalanced()) {
			// no org element or not need to be balanced
			return false;
		}
		
		if (p_lines.length <= 0) {
			// no lines
			return false;
		}
		
		int startorg = getBank_Org_ID();
		if (startorg == 0)
			startorg = p_lines[0].getAD_Org_ID();
		// validate if the allocation involves more than one org
		for (int i = 0; i < p_lines.length; i++) {
			if (p_lines[i].getAD_Org_ID() != startorg)
				return true;
		}
		
		return false;
	}
	 */

	/**
	 * 	Get AD_Org_ID from Bank Account
	 * 	@return AD_Org_ID or 0
	 */
	private int getBank_Org_ID ()
	{
		if (m_C_BankAccount_ID == 0)
			return 0;
		//
		MBankAccount ba = MBankAccount.get(getCtx(), m_C_BankAccount_ID);
		return ba.getAD_Org_ID();
	}	//	getBank_Org_ID
	
	private void addBPandOrg(FactLine fl, DocLine_Bank line)
	{
		if (fl != null)
		{
			if (m_C_BPartner_ID != 0)
				fl.setC_BPartner_ID(m_C_BPartner_ID);
			if (m_AD_Org_ID != 0)
				fl.setAD_Org_ID(m_AD_Org_ID);
			else
				fl.setAD_Org_ID(line.getAD_Org_ID(true)); // from payment
		}
	}
}   //  Doc_Bank
