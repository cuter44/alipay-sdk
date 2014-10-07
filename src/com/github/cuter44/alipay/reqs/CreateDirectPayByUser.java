package com.github.cuter44.alipay.reqs;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.*;
import com.github.cuter44.alipay.resps.*;
import com.github.cuter44.alipay.helper.*;

public class CreateDirectPayByUser extends CreateTradeBase
{
  // KEYS
    public static final String KEY_ROYALTY_TYPE        = "royalty_type";
    public static final String KEY_ROYALTY_TYPE_V      = "10";
    public static final String KEY_ROYALTY_PARAMETERS  = "royalty_parameters";

    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "_input_charset",
        "anti_phishing_key",
        "body",
        "buyer_account_name",
        "buyer_email",
        "buyer_id",
        "default_login",
        "enable_paymethod",
        "err_notify_uri",
        "extend_param",
        "exter_invoke_ip",
        "extra_common_param",
        "it_b_pay",
        "item_order_info",
        "need_ctu_check",
        "notify_url",
        "out_trade_no",
        "partner",
        "payment_type",
        "paymethod",
        "price",
        "product_type",
        "qr_pay_mode",
        "quantity",
        "return_url",
        "royalty_parameters",
        "royalty_type",
        "seller_account_name",
        "seller_email",
        "seller_id",
        "service",
        "show_url",
        "sign_id_ext",
        "subject",
        "token",
        "total_fee"
    );

  // CONSTRUCT
    public CreateDirectPayByUser(Properties prop)
    {
        super(prop);
        this.setProperty("service", "create_direct_pay_by_user");

        return;
    }

  // SIGN
    @Override
    public CreateDirectPayByUser sign()
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

  // PROPERTY
    public CreateDirectPayByUser setRoyalty(RoyaltyList l)
    {
        this.setProperty(KEY_ROYALTY_TYPE, KEY_ROYALTY_TYPE_V);
        this.setProperty(KEY_ROYALTY_PARAMETERS, l.getRoyaltyParameters());

        return(this);
    }
}
