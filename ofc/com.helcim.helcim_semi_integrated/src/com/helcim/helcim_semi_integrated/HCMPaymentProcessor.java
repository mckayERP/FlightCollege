/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation & McKayERP.com,               *
 * All Rights Reserved.                                                       *
 *                                                                            *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package com.helcim.helcim_semi_integrated;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Currency;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.model.MSysConfig;
import org.compiere.model.PaymentProcessor;
import org.compiere.model.X_C_Payment;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.Util;

/**
 * Helcim Payment Processor using the helcim credit card terminals.  This class manages the 
 * interface between the Helcim API and the ADempiere payment processor.  It is a thread-based
 * processor to allow the user interface to retain control during the credit card processing
 * and also to provide feedback to the user.  The process can be voided before approval,
 * for example, when the user makes an error.
 * 
 * The processor includes a simulator of the terminals which can be enabled by setting
 * the Boolean simulation to true.
 * 
 * The classes and threads are created and closed for each transaction.  No thread should 
 * remain running once the transaction is complete.
 * 
 * @author mckayERP
 *
 */
public class HCMPaymentProcessor extends PaymentProcessor {

	/** The helcim terminal interface */ 
	private hcmTerminal terminal;
	
	/** A flag indicating that the transaction in process should be voided */
	private boolean voidTrx = false; 
	
