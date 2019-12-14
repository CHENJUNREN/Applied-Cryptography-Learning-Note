package test2;

import java.math.BigInteger;
import java.util.Random;

public class Q3 {

    private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {
        Random rnd = new Random();
        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
        return res;
    }

    public static void main(String[] args) {
        BigInteger n = new BigInteger("3132403053003939537259120511215652669000209802124007390698026746711761911350452648337514579811695547619");
        BigInteger e = new BigInteger("101");
        BigInteger d = new BigInteger("837375073575310569366299518949623998366519985909693982657541642622662024250651023885704470531859949101");

        BigInteger k = d.multiply(e).subtract(BigInteger.ONE);
        BigInteger t;
        BigInteger x;
        BigInteger g;

        while (true) {
            g = uniformRandom(BigInteger.valueOf(2), n.subtract(BigInteger.ONE));
            t = k;
            if (t.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                t = t.divide(BigInteger.valueOf(2));
                x = g.modPow(t, n);
                break;
            }
        }

        BigInteger y;
        BigInteger p;
        BigInteger q;
        while (true) {
            y = n.gcd(x.subtract(BigInteger.ONE));
            if (x.compareTo(BigInteger.ONE) > 0 && y.compareTo(BigInteger.ONE) > 0) {
                p = y;
                q = n.divide(y);
                System.out.println(p);
                System.out.println(q);
                if (n.equals(p.multiply(q))) {
                    System.out.println("n=p*q!!!!");
                }
                break;
            } else {
                t = t.divide(BigInteger.valueOf(2));
                x = g.modPow(t, n);
            }
        }
    }
}
