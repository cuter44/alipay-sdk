package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class AlipayNotifyFactory
{
  // CONSTANTS
    protected static final String TYPE_TRADE_STATUS_SYNC = "trade_status_sync";

    protected static final String KEY_NOTIFY_TYPE = "notify_type";

  // FACTORY
    /** wrap notify
     * if service not known, it returns a NotifyBase
     */
    public static NotifyBase newNotifyInstance(Properties prop)
    {
        String notifyType = prop.getProperty(KEY_NOTIFY_TYPE);

        //if ("alipay.wap.trade.create.direct".equals(notifyType))
            //return(new WapTradeCreateDirectNotify(null, prop));

        if ("trade_status_sync".equals(notifyType))
            return(new TradeStatusSyncNotify(null, prop));

        return(new NotifyBase(null, prop));
    }
}
