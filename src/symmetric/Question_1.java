package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Question_1 {

	public static void main(String[] args) throws Exception {

		byte[] ky = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		byte[] ct = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");
		
		Key key = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key, aps);
		byte[] bk = cipher.doFinal(ct);
		System.out.println("bk = " + new String(bk));
	}

}
