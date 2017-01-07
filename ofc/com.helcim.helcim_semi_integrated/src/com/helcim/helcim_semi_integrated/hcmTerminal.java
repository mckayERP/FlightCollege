package com.helcim.helcim_semi_integrated;

/*
 *
 * Helcim Semi Integrated Class
 *
 * Created by Helcim on 2016-01
 * Copyright © 2016 Helcim. All rights reserved.
 *
 */

//import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import org.compiere.util.CLogger;

public class hcmTerminal {

	CLogger log = CLogger.getCLogger(hcmTerminal.class);

	//
	// INPUT PROPERTIES
	//

	public String terminalIp;
	public Integer terminalPort;
	public Integer amount;
	public Integer invoiceNumber;
	public Integer clerkId;

	//
	// OUTPUT PROPERTIES
	//

	public String MID;
	public Integer response;
	public String responseMessage;
	public String responseCode;
	public Integer batchTotal;
	public Integer batchNumber;
	public Integer batchTotalCount;
	public Integer batchSaleTotal;
	public Integer batchSaleTotalCount;
	public Integer batchRefundTotal;
	public Integer batchRefundTotalCount;
	public Integer batchVoidTotal;
	public Integer batchVoidTotalCount;
	public Integer batchCashBackTotal;
	public String cardType;
	public String cardEntry;
	public String cardNumber;
	public String TID;
	public String referenceNumber;
	public String authorizationNumber;
	public Float transactionAmount;
	public String connectionLog;

	//
	// PRIVATE PROPERTIES
	//

	private String hexResponse;
	private String hexRequest;
	private String stringResponse;
	private String stringRequest;

	private Socket hcmSocket = null;
	private BufferedWriter sendingStream = null;
	private BufferedReader receivingStream = null;

	public hcmTerminal() {

	}

	public void hcmPurchase() {

		// LOG
		this.connectionLog = "Attempting Sale";
		log.info(this.connectionLog);

		// CONSTRUCT PURCHASE STRING
		this.stringRequest = "00 001" + this.amount + " 003" + this.clerkId
				+ " 004" + this.invoiceNumber;

		// PROCESS
		this.process();

	} // END FUNCTION

	public void hcmCapture() {

		// LOG
		this.connectionLog = "Attempting Capture";

		// CONSTRUCT PURCHASE STRING
		this.stringRequest = "04 001" + this.amount + " 003" + this.clerkId
				+ " 004" + this.invoiceNumber;

		// PROCESS
		this.process();

	} // END FUNCTION

	public void hcmRefund() {

		// LOG
		this.connectionLog = "Attempting Refund";

		// CONSTRUCT PURCHASE STRING
		this.stringRequest = "03 001" + this.amount + " 003" + this.clerkId
				+ " 004" + this.invoiceNumber;

		// PROCESS
		this.process();

	} // END FUNCTION

	public void hcmVoid() {

		// LOG
		this.connectionLog = "Attempting Void";

		// CONSTRUCT PURCHASE STRING
		this.stringRequest = "05";

		// PROCESS
		this.process();

	} // END FUNCTION

	public void hcmPreAuth() {

		// LOG
		this.connectionLog = "Attempting PreAuth";

		// CONSTRUCT PURCHASE STRING
		this.stringRequest = "07 001" + this.amount + " 003" + this.clerkId
				+ " 004" + this.invoiceNumber;

		// PROCESS
		this.process();

	} // END FUNCTION

	public void hcmSettle() {

		// LOG
		this.connectionLog = "Attempting Settle";

		// CONSTRUCT PURCHASE STRING
		this.stringRequest = "20";

		// PROCESS
		this.process();

	} // END FUNCTION

	public void hcmRePrint() {

		// LOG
		this.connectionLog = "Attempting Reprint";

		// CONSTRUCT PURCHASE STRING
		this.stringRequest = "22";

		// PROCESS
		this.process();

	} // END FUNCTION

	public void hcmTestConnection() {

		try {

			// GET SERVER IP
			InetAddress serverAddr = InetAddress.getByName(this.terminalIp);

			// CREATE SOCKET
			Socket hcmSocket = new Socket(serverAddr, this.terminalPort);

			// LOG
			this.connectionLog += "Connected\n\n";

		} catch (Exception e) {

			// LOG
			this.connectionLog += "Cannot Connect" + e.getMessage() + "\n\n";

		}

	} // END FUNCTION

