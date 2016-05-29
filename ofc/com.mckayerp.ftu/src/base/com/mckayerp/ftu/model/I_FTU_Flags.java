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
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FTU_Flags
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_FTU_Flags 
{

    /** TableName=FTU_Flags */
    public static final String Table_Name = "FTU_Flags";

    /** AD_Table_ID=1000031 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name FlagName */
    public static final String COLUMNNAME_FlagName = "FlagName";

	/** Set Flag Name.
	  * The unique name for the flag
	  */
	public void setFlagName (String FlagName);

	/** Get Flag Name.
	  * The unique name for the flag
	  */
	public String getFlagName();

    /** Column name OFC_IsProcessing */
    public static final String COLUMNNAME_OFC_IsProcessing = "OFC_IsProcessing";

	/** Set OFC_IsProcessing.
	  * A boolean.  If true the process is currently running.
	  */
	public void setOFC_IsProcessing (boolean OFC_IsProcessing);

	/** Get OFC_IsProcessing.
	  * A boolean.  If true the process is currently running.
	  */
	public boolean isOFC_IsProcessing();
}
