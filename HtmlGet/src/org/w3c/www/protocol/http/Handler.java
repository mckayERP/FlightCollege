// Handler.java
// $Id: Handler.java,v 1.1.2.1 2003/05/28 22:58:51 owenc Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.protocol.http ;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {

    protected URLConnection openConnection (URL u)
	throws IOException 
    {
	return new HttpURLConnection(u);
    }

}
