package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

public class MCTDataInstance extends X_CT_DataInstance {

	public MCTDataInstance(Properties ctx, int CT_DataInstance_ID,
			String trxName) {
		super(ctx, CT_DataInstance_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTDataInstance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MCTDataInstance getOrCreate(Properties ctx,
			int ct_dataSetInstance_id, int ct_dataElement_id, String trxName) {
		
		String where = MCTDataInstance.COLUMNNAME_CT_DataSetInstance_ID + "=?"
				+ " AND " + MCTDataInstance.COLUMNNAME_CT_DataElement_ID + "=?";
		
		MCTDataInstance data = new Query(ctx, MCTDataInstance.Table_Name, where, trxName)
									.setClient_ID()
									.setOnlyActiveRecords(true)
									.setParameters(ct_dataSetInstance_id, ct_dataElement_id)
									.firstOnly();
		
		if (data == null)
		{
			data = new MCTDataInstance(ctx, 0, trxName);
			data.setCT_DataElement_ID(ct_dataElement_id);
			data.setCT_DataSetInstance_ID(ct_dataSetInstance_id);
		}
		return data;
	}

}
