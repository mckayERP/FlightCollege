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
/** Generated Model - DO NOT CHANGE */
package com.mckayerp.ftu.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for FTU_Student
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_FTU_Student extends PO implements I_FTU_Student, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160721L;

    /** Standard Constructor */
    public X_FTU_Student (Properties ctx, int FTU_Student_ID, String trxName)
    {
      super (ctx, FTU_Student_ID, trxName);
      /** if (FTU_Student_ID == 0)
        {
			setC_BPartner_ID (0);
			setFTU_Student_ID (0);
			setInternational (false);
// N
			setStudentNum (null);
        } */
    }

    /** Load Constructor */
    public X_FTU_Student (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_FTU_Student[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Date Departed.
		@param DateDeparted 
		Date the student left the program and stopped being a student.
	  */
	public void setDateDeparted (Timestamp DateDeparted)
	{
		set_Value (COLUMNNAME_DateDeparted, DateDeparted);
	}

	/** Get Date Departed.
		@return Date the student left the program and stopped being a student.
	  */
	public Timestamp getDateDeparted () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDeparted);
	}

	/** Set Date Enrolled.
		@param DateEnrolled 
		The date the student was enrolled or matriculated.
	  */
	public void setDateEnrolled (Timestamp DateEnrolled)
	{
		set_Value (COLUMNNAME_DateEnrolled, DateEnrolled);
	}

	/** Get Date Enrolled.
		@return The date the student was enrolled or matriculated.
	  */
	public Timestamp getDateEnrolled () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateEnrolled);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public com.mckayerp.ftu.model.I_FTU_Class getFTU_Class() throws RuntimeException
    {
		return (com.mckayerp.ftu.model.I_FTU_Class)MTable.get(getCtx(), com.mckayerp.ftu.model.I_FTU_Class.Table_Name)
			.getPO(getFTU_Class_ID(), get_TrxName());	}

	/** Set Class ID.
		@param FTU_Class_ID Class ID	  */
	public void setFTU_Class_ID (int FTU_Class_ID)
	{
		if (FTU_Class_ID < 1) 
			set_Value (COLUMNNAME_FTU_Class_ID, null);
		else 
			set_Value (COLUMNNAME_FTU_Class_ID, Integer.valueOf(FTU_Class_ID));
	}

	/** Get Class ID.
		@return Class ID	  */
	public int getFTU_Class_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Class_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Student ID.
		@param FTU_Student_ID Student ID	  */
	public void setFTU_Student_ID (int FTU_Student_ID)
	{
		if (FTU_Student_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_Student_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_Student_ID, Integer.valueOf(FTU_Student_ID));
	}

	/** Get Student ID.
		@return Student ID	  */
	public int getFTU_Student_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_Student_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FundingSource AD_Reference_ID=1000050 */
	public static final int FUNDINGSOURCE_AD_Reference_ID=1000050;
	/** Self-Funded Students = 1SF */
	public static final String FUNDINGSOURCE_Self_FundedStudents = "1SF";
	/** Ontario Student Assistance Program (OSAP) = 2OSAP */
	public static final String FUNDINGSOURCE_OntarioStudentAssistanceProgramOSAP = "2OSAP";
	/** Employment Ontario = 3EO */
	public static final String FUNDINGSOURCE_EmploymentOntario = "3EO";
	/** Workplace Safety and Insurance Board = 4WSIB */
	public static final String FUNDINGSOURCE_WorkplaceSafetyAndInsuranceBoard = "4WSIB";
	/** Exclusively Employers (Corporate Training) = 5EE */
	public static final String FUNDINGSOURCE_ExclusivelyEmployersCorporateTraining = "5EE";
	/** Exclusively Aboriginal Band Councils = 6EA */
	public static final String FUNDINGSOURCE_ExclusivelyAboriginalBandCouncils = "6EA";
	/** Other - Specify Funder = 7Other */
	public static final String FUNDINGSOURCE_Other_SpecifyFunder = "7Other";
	/** Set Funding Source.
		@param FundingSource 
		The main (>50%) funding source of the students as required by the Ministry of Training Colleges and Universities
	  */
	public void setFundingSource (String FundingSource)
	{

		set_Value (COLUMNNAME_FundingSource, FundingSource);
	}

	/** Get Funding Source.
		@return The main (>50%) funding source of the students as required by the Ministry of Training Colleges and Universities
	  */
	public String getFundingSource () 
	{
		return (String)get_Value(COLUMNNAME_FundingSource);
	}

	/** Set International.
		@param International 
		Is the student international or domestic.
	  */
	public void setInternational (boolean International)
	{
		set_Value (COLUMNNAME_International, Boolean.valueOf(International));
	}

	/** Get International.
		@return Is the student international or domestic.
	  */
	public boolean isInternational () 
	{
		Object oo = get_Value(COLUMNNAME_International);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Full Time?.
		@param IsFullTime 
		Is the student a full-time student? No indicates a part time student.
	  */
	public void setIsFullTime (boolean IsFullTime)
	{
		set_Value (COLUMNNAME_IsFullTime, Boolean.valueOf(IsFullTime));
	}

	/** Get Full Time?.
		@return Is the student a full-time student? No indicates a part time student.
	  */
	public boolean isFullTime () 
	{
		Object oo = get_Value(COLUMNNAME_IsFullTime);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Vocational Student.
		@param IsVocational 
		Is the student a vocational student.
	  */
	public void setIsVocational (boolean IsVocational)
	{
		set_Value (COLUMNNAME_IsVocational, Boolean.valueOf(IsVocational));
	}

	/** Get Vocational Student.
		@return Is the student a vocational student.
	  */
	public boolean isVocational () 
	{
		Object oo = get_Value(COLUMNNAME_IsVocational);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_S_Resource getPrimary_Inst() throws RuntimeException
    {
		return (org.compiere.model.I_S_Resource)MTable.get(getCtx(), org.compiere.model.I_S_Resource.Table_Name)
			.getPO(getPrimary_Inst_ID(), get_TrxName());	}

	/** Set Primary Instructor.
		@param Primary_Inst_ID 
		The primary instructor for the student
	  */
	public void setPrimary_Inst_ID (int Primary_Inst_ID)
	{
		if (Primary_Inst_ID < 1) 
			set_Value (COLUMNNAME_Primary_Inst_ID, null);
		else 
			set_Value (COLUMNNAME_Primary_Inst_ID, Integer.valueOf(Primary_Inst_ID));
	}

	/** Get Primary Instructor.
		@return The primary instructor for the student
	  */
	public int getPrimary_Inst_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Primary_Inst_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_S_Resource getSecondary_Inst() throws RuntimeException
    {
		return (org.compiere.model.I_S_Resource)MTable.get(getCtx(), org.compiere.model.I_S_Resource.Table_Name)
			.getPO(getSecondary_Inst_ID(), get_TrxName());	}

	/** Set Secondary Instructor.
		@param Secondary_Inst_ID 
		The secondary instructor for the student
	  */
	public void setSecondary_Inst_ID (int Secondary_Inst_ID)
	{
		if (Secondary_Inst_ID < 1) 
			set_Value (COLUMNNAME_Secondary_Inst_ID, null);
		else 
			set_Value (COLUMNNAME_Secondary_Inst_ID, Integer.valueOf(Secondary_Inst_ID));
	}

	/** Get Secondary Instructor.
		@return The secondary instructor for the student
	  */
	public int getSecondary_Inst_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Secondary_Inst_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Student Number.
		@param StudentNum 
		The ID of the Student at the FTU
	  */
	public void setStudentNum (String StudentNum)
	{
		set_Value (COLUMNNAME_StudentNum, StudentNum);
	}

	/** Get Student Number.
		@return The ID of the Student at the FTU
	  */
	public String getStudentNum () 
	{
		return (String)get_Value(COLUMNNAME_StudentNum);
	}

	/** StudentStage AD_Reference_ID=1000022 */
	public static final int STUDENTSTAGE_AD_Reference_ID=1000022;
	/** SPP = 10 */
	public static final String STUDENTSTAGE_SPP = "10";
	/** PPL = 20 */
	public static final String STUDENTSTAGE_PPL = "20";
	/** NR = 30 */
	public static final String STUDENTSTAGE_NR = "30";
	/** CPL = 40 */
	public static final String STUDENTSTAGE_CPL = "40";
	/** ME = 50 */
	public static final String STUDENTSTAGE_ME = "50";
	/** IFR = 60 */
	public static final String STUDENTSTAGE_IFR = "60";
	/** MIFR = 70 */
	public static final String STUDENTSTAGE_MIFR = "70";
	/** INST = 80 */
	public static final String STUDENTSTAGE_INST = "80";
	/** Set Training Stage.
		@param StudentStage 
		The stage of training the student is currently working towards.
	  */
	public void setStudentStage (String StudentStage)
	{

		set_Value (COLUMNNAME_StudentStage, StudentStage);
	}

	/** Get Training Stage.
		@return The stage of training the student is currently working towards.
	  */
	public String getStudentStage () 
	{
		return (String)get_Value(COLUMNNAME_StudentStage);
	}

	/** StudentStatus AD_Reference_ID=1000008 */
	public static final int STUDENTSTATUS_AD_Reference_ID=1000008;
	/** Enrolled = Enrolled */
	public static final String STUDENTSTATUS_Enrolled = "Enrolled";
	/** Graduated = Graduated */
	public static final String STUDENTSTATUS_Graduated = "Graduated";
	/** Dropped Out = Dropped Out */
	public static final String STUDENTSTATUS_DroppedOut = "Dropped Out";
	/** Incomplete = Incomplete */
	public static final String STUDENTSTATUS_Incomplete = "Incomplete";
	/** Transferred = Transferred */
	public static final String STUDENTSTATUS_Transferred = "Transferred";
	/** Suspended = Suspended */
	public static final String STUDENTSTATUS_Suspended = "Suspended";
	/** Set Student Status.
		@param StudentStatus 
		The status of the student.
	  */
	public void setStudentStatus (String StudentStatus)
	{

		set_Value (COLUMNNAME_StudentStatus, StudentStatus);
	}

	/** Get Student Status.
		@return The status of the student.
	  */
	public String getStudentStatus () 
	{
		return (String)get_Value(COLUMNNAME_StudentStatus);
	}
}