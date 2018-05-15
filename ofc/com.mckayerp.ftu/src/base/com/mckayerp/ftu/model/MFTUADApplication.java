package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
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

	/**
	 * Add components to this AD. This should be called when the AD is first created.
	 */
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

	/**
	 *   Add a component to all ADs to which it could apply and return the list of maintenance requirements.
	 */
	public static List<MFTUMaintRequirement> findOrCreateADApplicabilityByComponent(Properties ctx, int ct_component_id, String trxName) {

		if (ct_component_id <= 0)
			return null;
		
		MCTComponent component = new MCTComponent(ctx, ct_component_id, trxName);
		List<MFTUMaintRequirement> mrList = new ArrayList<MFTUMaintRequirement>();
		
		// Find the list of AD applications that apply to the product
		List<MFTUADApplication> adApplicationList = MFTUADApplication.getByProduct(ctx, component.getM_Product_ID(), trxName);
		
		// For each AD application, find or create an entry for this component.
		for (MFTUADApplication adApplication : adApplicationList)
		{
			// This AD should apply to the component.  If there is no entry, create one.
			MFTUADApplicability applies = MFTUADApplicability.getByADApplicationAndComponent
											(ctx, adApplication.getFTU_ADApplication_ID(), 
											ct_component_id, trxName);
			
			if (applies == null)
			{
				applies = new MFTUADApplicability(ctx, 0, trxName);
				applies.setFTU_ADApplication_ID(adApplication.getFTU_ADApplication_ID());
				applies.setCT_Component_ID(component.getCT_Component_ID());
				applies.setFTU_IsADApplies(true);
				applies.saveEx();
			}
			
			// If the Ad applies to this component, add it to the list.
			if (applies.isFTU_IsADApplies())
			{
				int ftu_airworthinessDirective_id = adApplication.getFTU_AirworthinessDirective_ID();				
				mrList.addAll(MFTUMaintRequirement.getByFTU_AirworthinessDirective(ctx, ftu_airworthinessDirective_id, trxName));
			}
		}
		
		return mrList;
	}

	private static List<MFTUADApplication> getByProduct(Properties ctx,
			int m_product_id, String trxName) {
		
		String where = MFTUADApplication.COLUMNNAME_M_Product_ID + "=?"
				+ " AND " + MFTUADApplication.COLUMNNAME_FTU_IsADApplies + "='Y'";
		
		return new Query(ctx, MFTUADApplication.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(m_product_id)
					.list();
	}

}
