// LRUAble.java
// $Id: LRUAble.java,v 1.1.2.1 2003/05/28 22:58:46 owenc Exp $  
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.util ;

public interface LRUAble {
    public LRUAble getNext() ;
    public LRUAble getPrev() ;
    public void setNext(LRUAble next) ;
    public void setPrev(LRUAble prev) ;

}
