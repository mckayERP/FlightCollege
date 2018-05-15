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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for FTU_MaintRequirementLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_MaintRequirementLine extends PO implements I_FTU_MaintRequirementLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180407L;

    /** Standard Constructor */
    public X_FTU_MaintRequirementLine (Properties ctx, int FTU_MaintRequirementLine_ID, String trxName)
    {
      super (ctx, FTU_MaintRequirementLine_ID, trxName);
      /** if (FTU_MaintRequirementLine_ID == 0)
        {
			setFTU_Action (null);
			setFTU_MaintRequirement_ID (0);
			setFTU_MaintRequirementLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FTU_MaintRequirementLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MaintRequirementLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** CT_ComponentResolutionType AD_Reference_ID=53872 */
	public static final int CT_COMPONENTRESOLUTIONTYPE_AD_Reference_ID=53872;
	/** Shipped = Shipped */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Shipped = "Shipped";
	/** Received = Received */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Received = "Received";
	/** Scrapped = Scrapped */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Scrapped = "Scrapped";
	/** Added to inventory = Added */
	public static final String CT_COMPONENTRESOLUTIONTYPE_AddedToInventory = "Added";
	/** Drawn from inentory = Drawn */
	public static final String CT_COMPONENTRESOLUTIONTYPE_DrawnFromInentory = "Drawn";
	/** Installed = Installed */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Installed = "Installed";
	/** Uninstalled = Uninstalled */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Uninstalled = "Uninstalled";
	/** Created = Created */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Created = "Created";
	/** Inspected = Inspected */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Inspected = "Inspected";
	/** Overhauled = Overhauled */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Overhauled = "Overhauled";
	/** Repaired = Repaired */
	public static final String CT_COMPONENTRESOLUTIONTYPE_Repaired = "Repaired";
	/** Set Resolution Type.
		@param CT_ComponentResolutionType 
		The type of action performed on the component to resolve a maintenance requirement
	  */
	public void setCT_ComponentResolutionType (String CT_ComponentResolutionType)
	{

		set_Value (COLUMNNAME_CT_ComponentResolutionType, CT_ComponentResolutionType);
	}

	/** Get Resolution Type.
		@return The type of action performed on the component to resolve a maintenance requirement
	  */
	public String getCT_ComponentResolutionType () 
	{
		return (String)get_Value(COLUMNNAME_CT_ComponentResolutionType);
	}

	public com.mckayerp.model.I_CT_DataSet getCT_DataSet() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_DataSet)MTable.get(getCtx(), com.mckayerp.model.I_CT_DataSet.Table_Name)
			.getPO(getCT_DataSet_ID(), get_TrxName());	}

	/** Set Data Set.
		@param CT_DataSet_ID 
		A definition of a set of data.
	  */
	public void setCT_DataSet_ID (int CT_DataSet_ID)
	{
		if (CT_DataSet_ID < 1) 
			set_Value (COLUMNNAME_CT_DataSet_ID, null);
		else 
			set_Value (COLUMNNAME_CT_DataSet_ID, Integer.valueOf(CT_DataSet_ID));
	}

	/** Get Data Set.
		@return A definition of a set of data.
	  */
	public int getCT_DataSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_DataSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_M_Product getFTU_AppliesToProduct() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getFTU_AppliesToProduct_ID(), get_TrxName());	}

	/** Set Applies to Product.
		@param FTU_AppliesToProduct_ID 
		The product to which the maintenance requirement or schedule applies.
	  */
	public void setFTU_AppliesToProduct_ID (int FTU_AppliesToProduct_ID)
	{
		if (FTU_AppliesToProduct_ID < 1) 
			set_Value (COLUMNNAME_FTU_AppliesToProduct_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_AppliesToProduct_ID, Integer.valueOf(FTU_AppliesToProduct_ID));
	}

	/** Get Applies to Product.
		@return The product to which the maintenance requirement or schedule applies.
	  */
	public int getFTU_AppliesToProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_AppliesToProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Maintenance Requirement Line.
		@param FTU_MaintRequirementLine_ID Maintenance Requirement Line	  */
	public void setFTU_MaintRequirementLine_ID (int FTU_MaintRequirementLine_ID)
	{
		if (FTU_MaintRequirementLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirementLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MaintRequirementLine_ID, Integer.valueOf(FTU_MaintRequirementLine_ID));
	}

	/** Get Maintenance Requirement Line.
		@return Maintenance Requirement Line	  */
	public int getFTU_MaintRequirementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MaintRequirementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Process.
		@param FTU_Process 
		A text description of the process to follow.
	  */
	public void setFTU_Process (String FTU_Process)
	{
		set_Value (COLUMNNAME_FTU_Process, FTU_Process);
	}

	/** Get Process.
		@return A text description of the process to follow.
	  */
	public String getFTU_Process () 
	{
		return (String)get_Value(COLUMNNAME_FTU_Process);
	}

	/** Set Resolution Fault Found Template.
		@param FTU_ResolutionFFTemplate 
		A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action when a fault is found.
	  */
	public void setFTU_ResolutionFFTemplate (String FTU_ResolutionFFTemplate)
	{
		set_Value (COLUMNNAME_FTU_ResolutionFFTemplate, FTU_ResolutionFFTemplate);
	}

	/** Get Resolution Fault Found Template.
		@return A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action when a fault is found.
	  */
	public String getFTU_ResolutionFFTemplate () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ResolutionFFTemplate);
	}

	/** Set Resolution Template.
		@param FTU_ResolutionTemplate 
		A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action.
	  */
	public void setFTU_ResolutionTemplate (String FTU_ResolutionTemplate)
	{
		set_Value (COLUMNNAME_FTU_ResolutionTemplate, FTU_ResolutionTemplate);
	}

	/** Get Resolution Template.
		@return A text string that will be used as a template in the maintenance work order result for the resolution of the maintenance action.
	  */
	public String getFTU_ResolutionTemplate () 
	{
		return (String)get_Value(COLUMNNAME_FTU_ResolutionTemplate);
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
}