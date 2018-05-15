package com.mckayerp.swing.apps.search;

import java.awt.Frame;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import javax.swing.table.DefaultTableModel;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.search.Info;
import org.compiere.apps.search.InfoFactory;
import org.compiere.apps.search.Info_Column;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VPAttribute;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridTab;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.swing.CLabel;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

import com.mckayerp.ftu.model.I_FTU_MaintWORL_Detail;
import com.mckayerp.ftu.model.MFTUMaintWORLDetail;
import com.mckayerp.ftu.model.X_FTU_MaintWORL_Detail;
import com.mckayerp.model.I_CT_Component;
import com.mckayerp.model.I_CT_ComponentHistory;
import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentBOMLine;
import com.mckayerp.model.MCTComponentHistory;

public class InfoComponent extends Info {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7819019191029076539L;
	
	private VLookup fProduct_ID;
	private VPAttribute fASI_ID;
	private VLookup fRootComp_ID;
//	private VLookup fParentComp_ID;
	private VLookup fComponentActionType;
	private VCheckBox fInstalled;

	private CLabel lProduct_ID = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTComponentBOMLine.COLUMNNAME_Master_Product_ID)));
	private CLabel lASI = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTComponent.COLUMNNAME_M_AttributeSetInstance_ID)));
	private CLabel lRootComp_ID = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTComponent.COLUMNNAME_Root_Component_ID)));
