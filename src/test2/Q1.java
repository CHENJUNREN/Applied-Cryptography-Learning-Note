package test2;

import java.math.BigInteger;

public class Q1 {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("202628268290269033102686190252075046249");
        BigInteger q = new BigInteger("171213909205172846338769230720971291221");
        BigInteger e = new BigInteger("1031");
        BigInteger ct = new BigInteger("27588605030657846983199614757691133483802777095581092906213019390126237391112");

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(phi);
        BigInteger m = ct.modPow(d, n);
        String bk = new String(m.toByteArray());
        System.out.println(bk);
    }
}