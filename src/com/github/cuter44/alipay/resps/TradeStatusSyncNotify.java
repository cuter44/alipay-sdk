package com.github.cuter44.alipay.resps;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.constants.*;
import com.github.cuter44.alipay.helper.*;

public class TradeStatusSyncNotify extends NotifyBase
{
    public static final String KEY_OUT_TRADE_NO         = "out_trade_no";
    public static final String KEY_TRADE_NO             = "trade_no";
    public static final String KEY_TRADE_STATUS         = "trade_status";
    public static final String KEY_BUYER_ID             = "buyer_id";
    public static final String KEY_BUYER_EMAIL          = "buyer_email";
    public static final String KEY_SELLER_ID            = "seller_id";
    public static final String KEY_SELLER_EMAIL         = "seller_email";

    public static final String KEY_LOGISTICS_TYPE       = "logistics_type";
    public static final String KEY_LOGISTICS_FEE        = "logistics_fee";
    public static final String KEY_LOGISTICS_PAYMENT    = "logistics_payment";

    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "body",
        "business_scene",
        "buyer_email",
        "buyer_id",
        "discount",
        "extra_common_param",
        "gmt_close",
        "gmt_create",
        "gmt_payment",
        "gmt_refund",
        "is_total_fee_adjust",
        "notify_id",
        "notify_time",
        "notify_type",
        "out_channel_amount",
        "out_channel_inst",
        "out_channel_type",
        "out_trade_no",
        "payment_type",
        "price",
        "quantity",
        "refund_status",
        "seller_email",
        "seller_id",
        "subject",
        "total_fee",
        "trade_no",
        "trade_status",
        "use_coupon"
    );

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

  // VERIFY
    @Override
    public boolean verifyNotifySign(Properties conf)
        throws UnsupportedEncodingException
    {
        return(
            super.verifyNotifySign(KEYS_PARAM_NAME, conf)
        );
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

    public AlipayAccount getBuyer()
    {
        return(
            new AlipayAccount(
                this.respProp.getProperty(KEY_BUYER_ID),
                this.respProp.getProperty(KEY_BUYER_EMAIL),
                null
            )
        );
    }

    public AlipayAccount getSeller()
    {
        return(
            new AlipayAccount(
                this.respProp.getProperty(KEY_SELLER_ID),
                this.respProp.getProperty(KEY_SELLER_EMAIL),
                null
            )
        );
    }

    public Logistics getLogistics()
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
