package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MFTUAircraft extends X_FTU_Aircraft {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5290911219260845457L;

	public MFTUAircraft(Properties ctx, int FTU_Aircraft_ID, String trxName) {
		super(ctx, FTU_Aircraft_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUAircraft(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MFTUAircraft get(Properties ctx, int FTU_Aircraft_ID, String trxName) {
		if (FTU_Aircraft_ID == 0)
			return null;
		return new MFTUAircraft(ctx, FTU_Aircraft_ID, trxName);
	}
}
