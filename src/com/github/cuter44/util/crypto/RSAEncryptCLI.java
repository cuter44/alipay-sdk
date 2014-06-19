package com.github.cuter44.util.crypto;

import java.math.BigInteger;
import java.util.Scanner;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

public class RSAEncryptCLI
{
    public static void main(String[] args)
    {
        try
        {
            String s;
            String pt;
            BigInteger m = null;
            BigInteger e = null;
            Scanner scn = new Scanner(System.in);

            KeyFactory kf = KeyFactory.getInstance("RSA");

            while (true)
            {
                System.out.println("plaintext?");
                pt = scn.nextLine();
                System.out.println("m? in hex, [Enter] to use latest");
                s = scn.nextLine();
                if (s.length() != 0)
                {
                    m = new BigInteger(s, 16);
                    System.out.println("e?");
                    s = scn.nextLine();
                    e = new BigInteger(s, 16);
                }

                PublicKey key = kf.generatePublic(new RSAPublicKeySpec(m, e));

                byte[] data = pt.getBytes("UTF-8");
                data = CryptoUtil.RSAEncrypt(data, key);

                System.out.println("ciphertext:");
                System.out.println(CryptoUtil.byteToHex(data));
                System.out.println();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return;
        }
    }
}
