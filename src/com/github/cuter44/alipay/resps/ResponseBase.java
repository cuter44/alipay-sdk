package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class ResponseBase
{
  // STRING
    protected String respString;
    /**
     * retrieve callback params or response content as String
     */
    public String getString()
    {
        return(this.respString);
    }

  // PROPERTIES
    protected Properties respProp;

    /**
     * retrieve callback params or response content as Properties
     */
    public Properties getProperties()
    {
        return(this.respProp);
    }

  // CONSTRUCT
    public ResponseBase()
    {
        return;
    }

    public ResponseBase(String aRespString)
    {
        this(aRespString, null);

        return;
    }

    public ResponseBase(Properties aRespProp)
    {
        this(null, aRespProp);

        return;
    }

    public ResponseBase(String aRespString, Properties aRespProp)
    {
        this.respString = aRespString;
        this.respProp = aRespProp;

        return;
    }
}
