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

/** Generated Interface for FTU_Course
 *  @author Adempiere (generated) 
 *  @version OFC Custom 3.8.0_2
 */
public interface I_FTU_Course 
{

    /** TableName=FTU_Course */
    public static final String Table_Name = "FTU_Course";

    /** AD_Table_ID=1000004 */
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

    /** Column name AddMissingButton */
    public static final String COLUMNNAME_AddMissingButton = "AddMissingButton";

	/** Set Add Missing Button	  */
	public void setAddMissingButton (String AddMissingButton);

	/** Get Add Missing Button	  */
	public String getAddMissingButton();

    /** Column name CourseHrs */
    public static final String COLUMNNAME_CourseHrs = "CourseHrs";

	/** Set Hours.
	  * Hours
	  */
	public void setCourseHrs (BigDecimal CourseHrs);

	/** Get Hours.
	  * Hours
	  */
	public BigDecimal getCourseHrs();

    /** Column name CourseLevel */
    public static final String COLUMNNAME_CourseLevel = "CourseLevel";

	/** Set Level.
	  * Level of the course
	  */
	public void setCourseLevel (String CourseLevel);

	/** Get Level.
	  * Level of the course
	  */
	public String getCourseLevel();

    /** Column name CourseTitle */
    public static final String COLUMNNAME_CourseTitle = "CourseTitle";

	/** Set Course Title.
	  * Course Title
	  */
	public void setCourseTitle (String CourseTitle);

	/** Get Course Title.
	  * Course Title
	  */
	public String getCourseTitle();

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

    /** Column name FTU_Course_ID */
    public static final String COLUMNNAME_FTU_Course_ID = "FTU_Course_ID";

	/** Set Course ID	  */
	public void setFTU_Course_ID (int FTU_Course_ID);

	/** Get Course ID	  */
	public int getFTU_Course_ID();

    /** Column name FTU_Training_Unit_ID */
    public static final String COLUMNNAME_FTU_Training_Unit_ID = "FTU_Training_Unit_ID";

	/** Set Training Unit ID.
	  * ID (Key) of the Training Unit
	  */
	public void setFTU_Training_Unit_ID (int FTU_Training_Unit_ID);

	/** Get Training Unit ID.
	  * ID (Key) of the Training Unit
	  */
	public int getFTU_Training_Unit_ID();

	public com.mckayerp.ftu.model.I_FTU_Training_Unit getFTU_Training_Unit() throws RuntimeException;

    /** Column name GradingSystem */
    public static final String COLUMNNAME_GradingSystem = "GradingSystem";

	/** Set Grading System	  */
	public void setGradingSystem (String GradingSystem);

	/** Get Grading System	  */
	public String getGradingSystem();

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

    /** Column name IsElective */
    public static final String COLUMNNAME_IsElective = "IsElective";

	/** Set Elective.
	  * Is the course an elective?
	  */
	public void setIsElective (boolean IsElective);

	/** Get Elective.
	  * Is the course an elective?
	  */
	public boolean isElective();

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

	/** Set Course Number.
	  * The Course Number for the Course
	  */
	public void setValue (String Value);

	/** Get Course Number.
	  * The Course Number for the Course
	  */
	public String getValue();
}
