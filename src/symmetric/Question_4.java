package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Question_4 {

	public static void main(String[] args) throws Exception {
		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
		String[] blocks_hex = getBlocks("437DBAB5607137A5CFC1031114634087");
		String bk = "";
		
		for (int i = 0; i < blocks_hex.length; i++) {
			byte[] block = CryptoTools.hexToBytes(blocks_hex[i]);
			byte[] negated = getNegation(iv);

			Key secret = new SecretKeySpec(ky, "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
			AlgorithmParameterSpec aps = new IvParameterSpec(negated);
			cipher.init(Cipher.DECRYPT_MODE, secret, aps);
			bk += new String(cipher.doFinal(block));
			iv = block;
		}
		System.out.println("bk = " + bk + " --- size = " + bk.length());
		

	}
	
	public static String[] getBlocks(String ct_hex) {
		int blockNum = ct_hex.length() / 16;
		String[] result = new String[blockNum];
		for (int i = 0; i < blockNum; i++) {
			result[i] = ct_hex.substring(i * 16, (i+1) * 16);
		}
		return result;
	}
	
	public static byte[] getNegation(byte[] ar) {
		byte[] result = new byte[ar.length];
		for (int i = 0; i < ar.length; i++) {
			result[i] = (byte) ~ar[i];
		}
		return result;
	}

}
