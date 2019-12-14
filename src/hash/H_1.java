package hash;

import util.CryptoTools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class H_1 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        BigInteger n = new BigInteger("945874683351289829816050197767812346183848578056570056860845622609107886220137" +
                "220709264916908438536900712481301344278323249667285825328323632215422317870682" +
                "037630270674000828353944598575250177072847684118190067762114937353265007829546" +
                "21660256501187035611332577696332459049538105669711385995976912007767106063");
        BigInteger e = new BigInteger("74327");
        BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298" +
                "3441046583933853592091262678338882236956093668440986552405421520173544428836766" +
                "3419319185756836904299985444024205035318170370675348574916529512369448767695219" +
                "8090537385200990850805837963871485320168470788328336240930212290450023");
        byte[] pt = "Meet me at 5 pm tomorrow".getBytes();

        // Signature RSA = [ h(m) ]^d mod n

        // hash the pt
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hash = md.digest(pt);
        System.out.println(CryptoTools.bytesToHex(hash));
        // sign the hash
        BigInteger signature = new BigInteger(hash).modPow(d, n);
        System.out.println(CryptoTools.bytesToHex(signature.toByteArray()));



        // alice received pt and signature
        // decrypt signature using my public key to get hash
        byte[] received_hash = signature.modPow(e, n).toByteArray();
        System.out.println("Received Hash: " + CryptoTools.bytesToHex(received_hash));
        // hash the pt
        md = MessageDigest.getInstance("SHA-512");
        byte[] computed_hash = md.digest(pt);
        System.out.println("Computed Hash: " + CryptoTools.bytesToHex(computed_hash));
        if (Arrays.equals(computed_hash, received_hash)) {
            System.out.println("Sender and Content Integrity is confirmed!!!");
        }
    }
}
