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

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FTU_DefectLog
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_DefectLog 
{

    /** TableName=FTU_DefectLog */
    public static final String Table_Name = "FTU_DefectLog";

    /** AD_Table_ID=1000036 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name ApprovalAmt */
    public static final String COLUMNNAME_ApprovalAmt = "ApprovalAmt";

	/** Set Approval Amount.
	  * Document Approval Amount
	  */
	public void setApprovalAmt (BigDecimal ApprovalAmt);

	/** Get Approval Amount.
	  * Document Approval Amount
	  */
	public BigDecimal getApprovalAmt();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CT_Component_ID */
    public static final String COLUMNNAME_CT_Component_ID = "CT_Component_ID";

	/** Set Component.
	  * A component of an assembly or asset.
	  */
	public void setCT_Component_ID (int CT_Component_ID);

	/** Get Component.
	  * A component of an assembly or asset.
	  */
	public int getCT_Component_ID();

	public com.mckayerp.model.I_CT_Component getCT_Component() throws RuntimeException;

    /** Column name CT_ComponentBOMLine_ID */
    public static final String COLUMNNAME_CT_ComponentBOMLine_ID = "CT_ComponentBOMLine_ID";

	/** Set Component BOM Line.
	  * A component included in an assembly
	  */
	public void setCT_ComponentBOMLine_ID (int CT_ComponentBOMLine_ID);

	/** Get Component BOM Line.
	  * A component included in an assembly
	  */
	public int getCT_ComponentBOMLine_ID();

	public com.mckayerp.model.I_CT_ComponentBOMLine getCT_ComponentBOMLine() throws RuntimeException;

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

    /** Column name Defect */
    public static final String COLUMNNAME_Defect = "Defect";

	/** Set Defect.
	  * The defect description
	  */
	public void setDefect (String Defect);

	/** Get Defect.
	  * The defect description
	  */
	public String getDefect();

    /** Column name DefectDate */
    public static final String COLUMNNAME_DefectDate = "DefectDate";

	/** Set Defect Date.
	  * The date and time the defect was entered in the log.
	  */
	public void setDefectDate (Timestamp DefectDate);

	/** Get Defect Date.
	  * The date and time the defect was entered in the log.
	  */
	public Timestamp getDefectDate();

    /** Column name DefectType */
    public static final String COLUMNNAME_DefectType = "DefectType";

	/** Set Defect Type.
	  * The type of the defect
	  */
	public void setDefectType (String DefectType);

	/** Get Defect Type.
	  * The type of the defect
	  */
	public String getDefectType();

    /** Column name DeferredBy */
    public static final String COLUMNNAME_DeferredBy = "DeferredBy";

	/** Set Deferred By.
	  * The User that deferred the snag.
	  */
	public void setDeferredBy (int DeferredBy);

	/** Get Deferred By.
	  * The User that deferred the snag.
	  */
	public int getDeferredBy();

	public org.compiere.model.I_AD_User getDeferre() throws RuntimeException;

    /** Column name DeferredDate */
    public static final String COLUMNNAME_DeferredDate = "DeferredDate";

	/** Set Deferred Date.
	  * The date the defect was deferred.
	  */
	public void setDeferredDate (Timestamp DeferredDate);

	/** Get Deferred Date.
	  * The date the defect was deferred.
	  */
	public Timestamp getDeferredDate();

    /** Column name DeferredNote */
    public static final String COLUMNNAME_DeferredNote = "DeferredNote";

	/** Set Deferred Note.
	  * A note that will be added to the Journey Logs to indicate that the defect has been deferred and any operational limitations that result.
	  */
	public void setDeferredNote (String DeferredNote);

	/** Get Deferred Note.
	  * A note that will be added to the Journey Logs to indicate that the defect has been deferred and any operational limitations that result.
	  */
	public String getDeferredNote();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Doc_User_ID */
    public static final String COLUMNNAME_Doc_User_ID = "Doc_User_ID";

	/** Set Document Owner.
	  * The AD_User_ID of the document owner.
	  */
	public void setDoc_User_ID (int Doc_User_ID);

	/** Get Document Owner.
	  * The AD_User_ID of the document owner.
	  */
	public int getDoc_User_ID();

	public org.compiere.model.I_AD_User getDoc_User() throws RuntimeException;

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name EnteredBy */
    public static final String COLUMNNAME_EnteredBy = "EnteredBy";

	/** Set Entered By.
	  * The User that entered (accepted) the snag, which could be different then the business partner who noticed the problem and the person who created the entry.
	  */
	public void setEnteredBy (int EnteredBy);

	/** Get Entered By.
	  * The User that entered (accepted) the snag, which could be different then the business partner who noticed the problem and the person who created the entry.
	  */
	public int getEnteredBy();

	public org.compiere.model.I_AD_User getEntere() throws RuntimeException;

    /** Column name FTU_Aircraft_ID */
    public static final String COLUMNNAME_FTU_Aircraft_ID = "FTU_Aircraft_ID";

	/** Set Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID);

	/** Get Aircraft	  */
	public int getFTU_Aircraft_ID();

	public com.mckayerp.ftu.model.I_FTU_Aircraft getFTU_Aircraft() throws RuntimeException;

    /** Column name FTU_DefectLog_ID */
    public static final String COLUMNNAME_FTU_DefectLog_ID = "FTU_DefectLog_ID";

	/** Set Defect	  */
	public void setFTU_DefectLog_ID (int FTU_DefectLog_ID);

	/** Get Defect	  */
	public int getFTU_DefectLog_ID();

    /** Column name FTU_Flightsheet_ID */
    public static final String COLUMNNAME_FTU_Flightsheet_ID = "FTU_Flightsheet_ID";

	/** Set Flight	  */
	public void setFTU_Flightsheet_ID (int FTU_Flightsheet_ID);

	/** Get Flight	  */
	public int getFTU_Flightsheet_ID();

	public com.mckayerp.ftu.model.I_FTU_Flightsheet getFTU_Flightsheet() throws RuntimeException;

    /** Column name FTU_MaintJASCCode_ID */
    public static final String COLUMNNAME_FTU_MaintJASCCode_ID = "FTU_MaintJASCCode_ID";

	/** Set JASC Code.
	  * The JASC Code
	  */
	public void setFTU_MaintJASCCode_ID (int FTU_MaintJASCCode_ID);

	/** Get JASC Code.
	  * The JASC Code
	  */
	public int getFTU_MaintJASCCode_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintJASCCode getFTU_MaintJASCCode() throws RuntimeException;

    /** Column name FTU_MaintJASCHdr_ID */
    public static final String COLUMNNAME_FTU_MaintJASCHdr_ID = "FTU_MaintJASCHdr_ID";

	/** Set JASC Code Header.
	  * The summary group of the JASC code. Typically, the first two digits of the code.
	  */
	public void setFTU_MaintJASCHdr_ID (int FTU_MaintJASCHdr_ID);

	/** Get JASC Code Header.
	  * The summary group of the JASC code. Typically, the first two digits of the code.
	  */
	public int getFTU_MaintJASCHdr_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintJASCHdr getFTU_MaintJASCHdr() throws RuntimeException;

    /** Column name FTU_MaintWOResult_ID */
    public static final String COLUMNNAME_FTU_MaintWOResult_ID = "FTU_MaintWOResult_ID";

	/** Set Maintenance Work Order Result	  */
	public void setFTU_MaintWOResult_ID (int FTU_MaintWOResult_ID);

	/** Get Maintenance Work Order Result	  */
	public int getFTU_MaintWOResult_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWOResult getFTU_MaintWOResult() throws RuntimeException;

    /** Column name FTU_MaintWOResultLine_ID */
    public static final String COLUMNNAME_FTU_MaintWOResultLine_ID = "FTU_MaintWOResultLine_ID";

	/** Set Maintenance Work Order Result Line	  */
	public void setFTU_MaintWOResultLine_ID (int FTU_MaintWOResultLine_ID);

	/** Get Maintenance Work Order Result Line	  */
	public int getFTU_MaintWOResultLine_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWOResultLine getFTU_MaintWOResultLine() throws RuntimeException;

    /** Column name FTU_MaintWorkOrderLine_ID */
    public static final String COLUMNNAME_FTU_MaintWorkOrderLine_ID = "FTU_MaintWorkOrderLine_ID";

	/** Set Maintenance Work Order Line	  */
	public void setFTU_MaintWorkOrderLine_ID (int FTU_MaintWorkOrderLine_ID);

	/** Get Maintenance Work Order Line	  */
	public int getFTU_MaintWorkOrderLine_ID();

	public com.mckayerp.ftu.model.I_FTU_MaintWorkOrderLine getFTU_MaintWorkOrderLine() throws RuntimeException;

    /** Column name IdentifiedDate */
    public static final String COLUMNNAME_IdentifiedDate = "IdentifiedDate";

	/** Set Date Identified.
	  * The date the issue was identified
	  */
	public void setIdentifiedDate (Timestamp IdentifiedDate);

	/** Get Date Identified.
	  * The date the issue was identified
	  */
	public Timestamp getIdentifiedDate();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsAdministrative */
    public static final String COLUMNNAME_IsAdministrative = "IsAdministrative";

	/** Set Is Administrative.
	  * Is the defect administrative? If selected, the defect will not be added to the AC Journey log.
	  */
	public void setIsAdministrative (boolean IsAdministrative);

	/** Get Is Administrative.
	  * Is the defect administrative? If selected, the defect will not be added to the AC Journey log.
	  */
	public boolean isAdministrative();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsDeferred */
    public static final String COLUMNNAME_IsDeferred = "IsDeferred";

	/** Set Deferred.
	  * Is the defect deferred?
	  */
	public void setIsDeferred (boolean IsDeferred);

	/** Get Deferred.
	  * Is the defect deferred?
	  */
	public boolean isDeferred();

    /** Column name IsRepaired */
    public static final String COLUMNNAME_IsRepaired = "IsRepaired";

	/** Set Repaired.
	  * Is the defect repaired or resolved?
	  */
	public void setIsRepaired (boolean IsRepaired);

	/** Get Repaired.
	  * Is the defect repaired or resolved?
	  */
	public boolean isRepaired();

    /** Column name LifeUsed */
    public static final String COLUMNNAME_LifeUsed = "LifeUsed";

	/** Set Life Used.
	  * The life used for this component.
	  */
	public void setLifeUsed (BigDecimal LifeUsed);

	/** Get Life Used.
	  * The life used for this component.
	  */
	public BigDecimal getLifeUsed();

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name ProcessedOn */
    public static final String COLUMNNAME_ProcessedOn = "ProcessedOn";

	/** Set Processed On.
	  * The date+time (expressed in decimal format) when the document has been processed
	  */
	public void setProcessedOn (BigDecimal ProcessedOn);

	/** Get Processed On.
	  * The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Rectification */
    public static final String COLUMNNAME_Rectification = "Rectification";

	/** Set Rectification.
	  * Description of how the defect was rectified.
	  */
	public void setRectification (String Rectification);

	/** Get Rectification.
	  * Description of how the defect was rectified.
	  */
	public String getRectification();

    /** Column name RectifiedBy */
    public static final String COLUMNNAME_RectifiedBy = "RectifiedBy";

	/** Set Rectified By.
	  * The user that marked the issue rectified. 
	  */
	public void setRectifiedBy (int RectifiedBy);

	/** Get Rectified By.
	  * The user that marked the issue rectified. 
	  */
	public int getRectifiedBy();

	public org.compiere.model.I_AD_User getRectifie() throws RuntimeException;

    /** Column name RepairedDate */
    public static final String COLUMNNAME_RepairedDate = "RepairedDate";

	/** Set Repaired Date.
	  * The date the defect was repaired.
	  */
	public void setRepairedDate (Timestamp RepairedDate);

	/** Get Repaired Date.
	  * The date the defect was repaired.
	  */
	public Timestamp getRepairedDate();

    /** Column name TotalAirframeTime */
    public static final String COLUMNNAME_TotalAirframeTime = "TotalAirframeTime";

	/** Set Total Airframe Time	  */
	public void setTotalAirframeTime (BigDecimal TotalAirframeTime);

	/** Get Total Airframe Time	  */
	public BigDecimal getTotalAirframeTime();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
