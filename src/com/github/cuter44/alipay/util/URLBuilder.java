package com.github.cuter44.alipay.util;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

/** Auxiliary tool to build URL for damn Alipay API.
 * Works like StringBuilder
 */
public class URLBuilder
{
    protected StringBuilder builder = new StringBuilder();
    protected boolean firstParam = true;
    protected boolean hasPath = false;

    protected void appendParamPrefix()
    {
        if (this.firstParam)
        {
            this.firstParam = false;
            if (this.hasPath)
                this.builder.append('?');
        }
        else
        {
            this.builder.append('&');
        }
    }

    public URLBuilder appendPath(String path)
    {
        if (path == null)
            return(this);

        this.hasPath = true;

        this.builder.append(path);

        return(this);
    }

    public URLBuilder appendParam(String key, String value)
    {
        if (key!=null)
        {
            this.appendParamPrefix();
            this.builder.append(key).append('=');
            if (value!=null)
                this.builder.append(value);
        }

        return(this);
    }

    public URLBuilder appendParamEncode(String key, String value)
        throws UnsupportedEncodingException
    {
        return(
            this.appendParamEncode(key, value, "utf-8")
        );
    }

    public URLBuilder appendParamEncode(String key, String value, String charset)
        throws UnsupportedEncodingException
    {
        if (key!=null)
        {
            this.appendParamPrefix();
            this.builder.append(key).append('=');
            if (value!=null)
                this.builder.append(URLEncoder.encode(value, charset));
        }

        return(this);
    }

    @Override
    public String toString()
    {
        return(
            this.builder.toString()
        );
    }

}