//	private CLabel lParentComp_ID = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTComponentHistory.COLUMNNAME_ParentComponent_ID)));  
	private CLabel lInstalled = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), "Installed")));
	
	private int ct_componentBOMLine_id = 0;
	
	private int fieldID = 0;


	/**
	 *	Standard Constructor

	 * @param frame frame
	 * @param modal modal
	 * @param WindowNo window no
	 * @param record_id The record ID to find
	 * @param value query value to find, exclusive of record_id
	 * @param multiSelection multiple selections
	 * @param saveResults  True if results will be saved, false for info only
	 * @param whereClause where clause
	 */
	public InfoComponent (Frame frame, boolean modal, int WindowNo,
		int record_id, String value,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "CT_Component", "CT_Component_ID", multiSelection, saveResults, whereClause);

		setTitle(Msg.getMsg(Env.getCtx(), "InfoComponent"));  // TODO Translate
		//
		StringBuffer where = new StringBuffer();
		where.append("CT_Component.IsActive='Y'");
		if (whereClause != null && !whereClause.isEmpty())
		{
			if (whereClause.indexOf('@') == -1)
				where.append(" AND ").append(whereClause);
			else
			{
				String tempClause = "";
				tempClause = Env.parseContext(Env.getCtx(), p_WindowNo, whereClause, false, false);
				if (tempClause.length() == 0)
				{
					log.log(Level.WARNING, "Cannot parse context= " + whereClause);
				}
				else
				{
					where.append(" AND ").append(whereClause);
				}
			}
		}
		setWhereClause(where.toString());
		setTableLayout(s_Layout);
		setFromClause(s_From);
		setOrderClause("");
		//
		statInit();
		initInfo (record_id, value);

		//  To get the focus after the table update
		m_heldLastFocus = fProduct_ID;
		
		//	AutoQuery
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();
		
		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);
	}
	
	/** From Clause             */
	private static String s_From = "CT_Component CT_Component"
			+ " JOIN M_Product M_Product ON (M_Product.M_Product_ID = CT_Component.M_Product_ID)"
			+ " JOIN M_AttributeSetInstance M_AttributeSetInstance ON (M_AttributeSetInstance.M_AttributeSetInstance_ID = CT_Component.M_AttributeSetInstance_ID)";

	/**  Array of Column Info    */
	private static final Info_Column[] s_Layout = {
		new Info_Column(" ", "CT_Component.CT_Component_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "M_Product_ID"), "M_Product.Value || '_' || M_Product.Name", VLookup.class),
		new Info_Column(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"), "M_AttributeSetInstance.description", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Root_Component_ID"), 
				"(SELECT p.Value || '_' || asi.description"
			+ " FROM CT_Component c"
			+ " JOIN M_Product p ON (p.M_Product_ID = c.M_Product_ID)"
			+ " JOIN M_AttributeSetInstance asi ON (asi.M_AttributeSetInstance_ID = c.M_AttributeSetInstance_ID )"
			+ " WHERE c.CT_Component_ID = CT_Component.Root_Component_ID)", VLookup.class)
	};

	@Override
	protected String getSQLWhere() {
		
		boolean installed = fInstalled.isSelected();
		
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if (!(fieldID == 0))
		{
			sql.append(" AND CT_Component.CT_Component_ID = ?");
			return sql.toString();
		}
		//  M_Product_ID
		Integer m_product_id = (Integer)fProduct_ID.getValue();
		if (m_product_id != null && m_product_id.intValue() > 0 )
			sql.append (" AND (CT_Component.M_Product_ID=?"
					+ " OR CT_Component.M_Product_ID IN ("
					+ " SELECT sub.Substitute_ID FROM M_Substitute sub"
					+ " where sub.M_Product_ID=?))");
		//  M_AttributeSetInstance_ID
		Integer m_attributeSetInstance_id = (Integer)fASI_ID.getValue();
		if (m_attributeSetInstance_id != null && m_attributeSetInstance_id.compareTo(0) > 0)
			sql.append (" AND CT_Component.M_AttributeSetInstance_ID=?");
		//  Root_Component_ID
		Integer root_component_id = (Integer)fRootComp_ID.getValue();
		if (installed)
		{
			
			if ( ct_componentBOMLine_id > 0)
			{
				sql.append(" AND CT_Component.Root_Component_ID > 0 "
						+ " AND CT_Component.CT_Component_ID IN (SELECT cbl.CT_Component_ID FROM CT_ComponentBOMLine cbl"
						+ " WHERE cbl.CT_ComponentBOMLine_ID = ?)");				
			}
			else if (root_component_id != null && root_component_id.intValue() > 0)
				sql.append (" AND CT_Component.Root_Component_ID=?");
			else
				sql.append(" AND COALESCE(CT_Component.Root_Component_ID, 0)>0");
		}
		else
		{
			if ( ct_componentBOMLine_id > 0)
			{
				sql.append(" AND COALESCE(CT_Component.Root_Component_ID, 0)=0 "
						+ " AND CT_Component.M_Product_ID IN ((SELECT cbl.Master_Product_ID FROM CT_ComponentBOMLine cbl"
						+ " WHERE COALESCE(cbl.QtyInstalled,0) < cbl.QtyRequired "
						+ " AND cbl.CT_ComponentBOMLine_ID = ?)"
						+ " UNION"
						+ " (SELECT sub.Substitute_ID FROM M_Substitute sub"
						+ " JOIN CT_ComponentBOMLine cbl2 ON (sub.M_Product_ID=cbl2.Master_Product_ID)"
						+ " WHERE COALESCE(cbl2.QtyInstalled,0) < cbl2.QtyRequired "
						+ " AND cbl2.CT_ComponentBOMLine_ID = ?))");				
			}
			else if (root_component_id != null)
			{
				sql.append(" AND COALESCE(CT_Component.Root_Component_ID, 0)=0 "
					+ " AND CT_Component.M_Product_ID IN ((SELECT cbl.Master_Product_ID FROM CT_ComponentBOMLine cbl"
					+ " JOIN CT_ComponentBOM cb ON (cb.CT_ComponentBOM_ID = cbl.CT_ComponentBOM_ID)"
					+ " JOIN CT_Component c ON (cb.CT_Component_ID = c.CT_Component_ID)"
					+ " WHERE COALESCE(cbl.QtyInstalled,0) < cbl.QtyRequired "
					+ " AND COALESCE(c.Root_Component_ID, c.CT_Component_ID) = ?)"
					+ " UNION"
					+ " (SELECT sub.Substitute_ID FROM M_Substitute sub"
					+ " JOIN CT_ComponentBOMLine cbl2 ON (sub.M_Product_ID=cbl2.Master_Product_ID)"
					+ " JOIN CT_ComponentBOM cb2 ON (cb2.CT_ComponentBOM_ID = cbl2.CT_ComponentBOM_ID)"
					+ " JOIN CT_Component c2 ON (cb2.CT_Component_ID = c2.CT_Component_ID)"
					+ " WHERE COALESCE(cbl2.QtyInstalled,0) < cbl2.QtyRequired "
					+ " AND COALESCE(c2.Root_Component_ID, c2.CT_Component_ID) = ?)"
					+ ")");
			}
			else
				sql.append(" AND COALESCE(CT_Component.Root_Component_ID, 0)=0"); 
		}
		//
		return sql.toString();
	}

	@Override
	protected void setParameters(PreparedStatement pstmt, boolean forCount)
			throws SQLException {
		
		boolean installed = fInstalled.isSelected();

		int index = 1;
		//  => ID
		if(!(fieldID ==0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record_ID: " + fieldID);
			return;
		}
		//  => Product
		if (fProduct_ID.getValue() != null)
		{
			// Required twice
			Integer p = (Integer)fProduct_ID.getValue();
			pstmt.setInt(index++, p.intValue());
			pstmt.setInt(index++, p.intValue());
			log.fine("Product=" + p);
		}
		//  => AttributeSetInstance
		if (fASI_ID.getValue() != null && ((Integer) fASI_ID.getValue()).compareTo(0)>0 )
		{
			Integer asi = (Integer)fASI_ID.getValue();
			pstmt.setInt(index++, asi.intValue());
			log.fine("AttributeSetInstance=" + asi);
		}
		//  => Root Component (both installed and uninstalled
		if (installed)
		{
			if (ct_componentBOMLine_id > 0)
			{
				pstmt.setInt(index++, ct_componentBOMLine_id);
				log.fine("Component BOM Line ID=" + ct_componentBOMLine_id);			
			} else if (fRootComp_ID.getValue() != null)
			{
				Integer rc = (Integer)fRootComp_ID.getValue();
				pstmt.setInt(index++, rc.intValue());
				log.fine("Root Component=" + rc);
			}
		}
		else
		{
			if (ct_componentBOMLine_id > 0)
			{
				// Required twice
				pstmt.setInt(index++, ct_componentBOMLine_id);
				pstmt.setInt(index++, ct_componentBOMLine_id);
				log.fine("Component BOM Line ID=" + ct_componentBOMLine_id);			
			} else if (fRootComp_ID.getValue() != null)
			{
				// Required twice
				Integer rc = (Integer)fRootComp_ID.getValue();
				pstmt.setInt(index++, rc.intValue());
				pstmt.setInt(index++, rc.intValue());
				log.fine("Root Component=" + rc);
			}			
		}
	}

	/**
	 *  Save Selection Details
	 *  Get Location/Partner Info
	 */
	public void saveSelectionDetail()
	{
		int row = p_table.getSelectedRow();
		if (row == -1)
			return;

		//  publish for Callout to read
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "CT_Component_ID", ID == null ? "0" : ID.toString());
	}   //  saveSelectionDetail

	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	private void statInit()
	{
		//	From CT_Component.
		
		fProduct_ID = new VLookup("M_Product_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
					MColumn.getColumn_ID(I_CT_Component.Table_Name,I_CT_Component.COLUMNNAME_M_Product_ID),
					DisplayType.Search));
		lProduct_ID.setLabelFor(fProduct_ID);
		fProduct_ID.setBackground(AdempierePLAF.getInfoBackground());
		fProduct_ID.addVetoableChangeListener(this);
		//
		lASI.setText(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		fASI_ID = new VPAttribute((GridTab) null, false, false, true, p_WindowNo, 
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MAttributeSetInstance.Table_Name, MAttributeSetInstance.COLUMNNAME_M_AttributeSet_ID),
						DisplayType.PAttribute), true);
		fASI_ID.setBackground(AdempierePLAF.getInfoBackground());
		fASI_ID.addVetoableChangeListener(this);
		//
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
				MColumn.getColumn_ID(I_CT_Component.Table_Name,I_CT_Component.COLUMNNAME_Root_Component_ID),
				DisplayType.Search);
		lookup.getLookupInfo().InfoFactoryClass = 	MColumn.get(Env.getCtx(), MColumn.getColumn_ID(I_CT_Component.Table_Name,I_CT_Component.COLUMNNAME_Root_Component_ID)).getInfoFactoryClass();

		fRootComp_ID = new VLookup("Root_Component_ID", false, false, true, lookup);
		lRootComp_ID.setLabelFor(fRootComp_ID);
		fRootComp_ID.setBackground(AdempierePLAF.getInfoBackground());
		fRootComp_ID.addVetoableChangeListener(this);

		fInstalled = new VCheckBox();
		lInstalled.setLabelFor(fInstalled);
		fInstalled.setBackground(AdempierePLAF.getInfoBackground());
		fInstalled.addVetoableChangeListener(this);

		String actionType = Env.getContext(Env.getCtx(), lookup.getWindowNo(), X_FTU_MaintWORL_Detail.COLUMNNAME_CT_ComponentActionType);
		int root_component_id = Env.getContextAsInt(Env.getCtx(), lookup.getWindowNo(), X_FTU_MaintWORL_Detail.COLUMNNAME_Root_Component_ID);
		int target_root_component_id = Env.getContextAsInt(Env.getCtx(), lookup.getWindowNo(), X_FTU_MaintWORL_Detail.COLUMNNAME_Target_RootComponent_ID);
		int bomLine_id = Env.getContextAsInt(Env.getCtx(), lookup.getWindowNo(), X_FTU_MaintWORL_Detail.COLUMNNAME_CT_ComponentBOMLine_ID);
		int target_bomLine_id = Env.getContextAsInt(Env.getCtx(), lookup.getWindowNo(), X_FTU_MaintWORL_Detail.COLUMNNAME_Target_ComponentBOMLine_ID);
		if (X_FTU_MaintWORL_Detail.CT_COMPONENTACTIONTYPE_Uninstalled.equals(actionType))
		{
			// List the installed products
			fInstalled.setSelected(true);
			fRootComp_ID.setValue(root_component_id);
			ct_componentBOMLine_id = bomLine_id;
		}
		else if (X_FTU_MaintWORL_Detail.CT_COMPONENTACTIONTYPE_Installed.equals(actionType))
		{
			// List the installed products
			fInstalled.setSelected(false);
			fRootComp_ID.setValue(target_root_component_id);
			ct_componentBOMLine_id = target_bomLine_id;
		}

