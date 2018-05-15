package com.mckayerp.ftu.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.Callout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

import com.mckayerp.model.MCTComponent;
import com.mckayerp.model.MCTComponentData;
import com.mckayerp.model.MCTDataSet;

public class CalloutCaptureData extends CalloutEngine {

	private List<Integer> m_dataSets;
	private int m_dataSet_id;
	private List<Integer> m_bomLines;

	public String captureData (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		
		return "";
	}		
	
}
