package com.github.cuter44.alipay.resps;

import java.util.List;
import java.util.Properties;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import com.github.cuter44.util.crypto.CryptoUtil;
import com.github.cuter44.util.text.URLBuilder;

import com.github.cuter44.alipay.AlipayFactory;
import com.github.cuter44.alipay.reqs.NotifyVerify;
import com.github.cuter44.alipay.constants.*;

public class NotifyBase extends ResponseBase
{
  // CONSTANTS
    protected static final String KEY_NOTIFY_ID = "notify_id";
    protected static final String KEY_NOTIFY_TYPE = "notify_type";
    protected static final String KEY_NOTIFY_TIME = "notify_time";

    protected Boolean validity = null;

  // CONSTRUCT
    public NotifyBase(ResponseBase resp)
    {
        this(resp.respString, resp.respProp);

        return;
    }

    public NotifyBase(String respString, Properties respProp)
    {
        super(respString, respProp);

        return;
    }

  // VERIFY
    protected String sign(List<String> paramNames, String signType, String key, String charset)
        throws UnsupportedEncodingException, UnsupportedOperationException
    {
        String sign = null;

        // SWITCH signType
        if ("MD5".equals(signType))
            sign = this.signMD5(paramNames, key, charset);
        if (sign == null)
            throw(new UnsupportedOperationException("Unrecognized sign_type:"+signType));

        return(sign);
    }

    /**
     * @exception UnsupportedEncodingException if <code>charset</code> is incorrect.
     */
    protected String signMD5(List<String> paramNames, String key, String charset)
        throws UnsupportedEncodingException
    {
        if (key == null)
            throw(new IllegalArgumentException("No KEY, no sign. Please check your configuration."));

        StringBuilder sb = new StringBuilder()
            .append(this.toQueryString(paramNames))
            .append(key);

        String sign = CryptoUtil.byteToHex(
            CryptoUtil.MD5Digest(
                sb.toString().getBytes(charset)
        ));

        return(sign);
    }

    /** Provide query string to sign().
     * toURL() may not invoke this method.
     */
    protected String toQueryString(List<String> paramNames)
    {
        URLBuilder ub = new URLBuilder();

        for (String key:paramNames)
            ub.appendParam(key, this.getProperty(key));

        return(ub.toString());
    }

    public boolean verifyNotifySign(List<String> paramNames, Properties conf)
        throws UnsupportedEncodingException
    {
        String stated = this.getProperty("sign");
        String calculated = this.sign(
            paramNames,
            this.getProperty("sign_type"),
            conf.getProperty("KEY"),
            conf.getProperty("_input_charset")
        );

        return(
            stated!=null && stated.equals(calculated)
        );
    }

    /** 子类应该实现这个方法以验证签名
     * SUB-CLASS MUST IMPLEMENT THIS METHOD TO BE CALLBACKED.
     */
    protected boolean verifyNotifySign(Properties conf)
        throws UnsupportedEncodingException, UnsupportedOperationException
    {
        throw(new UnsupportedOperationException("Don't know which params should be signed."));
    }

    /** 向支付宝询问该 Notify 的合法性
     */
    public boolean verifyNotifyId(Properties conf)
    {
        Properties finals = new Properties(conf);
        finals.setProperty(KEY_NOTIFY_ID, this.getNotifyId());

        NotifyVerify req = new NotifyVerify(finals);

        NotifyVerifyResponse resp = req.build().execute();

        return(resp.isTrue());
    }

    /** @deprecated for decoupling, please use <code>verify(Properties prop)</code> instead
     * Now this method is forwarded to this.verify(factory.getConf())
     */
    @Deprecated
    public boolean verify(AlipayFactory factory)
        throws UnsupportedEncodingException
    {
        return(
            this.verify(
                factory.getConf()
            )
        );
    }

    public boolean verify(Properties conf)
        throws UnsupportedEncodingException
    {
        if (this.validity != null)
            return(this.validity);

        Boolean skipId      = Boolean.valueOf(conf.getProperty("SKIP_VERIFY_NOTIFY_ID"));
        Boolean skipSign    = Boolean.valueOf(conf.getProperty("SKIP_VERIFY_NOTIFY_SIGN"));

        // else
        this.validity = true;

        if (!skipId)
            this.validity = this.validity && this.verifyNotifyId(conf);

        if (!skipSign)
            this.validity = this.validity && this.verifyNotifySign(conf);

        return(this.validity);
    }



  // GET
    public String getProperty(String key)
    {
        return(
            this.respProp.getProperty(key)
        );
    }

  // PROPERTY
    public String getNotifyId()
    {
        return(
            this.respProp.getProperty(KEY_NOTIFY_ID)
        );
    }

    public NotifyType getNotifyType()
    {
        return(
            NotifyType.forName(
                this.respProp.getProperty(KEY_NOTIFY_TYPE)
            )
        );
    }

    /**
     * exception ParseException while format is incorrect
     */
    public Date getNotifyTime()
        throws ParseException
    {
        return(
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(this.respProp.getProperty(KEY_NOTIFY_TIME))
        );
    }

}
