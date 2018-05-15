package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentBOMLine;

public class MFTUMaintWORLDetail extends X_FTU_MaintWORL_Detail {

	public MFTUMaintWORLDetail(Properties ctx, int FTU_MaintWORL_Detail_ID,
			String trxName) {
		super(ctx, FTU_MaintWORL_Detail_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUMaintWORLDetail(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		/** Prevents saving
		log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
		log.saveError("FillMandatory", Msg.getElement(getCtx(), "PriceEntered"));
		/** Issues message
		log.saveWarning(AD_Message, message);
		log.saveInfo (AD_Message, message);
		**/
		if (this.getFTU_MaintWOResultLine_ID() <= 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "FTU_MaintWOResultLine"));
			return false;
		}
		
		// TODO - The WOResult should reference the Root component - not the Parent.
		if (this.getTarget_RootComponent_ID() <= 0)
		{
			this.setTarget_RootComponent_ID(this.getFTU_MaintWOResultLine().getFTU_MaintWOResult().getParentComponent_ID());
		}
		
		if (this.is_ValueChanged(MFTUMaintWORLDetail.COLUMNNAME_CT_Component_ID))
		{
			if (this.getCT_Component_ID() > 0)
			{
				MCTComponent comp = (MCTComponent) this.getCT_Component();
				this.setCT_ComponentLifeAtAction(comp.getLifeUsed());
				this.setOverhaulCount(comp.getOverhaulCount());
				this.setRoot_Component_ID(comp.getRoot_Component_ID());
				List<MCTComponentBOMLine> lines = MCTComponentBOMLine.getLinesByCTComponentID(getCtx(), getCT_Component_ID(), get_TrxName());
				if (lines.size() > 0)
				{
					this.setCT_ComponentBOMLine_ID(lines.get(0).getCT_ComponentBOMLine_ID());
					this.setParentComponent_ID(lines.get(0).getCT_ComponentBOM().getCT_Component_ID());
				}
			}
			else
			{
				this.setParentComponent_ID(0);
				this.setRoot_Component_ID(0);
				this.setCT_ComponentBOMLine_ID(0);
				this.setOverhaulCount(0);
				this.setCT_ComponentLifeAtAction(Env.ZERO);
			}
		}

		if (newRecord)
		{
		}
		
		return true;
	}	//	beforeSave

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		return success;
	}	//	afterSave

	public void process() {

		if (this.isProcessed())
			return;
		
		MCTComponent comp = (MCTComponent) getCT_Component();
		String action = getCT_ComponentActionType();

		if (CT_COMPONENTACTIONTYPE_Installed.equals(action))
		{
			MCTComponent parent = (MCTComponent) this.getParentComponent();
			MCTComponentBOMLine bomLine = (MCTComponentBOMLine) this.getCT_ComponentBOMLine();
			bomLine.installComponent(this.getCT_Component_ID(), this.getQty(), this.getFTU_MaintWOResultLine_ID(), this.getDateAction());
			bomLine.saveEx();
			
		}
		else if (CT_COMPONENTACTIONTYPE_Uninstalled.equals(action))
		{
			MCTComponent parent = (MCTComponent) this.getParentComponent();
			MCTComponentBOMLine bomLine = (MCTComponentBOMLine) this.getCT_ComponentBOMLine();
			bomLine.uninstallComponent(this.getQty(), this.getFTU_MaintWOResultLine_ID());
			bomLine.saveEx();			
		}
	}
}
