package com.mckayerp.ftu.process;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.service.dsl.ProcessBuilder;

import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebResponseData;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.WebConnectionWrapper;
import com.mckayerp.ftu.model.MFTUADApplicability;
import com.mckayerp.ftu.model.MFTUADApplication;
import com.mckayerp.ftu.model.MFTUAircraft;
import com.mckayerp.ftu.model.MFTUAirworthinessDirective;
import com.mckayerp.ftu.model.MFTUCAWISManufacturer;
import com.mckayerp.ftu.model.MFTUCAWISModel;
import com.mckayerp.ftu.model.X_FTU_AirworthinessDirective;
import com.mckayerp.model.MCTComponent;

public class LoadAirworthinessDirectives extends SvrProcess {
	
	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (LoadAirworthinessDirectives.class);
	
	private Vector<Object[]> results = new Vector<Object[]>();
	private Object[] rowResults;

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 1000000;
	
	/** Org to be imported to	  		*/
	private int				m_AD_Org_ID = 1000000;

	/** User doing the importing		*/
	private int				m_AD_User_ID = 0;
	
	private Properties		m_ctx;

	private String linkURL = null;
	private String m_adType = null;
	
	/** The Transport Canada website with the airworthiness directives. */
	private String m_cawis_site_url = "http://wwwapps3.tc.gc.ca/Saf-Sec-Sur/2/cawis-swimn/awd-lv-cs1401.asp?rand=";
	
	private List<MProduct> products = null;

	/** A flag to indicate if the CAWIS manufacturers should be loaded. */
	private boolean m_isLoadManufacturers;
	/**	Parameter Name for IsLoadManufacturers	*/
	public static final String IsLoadManufacturers = "IsLoadManufacturers";

	protected void prepare() {
						
		// Get any parameters and set the local variables
		if (this.getParameterAsString("CAWIS_URL") != null && this.getParameterAsString("CAWIS_URL").length() > 0)
		{
			m_cawis_site_url = this.getParameterAsString("CAWIS_URL");
		}
		
		
		m_AD_Client_ID = this.getParameterAsInt("AD_Client_ID");
		m_AD_Org_ID = this.getParameterAsInt("AD_Org_ID");
		m_AD_User_ID = this.getParameterAsInt("AD_User_ID");
		
		m_isLoadManufacturers  = this.getParameterAsBoolean(IsLoadManufacturers);

		// TODO - fix for a server process where the ctx has to change
		m_ctx = getCtx();

	} // Prepare

	@Override
	protected String doIt() throws Exception {
		
		if (m_isLoadManufacturers)
		{
			updateCAWISManufacturersAndModels();
		}
		
		loadAC_Engines_Props();
		
		loadMiscEquipmentADs();
		
		updateApplicability();

		return "done";
		
	}

