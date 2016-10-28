// ConnectedEvent.java
// $Id: ConnectedEvent.java,v 1.1.2.1 2003/05/28 22:58:51 owenc Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.protocol.http;

import java.io.OutputStream;

// FIXME doc

public class ConnectedEvent extends RequestEvent {
    /**
     * The HTTP <code>CONTINUE</code> packet.
     */
    public OutputStream output = null;

    public ConnectedEvent(HttpServer s, Request req,  OutputStream output) {
	super(s, req, EVT_CONNECTED);
	this.output = output;
    }
}


