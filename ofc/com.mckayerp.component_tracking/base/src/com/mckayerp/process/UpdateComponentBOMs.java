package com.mckayerp.process;

import java.util.List;

import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MPPProductBOM;

import com.mckayerp.model.MCTComponent;

public class UpdateComponentBOMs extends SvrProcess {

	private int m_product_id;

	@Override
	protected void prepare() {
		m_product_id = getParameterAsInt("M_Product_ID");		
	}

	@Override
	protected String doIt() throws Exception {
				
		//  Set the where clause - products must be components and have a BOM.
		String where = MProduct.COLUMNNAME_IsBOM + "= 'Y' AND " + MProduct.COLUMNNAME_IsTrackAsComponent + "= 'Y'"; 
		
		//  Look at all products unless an ID was specified
		if (m_product_id > 0)
		{
			where += " AND " + MProduct.COLUMNNAME_M_Product_ID + "=?";
		}
		
		Query query = new Query(getCtx(), MProduct.Table_Name, where, this.get_TrxName())
							.setClient_ID()
							.setOnlyActiveRecords(true);

		if (m_product_id > 0)
		{
			query = query.setParameters(m_product_id);
		}
		
		List<MProduct> products = query.list();

		for (MProduct product : products)
		{
			
			if (product != null && product.isBOM())
			{
				
				//  Find the product BOM
//				List<MPPProductBOM> bomList = MPPProductBOM.getProductBOMs(product);
//				MPPProductBOM bomMaster = null;
//				for (MPPProductBOM bom : bomList)
//				{
//					
//					if (bom.getBOMUse().equals(MPPProductBOM.BOMUSE_Master) 
//							&& bom.isValidFromTo(Env.getContextAsDate(getCtx(), "#Date")));
//					{
//						bomMaster = bom;
//						break;
//					}
//				}
//				
//				if (bomMaster == null)
//				{
//					return Msg.parseTranslation(getCtx(), "@Error@ @NotFound@ " + MPPProductBOM.COLUMNNAME_BOMUse + "=" + MPPProductBOM.BOMUSE_Master);
//				}
				
				List<MCTComponent> components = MCTComponent.getByProduct(getCtx(), product.getM_Product_ID(), get_TrxName());
				
				for (MCTComponent component : components)
				{
					
					component.createOrUpdateComponentBOM();
					
//					//  Get the component BOM - there should only be one as the BOM represents a physical reality where the product BOM
//					//  can represent one of a number of design specs.  Limit of one BOM per component is in the MCTComponentBOM beforeSave().
//					//  TODO - deal with engineering changes to components that require multiple BOMs. Not sure this is required.
//					MCTComponentBOM componentBOM = MCTComponentBOM.getByCT_Component_ID(getCtx(), component.getCT_Component_ID(), get_TrxName());
//										
//					//  Check if the BOM needs to be added.  This can happen if the component was defined AFTER
//					//  the product BOM was created.
//					if (componentBOM == null)
//					{
//						String result = component.addPP_ProductBOM(getCtx(), bomMaster, get_TrxName());
//						
//						if (result != null)
//							log.severe(result);
//						
//						countBOMAdded ++;
//						
//						continue;  // Done.  Next component.
//					}
//
//					//  Update the componentBOM to match the product BOM Master
//					for (MPPProductBOMLine bomLine : bomMaster.getLines())
//					{
//					
//						Boolean match = false;
//						
//						MCTComponentBOMLine compBOMLine = null;
//						// Check that every bomLine is included in the component BOM
//						for (MCTComponentBOMLine line : componentBOM.getLines())
//						{
//							
//							// If a match is found, move to the next bomLine
//							if (line.getPP_Product_BOMLine_ID() == bomLine.getPP_Product_BOMLine_ID())
//							{
//							
//								compBOMLine = line;
//								match = true;
//								// Stop this compBOMLine loop
//								break;
//
//							}
//						}
//						
//						if (match)
//						{
//							// Update the info
//							// Easy changes
//							compBOMLine.setLine(bomLine.getLine());
//							compBOMLine.setDescription(bomLine.getDescription());
//							MProduct subProduct = (MProduct) bomLine.getM_Product();
//							if (subProduct != null && subProduct.getM_AttributeSet_ID() > 0)
//							{
//								MAttributeSet mas = (MAttributeSet) subProduct.getM_AttributeSet();
//								if (mas.isInstanceAttribute())
//								{
//										compBOMLine.setQtyRequired(Env.ONE);
//								}
//								else
//								{
//									compBOMLine.setQtyRequired(bomLine.getQtyBOM()); // Not percentage
//								}
//							}
//							
//							// Difficult changes
//							if (compBOMLine.getMaster_Product_ID() > 0 
//								&& compBOMLine.getMaster_Product_ID() != bomLine.getM_Product_ID() 
//								&& compBOMLine.getCT_Component_ID() > 0)
//							{
//								// Problem. The product BOM has changed product but the component BOM has a 
//								// sub component installed in that spot.  Check if the installed component 
//								// is a valid substitute.
//								MProduct installedProduct = (MProduct) compBOMLine.getM_Product();  // Should be non-null as a component exists.
//								MProduct masterProduct = (MProduct) bomLine.getM_Product();
//								if (installedProduct.isValidSubstitueFor(masterProduct))
//								{
//									// Just update the master product.  The installed product is valid
//									compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());
//								}
//								else
//								{
//									// Need to uninstall the component.  Throw error to prevent changes.
//									String errorMsg = component.toString() + " has sub-component " + compBOMLine.getCT_Component().toString();
//									errorMsg += " installed on line " + compBOMLine.getLine() + ".  BOM Master Product has changed to " + bomLine.getM_Product().toString();
//									errorMsg += " Please uninstall the component and attempt the update again.";
//									throw new AdempiereException(errorMsg);
//								}
//							}
//							compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());
//
//							// Update the ASI if there is no component
//							if (compBOMLine.getM_AttributeSetInstance_ID() <= 0  
//									|| compBOMLine.getCT_Component_ID() <= 0)
//							{
//								compBOMLine.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
//							}
//							compBOMLine.saveEx();
//							
//						}
//						else
//						{
//							// Add the missing one
//							MProduct subProduct = (MProduct) bomLine.getM_Product();
//							int copies = 1;
//							BigDecimal qty = bomLine.getQtyBOM();
//							if (subProduct != null && subProduct.getM_AttributeSet_ID() > 0)
//							{
//								MAttributeSet mas = (MAttributeSet) subProduct.getM_AttributeSet();
//								if (mas.isInstanceAttribute() && bomLine.getQtyBOM() != null)
//								{
//									copies = bomLine.getQtyBOM().intValue(); // Not percentage
//									qty = Env.ONE;
//								}
//							}
//							
//							for (; copies >= 0; copies--)
//							{
//								compBOMLine = new MCTComponentBOMLine(getCtx(), 0, get_TrxName());
//								compBOMLine.setCT_ComponentBOM_ID(componentBOM.getCT_ComponentBOM_ID());
//								compBOMLine.setPP_Product_BOMLine_ID(bomLine.getPP_Product_BOMLine_ID());
//								compBOMLine.setLine(bomLine.getLine());
//								compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());
//								compBOMLine.setM_Product_ID(bomLine.getM_Product_ID());  // Default
//								compBOMLine.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
//								compBOMLine.setDescription(bomLine.getDescription());
//								compBOMLine.setQtyRequired(qty);
//								compBOMLine.saveEx();
//							}
//							
//							match = true;
//							countBOMLineAdded ++;
//						}
//						
//						if (!match)
//						{
//							//  Component BOM has a line that no longer exists on the Product BOM Master
//							//  TODO - what to do with it?  Can't delete the line if there is a component
//							//  installed - where would it go?
//						}
//					}
				}
			}
		}
				
		return Msg.parseTranslation(getCtx(), "@Success@");
	}

}
