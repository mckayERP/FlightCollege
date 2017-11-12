package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MFTUMaintWOResultLine extends X_FTU_MaintWOResultLine {

	public MFTUMaintWOResultLine(Properties ctx, int FTU_MaintWOResultLine_ID,
			String trxName) {
		super(ctx, FTU_MaintWOResultLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUMaintWOResultLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
