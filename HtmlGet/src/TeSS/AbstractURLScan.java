package TeSS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import TeSS.util.Debug;
import TeSS.util.SQLProperties;
import TeSS.util.exceptions.GeneralTelegraphException;



/** A Site searcher class.  When given a site definition it will 
 *  go out and grab the site and then parse it based on that definition.
 *  See the documentation for information about writting site defintions.
 *  @author <A HREF="mailto:nickl@db.cs.berkeley.edu">Nick Lanham</A>
 */
public abstract class AbstractURLScan {
    
  /** Only for use with scripts that don't want to use the arguments from the .jsc file
   * that calls the script and rather want to use args from other sources */
  public AbstractURLScan() {
    mReader = null;
    mProperties = null;
    mURLConfig = null;
  }


	public AbstractURLScan(URLConfig urlConfig, InputStream is)
	{
		mURLConfig=urlConfig;
		mProperties=new SQLProperties();
		mReader=new BufferedReader(new InputStreamReader(is));
		mDone=false;
		mTarget="http://localhost/url-unknown";
	}


  /** This sets up all the needed info to scan the given site 
   * Starts a new scan on this web page with specified search arguments.
   * @param urlConfig Config information about the web page used to parse it
   * @param properties URL properties (name-value pairs)
   */
  public AbstractURLScan(URLConfig urlConfig, SQLProperties properties) 
  throws GeneralTelegraphException 
  {
	  org.w3c.www.protocol.http.Reply reply=null;   
    mURLConfig = urlConfig;
    mProperties = new SQLProperties(properties);
    BufferedReader aReader = null;
    String target = null;
 

	target = mURLConfig.getHost() + mURLConfig.getURL();

	String name="";
	String propsstr="";
	Properties postprops=null;
	target += "?";
   
	for (Iterator props=properties.getNames(), values=properties.getValues();
		 props.hasNext() && values.hasNext(); )
		{
					String nextKey = (String) props.next();
					String nextValue = (String) values.next();
					 propsstr += nextKey + "="+nextValue ;
					if (props.hasNext() && values.hasNext()) {
						propsstr += "&";
	
					}
		}
	if(mURLConfig.doPost())
		{
			postprops = mURLConfig.htmlget.convertStringToPostProperties(propsstr);
		}
	else
		{
			postprops = null;
			target += propsstr;
		}
	try
		{
			
			reply = mURLConfig.htmlget.requestWithRedirect(new URL("http://"+target),1,mURLConfig.doPost(),postprops, true);
			aReader = new BufferedReader(new InputStreamReader(reply.getInputStream()), 2048); 
								 
		}
	catch (Throwable t)
		{
			throw new GeneralTelegraphException(t.getMessage());
		}



    mReader = aReader;
    mDone = false;
    mTarget = target;
    if (mReader==null) Debug.error("cannot open reader on target " + target);
		  
  }



  /** Closes this scanner, freeing any internal data structures */
  public void close() throws GeneralTelegraphException {
//    Debug.println("closing " + this);
    try {
      mReader.close();
    }catch(IOException e) {
      Debug.error("AbstractURLScan " + this + " got IOException " + e + 
                  " while closing " + mReader + " for properties " + mProperties);
    }
    mReader = null;
    mDone = true;
  }



  /** Checks if this scan has finished */
  public boolean isDone() {return mDone;}


  /** Gets the next row from the web page as an array of strings 
   * @return Array of strings of results, in order specified in mURLConfig */
  public abstract Object[] getNextRow() throws GeneralTelegraphException;




  /** Metadata to parse webpages */
  protected final URLConfig mURLConfig;

  /** URL properties string used for this scan */
  protected final SQLProperties mProperties;

  /** Cursor for reading from web page */
  protected volatile BufferedReader mReader;

  /** Whether this scan has finished */
  protected boolean mDone;

  /** Whether this scan has any data in it or not */
  protected boolean mNoData = false;

  /** StringBuffer that holds all the page text */
  protected StringBuffer mStringBuf = new StringBuffer();

  /** Boolean that the pagebuffer sets to say it has buffered the whole page */
  protected boolean readDone = false;

  protected String mTarget = "";

}







