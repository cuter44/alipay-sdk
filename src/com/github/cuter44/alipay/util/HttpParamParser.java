package com.github.cuter44.alipay.util;

import java.util.Properties;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

public class HttpParamParser
{
    public static Properties parseHttpParam(String paramString)
        throws UnsupportedEncodingException
    {
        return(
            parseHttpParam(paramString, "utf-8")
        );
    }

    public static Properties parseHttpParam(String paramString, String charset)
        throws UnsupportedEncodingException
    {
        Properties prop = new Properties();
        String[] params = paramString.split("&");

        for (String param : params)
        {
            String[] kv = param.split("=");
            prop.setProperty(
                URLDecoder.decode(kv[0], charset),
                URLDecoder.decode(kv[1], charset)
            );
        }

        return prop;
    }
}
