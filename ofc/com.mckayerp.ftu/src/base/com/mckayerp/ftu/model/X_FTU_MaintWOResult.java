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

/** Generated Model for FTU_MaintWOResult
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintWOResult extends PO implements I_FTU_MaintWOResult, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_MaintWOResult (Properties ctx, int FTU_MaintWOResult_ID, String trxName)
    {
      super (ctx, FTU_MaintWOResult_ID, trxName);
      /** if (FTU_MaintWOResult_ID == 0)
        {
			setC_DocType_ID (0);
			setDateDoc (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setFTU_MaintWOResult_ID (0);
			setFTU_MaintWorkOrder_ID (0);
			setIsApproved (false);
// N
			setM_Warehouse_ID (0);
			setProcessed (false);
// N
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintWOResult (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintWOResult[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
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
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
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

	/** Set Component Life.
		@param CT_ComponentLifeAtAction 
		The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public void setCT_ComponentLifeAtAction (BigDecimal CT_ComponentLifeAtAction)
	{
		set_Value (COLUMNNAME_CT_ComponentLifeAtAction, CT_ComponentLifeAtAction);
	}

	/** Get Component Life.
		@return The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public BigDecimal getCT_ComponentLifeAtAction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CT_ComponentLifeAtAction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set AMO Reference.
		@param FTU_AMORef 
		The identifier of the associated document from the AMO.
	  */
	public void setFTU_AMORef (String FTU_AMORef)
	{
		set_Value (COLUMNNAME_FTU_AMORef, FTU_AMORef);
	}

	/** Get AMO Reference.
		@return The identifier of the associated document from the AMO.
	  */
	public String getFTU_AMORef () 
	{
		return (String)get_Value(COLUMNNAME_FTU_AMORef);
	}

	/** Set Create Result Lines.
		@param FTU_MaintCreateResultLines 
		Creates draft lines based on the work order.
	  */
	public void setFTU_MaintCreateResultLines (String FTU_MaintCreateResultLines)
	{
		set_Value (COLUMNNAME_FTU_MaintCreateResultLines, FTU_MaintCreateResultLines);
	}

	/** Get Create Result Lines.
		@return Creates draft lines based on the work order.
	  */
	public String getFTU_MaintCreateResultLines () 
	{
		return (String)get_Value(COLUMNNAME_FTU_MaintCreateResultLines);
	}

	/** Set Maintenance Work Order Result.
		@param FTU_MaintWOResult_ID Maintenance Work Order Result	  */
	public void setFTU_MaintWOResult_ID (int FTU_MaintWOResult_ID)
	{
		if (FTU_MaintWOResult_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResult_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResult_ID, Integer.valueOf(FTU_MaintWOResult_ID));
	}

	/** Get Maintenance Work Order Result.
		@return Maintenance Work Order Result	  */
	public int getFTU_MaintWOResult_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWOResult_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintWorkOrder getFTU_MaintWorkOrder() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintWorkOrder)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintWorkOrder.Table_Name)
			.getPO(getFTU_MaintWorkOrder_ID(), get_TrxName());	}

	/** Set Maintenance Work Order.
		@param FTU_MaintWorkOrder_ID 
		The Maintenance Work Order
	  */
	public void setFTU_MaintWorkOrder_ID (int FTU_MaintWorkOrder_ID)
	{
		if (FTU_MaintWorkOrder_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrder_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWorkOrder_ID, Integer.valueOf(FTU_MaintWorkOrder_ID));
	}

	/** Get Maintenance Work Order.
		@return The Maintenance Work Order
	  */
	public int getFTU_MaintWorkOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWorkOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_C_UOM getLifeUsageUOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getLifeUsageUOM_ID(), get_TrxName());	}

	/** Set Life Use UOM.
		@param LifeUsageUOM_ID 
		The unit of measure (UOM) for the life usage.  Example, hours, km.
	  */
	public void setLifeUsageUOM_ID (int LifeUsageUOM_ID)
	{
		if (LifeUsageUOM_ID < 1) 
			set_Value (COLUMNNAME_LifeUsageUOM_ID, null);
		else 
			set_Value (COLUMNNAME_LifeUsageUOM_ID, Integer.valueOf(LifeUsageUOM_ID));
	}

	/** Get Life Use UOM.
		@return The unit of measure (UOM) for the life usage.  Example, hours, km.
	  */
	public int getLifeUsageUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LifeUsageUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 0) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.model.I_CT_Component getParentComponent() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getParentComponent_ID(), get_TrxName());	}

	/** Set Parent Component.
		@param ParentComponent_ID 
		The component/assembly that the current component is installed in.
	  */
	public void setParentComponent_ID (int ParentComponent_ID)
	{
		if (ParentComponent_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ParentComponent_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ParentComponent_ID, Integer.valueOf(ParentComponent_ID));
	}

	/** Get Parent Component.
		@return The component/assembly that the current component is installed in.
	  */
	public int getParentComponent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ParentComponent_ID);
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
}