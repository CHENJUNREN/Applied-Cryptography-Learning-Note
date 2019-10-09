package symmetric;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Conceptual {
    public static void main(String[] args) throws Exception {
        System.out.println("1.OTP---------------------------------");
        byte[] pt = "SENDMOREMONEY".getBytes();
        byte[] key = "JABHXPVOLLCIJ".getBytes();
        byte[] ct = new byte[pt.length];
        for (int i = 0; i < ct.length; i++) {
            ct[i] = (byte) ((((pt[i] - 'A') + (key[i] - 'A')) % 26) + 'A');
        }
        System.out.println("ct = " + new String(ct));

        byte[] fpt = "CASHNOTNEEDED".getBytes();
        byte[] fkey = new byte[key.length];
        for (int i = 0; i < fkey.length; i++) {
            int temp = ((ct[i] - 'A') - (fpt[i] - 'A')) % 26;
            if (temp < 0) temp += 26;
            fkey[i] = (byte) (temp + 'A');
        }
        System.out.println("wrong key = " + new String(fkey));



        System.out.println("2.OTP---------------------------------");
        byte[] send_1 = CryptoTools.hexToBytes("0A4F0C08003503492F247442105B5757");
        byte[] send_2 = CryptoTools.hexToBytes("5E2769286B507A69494B066252343579");
        byte[] send_3 = CryptoTools.hexToBytes("170708454B1116002A2E2111725F5000");
        byte[] a_key = new byte[send_3.length];
        for (int i = 0; i < a_key.length; i++) {
            a_key[i] = (byte) (send_3[i] ^ send_2[i]);
        }
        pt = new byte[a_key.length];
        for (int i = 0; i < a_key.length; i++) {
            pt[i] = (byte) (send_1[i] ^ a_key[i]);
        }
        System.out.println("pt = " + new String(pt));



        System.out.println("4.DES---------------------------------");
        pt = "Facebook".getBytes();
        key = "universe".getBytes();
        Key secret = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        ct = cipher.doFinal(pt);
        System.out.println("ct (binary) = " + CryptoTools.bytesToBin(ct));


        for (int i = 0; i < 10; i++) {
            String pt_bin = CryptoTools.bytesToBin(pt);
            int ran = (int) (Math.random() * 64);

        }
    }
}
