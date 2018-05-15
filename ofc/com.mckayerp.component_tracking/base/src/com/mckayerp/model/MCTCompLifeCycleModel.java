package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.model.POWrapper;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.Msg;

import com.mckayerp.ftu.model.MFTUMaintRequirement;

public class MCTCompLifeCycleModel extends X_CT_CompLifeCycleModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4215155059419644595L;

	public MCTCompLifeCycleModel(Properties ctx,
			int M_CompLifeCycleModel_ID, String trxName) {
		super(ctx, M_CompLifeCycleModel_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTCompLifeCycleModel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MCTCompLifeCycleModel getByProduct(Properties ctx,
			MProduct product, String trxName) {

		if (product == null)
			return null;
		
		
		// Try to match the product ID first, then the product group
		String where = MCTCompLifeCycleModel.COLUMNNAME_M_Product_ID + "=?";
		
		MCTCompLifeCycleModel model = new Query(ctx, MCTCompLifeCycleModel.Table_Name, where, trxName)
										.setClient_ID()
										.setOnlyActiveRecords(true)
										.setParameters(product.getM_Product_ID())
										.first();
		
		if (model == null )  // Try to match the group
		{
			where = MCTCompLifeCycleModel.COLUMNNAME_M_Product_Group_ID + "=?";
		
			model = new Query(ctx, MCTCompLifeCycleModel.Table_Name, where, trxName)
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.setParameters(product.getM_Product_Group_ID())
							.first();

	
		}
		
		return model;
	}

	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		/** Prevents saving
		log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
		log.saveError("FillMandatory", Msg.getElement(getCtx(), "PriceEntered"));
		/** Issues message
		log.saveWarning(AD_Message, message);
		log.saveInfo (AD_Message, message);
		**/
		
		// Ensure the product or product group are identified
		if (this.getM_Product_ID() <= 0 && this.getM_Product_Group_ID() <= 0)
		{
			log.saveError("FillMandatory", Msg.parseTranslation(getCtx(), "@M_Product_ID@ @OR@ @M_Product_Group_ID@"));
			return false;
		}
		return true;
	}	//	beforeSave

	public List<MFTUMaintRequirement> getFTU_MaintRequirements() {
		
		String where = MFTUMaintRequirement.COLUMNNAME_CT_CompLifeCycleModel_ID + "=?";
		
		return new Query(getCtx(), MFTUMaintRequirement.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(this.getCT_CompLifeCycleModel_ID())
					.list();
	}
}
