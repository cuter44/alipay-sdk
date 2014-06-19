package com.github.cuter44.util.crypto;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.Arrays;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

//import com.github.cuter44.util.crypto.*;

public class RSAGenKeyCLI
{
    public static void main(String[] args)
    {
        try
        {
            KeyPair kp = CryptoUtil.generateRSAKey();
            byte[] ba;

            ba = ((RSAPrivateKey)kp.getPrivate()).getPrivateExponent().toByteArray();
            ba = (ba[0]!=0 ? ba : Arrays.copyOfRange(ba, 1, ba.length));
            System.out.println("d=");
            System.out.println(CryptoUtil.byteToHex(ba));

            ba = ((RSAPrivateKey)kp.getPrivate()).getModulus().toByteArray();
            ba = (ba[0]!=0 ? ba : Arrays.copyOfRange(ba, 1, ba.length));
            System.out.println("m=");
            System.out.println(CryptoUtil.byteToHex(ba));

            ba = ((RSAPublicKey)kp.getPublic()).getPublicExponent().toByteArray();
            ba = (ba[0]!=0 ? ba : Arrays.copyOfRange(ba, 1, ba.length));
            System.out.println("e=");
            System.out.println(CryptoUtil.byteToHex(ba));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return;
        }
    }
}
