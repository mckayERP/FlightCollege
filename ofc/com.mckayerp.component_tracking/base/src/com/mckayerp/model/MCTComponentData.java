package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MCTComponentData extends X_CT_ComponentData {

	public MCTComponentData(Properties ctx, int CT_ComponentData_ID,
			String trxName) {
		super(ctx, CT_ComponentData_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTComponentData(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
