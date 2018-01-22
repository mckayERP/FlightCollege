package com.mckayerp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.engine.IDocumentLine;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MMovement;
import org.compiere.model.MProduct;
import org.compiere.model.MProduction;
import org.compiere.model.MTransaction;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MPPProductBOMLine;

public class ComponentModelValidator implements ModelValidator {

	public ComponentModelValidator() {
		super();
	}

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());
	
	/** Client ID 		*/
	private int m_ad_client_id;

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// Not global
		if (client == null)
			return;
		
		if (client != null)
		{	
			m_ad_client_id = client.getAD_Client_ID();
		}
		
		engine.addDocValidate(MInOut.Table_Name, this);
		engine.addDocValidate(MProduction.Table_Name, this);
		engine.addDocValidate(MInventory.Table_Name, this);
		engine.addDocValidate(MMovement.Table_Name, this);
		
		engine.addModelChange(MCTComponentBOMLine.Table_Name, this);
		engine.addModelChange(MCTComponent.Table_Name, this);
		engine.addModelChange(MPPProductBOMLine.Table_Name, this);
		
	}

	@Override
	public int getAD_Client_ID() {

		return m_ad_client_id;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		
		log.finest("");
		
		boolean isAfterSave = (ModelValidator.TYPE_AFTER_CHANGE == type);
		boolean isBeforeSave = (ModelValidator.TYPE_BEFORE_CHANGE == type);
		boolean isBeforeDelete = (ModelValidator.TYPE_BEFORE_DELETE == type);
		
		if(isBeforeSave 
			&& po instanceof MCTComponentBOMLine)
		{
			return mc_componentBOMLine_beforeSave(po);
		} 

		if(isBeforeDelete 
				&& po instanceof MPPProductBOMLine)
		{
			return mc_MPPProductBOMLine_beforeDelete(po);
		} 

		if(isAfterSave 
			&& po instanceof MCTComponentBOMLine)
		{
			return mc_componenteBOMLine_afterSave(po);
		}  

		if(isAfterSave 
				&& po instanceof MCTComponent)
		{
			return mc_component_afterSave(po);
		}

		return null;
	}

	private String mc_MPPProductBOMLine_beforeDelete(PO po) {

		log.finest("");
		
		MPPProductBOMLine line = (MPPProductBOMLine) po;
		
		// Find the associated component BOM lines and delete them only if 
		// there is no component installed on that line.
		List<MCTComponentBOMLine> compBOMLines = MCTComponentBOMLine
					.getLinesByPPProductBOMLineID(po.getCtx(), line.getPP_Product_BOMLine_ID(), po.get_TrxName());
		
		for(MCTComponentBOMLine compBOMLine : compBOMLines)
		{
			// Delete only if there is no component installed.
			if (compBOMLine.getCT_Component_ID() <= 0)
			{
				// Delete the component BOM Line
				// This may fail (throw an error) if there are associated history records
				List <MCTComponentHistory> history = MCTComponentHistory.getByComponentBOMLine(po.getCtx(), compBOMLine.getCT_ComponentBOMLine_ID(), po.get_TrxName());
				if (history.size() > 0)
				{
					return Msg.parseTranslation(po.getCtx(), "@Error@ @CT_ComponentHistory_ID@ @Exists@ " + history.toString());
				}
				compBOMLine.deleteEx(true);  
			}
			else
			{
				return Msg.parseTranslation(po.getCtx(), "@Error@ " +  compBOMLine.getCT_Component().toString() + " installed.");  //TODO translation
			}
		}
		
		return null;
	}

	private String mc_component_afterSave(PO po) {
		
		log.finest("");
		
		MCTComponent component = (MCTComponent) po;
		if (component.is_ValueChanged(MCTComponent.COLUMNNAME_LifeUsed))
		{
			
			MCTComponentBOM compBOM = MCTComponentBOM.getByCT_Component_ID(po.getCtx(), component.getCT_Component_ID(), po.get_TrxName());
			
			if (compBOM != null)
			{
				
				List<MCTComponentBOMLine> lines = compBOM.getLines();
				
				for (MCTComponentBOMLine line : lines)
				{
					MCTComponent subComp = (MCTComponent) line.getCT_Component();
					
					if (subComp.getLifeUsageSource().equals(MCTComponent.LIFEUSAGESOURCE_InheritFromParent))
					{
						
						line.setCurrentParentLife(component.getLifeUsed());

						BigDecimal usageSinceInstall = line.getCurrentParentLife().subtract(line.getParentLifeAtInstall());
						BigDecimal currentLife = line.getCompLifeAtInstall().add(usageSinceInstall);
						
						line.setParentUsageSinceInstall(usageSinceInstall);
						
						// TODO - deal with different UOM?  If inherit from Parent - need check that UOM is the same.
						line.setCompUsageSinceInstall(usageSinceInstall);
						line.setCurrentCompLife(currentLife);
						line.saveEx();
						
						subComp.setLifeUsed(currentLife);  // Will trigger other subcomponents
						subComp.saveEx();
						
					}
				}
			}
		}

		return null;
	}

	private String mc_componenteBOMLine_afterSave(PO po) {
		
		log.finest("");

		MCTComponentBOMLine bomLine = (MCTComponentBOMLine) po;
		
		// New component ID install
		// TODO test for source i.e. Work Order - WO line should be recorded in history
		if (bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) 
				&& bomLine.get_ValueAsInt(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) > 0)
		{
			// Install !!
			MCTComponentBOM bom = (MCTComponentBOM) bomLine.getCT_ComponentBOM();
			MCTComponent component = (MCTComponent) bomLine.getCT_Component();
			MCTComponent parent = (MCTComponent) bom.getCT_Component();

			MCTComponentHistory history = new MCTComponentHistory(po.getCtx(), 0, po.get_TrxName());
			history.setCT_Component_ID(component.getCT_Component_ID());
			history.setCT_ComponentActionType(MCTComponentHistory.CT_COMPONENTACTIONTYPE_Installed);
			history.setDateAction(bomLine.getDateInstalled());
			history.setQty(bomLine.getQtyInstalled());
			history.setParentComponent_ID(parent.getCT_Component_ID());
			history.setComponentLifeAtAction(bomLine.getCompLifeAtInstall());
			history.setParentLifeAtAction(bomLine.getParentLifeAtInstall());
			history.setCT_ComponentBOMLine_ID(bomLine.getCT_ComponentBOMLine_ID());
			history.saveEx();
			
			// Update the component's root component id. This should match the parent 
			// or be the parent, if the parent is a root component.
			int root_component_id = parent.getRoot_Component_ID();
			if (root_component_id == 0)
				root_component_id = parent.getCT_Component_ID();
			component.setRoot_Component_ID(root_component_id); // Will trigger updates in sub components
			component.saveEx();
				
		}
		
		// Old component uninstalled
		if (bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) 
				&& bomLine.get_ValueOldAsInt(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) > 0)
		{
			
			// Uninstall !!
			int ct_component_id = bomLine.get_ValueOldAsInt(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID);
			MCTComponent component = new MCTComponent(po.getCtx(), ct_component_id, po.get_TrxName());

			MCTComponentBOM bom = (MCTComponentBOM) bomLine.getCT_ComponentBOM();
			MCTComponent parent = (MCTComponent) bom.getCT_Component();

			MCTComponentHistory history = new MCTComponentHistory(po.getCtx(), 0, po.get_TrxName());
			history.setCT_Component_ID(component.getCT_Component_ID());
			history.setCT_ComponentActionType(MCTComponentHistory.CT_COMPONENTACTIONTYPE_Uninstalled);
			history.setDateAction(new Timestamp (System.currentTimeMillis()));
			// TODO quantity on uninstall?
			if (bomLine.get_ValueOld(MCTComponentBOMLine.COLUMNNAME_QtyInstalled) == null) // Shouldn't happen
			{
				history.setQty(Env.ZERO);					
			}
			else
			{
				history.setQty(bomLine.getQtyInstalled().subtract((BigDecimal) bomLine.get_ValueOld(MCTComponentBOMLine.COLUMNNAME_QtyInstalled)));
			}
			history.setParentComponent_ID(parent.getCT_Component_ID());
			history.setCT_ComponentBOMLine_ID(bomLine.getCT_ComponentBOMLine_ID());
			history.setComponentLifeAtAction(component.getLifeUsed());
			history.setParentLifeAtAction(parent.getLifeUsed());
			history.saveEx();
			
			// Update the root component for the uninstalled component and its sub components
			component.setRoot_Component_ID(0);  // Will trigger updates in sub components.
			component.saveEx();
			
		}
		
		if (bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_CurrentCompLife))
		{
			// Installing a sub component and manually updating the life.
			// Assume the Parent Life at Install and Component Life At Install are correct
			// as the user could be manually creating the history.  Find the Parent current life.
			// Calculate the usage and component life.  Update the component life.
			
			MCTComponent subComp = (MCTComponent) bomLine.getCT_Component();
			if (subComp != null && subComp.getCT_Component_ID() > 0 )
			{				
				subComp.setLifeUsed(bomLine.getCurrentCompLife());  // Will trigger other subcomponents
				subComp.saveEx();
			}

		}

		return null;
	}

	private String mc_componentBOMLine_beforeSave(PO po) {
		
		log.finest("");
		
		MCTComponentBOMLine bomLine = (MCTComponentBOMLine) po;
		
		// Prevent isActive = false if a subcomponent is installed
		if (bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_IsActive)
				&& !bomLine.isActive()
				&& bomLine.getCT_Component_ID() > 0)
		{
			// A component is installed
			return Msg.parseTranslation(po.getCtx(), "@Error@ @CT_Component_ID@ != 0");
		}
		
		
		// Check if the component was uninstalled by setting ASI == 0;
		if (bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_M_AttributeSetInstance_ID)
				&& bomLine.get_ValueAsInt(MCTComponentBOMLine.COLUMNNAME_M_AttributeSetInstance_ID) == 0
				&& bomLine.get_ValueAsInt(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) > 0)
		{
			
			//  Uninstall the component
			bomLine.setM_Product_ID(0);
			bomLine.setCT_Component_ID(0);  // This will cause history actions below
			
		}
		
		if (bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_M_AttributeSetInstance_ID)
			&& bomLine.get_ValueAsInt(MCTComponentBOMLine.COLUMNNAME_M_AttributeSetInstance_ID) > 0
			&& bomLine.get_ValueAsInt(MCTComponentBOMLine.COLUMNNAME_M_Product_ID) > 0
			&& bomLine.get_ValueAsInt(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) == 0)
		{
			// New component?
			int m_product_id = bomLine.getM_Product_ID();
			int m_attributeSetInstance_id = bomLine.getM_AttributeSetInstance_ID();
			
			MProduct product = MProduct.get(po.getCtx(), m_product_id);
			if (product.isTrackAsComponent())
			{
				// Check if the component exists. If not create it.
				MCTComponent component = MCTComponent.getCreateByProductAndASI(po.getCtx(), m_product_id, m_attributeSetInstance_id, po.get_TrxName());
				MAttributeSet as = (MAttributeSet) bomLine.getM_AttributeSetInstance().getM_AttributeSet();
				
				if (as.isInstanceAttribute())  // ASI should be unique
				{
					// Check that it is not already installed somewhere - shouldn't happen
					String where = MCTComponentBOMLine.COLUMNNAME_CT_Component_ID + "=?";
					int count = new Query(po.getCtx(), MCTComponentBOMLine.Table_Name, where, po.get_TrxName())
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.setParameters(component.getCT_Component_ID())
							.count();
					if (count > 0)
					{
						return "@Error@ @AlreadyInstalled@";
					}
					else
					{
						bomLine.setQtyInstalled(Env.ONE); // Unique ASI - only one is possible
					}
					
				}
				
				bomLine.setCT_Component_ID(component.getCT_Component_ID());
				
			}
		}
			
		
		if (bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) 
				&& bomLine.get_ValueAsInt(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) > 0)
		{
			// Install !!
			MCTComponentBOM bom = (MCTComponentBOM) bomLine.getCT_ComponentBOM();
			MCTComponent component = (MCTComponent) bomLine.getCT_Component();
			MCTComponent parent = (MCTComponent) bom.getCT_Component();
			
			if(bomLine.getDateInstalled() == null)
				bomLine.setDateInstalled(new Timestamp (System.currentTimeMillis()));

			if(bomLine.getQtyInstalled() == null)
				bomLine.setQtyInstalled(Env.ONE);

			bomLine.setParentLifeAtInstall(parent.getLifeUsed());
			bomLine.setCurrentParentLife(parent.getLifeUsed());
			bomLine.setCompLifeAtInstall(component.getLifeUsed());
			bomLine.setCurrentCompLife(component.getLifeUsed());
			bomLine.setParentUsageSinceInstall(Env.ZERO);
			bomLine.setCompUsageSinceInstall(Env.ZERO);
								
		}
		
		if (bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) 
				&& bomLine.get_ValueAsInt(MCTComponentBOMLine.COLUMNNAME_CT_Component_ID) <= 0)
		{
			
			// Uninstall
			bomLine.setM_Product_ID(0);
			bomLine.setM_AttributeSetInstance_ID(0);					
			bomLine.setQtyInstalled(Env.ZERO);
			bomLine.setDateInstalled(null);
			bomLine.setParentLifeAtInstall(Env.ZERO);
			bomLine.setCurrentParentLife(Env.ZERO);
			bomLine.setCompLifeAtInstall(Env.ZERO);
			bomLine.setCurrentCompLife(Env.ZERO);
			bomLine.setParentUsageSinceInstall(Env.ZERO);
			bomLine.setCompUsageSinceInstall(Env.ZERO);
			
		}
		
		if ((bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_ParentLifeAtInstall)
			|| bomLine.is_ValueChanged(MCTComponentBOMLine.COLUMNNAME_CompLifeAtInstall))
			&& bomLine.getCT_Component_ID() > 0)
		{
			// Installing a sub component and manually updating the life.
			// Assume the Parent Life at Install and Component Life At Install are correct
			// as the user could be manually creating the history.  Find the Parent current life.
			// Calculate the usage and component life.  Update the component life.

			MCTComponent subComp = (MCTComponent) bomLine.getCT_Component();
			
			if (subComp.getLifeUsageSource().equals(MCTComponent.LIFEUSAGESOURCE_InheritFromParent))
			{
				BigDecimal usageSinceInstall = bomLine.getCurrentParentLife().subtract(bomLine.getParentLifeAtInstall());
				BigDecimal currentLife = bomLine.getCompLifeAtInstall().add(usageSinceInstall);
									
				bomLine.setParentUsageSinceInstall(usageSinceInstall);
				
				// TODO - deal with different UOM?  If inherit from Parent - need check that UOM is the same.
				bomLine.setCompUsageSinceInstall(usageSinceInstall);
				bomLine.setCurrentCompLife(currentLife);
				
			}
		}
		
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		
		boolean isCompleted = (ModelValidator.TIMING_AFTER_COMPLETE == timing);

		if (!isCompleted)
			return "";
		
		IDocumentLine[] lines = null;
		
		if(po instanceof MInOut)
		{
			lines = (IDocumentLine[]) ((MInOut) po).getLines();
		}
		else if (po instanceof MInventory)
		{
			lines = (IDocumentLine[]) ((MInventory) po).getLines(false);
		}
		else if (po instanceof MMovement)
		{
			lines = (IDocumentLine[]) ((MMovement) po).getLines(false);
		}
		else
		{
			return "";
		}

		if (lines != null && lines.length > 0)
		{
			
			log.finest("");

			for (IDocumentLine line : lines)
			{
				
				int m_product_id = line.getM_Product_ID();
				int m_attributeSetInstance_id = line.getM_AttributeSetInstanceTo_ID();
				
				MProduct product = MProduct.get(po.getCtx(), m_product_id);
				if (product.isTrackAsComponent())
				{
					// Check if the component exists. If not create it.
					MCTComponent component = MCTComponent.getCreateByProductAndASI(po.getCtx(), m_product_id, m_attributeSetInstance_id, po.get_TrxName());
					
					// Track component
					// TODO: issues to/from projects
					// MInOut
					if (po instanceof MInOut)
					{
						//	Incoming Trx are positive receipts or negative shipments
						String movementType = line.getMovementType();
						BigDecimal movementQty = line.getMovementQty();
						boolean incomingTrx = MTransaction.isIncomingTransaction(movementType) && movementQty.signum() >= 0
											|| !MTransaction.isIncomingTransaction(movementType) && movementQty.signum() < 0;	//	V+ Vendor Receipt

						String action = MCTComponentHistory.CT_COMPONENTACTIONTYPE_Shipped;
						component.setRoot_Locator_ID(0);
						component.setRoot_InOut_ID(line.get_ID());							
						if (incomingTrx) 
						{
							action = MCTComponentHistory.CT_COMPONENTACTIONTYPE_Received;
							component.setRoot_Locator_ID(line.getM_LocatorTo_ID());
							component.setRoot_InOut_ID(0);							
						}
						
						addHistoryFromDocLine(po.getCtx(), component, line, line.getM_Locator_ID(), 0, action, movementType, po.get_TrxName());
						
					}
					else if (po instanceof MInventory)
					{
						//  Component added/removed from inventory
						//	Incoming Trx are positive receipts or negative shipments
						String movementType = line.getMovementType();
						BigDecimal movementQty = line.getMovementQty();
						boolean incomingTrx = MTransaction.isIncomingTransaction(movementType) && movementQty.signum() >= 0
											|| !MTransaction.isIncomingTransaction(movementType) && movementQty.signum() < 0;	//	V+ Vendor Receipt

						String action = MCTComponentHistory.CT_COMPONENTACTIONTYPE_Scrapped;
						component.setRoot_Locator_ID(0);
						if (incomingTrx) 
						{
							action = MCTComponentHistory.CT_COMPONENTACTIONTYPE_AddedToInventory;
							component.setRoot_Locator_ID(line.getM_LocatorTo_ID());
						}
						
						addHistoryFromDocLine(po.getCtx(), component, line, line.getM_Locator_ID(), 0, action, movementType, po.get_TrxName());
						
					}
					else if (po instanceof MMovement)
					{
						//  Component moved in inventory - could be a change in component (ASI value).
						//  The current component uses the "To" value of the ASI.  Record the history 
						//  for that transaction and create a new one for the "From" values.
						
						// Check if the ASI changes, which implies a change in component
						if (line.getM_AttributeSetInstance_ID() != line.getM_AttributeSetInstanceTo_ID())
						{
							// Check if the "from" component exists. If not create it.
							MCTComponent componentFrom = MCTComponent.getByProductAndASI(po.getCtx(), m_product_id, line.getM_AttributeSetInstance_ID(), po.get_TrxName());
							
							if (componentFrom == null)
							{
								componentFrom = new MCTComponent(po.getCtx(), 0, po.get_TrxName());
								componentFrom.setM_Product_ID(product.getM_Product_ID());
								componentFrom.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
								componentFrom.saveEx();
							}

							//  Add the "From" history entry
							//	Incoming Trx are positive receipts or negative shipments
							String movementType = MTransaction.MOVEMENTTYPE_MovementFrom;
							BigDecimal movementQty = line.getMovementQty();
							boolean incomingTrx = MTransaction.isIncomingTransaction(movementType) && movementQty.signum() >= 0
												|| !MTransaction.isIncomingTransaction(movementType) && movementQty.signum() < 0;	//	V+ Vendor Receipt

							String action = MCTComponentHistory.CT_COMPONENTACTIONTYPE_DrawnFromInentory;
							componentFrom.setRoot_Locator_ID(0);
							if (incomingTrx) 
							{
								action = MCTComponentHistory.CT_COMPONENTACTIONTYPE_AddedToInventory;
								componentFrom.setRoot_Locator_ID(line.getM_LocatorTo_ID());
							}
							componentFrom.saveEx();
							
							addHistoryFromDocLine(po.getCtx(), componentFrom, line, line.getM_Locator_ID(), 0, action, movementType, po.get_TrxName());

						}

						//  Add the "To" history entry
						//	Incoming Trx are positive receipts or negative shipments
						String movementType = MTransaction.MOVEMENTTYPE_MovementTo;
						BigDecimal movementQty = line.getMovementQty();
						boolean incomingTrx = MTransaction.isIncomingTransaction(movementType) && movementQty.signum() >= 0
											|| !MTransaction.isIncomingTransaction(movementType) && movementQty.signum() < 0;	//	V+ Vendor Receipt

						String action = MCTComponentHistory.CT_COMPONENTACTIONTYPE_DrawnFromInentory;
						component.setRoot_Locator_ID(0);
						if (incomingTrx) 
						{
							action = MCTComponentHistory.CT_COMPONENTACTIONTYPE_AddedToInventory;
							component.setRoot_Locator_ID(line.getM_LocatorTo_ID());
						}
						
						addHistoryFromDocLine(po.getCtx(), component, line, line.getM_LocatorTo_ID(), 0, action, movementType, po.get_TrxName());
						
					}
					
					component.saveEx();

				}				
			}
		}
		
		return null;
		
	}

	public static void addHistoryFromDocLine(Properties ctx, MCTComponent component,
			IDocumentLine line, int m_locator_id, int parentComponent_id, String action, String movementType,
			String trxName) {
		
		MCTComponentHistory history = MCTComponentHistory.getByline(ctx, line, action, trxName);
		if (history == null)
		{
			history = new MCTComponentHistory(ctx, 0, trxName);
			history.setCT_Component_ID(component.getCT_Component_ID());
			history.setLine_ID(line);
			history.setCT_ComponentActionType(action);
			history.setMovementType(movementType);
			history.setDateAction(line.getMovementDate());
			history.setQty(line.getMovementQty());
			history.setM_Locator_ID(m_locator_id);
			history.setParentComponent_ID(parentComponent_id);
			history.setComponentLifeAtAction(component.getLife());  // TODO: Will be the current life, not the life at the movement date.
			history.setParentLifeAtAction(component.getParenetLife()); // TODO: will be the current life, not the life at the movement date.
			history.saveEx();
		}
	}

}
