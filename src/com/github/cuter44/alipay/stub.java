package com.github.cuter44.alipay;

import java.util.Properties;
import java.util.Random;

import com.github.cuter44.alipay.reqs.*;
import com.github.cuter44.alipay.resps.*;

public class stub
{
    public static String demoTradeCreateByBuyer()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = new AlipayFactory();

            RequestBase req = factory.newTradeCreateByBuyer()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "交易功能测试")
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
            AlipayFactory factory = new AlipayFactory();

            RequestBase req = factory.newWapTradeCreateDirect()
                .setProperty("req_id",              "test"+rand.nextLong())
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("total_fee",           "0.01")
                .setProperty("subject",             "交易功能测试")
                .setProperty("call_back_url",       "http://www.douban.com/people/51983043");
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
            AlipayFactory factory = new AlipayFactory();

            RequestBase req = factory.newWapAuthAndExecute(prop);
            return(req.build().sign().toURL());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public static void shellExecuteWindows(String url)
    {
        try
        {
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
        // not really executable on a PC, you'd better take down the URL and run on your phone...
        shellExecuteWindows(
            demoWapAuthAndExecute(
                demoWapTradeCreateDirect().getProperties()
        ));
    }
}
