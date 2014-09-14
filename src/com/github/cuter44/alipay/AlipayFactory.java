package com.github.cuter44.alipay;

import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.MissingResourceException;

import com.github.cuter44.alipay.reqs.*;

/** 创建支付宝业务的工厂类
 */
public class AlipayFactory
{
  // CONSTANT
    private static final String RESOURCE_ALIPAY_PROPERTIES = "/alipay.properties";

  // CONFIG
    protected Properties conf;

  // CONSTRUCT
    public AlipayFactory(Properties aConf) {
        this.conf = aConf;
    }

    /**
     * Default constructor which looking for a .properties resource in order below:
     * 1. System.getProperty("com.github.cuter44.alipay.alipay_properties")
     * 2. AlipayFactory.class.getResource("/alipay.properties")
     * Properties MUST stored in utf-8.
     */
    public AlipayFactory()
        throws MissingResourceException
    {
        String res = "";

        try
        {
            res = System.getProperty("com.github.cuter44.alipay.alipay_properties");
            res = res!=null ? res : RESOURCE_ALIPAY_PROPERTIES;

            this.conf = new Properties();
            this.conf.load(
                new InputStreamReader(
                    AlipayFactory.class.getResourceAsStream(res),
                    "utf-8"
            ));
        }
        catch (Exception ex)
        {
            MissingResourceException mrex = new MissingResourceException(
                "Failed to load conf resource.",
                AlipayFactory.class.getName(),
                res
            );
            mrex.initCause(ex);

            throw(mrex);
        }
    }

  // SINGLETON
    private static class Singleton
    {
        public static final AlipayFactory instance = new AlipayFactory();
    }

    public static AlipayFactory getInstance()
    {
        return(Singleton.instance);
    }

  // FACTORY
    public TradeCreateByBuyer newTradeCreateByBuyer()
    {
        return(
            new TradeCreateByBuyer(
                new Properties(this.conf)
        ));
    }
    public TradeCreateByBuyer newTradeCreateByBuyer(Properties p)
    {
        return(
            new TradeCreateByBuyer(
                buildConf(p, this.conf)
        ));
    }

    public SendGoodsConfirmByPlatform newSendGoodsConfirmByPlatform()
    {
        return(
            new SendGoodsConfirmByPlatform(
                new Properties(this.conf)
        ));
    }
    public SendGoodsConfirmByPlatform newSendGoodsConfirmByPlatform(Properties p)
    {
        return(
            new SendGoodsConfirmByPlatform(
                buildConf(p, this.conf)
        ));
    }

    public WapTradeCreateDirect newWapTradeCreateDirect()
    {
        return(
            new WapTradeCreateDirect(
                new Properties(this.conf)
        ));
    }
    public WapTradeCreateDirect newWapTradeCreateDirect(Properties p)
    {
        return(
            new WapTradeCreateDirect(
                buildConf(p, this.conf)
        ));
    }

    public WapAuthAndExecute newWapAuthAndExecute()
    {
        return(
            new WapAuthAndExecute(
                new Properties(this.conf)
        ));
    }

    public WapAuthAndExecute newWapAuthAndExecute(Properties p)
    {
        return(
            new WapAuthAndExecute(
                buildConf(p, this.conf)
        ));
    }

    public CreateDirectPayByUser newCreateDirectPayByUser()
    {
        return(
            new CreateDirectPayByUser(
                new Properties(this.conf)
        ));
    }

    public CreateDirectPayByUser newCreateDirectPayByUser(Properties p)
    {
        return(
            new CreateDirectPayByUser(
                buildConf(p, this.conf)
        ));
    }

    public CreateDirectPayByUserBank newCreateDirectPayByUserBank()
    {
        return(
            new CreateDirectPayByUserBank(
                new Properties(this.conf)
        ));
    }

    public CreateDirectPayByUserBank newCreateDirectPayByUserBank(Properties p)
    {
        return(
            new CreateDirectPayByUserBank(
                buildConf(p, this.conf)
        ));
    }

    public NotifyVerify newNotifyVerify()
    {
        return(
            new NotifyVerify(
                new Properties(this.conf)
        ));
    }

    public NotifyVerify newNotifyVerify(Properties p)
    {
        return(
            new NotifyVerify(
                buildConf(p, this.conf)
        ));
    }

    /** @deprecated not supported yet
     */
    @Deprecated
    public BatchTransNotify newBatchTransNotify()
    {
        return(
            new BatchTransNotify(
                new Properties(this.conf)
        ));
    }

    /** @deprecated not supported yet
     */
    @Deprecated
    public BatchTransNotify newBatchTransNotify(Properties p)
    {
        return(
            new BatchTransNotify(
                buildConf(p, this.conf)
        ));
    }

    public RefundFastpayByPlatformPwd newRefundFastpayByPlatformPwd()
    {
        return(
            new RefundFastpayByPlatformPwd(
                new Properties(this.conf)
        ));
    }

    public RefundFastpayByPlatformPwd newRefundFastpayByPlatformPwd(Properties p)
    {
        return(
            new RefundFastpayByPlatformPwd(
                buildConf(p, this.conf)
        ));
    }


  // MISC
    protected static Properties buildConf(Properties prop, Properties defaults)
    {
        Properties ret = new Properties(defaults);
        Iterator<String> iter = prop.stringPropertyNames().iterator();
        while (iter.hasNext())
        {
            String key = iter.next();
            ret.setProperty(key, prop.getProperty(key));
        }

        return(ret);
    }
}
