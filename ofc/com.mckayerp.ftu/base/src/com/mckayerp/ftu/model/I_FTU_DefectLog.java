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

/** Generated Interface for FTU_DefectLog
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public interface I_FTU_DefectLog 
{

    /** TableName=FTU_DefectLog */
    public static final String Table_Name = "FTU_DefectLog";

    /** AD_Table_ID=1000036 */
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

    /** Column name DefectDate */
    public static final String COLUMNNAME_DefectDate = "DefectDate";

	/** Set Defect Date.
	  * The date the defect was entered in the log
	  */
	public void setDefectDate (Timestamp DefectDate);

	/** Get Defect Date.
	  * The date the defect was entered in the log
	  */
	public Timestamp getDefectDate();

    /** Column name DefectDesc */
    public static final String COLUMNNAME_DefectDesc = "DefectDesc";

	/** Set Defect.
	  * The defect description
	  */
	public void setDefectDesc (String DefectDesc);

	/** Get Defect.
	  * The defect description
	  */
	public String getDefectDesc();

    /** Column name DefectStatus */
    public static final String COLUMNNAME_DefectStatus = "DefectStatus";

	/** Set Defect Status	  */
	public void setDefectStatus (String DefectStatus);

	/** Get Defect Status	  */
	public String getDefectStatus();

    /** Column name DefectType */
    public static final String COLUMNNAME_DefectType = "DefectType";

	/** Set Defect Type.
	  * The type of the defect
	  */
	public void setDefectType (String DefectType);

	/** Get Defect Type.
	  * The type of the defect
	  */
	public String getDefectType();

    /** Column name DeferredDate */
    public static final String COLUMNNAME_DeferredDate = "DeferredDate";

	/** Set Deferred Date.
	  * The date the defect was deferred.
	  */
	public void setDeferredDate (Timestamp DeferredDate);

	/** Get Deferred Date.
	  * The date the defect was deferred.
	  */
	public Timestamp getDeferredDate();

    /** Column name FTU_Aircraft_ID */
    public static final String COLUMNNAME_FTU_Aircraft_ID = "FTU_Aircraft_ID";

	/** Set Aircraft	  */
	public void setFTU_Aircraft_ID (int FTU_Aircraft_ID);

	/** Get Aircraft	  */
	public int getFTU_Aircraft_ID();

	public com.mckayerp.ftu.model.I_FTU_Aircraft getFTU_Aircraft() throws RuntimeException;

    /** Column name FTU_DefectLog_ID */
    public static final String COLUMNNAME_FTU_DefectLog_ID = "FTU_DefectLog_ID";

	/** Set Aircraft Defect Log ID	  */
	public void setFTU_DefectLog_ID (int FTU_DefectLog_ID);

	/** Get Aircraft Defect Log ID	  */
	public int getFTU_DefectLog_ID();

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

    /** Column name Rectification */
    public static final String COLUMNNAME_Rectification = "Rectification";

	/** Set Rectification.
	  * Description of how the defect was rectified.
	  */
	public void setRectification (String Rectification);

	/** Get Rectification.
	  * Description of how the defect was rectified.
	  */
	public String getRectification();

    /** Column name RepairedDate */
    public static final String COLUMNNAME_RepairedDate = "RepairedDate";

	/** Set Repaired Date.
	  * The date the defect was repaired.
	  */
	public void setRepairedDate (Timestamp RepairedDate);

	/** Get Repaired Date.
	  * The date the defect was repaired.
	  */
	public Timestamp getRepairedDate();

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
