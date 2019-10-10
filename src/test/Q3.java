package test;

import util.CryptoTools;

public class Q3 {
    public static void main(String[] args) {
        byte[] ct = CryptoTools.hexToBytes("06071C07454903410C1E13472A4E1148261C4D264139130B");
        int index = ct.length - 5;
        char[] paris = "Paris".toCharArray();
        char[] cairo = "Cairo".toCharArray();
        for (int i = 0; i < paris.length; i++) {
            ct[index] = (byte) (ct[index] ^ paris[i]);
            ct[index] = (byte) (ct[index] ^ cairo[i]);
            index++;
        }
        System.out.println(CryptoTools.bytesToHex(ct));
    }
}
