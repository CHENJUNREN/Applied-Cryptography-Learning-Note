package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_CBC_PKCS5Padding {

	public static void main(String[] args) throws Exception {
		///////////////////////////////////////////////////////////////////////////////////////////////////
		String ct_hex = "????????????????4E51297B424F90D8B2ACD6ADF010DDC4";
		//String ct_hex = "38A347228B775DCA20A58773607F9692B8B4F3DD9BF93D61";
		String[] blocks_hex = CryptoTools.getBlocks(ct_hex);
		byte[] ct_block_2 = CryptoTools.hexToBytes(blocks_hex[1]);
		byte[] ct_block_3 = CryptoTools.hexToBytes(blocks_hex[2]);
		
		byte[] ky = "CSE@YORK".getBytes();
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(ct_block_2);
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		
		byte[] bk_block_3 = cipher.doFinal(ct_block_3);
		System.out.println("BK = " + new String(bk_block_3) + "<");
	}

}
