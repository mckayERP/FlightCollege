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
package org.compiere.process;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MTable;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Login;



/**
 *	Try to complete Documents
 * 	@author 	Angelo Dabala' Nectosoft S.p.a.
 * 	@version 	$Id: CompleteDocumentsBatch.java,v 1.0 2008/07/25 00:51:01 Angelo Dabala' Exp $
 */
public class CompleteDocumentsBatch extends SvrProcess {
	/**	Client to process		*/
	private int				m_AD_Client_ID = 0;
	/**	Table to process	*/
	private int				m_AD_Table_ID = 0;
	/** Date Range				    	*/
	private Timestamp		m_DateAcct_from = null;
	private Timestamp		m_DateAcct_to = null;
	/** BPartner                        	    */
	private int 			m_C_BPartner_ID = 0;
	/** Is SOTrx					*/
	private boolean			m_isSOTrx = true;
	
	private Properties m_ctx = null;
	@Override
	protected String doIt() throws Exception {
		MTable m_table = new MTable(m_ctx, m_AD_Table_ID, get_TrxName());


		Class<?> clazz = MTable.getClass(m_table.getTableName());
		if (clazz == null)
			return "No suitable Class found for "+m_table.getTableName();

		boolean okclass = false; 
		for ( Class<?> cz : clazz.getInterfaces() )
		{
			if(cz.equals(DocAction.class))
				okclass = true;
		}
		if(!okclass)
			return "Class does not implements DocAction "+clazz.getName();

		
		DocAction action = null;
        Constructor<?> constructor = null;

        try	
        {
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, ResultSet.class, String.class});
        } 
        catch (Exception e) {
			log.log(Level.SEVERE, "(id) - Table=" + m_table.getTableName() + ",Class=" + clazz, e);
	        return e.toString();
        }

		boolean hasBP = (m_table.getColumn("C_BPartner_ID") != null);
		boolean hasSO = (m_table.getColumn("IsSOTrx") != null);
		boolean hasDA = (m_table.getColumn("DateAcct") != null);

		StringBuffer sql = new StringBuffer ("SELECT * FROM ").append(m_table.getTableName())
		.append(" WHERE AD_Client_ID=?")	// 1
		.append(" AND Processing='N' AND Posted='N' AND IsActive='Y'")
		.append(" AND DocStatus IN ('DR','IN')");	// 2
		if(m_C_BPartner_ID > 0 && hasBP)
			sql.append(" AND C_BPartner_ID=?");	// 3
		if(hasSO)
			sql.append(" AND IsSOTrx=?");	// 4(3)
		if (m_DateAcct_from != null && m_DateAcct_to != null && hasDA)
			sql.append (" AND DATEACCT BETWEEN ? AND ?");
		int count = 0;
		int countError = 0;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;

			pstmt.setInt(index++, m_AD_Client_ID);
			if(m_C_BPartner_ID > 0 && hasBP)
				pstmt.setInt(index++, m_C_BPartner_ID);
			if(hasSO)
				if(m_isSOTrx)
					pstmt.setString(index++, "Y");
				else
					pstmt.setString(index++, "N");
			if(hasDA){
				pstmt.setTimestamp(index++, m_DateAcct_from);
				pstmt.setTimestamp(index++, m_DateAcct_to);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				boolean ok = true;
				try
				{
					action = (DocAction)constructor.newInstance(new Object[] {m_ctx, rs, get_TrxName()});
					if (action != null)
					{
						if(action.getDocStatus().equals(DocumentEngine.STATUS_Invalid))
						{
							ok = action.processIt(DocAction.ACTION_Prepare);
							if (!ok)
								log.warning("CompleteDocumentsBatch - Prepare failed: " + action);
						}
						if(action.getDocStatus().equals(DocumentEngine.STATUS_Drafted)
								||
								action.getDocStatus().equals(DocumentEngine.STATUS_InProgress)
								)
						{
							ok = action.processIt(DocAction.ACTION_Complete);
							if (!ok)
								log.warning("CompleteDocumentsBatch - Complete failed: " + action);
						}
						else
							log.warning("CompleteDocumentsBatch - Invalid Status: " + action.getDocStatus() + " - " + action.getDocumentInfo());
						action.save();
						//
						addLog(action.get_ID(), null, null, action.getDocStatus() + " - " + action.getDocumentInfo());

					}
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, getName() + ": " + m_table.getTableName(), e);
					ok = false;
				}
				if (ok)	count++;
				else countError++;
			}
			rs.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(pstmt);
		}

		return "@Completed@ = " + count + " - @Errors@=" + countError;
	}

	@Override
	protected void prepare() {
		m_ctx = getCtx();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Table_ID"))
				m_AD_Table_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("IsSOTrx"))
				m_isSOTrx = "Y".equals(para[i].getParameter());
			else if (name.equals("C_BPartner_ID"))
			{
				if (!(para[i].getParameter() == null))
					m_C_BPartner_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}
			else if (name.equals("DateAcct"))
			{
				m_DateAcct_from = (Timestamp)para[i].getParameter();
				m_DateAcct_to = (Timestamp)para[i].getParameter_To();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	public static void main(String[] args) {
		if (args.length != 3)
		{
			System.out.println("format : java CompleteDocumentsBatch AD_Client_ID AD_Table_ID IsSOTrx");
			System.out.println("example: java CompleteDocumentsBatch 1000001 318 N");
			System.exit(1);
		}
		//
		Login.initTest (false);
		Integer iAD_Client_ID = new Integer(args[0]); 
		Integer iAD_Table_ID = new Integer(args[1]); 
		
		CompleteDocumentsBatch cdb = new CompleteDocumentsBatch();
		cdb.m_AD_Client_ID = iAD_Client_ID;
		cdb.m_AD_Table_ID = iAD_Table_ID;
		cdb.m_isSOTrx = (args[2].equalsIgnoreCase("Y"));
		cdb.m_ctx = Env.getCtx();
		try
		{
			System.out.println(cdb.doIt());
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		System.exit(0);
	}

}
