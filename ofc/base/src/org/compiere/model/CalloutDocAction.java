package org.compiere.model;

import java.util.Properties;

import org.compiere.apps.ADialog;

public class CalloutDocAction extends CalloutEngine {

	public CalloutDocAction() {
		// TODO Auto-generated constructor stub
	}

	public String docAction(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value)
	{
		if (mTab.getRecord_ID() <= 0)
			return null;

		if (! mTab.getTableName().equals(MPayment.Table_Name) 
			|| !mField.getColumnName().equals(MPayment.COLUMNNAME_DocAction))
			return null;
		
		Boolean isOnline = mTab.getValueAsBoolean(MPayment.COLUMNNAME_IsOnline);
		String tenderType = (String) mTab.getValue(MPayment.COLUMNNAME_TenderType);
		
		
		MPayment payment = new MPayment(ctx, mTab.getRecord_ID(), null);
		if (!payment.setPaymentProcessor() || !payment.isReceipt())
		{
			// There is no payment processor or the payment is not a receipt
			payment = null;
			return null;  
		}
		
		if (isOnline && MPayment.TENDERTYPE_CreditCard.equals(tenderType) 
				&& (MPayment.DOCACTION_Void.equals(value)
				 || MPayment.DOCACTION_Reverse_Correct.equals(value)))
		{
			ADialog.warn(WindowNo, null, "Credit/Debit Card Process.  Follow prompts on the terminal.");
		}
		else if (isOnline && MPayment.DOCACTION_Complete.equals(value))
		{
			ADialog.warn(WindowNo, null, "This payment uses a payment processor. Use the 'Online Process' button to Complete the payment.");
		}
		
		return null;
	}
}
