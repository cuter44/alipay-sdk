package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class WapTradeCreateDirectResponse extends ResponseBase
{
  // CONSTANTS
    public static final String KEY_REQUEST_TOKEN = "request_token";

  // CONSTRUCT
    public WapTradeCreateDirectResponse(ResponseBase r)
    {
        this(r.respString, r.respProp);
    }

    public WapTradeCreateDirectResponse(String respString, Properties respProp)
    {
        super(respString, respProp);
    }

  // MISC
    public String getRequestToken()
    {
        return(this.respProp.getProperty(KEY_REQUEST_TOKEN));
    }


}
