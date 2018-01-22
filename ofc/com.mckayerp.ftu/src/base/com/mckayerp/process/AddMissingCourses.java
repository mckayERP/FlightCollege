package com.mckayerp.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * Add missing course instances to the OFC_Course_Inst table.
 * @author Michael McKay
 * @version 0
 */
public class AddMissingCourses extends SvrProcess
	{
		protected void prepare()
		{
		}	//	prepare

		/**
		 *  Perform process.
		 *  @return Message (clear text)
		 *  @throws Exception if not successful
		 */
		@SuppressWarnings("deprecation")
		protected String doIt() throws Exception
		{
			log.info("Adding missing course instances.");
			String sql = "SELECT ofc_add_missing_course_inst()";
			int noLine = DB.executeUpdate (sql);
			return "Added missing course instances.  Mark any unneccesary instances as inactive.";
		}	//	doIt

	}	//	ofcAddMissingCourses

