package com.github.cuter44.alipay.reqs;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.*;
import com.github.cuter44.alipay.resps.*;

public class CreatePartnerTradeByBuyer extends WebRequestBase
{
  // KEYS
    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "_input_charset",
        "body",
        "buyer_account_name",
        "buyer_email",
        "buyer_id",
        "discount",
        "it_b_pay",
        "logistics_fee",
        "logistics_payment",
        "logistics_type",
        "logistics_fee_1",
        "logistics_payment_1",
        "logistics_type_1",
        "logistics_fee_2",
        "logistics_payment_2",
        "logistics_type_2",
        "notify_url",
        "out_trade_no",
        "partner",
        "payment_type",
        "price",
        "quantity",
        "receive_address",
        "receive_name",
        "receive_mobile",
        "receive_phone",
        "receive_zip",
        "return_url",
        "seller_account_name",
        "seller_email",
        "seller_id",
        "service",
        "show_url",
        "subject",
        "t_s_send_1",
        "t_s_send_2",
        "t_b_rec_post",
        "token",
        "total_fee"
    );

  // CONSTRUCT
    public CreatePartnerTradeByBuyer(Properties prop)
    {
        super(prop);
        this.setProperty("service", "create_partner_trade_by_buyer");

        return;
    }

  // SIGN
    @Override
    public CreatePartnerTradeByBuyer sign()
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
    public ResponseBase execute()
    {
        throw(new UnsupportedOperationException("This request should run on client."));
    }
}
