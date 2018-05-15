package org.compiere.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.NamePair;

import com.mckayerp.model.MCTDataSetInstance;

public class MDataPointLookup extends Lookup implements Serializable {

	/**
	 * 	Constructor
	 * 	@param ctx context - ignored
	 *	@param WindowNo window no
	 */
	public MDataPointLookup(Properties ctx, int WindowNo)
	{
		//  ctx ignored
		super(DisplayType.TableDir, WindowNo);
	}	//	MPAttribute

	public MDataPointLookup(int displayType, int windowNo) {
		super(displayType, windowNo);
	}

	/**	No Instance Value			*/
	private static KeyNamePair	NO_INSTANCE = new KeyNamePair (0,"");

	@Override
	public String getDisplay(Object value) {

		if (value == null)
			return "Not Collected. Click to record.";  // TODO translate
		NamePair pp = get (value);
		if (pp == null)
			return "<" + value.toString() + ">";
		return pp.getName();

	}

	@Override
	public NamePair get(Object value) {

		if (value == null)
			return null;
		
		int CT_DataSetInstance_ID = 0;
		if (value instanceof Integer)
			CT_DataSetInstance_ID = ((Integer)value).intValue();
		else
		{
			try
			{
				CT_DataSetInstance_ID = Integer.parseInt(value.toString());
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Value=" + value, e);
			}
		}
		if (CT_DataSetInstance_ID == 0)
			return NO_INSTANCE;
		//
		String Description = null;
		
		String where = MCTDataSetInstance.COLUMNNAME_CT_DataSetInstance_ID + "=?";
		MCTDataSetInstance dataPoint = new Query(Env.getCtx(), MCTDataSetInstance.Table_Name, where, null)
											.setClient_ID()
											.setOnlyActiveRecords(true)
											.setParameters(CT_DataSetInstance_ID)
											.firstOnly();

		if (dataPoint != null)
		{
			Description = dataPoint.getDescription();
		}
		else
		{
			Description = "Not Captured. Click to capture.";
		}
		if (Description == null)
			return null;
		return new KeyNamePair (CT_DataSetInstance_ID, Description);
	}

	@Override
	public ArrayList<Object> getData(boolean mandatory, boolean onlyValidated,
			boolean onlyActive, boolean temporary) {
		log.log(Level.SEVERE, "Not implemented");
		return null;
	}

	@Override
	public String getColumnName() {
		return "CT_DataSetInstance_ID";
	}

	@Override
	public boolean containsKey(Object key) {
		return get(key) != null;
	}

}
