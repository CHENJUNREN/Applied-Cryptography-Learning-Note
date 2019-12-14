package test2;

import util.CryptoTools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Q2 {
    public static String doYMAC(byte[] message, byte[] key) throws NoSuchAlgorithmException {
        byte[] km = array_concat(key, message);
        byte[] kmk = array_concat(km, key);
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] ymac = md.digest(kmk);
        return CryptoTools.bytesToHex(ymac);
    }

    public static byte[] array_concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String[] messages = {"Meet at 6:30 pm on 20.", "Buy 270 RY at MarketP.",
                "Temperature 28 in YYZ.", "Approach runway 24 SW."};
        String expected = "9975536EBEA43B5033E7DE5FD117C260DB8B02AB";
        byte[] key = CryptoTools.hexToBytes("856AB243EF5798B245A4A4");
        for (int i = 0; i < messages.length; i++) {
            System.out.println(messages[i] + " ---> " + doYMAC(messages[i].getBytes(), key));
            if (doYMAC(messages[i].getBytes(), key).equals(expected)) {
                System.out.println("found ---> " + messages[i]);
            }
        }
    }
}
