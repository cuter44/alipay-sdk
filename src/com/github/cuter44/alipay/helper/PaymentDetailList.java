package com.github.cuter44.alipay.helper;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * <b>Note that this implementation is not synchronized.</b> If multiple threads access an <code>RoyaltyList</code> instance concurrently,
 * and at least one of the threads modifies the list structurally, it must be synchronized externally.
 */
public class PaymentDetailList extends ArrayList<IPaymentItem>
{
    public static final int MAX_CAPACITY = 1000;

  // CONSTRUCT
    public PaymentDetailList()
    {
        return;
    }

    public PaymentDetailList(Collection<? extends IPaymentItem> c)
    {
        this();

        this.addAll(c);

        return;
    }

    public PaymentDetailList(IPaymentItem... paymentItems)
    {
        this();

        this.ensureCapacity(paymentItems.length);
        for (IPaymentItem i:paymentItems)
            this.add(i);

        return;
    }

  // ADD
    protected void sizeConstraint(int size)
    {
        if (size > MAX_CAPACITY)
            throw(new IndexOutOfBoundsException("Attempt to overload PaymentDetailList, operation aborted."));

        return;
    }


    /**
     * @exception IndexOutOfBoundsException on size%gt;1000, according to alipay spec.
     */
    @Override
    public boolean add(IPaymentItem e)
    {
        this.sizeConstraint(this.size()+1);

        return(
            super.add(e)
        );
    }

    /**
     * @exception IndexOutOfBoundsException on size%gt;1000, according to alipay spec.
     */
    @Override
    public void add(int index, IPaymentItem e)
    {
        this.sizeConstraint(this.size()+1);

        super.add(index, e);

        return;
    }

    /**
     * @exception IndexOutOfBoundsException on size%gt;1000, according to alipay spec.
     */
    @Override
    public boolean addAll(Collection<? extends IPaymentItem> c)
    {
        this.sizeConstraint(this.size()+c.size());

        return(
            super.addAll(c)
        );
    }

    /**
     * @exception IndexOutOfBoundsException on size%gt;1000, according to alipay spec.
     */
    @Override
    public boolean addAll(int index, Collection<? extends IPaymentItem> c)
    {
        this.sizeConstraint(this.size()+c.size());

        return(
            super.addAll(index, c)
        );
    }

  // OUTPUT
    /**
     * print out 流水号 1^收款方账号1^收款账号姓名1^付款金额 1^备注说明 1|流水号 2^收款方账号 2^收款账号姓名2^付款金额 2^备注说明 2
     */
    @Override
    public String toString()
    {
        boolean first = true;
        StringBuilder sb = new StringBuilder();

        for (IPaymentItem i:this)
        {
            if (first)
                first = false;
            else
                sb.append('|');

            sb.append(i.getSn())
                .append('^')
                .append(i.getAccount())
                .append('^')
                .append(i.getName())
                .append('^')
                .append(String.format("%.2f", i.getAmount()))
                .append('^')
                .append(i.getMemo());
        }

        return(sb.toString());
    }

    public String getDetailData()
    {
        return(this.toString());
    }

    public String getBatchNum()
    {
        return(String.format("%d", this.size()));
    }

    public String getBatchFee()
    {
        Double total = 0.00;

        for (IPaymentItem i:this)
            total += i.getAmount();

        return(String.format("%.2f", total));
    }
}
