package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.apps.RecordInfo;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MLookup;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.swing.CButton;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTDataSetInstance;

public class VDataPoint extends JComponent
	implements VEditor, ActionListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	/**
	 *	Mouse Listener
	 */
	final class VDataPoint_mouseAdapter extends MouseAdapter
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
		 *  @param e event
		 */
		public void mouseClicked(MouseEvent e)
		{
			//	Double Click
			if (e.getClickCount() > 1)
				m_adaptee.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "Mouse"));
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				m_adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouse Clicked

	}	//	VDataPoint_mouseAdapter

	private CMenuItem menuInfo;

	private int m_ct_component_id;

	private int m_root_component_id;
	
	/**
	 *	IDE Constructor
	 */
	public VDataPoint()
	{
		this (null, false, false, true, 0, (MLookup) null, false);
	}

	/**
	 *	Create Data Point Editor for a Data Set Instance.
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup the column lookup model (MLookup)
	 */
	public VDataPoint (boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int WindowNo, MLookup lookup, boolean searchOnly)
	{
		this(null, mandatory, isReadOnly, isUpdateable, WindowNo, lookup, searchOnly);
	}
	
	/**
	 *	Create Data Point Editor for a Data Set Instance.
	 * @param gridTab
	 * @param mandatory Set true if the field is mandatory
	 * @param isReadOnly Set true if the field is read only
	 * @param isUpdateable Set true if the field can be updated
	 * @param WindowNo The parent window number
	 * @param lookup ignored - will be disposed on close.
	 * @param searchOnly ignored
	 */
	public VDataPoint(GridTab gridTab, boolean mandatory, boolean isReadOnly,
			boolean isUpdateable, int WindowNo, MLookup lookup, boolean searchOnly) {

		super();
		super.setName(m_columnName);
		m_value = 0;
		m_GridTab = gridTab; // added for processCallout
		m_WindowNo = WindowNo;
		m_lookup = lookup;
		m_text.setName("VDataPoint Text - " + m_columnName);
		m_buttonDialog.setName("VDataPoint Dialog Button - " + m_columnName);
		m_buttonAnalysis.setName("VDataPoint Analysis Button - " + m_columnName);

		// The creating function should set the field and name. See VEditorFactory.
		// To initialize the field in cases of forms, set the field to null.
		setField(null);    
		
		LookAndFeel.installBorder(this, "TextField.border");
		this.setLayout(new BorderLayout());
		//  Size
		Dimension size = m_text.getPreferredSize();  // Seems to have width set to 6?
		setPreferredSize(new Dimension (60, size.height));  
		setMinimumSize(new Dimension (60, size.height));
		//
		int height = size.height;
		
		//	***	Text	***
		m_text.setEditable(false);
		m_text.setFocusable(true);
		m_text.setBorder(null);
		m_text.setHorizontalAlignment(JTextField.LEADING);
		m_text.addActionListener(this);
		m_text.addFocusListener(this);
	//	Background
		setMandatory(mandatory);
		this.add(m_text, BorderLayout.CENTER);
		
		//  Edit Button
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		this.add(buttonPanel, BorderLayout.EAST);


		//	***	Dialog Button	***
		m_buttonDialog.setIcon(Env.getImageIcon("PDataPoint10.gif"));
		m_buttonDialog.setMargin(new Insets(0, 0, 0, 0));
		m_buttonDialog.setPreferredSize(new Dimension(height, height));
		m_buttonDialog.addActionListener(this);
		m_buttonDialog.setFocusable(true);
		m_buttonDialog.setEnabled(true);  // Always active
		buttonPanel.add(m_buttonDialog);

		//	***	Dialog Button	***
		m_buttonAnalysis.setIcon(Env.getImageIcon("PDataGraph10.gif"));
		m_buttonAnalysis.setMargin(new Insets(0, 0, 0, 0));
		m_buttonAnalysis.setPreferredSize(new Dimension(height, height));
		m_buttonAnalysis.addActionListener(this);
		m_buttonAnalysis.setFocusable(true);
		m_buttonAnalysis.setEnabled(true);  // Always active
		buttonPanel.add(m_buttonAnalysis);

		//	Prefereed Size
		this.setPreferredSize(this.getPreferredSize());		//	causes r/o to be the same length
		//	ReadWrite
		setReadWrite(!isReadOnly && isUpdateable);

		//	Popup
		m_text.addMouseListener(new VDataPoint_mouseAdapter(this));
        menuInfo = new CMenuItem(Msg.getMsg(Env.getCtx(), "Info"), Env.getImageIcon("Zoom16.gif"));
		menuZoom = new CMenuItem(Msg.getMsg(Env.getCtx(), "Zoom"), Env.getImageIcon("Zoom16.gif"));
		menuInfo.addActionListener(this);
		menuZoom.addActionListener(this);
		popupMenu.add(menuZoom);
		popupMenu.add(menuInfo);
		
		// In case value is not set.
		m_text.setText("Empty. Click to record.");  // TODO translate
		m_text.setToolTipText("Click to record a new data point");
		set_oldValue();
	}	//	VDataPoint

	/**	Data Value				*/
	private Object				m_value = new Object();

	/** Column Name - fixed		*/
	private String				m_columnName = "CT_DataSetInstance_ID";

	/** The Text Field          */
	private CTextField			m_text = new CTextField();
	/** The Button              */
	private CButton				m_buttonDialog = new CButton();
	/** The Analysis Button              */
	private CButton				m_buttonAnalysis = new CButton();

	JPopupMenu          		popupMenu = new JPopupMenu();
	private CMenuItem 			menuZoom;

	private boolean				m_readWrite;
	private boolean				m_mandatory;
	private int					m_WindowNo;
	private boolean 			isProductWindow;
	/** The Grid Tab * */
	private GridTab m_GridTab; // added for processCallout
	/** The Grid Field * */
	private GridField m_GridField; // added for processCallout
	
	/**	Calling Window Info				*/
	private int					m_AD_Column_ID = 0;
	/** record the value for comparison at a point in the future */
	private Integer m_oldValue = 0;
	private String m_oldText = "";
	private String m_oldWhere = "";
	private boolean m_haveFocus;
	/** The last display value.  The text displayed can change without the underlying
	 *  value changing so this variable provides a means to test if a change has occurred.
	 */
	private String m_lastDisplay;

	private int m_ct_dataSetInstance_id;
	private MLookup m_lookup;
	private int m_ct_dataSet_id;
	private MCTDataSetInstance m_dataPoint;

	private BigDecimal m_compLifeAtAction;

	private Timestamp m_dateRecorded;

	private BigDecimal m_rootLifeAtAction;

	private int m_old_ct_dataSetInstance_id;

	private int m_overhaulCycle;

	/**	No Instance Key					*/
	private static Integer		NO_INSTANCE = new Integer(0);
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VDataPoint.class);
		

	/**
	 * 	Dispose resources
	 */
	public void dispose()
	{
		m_text = null;
		m_buttonDialog = null;
		m_lookup.dispose();
		m_lookup = null;
//		m_mPAttribute.dispose();
//		m_mPAttribute = null;
		m_GridField = null;
		m_GridTab = null;
	}	//	dispose

	/**
	 * 	Set Mandatory
	 * 	@param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
		m_buttonDialog.setMandatory(mandatory);
		setBackground (false);
	}	//	setMandatory

	/**
	 * 	Get Mandatory
	 *  @return mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}	//	isMandatory

	/**
	 * 	Set ReadWrite
	 * 	@param rw read write
	 */
	public void setReadWrite (boolean rw)
	{
		m_readWrite = rw;
		enableControl();
		//m_buttonDialog.setReadWrite(rw);
		//setBackground (false);
	}	//	setReadWrite

	/**
	 * 	Is Read Write
	 * 	@return read write
	 */
	public boolean isReadWrite()
	{
		return m_readWrite;
	}	//	isReadWrite

	/**
	 * 	Set Foreground
	 * 	@param color color
	 */
	public void setForeground (Color color)
	{
		m_text.setForeground(color);
	}	//	SetForeground

	/**
	 * 	Set Background
	 * 	@param error Error
	 */
	public void setBackground (boolean error)
	{
		if (!m_readWrite)
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (m_mandatory)
			setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		else if (error)
			setBackground(AdempierePLAF.getFieldBackground_Error());
		else
			setBackground(AdempierePLAF.getInfoBackground());
	}	//	setBackground

	/**
	 * 	Set Background
	 * 	@param color Color
	 */
	public void setBackground (Color color)
	{
		m_text.setBackground(color);
	}	//	setBackground

	
	/**************************************************************************
	 * 	Set/lookup Value
	 * 	@param value value
	 */
	public void setValue(Object value)
	{
		log.fine(m_columnName + "=" + value);
		if (value == null || NO_INSTANCE.equals(value))
		{
			m_value = value;
			m_lastDisplay = "";
			m_old_ct_dataSetInstance_id = m_ct_dataSetInstance_id;
			m_ct_dataSetInstance_id = 0;
		}

		//	changed
		else if (!value.equals(m_value)) {
			//	new value
			m_value = value;
			m_old_ct_dataSetInstance_id = m_ct_dataSetInstance_id;
			m_ct_dataSetInstance_id = ((Integer) value).intValue();
		}
		
		// Reset the display whether a change was made or not - in case text was entered and cancelled
		m_text.setText(m_lookup.getDisplay(value));	//	loads value
		// The text can be long.  Use the tooltip to help display the info.
		m_text.setToolTipText(m_text.getText());

		m_lastDisplay = m_text.getText();

		if (m_ct_dataSetInstance_id > 0 && (m_ct_dataSetInstance_id != m_old_ct_dataSetInstance_id || m_dataPoint == null)) {
			m_dataPoint = new MCTDataSetInstance(Env.getCtx(), m_ct_dataSetInstance_id, null);
			m_ct_dataSet_id = m_dataPoint.getCT_DataSet_ID();
		}
		else
		{
			// try to find a the data set to use
			findSupportingData();
		}			

		enableControl();

		return;
	}	//	setValue

	private void enableControl() {

		// Enable or disable controls
		//  Check if the associated Maintenance Requirement has a data set defined.
		
//		if (m_ct_dataSetInstance_id > 0 && (m_ct_dataSetInstance_id != m_old_ct_dataSetInstance_id || m_dataPoint == null)) {
//			m_dataPoint = new MCTDataSetInstance(Env.getCtx(), m_ct_dataSetInstance_id, null);
//			m_ct_dataSet_id = m_dataPoint.getCT_DataSet_ID();
//		}
//		else
//		{
//			// try to find a the data set to use
//			findSupportingData();
//		}			
		
		boolean enabled = true;
		
		if (m_ct_dataSet_id <= 0)
		{
			enabled = false;  
			m_buttonDialog.setEnabled(enabled);
			m_buttonAnalysis.setEnabled(enabled);
//			m_text.setEnabled(false);  // No text input
		}
		else {
			m_buttonDialog.setEnabled(m_readWrite || getValue() != null);  // Button is always enabled if the dataset has data.
			m_buttonAnalysis.setEnabled(getValue() != null);  // Button is always enabled if the dataset has data.
//			m_text.setEnabled(false);  // No text input
		}
//		setBackground(false);		
	}

	/**
	 * Get and set the data set from the context.  
	 */
	private void findSupportingData() {

		m_ct_dataSet_id = getFromFieldOrContextAsInt("CT_DataSet_ID");
		m_ct_component_id = getFromFieldOrContextAsInt("CT_Component_ID");
		m_root_component_id = getFromFieldOrContextAsInt("Root_Component_ID");
		m_compLifeAtAction = getFromFieldOrContextAsBigDecimal("CT_ComponentLifeAtAction");
		
		if (m_ct_component_id > 0)
		{
			MCTComponent comp = new MCTComponent(Env.getCtx(), m_ct_component_id, null);
			
			if (m_compLifeAtAction == null)
				m_compLifeAtAction = comp.getLifeUsed();
			
			if (m_overhaulCycle == 0)
				m_overhaulCycle = comp.getOverhaulCount();
			
			if (m_root_component_id <= 0)
			{
				m_root_component_id = comp.getRoot_Component_ID();
			}
		}
		
		m_rootLifeAtAction = getFromFieldOrContextAsBigDecimal("CT_RootLifeAtAction");
		if (m_rootLifeAtAction == null && m_root_component_id > 0)
		{
			MCTComponent root = new MCTComponent(Env.getCtx(), m_root_component_id, null);
			m_rootLifeAtAction = root.getLifeUsed();
		}

		m_dateRecorded = new Timestamp(System.currentTimeMillis());
	}

	private int getFromFieldOrContextAsInt(String fieldName) {

		int data = 0;
		
		if (m_GridTab != null  && m_GridTab.getField(fieldName) != null) 
		{
			Integer id = (Integer) m_GridTab.getField(fieldName).getValue();
			if (id != null)
			{
				data = id.intValue();
			}
		}
		
		if (data <= 0)
		{
			// Try only the tab first - in case the context also appears on higher level tabs
			data = Env.getContextAsInt (Env.getCtx(), m_WindowNo, m_GridTab.getTabNo(), fieldName, true, true);
		}
		else 
		{
			// Fall back to window context
			data = Env.getContextAsInt (Env.getCtx(), m_WindowNo, fieldName, true);
		}
		
		return data;
	}

	private BigDecimal getFromFieldOrContextAsBigDecimal(String fieldName) {

		BigDecimal data = null;
		
		if (m_GridTab != null  && m_GridTab.getField(fieldName) != null) 
		{
			data = (BigDecimal) m_GridTab.getField(fieldName).getValue();
		}
		
		if (data == null || data.compareTo(Env.ZERO) <= 0)
		{
			// Try only the tab first - in case the context also appears on higher level tabs
			String ctx = Env.getContext(Env.getCtx(), m_WindowNo, m_GridTab.getTabNo(), fieldName, true, true);
			if (ctx != null && !ctx.isEmpty())
			{
				try {
					data = new BigDecimal(ctx);
				}
				catch (NumberFormatException e)
				{
					log.severe("Context for '" + fieldName + "' is not a Bigdecimal: " + ctx);
				}
			}
		}
		else 
		{
			// Fall back to window context
			String ctx = Env.getContext(Env.getCtx(), m_WindowNo, fieldName, true);
			try {
				data = new BigDecimal(ctx);
			}
			catch (NumberFormatException e)
			{
				log.severe("Context for '" + fieldName + "' is not a Bigdecimal: " + ctx);
			}
		}
		
		return data;
	}

	
	
	/**
	 * 	Get Value
	 * 	@return value
	 */
	public Object getValue()
	{
		Integer temp = null;
		if (m_value != null || NO_INSTANCE.equals(m_value)) {
			try {
				temp = (Integer) m_value;
			}
			catch (ClassCastException cce)
			{
				temp = null;
			}
		}
		return temp;
	}	//	getValue

	/**
	 * 	Get Display Value
	 *	@return info
	 */
	public String getDisplay()
	{
		return m_text.getText();
	}	//	getDisplay

	
	/**************************************************************************
	 * 	Set Field
	 * 	@param mField MField
	 */
	public void setField(GridField mField)
	{
		//	To determine behaviour
		m_GridField = mField;
		
		if (m_GridField != null) {
			m_columnName = m_GridField.getColumnName();
			m_AD_Column_ID = m_GridField.getAD_Column_ID();
			m_text.setName("VDataPoint Text - " + m_columnName);
			m_buttonDialog.setName("VDataPoint Dialog Button - " + m_columnName);
			m_buttonAnalysis.setName("VDataPoint Analysis Button - " + m_columnName);
			RecordInfo.addMenu(this, popupMenu);
		}
		else {
			m_columnName = "CT_DataSetInstance_ID";
			m_AD_Column_ID = 0;
		}
		
		enableControl();
	}	//	setField
	
	@Override
	public GridField getField() {
		return m_GridField;
	}

	/**
	 *  Action Listener Interface
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
		m_text.addActionListener(listener);
	}   //  addActionListener

	/**
	 * 	Action Listener - start dialog
	 * 	@param e Event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(RecordInfo.CHANGE_LOG_COMMAND))
		{
			RecordInfo.start(m_GridField);
			return;
		}
		
		if (e.getSource() instanceof CTextField)
			actionText();
		else if (e.getSource() instanceof CButton)
		{
			if (e.getSource() == m_buttonAnalysis)
				actionButtonAnalysis();
			else if (e.getSource() == m_buttonDialog)
				actionButtonDialog();
		}
		else if(e.getSource() == menuInfo)
		{
			actionButtonDialog();
		}
		//  Popup Menu
		else if (e.getSource() == menuZoom)
			actionZoom();
		
		requestFocus();
	}	//	actionPerformed

	private void actionButtonAnalysis() {
		
		
		if (m_ct_dataSet_id <= 0)
			return;
		
		SwingUtilities.invokeLater(new Runnable() {
		     public void run() {
		 		final int AD_Window_ID=1000032;  // Hardcoded Data Analysis
		 		
				final MQuery query = new MQuery(MTable.getTable_ID("CT_DataSetAnalysis_v"));
				
				String whereClause = "CT_DataSet_ID=" + m_ct_dataSet_id;
						
				query.addRestriction(whereClause);
				
				AWindow frame = new AWindow(null);
				if (!frame.initWindow(AD_Window_ID, query))
					return;
				AEnv.addToWindowManager(frame);
				if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) 
				{
					AEnv.showMaximized(frame);
				}
				else
				{
					AEnv.showCenterScreen(frame);
				}
				
				frame = null;
		     }
		 });

	}

	/**
	 *  Property Change Listener
	 *  @param evt event
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
		if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
			setValue(evt.getNewValue());
	}   //  propertyChange
	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 */
	public void set_oldValue() {
		if (getValue() != null) {
			try {
				this.m_oldValue = ((Integer) getValue());
			} 
			catch (ClassCastException e)
			{
				this.m_oldValue = null;
			}
		}
		else
			this.m_oldValue = null;
		if (m_text != null)
			this.m_oldText = m_text.getDisplay();
		else
			m_oldText = "";
	}
	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return m_oldValue;
	}
	/**
	 * Has the field changed over time?
	 * @return true if the old value is different than the current.
	 */
	public boolean hasChanged() {
		// Both or either could be null
		
		// Don't think a test of Value is needed as value is not set by the search window
		//if(getValue() != null)
		//	if(m_oldValue != null)
		//		return !m_oldValue.equals(getValue());
		//	else
		//		return true;
		//else  // getValue() is null
		//	if(m_oldValue != null)
		//		return true;

		if(m_text != null)
			if(m_oldText != null)
				return !m_oldText.equals(m_text.getDisplay());
			else
				return true;
		else  // m_text is null
			if(m_oldText != null)
				return true;

		return false;
	
	}

	/**************************************************************************
	 *	Focus Listener for ComboBoxes with missing Validation or invalid entries
	 *	- Requery listener for updated list
	 *  @param e FocusEvent
	 */
	public void focusGained (FocusEvent e)
	{
		if ((e.getSource() != m_text)
			|| e.isTemporary() || m_haveFocus)
			return;

		//
		log.finest("Have Focus!");
		m_haveFocus = true;     //  prevents calling focus gained twice
		m_text.selectAll();

	}	//	focusGained

	/**
	 *	Reset Selection List
	 *  @param e FocusEvent
	 */
	public void focusLost(FocusEvent e)
	{
		// Change of value performed by the dialog not loss of focus		
		log.finest("Losing Focus!");
		m_haveFocus = false;    //  can gain focus again
	}	//	focusLost

	/**
	 *	Check, if data returns unique entry, otherwise involve Info via Button
	 */
	private void actionText()
	{
		// On any action, open the dialog
		actionButtonDialog();		
		m_text.requestFocus();
	}	//	actionText

	/**
	 *	Perform the actions of clicking the button in the control
	 */
	private void actionButtonDialog()
	{
		if (!m_buttonDialog.isEnabled ())
			return;
		
		m_buttonDialog.setEnabled (false);
		//
		Integer oldValue = 0;
		boolean changed = false;
				
		try
		{
			oldValue = (Integer)getValue ();			
		}
		catch(ClassCastException cce)
		{
			// Possible Invalid Cast exception if getValue() return new instance of Object.
			oldValue = 0;
		}
		
		int oldValueInt = oldValue == null ? 0 : oldValue.intValue ();
		m_ct_dataSetInstance_id = oldValueInt;
		
		VDataPointDialog vdpd = new VDataPointDialog (Env.getFrame (this), 
			m_ct_dataSetInstance_id, m_ct_dataSet_id, m_ct_component_id, 
			m_root_component_id, m_compLifeAtAction, m_rootLifeAtAction, m_overhaulCycle,
			m_dateRecorded, m_AD_Column_ID, m_WindowNo, isReadWrite());
		
		if (vdpd.isChanged() || vdpd.getCT_DataSetInstance_ID() != oldValueInt)
		{
			m_old_ct_dataSetInstance_id = m_ct_dataSetInstance_id;
			m_ct_dataSetInstance_id = vdpd.getCT_DataSetInstance_ID();
			changed = true;
		}
		
		//	Set Value
		if (changed)
		{
			log.finest("Changed CT_DataSetInstance_ID=" + m_ct_dataSetInstance_id);
			setAndBindValue(m_ct_dataSetInstance_id);
		}	//	change
		
		setValue(getValue()); // Reset the display in case text was entered.
	
		if (m_ct_dataSetInstance_id == oldValueInt && m_GridTab != null && m_GridField != null)
		{
			//  force Change - user does not realize that embedded object is already saved.
			//  This will fire the callouts on the field if any.
			m_GridTab.processFieldChange(m_GridField); 
		}
		m_buttonDialog.setEnabled(true);
	}

	private void setAndBindValue(int ct_dataSetInstance_id) {

		Integer oldValue = 0;
		try
		{
			oldValue = (Integer)getValue ();			
		}
		catch(ClassCastException cce)
		{
			// Possible Invalid Cast exception if getValue() returns new instance of Object.
			oldValue = 0;
		}

		Object newValue;		
		if (ct_dataSetInstance_id == 0)
			newValue = null;
		else
			newValue = new Integer(ct_dataSetInstance_id);
		
		//
		try
		{
			// Listener sets the value or throws an error
	 	 	fireVetoableChange(m_columnName, oldValue, newValue);
		}
		catch (PropertyVetoException pve)
		{
			log.log(Level.SEVERE, "", pve);
		}
	}
	
	/**
	 *	Action - Zoom
	 *	@param selectedItem item
	 */
	private void actionZoom ()
	{
		//
		MQuery zoomQuery = new MQuery();
		Object value = getValue();
		if (value == null)
			value = Integer.valueOf(0);
		String keyTableName = MCTDataSetInstance.Table_Name;
		String keyColumnName = MCTDataSetInstance.COLUMNNAME_CT_DataSetInstance_ID;

		zoomQuery.addRestriction(keyColumnName, MQuery.EQUAL, value);
		zoomQuery.setZoomColumnName(keyColumnName);
		zoomQuery.setZoomTableName(keyTableName);
		zoomQuery.setZoomValue(value);
		zoomQuery.setRecordCount(1);	//	guess

		int	AD_Window_ID = m_lookup.getZoom(zoomQuery);
		//
		log.info(m_columnName + " - AD_Window_ID=" + AD_Window_ID
			+ " - Query=" + zoomQuery + " - Value=" + value);
		//
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//
		AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, zoomQuery))
		{
			setCursor(Cursor.getDefaultCursor());
			ValueNamePair pp = CLogger.retrieveError();
			String msg = pp==null ? "AccessTableNoView" : pp.getValue();
			ADialog.error(m_lookup.getWindowNo(), this, msg, pp==null ? "" : pp.getName());
		}
		else
		{
			AEnv.addToWindowManager(frame);
			if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED))
			{
				AEnv.showMaximized(frame);
			}
			else
			{
				AEnv.showCenterScreen(frame);
			}
		}
			//  async window - not able to get feedback
		frame = null;
		//
		setCursor(Cursor.getDefaultCursor());
	}	//	actionZoom

	/**
	 * 	Request Focus
	 */
	public void requestFocus ()
	{
		m_text.requestFocus ();
	}	//	requestFocus
}
