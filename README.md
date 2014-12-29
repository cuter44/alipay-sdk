# 支付宝服务器端SDK for Java / alipay seller-side sdk for Java  

## Release

...can be found in https://github.com/cuter44/wxpay-sdk/releases

## License & Acknokledgement/授权 & 鸣谢

see LICENSE.md

## Disclaimer/免责声明

Though I made effort to not stealing your money or any secret about your alipay account, or leak them to third-party. But I am still not responsable for any direct or indriect lost for your using of these (or part of) source code. It is your responsibility to check any risk before applying them.  

作者不对使用代码所带来的直接或间接损失负责. 由于源代码是公开的, 检查和确保代码的安全性是属于使用者的职责. 请时刻清醒地意识这一点.  

## Tutorial/教程

Frist of all, ensure you have `jdk` and `apache-ant`.  
`Oracle-HotSpot 1.7` and `apache-ant-1.9.2` are applied in development environment, all goes well.  

Besides, other runtime libraries are required, which are included in `lib` directory.

1. Generate jar file

run `ant jar`
then there will be the `alipay-sdk-x.y.z.jar` in project root, where xyz is version code.

2. Config

add these to your classpath:
* the jar-file mentioned above
* jar-files in `lib/`, which are depended
* `alipay.properties`, whose schema are provided in `doc/alipay.properties.sample`

3. Invoke

Demos are provided in `src/com/github/cuter44/test.java`. Which can be safely removed if you consider it insecure.

Thanks to GFW, it is such a tough thing to upload binary release to Github. Sorry for that.  

首先, 你要有一个 `jdk` 和 `apache-ant`. 开发环境使用 Oracle家的jdk7 和 ant 1.9.2, 请依个人喜好酌量添加.  
依赖的函数库被放置于`lib`文件夹中.

1. 编译: 执行 `ant jar`, 然后你就有个jar了....  
2. 配置: 将那个jar以及 `lib/` 里的jar加到你的classpath, 然后还需要一个配置文件, 怎么写参见 `doc/alipay.properties.sample`  
3. 调用: 参见 `src/com/github/cuter44/test.java`. 如果需要安全性, 请排除这个类.

## Tutorial/说明

### Configurate/配置  

`doc/alipay.properties.sample` provides a sample config file. Comments in that file instruct what you should do with that.

`doc/alipay.properties.sample` 是一份样例操作文件. 关于如何使用该配置文件请参照其中的注释.

### Factory/工厂方法

This SDK provides factory object to easily create requests. This factory reads `alipay.properties` mentioned above and use them as default config, code in next section demostrate how it works.  
This mechanism works profectly if you are using a unique alipay enterprise account. Otherwise you may not use a config file but create and config factories/requests programtically (using `new`).

SDK 中提供了一个工厂方法用于快速地创建请求. 这个工厂方法读取 `alipay.properties` 并将之传递给请求作为默认参数(可以在运行时被覆盖)  
工厂方法适用于单实例的时候. 如果你的系统需要集成多个商家帐号, 则你需要编程创建并自行维护工厂对象或单个请求(使用其构造器而不是 `getDefaultInstance()`)

### Initiative request/主动请求

Where initiative means your app generate links and provides to your customer (BUY ME NOW!), or directly send reqs to alibaba's server to achieve certain action.

```Java
    public static String demoTradeCreateByBuyer()
    {
        Random rand = new Random();

        try{
            AlipayFactory factory = AlipayFactory.getDefaultInstance();

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
```

All initiiative reqs are handled alike, you should first obtain an AlipayFactory singleton(to load alipay.properties, also you can "new" one then config programmatically), then call the factory method you need, Pass additional parameters.  
And the rest are in one breath. You should sequentially call `.build().sign().toURL()`(if you need to put it on html) or `build().sign().execute()`(...if it is executed on server-side, according to alipay's specification).

If executed, the `execute()` method returns a response in correponding class (need manual casting), which has the response parsed in key-value pairs. As of the to-url-requests, a UnsupportedOperationException will be thrown.

主动请求是指支付宝提供的接口, 包括放在页面上给客户点击的(买买买买买!)和直接在服务器端执行的.

```
(代码见上)
```

主动请求的操作方法基本类同, 首先要构建一个AlipayFactory(加载配置文件, 或者编程地配置它); 然后使用工厂方法构建你想要的请求; 往请求里填参数...  
然后就简单了, `.build().sign().toURL()` 用于生成链接并嵌入到页面, 或者 `.build().sign().execute()` 直接执行(视支付宝文档而定).

可执行的请求会在执行后返回一个响应, 里面包含解析成键值对的信息; 不应该在服务器端执行的请求会被以 `UnsupportedOperationException` 吐槽.


### Notify/接受回调

Some interfaces accept a `notify_url`, which means alipay will callback while trade status varying.  
This sdk provided a default servlet and a event-publisher to help you accomplish it:

```
...
```

Well, I am not pasting code here, go and find it in `src/.../webinterface/AlipayNotifyGatewayServlet.java` and `src/.../AlipayNotifyPublisher.java`. If you are too lazy to read them, just have a look at `doc/web.xml`, and come up your experience about standard publisher-listener model.

有些接口接受名为 `notify_url` 的参数, 配置的 URL 会在诸如交易状态变化的时候被回调.
本 sdk 提供了一个 servlet 和一个 publisher-listener 结构来完成这个工作:

```
(不贴代码了)
```

你这么聪明肯定懂的, 参考这两个就好了:
`src/.../webinterface/AlipayNotifyGatewayServlet.java`   
`src/.../AlipayNotifyPublisher.java`  

## Covered API/已封装的接口

Listed as below, indluding related API.

```
// BETA
即时到账交易接口-create_direct_pay_by_user(20140617)
即时到账批量退款有密接口-refund_fastpay_by_platform_pwd(20130621)
手机网页即时到账接口-手机网页即时到账接口(20131121)
批量付款到支付宝账户有密接口-batch_trans_notify(20140617)
标准双接口-trade_create_by_buyer(20140122)
确认发货接口-send_goods_confirm_by_platform(20130530)
纯担保交易接口-create_partner_trade_by_buyer(20140122)
纯网关接口-create_direct_pay_by_user(20140710)
```

## Detailed docs/详细文档

For questions with alipay, ask your search engineer.  
For javadoc, run `ant javadoc`(recommended), or visit [http://cuter44.github.io/alipay-sdk/javadoc/](http://cuter44.github.io/alipay-sdk/javadoc/).  
For bugs/issues, thanks for visiting [https://github.com/cuter44/alipay-sdk/issues](https://github.com/cuter44/alipay-sdk/issues)  
