package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class NotifyVerifyResponse extends ResponseBase
{
  // CONSTRUCT
    public NotifyVerifyResponse(ResponseBase r)
    {
        this(r.respString, r.respProp);

        return;
    }

    public NotifyVerifyResponse(String respString, Properties respProp)
    {
        super(respString, respProp);

        return;
    }

  // MISC
    public boolean isTrue()
    {
        return(
            "true".equals(this.respString)
        );
    }
}
