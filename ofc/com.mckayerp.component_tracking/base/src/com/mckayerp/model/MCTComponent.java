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
	 * @param m_product_id
	 * @param m_attributeSetInstance_id
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
		// TODO Auto-generated method stub.  What if collective?
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
		
		if (getRoot_Component_ID() == 0)
			setRoot_Component_ID(getCT_Component_ID());
		
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
		
		// Copy the product BOM
		MProduct product =  (MProduct) getM_Product();
		
		if (!product.isBOM())
		{
			return null;
		}

		//  Get the component BOM - there should only be one as the BOM represents a physical reality where the product BOM
		//  can represent one of a number of design specs.  Limit of one BOM per component is in the MCTComponentBOM beforeSave().
		//  TODO - deal with engineering changes to components that require multiple BOMs. Not sure this is required.
		MCTComponentBOM componentBOM = MCTComponentBOM.getByCT_Component_ID(getCtx(), getCT_Component_ID(), get_TrxName());
		MPPProductBOM bomMaster = null;
		
		if (componentBOM != null)
		{
			bomMaster = (MPPProductBOM) componentBOM.getPP_Product_BOM();
		}

		if (bomMaster == null)
		{
			List<MPPProductBOM> bomList = MPPProductBOM.getProductBOMs(product);
			for (MPPProductBOM bom : bomList)
			{
				//  Find a valid master BOM to use as the master.
				if (bom.getBOMUse().equals(MPPProductBOM.BOMUSE_Master) 
						&& bom.isValidFromTo(Env.getContextAsDate(getCtx(), "#Date")))
				{
					bomMaster = bom;
					break;
				}
			}
		}
		
		if (bomMaster == null)
		{
			return Msg.parseTranslation(getCtx(), "@PP_ProductBOM@ @NotFound@");
		}
		
		// We should have a valid BOM master
		
		//  Check if the BOM needs to be added.  This can happen if the component was defined BEFORE
		//  the product BOM was created.
		if (componentBOM == null)
		{
			componentBOM = addComponentBOM(getCtx(), bomMaster, get_TrxName());
			
			if (componentBOM == null)
			{
				log.severe("Unable to create or find the Component BOM record!");
				return Msg.parseTranslation(getCtx(), "@Error@ Can't create component BOM for " + bomMaster.toString());
			}
		}

		//  Update the componentBOM to match the product BOM Master
		//  Every product BOM line with a component product and positive qty
		//  will be copied across
		for (MPPProductBOMLine bomLine : bomMaster.getLines())
		{

			if (bomLine.getQty() == null 
				|| bomLine.getQty().equals(Env.ZERO)
				|| bomLine.getM_Product_ID() <= 0
				|| !bomLine.getM_Product().isTrackAsComponent())
				continue;

			BigDecimal qtyToMatch = bomLine.getQty();
			
			// Check that every bomLine is included in the component BOM
			// There may be multiple component BOM lines for a product BOM line, if the ASI has instance attributes
			// and the quantity is > 1
			for (MCTComponentBOMLine compBOMLine : componentBOM.getLines(bomLine.getPP_Product_BOMLine_ID()))
			{
				
				// Update the info
				// Easy changes
				compBOMLine.setDescription(bomLine.getDescription());
					
				// Check the product quantities. A single Product BOM line could have
				// multiple component BOM lines if the product BOM line qty > 1 and
				// the ASI has instance values.
				MProduct subProduct = (MProduct) bomLine.getM_Product();
				if (subProduct != null && subProduct.getM_AttributeSet_ID() > 0)
				{
					MAttributeSet mas = (MAttributeSet) subProduct.getM_AttributeSet();
					if (mas.isInstanceAttribute())
					{
							compBOMLine.setQtyRequired(Env.ONE);
							qtyToMatch = qtyToMatch.subtract(Env.ONE);
					}
					else
					{
						compBOMLine.setQtyRequired(bomLine.getQtyBOM()); // Not percentage
						qtyToMatch = Env.ZERO;
					}
				}
					
				// Difficult changes
				if (compBOMLine.getMaster_Product_ID() > 0 
					&& compBOMLine.getMaster_Product_ID() != bomLine.getM_Product_ID() 
					&& compBOMLine.getCT_Component_ID() > 0)
				{
					// Problem. The product BOM has changed product but the component BOM has a 
					// sub component installed in that spot.  Check if the installed component 
					// is a valid substitute.
					MProduct installedProduct = (MProduct) compBOMLine.getM_Product();  // Should be non-null as a component exists.
					MProduct masterProduct = (MProduct) bomLine.getM_Product();
					if (installedProduct.isValidSubstitueFor(masterProduct))
					{
						// Just update the master product.  The installed product is valid
						compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());
					}
					else
					{
						// Need to uninstall the component.  Throw error to prevent changes.
						String errorMsg = this.toString() + " has sub-component " + compBOMLine.getCT_Component().toString();
						errorMsg += " installed on line " + compBOMLine.getLine() + ".  BOM Master Product has changed to " + bomLine.getM_Product().toString();
						errorMsg += " Please uninstall the component and attempt the update again.";
						throw new AdempiereException(errorMsg);
					}
				}
				compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());

				// Update the ASI if there is no component
				if (compBOMLine.getM_AttributeSetInstance_ID() <= 0  
						|| compBOMLine.getCT_Component_ID() <= 0)
				{
					compBOMLine.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
				}
				compBOMLine.saveEx();								
			} //  Check componentBOM has the product BOM Line and qty
			
			if (qtyToMatch.compareTo(Env.ZERO) > 0)  // No match or missing qty
			{
				// Add the missing one(s)
				MProduct subProduct = (MProduct) bomLine.getM_Product();
				int copies = 1;
				BigDecimal qty = qtyToMatch;
				if (subProduct != null && subProduct.getM_AttributeSet_ID() > 0)
				{
					MAttributeSet mas = (MAttributeSet) subProduct.getM_AttributeSet();
					if (mas.isInstanceAttribute())
					{
						copies = qty.intValue(); // Not percentage
						qty = Env.ONE;
					}
				}
				
				// Find the maximum line number
				String where = MCTComponentBOMLine.COLUMNNAME_CT_ComponentBOM_ID + "=?";
				int nextLine = (new Query(getCtx(), MCTComponentBOMLine.Table_Name, where, get_TrxName())
									.setClient_ID()
									.setParameters(componentBOM.getCT_ComponentBOM_ID())
									.aggregate(MCTComponentBOMLine.COLUMNNAME_Line, Query.AGGREGATE_MAX)).intValue();
				
				for (; copies > 0; copies--)
				{
					nextLine += 10;
					MCTComponentBOMLine compBOMLine = new MCTComponentBOMLine(getCtx(), 0, get_TrxName());
					compBOMLine.setCT_ComponentBOM_ID(componentBOM.getCT_ComponentBOM_ID());
					compBOMLine.setPP_Product_BOMLine_ID(bomLine.getPP_Product_BOMLine_ID());
					compBOMLine.setLine(nextLine);
					compBOMLine.setMaster_Product_ID(bomLine.getM_Product_ID());
//					compBOMLine.setM_Product_ID(bomLine.getM_Product_ID());  // Default
//					compBOMLine.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
					compBOMLine.setDescription(bomLine.getDescription());
					compBOMLine.setQtyRequired(qty);
					compBOMLine.saveEx();
				} // Add copies of the product BOM line
			} // missing qty in component BOM lines						
		} // Product BOM Line loop

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
				bomLine.setParentUsageSinceInstall(getLifeUsed().subtract(bomLine.getParentLifeAtInstall()));
				bomLine.setCompUsageSinceInstall(bomLine.getParentUsageSinceInstall());
				bomLine.setCurrentCompLife(bomLine.getCompLifeAtInstall().add(bomLine.getCompUsageSinceInstall()));
				bomLine.saveEx();
				
				subComp.setLifeUsed(bomLine.getCurrentCompLife());
				subComp.saveEx();
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

	/**
	 * Add a BOM skeleton to the Component.  Does not install component parts
	 * in the BOM.  The BOM added should represent the physical reality of the
	 * component as assembled.
	 * 
	 * @param ctx
	 * 			context properties
	 * @param bom
	 * 			the Product BOM to add
	 * @param trxName
	 * 			Transaction Name
	 * @return the Component BOM added.  Returns null if the Product BOM is null or the BOM
	 * 				is not a valid Master
	 */
	private MCTComponentBOM addComponentBOM(Properties ctx, MPPProductBOM bom,
			String trxName){

		// TODO In a production environment, the BOM may be created
		// at the time of production using a different BOM Use type.  
		// The component then should retain its production configuration.

		if (bom == null)
		{
			// Illegal argument exception?
			return null;
		}
		
		// Check that this is the product BOM Master - don't use engineering
		// masters.  
		if (!bom.getBOMUse().equals(MPPProductBOM.BOMUSE_Master) && !bom.isValidFromTo(Env.getContextAsDate(getCtx(), "@#Date@")))
		{
			return null;
		}
			
		// Create a new BOM.  The check that the component has only one BOM is performed
		// in the MCTComponentBOM model beforeSave(). 
		MCTComponentBOM componentBOM = new MCTComponentBOM(ctx, 0, trxName);
		componentBOM.setCT_Component_ID(getCT_Component_ID());
		componentBOM.setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID());
		componentBOM.setName(bom.getName());
		componentBOM.setDescription(bom.getDescription());
		componentBOM.saveEx();
			
		return componentBOM;	
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
}
