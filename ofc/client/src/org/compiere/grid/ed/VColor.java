/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.Segment;

import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.apps.RecordInfo;
import org.compiere.apps.search.Info;
import org.compiere.apps.search.InfoBPartner;
import org.compiere.apps.search.InfoFactory;
import org.compiere.apps.search.InfoProduct;
import org.compiere.grid.ed.VDataPoint.VDataPoint_mouseAdapter;
import org.compiere.grid.ed.VLookup.VLookup_mouseAdapter;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Lookup;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MColor;
import org.compiere.model.MColorLookup;
import org.compiere.model.MColumn;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLookup;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProductPrice;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.plaf.CompiereColor;
import org.compiere.plaf.CompiereLookAndFeel;
import org.compiere.swing.CButton;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.swing.ColorEditor;
import org.compiere.util.CLogger;
import org.compiere.util.ColorPair;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyColorPair;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.NamePair;
import org.compiere.util.Trx;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.I_PP_Product_BOMLine;

import sun.swing.DefaultLookup;

import com.jgoodies.looks.common.ComboBoxEditorTextField;
import com.mckayerp.model.MCTDataSetInstance;

/**
 *  Displays a color and allows the color to be edited through a dialog.
 *  The color value is stored in AD_Color_ID and is referenced by the ID 
 *  value.  The MColor model provides a link with the ID and the 
 *  AdempiereColor definition.
 *  .
 *
 *  @author     Jorg Janke
 *  @version    $Id: VColor.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  
 *  @author		Michael McKay - change to stored data for color info in AD_Color.
 */
