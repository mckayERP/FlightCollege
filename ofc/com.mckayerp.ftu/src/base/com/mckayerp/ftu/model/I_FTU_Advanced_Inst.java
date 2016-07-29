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

/** Generated Interface for FTU_Advanced_Inst
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Advanced_Inst 
{

    /** TableName=FTU_Advanced_Inst */
    public static final String Table_Name = "FTU_Advanced_Inst";

    /** AD_Table_ID=1000030 */
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

    /** Column name FlightCourseType */
    public static final String COLUMNNAME_FlightCourseType = "FlightCourseType";

	/** Set Course Type.
	  * The type of flight training course (flight type)
	  */
	public void setFlightCourseType (String FlightCourseType);

	/** Get Course Type.
	  * The type of flight training course (flight type)
	  */
	public String getFlightCourseType();

    /** Column name FTU_Advanced_Inst_ID */
    public static final String COLUMNNAME_FTU_Advanced_Inst_ID = "FTU_Advanced_Inst_ID";

	/** Set Advanced Instruction Courses ID	  */
	public void setFTU_Advanced_Inst_ID (int FTU_Advanced_Inst_ID);

	/** Get Advanced Instruction Courses ID	  */
	public int getFTU_Advanced_Inst_ID();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name Intro_Product_ID */
    public static final String COLUMNNAME_Intro_Product_ID = "Intro_Product_ID";

	/** Set Intro Product.
	  * The product to charge for an intro flight.
	  */
	public void setIntro_Product_ID (int Intro_Product_ID);

	/** Get Intro Product.
	  * The product to charge for an intro flight.
	  */
	public int getIntro_Product_ID();

	public org.compiere.model.I_M_Product getIntro_Product() throws RuntimeException;

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

    /** Column name IsAdvanced */
    public static final String COLUMNNAME_IsAdvanced = "IsAdvanced";

	/** Set Advanced.
	  * Is the course/flight advanced?
	  */
	public void setIsAdvanced (boolean IsAdvanced);

	/** Get Advanced.
	  * Is the course/flight advanced?
	  */
	public boolean isAdvanced();

    /** Column name IsIntro */
    public static final String COLUMNNAME_IsIntro = "IsIntro";

	/** Set Intro.
	  * Is the flight course an intro flight?
	  */
	public void setIsIntro (boolean IsIntro);

	/** Get Intro.
	  * Is the flight course an intro flight?
	  */
	public boolean isIntro();

    /** Column name IsNoShow */
    public static final String COLUMNNAME_IsNoShow = "IsNoShow";

	/** Set No-Show.
	  * Is the flight course type one that should be charged a No-Show fee?
	  */
	public void setIsNoShow (boolean IsNoShow);

	/** Get No-Show.
	  * Is the flight course type one that should be charged a No-Show fee?
	  */
	public boolean isNoShow();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NoShow_Product_ID */
    public static final String COLUMNNAME_NoShow_Product_ID = "NoShow_Product_ID";

	/** Set No-Show Product.
	  * The product associated with the No-Show for this course type.
	  */
	public void setNoShow_Product_ID (int NoShow_Product_ID);

	/** Get No-Show Product.
	  * The product associated with the No-Show for this course type.
	  */
	public int getNoShow_Product_ID();

	public org.compiere.model.I_M_Product getNoShow_Product() throws RuntimeException;

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
