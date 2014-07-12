package com.github.cuter44.alipay.constants;

import java.util.Map;
import java.util.HashMap;

public enum BankType
{
    ICBCBTB         ( 1,"中国工商银行（B2B）"),
    ABCBTB          ( 2,"中国农业银行（B2B）"),
    CCBBTB          ( 3,"中国建设银行（B2B）"),
    SPDBB2B         ( 4,"上海浦东发展银行（B2B）"),
    BOCBTB          ( 5,"中国银行（B2B）"),
    CMBBTB          ( 6,"招商银行（B2B）"),
    BOCB2C          ( 7,"中国银行"),
    ICBCB2C         ( 8,"中国工商银行"),
    CMB             ( 9,"招商银行"),
    CCB             (10,"中国建设银行"),
    ABC             (11,"中国农业银行"),
    SPDB            (12,"上海浦东发展银行"),
    CIB             (13,"兴业银行"),
    GDB             (14,"广发银行"),
    FDB             (15,"富滇银行"),
    CITIC           (16,"中信银行"),
    HZCBB2C         (17,"杭州银行"),
    SHBANK          (18,"上海银行"),
    NBBANK          (19,"宁波银行"),
    SPABANK         (20,"平安银行"),
    POSTGC          (21,"中国邮政储蓄银行"),
    abc1003         (22,"VISA"),
    abc1004         (23,"Master"),
    CMB_DEBIT       (24,"招商银行"),
    CCB_DEBIT       (25,"中国建设银行"),
    ICBC_DEBIT      (26,"中国工商银行"),
    COMM_DEBIT      (27,"交通银行"),
    GDB_DEBIT       (28,"广发银行"),
    BOC_DEBIT       (29,"中国银行"),
    CEB_DEBIT       (30,"中国光大银行"),
    SPDB_DEBIT      (31,"上海浦东发展银行"),
    PSBC_DEBIT      (32,"中国邮政储蓄银行"),
    BJBANK          (33,"北京银行"),
    SHRCB           (35,"上海农商银行"),
    WZCBB2C_DEBIT   (36,"温州银行"),
    COMM            (37,"交通银行"),
    CMBC            (38,"中国民生银行"),
    BJRCB           (39,"北京农村商业银行");


  // FIELDS
    private int code;
    private String friendly;

    /** 错误代码
     */
    public int getCode()
    {
        return(this.code);
    }

    /** 银行的显示名称
     */
    public String getFriendly()
    {
        return(this.friendly);
    }

  // INDEX/REFLECT
    private static Map<Integer, BankType> idxCode = new HashMap<Integer, BankType>();

    /** 从错误代码实例化
     */
    public static BankType forCode(int code)
    {
        return(idxCode.get(code));
    }

    /** 从错误名实例化
     */
    public static BankType forName(String name)
    {
        return(
            Enum.valueOf(BankType.class, name)
        );
    }

  // CONSTRUCT
    private BankType(int aCode, String aFriendly)
    {
        this.code = aCode;
        this.friendly = aFriendly;

        return;
    }

    static {
        for (BankType i:BankType.values())
            idxCode.put(i.code, i);
    }

}
