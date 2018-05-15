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
				
				List<MCTComponent> components = MCTComponent.getByProduct(getCtx(), product.getM_Product_ID(), get_TrxName());
				
				for (MCTComponent component : components)
				{
					
					component.createOrUpdateComponentBOM();
					
				}
			}
		}
				
		return Msg.parseTranslation(getCtx(), "@Success@");
	}

}
