package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class TradeStatusSyncNotify extends NotifyBase
{
    public TradeStatusSyncNotify(NotifyBase n)
    {
        this(n.getString(), n.getProperties());

        return;
    }

    public TradeStatusSyncNotify(String respString, Properties respProp)
    {
        super(respString, respProp);

        return;
    }
}
