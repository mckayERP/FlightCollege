package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.DB;

public class MFTUStudent extends X_FTU_Student {

	public MFTUStudent(Properties ctx, int FTU_Student_ID, String trxName) {
		super(ctx, FTU_Student_ID, trxName);
	}

	public MFTUStudent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Test if a business partner is/was an enrolled vocational student at the provided date 
	 * @param ctx
	 * @param c_bpartner_id
	 * @param date
	 * @param trxName
	 * @return
	 */
	public static boolean isVocationalStudent(Properties ctx, int c_bpartner_id, Timestamp date, String trxName)
	{
		if (c_bpartner_id <= 0)
			return false;
		
		String where = MFTUStudent.COLUMNNAME_C_BPartner_ID + "=" + c_bpartner_id
						+ " AND " + MFTUStudent.COLUMNNAME_IsVocational + "='Y'"
						+ " AND ((" + DB.TO_DATE(date) + " BETWEEN "
						+ MFTUStudent.COLUMNNAME_DateEnrolled + " AND "
						+ MFTUStudent.COLUMNNAME_DateDeparted + ")"
						+ " OR (" + DB.TO_DATE(date) + " > " + MFTUStudent.COLUMNNAME_DateEnrolled
						+ " AND " + MFTUStudent.COLUMNNAME_DateDeparted + " is null"
						+ " AND " + MFTUStudent.COLUMNNAME_StudentStatus + "="
						+ DB.TO_STRING(MFTUStudent.STUDENTSTATUS_Enrolled) + "))";
		
		int countEnrollments = new Query(ctx, MFTUStudent.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.count();
		
		return countEnrollments > 0;
		
	}

}