	private void process() {

		// TRANSLATE REQUEST
		this.translateRequest();

		// CONNECT
		try {

			if (hcmSocket == null) {

				// GET SERVER IP
				InetAddress serverAddr = InetAddress.getByName(this.terminalIp);

				// CREATE SOCKET
				hcmSocket = new Socket(serverAddr, this.terminalPort);

				// CREATE READER AND WRITER
				sendingStream = new BufferedWriter(new OutputStreamWriter(
						hcmSocket.getOutputStream()));
				receivingStream = new BufferedReader(new InputStreamReader(
						hcmSocket.getInputStream()));

				// CREATE TIMER
				final Timer timer = new Timer();

				// START TIMER TASK
				timer.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {

						// SEND REQUEST
						try {
							if (hexRequest != null && hexRequest.length() > 0) {
								log.info("Sending request:" + stringRequest);
								sendingStream.write(hexRequest);
								sendingStream.flush();
								hexRequest = "";
							}
						} catch (IOException e) {
							timer.cancel();
							e.printStackTrace();
						}

						// GET DATA
						hexResponse = iStreamToString(receivingStream);

						if (hexResponse != null && hexResponse.length() > 0) {

							// LOG
							connectionLog += hexResponse;

							// TRANSLATE
							translateResponse();

							if (connectionLog.contains("Ended Connection")
									|| Thread.currentThread().isInterrupted())
								timer.cancel();

						}

					}
				}, 0, 500);

			}

		} catch (Exception e) {

			this.connectionLog += "Error: " + e.getMessage() + "\n\n";

		}

	} // END FUNCTION

	public String iStreamToString(BufferedReader rd) {

		String line = "";
		StringBuilder sb = new StringBuilder();
		try {
			while (rd.ready()) {
				line = rd.readLine();
				log.info("Read line: " + line);
				if (line != null && line.length() > 0) {
					sb.append(line).append("\n");
					line = "";
				}
			}
			// rd.close();

		} catch (IOException e) {

			e.fillInStackTrace();

		}

		return sb.toString();
	}

	// public String iStreamToString(InputStream is1) {
	//
	// BufferedReader rd = new BufferedReader(new InputStreamReader(is1));
	// String line;
	// StringBuilder sb = new StringBuilder();
	// try {
	// while (rd.ready() && (line = rd.readLine()) != null) {
	// sb.append(line);
	// }
	// rd.close();
	//
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	//
	// }
	//
	// return sb.toString();
	// }

	private void translateRequest() {

		// REPLACE CHARACTERS SPACE WITH FS
		this.stringRequest = this.stringRequest.replace((char) 0x20,
				(char) 0x1C);

		this.hexRequest = this.stringRequest;
	}

	private void translateResponse() {

		// SET STRING RESPONSE
		this.stringResponse = this.hexResponse;

		if (this.stringResponse != null) {

			// REPLACE DC1 WITH NOTHING
			this.stringResponse = this.stringResponse.replace((char) 0x11,
					(char) 0x00);

			// REPLACE FS WITH SPACE
			this.stringResponse = this.stringResponse.replace((char) 0x1c,
					(char) 0x20);

			// REPLACE GS WITH SPACE
			this.stringResponse = this.stringResponse.replace((char) 0x1d,
					(char) 0x20);

			// SPLIT STRING INTO ARRAY BASED ON SPACES
			String[] responseContents = this.stringResponse.split(" ");

			// LOOP THROUGH ARRAY
			for (String responseItem : responseContents) {

				try {

					// CHECK CONTENTS
					if (responseItem.substring(0, 3).equals("300")) {

						//
						// CARD TYPE
						//

						// CHECK CARD TYPE
						this.getCardType(responseItem.substring(3));

					} else if (responseItem.substring(0, 3).equals("306")) {

						//
						// CARD ENTRY
						//

						// CHECK CARD ENTRY
						this.getCardEntry(responseItem.substring(3));

					} else if (responseItem.substring(0, 3).equals("402")) {

						//
						// MESSAGE
						//

						// CHECK IF APPROVAL
						if (responseItem.substring(3).equals("APPROVAL")) {

							// SET RESPONSE AND MESSAGE
							this.response = 1;
							this.responseMessage = "Approved";

							// LOG
							this.connectionLog += "\r\nApproved";

						} else {

							// SET RESPONSE AND MESSAGE
							this.response = 0;
							this.responseMessage = "Declined";

							// LOG
							this.connectionLog += "\r\nDeclined";

						}

					} else if (responseItem.substring(0, 3).equals("302")) {

						//
						// CARD NUMBER
						//

						// SET CARD NUMBER
						this.cardNumber = responseItem.substring(3);

					} else if (responseItem.substring(0, 3).equals("112")) {

						//
						// REF NUMBER
						//

						// SET REF NUMBER
						this.referenceNumber = responseItem.substring(3);

					} else if (responseItem.substring(0, 3).equals("400")) {

						//
						// AUTH NUMBER
						//

						// SET AUTH NUMBER
						this.authorizationNumber = responseItem.substring(3);

					} else if (responseItem.substring(0, 3).equals("601")) {

						//
						// TID
						//

						// SET TID
						this.TID = responseItem.substring(3);

					} else if (responseItem.substring(0, 3).equals("401")) {

						//
						// RESPONSE CODE
						//

						// SET RESPONSE CODE
						this.responseCode = responseItem.substring(3);

					} else if (responseItem.substring(0, 3).equals("602")) {

						//
						// MID
						//

						// SET MID
						this.MID = responseItem.substring(3);

					} else if (responseItem.substring(0, 3).equals("500")) {

						//
						// BATCH NUMBER
						//

						// SET BATCH NUMBER
						this.batchNumber = Integer.parseInt(responseItem
								.substring(3));

					} else if (responseItem.substring(0, 3).equals("502")) {

						//
						// BATCH TOTAL
						//

						// SET BATCH TOTAL
						this.batchTotal = Integer.parseInt(responseItem
								.substring(3));

					} else if (responseItem.substring(0, 3).equals("503")) {

						//
						// BATCH TOTAL COUNT
						//

						// SET BATCH TOTAL COUNT
						this.batchTotalCount = Integer.parseInt(responseItem
								.substring(3));

					} else if (responseItem.substring(0, 3).equals("504")) {

						//
						// BATCH SALE TOTAL
						//

						// SET BATCH SALE TOTAL
						this.batchSaleTotal = Integer.parseInt(responseItem
								.substring(3));

					} else if (responseItem.substring(0, 3).equals("505")) {

						//
						// BATCH SALE COUNT
						//

						// SET BATCH SALE COUNT
						this.batchSaleTotalCount = Integer
								.parseInt(responseItem.substring(3));

					} else if (responseItem.substring(0, 3).equals("506")) {

						//
						// BATCH REFUND TOTAL
						//

						// SET BATCH REFUND TOTAL
						this.batchRefundTotal = Integer.parseInt(responseItem
								.substring(3));

					} else if (responseItem.substring(0, 3).equals("507")) {

						//
						// BATCH REFUND COUNT
						//

						// SET BATCH REFUND COUNT
						this.batchRefundTotalCount = Integer
								.parseInt(responseItem.substring(3));

					} else if (responseItem.substring(0, 3).equals("508")) {

						//
						// BATCH VOID AMOUNT
						//

						// SET BATCH VOID AMOUNT
						this.batchVoidTotal = Integer.parseInt(responseItem
								.substring(3));

					} else if (responseItem.substring(0, 3).equals("509")) {

						//
						// BATCH VOID COUNT
						//

						// SET BATCH VOID COUNT
						this.batchVoidTotalCount = Integer
								.parseInt(responseItem.substring(3));

					} else if (responseItem.substring(0, 3).equals("512")) {

						//
						// BATCH CASHBACK AMOUNT
						//

						// SET BATCH CASHBACK AMOUNT
						this.batchCashBackTotal = Integer.parseInt(responseItem
								.substring(3));

					}

				} catch (Exception e) {

				}

			}
		}

	} // END FUNCTION

	private void getCardType(String id) {

		// CHECK CARD TYPE
		if (id.equals("00")) {

			// SET VALUE
			this.cardType = "Debit";

		} else if (id.equals("01")) {

			// SET VALUE
			this.cardType = "Visa";

		} else if (id.equals("02")) {

			// SET VALUE
			this.cardType = "Mastercard";

		} else if (id.equals("03")) {

			// SET VALUE
			this.cardType = "Amex";

		} else if (id.equals("04")) {

			// SET VALUE
			this.cardType = "Diners Club";

		} else if (id.equals("05")) {

			// SET VALUE
			this.cardType = "Discover Card";

		} else if (id.equals("06")) {

			// SET VALUE
			this.cardType = "JCB";

		} else if (id.equals("07")) {

			// SET VALUE
			this.cardType = "Union Pay Card";

		} else if (id.equals("08")) {

			// SET VALUE
			this.cardType = "Other Credit Card";

		} else if (id.equals("09")) {

			// SET VALUE
			this.cardType = "Gift Card";

		} else if (id.equals("10")) {

			// SET VALUE
			this.cardType = "Cash";

		}

	} // END FUNCTION

	private void getCardEntry(String id) {

		// CHECK FOR TENDER TYPES
		if (id.equals("0")) {

			// SET VALUE
			this.cardEntry = "Magnetic Stripe";

		} else if (id.equals("1")) {

			// SET VALUE
			this.cardEntry = "Chip";

		} else if (id.equals("2")) {

			// SET VALUE
			this.cardEntry = "Tap";

		} else if (id.equals("3")) {

			// SET VALUE
			this.cardEntry = "Manual Entry";

		} else if (id.equals("4")) {

			// SET VALUE
			this.cardEntry = "Chip Fallback to Swipe";

		} else if (id.equals("5")) {

			// SET VALUE
			this.cardEntry = "Chip Fallback to Manual";

		} else if (id.equals("6")) {

			// SET VALUE
			this.cardEntry = "Card not Present Manual";

		}

	} // END FUNCTION

} // END CLASS
