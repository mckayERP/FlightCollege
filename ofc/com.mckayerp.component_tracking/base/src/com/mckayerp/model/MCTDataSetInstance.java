package com.mckayerp.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Msg;

public class MCTDataSetInstance extends X_CT_DataSetInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2812006982886381021L;

	public MCTDataSetInstance(Properties ctx, int ct_dataSetInstance_id, 
			String trxName) {
		super(ctx, ct_dataSetInstance_id, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTDataSetInstance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	private static CLogger		s_log = CLogger.getCLogger (MAttributeSetInstance.class);

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
		if (this.getCT_DataSet_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "CT_DataSet_ID"));
			return false;
		}

		if (this.getCT_Component_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "CT_Component_ID"));
			return false;
		}

		if (this.getDateRecorded() == null)
		{
			this.setDateRecorded(new Timestamp(System.currentTimeMillis()));
		}
		
		String description = this.getCT_DataSet().getValue() 
				+ " " + this.getDateRecorded() 
				+ " " + this.getCT_Component().getM_Product().getValue();
		
		if (this.getCT_Component().getM_AttributeSetInstance_ID() > 0)
		{
			description += "-" + this.getCT_Component().getM_AttributeSetInstance().getDescription();
		}
		
		setDescription(description);

		return true;
	}	//	beforeSave

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{		
		return success;
	}	//	afterSave

	public List<MCTDataInstance> getData() {
		
		String where = MCTDataInstance.COLUMNNAME_CT_DataSetInstance_ID + "=?";
		
		return new Query(getCtx(), MCTDataInstance.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(getCT_DataSetInstance_ID())
					.list();		
	}

	public MCTDataInstance getDataInstance(String key) {
		
		String where = MCTDataInstance.COLUMNNAME_CT_DataSetInstance_ID + "=?"
				+ " AND " + MCTDataInstance.COLUMNNAME_CT_DataElement_ID + "="
				+ " (SELECT " + MCTDataElement.COLUMNNAME_CT_DataElement_ID
				+ " FROM " + MCTDataElement.Table_Name 
				+ " WHERE " + MCTDataElement.COLUMNNAME_Value + "=?"
				+ " AND " + MCTDataElement.COLUMNNAME_CT_DataSet_ID + "=?)";
		
		return new Query(getCtx(), MCTDataInstance.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(getCT_DataSetInstance_ID(), key, getCT_DataSet_ID())
					.firstOnly();

	}
}
