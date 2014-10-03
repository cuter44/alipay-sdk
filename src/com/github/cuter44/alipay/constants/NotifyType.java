package com.github.cuter44.alipay.constants;

import java.util.Map;
import java.util.HashMap;

public enum NotifyType
{
  // ENUM
    trade_status_sync           (1, "trade_status_sync");

  // FIELD
    private int code;
    private String msg;

  // INDEX/REFLECT
    private static Map<Integer, NotifyType> idxCode = new HashMap<Integer, NotifyType>();

    public static NotifyType forCode(int code)
    {
        return(idxCode.get(code));
    }

    public static NotifyType forName(String name)
    {
        return(
            Enum.valueOf(NotifyType.class, name)
        );
    }

  // CONSTRUCT
    private NotifyType(int aCode, String aMsg)
    {
        this.code = aCode;
        this.msg = aMsg;

        return;
    }

    static {
        for (NotifyType i:NotifyType.values())
            idxCode.put(i.code, i);
    }
}
