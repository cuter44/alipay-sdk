package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class NotifyBase extends ResponseBase
{

  // CONSTRUCT
    public NotifyBase(ResponseBase resp)
    {
        this(resp.respString, resp.respProp);

        return;
    }

    public NotifyBase(String respString, Properties respProp)
    {
        super(respString, respProp);

        return;
    }


    public boolean verify()
    {
        // TODO
        return(true);
    }


}
