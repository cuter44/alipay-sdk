package com.github.cuter44.alipay.reqs;

import java.util.List;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.alipay.util.URLBuilder;
import org.apache.http.client.fluent.*;

import com.github.cuter44.alipay.AlipayException;
import com.github.cuter44.alipay.resps.ResponseBase;
import static com.github.cuter44.alipay.util.HttpParamParser.parseHttpParam;
import static com.github.cuter44.alipay.util.XMLParser.parseXML;

public abstract class WapRequestBase extends RequestBase
{
    //public static final String PROPKEY_CHARSET      = "_input_charset";
    public static final String PROPKEY_KEY          = "KEY";
    public static final String PROPKEY_SIGN_TYPE    = "sec_id";
    public static final String PROPKEY_SIGN         = "sign";
    public static final String PROPKEY_REQ_DATA     = "req_data";
    public static final String PROPKEY_RES_DATA     = "res_data";
    public static final String URL_ALIPAY_GATEWAY   = "http://wappaygw.alipay.com/service/rest.htm";

  // BUILD
    protected void buildReqData(List<String> paramNames, String rootTag)
    {
        StringBuilder sb = new StringBuilder();

        sb.append('<').append(rootTag).append(">");
        for (String key:paramNames)
        {
            String value = this.getProperty(key);

            if (value!=null)
                sb.append('<').append(key).append('>')
                  .append(value)
                  .append("</").append(key).append(">");
        }
        sb.append("</").append(rootTag).append(">");

        //String reqData = sb.toString();
        this.setProperty(PROPKEY_REQ_DATA, sb.toString());
        return;
    }

  // SIGN
    /** Default sign adapter
     * It will grab <code>sign_type</code> and <code>sign</code>
     */
    public RequestBase sign(List<String> paramNames)
        throws UnsupportedEncodingException
    {
        String signType = this.getProperty(PROPKEY_SIGN_TYPE);
        //String charset = this.getProperty(PROPKEY_CHARSET);
        String key = this.getProperty(PROPKEY_KEY);

        String sign = this.sign(paramNames, signType, key, "utf-8");

        this.setProperty(PROPKEY_SIGN, sign);

        return(this);
    }

  // TO_URL
    /** default url constructors
     */
    protected String toUnsignedURL(List<String> paramNames, String charset)
        throws UnsupportedEncodingException
    {
        URLBuilder ub = new URLBuilder();

        ub.appendPath(URL_ALIPAY_GATEWAY);
        for (String key:paramNames)
        {
            String value = this.getProperty(key);

            if (value!=null)
            {
                //if (PROPKEY_REQ_DATA.equals(key))
                    //ub.appendParam(key,value);
                //else
                    ub.appendParamEncode(key, value, charset);
            }
        }

        return(ub.toString());
    }

    protected String toSignedURL(List<String> paramNames, String charset)
        throws UnsupportedEncodingException
    {
        URLBuilder ub = new URLBuilder();

        ub.appendPath(URL_ALIPAY_GATEWAY);
        for (String key:paramNames)
        {
            String value = this.getProperty(key);

            if (value!=null)
            {
                //if (PROPKEY_REQ_DATA.equals(key))
                    //ub.appendParam(key,value);
                //else
                    ub.appendParamEncode(key, value, charset);
            }
        }

        if (!paramNames.contains(PROPKEY_SIGN_TYPE))
            ub.appendParamEncode(PROPKEY_SIGN_TYPE, this.getProperty(PROPKEY_SIGN_TYPE), charset);

        if (!paramNames.contains(PROPKEY_SIGN))
            ub.appendParamEncode(PROPKEY_SIGN, this.getProperty(PROPKEY_SIGN), charset);

        return(ub.toString());
    }

  // EXECUTE
    @Override
    /**
     * currently throws no exception
     */
    public ResponseBase execute()
        throws AlipayException
    {
        try
        {
            String params = Request.Get(this.toURL())
                .execute()
                .returnContent()
                .asString();

            Properties prop = parseHttpParam(params);
            prop.putAll(parseXML(prop.getProperty(PROPKEY_RES_DATA)));

            return(new ResponseBase(params, prop));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw(new AlipayException(ex));
        }
    }

  // CONSTRUCT
    public WapRequestBase(Properties aConf)
    {
        super(aConf);
    }
}
