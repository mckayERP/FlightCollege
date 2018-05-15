package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MCTDataSet extends X_CT_DataSet {

	public MCTDataSet(Properties ctx, int CT_DataSet_ID, String trxName) {
		super(ctx, CT_DataSet_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTDataSet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTDataSetInstance createNewDataPoint() {
		
		MCTDataSetInstance dataPoint = new MCTDataSetInstance(getCtx(), 0, get_TrxName());
		dataPoint.setCT_DataSet_ID(getCT_DataSet_ID());
		
		return dataPoint;
	}

	public List<MCTDataElement> getCT_DataElements() {
		
		String where = MCTDataElement.COLUMNNAME_CT_DataSet_ID + "=?";

		return new Query(getCtx(), MCTDataElement.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(getCT_DataSet_ID())
					.setOrderBy(MCTDataElement.COLUMNNAME_SeqNo)
					.list();
	}

}
