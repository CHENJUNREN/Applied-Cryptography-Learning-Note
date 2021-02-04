package test3;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Q2 {

    public static void main(String[] args) throws Exception {
        BigInteger p = new BigInteger("133717718270293296200188525502739327388353629179621058494112854251193809058703");
        BigInteger g = new BigInteger("88653877561129476419831247959360597000469401190192885546499619313456391487");
        BigInteger aX = new BigInteger("40946114960323376780632697114159865026104715183899616608412774756574256842942");
        BigInteger bY = new BigInteger("66084206540781068627271629211666577778562915982852519047450133068281295962922");

        byte[] ct = CryptoTools.hexToBytes("46ECC6540D13291E1C6D97277597423F40EE73C6FE179DEBDB40B6CB7A0C973E");

        BigInteger ks = bY.modPow(aX, p);
        byte[] candidateKey = ks.toByteArray();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] k = md.digest(candidateKey);

        Key secret = new SecretKeySpec(k, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] back = cipher.doFinal(ct);
        System.out.println(new String(back));
    }
}
