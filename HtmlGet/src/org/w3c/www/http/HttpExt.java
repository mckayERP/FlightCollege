// HttpExt.java
// $Id: HttpExt.java,v 1.1.2.1 2003/05/28 22:58:48 owenc Exp $
// (c) COPYRIGHT MIT and INRIA, 1998.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.http;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @version $Revision: 1.1.2.1 $
 * @author  Benoit Mahe (bmahe@w3.org)
 */
public class HttpExt {

    protected String    name      = null;
    protected String    ns        = null;
    protected Hashtable<String, String> exts      = null;
    protected boolean   generated = true;
    protected boolean   headers   = false;

    protected void setName(String name) {
	this.name = name;
    }

    /**
     * Get the http extension declaration name.
     * @return a String instance
     */
    public String getName() {
	return name;
    }

    protected void setNamespace(String ns) {
	this.ns = ns;
        this.headers = true;
    }

    /**
     * Get the http extension declaration namespace.
     * @return a String instance
     */
    public String getNamespace() {
	return ns;
    }

    /**
     * Does this extension needs specific headers?
     * @return a boolean.
     */
    public boolean needsHeaders() {
	return headers;
    }

    /**
     * Add an http extension declaration <token/value>
     * @param name the token name.
     * @param value the value.     
     */
    public void addDeclExt(String token, String value) {
	exts.put(token, value);
    }

    /**
     * Get an http extension declaration token value.
     * @param name the token name.
     * @return a String instance
     */
    public String getDeclExt(String name) {
	return exts.get(name);
    }

    /**
     * Get all http extension declaration <token/value>
     * @return an Enumeration instance
     */
    public Enumeration<String> getDeclExtNames() {
	return exts.keys();
    }

    protected String getRealHeader(String header) {
	return ns+header;
    }

	public String toString() {
	String string = "\""+name+"\" ; ns="+ns;
	Enumeration<String> enum1 = exts.keys();
	while (enum1.hasMoreElements()) {
	    String tok = enum1.nextElement();
	    String val = exts.get(tok);
	    string += ("; "+tok+"="+val);
	}
	return string;
    }

    protected boolean isGenerated() {
	return generated;
    }

    /**
     * Constructor, for User
     * @param name the Http extension declaration name 
     * @param header Does this extension needs specific headers?
     * (absoluteURI or field-name)
     */
    public HttpExt(String name, boolean headers) {
	this.generated = false;
	this.name      = name;
	this.exts      = new Hashtable<String, String>(3);
	this.headers   = headers;
    }

    /**
     * Constructor, for User
     * @param old the old Http extension declaration  
     * If you want to reply the same extension, use this
     * contructor.
     */
    public HttpExt(HttpExt old) {
	this.generated = false;
	this.name      = old.name;
	this.exts      = new Hashtable<String, String>(3);
	this.headers   = old.headers;
    }

    protected HttpExt() {
	this.generated = true;
	this.headers   = false;
	this.exts      = new Hashtable<String, String>(3);
    }
}
