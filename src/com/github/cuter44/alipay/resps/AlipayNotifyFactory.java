package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class AlipayNotifyFactory
{
  // CONSTANTS
    protected static final String TYPE_TRADE_STATUS_SYNC = "trade_status_sync";

    protected static final String KEY_NOTIFY_TYPE = "notify_type";

  // FACTORY
    /** Return a NotifyBase WITHOUT parsing a query string.
     */
    public static NotifyBase newNotifyInstance(String queryString)
    {
        return(new NotifyBase(queryString, null));
    }

    public static NotifyBase newNotifyInstance(Properties prop)
    {
        return(newNotifyInstance(null, prop));
    }


    /** wrap notify
     * if service not known, it returns a NotifyBase
     */
    public static NotifyBase newNotifyInstance(String queryString, Properties prop)
    {
        String notifyType = prop.getProperty(KEY_NOTIFY_TYPE);

        //if ("alipay.wap.trade.create.direct".equals(notifyType))
            //return(new WapTradeCreateDirectNotify(null, prop));

        if ("trade_status_sync".equals(notifyType))
            return(new TradeStatusSyncNotify(null, prop));

        if ("batch_trans_notify".equals(notifyType))
            return(new BatchTransNotifyNotify(null, prop));

        return(new NotifyBase(null, prop));
    }
}
