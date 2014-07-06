package com.github.cuter44.alipay.constants;

import java.util.Map;
import java.util.HashMap;

public enum AlipayErrorCode
{
  // ENUM
    ILLEGAL_PAYMENT_TYPE                        (- 1, "非法的支付类型"),
    ILLEGAL_FEE_PARAM                           (- 2, "金额参数传递混乱"),
    PRODUCT_NOT_EXIST                           (- 3, "产品类型不存在"),
    EXPARTNER_INFO_UNCORRECT                    (- 4, "传入商户接口信息不正确"),
    TRADE_DATA_MATCH_ERROR                      (- 5, "交易信息真实性有误"),
    LOGISTICS_CHOOSE_ERROR                      (- 6, "物流信息真实性错误"),
    ILLEGAL_REQUEST                             (- 7, "无效请求"),
    SELLER_NOT_EXIST                            (- 8, "交易卖家不存在"),
    SELLER_NOT_IN_SPECIFIED_SELLERS             (- 9, "卖家不在指定的商户限制卖家中"),
    SELF_TIMEOUT_NOT_SUPPORT                    (-10,"商户和支付宝未签署支付超时协议"),
    TRADE_BUYER_NOT_MATCH                       (-11,"输入的买家与交易买家不匹配"),
    TRADE_SELLER_NOT_MATCH                      (-12,"输入的卖家与交易卖家不匹配"),
    TOTAL_FEE_LESSEQUAL_ZERO                    (-13,"交易总额小于等于 0"),
    MAX_MONEY_COMPANY_TO_INDIVIDUAL             (-14,"公司账户给个人账户付款，单笔金额不得大于 5 万元"),
    EXTERFACE_INVOKE_CONTEXT_EXPIRED            (-15,"接口调用上下文过期"),
    ILLEGAL_LOGISTICS_FORMAT                    (-16,"物流信息定义错误"),
    ILLEGAL_SIGN                                (-17,"签名不正确"),
    ILLEGAL_DYN_MD5_KEY                         (-18,"动态密钥信息错误"),
    ILLEGAL_ENCRYPT                             (-19,"加密不正确"),
    ILLEGAL_ARGUMENT                            (-20,"参数不正确"),
    ILLEGAL_SERVICE                             (-21,"参数不正确"),
    ILLEGAL_USER                                (-22,"用户 ID 不正确"),
    ILLEGAL_PARTNER                             (-23,"合作伙伴 ID 不正确"),
    ILLEGAL_EXTERFACE                           (-24,"接口配置不正确"),
    ILLEGAL_PARTNER_EXTERFACE                   (-25,"合作伙伴接口信息不正确"),
    ILLEGAL_SECURITY_PROFILE                    (-26,"未找到匹配的密钥配置"),
    ILLEGAL_AGENT                               (-27,"代理 ID 不正确"),
    ILLEGAL_SIGN_TYPE                           (-28,"签名类型不正确"),
    HAS_NO_PRIVILEGE                            (-29,"无权访问"),
    ILLEGAL_REQUEST_REFERER                     (-30,"防钓鱼检查不支持该请求来源"),
    ILLEGAL_ANTI_PHISHING_KEY                   (-31,"防钓鱼检查非法时间戳参数"),
    ANTI_PHISHING_KEY_TIMEOUT                   (-32,"防钓鱼检查时间戳超时"),
    ILLEGAL_EXTER_INVOKE_IP                     (-33,"防钓鱼检查非法外部调用 IP"),
    SYSTEM_EXCEPTION                            (-34,"系统异常"),
    COMMON_PARAMS_ILLEGAL                       (-35,"通用参数中少了如 service、partner 等必填参数"),
    SIGN_ILLEGAL                                (-36,"签名不正确"),
    SERVICE_NOT_EXIST                           (-37,"不存在对应的 service"),
    REQ_DATA_ILLEGAL                            (-38,"req_data 没有按照<req>…<req>格式要求填写"),
    PARTNER_ILLEGAL                             (-39,"合作伙伴没有开通接口访问权限"),
    SEC_ID_NOT_EXIST                            (-40,"不存在对应的 sec_id"),
    BIZ_PARAMS_ILLEGAL                          (-41,"缺少了非空的业务参数"),
    BIZ_PARAMS_TOO_LONG                         (-42,"业务参数超出长度限制"),
    SELLER_ACCOUNT_NOT_MATCH                    (-43,"卖家账号不匹配"),
    ILLEGAL_INTEGER_FORMAT                      (-44,"Int 类型格式不合法"),
    ROYALTY_PAY_EMAIL_NOT_EXIST                 (-45,"抱歉，分润付款账户经验证不存在，请联系您的商户。"),
    ROYALTY_LENGTH_ERROR                        (-46,"抱歉，分润信息过长，不能超过 1000 个字符，请检查后重新集成。"),
    ROYALTY_RECEIVER_NOT_IN_SPECIFIED_ACCOUNTS  (-47,"抱歉，分润账号不是指定的分润账户，请确保该分润账户已签署分润协议。"),
    ROYALTY_RECEIVE_EMAIL_NOT_EXIST             (-48,"抱歉，分润账户经验证不存在，请联系您的商户。"),
    ROYALTY_RECEIVE_EMAIL_NOT_CERTIFY           (-49,"抱歉，分润账户经验证未通过人行验证，请联系您的商户。"),
    PAY_CHECK_FAIL                              (-50,"抱歉，付款失败! 该笔交易可能存在风险，建议您付款前<a href=\"http://bbs.taobao.com/catalog/thread/154504-5782260.htm\" target=\"_blank\">先查看防骗案例</a>。"),
    BUYER_EMAIL_ID_MUST_NULL                    (-51,"抱歉，该笔交易的买家账户必须为空，请联系您的商户。"),
    TRADE_QUANTITY_NOT_MATCH                    (-52,"抱歉，该商品的购买数量与原先的不一致，请重新创建交易付款。"),
    TRADE_PRICE_NOT_MATCH                       (-53,"抱歉，该商品的交易单价与原先的不一致，请重新创建交易付款。"),
    TRADE_TOTALFEE_NOT_MATCH                    (-54,"抱歉，该商品的交易金额与原先的不一致，请重新创建交易付款。"),
    ROYALTY_TYPE_ERROR                          (-55,"抱歉，接口传递的分润类型错误，请检查后重新集成。"),
    ILLEGAL_EXTRA_COMMON_PARAM                  (-56,"抱歉，接口通用回传参数格式不正确，请联系您的商户。"),
    BUYER_SELLER_EQUAL                          (-57,"抱歉，买家和卖家不能是同一个账户。"),
    BUYER_NOT_EXIST                             (-58,"抱歉，买家账户经验证不存在。"),
    ROYALTY_SELLER_NOT_CERTIFY                  (-59,"抱歉，卖家尚未通过认证，不能进行收款，请联系您的商户。"),
    ROYALTY_SELLER_ENABLE_STATUS_FORBID         (-60,"抱歉，卖家暂时无法进行收款操作，请联系您的商户。"),
    TRADE_NOT_ALLOWED_PAY                       (-61,"抱歉，您不能进行本次支付，请查看该交易是否已超时或已被关闭等。"),
    NEED_CTU_CHECK_PARAMETER_ERROR              (-62,"抱歉，您传递的商户可信任参数权限参数错误。"),
    DIS_NOT_SIGN_PROTOCOL                       (-63,"抱歉，您的分销商没有与支付宝签约，请联系您的商户。"),
    EVOUCHER_ID_NOT_EXIST                       (-64,"抱歉，商户传递的消费券交易公共业务扩展参数中凭证号不存在，请联系您的商家。"),
    NEED_CTU_CHECK_NOT_ALLOWED                  (-65,"抱歉，商户没有可信任参数校验的权限。"),
    NOT_SUPPORT_GATEWAY                         (-66,"抱歉，商户网关配置出错，请联系您的商户。"),
    TAOBAO_ANTI_PHISHING_CHECK_FAIL             (-67,"抱歉，无法付款! 该笔交易可能存在风险，如果您确定本次交易没有问题，请 1 个小时后再付款。"),
    BODY_HAS_FORBIDDENWORD                      (-68,"抱歉，无法付款! 请联系商户修改商品描述，再重新购买。"),
    SUBJECT_HAS_FORBIDDENWORD                   (-69,"抱歉，无法付款! 请联系商户修改商品名称，再重新购买。"),
    FAIL_CREATE_CASHIER_PAY_ORDER               (-70,"抱歉，系统异常，无法创建本次收银台支付订单，请稍后再试。"),
    ILLEGAL_OUTTIME_ARGUMENT                    (-71,"抱歉，自定义超时时间设置错误，请联系您的商户。"),
    ILLEGAL_ENCODING                            (-72,"不支持该编码类型"),
    PARAMTER_IS_NULL                            (-73,"参数值为空"),
    ILLEGAL_LENGTH                              (-74,"参数值长度不合法"),
    NAVIGATION_INCOME_OF_ROYALTY_ACCOUNT        (-75,"分润账户入不敷出"),
    DIRECTIONAL_PAY_FORBIDDEN                   (-76,"付款受限，请确保收款方有权进行收款。"),
    TRADE_NOT_FOUND                             (-77,"根据交易号无法找到交易。"),
    TRADE_GOOD_INFO_NOT_FOUND                   (-77,"根据交易号无法找到交易详情。"),
    ILLEGAL_MONEY_FORMAT                        (-78,"金额格式不合法"),
    ILLEGAL_CLIENT_IP                           (-79,"客户端 IP 地址无权访问服务"),
    SELLER_ENABLE_STATUS_FORBID                 (-80,"卖家状态不正常。"),
    DEFAULT_BANK_INVALID                        (-81,"您传递的默认网银参数不在规定的范围内。"),
    PRODUCT_NOT_ALLOWED                         (-82,"您未开通此产品，暂时无法使用本服务。"),
    ILLEGAL_DATA_FORMAT                         (-83,"日期格式错误"),
    SUBJECT_MUST_NOT_BE_NULL                    (-84,"商品名不能为空。"),
    ILLEGAL_NUMBER_FORMAT                       (-85,"数字格式不合法"),
    DIRECT_PAY_WITHOUT_CERT_CLOSE               (-86,"未开通非证书余额支付，无法完成支付。"),
    ILLEGAL_FILE_FORMAT                         (-87,"文件格式不正确"),
    ILLEGAL_DIGEST                              (-88,"文件摘要不正确"),
    EBANK_CERDIT_GW_RULE_NOT_OPEN               (-89,"信用卡未签约 （签约到期） 或者接口参数未指定开通信用卡支付。"),
    ILLEGAL_DIGEST_TYPE                         (-90,"摘要类型不正确"),
    REGEXP_MATCH_FAIL                           (-91,"正则表达式匹配失败"),
    ILLEGAL_CHARSET                             (-92,"字符集不合法");

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