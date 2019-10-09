package symmetric;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class DES_YORK_NoPadding {

	public static void main(String[] args) throws Exception {
		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
		String[] blocks_hex = CryptoTools.getBlocks("437DBAB5607137A5CFC1031114634087");
		StringBuilder bk = new StringBuilder();

		for (String blocksHex : blocks_hex) {
			byte[] block = CryptoTools.hexToBytes(blocksHex);
			byte[] negated = CryptoTools.getNegation(iv);

			Key secret = new SecretKeySpec(ky, "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
			AlgorithmParameterSpec aps = new IvParameterSpec(negated);
			cipher.init(Cipher.DECRYPT_MODE, secret, aps);
			bk.append(new String(cipher.doFinal(block)));
			iv = block;
		}
		System.out.println("bk = " + bk + " --- size = " + bk.length());
	}
}
