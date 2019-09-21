package foundation;

import util.CryptoTools;

public class C_Exhaustive {

	public static void main(String[] args) throws Exception {
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct.txt");

		int num_shift = 26;
		byte[] bk;
		
		int max_key = 0;
		double max_dot = 0;
		String max_dct = "";
		for (int key = 0; key < num_shift; key++) {
			bk = new byte[ct.length];
			for (int i = 0; i < ct.length; i++) {
				int temp = (ct[i] - 'A' - key) % 26;
				if (temp < 0) temp += 26;
				bk[i] = (byte) (temp + 'A');
			}
			//System.out.println("dct = " + new String(bk));
			int[] freq = CryptoTools.getFrequencies(bk);
			double dotProduct = CryptoTools.getDotProduct(freq, CryptoTools.ENGLISH);
			//System.out.printf("shift = %d ---> dot product = %f\n\n", key, dotProduct);
			
			if (dotProduct > max_dot) {
				max_dot = dotProduct;
				max_key = key;
				max_dct = new String(bk);
			}
		}
		System.out.println("dct = " + max_dct);
		System.out.printf("shift = %d ---> dot product = %f\n\n", max_key, max_dot);
	}

}
