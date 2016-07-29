package com.mckayerp.ftu.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MQuery;
import org.compiere.model.MRefList;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Reference;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MFTUDefectLog extends X_FTU_DefectLog implements DocAction, DocOptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5534916727936182301L;
	private String m_processMsg;
	private DocumentEngine m_documentEngine;

	/**	Aircraft Maintenance Defect Entry   */
	public static final String 	DOCTYPE_DefectLog     = "AMC";

	// MFTU Custom Actions
	/** Enter/Record defects = ME */
	public static final String ACTION_Enter = "ME";
	/** Defer = MD */
	public static final String ACTION_Defer = "MD";
	/** Rectify = MR */
	public static final String ACTION_Rectify = "MR";
	
	// MFTU Custom Status
	/** Entered = ME */
	public static final String STATUS_Entered = "ME";
	/** Deferred = MD */
	public static final String STATUS_Deferred = "MD";
	/** Rectified = MR */
	public static final String STATUS_Rectified = "MR";


	public MFTUDefectLog(Properties ctx, int FTU_DefectLog_ID, String trxName) {
		super(ctx, FTU_DefectLog_ID, trxName);
		
		//  New
		if (FTU_DefectLog_ID == 0)
		{
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction (DOCACTION_Enter);
			MDocType types[] = MDocType.getOfDocBaseType(getCtx(), "AMC");
			if (types.length > 0)	//	get first
				setC_DocType_ID(types[0].getC_DocType_ID());
		}

	}

	public MFTUDefectLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public boolean processIt(String action) throws Exception {
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		m_documentEngine = engine;
		return engine.processIt (action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}

	@Override
	public boolean invalidateIt() {
		log.info(toString());
		setDocAction(DOCACTION_Enter);
		return true;
	}

	@Override
	public String prepareIt() {
		log.info(toString());
		return null;
	}

	@Override
	public boolean approveIt() {
		log.info(toString());
		setIsApproved(true);
		return true;
	}

	@Override
	public boolean rejectIt() {
		log.info(toString());
		setIsApproved(false);
		return true;
	}

	@Override
	public String completeIt() {
		log.info(toString());
		return null;
	}

	@Override
	public boolean voidIt() {
		
		// Delete the defect from the Journey Log
		List<MFTUACJourneyLog> jlEntries = MFTUACJourneyLog.getByDefectLogID(getCtx(), this.getFTU_DefectLog_ID(), get_TrxName());
		for (MFTUACJourneyLog jl : jlEntries) {
			jl.delete(true);
		}
		//
		this.setDefect("VOIDED: " + getDefect());
		
		// Set the aircraft status - remove it from service.
		setACStatus(getFTU_Aircraft_ID());

		return true;
	}

	@Override
	public boolean closeIt() {
		log.info(toString());
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		log.info(toString());
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		log.info(toString());
		return false;
	}

	@Override
	public boolean reActivateIt() {
		log.info(toString());
		return false;
	}

	@Override
	public String getSummary() {
		
		MRefList docStatus = new MRefList(getCtx(), 135, getDocStatus());
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo()).append(" ").append(docStatus.getName())
			.append(this.getDefectDate()).append("\n")
			.append(Msg.translate(getCtx(),"Defect")).append("=").append(getDefect());
		//   Related Info
		if (getFTU_Flightsheet_ID() > 0) {
			sb.append("\n").append(Msg.translate(getCtx(), "FlightID")).append(": ").append(getFTU_Flightsheet().getFlightID());
		}
		if (getC_BPartner_ID() > 0) {
			sb.append("\n").append(Msg.translate(getCtx(), "Reported By")).append(": ").append(getC_BPartner().getName());
		}
		sb.append("\n").append(Msg.translate(getCtx(), "DefectType")).append(": ").append(this.getDefectType());
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append("\n").append(getDescription());
		if (this.isDeferred())
			sb.append("\n \n").append(Msg.translate(getCtx(),"Deferred on "))
							.append(getDeferredDate().toString()).append("\n")
							.append(this.getDeferredNote());
		if (this.isRepaired())
			sb.append("\n \n").append(Msg.translate(getCtx(),"Repaired on "))
							.append(getRepairedDate().toString()).append("\n")
							.append(this.getRectification());
		return sb.toString();
	}

	@Override
	public String getDocumentInfo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		MFTUAircraft ac= (MFTUAircraft) getFTU_Aircraft();
		return dt.getName() + " " + getDocumentNo() + ": " + ac.getACRegistration();
	}

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
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
		MDocType docType = (MDocType) this.getC_DocType();
		int AD_PrintFormat_ID = docType.getAD_PrintFormat_ID();
		
		if (AD_PrintFormat_ID == 0)
			return null;
		
		//	Get Format & Data
		MPrintFormat format = MPrintFormat.get (getCtx(), AD_PrintFormat_ID, true);
		//format.setLanguage(language);		//	BP Language if Multi-Lingual
		//	if (!Env.isBaseLanguage(language, DOC_TABLES[type]))
		//format.setTranslationLanguage(language);
		//	query
		MQuery query = new MQuery(this.get_Table_ID());
		query.addRestriction(this.COLUMNNAME_FTU_DefectLog_ID, MQuery.EQUAL, this.getFTU_DefectLog_ID());
		//	log.config( "ReportCtrl.startDocumentPrint - " + format, query + " - " + language.getAD_Language());
		//
		String DocumentNo = "DocPrint";
		if (getDocumentNo() != null && getDocumentNo().length() > 0)
			DocumentNo = getDocumentNo();
		PrintInfo info = new PrintInfo(
				DocumentNo,
				this.get_Table_ID(),
				this.getFTU_DefectLog_ID(),
				0);
		info.setCopies(docType.getDocumentCopies());
		info.setDocumentCopy(false);		//	true prints "Copy" on second
		info.setPrinterName(format.getPrinterName());

		//	Engine
		ReportEngine re = new ReportEngine(getCtx(), format, query, info, get_TrxName());

		return re.getPDF(file);
	}	//	createPDF

	@Override
	public String getProcessMsg() {
		return m_processMsg	;
	}

	@Override
	public int getC_Currency_ID() {
		return Env.getContextAsInt(getCtx(), "C_Currency_ID");
	}

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		return success;		
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
		if (newRecord) {
			//this.setEnteredBy(0);
			this.setIsDeferred(false);
			this.setDeferredDate(null);
			this.setDeferredNote(null);
			//this.setDeferredBy(0);
			this.setIsRepaired(false);
			this.setRepairedDate(null);
			this.setRectification(null);
			//this.setRectifiedBy(0);
			
			if (this.getDefectDate() == null) {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				this.setDefectDate(now);
			}

			MDocType types[] = MDocType.getOfDocBaseType(getCtx(), DOCTYPE_DefectLog);
			if (types.length > 0)	//	get first
				setC_DocType_ID(types[0].getC_DocType_ID());
			else
			{
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@ @C_DocType_ID@"));
				return false;
			}

			return true;
		}
		else if (this.isDeferred() && !this.isRepaired()) {
			if (this.getDeferredNote() == null || this.getDeferredNote().length() == 0) {
				log.saveError("FillMandatory", "A note about the deferral and any operational limits on the aircraft is required.");
			}
			
			if (this.getDeferredDate() == null) {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				this.setDeferredDate(now);
			}
		}
		else if (this.isRepaired()) {
			if (this.getRectification() == null || this.getRectification().length() == 0) {
				log.saveError("FillMandatory", "A note regarding the rectification of this defect is required.");  // TODO Translate
			}

			if (this.getRepairedDate() == null) {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				this.setRepairedDate(now);
			}
		}		
		return true;	
	}

	public boolean rectifyIt() {

		if (m_documentEngine == null)
			m_documentEngine = new DocumentEngine(this, getDocStatus());
		
		if (!m_documentEngine.isValidAction(ACTION_Rectify))
			return false;
		
		Timestamp now = new Timestamp(System.currentTimeMillis());		
		if (getRepairedDate() == null) {
			setRepairedDate(now);
		}
		
		setDocAction(DOCACTION_None);
		setDocStatus(STATUS_Rectified);
		setProcessed(true);
		
		MFTUACJourneyLog jl = new MFTUACJourneyLog(getCtx(), 0, get_TrxName());
		jl.setFTU_Aircraft_ID(getFTU_Aircraft_ID());
		jl.setEntryDate(this.getRepairedDate());
		jl.setIntendedFlight("SNAG Repaired: [" + this.getDocumentNo() + "] " + this.getRectification());
		jl.setFTU_DefectLog_ID(this.getFTU_DefectLog_ID());
		jl.setTotalAirframeTime(MFTUACJourneyLog.getTotalAirframeTime(getCtx(), getFTU_Aircraft_ID(), this.getRepairedDate(), get_TrxName()));
		jl.saveEx();
		
		// Set the aircraft status - remove it from service.
		setACStatus(getFTU_Aircraft_ID());

		return true;
	}

	public boolean deferIt() {

		if (m_documentEngine == null)
			m_documentEngine = new DocumentEngine(this, getDocStatus());

		if (!m_documentEngine.isValidAction(ACTION_Defer))
			return false;
		
		if (this.getDeferredNote() == null || this.getDeferredNote().length() == 0)
			throw new AdempiereException("A note regarding the deferral of this defect is required.");  // TODO Translate
		
		if (this.getDeferredDate() == null) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.setDeferredDate(now);
		}
		this.setDeferredBy(Env.getAD_User_ID(getCtx()));
		this.setDocAction(DOCACTION_Rectify);
		this.setDocStatus(STATUS_Deferred);

		MFTUACJourneyLog jl = new MFTUACJourneyLog(getCtx(), 0, get_TrxName());
		jl.setFTU_Aircraft_ID(getFTU_Aircraft_ID());
		jl.setEntryDate(this.getDeferredDate());
		jl.setIntendedFlight("SNAG Deferred: [" + this.getDocumentNo() + "] " + this.getDeferredNote());
		jl.setFTU_DefectLog_ID(this.getFTU_DefectLog_ID());
		jl.setTotalAirframeTime(MFTUACJourneyLog.getTotalAirframeTime(getCtx(), getFTU_Aircraft_ID(), this.getDeferredDate(), get_TrxName()));
		jl.saveEx();
		
		// Set the aircraft status - remove it from service.
		setACStatus(getFTU_Aircraft_ID());

		return true;
	}

	public boolean enterIt() {

		if (m_documentEngine == null)
			m_documentEngine = new DocumentEngine(this, getDocStatus());

		if (!m_documentEngine.isValidAction(ACTION_Enter))
			return false;

		//	Implicit Approval
		if (!isApproved())
			approveIt();
		
		if (this.getDefectDate() == null) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.setDefectDate(now);
		}

		// Add the defect to the Journey Log
		MFTUACJourneyLog jl = new MFTUACJourneyLog(getCtx(), 0, get_TrxName());
		jl.setFTU_Aircraft_ID(getFTU_Aircraft_ID());
		jl.setEntryDate(this.getDefectDate());
		jl.setIntendedFlight("SNAG: [" + this.getDocumentNo() + "] " + this.getDefect());
		jl.setFTU_DefectLog_ID(this.getFTU_DefectLog_ID());
		jl.setTotalAirframeTime(MFTUACJourneyLog.getTotalAirframeTime(getCtx(), getFTU_Aircraft_ID(), this.getDefectDate(), get_TrxName()));
		jl.saveEx();
		
		this.setTotalAirframeTime(jl.getTotalAirframeTime());
		this.setEnteredBy(Env.getAD_User_ID(getCtx()));
		this.setDocStatus(STATUS_Entered);
		this.setDocAction(DOCACTION_Rectify);
				
		// Set the aircraft status - remove it from service.
		setACStatus(getFTU_Aircraft_ID());

		return true;
	}

	/**
	 * Set the AC Status according to the open snags/defect log.
	 */
	public void setACStatus(int FTU_Aircraft_ID) {

		if (FTU_Aircraft_ID == 0)
			return;
		
		MFTUAircraft ac = new MFTUAircraft(getCtx(), getFTU_Aircraft_ID(), get_TrxName());

		String where = MFTUDefectLog.COLUMNNAME_FTU_Aircraft_ID + "=" + getFTU_Aircraft_ID() + " AND "  
					 + MFTUDefectLog.COLUMNNAME_DocStatus + "=" + DB.TO_STRING(MFTUDefectLog.STATUS_Entered);
		
		int count = new Query(getCtx(),MFTUDefectLog.Table_Name,where,get_TrxName())
						.count();
		
		if (count > 0) {
			ac.setACStatus(MFTUAircraft.ACSTATUS_Unservicable);
		}
		else {
			ac.setACStatus(MFTUAircraft.ACSTATUS_Servicable);
		}
		ac.saveEx();
	}

	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {

		index = 0;
			
//		Locked
		if (processing != null)
		{
			boolean locked = "Y".equals(processing);
			if (!locked && processing instanceof Boolean)
				locked = ((Boolean)processing).booleanValue();
			if (locked)
				options[index++] = DocumentEngine.ACTION_Unlock;
		}
	
		//	Drafted, Approval required or Invalid           ..  DR/NA/IN
		if (docStatus.equals(DocumentEngine.STATUS_Drafted)
				|| docStatus.equals(DocumentEngine.STATUS_NotApproved) 
				|| docStatus.equals(DocumentEngine.STATUS_Invalid))
		{
			options[index++] = ACTION_Enter;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	Entered						..  ME
		else if (docStatus.equals(STATUS_Entered))
		{
			options[index++] = ACTION_Defer;
			options[index++] = ACTION_Rectify;
		//	options[index++] = DocumentEngine.ACTION_Prepare;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	In Process                  ..  IP
		else if (docStatus.equals(DocumentEngine.STATUS_InProgress)
			|| docStatus.equals(DocumentEngine.STATUS_Approved))
		{
			options[index++] = ACTION_Enter;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	Complete                    ..  CO
		else if (docStatus.equals(STATUS_Deferred))
		{
			options[index++] = ACTION_Rectify;
		}
		return index;
	}

	@Override
	public String[] getCustomizedActionOptions() {
		
		if (DocAction.STATUS_Drafted.equals(getDocStatus()))
			return new String[] {ACTION_Reject, ACTION_Void, ACTION_Enter};
		
		if (DocAction.STATUS_InProgress.equals(getDocStatus()) || DocAction.STATUS_Approved.equals(getDocStatus()))
			return new String[] {ACTION_Defer, ACTION_Rectify, ACTION_Void};
		
		if (DocAction.STATUS_NotApproved.equals(getDocStatus()))
			return new String[] {ACTION_Reject, ACTION_Enter, 
				ACTION_Unlock, ACTION_Void};
		
		if (this.STATUS_Entered.equals(getDocStatus()))
			return new String[] {ACTION_Defer, ACTION_Rectify, ACTION_Void};

		if (this.STATUS_Deferred.equals(getDocStatus()))
			return new String[] {ACTION_Rectify};

		return new String[] {};

	}

	@Override
	public boolean processCustomAction(String m_action) {
		
		if (ACTION_Enter.equals(m_action))
			return enterIt();
		if (ACTION_Defer.equals(m_action))
			return deferIt();
		if (ACTION_Rectify.equals(m_action))
			return rectifyIt();

		return false;
	}
}
