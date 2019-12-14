package sTopics;

import java.math.BigInteger;
import java.util.Random;

public class S_Sharing {
    public static void main(String[] args) {
        BigInteger m = BigInteger.valueOf(44);
        int t = 3;
        int n = 5;

        BigInteger p = generatePrime(m);
        BigInteger[] coefficients = generateCoefficients(t, p);
        // tests
        System.out.println("modulus: " + p);
        System.out.println("modulus is prime: " + p.isProbablePrime(20));
        for (int i = 0; i < coefficients.length; i++) {
            System.out.print("coefficient " + (i+1) + ": ");
            System.out.println(coefficients[i]);
        }

        for (int i = 0; i < n; i++) {
            BigInteger x = BigInteger.valueOf(i+1);
            System.out.print("Share " + x + ": ");
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

    public static BigInteger generatePrime(BigInteger m) {
        BigInteger result;
        do {
            result = new BigInteger(m.bitLength() + 5, new Random());
        } while (!result.isProbablePrime(20) || result.compareTo(m) <= 0);
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
