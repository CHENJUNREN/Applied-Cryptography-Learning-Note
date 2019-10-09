package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class AES_CBC_PKCS5Padding_2 {

	public static void main(String[] args) throws Exception {

		byte[] ky = "DO NOT TELL EVE!".getBytes();
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		byte[] ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");
		
		Key key = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key, aps);
		byte[] bk = cipher.doFinal(ct);
		System.out.println("bk = " + new String(bk) + "<");
	}

}
