package com.github.cuter44.alipay.resps;

import java.util.Properties;

import com.github.cuter44.alipay.constants.*;
import com.github.cuter44.alipay.helper.*;

public class TradeStatusSyncNotify extends NotifyBase
{
    public static final String KEY_OUT_TRADE_NO = "out_trade_no";
    public static final String KEY_TRADE_NO = "trade_no";
    public static final String KEY_TRADE_STATUS = "trade_status";
    public static final String KEY_BUYER_ID = "buyer_id";
    public static final String KEY_BUYER_EMAIL = "buyer_email";
    public static final String KEY_SELLER_ID = "seller_id";
    public static final String KEY_SELLER_EMAIL = "seller_email";

    public static final String KEY_LOGISTICS_TYPE = "logistics_type";
    public static final String KEY_LOGISTICS_FEE = "logistics_fee";
    public static final String KEY_LOGISTICS_PAYMENT = "logistics_payment";

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

  // PROPERTY
    public String getOutTradeNo()
    {
        return(
            this.respProp.getProperty(KEY_OUT_TRADE_NO)
        );
    }

    public String getTradeNo()
    {
        return(
            this.respProp.getProperty(KEY_TRADE_NO)
        );
    }


    public TradeStatus getTradeStatus()
    {
        return(
            TradeStatus.forName(
                this.respProp.getProperty(KEY_TRADE_STATUS)
        ));
    }

    public IAlipayAccount getBuyer()
    {
        return(
            new AlipayAccount(
                this.respProp.getProperty(KEY_BUYER_ID),
                this.respProp.getProperty(KEY_BUYER_EMAIL),
                null
            )
        );
    }

    public IAlipayAccount getSeller()
    {
        return(
            new AlipayAccount(
                this.respProp.getProperty(KEY_SELLER_ID),
                this.respProp.getProperty(KEY_SELLER_EMAIL),
                null
            )
        );
    }

    public ILogistics getLogistics()
    {
        return(
            new Logistics(
                LogisticsType.forName(this.respProp.getProperty(KEY_LOGISTICS_TYPE)),
                Double.valueOf(this.respProp.getProperty(KEY_SELLER_EMAIL)),
                LogisticsPayment.forName(this.respProp.getProperty(KEY_LOGISTICS_PAYMENT))
            )
        );
    }
}
