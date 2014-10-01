package com.github.cuter44.alipay.constants;

import java.util.Map;
import java.util.HashMap;

public enum LogisticsPayment
{
  // ENUM
    BUYER_PAY               (1, "买家承担运费"),
    SELLER_PAY              (2, "卖家承担运费"),
    BUYER_PAY_AFTER_RECEIVE (3, "买家到货付款");

  // FIELD
    private int code;
    private String msg;

  // INDEX/REFLECT
    private static Map<Integer, LogisticsPayment> idxCode = new HashMap<Integer, LogisticsPayment>();

    public static LogisticsPayment forCode(int code)
    {
        return(idxCode.get(code));
    }

    public static LogisticsPayment forName(String name)
    {
        return(
            Enum.valueOf(LogisticsPayment.class, name)
        );
    }

  // CONSTRUCT
    private LogisticsPayment(int aCode, String aMsg)
    {
        this.code = aCode;
        this.msg = aMsg;

        return;
    }

    static {
        for (LogisticsPayment i:LogisticsPayment.values())
            idxCode.put(i.code, i);
    }
}
