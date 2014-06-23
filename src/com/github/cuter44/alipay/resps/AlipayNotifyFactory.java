package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class AlipayNotifyFactory
{
    protected static final String PROPKEY_SERVICE = "service";

    /** wrap notify
     * if service not known, it returns a NotifyBase
     */
    public static NotifyBase newNotifyInstance(Properties prop)
    {
        String notifyType = prop.getProperty(PROPKEY_SERVICE);

        if ("alipay.wap.trade.create.direct".equals(notifyType))
            return(new WapTradeCreateDirectNotify(null, prop));

        return(new NotifyBase(null, prop));
    }
}
