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

package com.mckayerp.ftu.process;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.compiere.model.MAttachment;
import org.compiere.model.MMailText;
import org.compiere.model.MNote;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Trx;

import com.mckayerp.ftu.model.MFTUStudent;

/** Generated Process for (Batch EMail TL11B Forms)
 *  @author ADempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public class TL11B extends TL11BAbstract
{
	/** Recipient List to prevent duplicate mails	*/
	private ArrayList<Integer>	m_list = new ArrayList<Integer>();
	private static final int TL11B_FORM_PROCESS_ID = 1000017;

	@Override
	protected void prepare()
	{
		super.prepare();
		
	}

	@Override
	protected String doIt() throws Exception
	{

		if (Ini.isClient())
		{
			if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog
				(null, "This process will email all registered vocational students\n"
						+ "a copy of their TL11B form.\n\nAre you sure you want to do\n"
						+ "this?",
						"TL11B Batch Email Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
			{
					return "Aborted";
			}
		}

		log.info("R_MailText_ID=" + this.getEmailTemplateId());
		//	Mail Test
		m_mailText = new MMailText (getCtx(), getEmailTemplateId(), get_TrxName());
		if (m_mailText.getR_MailText_ID() == 0)
			throw new Exception ("Not found @R_MailText_ID@=" + getEmailTemplateId());
		//	Client Info
		if (m_client.getAD_Client_ID() == 0)
			throw new Exception ("Not found @AD_Client_ID@");
		if (m_client.getSMTPHost() == null || m_client.getSMTPHost().length() == 0)
			throw new Exception ("No SMTP Host found");
		
		BigDecimal report_year = Env.ZERO;
		try{
			report_year = new BigDecimal(Integer.parseInt(getYear()));
		}
		catch (NumberFormatException e) {
			throw new Exception ("Year is not an Integer value: " + getYear());
		}

		// Find the student or all students
		String where = MFTUStudent.COLUMNNAME_IsVocational + "='Y'";
		
		if (getVocationalStudentId() > 0) 
		{
			where += " AND " + MFTUStudent.COLUMNNAME_C_BPartner_ID + "=" + getVocationalStudentId();
		}
		
		List<MFTUStudent> students = new Query(getCtx(), MFTUStudent.Table_Name, where, get_TrxName())
						.setClient_ID()
						.setOnlyActiveRecords(true)
						.list();
		
		for (MFTUStudent student : students)
		{

			ArrayList<File> attachments = new ArrayList<File>();
			int ad_process_id = TL11B_FORM_PROCESS_ID ;
			
			String studentName = student.getC_BPartner().getName();
			
			MProcess worker = new MProcess(getCtx(),ad_process_id,get_TrxName());
			worker.setClassname("org.compiere.report.ReportStarter");  // TODO Set in the database?

			MPInstance instance = new MPInstance(worker, 0);

			ProcessInfo pi = new ProcessInfo("TL11B Form Batch", ad_process_id);
			pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());
			pi.setIsBatch(true);
			pi.addParameter(C_BPartner_ID, new BigDecimal(student.getC_BPartner_ID()), "");
			pi.addParameter("Report_Year", report_year, "");

			worker.processIt(pi, Trx.get(get_TrxName(), true));
			
			File attachment = pi.getPDFReport();
			
			if (attachment == null)
			{
				log.info("No TL11B Form data found for " + student.getC_BPartner().getName() + " in year " + report_year);
				// TODO Translate
				addLog(0, null, null, "No TL11B Form data found for " + student.getC_BPartner().getName() + " in year " + report_year);
				continue;
			}

			File temp =	File.createTempFile("TL11BForm-" +getYear() + "-" + studentName.replaceAll("[,.] ", "_") + "_", ".pdf");
			temp.delete();  // Remove the file. Just capture the name or the renameTo will fail.
			if(attachment.renameTo(temp))
			{
				attachment = temp;
			}
			else
			{
				log.warning("Unable to rename attachment: " + attachment.getPath());
			}
			
			attachments.add(attachment);

			int countMail = 0;

			MUser[] users = MUser.getOfBPartner(getCtx(), student.getC_BPartner_ID(), get_TrxName());
			
			for (MUser user : users) {
				
//				int user_id = 1000893; // user.getAD_User_ID()
				int user_id = user.getAD_User_ID();
							
				// Add info for parsing context
				m_mailText.setUser(user_id);
				m_mailText.setBPartner(user.getC_BPartner_ID());
				m_mailText.setProcessInfo(pi);
				String subject = m_mailText.getMailHeader();
				String message = m_mailText.getMailText(true);

				if (user.isNotificationEMail()) {
					
					//	Prevent duplicate emails
					Integer ii = new Integer (user.getAD_User_ID());
					if (m_list.contains(ii))
						return null;
					m_list.add(ii);
					//
					if (m_client.sendEMailAttachments (user_id, subject, message, attachments, m_mailText.isHtml()))
					{						
						countMail ++;
						log.fine(user.getEMail());
						addLog(0, null, null, "@OK@" + " - " + studentName + " " + user.getEMail());

					}
					else 
					{
						log.warning("FAILURE - " + user.getEMail());						
						addLog(0, null, null, "@ERROR@" + " - " + studentName + " " + user.getEMail());
					}
				}

				if (user.isNotificationNote()) {
					Trx trx = null;
					try {
						trx = Trx.get(Trx.createTrxName("TL11B_NU"), true);
						// Notice
						int AD_Message_ID = 52244;  /* TODO - Hardcoded message=notes */
						MNote note = new MNote(getCtx(), AD_Message_ID, user.getAD_User_ID(), trx.getTrxName());
						note.setClientOrg(user.getAD_Client_ID(), user.getAD_Org_ID());
						note.setTextMsg(message);
						note.saveEx();
						// Attachment
						MAttachment noteAttachment = new MAttachment (getCtx(), MNote.Table_ID, note.getAD_Note_ID(), trx.getTrxName());
						for (File f : attachments) {
							noteAttachment.addEntry(f);
						}
						noteAttachment.setTextMsg(message);
						noteAttachment.saveEx();
						countMail++;
						log.fine(user.getName() + " Note added.");
						addLog(0, null, null, "@OK@" + " - " + user.getName() + "@" + MNote.COLUMNNAME_AD_Note_ID + "@");
						trx.commit();
					} 
					catch (Throwable e) {
						if (trx != null) trx.rollback();
						log.warning("Failure - " + user.getName() + " Note failed.");
						addLog(0, null, null, "@ERROR@" + " - " + user.getName() + "@" + MNote.COLUMNNAME_AD_Note_ID + "@");
					} 
					finally {
						if (trx != null) trx.close();
					}
				}
			}
		}
		return "";
	}
}