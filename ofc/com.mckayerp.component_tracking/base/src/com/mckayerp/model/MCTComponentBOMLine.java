package com.mckayerp.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MColumn;
import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.mckayerp.ftu.model.MFTUDefectLog;

public class MCTComponentBOMLine extends X_CT_ComponentBOMLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6697382509021814829L;
	
	/** Static Logger					*/
	private static CLogger		s_log = CLogger.getCLogger (MCTComponentBOMLine.class);


	public MCTComponentBOMLine(Properties ctx, int M_ComponentBOMLine_ID,
			String trxName) {
		super(ctx, M_ComponentBOMLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTComponentBOMLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if pre-save operation was success
	 *	@return if save was a success
	 */
	protected boolean beforeSave (boolean newRecord) {
		
		//  Check that the component is a valid component for the BOM line.
		if ((this.getM_Product_ID() <= 0 || this.getM_AttributeSetInstance_ID() < 0) && this.getCT_Component_ID() > 0) 
		{
			if (this.getMaster_Product_ID() > 0)  // Mandatory - should always be > 0
			{
				
				//  The product could be a substitute
				MProduct masterProduct = (MProduct) this.getMaster_Product();
				MProduct compProduct = (MProduct) this.getCT_Component().getM_Product();
				if (!compProduct.isValidSubstitueFor(masterProduct))
				{
					log.saveError("Error", "The component is not a valid substitute product for this BOM line.");
					return false;
				}
				else
				{
					this.setM_Product_ID(compProduct.getM_Product_ID());
					this.setM_AttributeSetInstance_ID(this.getCT_Component().getM_AttributeSetInstance_ID());
				}
				
				// If the ASI is an instance type, the quantity installed is restricted to one
				if (compProduct.getM_AttributeSetInstance_ID()>0 
						&& compProduct.getM_AttributeSetInstance().getM_AttributeSet().isInstanceAttribute())
				{
					if (this.getQtyInstalled().compareTo(Env.ONE) >= 0)
					{
						log.saveError("Error", "The ASI is an instance attribute which means the quantity can only be one each.");
						return false;
					}
				}

				if (this.getQtyInstalled().compareTo(Env.ZERO) == 0)
				{
					log.saveError("Error", "@QtyInstalled@ == 0");
					return false;
				}
			}
		}
		
		MCTComponent parent = (MCTComponent) this.getCT_ComponentBOM().getCT_Component();
		this.setCurrentParentLife(parent.getLifeUsed());
		
		if (this.getCT_Component_ID() <= 0)
		{
			// See ComponentModelValidator for other 'before save' actions
			this.setQtyInstalled(Env.ZERO);
		}
		
		if (this.getCT_Component_ID() > 0)
		{
			setParentUsageSinceInstall(getCurrentParentLife().subtract(getParentLifeAtInstall()));
			setCompUsageSinceInstall(getParentUsageSinceInstall());
			setCurrentCompLife(getCompLifeAtInstall().add(getCompUsageSinceInstall()));

			MCTComponent subComp = (MCTComponent) getCT_Component(); 
			subComp.setLifeUsed(getCurrentCompLife());
			subComp.saveEx();

		}

		return true;
	}

	/**
	 * 	Get BOM Lines that are based on a particular product BOM Line.  Includes inactive records.
	 *  @param p_productBOMLine_id
	 *  				The ID of the PP_ProductBOMLine upon which the component BOM lines of interest are based
	 * 	@return a List of Component BOM Lines which have the given PP_ProductBOMLine_ID 

	 * @param ctx
	 * 		The context of the transaction
	 * @param pp_productBOMLine_id
	 * 		The ID of the PP_ProductBOMLine upon which the component BOM lines of interest are based				
	 * @param trxName
	 * 		The transaction name for the transaction
	 * @return a List of Component BOM Lines which have the given PP_ProductBOMLine_ID 
	 */
	public static List<MCTComponentBOMLine> getLinesByPPProductBOMLineID(Properties ctx, int pp_productBOMLine_id, String trxName)
	{
		
		final String whereClause = MCTComponentBOMLine.COLUMNNAME_PP_Product_BOMLine_ID + "=?";

		List<MCTComponentBOMLine> lines = new Query(ctx, MCTComponentBOMLine.Table_Name, whereClause, trxName)
											.setParameters(pp_productBOMLine_id)
											.setClient_ID()
											.setOrderBy(MCTComponentBOMLine.COLUMNNAME_Line)
											.list();
		return lines;

	}
	
	/**
	 * 	Get BOM Lines where a particular component is installed. The list does not includes inactive records.
	 *  Generally, the component will be installed on only one assembly. In cases where the ASI does not
	 *  include instance information, it is possible to install the component in many lines at the same time.
	 *  
	 *  @param cc_component_id
	 *  				The ID of the component of interest
	 * 	@return a List of Component BOM Lines where that component is installed. 

	 * @param ctx
	 * 		The context of the transaction
	 * @param ct_component_id
	 * 		The ID of the component installed				
	 * @param trxName
	 * 		The transaction name for the transaction
	 * @return a List of Component BOM Lines where that component is installed 
	 */
	public static List<MCTComponentBOMLine> getLinesByCTComponentID(Properties ctx, int ct_component_id, String trxName)
	{
		
		final String whereClause = MCTComponentBOMLine.COLUMNNAME_CT_Component_ID + "=?";

		List<MCTComponentBOMLine> lines = new Query(ctx, MCTComponentBOMLine.Table_Name, whereClause, trxName)
											.setParameters(ct_component_id)
											.setClient_ID()
											.setOrderBy(MCTComponentBOMLine.COLUMNNAME_Line)
											.list();
		return lines;

	}
	
	public boolean installComponent(int ct_component_id, BigDecimal qty, int ftu_maintWOResultLine_id, Timestamp dateAction)
	{
		Properties ctx = getCtx();
		String trxName = get_TrxName();

		if (ct_component_id <= 0)
			throw new IllegalArgumentException(Msg.parseTranslation(ctx, "@CT_Component_ID@ <= 0"));
		
		if (qty.compareTo(Env.ZERO) <= 0)
			throw new IllegalArgumentException(Msg.parseTranslation(ctx, "@Qty@ <= 0"));
		
		if (qty.add(getQtyInstalled()).compareTo(getQtyRequired()) > 0)
			throw new IllegalArgumentException(Msg.parseTranslation(ctx, "@Qty@ > @QtyRequired@ - @QtyInstalled@"));

		int installedCompID = this.getCT_Component_ID();
		MCTComponent comp = new MCTComponent(ctx, ct_component_id, trxName);
		if (comp.getCT_Component_ID() <= 0)
			throw new AdempiereException(Msg.parseTranslation(ctx, "@NotFound@ @CT_Component_ID@ = " + ct_component_id));
		
		if (!comp.isServicable())
			throw new AdempiereException(Msg.parseTranslation(ctx, "@CT_Component_ID@ is not serviceable. Max life or max overhauls cycles exceeded."));

		// TODO Library access?? Move to FTUModelValidator
		if (0 < MFTUDefectLog.getCountOpenbyComponent(ctx, ct_component_id, trxName))
			throw new AdempiereException(Msg.parseTranslation(ctx, "@CT_Component_ID@ has open defects."));

		if (dateAction == null)
			dateAction = new Timestamp(System.currentTimeMillis());
		
		// Simple case
		if (installedCompID <= 0)
		{
			// The product/substitute compatibility is tested in beforeSave();
			this.setCT_Component_ID(ct_component_id);
		}
		else
		{
			// There already is a component installed - check if its possible
			// to add more qty.  
			if (installedCompID == ct_component_id)
			{
				if (comp.getM_AttributeSetInstance_ID()>0 
						&& ((MAttributeSetInstance) comp.getM_AttributeSetInstance()).hasInstanceValues())
				{
					if (this.getQtyInstalled().compareTo(Env.ONE) >= 0)
					{
						log.severe("The ASI is an instance attribute which means the quantity can only be one each.");
						return false;
					}
				}
			}
			else
			{
				log.severe("A component with different ID is already installed on this BOM line.");
				return false;
			}
		}
		
		setFTU_MaintWOResultLine_ID(ftu_maintWOResultLine_id);
		setQtyInstalled(getQtyInstalled().add(qty));
	    setDateInstalled(dateAction); // As a default

		return true;
	}

	public boolean uninstallComponent(BigDecimal qty, int ftu_maintWOResultLine_id)
	{
		Properties ctx = getCtx();
		String trxName = get_TrxName();
		
		if (qty.compareTo(Env.ONE) < 0)
			throw new IllegalArgumentException(Msg.parseTranslation(ctx, "@Qty@ < 0"));

		if (qty.compareTo(this.getQtyInstalled()) > 0)
			throw new IllegalArgumentException(Msg.parseTranslation(ctx, "@Qty@ > @QtyInstalled@"));

		this.setQtyInstalled(getQtyInstalled().subtract(qty));
		
		// If there are zero installed, remove the component info
		if (getQtyInstalled().compareTo(Env.ZERO) == 0)
		{
			this.setCT_Component_ID(0);
			this.setM_Product_ID(0);
			this.setM_AttributeSetInstance_ID(-1);
		}

		this.setDateInstalled(null);
		this.setFTU_MaintWOResultLine_ID(ftu_maintWOResultLine_id);
		
		return true;
	}

	/**
	 * Determine if the Component BOM Line has ever had a component installed
	 * @return true if there is at least one record of a installed component in the history
	 */
	public boolean hasHistory() {
		
		String where = MCTComponentHistory.COLUMNNAME_CT_ComponentBOMLine_ID + "=?"
				+ " AND " + MCTComponentHistory.COLUMNNAME_CT_ComponentActionType + "=?" ;
		
		return 0 < new Query(getCtx(), MCTComponentHistory.Table_Name, where, get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(
							getCT_ComponentBOMLine_ID(), 
							MCTComponentHistory.CT_COMPONENTACTIONTYPE_Installed)
					.count();
	}


	
	public static List<Integer> getLinesByRootAndDataSet(int root_component_id, int ct_dataset_id)
	{
		
		String sqlFrom = " FROM CT_ComponentData cd"
				+ " JOIN CT_CompLifeCycleModel lcm ON (lcm.CT_CompLifeCycleModel_ID = cd.CT_CompLifeCycleModel_ID)"
				+ " JOIN CT_Component c ON (c.CT_CompLifeCycleModel_ID = lcm.CT_CompLifeCycleModel_ID)"
				+ " JOIN CT_ComponentBOMLine bl ON (bl.CT_Component_ID = c.CT_Component_ID)";

		String sqlWhere = " WHERE cd.IsActive='Y' AND lcm.IsActive='Y'"
				+ " AND c.IsActive = 'Y' and bl.IsActive='Y'";

		String sql = " SELECT bl.ct_componentbomline_id"
				+ sqlFrom
				+ sqlWhere + " AND c.Root_Component_ID = ? AND cd.CT_DataSet_ID = ?"
				+ " GROUP BY bl.ct_componentbomline_id";
	
		List<Integer> ids = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, root_component_id);
			pstmt.setInt(2, ct_dataset_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs,  pstmt);
		}
		
		return ids;
		
	}

}
