package com.github.cuter44.alipay.reqs;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.*;
import com.github.cuter44.alipay.resps.*;
import com.github.cuter44.alipay.helper.*;

/** Warning: this is not fully tested.
 */
public class BatchTransNotify extends WebRequestBase
{
  // KEYS
    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "_input_charset",
        "account_name",
        "batch_fee",
        "batch_no",
        "batch_num",
        "buyer_account_name",
        "detail_data",
        "email",
        "extend_param",
        "notify_url",
        "partner",
        "pay_date",
        "service"
    );

  // CONSTRUCT
    public BatchTransNotify(Properties prop)
    {
        super(prop);
        this.setProperty("service", "batch_trans_notify");
        this.setProperty(
            "pay_date",
            new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()))
        );

        return;
    }

  // SIGN
    @Override
    public BatchTransNotify sign()
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

  // EXTRA
    /** Inject <code>payment_detail</code>, <code>batch_num</code> and <code>batch_fee</code> into this request.
     * <br />
     * Notice that this is an instant method, setPaymentDetail reads a snapshot of PDL.
     * Changes to PDL after invoking this method will not take effect.
     */
    public BatchTransNotify setPaymentDetail(PaymentDetailList l)
    {
        this.setProperty("detail_data", l.getDetailData())
            .setProperty("batch_num", l.getBatchNum())
            .setProperty("batch_fee", l.getBatchFee());

        return(this);
    }
}
