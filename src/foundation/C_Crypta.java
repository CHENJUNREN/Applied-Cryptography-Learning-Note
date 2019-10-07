package foundation;

import util.CryptoTools;

public class C_Crypta {

	public static void main(String[] args) throws Exception {
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct.txt");

		double[] freq = CryptoTools.getRelativeFrequencies(ct);
		double max_freq = 0;
		char max_char = 0;
		for (int i = 0; i < freq.length; i++) {
			System.out.printf("%c ---> frequceny: %f\n", i + 'A', freq[i]);
			if (freq[i] > max_freq) {
				max_freq = freq[i];
				max_char = (char) ('A' + i);
			}
		}
		System.out.println("=====================================================");
		System.out.printf("MAX: %c ---> frequceny: %f\n", max_char, max_freq);
		int key = max_char - 'E';
		if (key < 0) key += 26;
		System.out.println("key = " + key);
		
		byte[] bk = new byte[ct.length];
		for (int i = 0; i < ct.length; i++) {
			int temp = (ct[i] - 'A' - key) % 26;
			if (temp < 0) temp += 26;
			bk[i] = (byte) (temp + 'A');
		}
		System.out.println("dct = " + new String(bk));
	}

}
