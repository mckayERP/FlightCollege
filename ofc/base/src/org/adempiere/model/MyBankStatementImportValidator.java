package org.adempiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.adempiere.process.ImportProcess;
import org.compiere.model.MBankDepositLine;
import org.compiere.model.MBankStatement;
import org.compiere.model.MTable;
import org.compiere.model.X_I_BankStatement;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MyBankStatementImportValidator implements ImportValidator {

	public static final String ImportTableName = X_I_BankStatement.Table_Name;
	public static final int Table_ID = X_I_BankStatement.Table_ID;

	/**	Logger							*/
	protected CLogger			log = CLogger.getCLogger (getClass());

	
	int no = 0;

	/** BankStatement Model				*/
	private X_I_BankStatement p_imp = null;
	/**	Import table		*/
	private int				p_AD_Table_ID;
	private int				p_AD_ImpVal_Rule_ID = 0;
	private Properties		p_ctx;
	private ProcessInfo 	p_Info;
	private int				p_Timing;
	private String			p_trxName;
	private String			p_SetClause;
	private String			p_WhereClause;

	@Override
	public void validate(ImportProcess process, Object importModel,
			Object targetModel, int timing) {

		if (importModel != null)
			p_imp = (X_I_BankStatement)importModel;
		else
			p_imp = null;

		p_AD_Table_ID = MTable.getTable_ID("I_BankStatement");
		p_ctx = process.getCtx();
		p_Info = process.getProcessInfo();
		p_trxName = process.get_TrxName();
		p_Timing = timing;
		p_SetClause = "";
		p_WhereClause = "";
		
		applyValidatorRules();
				
 		if (timing == TIMING_AFTER_IMPORT && targetModel instanceof MBankStatement)
 		{

 		}
 		else if (timing == TIMING_AFTER_IMPORT && targetModel instanceof MBankDepositLine)
 		{

 		}
 		
	}
	
	private void applyValidatorRules() {
		
		//  TODO: add a seqno and order by clause
		//  Find the set of applicable rules and apply each of them
		StringBuffer sql = new StringBuffer (
				"SELECT IVR.AD_ImpVal_Rule_ID "
				+ "FROM "
				+"	AD_ImpValidatorRules IVRS "
				+"	JOIN AD_ImpVal_Rule IVR "
				+"	ON (IVRS.AD_ImpValidatorRules_ID=IVR.AD_ImpValidatorRules_ID) "
				+"WHERE "
				+"	IVRS.AD_Client_ID = ? "
				+"	AND IVRS.AD_TABLE_ID = ? "
				+"  AND IVR.ValidatorTiming = ?"
				+"	AND IVRS.IsActive = 'Y' "
				+"	AND IVR.IsActive = 'Y'");
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), p_trxName);
			DB.setParameter(pstmt, 1, p_Info.getAD_Client_ID());
			DB.setParameter(pstmt, 2, p_AD_Table_ID);
			DB.setParameter(pstmt, 3, String.valueOf(p_Timing));
			rs = pstmt.executeQuery();

			while (rs.next())
			{	
				p_AD_ImpVal_Rule_ID = rs.getInt("AD_ImpVal_Rule_ID");
				
				//  Apply each rule in order
				applyRule();
			}
			DB.commit(true, p_trxName);
			DB.close(rs, pstmt);
		}
		catch (SQLException e)
		{
			log.severe(e.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
			//	Set Error to indicator to not imported
		}
	}
	
	private void applyRule(){ 

		setConditions();
		setSetClause();
		
		if (p_SetClause == null || p_SetClause.length() == 0)
			return;
		
		StringBuffer sql = new StringBuffer (
				"UPDATE I_BankStatement ").append(p_SetClause);
		
		sql.append("WHERE ");
		
		// Add conditions
		if (p_WhereClause != null && p_WhereClause.length() > 0)
			sql.append(p_WhereClause).append(" AND ");
		
		// Add system conditions
		sql.append("(I_IsImported!='Y' OR I_IsImported IS NULL) ")
			.append(" AND AD_Client_ID=" + p_Info.getAD_Client_ID());
		
		try
		{
			log.fine("Applying rule: " + sql.toString());
			int no = DB.executeUpdateEx(sql.toString(), p_trxName);
			log.fine("Rule affected " + no + " lines.");
		}
		catch (DBException e)
		{
			log.severe(e.toString());
		}
		
	}
	
	private void setConditions(){
		// Get the conditions
		
		p_WhereClause = "";
		
		StringBuffer sql = new StringBuffer (
				"SELECT AndOr, LeftBracket, AD_Column.columnname, AD_Reference.Name, Operator, Value1, Value2, rightbracket "
					+ "FROM "
					+ "	AD_ImpValRule_Cond IVC "
					+ "	JOIN AD_Column ON (IVC.AD_COLUMN_ID = AD_Column.AD_Column_ID) "
					+ "	JOIN AD_Reference ON (AD_Column.AD_Reference_ID = AD_Reference.AD_Reference_ID)"
					+ "WHERE "
					+ "	IVC.AD_Client_ID = ? "
					+ "	AND IVC.AD_ImpVal_Rule_ID = ? "
					+ "	AND IVC.IsActive = 'Y' "
					+ "ORDER BY IVC.SeqNo");
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), p_trxName);
			DB.setParameter(pstmt, 1, p_Info.getAD_Client_ID());
			DB.setParameter(pstmt, 2, p_AD_ImpVal_Rule_ID);
			rs = pstmt.executeQuery();

			int conditionNo = 0;
			while (rs.next())
			{	
				conditionNo++;
				
				if (rs.getString("AndOr") != null)
				{
					if (rs.getString("AndOr").equals("A"))
						p_WhereClause += " AND ";
					else
						p_WhereClause += " OR ";
				}
				else if (conditionNo > 1)
				{
					log.severe(Msg.getMsg(Env.getCtx(), "ImpVal-missingAndOr"));
				}
				if (rs.getString("LeftBracket") != null)
					p_WhereClause += rs.getString("LeftBracket"); // Left brackets

				if (isFieldString(rs.getString("name")))
				{
					//  If the field is a string, trim any spaces from it
					p_WhereClause += "trim(" + rs.getString("columnname") + ")";
				}
				else
					p_WhereClause += rs.getString("columnname");
				
				//  Create the condition statement.  There are a number of special cases where the operator changes
				//  depending on the values used.  
				//
				//  null  =>  equals (=) becomes "is" and != becomes "is not"
				//  Tilde (~) becomes "like"
				//  >-< becomes "between"
				
				//  Handle the null case - value1 is null and value2 could have any value
				String value1 = "";
				String value2 = "";
				String operator = rs.getString("Operator");
				if (rs.getString("Value1") == null)
				{
					value1 = "null";
				}
				else
					value1 = rs.getString("Value1");
				
				if (rs.getString("Value2") == null)
				{
					value2 = "null";
				}
					value2 = rs.getString("Value2");
				
				if (operator.equals("=") && value1.equals("null"))
				{
						p_WhereClause += " IS ";
				}
				else if (operator.equals("!=") && value1.equals("null"))
				{
					p_WhereClause += " IS NOT ";
				}
				else if (operator.equals("~"))
				{
					p_WhereClause += " LIKE ";
				}
				else if (operator.equals(">-<"))  // Between
				{
					p_WhereClause += " BETWEEN ";
				}
				else
				{
					p_WhereClause += " " + operator;
				}

				//  Add value1
				if (value1.equals("null"))
				{
					p_WhereClause += value1;
				}
				else if (value1.equals(""))
				{
					if (isString(rs.getString("name")))
					{
						p_WhereClause += "\'\'";
					}
					else
						p_WhereClause += "null";  // Will always result in false					
				}
				else if (isString(rs.getString("name")))
				{
					p_WhereClause += " trim(\'" + value1 + "\')";
				}
				else 
					p_WhereClause +=  value1;

				//  Add value2 only if using the between
				if (operator.equals(">-<"))
				{
					if (value2.equals("null"))
					{
						p_WhereClause += " AND " + value2;  // Will always result in false
					}
					else if (value2.equals(""))
					{
						if (isString(rs.getString("name")))
						{
							p_WhereClause += " AND \'\'";
						}
						else
							p_WhereClause += " AND null";  // Will always result in false					
					}
					else if (isString(rs.getString("name")))
					{
						p_WhereClause += " AND trim(\'" + value1 + "\')";
					}
					else 
						p_WhereClause += " AND " + value1;
				}
				
				if (rs.getString("RightBracket") != null)
					p_WhereClause += rs.getString("RightBracket");				
			}
			DB.close(rs, pstmt);
		}
		catch (SQLException e)
		{
			log.severe(e.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		log.fine("Condition where: " + p_WhereClause);
	}
	
	private void setSetClause() {
		// Get the set clause from the actions
		// The clause should have the form "Set ..."
		
		p_SetClause = "";
		
		StringBuffer setClause = new StringBuffer("");
		
		StringBuffer sql = new StringBuffer (
				"SELECT  c1.columnname as Target, R1.name as TargetType, c2.columnname as Source, R2.name as SourceType, IVA.newvalue " 
				+ "FROM "
				+ "AD_ImpValRule_Action IVA " 
				+ "JOIN AD_Column C1 ON (IVA.AD_COLUMN_ID = C1.AD_Column_ID) "
				+ "Left Outer JOIN AD_Column C2 ON (CAST(coalesce(IVA.AD_COLUMN_FROM, '0') AS integer) = C2.AD_Column_ID) "
				+ "JOIN AD_Reference R1 ON (C1.AD_Reference_ID = R1.AD_Reference_ID) "
				+ "Left Outer JOIN AD_Reference R2 ON (C2.AD_Reference_ID = R2.AD_Reference_ID) "
				+ "WHERE "
					+ "	IVA.AD_Client_ID = ? "
					+ "	AND IVA.AD_ImpVal_Rule_ID = ? "
					+ "	AND IVA.IsActive = 'Y' "
					+ "ORDER BY IVA.SeqNo");
		
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), p_trxName);
			DB.setParameter(pstmt, 1, p_Info.getAD_Client_ID());
			DB.setParameter(pstmt, 2, p_AD_ImpVal_Rule_ID);
			rs = pstmt.executeQuery();
			
			int no = 0;

			while (rs.next())
			{	
				String target = rs.getString("Target");
				String newvalue = rs.getString("NewValue");
				String source = rs.getString("Source");
				String targetType = rs.getString("TargetType");
				String sourceType = rs.getString("SourceType");
				
				if (target == null || target.length() == 0)
					break; // there is no target

				if (no++ == 0)
					setClause.append("SET ");
				else
					setClause.append(", ");
				
				setClause.append(target + "=");

				if (source == null || source.length() == 0)
				{
					// Use the value, even if null
					if (newvalue == null || newvalue.length() == 0)
					{
						if (isString(targetType))
						{
							setClause.append("\'\'");		
						}
						else
							setClause.append("null");
					}
					else if (newvalue.startsWith("@SQL="))
					{
						/**
						 *  SQL Statement (copied from GridField.java)
						 *  Here we don't execute the sql but add it to our statement UPDATE n SET target=[SQL] WHERE ...
						 *  The statement doesn't have to be fully formed SQL statement but should fit in as part of the statement.
						 *  This allows sql functions to be called to process data, such as trim().
						 */	
						String tempvalue = newvalue.substring(5);			//	w/o tag
						tempvalue = Env.parseContext(p_ctx, 0, tempvalue, false, false);	//	replace variables
						if (tempvalue.equals(""))
						{
							log.log(Level.WARNING, "(newvalue) - SQL variable parse failed: "
								+ newvalue);
						}
						else
						{
							newvalue = tempvalue;
						}
						if (newvalue != null && newvalue.length() > 0)
						{
							log.fine("[SQL] newvalue =" + newvalue);
							setClause.append(newvalue);
						}
					}	//	SQL Statement
					else if (isString(targetType))
					{
						setClause.append("\'").append(newvalue).append("\'");		
					}
					else
						setClause.append(newvalue);		
				}
				else if (targetType.equals(sourceType))
				{
					setClause.append(source);
				}
				else if (!(isString(targetType) ^ isString(sourceType)))
				{
					// Types are different but may work.
					setClause.append(source);					
				}
				else
				{
					log.warning("Can't resolve or match new value or source with the target. New value: " + newvalue + 
							" Source: " + source + " Source is String?: " + isString(sourceType) + 
							" Target: " + target + " Target is String?: " + isString(targetType));
				}
			}
			DB.close(rs, pstmt);
		}
		catch (SQLException e)
		{
			log.severe(e.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (setClause != null && setClause.length() > 0)
		{
			setClause.append(" ");		
			p_SetClause = setClause.toString();
		}
		
		log.fine("Action set: " + p_SetClause + " (" + p_SetClause.length() + ")");
		
	}
	
	/** 
	 * Test if the value can be treated as a string ie: trim(value)
	 * @param type
	 * @return
	 */
	private Boolean isString(String type) {
		return type.equals("String")
		|| type.equals("Yes-No")
		|| type.equals("Text")
	//	|| type.equals("Date")
	//	|| type.equals("Date+Time")
	//	|| type.equals("Time")
		|| type.equals("FilePath")
		|| type.equals("FileName")
		|| type.equals("Printer Name");
	}

	/**
	 * Test if the field can be treated as a string ie: trim(field)
	 * @param type
	 * @return true if its a string type
	 */
	private Boolean isFieldString(String type) {
		return type.equals("String")
		|| type.equals("Text")
		|| type.equals("FilePath")
		|| type.equals("FileName")
		|| type.equals("Printer Name");
	}

}
