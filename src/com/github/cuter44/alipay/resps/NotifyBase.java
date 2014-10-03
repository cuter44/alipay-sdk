package com.github.cuter44.alipay.resps;

import java.util.Properties;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

import com.github.cuter44.alipay.AlipayFactory;
import com.github.cuter44.alipay.reqs.NotifyVerify;
import com.github.cuter44.alipay.constants.*;

public class NotifyBase extends ResponseBase
{
  // CONSTANTS
    protected static final String KEY_NOTIFY_ID = "notify_id";
    protected static final String KEY_NOTIFY_TYPE = "notify_type";
    protected static final String KEY_NOTIFY_TIME = "notify_time";

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
    public String getProperty(String key)
    {
        return(
            this.respProp.getProperty(key)
        );
    }

  // PROPERTY
    public String getNotifyId()
    {
        return(
            this.respProp.getProperty(KEY_NOTIFY_ID)
        );
    }

    public NotifyType getNotifyType()
    {
        return(
            NotifyType.forName(
                this.respProp.getProperty(KEY_NOTIFY_TYPE)
            )
        );
    }

    public Date getNotifyTime()
    {
        return(
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(
                    this.respProp.getProperty(KEY_NOTIFY_TIME),
                    new ParsePosition(1)
                )
        );
    }

}
