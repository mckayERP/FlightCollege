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

/** Generated Model for FTU_MaintWORL_Detail
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintWORL_Detail extends PO implements I_FTU_MaintWORL_Detail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_MaintWORL_Detail (Properties ctx, int FTU_MaintWORL_Detail_ID, String trxName)
    {
      super (ctx, FTU_MaintWORL_Detail_ID, trxName);
      /** if (FTU_MaintWORL_Detail_ID == 0)
        {
			setCT_ComponentActionType (null);
			setDateAction (new Timestamp( System.currentTimeMillis() ));
			setFTU_MaintWOResultLine_ID (0);
			setFTU_MaintWORL_Detail_ID (0);
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM FTU_MaintWORL_Detail WHERE FTU_MaintWOResultLine_ID=@FTU_MaintWOResultLine_ID@
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintWORL_Detail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintWORL_Detail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
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

		set_Value (COLUMNNAME_CT_ComponentActionType, CT_ComponentActionType);
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
			set_Value (COLUMNNAME_CT_ComponentBOMLine_ID, null);
		else 
			set_Value (COLUMNNAME_CT_ComponentBOMLine_ID, Integer.valueOf(CT_ComponentBOMLine_ID));
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

	/** Set Action Date.
		@param DateAction 
		The date the action took place
	  */
	public void setDateAction (Timestamp DateAction)
	{
		set_Value (COLUMNNAME_DateAction, DateAction);
	}

	/** Get Action Date.
		@return The date the action took place
	  */
	public Timestamp getDateAction () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAction);
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
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResultLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResultLine_ID, Integer.valueOf(FTU_MaintWOResultLine_ID));
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

	/** Set Maintenance Work Order Result Line Detail ID.
		@param FTU_MaintWORL_Detail_ID Maintenance Work Order Result Line Detail ID	  */
	public void setFTU_MaintWORL_Detail_ID (int FTU_MaintWORL_Detail_ID)
	{
		if (FTU_MaintWORL_Detail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWORL_Detail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWORL_Detail_ID, Integer.valueOf(FTU_MaintWORL_Detail_ID));
	}

	/** Get Maintenance Work Order Result Line Detail ID.
		@return Maintenance Work Order Result Line Detail ID	  */
	public int getFTU_MaintWORL_Detail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWORL_Detail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reset Component Life.
		@param IsResetComponentLife 
		Reset the Component Life used to zero and increment the life cycle count.
	  */
	public void setIsResetComponentLife (boolean IsResetComponentLife)
	{
		set_Value (COLUMNNAME_IsResetComponentLife, Boolean.valueOf(IsResetComponentLife));
	}

	/** Get Reset Component Life.
		@return Reset the Component Life used to zero and increment the life cycle count.
	  */
	public boolean isResetComponentLife () 
	{
		Object oo = get_Value(COLUMNNAME_IsResetComponentLife);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_ParentComponent_ID, null);
		else 
			set_Value (COLUMNNAME_ParentComponent_ID, Integer.valueOf(ParentComponent_ID));
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

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
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

	public com.mckayerp.model.I_CT_ComponentBOMLine getTarget_ComponentBOMLine() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_ComponentBOMLine)MTable.get(getCtx(), com.mckayerp.model.I_CT_ComponentBOMLine.Table_Name)
			.getPO(getTarget_ComponentBOMLine_ID(), get_TrxName());	}

	/** Set Target BOM Line.
		@param Target_ComponentBOMLine_ID 
		The target component BOM line for the install operation.
	  */
	public void setTarget_ComponentBOMLine_ID (int Target_ComponentBOMLine_ID)
	{
		if (Target_ComponentBOMLine_ID < 1) 
			set_Value (COLUMNNAME_Target_ComponentBOMLine_ID, null);
		else 
			set_Value (COLUMNNAME_Target_ComponentBOMLine_ID, Integer.valueOf(Target_ComponentBOMLine_ID));
	}

	/** Get Target BOM Line.
		@return The target component BOM line for the install operation.
	  */
	public int getTarget_ComponentBOMLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Target_ComponentBOMLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.model.I_CT_Component getTarget_ParentComponent() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getTarget_ParentComponent_ID(), get_TrxName());	}

	/** Set Target Parent Component.
		@param Target_ParentComponent_ID 
		The target parent component into which the component will be installed.
	  */
	public void setTarget_ParentComponent_ID (int Target_ParentComponent_ID)
	{
		if (Target_ParentComponent_ID < 1) 
			set_Value (COLUMNNAME_Target_ParentComponent_ID, null);
		else 
			set_Value (COLUMNNAME_Target_ParentComponent_ID, Integer.valueOf(Target_ParentComponent_ID));
	}

	/** Get Target Parent Component.
		@return The target parent component into which the component will be installed.
	  */
	public int getTarget_ParentComponent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Target_ParentComponent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.model.I_CT_Component getTarget_RootComponent() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_Component)MTable.get(getCtx(), com.mckayerp.model.I_CT_Component.Table_Name)
			.getPO(getTarget_RootComponent_ID(), get_TrxName());	}

	/** Set Target Root Component.
		@param Target_RootComponent_ID 
		The target root component or assembly into which the component will be installed.
	  */
	public void setTarget_RootComponent_ID (int Target_RootComponent_ID)
	{
		if (Target_RootComponent_ID < 1) 
			set_Value (COLUMNNAME_Target_RootComponent_ID, null);
		else 
			set_Value (COLUMNNAME_Target_RootComponent_ID, Integer.valueOf(Target_RootComponent_ID));
	}

	/** Get Target Root Component.
		@return The target root component or assembly into which the component will be installed.
	  */
	public int getTarget_RootComponent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Target_RootComponent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}