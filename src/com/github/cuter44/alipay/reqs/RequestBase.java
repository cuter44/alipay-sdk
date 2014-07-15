package com.github.cuter44.alipay.reqs;

import java.util.Properties;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import com.github.cuter44.util.crypto.*;
import com.github.cuter44.util.text.URLBuilder;
import org.apache.http.client.fluent.*;

import com.github.cuter44.alipay.AlipayException;
import com.github.cuter44.alipay.resps.ResponseBase;

/**
 * @author galin<cuter44@foxmail.com>
 * @date 2014/6/18
 */
public abstract class RequestBase
{
  // CONSTANT
    public static final String KEY_KEY = "KEY";

  // CONFIG
    protected Properties conf;

    public String getProperty(String key)
    {
        return(
            this.conf.getProperty(key)
        );
    }

    /**
     * chain supported
     */
    public RequestBase setProperty(String key, String value)
    {
        this.conf.setProperty(key, value);
        return(this);
    }

    /**
     * batch setProperty
     */
    public RequestBase setProperties(Map aConf)
    {
        this.conf.putAll(aConf);
        return(this);
    }

  // BUILD
   public abstract RequestBase build();

  // SIGN
    /** sign
     * @exception UnsupportedOperationException if <code>sign_type</code> is other than <code>MD5</code>
     * @exception IllegalStateException if <code>Key</code> or something else (related to algorithm) not found
     */
    public abstract RequestBase sign()
        throws UnsupportedEncodingException, UnsupportedOperationException, IllegalStateException;

    /**
     * @param paramNames key names to submit, in dictionary order
     * @param signType MD5/DSA/RSA/0001, currently supported MD5 only.
     * @param charset utf-8/gb2312, utf-8 recommended.
     */
    protected String sign(List<String> paramNames, String signType, String key, String charset)
        throws UnsupportedEncodingException, UnsupportedOperationException, IllegalStateException
    {
        String sign = null;

        // SWITCH signType
        if ("MD5".equals(signType))
            sign = this.signMD5(paramNames, key, charset);
        if (sign == null)
            throw(new UnsupportedOperationException("Unrecognized sign_type:"+signType));

        return(sign);
    }

    /**
     * experimental
     * @exception UnsupportedEncodingException if <code>charset</code> is incorrect.
     */
    protected String signMD5(List<String> paramNames, String key, String charset)
        throws UnsupportedEncodingException
    {
        StringBuilder sb = new StringBuilder()
            .append(this.toQueryString(paramNames))
            .append(key);

        String sign = CryptoUtil.byteToHex(
            CryptoUtil.MD5Digest(
                sb.toString().getBytes(charset)
        ));

        return(sign);
    }

  // TO_URL
    /** Provide query string to sign().
     * toURL() may not invoke this method.
     */
    protected String toQueryString(List<String> paramNames)
    {
        URLBuilder ub = new URLBuilder();

        for (String key:paramNames)
            ub.appendParam(key, this.getProperty(key));

        return(ub.toString());
    }

    /** Generate full URL for execute
     * Auxiliary function to generate excat URL of the request, which can be executed correctly by a http-client.
     */
    public abstract String toURL()
        throws UnsupportedEncodingException;

  // EXECUTE
    /** Execute the constructed query
     */
    public abstract ResponseBase execute()
        throws AlipayException;

  // CONSTRUCT
    public RequestBase(Properties aConf)
    {
        this.conf = aConf;
        return;
    }
}

