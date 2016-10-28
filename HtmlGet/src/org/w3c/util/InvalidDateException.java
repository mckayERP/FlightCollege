// InvalidDateException.java
// $Id: InvalidDateException.java,v 1.1.2.1 2003/05/28 22:58:46 owenc Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.util;

/**
 * @version $Revision: 1.1.2.1 $
 * @author  Benoit Mahe (bmahe@w3.org)
 */
public class InvalidDateException extends Exception {

    public InvalidDateException(String msg) {
	super(msg);
    }
    
}
