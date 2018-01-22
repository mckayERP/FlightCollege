package com.mckayerp.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.engine.IDocumentLine;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMovementLine;
import org.compiere.model.Query;
import org.compiere.util.Msg;

import com.mckayerp.ftu.model.MFTUMaintWOResultLine;

public class MCTComponentHistory extends X_CT_ComponentHistory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6181385829859655967L;

	public MCTComponentHistory(Properties ctx, int M_ComponentHistory_ID,
			String trxName) {
		super(ctx, M_ComponentHistory_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTComponentHistory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static MCTComponentHistory getByline(Properties ctx,
			IDocumentLine line, String action, String trxName) {

		String where = "";
		
		if (line instanceof MInOutLine)
		{
			where = MCTComponentHistory.COLUMNNAME_M_InOutLine_ID + "=?";
		}
		else if (line instanceof MInventoryLine)
		{
			where = MCTComponentHistory.COLUMNNAME_M_InventoryLine_ID + "=?";
		}
		else if (line instanceof MMovementLine)
		{
			where = MCTComponentHistory.COLUMNNAME_M_MovementLine_ID + "=?";
		}
		else if (line instanceof MFTUMaintWOResultLine)
		{
			where = MCTComponentHistory.COLUMNNAME_FTU_MaintWOResultLine_ID + "=?";
		}

		where += " AND " + MCTComponentHistory.COLUMNNAME_CT_ComponentActionType + "=?";
		
		return new Query(ctx, MCTComponentHistory.Table_Name, where, trxName)
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

	/**
	 * Get the list of Component History Records related to a component BOM line
	 * @param ctx
	 * @param ct_componentBOM_id
	 * @param trxName
	 * @return A list of the component history records.  Only active
	 * records are returned.
	 */
	public static List<MCTComponentHistory> getByComponentBOMLine(Properties ctx,
			int ct_componentBOM_id, String trxName) {
		
		String where = MCTComponentHistory.COLUMNNAME_CT_ComponentBOMLine_ID + "=?";
		
		return new Query(ctx, MCTComponentHistory.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ct_componentBOM_id)
					.list();

	}
	
	public String toString() {
		
		MCTComponent comp = (MCTComponent) getCT_Component();
		return Msg.parseTranslation(getCtx(), "@CT_ComponentHistory_ID@ " + comp.toString() + " (" + getCT_ComponentHistory_ID() + ")");
		
	}

}
