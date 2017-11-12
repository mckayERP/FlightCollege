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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for FTU_MaintWOResultLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintWOResultLine extends PO implements I_FTU_MaintWOResultLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170425L;

    /** Standard Constructor */
    public X_FTU_MaintWOResultLine (Properties ctx, int FTU_MaintWOResultLine_ID, String trxName)
    {
      super (ctx, FTU_MaintWOResultLine_ID, trxName);
      /** if (FTU_MaintWOResultLine_ID == 0)
        {
			setFTU_MaintActionTaken (null);
			setFTU_MaintWOResult_ID (0);
			setFTU_MaintWOResultLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintWOResultLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintWOResultLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Action.
		@param FTU_Action 
		The action that must be taken to address the maintenance requirement
	  */
	public void setFTU_Action (String FTU_Action)
	{
		set_Value (COLUMNNAME_FTU_Action, FTU_Action);
	}

	/** Get Action.
		@return The action that must be taken to address the maintenance requirement
	  */
	public String getFTU_Action () 
	{
		return (String)get_Value(COLUMNNAME_FTU_Action);
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
			set_Value (COLUMNNAME_FTU_Component_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Component_ID, Integer.valueOf(FTU_Component_ID));
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

		set_Value (COLUMNNAME_FTU_ComponentActionType, FTU_ComponentActionType);
	}

	/** Get Action Type.
		@return The type of action performed on the component
	  */
	public String getFTU_ComponentActionType () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ComponentActionType);
	}

	/** Set Component Life.
		@param FTU_ComponentLifeAtAction 
		The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public void setFTU_ComponentLifeAtAction (BigDecimal FTU_ComponentLifeAtAction)
	{
		set_Value (COLUMNNAME_FTU_ComponentLifeAtAction, FTU_ComponentLifeAtAction);
	}

	/** Get Component Life.
		@return The component life used at the time of the action. For example, the time in service of a replacement part at the time of install.
	  */
	public BigDecimal getFTU_ComponentLifeAtAction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FTU_ComponentLifeAtAction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Action Taken.
		@param FTU_MaintActionTaken 
		The maintenance action taken to satisfy the maintenance requirement.
	  */
	public void setFTU_MaintActionTaken (String FTU_MaintActionTaken)
	{
		set_Value (COLUMNNAME_FTU_MaintActionTaken, FTU_MaintActionTaken);
	}

	/** Get Action Taken.
		@return The maintenance action taken to satisfy the maintenance requirement.
	  */
	public String getFTU_MaintActionTaken () 
	{
		return (String)get_Value(COLUMNNAME_FTU_MaintActionTaken);
	}

	public com.mckayerp.ftu.model.I_FTU_MaintRequirement getFTU_MaintRequirement() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintRequirement)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintRequirement.Table_Name)
			.getPO(getFTU_MaintRequirement_ID(), get_TrxName());	}

	/** Set Maintenance Requirement.
		@param FTU_MaintRequirement_ID 
		A requirement to perform some maintenance action due to a snag, preventive maintenance or other corrective action.
	  */
	public void setFTU_MaintRequirement_ID (int FTU_MaintRequirement_ID)
	{
		if (FTU_MaintRequirement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirement_ID, Integer.valueOf(FTU_MaintRequirement_ID));
	}

	/** Get Maintenance Requirement.
		@return A requirement to perform some maintenance action due to a snag, preventive maintenance or other corrective action.
	  */
	public int getFTU_MaintRequirement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintRequirement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintRequirementLine getFTU_MaintRequirementLine() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintRequirementLine)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintRequirementLine.Table_Name)
			.getPO(getFTU_MaintRequirementLine_ID(), get_TrxName());	}

	/** Set Maintenance Requirement Line ID.
		@param FTU_MaintRequirementLine_ID Maintenance Requirement Line ID	  */
	public void setFTU_MaintRequirementLine_ID (int FTU_MaintRequirementLine_ID)
	{
		if (FTU_MaintRequirementLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirementLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirementLine_ID, Integer.valueOf(FTU_MaintRequirementLine_ID));
	}

	/** Get Maintenance Requirement Line ID.
		@return Maintenance Requirement Line ID	  */
	public int getFTU_MaintRequirementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintRequirementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public com.mckayerp.ftu.model.I_FTU_MaintWOResult getFTU_MaintWOResult() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_MaintWOResult)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_MaintWOResult.Table_Name)
			.getPO(getFTU_MaintWOResult_ID(), get_TrxName());	}

	/** Set Maintenance Work Order Result ID.
		@param FTU_MaintWOResult_ID Maintenance Work Order Result ID	  */
	public void setFTU_MaintWOResult_ID (int FTU_MaintWOResult_ID)
	{
		if (FTU_MaintWOResult_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResult_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintWOResult_ID, Integer.valueOf(FTU_MaintWOResult_ID));
	}

	/** Get Maintenance Work Order Result ID.
		@return Maintenance Work Order Result ID	  */
	public int getFTU_MaintWOResult_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintWOResult_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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

	/** Set Completed.
		@param IsMaintReqCompleted 
		Is the maintenance requirement completed.
	  */
	public void setIsMaintReqCompleted (boolean IsMaintReqCompleted)
	{
		set_Value (COLUMNNAME_IsMaintReqCompleted, Boolean.valueOf(IsMaintReqCompleted));
	}

	/** Get Completed.
		@return Is the maintenance requirement completed.
	  */
	public boolean isMaintReqCompleted () 
	{
		Object oo = get_Value(COLUMNNAME_IsMaintReqCompleted);
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
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
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
}