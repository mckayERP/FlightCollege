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
package com.mckayerp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CT_DataInstance
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_CT_DataInstance 
{

    /** TableName=CT_DataInstance */
    public static final String Table_Name = "CT_DataInstance";

    /** AD_Table_ID=1000096 */
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

    /** Column name CT_DataElement_ID */
    public static final String COLUMNNAME_CT_DataElement_ID = "CT_DataElement_ID";

	/** Set Data Element.
	  * Definition of a data element that forms part of a data set.
	  */
	public void setCT_DataElement_ID (int CT_DataElement_ID);

	/** Get Data Element.
	  * Definition of a data element that forms part of a data set.
	  */
	public int getCT_DataElement_ID();

	public com.mckayerp.model.I_CT_DataElement getCT_DataElement() throws RuntimeException;

    /** Column name CT_DataElementValue_ID */
    public static final String COLUMNNAME_CT_DataElementValue_ID = "CT_DataElementValue_ID";

	/** Set Data Element Value.
	  * A predefined value of a data element.
	  */
	public void setCT_DataElementValue_ID (int CT_DataElementValue_ID);

	/** Get Data Element Value.
	  * A predefined value of a data element.
	  */
	public int getCT_DataElementValue_ID();

	public com.mckayerp.model.I_CT_DataElementValue getCT_DataElementValue() throws RuntimeException;

    /** Column name CT_DataInstance_ID */
    public static final String COLUMNNAME_CT_DataInstance_ID = "CT_DataInstance_ID";

	/** Set Data Instance ID	  */
	public void setCT_DataInstance_ID (int CT_DataInstance_ID);

	/** Get Data Instance ID	  */
	public int getCT_DataInstance_ID();

    /** Column name CT_DataSetInstance_ID */
    public static final String COLUMNNAME_CT_DataSetInstance_ID = "CT_DataSetInstance_ID";

	/** Set Data Set Instance.
	  * A data point within a data set.
	  */
	public void setCT_DataSetInstance_ID (int CT_DataSetInstance_ID);

	/** Get Data Set Instance.
	  * A data point within a data set.
	  */
	public int getCT_DataSetInstance_ID();

	public com.mckayerp.model.I_CT_DataSetInstance getCT_DataSetInstance() throws RuntimeException;

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

    /** Column name ValueDateTime */
    public static final String COLUMNNAME_ValueDateTime = "ValueDateTime";

	/** Set Value.
	  * Date/time value
	  */
	public void setValueDateTime (Timestamp ValueDateTime);

	/** Get Value.
	  * Date/time value
	  */
	public Timestamp getValueDateTime();

    /** Column name ValueInteger */
    public static final String COLUMNNAME_ValueInteger = "ValueInteger";

	/** Set Value.
	  * Integer Value
	  */
	public void setValueInteger (int ValueInteger);

	/** Get Value.
	  * Integer Value
	  */
	public int getValueInteger();

    /** Column name ValueNumber */
    public static final String COLUMNNAME_ValueNumber = "ValueNumber";

	/** Set Value.
	  * Numeric Value
	  */
	public void setValueNumber (BigDecimal ValueNumber);

	/** Get Value.
	  * Numeric Value
	  */
	public BigDecimal getValueNumber();

    /** Column name ValueString */
    public static final String COLUMNNAME_ValueString = "ValueString";

	/** Set ValueString.
	  * The value of the data as a string
	  */
	public void setValueString (String ValueString);

	/** Get ValueString.
	  * The value of the data as a string
	  */
	public String getValueString();

    /** Column name ValueYN */
    public static final String COLUMNNAME_ValueYN = "ValueYN";

	/** Set Value.
	  * Yes-No Value
	  */
	public void setValueYN (boolean ValueYN);

	/** Get Value.
	  * Yes-No Value
	  */
	public boolean isValueYN();
}
