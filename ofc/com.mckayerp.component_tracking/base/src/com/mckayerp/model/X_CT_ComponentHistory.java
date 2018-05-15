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
package com.mckayerp.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for CT_ComponentHistory
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_CT_ComponentHistory extends PO implements I_CT_ComponentHistory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_CT_ComponentHistory (Properties ctx, int CT_ComponentHistory_ID, String trxName)
    {
      super (ctx, CT_ComponentHistory_ID, trxName);
      /** if (CT_ComponentHistory_ID == 0)
        {
			setCT_Component_ID (0);
			setCT_ComponentActionType (null);
			setCT_ComponentHistory_ID (0);
			setDateAction (new Timestamp( System.currentTimeMillis() ));
			setQty (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_CT_ComponentHistory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CT_ComponentHistory[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Component Life at Action.
		@param ComponentLifeAtAction 
		The life usage of the component at the time of the action
	  */
	public void setComponentLifeAtAction (BigDecimal ComponentLifeAtAction)
	{
		set_ValueNoCheck (COLUMNNAME_ComponentLifeAtAction, ComponentLifeAtAction);
	}

	/** Get Component Life at Action.
		@return The life usage of the component at the time of the action
	  */
	public BigDecimal getComponentLifeAtAction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ComponentLifeAtAction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			set_ValueNoCheck (COLUMNNAME_CT_Component_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_Component_ID, Integer.valueOf(CT_Component_ID));
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

	/** CT_ComponentActionType AD_Reference_ID=53872 */
	public static final int CT_COMPONENTACTIONTYPE_AD_Reference_ID=53872;
	/** Shipped = Shipped */
	public static final String CT_COMPONENTACTIONTYPE_Shipped = "Shipped";
	/** Received = Received */
	public static final String CT_COMPONENTACTIONTYPE_Received = "Received";
	/** Scrapped = Scrapped */
	public static final String CT_COMPONENTACTIONTYPE_Scrapped = "Scrapped";
	/** Added to inventory = Added */
	public static final String CT_COMPONENTACTIONTYPE_AddedToInventory = "Added";
	/** Drawn from inentory = Drawn */
	public static final String CT_COMPONENTACTIONTYPE_DrawnFromInentory = "Drawn";
	/** Installed = Installed */
	public static final String CT_COMPONENTACTIONTYPE_Installed = "Installed";
	/** Uninstalled = Uninstalled */
	public static final String CT_COMPONENTACTIONTYPE_Uninstalled = "Uninstalled";
	/** Created = Created */
	public static final String CT_COMPONENTACTIONTYPE_Created = "Created";
	/** Inspected = Inspected */
	public static final String CT_COMPONENTACTIONTYPE_Inspected = "Inspected";
	/** Overhauled = Overhauled */
	public static final String CT_COMPONENTACTIONTYPE_Overhauled = "Overhauled";
	/** Repaired = Repaired */
	public static final String CT_COMPONENTACTIONTYPE_Repaired = "Repaired";
	/** Set Action Type.
		@param CT_ComponentActionType 
		The type of action performed on the component
	  */
	public void setCT_ComponentActionType (String CT_ComponentActionType)
	{

		set_ValueNoCheck (COLUMNNAME_CT_ComponentActionType, CT_ComponentActionType);
	}

	/** Get Action Type.
		@return The type of action performed on the component
	  */
	public String getCT_ComponentActionType () 
	{
		return (String)get_Value(COLUMNNAME_CT_ComponentActionType);
	}

	public com.mckayerp.model.I_CT_ComponentBOMLine getCT_ComponentBOMLine() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_ComponentBOMLine)MTable.get(getCtx(), com.mckayerp.model.I_CT_ComponentBOMLine.Table_Name)
			.getPO(getCT_ComponentBOMLine_ID(), get_TrxName());	}

	/** Set Component BOM Line.
		@param CT_ComponentBOMLine_ID 
		A component included in an assembly
	  */
	public void setCT_ComponentBOMLine_ID (int CT_ComponentBOMLine_ID)
	{
		if (CT_ComponentBOMLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_ComponentBOMLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_ComponentBOMLine_ID, Integer.valueOf(CT_ComponentBOMLine_ID));
	}

	/** Get Component BOM Line.
		@return A component included in an assembly
	  */
	public int getCT_ComponentBOMLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_ComponentBOMLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Component History.
		@param CT_ComponentHistory_ID 
		A log of the changes made to components
	  */
	public void setCT_ComponentHistory_ID (int CT_ComponentHistory_ID)
	{
		if (CT_ComponentHistory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CT_ComponentHistory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CT_ComponentHistory_ID, Integer.valueOf(CT_ComponentHistory_ID));
	}

	/** Get Component History.
		@return A log of the changes made to components
	  */
	public int getCT_ComponentHistory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_ComponentHistory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Action Date.
		@param DateAction 
		The date the action took place
	  */
	public void setDateAction (Timestamp DateAction)
	{
		set_ValueNoCheck (COLUMNNAME_DateAction, DateAction);
	}

	/** Get Action Date.
		@return The date the action took place
	  */
	public Timestamp getDateAction () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAction);
	}

	/** Set Date Tolerance Applied.
		@param FTU_DateToleranceApplied 
		The amount of a date tolerance applied at the time of the maintenance action
	  */
	public void setFTU_DateToleranceApplied (BigDecimal FTU_DateToleranceApplied)
	{
		set_Value (COLUMNNAME_FTU_DateToleranceApplied, FTU_DateToleranceApplied);
	}

	/** Get Date Tolerance Applied.
		@return The amount of a date tolerance applied at the time of the maintenance action
	  */
	public BigDecimal getFTU_DateToleranceApplied () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_DateToleranceApplied);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public com.mckayerp.ftu.model.I_FTU_MaintWOResultLine getFTU_MaintWOResultLine() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintWOResultLine)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintWOResultLine.Table_Name)
			.getPO(getFTU_MaintWOResultLine_ID(), get_TrxName());	}

	/** Set Maintenance Work Order Result Line.
		@param FTU_MaintWOResultLine_ID Maintenance Work Order Result Line	  */
	public void setFTU_MaintWOResultLine_ID (int FTU_MaintWOResultLine_ID)
	{
		if (FTU_MaintWOResultLine_ID < 1) 
			set_Value (COLUMNNAME_FTU_MaintWOResultLine_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_MaintWOResultLine_ID, Integer.valueOf(FTU_MaintWOResultLine_ID));
	}

	/** Get Maintenance Work Order Result Line.
		@return Maintenance Work Order Result Line	  */
	public int getFTU_MaintWOResultLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWOResultLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Usage Tolerance Applied.
		@param FTU_UsageToleranceApplied 
		The amount of a usage tolerance applied at the time of the maintenance action
	  */
	public void setFTU_UsageToleranceApplied (BigDecimal FTU_UsageToleranceApplied)
	{
		set_Value (COLUMNNAME_FTU_UsageToleranceApplied, FTU_UsageToleranceApplied);
	}

	/** Get Usage Tolerance Applied.
		@return The amount of a usage tolerance applied at the time of the maintenance action
	  */
	public BigDecimal getFTU_UsageToleranceApplied () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_UsageToleranceApplied);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_InOutLine getM_InOutLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_InOutLine)MTable.get(getCtx(), org.compiere.model.I_M_InOutLine.Table_Name)
			.getPO(getM_InOutLine_ID(), get_TrxName());	}

	/** Set Shipment/Receipt Line.
		@param M_InOutLine_ID 
		Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		if (M_InOutLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_InOutLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
	}

	/** Get Shipment/Receipt Line.
		@return Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOutLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_InventoryLine getM_InventoryLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_InventoryLine)MTable.get(getCtx(), org.compiere.model.I_M_InventoryLine.Table_Name)
			.getPO(getM_InventoryLine_ID(), get_TrxName());	}

	/** Set Phys.Inventory Line.
		@param M_InventoryLine_ID 
		Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID)
	{
		if (M_InventoryLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_InventoryLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_InventoryLine_ID, Integer.valueOf(M_InventoryLine_ID));
	}

	/** Get Phys.Inventory Line.
		@return Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InventoryLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_MovementLine getM_MovementLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_MovementLine)MTable.get(getCtx(), org.compiere.model.I_M_MovementLine.Table_Name)
			.getPO(getM_MovementLine_ID(), get_TrxName());	}

	/** Set Move Line.
		@param M_MovementLine_ID 
		Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID)
	{
		if (M_MovementLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
	}

	/** Get Move Line.
		@return Inventory Move document Line
	  */
	public int getM_MovementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** MovementType AD_Reference_ID=189 */
	public static final int MOVEMENTTYPE_AD_Reference_ID=189;
	/** Customer Shipment = C- */
	public static final String MOVEMENTTYPE_CustomerShipment = "C-";
	/** Customer Returns = C+ */
	public static final String MOVEMENTTYPE_CustomerReturns = "C+";
	/** Vendor Receipts = V+ */
	public static final String MOVEMENTTYPE_VendorReceipts = "V+";
	/** Vendor Returns = V- */
	public static final String MOVEMENTTYPE_VendorReturns = "V-";
	/** Inventory Out = I- */
	public static final String MOVEMENTTYPE_InventoryOut = "I-";
	/** Inventory In = I+ */
	public static final String MOVEMENTTYPE_InventoryIn = "I+";
	/** Movement From = M- */
	public static final String MOVEMENTTYPE_MovementFrom = "M-";
	/** Movement To = M+ */
	public static final String MOVEMENTTYPE_MovementTo = "M+";
	/** Production + = P+ */
	public static final String MOVEMENTTYPE_ProductionPlus = "P+";
	/** Production - = P- */
	public static final String MOVEMENTTYPE_Production_ = "P-";
	/** Work Order + = W+ */
	public static final String MOVEMENTTYPE_WorkOrderPlus = "W+";
	/** Work Order - = W- */
	public static final String MOVEMENTTYPE_WorkOrder_ = "W-";
	/** Set Movement Type.
		@param MovementType 
		Method of moving the inventory
	  */
	public void setMovementType (String MovementType)
	{

		set_Value (COLUMNNAME_MovementType, MovementType);
	}

	/** Get Movement Type.
		@return Method of moving the inventory
	  */
	public String getMovementType () 
	{
		return (String)get_Value(COLUMNNAME_MovementType);
	}

	/** Set Overhaul Count.
		@param OverhaulCount 
		The number of life cycles completed or underway.
	  */
	public void setOverhaulCount (int OverhaulCount)
	{
		set_Value (COLUMNNAME_OverhaulCount, Integer.valueOf(OverhaulCount));
	}

	/** Get Overhaul Count.
		@return The number of life cycles completed or underway.
	  */
	public int getOverhaulCount () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OverhaulCount);
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

	/** Set Parent Life at Action.
		@param ParentLifeAtAction 
		The life usage measure of the parent at the time of the action.
	  */
	public void setParentLifeAtAction (BigDecimal ParentLifeAtAction)
	{
		set_ValueNoCheck (COLUMNNAME_ParentLifeAtAction, ParentLifeAtAction);
	}

	/** Get Parent Life at Action.
		@return The life usage measure of the parent at the time of the action.
	  */
	public BigDecimal getParentLifeAtAction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ParentLifeAtAction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_ValueNoCheck (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public com.mckayerp.model.I_CT_Component getRoot_Component() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getRoot_Component_ID(), get_TrxName());	}

	/** Set Root Component.
		@param Root_Component_ID 
		The Root Component of the component BOM tree.
	  */
	public void setRoot_Component_ID (int Root_Component_ID)
	{
		if (Root_Component_ID < 1) 
			set_Value (COLUMNNAME_Root_Component_ID, null);
		else 
			set_Value (COLUMNNAME_Root_Component_ID, Integer.valueOf(Root_Component_ID));
	}

	/** Get Root Component.
		@return The Root Component of the component BOM tree.
	  */
	public int getRoot_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Root_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}