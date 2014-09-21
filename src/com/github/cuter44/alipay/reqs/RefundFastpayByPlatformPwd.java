package com.github.cuter44.alipay.reqs;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.*;
import com.github.cuter44.alipay.resps.*;

/** Warning: this is not fully tested.
 */
public class RefundFastpayByPlatformPwd extends WebRequestBase
{
  // KEYS
    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "_input_charset",
        "batch_no",
        "batch_num",
        "detail_data",
        "notify_url",
        "partner",
        "refund_date",
        "seller_email",
        "seller_user_id",
        "service"
    );

  // CONSTRUCT
    public RefundFastpayByPlatformPwd(Properties prop)
    {
        super(prop);
        this.setProperty("service", "refund_fastpay_by_platform_pwd");
        this.setProperty(
            "refund_date",
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))
        );

        return;
    }

  // SIGN
    @Override
    public RefundFastpayByPlatformPwd sign()
        throws UnsupportedEncodingException
    {
        this.sign(KEYS_PARAM_NAME);
        return(this);
    }

  // TO_URL
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
    public ResponseBase execute()
    {
        throw(new UnsupportedOperationException("This request should run on client."));
    }
}
