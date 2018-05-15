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

package com.mckayerp.ftu.process;

import java.util.Properties;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.util.Env;

import com.mckayerp.ftu.model.MFTUMaintWORLDetail;
import com.mckayerp.ftu.model.MFTUMaintWOResultLine;
import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentBOMLine;

/** Generated Process for (WO Result Line Detail Process)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public class FTU_MaintWORL_Detail_Process extends FTU_MaintWORL_Detail_ProcessAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		int ftu_maintWORL_detail_id = this.getRecord_ID();
		Properties ctx = this.getCtx();
		String trxName = this.get_TrxName();
		
		MFTUMaintWORLDetail mrld = new MFTUMaintWORLDetail(ctx, ftu_maintWORL_detail_id, trxName);
		if (mrld == null || mrld.getFTU_MaintWORL_Detail_ID() <= 0)
			return "";

		if (mrld.isProcessed())
			return "";

		if (mrld.getQty().compareTo(Env.ZERO) == 0)
		{
			return "@Error@ @Qty@ = 0";
		}

		MCTComponent comp = null;
		if (mrld.getCT_Component_ID() <= 0)
		{
			// if no component is identified, try to create one.
			if (mrld.getM_Product_ID() <= 0)
				return "@Error@ @CT_Component_ID@ = 0 & @M_Product_ID@ = 0";
			
			comp = MCTComponent.getCreateByProductAndASI(getCtx(), mrld.getM_Product_ID(), mrld.getM_AttributeSetInstance_ID(), get_TrxName());
			if (comp != null && comp.getCT_Component_ID() > 0)
			{
				mrld.setCT_Component_ID(comp.getCT_Component_ID());
				mrld.setCT_ComponentLifeAtAction(comp.getLifeUsed());
				mrld.setOverhaulCount(comp.getOverhaulCount());
			}
			else
			{
				return "@Error@ @CT_Component_ID@ could not be created";
			}
		}
		else
		{
			comp = (MCTComponent) mrld.getCT_Component();
		}
		
		//  Check if the component has instance values.  If so, the quantity can only be one.
		if (((MAttributeSetInstance) comp.getM_AttributeSetInstance()).hasInstanceValues())
		{
			if (mrld.getQty().compareTo(Env.ONE) != 0)
			{
				return "@Error@ @Qty@ != 1";
			}
		}
		
		if (UNINSTALLED.equals(mrld.getCT_ComponentActionType()))
		{
			
			// Uninstall the component
			// The BOM line must be specified
			MCTComponentBOMLine bomLine = (MCTComponentBOMLine) mrld.getCT_ComponentBOMLine();
			if (bomLine == null || bomLine.getCT_Component_ID() != mrld.getCT_Component_ID())
			{
				return "@Error@ @CT_ComponentBOMLine_ID@ is null or @CT_Component_ID@ does not match.";
			}
			
			if (!bomLine.uninstallComponent(mrld.getQty(), mrld.getFTU_MaintWOResultLine_ID()))
			{
				return "@Error@ - unable to uninstall product to that BOM line.";				
			}
			bomLine.saveEx();
		}
		else if (INSTALLED.equals(mrld.getCT_ComponentActionType()))
		{
			// Install the component
		
			//  Check if the component needs to be overhauled.
			//  This happens when the life is reset.
			if(mrld.isResetComponentLife())
			{
				//  An overhaul has been completed.
				//  Need to increment the component overhaul count and reset the life used to zero
				MCTComponent component = (MCTComponent) mrld.getCT_Component();
				if (!component.overhaul(mrld.getFTU_MaintWOResultLine_ID(), mrld.getDateAction()))
				{
					return "@Error@ - Component Max Overhaul Cycles exceeded.";
				}
			}
			// The BOM line must be specified
			MCTComponentBOMLine bomLine = (MCTComponentBOMLine) mrld.getTarget_ComponentBOMLine();
			if (bomLine == null)
			{
				return "@Error@ @Target_ComponentBOMLine_ID@ is null";
			}
			
			if (!bomLine.installComponent(mrld.getCT_Component_ID(), mrld.getQty(), mrld.getFTU_MaintWOResultLine_ID(), mrld.getDateAction()))
			{
				return "@Error@ - unable to install product to that BOM line.";
			}
			bomLine.saveEx();
		}

		mrld.setProcessed(true);
		mrld.saveEx();
		return "";
	}
}