package com.github.cuter44.alipay.resps;

import java.util.Properties;

import com.github.cuter44.alipay.constants.TradeStatus;

public class SendGoodsConfirmByPlatformResponse extends ResponseBase
{
    public static final String KEY_OUT_TRADE_NO = "out_trade_no";
    public static final String KEY_TRADE_STATUS = "trade_status";

    public SendGoodsConfirmByPlatformResponse(ResponseBase r)
    {
        this(r.getString(), r.getProperties());

        return;
    }

    public SendGoodsConfirmByPlatformResponse(String respString, Properties respProp)
    {
        super(respString, respProp);

        return;
    }

    public String getOutTradeNo()
    {
        return(
            this.respProp.getProperty(KEY_OUT_TRADE_NO)
        );
    }

    public TradeStatus getTradeStatus()
    {
        return(
            TradeStatus.forName(
                this.respProp.getProperty(KEY_TRADE_STATUS)
        ));
    }
}
