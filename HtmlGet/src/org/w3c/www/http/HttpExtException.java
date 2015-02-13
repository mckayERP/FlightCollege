// HttpExtException.java
// $Id: HttpExtException.java,v 1.1.2.1 2003/05/28 22:58:48 owenc Exp $
// (c) COPYRIGHT MIT and INRIA, 1998.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.http;

/**
 * @version $Revision: 1.1.2.1 $
 * @author  Benoit Mahe (bmahe@w3.org)
 */
public class HttpExtException extends RuntimeException {

    protected HttpExtException(String msg) {
	super(msg);
    }

    protected HttpExtException() {
	super();
    }
}
