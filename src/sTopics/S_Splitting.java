package sTopics;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class S_Splitting {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        BigInteger secret = new BigInteger("291639075201575653178417");
        BigInteger[] shares = new BigInteger[5];
        for (int i = 0; i < shares.length; i++) {
            byte[] temp = new byte[10];
            if (i < shares.length - 1) {
                SecureRandom.getInstanceStrong().nextBytes(temp);
                shares[i] = new BigInteger(temp);
            } else {
                temp = secret.toByteArray();
                for (int j = 0; j < i; j++) {
                    for (int k = 0; k < temp.length; k++) {
                        temp[k] = (byte) (temp[k] ^ (shares[j].toByteArray()[k]));
                    }
                }
                shares[i] = new BigInteger(temp);
            }
            System.out.println(shares[i]);
        }

        //test
        byte[] expected = secret.toByteArray();
        byte[] actual = new byte[expected.length];
        for (int i = 0; i < shares.length; i++) {
            for (int j = 0; j < actual.length; j++) {
                actual[j] = (byte) (actual[j] ^ shares[i].toByteArray()[j]);
            }
        }
        System.out.println("expected = " + secret);
        System.out.println("actual = " + new BigInteger(actual));
        if (Arrays.equals(expected, actual)) {
            System.out.println("expected = actual !!!");
        }
    }
}
