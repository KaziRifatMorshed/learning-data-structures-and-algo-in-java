package InformationSecurityLab.lab5;

import java.math.BigInteger;

public class ElGamal {

    public static class Signature {
        private final long s1, s2;

        public Signature(long s1, long s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        public long getS1() {
            return s1;
        }

        public long getS2() {
            return s2;
        }

        public String toString() {
            return "Signatures: S1=" + s1 + ", S2=" + s2;
        }
    }

    private final long q;
    private final long alpha;
    private final long privateKeyA;
    private final long publicKeyA;

    public ElGamal(long q, long alpha) {
        this.q = q;
        this.alpha = alpha;
        long[] keys = generateKeys(q, alpha);
        this.privateKeyA = keys[0];
        this.publicKeyA = keys[1];
    }

    public long getPrivateKeyA() {
        return privateKeyA;
    }

    public long getPublicKeyA() {
        return publicKeyA;
    }

    public static long modInverse(long a, long m) {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(m)).longValue();
    }

    public static long modPow(long base, long exp, long mod) {
        return BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).longValue();
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long[] generateKeys(long q, long alpha) {
        long X_A = 2 + (long) (Math.random() * (q - 3));
        long Y_A = modPow(alpha, X_A, q);
        return new long[]{X_A, Y_A};
    }

    private static long hashMessage(String message, long q) {
        long m = 0;
        for (char c : message.toCharArray()) m += c;
        m %= q;
        return m == 0 ? 1 : m;
    }

    public Signature sign(String message) {
        return sign(message, this.privateKeyA, this.q, this.alpha);
    }

    public static Signature sign(String message, long X_A, long q, long alpha) {
        long m = hashMessage(message, q);

        long K;
        while (true) {
            K = 2 + (long) (Math.random() * (q - 3));
            if (gcd(K, q - 1) == 1) break;
        }

        long s1 = modPow(alpha, K, q);
        long kInv = modInverse(K, q - 1);
        long diff = (m - (X_A % (q - 1)) * (s1 % (q - 1))) % (q - 1);
        if (diff < 0) diff += (q - 1);
        long s2 = (kInv * diff) % (q - 1);

        return new Signature(s1, s2);
    }

    public boolean verify(String message, Signature signature) {
        return verify(message, signature, this.q, this.alpha, this.publicKeyA);
    }

    public static boolean verify(String message, Signature signature, long q, long alpha, long Y_A) {
        long s1 = signature.getS1();
        long s2 = signature.getS2();
        if (s1 <= 0 || s1 >= q) return false;

        long m = hashMessage(message, q);
        long v1 = modPow(alpha, m, q);
        long v2 = (modPow(Y_A, s1, q) * modPow(s1, s2, q)) % q;

        return v1 == v2;
    }

    public static void main(String[] args) {
        long q = 9999991;
        long alpha = 22;
        System.out.println("Prime q: " + q + ", Primitive root alpha: " + alpha);
        ElGamal elGamal = new ElGamal(q, alpha);
        System.out.println("Private key (X_A): " + elGamal.getPrivateKeyA());
        System.out.println("Public key (Y_A): " + elGamal.getPublicKeyA());
        System.out.println();

        String message = "Hello World";
        System.out.println("Message: " + message);

        Signature signature = elGamal.sign(message);
        System.out.println("Signature: S1=" + signature.getS1() + ", S2=" + signature.getS2());
        System.out.println();

        System.out.println("Signature validity: " + elGamal.verify(message, signature));
        System.out.println();


        String tampered = "Hello World!";
        System.out.println("Tampered message: " + tampered);
        System.out.println("Signature validity: " + elGamal.verify(tampered, signature));
    }
}
/*
Prime q: 9999991, Primitive root alpha: 22
Private key (X_A): 2640997
Public key (Y_A): 3158813

Message: Hello World
Signature: S1=2208387, S2=480563

Signature validity: true

Tampered message: Hello World!
Signature validity: false
 */