package com.mckayerp.helcim.model;

import org.compiere.model.MClient;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

import com.helcim.helcim_semi_integrated.HCMPaymentProcessor;

public class HelcimModelValidator implements ModelValidator {
	
	public HelcimModelValidator() {
		super();
	}


	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());

	/** Client			*/
	private int		m_AD_Client_ID = -1;

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		
		// Not global
		if (client == null)
			return;
		
		if (client != null)
		{	
			m_AD_Client_ID = client.getAD_Client_ID();
		}
		engine.addDocValidate(MPayment.Table_Name, this);
		
	}

	@Override
	public int getAD_Client_ID() {
		return m_AD_Client_ID;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		
		boolean isNew = (TYPE_AFTER_NEW == type);
		boolean isChange = (TYPE_AFTER_CHANGE == type);
		
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {

		if (po instanceof MPayment) {	
			log.fine(po.get_TableName() + " Timing: "+ timing);
			boolean isBeingVoided = (ModelValidator.TIMING_BEFORE_VOID == timing);
			boolean isReversed = (ModelValidator.TIMING_AFTER_REVERSECORRECT == timing);
			
			MPayment pmt = (MPayment) po;

			if (!MPayment.DOCSTATUS_Completed.equals(pmt.getDocStatus()))
			{
				return null;  // OK - only interested in completed payments.
			}
			
			if (!pmt.setPaymentProcessor())
			{
				return null;
			}
			
			MPaymentProcessor m_pp = pmt.getMPaymentProcessor();
			
			if (!HCMPaymentProcessor.class.getCanonicalName().equals(m_pp.getPayProcessorClass()))
			{
				return null; // Don't care
			}
			
			if (pmt.getR_PnRef() == null || pmt.getR_AuthCode() == null || !pmt.isOnline())
			{
				return null; // wasn't processed online so don't care
			}
			
			if (isBeingVoided)
			{
				// The payment is a valid credit card transaction.  It can be voided on the same day
				// or reversed if more than a day has gone past.
				// Try void
				if (pmt.getDateTrx().compareTo(Env.getContextAsDate(po.getCtx(), "#Date")) != 0)
				{
					pmt.setDocAction(MPayment.DOCACTION_Reverse_Correct);
				}
			}
		}
		return null;
	}
}
