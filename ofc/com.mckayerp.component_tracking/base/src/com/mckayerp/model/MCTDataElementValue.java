package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MCTDataElementValue extends X_CT_DataElementValue {

	public MCTDataElementValue(Properties ctx, int CT_DataElementValue_ID,
			String trxName) {
		super(ctx, CT_DataElementValue_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTDataElementValue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
