package com.helcim.helcim_semi_integrated;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.compiere.model.X_C_Payment;
import org.compiere.util.CLogger;

public class HCMTerminalSimulator implements Runnable {
	
	CLogger log = CLogger.getCLogger(HCMTerminalSimulator.class);

	private String ip = "127.0.0.1";
	private int port = 41234;
	
    //
    // PRIVATE PROPERTIES
    //

    private String hexResponse;
    private String hexRequest;
    private String stringResponse;
    private String stringRequest;

    private String simLog;
	private String requestType = "";
	private int amount;
	private int clerkId;
	private int invoiceNumber;
	private Object cardType;
	
    private BufferedWriter sendingStream;
    private BufferedReader receivingStream;
    private Socket hcmSocket;
    private ServerSocket serverSocket;
    
    Boolean isConnected = false;
    
    // CREATE TERMINAL TIMER
    public final Timer receiptTimer = new Timer();
    public final Timer responseTimer = new Timer();
    
	private static int batchTotal = 0;
	private static int batchTotalCount = 0;
	private static int batchSaleTotal = 0;
	private static int batchSaleTotalCount = 0;
	private static int batchRefundTotal = 0;
	private static int batchRefundTotalCount = 0;
	private static int batchVoidTotal = 0;
	private static int batchVoidTotalCount = 0;
	private static int batchCashBackTotal = 0;

	public HCMTerminalSimulator() {
		
	}

	
	@Override
	public void run() {

        // CONNECT
        try {

            // GET SERVER IP
            InetAddress serverAddr = InetAddress.getByName(ip);

            // CREATE SOCKET
            serverSocket = new ServerSocket(port);
            hcmSocket = serverSocket.accept();

            // CREATE READER AND WRITER
            receivingStream = new BufferedReader(new InputStreamReader(hcmSocket.getInputStream()));

            final InputStream input = hcmSocket.getInputStream();

            
            // START TIMER TASK for receive
            receiptTimer.scheduleAtFixedRate(new TimerTask() {


										@Override
                                        public void run() {

                                              // GET DATA
                                              hexRequest = iStreamToString(receivingStream);
                                              
                                              if (hexRequest != null && hexRequest.length() > 0)
                                              {
	
	                                              // LOG
	                                              simLog += hexRequest;
	                                              log.info("Received request: " + hexRequest);

	                                              // SEND REQUEST
//	                                              try {
//	                                            	  if (! isConnected)
//	                                            	  {
//	                                            		  isConnected = true;
//		                                            	  sendingStream.write("\nTerminal Connected\n");
//		                                            	  sendingStream.flush();
//		                                            	  Thread.sleep(1000);
//	                                            	  }
//	                                              } catch (IOException | InterruptedException e) {
//														// TODO Auto-generated catch block
//														e.printStackTrace();
//	                                              }
	                                              
	                                              // Translate request
	                                              translateRequest();
	                                              
                                              }	                                              
                                          }
                                      }, 0, 500
            );

            // START TIMER TASK for response
            responseTimer.scheduleAtFixedRate(new TimerTask() {


										@Override
                                        public void run() {

													
                                              generateResponse();
                                              
                                              sendResponse();
                                              
                                    		  
                                        	  responseTimer.cancel();
                                        	  receiptTimer.cancel();
                                        	  
                                          }
                                      }, 0, 2000
            );

        } 
        catch (Exception e) 
        {

            this.simLog += "Error: " + e.getMessage() + "\n";
            log.severe(e.getMessage());

        }

	}
	
    public String iStreamToString(BufferedReader rd) {

        char[] line = new char[200];
        StringBuilder sb = new StringBuilder();
        try {
        	Boolean done = false;
            while (rd.ready() && !done) {
            	done = rd.read(line) == -1;
            	if (line != null && line.length > 0)
            	{
            		sb.append(line);
            		line = new char[200];
            	}
            }
//            rd.close();

        } catch (IOException e) {

            e.fillInStackTrace();

        }

        return sb.toString();
    }

