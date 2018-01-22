/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
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

package com.mckayerp.process;

import org.compiere.model.MClient;
import org.compiere.model.MMailText;
import org.compiere.process.SvrProcess;
/** Generated Process for (Batch EMail TL11B Forms)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public abstract class TL11BAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "FTU_TL11B_Batch";
	/** Process Name 	*/
	private static final String NAME = "Batch EMail TL11B Forms";
	/** Process Id 	*/
	private static final int ID = 1000052;
 
	/**	Parameter Name for CalendarYear	*/
	public static final String CalendarYear = "CalendarYear";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for IsEmailAutomatically	*/
	public static final String IsEmailAutomatically = "IsEmailAutomatically";
	/**	Parameter Name for R_MailText_ID	*/
	public static final String R_MailText_ID = "R_MailText_ID";

	/**	Parameter Value for year	*/
	private String year;
	/**	Parameter Value for vocationalStudentId	*/
	private int vocationalStudentId;
	/**	Parameter Value for isEmailAutomatically	*/
	private boolean isEmailAutomatically;
	/**	Parameter Value for emailTemplateId	*/
	private int emailTemplateId;
	
	/**	Client for the process	*/
	protected MClient m_client;
	/** Mail template 	*/
	protected MMailText m_mailText;
 

	@Override
	protected void prepare()
	{
		year = getParameterAsString(CalendarYear);
		vocationalStudentId = getParameterAsInt(C_BPartner_ID);
		isEmailAutomatically = getParameterAsBoolean(IsEmailAutomatically);
		emailTemplateId = getParameterAsInt(R_MailText_ID);
		
		m_client = MClient.get(getCtx(), getAD_Client_ID());
		
	}

	/**	 Getter Parameter Value for year	*/
	protected String getYear() {
		return year;
	}

	/**	 Getter Parameter Value for vocationalStudentId	*/
	protected int getVocationalStudentId() {
		return vocationalStudentId;
	}

	/**	 Getter Parameter Value for isEmailAutomatically	*/
	protected boolean isEmilAutomatically() {
		return isEmailAutomatically;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}

	protected int getEmailTemplateId() {
		return emailTemplateId;
	}

	protected void setEmailTemplateId(int emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}
}