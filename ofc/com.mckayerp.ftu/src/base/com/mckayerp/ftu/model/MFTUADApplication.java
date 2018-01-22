package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Msg;

import com.mckayerp.ftu.model.X_FTU_ADApplication;
import com.mckayerp.model.MCTComponent;

public class MFTUADApplication extends X_FTU_ADApplication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4155737587185425415L;

	public MFTUADApplication(Properties ctx, int FTU_ADApplication_ID, 
			String trxName) {
		super(ctx, FTU_ADApplication_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUADApplication(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success) {

		if (newRecord) 
		{

			if (getM_Product_ID() == 0)
			{
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@M_Product_ID@ == 0"), false);
				return false;
			}
			
			if (!this.isFTU_IsADApplies())
			{
				return success;  // Skip components
			}
			
			applyToComponents();
		}
		else if (this.is_ValueChanged(COLUMNNAME_M_Product_ID))
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@ActionNotAllowedHere@"), false);
			return false;
		}
		else if (this.is_ValueChanged(COLUMNNAME_FTU_IsADApplies))
		{
			if (isFTU_IsADApplies())
			{
				applyToComponents();
			}
			else
			{
				unapplyComponents();
			}
		}
		
		return success;
	}

	private void unapplyComponents() {

		List<MFTUADApplicability> applies 
				= MFTUADApplicability.getByADApplication(getCtx(),getFTU_ADApplication_ID(),get_TrxName());
		
		for (MFTUADApplicability appliesTo : applies)
		{
			appliesTo.deleteEx(false);
		}
		
	}

	private void applyToComponents() {

		List<MCTComponent> components = MCTComponent.getByProduct(getCtx(), getM_Product_ID(), get_TrxName());

		for (MCTComponent component : components)
		{
		
			MFTUADApplicability applies = new MFTUADApplicability(getCtx(),0,get_TrxName());
			applies.setFTU_ADApplication_ID(getFTU_ADApplication_ID());
			applies.setCT_Component_ID(component.getCT_Component_ID());
			applies.setFTU_IsADApplies(true);
			applies.saveEx();
			
		}
	}
	
}
