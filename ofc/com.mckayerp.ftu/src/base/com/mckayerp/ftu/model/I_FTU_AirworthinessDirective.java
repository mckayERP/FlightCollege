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
package com.mckayerp.ftu.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FTU_AirworthinessDirective
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_AirworthinessDirective 
{

    /** TableName=FTU_AirworthinessDirective */
    public static final String Table_Name = "FTU_AirworthinessDirective";

    /** AD_Table_ID=54171 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

    /** Column name FTU_ADCountryCode */
    public static final String COLUMNNAME_FTU_ADCountryCode = "FTU_ADCountryCode";

	/** Set Country.
	  * The Country of issue
	  */
	public void setFTU_ADCountryCode (String FTU_ADCountryCode);

	/** Get Country.
	  * The Country of issue
	  */
	public String getFTU_ADCountryCode();

    /** Column name FTU_ADDocumentLink */
    public static final String COLUMNNAME_FTU_ADDocumentLink = "FTU_ADDocumentLink";

	/** Set Link.
	  * Link to the on-line document
	  */
	public void setFTU_ADDocumentLink (String FTU_ADDocumentLink);

	/** Get Link.
	  * Link to the on-line document
	  */
	public String getFTU_ADDocumentLink();

    /** Column name FTU_ADDocumentLinkPDF */
    public static final String COLUMNNAME_FTU_ADDocumentLinkPDF = "FTU_ADDocumentLinkPDF";

	/** Set PDF.
	  * Link to the on-line PDF document
	  */
	public void setFTU_ADDocumentLinkPDF (String FTU_ADDocumentLinkPDF);

	/** Get PDF.
	  * Link to the on-line PDF document
	  */
	public String getFTU_ADDocumentLinkPDF();

    /** Column name FTU_ADNumber */
    public static final String COLUMNNAME_FTU_ADNumber = "FTU_ADNumber";

	/** Set AD Number.
	  * The reference number for this AD
	  */
	public void setFTU_ADNumber (String FTU_ADNumber);

	/** Get AD Number.
	  * The reference number for this AD
	  */
	public String getFTU_ADNumber();

    /** Column name FTU_ADReference */
    public static final String COLUMNNAME_FTU_ADReference = "FTU_ADReference";

	/** Set Reference.
	  * Reference information that expands the Airworthiness Directive
	  */
	public void setFTU_ADReference (String FTU_ADReference);

	/** Get Reference.
	  * Reference information that expands the Airworthiness Directive
	  */
	public String getFTU_ADReference();

    /** Column name FTU_ADRepeatText */
    public static final String COLUMNNAME_FTU_ADRepeatText = "FTU_ADRepeatText";

	/** Set Repeat Inspection?.
	  * Does the AD require repetitive inspections?
	  */
	public void setFTU_ADRepeatText (String FTU_ADRepeatText);

	/** Get Repeat Inspection?.
	  * Does the AD require repetitive inspections?
	  */
	public String getFTU_ADRepeatText();

    /** Column name FTU_ADSubject */
    public static final String COLUMNNAME_FTU_ADSubject = "FTU_ADSubject";

	/** Set AD Subject.
	  * The subject of the Airworthiness Directives
	  */
	public void setFTU_ADSubject (String FTU_ADSubject);

	/** Get AD Subject.
	  * The subject of the Airworthiness Directives
	  */
	public String getFTU_ADSubject();

    /** Column name FTU_ADType */
    public static final String COLUMNNAME_FTU_ADType = "FTU_ADType";

	/** Set Product Type.
	  * The type of airworthiness directive and the product/component it pertains to.
	  */
	public void setFTU_ADType (String FTU_ADType);

	/** Get Product Type.
	  * The type of airworthiness directive and the product/component it pertains to.
	  */
	public String getFTU_ADType();

    /** Column name FTU_AirworthinessDirective_ID */
    public static final String COLUMNNAME_FTU_AirworthinessDirective_ID = "FTU_AirworthinessDirective_ID";

	/** Set Airworthiness Directives ID	  */
	public void setFTU_AirworthinessDirective_ID (int FTU_AirworthinessDirective_ID);

	/** Get Airworthiness Directives ID	  */
	public int getFTU_AirworthinessDirective_ID();

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
}
