package com.github.cuter44.alipay.helper;

public interface IPaymentItem
{
    public static final int MAX_SN_LENGTH = 22;

    public abstract String getSn();
    public abstract String getAccount();
    public abstract String getName();
    public abstract Double getAmount();
    public abstract String getMemo();
}
