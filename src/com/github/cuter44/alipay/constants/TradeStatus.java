package com.github.cuter44.alipay.constants;

import java.util.Map;
import java.util.HashMap;

public enum TradeStatus
{
  // ENUM
    WAIT_BUYER_PAY              (0, "等待买家付款"),

    WAIT_SELLER_SEND_GOODS      (2, "等待卖家发货"),
    COD_WAIT_SELLER_SEND_GOODS  (3, "等待卖家发货(货到付款)"),

    WAIT_BUYER_CONFIRM_GOODS    (4, "等待买家签收"),
    COD_WAIT_BUTER_PAY          (5, "等待买家签收(货到付款)"),

    COD_WAIT_SYS_PAY_SELLER     (6, "等待到帐"),

    TRADE_PENDING               (7, "卖家帐号无法收款"),

    TRADE_SUCCESS               (8, "交易成功"),
    TRADE_FINISH                (10, "交易结束"),

    TRADE_CLOSED                (-1,"交易取消");

  // FIELD
    private int code;
    private String msg;

  // INDEX/REFLECT
    private static Map<Integer, TradeStatus> idxCode = new HashMap<Integer, TradeStatus>();

    public static TradeStatus forCode(int code)
    {
        return(idxCode.get(code));
    }

    public static TradeStatus forName(String name)
    {
        return(
            Enum.valueOf(TradeStatus.class, name)
        );
    }

  // CONSTRUCT
    private TradeStatus(int aCode, String aMsg)
    {
        this.code = aCode;
        this.msg = aMsg;

        return;
    }

    static {
        for (TradeStatus i:TradeStatus.values())
            idxCode.put(i.code, i);
    }
}
