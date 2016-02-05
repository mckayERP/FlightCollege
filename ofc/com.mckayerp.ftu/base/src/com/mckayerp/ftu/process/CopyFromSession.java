package com.mckayerp.ftu.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 *  Copy from Training Session
 *
 *	@author Mike McKay
 */
public class CopyFromSession extends SvrProcess
{
	private int		m_OFC_Training_ID = 0;
	private Timestamp m_Session_Date;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("OFC_Training_ID"))
				m_OFC_Training_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("Session_Date"))
				m_Session_Date = ((Timestamp)para[i].getParameter());
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		int no = 0;
		int To_OFC_Training_Session_ID = getRecord_ID();
		log.info("From OFC_Training_ID =" + m_OFC_Training_ID + " and Date " + m_Session_Date + " to " + To_OFC_Training_Session_ID);
		if (To_OFC_Training_Session_ID == 0)
			throw new IllegalArgumentException("Target OFC_Training_Session_ID == 0");
		if (m_OFC_Training_ID == 0)
			throw new IllegalArgumentException("Source OFC_Training_Session_ID == 0");
		
		String sql = "SELECT " +
						"ad_client_id, ad_org_id, c_bpartner_id, " +
						"created, createdby, description, " +
						"isactive, ofc_training_record_id, " +
						"ofc_training_session_id, trainingresult, updated, updatedby " +
						"FROM OFC_Training_Record " +
						"WHERE ofc_training_session_id IN (SELECT MAX(OFC_Training_Session_ID) from OFC_Training_Session " +
						"WHERE OFC_Training_ID = ? " +
						"AND TrainingDate = ?)";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			DB.setParameter(pstmt, 1, m_OFC_Training_ID);
			DB.setParameter(pstmt, 2, m_Session_Date);
	    	rs = pstmt.executeQuery();
	    	while(rs.next())
		    {
	    		int ID = DB.getNextID(getCtx(), "OFC_Training_Record", get_TrxName());
	    		StringBuffer insql = new StringBuffer ("INSERT INTO OFC_Training_Record(")
	    							.append("ad_client_id, ad_org_id, c_bpartner_id, ") 
	    							.append("created, createdby, description, ")
	    							.append("isactive, ofc_training_record_id, ")
	    							.append("ofc_training_session_id, trainingresult, ")
	    							.append("updated, updatedby) ")
	    							.append("VALUES (")
    								.append(rs.getInt("ad_client_id")).append(", ")
    								.append(rs.getInt("ad_org_id")).append(", ")
    								.append(rs.getInt("c_bpartner_id")).append(", ")
    								.append("'" + rs.getString("created") + "'").append(", ")
    								.append(rs.getInt("createdby")).append(", ")
    								.append(rs.getString("description")).append(", ")
    								.append("'" + rs.getString("isactive") + "'").append(", ")
    								.append(ID).append(", ")
    								.append(To_OFC_Training_Session_ID).append(", ")
    								.append("'" + rs.getString("trainingresult") + "'").append(", ")
    								.append("'" + rs.getString("updated") + "'").append(", ")
    								.append(rs.getInt("updatedby")).append(")");

	    		try
	    		{
	    			PreparedStatement pstmt2 = null;
	    			pstmt2 = DB.prepareStatement(insql.toString(), get_TrxName());
	    			no = pstmt2.executeUpdate();
	    		}
	    		catch (SQLException e)
	    		{
	    			log.log(Level.SEVERE, insql.toString(), e);
	    			return "Error in insert";
	    		}
		    }
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			return "Error in finding source records.";
		}
		
		//
		return "@Copied@=" + no;
	}	//	doIt

}	//	CopyFromSession
