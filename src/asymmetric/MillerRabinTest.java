package asymmetric;

import java.math.BigInteger;
import java.util.Random;

/**
 * Input #1: n > 3, an odd integer to be tested for primality
 * Input #2: k, the number of rounds of testing to perform
 * Output: “composite” if n is found to be composite, “probably prime” otherwise
 *
 * write n as 2^r * d + 1 with d odd (by factoring out powers of 2 from n − 1)
 * WitnessLoop: repeat k times:
 *    pick a random integer a in the range [2, n − 2]
 *    x ← a^d mod n
 *    if x = 1 or x = n − 1 then
 *       continue WitnessLoop
 *    repeat r − 1 times:
 *       x ← x2 mod n
 *       if x = n − 1 then
 *          continue WitnessLoop
 *    return “composite”
 * return “probably prime”
 */
public class MillerRabinTest {
    public static boolean isProbablePrime(BigInteger n, int testNum) {
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return false;
        }
        if (n.compareTo(new BigInteger("3")) <= 0) {
            return true;
        }
        if (n.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            return false;
        }
        int r = 0;
        BigInteger d = n.subtract(BigInteger.ONE);
        BigInteger two = new BigInteger("2");
        while (d.mod(two).equals(BigInteger.ZERO)) {
            r++;
            d = d.divide(two);
        }

        for (int i = 0; i < testNum; i++) {
            BigInteger a = uniformRandom(two, n.subtract(two));
            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }
            int j = 0;
            for (; j < r - 1; j++) {
                x = x.modPow(two, n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    break;
                }
            }
            if (j == r - 1) {
                return false;
            }
        }
        return true;
    }

    private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {
        Random rnd = new Random();
        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
        return res;
    }

    public static void main(String[] args) throws Exception {
        int k = 10;
        BigInteger tmp;
        for (int i = 0; i < 1000; i++) {
            tmp = new BigInteger(500, new Random());
            boolean expected = tmp.isProbablePrime(2 * k);
            boolean actual = isProbablePrime(tmp, k);
            if (actual != expected) {
                throw new Exception("wrong answer detected!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
//            System.out.println(tmp.toString() + " is prime --->");
//            System.out.println("    expected: " + expected);
//            System.out.println("    actual: " + actual);
        }
        System.out.println("all test cases passed!!!");

        tmp = new BigInteger("1033931178476059651954862004553");
        System.out.println(tmp.toString() + " is a prime --->");
        System.out.println("    expected: " + tmp.isProbablePrime(2 * k));
        System.out.println("    actual: " + isProbablePrime(tmp, k));
    }
}
