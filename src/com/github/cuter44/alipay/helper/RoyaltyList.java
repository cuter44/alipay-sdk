package com.github.cuter44.alipay.helper;

import static java.lang.Math.signum;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <b>Note that this implementation is not synchronized.</b> If multiple threads access an <code>RoyaltyList</code> instance concurrently,
 * and at least one of the threads modifies the list structurally, it must be synchronized externally.
 */
public class RoyaltyList extends ArrayList<IRoyaltyItem>
{
    public static final int MAX_CAPACITY = 10;

  // CONSTRUCT
    public RoyaltyList()
    {
        return;
    }

    public RoyaltyList(Collection<? extends IRoyaltyItem> c)
    {
        this();

        this.addAll(c);

        return;
    }

    public RoyaltyList(IRoyaltyItem... royaltyItems)
    {
        this();

        this.ensureCapacity(royaltyItems.length);
        for (IRoyaltyItem i:royaltyItems)
            this.add(i);

        return;
    }

  // ADD
    protected void sizeConstraint(int size)
    {
        if (size > MAX_CAPACITY)
            throw(new IndexOutOfBoundsException("Attempt to overload RoyaltyList, operation aborted."));

        return;
    }


    /**
     * @exception IndexOutOfBoundsException on size%gt;10, according to alipay spec.
     */
    @Override
    public boolean add(IRoyaltyItem e)
    {
        this.sizeConstraint(this.size()+1);

        return(
            super.add(e)
        );
    }

    /**
     * @exception IndexOutOfBoundsException on size%gt;10, according to alipay spec.
     */
    @Override
    public void add(int index, IRoyaltyItem e)
    {
        this.sizeConstraint(this.size()+1);

        super.add(index, e);

        return;
    }

    /**
     * @exception IndexOutOfBoundsException on size%gt;10, according to alipay spec.
     */
    @Override
    public boolean addAll(Collection<? extends IRoyaltyItem> c)
    {
        this.sizeConstraint(this.size()+c.size());

        return(
            super.addAll(c)
        );
    }

    /**
     * @exception IndexOutOfBoundsException on size%gt;10, according to alipay spec.
     */
    @Override
    public boolean addAll(int index, Collection<? extends IRoyaltyItem> c)
    {
        this.sizeConstraint(this.size()+c.size());

        return(
            super.addAll(index, c)
        );
    }

  // VALIDATE
    /** validate if this RoyaltyList can be executed.
     * <br />
     * Note that this check is not always accurate, since alipay did not state which rounding strategy applied.
     * Another problem is, due to the precision lost of floating-point, potential crash may happened.
     * To avoid this, please don't attempt to touch the critical value, or skip this check if you are confident.
     *
     * @param income amount that the buyer actually payed.
     * @param fee fee charged by alipay, in ratio. Ask your customer service for accurate value.
     * @return this, if passed.
     * @exception IllegalStateException if there is a obvious leak.
     */
    public RoyaltyList validate(double income, double fee)
        throws IllegalStateException
    {
        HashMap<String, Double> map = new HashMap<String, Double>(this.size());

        map.put("$", income*(1.00-fee));

        for (IRoyaltyItem i:this)
        {
            String out = i.getPayAccount();
            out = out!=null?out:"$";

            String in = i.getAccount();
            Double amount = i.getAmount();

            if (map.get(out)==null)
                map.put(out, 0.00);
            if (map.get(in)==null)
                map.put(in, 0.00);

            map.put(
                out,
                map.get(out)-amount
            );
            map.put(
                in,
                map.get(in)+amount
            );
        }

        for (Double v:map.values())
            if (signum(v) < -0.5)
                throw(new IllegalStateException("Roayalty validateion failed:"+this.toString()));

        return(this);
    }

    /** invoke <code>this.validate(income, 0.0012)</code>(which is the max value of DirectPay)
     */
    public RoyaltyList validate(double income)
        throws IllegalStateException
    {
        return(
            this.validate(income, 0.0012)
        );
    }

  // OUTPUT
    /**
     * print out (付款帐号1^)?收款方账号1^付款金额 1^备注说明 1|(付款帐号2^)?收款方账号2^付款金额 2^备注说明 2
     */
    @Override
    public String toString()
    {
        boolean first = true;
        StringBuilder sb = new StringBuilder();

        for (IRoyaltyItem i:this)
        {
            if (first)
                first = false;
            else
                sb.append('|');

            if (i.getPayAccount()!=null)
                sb.append(i.getPayAccount())
                    .append('^');

            sb.append(i.getAccount())
                .append('^')
                .append(String.format("%.2f", i.getAmount()))
                .append('^')
                .append(i.getMemo());
        }

        return(sb.toString());
    }

    public String getRoyaltyParameters()
    {
        return(this.toString());
    }
}
