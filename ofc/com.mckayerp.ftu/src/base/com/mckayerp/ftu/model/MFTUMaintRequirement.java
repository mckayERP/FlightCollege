package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class MFTUMaintRequirement extends X_FTU_MaintRequirement {

	public MFTUMaintRequirement(Properties ctx, int FTU_MaintRequirement_ID,
			String trxName) {
		super(ctx, FTU_MaintRequirement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MFTUMaintRequirement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static List<MFTUMaintRequirement> getByFTU_DefectLog(Properties ctx,
			int ftu_defectLog_id, String trxName) {
		
		String where = MFTUMaintRequirement.COLUMNNAME_FTU_DefectLog_ID + "=?";
		
		return new Query(ctx, MFTUMaintRequirement.Table_Name, where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(ftu_defectLog_id)
					.list();
	}

	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		/** Prevents saving
		log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
		log.saveError("FillMandatory", Msg.getElement(getCtx(), "PriceEntered"));
		/** Issues message
		log.saveWarning(AD_Message, message);
		log.saveInfo (AD_Message, message);
		**/

		// Convert the usage and intervals entered to standard units
		// Use hours for usage and days for time

		MUOM hourUOM = MUOM.get(getCtx(), "Hour", get_TrxName());
		MUOM dayUOM = MUOM.get(getCtx(), "Day", get_TrxName());
		
		if( this.getFTU_TimeIntervalUOM_ID() == 0)
			this.setFTU_TimeIntervalUOM_ID(dayUOM.getC_UOM_ID());
		
		if( this.getFTU_UsageIntervalUOM_ID() == 0)
			this.setFTU_UsageIntervalUOM_ID(hourUOM.getC_UOM_ID());

		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageInterval_Entered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageIntervalUOM_ID))
		{
			if (this.getFTU_UsageInterval_Entered() == null)
			{
				this.setFTU_UsageInterval(null);					
			}
			else if( this.getFTU_UsageInterval_Entered().equals(Env.ZERO) )
			{
				this.setFTU_UsageInterval(Env.ZERO);
			}
			else
			{
				
				BigDecimal conversion = MUOMConversion.convert(getCtx(), 
											this.getFTU_UsageIntervalUOM_ID(), 
											hourUOM.getC_UOM_ID(), this.getFTU_UsageInterval_Entered());
				
				this.setFTU_UsageInterval(conversion);
				
			}
		}

		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_TimeInterval_Entered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_TimeIntervalUOM_ID))
		{
			if (this.getFTU_TimeInterval_Entered() == null)
			{
				this.setFTU_TimeInterval(null);					
			}
			else if( this.getFTU_TimeInterval_Entered().equals(Env.ZERO) )
			{
				this.setFTU_TimeInterval(Env.ZERO);
			}
			else
			{
				
				BigDecimal conversion = MUOMConversion.convert(getCtx(), 
											this.getFTU_TimeIntervalUOM_ID(), 
											dayUOM.getC_UOM_ID(), this.getFTU_TimeInterval_Entered());
				
				this.setFTU_TimeInterval(conversion);
				
			}
		}

		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageIntervalTol_Entered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_UsageIntervalUOM_ID))
		{
			if (this.getFTU_UsageIntervalTol_Entered() == null)
			{
				this.setFTU_UsageIntervalTol(null);					
			}
			else if( this.getFTU_UsageIntervalTol_Entered().equals(Env.ZERO) )
			{
				this.setFTU_UsageIntervalTol(Env.ZERO);
			}
			else
			{
				
				BigDecimal conversion = MUOMConversion.convert(getCtx(), 
											this.getFTU_UsageIntervalUOM_ID(), 
											hourUOM.getC_UOM_ID(), this.getFTU_UsageIntervalTol_Entered());
				
				this.setFTU_UsageIntervalTol(conversion);
				
			}
		}

		if( newRecord 
				|| this.is_ValueChanged(COLUMNNAME_FTU_TimeIntervalTol_Entered)
				|| this.is_ValueChanged(COLUMNNAME_FTU_TimeIntervalUOM_ID))
		{
			if (this.getFTU_TimeIntervalTol_Entered() == null)
			{
				this.setFTU_TimeIntervalTol(null);					
			}
			else if( this.getFTU_TimeIntervalTol_Entered().equals(Env.ZERO) )
			{
				this.setFTU_TimeIntervalTol(Env.ZERO);
			}
			else
			{
				
				BigDecimal conversion = MUOMConversion.convert(getCtx(), 
											this.getFTU_TimeIntervalUOM_ID(), 
											dayUOM.getC_UOM_ID(), this.getFTU_TimeIntervalTol_Entered());
				
				this.setFTU_TimeIntervalTol(conversion);
				
			}
		}

		return true;
	}	//	beforeSave

}
