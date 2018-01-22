package com.mckayerp.swing.apps.search;

import java.awt.Frame;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.search.Info;
import org.compiere.apps.search.InfoFactory;
import org.compiere.apps.search.Info_Column;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VPAttribute;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridTab;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.swing.CLabel;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

import com.mckayerp.model.I_CT_Component;
import com.mckayerp.model.MCTComponent;

public class InfoComponent extends Info {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7819019191029076539L;
	
	private VLookup fProduct_ID;
	private VPAttribute fASI_ID;

	private CLabel lProduct_ID = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTComponent.COLUMNNAME_M_Product_ID)));
	private CLabel labelASI = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTComponent.COLUMNNAME_M_AttributeSetInstance_ID)));
	
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
		super (frame, modal, WindowNo, "ct", "CT_Component_ID", multiSelection, saveResults, whereClause);

		setTitle(Msg.getMsg(Env.getCtx(), "InfoComponent"));  // TODO Translate
		//
		StringBuffer where = new StringBuffer();
		where.append("ct.IsActive='Y'");
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ").append(whereClause);
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
	private static String s_From = "CT_Component ct"
			+ " JOIN M_Product p ON (p.M_Product_ID = ct.M_Product_ID)"
			+ " JOIN M_AttributeSetInstance asi ON (asi.M_AttributeSetInstance_ID = ct.M_AttributeSetInstance_ID)";

	/**  Array of Column Info    */
	private static final Info_Column[] s_Layout = {
		new Info_Column(" ", "ct.CT_Component_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "M_Product_ID"), "p.Value || '_' || p.Name", VLookup.class),
		new Info_Column(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"), "asi.description", String.class),
	};

	@Override
	protected String getSQLWhere() {
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if (!(fieldID == 0))
			sql.append(" AND ct.CT_Component_ID = ?");
		//  M_Product_ID
		Integer m_product_id = (Integer)fProduct_ID.getValue();
		if (m_product_id != null)
			sql.append (" AND ct.M_Product_ID=?");
		//  M_Product_ID
		Integer m_attributeSetInstance_id = (Integer)fASI_ID.getValue();
		if (m_attributeSetInstance_id != null && m_attributeSetInstance_id.compareTo(0) > 0)
			sql.append (" AND ct.M_AttributeSetInstance_ID=?");
		//
		return sql.toString();
	}

	@Override
	protected void setParameters(PreparedStatement pstmt, boolean forCount)
			throws SQLException {
		int index = 1;
		//  => ID
		if(!(fieldID ==0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record_ID: " + fieldID);
		}
		//  => Product
		if (fProduct_ID.getValue() != null)
		{
			Integer p = (Integer)fProduct_ID.getValue();
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
		fProduct_ID.addActionListener(this);
		//
		labelASI.setText(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		fASI_ID = new VPAttribute((GridTab) null, false, false, true, p_WindowNo, 
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MAttributeSetInstance.Table_Name, MAttributeSetInstance.COLUMNNAME_M_AttributeSet_ID),
						DisplayType.PAttribute), true);
		fASI_ID.setBackground(AdempierePLAF.getInfoBackground());
		fASI_ID.addActionListener(this);

		//
		p_criteriaGrid.add(lProduct_ID, new ALayoutConstraint(0,0));
		p_criteriaGrid.add(fProduct_ID, null);
		p_criteriaGrid.add(labelASI, null);
		p_criteriaGrid.add(fASI_ID, null);
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
			
			// Use the value, if any, to find the product
			if (value != null && value.length() > 0)
			{
				StringBuffer where = new StringBuffer();
				if (value.startsWith("@") && value.endsWith("@"))
				{
					where.append("UPPER(Name) LIKE  ")
						.append(DB.TO_STRING(value.substring(1,value.length()-1)));
				}
				else
				{
					where.append("(UPPER(Value) LIKE ").append(getSQLText(value))
						.append(" OR UPPER(Name) LIKE ").append(getSQLText(value))
						.append(" OR UPPER(SKU) LIKE ").append(getSQLText(value))
						.append(" OR UPPER(UPC) LIKE ").append(getSQLText(value)).append(")");
				}

				MProduct[] products = MProduct.get(Env.getCtx(), where.toString(), null);
				if (products.length > 0)
				{
					
					for (MProduct product : products)
					{
						DefaultTableModel tableModel = (DefaultTableModel) p_table.getModel();
						for (int i = tableModel.getRowCount()-1; i>=0; i--)
							tableModel.removeRow(i);
						
						int rowIndex = 0;
						List<MCTComponent> comps = MCTComponent.getByProduct(Env.getCtx(), product.getM_Product_ID(), null);
						for (MCTComponent comp : comps)
						{
							tableModel.setValueAt(comp.getCT_Component_ID(), rowIndex, 0);
							tableModel.setValueAt(comp.getM_Product_ID(), rowIndex, 1);
							tableModel.setValueAt(comp.getM_AttributeSetInstance_ID(), rowIndex, 2);
						}
					}
				}
				else // No product match - try attribute set instance
				{
					Integer asi_id = MAttributeSetInstance
							.getByMatchOfIDSerialNumberLotORDate(Env.getCtx(), 0, value, null);
					if (asi_id.compareTo(0) > 0)
					{
						DefaultTableModel tableModel = (DefaultTableModel) p_table.getModel();
						for (int i = tableModel.getRowCount()-1; i>=0; i--)
							tableModel.removeRow(i);
						
						int rowIndex = 0;
						MCTComponent comp = MCTComponent.getByAttributSetInstance(Env.getCtx(), asi_id, null);
						tableModel.setValueAt(comp.getCT_Component_ID(), rowIndex, 0);
						tableModel.setValueAt(comp.getM_Product_ID(), rowIndex, 1);
						tableModel.setValueAt(comp.getM_AttributeSetInstance_ID(), rowIndex, 2);
					}
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
		return(
				fProduct_ID.hasChanged()	||
				fASI_ID.hasChanged());
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fProduct_ID.set_oldValue();
		fASI_ID.set_oldValue();
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
	}

}
