package sTopics;

import util.CryptoTools;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.Key;

public class Check {
    public static void main(String[] args) throws Exception {
        BigInteger p = new BigInteger("1426978031065901624399459");  //prime modulus
        BigInteger g = new BigInteger("142983226354603241203899");   //primitive root
        BigInteger aX = new BigInteger("159103277483369930760549");  //Alice's DH private
        BigInteger bY = new BigInteger("47962228300062216296176"); //Bob's DH public
        String ct = "905F4B5AB6757A59"; //The received DES/ECB/PKCS5Padding ciphertext 0x

        BigInteger bYaX = bY.modPow(aX, p);
        byte[] candidateKey = bYaX.toByteArray();
        // used the first (i.e. leftmost) 64 bits/8 bytes of K as a key for the DES/ECB/PKCS5Padding cipher
        String temp = CryptoTools.bytesToHex(candidateKey);
        System.out.println(temp);
        byte[] key = CryptoTools.hexToBytes(temp.substring(0, 16));
        System.out.println(temp.substring(0, 16));

        Key secret = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] back = cipher.doFinal(CryptoTools.hexToBytes(ct));
        System.out.println(new String(back));
    }
}
