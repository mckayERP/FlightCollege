package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.Query;
import org.compiere.model.X_M_Substitute;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.mckayerp.model.MCTCompLifeCycleModel;
import com.mckayerp.model.MCTComponent;

public class MFTUMaintRequirement extends X_FTU_MaintRequirement {
	
	List<MFTUMaintRequirementLine> m_lines;

	public MFTUMaintRequirement(Properties ctx, int FTU_MaintRequirement_ID,
			String trxName) {
		super(ctx, FTU_MaintRequirement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUMaintRequirement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static List<MFTUMaintRequirement> getByFTU_DefectLog(Properties ctx,
			int ftu_defectLog_id, String trxName) {
		
		String where = MFTUMaintRequirement.COLUMNNAME_FTU_DefectLog_ID + "=?";
		
		return new Query(ctx, MFTUMaintRequirement.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ftu_defectLog_id)
					.list();
	}

	/**
	 * Get the list of maintenance requirements that apply to the component with the given id.
	 * The list will also include maintenance requirements for the component product or for
	 * any product where the component product is a valid substitute or vice versa. If the 
	 * maintenance requirement identifies a product group and the component product is of
	 * that product group, it will also be included. 
	 * 
	 * The list will include any Airworthiness Directives that apply.  AD applicability 
	 * entries will be created for the given component id if they do not exist.
	 * 
	 * @param ctx
	 * @param ct_component_id
	 * @param trxName
	 * @return A list of MFTUMaintRequirement models. The list could be empty or null.
	 */
	public static List<MFTUMaintRequirement> getOrCreateByCT_Component(Properties ctx,
			int ct_component_id, String trxName) {
		
		if (ct_component_id <= 0)
			return null;
		
		MCTComponent comp = new MCTComponent(ctx, ct_component_id, trxName);
		int m_product_id = comp.getM_Product_ID();
		int m_product_group_id = comp.getM_Product().getM_Product_Group_ID();
		
		List<Object> parameters = new ArrayList<Object>();
		
		// ct_component_id and m_product_id will both be non-zero.
		parameters.add(ct_component_id);
		parameters.add(m_product_id);
		parameters.add(m_product_id);
		parameters.add(m_product_id);
			
		String where = "(" + MFTUMaintRequirement.COLUMNNAME_CT_Component_ID + "=?"
				+ " OR " + MFTUMaintRequirement.COLUMNNAME_FTU_AppliesToProduct_ID + "=?"		// MR and Comp use same product
				+ " OR " + MFTUMaintRequirement.COLUMNNAME_FTU_AppliesToProduct_ID + " IN "     // OR MR is the substitute product
				+ " (SELECT " + X_M_Substitute.COLUMNNAME_Substitute_ID 
				+ "  FROM " + X_M_Substitute.Table_Name
				+ "  WHERE " + X_M_Substitute.COLUMNNAME_M_Product_ID + "=?)"
				+ " OR " + MFTUMaintRequirement.COLUMNNAME_FTU_AppliesToProduct_ID + " IN "     // OR MR is the master product
				+ " (SELECT " + X_M_Substitute.COLUMNNAME_M_Product_ID 
				+ "  FROM " + X_M_Substitute.Table_Name
				+ "  WHERE " + X_M_Substitute.COLUMNNAME_Substitute_ID + "=?)";
		
		if (m_product_group_id > 0)
		{
				where += " OR " + MFTUMaintRequirement.COLUMNNAME_FTU_AppliesToProdGroup_ID + "=?";  // OR MR applies to the product group
				parameters.add(m_product_group_id);
		}
		
		where += ")";
		
		List<MFTUMaintRequirement> mrList =  new Query(ctx, MFTUMaintRequirement.Table_Name, where, trxName)
													.setClient_ID()
													.setOnlyActiveRecords(true)
													.setParameters(parameters)
													.list();

		mrList.addAll(MFTUADApplication.findOrCreateADApplicabilityByComponent(ctx, ct_component_id, trxName));
		
		return mrList;
	
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

		//  If attached to a component life cycle model, use that product or product group id
		//  Update the values, in case they have changed.
		if (this.getCT_CompLifeCycleModel_ID() > 0)
		{
			this.setFTU_AppliesToProduct_ID(this.getCT_CompLifeCycleModel().getM_Product_ID());
			this.setFTU_AppliesToProdGroup_ID(this.getCT_CompLifeCycleModel().getM_Product_Group_ID());
			
			if (this.isOnConditionSchedule())
			{
					this.setIsOverhaulSchedule(false);
			}
			
			if (this.isOverhaulSchedule())
			{
				MCTCompLifeCycleModel lcm = (MCTCompLifeCycleModel) this.getCT_CompLifeCycleModel();
				
				this.setFTU_ComplianceType(FTU_COMPLIANCETYPE_RepeatEvery);
				this.setFTU_UsageInterval_Entered(lcm.getMaxLifeUsage());
				this.setFTU_UsageIntervalUOM_ID(lcm.getLifeUsageUOM_ID());
			}
		}
		
		// Ensure a component ID, Product ID or Product Group is identified
		if (this.getFTU_AppliesToProdGroup_ID() <=0 && this.getFTU_AppliesToProduct_ID() <= 0)
		{
			if (this.getCT_Component_ID() <= 0)
			{
				if (this.getFTU_DefectLog_ID() > 0) 
				{
					// Find the component from the Defect Log
					MFTUDefectLog dl = (MFTUDefectLog) this.getFTU_DefectLog();
					if (dl.getCT_Component_ID() > 0)
					{
						this.setCT_Component_ID(dl.getCT_Component_ID());
					}
					else
					{
						// This is a fall back as the component should be set by the defect log, before save
						MFTUAircraft ac = (MFTUAircraft) dl.getFTU_Aircraft();
						if (ac.getCT_Component_ID() > 0)
						{
							this.setCT_Component_ID(ac.getCT_Component_ID());
						}
					}					
				}
			}
			if (this.getCT_Component_ID() <= 0)
			{
				//  There is a problem - no product group, product or component to which this MR applies
				log.saveError("FillMandatory", Msg.parseTranslation(getCtx(), 
						"@CT_Component_ID@, @M_Product_ID@ @or@ @M_Product_Group_ID@"));
				return false;
			}
		}
		
		// Convert the usage and intervals entered to standard units
		// Use hours for usage and days for time

		MUOM hourUOM = MUOM.get(getCtx(), "Hour", get_TrxName());
		MUOM dayUOM = MUOM.get(getCtx(), "Day", get_TrxName());
		
		if( this.getFTU_TimeIntervalUOM_ID() == 0)
			this.setFTU_TimeIntervalUOM_ID(dayUOM.getC_UOM_ID());
		
		if( this.getFTU_UsageIntervalUOM_ID() == 0)
			this.setFTU_UsageIntervalUOM_ID(hourUOM.getC_UOM_ID());

		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageInterval_Entered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageIntervalUOM_ID))
		{
			
			BigDecimal conversion = MUOMConversion.convert(getCtx(), 
										this.getFTU_UsageIntervalUOM_ID(), 
										hourUOM.getC_UOM_ID(), this.getFTU_UsageInterval_Entered());
			
			this.setFTU_UsageInterval(conversion);
				
		}

		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_TimeInterval_Entered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_TimeIntervalUOM_ID))
		{

			BigDecimal conversion = MUOMConversion.convert(getCtx(), 
										this.getFTU_TimeIntervalUOM_ID(), 
										dayUOM.getC_UOM_ID(), this.getFTU_TimeInterval_Entered());
			
			this.setFTU_TimeInterval(conversion);
				
		}

		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageIntervalTol_Entered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageIntervalUOM_ID))
		{
				
			BigDecimal conversion = MUOMConversion.convert(getCtx(), 
										this.getFTU_UsageIntervalUOM_ID(), 
										hourUOM.getC_UOM_ID(), this.getFTU_UsageIntervalTol_Entered());
			
			this.setFTU_UsageIntervalTol(conversion);
				
		}

		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_TimeIntervalTol_Entered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_TimeIntervalUOM_ID))
		{
				
			BigDecimal conversion = MUOMConversion.convert(getCtx(), 
										this.getFTU_TimeIntervalUOM_ID(), 
										dayUOM.getC_UOM_ID(), this.getFTU_TimeIntervalTol_Entered());
			
			this.setFTU_TimeIntervalTol(conversion);
				
		}

		// Update the phase interval
		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_PhaseIntervalEntered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageIntervalUOM_ID))
		{
				
			BigDecimal conversion = MUOMConversion.convert(getCtx(), 
										this.getFTU_UsageIntervalUOM_ID(), 
										hourUOM.getC_UOM_ID(), this.getFTU_PhaseIntervalEntered());
			
			this.setFTU_PhaseInterval(conversion);
				
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
		
		//  Create the maintenance action for this maintenance requirement.
		//  The next action will be created for the specific component or for all
		//  components that are based on the product or Product Group.
		List<MCTComponent> components = new ArrayList<MCTComponent>();
		
		if (this.getCT_Component_ID() > 0)  // Defects apply to specific components/aircraft
		{
			components.add((MCTComponent) this.getCT_Component());
		}
		else if (this.getFTU_AppliesToProduct_ID() > 0)
		{
			components.addAll(MCTComponent.getByProduct(getCtx(), getFTU_AppliesToProduct_ID(), get_TrxName()));
		}
		else if (this.getFTU_AppliesToProdGroup_ID() > 0)
		{
			components.addAll(MCTComponent.getByProductGroup(getCtx(), getFTU_AppliesToProdGroup_ID(), get_TrxName()));
		}
		
		// For ADs the components are under the application table
		if (this.getFTU_AirworthinessDirective_ID() > 0)
		{

			int ad_id = this.getFTU_AirworthinessDirective_ID();
			components = MFTUAirworthinessDirective.getAffectedComponents(getCtx(), ad_id, get_TrxName());
			
		}

		for (MCTComponent comp : components)
		{
			//  Create or update the next maintenance action based on the changed/new maint requirement
			MFTUMaintNextAction na = MFTUMaintNextAction.getByComponentAndMaintRequirement(getCtx(), comp.getCT_Component_ID(), getFTU_MaintRequirement_ID(), get_TrxName());
			na.recalc();
			na.saveEx();  
		}

		return success;
	}	//	afterSave
	
	/**
	 * Determine if the Maintenance Requirement has detail lines.
	 * @return true if there are lines.  
	 */
	public boolean hasLines() {
		
		if (m_lines == null)
			m_lines = getLines(false);
		
		return m_lines.size() > 0;
	}

	/**
	 * Get the List of Maintenance Requirement detail lines or an empty list.
	 * @param requery - true to requery the database. False will use a cached version
	 * @return the List of detail lines or an empty List. 
	 */
	public List<MFTUMaintRequirementLine> getLines(boolean requery) {
		
		if (m_lines == null || requery)
		{

			String where = MFTUMaintRequirementLine.COLUMNNAME_FTU_MaintRequirement_ID + "=?";

			m_lines = new Query(this.getCtx(), MFTUMaintRequirementLine.Table_Name, where, this.get_TrxName())
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.setParameters(this.getFTU_MaintRequirement_ID())
							.list();

		}
		return m_lines;
	}

	public static List<MFTUMaintRequirement> getByFTU_AirworthinessDirective(
			Properties ctx, int ftu_airworthinessDirective_id, String trxName) {
		String where = MFTUMaintRequirement.COLUMNNAME_FTU_AirworthinessDirective_ID + "=?";
		
		return new Query(ctx, MFTUMaintRequirement.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ftu_airworthinessDirective_id)
					.list();
	}

	public static void addCT_Component(Properties ctx, int ct_component_id,
			String trxName) {
		
		List<MFTUMaintRequirement> mrList = MFTUMaintRequirement.getOrCreateByCT_Component(ctx, ct_component_id, trxName);

		for (MFTUMaintRequirement mr : mrList)
		{
			//  Create or update the next maintenance action based on the component and maint req.
			MFTUMaintNextAction na = MFTUMaintNextAction.getByComponentAndMaintRequirement(ctx, ct_component_id, mr.getFTU_MaintRequirement_ID(), trxName);
			na.recalc();
			na.saveEx();  //  Most of the action happens in the before save.
		}
	}

	/**
	 * 	Executed before Delete operation.
	 *	@return true if record can be deleted
	 */
	protected boolean beforeDelete ()
	{
		String where = MFTUMaintNextAction.COLUMNNAME_CT_Component_ID + "=?"
				+ " AND " + MFTUMaintNextAction.COLUMNNAME_FTU_MaintRequirement_ID + "=?";
		
		MFTUMaintNextAction na =  new Query(getCtx(), I_FTU_MaintNextAction.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(getCT_Component_ID(), getFTU_MaintRequirement_ID())
					.firstOnly();

		if (na != null)
			na.deleteEx(true);
		
		//	log.saveError("Error", Msg.getMsg(getCtx(), "CannotDelete"));
		return true;
	} 	//	beforeDelete

	public static List<MFTUMaintRequirement> getByFTU_PhaseFromMaintReq(
			Properties ctx, int ftu_MaintRequirement_ID, String trxName) {

		String where = MFTUMaintRequirement.COLUMNNAME_FTU_PhaseFromMaintReq_ID + "=?";
		
		return new Query(ctx, MFTUMaintRequirement.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ftu_MaintRequirement_ID)
					.list();
	}

}
