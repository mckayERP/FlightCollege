package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.engine.IDocumentLine;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMovementLine;
import org.compiere.model.Query;

public class MFTUComponentHistory extends X_FTU_ComponentHistory {

	public MFTUComponentHistory(Properties ctx, int M_ComponentHistory_ID,
			String trxName) {
		super(ctx, M_ComponentHistory_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUComponentHistory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MFTUComponentHistory getByline(Properties ctx,
			IDocumentLine line, String action, String trxName) {

		String where = "";
		
		if (line instanceof MInOutLine)
		{
			where = MFTUComponentHistory.COLUMNNAME_M_InOutLine_ID + "=?";
		}
		else if (line instanceof MInventoryLine)
		{
			where = MFTUComponentHistory.COLUMNNAME_M_InventoryLine_ID + "=?";
		}
		else if (line instanceof MMovementLine)
		{
			where = MFTUComponentHistory.COLUMNNAME_M_MovementLine_ID + "=?";
		}
		else if (line instanceof MFTUMaintWOResultLine)
		{
			where = MFTUComponentHistory.COLUMNNAME_FTU_MaintWOResultLine_ID + "=?";
		}

		where += " AND " + MFTUComponentHistory.COLUMNNAME_FTU_ComponentActionType + "=?";
		
		return new Query(ctx, MFTUComponentHistory.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(line.get_ID(), action)
					.firstOnly();  //  There should be only one entry per line and action
	}

	public void setLine_ID(IDocumentLine line) {

		if (line instanceof MInOutLine)
		{
			setM_InOutLine_ID(line.get_ID());
		}
		else if (line instanceof MInventoryLine)
		{
			setM_InventoryLine_ID(line.get_ID());
		}
		else if (line instanceof MMovementLine)
		{
			setM_MovementLine_ID(line.get_ID());
		}
		else if (line instanceof MFTUMaintWOResultLine)
		{
			setFTU_MaintWOResultLine_ID(line.get_ID());
		}
	}

}
