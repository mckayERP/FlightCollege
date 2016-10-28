// Comparable.java
// $Id: Comparable.java,v 1.1.2.1 2003/05/28 22:58:45 owenc Exp $
// (c) COPYRIGHT MIT and INRIA, 1998.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.tools.sorter ;

/**
 * used for string comparison.
 */
public interface Comparable {

    public boolean greaterThan(Comparable comp);

    public String getStringValue();

}
