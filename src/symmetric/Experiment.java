package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import util.CryptoTools;

public class Experiment {

	public static void main(String[] args) throws Exception {
		byte[] pt = "My name is Ren Chenjun".getBytes();
		byte[] ky = "CSE@YORK".getBytes();
		byte[] iv = CryptoTools.hexToBytes("0123456701234567");
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, secret, aps);
		byte[] ct = cipher.doFinal(pt);
		
		System.out.println("CT = " + CryptoTools.bytesToHex(ct));
		
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] bk = cipher.doFinal(ct);
		System.out.println("BK = " + new String(bk) + "<");
	}

}
