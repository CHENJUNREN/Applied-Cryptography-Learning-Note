package test;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class Q2 {
    public static void main(String[] args) throws Exception {
        //byte[] ct = "4519D15E452E82E3F93ECFDCA2373791".getBytes();
        byte[] ct = CryptoTools.hexToBytes("4519D15E452E82E3F93ECFDCA2373791");
        byte[] key2 = CryptoTools.hexToBytes("6D79796F726B756E6976657273697479");
        byte[] key1 = "Geologic".getBytes();
        byte[] iv = CryptoTools.hexToBytes("30396F6968677771637667686A6B3635");

        // decrypt AES (using key2, PKCS5 padding, and CBC with the IV)
        Key key = new SecretKeySpec(key2, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        AlgorithmParameterSpec aps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, aps);
        byte[] bk = cipher.doFinal(ct);
        System.out.println("bk = " + new String(bk));

        // decrypt DES (using key1, no padding, and ECB)
        key = new SecretKeySpec(key1, "DES");
        cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bk_2 = cipher.doFinal(bk);
        System.out.println("pt = " + new String(bk_2));
    }
}
