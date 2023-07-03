import java.math.BigInteger;

class Diffie {
    private static BigInteger power(BigInteger a, BigInteger b, BigInteger p) {
        if (b.equals(BigInteger.ONE))
            return a;
        else
            return a.modPow(b, p);
    }

    public static void main(String[] args) {
        BigInteger P, G, x, a, y, b, ka, kb;

        P = BigInteger.valueOf(353);
        System.out.println("The value of P: " + P);

        G = BigInteger.valueOf(3);
        System.out.println("The value of G: " + G);

        a = BigInteger.valueOf(97);
        System.out.println("The private key a for Alice: " + a);

        x = power(G, a, P);

        b = BigInteger.valueOf(233);
        System.out.println("The private key b for Bob: " + b);

        y = power(G, b, P);

        ka = power(y, a, P);
        kb = power(x, b, P);

        System.out.println("Secret key for Alice: " + ka);
        System.out.println("Secret key for Bob: " + kb);
    }
}
