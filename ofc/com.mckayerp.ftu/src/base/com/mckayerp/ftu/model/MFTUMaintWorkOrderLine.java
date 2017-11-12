package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MFTUMaintWorkOrderLine extends X_FTU_MaintWorkOrderLine {

	public MFTUMaintWorkOrderLine(Properties ctx,
			int FTU_MaintWorkOrderLine_ID, String trxName) {
		super(ctx, FTU_MaintWorkOrderLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUMaintWorkOrderLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
