package com.github.cuter44.alipay.reqs;

import java.util.List;
import java.util.Arrays;
import java.util.Properties;
import java.net.URISyntaxException;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.*;
import com.github.cuter44.alipay.resps.*;

public class WapTradeCreateDirect extends WapRequestBase
{
  // CONSTANTS
    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "format",
        "partner",
        "req_data",
        "req_id",
        "sec_id",
        "service",
        "v"
    );
    public static final List<String> TAGNAMES_REQ_DATA = Arrays.asList(
        "call_back_url",
        "merchant_url",
        "notify_url",
        "out_trade_no",
        "pay_expire",
        "seller_account_name",
        "subject",
        "total_fee"
    );
    public static final String TAGNAME_REQ_DATA_ROOT = "direct_trade_create_req";

  // CONSTRUCT
    public WapTradeCreateDirect(Properties prop)
    {
        super(prop);
        this.setProperty("service", "alipay.wap.trade.create.direct")
            .setProperty("format" , "xml")
            .setProperty("v"      , "2.0");
    }

  // BUILD
    @Override
    public WapTradeCreateDirect build()
    {
        this.buildReqData(TAGNAMES_REQ_DATA, TAGNAME_REQ_DATA_ROOT);
        return(this);
    }

  // SIGN
    @Override
    public WapTradeCreateDirect sign()
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
    public WapTradeCreateDirectResponse execute()
        throws AlipayException
    {
        return(
            new WapTradeCreateDirectResponse(
                super.execute()
        ));
    }
    // use default
}
