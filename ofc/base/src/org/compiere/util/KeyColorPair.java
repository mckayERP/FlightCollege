/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.util;

import java.awt.Color;

/**
 *	(Numeric) Key Name Pair
 *
 *  @author     Jorg Janke
 *  @version    $Id: KeyNamePair.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public final class KeyColorPair extends ColorPair
{
	public KeyColorPair(int key, String name, Color color) {
		super(name, color);
		m_key = key;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6347385376010388473L;
	
	public static final KeyColorPair EMPTY = new KeyColorPair(-1, "", null);

	/** The Key         */
	private int 	m_key = 0;

	/**
	 *	Get Key
	 *  @return key
	 */
	public int getKey()
	{
		return m_key;
	}	//	getKey

	/**
	 *	Get ID (key as String)
	 *
	 *  @return String value of key or null if -1
	 */
	public String getID()
	{
		if (m_key == -1)
			return null;
		return String.valueOf(m_key);
	}	//	getID


	/**
	 *	Equals
	 *  @param obj object
	 *  @return true if equal
	 */
	public boolean equals(Object obj)
	{
		if (obj instanceof KeyColorPair)
		{
			KeyColorPair pp = (KeyColorPair)obj;
			if (pp.getKey() == m_key
				&& pp.getName() != null
				&& pp.getName().equals(getName())
				&& pp.getColor() != null
				&& pp.getColor().equals(getColor()))
				return true;
			return false;
		}
		return false;
	}	//	equals

	/**
	 *  Return Hashcode of key
	 *  @return hascode
	 */
	public int hashCode()
	{
		return m_key;
	}   //  hashCode

}	//	KeyNamePair
