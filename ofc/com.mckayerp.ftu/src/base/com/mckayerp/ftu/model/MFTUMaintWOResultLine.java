package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.adempiere.engine.IDocumentLine;
import org.compiere.model.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class MFTUMaintWOResultLine extends X_FTU_MaintWOResultLine implements IDocumentLine, Callout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2566455711773054958L;
	private MFTUMaintWOResult m_parent;

	public MFTUMaintWOResultLine(Properties ctx, int FTU_MaintWOResultLine_ID,
			String trxName) {
		super(ctx, FTU_MaintWOResultLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUMaintWOResultLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MFTUMaintWOResult getParent()
	{
		if (m_parent == null)
			m_parent = new MFTUMaintWOResult (getCtx(), getFTU_MaintWOResult_ID(), get_TrxName());
		return m_parent;
	}	//	getParent


	@Override
	public int getC_DocType_ID() {
		return getParent().getC_DocType_ID();
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public int getM_LocatorTo_ID() {
		return 0;
	}

	@Override
	public int getM_AttributeSetInstanceTo_ID() {
		return 0;
	}

	@Override
	public Timestamp getDateAcct() {
		return getParent().getDateDoc();
	}

	@Override
	public BigDecimal getMovementQty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSOTrx() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getReversalLine_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getPriceActual() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDocumentLine getReversalDocumentLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getM_MPolicyTicket_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setM_MPolicyTicket_ID(int M_MPolicyTicket_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isReversal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getM_Warehouse_ID() {
		
		return this.getFTU_MaintWOResult().getM_Warehouse_ID();
		
	}

	@Override
	public Timestamp getMovementDate() {
		return this.getFTU_MaintWOResult().getDateDoc(); // TODO - add a date field to the result line
	}

	@Override
	public String getMovementType() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MFTUMaintWORLDetail> getDetails() {
		
		String where = MFTUMaintWORLDetail.COLUMNNAME_FTU_MaintWOResultLine_ID + "=?";
		
		return new Query(getCtx(), MFTUMaintWORLDetail.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(this.getFTU_MaintWOResultLine_ID())
					.list();
	}

	@Override
	public String start(Properties ctx, String method, int WindowNo,
			GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String convert(String method, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	/** Get the count of the number of times, the maintenance action has been completed
	 * 
	 * @param ctx
	 * @param ftu_maintNextAction_id
	 * @param trxName
	 * @return the count of times
	 */
	public static int countByNextAction(Properties ctx,
			int ftu_maintNextAction_id, String trxName) {
		
		String where = MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintNextAction_ID + "=?"
				+ " AND " + MFTUMaintWOResultLine.COLUMNNAME_IsMaintReqCompleted + "='Y'"
				+ " AND " + MFTUMaintWOResultLine.COLUMNNAME_Processed + "='Y'";
		
		return new Query(ctx, MFTUMaintWOResultLine.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ftu_maintNextAction_id)
					.count();
	}
}
