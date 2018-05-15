package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.VDataPointCollector;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.swing.CDialog;
import org.compiere.swing.CEditor;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;

import com.mckayerp.model.I_CT_Component;
import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTDataElement;
import com.mckayerp.model.MCTDataElementValue;
import com.mckayerp.model.MCTDataInstance;
import com.mckayerp.model.MCTDataSet;
import com.mckayerp.model.MCTDataSetInstance;

public class VDataPointDialog extends CDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3083696704313555186L;

	@Override
	public void actionPerformed(ActionEvent e) {
		//	OK
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (saveSelection())
				dispose();
		}
		//	Cancel
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			m_ct_dataSetInstance_id = 0;
			m_changed = m_ct_dataSetInstance_id != m_old_ct_dataSetInstance_id;
			dispose();
		}
		else if (e.getSource() instanceof VComboBox)
		{
			if (((VComboBox) e.getSource()).getParent().equals(fComp_ID))
			{
				if (fComp_ID.getValue() != null && ((Integer) fComp_ID.getValue()).compareTo(Integer.valueOf(0)) > 0 )
				{
					int id = ((Integer) fComp_ID.getValue()).intValue();
					MCTComponent comp = new MCTComponent(Env.getCtx(), id, null);
					fCompLife.setValue(comp.getLifeUsed());
					fRootComp_ID.setValue(comp.getRoot_Component_ID());
					fOverhaulCycle.setValue(comp.getOverhaulCount());
					if (comp.getRoot_Component_ID() > 0)
						fRootLife.setValue(comp.getRoot_Component().getLifeUsed());
				}
				else
				{
					fRootComp_ID.setValue(null);
					fCompLife.setValue(null);
					fRootLife.setValue(null);
					fOverhaulCycle.setValue(null);
				}
			}
		}
		else
			log.log(Level.SEVERE, "Event handler not found - " + e);
	}	//	actionPerformed

	
	/*****************************************************************************
	 *	Mouse Listener for Popup Menu
	 */
	final class VDataPoint_mouseAdapter extends java.awt.event.MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VDataPoint_mouseAdapter(VDataPoint adaptee)
		{
			m_adaptee = adaptee;
		}	//	VDataPoint_mouseAdapter

		private VDataPoint m_adaptee;

		/**
		 *	Mouse Listener
		 *  @param e MouseEvent
		 */
		public void mouseClicked(MouseEvent e)
		{
		//	System.out.println("mouseClicked " + e.getID() + " " + e.getSource().getClass().toString());
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				m_adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouse Clicked

	}	//	VDataPoint_mouseAdapter	

	private boolean m_readWrite;
	private int m_old_ct_dataSetInstance_id;
	private Timestamp m_dateRecorded;
	private int m_ct_component_id;
	private int m_root_component_id;
	private BigDecimal m_compLifeAtAction;
	private BigDecimal m_rootLifeAtAction;
	private int m_overhaulCycle;

	
	public VDataPointDialog(VDataPointCollector vDataPointCollector,
			int ct_dataSetInstance_id, int ct_dataset_id,
			int ct_component_id, int root_component_id,
			BigDecimal compLifeAtAction, BigDecimal rootLifeAtAction,
			int overhaulCycle, Timestamp dateRecorded, int AD_Column_ID, int WindowNo, boolean
			readWrite) {
		
		this (vDataPointCollector.getFrame(), ct_dataSetInstance_id, ct_dataset_id, 
				ct_component_id, root_component_id, compLifeAtAction, rootLifeAtAction, overhaulCycle,
				dateRecorded, AD_Column_ID, WindowNo, readWrite);
		
		
		
		addWindowListener(vDataPointCollector);
		
	}

	/**
	 *	Data Point (Data Set Instance) Dialog
	 *	@param frame parent frame
	 *	@param CT_DataSetInstance_ID Data Set Instance id
	 * 	@param CT_Component_ID component id
	 * 	@param DateRecorded Timestamp date+time when recorded
	 * 	@param AD_Column_ID column
	 * 	@param WindowNo window
	 */
	public VDataPointDialog (Frame frame, int ct_dataSetInstance_id, int ct_dataSet_id, 
		int ct_component_id, int root_component_id, BigDecimal compLifeAtAction, 
		BigDecimal rootLifeAtAction, int overhaulCycle,
		Timestamp dateRecorded, int AD_Column_ID, int WindowNo, boolean readWrite)
	{
		super (frame, Msg.translate(Env.getCtx(), "CT_DataSetInstance_ID") , true);
		log.config("CT_DataSetInstance_ID=" + ct_dataSetInstance_id
			+ ", CT_DataSet_ID=" + ct_dataSet_id
			+ ", CT_Component_ID=" + ct_component_id
			+ ", DateRecorded=" + dateRecorded
			+ ", Column=" + AD_Column_ID);
		m_WindowNo = Env.createWindowNo (this);
		m_ct_dataSet_id = ct_dataSet_id;
		m_ct_dataSetInstance_id = ct_dataSetInstance_id;
		m_ct_component_id = ct_component_id;
		m_root_component_id = root_component_id;
		m_compLifeAtAction = compLifeAtAction;
		m_rootLifeAtAction = rootLifeAtAction;
		m_old_ct_dataSetInstance_id = ct_dataSetInstance_id;
		m_dateRecorded = dateRecorded;
		m_AD_Column_ID = AD_Column_ID;
		m_readWrite = readWrite;
		m_overhaulCycle = overhaulCycle;

		//get columnName from ad_column
		if (m_AD_Column_ID > 0)
		{
			m_columnName = DB.getSQLValueString(null, "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID = ?", m_AD_Column_ID);
		}
		
 	 	if (m_columnName == null || m_columnName.trim().length() == 0)
 	 	{
 	 		//fallback
 	 		m_columnName = "CT_DataSetInstance_ID";
 	 	}
 	 	

		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "VDataPoint" + ex);
		}
		//	Dynamic Init
		if (!initDataValues ())
		{
			m_changed = false;
			dispose();
			return;
		}
		AEnv.showCenterWindow(frame, this);
	}	//	VDataPoint


	private int						m_WindowNo;
	private MCTDataSetInstance		m_dataPoint;
	private int 					m_ct_dataSetInstance_id;
	private int						m_ct_dataSet_id;
	private int						m_AD_Column_ID;
	/**	Change							*/
	private boolean					m_changed = false;
	
	private CLogger					log = CLogger.getCLogger(getClass());
	/** Row Counter					*/
	private int						m_row = 0;
	
	/** A set for the editor. */
	private class MEditor {
		
		public MEditor (CEditor ceditor) {
			this.editor = ceditor;
		}
		
		public CEditor editor = null;
	};
	
	/** List of Editors				*/
	private ArrayList<MEditor>		m_editors = new ArrayList<MEditor>();
	private ArrayList<MCTDataElement>	m_dataElements = new ArrayList<MCTDataElement>();

	/** Length of Instance value (40)	*/
	private static final int		INSTANCE_VALUE_LENGTH = 40;

	//
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel centerPanel = new CPanel();
	private ALayout centerLayout = new ALayout(5,5, true);
	private ConfirmPanel confirmPanel = new ConfirmPanel (true);
	private CScrollPane centerScroll = new CScrollPane();

	private String m_columnName = null;
	private VNumber fOverhaulCycle;
	private VNumber fCompLife;
	private VLookup fComp_ID;
	private VNumber fRootLife;
	private VLookup fRootComp_ID;
	private VDate fdateRecorded;

	/**
	 *	Layout
	 * 	@throws Exception
	 */
	private void jbInit () throws Exception
	{
	
		this.getContentPane().setLayout(mainLayout);
		centerScroll.getViewport().add(centerPanel);
		this.add(centerScroll, BorderLayout.CENTER);
		this.getContentPane().add(confirmPanel, BorderLayout.SOUTH);

		centerPanel.setLayout(centerLayout);
		//
		confirmPanel.addActionListener(this);
	}	//	jbInit

	/**
	 *	Dyanmic Init.
	 *  @return true if initialized
	 */
	private boolean initDataValues ()
	{
		MCTDataSet ds = null;

		//  If we have a data set instance id, find the model.
		//  Display the existing data set instance
		if (m_ct_dataSetInstance_id > 0)
		{
			m_dataPoint = new MCTDataSetInstance(Env.getCtx(), m_ct_dataSetInstance_id, null);
			m_ct_dataSet_id = m_dataPoint.getCT_DataSet_ID();
			m_ct_component_id = m_dataPoint.getCT_Component_ID();
			m_root_component_id = m_dataPoint.getRoot_Component_ID();
			m_compLifeAtAction = m_dataPoint.getCT_ComponentLifeAtAction();
			m_rootLifeAtAction = m_dataPoint.getCT_RootLifeAtAction();
			m_dateRecorded = m_dataPoint.getDateRecorded();
			m_overhaulCycle = m_dataPoint.getOverhaulCount();
			ds = (MCTDataSet) m_dataPoint.getCT_DataSet();
			if (m_ct_dataSet_id != ds.getCT_DataSet_ID())
				log.severe("Data sets don't match!!");
		}
		else // We're capturing a new data point
		{
			//  If the data set is undefined, the dialog will have no 
			//  information about what to display so return false.
			if (m_ct_dataSet_id <= 0)
				return false;
			
			ds = new MCTDataSet(Env.getCtx(), m_ct_dataSet_id, null);
			m_dataPoint = ds.createNewDataPoint();
			m_dataPoint.setCT_Component_ID(m_ct_component_id);
			m_dataPoint.setRoot_Component_ID(m_root_component_id);
			m_dataPoint.setDateRecorded(m_dateRecorded);
			m_dataPoint.setCT_ComponentLifeAtAction(m_compLifeAtAction);
			m_dataPoint.setCT_RootLifeAtAction(m_rootLifeAtAction);
			m_dataPoint.setOverhaulCount(m_overhaulCycle);
		}
		
				
		// Clear the data list
		m_dataElements = new ArrayList<MCTDataElement>();
		m_editors = new ArrayList<MEditor>();
		
		//  Clear the current data, if any.
    	centerPanel.removeAll();
    	m_row = 0;

        if (ds != null) 
        {
        	
			CLabel group = new CLabel(Msg.translate(Env.getCtx(), "DataPoint"));
			group.setFontBold(true);
			group.setHorizontalAlignment(SwingConstants.CENTER);
			centerPanel.add(group, new ALayoutConstraint(m_row++,0));
			
			// Add the instance header elements
			// DataSet Name
			CLabel dataSetName = new CLabel(ds.getName());
			dataSetName.setFontBold(true);
			dataSetName.setHorizontalAlignment(SwingConstants.CENTER);			
			centerPanel.add(dataSetName);

			// DataSet Name
			
			fdateRecorded = new VDate("DateRecorded", true, !m_readWrite, true,
					DisplayType.Date, "Date Recorded");
			fdateRecorded.setValue(m_dateRecorded);
			CLabel ldateRecorded = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), "DateRecorded")));
			ldateRecorded.setLabelFor(fdateRecorded);
			
			centerPanel.add(ldateRecorded, new ALayoutConstraint(m_row++,0));
			centerPanel.add(fdateRecorded);

			MLookup lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
					MColumn.getColumn_ID(I_CT_Component.Table_Name,I_CT_Component.COLUMNNAME_Root_Component_ID),
					DisplayType.Search);
			lookup.getLookupInfo().InfoFactoryClass = 	MColumn.get(Env.getCtx(), MColumn.getColumn_ID(I_CT_Component.Table_Name,I_CT_Component.COLUMNNAME_Root_Component_ID)).getInfoFactoryClass();

			fRootComp_ID = new VLookup("Root_Component_ID", false, true, false, lookup);
			fRootComp_ID.setValue(m_root_component_id);
			CLabel lRootComp_ID = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTComponent.COLUMNNAME_Root_Component_ID)));
			lRootComp_ID.setLabelFor(fRootComp_ID);
			
			centerPanel.add(lRootComp_ID, new ALayoutConstraint(m_row++,0));
			centerPanel.add(fRootComp_ID);
			
			fRootLife = new VNumber("CT_RootLifeAtAction", true, !m_readWrite, true, DisplayType.Number, "Root Life");
			fRootLife.setValue(m_rootLifeAtAction);
			CLabel lRootLife = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTDataSetInstance.COLUMNNAME_CT_RootLifeAtAction)));
			lRootLife.setLabelFor(fRootLife);

			centerPanel.add(lRootLife);
			centerPanel.add(fRootLife);
			
			lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
					MColumn.getColumn_ID(I_CT_Component.Table_Name,I_CT_Component.COLUMNNAME_CT_Component_ID),
					DisplayType.Search);
			// Use the same info factory as the root component
			lookup.getLookupInfo().InfoFactoryClass = 	MColumn.get(Env.getCtx(), MColumn.getColumn_ID(I_CT_Component.Table_Name,I_CT_Component.COLUMNNAME_Root_Component_ID)).getInfoFactoryClass();

			fComp_ID = new VLookup("CT_Component_ID", true, !m_readWrite, true, lookup);
			fComp_ID.setValue(m_ct_component_id);
			fComp_ID.addActionListener(this);
			CLabel lComp_ID = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTComponent.COLUMNNAME_CT_Component_ID)));
			lComp_ID.setLabelFor(fComp_ID);
			
			centerPanel.add(lComp_ID, new ALayoutConstraint(m_row++,0));
			centerPanel.add(fComp_ID);

			fCompLife = new VNumber("CT_ComponentLifeAtAction", true, !m_readWrite, true, DisplayType.Number, "Component Life");
			fCompLife.setValue(m_compLifeAtAction);
			CLabel lCompLife = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTDataSetInstance.COLUMNNAME_CT_ComponentLifeAtAction)));
			lCompLife.setLabelFor(fCompLife);

			centerPanel.add(lCompLife);
			centerPanel.add(fCompLife);
			
			fOverhaulCycle = new VNumber("OverhaulCount", true, !m_readWrite, true, DisplayType.Integer, "Component Overhaul Count");
			fOverhaulCycle.setValue(m_overhaulCycle);
			CLabel lOverhaulCycle = new CLabel(Util.cleanAmp(Msg.translate(Env.getCtx(), MCTDataSetInstance.COLUMNNAME_OverhaulCount)));
			lOverhaulCycle.setLabelFor(fOverhaulCycle);
			
			centerPanel.add(lOverhaulCycle, new ALayoutConstraint(m_row++,0));
			centerPanel.add(fOverhaulCycle);
			
			centerPanel.add(new JSeparator(SwingConstants.HORIZONTAL), new ALayoutConstraint(m_row++,0));
			centerPanel.add(new JSeparator(SwingConstants.HORIZONTAL), null);
			centerPanel.add(new JSeparator(SwingConstants.HORIZONTAL), null);
			centerPanel.add(new JSeparator(SwingConstants.HORIZONTAL), null);

			//	Add the Data Elements if any.
			List<MCTDataElement> dataElements = ds.getCT_DataElements();
			log.fine ("Data Element count=" + dataElements.size());
			for (MCTDataElement dataElement : dataElements)
			{
				addDataElementLine (dataElement, !m_readWrite);
			}
        }

		if (m_row == 0)
		{
			ADialog.error(m_WindowNo, this, "DataPointNoInfo");  // TODO Translate
			//return false;
		}

		//	Window usually to wide (??)
		Dimension dd = centerPanel.getPreferredSize();
		dd.width = Math.min(800, dd.width);
		centerPanel.setPreferredSize(dd);
		return true;
	}	//	initDataValues

	/**
	 * 	Add DataElement Line
	 *	@param Data Element
	 * 	@param readOnly value is read only
	 */
	private void addDataElementLine (MCTDataElement dataElement, boolean readOnly)
	{
		log.fine(dataElement + ", R/O=" + readOnly);
		
		boolean isCalculated = dataElement.isCalculated();
		readOnly = readOnly || isCalculated;
		
		CLabel label = new CLabel (dataElement.getValue());
//		label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, label.getFont().getSize()));
		if (dataElement.getDescription() != null)
			label.setToolTipText(dataElement.getDescription());
		if (DisplayType.YesNo != dataElement.getCT_DataType_ID())
			centerPanel.add(label, new ALayoutConstraint(m_row++,0));
		else
			centerPanel.add(new CLabel(""), new ALayoutConstraint(m_row++,0));
		//

		// Set the values according to the instance, if it exists, or the product ASI, if one exists.
		MCTDataInstance instance = null;
		if (m_ct_dataSetInstance_id != 0) 
			instance = dataElement.getCTDataInstance (m_ct_dataSetInstance_id);

		VEditor vEditor = null;
		
		if (DisplayType.List == dataElement.getCT_DataType_ID())
		{
			MCTDataElementValue[] values = dataElement.getDataElementValues();	//	optional = null
			VComboBox editor = new VComboBox(values);
			label.setLabelFor(editor);

			editor.setMandatory(dataElement.isMandatory());
 			if (readOnly) {
 				editor.setReadWrite(false);
				editor.setEditable(false);
 			}

 			boolean found = false;
			if (instance != null)
			{
				for (int i = 0; i < values.length; i++)
				{
					if (values[i] != null && values[i].getCT_DataElementValue_ID () == instance.getCT_DataElementValue_ID ())
					{
						editor.setSelectedIndex (i);
						found = true;
						break;
					}
				}
				if (found)
					log.fine("Data Element=" + dataElement.getName() + " #" + values.length + " - found: " + instance);
				else
					log.warning("Data Element=" + dataElement.getName() + " #" + values.length + " - NOT found: " + instance);
			}	//	setComboBox
			else
				log.fine("Data Element=" + dataElement.getName() + " #" + values.length + " no instance");

			vEditor = editor;
			centerPanel.add(editor, null);

		}
		else if (DisplayType.Number==dataElement.getCT_DataType_ID() 
				|| DisplayType.Quantity==dataElement.getCT_DataType_ID())
		{
			VNumber editor = new VNumber(dataElement.getName(), dataElement.isMandatory(), 
				readOnly, !readOnly, dataElement.getCT_DataType_ID(), dataElement.getName());
			label.setLabelFor(editor);

			if (instance != null)
				editor.setValue(instance.getValueNumber());
			else
				editor.setValue(Env.ZERO);

			vEditor = editor;
			centerPanel.add(editor, null);

		}
		else if (DisplayType.Integer==dataElement.getCT_DataType_ID())
		{
			VNumber editor = new VNumber(dataElement.getName(), dataElement.isMandatory(), 
				readOnly, !readOnly, dataElement.getCT_DataType_ID(), dataElement.getName());
			label.setLabelFor(editor);

			if (instance != null)
				editor.setValue(instance.getValueInteger());
			else
				editor.setValue(Integer.valueOf(0));

			vEditor = editor;
			centerPanel.add(editor, null);

		}
		else if (DisplayType.isDate(dataElement.getCT_DataType_ID()))
		{
			if (dataElement.getCT_DataType_ID() == DisplayType.DateTime)
				readOnly = true;
			VDate editor = new VDate(dataElement.getName(), dataElement.isMandatory(), readOnly, !readOnly,
					dataElement.getCT_DataType_ID(), dataElement.getName());
			label.setLabelFor(editor);

			if (instance != null)
				editor.setValue(instance.getValueDateTime());
			else
				editor.setValue(null);

			vEditor = editor;
			centerPanel.add(editor, null);

		}
		else if (DisplayType.isText(dataElement.getCT_DataType_ID()))
		{
			VString editor = new VString (dataElement.getName(), dataElement.isMandatory(), 
				false, true, 20, INSTANCE_VALUE_LENGTH, null, null);
			label.setLabelFor(editor);

			if (readOnly) {
 				editor.setReadWrite(false);
				editor.setEditable(false);
			}

			if (instance != null)
				editor.setValue(instance.getValueString());

			vEditor = editor;
			centerPanel.add(editor, null);

		}
		else if (DisplayType.YesNo == dataElement.getCT_DataType_ID())	//	Yes-No Field
		{
			VCheckBox editor = new VCheckBox(dataElement.getName(), dataElement.isMandatory(), readOnly, !readOnly,
					dataElement.getName(), dataElement.getDescription(), false);
			label.setLabelFor(editor);
			if (readOnly) {
 				editor.setReadWrite(false);
				editor.setEditable(false);
			}

			if (instance != null)
				editor.setValue(instance.isValueYN());

			vEditor = editor;
			centerPanel.add(editor, null);
			
		}
		else if (DisplayType.isLookup(dataElement.getCT_DataType_ID()))	//	Search Field
		{
			String validationCode = "";
			if (dataElement.getAD_Val_Rule_ID() > 0)
				validationCode = dataElement.getAD_Val_Rule().getCode();
			
			MColumn column = MColumn.get(Env.getCtx(), dataElement.getAD_Column_ID());
			MLookupInfo  lookupInfo = MLookupFactory.getLookupInfo(Env.getCtx(), m_WindowNo, 
				dataElement.getAD_Column_ID(), dataElement.getCT_DataType_ID(), 
				Env.getLanguage(Env.getCtx()), column.getColumnName(), 
				dataElement.getAD_Reference_Value_ID(), false, validationCode);
			//lookupInfo.DisplayType = DisplayType.Search;

			MLookup lookup = new MLookup (lookupInfo, 0);
			// TODO - load info factory class??
			//lookup.getLookupInfo().InfoFactoryClass = 	MColumn.get(Env.getCtx(), dataElement.getAD_Column_ID()).getInfoFactoryClass();

			VLookup editor = new VLookup(dataElement.getName(), dataElement.isMandatory(), readOnly, !readOnly,
					lookup);
			
			label.setLabelFor(editor);
			if (readOnly) {
 				editor.setReadWrite(false);
			}

			if (instance != null)
				editor.setValue(instance.getValueInteger());

			vEditor = editor;
			centerPanel.add(editor, null);
			
		}

		m_dataElements.add (dataElement);
		m_editors.add (new MEditor(vEditor));

		
	}	//	addAttributeLine

	/**
	 *	dispose
	 */
	public void dispose()
	{
		removeAll();
		//
		Env.setContext(Env.getCtx(), m_WindowNo, Env.TAB_INFO, m_columnName, 
			String.valueOf(m_ct_dataSetInstance_id));
		//
		super.dispose();
	}	//	dispose

	/**
	 *	Save Selection
	 *	@return true if saved
	 */
	private boolean saveSelection()
	{
//		if(!m_readWrite)
//			return true;
		
		log.fine("");
				
		String mandatory = "";
		m_changed = false;
		
		//Save the header info
		m_dataPoint.setDateRecorded(fdateRecorded.getTimestamp());
		if (fComp_ID.getValue() != null)
		{
			int id = ((Integer) fComp_ID.getValue()).intValue();
			m_dataPoint.setCT_Component_ID(id);
		}
		else
		{
			m_dataPoint.setCT_Component_ID(0);
		}

		if (fRootComp_ID.getValue() != null)
		{
			int id = ((Integer) fRootComp_ID.getValue()).intValue();
			m_dataPoint.setRoot_Component_ID(id);
		}
		else
		{
			m_dataPoint.setRoot_Component_ID(0);
		}
		
		m_dataPoint.setCT_ComponentLifeAtAction((BigDecimal) fCompLife.getValue());
		m_dataPoint.setCT_RootLifeAtAction((BigDecimal) this.fRootLife.getValue());

		// Get the set of data values from the editors and check for missing
		// mandatory fields.  The order of the data elements is set by the order that
		// editors are created. 
		Object[] values = new Object[m_dataElements.size()];
		for (int i = 0; i < m_dataElements.size(); i++)
		{
			
			if (DisplayType.List == m_dataElements.get(i).getCT_DataType_ID())
			{
				VComboBox editor = (VComboBox)m_editors.get(i).editor;
				values[i] = (MCTDataElementValue) editor.getSelectedItem();
			}
			else if (DisplayType.Number == m_dataElements.get(i).getCT_DataType_ID())
			{
					VNumber editor = (VNumber)m_editors.get(i).editor;
					values[i] = (BigDecimal) editor.getValue();
			}
			else if (DisplayType.Integer == m_dataElements.get(i).getCT_DataType_ID())
			{
					VNumber editor = (VNumber)m_editors.get(i).editor;
					values[i] = (Integer) editor.getValue();
			}
			else if (DisplayType.Quantity == m_dataElements.get(i).getCT_DataType_ID())
			{
					VNumber editor = (VNumber)m_editors.get(i).editor;
					values[i] = (BigDecimal) editor.getValue();
			}
			else if (DisplayType.isDate(m_dataElements.get(i).getCT_DataType_ID()))
			{
				VDate editor = (VDate)m_editors.get(i).editor;
				values[i] = (Timestamp) editor.getValue();
			}
			else if (DisplayType.isText(m_dataElements.get(i).getCT_DataType_ID()))
			{
				VString editor = (VString)m_editors.get(i).editor;
				values[i] = editor.getText();				
			}
			else if (DisplayType.YesNo == m_dataElements.get(i).getCT_DataType_ID())	//	Yes-No Field
			{
				VCheckBox editor = (VCheckBox) m_editors.get(i).editor;
				values[i] = editor.getValue();
			}
			else if (DisplayType.Search == m_dataElements.get(i).getCT_DataType_ID()
					|| DisplayType.Table == m_dataElements.get(i).getCT_DataType_ID())	//	Search Field
			{
				VLookup editor = (VLookup) m_editors.get(i).editor;
				values[i] = editor.getValue();
			}
			else
			{
				log.severe("Data Element (" + i+ ") Unknown display type " + m_dataElements.get(i).getCT_DataType_ID());
			}

			log.fine(m_dataElements.get(i).getName() + "=" + values[i]);
			if (m_dataElements.get(i).isMandatory() && values[i] == null)
				mandatory += " - " + m_dataElements.get(i).getName();

		}
		//  Prevent save if the mandatory fields are not filled.
		if (mandatory.length() > 0)
		{
			ADialog.error(m_WindowNo, this, "FillMandatory", mandatory);
			return false;
		}

//		Trx.run(trxName, new TrxRunnable(){
//			
//			public void run(String trxName)
//			{

				// If new or if anything changed, save the instanceASI
//				if (m_dataPoint.is_new() || m_dataPoint.is_Changed()) {
//					m_dataPoint.set_TrxName(trxName);
					m_changed = true;
					m_dataPoint.setIsComplete(false); // To indicate that the datainstance values which may have changed are not saved yet.
					if (!m_dataPoint.save()) {  // Not saveEx which throws an error.  Trap and display the error.
						String msg = null;
						ValueNamePair err = CLogger.retrieveError();
						if (err != null)
							msg = err.getName();
						if (msg == null || msg.length() == 0)
							msg = "Unknown";
						ADialog.error(m_WindowNo, this, "SaveError", msg);
						return false;
					}
//				}
		
				m_ct_dataSetInstance_id = m_dataPoint.getCT_DataSetInstance_ID();
				
				//  Save attributes
				//  m_readWrite is true
				if (m_ct_dataSetInstance_id > 0) {
					//	Save all Attribute value instances
					for (int i = 0; i < m_dataElements.size(); i++)
					{
						// Find or create the data values for this data set instance
						MCTDataInstance data = MCTDataInstance.getOrCreate(Env.getCtx(), m_ct_dataSetInstance_id, m_dataElements.get(i).getCT_DataElement_ID(), null);
						
						if (DisplayType.List == m_dataElements.get(i).getCT_DataType_ID())
						{
							data.setCT_DataElementValue_ID(((MCTDataElementValue) values[i]).getCT_DataElementValue_ID());
						}
						else if (DisplayType.Number == m_dataElements.get(i).getCT_DataType_ID()
								|| DisplayType.Quantity == m_dataElements.get(i).getCT_DataType_ID())
						{
							data.setValueNumber((BigDecimal) values[i]);
						}
						else if (DisplayType.Integer == m_dataElements.get(i).getCT_DataType_ID())
						{
							data.setValueInteger((Integer) values[i]);
						}
						else if (DisplayType.isDate(m_dataElements.get(i).getCT_DataType_ID()))
						{
							data.setValueDateTime((Timestamp) values[i]);
						}
						else if (DisplayType.isText(m_dataElements.get(i).getCT_DataType_ID()))
						{
							data.setValueString((String) values[i]);
						}
						else if (DisplayType.YesNo == m_dataElements.get(i).getCT_DataType_ID())	//	Yes-No Field
						{
							data.setValueYN((boolean) values[i]);
						}
						else if (DisplayType.Search == m_dataElements.get(i).getCT_DataType_ID()
								|| DisplayType.Table == m_dataElements.get(i).getCT_DataType_ID())
						{
							if (values[i] == null)
								data.setValueInteger(0);
							else
								data.setValueInteger((Integer) values[i]);
						}
						else
						{
							log.severe("Data Element (" + i+ ") Unknown display type " + m_dataElements.get(i).getCT_DataType_ID());
						}
						
						if (data.is_new() || data.is_Changed())
							data.saveEx();
						
					}
				}	//	for all data elements
				
				// Finally - mark the dsi as complete. This is required so
				// model validators can work on the complete set in an aftersave.
				// Without this step, the DSI is saved "empty" and data values are 
				// added to it after the fact.
				m_dataPoint.setIsComplete(true);
				if (!m_dataPoint.save()) {
					String msg = null;
					ValueNamePair err = CLogger.retrieveError();
					if (err != null)
						msg = err.getName();
					if (msg == null || msg.length() == 0)
						msg = "Unknown";
					ADialog.error(m_WindowNo, this, "SaveError", msg);
					return false;
				}

//			}});		
		//
		return true;
	}	//	saveSelection

	
	/**************************************************************************
	 * 	Get Instance ID
	 * 	@return Instance ID
	 */
	public int getCT_DataSetInstance_ID()
	{
		return m_ct_dataSetInstance_id;
	}	//	getCT_DataSetInstance_ID

	/**
	 * 	Value Changed
	 *	@return true if changed
	 */
	public boolean isChanged()
	{
		return m_changed;
	}	//	isChanged
	
}
