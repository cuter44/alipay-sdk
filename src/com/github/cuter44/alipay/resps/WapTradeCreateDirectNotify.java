package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class WapTradeCreateDirectNotify extends NotifyBase
{
    public WapTradeCreateDirectNotify(NotifyBase n)
    {
        this(n.getString(), n.getProperties());

        return;
    }

    public WapTradeCreateDirectNotify(String respString, Properties respProp)
    {
        super(respString, respProp);

        return;
    }
}
