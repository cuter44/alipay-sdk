package com.github.cuter44.alipay.reqs;

import java.util.List;
import java.util.Properties;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.util.URLBuilder;

public abstract class WebRequestBase extends RequestBase
{
    public static final String PROPKEY_CHARSET      = "_input_charset";
    public static final String PROPKEY_KEY          = "KEY";
    public static final String PROPKEY_SIGN_TYPE    = "sign_type";
    public static final String PROPKEY_SIGN         = "sign";
    public static final String URL_ALIPAY_GATEWAY   = "https://mapi.alipay.com/gateway.do";

  // BUILD
    @Override
    public RequestBase build()
    {
        return(this);
    }

  // SIGN
    /** Default sign adapter
     * It will grab <code>sign_type</code> and <code>sign</code>
     */
    public RequestBase sign(List<String> paramNames)
        throws UnsupportedEncodingException
    {
        String signType = this.getProperty(PROPKEY_SIGN_TYPE);
        String charset = this.getProperty(PROPKEY_CHARSET);
        String key = this.getProperty(PROPKEY_KEY);

        String sign = this.sign(paramNames, signType, key, charset);

        this.setProperty(PROPKEY_SIGN, sign);

        return(this);
    }

  // TO_URL
    /** default url constructors
     */
    protected String toUnsignedURL(List<String> paramNames, String charset)
        throws UnsupportedEncodingException
    {
        URLBuilder ub = new URLBuilder();
        Iterator<String> iter = paramNames.iterator();

        ub.appendPath(URL_ALIPAY_GATEWAY);
        while (iter.hasNext())
        {
            String key = iter.next();
            String value = this.getProperty(key);

            if (value!=null)
                ub.appendParamEncode(key, value, charset);
        }

        return(ub.toString());
    }

    protected String toSignedURL(List<String> paramNames, String charset)
        throws UnsupportedEncodingException
    {
        URLBuilder ub = new URLBuilder();
        Iterator<String> iter = paramNames.iterator();

        ub.appendPath(URL_ALIPAY_GATEWAY);
        while (iter.hasNext())
        {
            String key = iter.next();
            String value = this.getProperty(key);

            if (value!=null)
                ub.appendParamEncode(key, value, charset);
        }

        if (!paramNames.contains(PROPKEY_SIGN_TYPE))
            ub.appendParamEncode(PROPKEY_SIGN_TYPE, this.getProperty(PROPKEY_SIGN_TYPE), charset);

        if (!paramNames.contains(PROPKEY_SIGN))
            ub.appendParamEncode(PROPKEY_SIGN, this.getProperty(PROPKEY_SIGN), charset);

        return(ub.toString());
    }

  // CONSTRUCT
    public WebRequestBase(Properties aConf)
    {
        super(aConf);
    }
}
