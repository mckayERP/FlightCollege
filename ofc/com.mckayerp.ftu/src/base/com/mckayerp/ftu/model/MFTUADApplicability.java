package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

import com.mckayerp.ftu.model.X_FTU_ADApplicability;

public class MFTUADApplicability extends X_FTU_ADApplicability {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4063233160403052609L;

	public MFTUADApplicability(Properties ctx, int FTU_ADApplicability_ID,
			String trxName) {
		super(ctx, FTU_ADApplicability_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUADApplicability(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns all ADApplication records, including inactive ones.
	 * @param ctx
	 * @param ftu_adApplication_id
	 * @param trxName
	 * @return
	 */
	public static List<MFTUADApplicability> getByADApplication(Properties ctx,
			int ftu_adApplication_id, String trxName) {
		
		String where = MFTUADApplicability.COLUMNNAME_FTU_ADApplication_ID + "=?";
		
		return new Query(ctx, MFTUADApplicability.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(false)
					.setParameters(ftu_adApplication_id)
					.list();
		
	}

	public static MFTUADApplicability getByADApplicationAndComponent(
			Properties ctx, int ftu_adApplication_id, int ct_component_id,
			String trxName) {

		String where = MFTUADApplicability.COLUMNNAME_FTU_ADApplication_ID + "=?"
				+ " AND " + MFTUADApplicability.COLUMNNAME_CT_Component_ID + "=?";
		
		return new Query(ctx, MFTUADApplicability.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(false)
					.setParameters(ftu_adApplication_id, ct_component_id)
					.firstOnly();  // There should be only one.
		
	}

}
