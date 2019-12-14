package hash;

import util.CryptoTools;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class H_3 {
    // The underline hash algorithm is SHA1 (block size: 64 bytes, output size: 20 bytes)
    public static byte[] doHMACSHA1(byte[] key, byte[] message, byte opad_value, byte ipad_value) throws NoSuchAlgorithmException {
        final int blockSize = 64;
        final int outputSize = 20;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        if (key.length > blockSize) {
            key = md.digest(key);
        }
        if (key.length < blockSize) {
            // Keys shorter than blockSize are padded to blockSize by padding with zeros on the right
            byte[] zeros = new byte[blockSize - key.length];
            Arrays.fill(zeros, (byte) 0);
            key = array_concat(key, zeros);
        }

        // initialize Outer padded key and Inner padded key
        byte[] o_key_pad = new byte[blockSize];
        byte[] i_key_pad = new byte[blockSize];
        for (int i = 0; i < blockSize; i++) {
            o_key_pad[i] = (byte) (key[i] ^ opad_value);
            i_key_pad[i] = (byte) (key[i] ^ ipad_value);
        }
        return md.digest(array_concat(o_key_pad, md.digest(array_concat(i_key_pad, message))));
    }

    public static byte[] array_concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static byte[] hmacsha1(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        byte opad_value = CryptoTools.hexToBytes("5c")[0];
        byte ipad_value = CryptoTools.hexToBytes("36")[0];
        byte[] actual = doHMACSHA1("This is an ultra-secret key".getBytes(), "Mainly cloudy with 40 percent chance of showers".getBytes(), opad_value, ipad_value);
        System.out.println("actual = " + CryptoTools.bytesToHex(actual));

        // test
        byte[] expected = hmacsha1("Mainly cloudy with 40 percent chance of showers".getBytes(), "This is an ultra-secret key".getBytes());
        System.out.println("expected = " + CryptoTools.bytesToHex(expected));
        if (Arrays.equals(actual, expected)) {
            System.out.println("actual = expected");
        } else {
            System.out.println("actual /= expected!!!!!!!!!!!!!!!!!");
        }
    }
}
