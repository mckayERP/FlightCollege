/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *																			  *
 *****************************************************************************/
package com.mckayerp.ftu.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocator;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;



/**
 *	Receive invoice products in batch
 *  @author mckayERP
 */
public class ReceiveInvoicedProducts extends SvrProcess {
	
	private Properties m_ctx = null;
	private int m_product_id;
	private int c_bpartner_id;
	private MInOut m_inout;
	private int m_locator_id;
	@Override
	protected String doIt() throws Exception {
		
		boolean matched = false;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT lin.C_InvoiceLine_ID "
				+ "FROM C_Invoice hdr"
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_InvoiceLine lin ON (hdr.C_Invoice_ID=lin.C_Invoice_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_UOM uom ON (p.C_UOM_ID = uom.C_UOM_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID=dt.C_DocType_ID AND dt.DocBaseType IN ('API','APC'))"
				+ " FULL JOIN M_MatchInv mi ON (lin.C_InvoiceLine_ID=mi.C_InvoiceLine_ID) "
				+ "WHERE hdr.DocStatus IN ('CO','CL')"
				+ " AND lin.AD_Client_ID=?"
				+ " AND bp.C_BPartner_ID=?"
				+ " AND p.M_Product_ID=?"
				+ " GROUP BY lin.C_InvoiceLine_ID, uom.stdprecision " //JAVIER
				// Very small values are equivalent to zero.  Round to the product standard precision.
				+ "HAVING round(lin.QtyInvoiced-SUM(NVL(mi.Qty,0)),uom.stdprecision)"
				+ (matched ? "=0" : "<>0"));

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.prepareStatement(sql.toString(), get_TrxName());
			stmt.setInt(1, Env.getAD_Client_ID(getCtx()));
			stmt.setInt(2, c_bpartner_id);
			stmt.setInt(3, m_product_id);
			rs = stmt.executeQuery();
			while (rs.next())
			{
				
				int c_invoiceLine_id = rs.getInt(1);
				MInvoiceLine line = new MInvoiceLine(getCtx(), c_invoiceLine_id, get_TrxName());
				MInvoice invoice = (MInvoice) line.getC_Invoice();
				createLine(invoice, line);
				
				m_inout.processIt(DocAction.ACTION_Complete);
				
				m_inout = null;
			}
		}
		catch (SQLException e)
		{
			log.fine(e.getLocalizedMessage());
			return "@Error@" + e.getMessage();
		}
		finally
		{
			
			DB.close(rs, stmt);
			rs = null;
			stmt = null;
			
		}
		
		return "";
	}
	
	/**
	 * Create Shipment/Receipt header
	 * @param invoice
	 * @return Shipment/Receipt header
	 */
	private MInOut getCreateHeader(MInvoice invoice)
	{
		if (m_inout != null)
			return m_inout;
		
		int warehouse_id = MLocator.get(getCtx(), m_locator_id).getM_Warehouse_ID(); 
		m_inout = new MInOut (invoice, 0, invoice.getDateInvoiced(), warehouse_id);
		m_inout.saveEx();
		return m_inout;
	}
	
	/**
	 * Create shipment/receipt line
	 * @param invoice
	 * @param invoiceLine
	 * @return shipment/receipt line
	 */
	private MInOutLine createLine(MInvoice invoice, MInvoiceLine invoiceLine)
	{
		BigDecimal qtyMatched = invoiceLine.getMatchedQty();
		BigDecimal qtyInvoiced = invoiceLine.getQtyInvoiced();
		BigDecimal qtyNotMatched = qtyInvoiced.subtract(qtyMatched);
		// If is fully matched don't create anything
		if (qtyNotMatched.signum() == 0)
		{
			return null;
		}
		MInOut inout = getCreateHeader(invoice);
		MInOutLine sLine = new MInOutLine(inout);
		sLine.setInvoiceLine(invoiceLine, m_locator_id,	//	Locator 
			invoice.isSOTrx() ? qtyNotMatched : Env.ZERO);
		sLine.setQtyEntered(qtyNotMatched);
		sLine.setMovementQty(qtyNotMatched);
		if (invoice.isCreditMemo())
		{
			sLine.setQtyEntered(sLine.getQtyEntered().negate());
			sLine.setMovementQty(sLine.getMovementQty().negate());
		}
		sLine.saveEx();
		//
		invoiceLine.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
		invoiceLine.saveEx();
		//
		return sLine;
	}

	@Override
	protected void prepare() {
		m_product_id = getParameterAsInt("M_Product_ID");
		c_bpartner_id = getParameterAsInt("Vendor_ID");
		m_locator_id = getParameterAsInt("M_Locator_ID");
	}

}
