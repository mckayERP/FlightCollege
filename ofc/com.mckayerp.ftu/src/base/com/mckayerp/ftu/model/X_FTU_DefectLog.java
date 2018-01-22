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
/** Generated Model - DO NOT CHANGE */
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for FTU_DefectLog
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_DefectLog extends PO implements I_FTU_DefectLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180107L;

    /** Standard Constructor */
    public X_FTU_DefectLog (Properties ctx, int FTU_DefectLog_ID, String trxName)
    {
      super (ctx, FTU_DefectLog_ID, trxName);
      /** if (FTU_DefectLog_ID == 0)
        {
			setDateDoc (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDefect (null);
			setDescription (null);
			setDocAction (null);
// ME
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setFTU_Aircraft_ID (0);
			setFTU_DefectLog_ID (0);
			setIdentifiedDate (new Timestamp( System.currentTimeMillis() ));
			setIsApproved (false);
// N
			setProcessed (false);
// N
        } */
    }

    /** Load Constructor */
    public X_FTU_DefectLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_FTU_DefectLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Approval Amount.
		@param ApprovalAmt 
		Document Approval Amount
	  */
	public void setApprovalAmt (BigDecimal ApprovalAmt)
	{
		set_Value (COLUMNNAME_ApprovalAmt, ApprovalAmt);
	}

	/** Get Approval Amount.
		@return Document Approval Amount
	  */
	public BigDecimal getApprovalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ApprovalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.model.I_CT_Component getCT_Component() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getCT_Component_ID(), get_TrxName());	}

	/** Set Component.
		@param CT_Component_ID 
		A component of an assembly or asset.
	  */
	public void setCT_Component_ID (int CT_Component_ID)
	{
		if (CT_Component_ID < 1) 
			set_Value (COLUMNNAME_CT_Component_ID, null);
		else 
			set_Value (COLUMNNAME_CT_Component_ID, Integer.valueOf(CT_Component_ID));
	}

	/** Get Component.
		@return A component of an assembly or asset.
	  */
	public int getCT_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
	}

	/** Set Defect.
		@param Defect 
		The defect description
	  */
	public void setDefect (String Defect)
	{
		set_Value (COLUMNNAME_Defect, Defect);
	}

	/** Get Defect.
		@return The defect description
	  */
	public String getDefect () 
	{
		return (String)get_Value(COLUMNNAME_Defect);
	}

	/** Set Defect Date.
		@param DefectDate 
		The date the defect was entered in the log
	  */
	public void setDefectDate (Timestamp DefectDate)
	{
		set_Value (COLUMNNAME_DefectDate, DefectDate);
	}

	/** Get Defect Date.
		@return The date the defect was entered in the log
	  */
	public Timestamp getDefectDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DefectDate);
	}

	/** DefectType AD_Reference_ID=1000039 */
	public static final int DEFECTTYPE_AD_Reference_ID=1000039;
	/** Lights and Electrical = Lights */
	public static final String DEFECTTYPE_LightsAndElectrical = "Lights";
	/** Avionics and Instruments = Avionics */
	public static final String DEFECTTYPE_AvionicsAndInstruments = "Avionics";
	/** Engines, fuel and associate parts. = Engines */
	public static final String DEFECTTYPE_EnginesFuelAndAssociateParts = "Engines";
	/** Airframe, wheels, and other stuff. = Airframe */
	public static final String DEFECTTYPE_AirframeWheelsAndOtherStuff = "Airframe";
	/** Routine Inspection = Inspection */
	public static final String DEFECTTYPE_RoutineInspection = "Inspection";
	/** Set Defect Type.
		@param DefectType 
		The type of the defect
	  */
	public void setDefectType (String DefectType)
	{

		set_Value (COLUMNNAME_DefectType, DefectType);
	}

	/** Get Defect Type.
		@return The type of the defect
	  */
	public String getDefectType () 
	{
		return (String)get_Value(COLUMNNAME_DefectType);
	}

	public org.compiere.model.I_AD_User getDeferre() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getDeferredBy(), get_TrxName());	}

	/** Set Deferred By.
		@param DeferredBy 
		The User that deferred the snag.
	  */
	public void setDeferredBy (int DeferredBy)
	{
		set_Value (COLUMNNAME_DeferredBy, Integer.valueOf(DeferredBy));
	}

	/** Get Deferred By.
		@return The User that deferred the snag.
	  */
	public int getDeferredBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DeferredBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Deferred Date.
		@param DeferredDate 
		The date the defect was deferred.
	  */
	public void setDeferredDate (Timestamp DeferredDate)
	{
		set_Value (COLUMNNAME_DeferredDate, DeferredDate);
	}

	/** Get Deferred Date.
		@return The date the defect was deferred.
	  */
	public Timestamp getDeferredDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DeferredDate);
	}

	/** Set Deferred Note.
		@param DeferredNote 
		A note that will be added to the Journey Logs to indicate that the defect has been deferred and any operational limitations that result.
	  */
	public void setDeferredNote (String DeferredNote)
	{
		set_Value (COLUMNNAME_DeferredNote, DeferredNote);
	}

	/** Get Deferred Note.
		@return A note that will be added to the Journey Logs to indicate that the defect has been deferred and any operational limitations that result.
	  */
	public String getDeferredNote () 
	{
		return (String)get_Value(COLUMNNAME_DeferredNote);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public org.compiere.model.I_AD_User getDoc_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getDoc_User_ID(), get_TrxName());	}

	/** Set Document Owner.
		@param Doc_User_ID 
		The AD_User_ID of the document owner.
	  */
	public void setDoc_User_ID (int Doc_User_ID)
	{
		if (Doc_User_ID < 1) 
			set_Value (COLUMNNAME_Doc_User_ID, null);
		else 
			set_Value (COLUMNNAME_Doc_User_ID, Integer.valueOf(Doc_User_ID));
	}

	/** Get Document Owner.
		@return The AD_User_ID of the document owner.
	  */
	public int getDoc_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Doc_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Enter = ME */
	public static final String DOCACTION_Enter = "ME";
	/** Defer = MD */
	public static final String DOCACTION_Defer = "MD";
	/** Rectify = MR */
	public static final String DOCACTION_Rectify = "MR";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Entered = ME */
	public static final String DOCSTATUS_Entered = "ME";
	/** Rectified = MR */
	public static final String DOCSTATUS_Rectified = "MR";
	/** Deferred = MD */
	public static final String DOCSTATUS_Deferred = "MD";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	public org.compiere.model.I_AD_User getEntere() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getEnteredBy(), get_TrxName());	}

	/** Set Entered By.
		@param EnteredBy 
		The User that entered (accepted) the snag, which could be different then the business partner who noticed the problem and the person who created the entry.
	  */
	public void setEnteredBy (int EnteredBy)
	{
		set_Value (COLUMNNAME_EnteredBy, Integer.valueOf(EnteredBy));
	}

	/** Get Entered By.
		@return The User that entered (accepted) the snag, which could be different then the business partner who noticed the problem and the person who created the entry.
	  */
	public int getEnteredBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EnteredBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_Aircraft getFTU_Aircraft() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Aircraft)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Aircraft.Table_Name)
			.getPO(getFTU_Aircraft_ID(), get_TrxName());	}

	/** Set Aircraft.
		@param FTU_Aircraft_ID Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID)
	{
		if (FTU_Aircraft_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Aircraft_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Aircraft_ID, Integer.valueOf(FTU_Aircraft_ID));
	}

	/** Get Aircraft.
		@return Aircraft	  */
	public int getFTU_Aircraft_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Aircraft_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Defect.
		@param FTU_DefectLog_ID Defect	  */
	public void setFTU_DefectLog_ID (int FTU_DefectLog_ID)
	{
		if (FTU_DefectLog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_DefectLog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_DefectLog_ID, Integer.valueOf(FTU_DefectLog_ID));
	}

	/** Get Defect.
		@return Defect	  */
	public int getFTU_DefectLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_DefectLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_Flightsheet getFTU_Flightsheet() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Flightsheet)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Flightsheet.Table_Name)
			.getPO(getFTU_Flightsheet_ID(), get_TrxName());	}

	/** Set Flight.
		@param FTU_Flightsheet_ID Flight	  */
	public void setFTU_Flightsheet_ID (int FTU_Flightsheet_ID)
	{
		if (FTU_Flightsheet_ID < 1) 
			set_Value (COLUMNNAME_FTU_Flightsheet_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Flightsheet_ID, Integer.valueOf(FTU_Flightsheet_ID));
	}

	/** Get Flight.
		@return Flight	  */
	public int getFTU_Flightsheet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Flightsheet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintJASCCode getFTU_MaintJASCCode() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintJASCCode)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintJASCCode.Table_Name)
			.getPO(getFTU_MaintJASCCode_ID(), get_TrxName());	}

	/** Set JASC Code.
		@param FTU_MaintJASCCode_ID 
		The JASC Code
	  */
	public void setFTU_MaintJASCCode_ID (int FTU_MaintJASCCode_ID)
	{
		if (FTU_MaintJASCCode_ID < 1) 
			set_Value (COLUMNNAME_FTU_MaintJASCCode_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_MaintJASCCode_ID, Integer.valueOf(FTU_MaintJASCCode_ID));
	}

	/** Get JASC Code.
		@return The JASC Code
	  */
	public int getFTU_MaintJASCCode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintJASCCode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintJASCHdr getFTU_MaintJASCHdr() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintJASCHdr)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintJASCHdr.Table_Name)
			.getPO(getFTU_MaintJASCHdr_ID(), get_TrxName());	}

	/** Set JASC Code Header.
		@param FTU_MaintJASCHdr_ID 
		The summary group of the JASC code. Typically, the first two digits of the code.
	  */
	public void setFTU_MaintJASCHdr_ID (int FTU_MaintJASCHdr_ID)
	{
		if (FTU_MaintJASCHdr_ID < 1) 
			set_Value (COLUMNNAME_FTU_MaintJASCHdr_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_MaintJASCHdr_ID, Integer.valueOf(FTU_MaintJASCHdr_ID));
	}

	/** Get JASC Code Header.
		@return The summary group of the JASC code. Typically, the first two digits of the code.
	  */
	public int getFTU_MaintJASCHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintJASCHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintWorkOrderLine getFTU_MaintWorkOrderLine() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintWorkOrderLine)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintWorkOrderLine.Table_Name)
			.getPO(getFTU_MaintWorkOrderLine_ID(), get_TrxName());	}

	/** Set Maintenance Work Order Line ID.
		@param FTU_MaintWorkOrderLine_ID Maintenance Work Order Line ID	  */
	public void setFTU_MaintWorkOrderLine_ID (int FTU_MaintWorkOrderLine_ID)
	{
		if (FTU_MaintWorkOrderLine_ID < 1) 
			set_Value (COLUMNNAME_FTU_MaintWorkOrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_MaintWorkOrderLine_ID, Integer.valueOf(FTU_MaintWorkOrderLine_ID));
	}

	/** Get Maintenance Work Order Line ID.
		@return Maintenance Work Order Line ID	  */
	public int getFTU_MaintWorkOrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWorkOrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Identified.
		@param IdentifiedDate 
		The date the issue was identified
	  */
	public void setIdentifiedDate (Timestamp IdentifiedDate)
	{
		set_Value (COLUMNNAME_IdentifiedDate, IdentifiedDate);
	}

	/** Get Date Identified.
		@return The date the issue was identified
	  */
	public Timestamp getIdentifiedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_IdentifiedDate);
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Deferred.
		@param IsDeferred 
		Is the defect deferred?
	  */
	public void setIsDeferred (boolean IsDeferred)
	{
		set_Value (COLUMNNAME_IsDeferred, Boolean.valueOf(IsDeferred));
	}

	/** Get Deferred.
		@return Is the defect deferred?
	  */
	public boolean isDeferred () 
	{
		Object oo = get_Value(COLUMNNAME_IsDeferred);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Repaired.
		@param IsRepaired 
		Is the defect repaired or resolved?
	  */
	public void setIsRepaired (boolean IsRepaired)
	{
		set_Value (COLUMNNAME_IsRepaired, Boolean.valueOf(IsRepaired));
	}

	/** Get Repaired.
		@return Is the defect repaired or resolved?
	  */
	public boolean isRepaired () 
	{
		Object oo = get_Value(COLUMNNAME_IsRepaired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Life Used.
		@param LifeUsed 
		The life used for this component.
	  */
	public void setLifeUsed (BigDecimal LifeUsed)
	{
		set_Value (COLUMNNAME_LifeUsed, LifeUsed);
	}

	/** Get Life Used.
		@return The life used for this component.
	  */
	public BigDecimal getLifeUsed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LifeUsed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed On.
		@param ProcessedOn 
		The date+time (expressed in decimal format) when the document has been processed
	  */
	public void setProcessedOn (BigDecimal ProcessedOn)
	{
		set_Value (COLUMNNAME_ProcessedOn, ProcessedOn);
	}

	/** Get Processed On.
		@return The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ProcessedOn);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rectification.
		@param Rectification 
		Description of how the defect was rectified.
	  */
	public void setRectification (String Rectification)
	{
		set_Value (COLUMNNAME_Rectification, Rectification);
	}

	/** Get Rectification.
		@return Description of how the defect was rectified.
	  */
	public String getRectification () 
	{
		return (String)get_Value(COLUMNNAME_Rectification);
	}

	public org.compiere.model.I_AD_User getRectifie() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getRectifiedBy(), get_TrxName());	}

	/** Set Rectified By.
		@param RectifiedBy 
		The user that marked the issue rectified. 
	  */
	public void setRectifiedBy (int RectifiedBy)
	{
		set_Value (COLUMNNAME_RectifiedBy, Integer.valueOf(RectifiedBy));
	}

	/** Get Rectified By.
		@return The user that marked the issue rectified. 
	  */
	public int getRectifiedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RectifiedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Repaired Date.
		@param RepairedDate 
		The date the defect was repaired.
	  */
	public void setRepairedDate (Timestamp RepairedDate)
	{
		set_Value (COLUMNNAME_RepairedDate, RepairedDate);
	}

	/** Get Repaired Date.
		@return The date the defect was repaired.
	  */
	public Timestamp getRepairedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_RepairedDate);
	}

	/** Set Total Airframe Time.
		@param TotalAirframeTime Total Airframe Time	  */
	public void setTotalAirframeTime (BigDecimal TotalAirframeTime)
	{
		set_Value (COLUMNNAME_TotalAirframeTime, TotalAirframeTime);
	}

	/** Get Total Airframe Time.
		@return Total Airframe Time	  */
	public BigDecimal getTotalAirframeTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAirframeTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}