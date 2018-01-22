package com.mckayerp.swing.apps.search;

import java.awt.Frame;

import org.compiere.apps.AEnv;
import org.compiere.apps.search.Info;
import org.compiere.apps.search.InfoAssignment;
import org.compiere.apps.search.InfoFactory;
import org.compiere.apps.search.InfoGeneral;

public class ComponentInfoFactory implements InfoFactory {

	public ComponentInfoFactory() {
	}


	@Override
	@Deprecated
	public Info create(Frame frame, boolean modal, int WindowNo,
			String tableName, String keyColumn, String value,
			boolean multiSelection, String whereClause) {

		return create (frame, modal, WindowNo,
				tableName, keyColumn, 0, value,
				multiSelection, true, whereClause);
	}

	@Override
	@Deprecated
	public Info create(Frame frame, boolean modal, int WindowNo,
			String tableName, String keyColumn, int record_id, String value,
			boolean multiSelection, String whereClause) {
		
		return create (frame, modal, WindowNo,
				tableName, keyColumn, record_id, value,
				multiSelection, true, whereClause);
	}

	@Override
	public Info create(Frame frame, boolean modal, int windowNo,
			String tableName, String keyColumn, int record_id, String value,
			boolean multiSelection, boolean saveResult, String whereClause) {
		
		Info info = null;

		if (tableName.equals("CT_Component"))
			info = new InfoComponent (frame, modal, windowNo, record_id, value,
					multiSelection, saveResult, whereClause);
		else
			info = Info.create(frame, modal, windowNo, tableName, 
					keyColumn, record_id, value, multiSelection, 
					saveResult, whereClause);
		//
		AEnv.positionCenterWindow(frame, info);
		return info;
	}

}
