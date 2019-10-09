package symmetric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Double_DES_ECB_NoPadding {

	public static void main(String[] args) throws Exception {
		
		BufferedReader reader = new BufferedReader(new FileReader(new File("data/MSG.ct.txt")));
		String ct_hex = reader.readLine();
		byte[] ct = CryptoTools.hexToBytes(ct_hex);
		byte[] ky = "FACEBOOK".getBytes();
		byte[] negated_ky = CryptoTools.getNegation(ky);
		
		// decrypt first layer
		Key secret = new SecretKeySpec(negated_ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] temp = cipher.doFinal(ct);
		
		// second layer
		secret = new SecretKeySpec(ky, "DES");
		cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] bk = cipher.doFinal(temp);
		
		System.out.println("bk = " + new String(bk) + "<");
		reader.close();
	}
}