    private synchronized void translateRequest() {

        // SET STRING RESPONSE
        this.stringRequest = this.hexRequest;

        if (this.stringRequest != null) {

            // REPLACE DC1 WITH NOTHING
            this.stringRequest = this.stringRequest.replace((char) 0x11, (char) 0x00);

            // REPLACE FS WITH SPACE
            this.stringRequest = this.stringRequest.replace((char) 0x1c, (char) 0x20);

            // REPLACE GS WITH SPACE
            this.stringRequest = this.stringRequest.replace((char) 0x1d, (char) 0x20);

            // SPLIT STRING INTO ARRAY BASED ON SPACES
            String[] requestContents = this.stringRequest.split(" ");
            
            if (requestContents.length == 0)
            	requestContents[0] = this.stringRequest;
            
            // LOOP THROUGH ARRAY
            // Need the index
            for (int i = 0; i < requestContents.length; i++) {

                try 
                {
                	String requestItem = requestContents[i];
                	
                	// Purchase
                    if (i == 0 && requestItem.substring(0, 2).equals("00"))
                    {
                    	requestType = "Purchase";
                    }
                    else if (i == 0 && requestItem.substring(0, 2).equals("04"))
                    {
                    	requestType = "Capture";
                    }
                    else if (i == 0 && requestItem.substring(0, 2).equals("03"))
                    {
                    	requestType = "Refund";
                    }
                    else if (i == 0 && requestItem.substring(0, 2).equals("05"))
                    {
                    	requestType = "Void";
                    }
                    else if (i == 0 && requestItem.substring(0, 2).equals("07"))
                    {
                    	requestType = "Preauth";
                    }
                    else if (i == 0 && requestItem.substring(0, 2).equals("20"))
                    {
                    	requestType = "Settle";
                    }
                    else if (i == 0 && requestItem.substring(0, 2).equals("22"))
                    {
                    	requestType = "Reprint";
                    }
                    else if (requestItem.substring(0, 3).equals("001"))
                    {
                    	this.amount = Integer.parseInt(requestContents[i++]);
                    }
                    else if (requestItem.substring(0, 3).equals("003"))
                    {
                    	this.clerkId = Integer.parseInt(requestContents[i++]);
                    }
                    else if (requestItem.substring(0, 3).equals("004"))
                    {
                    	this.invoiceNumber = Integer.parseInt(requestContents[i++]);
                    }
                    
	            } catch (Exception e) {
	
	
	            }
            }
            notify();
        }
    }

    private synchronized void generateResponse() {

        // SET STRING RESPONSE
        this.stringResponse = "";
        
        while (requestType.length() == 0)
        {
	    	try {
				wait();
			} catch (InterruptedException e) {
				// Reset the thread interrupt state
				Thread.currentThread().interrupt();
			}
        }
        
        if (this.requestType.equals("Purchase")) 
        {
        	this.setBatchTotal(requestType, this.amount);
        	
        	this.stringResponse += "601"
        			+ this.getTID()
                    + " ";

        	this.stringResponse += "602"
        			+ this.getMID()
                    + " ";

        	this.stringResponse += "300"			// Card Type
                    + this.getCardType()
                    + " ";

        	this.stringResponse += "302"
                    + this.getCardNumber()
                    + " ";

        	this.stringResponse += "306"			// Card Entry
                    + this.getCardEntry()
                    + " ";
        	
        	this.stringResponse += "402"
                    + this.getResponseMessage()
                    + " ";

        	this.stringResponse += "112"
                    + this.getReferenceNumber()
                    + " ";

        	this.stringResponse += "400"
                    + this.getAuthorizationNumber()
                    + " ";

        	this.stringResponse += "401"
        			+ this.getResponseCode()
                    + " ";

        	this.stringResponse += "500"
        			+ this.getBatchNumber()
                    + " ";
        	
        }  
        else if (this.requestType.equals("Refund") || this.requestType.equals("Void")) 
        {
        	
        	this.setBatchTotal(requestType, this.amount);

        	this.stringResponse += "601"
        			+ this.getTID()
                    + " ";

        	this.stringResponse += "602"
        			+ this.getMID()
                    + " ";

        	this.stringResponse += "300"			// Card Type
                    + this.getCardType()
                    + " ";

        	this.stringResponse += "302"
                    + this.getCardNumber()
                    + " ";

        	this.stringResponse += "306"			// Card Entry
	                + this.getCardEntry()
	                + " ";
	        	
        	this.stringResponse += "402"
                    + this.getResponseMessage()
                    + " ";

        	this.stringResponse += "112"
                    + this.getReferenceNumber()
                    + " ";

        	this.stringResponse += "400"
                    + this.getAuthorizationNumber()
                    + " ";

        	this.stringResponse += "401"
        			+ this.getResponseCode()
                    + " ";

        	this.stringResponse += "500"
        			+ this.getBatchNumber()
                    + " ";
        }
        else if (this.requestType.equals("Settle")) 
        {

        	this.stringResponse += "500"
        			+ this.getBatchNumber()
                    + " ";
        	
        	this.stringResponse += "502"
        			+ this.getBatchTotal()
                    + " ";
        	
        	this.stringResponse += "503"
        			+ this.getBatchTotalCount()
                    + " ";
        	
        	this.stringResponse += "504"
        			+ this.getBatchSaleTotal()
                    + " ";
        	
        	this.stringResponse += "505"
        			+ this.getBatchSaleTotalCount()
                    + " ";
        	
        	this.stringResponse += "506"
        			+ this.getBatchRefundTotal()
                    + " ";
        	
        	
        	this.stringResponse += "507"
        			+ this.getBatchRefundTotalCount()
                    + " ";
        	
        	this.stringResponse += "508"
        			+ this.getBatchVoidTotal()
                    + " ";
        	
        	this.stringResponse += "509"
        			+ this.getBatchVoidTotalCount()
                    + " ";
        	
        	this.stringResponse += "512"
        			+ this.getBatchCashBackTotal()
                    + " ";
      	
        } // End Batch

        if (this.stringResponse != null) 
        {

            // REPLACE DC1 WITH NOTHING
            this.stringResponse = this.stringResponse.replace((char) 0x00, (char) 0x11);

            // REPLACE FS WITH SPACE
            this.stringResponse = this.stringResponse.replace((char) 0x20, (char) 0x1c);

            // REPLACE GS WITH SPACE
            this.stringResponse = this.stringResponse.replace((char) 0x20, (char) 0x1d);
        }
        
//      this.stringResponse += "\n";
        
//        if (requestType.equals("Void") || stateCounter==3)
//        {
//		    //  Need to add a terminating end of line
//		    this.stringResponse += "Ended Connection\n";
//        }

        this.hexResponse = this.stringResponse;

    } // END FUNCTION

