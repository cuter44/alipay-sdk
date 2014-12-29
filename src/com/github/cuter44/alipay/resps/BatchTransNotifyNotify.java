package com.github.cuter44.alipay.resps;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import com.github.cuter44.alipay.constants.*;
import com.github.cuter44.alipay.helper.*;

public class BatchTransNotifyNotify extends NotifyBase
{
    public static final String KEY_BATCH_NO         = "batch_no";
    public static final String KEY_SUCCESS_DETAILS  = "success_details";
    public static final String KEY_FAIL_DETAILS     = "fail_details";
    public static final String KEY_PAY_ACCOUNT_NO   = "pay_account_no";
    public static final String KEY_PAY_USER_ID      = "pay_user_id";
    public static final String KEY_PAY_USER_NAME    = "pay_user_name";

    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
        "batch_no",
        "fail_details",
        "notify_id",
        "notify_time",
        "notify_type",
        "pay_account_no",
        "pay_user_id",
        "pay_user_name",
        "success_details"
    );

    protected List<PaymentItem> successDetails = null;
    protected List<PaymentItem> failDetails = null;

    public BatchTransNotifyNotify(NotifyBase n)
    {
        this(n.getString(), n.getProperties());

        return;
    }

    public BatchTransNotifyNotify(String respString, Properties respProp)
    {
        super(respString, respProp);

        return;
    }

  // VERIFY
    @Override
    public boolean verifyNotifySign(Properties conf)
        throws UnsupportedEncodingException
    {
        return(
            super.verifyNotifySign(KEYS_PARAM_NAME, conf)
        );
    }

  // PROPERTY
    public String getBatchNo()
    {
        return(
            this.respProp.getProperty(KEY_BATCH_NO)
        );
    }

    public AlipayAccount getPayUser()
    {
        AlipayAccount aa = new AlipayAccount(
            this.respProp.getProperty(KEY_PAY_USER_ID),
            this.respProp.getProperty(KEY_PAY_USER_NAME),
            this.respProp.getProperty(KEY_PAY_ACCOUNT_NO)
        );

        return(aa);
    }

    public List<PaymentItem> getSuccessDetails()
    {
        if (this.successDetails != null)
            return(this.successDetails);

        String details = this.respProp.getProperty(KEY_SUCCESS_DETAILS);
        List<PaymentItem> l = new ArrayList<PaymentItem>();

        if (details == null)
            return(l);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        String[] items = details.split("\\|");

        for (String item:items)
        {
            try
            {
                String[] values = item.split("\\^");
                if (values.length == 1)
                    continue;

                PaymentItem pi = new PaymentItem(
                    values[0],
                    values[1],
                    values[2],
                    Double.valueOf(values[3]),
                    "S".equals(values[4]),
                    null,
                    values[6],
                    df.parse(values[7])
                );

                l.add(pi);
            }
            catch (ParseException ex)
            {
                ex.printStackTrace();
            }
        }

        this.successDetails = l;

        return(l);
    }

}
