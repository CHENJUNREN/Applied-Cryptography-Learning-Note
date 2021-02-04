package test3;

import java.math.BigInteger;

public class Q4 {

    public static void main(String[] args) {
        BigInteger p = new BigInteger("499170338126214911321316210002647309787");
        BigInteger aX = new BigInteger("135841463366881218399046066202995310891");
        BigInteger aY = new BigInteger("247958587893908989381714980913228518468");

        BigInteger gcd = aX.gcd(p.subtract(BigInteger.ONE));
        if (gcd.equals(BigInteger.ONE)) {
            BigInteger temp = aX.modPow(BigInteger.valueOf(-1), p.subtract(BigInteger.ONE));
            // temp = aX.modInverse(p.subtract(BigInteger.ONE));
            BigInteger g = aY.modPow(temp, p);
            BigInteger check = g.modPow(aX, p);
            if (aY.equals(check)) {
                System.out.println("expected: " + aY);
                System.out.println("actual: " + check);
                System.out.println("found g!!!!!!");
                System.out.println("g is " + g);
            }
        } else {
            System.out.println("Not Possible");
        }
    }
}
