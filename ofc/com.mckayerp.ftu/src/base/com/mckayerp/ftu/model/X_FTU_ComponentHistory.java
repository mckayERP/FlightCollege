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

/** Generated Model for FTU_ComponentHistory
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_ComponentHistory extends PO implements I_FTU_ComponentHistory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170423L;

    /** Standard Constructor */
    public X_FTU_ComponentHistory (Properties ctx, int FTU_ComponentHistory_ID, String trxName)
    {
      super (ctx, FTU_ComponentHistory_ID, trxName);
      /** if (FTU_ComponentHistory_ID == 0)
        {
			setDateAction (new Timestamp( System.currentTimeMillis() ));
			setFTU_Component_ID (0);
			setFTU_ComponentActionType (null);
			setFTU_ComponentHistory_ID (0);
			setQty (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_FTU_ComponentHistory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_ComponentHistory[")
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

	/** Set Action Date.
		@param DateAction Action Date	  */
	public void setDateAction (Timestamp DateAction)
	{
		set_ValueNoCheck (COLUMNNAME_DateAction, DateAction);
	}

	/** Get Action Date.
		@return Action Date	  */
	public Timestamp getDateAction () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAction);
	}

	public com.mckayerp.ftu.model.I_FTU_Component getFTU_Component() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Component)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Component.Table_Name)
			.getPO(getFTU_Component_ID(), get_TrxName());	}

	/** Set Component.
		@param FTU_Component_ID 
		A component of an assembly or asset.
	  */
	public void setFTU_Component_ID (int FTU_Component_ID)
	{
		if (FTU_Component_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Component_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Component_ID, Integer.valueOf(FTU_Component_ID));
	}

	/** Get Component.
		@return A component of an assembly or asset.
	  */
	public int getFTU_Component_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Component_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FTU_ComponentActionType AD_Reference_ID=53872 */
	public static final int FTU_COMPONENTACTIONTYPE_AD_Reference_ID=53872;
	/** Shipped = Shipped */
	public static final String FTU_COMPONENTACTIONTYPE_Shipped = "Shipped";
	/** Received = Received */
	public static final String FTU_COMPONENTACTIONTYPE_Received = "Received";
	/** Scrapped = Scrapped */
	public static final String FTU_COMPONENTACTIONTYPE_Scrapped = "Scrapped";
	/** Added to inventory = Added */
	public static final String FTU_COMPONENTACTIONTYPE_AddedToInventory = "Added";
	/** Drawn from inentory = Drawn */
	public static final String FTU_COMPONENTACTIONTYPE_DrawnFromInentory = "Drawn";
	/** Installed = Installed */
	public static final String FTU_COMPONENTACTIONTYPE_Installed = "Installed";
	/** Uninstalled = Uninstalled */
	public static final String FTU_COMPONENTACTIONTYPE_Uninstalled = "Uninstalled";
	/** Created = Created */
	public static final String FTU_COMPONENTACTIONTYPE_Created = "Created";
	/** Set Action Type.
		@param FTU_ComponentActionType 
		The type of action performed on the component
	  */
	public void setFTU_ComponentActionType (String FTU_ComponentActionType)
	{

		set_ValueNoCheck (COLUMNNAME_FTU_ComponentActionType, FTU_ComponentActionType);
	}

	/** Get Action Type.
		@return The type of action performed on the component
	  */
	public String getFTU_ComponentActionType () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ComponentActionType);
	}

	/** Set Component History ID.
		@param FTU_ComponentHistory_ID Component History ID	  */
	public void setFTU_ComponentHistory_ID (int FTU_ComponentHistory_ID)
	{
		if (FTU_ComponentHistory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_ComponentHistory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_ComponentHistory_ID, Integer.valueOf(FTU_ComponentHistory_ID));
	}

	/** Get Component History ID.
		@return Component History ID	  */
	public int getFTU_ComponentHistory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_ComponentHistory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_M_InOutLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
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
			set_Value (COLUMNNAME_M_InventoryLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_InventoryLine_ID, Integer.valueOf(M_InventoryLine_ID));
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

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
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
			set_Value (COLUMNNAME_M_MovementLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
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

	public com.mckayerp.ftu.model.I_FTU_Component getParentComponent() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Component)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Component.Table_Name)
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
}