package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class WapTradeCreateDirectResponse extends ResponseBase
{
  // CONSTANTS
    public static final String PROPKEY_REQUEST_TOKEN = "request_token";
    public String getRequestToken()
    {
        return(this.respProp.getProperty(PROPKEY_REQUEST_TOKEN));
    }

   // CONSTRUCT
    public WapTradeCreateDirectResponse(String respString, Properties respProp)
    {
        super(respString, respProp);
    }

    public WapTradeCreateDirectResponse(ResponseBase resp)
    {
        this(resp.respString, resp.respProp);
    }


}
