package test;

public class Q1 {
    public static void main(String[] args) {
        byte[] ct = "YYTFFZNZEHTFGSIEGXQPDJIDLXEHDTPFVFEIGEIPBRNWHMQFSOTTBSRPNDC".getBytes();
        // decrypt vigenere
        char[] key2 = "ELABORATE".toCharArray();
        byte[] bk = new byte[ct.length];
        for (int i = 0; i < ct.length; i++) {
            int temp = ((ct[i] - 'A') - (key2[i % key2.length] - 'A')) % 26;
            if (temp < 0) temp += 26;
            bk[i] = (byte) (temp + 'A');
        }
        System.out.println(new String(bk));

        // decrypt transposition
        int key1 = 55;
        byte[] bk_2 = new byte[bk.length];
        for (int i = 0; i < bk.length; i++) {
            bk_2[i] = bk[(i+key1) % bk.length];
        }
        System.out.println("pt = " + new String(bk_2));
    }
}