	private void updateApplicability() throws IOException {
		log.info("");
		
		final WebClient webClient = new WebClient();
		
		List<MFTUAirworthinessDirective> adList 
						= new Query(m_ctx, MFTUAirworthinessDirective.Table_Name, "", get_TrxName())
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.list();
									
		try 
		{	
	     
	        HtmlPage page1 = webClient.getPage(m_cawis_site_url);

	        for (MFTUAirworthinessDirective ad : adList)
	        {
	        	
	        	String adNumber = ad.getFTU_ADNumber();
	        	
	        	// Find the form with the search field and button
		        HtmlForm form = page1.getFormByName("F_SEARCH_BY_AD_NUMBER");
		        HtmlInput adNumberInput = form.getInputByName("SEARCH_TAIL_BY_AD_NUMBER");
		        HtmlInput button = form.getInputByName("cmdSearchByADNumber");
		        
		        if (adNumber.contains("R"))
		        {
		        	// Strip the revision info
		        	adNumber = adNumber.substring(0, adNumber.lastIndexOf("R"));
		        }
		        
		        adNumberInput.setValueAttribute(adNumber);
		        	        		
		        // Now submit the form by clicking the button and get back the second page.
		        page1 = button.click();
		        
		        HtmlForm tableForm = page1.getFormByName("F");
		        DomNodeList<HtmlElement> rowElements = tableForm.getElementsByTagName("tr");
	
				int rowCount = 0;
	
				for (DomElement rowElement : rowElements)
				{
						
					if (!(rowElement instanceof HtmlTableRow))
						continue;
					
					HtmlTableRow row = (HtmlTableRow) rowElement; 
	
					
					if (row.getAttribute("class").startsWith("Inside"))
					{
						
						// we have good data!
						rowCount ++;
						int cellCount = 0;
						String manufacturer = null;
						String applicability = null;
						
						if (row.getCells().size()==7)
						{

							manufacturer = row.getCell(4).asText();
							applicability = row.getCell(5).asText();
							
					    }
					    
					    // Find AD Application records with that manufacturer and
					    // set the applicability
					    if (manufacturer != null && applicability != null)
					    {
					    	MFTUCAWISManufacturer ftucawisManufacturer = MFTUCAWISManufacturer.getByName(m_ctx, manufacturer, get_TrxName());
					    	
					    	if (ftucawisManufacturer == null)
					    		continue;
					    	
							String where = MProduct.COLUMNNAME_IsTrackAsComponent + "='Y'"
									+ " AND " + MProduct.COLUMNNAME_FTU_CAWIS_Manufacturer_ID + "=?";
							
							products = new Query(getCtx(), MProduct.Table_Name, where, get_TrxName())
										.setClient_ID()
										.setOnlyActiveRecords(true)
										.setParameters(ftucawisManufacturer.getFTU_CAWIS_Manufacturer_ID())
										.list();

					    	for (MProduct product : products)
					    	{
					    		where = MFTUADApplication.COLUMNNAME_FTU_AirworthinessDirective_ID + "=?"
					    				+ " AND " + MFTUADApplication.COLUMNNAME_M_Product_ID + "=?";
					    		
			    				MFTUADApplication adApplication
					    			= new Query(m_ctx, MFTUADApplication.Table_Name, where, get_TrxName())
					    				.setClient_ID()
					    				.setOnlyActiveRecords(true)
					    				.setParameters(ad.getFTU_AirworthinessDirective_ID(),product.getM_Product_ID())
					    				.firstOnly();
			    				
			    				// Update if required
			    				if (	adApplication != null 
			    						&& (adApplication.getFTU_CAWIS_Applicability() == null
		    						|| !adApplication.getFTU_CAWIS_Applicability().equalsIgnoreCase(applicability)))
			    				{
			    					
				    				adApplication.setFTU_CAWIS_Applicability(applicability);
				    				adApplication.saveEx();
				    				
			    				}
					    	}
					    }					    
					}					
		        }
	        }
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			webClient.close();
		}
		
	}

