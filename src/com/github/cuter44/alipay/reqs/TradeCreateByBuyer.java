package com.github.cuter44.alipay.reqs;

import java.util.List;
import java.util.Arrays;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.AlipayException;
import com.github.cuter44.alipay.helper.*;
import com.github.cuter44.alipay.resps.*;

public class TradeCreateByBuyer extends WebRequestBase
{
  // KEYS
    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "_input_charset",
        "anti_phishing_key",
        "body",
        "buyer_account_name",
        "buyer_email",
        "buyer_id",
        "discount",
        "it_b_pay",
        "logistics_fee",
        "logistics_fee_1",
        "logistics_fee_2",
        "logistics_payment",
        "logistics_payment_1",
        "logistics_payment_2",
        "logistics_type",
        "logistics_type_1",
        "logistics_type_2",
        "notify_url",
        "out_trade_no",
        "partner",
        "payment_type",
        "price",
        "quantity",
        "receive_address",
        "receive_mobile",
        "receive_name",
        "receive_phone",
        "receive_zip",
        "return_url",
        "seller_account_name",
        "seller_email",
        "seller_id",
        "service",
        "show_url",
        "subject",
        "t_b_rec_post",
        "t_s_send_1",
        "t_s_send_2",
        "token",
        "total_fee"
    );

  // CONSTRUCT
    public TradeCreateByBuyer(Properties prop)
    {
        super(prop);
        this.setProperty("service",         "trade_create_by_buyer");
        this.setProperty("payment_type",    "1");

        return;
    }

  // SIGN
    @Override
    public TradeCreateByBuyer sign()
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
        String charset = this.conf.getProperty(KEY_CHARSET);

        return(
            this.toSignedURL(KEYS_PARAM_NAME, charset)
        );
    }

  // EXECUTE
    @Override
    public ResponseBase execute()
        throws AlipayException
    {
        throw(new UnsupportedOperationException("This request should run on client."));
    }

  // PROPERTY
    public TradeCreateByBuyer setOutTradeNo(String outTradeNo)
    {
        this.setProperty("out_trade_no", outTradeNo);

        return(this);
    }

    public TradeCreateByBuyer setSubject(String subject)
    {
        this.setProperty("subject", subject);

        return(this);
    }

    /**
     * Set <code>buyer_id</code>, <code>buyer_email</code>, <code>buyer_account_name</code>, if provided.
     * Setting this field can avoid paying with arbitrary account, which may annoy your customer.
     */
    public TradeCreateByBuyer setBuyer(IAlipayAccount account)
    {
        if (account.getId()!=null)
            this.setProperty("buyer_id", account.getId());

        if (account.getEmail()!=null)
            this.setProperty("buyer_email", account.getEmail());

        if (account.getAccountName()!=null)
            this.setProperty("buyer_account_name", account.getAccountName());

        return(this);
    }

    /**
     * Set <code>seller_id</code>, <code>seller_email</code>, <code>seller_account_name</code>, if provided.
     */
    public TradeCreateByBuyer setSeller(IAlipayAccount account)
    {
        if (account.getId()!=null)
            this.setProperty("seller_id", account.getId());

        if (account.getEmail()!=null)
            this.setProperty("seller_email", account.getEmail());

        if (account.getAccountName()!=null)
            this.setProperty("seller_account_name", account.getAccountName());

        return(this);
    }

    /**
     * Set <code>logistics_(type|fee|payment)(|_1|_2)</code>, redundant argument will be ignored.
     */
    public TradeCreateByBuyer setLogistics(ILogistics... logistics)
    {
        if (logistics.length>0)
        {
            ILogistics l = logistics[0];
            this.setProperty("logistics_type", l.getLogisticsType().name());
            this.setProperty("logistics_fee", String.format("%.2f", l.getLogisticsFee()));
            this.setProperty("logistics_payment", l.getLogisticsPayment().name());
        }

        if (logistics.length>1)
        {
            ILogistics l = logistics[1];
            this.setProperty("logistics_type_1", l.getLogisticsType().name());
            this.setProperty("logistics_fee_1", String.format("%.2f", l.getLogisticsFee()));
            this.setProperty("logistics_payment_1", l.getLogisticsPayment().name());
        }
        if (logistics.length>2)
        {
            ILogistics l = logistics[2];
            this.setProperty("logistics_type_2", l.getLogisticsType().name());
            this.setProperty("logistics_fee_2", String.format("%.2f", l.getLogisticsFee()));
            this.setProperty("logistics_payment_2", l.getLogisticsPayment().name());
        }

        return(this);
    }

}
