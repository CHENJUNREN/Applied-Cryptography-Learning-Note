package hash;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;

// The Message: "No one can make you feel inferior without your consent."
// The Secret Key: "anotherpopularab"

// MAC (Message Authenticated Code)
// Encrypt digest with a secret key: MAC = E[ K, h(m) ]
public class Check {
    public static void main(String[] args) throws Exception {
        byte[] pt = "No one can make you feel inferior without your consent.".getBytes();
        byte[] key = "anotherpopularab".getBytes();
        // hash the pt
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(pt);
        System.out.println(hash.length);
        System.out.println("h(m) = " + CryptoTools.bytesToHex(hash));

        if (CryptoTools.bytesToHex(hash).equals("AD7C39EBFA805C61969F0A6A4616DB34")) {
            System.out.println("your hash is the same as AD7C39EBFA805C61969F0A6A4616DB34");
        }

        // encrypt the hash with AES no padding
        System.out.println(key.length);
        Key ky = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, ky);
        byte[] mac = cipher.doFinal(hash);
        System.out.println("MAC = E[K, h(m)] = " + CryptoTools.bytesToHex(mac));

        if ("e933fe69a4a8d5a169a33a3dfc7b173a".toUpperCase().equals(CryptoTools.bytesToHex(mac))) {
            System.out.println("your mac is the same as e933fe69a4a8d5a169a33a3dfc7b173a");
        }
    }
}
