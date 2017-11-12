package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MColumn;
import org.compiere.model.MProduct;
import org.compiere.util.Env;

public class MFTUComponentBOMLine extends X_FTU_ComponentBOMLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6697382509021814829L;

	public MFTUComponentBOMLine(Properties ctx, int M_ComponentBOMLine_ID,
			String trxName) {
		super(ctx, M_ComponentBOMLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUComponentBOMLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if pre-save operation was success
	 *	@return if save was a success
	 */
	protected boolean beforeSave (boolean newRecord, boolean success) {

		return success;
	}

}
