package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.engine.IDocumentLine;
import org.compiere.model.MInOut;

import com.mckayerp.ftu.model.X_FTU_MaintWOResultLine;

public class MFTUMaintWOResultLine extends X_FTU_MaintWOResultLine implements IDocumentLine {

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Timestamp getMovementDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovementType() {
		// TODO Auto-generated method stub
		return null;
	}

}
