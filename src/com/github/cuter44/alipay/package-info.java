/** Alipay SDK | 支付宝接口封装
 * @author "galin"&lt;cuter44@foxmail.com&gt;
 * @date 2014/6/17
 * @version 1.0-alipay1.2
 *
 * REQUIRED_DT
 * apache-ant-1.7+
 *
 * REQUIRED-RT
 * apache-http-components-4+
 *
 * USAGE
 * 配置文件:
 * 修改 alipay.properties, 或使用 -Dcom.github.cuter44.alipay.alipay_properties 指定配置文件资源的位置
 * 默认的配置文件请参见 alipay.properties.sample
 * 编译:
 * ant build-all -s
 * 调用:
 * 可参见 src/com/github/cuter44/alipay/stub.java, 样例如下:
    public static String demoTradeCreateByBuyer()
    {
        try{
            // 创建工厂方法, 自动读取alipay.properties
            AlipayFactory factory = new AlipayFactory();
            Random rand = new Random();

            // 构造请求
            BaseRequest req = factory.newTradeCreateByBuyer()
                .setProperty("out_trade_no",        "test"+rand.nextLong())
                .setProperty("subject",             "交易功能测试")
                .setProperty("payment_type",        "1")
                .setProperty("logistics_type",      "EMS")
                .setProperty("logistics_fee",       "0.01")
                .setProperty("logistics_payment",   "SELLER_PAY")
                .setProperty("price",               "0.01")
                .setProperty("quantity",            "1");

            // 签名并输出构造的请求URL
            return(req.build().sign().toURL());

            // 也可调用 req.execute() 编程执行 HTTP 请求, 视实际情况使用.
            //req.build().sign().execute();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }
 *
 */
package com.github.cuter44.alipay;

