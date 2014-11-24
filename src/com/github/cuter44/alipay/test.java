package com.github.cuter44.alipay;

import static java.lang.Math.abs;
import java.util.Properties;
import java.util.Random;
import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.awt.Desktop;

import com.github.cuter44.alipay.reqs.*;
import com.github.cuter44.alipay.resps.*;
import com.github.cuter44.alipay.constants.*;
import com.github.cuter44.alipay.helper.*;

/**
 * HINT: READ SOURCE CODE before you run these test!!
 */
public class test
{
    public static void browserOpen(String url)
    {
        try
        {
            System.out.println("Execute:");
            System.out.println(url);

            Desktop.getDesktop().browse(new URI(url));
        }
        catch (Exception ex)
        {
            System.out.println("Your OS seems not supporting a desktop browser, which is required... (´・ω・｀)");
            ex.printStackTrace();
        }
    }

    @Deprecated
    public static String demoTradeCreateByBuyer()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newTradeCreateByBuyer()
                .setOutTradeNo("test"+rand.nextLong())
                // equals to:
                //.setProperty("out_trade_no",        "test"+rand.nextLong())

                .setSubject("标准双接口支付测试")
                // equals to:
                //.setProperty("subject",             "标准双接口支付测试")

                .setLogistics(
                    new Logistics(
                        LogisticsType.EXPRESS, 0.01, LogisticsPayment.BUYER_PAY_AFTER_RECEIVE
                    ),
                    new Logistics(
                        LogisticsType.EMS, 0.01, LogisticsPayment.BUYER_PAY
                    ),
                    new Logistics(
                        LogisticsType.POST, 0.01, LogisticsPayment.SELLER_PAY
                    )
                )
                // similar to: (not fully listed)
                //.setProperty("logistics_type",      "EXPRESS")
                //.setProperty("logistics_fee",       "0.01")
                //.setProperty("logistics_payment",   "BUYER_PAY_AFTER_RECEIVE")

                //.setBuyer(AlipayAccount.withEmail("cuter44@qq.com"))
                .setPrice(2.00)
                .setQuantity(1);

