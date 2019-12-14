package hash;

import java.math.BigInteger;

public class H_2 {
    public static void main(String[] args) {
        BigInteger na = new BigInteger("171024704183616109700818066925197841516671277");
        BigInteger ea = new BigInteger("1571");

        BigInteger m_en = new BigInteger("418726553997094258577980055061305150940547956");
        BigInteger s_en = new BigInteger("749142649641548101520133634736865752883277237");

        BigInteger pb = new BigInteger("98763457697834568934613");
        BigInteger qb = new BigInteger("8495789457893457345793");
        BigInteger eb = new BigInteger("87697");

        // nb = pb * qb
        BigInteger nb = pb.multiply(qb);
        // phib = pb-1 * qb-1
        BigInteger phib = pb.subtract(BigInteger.ONE).multiply(qb.subtract(BigInteger.ONE));
        // db = eb ^ -1 mod phib
        BigInteger db = eb.modInverse(phib);

        BigInteger m_actual = m_en.modPow(db, nb);
        // 19012507151504022505
        System.out.println(m_actual);
        BigInteger s_de = s_en.modPow(db, nb);
        BigInteger m_expected = s_de.modPow(ea, na);
        System.out.println(m_expected);
        if (m_actual.equals(m_expected)) {
            System.out.println("The message is from its origin and is not tampered!");
        }

//        byte[] pt = "Meet me".getBytes();
//        BigInteger tmp = new BigInteger(pt).modPow(eb, nb);
//        byte[] bk = tmp.modPow(db, nb).toByteArray();
//        System.out.println(new String(bk));
    }
}
