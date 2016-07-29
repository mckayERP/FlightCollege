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

/** Generated Interface for AD_ImpValRule_Cond
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_AD_ImpValRule_Cond 
{

    /** TableName=AD_ImpValRule_Cond */
    public static final String Table_Name = "AD_ImpValRule_Cond";

    /** AD_Table_ID=1000061 */
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

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException;

    /** Column name AD_ImpVal_Rule_ID */
    public static final String COLUMNNAME_AD_ImpVal_Rule_ID = "AD_ImpVal_Rule_ID";

	/** Set Import Validator Rule	  */
	public void setAD_ImpVal_Rule_ID (int AD_ImpVal_Rule_ID);

	/** Get Import Validator Rule	  */
	public int getAD_ImpVal_Rule_ID();

	public com.mckayerp.ftu.model.I_AD_ImpVal_Rule getAD_ImpVal_Rule() throws RuntimeException;

    /** Column name AD_ImpValRule_Cond_ID */
    public static final String COLUMNNAME_AD_ImpValRule_Cond_ID = "AD_ImpValRule_Cond_ID";

	/** Set Import Validation Rule Condition	  */
	public void setAD_ImpValRule_Cond_ID (int AD_ImpValRule_Cond_ID);

	/** Get Import Validation Rule Condition	  */
	public int getAD_ImpValRule_Cond_ID();

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

    /** Column name AndOr */
    public static final String COLUMNNAME_AndOr = "AndOr";

	/** Set And/Or.
	  * Logical operation: AND or OR
	  */
	public void setAndOr (String AndOr);

	/** Get And/Or.
	  * Logical operation: AND or OR
	  */
	public String getAndOr();

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

    /** Column name LeftBracket */
    public static final String COLUMNNAME_LeftBracket = "LeftBracket";

	/** Set Left Bracket.
	  * The left bracket(s) to apply to the expression
	  */
	public void setLeftBracket (String LeftBracket);

	/** Get Left Bracket.
	  * The left bracket(s) to apply to the expression
	  */
	public String getLeftBracket();

    /** Column name Operator */
    public static final String COLUMNNAME_Operator = "Operator";

	/** Set Operator.
	  * Operators used in logic expressions ("=", "<", ...)
	  */
	public void setOperator (String Operator);

	/** Get Operator.
	  * Operators used in logic expressions ("=", "<", ...)
	  */
	public String getOperator();

    /** Column name RightBracket */
    public static final String COLUMNNAME_RightBracket = "RightBracket";

	/** Set Right Bracket	  */
	public void setRightBracket (String RightBracket);

	/** Get Right Bracket	  */
	public String getRightBracket();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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

    /** Column name Value1 */
    public static final String COLUMNNAME_Value1 = "Value1";

	/** Set Value 1.
	  * Value or value from is comparator is between ">-<"
	  */
	public void setValue1 (String Value1);

	/** Get Value 1.
	  * Value or value from is comparator is between ">-<"
	  */
	public String getValue1();

    /** Column name Value2 */
    public static final String COLUMNNAME_Value2 = "Value2";

	/** Set Value To.
	  * Value To
	  */
	public void setValue2 (String Value2);

	/** Get Value To.
	  * Value To
	  */
	public String getValue2();
}
