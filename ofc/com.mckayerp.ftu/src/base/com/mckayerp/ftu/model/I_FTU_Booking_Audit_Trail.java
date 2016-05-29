/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FTU_Booking_Audit_Trail
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Booking_Audit_Trail 
{

    /** TableName=FTU_Booking_Audit_Trail */
    public static final String Table_Name = "FTU_Booking_Audit_Trail";

    /** AD_Table_ID=1000017 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name ActionPerformed */
    public static final String COLUMNNAME_ActionPerformed = "ActionPerformed";

	/** Set Action Performed.
	  * The action performed by the flight sheet user
	  */
	public void setActionPerformed (String ActionPerformed);

	/** Get Action Performed.
	  * The action performed by the flight sheet user
	  */
	public String getActionPerformed();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AssignDateFrom */
    public static final String COLUMNNAME_AssignDateFrom = "AssignDateFrom";

	/** Set Assign From.
	  * Assign resource from
	  */
	public void setAssignDateFrom (Timestamp AssignDateFrom);

	/** Get Assign From.
	  * Assign resource from
	  */
	public Timestamp getAssignDateFrom();

    /** Column name AssignDateTo */
    public static final String COLUMNNAME_AssignDateTo = "AssignDateTo";

	/** Set Assign To.
	  * Assign resource until
	  */
	public void setAssignDateTo (Timestamp AssignDateTo);

	/** Get Assign To.
	  * Assign resource until
	  */
	public Timestamp getAssignDateTo();

    /** Column name AuditReference */
    public static final String COLUMNNAME_AuditReference = "AuditReference";

	/** Set Audit Reference.
	  * The Booking Audit Reference number
	  */
	public void setAuditReference (BigDecimal AuditReference);

	/** Get Audit Reference.
	  * The Booking Audit Reference number
	  */
	public BigDecimal getAuditReference();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Flightsheet_Client */
    public static final String COLUMNNAME_Flightsheet_Client = "Flightsheet_Client";

	/** Set Client Name.
	  * The Flightsheet client
	  */
	public void setFlightsheet_Client (String Flightsheet_Client);

	/** Get Client Name.
	  * The Flightsheet client
	  */
	public String getFlightsheet_Client();

    /** Column name FTU_Booking_Audit_Trail_ID */
    public static final String COLUMNNAME_FTU_Booking_Audit_Trail_ID = "FTU_Booking_Audit_Trail_ID";

	/** Set Booking Audit Trail ID	  */
	public void setFTU_Booking_Audit_Trail_ID (int FTU_Booking_Audit_Trail_ID);

	/** Get Booking Audit Trail ID	  */
	public int getFTU_Booking_Audit_Trail_ID();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name PerformedAt */
    public static final String COLUMNNAME_PerformedAt = "PerformedAt";

	/** Set Performed At	  */
	public void setPerformedAt (String PerformedAt);

	/** Get Performed At	  */
	public String getPerformedAt();

    /** Column name PerformedBy */
    public static final String COLUMNNAME_PerformedBy = "PerformedBy";

	/** Set Performed By.
	  * The action was performed by this person.
	  */
	public void setPerformedBy (String PerformedBy);

	/** Get Performed By.
	  * The action was performed by this person.
	  */
	public String getPerformedBy();

    /** Column name S_Resource_ID */
    public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

	/** Set Resource.
	  * Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID);

	/** Get Resource.
	  * Resource
	  */
	public int getS_Resource_ID();

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
