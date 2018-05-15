package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JLabel;

import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VDataPointDialog;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.mckayerp.ftu.model.MFTUAircraft;
import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentBOMLine;
import com.mckayerp.model.MCTDataSet;

public class VDataPointCollector extends CPanel implements FormPanel, ActionListener, WindowListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7968930292221113203L;
	
	private FormFrame	m_formFrame = null;
	private int m_WindowNo;
	private int m_ftu_aircraft_id = 0;
	private int m_ct_dataset_id = 0;
	private int m_ct_compBOMLine_id;
	private List<Integer> m_dataSets;
	private static List<Integer> m_bomLines;
	private int m_ct_component_id;
	private BigDecimal m_compLifeAtAction;
	private int m_root_component_id;
	private BigDecimal m_rootLifeAtAction;
	private int m_overhaulCycle;

	private static String m_sqlFrom = " FROM CT_ComponentData cd"
			+ " JOIN CT_CompLifeCycleModel lcm ON (lcm.CT_CompLifeCycleModel_ID = cd.CT_CompLifeCycleModel_ID)"
			+ " JOIN CT_Component c ON (c.CT_CompLifeCycleModel_ID = lcm.CT_CompLifeCycleModel_ID)"
			+ " JOIN CT_ComponentBOMLine bl ON (bl.CT_Component_ID = c.CT_Component_ID)"
			+ "	JOIN FTU_Aircraft ac ON (ac.CT_Component_ID = c.Root_Component_ID)";

	private static String m_sqlWhere = " WHERE cd.IsActive='Y' AND lcm.IsActive='Y' AND c.IsActive = 'Y' and bl.IsActive='Y'";
	
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VDataPointCollector.class);

	/**
	 * Data Point collector form
	 */
	public VDataPointCollector() {
		super();
	}
	
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CComboBox datasetCombo = new CComboBox();
	private JLabel datasetLabel = new JLabel();
	private CComboBox bomlineCombo = new CComboBox();
	private JLabel bomlineLabel = new JLabel();

	private CPanel centerPanel = new CPanel();
	private FlowLayout centerLayout = new FlowLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);

	private boolean m_dispose = false;

	private boolean m_initialized = false;

	private boolean dynInit() {
				
		boolean success = getDataSets(m_ftu_aircraft_id);
		
		success = fillBOMLineCombo(m_root_component_id, m_ct_dataset_id, success);

		return success;
		
	}

	private void jbInit() {
		
		try
		{
			// Components
			datasetLabel.setText(Msg.translate(Env.getCtx(), "CT_DataSet_ID"));
			datasetCombo.addActionListener(this);
			bomlineLabel.setText(Msg.translate(Env.getCtx(), "CT_Component_ID"));
			bomlineCombo.addActionListener(this);
			
			// Confirm panel
			confirmPanel.addActionListener(this);

			centerPanel.setLayout(centerLayout);
			centerLayout.setAlignment(FlowLayout.RIGHT);
			centerPanel.add(datasetLabel, new ALayoutConstraint(0,0));
			centerPanel.add(datasetCombo, null);
			centerPanel.add(bomlineLabel, new ALayoutConstraint(1,0));
			centerPanel.add(bomlineCombo, null);
			
			mainPanel.setLayout(mainLayout);
			mainPanel.add(centerPanel, BorderLayout.CENTER);
			mainPanel.add(confirmPanel, BorderLayout.SOUTH);

			m_formFrame.getContentPane().add(mainPanel);
			if (m_formFrame.getCFrame() != null)
			{
				m_formFrame.getCFrame().addWindowListener(this);
			}
			else if (m_formFrame.getCDialog() != null)
			{
				m_formFrame.getCDialog().addWindowListener(this);
			}			
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (!m_initialized)
			return;
		
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (collectData())
			{
				dispose();
				return;
			}
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
			return;
		}
		else if (e.getSource() == datasetCombo)
		{	
			// Set the dataset. The result should always be > 0
			m_ct_dataset_id = m_dataSets.get(datasetCombo.getSelectedIndex());
			if (!fillBOMLineCombo(m_root_component_id, m_ct_dataset_id, true))
			{
				dispose();
				return;
			}	
		}
		else if (e.getSource() == bomlineCombo)
		{	
			// Set the component. The result should always be > 0
			m_ct_compBOMLine_id  = m_bomLines.get(bomlineCombo.getSelectedIndex());

			MCTComponentBOMLine bl = new MCTComponentBOMLine(Env.getCtx(), m_ct_compBOMLine_id, null);
			m_ct_component_id = bl.getCT_Component_ID();
//			m_root_component_id = bl.getCT_Component().getRoot_Component_ID();  // Should be the same as the A/C component
			m_compLifeAtAction = bl.getCT_Component().getLifeUsed();
			m_overhaulCycle = bl.getCT_Component().getOverhaulCount();
			
			// It would be possible to just open the second dialog at this point, but that would not be good behaviour
			// for the UI.  Once the initial dialog has been opened, the user should click confirm to proceed.

		}
		
		enableComponents();

	}

	private void enableComponents() {
		
		if (m_ct_dataset_id <= 0)
		{
			datasetLabel.setVisible(true);
			datasetCombo.setVisible(true);
			bomlineLabel.setVisible(false);
			bomlineCombo.setVisible(false);
		}
		else
		{
			datasetLabel.setVisible(true);
			datasetCombo.setVisible(true);
			bomlineLabel.setVisible(true);
			bomlineCombo.setVisible(true);
		}
	}

	private boolean getDataSets(int ftu_aircraft_id) {
		
		if (m_ct_dataset_id > 0)
			return true;
		
		String sql = " SELECT cd.ct_dataset_id"
				+ m_sqlFrom
				+ m_sqlWhere + " AND ac.FTU_Aircraft_ID = ?"
				+ " GROUP BY cd.ct_dataset_id";
	
		List<Integer> ids = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, ftu_aircraft_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs,  pstmt);
		}
		
		m_dataSets = ids;
	
		if (m_dataSets == null || m_dataSets.size() == 0)
		{
			ADialog.error(m_WindowNo, this, "DataPointCollectorNoDataSet");
			return false;
		}
		
		if (m_dataSets.size() > 0)
		{
			int index = 0;

			// Add datasets to the datasetCombo
			for (int i = 0; i < m_dataSets.size(); i++)
			{
				
				MCTDataSet ds = new MCTDataSet(Env.getCtx(), m_dataSets.get(i), null); 				
				datasetCombo.addItem(ds.getName());
			}
			datasetCombo.setSelectedIndex(0);
			
			if (m_dataSets.size() == 1)
			{
				// Just use it.
				m_ct_dataset_id  = m_dataSets.get(0);
				if (m_ct_dataset_id <= 0)
				{
					log.severe("Query returned ct_dataset_id <= 0!");
					ADialog.error(m_WindowNo, this, "DataPointCollectorNoDataSet");
					return false;
				}			
			}
		}

		return true;
		
	}
		
	private boolean fillBOMLineCombo(int root_component_id, int ct_dataset_id, boolean success) {
		
		if (!success)
			return success;
		
		if (root_component_id <= 0 || ct_dataset_id <= 0)
			return success;  // Not an error - just not ready to find the bomline.
		
		m_bomLines = MCTComponentBOMLine.getLinesByRootAndDataSet(root_component_id, ct_dataset_id);
			
		if (m_bomLines.size() == 0)
		{
			// This shouldn't happen.  The dataset query linked to the bomline table so 
			// something should have been found.  IF this error occurs, there is a 
			// problem with the data.
			log.severe("Query couldn't find a bomline for dataset: " + ct_dataset_id);
			ADialog.error(m_WindowNo, this, "DataPointCollectorNoBPOMLine");
			return false;
		}
		
		if (m_bomLines.size() > 0)
		{

			// Add datasets to the datasetCombo
			for (int i = 0; i < m_bomLines.size(); i++)
			{
				
				MCTComponentBOMLine bl = new MCTComponentBOMLine(Env.getCtx(), m_bomLines.get(i), null);
				if (bl.getCT_Component_ID() <= 0)
				{
					// Shouldn't happen.  Same reason as above.
					log.severe("Query returned Component BOM Line with no component installed!");
					ADialog.error(m_WindowNo, this, "DataPointCollectorNoComponent");
					return false;
				}
				
				MCTComponent ct = (MCTComponent) bl.getCT_Component();				
				String pName = ct.getM_Product().getValue();
				String asiDesc = "";
				
				if (ct.getM_AttributeSetInstance_ID() > 0)
					asiDesc = ct.getM_AttributeSetInstance().getDescription();
				
				bomlineCombo.addItem(bl.getValue() + ": " + pName + " " + asiDesc);
				
			}
			bomlineCombo.setSelectedIndex(0);
			
			if (m_bomLines.size() == 1)
			{
				// Just use it.
				m_ct_compBOMLine_id  = m_bomLines.get(0);
				if (m_ct_compBOMLine_id <= 0)
				{
					// Shouldn't happen.  Same reason as above.
					log.severe("Query returned CT_CompBOMLine_ID <= 0!");
					ADialog.error(m_WindowNo, this, "DataPointCollectorNoBOMLine");
					return false;
				}
				MCTComponentBOMLine bl = new MCTComponentBOMLine(Env.getCtx(), m_ct_compBOMLine_id, null);
				m_ct_component_id = bl.getCT_Component_ID();
				m_root_component_id = bl.getCT_Component().getRoot_Component_ID();  // Should be the same as the A/C component
				m_compLifeAtAction = bl.getCT_Component().getLifeUsed();
				m_overhaulCycle = bl.getCT_Component().getOverhaulCount();
				
			}
		}

		return true;
	}

	private boolean collectData()
	{
		
		if (m_ct_dataset_id <= 0 || m_ct_component_id <= 0 || m_root_component_id <=0)
			return false;
		
		this.setVisible(false);  // Hide this dialog - it will be closed anyway. This just makes it look cleaner.

		// Open the data point dialog. The datasetinstance id is zero to create a new 
		// datapoint.  The column id is also zero - which will set the CT_DataSetInstance_ID
		// value in the context even though the field may not exist in the window.
		VDataPointDialog vdpd = new VDataPointDialog (this, 
				0, m_ct_dataset_id, m_ct_component_id, m_root_component_id, m_compLifeAtAction, m_rootLifeAtAction, m_overhaulCycle,
				new Timestamp(System.currentTimeMillis()), 0, m_WindowNo, true);
		
		if (vdpd.getCT_DataSetInstance_ID() > 0)
		{
			ADialog.info(m_WindowNo, this, "DataPointCollectorSuccess");
		}
		return true;
	}



	@Override
	public void init(int WindowNo, FormFrame frame) {
		
		log.config("");
		m_WindowNo = WindowNo;
		m_formFrame = frame;
		
		frame.setTitle("Data Point Collector");

		if (m_formFrame.getProcessInfo().getRecord_ID() <= 0)
			throw new IllegalArgumentException("ftu_aircraft_id must be > 0");
		
		
		m_ftu_aircraft_id = m_formFrame.getProcessInfo().getRecord_ID();
		MFTUAircraft ac = new MFTUAircraft(Env.getCtx(), m_ftu_aircraft_id, null);
		m_root_component_id = ac.getCT_Component_ID();
		m_rootLifeAtAction = ac.getAirframeTime();
		
		//
		jbInit();

		//
		if (!dynInit())
		{
			m_dispose  = true;
			return;
		}
		
		m_initialized  = true;
	}



	@Override
	public void dispose() {
		
		if (m_formFrame != null)
		{
			m_formFrame.dispose();
			m_formFrame = null;
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {

		log.fine(e.toString());
		if (e.getSource() instanceof CDialog)
		{
			// Try optimistically. It will return if we're not ready.
			collectData();
			
			if (m_dispose) // Flagged if there was an error during initialization.
			{
				dispose();
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log.fine(e.toString());
		
		// If the dialog closes, also close this.
		if (e.getSource() instanceof VDataPointDialog)
			dispose();
						
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Frame getFrame() {
		
		if (m_formFrame != null)
		{
			if (m_formFrame.getCFrame() != null)
				return m_formFrame.getCFrame();
			else if (m_formFrame.getCDialog() != null)
				return (Frame) m_formFrame.getCDialog().getOwner();
		}
		return null;
	}
}
