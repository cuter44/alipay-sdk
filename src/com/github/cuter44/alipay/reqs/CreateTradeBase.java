package com.github.cuter44.alipay.reqs;

import static java.lang.Math.signum;
import java.util.Properties;

import com.github.cuter44.alipay.constants.*;
import com.github.cuter44.alipay.helper.*;

public abstract class CreateTradeBase extends WebRequestBase
{
  // CONSTRUCT
    public CreateTradeBase(Properties aConf)
    {
        super(aConf);
    }

  // PROPERTY
    public CreateTradeBase setOutTradeNo(String outTradeNo)
    {
        this.setProperty("out_trade_no", outTradeNo);

        return(this);
    }

    public CreateTradeBase setSubject(String subject)
    {
        this.setProperty("subject", subject);

        return(this);
    }

    public CreateTradeBase setPrice(double price)
    {
        if (signum(price)<0.5)
            throw(new IllegalArgumentException("Illegal price:"+price));

        this.setProperty("price", String.format("%.2f", price));

        return(this);
    }

    public CreateTradeBase setQuantity(int quantity)
    {
        if (quantity<=0)
            throw(new IllegalArgumentException("Illegal quantity:"+quantity));

        this.setProperty("quantity", String.format("%d",quantity));

        return(this);
    }

    public CreateTradeBase setDiscount(double discount)
    {
        this.setProperty("discount", String.format("%.2f", discount));

        return(this);
    }

    public CreateTradeBase setTotalFee(double totalFee)
    {
        if (signum(totalFee)<0.5)
            throw(new IllegalArgumentException("Illegal totalFee:"+totalFee));

        this.setProperty("total_fee", String.format("%.2f", totalFee));

        return(this);
    }

    /**
     * Set <code>buyer_id</code>, <code>buyer_email</code>, <code>buyer_account_name</code>, if provided.
     * Setting this field can avoid paying with arbitrary account, which may annoy your customer.
     */
    public CreateTradeBase setBuyer(IAlipayAccount account)
    {
        if (account.getId()!=null)
            this.setProperty("buyer_id", account.getId());

        if (account.getEmail()!=null)
            this.setProperty("buyer_email", account.getEmail());

        if (account.getAccountName()!=null)
            this.setProperty("buyer_account_name", account.getAccountName());

        return(this);
    }

    /**
     * Set <code>seller_id</code>, <code>seller_email</code>, <code>seller_account_name</code>, if provided.
     */
    public CreateTradeBase setSeller(IAlipayAccount account)
    {
        if (account.getId()!=null)
            this.setProperty("seller_id", account.getId());

        if (account.getEmail()!=null)
            this.setProperty("seller_email", account.getEmail());

        if (account.getAccountName()!=null)
            this.setProperty("seller_account_name", account.getAccountName());

        return(this);
    }

    /**
     * Set <code>logistics_(type|fee|payment)(|_1|_2)</code>, redundant argument will be ignored.
     */
    public CreateTradeBase setLogistics(ILogistics... logistics)
    {
        if (logistics.length>0)
        {
            ILogistics l = logistics[0];
            this.setProperty("logistics_type", l.getLogisticsType().name());
            this.setProperty("logistics_fee", String.format("%.2f", l.getLogisticsFee()));
            this.setProperty("logistics_payment", l.getLogisticsPayment().name());
        }

        if (logistics.length>1)
        {
            ILogistics l = logistics[1];
            this.setProperty("logistics_type_1", l.getLogisticsType().name());
            this.setProperty("logistics_fee_1", String.format("%.2f", l.getLogisticsFee()));
            this.setProperty("logistics_payment_1", l.getLogisticsPayment().name());
        }
        if (logistics.length>2)
        {
            ILogistics l = logistics[2];
            this.setProperty("logistics_type_2", l.getLogisticsType().name());
            this.setProperty("logistics_fee_2", String.format("%.2f", l.getLogisticsFee()));
            this.setProperty("logistics_payment_2", l.getLogisticsPayment().name());
        }

        return(this);
    }
}
