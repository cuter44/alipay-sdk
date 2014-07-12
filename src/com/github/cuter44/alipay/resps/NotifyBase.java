package com.github.cuter44.alipay.resps;

import java.util.Properties;

import com.github.cuter44.alipay.AlipayFactory;
import com.github.cuter44.alipay.reqs.NotifyVerify;

public class NotifyBase extends ResponseBase
{
  // CONSTANTS
    protected static final String KEY_NOTIFY_ID = "notify_id";

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

  // VERIFY
    /** 向支付宝询问该 Notify 的合法性
     */
    public boolean verify(AlipayFactory factory)
    {
        NotifyVerify req = factory.newNotifyVerify();
        req.setProperty("notify_id", this.getNotifyId());

        NotifyVerifyResponse resp = req.build().execute();

        return(resp.isTrue());
    }

  // GET
    public String getNotifyId()
    {
        return(
            this.respProp.getProperty(KEY_NOTIFY_ID)
        );
    }


}
