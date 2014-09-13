package com.github.cuter44.alipay;

import java.util.Properties;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.github.cuter44.alipay.reqs.*;
import com.github.cuter44.alipay.resps.*;

public class stub
{
    @Deprecated
    public static String demoTradeCreateByBuyer()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getInstance();

            RequestBase req = factory.newTradeCreateByBuyer()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "标准双接口支付测试")
                .setProperty("payment_type",        "1")
                .setProperty("logistics_type",      "EMS")
                .setProperty("logistics_fee",       "0.01")
                .setProperty("logistics_payment",   "SELLER_PAY")
                .setProperty("price",               "2.00")
                .setProperty("quantity",            "1");

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
            AlipayFactory factory = AlipayFactory.getInstance();

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
            AlipayFactory factory = AlipayFactory.getInstance();

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
            AlipayFactory factory = AlipayFactory.getInstance();

            RequestBase req = factory.newCreateDirectPayByUser()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "直接到帐支付测试")
                .setProperty("payment_type",        "1")
                .setProperty("total_fee",           "0.01")
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
            AlipayFactory factory = AlipayFactory.getInstance();

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
            AlipayFactory factory = AlipayFactory.getInstance();

            RequestBase req1 = factory.newTradeCreateByBuyer()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "标准双接口支付测试")
                .setProperty("payment_type",        "1")
                .setProperty("logistics_type",      "EMS")
                .setProperty("logistics_fee",       "0.01")
                .setProperty("logistics_payment",   "SELLER_PAY")
                .setProperty("price",               "0.01")
                .setProperty("quantity",            "1");

            shellExecuteWindows(req1.build().sign().toURL());
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


    public static void shellExecuteWindows(String url)
    {
        try
        {
            System.out.println("Execute:");
            System.out.println(url);

            Runtime.getRuntime().exec(
                "rundll32 url.dll,FileProtocolHandler "+url
            );

            return;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        // TESTCASE 1
        //shellExecuteWindows(demoTradeCreateByBuyer());

        // TESTCASE 4
        // need to change borwser UA to run, see doc/ for a avaliable UA.
        //shellExecuteWindows(
            //demoWapAuthAndExecute(
                //demoWapTradeCreateDirect().getProperties()
        //));

        // TESTCASE 5
        shellExecuteWindows(
            demoCreateDirectPayByUser()
        );

        // TESTCASE 6
        //demoTradeCreateByUserAndSendGoods();

        // TESTCASE 7
        //shellExecuteWindows(demoCreateDirectPayByUserBank());

    }
}
