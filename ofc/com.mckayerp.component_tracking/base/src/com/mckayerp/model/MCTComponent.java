/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package com.mckayerp.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MClient;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MProduct;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

/**
 * Class MCTComponent provides model support for component-level tracking of
 * products.  A component is a particular product and Attribute Set Instance
 * (ASI).  The tracking covers location in storage and installation in 
 * assemblies.
 * 
 * @author mckayERP
 *
 */
public class MCTComponent extends X_CT_Component {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5385593210662916999L;
	private MCTComponentBOM m_bom;

	public MCTComponent(Properties ctx, int CT_Component_ID, String trxName) {
		super(ctx, CT_Component_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCTComponent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find the component entry for a product and attribute set instance pair.  Throws an error if more 
	 * than one matching component if found.
	 * @param ctx
	 * @param m_product_id 0 if unknown
	 * @param m_attributeSetInstance_id must be > 0
	 * @param trxName
	 * @return  The MFTUComponent represented by the product/ASI pair or null.
	 */
	public static MCTComponent getByProductAndASI(Properties ctx, int m_product_id, int m_attributeSetInstance_id, String trxName)
	{
		String where = "M_Product_ID=? AND M_AttributeSetInstance_ID=?";
		
		MCTComponent component = (MCTComponent) new Query(ctx, MCTComponent.Table_Name, where, trxName)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(m_product_id, m_attributeSetInstance_id)
								.firstOnly();
		
		return component;
	}

	/**
	 * Find the components for an attribute set instance. There can be more than one if the ASI has 
	 * no instance info or is not a single quantity - i.e. a lot.  
	 * @param ctx
	 * @param m_product_id 0 if unknown
	 * @param m_attributeSetInstance_id must be > 0
	 * @param trxName
	 * @return  A list with components or empty.
	 */
	public static List<MCTComponent> getByASI(Properties ctx, int m_attributeSetInstance_id, String trxName)
	{
		String where = "M_AttributeSetInstance_ID=?";
		
		List<MCTComponent> components = new Query(ctx, MCTComponent.Table_Name, where, trxName)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(m_attributeSetInstance_id)
								.list();
		
		return components;
	}

	/**
	 * Find the components of a product.
	 * @param ctx
	 * @param m_product_id
	 * @param trxName
	 * @return  A List of MFTUComponent that have the provided product ID.
	 */
	public static List<MCTComponent> getByProduct(Properties ctx, int m_product_id, String trxName)
	{
		String where = "M_Product_ID=?";
		
		return new Query(ctx, MCTComponent.Table_Name, where, trxName)
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.setParameters(m_product_id)
							.list();		
	}

	public int getParentComponent_ID() {
		
		// If the root component is zero, there is no parent.
		if (this.getRoot_Component_ID() == 0)
			return 0;
		
		String where = MCTComponentBOMLine.COLUMNNAME_CT_Component_ID + "=?";
		
		List<MCTComponentBOMLine> bomLines = new Query(this.getCtx(), MCTComponentBOMLine.Table_Name, where, this.get_TrxName())
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.setParameters(this.getCT_Component_ID())
						.list();
		
		if (bomLines.size() == 1)
		{
			return bomLines.get(0).getCT_ComponentBOM().getCT_Component_ID();
		}
		return 0;
	}

	public BigDecimal getLife() {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal getParenetLife() {
		// TODO Auto-generated method stub.  What if collective?
		return null;
	}
	
	/**
	 * 	Called after Save for Post-Save Operation
	 * 	@param newRecord new record
	 *	@param success true if save operation was success
	 *	@return if save was a success
	 */
	protected boolean afterSave (boolean newRecord, boolean success) {

		//  If new record, add created action to history
		if (newRecord) 
		{
			
			MCTComponentHistory history = new MCTComponentHistory(getCtx(), 0, get_TrxName());
			history.setCT_Component_ID(getCT_Component_ID());
			history.setCT_ComponentActionType(MCTComponentHistory.CT_COMPONENTACTIONTYPE_Created);
			history.setMovementType(null);  // Can't be an empty string
			history.setDateAction(new Timestamp(System.currentTimeMillis()));
			history.setQty(Env.ZERO);
			history.setM_Locator_ID(0);
			history.setParentComponent_ID(0);
			history.setComponentLifeAtAction(this.getLifeUsed());  // TODO: Will be the current life, not the life at the movement date.
			history.setParentLifeAtAction(Env.ZERO); // TODO: will be the current life, not the life at the movement date.
			history.saveEx();
			
			createOrUpdateComponentBOM();
			
		}
		else if (is_ValueChanged(MCTComponent.COLUMNNAME_LifeUsed))
		{
			updateSubComponentLife();
		}

		if (is_ValueChanged(MCTComponent.COLUMNNAME_Root_Component_ID)
			|| is_ValueChanged(MCTComponent.COLUMNNAME_Root_Locator_ID)
			|| is_ValueChanged(MCTComponent.COLUMNNAME_Root_InOut_ID))
		{
			updateRootElements();
		}

		// TODO If life value changes manually?
		
		return success;
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
		//  For new component records or in case the Overhaul count was 
		//  not previously set, set it to one. 
		if (newRecord || this.getOverhaulCount() == 0)
		{
			this.setOverhaulCount(1);
		}
		
		if (!newRecord && this.is_ValueChanged(COLUMNNAME_CT_CompLifeCycleModel_ID)
				&& this.getCT_CompLifeCycleModel_ID() > 0)
		{
			// Update the component model with the LCM values
			MCTCompLifeCycleModel lcm = (MCTCompLifeCycleModel) this.getCT_CompLifeCycleModel();
			this.setLifeUsageSource(lcm.getLifeUsageSource());
			this.setLifeUsageUOM_ID(lcm.getLifeUsageUOM_ID());
			this.setIsOnCondition(lcm.isOnCondition());
			this.setMaxLifeUsage(lcm.getMaxLifeUsage());
			this.setMaxOverhaulCount(lcm.getMaxOverhaulCount());
			
		}
		//	setRoot_Component_ID(getCT_Component_ID());
		
		return true;
	}	//	beforeSave

	/**
	 * Update the root elements of all sub components to match the root of this 
	 * component or change the elements of the sub components to this component where
	 * this root is null/zero.  Also updates the root locator and root in/out fields.
	 */
	private void updateRootElements() {
		
		if (getBOM() == null)
			return;
		
		int root_component_id = this.getRoot_Component_ID();
		if (root_component_id == 0)
			root_component_id = this.getCT_Component_ID();

		int root_locator_id = this.getRoot_Locator_ID(); // Zero/null allowed
		int root_inOut_id = this.getRoot_InOut_ID(); // Zero/null allowed

		List<MCTComponentBOMLine> bomLines = getBOM().getLines();
		
		for (MCTComponentBOMLine bomLine : bomLines)
		{
			MCTComponent subComp = (MCTComponent) bomLine.getCT_Component();
			if (subComp != null && subComp.get_ID() > 0)
			{
				subComp.setRoot_Component_ID(root_component_id);
				subComp.setRoot_Locator_ID(root_locator_id);
				subComp.setRoot_InOut_ID(root_inOut_id);
				subComp.saveEx();  // Will update sub-sub components
			}
		}
	}

	public String createOrUpdateComponentBOM() {
		
		MCTComponentBOM.createOrUpdateComponentBOM(getCtx(), getCT_Component_ID(), get_TrxName());
	
		return null;
	}

	/**
	 * Update the life used of all subcomponents where the subcomponents 
	 * inherit the usage of the parent assembly.  Together with the 
	 * afterSave of the component, this function will recursively 
	 * descend the BOM tree until there are no subscomponents that 
	 * inherit their life from the parents.  Subassemblies that do not
	 * inherit life usage will not be explored.
	 */
	private void updateSubComponentLife() {
				
		if (getBOM() == null)
			return;
		
		List<MCTComponentBOMLine> bomLines = getBOM().getLines();
		
		for (MCTComponentBOMLine bomLine : bomLines)
		{
			MCTComponent subComp = (MCTComponent) bomLine.getCT_Component();
			if (subComp != null && MCTComponent.LIFEUSAGESOURCE_InheritFromParent.equals(subComp.getLifeUsageSource()))
			{
				bomLine.setCurrentParentLife(getLifeUsed());
				bomLine.saveEx();	// Before save updates the installed component			  
			}
		}
	}

	private MCTComponentBOM getBOM() {

		if (m_bom == null)
		{

			String where = MCTComponentBOM.COLUMNNAME_CT_Component_ID + "=?";
			m_bom = new Query(this.getCtx(), MCTComponentBOM.Table_Name, where, this.get_TrxName())
										.setClient_ID()
										.setOnlyActiveRecords(true)
										.setParameters(this.getCT_Component_ID())
										.firstOnly();

		}
		return m_bom;
	}

	/** Set Product and associated component life cycle model
	 *  @param M_Product_ID 
	 *  Product, Service, Item
	 */
	public void setM_Product_ID (int M_Product_ID)
	{		
		super.setM_Product_ID(M_Product_ID);
		
		if (M_Product_ID > 0)
		{
			MProduct product = MProduct.get(getCtx(), M_Product_ID);
			MCTCompLifeCycleModel model = MCTCompLifeCycleModel.getByProduct(getCtx(), product, get_TrxName());
						
			if (model != null)
			{
				setLifeUsageSource(model.getLifeUsageSource());
				setLifeUsageUOM_ID(model.getLifeUsageUOM_ID());
				setMaxLifeUsage(model.getMaxLifeUsage());
				setIsLifeExtensionPossible(model.isLifeExtensionPossible());
			}

		}
		else
		{
			setLifeUsageSource(MCTComponent.LIFEUSAGESOURCE_NotTracked);
			setLifeUsageUOM_ID(0);
			setMaxLifeUsage(null);
			setIsLifeExtensionPossible(false);			
		}
	}

	
    public String toString()
    {
    	StringBuffer sb = new StringBuffer (Msg.parseTranslation(getCtx(), "@CT_Component_ID@ ["));
      
  		if (getM_Product_ID() > 0)
  		{
  			MProduct product = (MProduct) getM_Product();
  			sb.append(product.getValue())
  				.append(" ").append(product.getName())
  				.append(" ");
  		}
		
  		if (getM_AttributeSetInstance_ID() > 0)
  			sb.append(getM_AttributeSetInstance().getDescription())
  				.append(" ");
  		
  		sb.append("(")
  			.append(get_ID())
  			.append(")]");
  		
      return sb.toString();
    }

    /**
     * Get or create a component based on the product / ASI pair.
     * @param ctx  Context
     * @param m_product_id  
     * 				Throws Illegal Argument Exception if the ID is zero, the 
     * 				product can't be found or the product does not have the
     * 				isTrackAsComponent flag selected 
     * @param m_attributeSetInstance_id
     * 					ASI ID >= 0
     * @param trxName  Transaction Name
     * @return  the Component.
     */
	public static MCTComponent getCreateByProductAndASI(Properties ctx,
			int m_product_id, int m_attributeSetInstance_id, String trxName) {
		
		// Make sure the product exists
		MProduct product = new MProduct(ctx,m_product_id,trxName);
		
		if (product == null  || product.getM_Product_ID() == 0)
		{
			throw new IllegalArgumentException(Msg.parseTranslation(ctx, "@NotFound@ @M_Product_ID@ " + m_product_id));
		}

		// Make sure its a valid product
		if (!product.isTrackAsComponent())
		{
			throw new IllegalArgumentException(Msg.parseTranslation(ctx, "@M_Product_ID@ " + m_product_id + " @" + MProduct.COLUMNNAME_IsTrackAsComponent + "@ = 'N'"));
		}

		// Make sure its a valid product
		if (m_attributeSetInstance_id < 0)
		{
			throw new IllegalArgumentException(Msg.parseTranslation(ctx, "@M_AttributeSetInstance_ID@ < 0"));
		}

		MCTComponent component = getByProductAndASI(ctx, m_product_id, m_attributeSetInstance_id, trxName);
		
		if (component == null)
		{
			component = new MCTComponent(ctx, 0, trxName);
			component.setM_Product_ID(m_product_id);
			component.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
			component.setStartOfLifeDate(new Timestamp(0));  // Value unknown here - use the default of zero (1970)

			MCTCompLifeCycleModel model = MCTCompLifeCycleModel.getByProduct(ctx, product, trxName);

			if (model != null)
			{
				component.setLifeUsageSource(model.getLifeUsageSource());
				component.setLifeUsageUOM_ID(model.getLifeUsageUOM_ID());
				component.setMaxLifeUsage(model.getMaxLifeUsage());
				component.setIsLifeExtensionPossible(model.isLifeExtensionPossible());
			}
			
			component.saveEx();

		}
		return component;
	}

	public static MCTComponent getByAttributSetInstance(Properties ctx,
			Integer m_attributeSetInstance_id, String trxName) {
		String where = "M_AttributeSetInstance_ID=?";
		
		MCTComponent component = (MCTComponent) new Query(ctx, MCTComponent.Table_Name, where, trxName)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(m_attributeSetInstance_id)
								.firstOnly();  // should only be one if any
		
		return component;
	}

	public static void main(String[] args) {

		Properties ctx = Env.getCtx();
		
		Adempiere.startupEnvironment(false);
		
		if (! DB.isConnected()) {
			System.exit(1);
		}

		MClient[] clients = MClient.getAll(Env.getCtx());
		
		for (MClient client : clients)
		{
			if (client.get_ID() != 1000000)  // OFC
				continue;
			
			ctx.clear();
			Env.setContext(ctx, "#AD_Client_ID", client.getAD_Client_ID());
			Env.setContext(ctx, "#AD_Language", client.getAD_Language());
			Env.setContext(ctx, "#AD_Org_ID", 0);
			Env.setContext(ctx, "#AD_User_ID", MUser.getWithRole(MRole.getOfClient(ctx)[0])[0].get_ID());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat dateFormat4Timestamp = new SimpleDateFormat("yyyy-MM-dd"); 
			Env.setContext(ctx, "#Date", dateFormat4Timestamp.format(ts)+" 00:00:00" );    //  JDBC format
			//Env.setCtx(ctx);
			
			System.out.println("Client ID: " + Env.getAD_Client_ID(ctx));
	
			String whereClause = "NOT EXISTS (SELECT 1 FROM " + MCTComponentBOMLine.Table_Name 
					+ " cbl WHERE cbl." + MCTComponentBOMLine.COLUMNNAME_CT_Component_ID + "="
					+ MCTComponent.Table_Name + "." + MCTComponent.COLUMNNAME_CT_Component_ID
					+ ")";
						
			String trxName = Trx.createTrxName();
			int[] comp_ids = MCTComponent.getAllIDs(MCTComponent.Table_Name, whereClause, trxName);
			
			for (int comp_id : comp_ids)
			{
				MCTComponent comp = new MCTComponent(ctx, comp_id, trxName);
				if (comp.get_ID() == 0)
					continue;

				comp.setRoot_Component_ID(comp.getCT_Component_ID());
				comp.updateRootElements();
				comp.saveEx();
			}

			try {
				DB.commit(false, trxName);
			} catch (IllegalStateException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public List<MCTComponentBOMLine> getUninstalledComponentBOMlines() {
		
		List<MCTComponentBOMLine> uninstalledLines = new ArrayList<MCTComponentBOMLine>();
		
		if (getBOM() == null)
			return uninstalledLines;
		
		List<MCTComponentBOMLine> bomLines = getBOM().getLines();
		
		for (MCTComponentBOMLine bomLine : bomLines)
		{
			if (bomLine.getQtyInstalled().compareTo(bomLine.getQtyRequired()) < 0)
			{
				uninstalledLines.add(bomLine);
			}
			if (bomLine.getCT_Component_ID() > 0)
			{
				// Drill down
				MCTComponent subComp = (MCTComponent) bomLine.getCT_Component();
				uninstalledLines.addAll(subComp.getUninstalledComponentBOMlines());
			}
		}
		return uninstalledLines;
	}
	
    /** Get Root Component or this component if the root is zero.
	 *@return The Root Component of the component BOM tree.
	 */
	@Override
	public int getRoot_Component_ID() 
	{
		int ct_component_id = super.getRoot_Component_ID();
		if (ct_component_id <= 0)
			ct_component_id = getCT_Component_ID();
	
		return ct_component_id;
	}

	@Override
	public I_CT_Component getRoot_Component() throws RuntimeException
    {
		
		if (super.getRoot_Component_ID() <= 0)
			return this;
		
		return super.getRoot_Component();
    }

	public static Collection<? extends MCTComponent> getByProductGroup(
			Properties ctx, int m_product_group_id, String trxName) {
		
		String where = MCTComponent.COLUMNNAME_M_Product_ID + " IN"
				+ " (SELECT " + MProduct.COLUMNNAME_M_Product_ID 
				+ " FROM " + MProduct.Table_Name 
				+ " WHERE " + MProduct.COLUMNNAME_M_Product_Group_ID + "=?)";
		
		return new Query(ctx, MCTComponent.Table_Name, where, trxName)
							.setClient_ID()
							.setOnlyActiveRecords(true)
							.setParameters(m_product_group_id)
							.list();		

	}

	public boolean overhaul(int ftu_MaintWOResultLine_ID, Timestamp dateAction) {
		
		// If the component life is zero, there is nothing to do.
		if (this.getLifeUsed().compareTo(Env.ZERO) == 0)
			return true;
		
		if (dateAction == null)
			dateAction = new Timestamp(System.currentTimeMillis());
		
		// Overhaul the component
		// Create a history record with the current life
		MCTComponentHistory history = new MCTComponentHistory(getCtx(), 0, get_TrxName());
		history.setCT_Component_ID(getCT_Component_ID());
		history.setComponentLifeAtAction(getLifeUsed());
		history.setOverhaulCount(getOverhaulCount());
		history.setCT_ComponentActionType(MCTComponentHistory.CT_COMPONENTACTIONTYPE_Overhauled);
		history.setDateAction(dateAction);
		history.setRoot_Component_ID(getRoot_Component_ID());
		history.setParentLifeAtAction(Env.ZERO);
		history.setFTU_MaintWOResultLine_ID(ftu_MaintWOResultLine_ID);  // Library access?
		history.saveEx();

		this.setLifeUsed(Env.ZERO);
		this.setOverhaulCount(getOverhaulCount()+1);
		
		if (getOverhaulCount() > this.getMaxOverhaulCount())
		{
			// TODO do something about it. Defect?
		}
		
		// Record the changes
		saveEx();
		
		return getOverhaulCount() <= this.getMaxOverhaulCount();
	}

	public boolean isServicable() {
		
		// Life usage (Time since overhaul) is less than the max allowed
		boolean usageOK = getMaxLifeUsage().compareTo(Env.ZERO) == 0 
				|| this.getLifeUsed().compareTo(getMaxLifeUsage()) < 0
				|| this.isOnCondition();
		
		boolean overhaulCountOK =  getMaxOverhaulCount() == 0
					|| getOverhaulCount() <= getMaxOverhaulCount();
		
		return usageOK && overhaulCountOK;
	}
}
