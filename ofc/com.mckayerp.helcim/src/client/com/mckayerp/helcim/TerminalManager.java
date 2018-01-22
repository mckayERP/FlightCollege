package com.mckayerp.helcim;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.apps.form.VFactReconcile;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextArea;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

import com.helcim.helcim_semi_integrated.HCMPaymentProcessor;

public class TerminalManager extends CPanel
	implements FormPanel, ActionListener, PropertyChangeListener {
	
	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;

	/** Client ID               */
	private int             m_AD_Client_ID = 0;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(TerminalManager.class);

	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel centerPanel = new CPanel();
	private CardLayout centerLayout = new CardLayout();

	private CPanel kPanel = new CPanel();
	private CTextArea terminalLog = new CTextArea();
	
	private CButton kSettle = new CButton();
	private HCMPaymentProcessor m_pp;
	private MPaymentProcessor m_mpp;
	
	private CPanel commandPanel = new CPanel();
	private FlowLayout commandLayout = new FlowLayout();
	private JButton bCancel = ConfirmPanel.createCancelButton(true);

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ((e.getSource() == kSettle))
		{
			terminalLog.setText("");
			m_pp.settle();
		}
		else if (e.getSource() == bCancel)
		{
			dispose();
		}

	}

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	@Override
	public void init (int WindowNo, FormFrame frame)
	{
		log.info("");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try
		{

			jbInit();
			dynInit();
			
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	private void jbInit() {
				
		terminalLog.setPreferredSize(new Dimension(300,250));

		// CC Buttons
		kSettle.setText(Msg.getMsg(Env.getCtx(), "Settle"));  //TODO Add message
		kSettle.addActionListener(this);
		
		kPanel.add(kSettle);

		bCancel.addActionListener(this);
		commandPanel.setLayout(commandLayout);
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandPanel.add(bCancel, null);

		mainPanel.setLayout(mainLayout);
		mainPanel.add(terminalLog, BorderLayout.CENTER);
		mainPanel.add(kPanel, BorderLayout.SOUTH);

		m_frame.getContentPane().add(commandPanel, BorderLayout.SOUTH);
		m_frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
	}

	private void dynInit() {
		
		m_mpp = MPaymentProcessor.getByClass(Env.getCtx(), HCMPaymentProcessor.class.getName(), null);
		m_mpp.addPropertyChangeListener(this);
		m_pp = (HCMPaymentProcessor) HCMPaymentProcessor.create(m_mpp, null);
		
	}

	@Override
	public void dispose() {
		
		m_mpp.removePropertyChangeListener(this);
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (terminalLog.getText() == null)
			terminalLog.setText("");
		
		if (evt.getPropertyName().equals("terminalLog"))
		{
			if (terminalLog.getText() == null)
				terminalLog.setText("");
			terminalLog.setValue(terminalLog.getText() + (String) evt.getNewValue());
		}
		else if (evt.getPropertyName().equals("OnlineProcessCompleted"))
		{	
			terminalLog.setValue(terminalLog.getText() + "\nProcess Completed");				
		}
	}
}
