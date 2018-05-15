/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package com.mckayerp.ftu.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.engine.IDocumentLine;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoLog;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.eevolution.service.dsl.ProcessBuilder;

import com.mckayerp.ftu.model.I_FTU_MaintWOResultLine;
import com.mckayerp.ftu.model.X_FTU_MaintWOResult;
import com.mckayerp.model.MCTComponent;

/** Generated Model for FTU_MaintWOResult
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class MFTUMaintWOResult extends X_FTU_MaintWOResult implements DocAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 20170423L;

    /** Standard Constructor */
    public MFTUMaintWOResult (Properties ctx, int FTU_MaintWOResult_ID, String trxName)
    {
      super (ctx, FTU_MaintWOResult_ID, trxName);
    }

    /** Load Constructor */
    public MFTUMaintWOResult (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName() + get_ID() +"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF

	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;
	private MFTUMaintWOResultLine[] m_lines;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
	//	setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateDoc(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		//
		
		// Process the result lines and details - requery and unprocessed only
		MFTUMaintWOResultLine[] lines = (MFTUMaintWOResultLine[]) this.getLines(true, true);
		for (MFTUMaintWOResultLine line : lines)
		{
			
			ProcessInfo processInfo = ProcessBuilder.create(this.getCtx())
			.process(com.mckayerp.ftu.process.FTU_MaintWOResultLine_Process.class)
			.withTitle("Process Maintenance WO Result Line")
			.withRecordId(MFTUMaintWOResultLine.Table_ID, line.getFTU_MaintWOResultLine_ID())
			.execute();
			
			if (processInfo.isError())
			{
				if (processInfo.getLogList() != null)
				{
					for (ProcessInfoLog log : processInfo.getLogList())
					{
						m_processMsg += log.getP_Msg() + "\n";
					}
				}
				else
				{
					m_processMsg = "Problem processing result lines."; // TODO translate
				}
				return DocAction.STATUS_Invalid;
			}			
		}

		// Check the component/aircraft status for new defects caused by the detail lines
		// These could be parts uninstalled but not reinstalled in the aircraft.
		// As all the detail lines are processed at this point, the missing parts do create
		// a valid defect.  For these to be considered "real" defects as opposed to 
		// administrative defects related to incorrect data, there needs to have been at 
		// least one "installed" component in the BOM location.  Administrative defects
		// exist when the component BOM is first created but not populated.
		// Also only concerned with aircraft, not uninstalled components.
		
		// Find the aircraft from the work order
		MFTUAircraft ac = null;
		
		MFTUMaintWorkOrder mwo = (MFTUMaintWorkOrder) this.getFTU_MaintWorkOrder();
		if (mwo != null && mwo.getFTU_Aircraft_ID() > 0)
		{
			ac = (MFTUAircraft) mwo.getFTU_Aircraft();
		}
		else
		{
			// The aircraft wasn't identified specifically, try the WO Result parent component
			if (getParentComponent_ID() > 0)
			{
				ac = MFTUAircraft.getByCT_Component_ID(getCtx(), getParentComponent_ID(), get_TrxName());
				if (ac == null)
				{
					// Try the root of the parent component
					MCTComponent parentComp = (MCTComponent) getParentComponent();
					ac = MFTUAircraft.getByCT_Component_ID(getCtx(), parentComp.getRoot_Component_ID(), get_TrxName());
				}
			}
		}
		
		if (ac != null)
		{
			boolean createDefects = true;
			ac.checkComponentBOM(getFTU_MaintWOResult_ID(), createDefects);
		}
		

		// See if we are done.  Some lines may have added new lines which need to be processed.
		lines = (MFTUMaintWOResultLine[]) this.getLines(true, true);
		if (lines != null && lines.length > 0)
		{
			m_processMsg = "Not all lines have been completed/processed. New lines may have been added. Please check.";
			return DocAction.STATUS_InProgress;
		}
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	Set Definitive Document No
		setDefiniteDocumentNo();

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setDateDoc(new Timestamp(System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()) {
			String value = null;
			int index = p_info.getColumnIndex("C_DocType_ID");
			if (index == -1)
				index = p_info.getColumnIndex("C_DocTypeTarget_ID");
			if (index != -1)		//	get based on Doc Type (might return null)
				value = DB.getDocumentNo(get_ValueAsInt(index), get_TrxName(), true);
			if (value != null) {
				setDocumentNo(value);
			}
		}
	}

	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		return closeIt();
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());

		//	Close Not delivered Qty
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		setProcessed(false);
		if (reverseCorrectIt())
			return true;
		return false;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
	//	sb.append(": ")
	//		.append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
	//		.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary

	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
	//	return getSalesRep_ID();
		return 0;
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return null;	//getTotalLines();
	}	//	getApprovalAmt
	
	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
	//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
	//	return pl.getC_Currency_ID();
		return 0;
	}	//	getC_Currency_ID

    @Override
    public String toString()
    {
      StringBuffer sb = new StringBuffer ("MFTUMaintWOResult[")
        .append(getSummary()).append("]");
      return sb.toString();
    }


	public IDocumentLine[] getLines() {
		return getLines(false, false);
	}

    public IDocumentLine[] getLines(boolean requery, boolean unprocessedOnly) {
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		String where = I_FTU_MaintWOResultLine.COLUMNNAME_FTU_MaintWOResult_ID + "=?";
		if (unprocessedOnly)
			where += " AND " + I_FTU_MaintWOResultLine.COLUMNNAME_Processed + "!='Y'";
		List<MFTUMaintWOResultLine> list = new Query(getCtx(), I_FTU_MaintWOResultLine.Table_Name, where, get_TrxName())
		.setParameters(getFTU_MaintWOResult_ID())
		.setOrderBy(MFTUMaintWOResultLine.COLUMNNAME_Line)
		.list();
		//
		m_lines = new MFTUMaintWOResultLine[list.size()];
		list.toArray(m_lines);
		return m_lines;
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
		if (this.getFTU_MaintWorkOrder_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "FTU_MaintWorkOrder"));
			return false;
		}

		if (this.getFTU_MaintWorkOrder().getCT_Component_ID() == 0)
		{
			// Shouldn't happen. Caught by MFTUMaintWorkOrder beforesave().  Added in case to catch NPE below
			log.saveError("FillMandatory", Msg.parseTranslation(getCtx(), "@FTU_MaintWorkOrder@ @CT_Component_ID@ == 0"));
			return false;
		}

		if (newRecord)
		{
			MCTComponent comp = (MCTComponent) this.getFTU_MaintWorkOrder().getCT_Component();
			this.setParentComponent_ID(this.getFTU_MaintWorkOrder().getCT_Component_ID());
			this.setCT_ComponentLifeAtAction(comp.getLifeUsed());
			this.setLifeUsageUOM_ID(comp.getLifeUsageUOM_ID());
			this.setC_BPartner_ID(this.getFTU_MaintWorkOrder().getC_BPartner_ID());
			this.setC_BPartner_Location_ID(this.getFTU_MaintWorkOrder().getC_BPartner_Location_ID());
			this.setDescription(this.getFTU_MaintWorkOrder().getDescription());
		}
		
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

}