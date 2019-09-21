package foundation;

import java.math.BigInteger;

import util.CryptoTools;

public class A_Exhaustive {

	public static void main(String[] args) throws Exception {
		byte[] ct = CryptoTools.fileToBytes("data/check.txt");

		int count = 0;
		int alpha;
		int beta;
		byte[] test = new byte[ct.length];
		
		int a = 0,b = 0;
		double max_dot = 0;
		String dct = "";
		
		for (alpha = 1; alpha < 26; alpha++) {
			int gcd = BigInteger.valueOf(alpha).gcd(BigInteger.valueOf(26)).intValue();
			if (gcd == 1) {
				// alpha needs to be the value such that gcd(alpha, 26) = 1
				for (beta = 0; beta < 26; beta++) {
					int alpha_inverse = BigInteger.valueOf(alpha).modInverse(BigInteger.valueOf(26)).intValue();
					for (int i = 0; i < test.length; i++) {
						int temp = ((ct[i] - 'A' - beta) * alpha_inverse) % 26;
						if (temp < 0) temp += 26;
						test[i] = (byte) (temp + 'A');
					}
					int[] freq = CryptoTools.getFrequencies(test);
					double dot_product = CryptoTools.getDotProduct(freq, CryptoTools.ENGLISH);
					System.out.printf("alpha = %d, beta = %d ---> %f\n", alpha, beta, dot_product);
					count++;
					
					if (dot_product > max_dot) {
						max_dot = dot_product;
						a = alpha;
						b = beta;
						dct = new String(test);
					}
				}
			}
		}
		System.out.println("Total combs: " + count);
		System.out.printf("alpha = %d, beta = %d ---> %f\n%s", a, b, max_dot, dct);
	}

}