	private void loadMiscEquipmentADs() throws IOException {
		
		log.info("");
		
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		webClient.getOptions().setCssEnabled(false);
		
		if (products == null)
		{
			String where = MProduct.COLUMNNAME_IsTrackAsComponent + "='Y'"
					+ " AND " + MProduct.COLUMNNAME_FTU_CAWIS_Manufacturer_ID + ">0";
			
			products = new Query(getCtx(), MProduct.Table_Name, where, get_TrxName())
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.list();
		}

		
		if(results == null)
			results=new Vector<Object[]>();		
		results.clear();

		try 
		{	
	     
	        HtmlPage page1 = webClient.getPage(m_cawis_site_url);

        	final HtmlAnchor anchor = (HtmlAnchor) page1.getElementById("advsrch");
        	HtmlPage advSearchPage = anchor.click();

        	// Find the form and the Misc Equipment Button
	        HtmlForm form = advSearchPage.getFormByName("F");
	        	        	        
	        HtmlInput button = null;
	        final List<HtmlInput> buttons = form.getInputsByValue("All ADs");
	        for (HtmlInput input : buttons)
	        {
	        	if (input.getOnClickAttribute().equalsIgnoreCase("SubmitForm('2');"))
        		{
	        		button = input;
	        		break;
        		}
	        }
	        	        		
	        // Now submit the form by clicking the button and get back the second page.
	        page1 = button.click();
	        
	        HtmlForm tableForm = page1.getFormByName("F");
	        DomNodeList<HtmlElement> rowElements = tableForm.getElementsByTagName("tr");

			int rowCount = 0;

			for (DomElement rowElement : rowElements)
			{
					
				if (!(rowElement instanceof HtmlTableRow))
					continue;
				
				HtmlTableRow row = (HtmlTableRow) rowElement; 

				
				if (row.getAttribute("class").startsWith("Inside"))
				{
					
					// we have good data!
					rowCount ++;
					int cellCount = 0;
					rowResults = new Object[9];  // Last element empty
				    for (final HtmlTableCell cell : row.getCells()) {
				    	
				    	switch (cellCount) 
				    	{
				    		//  Expected cells: New, Manufacturer, Country, AD Number, html link, pdf link, 
				    		//  AD subject, Reference
				    		case 0: case 1: case 2: case 6: case 7:
				    			rowResults[cellCount] = cell.asText();
				    			break;
				    			
				    		case 3:
				    			log.fine("Found AD " + cell.asText());
				    			rowResults[cellCount] = cell.asText();
				    			break;					    			
				    			
				    		case 4: case 5:
				        		DomNode link = cell.getFirstChild().getNextSibling();
				        		if (link != null && link instanceof HtmlAnchor) 
				        		{
				        			try {
				        				final Page linkPage = ((HtmlAnchor) link).click();  // Triggers a new page and causes an event.
						        		rowResults[cellCount] = linkPage.getUrl().toString();
				        			}
				        			catch (RuntimeException e) {
				        				log.fine("Couldn't follow link(" + cellCount + ") for AD " + rowResults[3]);
				        				rowResults[cellCount] = "";
				        			}
				        		}
				        		
				        	default:
				        		break;
				    	
				    	}
				    	
				    	cellCount++;
				    }
				    
				    // Check if any products have that manufacturer
				    for (MProduct product : products)
				    {
				    	if (rowResults[1] != null && product.getFTU_CAWIS_Manufacturer().getFTU_CAWIS_Manufacturer()
				    			.equalsIgnoreCase((String) rowResults[1]))
				    	{
				    		// If there is a match, add the AD and set the application
				    		// Do one rowResult at a time as the product will change.
						    results.add(rowResults);
						    m_adType = MFTUAirworthinessDirective.FTU_ADTYPE_MiscellaneousEquipment;
							updateDB(m_ctx, product, m_adType, results, get_TrxName());
							results.clear();
				    	}
				    }
				}					
	        }
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			webClient.close();
		}
	}

