package com.mckayerp.ftu.process;

import java.util.List;

import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

import com.mckayerp.ftu.model.MFTUComponentBOM;
import com.mckayerp.ftu.model.MFTUComponentBOMLine;

public class UpdateComponentBOMs extends SvrProcess {

	private int m_product_id;

	@Override
	protected void prepare() {
		m_product_id = getParameterAsInt("M_Product_ID");		
	}

	@Override
	protected String doIt() throws Exception {
		
		
		MProduct product =  MProduct.get(getCtx(), m_product_id);
		if (product != null && product.isBOM())
		{
			
			List<MPPProductBOM> bomList = MPPProductBOM.getProductBOMs(product);
			for (MPPProductBOM bom : bomList)
			{
				
				if (bom.getBOMUse().equals(MPPProductBOM.BOMUSE_Master))
				{
					
					List<MFTUComponentBOM> componentBOMs = MFTUComponentBOM.getByPP_ProductBOM(getCtx(), bom.getPP_Product_BOM_ID(), get_TrxName());
					
					for (MFTUComponentBOM componentBOM : componentBOMs)
					{

						for (MPPProductBOMLine bomLine : bom.getLines())
						{
						
							Boolean match = false;
							
							// Check that every bomLine is included in the component BOM
							for (MFTUComponentBOMLine compBOMLine : componentBOM.getLines())
							{
								
								// If a match is found, move to the next bomLine
								if (compBOMLine.getPP_Product_BOMLine_ID() == bomLine.getPP_Product_BOMLine_ID())
								{
								
									match = true;
									// Stop this compBOMLine loop
									break;

								}
							}
							
							if (match)
								continue; // Try the next line
							else
							{
								// Add the missing one
								MFTUComponentBOMLine compBOMLine = new MFTUComponentBOMLine(getCtx(), 0, get_TrxName());
								compBOMLine.setFTU_ComponentBOM_ID(componentBOM.getFTU_ComponentBOM_ID());
								compBOMLine.setPP_Product_BOMLine_ID(bomLine.getPP_Product_BOMLine_ID());
								compBOMLine.setLine(bomLine.getLine());
								compBOMLine.setM_Product_ID(bomLine.getM_Product_ID());
								compBOMLine.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
								compBOMLine.setDescription(bomLine.getDescription());
								compBOMLine.setQtyRequired(bomLine.getAssay());
								compBOMLine.saveEx();
							
							}
						}
					}
				}	
			}
		}
		
		return null;  // TODO improve the message
	}

}
