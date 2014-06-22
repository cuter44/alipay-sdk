package com.github.cuter44.alipay.reqs;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.resps.ResponseBase;
import com.github.cuter44.alipay.AlipayException;

public class WapAuthAndExecute extends WapRequestBase
{
  // CONSTANTS
    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "format",
        "partner",
        "req_data",
        "sec_id",
        "service",
        "v"
    );

    public static final List<String> TAGNAMES_REQ_DATA = Arrays.asList(
        "request_token"
    );

    public static final String TAGNAME_REQ_DATA_ROOT = "auth_and_execute_req";

  // BUILD
    @Override
    public RequestBase build()
    {
        this.buildReqData(TAGNAMES_REQ_DATA, TAGNAME_REQ_DATA_ROOT);
        return(this);
    }

  // CONSTRUCT
    public WapAuthAndExecute(Properties aConf)
    {
        super(aConf);
        this.setProperty("service", "alipay.wap.auth.authAndExecute")
            .setProperty("format" , "xml")
            .setProperty("v"      , "2.0");

        return;
    }

  // SIGN
    @Override
    public WapAuthAndExecute sign()
        throws UnsupportedEncodingException, IllegalStateException
    {
        this.sign(KEYS_PARAM_NAME);
        return(this);
    }

  // TO_URL
    @Override
    public String toURL()
        throws UnsupportedEncodingException
    {
        return(
            this.toSignedURL(KEYS_PARAM_NAME, "utf-8")
        );
    }

  // EXECUTE
    @Override
    public ResponseBase execute()
        throws AlipayException
    {
        throw(new UnsupportedOperationException("This request should run on client."));
    }
}