	/** A flag to use the terminal simulator - typically for development only */
	private final Boolean simulation = MSysConfig.getBooleanValue("Use Helcim Simulator", false, Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()));
	
	
	/** The simulator thread, only used if simulation == true */
	private Thread simThread = null;
	
	/** The terminal simulator class for Helcim terminals */
	private HCMTerminalSimulator simulator;
	
	/** The approval code */
	private final static Integer APPROVED = 1; 
	
	/** The thread that manages the connection with the terminal */
	private Thread producerThread = null;
	
	/** The thread that manages the communications from the terminal */
	private Thread consumerThread = null;
	
	/** The class that manages the connection with the terminal */
	private HCMTerminalProducer producer;

	/** The class that manages the communication with the terminal and deals
	 *  with the results of the transaction.
	 */
	private HCMTerminalConsumer consumer;
	
	/** A thread synchronization object used for wait/notify actions */
	public static final Object syncObject = new Object();

	private static final String ACTION_Purchase = "Purchase";

	private static final String ACTION_Void = "Void";

	private static final String ACTION_Refund = "Refund";

	private static final String ACTION_Preauth = "Preauth";

	private static final String ACTION_Capture = "Capture";

	private static final String ACTION_Settle = "Settle";

	private static final String ACTION_Reprint = "Reprint";
	
    private boolean m_done = false;


	CLogger log = CLogger.getCLogger(HCMPaymentProcessor.class);
	
	/** The payment processor class for the Helcim credit card terminals.  This class
	 *  initializes the helcim terminal API and starts the threads necessary to manage
	 *  the communication between the terminal and the application. 
	 * 
	 */
	public HCMPaymentProcessor() 
	{
        //  The constructor does not block.
	}

	/** 
	 * The terminal consumer manages the output of the terminal and deals with the results
	 * of the transaction.  The results are passed back to the calling application either 
	 * through a property change event or via a process result message.  The property change
	 * event allows the user interface to stay active in its own thread and to receive
	 * updates from the terminal log during the transaction.  For processes, the method 
	 * {@link getResult()} will block until transaction is complete and will return the 
	 * process message results of the transaction.
	 *   
	 * @author mckayERP
	 *
	 */
	public class HCMTerminalConsumer implements Runnable {

		private HCMTerminalProducer m_producer;
		
		private String resultMsg = "";
		
		private Boolean processSuccess = false;

		private String m_action = "";
		
		HCMTerminalConsumer(HCMTerminalProducer producer, String action)
		{
			m_producer = producer;
			m_action  = action;
		}

		StringBuffer trxLog = new StringBuffer("");
		
		@Override
		public void run() {

			//  Consume the producer output - the terminal log
	        while ( !m_done )
	        {
	        	log.fine("Getting the terminal log - may block.");
	        	String terminalLog = m_producer.getTerminalLog();  // Will block until output available or interrupted.
				log.fine("Terminal log: " + terminalLog);
				
				//  Translate the terminal log into clear text
				String newValue = translateTerminalLog(terminalLog);

				//  Send the clear text to the user interface.
				p_mpp.firePropertyChange("terminalLog", null, newValue);
				
				//  Add the clear text to a buffer
				trxLog.append(newValue);

				// Test for completed transaction.  The terminal will include the phrase "Ended Connection"
				// when it is finished or "Error" if there is a problem.
	        	if ( terminalLog.contains("Ended Connection") 
	        			|| terminalLog.contains("Error")  
	        			|| Thread.currentThread().isInterrupted())
	        	{
	        		log.fine("Terminal Consumer loop finished. Ended Connection: " + terminalLog.contains("Ended Connection")
	        				+ ", Error: " + terminalLog.contains("Error") 
	        				+ ", Interruption: " + Thread.currentThread().isInterrupted());
					//  Send the clear text to the user interface.
					p_mpp.firePropertyChange("ConnectionEnded", null, terminalLog.contains("Error"));
	        		m_done = true;
	        	}
	        }

	        
			//  Need a final value for the payment save/post result inside 
	        //  the TrxRunnable below - using an array
			final boolean[] success = new boolean[] { true };

			//  Check for a response from the terminal - there should be one if the transaction
			//  was successful.
			if (terminal.response != null)
			{
				//  There is a response
				log.fine("Terminal response code: " + terminal.response);
				
				if (terminal.response.equals(APPROVED))  // TODO - which transactions provide this? Purchase, PreAuth or Capture?
				{
					
					// The transaction was approved.
					log.fine("Approved!");
					
					//  Save/Post the payment - its important that this succeeds or the user will have
					//  to take some action to manually create the payment.
					if (p_mp != null && p_mp.get_ID() > 0 && (MPayment.DOCSTATUS_Drafted.equals(p_mp.getDocStatus())
							|| MPayment.DOCSTATUS_Invalid.equals(p_mp.getDocStatus())))
					{
						// Use the payment transaction or create a new one if it is null.
						try {
							
							Trx.run(
								p_mp.get_TrxName(), 
								new TrxRunnable() 
								{
	
									public void run(String trxName) 
									{
										// Reprint and Settle actions will not get here unless the response is APPROVED
//										if (HCMPaymentProcessor.ACTION_Reprint.equals(m_action)
//											|| HCMPaymentProcessor.ACTION_Settle.equals(m_action))
//										{
//											success[0] = true;
//											return;
//										}
										
										//  Save the transaction results in the payment
										p_mp.setIsApproved(true);
										p_mp.setR_Result(terminal.responseMessage);
										p_mp.setCreditCardNumber(terminal.cardNumber, false);
										p_mp.setCreditCardType(ccType(terminal.cardType));
										p_mp.setR_PnRef(terminal.referenceNumber);
										p_mp.setR_AuthCode(terminal.authorizationNumber);
										p_mp.setR_RespMsg(terminal.responseMessage + " " + terminal.responseCode);
										p_mp.setDateTrx(new Timestamp(System.currentTimeMillis()));
										//p_mp.setDateAcct(Env.getContextAsDate(Env.getCtx(), "#Date"));
										p_mp.setDescription(trxLog.toString());
										//p_mp.saveEx();

										if (HCMPaymentProcessor.ACTION_Purchase.equals(m_action)
												|| HCMPaymentProcessor.ACTION_Void.equals(m_action)
												|| HCMPaymentProcessor.ACTION_Refund.equals(m_action)
												|| HCMPaymentProcessor.ACTION_Capture.equals(m_action))
										{
											// Try to fix invalid status first
											if (MPayment.DOCSTATUS_Invalid.equals(p_mp.getDocStatus()))
											{
												success[0] = p_mp.processIt(DocAction.ACTION_Prepare);
											}
											if (success[0])
											{
												success[0] = p_mp.processIt(DocAction.ACTION_Complete);
											}
										}
										else if (HCMPaymentProcessor.ACTION_Preauth.equals(m_action))
										{
											success[0] = p_mp.processIt(DocAction.ACTION_Prepare);
										}
										
//										if (success[0])
//										{
//											// Close the payment as it has a credit card transaction associated
//											// with it.  Void not allowed.
//											success[0] = p_mp.processIt(DocAction.ACTION_Close);
//										}
										p_mp.saveEx();
									}
								}
							);							
						} 
						catch (AdempiereException | IllegalStateException e) 
						{
							// Problem! The credit card transaction was approved but the 
							// ADempiere payment wasn't processed.
							success[0] = false;
							log.severe("Payment Error.  " + e.getMessage());
							e.printStackTrace();
						}

					}

				}
			}
			else
			{
				log.fine("No terminal response - null");
			}
			
			try 
			{
//				// The transaction is finished.  Let the calling processes/UI known the results.
//				//  Fire property change event for the UI.  Harmless if no listeners were registered.
//				log.fine("Firing property change: OnlineProcessCompleted");
//				p_mpp.firePropertyChange("OnlineProcessCompleted", null, terminal.response != null && terminal.response.equals(APPROVED));
				
				
				//  Save the result message to pass to the calling process, if any.
				String info;
				String result;
				
				if (success[0])
				{
					if (p_mp != null)
					{
						info = p_mp.getR_RespMsg() + " (Auth:" + p_mp.getR_AuthCode()
							+ ") Ref=" + p_mp.getR_PnRef();
						result = Msg.translate(Env.getCtx(), "PaymentProcessed") + " " +  info 
							+ "\n" + Msg.parseTranslation(Env.getCtx(), "@C_Payment_ID@") + ": " + p_mp.getDocumentNo();
					}
					else
						result = "";
					
				}
				else
				{
					
					result = "@Error@ Payment not saved/posted successfully.";
				}
				
				setProcessSuccess(success[0]);
				setResult(result);

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally 
			{
				// Ensure all threads are shut down
				closeAllThreads();
			}
		}
		
		/**
		 *  A synchronized getter for the transaction results.  This will block until notified
		 *  that the results are ready to be read.
		 * @return  The a process message with the results of the transaction.
		 */
		public String getResult()
		{
			synchronized(syncObject)
			{
				try {
					if (consumerThread.isAlive())
					syncObject.wait();
				} catch (InterruptedException e) {
					if (resultMsg == null || resultMsg.isEmpty())
						return ("@Error@ Process interrupted.");
					else
						return resultMsg;
				}
				return resultMsg;
			}
		}

		/**
		 * A synchronized setter for the result message.  Calling this function will
		 * set the result message and then notify any threads waiting for the results
		 * of the transaction.
		 * @param result - the message to provide to the calling process.
		 */
		public void setResult(String result)
		{
			synchronized (syncObject) {
				resultMsg = result;
				syncObject.notifyAll();
			}
		}

		/**
		 * @return the processSuccess
		 */
		public Boolean getProcessSuccess() {
			return processSuccess;
		}

		/**
		 * @param processSuccess the processSuccess to set
		 */
		public void setProcessSuccess(Boolean processSuccess) {
			this.processSuccess = processSuccess;
		}

		/**
		 * Translate the coded terminal log into clear text. 
		 * @param terminalLog
		 * @return The clear text version of the log.
		 */
		private String translateTerminalLog(String terminalLog) {

			String result = "";
			
			if (terminalLog == null)
				return result;
			
			// REPLACE DC1 WITH NOTHING
			terminalLog = terminalLog.replace((char) 0x11,
					(char) 0x00);

			// REPLACE FS WITH SPACE
			terminalLog = terminalLog.replace((char) 0x1c,
					(char) 0x20);

			// REPLACE GS WITH SPACE
			terminalLog = terminalLog.replace((char) 0x1d,
					(char) 0x20);

			// SPLIT STRING INTO ARRAY BASED ON SPACES
			String[] responseContents = terminalLog.split(" ");

			if (responseContents.length== 0)
				responseContents[0] = terminalLog;
			
			// LOOP THROUGH ARRAY
			for (String responseItem : responseContents) {
				// CHECK CONTENTS
				if (responseItem.length() < 3)
				{
					result += responseItem;
				}
				else if (responseItem.substring(0, 3).equals("300")) {

					//
					// CARD TYPE
					//
					result += "Card Type: " + getCardType(responseItem.substring(3)) + "\n";

				} else if (responseItem.substring(0, 3).equals("306")) {

					//
					// CARD ENTRY
					//

					// CHECK CARD ENTRY
					result += "Entry Method: " + getCardEntry(responseItem.substring(3)) + "\n";

				} else if (responseItem.substring(0, 3).equals("402")) {

					//
					// MESSAGE
					//

					// CHECK IF APPROVAL
					if (responseItem.substring(3).equals("APPROVAL")) {

						result += "Response: APPROVED\n";
						
					} else {

						result += "Response: DECLINED\n";

					}

				} else if (responseItem.substring(0, 3).equals("302")) {

					//
					// CARD NUMBER
					//

					// SET CARD NUMBER
					result += "Card Number: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("112")) {

					//
					// REF NUMBER
					//

					// SET REF NUMBER
					result += "Ref: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("400")) {

					//
					// AUTH NUMBER
					//

					// SET AUTH NUMBER
					result += "Auth: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("601")) {

					//
					// TID
					//

					// SET TID
					result += "Terminal: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("401")) {

					//
					// RESPONSE CODE
					//

					// SET RESPONSE CODE
					result += "Response Code: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("602")) {

					//
					// MID
					//

					// SET MID
					result += "Merchant ID: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("500")) {

					//
					// BATCH NUMBER
					//

					// SET BATCH NUMBER
					result += "Batch No: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("502")) {

					//
					// BATCH TOTAL
					//

					// SET BATCH TOTAL
					result += "Batch Total: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("503")) {

					//
					// BATCH TOTAL COUNT
					//

					// SET BATCH TOTAL COUNT
					result += "Batch Tot. Count: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("504")) {

					//
					// BATCH SALE TOTAL
					//

					// SET BATCH SALE TOTAL
					result += "Batch Sale Tot.: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("505")) {

					//
					// BATCH SALE COUNT
					//

					// SET BATCH SALE COUNT
					result += "Batch Sale Tot. Cnt: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("506")) {

					//
					// BATCH REFUND TOTAL
					//

					// SET BATCH REFUND TOTAL
					result += "Batch Refund Tot.: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("507")) {

					//
					// BATCH REFUND COUNT
					//

					// SET BATCH REFUND COUNT
					result += "Batch Refund Tot. Cnt: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("508")) {

					//
					// BATCH VOID AMOUNT
					//

					// SET BATCH VOID AMOUNT
					result += "Batch Void Tot.: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("509")) {

					//
					// BATCH VOID COUNT
					//

					// SET BATCH VOID COUNT
					result += "Void Total Count: " + responseItem.substring(3) + "\n";

				} else if (responseItem.substring(0, 3).equals("512")) {

					//
					// BATCH CASHBACK AMOUNT
					//

					// SET BATCH CASHBACK AMOUNT
					result += "Cashback Total: " + responseItem.substring(3) + "\n";

				}
				else 
				{
					// Add the responseItem directly
//					result += responseItem;
//					if ( !(responseItem.endsWith("\n") || responseItem.endsWith(" ")) )
//						result += " ";
				}
			}
			
			return result;
		}
	}
	
	/**
	 * This class manages the terminal communications and will initiate the void of
	 * a transaction if one is in progress.
	 * @author mckayERP
	 *
	 */
	public class HCMTerminalProducer implements Runnable {
		
		CLogger log = CLogger.getCLogger(HCMTerminalProducer.class);

		/** The terminal log queue managed as a list.  There is no size limit.  */
		private List<String> terminalLog = new ArrayList<String>();

		public HCMTerminalProducer() {

		}

		/**
		 * Returns the terminal log or the first element in the list and
		 * also removes that element from the list.  Will block/wait if the terminal log list
		 * is empty.
		 * @return
		 */
		public synchronized String getTerminalLog()
		{
			// Loop to read the queue and wait for valid input 
			while ( terminalLog.size() == 0 && !Thread.currentThread().isInterrupted())
			{
				
				try
				{
					
					log.fine("Waiting for terminal log input.");
					wait();
					
				}
				catch ( InterruptedException e ) 
				{ 
					
					log.fine("Received interrupted exception: " + e.getMessage());
					Thread.currentThread().interrupt();
					
				}
				
			}
			
			if (terminalLog.size() == 0) // Might happen if interrupted.
			{
				
				log.fine("Terminal log has no contents.");
				return "";
				
			}
			else
			{
				
				//  There is valid data in the queue.  Consume it and send 
				//  the data to the calling thread.
				log.info("Received terminal log input: " + terminalLog.get(0));
				return terminalLog.remove(0);
				
			}
		}
		
		/**
		 * Sets the terminal log and test for the end of the connection signaled by the terminal.
		 * If the terminal does signal the end of the connection, this thread will 
		 * @param logTimer
		 */
		public synchronized void setTerminalLog(Timer logTimer) {

			//  Check the terminal connection log string for valid data
	        if (terminal.connectionLog != null 
	        	&& terminal.connectionLog.length() > 1) 
            {
	        		// There is something in the log
	            	// Consume the connectionLog and produce the terminalLog
	            	// by adding the log data to the terminalLog queue
	            	log.fine("Terminal connection log reports: " + terminal.connectionLog);
	            	terminalLog.add(terminal.connectionLog + "\n");
	            	
	            	//  Check for the end of the transaction and cancel this logTimer thread.
	            	if (terminal.connectionLog.contains("Ended Connection"))
	            	{
	            		log.fine("Cancelling the logTimer. Ending the producer thread.");
	            		logTimer.cancel();
	            	}
	            	
	            	// Consume/reset the connection log
	            	terminal.connectionLog = "";
	            	
	            	// Notify the terminal log consumer that the log has a new item.
	            	log.info("Notifying terminalLog consummer.");
	            	notify();
	        }
		}
		
		/**
		 * Producer process.  Calls setTimerLog every half second and tests for
		 * the void flag to void the process.
		 */
		@Override
		public void run() {
			
	        // INIT TIMER - acts as the producer of the terminalLog
	        Timer logTimer = new Timer();

	        // START TIMER AT 500MS
	        logTimer.scheduleAtFixedRate(new TimerTask() 
	        {

				@Override
	            public void run() 
	            {
	            	setTerminalLog(logTimer);
	            	if (!m_done && voidTrx)
	            	{
	            		terminal.hcmVoid();
	            		voidTrx = false;
	            	}
	            	
	        	}
	            
	        }, 0, 500);

		}

	}

	/**
	 * Process the Credit Card transaction using the Helcim terminals.
	 */
	@Override
	public boolean processCC() throws IllegalArgumentException {
		
		return processCC(null);
	}

	/**
	 * Process the Credit Card transaction using the Helcim terminals.
	 */
	public boolean processCC(String action) throws IllegalArgumentException {
		
		if (action == null || action.isEmpty())
		{
			if (p_mp == null)
				return false;
			
			if (p_mp.getPayAmt().compareTo(Env.ZERO) > 0)
			{
				action = ACTION_Purchase;
			}
			else if (p_mp.getPayAmt().compareTo(Env.ZERO) < 0)
			{
				// Void transactions on the same day and when there is a transaction reference.
				if (p_mp.getDateTrx().compareTo(Env.getContextAsDate(p_mp.getCtx(), "#Date")) == 0 
					&& p_mp.getR_PnRef() != null && !p_mp.getR_PnRef().isEmpty())
				{
					action = ACTION_Void;
				}
				else
				{
					action = ACTION_Refund;
				}
			}
		}
		
		// Has the payment processor been set?
		if (p_mpp == null)
		{

			closeAllThreads();
			throw new IllegalArgumentException("Payment Processor not set.");

		}
		
		//  Check for the correct currency.  The Helcim terminals process in CDN
		if ( p_mp != null &&  p_mpp.getC_Currency_ID() != 0 && p_mpp.getC_Currency_ID() != p_mp.getC_Currency_ID() )
		{
		
			closeAllThreads();
			throw new IllegalArgumentException("Payment currency not supported by processor.");
			
		}
		
		//  Check for minimum payment amount - only test on positive payment amounts.
		//  Negative payment amounts indicate refunds and have no limit.
		if ( p_mp != null && p_mp.getPayAmt().signum() >= 0 &&  p_mpp.getMinimumAmt().compareTo(p_mp.getPayAmt()) > 0)
		{
			
			closeAllThreads();
			throw new IllegalArgumentException("Payment amount is less than minimum accepted.");
			
		}
		
		// Check if the CVC code is required by the processor
		if ( p_mp != null && p_mpp.isRequireVV() && Util.isEmpty(p_mp.getCreditCardVV(), true) )
		{
		
			closeAllThreads();
			throw new IllegalArgumentException("Credit card verification code required.");
			
		}
		
		//  Check the payment transaction type - only sales are supported
		if (p_mp != null && !p_mp.getTrxType().equals(MPayment.TRXTYPE_Sales))
		{
		
			closeAllThreads();
			throw new IllegalArgumentException("Only sales transactions are supported - " + p_mp.getTrxType());
			
		}
		
		setupTerminal(action);

		switch (action)
		{
			case ACTION_Purchase: 
			{
				//  Purchase 
				if (terminal.amount < 0)
				{
					throw new IllegalArgumentException("Payment amount is negative for sale.");
				}
				terminal.hcmPurchase();
				break;
			}
				
			case ACTION_Void:
			{
				terminal.hcmVoid();
				break;
			}
			
			case ACTION_Refund:
			{
				if (terminal.amount > 0)
				{
					throw new IllegalArgumentException("Can't refund a positive payment amount.");
				}
			
				//  Refund
				//  Negative payment amounts indicate a refund but the terminal
				//  accepts refunds as positive numbers
	
				// TODO Test
				terminal.amount = terminal.amount * -1;
				terminal.hcmRefund();
				break;
			}

			case ACTION_Preauth:
			{
				terminal.hcmPreAuth();
				break;
			}

			case ACTION_Capture:
			{
				terminal.hcmCapture();
				break;
			}

			case ACTION_Settle:
			{
				terminal.hcmSettle();
				break;
			}

			case ACTION_Reprint:
			{
				terminal.hcmRePrint();
				break;
			}

		}  // End switch

		// Need to wait here
		
		try {
			if (simThread != null)
				simThread.join(120000);
			
			producerThread.join(120000);
			consumerThread.join(120000);
		}
		catch (InterruptedException e)
		{
			
		}
		
		return isProcessedOK();
        
	}

	private void setupTerminal(String action) {

		// Create the terminal API
		terminal = new hcmTerminal();
		
		// If simulating the physical terminal, start the simulation
		if (simulation) 
		{
	
			log.info("Starting simulator");
			
			if (simulator == null)
				simulator = new HCMTerminalSimulator();
			
			if (simThread == null)
			{
					simThread  = new Thread(simulator);
					simThread.start();
			}
			
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				simThread.interrupt();
			}
			
			// Use the simulator IP and Port rather than the payment processor definitions
			terminal.terminalIp = simulator.getIp();
			terminal.terminalPort = simulator.getPort();
			
		}
		else // No simulation
		{
			
			// Connecting to the terminal through the payment processor host address (IP)
			// and port
			log.info("Connecting to terminal.");
			terminal.terminalIp = this.p_mpp.getHostAddress();
			terminal.terminalPort = this.p_mpp.getHostPort();
			
		}
		
        // CREATE THREADs - this is a classic consumer/producer multi-threaded design.
		// The producer commands the terminal and "produces" the output logs. The consumer
		// consumes the logs and handles the results of the transaction.
		log.info("Starting terminal log producer");
		producer = new HCMTerminalProducer();
        producerThread = new Thread(producer);
        producerThread.start();

        consumer = new HCMTerminalConsumer(producer, action);
        consumerThread = new Thread(consumer);
        consumerThread.start();
        
		
		//  Get the currency precision
		int precision = 2;  // cents
		terminal.amount = 0;
		terminal.invoiceNumber = 0;
		
		if (p_mp != null)
		{
			I_C_Currency currency = null;
			currency = p_mp.getC_Currency();
			if ( currency != null )
			{
				precision = currency.getStdPrecision();
			}

			//  Set the transaction amount without decimal places - if CAD, then in cents
			String stringAmount = p_mp.getPayAmt()
					.scaleByPowerOfTen(precision)
					.setScale(0, RoundingMode.HALF_UP)
					.toPlainString();
			terminal.amount = Integer.parseInt(stringAmount);

			//  Use the invoice document number as the invoice number for the terminal.
			if (p_mp.getC_Invoice_ID() > 0)
			{
				
				String documentNo = p_mp.getC_Invoice().getDocumentNo();
				documentNo = documentNo.replaceAll("\\D+","");
			    terminal.invoiceNumber = Integer.parseInt(documentNo);
			    
			}
			
			terminal.referenceNumber = p_mp.getR_PnRef();
		}		
				
		// Set the terminal clerkID to the User ID
		terminal.clerkId = Env.getAD_User_ID(Env.getCtx());

	}

	@Override
	public boolean isProcessedOK() 
	{
		
		return consumer.getProcessSuccess();
		
	}

	/**
	 * Translate the coded card type into english name
	 * @param id
	 * @return a string with the card type such as "Visa"
	 */
	private String getCardType(String id) 
	{

		// CHECK CARD TYPE
		if (id.equals("00")) 
		{

			// SET VALUE
			return "Debit";

		} 
		else if (id.equals("01")) 
		{

			// SET VALUE
			return "Visa";

		} 
		else if (id.equals("02")) 
		{

			// SET VALUE
			return "Mastercard";

		} 
		else if (id.equals("03")) 
		{

			// SET VALUE
			return "Amex";

		} 
		else if (id.equals("04")) 
		{

			// SET VALUE
			return "Diners Club";

		} 
		else if (id.equals("05")) 
		{

			// SET VALUE
			return "Discover Card";

		} 
		else if (id.equals("06")) 
		{

			// SET VALUE
			return "JCB";

		} 
		else if (id.equals("07")) 
		{

			// SET VALUE
			return "Union Pay Card";

		} 
		else if (id.equals("08")) 
		{

			// SET VALUE
			return "Other Credit Card";

		} 
		else if (id.equals("09")) 
		{

			// SET VALUE
			return "Gift Card";

		} 
		else if (id.equals("10")) 
		{

			// SET VALUE
			return "Cash";

		}
		
		return "Unknown";

	} // END FUNCTION

	/**
	 * Convert a string card Type to the ADempiere Credit Card Type codes. 
	 * @param cardType
	 * @return
	 */
	private String ccType(String cardType)
	{
        // CHECK CARD TYPE
        if (cardType.equals("Debit")) 
        {
        
        	return X_C_Payment.CREDITCARDTYPE_ATM;

        } 
        else if (cardType.equals("Visa")) 
        {
        	
        	return X_C_Payment.CREDITCARDTYPE_Visa;
        	
        } 
        else if (cardType.equals("Mastercard")) 
        {
        
        	return X_C_Payment.CREDITCARDTYPE_MasterCard;	

        } 
        else if (cardType.equals("Amex")) 
        {
        
        	return X_C_Payment.CREDITCARDTYPE_Amex;	

        } 
        else if (cardType.equals("Diners Club")) 
        {
        
        	return X_C_Payment.CREDITCARDTYPE_Diners;	

        } 
        else if (cardType.equals("Discover Card")) 
        {
        
        	return X_C_Payment.CREDITCARDTYPE_Discover;	

        } 
        else if (cardType.equals("Gift Card")) 
        {
        
        	return X_C_Payment.CREDITCARDTYPE_PurchaseCard;

        } 
        else 
        { 
        
        	// Card not known/supported. TODO Throw error?
        }

        return null;
	}
	
	/**
	 * Convert a coded cart entry method to a clear string
	 * @param id
	 * @return The clear text string describing the entry method.
	 */
	private String getCardEntry(String id) 
	{

		// CHECK FOR TENDER TYPES
		if (id.equals("0")) 
		{

			// SET VALUE
			return "Magnetic Stripe";

		} 
		else if (id.equals("1")) 
		{

			// SET VALUE
			return "Chip";

		} 
		else if (id.equals("2")) 
		{

			// SET VALUE
			return "Tap";

		} 
		else if (id.equals("3")) 
		{

			// SET VALUE
			return "Manual Entry";

		} 
		else if (id.equals("4")) 
		{

			// SET VALUE
			return "Chip Fallback to Swipe";

		} 
		else if (id.equals("5")) 
		{

			// SET VALUE
			return "Chip Fallback to Manual";

		} 
		else if (id.equals("6")) 
		{

			// SET VALUE
			return "Card not Present Manual";

		}
		
		return "Unknown";

	} // END FUNCTION

	/**
	 * Void a credit card payment.   
	 */
	@Override
	public boolean voidTrx()
	{
		return processCC("Void");		
	}

	/**
	 * Capture an authorized credit card payment.   
	 */
	public boolean captureTrx()
	{
		return processCC("Capture");		
	}

	/**
	 * Refund a settled credit card payment.   
	 */
	public boolean refundTrx()
	{
		return processCC("Refund");		
	}

	/**
	 * Preauthorize a credit card payment.   
	 */
	public boolean preAuthTrx()
	{
		return processCC("Preauth");		
	}

	/**
	 * Settle current payments.   
	 */
	public boolean settle()
	{
		return processCC("Settle");		
	}

	/**
	 * Reprint a payment.   
	 */
	public boolean reprint()
	{
		return processCC("Reprint");		
	}

	/**
	 * Close all the threads created by the payment processor.  The threads are 
	 * sent an interrupt and have to respond by ending their operation.
	 */
	public void closeAllThreads()
	{
		
		log.info("Closing threads");
		if (simulation && simThread != null && simThread.isAlive())
		{
		
			log.info("Closing sim thread");
			simThread.interrupt();
			simulator.receiptTimer.cancel();
			simulator.responseTimer.cancel();
			
		}
		
		if (producerThread != null && producerThread.isAlive())
		{
			
			log.info("Closing producer thread");
			producerThread.interrupt();
			
		}
		
		if (consumerThread != null && consumerThread.isAlive())
		{
			
			log.info("Closing consumer thread");
			consumerThread.interrupt();
			
		}
		
	}
	
	/**
	 * The result processes message.  This method will block until the process is complete
	 * or the consumer thread is interrupted.  This method should be called by process threads 
	 * and not by user interface threads. 
	 */
	public String getResult()
	{
		
		return consumer.getResult();  // Will block until complete.
		
	}
	
	@Override
	public String validate() throws IllegalArgumentException {
		return(null); // Not required for Helcim terminals
	}

}
