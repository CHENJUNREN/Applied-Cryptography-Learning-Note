package sTopics;

import java.math.BigInteger;
import java.util.Random;

public class S_Sharing {
    public static void main(String[] args) {
        BigInteger m = BigInteger.valueOf(44);
        int t = 3;
        int n = 5;

        // generate a prime number p that is bigger than n and m
        BigInteger p = generatePrime(m, BigInteger.valueOf(n));
        // generate t-1 coefficients such that all of them are smaller than p
        BigInteger[] coefficients = generateCoefficients(t, p);
        // tests
        System.out.println("modulus: " + p);
        System.out.println("modulus is prime: " + p.isProbablePrime(20));
        for (int i = 0; i < coefficients.length; i++) {
            System.out.print("coefficient " + (i + 1) + ": ");
            System.out.println(coefficients[i]);
        }

        // generate n shares
        for (int i = 0; i < n; i++) {
            BigInteger x = BigInteger.valueOf(i + 1);
            System.out.print("Share " + (i + 1) + ": ");
            System.out.print("(" + x + ", ");
            System.out.println(getValue(coefficients, m, x, p) + ")");
        }
    }

    public static BigInteger getValue(BigInteger[] coefficients, BigInteger m, BigInteger x, BigInteger p) {
        BigInteger result = m;
        for (int i = 0; i < coefficients.length; i++) {
            result = result.add(coefficients[i].multiply(x.pow(i + 1)));
        }
        return result.mod(p);
    }

    public static BigInteger generatePrime(BigInteger m, BigInteger n) {
        BigInteger result;
        do {
            result = new BigInteger(m.bitLength() + 5, new Random());
        } while (!result.isProbablePrime(20) || result.compareTo(m) <= 0 || result.compareTo(n) <= 0);
        return result;
    }

    public static BigInteger[] generateCoefficients(int t, BigInteger p) {
        BigInteger[] result = new BigInteger[t - 1];
        for (int i = 0; i < result.length; i++) {
            do {
                result[i] = new BigInteger(p.bitLength(), new Random());
            } while (result[i].compareTo(p) >= 0);
        }
        return result;
    }
}
