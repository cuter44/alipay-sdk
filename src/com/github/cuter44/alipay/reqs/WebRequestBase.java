package com.github.cuter44.alipay.reqs;

import java.util.List;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.util.text.URLBuilder;

public abstract class WebRequestBase extends RequestBase
{
    public static final String KEY_CHARSET      = "_input_charset";
    public static final String KEY_KEY          = "KEY";
    public static final String KEY_SIGN_TYPE    = "sign_type";
    public static final String KEY_SIGN         = "sign";
    public static final String URL_ALIPAY_GATEWAY   = "https://mapi.alipay.com/gateway.do";

  // CONSTRUCT
    public WebRequestBase(Properties aConf)
    {
        super(aConf);
    }

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
        String signType = this.getProperty(KEY_SIGN_TYPE);
        String charset = this.getProperty(KEY_CHARSET);
        String key = this.getProperty(KEY_KEY);

        String sign = this.sign(paramNames, signType, key, charset);

        this.setProperty(KEY_SIGN, sign);

        return(this);
    }

  // TO_URL
    /** default url constructors
     */
    protected String toUnsignedURL(List<String> paramNames, String charset)
        throws UnsupportedEncodingException
    {
        URLBuilder ub = new URLBuilder();

        ub.appendPath(URL_ALIPAY_GATEWAY);
        for (String key:paramNames)
            ub.appendParamEncode(key, this.getProperty(key), charset);

        return(ub.toString());
    }

    protected String toSignedURL(List<String> paramNames, String charset)
        throws UnsupportedEncodingException
    {
        URLBuilder ub = new URLBuilder();

        ub.appendPath(URL_ALIPAY_GATEWAY);
        for (String key:paramNames)
            ub.appendParamEncode(key, this.getProperty(key), charset);

        if (!paramNames.contains(KEY_SIGN_TYPE))
            ub.appendParamEncode(KEY_SIGN_TYPE, this.getProperty(KEY_SIGN_TYPE), charset);

        if (!paramNames.contains(KEY_SIGN))
            ub.appendParamEncode(KEY_SIGN, this.getProperty(KEY_SIGN), charset);

        return(ub.toString());
    }

  // PROPERTY
  // Below are most-general properties. Some of them may not work on certain type of reqs,
  // calling these api will not cause any effect or side-effect.
    public WebRequestBase setReturnUrl(String returnUrl)
    {
        this.setProperty("return_url", returnUrl);

        return(this);
    }

    public WebRequestBase setNotifyUrl(String notifyUrl)
    {
        this.setProperty("notify_url", notifyUrl);

        return(this);
    }
}
