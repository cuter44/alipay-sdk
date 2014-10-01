package com.github.cuter44.alipay.helper;

import com.github.cuter44.alipay.constants.*;

public class AlipayAccount
    implements IAlipayAccount
{
  // FIELDS
    public String id;
    public String email;
    public String accountName;

    public void setId(String newId)
    {
        if (newId!=null && !newId.matches("2088\\d{12}"))
            throw(new IllegalArgumentException("Illegal Alipay id:"+newId));

        this.id = newId;

        return;
    }

    @Override
    public String getId()
    {
        return(this.id);
    }

    public void setEmail(String newEmail)
    {
        this.email = newEmail;

        return;
    }

    @Override
    public String getEmail()
    {
        return(this.email);
    }

    public void setAccountName(String newAccountName)
    {
        this.accountName = newAccountName;

        return;
    }

    @Override
    public String getAccountName()
    {
        return(this.accountName);
    }

  // CONSTRUCT
    public AlipayAccount()
    {
        return;
    }

    public AlipayAccount(String id, String email, String accountName)
    {
        this();

        this.setId(id);
        this.setEmail(email);
        this.setAccountName(accountName);

        return;
    }

    public static AlipayAccount withId(String id)
    {
        AlipayAccount instance = new AlipayAccount();

        instance.setId(id);

        return(instance);
    }

    public static AlipayAccount withEmail(String email)
    {
        AlipayAccount instance = new AlipayAccount();

        instance.setEmail(email);

        return(instance);
    }

    public static AlipayAccount withAccountName(String accountName)
    {
        AlipayAccount instance = new AlipayAccount();

        instance.setAccountName(accountName);

        return(instance);
    }
}
