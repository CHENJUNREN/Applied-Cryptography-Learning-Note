package asymmetric;

import java.math.BigInteger;

public class A_2 {
    public static void main(String[] args) {
        BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722" +
                "07092649169084385369007124813013442783232496672858253283236322154223178706820376" +
                "30270674000828353944598575250177072847684118190067762114937353265007829546216602" +
                "56501187035611332577696332459049538105669711385995976912007767106063");
        BigInteger e = new BigInteger("74327");
        BigInteger c = new BigInteger("10870101966939556606443697147757930290262227730644958783498257036423105365610629" +
                "52991052582846432979261500260278236678653125327546335884041286783340625646715334" +
                "51395019521734099553221296896703454456327755743017818003765454489903326085581032" +
                "66831217073027652061091790342124418143422318965525239492387183438956");

        BigInteger p = new BigInteger("10358344307803887695931304169230543785620607743682421994532795393937342395753127" +
                "888522373061586445417642355843316524942445924294144921649080401518286829171");
        // n = p * q
        BigInteger q = n.divide(p);
        // phi = (p-1)(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // d = inverse of e mod phi
        BigInteger d = e.modInverse(phi);
        // m = c^d mod n
        BigInteger m = c.modPow(d, n);
        String message = new String(m.toByteArray());
        System.out.println(message + "<");

        // Test
        BigInteger pt = new BigInteger("Bob has the an RSA public key with the following exponent e and modulus n:".getBytes());
        BigInteger ct = pt.modPow(e, n);
        BigInteger bk = ct.modPow(d, n);
        System.out.println(new String(bk.toByteArray()) + "<");
    }
}
