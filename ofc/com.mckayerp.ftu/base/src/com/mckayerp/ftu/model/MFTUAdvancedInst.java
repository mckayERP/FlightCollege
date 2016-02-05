package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.DB;

public class MFTUAdvancedInst extends X_FTU_Advanced_Inst {

	public MFTUAdvancedInst(Properties ctx, int FTU_Advanced_Inst_ID,
			String trxName) {
		super(ctx, FTU_Advanced_Inst_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUAdvancedInst(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MFTUAdvancedInst getByCourseType(Properties ctx, String flightCourseType, String trxName) {
		String where = MFTUAdvancedInst.COLUMNNAME_FlightCourseType + "=" + DB.TO_STRING(flightCourseType);
		MFTUAdvancedInst ai = new Query(ctx, MFTUAdvancedInst.Table_Name, where, trxName)
									.setClient_ID()
									.setOnlyActiveRecords(true)
									.firstOnly();
		return ai;
	}
	
	public static MProduct getNoShowProduct(Properties ctx, String courseType, String trxName) {
		MFTUAdvancedInst ai = getByCourseType(ctx, courseType, trxName);
		if (ai.isNoShow()) {
			return new MProduct(ctx,ai.getNoShow_Product_ID(),trxName);
		}
		return null;		
	}

	public static MProduct getIntroProduct(Properties ctx, String courseType, String trxName) {
		MFTUAdvancedInst ai = getByCourseType(ctx, courseType, trxName);
		if (ai.isIntro()) {
			return new MProduct(ctx,ai.getIntro_Product_ID(),trxName);
		}
		return null;		
	}

	public static MProduct getAdvancedInstProduct(Properties ctx, String courseType, String trxName) {
		MFTUAdvancedInst ai = getByCourseType(ctx, courseType, trxName);
		if (ai.isAdvanced()) {
			return new MProduct(ctx,ai.getM_Product_ID(),trxName);
		}
		return null;
	}

}
