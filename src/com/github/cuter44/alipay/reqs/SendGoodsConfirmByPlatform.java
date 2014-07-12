package com.github.cuter44.alipay.reqs;

import java.util.List;
import java.util.Arrays;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

import org.apache.http.client.fluent.*;

import com.github.cuter44.alipay.*;
import com.github.cuter44.alipay.resps.*;
import static com.github.cuter44.alipay.util.XMLParser.parseXML;

public class SendGoodsConfirmByPlatform extends WebRequestBase
{
    public static final String KEY_ERROR = "error";

    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "_input_charset",
        "create_transport_type",
        "invoice_no",
        "logistics_name",
        "partner",
        "seller_ip",
        "service",
        "trade_no",
        "transport_type"
    );

  // CONSTRUCT
    public SendGoodsConfirmByPlatform(Properties prop)
    {
        super(prop);
        this.setProperty("service", "send_goods_confirm_by_platform");

        return;
    }

  // SIGN
    @Override
    public SendGoodsConfirmByPlatform sign()
        throws UnsupportedEncodingException
    {
        this.sign(KEYS_PARAM_NAME);
        return(this);
    }

  // TO_URL
    @Override
    public String toURL()
        throws UnsupportedEncodingException
    {
        String charset = this.getProperty(KEY_CHARSET);

        return(
            this.toSignedURL(KEYS_PARAM_NAME, charset)
        );
    }

  // EXECUTE
    @Override
    public SendGoodsConfirmByPlatformResponse execute()
    {
        try
        {
            Properties prop = new Properties();

            String content = Request.Get(this.toURL())
                .execute()
                .returnContent()
                .asString();

            prop.putAll(parseXML(content));

            if (prop.getProperty(KEY_ERROR)!=null)
                throw(
                    new AlipayException(
                        prop.getProperty(KEY_ERROR)
                            .toUpperCase()
                            .replace(' ','_')
                ));

            return(new SendGoodsConfirmByPlatformResponse(content, prop));
        }
        catch (IOException ex)
        {
            throw(new AlipayException(ex));
        }
    }

}