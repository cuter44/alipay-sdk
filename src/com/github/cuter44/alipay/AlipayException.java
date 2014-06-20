package com.github.cuter44.alipay;

import com.github.cuter44.alipay.constants.AlipayErrorCode;

public class AlipayException extends RuntimeException
{
  // FIELDS
    private AlipayErrorCode errorCode;

  // CONSTRUCT
    public AlipayException()
    {
        return;
    }

    public AlipayException(Throwable cause)
    {
        this.initCause(cause);

        return;
    }

    public AlipayException(AlipayErrorCode aErrorCode, Throwable cause)
    {
        this.errorCode = aErrorCode;
        this.initCause(cause);

        return;
    }

    public AlipayException(AlipayErrorCode aErrorCode)
    {
        this(aErrorCode, null);
    }

    public AlipayException(String aErrorName)
    {
        this(
            AlipayErrorCode.forName(aErrorName),
            null
        );
    }

    public AlipayException(String aErrorName, Throwable cause)
    {
        this(
            AlipayErrorCode.forName(aErrorName),
            cause
        );
    }

    public AlipayException(int aErrorCode)
    {
        this(
            AlipayErrorCode.forCode(aErrorCode),
            null
        );
    }

    public AlipayException(int aErrorCode, Throwable cause)
    {
        this(
            AlipayErrorCode.forCode(aErrorCode),
            cause
        );
    }

  // EXCEPTION
    @Override
    public String getMessage()
    {
        return(this.errorCode!=null ? this.errorCode.getMsg() : null);
    }
}
