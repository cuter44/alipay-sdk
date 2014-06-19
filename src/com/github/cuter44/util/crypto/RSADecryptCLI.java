package com.github.cuter44.util.crypto;

import java.math.BigInteger;
import java.util.Scanner;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

public class RSADecryptCLI
{
    public static void main(String[] args)
    {
        try
        {
            String s;
            String ct;
            BigInteger m = null;
            BigInteger d = null;
            Scanner scn = new Scanner(System.in);

            KeyFactory kf = KeyFactory.getInstance("RSA");

            while (true)
            {
                System.out.println("ciphertext?");
                ct = scn.nextLine();
                System.out.println("m? in hex, [Enter] to use latest");
                s = scn.nextLine();
                if (s.length() != 0)
                {
                    m = new BigInteger(s, 16);
                    System.out.println("d?");
                    s = scn.nextLine();
                    d = new BigInteger(s, 16);
                }

                PrivateKey key = kf.generatePrivate(new RSAPrivateKeySpec(m, d));

                byte[] data = CryptoUtil.hexToBytes(ct);
                data = CryptoUtil.RSADecrypt(data, key);

                System.out.println("plaintext:");
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
