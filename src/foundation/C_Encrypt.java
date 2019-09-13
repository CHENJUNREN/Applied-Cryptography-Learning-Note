package foundation;

import util.CryptoTools;

public class C_Encrypt {

	public static void main(String[] args) throws Exception {
		byte[] raw = CryptoTools.fileToBytes("data/MSG1.pt.txt");
		byte[] pt = CryptoTools.clean(raw);
		CryptoTools.bytesToFile(pt, "data/MSG1.clean");
		
		int key = 19;
		byte[] ct = new byte[pt.length];
		for (int i = 0; i < ct.length; i++) {
			ct[i] = (byte) ((pt[i] - 'A' + key) % 26 + 'A');
		}
		CryptoTools.bytesToFile(ct, "data/MSG1.ct");
		
		String actual_md5 = CryptoTools.getMD5(ct);
		String expected_md5 = "2C422B741EF90FD4424EBC83692398B0";
		System.out.printf("actual: %s\nexpected: %s\n", actual_md5, expected_md5);
		if (actual_md5.equals(expected_md5)) {
			System.out.println("identical!!!");
		} else {
			System.out.println("different!!!");
		}
		
		System.out.printf("the index of coincidence of the clean plaintext: %.2f\n", CryptoTools.getIC(pt));
		System.out.printf("the index of coincidence of the ciphertext: %.2f\n", CryptoTools.getIC(ct));
	}

}