public class VColor extends JComponent
	implements VEditor, ActionListener, FocusListener, ItemListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3881508174949938138L;

	/**
	 *	Mouse Listener
	 */
	final class VColor_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VColor_mouseAdapter(VColor adaptee)
		{
			m_adaptee = adaptee;
		}	//	VDataPoint_mouseAdapter

		private VColor m_adaptee;

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

	class MyIcon implements Icon {

		int height = 20;
		int width = 20;
		Color bg;
		
		public MyIcon() {
		}
		
		public void setColor(Color c) {
			bg = c;
		}
		
		public int getIconHeight() {
			return height;
		}
		
		public void setIconHeight(int h) {
			height = h;
		}
		
		public int getIconWidth() {
			return width;
		}

		public void setIconWidth(int w) {
			width = w;
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			if (bg != null)
				g.setColor(bg);
			g.fillRect(0, 0, width, height);
		}

	}

	class ColorCellRenderer implements ListCellRenderer {
		  protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

		  public Component getListCellRendererComponent(JList list, Object value, int index,
				  boolean isSelected, boolean cellHasFocus) {

			JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
		      isSelected, cellHasFocus);
			
			if (value != null && value instanceof KeyNamePair)
			{
				int key = ((KeyNamePair) value).getKey();
				if (key > 0)
				{
					MColor mc = MColor.get(Env.getCtx(), key);
					Color c = mc.getAdempiereColor().getFlatColor();
					MyIcon myIcon = new MyIcon();
					myIcon.setColor(c);
					renderer.setIcon(myIcon);
				}
			}
			else if (value != null && value instanceof KeyColorPair)
			{
				int key = ((KeyColorPair) value).getKey();
				String name = ((KeyColorPair) value).getName();
				Color c = ((KeyColorPair) value).getColor();
				MyIcon myIcon = new MyIcon();
				myIcon.setColor(c);
				renderer.setIcon(myIcon);
				renderer.setText(name);
			}
			return renderer;
		}
	}
	
	private int m_WindowNo;

	private MColorLookup m_lookup;  // Use the setter/getter

	private static CompiereColor m_defaultColor = null;

	private boolean m_readWrite;
	
	private boolean m_reload = false;

	JPopupMenu          		popupMenu = new JPopupMenu();

	private CMenuItem menuZoom;

	private CMenuItem menuInfo;

	private CMenuItem mRefresh;


	private VColor_mouseAdapter m_mouseAdapter;

	private boolean m_isTableCellRenderer = false;

	private boolean m_isSelected = false;

	private boolean m_isTableCellEditor;

	private boolean m_enableEdits;

	private boolean m_isUpdateable;
	
	/** Field Height 				 */
	public static int     		FIELD_HEIGHT = 0;


	/**
	 *	Create Color Editor for an AD_Color_ID.
	 * @param gridTab
	 * @param mandatory Set true if the field is mandatory
	 * @param isReadOnly Set true if the field is read only
	 * @param isUpdateable Set true if the field can be updated
	 * @param WindowNo The parent window number
	 * @param lookup ignored - will be disposed on close.
	 * @param searchOnly ignored
	 * @param isSelected 
	 */
	public VColor(GridTab gridTab, boolean mandatory, boolean isReadOnly,
			boolean isUpdateable, int WindowNo, Lookup lookup, boolean searchOnly) 
	{
		this(gridTab, mandatory, isReadOnly, isUpdateable, WindowNo, lookup, searchOnly, false, false, false);
	}
	
	/**
	 *	Create Color Editor for an AD_Color_ID.
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup the column lookup model (MLookup)
	 */
	public VColor (boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int WindowNo, MLookup lookup, boolean searchOnly)
	{
		this(null, mandatory, isReadOnly, isUpdateable, WindowNo, lookup, searchOnly);
	}
	
	/**
	 *	Create Color Editor for an AD_Color_ID.
	 * @param gridTab
	 * @param mandatory Set true if the field is mandatory
	 * @param isReadOnly Set true if the field is read only
	 * @param isUpdateable Set true if the field can be updated
	 * @param WindowNo The parent window number
	 * @param lookup ignored - will be disposed on close.
	 * @param searchOnly ignored
	 * @param isSelected 
	 */
	public VColor(GridTab gridTab, boolean mandatory, boolean isReadOnly,
			boolean isUpdateable, int WindowNo, Lookup lookup, boolean searchOnly, 
			boolean isTableCellRenderer, boolean isTableCellEditor, boolean isSelected)
	{

		super();
		super.setName(m_columnName);
		m_isTableCellRenderer = isTableCellRenderer;
		m_isSelected = isSelected;
		m_value = 0;
		m_mTab = gridTab; // added for processCallout
		m_mandatory = mandatory;
		m_WindowNo = WindowNo;
		setLookup(lookup);
		m_readWrite = !isReadOnly;
		m_isUpdateable = isUpdateable;

		//  Setup the user interface display 
		setUI ();
		
	}   //  VColor

	// Constructor for VCellRenderer
	public VColor(GridField gridField, boolean isTableCellRenderer, boolean isSelected, boolean hasFocus) {
		
		super();

		// Initialize m_cc
		m_cc = getDefaultColor();

		if (gridField != null)
		{
			m_isTableCellRenderer = isTableCellRenderer;
			m_isSelected = isSelected;
			m_haveFocus = hasFocus;
			setField(gridField);  
		}
		
		m_cc = loadColor();
		setUI();

	}

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		m_mTab = null;
	}   //  dispose

	/** Column Name - fixed		*/
	private String				m_columnName = "AD_Color_ID";

	private GridTab            m_mTab;
	private boolean         m_mandatory;
    private int             m_AD_Color_ID = 0;
	private CompiereColor   m_cc = null;

	/** The Text Field          */
	private CTextField			m_text = new CTextField();
	/** The color Button        */
	private CButton				m_button = new CButton();
	/** The Combo Box           */
	private VComboBox			m_combo = new VComboBox();

	//	Field for Value Preference
	private GridField       m_mField = null;
	private Class<?> 		m_valueClass;
	private MColor			m_mColor;
	
	/**	No Instance Key					*/
	private static Integer		NO_INSTANCE = new Integer(0);
	
	/** Not Found Key	 */
	private static Integer		NOT_FOUND = new Integer(-1);

	/** The database color which is the string representation
	 *  of the Compiere Color
	 */
	private Object	m_value;
	private Integer m_IDNotFound;
	private boolean m_valueStringError;

	private Object m_AD_Column_ID;

	private String m_lastDisplay;

	private int m_old_AD_Color_ID;

	private String m_tableName;

	private String m_keyColumnName;

	private boolean m_comboActive;

	private boolean m_settingValue;

	private boolean m_haveFocus;

	private boolean m_settingFocus;

	private boolean m_isAD_Color_Table = false;

	private boolean m_inserting;
	
	private Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	private boolean m_initialSetupCompleted = false;

	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VColor.class);

	/**
	 *  Set Mandatory
	 *  @param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
	}   //  setMandatory

	/**
	 *  Is Mandatory
	 *  @return true if Mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}   //  isMandatory

	/**
	 *  Set Background (nop)
	 *  @param error error
	 */
	public void setBackground (boolean error)
	{
	}   //  setBackground

	/**
	 *  Set Value
	 *  @param value value
	 */
	public void setValue (Object value)
	{
		
		if (!m_settingValue)
			m_settingValue = true;
		
		log.config(m_columnName + "=" + value);
		
		if (value != null && !(value instanceof Integer))
		{
			throw new IllegalArgumentException("Value is not an instance of Integer");
		}
		
		m_reload = false;
		
		m_value = value;
		m_old_AD_Color_ID = m_AD_Color_ID;
		
		//  The lookup will return null if called with the key value = null or -1.  A 
		//  value of 0 just means no instance of the model so don't bother looking 
		//  for it.  
		if (m_value == null || NO_INSTANCE.equals(m_value) || NOT_FOUND.equals(m_value))
		{
			
			m_lastDisplay = "";
			m_AD_Color_ID = 0;
			m_mColor = null;
			m_cc = m_defaultColor;
			m_combo.setValue (null);
			m_text.setText (null);
			
		}
		else //  Not null
		{
			//  The color will need to be found.  Set the flag to reload it
			//  once all the data is set.
			m_reload  = true;
			
			//  Record the AS_Color_ID
			m_AD_Color_ID = ((Integer) m_value).intValue();
			
			//  If there is no lookup, just use the value.
			if (m_lookup == null)  
			{
				
				//  Set the editor values directly.
				if (m_comboActive)
					m_combo.setValue (m_value);
				m_text.setText (value.toString());
				m_lastDisplay = value.toString();
				
			}
			else  // use the lookup to find the display values
			{	
				
				//  Should call m_combo.setvalue after m_lookup.getDisplay() as
				//  loading of combo data might happen in m_lookup.getDisplay()
				m_lastDisplay = m_lookup.getDisplay(m_value);  // Updated every 5 sec.
				if (m_comboActive)
					m_combo.setValue(m_value);  // First try - if the combo isn't loaded, this may not work.
				
				// Set a flag to indicate the value was not found.  
				boolean notFound = m_lastDisplay.startsWith("<") && m_lastDisplay.endsWith(">");
		
				//	Nothing showing in Combo and should be showing
				if (m_combo.getSelectedItem() == null
					&& (m_comboActive || (m_inserting)))
				{

					if (notFound)
					{
	
						//  We may have a new value that hasn't been loaded in the regular update
						//  Force the update now.
						log.finest(m_columnName + "=" + m_value + ": Not found - " + m_lastDisplay);
						m_lookup.refresh();
						m_lastDisplay = getLookup().getDisplay(m_value);
						m_combo.setValue (m_value);
						notFound = m_lastDisplay.startsWith("<") && m_lastDisplay.endsWith(">");
						
					}
				
					if (notFound)	//	Still not found - a possible problem.
					{
						
						// Reject the value
						log.fine(m_columnName + "=" + m_value + ": Not found");
						m_AD_Color_ID = 0;
						m_value = null;
						if (m_comboActive)
							actionCombo (null);     //  data binding - will fire events and call setvalue again.
						// Now change lastDisplay to something that make sense for not found.
						if (m_lastDisplay.equals("<-1>"))
							m_lastDisplay = "";
						
					}
				
					if (m_combo.getSelectedItem() == null && m_comboActive)
					{
						// If the lookup is still null, just add the
						// single name pair.  No need to load the whole set.
						ColorPair pp = ((MColorLookup) getLookup()).getColorPair(m_value);
						if (pp != null)
						{
							log.fine(m_columnName + " added to combo - " + pp);
							//  Add to Combo
							m_combo.addItem (pp);
							m_combo.setValue (m_value);
						}
					}

				}
				
				m_text.setText (m_lastDisplay);				
				// The text can be long.  Use the tooltip to help display the info.
				m_text.setToolTipText(m_text.getText());
				m_text.setCaretPosition (0); //	show beginning

			}
		}

		
		Object display = m_combo.getEditor().getItem();
		log.fine("Combo editor item:" + display + " (" + m_lastDisplay + ")");


		// Always reload the color	
		m_cc = loadColor();
		
		if (m_cc != null)
		{
	
			// Set the background color and then find a contrasting
			// foreground based on the background "flat" color
			m_button.setOpaque(true);
			m_button.setBackgroundColor(m_cc);			
	        m_button.setBorderPainted(false);

		}
		else
		{
			// We don't know what to display - make the control transparent.
			m_button.setOpaque(false);
	        m_button.setBorderPainted(true);
	        
		}
		
		enableControl();
		repaint();

		m_reload = false;	
		m_settingValue = false;
	}   //  setValue

	private CompiereColor loadColor() {

		CompiereColor cc = null;
		
		if (m_isAD_Color_Table && !m_isTableCellRenderer)
		{
			//  Try to load the data from the tab directly.  If its a table view, this may not work
			//  in which case, go straight to the database.
			cc = getFromTab();
		}
		
		if (cc == null && m_AD_Color_ID > 0)  
		{
			m_mColor = new MColor(Env.getCtx(), m_AD_Color_ID, null);
			cc = m_mColor.getAdempiereColor();
		}

		if (cc == null)
		{
			m_mColor = null;
			cc = getDefaultColor();
		}			

		return cc;
	}

	/**
	 *  GetValue
	 *  @return value
	 */
	public Object getValue()
	{
		if (m_comboActive)
			return m_combo.getValue ();
		return m_value;
	}   //  getValue

	/**
	 *  Get Displayed Value
	 *  @return String representation
	 */
	public String getDisplay()
	{
		if (m_IDNotFound != null)
			return "<" + m_IDNotFound.toString() + ">";
		
		if (m_valueStringError)
			return Msg.translate(Env.getCtx(), "Unknown color");
		
		if (m_cc == null)
			return "-/-";
		
		return " "; // Just display the color
		
	}   //  getDisplay

	/**
	 *  Property Change Listener
	 *  @param evt event
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
	//	log.config( "VColor.propertyChange", evt);
		if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
		{
			m_inserting = GridField.INSERTING.equals(evt.getOldValue());	//	MField.setValue
			setValue(evt.getNewValue());
			m_inserting = false;
			setBackground(false);
		}
	}   //  propertyChange
	
	/**
	 *  Set Field.  This happens after the Editor/Renderer is created
	 *  so the method also updates the GUI.
	 *  @param mField field
	 */
	public void setField (GridField mField)
	{
		m_mField = mField;
		
		if (m_mField != null) {
			
			m_value = m_mField.getValue();
			m_AD_Color_ID = m_value == null ? 0 : ((Integer) m_value).intValue();
			m_mTab = m_mField.getGridTab();
			m_mandatory = m_mField.isMandatory(false);
			m_WindowNo = m_mTab.getWindowNo();
			setLookup(m_mField.getLookup());
			m_readWrite = !m_mField.isReadOnly();
			m_isUpdateable = m_mField.isUpdateable();

			setColumnName(m_mField.getColumnName());
			m_AD_Column_ID = m_mField.getAD_Column_ID();

			if (m_mTab != null 
					&& m_mTab.get_TableName().equals(MColor.Table_Name)
					&& (m_columnName.equals(MColor.COLUMNNAME_AD_Color_ID) || m_mField.isKey()))
			{
				m_isAD_Color_Table  = true;
			}
			else
			{
				m_isAD_Color_Table = false;
			}
		}
		else {
			
			setColumnName(m_columnName);
			m_AD_Column_ID = 0;
			m_value = null;
			m_AD_Color_ID = 0;
			m_mTab = null;
			m_mandatory = false;
			m_WindowNo = 0;
			setLookup(null);
			m_readWrite = false;
			m_isUpdateable = false;

		}
		
		setUI();

	}   //  setField

	@Override
	public GridField getField() {
		return m_mField;
	}
	
	/*************************************************************************/

