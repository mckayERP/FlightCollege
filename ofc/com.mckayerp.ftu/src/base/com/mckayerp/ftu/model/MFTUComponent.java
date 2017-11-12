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
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

/**
 * Class MFTUComponent provides model support for component-level tracking of
 * products.  A component is a particular product and Attribute Set Instance
 * (ASI).  The tracking covers location in storage and installation in 
 * assemblies.
 * 
 * @author mckayERP
 *
 */
public class MFTUComponent extends X_FTU_Component {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5385593210662916999L;
	private MFTUComponentBOM m_bom;

	public MFTUComponent(Properties ctx, int M_Component_ID, String trxName) {
		super(ctx, M_Component_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUComponent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find the component entry for a product and attribute set instance pair.  Throws an error if more 
	 * than one matching component if found.
	 * @param ctx
	 * @param m_product_id
	 * @param m_attributeSetInstance_id
	 * @param trxName
	 * @return  The MFTUComponent represented by the product/ASI pair or null.
	 */
	public static MFTUComponent getByProductAndASI(Properties ctx, int m_product_id, int m_attributeSetInstance_id, String trxName)
	{
		String where = "M_Product_ID=? AND M_AttributeSetInstance_ID=?";
		
		MFTUComponent component = (MFTUComponent) new Query(ctx, MFTUComponent.Table_Name, where, trxName)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(m_product_id, m_attributeSetInstance_id)
								.firstOnly();
		
		return component;
	}

	/**
	 * Find the components of a product.
	 * @param ctx
	 * @param m_product_id
	 * @param trxName
	 * @return  A List of MFTUComponent that have the provided product ID.
	 */
	public static List<MFTUComponent> getByProduct(Properties ctx, int m_product_id, String trxName)
	{
		String where = "M_Product_ID=?";
		
		return new Query(ctx, MFTUComponent.Table_Name, where, trxName)
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.setParameters(m_product_id)
							.list();		
	}

	public int getParentComponent_ID() {
		// TODO Auto-generated method stub.  What if collective?
		return 0;
	}

	public BigDecimal getLife() {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal getParenetLife() {
		// TODO Auto-generated method stub.  What if collective?
		return null;
	}
	
	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success) {

		//  If new record, add created action to history
		if (newRecord) 
		{
			
			MFTUComponentHistory history = new MFTUComponentHistory(getCtx(), 0, get_TrxName());
			history.setFTU_Component_ID(getFTU_Component_ID());
			history.setFTU_ComponentActionType(MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Created);
			history.setMovementType(null);  // Can't be an empty string
			history.setDateAction(new Timestamp(System.currentTimeMillis()));
			history.setQty(Env.ZERO);
			history.setM_Locator_ID(0);
			history.setParentComponent_ID(0);
			history.setComponentLifeAtAction(this.getLifeUsed());  // TODO: Will be the current life, not the life at the movement date.
			history.setParentLifeAtAction(Env.ZERO); // TODO: will be the current life, not the life at the movement date.
			history.saveEx();
			
			// Copy the product BOM
			MProduct product =  (MProduct) getM_Product();
			if (product.isBOM())
			{
				
				List<MPPProductBOM> bomList = MPPProductBOM.getProductBOMs(product);
				for (MPPProductBOM bom : bomList)
				{
					
					if (bom.getBOMUse().equals(MPPProductBOM.BOMUSE_Master))
					{
						
						MFTUComponentBOM componentBOM = new MFTUComponentBOM(getCtx(), 0, get_TrxName());
						componentBOM.setFTU_Component_ID(getFTU_Component_ID());
						componentBOM.setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID());
						componentBOM.setName(bom.getName());
						componentBOM.setDescription(bom.getDescription());
						componentBOM.saveEx();

						for (MPPProductBOMLine bomLine : bom.getLines())
						{

							MFTUComponentBOMLine compBOMLine = new MFTUComponentBOMLine(getCtx(), 0, get_TrxName());
							compBOMLine.setFTU_ComponentBOM_ID(componentBOM.getFTU_ComponentBOM_ID());
							compBOMLine.setPP_Product_BOMLine_ID(bomLine.getPP_Product_BOMLine_ID());
							compBOMLine.setLine(bomLine.getLine());
							compBOMLine.setM_Product_ID(bomLine.getM_Product_ID());
							compBOMLine.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
							compBOMLine.setDescription(bomLine.getDescription());
							compBOMLine.setQtyRequired(bomLine.getQtyBOM());							
							compBOMLine.saveEx();
							
						}
					}	
				}
			}	
		}
		else if (is_ValueChanged(MFTUComponent.COLUMNNAME_LifeUsed))
		{
			updateSubComponentLife();
		}
		
		// TODO If life value changes manually?
		
		return success;
	}

	/**
	 * Update the life used of all subcomponents where the subcomponents 
	 * inherit the usage of the parent assembly.  Together with the 
	 * afterSave of the component, this function will recursively 
	 * descend the BOM tree until there are no subscomponents that 
	 * inherit their life from the parents.  Subassemblies that do not
	 * inherit life usage will not be explored.
	 */
	private void updateSubComponentLife() {
				
		if (getBOM() == null)
			return;
		
		List<MFTUComponentBOMLine> bomLines = getBOM().getLines();
		
		for (MFTUComponentBOMLine bomLine : bomLines)
		{
			MFTUComponent subComp = (MFTUComponent) bomLine.getFTU_Component();
			if (subComp != null && MFTUComponent.LIFEUSAGESOURCE_InheritFromParent.equals(subComp.getLifeUsageSource()))
			{
				bomLine.setCurrentParentLife(getLifeUsed());
				bomLine.setParentUsageSinceInstall(getLifeUsed().subtract(bomLine.getParentLifeAtInstall()));
				bomLine.setCompUsageSinceInstall(bomLine.getParentUsageSinceInstall());
				bomLine.setCurrentCompLife(bomLine.getCompLifeAtInstall().add(bomLine.getCompUsageSinceInstall()));
				bomLine.saveEx();
				
				subComp.setLifeUsed(bomLine.getCurrentCompLife());
				subComp.saveEx();
			}
		}
	}

	private MFTUComponentBOM getBOM() {

		if (m_bom == null)
		{

			String where = MFTUComponentBOM.COLUMNNAME_FTU_Component_ID + "=?";
			m_bom = new Query(this.getCtx(), MFTUComponentBOM.Table_Name, where, this.get_TrxName())
										.setClient_ID()
										.setOnlyActiveRecords(true)
										.setParameters(this.getFTU_Component_ID())
										.firstOnly();

		}
		return m_bom;
	}

	/** Set Product and associated component life cycle model
	 *  @param M_Product_ID 
	 *  Product, Service, Item
	 */
	public void setM_Product_ID (int M_Product_ID)
	{		
		super.setM_Product_ID(M_Product_ID);
		
		if (M_Product_ID > 0)
		{
			MProduct product = MProduct.get(getCtx(), M_Product_ID);
			MFTUCompLifeCycleModel model = MFTUCompLifeCycleModel.getByProduct(getCtx(), product, get_TrxName());
						
			if (model != null)
			{
				setLifeUsageSource(model.getLifeUsageSource());
				setLifeUsageUOM_ID(model.getLifeUsageUOM_ID());
				setMaxLifeUsage(model.getMaxLifeUsage());
				setIsLifeExtensionPossible(model.isLifeExtensionPossible());
			}

		}
		else
		{
			setLifeUsageSource(MFTUComponent.LIFEUSAGESOURCE_NotTracked);
			setLifeUsageUOM_ID(0);
			setMaxLifeUsage(null);
			setIsLifeExtensionPossible(false);			
		}
	}
}
