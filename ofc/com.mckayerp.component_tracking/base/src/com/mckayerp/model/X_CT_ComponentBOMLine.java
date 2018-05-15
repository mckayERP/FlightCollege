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

/** Generated Model for CT_ComponentBOMLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_CT_ComponentBOMLine extends PO implements I_CT_ComponentBOMLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_CT_ComponentBOMLine (Properties ctx, int CT_ComponentBOMLine_ID, String trxName)
    {
      super (ctx, CT_ComponentBOMLine_ID, trxName);
      /** if (CT_ComponentBOMLine_ID == 0)
        {
			setCT_ComponentBOMLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_CT_ComponentBOMLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CT_ComponentBOMLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Component life at install.
		@param CompLifeAtInstall 
		The existing life usage of the component when it was installed.
	  */
	public void setCompLifeAtInstall (BigDecimal CompLifeAtInstall)
	{
		set_Value (COLUMNNAME_CompLifeAtInstall, CompLifeAtInstall);
	}

	/** Get Component life at install.
		@return The existing life usage of the component when it was installed.
	  */
	public BigDecimal getCompLifeAtInstall () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CompLifeAtInstall);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Component usage since install.
		@param CompUsageSinceInstall 
		The difference bewteen the current component life and its life usage at installation
	  */
	public void setCompUsageSinceInstall (BigDecimal CompUsageSinceInstall)
	{
		set_ValueNoCheck (COLUMNNAME_CompUsageSinceInstall, CompUsageSinceInstall);
	}

	/** Get Component usage since install.
		@return The difference bewteen the current component life and its life usage at installation
	  */
	public BigDecimal getCompUsageSinceInstall () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CompUsageSinceInstall);
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

	public com.mckayerp.model.I_CT_ComponentBOM getCT_ComponentBOM() throws RuntimeException
    {
		return (com.mckayerp.model.I_CT_ComponentBOM)MTable.get(getCtx(), com.mckayerp.model.I_CT_ComponentBOM.Table_Name)
			.getPO(getCT_ComponentBOM_ID(), get_TrxName());	}

	/** Set Component BOM.
		@param CT_ComponentBOM_ID 
		The list of subcomponents in this assembly
	  */
	public void setCT_ComponentBOM_ID (int CT_ComponentBOM_ID)
	{
		if (CT_ComponentBOM_ID < 1) 
			set_Value (COLUMNNAME_CT_ComponentBOM_ID, null);
		else 
			set_Value (COLUMNNAME_CT_ComponentBOM_ID, Integer.valueOf(CT_ComponentBOM_ID));
	}

	/** Get Component BOM.
		@return The list of subcomponents in this assembly
	  */
	public int getCT_ComponentBOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CT_ComponentBOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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

	/** Set Current Component Life.
		@param CurrentCompLife 
		The current life usage of the component/assembly.
	  */
	public void setCurrentCompLife (BigDecimal CurrentCompLife)
	{
		set_Value (COLUMNNAME_CurrentCompLife, CurrentCompLife);
	}

	/** Get Current Component Life.
		@return The current life usage of the component/assembly.
	  */
	public BigDecimal getCurrentCompLife () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentCompLife);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Parent Life.
		@param CurrentParentLife 
		The current life usage of the parent component/assembly.
	  */
	public void setCurrentParentLife (BigDecimal CurrentParentLife)
	{
		set_Value (COLUMNNAME_CurrentParentLife, CurrentParentLife);
	}

	/** Get Current Parent Life.
		@return The current life usage of the parent component/assembly.
	  */
	public BigDecimal getCurrentParentLife () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentParentLife);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Date Installed.
		@param DateInstalled Date Installed	  */
	public void setDateInstalled (Timestamp DateInstalled)
	{
		set_Value (COLUMNNAME_DateInstalled, DateInstalled);
	}

	/** Get Date Installed.
		@return Date Installed	  */
	public Timestamp getDateInstalled () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateInstalled);
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

	/** Set Interval since install.
		@param IntervalInstalled 
		The time interval since the component was installed in the assembly in days.
	  */
	public void setIntervalInstalled (int IntervalInstalled)
	{
		throw new IllegalArgumentException ("IntervalInstalled is virtual column");	}

	/** Get Interval since install.
		@return The time interval since the component was installed in the assembly in days.
	  */
	public int getIntervalInstalled () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IntervalInstalled);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_M_Product getMaster_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getMaster_Product_ID(), get_TrxName());	}

	/** Set Product Master.
		@param Master_Product_ID 
		The product that appears on the Bill of Materials
	  */
	public void setMaster_Product_ID (int Master_Product_ID)
	{
		if (Master_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Master_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Master_Product_ID, Integer.valueOf(Master_Product_ID));
	}

	/** Get Product Master.
		@return The product that appears on the Bill of Materials
	  */
	public int getMaster_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Master_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Parent Life At install.
		@param ParentLifeAtInstall 
		The life usage of the parent component at the time of installation
	  */
	public void setParentLifeAtInstall (BigDecimal ParentLifeAtInstall)
	{
		set_Value (COLUMNNAME_ParentLifeAtInstall, ParentLifeAtInstall);
	}

	/** Get Parent Life At install.
		@return The life usage of the parent component at the time of installation
	  */
	public BigDecimal getParentLifeAtInstall () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ParentLifeAtInstall);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Parent usage since install.
		@param ParentUsageSinceInstall 
		The difference bewteen the current parent life and the parent life at install.
	  */
	public void setParentUsageSinceInstall (BigDecimal ParentUsageSinceInstall)
	{
		set_ValueNoCheck (COLUMNNAME_ParentUsageSinceInstall, ParentUsageSinceInstall);
	}

	/** Get Parent usage since install.
		@return The difference bewteen the current parent life and the parent life at install.
	  */
	public BigDecimal getParentUsageSinceInstall () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ParentUsageSinceInstall);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.eevolution.model.I_PP_Product_BOMLine getPP_Product_BOMLine() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Product_BOMLine)MTable.get(getCtx(), org.eevolution.model.I_PP_Product_BOMLine.Table_Name)
			.getPO(getPP_Product_BOMLine_ID(), get_TrxName());	}

	/** Set BOM Line.
		@param PP_Product_BOMLine_ID 
		BOM Line
	  */
	public void setPP_Product_BOMLine_ID (int PP_Product_BOMLine_ID)
	{
		if (PP_Product_BOMLine_ID < 1) 
			set_Value (COLUMNNAME_PP_Product_BOMLine_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Product_BOMLine_ID, Integer.valueOf(PP_Product_BOMLine_ID));
	}

	/** Get BOM Line.
		@return BOM Line
	  */
	public int getPP_Product_BOMLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Product_BOMLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Qty Installed.
		@param QtyInstalled 
		The quantity of a component currently installed
	  */
	public void setQtyInstalled (BigDecimal QtyInstalled)
	{
		set_Value (COLUMNNAME_QtyInstalled, QtyInstalled);
	}

	/** Get Qty Installed.
		@return The quantity of a component currently installed
	  */
	public BigDecimal getQtyInstalled () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInstalled);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Qty Required.
		@param QtyRequired 
		The quantity required to be installed.
	  */
	public void setQtyRequired (BigDecimal QtyRequired)
	{
		set_Value (COLUMNNAME_QtyRequired, QtyRequired);
	}

	/** Get Qty Required.
		@return The quantity required to be installed.
	  */
	public BigDecimal getQtyRequired () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyRequired);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}