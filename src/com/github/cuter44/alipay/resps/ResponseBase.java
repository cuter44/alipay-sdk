package com.github.cuter44.alipay.resps;

import java.util.Properties;

public class ResponseBase
{
    protected String respString;
    protected Properties respProp;

    /**
     * retrieve callback params or response content as String
     */
    public String getString()
    {
        throw(new UnsupportedOperationException("getString() not implemented"));
    }

    /**
     * retrieve callback params or response content as Properties
     */
    public Properties getProperties()
    {
        throw(new UnsupportedOperationException("getProperties() not implemented"));
    }

    public static void main(String[] args)
    {
        System.out.println("Hello World!");
    }
}