            return(req.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static ResponseBase demoWapTradeCreateDirect()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newWapTradeCreateDirect()
                .setProperty("req_id",              "test"+rand.nextLong())
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("total_fee",           "0.01")
                .setProperty("subject",             "WAP直接到帐测试")
                .setProperty("call_back_url",       "http://www.douban.com/people/51983043")
                .setProperty("notify_url",          "http://weixin.uutime.cn/nyagalin/gateway");
            return(req.build().sign().execute());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static String demoWapAuthAndExecute(Properties prop)
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newWapAuthAndExecute(prop);
            return(req.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static String demoCreateDirectPayByUser()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newCreateDirectPayByUser()
                .setRoyalty(
                    new RoyaltyList(
                        new PaymentItem("468859947@qq.com", 1.00, "一级分润"),
                        new PaymentItem("468859947@qq.com", "18825166523", 0.50, "二级分润")
                    ).validate(2.00)
                )
                // equals to:
                //.setProperty("royalty_type","10")
                //.setProperty("royalty_parameters","468859947@qq.com^0.50^平级分润测试|18825166523^0.50^平级分润测试");

                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "直接到帐支付测试")
                .setProperty("payment_type",        "1")
                .setProperty("total_fee",           "2.00")
                .setProperty("notify_url",          "http://weixin.uutime.cn/nyagalin/gateway");

            return(req.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static String demoCreateDirectPayByUserBank()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newCreateDirectPayByUserBank()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "银行网关支付测试")
                .setProperty("payment_type",        "1")
                .setProperty("total_fee",           "0.01")
                .setProperty("defaultbank",         "ICBC-DEBIT");

            return(req.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static void demoTradeCreateByUserAndSendGoods()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req1 = factory.newTradeCreateByBuyer()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "标准双接口支付测试")
                .setProperty("payment_type",        "1")
                .setProperty("logistics_type",      "EMS")
                .setProperty("logistics_fee",       "0.01")
                .setProperty("logistics_payment",   "SELLER_PAY")
                .setProperty("price",               "0.01")
                .setProperty("quantity",            "1");

            browserOpen(req1.build().sign().toURL());
            System.out.println("Finish payment in your browser, back here and type in the trade_no:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String trade_no = reader.readLine();

            RequestBase req2 = factory.newSendGoodsConfirmByPlatform()
                .setProperty("trade_no", trade_no)
                .setProperty("transport_type", "EXPRESS")
                .setProperty("logistics_name", "黑猫宅配便");

            System.out.println(req2.build().sign().toURL());
            ResponseBase resp2 = req2.build().sign().execute();
            System.out.println(resp2.getProperties());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Deprecated
    public static String demoBatchTransNotify()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newBatchTransNotify()
                .setPaymentDetail(
                    new PaymentDetailList(
                        new PaymentItem(Long.toString(abs(rand.nextLong())), "cuter44@qq.com",   "吴嘉林", 0.01, "支付宝商家付款测试, 么么哒"),
                        new PaymentItem(Long.toString(abs(rand.nextLong())), "18825180163",      "陈晓杰", 0.01, "支付宝商家付款测试, 么么哒"),
                        //new PaymentItem(Long.toString(abs(rand.nextLong())), "468859947@qq.com", "黄文杰", 0.01, "支付宝商家付款测试, 么么哒"),
                        //new PaymentItem(Long.toString(abs(rand.nextLong())), "18814127437",      "陈蔓青", 0.01, "支付宝商家付款测试, 么么哒"),
                        //new PaymentItem(Long.toString(abs(rand.nextLong())), "cuter44@qq.com",   "吴嘉林", -0.01, "金额不正确的用例"),
                        new PaymentItem(Long.toString(abs(rand.nextLong())), "468859947@qq.com", "0xDEADDEAD", 0.01, "名字不正确的用例"),
                        new PaymentItem(Long.toString(abs(rand.nextLong())), "0xDEADDEAD",      "陈蔓青", 0.01, "帐号不正确的用例")
                    )
                )
                .setProperty("batch_no",        "batch"+abs(rand.nextLong()))

                .setProperty("notify_url",      "http://weixin.uutime.cn/nyagalin/gateway");

            return(req.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }


    public static String demoRefundFastpayByPlatformPwd()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newCreateDirectPayByUser()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "即时到帐退款测试")
                .setProperty("payment_type",        "1")
                .setProperty("total_fee",           "0.01")
                .setProperty("notify_url",          "http://weixin.uutime.cn/nyagalin/gateway");

            browserOpen(req.build().sign().toURL());

            System.out.println("Finish payment in your browser, back here and type in the trade_no:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String trade_no = reader.readLine();

            RequestBase req2 = factory.newRefundFastpayByPlatformPwd()
                .setProperty("batch_no",        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .setProperty("batch_num",       "1")
                .setProperty("detail_data",     trade_no+"^0.01^退款测试");

            return(req2.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static String demoCreatePartnerTradeByBuyer()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newCreatePartnerTradeByBuyer()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("seller_email",        "13622833735")
                .setProperty("subject",             "平台商担保支付测试")
                .setProperty("payment_type",        "1")
                .setProperty("logistics_type",      "EXPRESS")
                .setProperty("logistics_fee",       "0")
                .setProperty("logistics_payment",   "BUYER_PAY")
                .setProperty("price",               "0.01")
                .setProperty("quantity",            "1")
                //.setProperty("total_fee",           "0.01")
                .setProperty("notify_url",          "http://weixin.uutime.cn/nyagalin/gateway");

            return(req.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static String donateToAuthor()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

            RequestBase req = factory.newCreateDirectPayByUser()
                .setRoyalty(
                    new RoyaltyList(
                        new PaymentItem("cuter44@qq.com", 1.00, "辛苦了这是小鱼干(alipay-sdk)")
                    )
                )
                .setProperty("out_trade_no",        "nyaguru"+rand.nextLong())
                .setProperty("subject",             "捐赠给支付宝SDK作者")
                .setProperty("payment_type",        "1")
                .setProperty("total_fee",           "1.01")
                .setProperty("notify_url",          "http://weixin.uutime.cn/nyagalin/gateway");

            return(req.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static void main(String[] args)
    {
        // TESTCASE 1
        //browserOpen(demoTradeCreateByBuyer());

        // TESTCASE 4
        // need to change borwser UA to run, see doc/ for a avaliable UA.
        //browserOpen(
            //demoWapAuthAndExecute(
                //demoWapTradeCreateDirect().getProperties()
        //));

        // TESTCASE 5
        //browserOpen(
            //demoCreateDirectPayByUser()
        //);

        // TESTCASE 6
        //demoTradeCreateByUserAndSendGoods();

        // TESTCASE 7
        //browserOpen(demoCreateDirectPayByUserBank());

        // TESTCASE 8
        browserOpen(
            demoBatchTransNotify()
        );

        // TESTCASE 9
        //browserOpen(
            //demoRefundFastpayByPlatformPwd()
        //);

        // TESTCASE 10
        //browserOpen(
            //demoCreatePartnerTradeByBuyer()
        //);

        // Donate to author~
        // As LICENSE.md states, a postcard is more preferred.
        // But if you are too busy, there is an alternative...
        // After all, thanks~
        //browserOpen(
            //donateToAuthor()
        //);
    }
}
