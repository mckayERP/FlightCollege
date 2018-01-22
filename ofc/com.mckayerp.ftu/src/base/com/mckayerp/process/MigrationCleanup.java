package com.mckayerp.process;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MColumn;
import org.compiere.model.MMigration;
import org.compiere.model.MMigrationData;
import org.compiere.model.MMigrationStep;
import org.compiere.model.MSequence;
import org.compiere.model.MTable;
import org.compiere.process.ProcessInfo;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.service.dsl.ProcessBuilder;

public class MigrationCleanup extends SvrProcess {

	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (LoadAirworthinessDirectives.class);
	
	int ad_migration_id = 0;

	public MigrationCleanup() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		ad_migration_id = this.getParameterAsInt("AD_Migration_ID");
	}

	@Override
	protected String doIt() throws Exception {
		
		MMigration migration = new MMigration(getCtx(), ad_migration_id, get_TrxName());
		
		if (migration == null)
		{
			log.info("Migration not found");
			return "Migration not found";
		}
		
		log.info("Found " + migration.toString());
		
		int[] migrationStepIds = migration.getStepIds(true, false);
		
		log.info("Found " + migrationStepIds.length + " steps.");
		
		int record_id = 0;
		int newRecord_id = 0;
		int table_id = 0;
		MTable table = null;
		int ad_column_id = 0;
		String columnName = "";
		List<MMigrationData> dataRecs = null;
		
		for (int i=0; i < migrationStepIds.length; i++)
		{
			MMigrationStep step = new MMigrationStep(getCtx(), migrationStepIds[i], get_TrxName());
			log.info(step.getSeqNo() + " " + step.getAction());
			if (step.getAction().equals(MMigrationStep.ACTION_Insert))
			{
				record_id = step.getRecord_ID();
				if (record_id < 1000000)
					continue;
				
				table_id = step.getAD_Table_ID();
				table = MTable.get(getCtx(), table_id);
				if (table.get_TableName().endsWith("_Trl"))
					continue;
				
				columnName  = table.getKeyColumns()[0];
				ad_column_id = MColumn.getColumn_ID(table.getTableName(), columnName);
				
				
				//newRecord_id = 53513;
				newRecord_id = MSequence.getNextOfficialID_HTTP(table.getTableName());
				
				if (newRecord_id == 0)
				{
					log.severe("Unable to create new ID!!");
					break;
				}
				
				step.setRecord_ID(newRecord_id);
				step.saveEx();
				
				log.info("    Step " + step.getSeqNo() + " Table " + table.getTableName() + ", Column " + columnName + " (Column ID " + ad_column_id + "), Record " + record_id 
						+	" replaced by " + newRecord_id);

				if (step.getStepType().equals(MMigrationStep.STEPTYPE_ApplicationDictionary))
				{
					dataRecs = step.getData();
					for (MMigrationData data : dataRecs)
					{
						if (!data.isNewNull() && data.getNewValue() != null && data.getNewValue().equals(String.valueOf(record_id)))
						{
							if (data.getAD_Column().getColumnName().equals(columnName))
							{
								// Replace the entry with the new ID.
								data.set_TrxName(get_TrxName());
								String oldValue = data.getNewValue();
								data.setNewValue(String.valueOf(newRecord_id));
								data.saveEx();
								log.info("       Step " + step.getSeqNo() + " data uses record_id: " + data.getAD_Column().getColumnName() + " " + oldValue
										+ " Replaced with " + newRecord_id);
							}
							else
							{
								log.info("       Step " + step.getSeqNo() + " data uses record_id: " + data.getAD_Column().getColumnName() + " " + data.getNewValue()
										+ " Not Replaced!");
								
							}
						}
					}
				}

				for (int j = i+1; j < migrationStepIds.length; j++)
				{
					MMigrationStep refStep = new MMigrationStep(getCtx(), migrationStepIds[j], get_TrxName());
					
					if ((refStep.getAD_Table_ID() == table_id 
							|| refStep.getAD_Table().getTableName().endsWith("_Trl") )
						&& refStep.getRecord_ID() == record_id	)
					{
						table = (MTable) refStep.getAD_Table();
						ad_column_id = MColumn.getColumn_ID(table.getTableName(), table.getKeyColumns()[0]);
						refStep.setRecord_ID(newRecord_id);
						refStep.saveEx();
						
						log.info("    Step " + refStep.getSeqNo() + " Table " + refStep.getAD_Table().getTableName() + ", Record " + record_id 
								+	" replaced by " + newRecord_id);

					}
					
					if (refStep.getStepType().equals(MMigrationStep.STEPTYPE_ApplicationDictionary))
					{
						dataRecs = refStep.getData();
						for (MMigrationData data : dataRecs)
						{
							if (!data.isNewNull() && data.getNewValue() != null && data.getNewValue().equals(String.valueOf(record_id)))
							{
								if (data.getAD_Column().getColumnName().equals(columnName))
								{
									// Replace the entry with the new ID.
									data.set_TrxName(get_TrxName());
									String oldValue = data.getNewValue();
									data.setNewValue(String.valueOf(newRecord_id));
									data.saveEx();
									log.info("       Step " + refStep.getSeqNo() + " data uses record_id: " + data.getAD_Column().getColumnName() + " " + oldValue
											+ " Replaced with " + newRecord_id);
								}
								else
								{
									log.info("       Step " + refStep.getSeqNo() + " data uses record_id: " + data.getAD_Column().getColumnName() + " " + data.getNewValue()
											+ " Not Replaced!");
									
								}
							}
						}
					}
				}

			}
			
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.INFO);
		
		if (! DB.isConnected()) 
		{
			
			log.info("No DB Connection");
			System.exit(1);
			
		}

		Properties context = Env.getCtx();		

		try 
		{
			
			//Import Airworthiness Directives
			ProcessInfo processInfo = ProcessBuilder.create(context)
			.process(com.mckayerp.process.MigrationCleanup.class)
			.withTitle("Migration Cleanup")
			.withParameter("AD_Migration_ID", 1000214)
			.execute();

			log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());

		} 
		catch (AdempiereException e) 
		{
			e.printStackTrace();
		}
	}

}
