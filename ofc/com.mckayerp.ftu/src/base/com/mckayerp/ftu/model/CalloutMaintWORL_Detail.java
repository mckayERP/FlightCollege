package com.mckayerp.ftu.model;

import java.util.List;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentBOM;
import com.mckayerp.model.MCTComponentBOMLine;

public class CalloutMaintWORL_Detail extends CalloutEngine {

	/**
	 * Called from the Maint WO Result Line Detail tab when the Component is changed.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String setComponentRelatedFields(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (isCalloutActive())	//	prevent recursive
			return "";

		if (value == null)
			value = new Integer(0);
		
		if (!(value instanceof Integer))
			return "";
		
		int ct_component_id = ((Integer) value).intValue();
		
		if (ct_component_id == 0)
		{
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_Product_ID, null);
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_AttributeSetInstance_ID, null);
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_ParentComponent_ID, null);
		}
		else
		{
			MCTComponent comp = new MCTComponent(Env.getCtx(), ct_component_id, null);
			// Set product and ASI
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_Product_ID, comp.getM_Product_ID());
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_AttributeSetInstance_ID, comp.getM_AttributeSetInstance_ID());
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_Root_Component_ID, comp.getRoot_Component_ID());
			// Set the BOMLine and Parent if there is only one.  Ignore it otherwise.
			List<MCTComponentBOMLine> bomLines = MCTComponentBOMLine.getLinesByCTComponentID(Env.getCtx(), ct_component_id, null);
			if (bomLines.size() == 1)
			{
				MCTComponentBOM bom = (MCTComponentBOM) bomLines.get(0).getCT_ComponentBOM();
				mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_ParentComponent_ID, bom.getCT_Component_ID());
				mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_CT_ComponentBOMLine_ID, bomLines.get(0).getCT_ComponentBOMLine_ID());
			}
			
			// Set life an overhaul cycle
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_CT_ComponentLifeAtAction, comp.getLifeUsed());
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_OverhaulCount, comp.getOverhaulCount());
		}
		
		return "";		
	}

	/**
	 * Called from the Maint WO Result Line Detail tab when the ComponentActionType is changed.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String componentActionType(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (isCalloutActive())	//	prevent recursive
			return "";

		boolean installed = MFTUMaintWORLDetail.CT_COMPONENTACTIONTYPE_Installed.equals((String) value);
		boolean uninstalled = MFTUMaintWORLDetail.CT_COMPONENTACTIONTYPE_Uninstalled.equals((String) value);

		Integer ftu_maintWOResultLine_id = (Integer) mTab.getValue(MFTUMaintWORLDetail.COLUMNNAME_FTU_MaintWOResultLine_ID);
		if (ftu_maintWOResultLine_id == null)
			return "";
		
		MFTUMaintWOResultLine mrl = new MFTUMaintWOResultLine(ctx, ftu_maintWOResultLine_id.intValue(), null);
		if (mrl == null)
			return "";
		
		if (mField != null && value != null && mField.getOldValue() != null && !((String) mField.getOldValue()).equals((String) value))
		{
			// zero the component info
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_CT_Component_ID, null);
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_ParentComponent_ID, null);
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_Target_ParentComponent_ID, null);
			
		}
		
		int target_rootComponent_id = mrl.getFTU_MaintWOResult().getParentComponent_ID();  // TODO should be root component.
		if (installed)
		{
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_Target_RootComponent_ID, target_rootComponent_id);
		}
		
		Integer component_id = (Integer) mTab.getValue(MFTUMaintWORLDetail.COLUMNNAME_CT_Component_ID);
		int ct_component_id = 0;
		if (component_id != null)
			ct_component_id = component_id.intValue();
		
		if (ct_component_id == 0)
		{
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_Product_ID, null);
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_AttributeSetInstance_ID, null);
		}
		else
		{
			MCTComponent comp = new MCTComponent(Env.getCtx(), ct_component_id, null);
			// Set product and ASI
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_Product_ID, comp.getM_Product_ID());
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_AttributeSetInstance_ID, comp.getM_AttributeSetInstance_ID());
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_Root_Component_ID, comp.getRoot_Component_ID());
			// Set the BOMLine and Parent if there is only one.  Ignore it otherwise.
			List<MCTComponentBOMLine> bomLines = MCTComponentBOMLine.getLinesByCTComponentID(Env.getCtx(), ct_component_id, null);
			if (bomLines.size() == 1)
			{
				MCTComponentBOM bom = (MCTComponentBOM) bomLines.get(0).getCT_ComponentBOM();
				mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_ParentComponent_ID, bom.getCT_Component_ID());
			}
		}
		
		return "";		
	}

	/**
	 * Called from the Maint WO Result Line Detail tab when the Component BOM line is changed.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String setComponentBOMRelatedFields(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		Integer ct_componentBOMLine_id = (Integer) value;
		
		if (ct_componentBOMLine_id == null)
			return "";
		
		MCTComponentBOMLine cbl = new MCTComponentBOMLine(ctx, ct_componentBOMLine_id.intValue(), null);
		
		if (cbl != null)
		{
			mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_CT_Component_ID, cbl.getCT_Component_ID());
			if (cbl.getCT_Component_ID()>0)
			{
				MCTComponent comp = new MCTComponent(Env.getCtx(), cbl.getCT_Component_ID(), null);
				// Set product and ASI
				mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_Product_ID, comp.getM_Product_ID());
				mTab.setValue(MFTUMaintWORLDetail.COLUMNNAME_M_AttributeSetInstance_ID, comp.getM_AttributeSetInstance_ID());
			}
		}
		
		return "";
	}
}
