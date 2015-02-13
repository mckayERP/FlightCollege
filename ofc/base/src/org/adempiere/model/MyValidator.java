package org.adempiere.model;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;

public class MyValidator implements ModelValidator {

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAD_Client_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// Register Import Validators
		engine.addImportValidate(MyBankStatementImportValidator.ImportTableName, new MyBankStatementImportValidator());
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
