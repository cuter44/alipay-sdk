package com.github.cuter44.alipay.resps;

import java.util.Properties;

/**
 * @deprecated Now merged to TradeStatusSyncNotify, this class of notify is no longer generated.
 */
@Deprecated
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
