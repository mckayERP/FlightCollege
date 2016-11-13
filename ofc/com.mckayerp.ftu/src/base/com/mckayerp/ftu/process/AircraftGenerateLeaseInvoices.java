package com.mckayerp.ftu.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.service.dsl.ProcessBuilder;

import com.mckayerp.ftu.model.MFTUACJourneyLog;
import com.mckayerp.ftu.model.MFTUAircraft;
import com.mckayerp.ftu.model.MFTUFlightsheet;

public class AircraftGenerateLeaseInvoices extends SvrProcess {
	
	private int ftu_aircraft_id;
	private int c_period_id;
	private Timestamp startDate;
	private Timestamp endDate;
	
	private long oneDay = 24*60*60*1000;  // One day in milliseconds
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private MPeriod period;

	@Override
	protected void prepare() {
		ftu_aircraft_id = getParameterAsInt("FTU_Aircraft_ID");
		c_period_id = getParameterAsInt("C_Period_ID");
		
	} // Prepare

	@Override
	protected String doIt() throws Exception {
		
		
		
		if (c_period_id == 0)
		{
			
			return ("@Error@ @FillMandatory@ @C_Period_ID@");
		}
		period =  MPeriod.get(getCtx(), c_period_id);
		startDate = period.getStartDate();
		endDate = period.getEndDate();
		
		// Scrape the flight sheets over the period and sync the flight log
		// Updates the journey log flight hours as well.
		ProcessBuilder.create(getCtx())
		.process(com.mckayerp.ftu.process.LoadFlightsheetFromXML.class)
		.withTitle("Update flightsheets")
		.withParameterRange("FlightDate", startDate, endDate)
		.execute();


		StringBuffer where = new StringBuffer("IsACLeased='Y'");
		if (ftu_aircraft_id > 0)
		{
			
			where.append(" AND FTU_Aircraft_ID=").append(ftu_aircraft_id);
		}
		List<MFTUAircraft> fleet = new Query(getCtx(), MFTUAircraft.Table_Name, where.toString(), get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.list();

		// Only leased aircraft
		for (MFTUAircraft ac : fleet)
		{
			if (ac.getDateExpiryLease() == null)
			{
				addLog("@Error@ " + ac.getACRegistration() + " has no lease expiry date." );
				continue;
			}
			
			if (ac.getDateExpiryLease().before(startDate))
			{
				
				addLog(ac.getACRegistration() + " Lease expired on " + ac.getDateExpiryLease());
				continue;
			}
			
			switch (ac.getLease_QtyType())
			{
			
				case MFTUAircraft.LEASE_QTYTYPE_MonthlyFixed: 		
					generateMonthlyFixedLease(ac);
					break;
					
				case MFTUAircraft.LEASE_QTYTYPE_AirtimeInPeriod: 				
					generateAirTimeInPeriodLease(ac);
					break;
					
				case MFTUAircraft.LEASE_QTYTYPE_FlightTimeInPeriod: 
					generateFlightTimeInPeriodLease(ac);
					break;
	
				case MFTUAircraft.LEASE_QTYTYPE_FMFL3_TiereLeaseTerms: 
					generateFMFLLease(ac);
					break;
	
				default:
					addLog("@Error@ Unknown lease type for aircraft: " + ac.getACRegistration() + " lease type:" + ac.getLease_QtyType());
					break;
			}
		}
		return "Aircraft leases generated.";
	}

	private void generateFMFLLease(MFTUAircraft ac) {
		
		// Pure calculation - no history of last invoice
				
		BigDecimal rollOverLimitToStartOfPeriod;
		
		BigDecimal rollOverHoursToApplyInThisPeriod = Env.ZERO;
		BigDecimal rollOverApplied = Env.ZERO;
		
		// Pro rate limits on first month
		MPeriod firstPeriod = MPeriod.get(getCtx(), ac.getDateStartLease(), ac.getAD_Org_ID());
		Timestamp firstEndDate = firstPeriod.getEndDate();
		Timestamp leaseStartDate = ac.getDateStartLease();
		
		ZonedDateTime end = ZonedDateTime.ofInstant(firstEndDate.toInstant(), ZoneId.of("UTC"));
		ZonedDateTime start = ZonedDateTime.ofInstant(leaseStartDate.toInstant(), ZoneId.of("UTC"));
		int days = ((int) Duration.between(start, end).toDays()) + 1;  // make it inclusive
		
		// The first month minimum is 1 hour a day, so start by adding the days.
		rollOverLimitToStartOfPeriod = new BigDecimal(days);
		
		// If this is the first month, pro rate the monthly minimum
		BigDecimal monthlyMinimum = ac.getLeaseMinMonthlyHours();
		if (firstPeriod.getC_Period_ID() == c_period_id)
		{
			monthlyMinimum = new BigDecimal(days);
		}
		
		// Count the number of months/periods between the first month and the period in question.
		String where = MPeriod.COLUMNNAME_StartDate + " BETWEEN " 
				+ DB.TO_DATE(firstPeriod.getEndDate()) + " AND "
				+ DB.TO_DATE(new Timestamp(startDate.getTime() - oneDay));
		
		int numMonths = new Query(getCtx(), MPeriod.Table_Name, where, get_TrxName())
							.count();
		
		// The minimum is say, 20 hrs a month.  But we need to apply tier1 rates to the 30 hours 
		// a month.
		rollOverLimitToStartOfPeriod = rollOverLimitToStartOfPeriod
				.add(new BigDecimal(numMonths).multiply(ac.getLeaseRollOverHours()));
		
		// Get the number of lease hours billed at the tier 1 rate
		String sql = "SELECT SUM(il.qtyInvoiced) FROM "
				+ " C_InvoiceLine il "
				+ " JOIN C_Invoice i ON (i.C_Invoice_ID = il.C_Invoice_ID)"
				+ " WHERE i.DocStatus IN ('CO','CL') "
				+ " AND i.dateInvoiced BETWEEN "
				+ DB.TO_DATE(ac.getDateStartLease()) + " AND " + DB.TO_DATE(new Timestamp(startDate.getTime() - oneDay)) 
				+ " AND i.isSOTrx = 'N'"
				+ " AND il.M_Product_ID="
				+ ac.getLeaseTier1ProductID();
		
		BigDecimal tier1HoursBilled = DB.getSQLValueBD(get_TrxName(), sql);
		if (tier1HoursBilled == null) // nothing billed
		{
			
			tier1HoursBilled = Env.ZERO;
			
		}
		
		if (tier1HoursBilled.compareTo(rollOverLimitToStartOfPeriod) < 0)
		{
			
			rollOverHoursToApplyInThisPeriod = rollOverLimitToStartOfPeriod.subtract(tier1HoursBilled);
			
		}
		
		sql = "SELECT SUM(il.qtyInvoiced) FROM "
				+ " C_InvoiceLine il "
				+ " JOIN C_Invoice i ON (i.C_Invoice_ID = il.C_Invoice_ID)"
				+ " WHERE i.DocStatus IN ('CO','CL') "
				+ " AND i.dateInvoiced BETWEEN "
				+ DB.TO_DATE(ac.getDateStartLease()) + " AND " + DB.TO_DATE(new Timestamp(startDate.getTime() - oneDay)) 
				+ " AND i.isSOTrx = 'N'"
				+ " AND il.M_Product_ID IN ("
				+ ac.getLeaseTier1ProductID() + ", "
				+ ac.getLeaseTier2ProductID() + ", "
				+ ac.getLeaseTier3ProductID() + ")";
		
		BigDecimal totalHoursBilled = DB.getSQLValueBD(get_TrxName(), sql);
		if (totalHoursBilled == null) // nothing billed
		{
			
			totalHoursBilled = Env.ZERO;
			
		}
		
		if (totalHoursBilled.compareTo(rollOverLimitToStartOfPeriod) < 0)
		{
			
			rollOverHoursToApplyInThisPeriod = rollOverLimitToStartOfPeriod.subtract(totalHoursBilled);
			
		}
		
		 where = MProduct.COLUMNNAME_S_Resource_ID + "=" + ac.getS_Resource_ID();
		 MProduct acProduct = new Query(getCtx(), MProduct.Table_Name, where, get_TrxName())
		 						.setClient_ID()
		 						.setOnlyActiveRecords(true)
		 						.firstOnly();
		 
		// Get the number of billed flight hours
		sql = "SELECT SUM(il.qtyInvoiced) FROM "
				+ " C_InvoiceLine il "
				+ " JOIN C_Invoice i ON (i.C_Invoice_ID = il.C_Invoice_ID)"
				+ " WHERE i.DocStatus IN ('CO','CL') "
				+ " AND i.isSOTrx = 'Y'"
				+ " AND il.M_Product_ID =" + acProduct.getM_Product_ID()
				+ " AND i.dateInvoiced BETWEEN "
				+ DB.TO_DATE(ac.getDateStartLease()) + " AND " + DB.TO_DATE(new Timestamp(endDate.getTime())); 
		
		BigDecimal flightHoursBilled =  DB.getSQLValueBD(get_TrxName(), sql);
		if (flightHoursBilled == null) // nothing billed
		{
			
			flightHoursBilled = Env.ZERO;
			
		}
		
		BigDecimal tier1hours = Env.ZERO;
		BigDecimal tier2hours = Env.ZERO;
		BigDecimal tier3hours = Env.ZERO;

		String invoiceDesc = "Aircraft Lease for " + ac.getACRegistration() + " for " + period.getName() + ". Total billable flight hours in period: "
				+ flightHoursBilled.setScale(1, RoundingMode.HALF_UP).toString() + ".";

		// Tier1 hours - minimum, between minimum and tier 1 limit plus any roll over.
		if (flightHoursBilled.compareTo(monthlyMinimum) <= 0) 
		{
			tier1hours = monthlyMinimum;
			invoiceDesc += "\nTier-1: Minimum Monthly Hours " + tier1hours.setScale(1, RoundingMode.HALF_UP) + ".";
		}
		else if (flightHoursBilled.compareTo(ac.getLeaseRateTier1_MaxHours()) <= 0)
		{
			tier1hours = flightHoursBilled;
			invoiceDesc += "\nTier-1: Flight hours (no roll over): " + tier1hours.setScale(1, RoundingMode.HALF_UP) + ".";
		}
		else if (rollOverHoursToApplyInThisPeriod.signum()>0 && 
				flightHoursBilled.compareTo(ac.getLeaseRateTier1_MaxHours().add(rollOverHoursToApplyInThisPeriod)) <= 0)
		{
			tier1hours = flightHoursBilled;
			rollOverApplied = rollOverHoursToApplyInThisPeriod.subtract(flightHoursBilled.subtract(ac.getLeaseRateTier1_MaxHours()));
			invoiceDesc += "\nTier-1: Flight hours: " + tier1hours.setScale(1, RoundingMode.HALF_UP) + " plus rollover of " + rollOverApplied.setScale(1, RoundingMode.HALF_UP) + ".";
		}
		else
		{
			tier1hours = ac.getLeaseRateTier1_MaxHours().add(rollOverHoursToApplyInThisPeriod);
			invoiceDesc += "\nTier-1: Max of " + ac.getLeaseRateTier1_MaxHours().setScale(1, RoundingMode.HALF_UP) + " hours plus roll over of " + rollOverHoursToApplyInThisPeriod.setScale(1, RoundingMode.HALF_UP) + ".";
		}
		
		flightHoursBilled = flightHoursBilled.subtract(tier1hours);
		if (flightHoursBilled.compareTo(Env.ZERO) < 0)
		{
			
			flightHoursBilled = Env.ZERO;
			
		}
			
		if (flightHoursBilled.compareTo(ac.getLeaseRateTier2_MaxHours()) <= 0)
		{
			
			tier2hours = flightHoursBilled;
			
		}
		else
		{
			  
			tier2hours = ac.getLeaseRateTier2_MaxHours();
			
		}
		
		if (tier2hours.signum() > 0)
		{
			invoiceDesc += " Tier-2: " + tier2hours.setScale(1, RoundingMode.HALF_UP) + " hours.";
		}

		flightHoursBilled = flightHoursBilled.subtract(tier1hours);
		if (flightHoursBilled.compareTo(Env.ZERO) < 0)
		{
			
			flightHoursBilled = Env.ZERO;
			
		}
		
		tier3hours = flightHoursBilled; // All the remaining.
		
		if (tier3hours.signum() > 0)
		{
			invoiceDesc += " Tier-3: " + tier3hours.setScale(1, RoundingMode.HALF_UP) + " hours. ";
		}
		BigDecimal rollOverFuture = rollOverHoursToApplyInThisPeriod.subtract(rollOverApplied).setScale(1, RoundingMode.HALF_UP);
		if (tier1hours.compareTo(ac.getLeaseRateTier1_MaxHours()) < 0)
		{
			rollOverFuture = rollOverFuture.add(ac.getLeaseRateTier1_MaxHours().subtract(tier1hours));
		}
		BigDecimal totalCharged = totalHoursBilled.add(tier1hours).add(tier2hours).add(tier3hours);
		invoiceDesc += "\nRollover hours remaining: " + rollOverFuture.setScale(1, RoundingMode.HALF_UP) + ".";
		invoiceDesc += "\nTotal hours charged on lease to date: " + totalCharged.setScale(1, RoundingMode.HALF_UP) + ".";
		invoiceDesc += " Hours remaining on lease: " + ac.getLeaseMaxHours().subtract(totalCharged).setScale(1, RoundingMode.HALF_UP)  + ".";
		// Build the lease invoice
		MBPartner bp = (MBPartner) ac.getC_BPartner();
		MInvoice invoice = new MInvoice(getCtx(),0,get_TrxName());
		invoice.setIsSOTrx(false);
		invoice.setC_BPartner_ID(ac.getC_BPartner_ID());
		invoice.setDateInvoiced(endDate);
		invoice.setDateAcct(endDate);
		invoice.setDescription(invoiceDesc);
		invoice.saveEx();
		
		MProduct leaseProduct = (MProduct) ac.getLeaseTier1Produc();
		MInvoiceLine line = new MInvoiceLine(invoice);
		line.setM_Product_ID(leaseProduct.getM_Product_ID());
		line.setC_UOM_ID(leaseProduct.getC_UOM_ID());
		line.setQty(tier1hours);
		line.setPrice(ac.getLeaseRateTier1());
		line.setDescription("Tier-1 rate applied to first " + ac.getLeaseRateTier1_MaxHours().setScale(1, RoundingMode.HALF_UP) 
				+ " billable flight hours plus rollover hours from previous months.");
		if (!ac.isLease_IsTaxApplied())
		{
			line.setC_Tax_ID(1000012);	// TODO - Hard coded "No Tax Charged"
		}
		line.setLineNetAmt();
		line.saveEx();
		
		invoice.setGrandTotal(line.getLineTotalAmt());
		
		if (tier2hours.signum() > 0)
		{
			
			leaseProduct = (MProduct) ac.getLeaseTier2Produc();
			line = new MInvoiceLine(invoice);
			line.setM_Product_ID(leaseProduct.getM_Product_ID());
			line.setC_UOM_ID(leaseProduct.getC_UOM_ID());
			line.setQty(tier2hours);
			line.setPriceEntered(ac.getLeaseRateTier2());
			line.setDescription("Tier 2 rate applied to next " + ac.getLeaseRateTier2_MaxHours().setScale(1, RoundingMode.HALF_UP) 
					+ " billable flight hours above the tier 1 hours plus any roll over amounts applied.");
			if (!ac.isLease_IsTaxApplied())
			{
				line.setC_Tax_ID(1000012);	// TODO - Hard coded "No Tax Charged"
			}
			line.setLineNetAmt();
			line.saveEx();

			invoice.setGrandTotal(invoice.getGrandTotal().add(line.getLineTotalAmt()));

		}

		if (tier3hours.signum() > 0)
		{
			
			leaseProduct = (MProduct) ac.getLeaseTier3Produc();
			line = new MInvoiceLine(invoice);
			line.setM_Product_ID(leaseProduct.getM_Product_ID());
			line.setC_UOM_ID(leaseProduct.getC_UOM_ID());
			line.setQty(tier3hours);
			line.setPriceEntered(ac.getLeaseRateTier3());
			line.setDescription("Tier 3 rate applied to any remaining " 
					+ " billable flight hours.");
			if (!ac.isLease_IsTaxApplied())
			{
				line.setC_Tax_ID(1000012);	// TODO - Hard coded "No Tax Charged"
			}
			line.setLineNetAmt();
			line.saveEx();

			invoice.setGrandTotal(invoice.getGrandTotal().add(line.getLineTotalAmt()));

		}
		
		invoice.saveEx();

		addLog(ac.getACRegistration() + " Lease Invoice " + invoice.getDocumentNo() + " - FMFL -Tier Lease, total: " + invoice.getGrandTotal());

	}

	private void generateFlightTimeInPeriodLease(MFTUAircraft ac){

		Timestamp openDate = new Timestamp(startDate.getTime() - oneDay);
		
		String whereClause = MFTUFlightsheet.COLUMNNAME_FTU_Aircraft_ID + " = " + ac.getFTU_Aircraft_ID()
				+ " AND " + MFTUFlightsheet.COLUMNNAME_FlightDate + " BETWEEN " + DB.TO_DATE(startDate) + " AND " + DB.TO_DATE(endDate);
		List<MFTUFlightsheet> flights = new Query(getCtx(), MFTUFlightsheet.Table_Name, whereClause, get_TrxName())
								.setClient_ID()
								.setOnlyActiveRecords(false)
								.list();

		BigDecimal flightTime = Env.ZERO;
		for (MFTUFlightsheet flight : flights)
		{
			
			flightTime = flightTime.add(flight.getFlightTime());
			
		}

		MBPartner bp = (MBPartner) ac.getC_BPartner();
		MInvoice invoice = new MInvoice(getCtx(),0,get_TrxName());
		invoice.setIsSOTrx(false);
		invoice.setC_BPartner_ID(ac.getC_BPartner_ID());
		invoice.setDateInvoiced(endDate);
		invoice.setDateAcct(endDate);
		invoice.setDescription("Aircraft lease for " + ac.getACRegistration() + ", " + period.getName() + ", flight time in month " + flightTime.setScale(1, RoundingMode.HALF_UP));
		invoice.saveEx();
		
		MInvoiceLine line = new MInvoiceLine(invoice);
		line.setC_Charge_ID(ac.getC_Charge_ID());
		line.setC_UOM_ID(ac.getC_UOM_ID());
		line.setQty(flightTime);
		line.setPrice(ac.getLeaseRateFixed());
		line.setDescription("Aircraft lease, flight time per month "+ period.getName() + ",");
		if (!ac.isLease_IsTaxApplied())
		{
			line.setC_Tax_ID(1000012);	// TODO - Hard coded "No Tax Charged"
		}
		line.setLineNetAmt();
		line.saveEx();
		
		invoice.setGrandTotal(line.getLineTotalAmt());
		invoice.saveEx();
		
		addLog(ac.getACRegistration() + " Lease Invoice " + invoice.getDocumentNo() + " - flight time, total: " + invoice.getGrandTotal());
		
	}

	private void generateAirTimeInPeriodLease(MFTUAircraft ac) {
		
		BigDecimal startTime = MFTUACJourneyLog.getTotalAirframeTime(getCtx(), ac.getFTU_Aircraft_ID(), startDate,	get_TrxName());
		BigDecimal endTime = MFTUACJourneyLog.getTotalAirframeTime(getCtx(), ac.getFTU_Aircraft_ID(), 
					new Timestamp(endDate.getTime() + oneDay),	get_TrxName());
		
		if (endTime.subtract(startTime).signum() <= 0)
		{
			addLog(ac.getACRegistration() + " no flights recorded in period.");
			return;
		}
		
		MBPartner bp = (MBPartner) ac.getC_BPartner();
		MInvoice invoice = new MInvoice(getCtx(),0,get_TrxName());
		invoice.setIsSOTrx(false);
		invoice.setC_BPartner_ID(ac.getC_BPartner_ID());
		invoice.setDateInvoiced(MPeriod.get(getCtx(), c_period_id).getEndDate());
		invoice.setDateAcct(MPeriod.get(getCtx(), c_period_id).getEndDate());
		invoice.setDescription("Aircraft lease for " + ac.getACRegistration() + ", " + period.getName() + ", air time from " + startTime.setScale(1, RoundingMode.HALF_UP) + ""
				+ " to " + endTime.setScale(1, RoundingMode.HALF_UP));
		invoice.saveEx();
		
		MInvoiceLine line = new MInvoiceLine(invoice);
		line.setC_Charge_ID(ac.getC_Charge_ID());
		line.setC_UOM_ID(ac.getC_UOM_ID());
		line.setQty(endTime.subtract(startTime));
		line.setPrice(ac.getLeaseRateFixed());
		line.setDescription("Aircraft lease, air time per month " + period.getName());
		if (!ac.isLease_IsTaxApplied())
		{
			line.setC_Tax_ID(1000012);	// TODO - Hard coded "No Tax Charged"
		}
		line.setLineNetAmt();
		line.saveEx();
		
		invoice.setGrandTotal(line.getLineTotalAmt());
		invoice.saveEx();
		
		addLog(ac.getACRegistration() + " Lease Invoice " + invoice.getDocumentNo() + " - air time, total: " + invoice.getGrandTotal());
				
	}

	private void generateMonthlyFixedLease(MFTUAircraft ac) {
		
		MBPartner bp = (MBPartner) ac.getC_BPartner();
		MInvoice invoice = new MInvoice(getCtx(),0,get_TrxName());
		invoice.setIsSOTrx(false);
		invoice.setC_BPartner_ID(ac.getC_BPartner_ID());
		invoice.setDateInvoiced(MPeriod.get(getCtx(), c_period_id).getEndDate());
		invoice.setDateAcct(MPeriod.get(getCtx(), c_period_id).getEndDate());
		invoice.setDescription("Aircraft lease for " + ac.getACRegistration() + ", " + period.getName() + ".");
		invoice.saveEx();
		
		MInvoiceLine line = new MInvoiceLine(invoice);
		line.setC_Charge_ID(ac.getC_Charge_ID());
		line.setPrice(ac.getLeaseRateFixed());
		line.setQty(Env.ONE);
		line.setDescription("Aircraft lease, fixed rate per monthmm, " + period.getName() + ".");
		if (!ac.isLease_IsTaxApplied())
		{
			line.setC_Tax_ID(1000012);	// TODO - Hard coded "No Tax Charged"
		}
		line.setLineNetAmt();
		line.saveEx();

		invoice.setGrandTotal(line.getLineTotalAmt());
		invoice.saveEx();

		addLog(ac.getACRegistration() + " Lease Invoice " + invoice.getDocumentNo() + " - fixed rate, total: " + invoice.getGrandTotal());
		
	}
}