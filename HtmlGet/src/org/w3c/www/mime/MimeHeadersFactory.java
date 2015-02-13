// MimeHeadersFactory.java
// $Id: MimeHeadersFactory.java,v 1.1.2.1 2003/05/28 22:58:50 owenc Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.mime;

/**
 * A Mime header factory, that will build instances of the MimeHeaders class
 * to hold MIME headers.
 */

public class MimeHeadersFactory implements MimeParserFactory {

    /**
     * Create a new header holder to hold the parser's result.
     * @param parser The parser that has something to parse.
     * @return A MimeParserHandler compliant object.
     */

    public MimeHeaderHolder createHeaderHolder(MimeParser parser) {
	return new MimeHeaders(parser);
    }

}