//		fComponentActionType = new VLookup("CT_ComponentActionType", false, false, true,
//			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
//					MColumn.getColumn_ID(MFTUMaintWORLDetail.Table_Name,MFTUMaintWORLDetail.COLUMNNAME_CT_ComponentActionType),
//					DisplayType.Search));
//		String ct_componentActionType = Env.getContext(Env.getCtx(), lookup.getWindowNo(), MFTUMaintWORLDetail.COLUMNNAME_CT_ComponentActionType);
//		fComponentActionType.setValue(ct_componentActionType);

		//
		p_criteriaGrid.add(lProduct_ID, new ALayoutConstraint(0,0));
		p_criteriaGrid.add(fProduct_ID, null);
		p_criteriaGrid.add(lASI, null);
		p_criteriaGrid.add(fASI_ID, null);
		p_criteriaGrid.add(lRootComp_ID, new ALayoutConstraint(1,0));
		p_criteriaGrid.add(fRootComp_ID, null);
		p_criteriaGrid.add(lInstalled, null);
		p_criteriaGrid.add(fInstalled, null);
		
	}	//	statInit

	/**
	 *	Dynamic Init
	 */
	protected void initInfo (int record_id, String value)
	{
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		//  Set Value and boolean criteria (if any)
		if (!(record_id == 0))
		{
			fieldID = record_id;
		}
		else
		{	
			fieldID = 0;
			
			// Use the value, if any, to find the component
			if (value != null && value.length() > 0)
			{
				
				// Look for an ASI with an attribute value that matches the value
				Integer asi = MAttributeSetInstance.getByMatchOfIDSerialNumberLotORDate(Env.getCtx(), 0, value, null);
				if (asi.compareTo(new Integer(0)) > 0)
				{
					fASI_ID.setValue(asi);
					return;
				}				
			}
		}
	}	//	initInfo
	
	/**
	 *	Zoom
	 */
	protected void zoom(int record_ID)
	{
		log.info( "InfoComponent.zoom");
		Integer ct_component_id = record_ID;
		if (ct_component_id == null)
			return;
		MQuery query = new MQuery("CT_Component");
		query.addRestriction("CT_Component_ID", MQuery.EQUAL, ct_component_id);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("CT_Component", true);
		zoom (AD_WindowNo, query);
	}	//	zoom

	/**
	 *	Has Zoom
	 *  @return true
	 */
	protected boolean hasZoom()
	{
		return true;
	}	//	hasZoom

	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return (
				fProduct_ID.hasChanged()	||
				fASI_ID.hasChanged() ||
				fRootComp_ID.hasChanged()
				);
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fProduct_ID.set_oldValue();
		fASI_ID.set_oldValue();
		fRootComp_ID.set_oldValue();
		return;
	}
	/**
	 *  Clear all fields and set default values in check boxes
	 */
	protected void clearParameters()
	{
		//  Clear fields and set defaults
    	fProduct_ID.setValue(null);
    	fASI_ID.setValue(null);
    	fRootComp_ID.setValue(null);
    	fInstalled.setSelected(false);
    	ct_componentBOMLine_id = 0;
	}
}
