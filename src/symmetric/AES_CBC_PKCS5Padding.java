package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import util.CryptoTools;

public class AES_CBC_PKCS5Padding {

	public static void main(String[] args) throws Exception {

		byte[] ky = CryptoTools.hexToBytes("444F204E4F542054454C4C2045564521");
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		byte[] ct = CryptoTools.hexToBytes("FB0692B011F74F8BF77EDE2630852C1700C204407EDF2222D965F74A8BCEB236");
		
		Key key = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key, aps);
		byte[] bk = cipher.doFinal(ct);
		System.out.println("bk = " + new String(bk));
	}
}