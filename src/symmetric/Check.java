package symmetric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Check {

	public static void main(String[] args) throws Exception {
		
		BufferedReader reader = new BufferedReader(new FileReader(new File("data/check_B.txt")));
		String ct_hex = reader.readLine();
		byte[] ct = CryptoTools.hexToBytes(ct_hex);
		byte[] ky = CryptoTools.hexToBytes("6275696C64696E67");
		byte[] negated_ky = Question_4.getNegation(ky);
		
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
		
		System.out.println(new String(bk) + "<>");
		reader.close();
	}
}