	private void updateCAWISManufacturersAndModels() throws IOException {
		
		log.info("");

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		webClient.getOptions().setCssEnabled(false);

		try 
		{	

			// Look for manufacturers     
	        HtmlPage page1 = webClient.getPage(m_cawis_site_url);

        	HtmlAnchor anchor = (HtmlAnchor) page1.getElementById("advsrch");
        	HtmlPage advSearchPage = anchor.click();
        	
	        HtmlForm form = advSearchPage.getFormByName("F");
	        
 	        HtmlSelect selectMake = form.getSelectByName("LEGAL_NM_SELECT");
	        List<HtmlOption> makes = selectMake.getOptions();
	        
	        for (HtmlOption make : makes)
	        {
	        	if (make.getText() == null || make.getText().trim().isEmpty())
	        		continue;
	        	
	        	log.info("Looking for manufacturer " + make.getText());
	        	
	        	// Find the make in the database
	        	MFTUCAWISManufacturer manufacturer = MFTUCAWISManufacturer.getByName(getCtx(), make.asText(), get_TrxName());
	        	
	        	if(manufacturer == null)
	        	{

	        		manufacturer = new MFTUCAWISManufacturer(getCtx(), 0, get_TrxName());
	        		manufacturer.setFTU_CAWIS_Manufacturer(make.getText());
	        		manufacturer.saveEx();
		        	log.info(make.getText() + " not found - adding ID " + manufacturer.getFTU_CAWIS_Manufacturer_ID());
		        	
	        	}
	        	else
	        	{
	        		
		        	log.fine("Found " + make.getText() + " ID " + manufacturer.getFTU_CAWIS_Manufacturer_ID());

	        	}
	        		        	
	        	advSearchPage = selectMake.setSelectedAttribute(make.getValueAttribute(), true);

		        form = advSearchPage.getFormByName("F");
		        
		        // Try to find Aircraft, Engine or Prop models
		        HtmlSelect selectModel = form.getSelectByName("AIRCRAFT_SELECT_RESULT");
		        List<HtmlOption> models = selectModel.getOptions();
		        if (models.size() <= 1)
		        {
			        selectModel = form.getSelectByName("ENGINE_SELECT_RESULT");
			        models = selectModel.getOptions();	        	
			        if (models.size() <= 1)
			        {
				        selectModel = form.getSelectByName("PROPELLER_SELECT_RESULT");
				        models = selectModel.getOptions();
				        if (models.size() <= 1)
				        {
				        	log.fine("No models found for manufacturer " + make.getText());
				        }
			        }
		        }

		        for (HtmlOption model : models)
		        {
		        	if (model.getText() == null || model.getText().trim().isEmpty())
		        		continue;

		        	log.fine("Looking for model " + model.getText());

		        	// Find the make in the database
		        	MFTUCAWISModel cawisModel = MFTUCAWISModel.getByManufacturerAndName(getCtx(), manufacturer.getFTU_CAWIS_Manufacturer_ID(), model.asText(), get_TrxName());
		        	
		        	if(cawisModel == null)
		        	{
		        		cawisModel = new MFTUCAWISModel(getCtx(), 0, get_TrxName());
		        		cawisModel.setFTU_CAWIS_Manufacturer_ID(manufacturer.getFTU_CAWIS_Manufacturer_ID());
		        		cawisModel.setFTU_CAWIS_Model(model.asText());
		        		cawisModel.saveEx();
			        	log.info(model.getText()+ " not found. Adding ID " + cawisModel.getFTU_CAWIS_Model_ID());

		        	}
		        	else
		        	{
			        	log.fine("Found " + model.getText() + " ID " + cawisModel.getFTU_CAWIS_Model_ID());		        		
		        	}
		        }
		        
		        advSearchPage = selectMake.setSelectedAttribute(make, false);
		        form = advSearchPage.getFormByName("F");
		        selectMake = form.getSelectByName("LEGAL_NM_SELECT");
		        
	        }


	        // Now get list of misc ADs and add the manufacturers
	        page1 = webClient.getPage(m_cawis_site_url);

        	anchor = (HtmlAnchor) page1.getElementById("advsrch");
        	advSearchPage = anchor.click();

        	// Find the form and the Misc Equipment Button
	        form = advSearchPage.getFormByName("F");
	        	        	        
	        HtmlInput button = null;
	        final List<HtmlInput> buttons = form.getInputsByValue("All ADs");
	        for (HtmlInput input : buttons)
	        {
	        	if (input.getOnClickAttribute().equalsIgnoreCase("SubmitForm('2');"))
        		{
	        		button = input;
	        		break;
        		}
	        }
	        
	        if (button == null)
	        	return;
	        
	        // Now submit the form by clicking the button and get back the second page.
	        page1 = button.click();
	        
	        HtmlForm tableForm = page1.getFormByName("F");
	        DomNodeList<HtmlElement> rowElements = tableForm.getElementsByTagName("tr");

			for (DomElement rowElement : rowElements)
			{
					
				if (!(rowElement instanceof HtmlTableRow))
					continue;
				
				HtmlTableRow row = (HtmlTableRow) rowElement; 

				if (row.getAttribute("class").startsWith("Inside"))
				{
					
					// we have good data!
					int cellCount = 0;
					rowResults = new Object[9];  // Last element empty
					
					String make = row.getCell(1).asText();
		        	log.info("Looking for manufacturer " + make);
		        	
		        	// Find the make in the database
		        	MFTUCAWISManufacturer manufacturer = MFTUCAWISManufacturer.getByName(getCtx(), make, get_TrxName());
		        	
		        	if(manufacturer == null)
		        	{

		        		manufacturer = new MFTUCAWISManufacturer(getCtx(), 0, get_TrxName());
		        		manufacturer.setFTU_CAWIS_Manufacturer(make);
		        		manufacturer.saveEx();
			        	log.info(make + " not found - adding ID " + manufacturer.getFTU_CAWIS_Manufacturer_ID());
			        	
		        	}
		        	else
		        	{
		        		
			        	log.fine("Found " + make + " ID " + manufacturer.getFTU_CAWIS_Manufacturer_ID());

		        	}
				}
			}
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			webClient.close();
		}
		
	}

