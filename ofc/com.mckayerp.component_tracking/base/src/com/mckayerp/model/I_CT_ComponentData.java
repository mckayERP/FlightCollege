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

/** Generated Interface for CT_ComponentData
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_CT_ComponentData 
{

    /** TableName=CT_ComponentData */
    public static final String Table_Name = "CT_ComponentData";

    /** AD_Table_ID=54412 */
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

    /** Column name CT_CompLifeCycleModel_ID */
    public static final String COLUMNNAME_CT_CompLifeCycleModel_ID = "CT_CompLifeCycleModel_ID";

	/** Set Component Life Cycle Model.
	  * The component life cycle model to use when creating new components for this product.
	  */
	public void setCT_CompLifeCycleModel_ID (int CT_CompLifeCycleModel_ID);

	/** Get Component Life Cycle Model.
	  * The component life cycle model to use when creating new components for this product.
	  */
	public int getCT_CompLifeCycleModel_ID();

	public com.mckayerp.model.I_CT_CompLifeCycleModel getCT_CompLifeCycleModel() throws RuntimeException;

    /** Column name CT_ComponentData_ID */
    public static final String COLUMNNAME_CT_ComponentData_ID = "CT_ComponentData_ID";

	/** Set Component Data ID	  */
	public void setCT_ComponentData_ID (int CT_ComponentData_ID);

	/** Get Component Data ID	  */
	public int getCT_ComponentData_ID();

    /** Column name CT_DataSet_ID */
    public static final String COLUMNNAME_CT_DataSet_ID = "CT_DataSet_ID";

	/** Set Data Set.
	  * A definition of a set of data.
	  */
	public void setCT_DataSet_ID (int CT_DataSet_ID);

	/** Get Data Set.
	  * A definition of a set of data.
	  */
	public int getCT_DataSet_ID();

	public com.mckayerp.model.I_CT_DataSet getCT_DataSet() throws RuntimeException;

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
