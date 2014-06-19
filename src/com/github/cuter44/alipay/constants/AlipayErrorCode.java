package com.github.cuter44.alipay.constants;

import java.util.Map;
import java.util.HashMap;

public enum AlipayErrorCode
{
  // ENUM
    ILLEGAL_PAYMENT_TYPE            (-1, "非法的支付类型"),
    ILLEGAL_FEE_PARAM               (-2, "金额参数传递混乱"),
    PRODUCT_NOT_EXIST               (-3, "产品类型不存在"),
    EXPARTNER_INFO_UNCORRECT        (-4, "传入商户接口信息不正确"),
    TRADE_DATA_MATCH_ERROR          (-5, "交易信息真实性有误"),
    LOGISTICS_CHOOSE_ERROR          (-6, "物流信息真实性错误"),
    ILLEGAL_REQUEST                 (-7, "无效请求"),
    SELLER_NOT_EXIST                (-8, "交易卖家不存在"),
    SELLER_NOT_IN_SPECIFIED_SELLERS (-9, "卖家不在指定的商户限制卖家中"),
    SELF_TIMEOUT_NOT_SUPPORT        (-10,"商户和支付宝未签署支付超时协议"),
    TRADE_BUYER_NOT_MATCH           (-11,"输入的买家与交易买家不匹配"),
    TRADE_SELLER_NOT_MATCH          (-12,"输入的卖家与交易卖家不匹配"),
    TOTAL_FEE_LESSEQUAL_ZERO        (-13,"交易总额小于等于 0"),
    MAX_MONEY_COMPANY_TO_INDIVIDUAL (-14,"公司账户给个人账户付款，单笔金额不得大于 5 万元"),
    EXTERFACE_INVOKE_CONTEXT_EXPIRED(-15,"接口调用上下文过期"),
    ILLEGAL_LOGISTICS_FORMAT        (-16,"物流信息定义错误"),
    ILLEGAL_SIGN                    (-17,"签名不正确"),
    ILLEGAL_DYN_MD5_KEY             (-18,"动态密钥信息错误"),
    ILLEGAL_ENCRYPT                 (-19,"加密不正确"),
    ILLEGAL_ARGUMENT                (-20,"参数不正确"),
    ILLEGAL_SERVICE                 (-21,"参数不正确"),
    ILLEGAL_USER                    (-22,"用户 ID 不正确"),
    ILLEGAL_PARTNER                 (-23,"合作伙伴 ID 不正确"),
    ILLEGAL_EXTERFACE               (-24,"接口配置不正确"),
    ILLEGAL_PARTNER_EXTERFACE       (-25,"合作伙伴接口信息不正确"),
    ILLEGAL_SECURITY_PROFILE        (-26,"未找到匹配的密钥配置"),
    ILLEGAL_AGENT                   (-27,"代理 ID 不正确"),
    ILLEGAL_SIGN_TYPE               (-28,"签名类型不正确"),
    HAS_NO_PRIVILEGE                (-29,"无权访问"),
    ILLEGAL_REQUEST_REFERER         (-30,"防钓鱼检查不支持该请求来源"),
    ILLEGAL_ANTI_PHISHING_KEY       (-31,"防钓鱼检查非法时间戳参数"),
    ANTI_PHISHING_KEY_TIMEOUT       (-32,"防钓鱼检查时间戳超时"),
    ILLEGAL_EXTER_INVOKE_IP         (-33,"防钓鱼检查非法外部调用 IP");

  // FIELDS
    private int code;
    private String msg;

    /** 错误代码
     */
    public int getCode()
    {
        return(this.code);
    }

    /** 错误名, 遵循支付宝文档定义
     */
    public String getMsg()
    {
        return(this.msg);
    }

  // INDEX/REFLECT
    private static Map<Integer, AlipayErrorCode> idxCode = new HashMap<Integer, AlipayErrorCode>();

    /** 从错误代码实例化
     */
    public static AlipayErrorCode forCode(int code)
    {
        return(idxCode.get(code));
    }

    /** 从错误名实例化
     */
    public static AlipayErrorCode forName(String name)
    {
        return(
            Enum.valueOf(AlipayErrorCode.class, name)
        );
    }

  // CONSTRUCT
    private AlipayErrorCode(int aCode, String aMsg)
    {
        this.code = aCode;
        this.msg = aMsg;

        return;
    }

    static {
        for (AlipayErrorCode i:AlipayErrorCode.values())
            idxCode.put(i.code, i);
    }

}