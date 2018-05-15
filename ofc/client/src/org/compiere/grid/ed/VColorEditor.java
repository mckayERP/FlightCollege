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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MColor;
import org.compiere.plaf.CompiereColor;
import org.compiere.plaf.CompiereLookAndFeel;
import org.compiere.plaf.CompiereColor.ColorBackground;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.ValueNamePair;

/**
 *  Adempiere Color Editor
 *
 *  @author     Michael McKay
 *  Based on org.compiere.swing.ColorEditor 
 */
public class VColorEditor extends CDialog
	implements ActionListener, PropertyEditor, PropertyChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	
	private static CLogger log = CLogger.getCLogger(VColorEditor.class);
	
	/** The ID of the entry in the AD_Color table */ 
	private int m_AD_Color_ID;
	
	/** The model for the database color */
	private MColor m_mColor;
	
	/** Save the color edited color as an entry in the AD_Color table.
	 *  If false, the color will be available, but won't be saved.
	 */
	private boolean m_saveSystemColor = true;

	/** The original color passed in as a parameter. */
	private final CompiereColor 	m_origCC;
	
	/** The chosen color being worked on.  */
	private CompiereColor   m_cc = null;
	
	/** A flag indicating the data has been saved and 
	 *  should be valid.
	 */
	private boolean         m_isSaved = false;

	/** A flag indicating the color edit has been cancelled. */
	private boolean m_isCancelled;

	/** A flag indicating that the value is being set.
	 *  Used to prevent spurious action performed events.
	 */
	private boolean         m_setting = false;

	private Frame m_frame;

	private int m_windowNo;

	
	private class PaintableCPanel extends CPanel
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7297960497440944513L;

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Dimension d = getSize();
			Rectangle bounds = new Rectangle(0, 0, d.height, d.width);
			getBackgroundColor().getColorBackground(bounds).paintNoCheck(g, 
					this);
			
		}
		
	}
	/**
	 *  Open a dialog to edit a color
	 *  @param owner The parent frame for this dialog.
	 *  @param ad_color_id from the AD_Color table or zero if not known
	 *  @param cc the initial CompiereColor or null.  This color will be used to
	 *  initialize the dialog if an AD_Color_ID is not provided.
	 *  @param saveSystemColor True to save the color as a new color in the AD_Color table.
	 *  @return VColorEditor. When the editor dialog is closed, call 
	 *  <ul><li> {@link #isSaved()} to test if the value was saved.
	 *  <li> {@link #isCancelled()} to test if the operation was cancelled.
	 *  <li> {@link #getAD_Color_ID()} to get the selected/new ID
	 *  <li> {@link #getColor()} to get the select CompiereColor</ul> *  
	 */
	public static VColorEditor showDialog (Frame owner, int windowNo, int ad_color_id, CompiereColor cc, boolean saveSystemColor)
	{	
		return new VColorEditor (owner, windowNo, ad_color_id, cc, saveSystemColor);
		
	}   //  showDialog
	
	/**
	 *  Open a dialog to edit a color
	 *  @param frame The parent frame for this dialog.
	 *  @param ad_color_id from the AD_Color table or zero if not known
	 *  @param cc the initial CompiereColor or null.  This color will be used to
	 *  initialize the dialog if an AD_Color_ID is not provided.
	 *  @param saveSystemColor True to save the color as a new color in the AD_Color table.
	 *  @return VColorEditor. When the editor dialog is closed, call 
	 *  <ul><li> {@link #isSaved()} to test if the value was saved.
	 *  <li> {@link #isCancelled()} to test if the operation was cancelled.
	 *  <li> {@link #getAD_Color_ID()} to get the selected/new ID
	 *  <li> {@link #getColor()} to get the select CompiereColor</ul> 
	 */
	public VColorEditor (Frame frame, int windowNo, int ad_color_id, CompiereColor cc, boolean saveSystemColor)
	{
		super(frame, "", true);
		m_frame = frame;
		m_windowNo = windowNo;
		m_saveSystemColor  = saveSystemColor;
		m_origCC = cc;
		m_AD_Color_ID = ad_color_id;
		
		// Initialize the dialog with the color and 
		// open the dialog.
		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "VColorEditor" + ex);
		}

		if (!init())
		{
			m_isSaved = false;
			m_isCancelled = false;
			dispose();
			return;
		}
		
		// Open the dialog and get creative!
		AEnv.showCenterWindow(frame, this);		
		
	}   //  VColorEditor

	/**
	 *  Init Dialog
	 *  @return true if successful
	 */
	private boolean init ()
	{
		// Load the color model.  If the id is zero, it will be the base
		// of a new color should it be saved.
		m_mColor = new MColor(Env.getCtx(), m_AD_Color_ID, null);
		
		// Get the id. It will be the same as the parameter or
		// zero if the AD_Color table entry was not found.
		m_AD_Color_ID = m_mColor.getAD_Color_ID();
		
		//  Note - need to clone the incoming CompiereColor as we don't want any changes
		//  done in the dialog to affect the incoming if the dialog is cancelled.
		//  Use the MColor model to copy the values.
		//  TODO - add a clone method to the CompiereColor
		
		// If both the ID and the color are null, we're starting from
		// scratch.  Set the color to the default.
		if (m_AD_Color_ID <= 0 && m_origCC == null)
		{
			// Set to the defaults - the default background
			m_cc = new CompiereColor();
			m_mColor.setAdempiereColor(m_cc);
		}
		// The incoming color has the priority. If it is not null,
		// load it as the color to display in the dialog.
		else if (m_origCC != null) 
		{
			// This may change the model. It hasn't been 
			// save yet.
			m_mColor.setAdempiereColor(m_origCC);
			m_cc = m_mColor.getAdempiereColor();
		}
		else
		{
			// m_AD_Color_ID > 0 and cc== null  
			
			// Use the color model as the starting point for the dialog.
			// Copy the values from the model into the color.
			m_cc = m_mColor.getAdempiereColor();
		}

		setColor(m_cc);

		return true;
		
	}   //  init


	//  Fields and Panels
	private CPanel headerPane = new CPanel();
	private CPanel cardPane = new CPanel();
	private CPanel flatCard = new CPanel();
	private CPanel gradientCard = new CPanel();
	private CPanel textureCard = new CPanel();
	private CPanel lineCard = new CPanel();
	private ConfirmPanel confirmPanel = new ConfirmPanel (true);

	private CLabel flatColorLabel = new CLabel();
	private CLabel typeLabel = new CLabel();
	private CComboBox typeField = new CComboBox(CompiereColor.TYPES);
	private CButton gradientUpper = new CButton();
	private CButton gradientLower = new CButton();
	private CLabel urlLabel = new CLabel();
	private CTextField urlField = new CTextField(30);
	private CLabel alphaLabel = new CLabel();
	private CTextField alphaField = new CTextField(10);
	private CButton taintColor = new CButton();
	private CButton lineColor = new CButton();
	private CButton backColor = new CButton();
	private CLabel widthLabel = new CLabel();
	private VNumber widthField = new VNumber();
	private CLabel distanceLabel = new CLabel();
	private VNumber distanceField = new VNumber();
	private CButton flatField = new CButton();
	private CComboBox gradientStartField = new CComboBox(CompiereColor.GRADIENT_SP);
	private VNumber gradientDistanceField = new VNumber();
	private CLabel gradientStartLabel = new CLabel();
	private CLabel gradientDistanceLabel = new CLabel();
	private CLabel colorNameLabel = new CLabel();
	private CTextField colorNameField = new CTextField(30);
	
	private CPanel gradientSample = new PaintableCPanel();
	
	private static final Dimension fieldDimension = new Dimension(100,22);

	/**
	 *  Static Layout.
	 *  <pre>
	 *      - headerPane
	 *          - Name & Type
	 *      - cardPane - based on type
	 *      	- flatCard
	 *      	- gradientCard
	 *      	- lineCard
	 *      	- textureCard
	 *      - confimrPanel
	 *  </pre>
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{

		this.setTitle(Msg.translate(Env.getCtx(), "ColorEditor"));
		
		// Configure the fields
		
		// load the label text

		urlLabel.setText(Msg.translate(Env.getCtx(), "TextureURL"));
		alphaLabel.setText(Msg.translate(Env.getCtx(), "TextureAlpha"));
		taintColor.setText(Msg.translate(Env.getCtx(), "TextureTaintColor"));
		
		// Add Listeners to all fields
		typeField.addActionListener(this);
		urlField.addActionListener(this);
		alphaField.addActionListener(this);
		taintColor.addActionListener(this);
		colorNameField.addActionListener(this);

		// Set up the panels
		
		// Header pane
		headerPane.setLayout(new GridBagLayout());
//		headerPane.setPreferredSize(new Dimension(400, 150));
		GridBagConstraints c = new GridBagConstraints();
		
		colorNameLabel.setText(Msg.translate(Env.getCtx(), "ColorName"));
		colorNameField.setMandatory(true);
		colorNameField.setEditable(true);
		colorNameField.setPreferredSize(fieldDimension);
		
		typeLabel.setText(Msg.translate(Env.getCtx(), "ColorType"));
		typeField.setEditable(false);
		typeField.setVisible(true);
		typeField.setPreferredSize(fieldDimension);

		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 0;
		headerPane.add(colorNameLabel, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 1;
		c.gridy = 0;
		headerPane.add(colorNameField, c);

		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 1;
		headerPane.add(typeLabel, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 1;
		c.gridy = 1;
		headerPane.add(typeField, c);

		// Set up the card panes
		
		// Cards
		cardPane.setLayout(new CardLayout());

//		flatColorLabel.setText(Msg.translate(Env.getCtx(), "FlatColor") 
//				+ " (" + Msg.translate(Env.getCtx(), "ClickToChange") + ")");
//		
		flatField.setBorder(null);
		flatField.setFocusPainted(false);
		flatField.setBorderPainted(false);
		flatField.setText(Msg.translate(Env.getCtx(), "FlatColor") 
				+ " (" + Msg.translate(Env.getCtx(), "ClickToChange") + ")");
		flatField.setPreferredSize(new Dimension(150, 50));
		flatField.addActionListener(this);

		flatCard.setLayout(new GridBagLayout());
		
//		c.anchor = GridBagConstraints.EAST;
//		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
//		c.gridx = 0;
//		c.gridy = 0;
//		flatCard.add(flatColorLabel, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,0,0,0);  // top, left, bottom, right
		c.gridx = 1;
		c.gridy = 0;
		flatCard.add(flatField, c);
		
		cardPane.add(flatCard, CompiereColor.TYPE_FLAT);

		// Gradient Card
		gradientUpper.setText(Msg.translate(Env.getCtx(), "GradientUpperColor"));
		gradientUpper.addActionListener(this);
		gradientLower.setText(Msg.translate(Env.getCtx(), "GradientLowerColor"));
		gradientLower.addActionListener(this);
		gradientStartLabel.setText(Msg.translate(Env.getCtx(), "GradientStart"));
		gradientStartField.setPreferredSize(fieldDimension);
		gradientStartField.addActionListener(this);
		gradientDistanceLabel.setText(Msg.translate(Env.getCtx(), "GradientDistance"));
		gradientDistanceField.setDisplayType(DisplayType.Integer);
		gradientDistanceField.setPreferredSize(fieldDimension);
		gradientDistanceField.addActionListener(this);
		
		gradientSample.setPreferredSize(new Dimension (200, 200));
		gradientSample.setOpaque(false);  // Important if the custom paint function is to be visible.
		gradientSample.setBorder(BorderFactory.createLineBorder(Color.black));  // TODO - use PLAF

		gradientCard.setLayout(new GridBagLayout());
		
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 0;
		gradientCard.add(gradientUpper, c);

		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 1;
		c.gridy = 0;
		gradientCard.add(gradientLower, c);

		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 1;
		gradientCard.add(gradientStartLabel, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 1;
		c.gridy = 1;
		gradientCard.add(gradientStartField, c);

		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 2;
		gradientCard.add(gradientDistanceLabel, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 1;
		c.gridy = 2;
		gradientCard.add(gradientDistanceField, c);

		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		gradientCard.add(gradientSample, c);

		cardPane.add(gradientCard, CompiereColor.TYPE_GRADIENT);
		
		// Line Card
		lineColor.setText(Msg.translate(Env.getCtx(), "LineColor"));
		lineColor.addActionListener(this);
		backColor.setText(Msg.translate(Env.getCtx(), "LineBackColor"));
		backColor.addActionListener(this);
		widthLabel.setText(Msg.translate(Env.getCtx(), "LineWidth"));
		widthField.setDisplayType(DisplayType.Number);
		widthField.addActionListener(this);
		distanceLabel.setText(Msg.translate(Env.getCtx(), "LineDistance"));
		distanceField.setDisplayType(DisplayType.Integer);
		distanceField.addActionListener(this);

		lineCard.setLayout(new GridBagLayout());
		
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 0;
		lineCard.add(lineColor, c);

		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 1;
		lineCard.add(backColor, c);

		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 1;
		lineCard.add(widthLabel, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 1;
		c.gridy = 1;
		lineCard.add(widthField, c);

		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 0;
		c.gridy = 2;
		lineCard.add(distanceLabel, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);  // top, left, bottom, right
		c.gridx = 1;
		c.gridy = 2;
		lineCard.add(distanceField, c);

//		cardPane.setBorder(BorderFactory.createRaisedBevelBorder());
//		cardPane.setPreferredSize(new Dimension(400, 200));
//		cardPane.setOpaque(true);

		confirmPanel.addActionListener(this);

		this.getContentPane().add(headerPane,  BorderLayout.NORTH);
		this.getContentPane().add(cardPane, BorderLayout.CENTER);
		this.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
//		headerPane.add(gradientLower,              new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(urlField,               new GridBagConstraints(1, 5, 2, 1, 1.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(alphaLabel,            new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
//			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(alphaField,              new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(taintColor,            new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(backColor,            new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(widthLabel,            new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0
//			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(widthField,             new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(distanceLabel,            new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0
//			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(distanceField,             new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(flatField,          new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(gradientStartField,           new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(gradientDistanceField,          new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
//			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(urlLabel,      new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
//			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(gradientStartLabel,    new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
//			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(gradientDistanceLabel,     new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
//			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(gradientUpper,    new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
//			,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(lineColor,   new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
//			,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(colorNameLabel,   new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
//				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		headerPane.add(colorNameField,   new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
//				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}   //  jbInit

	/** 
	 * Set the foreground and background colors. The background is
	 * defined.  The foreground is simply set to black or white to
	 * create the most contrast with the background color.
	 * @param field
	 * @param color
	 */
	private void setFieldColor(JComponent field, Color color) {
		
		if (color == null)
			return;
		
		field.setBackground(color);
		
		if (color.getBlue()+color.getRed()+color.getGreen() > 382) // half bright
		{
			field.setForeground(Color.BLACK);
		}
		else
		{
			field.setForeground(Color.WHITE);
		}
		// Set 
	}

	/**
	 *  Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (m_setting || m_isSaved || m_isCancelled)
			return;
		
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (colorNameField.getValue() == null || ((String) colorNameField.getValue()).isEmpty())
			{
				ADialog.error(m_windowNo, m_frame, "@FillMandatory@ @ColorName@");
				return;
			}
			
			saveSelection();
			m_isCancelled = false;
			m_isSaved = true;							
							
			// Update the MColor values and save the record.
			dispose();
			return;
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			m_AD_Color_ID = 0;
			m_isCancelled = true;
			dispose();
			return;
		}

		boolean update = false;
		/**** Field Changes  ****/
		try
		{
			//  Type
			if (e.getSource() == typeField)
			{
				cmd_type();
				saveColor();
				update = true;
			}
			//  color buttons
			else if (e.getSource() == flatField
					|| e.getSource() == gradientUpper
					|| e.getSource() == gradientLower
					|| e.getSource() == taintColor
					|| e.getSource() == lineColor
					|| e.getSource() == backColor)
			{
				Color c = JColorChooser.showDialog(this, Msg.translate(Env.getCtx(), "ColorEditor"), m_cc.getFlatColor());
				if (c != null)
				{
					setFieldColor((CButton) e.getSource(), c);
					saveColor();
					update = true;
				}
			}
			// Text / Number fields
			else if (e.getSource() == gradientStartField
					|| e.getSource() == gradientDistanceField
					|| e.getSource() == urlField
					|| e.getSource() == alphaField)
			{
				saveColor();
				update = true;
			}
			else if (e.getSource() == colorNameField)
				m_cc.setColorName(colorNameField.getText());
		}
		catch (Exception ee)
		{
			log.severe(ee.getMessage());
			ee.printStackTrace();
		}
		
