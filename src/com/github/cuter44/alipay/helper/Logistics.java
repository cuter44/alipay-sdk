package com.github.cuter44.alipay.helper;

import static java.lang.Math.signum;

import com.github.cuter44.alipay.constants.*;

/** Wrapper data structure for logistics info.
 * It is recommended to write your own factory class to produce this.
 */
public class Logistics
    implements ILogistics
{
  // FIELDS
    public LogisticsType logisticsType;
    public Double logisticsFee;
    public LogisticsPayment logisticsPayment;

    public void setLogisticsType(LogisticsType newLogisticsType)
    {
        this.logisticsType = newLogisticsType;

        return;
    }

    @Override
    public LogisticsType getLogisticsType()
    {
        return(this.logisticsType);
    }

    public void setLogisticsFee(Double newLogisticsFee)
    {
        if (signum(newLogisticsFee)<-0.5)
            throw(new IllegalArgumentException("Negative lgoistics fee not allowed:"+newLogisticsFee));

        this.logisticsFee = newLogisticsFee;

        return;
    }

    @Override
    public Double getLogisticsFee()
    {
        return(this.logisticsFee);
    }

    public void setLogisticsPayment(LogisticsPayment newLogisticsPayment)
    {
        this.logisticsPayment = newLogisticsPayment;

        return;
    }

    @Override
    public LogisticsPayment getLogisticsPayment()
    {
        return(this.logisticsPayment);
    }

  // CONSTRUCT
    public Logistics()
    {
        return;
    }

    public Logistics(LogisticsType logisticsType, Double logisticsFee, LogisticsPayment logisticsPayment)
    {
        this();

        this.setLogisticsType(logisticsType);
        this.setLogisticsFee(logisticsFee);
        this.setLogisticsPayment(logisticsPayment);

        return;
    }
}
