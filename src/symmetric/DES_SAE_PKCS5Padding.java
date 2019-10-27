package symmetric;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class DES_SAE_PKCS5Padding {

	public static void main(String[] args) throws Exception {
		byte[] ky = CryptoTools.hexToBytes("4F75725269676874");
		byte[] iv = CryptoTools.hexToBytes("496E566563746F72");
		String[] ct_blocks_hex = CryptoTools.getBlocks("7AA38A029E773CBBC188A9FCEADAE99DA560B784C99AFEF2");

		StringBuilder out_hex = new StringBuilder();
		byte[] pre_block = iv;
		for (String ct_block_Hex : ct_blocks_hex) {
			byte[] block = CryptoTools.hexToBytes(ct_block_Hex);
			byte[] tmp = new byte[block.length];
			for (int i = 0; i < block.length; i++) {
				tmp[i] = (byte) (block[i] ^ pre_block[i]);
			}
			out_hex.append(CryptoTools.bytesToHex(tmp));
			pre_block = block;
		}

		byte[] out = CryptoTools.hexToBytes(out_hex.toString());
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret);
		System.out.println("bk = " + new String(cipher.doFinal(out)));
	}
}
