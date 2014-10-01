package com.github.cuter44.alipay.constants;

import java.util.Map;
import java.util.HashMap;

public enum LogisticsType
{
  // ENUM
    POST    (1, "平邮"),
    EXPRESS (2, "快递"),
    EMS     (3, "EMS");

  // FIELD
    private int code;
    private String msg;

  // INDEX/REFLECT
    private static Map<Integer, LogisticsType> idxCode = new HashMap<Integer, LogisticsType>();

    public static LogisticsType forCode(int code)
    {
        return(idxCode.get(code));
    }

    public static LogisticsType forName(String name)
    {
        return(
            Enum.valueOf(LogisticsType.class, name)
        );
    }

  // CONSTRUCT
    private LogisticsType(int aCode, String aMsg)
    {
        this.code = aCode;
        this.msg = aMsg;

        return;
    }

    static {
        for (LogisticsType i:LogisticsType.values())
            idxCode.put(i.code, i);
    }
}