	private void loadAC_Engines_Props() throws IOException {
		
		log.info("");

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		webClient.getOptions().setCssEnabled(false);

		if (products == null)
		{
			String where = MProduct.COLUMNNAME_IsTrackAsComponent + "='Y'"
					+ " AND " + MProduct.COLUMNNAME_FTU_CAWIS_Manufacturer_ID + ">0";
			
			products = new Query(getCtx(), MProduct.Table_Name, where, get_TrxName())
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.list();
		}
		
		if(results == null)
			results=new Vector<Object[]>();		
		results.clear();

		try 
		{	
	        for (MProduct product : products)
	        {
	        	String manufacturer = product.getFTU_CAWIS_Manufacturer().getFTU_CAWIS_Manufacturer();
	        	String modelName = null;
	        	if (product.getFTU_CAWIS_Model_ID() > 0)
	        	{
	        		modelName = product.getFTU_CAWIS_Model().getFTU_CAWIS_Model();
	        	}

	        	// For the aircraft, engines and props, skip the products
	        	if (manufacturer == null  || modelName == null)
	        		continue;
	        	
	        	log.fine("Looking for Product " + product.toString() + " - Make: " + manufacturer + ", Model: " + modelName);
	     
		        HtmlPage page1 = webClient.getPage(m_cawis_site_url);

	        	final HtmlAnchor anchor = (HtmlAnchor) page1.getElementById("advsrch");
	        	HtmlPage advSearchPage = anchor.click();
	        	
		        HtmlForm form = advSearchPage.getFormByName("F");
		        final HtmlSelect selectMake = form.getSelectByName("LEGAL_NM_SELECT");
		        List<HtmlOption> makes = selectMake.getOptions();
		        
		        boolean manufacturerFound = false;
		        for (HtmlOption make : makes)
		        {
		        	if(make.getText().equalsIgnoreCase(manufacturer))
		        	{
		        		manufacturerFound = true;
		        		advSearchPage = selectMake.setSelectedAttribute(make, true);
		        		break;
		        	}
		        }
		        
		        if (!manufacturerFound)
		        {
		        	log.fine("  Manufacturer not found " + manufacturer);
		        	continue;
		        }
	
		        form = advSearchPage.getFormByName("F");
	
		        String buttonName = "";
		        HtmlSelect selectModel = form.getSelectByName("AIRCRAFT_SELECT_RESULT");
		        List<HtmlOption> models = selectModel.getOptions();
		        if (models.size() <= 1)
		        {
			        selectModel = form.getSelectByName("ENGINE_SELECT_RESULT");
			        models = selectModel.getOptions();	        	
			        if (models.size() <= 1)
			        {
				        selectModel = form.getSelectByName("PROPELLER_SELECT_RESULT");
				        models = selectModel.getOptions();
				        if (models.size() <= 1)
				        {
				        	// Not an aircraft, engine or propeller.
				        	log.fine("  Not an aircraft, engine or propeller: " + modelName);
				        }
				        else
				        {
				        	m_adType = X_FTU_AirworthinessDirective.FTU_ADTYPE_Propellers;
				        	buttonName = "cmdPropellerAll";  // Propeller
				        	log.fine("  Propeller!");
				        }
			        }
			        else
			        {
			        	m_adType = X_FTU_AirworthinessDirective.FTU_ADTYPE_Engines;
			        	buttonName = "cmdEngineAll"; // Engine
			        	log.fine("  Engine!");
		        	}
		        }
		        else
		        {
		        	m_adType = X_FTU_AirworthinessDirective.FTU_ADTYPE_Aircraft;
		        	buttonName = "cmdAircraftAll"; // Aircraft
		        	log.fine("  Aircraft!");
		        }
		        	        
		        boolean modelFound = false;
		        for (HtmlOption model : models)
		        {
		        	if(model.getText().equalsIgnoreCase(modelName))
		        	{
		        		modelFound = true;
		        		log.fine("  Model found " + modelName);
		        		advSearchPage = selectModel.setSelectedAttribute(model, true);		
		        		break;
		        	}
		        }
		        
		        if (!modelFound)
		        {
		        	log.fine("  Model not found " + modelName);
		        	continue;
		        }
		        
		        form = advSearchPage.getFormByName("F");
		        final HtmlButtonInput button = form.getInputByName(buttonName);
		        	        		
		        // Now submit the form by clicking the button and get back the second page.
		        page1 = button.click();
		        
		        HtmlForm tableForm = page1.getFormByName("F");
		        log.config("Form " + tableForm.getReadyState());
		        DomNodeList<HtmlElement> rowElements = tableForm.getElementsByTagName("tr");
	
				int rowCount = 0;
	
				for (DomElement rowElement : rowElements)
				{
						
					if (!(rowElement instanceof HtmlTableRow))
						continue;
					
					HtmlTableRow row = (HtmlTableRow) rowElement; 

					if (row.getAttribute("class").startsWith("Inside"))
					{
						
						// we have good data!
						rowCount ++;
						int cellCount = 0;
						rowResults = new Object[9];
					    for (final HtmlTableCell cell : row.getCells()) {
					    	
					    	switch (cellCount) 
					    	{
					    		//  Expected cells: New, Model, Country, AD Number, html link, pdf link, 
					    		//  AD subject, Reference, Repeat
					    		case 0: case 1: case 2: case 6: case 7: case 8:
					    			rowResults[cellCount] = cell.asText();
					    			break;
					    			
					    		case 3:
					    			log.fine("Found AD " + cell.asText());
					    			rowResults[cellCount] = cell.asText();
					    			break;					    			
					    			
					    		case 4: case 5:
					        		DomNode link = cell.getFirstChild().getNextSibling();
					        		if (link instanceof HtmlAnchor) 
					        		{
					        			try {
					        				final Page linkPage = ((HtmlAnchor) link).click();  // Triggers a new page and causes an event.
							        		rowResults[cellCount] = linkPage.getUrl().toString();
					        			}
					        			catch (RuntimeException e) {
					        				log.fine("Couldn't follow link(" + cellCount + ") for AD " + rowResults[3]);
					        				rowResults[cellCount] = "";
					        			}
					        		}
					        		
					        	default:
					        		break;
					    	
					    	}
					    	
					    	cellCount++;
					    }
					    
					    results.add(rowResults);
					}
					
				}
			
				//	For all rows - update/insert DB table
				log.config("Found " + rowCount + " rows.");
				
				updateDB(m_ctx, product, m_adType, results, get_TrxName());
				
				results.clear();
	
	        }
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			webClient.close();
		}
	
	}