//		if (update)
//		{
//			SwingUtilities.invokeLater(new Runnable() {
//
//				@Override
//				public void run() {
//					
//					// Check if the dialog closed before this thread ran.
//					if (m_isSaved || m_isCancelled)
//						return;

					updateFields();
//				}});
//
//		}
		
	}   //  actionPerformed

	private void saveColor() {
		
		ValueNamePair vp = (ValueNamePair)typeField.getSelectedItem();
		String ColorType = vp.getValue();
		
		if (ColorType == null)
		{
			log.severe("No ColorType - can't proceed.");  
			return;
		}

		CompiereColor cc = null;
		//
		if (ColorType.equals(CompiereColor.TYPE_FLAT))
		{
			cc = new CompiereColor(flatField.getBackground(), true);
		}
		else if (ColorType.equals(CompiereColor.TYPE_GRADIENT))
		{
			Integer gdf = (Integer) gradientDistanceField.getValue();
			int repeatDistance = gdf == null ? 0 : gdf.intValue();
			int startPoint =((KeyNamePair)gradientStartField.getSelectedItem()).getKey();
			cc = new CompiereColor(gradientUpper.getBackground(), gradientLower.getBackground(), startPoint, repeatDistance);
		}
		else if (ColorType.equals(CompiereColor.TYPE_LINES))
		{
			BigDecimal width = (BigDecimal) widthField.getValue();
			float lineWidth = (float) (width == null ? 0.0 : width.floatValue());
			Integer distance = (Integer) distanceField.getValue();
			int lineDistance = distance == null ? 0 : distance.intValue();
			cc = new CompiereColor(lineColor.getBackground(), backColor.getBackground(), lineWidth, lineDistance);
		}
		else if (ColorType.equals(CompiereColor.TYPE_TEXTURE))
		{
//			int AD_Image_ID = getAD_Image_ID();
//			String url = getURL(AD_Image_ID);
//			if (url == null)
//				return null;
//			BigDecimal ImageAlpha = getImageAlpha();
//			float compositeAlpha = ImageAlpha == null ? 0.7f : ImageAlpha.floatValue();
//			cc = new CompiereColor(url, getColor(true), compositeAlpha);
		}
		
		cc.setColorName(colorNameField.getText());
		
		m_cc = cc;
		
	}
	
	private void saveSelection() {
		
		saveColor();  // Sets m_cc
		
		if (m_saveSystemColor)
		{
			m_mColor.setAdempiereColor(m_cc);
			m_mColor.saveEx();  // will set the ID and throw an error if there is a problem
			m_AD_Color_ID = m_mColor.getAD_Color_ID();  // get the final ID number
			m_cc = m_mColor.getAdempiereColor();  // Reload from the system (in case of changes)
		}

		this.m_isSaved = true;
		
	}


	/**
	 *  Set Type with default values
	 */
	private void cmd_type()
	{
		
		CardLayout cl = (CardLayout)(cardPane.getLayout());
		ValueNamePair vp = (ValueNamePair)typeField.getSelectedItem();
        cl.show(cardPane, vp.getValue());
		
	}   //  cmd_type

	/**
	 *  Set Color and update UI
	 *  @param color color
	 */
	public void setColor (CompiereColor color)
	{
		if (color == null)
			color = CompiereColor.getDefaultBackground();

		m_cc = color;

		//  update display
		updateFields();
		cmd_type();
//		cardPane.setBackgroundColor(m_cc);
//		cardPane.repaint();
	}   //  setColor

	/**
	 *  UpdateField from AdempiereColor
	 */
	private void updateFields()
	{
		m_setting = true;
		
		//  Name
		colorNameField.setText(m_cc.getColorName());
		
		//  Type
		for (int i = 0; i < CompiereColor.TYPES.length; i++)
		{
			if (m_cc.getType().equals(CompiereColor.TYPE_VALUES[i]))
			{
				typeField.setSelectedItem(CompiereColor.TYPES[i]);
				break;
			}
		}
		//
		if (m_cc.isFlat())
		{
			//
			setFieldColor(flatField,m_cc.getFlatColor());
		}
		else if (m_cc.isGradient())
		{
			//
			setFieldColor(gradientUpper, m_cc.getGradientUpperColor());
			setFieldColor(gradientLower, m_cc.getGradientLowerColor());
			gradientDistanceField.setValue(m_cc.getGradientRepeatDistance());
			for (int i = 0; i < CompiereColor.GRADIENT_SP.length; i++)
			{
				if (m_cc.getGradientStartPoint() == CompiereColor.GRADIENT_SP_VALUES[i])
				{
					gradientStartField.setSelectedItem(CompiereColor.GRADIENT_SP[i]);
					break;
				}
			}
			
			gradientSample.setBackgroundColor(m_cc);
			gradientSample.repaint();
						
		}
		else if (m_cc.isTexture())
		{
			//
			urlField.setText(m_cc.getTextureURL().toString());
			alphaField.setText(String.valueOf(m_cc.getTextureCompositeAlpha()));
			setFieldColor(taintColor,m_cc.getTextureTaintColor());
		}
		else if (m_cc.isLine())
		{
			//
			setFieldColor(lineColor,m_cc.getLineColor());
			setFieldColor(backColor,m_cc.getLineBackColor());
			widthField.setValue(m_cc.getLineWidth());
			distanceField.setValue(m_cc.getLineDistance());

		}
		m_setting = false;
		
	}   //  updateFields

	/**
	 *  Get Color
	 *  @return Color, when saved - else null
	 */
	public CompiereColor getColor()
	{
		return m_cc;
	}   //  getColor

	/**
	 *  Was the selection saved
	 *  @return true if saved
	 */
	public boolean isSaved()
	{
		return m_isSaved;
	}   //  m_isSaved

	/*************************************************************************/

	/**
	 * Set (or change) the object that is to be edited.  Primitive types such
	 * as "int" must be wrapped as the corresponding object type such as
	 * "java.lang.Integer".
	 *
	 * @param value The new target object to be edited.  Note that this
	 *     object should not be modified by the PropertyEditor, rather
	 *     the PropertyEditor should create a new object to hold any
	 *     modified value.
	 */
	public void setValue(Object value)
	{
		if (value != null && value instanceof CompiereColor)
			setColor (new CompiereColor((CompiereColor)value));
		else
			throw new IllegalArgumentException("VColorEditor.setValue requires AdempiereColor");
	}   //  setValue

	/**
	 * Gets the property value.
	 *
	 * @return The value of the property.  Primitive types such as "int" will
	 * be wrapped as the corresponding object type such as "java.lang.Integer".
	 */
	public Object getValue()
	{
		return getColor();
	}   //  getColor

	/**
	 * Determines whether this property editor is paintable.
	 * @return  True if the class will honor the paintValue method.
	 */
	public boolean isPaintable()
	{
		return false;
	}

	/**
	 * Paint a representation of the value into a given area of screen
	 * real estate.  Note that the propertyEditor is responsible for doing
	 * its own clipping so that it fits into the given rectangle.
	 * <p>
	 * If the PropertyEditor doesn't honor paint requests (see isPaintable)
	 * this method should be a silent noop.
	 * <p>
	 * The given Graphics object will have the default font, color, etc of
	 * the parent container.  The PropertyEditor may change graphics attributes
	 * such as font and color and doesn't need to restore the old values.
	 *
	 * @param gfx  Graphics object to paint into.
	 * @param box  Rectangle within graphics object into which we should paint.
	 */
	public void paintValue(Graphics gfx, Rectangle box)
	{
		/**@todo: Implement this java.beans.PropertyEditor method*/
		throw new java.lang.UnsupportedOperationException("Method paintValue() not yet implemented.");
	}   //  paintValue

	/**
	 * This method is intended for use when generating Java code to set
	 * the value of the property.  It should return a fragment of Java code
	 * that can be used to initialize a variable with the current property
	 * value.
	 * <p>
	 * Example results are "2", "new Color(127,127,34)", "Color.orange", etc.
	 *
	 * @return A fragment of Java code representing an initializer for the
	 *   	current value.
	 */
	public String getJavaInitializationString()
	{
		return "new AdempiereColor()";
	}   //  String getJavaInitializationString

	/**
	 * Gets the property value as text.
	 *
	 * @return The property value as a human editable string.
	 * <p>   Returns null if the value can't be expressed as an editable string.
	 * <p>   If a non-null value is returned, then the PropertyEditor should
	 *	     be prepared to parse that string back in setAsText().
	 */
	public String getAsText()
	{
		return m_cc.toString();
	}   //  getAsText

	/**
	 * Set the property value by parsing a given String.  May raise
	 * java.lang.IllegalArgumentException if either the String is
	 * badly formatted or if this kind of property can't be expressed
	 * as text.
	 * @param text  The string to be parsed.
	 * @throws IllegalArgumentException
	 */
	public void setAsText(String text) throws java.lang.IllegalArgumentException
	{
		throw new java.lang.IllegalArgumentException("VColorEditor.setAsText not supported");
	}   //  setAsText

	/**
	 * If the property value must be one of a set of known tagged values,
	 * then this method should return an array of the tags.  This can
	 * be used to represent (for example) enum values.  If a PropertyEditor
	 * supports tags, then it should support the use of setAsText with
	 * a tag value as a way of setting the value and the use of getAsText
	 * to identify the current value.
	 *
	 * @return The tag values for this property.  May be null if this
	 *   property cannot be represented as a tagged value.
	 */
	public String[] getTags()
	{
		return null;
	}   //  getTags

	/**
	 * A PropertyEditor may choose to make available a full custom Component
	 * that edits its property value.  It is the responsibility of the
	 * PropertyEditor to hook itself up to its editor Component itself and
	 * to report property value changes by firing a PropertyChange event.
	 * <P>
	 * The higher-level code that calls getCustomEditor may either embed
	 * the Component in some larger property sheet, or it may put it in
	 * its own individual dialog, or ...
	 *
	 * @return A java.awt.Component that will allow a human to directly
	 *      edit the current property value.  May be null if this is
	 *	    not supported.
	 */
	public Component getCustomEditor()
	{
		return this;
	}   //  getCustomEditor

	/**
	 * Determines whether this property editor supports a custom editor.
	 * @return  True if the propertyEditor can provide a custom editor.
	 */
	public boolean supportsCustomEditor()
	{
		return true;
	}   //  supportsCustomEditor

	/**
	 * Register a listener for the PropertyChange event.  When a
	 * PropertyEditor changes its value it should fire a PropertyChange
	 * event on all registered PropertyChangeListeners, specifying the
	 * null value for the property name and itself as the source.
	 *
	 * @param listener  An object to be invoked when a PropertyChange
	 *		event is fired.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		super.addPropertyChangeListener(listener);
	}   //  addPropertyChangeListener

	/**
	 * Remove a listener for the PropertyChange event.
	 * @param listener  The PropertyChange listener to be removed.
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		super.removePropertyChangeListener(listener);
	}   //  removePropertyChangeListener


	public boolean isCancelled() {
		return m_isCancelled;
	}


	public int getAD_Color_ID() {
		return m_AD_Color_ID;
	}

	/**
	 *  Get ColorName
	 *  @return Color Name
	 */
	public String getColorName()
	{
		return colorNameField.getDisplay();
	}   //  getColor

	/**
	 *  Get Color
	 *  @return Color, when saved - else null
	 */
	public void setColorName(String name)
	{
		colorNameField.setValue(name);
	}   //  getColor

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}   //  VColorEditor
