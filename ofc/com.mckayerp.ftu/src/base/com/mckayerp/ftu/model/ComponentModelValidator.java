package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.engine.IDocumentLine;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
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
		engine.addModelChange(MFTUAircraft.Table_Name, this);
		engine.addModelChange(MFTUComponentBOMLine.Table_Name, this);
		
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
		
		boolean isAfterSave = (ModelValidator.TYPE_AFTER_CHANGE == type);
		boolean isBeforeSave = (ModelValidator.TYPE_BEFORE_CHANGE == type);
		
		if(isAfterSave 
			&& po instanceof MFTUAircraft 
			&& po.get_ValueAsInt(MFTUAircraft.COLUMNNAME_FTU_Component_ID) > 0)
		{
			
			if (po.is_ValueChanged(MFTUAircraft.COLUMNNAME_FTU_Component_ID) 
					&& po.get_ValueOldAsInt(MFTUAircraft.COLUMNNAME_FTU_Component_ID) > 0)
			{
			
				// Remove the airframe time from the old component
				MFTUComponent component = new MFTUComponent(po.getCtx(), 
													po.get_ValueOldAsInt(MFTUAircraft.COLUMNNAME_FTU_Component_ID), po.get_TrxName());
				component.setLifeUsed(null);
				component.saveEx();
				
			}
			
			if (po.is_ValueChanged(MFTUAircraft.COLUMNNAME_FTU_Component_ID) 
				|| po.is_ValueChanged(MFTUAircraft.COLUMNNAME_AirframeTime))
			{
				
				// Updat the component with the current airframe time (TTSN)
				MFTUComponent component = (MFTUComponent) ((MFTUAircraft) po).getFTU_Component();
				component.setLifeUsed(((MFTUAircraft) po).getAirframeTime());
				component.saveEx();
				
			}
		}

		if(isBeforeSave 
			&& po instanceof MFTUComponentBOMLine)
		{
			
			MFTUComponentBOMLine bomLine = (MFTUComponentBOMLine) po;
			if (bomLine.is_ValueChanged(MFTUComponentBOMLine.COLUMNNAME_M_AttributeSetInstance_ID)
				&& bomLine.get_ValueAsInt(MFTUComponentBOMLine.COLUMNNAME_M_AttributeSetInstance_ID) > 0
				&& bomLine.get_ValueAsInt(MFTUComponentBOMLine.COLUMNNAME_M_Product_ID) > 0
				&& bomLine.get_ValueAsInt(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) == 0)
			{
				// New component?
				int m_product_id = bomLine.getM_Product_ID();
				int m_attributeSetInstance_id = bomLine.getM_AttributeSetInstance_ID();
				
				MProduct product = MProduct.get(po.getCtx(), m_product_id);
				if (product.isTrackAsComponent())
				{
					// Check if the component exists. If not create it.
					MFTUComponent component = MFTUComponent.getByProductAndASI(po.getCtx(), m_product_id, m_attributeSetInstance_id, po.get_TrxName());
					MAttributeSet as = (MAttributeSet) bomLine.getM_AttributeSetInstance().getM_AttributeSet();
					
					if (component == null)
					{
						component = new MFTUComponent(po.getCtx(), 0, po.get_TrxName());
						component.setM_Product_ID(product.getM_Product_ID());
						component.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
						component.saveEx();
					}

					if (as.isInstanceAttribute())  // ASI should be unique
					{
						// Check that it is not already installed somewhere - shouldn't happen
						String where = MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID + "=?";
						int count = new Query(po.getCtx(), MFTUComponentBOMLine.Table_Name, where, po.get_TrxName())
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(component.getFTU_Component_ID())
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
					
					bomLine.setFTU_Component_ID(component.getFTU_Component_ID());
					
				}
			}
				
			
			if (po.is_ValueChanged(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) 
					&& po.get_ValueAsInt(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) > 0)
			{
				// Install !!
				MFTUComponentBOM bom = (MFTUComponentBOM) bomLine.getFTU_ComponentBOM();
				MFTUComponent component = (MFTUComponent) bomLine.getFTU_Component();
				MFTUComponent parent = (MFTUComponent) bom.getFTU_Component();
				
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
			
			if (po.is_ValueChanged(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) 
					&& po.get_ValueAsInt(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) <= 0)
			{
				
				// Uninstall
				if(bomLine.getM_Product_ID() > 0)
				{
					bomLine.setM_AttributeSetInstance_ID(bomLine.getM_Product().getM_AttributeSetInstance_ID());					
				}
				else
				{
					bomLine.setM_AttributeSetInstance_ID(0);
				}
				bomLine.setQtyInstalled(Env.ZERO);
				bomLine.setDateInstalled(null);
				bomLine.setParentLifeAtInstall(Env.ZERO);
				bomLine.setCurrentParentLife(Env.ZERO);
				bomLine.setCompLifeAtInstall(Env.ZERO);
				bomLine.setCurrentCompLife(Env.ZERO);
				bomLine.setParentUsageSinceInstall(Env.ZERO);
				bomLine.setCompUsageSinceInstall(Env.ZERO);
				
			}
		}

		if(isAfterSave 
				&& po instanceof MFTUComponentBOMLine)
		{
			
			MFTUComponentBOMLine bomLine = (MFTUComponentBOMLine) po;
			
			if (po.is_ValueChanged(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) 
					&& po.get_ValueAsInt(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) > 0)
			{
				// Install !!
				MFTUComponentBOM bom = (MFTUComponentBOM) bomLine.getFTU_ComponentBOM();
				MFTUComponent component = (MFTUComponent) bomLine.getFTU_Component();
				MFTUComponent parent = (MFTUComponent) bom.getFTU_Component();

				MFTUComponentHistory history = new MFTUComponentHistory(po.getCtx(), 0, po.get_TrxName());
				history.setFTU_Component_ID(component.getFTU_Component_ID());
				history.setFTU_ComponentActionType(MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Installed);
				history.setDateAction(bomLine.getDateInstalled());
				history.setQty(bomLine.getQtyInstalled());
				history.setParentComponent_ID(parent.getFTU_Component_ID());
				history.setComponentLifeAtAction(bomLine.getCompLifeAtInstall());
				history.setParentLifeAtAction(bomLine.getParentLifeAtInstall());
				history.saveEx();
					
			}
			
			if (po.is_ValueChanged(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) 
					&& po.get_ValueOldAsInt(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID) > 0)
			{
				
				// Uninstall !!
				int ftu_component_id = po.get_ValueOldAsInt(MFTUComponentBOMLine.COLUMNNAME_FTU_Component_ID);
				MFTUComponent component = new MFTUComponent(po.getCtx(), ftu_component_id, po.get_TrxName());

				MFTUComponentBOM bom = (MFTUComponentBOM) bomLine.getFTU_ComponentBOM();
				MFTUComponent parent = (MFTUComponent) bom.getFTU_Component();

				MFTUComponentHistory history = new MFTUComponentHistory(po.getCtx(), 0, po.get_TrxName());
				history.setFTU_Component_ID(component.getFTU_Component_ID());
				history.setFTU_ComponentActionType(MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Uninstalled);
				history.setDateAction(new Timestamp (System.currentTimeMillis()));
				// TODO quantity on uninstall?
				if (bomLine.get_ValueOld(MFTUComponentBOMLine.COLUMNNAME_QtyInstalled) == null) // Shouldn't happen
				{
					history.setQty(Env.ZERO);					
				}
				else
				{
					history.setQty(bomLine.getQtyInstalled().subtract((BigDecimal) bomLine.get_ValueOld(MFTUComponentBOMLine.COLUMNNAME_QtyInstalled)));
				}
				history.setParentComponent_ID(parent.getFTU_Component_ID());
				history.setComponentLifeAtAction(component.getLifeUsed());
				history.setParentLifeAtAction(parent.getLifeUsed());
				history.saveEx();
				
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
			for (IDocumentLine line : lines)
			{
				
				int m_product_id = line.getM_Product_ID();
				int m_attributeSetInstance_id = line.getM_AttributeSetInstanceTo_ID();
				
				MProduct product = MProduct.get(po.getCtx(), m_product_id);
				if (product.isTrackAsComponent())
				{
					// Check if the component exists. If not create it.
					MFTUComponent component = MFTUComponent.getByProductAndASI(po.getCtx(), m_product_id, m_attributeSetInstance_id, po.get_TrxName());
					MFTUCompLifeCycleModel model = MFTUCompLifeCycleModel.getByProduct(po.getCtx(), product, po.get_TrxName());
					
					if (component == null)
					{
						component = new MFTUComponent(po.getCtx(), 0, po.get_TrxName());
						component.setM_Product_ID(product.getM_Product_ID());
						component.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
						
						if (model != null)
						{
							component.setLifeUsageSource(model.getLifeUsageSource());
							component.setLifeUsageUOM_ID(model.getLifeUsageUOM_ID());
							component.setMaxLifeUsage(model.getMaxLifeUsage());
							component.setIsLifeExtensionPossible(model.isLifeExtensionPossible());
						}
						
						component.saveEx();
					}
					
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

						String action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Shipped;
						if (incomingTrx) 
						{
							action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Received;
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

						String action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Scrapped;
						if (incomingTrx) 
						{
							action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_AddedToInventory;
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
							MFTUComponent componentFrom = MFTUComponent.getByProductAndASI(po.getCtx(), m_product_id, line.getM_AttributeSetInstance_ID(), po.get_TrxName());
							
							if (componentFrom == null)
							{
								componentFrom = new MFTUComponent(po.getCtx(), 0, po.get_TrxName());
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

							String action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_DrawnFromInentory;
							if (incomingTrx) 
							{
								action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_AddedToInventory;
							}
							
							addHistoryFromDocLine(po.getCtx(), componentFrom, line, line.getM_Locator_ID(), 0, action, movementType, po.get_TrxName());

						}

						//  Add the "To" history entry
						//	Incoming Trx are positive receipts or negative shipments
						String movementType = MTransaction.MOVEMENTTYPE_MovementTo;
						BigDecimal movementQty = line.getMovementQty();
						boolean incomingTrx = MTransaction.isIncomingTransaction(movementType) && movementQty.signum() >= 0
											|| !MTransaction.isIncomingTransaction(movementType) && movementQty.signum() < 0;	//	V+ Vendor Receipt

						String action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_DrawnFromInentory;
						if (incomingTrx) 
						{
							action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_AddedToInventory;
						}
						
						addHistoryFromDocLine(po.getCtx(), component, line, line.getM_LocatorTo_ID(), 0, action, movementType, po.get_TrxName());
						
					}
					else if (po instanceof MFTUMaintWOResult)
					{
						//  Maintenance work orders can add or remove components from assemblies and affect inventory
						String movementType = line.getMovementType();
						BigDecimal movementQty = line.getMovementQty();
						boolean incomingTrx = MTransaction.isIncomingTransaction(movementType) && movementQty.signum() >= 0
											|| !MTransaction.isIncomingTransaction(movementType) && movementQty.signum() < 0;	//	V+ Vendor Receipt

						// Two history elements - inventory and BOM actions
						String action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Installed;
						if (incomingTrx) 
						{
							action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Uninstalled;
							// Uninstall first, then return to inventory
							// TODO - is it important to track the component it was removed from?
							// This happens after the maintenance action was completed, so the parent ID will already by zero 
							addHistoryFromDocLine(po.getCtx(), component, line, 0, 0, action, movementType, po.get_TrxName());

							action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_AddedToInventory;
							addHistoryFromDocLine(po.getCtx(), component, line, line.getM_LocatorTo_ID(), 0, action, movementType, po.get_TrxName());
						}
						else
						{
							action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_DrawnFromInentory;
							// Draw from inventory first, then install
							addHistoryFromDocLine(po.getCtx(), component, line, line.getM_Locator_ID(), 0, action, movementType, po.get_TrxName());

							action = MFTUComponentHistory.FTU_COMPONENTACTIONTYPE_Installed;
							addHistoryFromDocLine(po.getCtx(), component, line, 0, component.getParentComponent_ID(), action, movementType, po.get_TrxName());
						}
						
					}

				}
				
			}
		}
		
		return null;
		
	}

	private void addHistoryFromDocLine(Properties ctx, MFTUComponent component,
			IDocumentLine line, int m_locator_id, int parentComponent_id, String action, String movementType,
			String trxName) {
		
		MFTUComponentHistory history = MFTUComponentHistory.getByline(ctx, line, action, trxName);
		if (history == null)
		{
			history = new MFTUComponentHistory(ctx, 0, trxName);
			history.setFTU_Component_ID(component.getFTU_Component_ID());
			history.setLine_ID(line);
			history.setFTU_ComponentActionType(action);
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
