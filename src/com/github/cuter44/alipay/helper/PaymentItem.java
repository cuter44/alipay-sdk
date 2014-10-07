package com.github.cuter44.alipay.helper;

import static java.lang.Math.signum;

public class PaymentItem
    implements IPaymentItem, IRoyaltyItem
{
  // FIELDS
    public String sn;
    public String payAccount;
    public String account;
    public String name;
    public Double amount;
    public String memo;

    public void setSn(String newSn)
    {
        if (newSn.contains("|") || newSn.contains("^"))
            throw(new IllegalArgumentException("Reeserved char found:"+newSn));
        if (newSn.length()>MAX_SN_LENGTH)
            throw(new IllegalArgumentException("Exceed MAX_SN_LENGTH:"+newSn));

        this.sn = newSn;
    }

    @Override
    public String getSn()
    {
        return(this.sn);
    }

    public void setPayAccount(String newAccount)
    {
        if (newAccount.contains("|") || newAccount.contains("^"))
            throw(new IllegalArgumentException("Reeserved char found:"+newAccount));

        this.payAccount = newAccount;
    }

    @Override
    public String getPayAccount()
    {
        return(this.payAccount);
    }

    public void setAccount(String newAccount)
    {
        if (newAccount.contains("|") || newAccount.contains("^"))
            throw(new IllegalArgumentException("Reeserved char found:"+newAccount));

        this.account = newAccount;
    }

    @Override
    public String getAccount()
    {
        return(this.account);
    }

    public void setName(String newName)
    {
        if (newName.contains("|") || newName.contains("^"))
            throw(new IllegalArgumentException("Reeserved char found:"+newName));

        this.name = newName;
    }

    @Override
    public String getName()
    {
        return(this.name);
    }

    public void setAmount(Double newAmount)
    {
        if (signum(newAmount)<=0.5)
            throw(new IllegalArgumentException("Positive payment required:"+newAmount));

        this.amount = newAmount;
    }

    @Override
    public Double getAmount()
    {
        return(this.amount);
    }

    public void setMemo(String newMemo)
    {
        if (newMemo.contains("|") || newMemo.contains("^"))
            throw(new IllegalArgumentException("Reeserved char found:"+newMemo));

        this.memo = newMemo;
    }

    @Override
    public String getMemo()
    {
        return(this.memo);
    }

  // CONSTRUCT
    /** constructor for refund
     */
    public PaymentItem(String sn, String account, String name, Double amount, String memo)
    {
        this.setSn(sn);
        this.setAccount(account);
        this.setName(name);
        this.setAmount(amount);
        this.setMemo(memo);

        return;
    }

    /** constructor for royalty
     */
    public PaymentItem(String account, Double amount, String memo)
    {
        this.setAccount(account);
        this.setAmount(amount);
        this.setMemo(memo);

        return;
    }

    /** constructor for royalty
     */
    public PaymentItem(String payAccount, String account, Double amount, String memo)
    {
        this(account, amount, memo);

        this.setPayAccount(payAccount);

        return;
    }
}
