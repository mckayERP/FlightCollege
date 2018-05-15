package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MCTDataElement extends X_CT_DataElement {

	public MCTDataElement(Properties ctx, int CT_DataElement_ID, String trxName) {
		super(ctx, CT_DataElement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTDataElement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTDataInstance getCTDataInstance(int ct_dataSetInstance_id) {

		String where = MCTDataInstance.COLUMNNAME_CT_DataSetInstance_ID + "=?"
				+ " AND " + MCTDataInstance.COLUMNNAME_CT_DataElement_ID + "=?";

		return new Query(getCtx(), MCTDataInstance.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ct_dataSetInstance_id, getCT_DataElement_ID())
					.firstOnly();
	}

	public MCTDataElementValue[] getDataElementValues() {

		String where = MCTDataElementValue.COLUMNNAME_CT_DataElement_ID + "=?";

		List<MCTDataElementValue> list = new Query(getCtx(), MCTDataElementValue.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(getCT_DataElement_ID())
					.list();
		
		return (MCTDataElementValue[]) list.toArray();
	}

	public Object getDefaultValue() {
		// Get the default value of the data element
		
		// TODO - define defaults as per columns and fields
		
		// Also, allow callouts & processes.
		
		// This is hardcoded.
		
		return null;
	}
	
}
