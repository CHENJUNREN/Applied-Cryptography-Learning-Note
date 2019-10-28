package asymmetric;

import java.math.BigInteger;

public class A_4 {
    public static void main(String[] args) {
        BigInteger m1 = new BigInteger("1055827021987");
        BigInteger a1 = new BigInteger("365944767426");
        BigInteger m2 = new BigInteger("973491987203");
        BigInteger a2 = new BigInteger("698856040412");

        BigInteger x;
        if (m1.gcd(m2).equals(BigInteger.ONE)) {
            BigInteger M = m1.multiply(m2);
            BigInteger M1 = M.divide(m1);
            BigInteger M2 = M.divide(m2);
            BigInteger y1 = M1.modInverse(m1);
            BigInteger y2 = M2.modInverse(m2);
            x = a1.multiply(M1).multiply(y1).add(a2.multiply(M2).multiply(y2)).mod(M);
            System.out.println("x is " + x.toString());
            System.out.println("x mod 1055827021987 --->");
            System.out.println("    expected: 365944767426");
            System.out.println("    actual: " + x.mod(m1).toString());
            System.out.println("    is equal? " + x.mod(m1).toString().equals("365944767426"));

            System.out.println("x mod 973491987203 --->");
            System.out.println("    expected: 698856040412");
            System.out.println("    actual: " + x.mod(m2).toString());
            System.out.println("    is equal? " + x.mod(m2).toString().equals("698856040412"));
        } else {
            BigInteger n = new BigInteger("464260182");
            while (true) {
                x = n.multiply(m1).add(a1);
                if (x.mod(m1).equals(a2)) {
                    break;
                } else {
                    /*
                    490178432028396103229------ > Failed...
                    490178433084223125216------ > Failed...
                    490178434140050147203------ > Failed...
                    490178435195877169190------ > Failed...
                    490178436251704191177------ > Failed...
                    490178437307531213164------ > Failed...
                    490178438363358235151------ > Failed...
                    490178439419185257138------ > Failed...
                    490178440475012279125------ > Failed...
                    490178441530839301112------ > Failed...
                    490178442586666323099------ > Failed...
                    490178443642493345086------ > Failed...
                    490178444698320367073------ > Failed...
                    490178445754147389060------ > Failed...

                    2237908033109115607669------ > Failed...
                    2237908034164942629656------ > Failed...
                    2237908035220769651643------ > Failed...
                    2237908036276596673630------ > Failed...
                    2237908037332423695617------ > Failed...
                    894202650421088662926662
                    */
                    System.out.println(x.toString() + "------> Failed...");
                }
                n = n.add(BigInteger.ONE);
            }
            System.out.println(x.toString() + "----------------> Succeeded!!!");
        }
    }
}
