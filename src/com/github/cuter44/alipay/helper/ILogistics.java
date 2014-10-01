package com.github.cuter44.alipay.helper;

import com.github.cuter44.alipay.constants.*;

public interface ILogistics
{
    public abstract LogisticsType getLogisticsType();
    public abstract Double getLogisticsFee();
    public abstract LogisticsPayment getLogisticsPayment();
}
