package sTopics;

import util.CryptoTools;
import java.math.BigInteger;

public class DiffieHellman {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("341769842231234673709819975074677605139");
        BigInteger g = new BigInteger("37186859139075205179672162892481226795");
        BigInteger aX = new BigInteger("83986164647417479907629397738411168307");
        BigInteger bX = new BigInteger("140479748264028247931575653178988397140");
        String answer = "00C15A519D8BB2050044D9E7F9803CCF66";

        BigInteger aY = g.modPow(aX, p);
        BigInteger bY = g.modPow(bX, p);

        String aYbX = CryptoTools.bytesToHex(aY.modPow(bX, p).toByteArray());
        System.out.println("aYbX = " + aYbX);
        String bYaX = CryptoTools.bytesToHex(bY.modPow(aX, p).toByteArray());
        System.out.println("bYaX = " + bYaX);

        if (aYbX.equals(answer) && bYaX.equals(answer)) {
            System.out.println("expected = actual");
        } else {
            System.out.println("your answer is wrong!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
}
