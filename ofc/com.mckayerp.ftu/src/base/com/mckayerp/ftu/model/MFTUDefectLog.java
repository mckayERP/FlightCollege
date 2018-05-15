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
import org.compiere.model.MUOM;
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

import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentBOM;
import com.mckayerp.model.MCTComponentBOMLine;

public class MFTUDefectLog extends X_FTU_DefectLog implements DocAction, DocOptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5534916727936182301L;
	private String m_processMsg;
	private DocumentEngine m_documentEngine;
	private MFTUMaintRequirement m_ftuMaintRequirement = null;

	/**	Aircraft Maintenance Defect Entry   */
	public static final String 	DOCTYPE_DefectRecord     = "AMD";

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
			MDocType types[] = MDocType.getOfDocBaseType(getCtx(), DOCTYPE_DefectRecord);
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
		
		// May not be necessary
		List<MFTUMaintRequirement> maintCARs 
			= MFTUMaintRequirement.getByFTU_DefectLog(getCtx(), this.getFTU_DefectLog_ID(), get_TrxName());
		
		for (MFTUMaintRequirement maintCAR : maintCARs)
		{
			maintCAR.deleteEx(false);
		}
		
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

		// May not be necessary
		List<MFTUMaintRequirement> maintCARs 
		= MFTUMaintRequirement.getByFTU_DefectLog(getCtx(), this.getFTU_DefectLog_ID(), get_TrxName());
	
		for (MFTUMaintRequirement maintCAR : maintCARs)
		{
			maintCAR.deleteEx(false);
		}

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
		
		MRefList docStatus = MRefList.get(getCtx(), MFTUDefectLog.DOCSTATUS_AD_Reference_ID, getDocStatus(), get_TrxName());
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo()).append(" ").append(docStatus.getName()).append(" ")
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
		query.addRestriction(I_FTU_DefectLog.COLUMNNAME_FTU_DefectLog_ID, MQuery.EQUAL, this.getFTU_DefectLog_ID());
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

			MDocType types[] = MDocType.getOfDocBaseType(getCtx(), DOCTYPE_DefectRecord);
			if (types.length > 0)	//	get first
				setC_DocType_ID(types[0].getC_DocType_ID());
			else
			{
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@ @C_DocType_ID@"));
				return false;
			}

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
		
		// Ensure component is identified - it should be the aircraft, unless a defect is
		// identified on a component from the aircraft
		if (this.getCT_Component_ID() <= 0)
		{
			if (this.getFTU_Aircraft_ID() > 0)
			{
				// Use the root component of the aircraft - it may be null if none is identified
				this.setCT_Component_ID(this.getFTU_Aircraft().getCT_Component_ID());
			}
		}
		else
		{
			// The component should override the aircraft
			// Verify the root component is used - this should be set in the window validation rules
			// but to be sure ...
			int ct_component_id = ((MCTComponent) getCT_Component()).getRoot_Component_ID();
			if (ct_component_id != getCT_Component_ID())
				setCT_Component_ID(ct_component_id);
			
			MFTUAircraft ac = MFTUAircraft.getByCT_Component_ID(getCtx(), ct_component_id, get_TrxName());
			if (ac != null)
			{
				this.setFTU_Aircraft_ID(ac.getFTU_Aircraft_ID());
			}
		}

		if (getFTU_Aircraft_ID() > 0)
		{
			this.setTotalAirframeTime(this.getFTU_Aircraft().getAirframeTime());
		}

		// Update the product/asi and life used of the component.
		if (this.getCT_Component_ID() > 0 && this.is_ValueChanged(X_FTU_DefectLog.COLUMNNAME_CT_Component_ID))
		{
			this.setM_Product_ID(this.getCT_Component().getM_Product_ID());
			this.setM_AttributeSetInstance_ID(this.getCT_Component().getM_AttributeSetInstance_ID());
			this.setLifeUsed(this.getCT_Component().getLifeUsed());
		}
		else if(this.getCT_Component_ID() <= 0)
		{
			this.setM_Product_ID(0);
			this.setM_AttributeSetInstance_ID(-1);  // zero is a valid ASI value
			this.setLifeUsed(null);
		}

		return true;	
	}

	public boolean rectifyIt() {

		if (m_documentEngine == null)
			m_documentEngine = new DocumentEngine(this, getDocStatus());
		
		if (!m_documentEngine.isValidAction(ACTION_Rectify))
			return false;
		
		if (this.getFTU_MaintWOResultLine_ID() <= 0 || !this.getFTU_MaintWOResultLine().isMaintReqCompleted())
		{
			m_processMsg = "Must use a Maintenance Result document to rectify a defect record.";	
			return false;
		}
		
		Timestamp now = new Timestamp(System.currentTimeMillis());		
		if (getRepairedDate() == null) {
			setRepairedDate(now);
		}
		
		setDocAction(DOCACTION_None);
		setDocStatus(STATUS_Rectified);
		setProcessed(true);
		
		if (getFTU_Aircraft_ID() >= 0  && !isAdministrative())
		{
			MFTUACJourneyLog jl = new MFTUACJourneyLog(getCtx(), 0, get_TrxName());
			jl.setFTU_Aircraft_ID(getFTU_Aircraft_ID());
			jl.setEntryDate(this.getRepairedDate());
			jl.setIntendedFlight("SNAG Repaired: [" + this.getDocumentNo() + "] " + this.getRectification());
			jl.setFTU_DefectLog_ID(this.getFTU_DefectLog_ID());
			jl.setTotalAirframeTime(MFTUACJourneyLog.getTotalAirframeTime(getCtx(), getFTU_Aircraft_ID(), this.getRepairedDate(), get_TrxName()));
			jl.saveEx();
		}
				
		return true;
	}

	public boolean deferIt() {

		if (m_documentEngine == null)
			m_documentEngine = new DocumentEngine(this, getDocStatus());

		if (!m_documentEngine.isValidAction(ACTION_Defer))
			return false;
		
		// Check if a deferral is possible in the case of a missing component
		if (this.getCT_ComponentBOMLine_ID() > 0)
		{
			MCTComponentBOMLine bomLine = (MCTComponentBOMLine) this.getCT_ComponentBOMLine();
			if (bomLine.getPP_Product_BOMLine().isCritical())
			{
				throw new AdempiereException("Can't defer a defect related to a missing critical component. See the component bom line."); // TODO Translate
			}
		}
		
		if (this.getDeferredNote() == null || this.getDeferredNote().length() == 0)
			throw new AdempiereException("A note regarding the deferral of this defect is required.");  // TODO Translate
		
		if (this.getDeferredDate() == null) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.setDeferredDate(now);
		}
		this.setDeferredBy(Env.getAD_User_ID(getCtx()));
		this.setDocAction(DOCACTION_Rectify);
		this.setDocStatus(STATUS_Deferred);

		if (getFTU_Aircraft_ID() >= 0  && !isAdministrative())
		{
			MFTUACJourneyLog jl = new MFTUACJourneyLog(getCtx(), 0, get_TrxName());
			jl.setFTU_Aircraft_ID(getFTU_Aircraft_ID());
			jl.setEntryDate(this.getDeferredDate());
			jl.setIntendedFlight("SNAG Deferred: [" + this.getDocumentNo() + "] " + this.getDeferredNote());
			jl.setFTU_DefectLog_ID(this.getFTU_DefectLog_ID());
			jl.setTotalAirframeTime(MFTUACJourneyLog.getTotalAirframeTime(getCtx(), getFTU_Aircraft_ID(), this.getDeferredDate(), get_TrxName()));
			jl.saveEx();
			
		}
		
		// Set any maintenance requirements to a 30 day time interval from the date of the snag
		List<MFTUMaintRequirement> maintCARs = MFTUMaintRequirement.getByFTU_DefectLog(getCtx(), this.getFTU_DefectLog_ID(), get_TrxName());
		
		if (maintCARs == null || maintCARs.size() == 0)
		{
			MFTUMaintRequirement maintCAR = new MFTUMaintRequirement(getCtx(), 0, get_TrxName());
			maintCAR.setFTU_Action("Repair Snag " + this.getDocumentNo() + ": " + this.getDefect());
			maintCAR.setFTU_DefectLog_ID(getFTU_DefectLog_ID());
			if (this.isDeferred())
			{
				maintCAR.setFTU_ComplianceType(MFTUMaintRequirement.FTU_COMPLIANCETYPE_OnceWithinNext);
				maintCAR.setFTU_TimeInterval_Entered(new BigDecimal(30));
				maintCAR.setFTU_TimeIntervalUOM_ID(MUOM.get(getCtx(), "Day", get_TrxName()).getC_UOM_ID());
				maintCAR.setFTU_DateAfter(this.getDefectDate());
			}
			else
			{
				maintCAR.setFTU_ComplianceType(MFTUMaintRequirement.FTU_COMPLIANCETYPE_BeforeFurtherFlightOrUse);
			}
			maintCAR.saveEx();
		}
		else
		{
			for (MFTUMaintRequirement maintCAR : maintCARs)
			{
				if (this.isDeferred())
				{
					maintCAR.setFTU_ComplianceType(MFTUMaintRequirement.FTU_COMPLIANCETYPE_OnceWithinNext);
					maintCAR.setFTU_TimeInterval_Entered(new BigDecimal(30));
					maintCAR.setFTU_TimeIntervalUOM_ID(MUOM.get(getCtx(), "Day", get_TrxName()).getC_UOM_ID());
					maintCAR.setFTU_DateAfter(this.getDefectDate());
					maintCAR.saveEx();
				}
				
			}
		}

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

		// Add the defect to the Journey Log if an aircraft is defined and the defect isn't an administrative defect.
		if (getFTU_Aircraft_ID() >= 0  && !isAdministrative())
		{
			MFTUACJourneyLog jl = new MFTUACJourneyLog(getCtx(), 0, get_TrxName());
			jl.setFTU_Aircraft_ID(getFTU_Aircraft_ID());
			jl.setEntryDate(this.getDefectDate());
			jl.setIntendedFlight("SNAG: [" + this.getDocumentNo() + "] " + this.getDefect());
			jl.setFTU_DefectLog_ID(this.getFTU_DefectLog_ID());
			jl.setTotalAirframeTime(MFTUACJourneyLog.getTotalAirframeTime(getCtx(), getFTU_Aircraft_ID(), this.getDefectDate(), get_TrxName()));
			jl.saveEx();
		
			this.setTotalAirframeTime(jl.getTotalAirframeTime());			
		}
		
		this.setEnteredBy(Env.getAD_User_ID(getCtx()));
		this.setDocStatus(STATUS_Entered);
		this.setDocAction(DOCACTION_Rectify);
						
		// Create a corrective action
		MFTUMaintRequirement maintCAR = new MFTUMaintRequirement(getCtx(), 0, get_TrxName());
		maintCAR.setValue(Msg.parseTranslation(getCtx(), "@FTU_DefectLog_ID@:" + this.getDocumentNo()));
		maintCAR.setFTU_Action("Repair Snag " + getDocumentNo() + ": " + getDefect());
		maintCAR.setFTU_Process("In accordance with the MCM and AMO approved processes.");
		maintCAR.setFTU_DefectLog_ID(getFTU_DefectLog_ID());
		maintCAR.setCT_Component_ID(getCT_Component_ID());
		if (this.isDeferred())
		{
			maintCAR.setFTU_ComplianceType(MFTUMaintRequirement.FTU_COMPLIANCETYPE_OnceWithinNext);
			maintCAR.setFTU_TimeInterval(new BigDecimal(30));
			maintCAR.setFTU_TimeIntervalUOM_ID(MUOM.get(getCtx(), "Day", get_TrxName()).getC_UOM_ID());
			maintCAR.setFTU_DateAfter(this.getDefectDate());
		}
		else
		{
			maintCAR.setFTU_ComplianceType(MFTUMaintRequirement.FTU_COMPLIANCETYPE_BeforeFurtherFlightOrUse);
		}
		maintCAR.saveEx();
		
		// Save the maintenance CAR
		m_ftuMaintRequirement = maintCAR;

		return true;
	}

	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {

		// Wipe the options - fully custom
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
		
		if (MFTUDefectLog.STATUS_Entered.equals(getDocStatus()))
			return new String[] {ACTION_Defer, ACTION_Rectify, ACTION_Void};

		if (MFTUDefectLog.STATUS_Deferred.equals(getDocStatus()))
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

	public MFTUMaintRequirement getFTU_MaintRequirement() {
		
		return m_ftuMaintRequirement;
		
	}

	public static MFTUDefectLog createDefectFromUninstalledBOMLine(
			Properties ctx, int ftu_aircraft_id, int ct_componentBOMLine_id,
			int ftu_maintWOResult_id, String trxName) {
		
		if (ftu_aircraft_id == 0 || ct_componentBOMLine_id == 0)
			return null;
		
		MFTUDefectLog defect = null;
		
		// Only one open defect related to an uninstalled component is allowed
		// per component BOM Line.  If more than one was allowed, it would be
		// possible to create multiple defects for the same uninstalled component
		// in cases where maintenance work extends across multiple work orders 
		// and result documents.  
		//
		// Search for an existing open defect for that BOM line that 
		String where = MFTUDefectLog.COLUMNNAME_CT_ComponentBOMLine_ID + "=?"
				+ " AND " + MFTUDefectLog.COLUMNNAME_DocStatus + " IN ('"
					+ MFTUDefectLog.DOCSTATUS_Entered + "', '"
					+ MFTUDefectLog.DOCSTATUS_Deferred + "')";
		
		int existingDefect_id = new Query(ctx, MFTUDefectLog.Table_Name, where, trxName)
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.setParameters(ct_componentBOMLine_id)
						.firstIdOnly();
		
		if (existingDefect_id <= 0)
		{
			
			// Create a defect record
			MFTUAircraft ac = new MFTUAircraft(ctx, ftu_aircraft_id, trxName);
			
			MFTUMaintWOResult MWOR = null;
			if (ftu_maintWOResult_id > 0)
				MWOR = new MFTUMaintWOResult(ctx, ftu_maintWOResult_id, trxName);
			
			MCTComponentBOMLine bomLine = new MCTComponentBOMLine(ctx, ct_componentBOMLine_id, trxName);
			MCTComponentBOM bom = (MCTComponentBOM) bomLine.getCT_ComponentBOM();
			
			boolean hasNoHistory = !bomLine.hasHistory();
			
			// Build string description and defect
			int line = bomLine.getLine();
			String productValue = bomLine.getMaster_Product().getValue();
			String productName = bomLine.getMaster_Product().getName();
			String parentComponent = ((MCTComponent) bom.getCT_Component()).toString();

			String description = Msg.getElement(ctx, MCTComponentBOMLine.COLUMNNAME_CT_ComponentBOMLine_ID)
					+ " " + line + " missing " + productValue + " " + productName
					+ " " + Msg.getElement(ctx, MCTComponentBOMLine.COLUMNNAME_QtyInstalled) + ": " + bomLine.getQtyInstalled() 
					+ " " + Msg.getElement(ctx, MCTComponentBOMLine.COLUMNNAME_QtyRequired) + ": " + bomLine.getQtyRequired();
			
			if (MWOR != null)
			{
				// Add reference to result document
				description = "After " + Msg.getElement(ctx, MFTUMaintWOResult.COLUMNNAME_FTU_MaintWOResult_ID) 
					+ " " + MWOR.getDocumentNo() + ": " + description;   
			}
			
			String defectShortDesc = Msg.getElement(ctx, MCTComponentBOMLine.COLUMNNAME_CT_ComponentBOMLine_ID)
					+ " " + line + " missing " + productValue + " " + productName;
					
			defect = new MFTUDefectLog(ctx, 0, trxName);
			if (MWOR != null)
			{
				defect.setAD_Org_ID(MWOR.getAD_Org_ID());
				defect.setFTU_MaintWOResult_ID(MWOR.getFTU_MaintWOResult_ID());
				defect.setDateDoc(MWOR.getDateDoc());
				defect.setIdentifiedDate(MWOR.getDateDoc());
			}
			else
			{
				defect.setAD_Org_ID(Env.getAD_Org_ID(ctx));
				defect.setFTU_MaintWOResult_ID(0);
				defect.setDateDoc(new Timestamp(System.currentTimeMillis()));
				defect.setIdentifiedDate(new Timestamp(System.currentTimeMillis()));
			}
			defect.setFTU_MaintWOResultLine_ID(0);
			defect.setDefect(defectShortDesc);
			defect.setCT_Component_ID(ac.getCT_Component_ID());
			defect.setCT_ComponentBOMLine_ID(ct_componentBOMLine_id);
			defect.setM_Product_ID(ac.getCT_Component().getM_Product_ID());
			defect.setM_AttributeSetInstance_ID(ac.getCT_Component().getM_AttributeSetInstance_ID());
			defect.setC_BPartner_ID(0);
			defect.setDefectDate(new Timestamp(System.currentTimeMillis()));
			defect.setDescription(description);
			defect.setEnteredBy(Env.getAD_User_ID(ctx));
			defect.setFTU_Aircraft_ID(ftu_aircraft_id);
			defect.setIsAdministrative(hasNoHistory);
			defect.saveEx();  // Get the ID - not assigned until it is saved.
			defect.enterIt();  // Creates the Maintenance Requirement

			// Find the maint requirement and update it a bit.
			MFTUMaintRequirement mr = defect.getFTU_MaintRequirement(); 
			if (mr != null)
			{
				mr.setFTU_Action(defectShortDesc); 
				mr.setFTU_ResolutionTemplate("Missing component installed.");
				mr.setFTU_ResolutionFFTemplate("(Please complete defect resolution with fault found template.)");
				mr.saveEx();
				
				if (ftu_maintWOResult_id > 0)
				{
					// As a default, add the defect maint requirement to the maintenance result lines.
					// This assumes that faults found will be fixed in the same work order/result.
					where = MFTUMaintWOResultLine.COLUMNNAME_FTU_MaintWOResult_ID + "=?";
					int lineNo = (new Query(ctx, MFTUMaintWOResultLine.Table_Name, where, trxName)
									.setClient_ID()
									.setOnlyActiveRecords(true)
									.setParameters(ftu_maintWOResult_id)
									.aggregate(MFTUMaintWOResultLine.Table_Name + "." + MFTUMaintWOResultLine.COLUMNNAME_Line,
									   Query.AGGREGATE_MAX)).intValue()+10;
					MFTUMaintWOResultLine rLine = new MFTUMaintWOResultLine(ctx, 0, trxName);
					rLine.setFTU_MaintWOResult_ID(ftu_maintWOResult_id);
					rLine.setFTU_Action(mr.getFTU_Action());
					rLine.setFTU_MaintRequirement_ID(mr.getFTU_MaintRequirement_ID());
					rLine.setFTU_MaintRequirementLine_ID(0);
					rLine.setLine(lineNo);
								
					if (ac.getCT_Component_ID() > 0)
					{
						rLine.setCT_Component_ID(ac.getCT_Component_ID());
						rLine.setM_Product_ID(ac.getCT_Component().getM_Product_ID());
						rLine.setM_AttributeSetInstance_ID(ac.getCT_Component().getM_AttributeSetInstance_ID());
						rLine.setCT_ComponentLifeAtAction(ac.getCT_Component().getLifeUsed());
						rLine.setLifeUsageUOM_ID(ac.getCT_Component().getLifeUsageUOM_ID());
					}
					rLine.saveEx();
					defect.setFTU_MaintWOResultLine_ID(rLine.getFTU_MaintWOResultLine_ID());
				}
			}
			defect.saveEx();
		}		
		return defect;
	}

	public static MFTUDefectLog getByComponentBOMLine(Properties ctx,
			int ct_componentBOMLine_id, String trxName) {
		
		//  Search for an existing open defect for that BOM line
		//  There can only be one at a time.
		String where = MFTUDefectLog.COLUMNNAME_CT_ComponentBOMLine_ID + "=?"
				+ " AND " + MFTUDefectLog.COLUMNNAME_DocStatus + " IN ('"
					+ MFTUDefectLog.DOCSTATUS_Entered + "', '"
					+ MFTUDefectLog.DOCSTATUS_Deferred + "')";
		
		return new Query(ctx, MFTUDefectLog.Table_Name, where, trxName)
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.setParameters(ct_componentBOMLine_id)
						.firstOnly();
	}
	
	/**
	 * 	Executed before Delete operation.
	 *	@return true if record can be deleted
	 */
	protected boolean beforeDelete ()
	{
		if (isAdministrative() || DocAction.STATUS_Drafted.equals(getDocStatus()))
		{
			List<MFTUMaintRequirement> mrs = MFTUMaintRequirement.getByFTU_DefectLog(getCtx(), getFTU_DefectLog_ID(), get_TrxName());
			for (MFTUMaintRequirement mr : mrs)
			{
				mr.deleteEx(true);
			}
			return true;
		}
		log.saveError("Error", Msg.getMsg(getCtx(), "CannotDelete"));
		return false;
	} 	//	beforeDelete

	public static int getCountOpenbyComponent(Properties ctx,
			int ct_component_id, String trxName) {
		
		String where = MFTUDefectLog.COLUMNNAME_CT_Component_ID + "=?"
				+ " AND " + MFTUDefectLog.COLUMNNAME_DocStatus + "=?";
		
		return new Query(ctx, MFTUDefectLog.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ct_component_id, MFTUDefectLog.DOCSTATUS_Entered)
					.count();
	}

}
