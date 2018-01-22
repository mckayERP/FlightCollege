package com.mckayerp.model;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.Callout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class CalloutComponent extends CalloutEngine {

	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		if (isCalloutActive())	//	prevent recursive
			return "";

		if (value == null || ((Integer)value).intValue() <= 0)
			return "";
		
		if (MCTComponent.COLUMNNAME_M_Product_ID.equals(mField.getColumnName()))
		{
			MProduct product = MProduct.get(ctx, ((Integer)value).intValue());
			if (product.isTrackAsComponent())
			{
				// Copy the product component life cycle model to the component fields
				// This just saves a bit of effort and helps ensure consistency
				MCTCompLifeCycleModel model = MCTCompLifeCycleModel.getByProduct(ctx, product, null);
				
				if (model != null)
				{
					mTab.setValue(MCTComponent.COLUMNNAME_LifeUsageSource, model.getLifeUsageSource());
					mTab.setValue(MCTComponent.COLUMNNAME_MaxLifeUsage, model.getMaxLifeUsage());
					mTab.setValue(MCTComponent.COLUMNNAME_LifeUsageUOM_ID, model.getLifeUsageUOM_ID());
					mTab.setValue(MCTComponent.COLUMNNAME_IsLifeExtensionPossible, model.isLifeExtensionPossible());
				}
			}
			else
			{
				// Shouldn't happen due to dynamic validation
				return "@Error@ Product is not tracked as a component";
			}
		}

		return "";
	}
	
}