//	/**
//	 *  Load Color from Tab
//	 *  @return true if loaded
//	 *  @see org.compiere.model.MColor#getAdempiereColor
//	 */
//	private CompiereColor getAdempiereColor()
//	{
//		// Hold your nose.  This should be context searches, not tabs.
//		// The AD_Color_ID is not used anywhere
//		Integer AD_Color_ID = (Integer)m_mTab.getValue("AD_Color_ID");
//		log.fine("AD_Color_ID=" + AD_Color_ID);
//		
//		//
//		CompiereColor cc = null;
//
//		//  Color Type
//		String ColorType = (String)m_mTab.getValue("ColorType");
//		if (ColorType == null)
//		{
//			log.fine("No ColorType");
//			// Default to flat
//			ColorType = CompiereColor.TYPE_FLAT;
//		}
//		//
//		if (ColorType.equals(CompiereColor.TYPE_FLAT))
//		{
//			cc = new CompiereColor(getColor(true), true);
//		}
//		else if (ColorType.equals(CompiereColor.TYPE_GRADIENT))
//		{
//			Integer RepeatDistance = (Integer)m_mTab.getValue(MColor.COLUMNNAME_RepeatDistance);
//			String StartPoint = (String)m_mTab.getValue(MColor.COLUMNNAME_StartPoint);
//			int repeatDistance = RepeatDistance == null ? 0 : RepeatDistance.intValue();
//			int startPoint = StartPoint == null ? 0 : Integer.parseInt(StartPoint);
//			cc = new CompiereColor(getColor(true), getColor(false), startPoint, repeatDistance);
//		}
//		else if (ColorType.equals(CompiereColor.TYPE_LINES))
//		{
//			BigDecimal LineWidth = getNumericTabField(MColor.COLUMNNAME_LineWidth);
//			BigDecimal LineDistance = getNumericTabField(MColor.COLUMNNAME_LineDistance);
//			int lineWidth = LineWidth == null ? 0 : LineWidth.intValue();
//			int lineDistance = LineDistance == null ? 0 : LineDistance.intValue();
//			cc = new CompiereColor(getColor(false), getColor(true), lineWidth, lineDistance);
//		}
//		else if (ColorType.equals(CompiereColor.TYPE_TEXTURE))
//		{
//			Integer AD_Image_ID = (Integer)m_mTab.getValue(MColor.COLUMNNAME_AD_Image_ID);
//			String url = getURL(AD_Image_ID);
//			if (url == null)
//				return null;
//			BigDecimal ImageAlpha = getNumericTabField(MColor.COLUMNNAME_ImageAlpha);
//			float compositeAlpha = ImageAlpha == null ? 0.7f : ImageAlpha.floatValue();
//			cc = new CompiereColor(url, getColor(true), compositeAlpha);
//		}
//		else
//			return null;
//
//		log.fine("AdempiereColor=" + cc);
//		return cc;
//	}   //  getAdempiereColor

	/**
	 *  Get Color from Tab
	 *  @param primary true if primary false if secondary
	 *  @return Color
	 */
	private Color getTabColorFields (boolean primary)
	{
		//	is either BD or Int
		BigDecimal Red = null;
		BigDecimal Green = null;
		BigDecimal Blue = null;
		BigDecimal Alpha = null;
		
		if (primary)
		{
			Red = getNumericTabField(MColor.COLUMNNAME_Red);
			Green = getNumericTabField(MColor.COLUMNNAME_Green);
			Blue = getNumericTabField(MColor.COLUMNNAME_Blue);
			Alpha = getNumericTabField(MColor.COLUMNNAME_Alpha);
		}
		else
		{
			Red = getNumericTabField(MColor.COLUMNNAME_Red_1);
			Green = getNumericTabField(MColor.COLUMNNAME_Green_1);
			Blue = getNumericTabField(MColor.COLUMNNAME_Blue_1);
			Alpha = getNumericTabField(MColor.COLUMNNAME_Alpha_1);
		}
		//
		int red = Red == null ? 0 : Red.intValue();
		int green = Green == null ? 0 : Green.intValue();
		int blue = Blue == null ? 0 : Blue.intValue();
		int alpha = Alpha == null ? 0 : Alpha.intValue();
		//
		return new Color (red, green, blue, alpha);
	}   //  getColor

	private BigDecimal getNumericTabField(String fieldName)
	{
		if (m_mTab == null)
			return null;
		
		Object oo = m_mTab.getValue(fieldName);
		if (oo == null)
			return null;
		if (oo instanceof Integer)
			return new BigDecimal(((Integer) oo).intValue());
		if (oo instanceof BigDecimal)
			return (BigDecimal) oo;
			
		return null;
	}
	/**
	 *  Get URL from Image
	 *  @param AD_Image_ID image
	 *  @return URL as String or null
	 */
	private String getURL (Integer AD_Image_ID)
	{
		if (AD_Image_ID == null || AD_Image_ID.intValue() == 0)
			return null;
		//
		String retValue = null;
		String sql = "SELECT ImageURL FROM AD_Image WHERE AD_Image_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt (1, AD_Image_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				retValue = rs.getString(1);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		return retValue;
	}   //  getURL

	/*************************************************************************/

	/**
	 *  Action Listener - Open Dialog
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		
		if (m_settingValue || m_settingFocus)
			return;
		
		if (e.getSource() instanceof CTextField)
		{
			actionText();
		}
		else if (e.getSource() instanceof CButton)
		{
			if (e.getSource() == m_button)
			{
				actionButton();
			}
		}
		//  Combo Selection
		else if (e.getSource() == m_combo)
		{
			Object p = m_combo.getSelectedItem();
			log.fine("Combo display:" + p + " (" + e.toString() + ")");
			
			Object value = null;
			String name = null;
			if (p instanceof NamePair)
			{
				name = ((NamePair) p).getName();
				if (p instanceof KeyNamePair)
				{
					if (((KeyNamePair) p).getID() != null)
						value =  new Integer(((KeyNamePair)p).getID());
				}
				else if (p instanceof ValueNamePair)
				{
					if (((ValueNamePair) p).getID() != null)
						value = new String(((ValueNamePair)p).getID());
				}
				else if (((NamePair) p).getID() != null)
					value = new Integer(((NamePair)p).getID());
			}

			if (name != null)
			{
				//  don't allow selection of inactive
				if (name.startsWith(MLookup.INACTIVE_S) && name.endsWith(MLookup.INACTIVE_E))
				{
					log.info(m_columnName + " - selection inactive set to NULL");
					value = null;
				}
			}
			
			actionCombo (value);                //  data binding

		}
		
		//  Combo Selection
		else if (e.getSource() == m_combo.getEditor())
		{
			Object display = m_combo.getEditor().getItem();
			log.fine("Combo editor item:" + display + " (" + e.toString() + ")");
			// TODO - still need this??
		}

		//  Popup Menu
		else if(e.getSource() == menuInfo)
		{
			actionButton();
		}
		else if (e.getSource() == menuZoom)
		{
			actionZoom(m_combo.getSelectedItem());
		}
		else if (e.getSource() == mRefresh)
		{
			actionRefresh();
		}

	}   //  actionPerformed

	/**
	 *  Set Color in Tab
	 *  @param c Color
	 *  @param primary true if primary false if secondary
	 */
	private void setTabColorFields (Color c, boolean primary)
	{
		if (primary)
		{
			m_mTab.setValue(MColor.COLUMNNAME_Red,    new BigDecimal(c.getRed()));
			m_mTab.setValue(MColor.COLUMNNAME_Green,  new BigDecimal(c.getGreen()));
			m_mTab.setValue(MColor.COLUMNNAME_Blue,   new BigDecimal(c.getBlue()));
			m_mTab.setValue(MColor.COLUMNNAME_Alpha,   new BigDecimal(c.getAlpha()));
		}
		else
		{
			m_mTab.setValue(MColor.COLUMNNAME_Red_1,    new BigDecimal(c.getRed()));
			m_mTab.setValue(MColor.COLUMNNAME_Green_1,  new BigDecimal(c.getGreen()));
			m_mTab.setValue(MColor.COLUMNNAME_Blue_1,   new BigDecimal(c.getBlue()));
			m_mTab.setValue(MColor.COLUMNNAME_Alpha_1,   new BigDecimal(c.getAlpha()));
		}
	}   //  setColor

	@Override
	public void setReadWrite(boolean rw) {

		m_readWrite = rw;

		setUI();
		
	}

	@Override
	public boolean isReadWrite() {
		return m_readWrite;
	}

	@Override
	public void addActionListener(ActionListener listener) {
		m_combo.addActionListener(listener);
		m_text.addActionListener(listener);
	}
	
	private void enableControl() {
		//  Button is always enabled if the field has data
		//  This allows users to see the color info, even if the field is read only
		m_button.setEnabled(m_readWrite || getValue() != null);  
	}

	@Override
	public void focusGained(FocusEvent e) {
		
		// Only care about the comboBox
		if (m_combo == null || m_combo.getEditor() == null || !m_comboActive)
			return;

		if ((e.getSource() != m_combo && e.getSource() != m_combo.getEditor().getEditorComponent())
			|| e.isTemporary() || m_haveFocus)
			return;

		m_haveFocus = true;     //  prevents calling focus gained twice
		m_settingFocus = true;  //  prevents actionPerformed
		//
		Object obj = m_lookup.getSelectedItem();
		log.config(m_columnName
			+ " - Start    Count=" + m_combo.getItemCount() + ", Selected=" + obj);
//		log.fine( "VColorHash=" + this.hashCode());
		boolean popupVisible = m_combo.isPopupVisible();
		
		// Fill the comboBox with data
		if (!m_lookup.isLoaded())
		{
			m_lookup.fillComboBox(isMandatory(), true, true, false);     //  only validated & active
			log.fine(m_columnName
			+ " - Lookup Updated   Count=" + m_combo.getItemCount() + ", Selected=" + m_lookup.getSelectedItem());
		}

		if (popupVisible)
		{
			//refresh
			m_combo.hidePopup();
			m_combo.showPopup();
		}
		
		m_settingFocus = false;
	}	//	focusGained

	/**
	 *	Reset Selection List
	 *  @param e FocusEvent
	 */
	public void focusLost(FocusEvent e)
	{
		if (e.isTemporary()
			|| m_lookup == null )
			return;
		
		//	Text Lost focus
		if (e.getSource() == m_combo.getEditor().getEditorComponent())
		{
			ComboBoxEditorTextField editor = (ComboBoxEditorTextField) m_combo.getEditor().getEditorComponent();
			
			try {
				PlainDocument doc = (PlainDocument) editor.getDocument();
				Segment segment = new Segment();
				int length = doc.getLength();
				int zero = 0;
				doc.getText(0, length, segment);
				String text = String.valueOf(segment.array);
				String[] textArray = text.split("\\n");
				log.fine(textArray[0].trim());
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == m_text)
		{
			String text = m_text.getText();
			log.config(m_columnName + " (Text) " + m_columnName + " = " + m_value + " - " + text);
			m_haveFocus = false;
			//	Skip if empty
			if ((m_value == null
				&& m_text.getText().length() == 0))
				return;
			if (m_lastDisplay.equals(text))
				return;
			//
			actionText();	//	re-display
			return;
		}
		//	Combo lost focus
		if (e.getSource() != m_combo && e.getSource() != m_combo.getEditor().getEditorComponent())
			return;

//		if (m_lookup.isValidated() && !m_lookup.hasInactive())
//		{
//			m_haveFocus = false;
//			return;
//		}
//		//
//		m_settingFocus = true;  //  prevents actionPerformed
//		//
//		log.config(m_columnName + " = " + m_combo.getSelectedItem());
//		Object obj = m_combo.getSelectedItem();
//		/*
//		//	set original model
//		if (!m_lookup.isValidated())
//			m_lookup.fillComboBox(true);    //  previous selection
//		*/
//		//	Set value
//		if (obj != null)
//		{
//			m_combo.setSelectedItem(obj);
//			//	original model may not have item
//			if (!m_combo.getSelectedItem().equals(obj))
//			{
//				log.fine(m_columnName + " - added to combo - " + obj);
//				m_combo.addItem(obj);
//				m_combo.setSelectedItem(obj);
//			}
//		}
	//	actionCombo(getValue());
		m_settingFocus = false;
		m_haveFocus = false;    //  can gain focus again
	}	//	focusLost

	/**
	 *	Check, if text name data returns unique color, otherwise invoke the dialog
	 *  to create a new one.
	 */
	private void actionText()
	{
		String text = m_text.getText();
		// Nothing entered, just pressing enter again => ignore
		if (text != null && text.length() > 0 && text.equals(m_lastDisplay))
		{
			log.finest("Nothing entered [SKIP]");
			return;
		}
		
		//	Nothing entered
		if (text == null || text.length() == 0 || text.equals("%"))
		{
			actionButton();
			return;
		}
		
		text = text.toUpperCase();
		log.config(m_columnName + " - " + text);

		//	Exact first
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rSQL = getDirectAccessSQL(text);  // Will be non-null sql
		String finalSQL = Msg.parseTranslation(Env.getCtx(), rSQL);
		int id = -3;
		try
		{
			pstmt = DB.prepareStatement(finalSQL, null);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				id = rs.getInt(1);		//	first
				if (rs.next())
					id = -1;			//	only if not unique
			}
		}
		catch (Exception e)
		{
			log.log(Level.FINE, finalSQL, e); // Log at fine level as there are searches which may commonly fail here. 
			id = -2;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	Try like
		if (id == -3)
		{
			rSQL = getDirectAccessSQL(Info.getSQLText(text));
			finalSQL = Msg.parseTranslation(Env.getCtx(), rSQL);
			try
			{
				pstmt = DB.prepareStatement(finalSQL, null);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					id = rs.getInt(1);		//	first
					if (rs.next())
						id = -1;			//	only if not unique
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, finalSQL, e);
				id = -2;
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}

		//	No (unique) result
		if (id <= 0)
		{
			if (id == -3)
				log.fine(m_columnName + " - Not Found - " + finalSQL);
			else
				log.fine(m_columnName + " - Not Unique - " + finalSQL);
			m_value = null;	// force re-display
			m_cc.setColorName(m_text.getText());  // as a default
 			actionButton();
			return;
		}
		log.fine(m_columnName + " - Unique ID=" + id);
		m_value = null;     //  forces re-display if value is unchanged but text updated and still unique
		
		// Set context info - See vlookup.resetTabinfo().  Not sure this is necessary for vcolor
		Env.setContext(Env.getCtx(), m_lookup.getWindowNo(), Env.TAB_INFO, "AD_Color_ID", null);
		actionCombo (new Integer(id));    //  data binding
		//
		// Don't request focus if value was found - teo_sarca [ 2552901 ]
		if (id <= 0)
		{
			m_text.requestFocus();
		}
	}	//	actionText

	/**
	 * 	Generate Access SQL for Search.
	 * 	The SQL returns the ID of the value entered
	 *	@param text uppercase text for LIKE comparison
	 *	@return sql
	 */
	private String getDirectAccessSQL (String text)
	{		
		StringBuffer sql = new StringBuffer();
		//
		sql.append("SELECT AD_Color_ID FROM AD_Color WHERE (")
			.append("UPPER(Name) LIKE  ")
			.append(DB.TO_STRING(text))
			.append(")");
				
		String wc = getWhereClause();
		if (wc != null && wc.length() > 0)
			sql.append(" AND ").append(wc);
		sql.append(" AND IsActive='Y'");
		//	***
		log.finest(m_columnName + " (predefined) " + sql.toString());
		return MRole.getDefault().addAccessSQL(sql.toString(),
			m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);

	}	//	getDirectAccessSQL

	/**
	 * 	Get Where Clause  
	 *	@return where clause or ""
	 */
	private String getWhereClause()
	{
		// TODO if this class extends VLookup, a lot of this stuff could be removed.
		// This is identical.
		String whereClause = "";
		if (m_lookup == null)
			return "";
		if (m_lookup.getZoomQuery() != null)
			whereClause = m_lookup.getZoomQuery().getWhereClause();
		String validation = m_lookup.getValidation();
		if (validation == null)
			validation = "";
		if (whereClause.length() == 0)
			whereClause = validation;
		else if (validation.length() > 0)
			whereClause += " AND " + validation;
	//	log.finest("ZoomQuery=" + (m_lookup.getZoomQuery()==null ? "" : m_lookup.getZoomQuery().getWhereClause())
	//		+ ", Validation=" + m_lookup.getValidation());
		if (whereClause.indexOf('@') != -1)
		{
			String validated = Env.parseContext(Env.getCtx(), m_lookup.getWindowNo(), whereClause, false);
			if (validated.length() == 0)
			{
				// Use a warning rather than a severe error.  The context may not be set which is OK. 
				log.warning(m_columnName + " - Cannot Parse=" + whereClause);
			}
			else
			{
				log.fine(m_columnName + " - Parsed: " + validated);
				return validated;
			}
		}
		return whereClause;
	}	//	getWhereClause

	/**
	 *  Set Content and Size of Components
	 *  @param initial if true, size and margins will be set
	 */
	private void setUI ()
	{

		if (!m_initialSetupCompleted )
		{
			this.setLayout(new BorderLayout());
						
			m_mouseAdapter = new VColor_mouseAdapter(this);    //  popup
			
			//	***	Text	***
			m_text.addActionListener(this);
			m_text.addFocusListener(this);
			m_text.addMouseListener(m_mouseAdapter);
			if (m_cc != null)
			m_text.setText(m_cc.getColorName());		

			//  Size
			Dimension size = m_text.getPreferredSize();  // Seems to have width set to 6?
			FIELD_HEIGHT = size.height;
			Dimension prefSize = new Dimension(60, size.height);
			Dimension minSize = new Dimension(30, size.height);
			Dimension bSize = new Dimension(size.height, size.height);
			m_button.setPreferredSize (bSize);
			setPreferredSize(prefSize);
			setMinimumSize(minSize);
			
			
			//	***	 Color Button	***
			m_button.addMouseListener(m_mouseAdapter);
			m_button.setMargin(new Insets(0, 0, 0, 0));
			m_button.addActionListener(this);
			m_button.setFocusable(false);
			m_button.setEnabled(true);  // Always active
			m_button.setBackgroundColor(m_cc);

			m_combo.setPreferredSize(prefSize);
			m_combo.setMinimumSize(minSize);
			m_text.setPreferredSize(prefSize);
			m_text.setMinimumSize(minSize);

			//

			this.add(m_button, BorderLayout.EAST);
			
			//	*** VComboBox	***
			//  The combo box is only required if the editor is active
			// AutoCompletion.enable(m_combo);
//			m_combo.addActionListener(this);
//			m_combo.setEditable(true);
			m_combo.addItemListener(this);
			m_combo.getEditor().getEditorComponent().addMouseListener(m_mouseAdapter);	//	popup
			m_combo.getEditor().getEditorComponent().addFocusListener(this);;
			m_combo.getEditor().addActionListener(this);
			m_combo.getEditor().getEditorComponent().addPropertyChangeListener(this);
			//	FocusListener to refresh selection before opening
			m_combo.addFocusListener(this);
			ListCellRenderer renderer = new ColorCellRenderer();
			m_combo.setRenderer(renderer);
			m_combo.setMaximumRowCount(15);
			
			//	Popup
	        menuInfo = new CMenuItem(Msg.getMsg(Env.getCtx(), "Info"), Env.getImageIcon("Zoom16.gif"));
			menuZoom = new CMenuItem(Msg.getMsg(Env.getCtx(), "Zoom"), Env.getImageIcon("Zoom16.gif"));
			mRefresh = new CMenuItem(Msg.getMsg(Env.getCtx(), "Refresh"), Env.getImageIcon("Refresh16.gif"));
			menuInfo.addActionListener(this);
			menuZoom.addActionListener(this);
			mRefresh.addActionListener(this);
			popupMenu.add(mRefresh);
			popupMenu.add(menuZoom);
			popupMenu.add(menuInfo);
			
			// get default no focus border
	        Border border = DefaultLookup.getBorder(this, ui, "Table.cellNoFocusBorder");
	        if (System.getSecurityManager() != null) {
	            if (border != null)
	            	m_noFocusBorder = border;
	        } 
	        else if (border != null)
	        {
	                m_noFocusBorder = border;
	        }

	        m_button.setBorderPainted(false);

	        m_initialSetupCompleted = true;
		}
		//	What to show
		this.remove(m_combo);  //  Need to attach m_combo to a parent for event processing in the color dialog.
		this.remove(m_button); // Always show the color button
		this.remove(m_text);
		
//		LookAndFeel.uninstallBorder(this);		
		if (m_isTableCellRenderer)
		{
	        setBorder(m_noFocusBorder);
			LookAndFeel.uninstallBorder(m_button);
	        m_button.setBorderPainted(false);
		}
		else
		{
			LookAndFeel.installBorder(this, "TextField.border");
	        m_button.setBorder(m_noFocusBorder);
	        m_combo.setBorder(null);
		}

		m_text.setBorder(null);

		//  If the control is not editable, only use the text field and button.
		if (!setEnableEdits())
		{
			this.add(m_text, BorderLayout.CENTER);
			this.add(m_combo, BorderLayout.SOUTH);  //  Need to attach m_combo to "this" so it has a parent
			this.add(m_button, BorderLayout.EAST);
			m_text.setReadWrite(false);
			m_combo.setReadWrite(false);
			m_combo.setVisible(false);
			m_comboActive = false;			
		}
		else
		{
			LookAndFeel.uninstallBorder(this);
			// We can edit something - use the Combo and button.
			this.add(m_combo, BorderLayout.CENTER);
			this.add(m_button, BorderLayout.EAST);
			m_combo.setReadWrite(true);
			m_combo.setVisible(true);
			m_comboActive = true;

		}
		
	}   //  setUI

	public static CompiereColor getDefaultColor() {
		// Set the default color displayed
		if (m_defaultColor == null)
			m_defaultColor = MColor.getDefaultColor(Env.getCtx());
		
		if (m_defaultColor == null)
			m_defaultColor = CompiereColor.getDefaultBackground();

		return m_defaultColor;
	}

	/**
	 *	Action - Button.
	 *	- Open the color chooser dialog
	 *	@param colorName initial colorName
	 */
	private void actionButton ()
	{
		
		m_button.setEnabled(false);                 //  disable double click
		
		m_text.requestFocus();						//  closes other editors
		
		Frame frame = Env.getFrame(this);

		int ad_color_id = 0;
		
		//  Is this needed?
		if (m_lookup != null)
		{
			String whereClause = getWhereClause();
			//
			log.fine(m_lookup.getColumnName()
				+ ", Zoom=" + m_lookup.getZoom()
				+ " (" + whereClause + ")");
		}
		
		// If this was a lookup, we'd open the info window.  For a color, its the color dialog.
		// For general windows, the color dialog will allow the user to select a system color
		// or define a new one for a color field.  The dialog will return the color ID.  For 
		// the System Color window, the ID is under the control of the GridTab. In this case, 
		// the values of the color will be set in the System Color tab fields directly.
		VColorEditor ce = VColorEditor.showDialog(Env.getFrame(this), m_WindowNo, ad_color_id, m_cc, !m_isAD_Color_Table);
		ad_color_id = ce.getAD_Color_ID();  // TODO Consider adding AD_Color_ID to CompiereColor - no, can't set the ID
		
		if (m_isAD_Color_Table)
		{
			// Here we ignore the ID as the editor can't set an ID field.  Instead, well use
			// the color selected in the editor to set the color fields in the tab.
			if (!ce.isCancelled() && ce.isSaved())
			{
				m_cc = ce.getColor();
				this.setTabFields();
			}
			else if (ce.isCancelled())
			{
				// Can't delete the record.  Do nothing.
				// m_cc will be what it was before the editor.
				// actionCombo(m_AD_Color_ID);
			}
		}
		else if(m_readWrite && m_isUpdateable)
		{
			//  save the result binding
			if (!ce.isCancelled() && ce.isSaved() && ad_color_id > 0)
			{
				log.config(m_columnName + " - AD_Color_ID = " + ad_color_id + " (" + m_cc.getColorName() + ")");
				//  make sure that value is in cache
				m_lookup.getDirect(ad_color_id, false, true);
				actionCombo (ad_color_id);  //  Data binding
			}
			else if (ce.isCancelled())
			{
				log.config(m_columnName + " - Result = null (deleted)");
				m_cc = CompiereColor.getDefaultBackground();
				m_AD_Color_ID = 0;
				actionCombo(null);  		//  Data binding
			}
			else
			{
				log.config(m_columnName + " - Result = null (op cancelled)");
				setValue(m_value);      	//  to re-display value
			}
			//
			m_text.requestFocus();
		}
		else
			log.config(m_columnName + " - Field not writable.  No change.");
		
		m_button.setEnabled(true);

	}	//	actionButton

	
	/**
	 *	Action - Combo.
	 *  <br>
	 *	== dataBinding == inform of new value
	 *  <pre>
	 *  VColor.actionCombo
	 *      GridController.vetoableChange
	 *          MTable.setValueAt
	 *              MField.setValue
	 *                  VLookup.setValue
	 *          MTab.dataStatusChanged
	 *  </pre>
	 *  @param value new value
	 */
	protected void actionCombo (Object value)
	{
//		String display = ((JTextField) m_combo.getEditor().getEditorComponent()).getText();
//		log.fine("Value=" + value + " Displayed text: " + display);
//		if (value == null && display != null && !display.isEmpty())
//		{
//			m_text.setText(display);
//			actionText();
//			return;
//		}
		m_settingValue = true;  // Stop events
		
		// A special case when this is a color field 
		if (m_isAD_Color_Table)
		{
			setTabFields(); 		// Update the tab
			m_reload = true;
			setValue(getValue()); 	// Redraw - there is no event to trigger it.
			return;
		}
		else
		{
			try
			{
				//  -> GridController.vetoableChange
				//  Will call setValue() if no veto occurs
				fireVetoableChange (m_columnName, m_value, value);
			}
			catch (PropertyVetoException pve)
			{
				//  Change was rejected.
				log.log(Level.INFO, m_columnName, pve);
				m_settingValue = false;
				return;
			}
		}
	}	//	actionCombo
	
	/**
	 *	Action - Refresh
	 */
	private void actionRefresh()
	{
		if (m_lookup == null)
			return;
		//
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//
		Object obj = m_combo.getSelectedItem();
		log.info(m_columnName + " #" + m_lookup.getSize() + ", Selected=" + obj);
		//no need to refresh readonly lookup, just remove direct cache
		if (!isReadWrite())
		{
			m_settingValue = true;		//	disable actions
			m_lookup.removeAllElements();
			m_lastDisplay = m_lookup.getDisplay(m_value);
			m_text.setText(m_lastDisplay);
			m_text.setCaretPosition(0);
			m_settingValue = false;
		}
		else
		{
			m_lookup.refresh();
			m_lookup.fillComboBox(isMandatory(), true, true, false);
			m_combo.setSelectedItem(obj);
			//m_combo.revalidate();
		}
		//
		setCursor(Cursor.getDefaultCursor());
		log.info(m_columnName + " #" + m_lookup.getSize() + ", Selected=" + m_combo.getSelectedItem());
	}	//	actionRefresh


	private void setTabFields() {

		if (m_mTab == null || !m_isAD_Color_Table || m_settingValue)
			return;
		
		m_settingValue = true;
		
		//  Update Values
		// Name
		m_mTab.setValue(MColor.COLUMNNAME_Name, m_cc.getColorName());
		
		// Type
		m_mTab.setValue(MColor.COLUMNNAME_ColorType, m_cc.getType());
		
		// Type related values
		if (m_cc.isFlat())
		{
			setTabColorFields (m_cc.getFlatColor(), true);
		}
		else if (m_cc.isGradient())
		{
			setTabColorFields (m_cc.getGradientUpperColor(), true);
			setTabColorFields (m_cc.getGradientLowerColor(), false);
			m_mTab.setValue(MColor.COLUMNNAME_RepeatDistance,   new BigDecimal(m_cc.getGradientRepeatDistance()));
			m_mTab.setValue(MColor.COLUMNNAME_StartPoint,       String.valueOf(m_cc.getGradientStartPoint()));
		}
		else if (m_cc.isLine())
		{
			setTabColorFields (m_cc.getLineBackColor(), true);
			setTabColorFields (m_cc.getLineColor(), false);
			m_mTab.setValue(MColor.COLUMNNAME_LineWidth, BigDecimal.valueOf(m_cc.getLineWidth()));
			m_mTab.setValue(MColor.COLUMNNAME_LineDistance, BigDecimal.valueOf(m_cc.getLineDistance()));
		}
		else if (m_cc.isTexture())
		{
			setTabColorFields (m_cc.getTextureTaintColor(), true);
			m_mTab.setValue(MColor.COLUMNNAME_AD_Image_ID, m_cc.getAD_Image_ID());
		}
		// ImageAlpha is mandatory (why??)
		m_mTab.setValue(MColor.COLUMNNAME_ImageAlpha, new BigDecimal(m_cc.getTextureCompositeAlpha()));
		
		m_settingValue = false;

	}

	/**
	 *	Action - Zoom
	 *	@param selectedItem item
	 */
	private void actionZoom (Object selectedItem)
	{

		if (m_lookup == null)
			return;
		//
		MQuery zoomQuery = m_lookup.getZoomQuery();
		Object value = getValue();
		if (value == null)
			value = selectedItem;
		//	If not already exist or exact value
		if (zoomQuery == null || value != null)
		{
			zoomQuery = new MQuery();	//	ColumnName might be changed in MTab.validateQuery
			String keyTableName = null;
			String keyColumnName = null;
			//	Check if it is a Table Reference
			if (m_lookup != null && m_lookup instanceof MColorLookup)
			{
				int AD_Reference_ID = ((MColorLookup)m_lookup).getAD_Reference_Value_ID();
				if (DisplayType.List == m_lookup.getDisplayType()) {
					keyColumnName = "AD_Ref_List_ID";
					keyTableName = "AD_Ref_List";
					value = DB.getSQLValue(null, "SELECT AD_Ref_List_ID FROM AD_Ref_List WHERE AD_Reference_ID=? AND Value=?", AD_Reference_ID, value);
				} else {
					if (AD_Reference_ID != 0)
					{
						String query = "SELECT kc.ColumnName, kt.TableName"
							+ " FROM AD_Ref_Table rt"
							+ " INNER JOIN AD_Column kc ON (rt.AD_Key=kc.AD_Column_ID)"
							+ " INNER JOIN AD_Table kt ON (rt.AD_Table_ID=kt.AD_Table_ID)"
							+ " WHERE rt.AD_Reference_ID=?";
	
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						try
						{
							pstmt = DB.prepareStatement(query, null);
							pstmt.setInt(1, AD_Reference_ID);
							rs = pstmt.executeQuery();
							if (rs.next())
							{
								keyColumnName = rs.getString(1);
								keyTableName = rs.getString(2);
							}
						}
						catch (Exception e)
						{
							log.log(Level.SEVERE, query, e);
						}
						finally
						{
							DB.close(rs, pstmt);
							rs = null; pstmt = null;
						}
					}	//	Table Reference
					
				}
			}	//	MLookup
	
			if(keyColumnName != null && keyColumnName.length() !=0)
			{
				zoomQuery.addRestriction(keyColumnName, MQuery.EQUAL, value);
				zoomQuery.setZoomColumnName(keyColumnName);
				zoomQuery.setZoomTableName(keyTableName);
			}
			else
			{
				zoomQuery.addRestriction(m_columnName, MQuery.EQUAL, value);
				if (m_columnName.indexOf(".") > 0)
				{
					zoomQuery.setZoomColumnName(m_columnName.substring(m_columnName.indexOf(".")+1));
					zoomQuery.setZoomTableName(m_columnName.substring(0, m_columnName.indexOf(".")));
				}
				else
				{
					zoomQuery.setZoomColumnName(m_columnName);
					//remove _ID to get table name
					zoomQuery.setZoomTableName(m_columnName.substring(0, m_columnName.length() - 3));
				}
			}
			zoomQuery.setZoomValue(value);
	
			zoomQuery.setRecordCount(1);	//	guess
		}
	
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
	 * Reload the color editor - applicable only if the editor is in the AD_Color table and references the AD_Color_ID field.
	 */
	public void reload()
	{
		if (!m_isAD_Color_Table || m_settingValue)
			return;
		
		setValue(getValue());  
	}
	/**
	 * 	Refresh Query
	 *	@return count
	 */
	public int refresh()
	{
		if (m_lookup == null)
			return -1;

		//no need to refresh readonly lookup, just remove direct cache
		if (!isReadWrite()) {
			m_lookup.removeAllElements();
			return 0;
		}

		return m_lookup.refresh();
	}	//	refresh

	// This applies to the special case of the VColor control in the System Color window
	// The control is connected to the AD_Color_ID so can't set the record itself without 
	// causing a DB lock.  This does the same as a callout.
	
	private CompiereColor getFromTab() {

		if (m_mTab == null || !m_isAD_Color_Table)
			return null;
		
		Integer AD_Color_ID = (Integer)m_mTab.getValue(MColor.COLUMNNAME_AD_Color_ID);
				
		log.fine("AD_Color_ID=" + AD_Color_ID);
		CompiereColor cc = null;

		//  Color Type
		String ColorType = (String)m_mTab.getValue(MColor.COLUMNNAME_ColorType);
		if (ColorType == null)
		{
			log.fine("No ColorType");
			return null;
		}
		//
		if (ColorType.equals(CompiereColor.TYPE_FLAT))
		{
			cc = new CompiereColor(getTabColorFields(true), true);
		}
		else if (ColorType.equals(CompiereColor.TYPE_GRADIENT))
		{
			BigDecimal RepeatDistance = getNumericTabField(MColor.COLUMNNAME_RepeatDistance);
			String StartPoint = (String)m_mTab.getValue(MColor.COLUMNNAME_StartPoint);
			int repeatDistance = RepeatDistance == null ? 0 : RepeatDistance.intValue();
			int startPoint = StartPoint == null ? 0 : Integer.parseInt(StartPoint);
			cc = new CompiereColor(getTabColorFields(true), getTabColorFields(false), startPoint, repeatDistance);
		}
		else if (ColorType.equals(CompiereColor.TYPE_LINES))
		{
			BigDecimal LineWidth = getNumericTabField(MColor.COLUMNNAME_LineWidth);
			BigDecimal LineDistance = getNumericTabField(MColor.COLUMNNAME_LineDistance);
			int lineWidth = LineWidth == null ? 0 : LineWidth.intValue();
			int lineDistance = LineDistance == null ? 0 : LineDistance.intValue();
			cc = new CompiereColor(getTabColorFields(false), getTabColorFields(true), lineWidth, lineDistance);
		}
		else if (ColorType.equals(CompiereColor.TYPE_TEXTURE))
		{
			Integer AD_Image_ID = (Integer)m_mTab.getValue(MColor.COLUMNNAME_AD_Image_ID);
			String url = getURL(AD_Image_ID);
			if (url == null)
			{
				log.fine("No image URL.");
				return null;
			}
			BigDecimal ImageAlpha = getNumericTabField(MColor.COLUMNNAME_ImageAlpha);
			float compositeAlpha = ImageAlpha == null ? 0.7f : ImageAlpha.floatValue();
			cc = new CompiereColor(url, getTabColorFields(true), compositeAlpha);
		}

		cc.setColorName(m_mTab.get_ValueAsString(MColor.COLUMNNAME_Name));
		
		log.fine("AdempiereColor=" + cc);
		return cc;
	}

	/**
	 * Respond to combo box events.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
				
		if (e.getStateChange() == ItemEvent.SELECTED)
		{
			if (m_settingValue)
				return;
			
			// This event handler responds to events caused by the user
			// in selecting elements of the combo-box.  It should NOT
			// respond to programmatic changes.
			
			Object p = m_combo.getSelectedItem();
			log.fine("Item Selected:" + p + " (Current selection " +  m_cc + ") " + e.toString());
			
			Object value = null;
			String name = null;
			if (p instanceof ColorPair)
			{
				name = ((ColorPair) p).getName();
				if (p instanceof KeyColorPair)
				{
					if (((KeyColorPair) p).getID() != null)
						value =  new Integer(((KeyColorPair)p).getID());
				}
			}

			if (name != null)
			{
				//  don't allow selection of inactive
				if (name.startsWith(MLookup.INACTIVE_S) && name.endsWith(MLookup.INACTIVE_E))
				{
					log.info(m_columnName + " - selection inactive set to NULL");
					value = null;
				}
			}
			
			actionCombo (value);                //  data binding

		}
	}

	/** Sets the m_enableEdits field variable.  Is called from SetUI().
	 * 
	 * @return true if edits should be enabled.
	 */
	private boolean setEnableEdits() {
		
		m_enableEdits = !m_isTableCellRenderer
				&& m_readWrite 
				&& (m_isUpdateable || (!m_isUpdateable && m_AD_Color_ID==0))
				&& m_mField != null && !m_mField.isKey()  // Can't edit key fields, but we can display them.
				&& !m_isAD_Color_Table  // Don't edit color fields in the AD_Color table directly.
				&& m_lookup != null;
		
		return m_enableEdits;

	}

	public void setColumnName(String columnName) {
		
		m_columnName = columnName;
		super.setName(m_columnName);
		m_text.setName("VColor Text - " + m_columnName);
		m_button.setName("VColor Dialog Button - " + m_columnName);
		m_combo.setName("VColor Combo - " + m_columnName);

	}

	public void setIsSelected(boolean isSelected, boolean hasFocus) {
		
		m_isSelected = isSelected;
		m_haveFocus = hasFocus; 
		
	}

	// Called from VCellRenderer when the renderer is being
	// provided to a table cell.
	public void setIsTableCellRenderer(boolean isTableCellRenderer) {
		
		m_isTableCellRenderer = isTableCellRenderer;
		setUI();
	}

	public Lookup getLookup() {
		return m_lookup;
	}

	public void setLookup(Lookup lookup) {
		
		if (lookup instanceof MColorLookup)
		{
			this.m_lookup = (MColorLookup) lookup;
		}
		else
		{
			log.severe("Incompatible lookup type.  Instance of MColorLookup required.");
			this.m_lookup = null;
		}
		
		if (lookup != null)
			m_combo.setModel(m_lookup);
	}

}   //  VColor