	private boolean updateDB (Properties ctx, MProduct product, String adType, Vector<Object[]> results, String trxName)
	{
		for (Object[] line : results)
		{
			if (line == null || line.length == 0)
			{
				log.finest("No Line");
				return false;
			}
			
			//  Expected cells: New, Model, Country, AD Number, html link, pdf link, 
			//  AD subject, Reference, Repeat
	
			String newEntry = (String) line[0];  // Not used
			String model 	= (String) line[1];	 // Or manufacturer
			String country  = (String) line[2];
			String adNumber = (String) line[3];
			String link		= (String) line[4];
			String pdf		= (String) line[5];
			String adSubject= (String) line[6];
			String reference= (String) line[7];
			String repeat	= (String) line[8];
	
			//	Check if the record is already there ----------------------------
			MFTUAirworthinessDirective ad = MFTUAirworthinessDirective.get(ctx, adNumber, get_TrxName());
			
			if (ad.getFTU_AirworthinessDirective_ID() == 0)
			{
				ad.setFTU_ADCountryCode(country);
				ad.setFTU_ADDocumentLink(link);
				ad.setFTU_ADDocumentLinkPDF(pdf);
				ad.setFTU_ADSubject(adSubject);
				ad.setFTU_ADReference(reference);
				ad.setFTU_ADRepeatText(repeat);
				ad.setFTU_ADType(adType);
				ad.saveEx();
				log.config("AD added: " + adNumber);
			}
			else
			{
				log.config("AD already exists: " + adNumber);
			}
			
			String where = MFTUADApplication.COLUMNNAME_M_Product_ID + "=?"
					+ " AND " + MFTUADApplication.COLUMNNAME_FTU_AirworthinessDirective_ID + "=?";
			
			MFTUADApplication application = new Query(getCtx(), MFTUADApplication.Table_Name, where, get_TrxName())
												.setClient_ID()
												.setOnlyActiveRecords(true)
												.setParameters(product.getM_Product_ID(), ad.getFTU_AirworthinessDirective_ID())
												.firstOnly();
			
			if (application == null)
			{
				
				application = new MFTUADApplication(getCtx(), 0, get_TrxName());
				application.setFTU_AirworthinessDirective_ID(ad.getFTU_AirworthinessDirective_ID());
				application.setM_Product_ID(product.getM_Product_ID());
				application.setFTU_IsADApplies(true);  // Default
				application.saveEx();  // After save sets the applicability of components
				
			}
		}										
		return true;
	}	//	updateDB


	public static void main(String[] args) {
				
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.FINE);
		
		if (! DB.isConnected()) 
		{
			
			log.info("No DB Connection");
			System.exit(1);
			
		}

		// Override the context - specifically if we are running on the server.
		// Otherwise, the server process won't be able to save the order to the
		// correct client.  Use the passed parameters.
		Env.setContext(Env.getCtx(), "#AD_Client_ID", 1000000);  // Replace the client
		Env.setContext(Env.getCtx(), "#AD_Org_ID", 1000000);		// Replace the org
		Env.setContext(Env.getCtx(), "#AD_User_ID", 1000000);		// Replace the user
		Properties context = Env.getCtx();		

		try 
		{
			
			//Import Airworthiness Directives
			ProcessInfo processInfo = ProcessBuilder.create(context)
			.process(com.mckayerp.ftu.process.LoadAirworthinessDirectives.class)
			.withTitle("Import Airworthiness Directives")
			.withParameter(IsLoadManufacturers, false)
			.execute();

			log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());

		} 
		catch (AdempiereException e) 
		{
			e.printStackTrace();
		}
	}

}
