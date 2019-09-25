package foundation;

import java.io.ByteArrayOutputStream;

import util.CryptoTools;

public class V_Crypta {

	public static void main(String[] args) throws Exception {
		byte[] ct = CryptoTools.fileToBytes("data/MSG4.ct.txt");

		for (int i = 1; i < 50; i++) {
			byte[] sample = sample(ct, i, 0);
			double ic = CryptoTools.getIC(sample);
			if (ic > 0.06) {
				System.out.printf("key length = %d ---> ic = %f\n", i, ic);
			}
		}

		int length = 9;
		int[] keySet = new int[length];
		for (int i = 0; i < length; i++) {
			byte[] sample = sample(ct, length, i);
			double maxDot = 0;
			int maxKey = 0;
			// Exhaustive 
			for (int j = 0; j < 26; j++) {
				byte[] test = new byte[sample.length];
				for (int k = 0; k < sample.length; k++) {
					int temp = (sample[k] - 'A' - j) % 26;
					if (temp < 0)
						temp += 26;
					test[k] = (byte) (temp + 'A');
				}
				double dp = CryptoTools.getDotProductWithEnglish(CryptoTools.getFrequencies(test));
				if (dp > maxDot) {
					maxDot = dp;
					maxKey = j;
				}
			}
			keySet[i] = maxKey;
		}
		
		/*
		for (int i = 0; i < length; i++) {
			byte[] sample = sample(ct, length, i);
			// Crypta
			double[] freq = CryptoTools.getFrequencies(sample);
			double max_freq = 0;
			char max_char = 0;
			for (int j = 0; j < freq.length; j++) {
				if (freq[j] > max_freq) {
					max_freq = freq[j];
					max_char = (char) ('A' + j);
				}
			}
			keySet[i] = max_char - 'E';
		} 
		*/
		
		System.out.println("-------------------------------------------------------------");
		// reveal the key
		byte[] key = new byte[length];
		for (int i = 0; i < keySet.length; i++) {
			key[i] = (byte) (keySet[i] + 'A');
		}
		System.out.printf("key = %s\n", new String(key));

		System.out.println("-------------------------------------------------------------");
		// Decrypt the ciphertext
		byte[] bk = new byte[ct.length];
		for (int i = 0; i < ct.length; i++) {
			int temp = (ct[i] - 'A' - keySet[i % length]) % 26;
			if (temp < 0)
				temp += 26;
			bk[i] = (byte) (temp + 'A');
		}
		System.out.println(new String(bk));
	}

	public static byte[] sample(byte[] ar, int skip, int startIndex) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		for (int i = startIndex; i < ar.length; i += skip) {
			baos.write(ar[i]);
		}
		return baos.toByteArray();
	}
}