	private int getBatchCashBackTotal() {
		return batchCashBackTotal;
	}


	private int getBatchVoidTotalCount() {
		return batchVoidTotalCount;
	}


	private int getBatchVoidTotal() {
		return batchVoidTotal;
	}


	private int getBatchRefundTotalCount() {
		return batchRefundTotalCount;
	}


	private int getBatchRefundTotal() {
		return batchRefundTotal;
	}


	private int getBatchSaleTotalCount() {
		return batchSaleTotalCount;
	}


	private int getBatchSaleTotal() {
		return batchSaleTotal;
	}


	private void sendResponse() {
        // SEND REQUEST
        try {
      	  log.info("Terminal sending response: " + hexResponse);
          sendingStream = new BufferedWriter(new OutputStreamWriter(hcmSocket.getOutputStream()));
      	  sendingStream.write(hexResponse);
      	  sendingStream.flush();
      	  
        } catch (IOException e) {
				e.printStackTrace();
        }
        finally
        {
        	try {
      		  sendingStream.close();
    		  receivingStream.close();
    		  serverSocket.close();
    		  hcmSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

	private String getBatchNumber() {
		Random rand = new Random();
		int refNum = rand.nextInt(1000);
		DecimalFormat myFormatter = new DecimalFormat("000");
		return myFormatter.format(refNum);
	}

	private String getMID() {
		return "OFC04326";
	}

	private String getResponseCode() {
		return "AA";
	}

	private String getTID() {
		return "POS91921833";
	}

	private String getAuthorizationNumber() {
		Random rand = new Random();
		int refNum = rand.nextInt(100000);
		DecimalFormat myFormatter = new DecimalFormat("00000");
		return myFormatter.format(refNum);
	}

	private String getReferenceNumber() {
		Random rand = new Random();
		int refNum = rand.nextInt(1000000);
		DecimalFormat myFormatter = new DecimalFormat("000000");
		return myFormatter.format(refNum);
	}

	private String getCardNumber() {
		
		Random rand = new Random();
		int lastFour = rand.nextInt(10000);
		DecimalFormat myFormatter = new DecimalFormat("0000");
		String output = myFormatter.format(lastFour);
		return "************" + output;
	}

	private String getResponseMessage() {
		
		return "APPROVAL";
	}

	private String getCardEntry() {
		
		return "2";
	}

	private String getCardType()
	{
		
		cardType = "Visa";
		
        // CHECK CARD TYPE
        if (cardType.equals("Debit")) {
        	return "00";

        } else if (cardType.equals("Visa")) {
        	return "01";
        	
        } else if (cardType.equals("Mastercard")) {
        	return "02";	

        } else if (cardType.equals("Amex")) {
        	return "03";	

        } else if (cardType.equals("Diners Club")) {
        	return "04";	

        } else if (cardType.equals("Discover Card")) {
        	return "05";	

        } else if (cardType.equals("Gift Card")) {
        	return "09";

        } else { 
        	  // Card not known/supported. TODO Throw error?
        }

        return null;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}


	public int getBatchTotal() {
		return batchTotal;
	}


	public void setBatchTotal(String requestType, int trxAmount) {
		
    	batchTotalCount ++;

    	if (this.requestType.equals("Purchase")) 
        {
        	batchTotal += this.amount;
            batchSaleTotal += this.amount;
            batchSaleTotalCount ++;
         }  
        else if (this.requestType.equals("Refund")) 
        {
        	batchTotal -= this.amount;
            batchRefundTotal += this.amount;
            batchRefundTotalCount ++;
        }
        else if (this.requestType.equals("Void")) 
        {
        	batchTotal -= this.amount;
            batchVoidTotal += this.amount;
            batchVoidTotalCount ++;
        }
        else if (this.requestType.equals("CashBack")) 
        {
        	batchTotal -= this.amount;
            batchVoidTotal += this.amount;
            batchVoidTotalCount ++;
        }
	}


	private void zeroBatch() {
		batchTotal = 0;
		batchSaleTotal = 0;
		batchRefundTotal = 0;
		batchVoidTotal = 0;
		batchTotalCount = 0;
		batchSaleTotalCount = 0;
		batchRefundTotalCount = 0;
		batchVoidTotalCount = 0;
	}
	
	public int getBatchTotalCount() {
		return batchTotalCount;
	}


	public void setBatchTotalCount(int batchTotalCount) {
		this.batchTotalCount = batchTotalCount;
	}

}
