package com.github.cuter44.alipay.reqs;

import java.util.List;
import java.util.Arrays;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.fluent.*;

import com.github.cuter44.alipay.*;
import com.github.cuter44.alipay.resps.*;

public class NotifyVerify extends WebRequestBase
{
  // CONSTANTS
    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "service",
        "notify_id",
        "partner"
    );

  // CONSTRUCT
    public NotifyVerify(Properties prop)
    {
        super(prop);
        this.setProperty("service", "notify_verify");

        return;
    }

  // BUILD
    @Override
    public NotifyVerify build()
    {
        return(this);
    }

  // SIGN
    @Override
    public NotifyVerify sign()
    {
        return(this);
    }

  // TO_URL
    @Override
    public String toURL()
    {
        try
        {
            return(
                this.toUnsignedURL(KEYS_PARAM_NAME, "utf-8")
            );
        }
        catch (UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    @Override
    public NotifyVerifyResponse execute()
        throws AlipayException
    {
        try
        {
            String params = Request.Get(this.toURL())
                .execute()
                .returnContent()
                .asString();

            return(
                new NotifyVerifyResponse(params, null)
            );
        }
        catch (Exception ex)
        {
            throw(new AlipayException(ex));
        }
    }
}
