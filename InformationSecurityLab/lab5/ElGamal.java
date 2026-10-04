package InformationSecurityLab.lab5;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

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

        @Override
        public String toString() {
            return "Signature: S1=" + s1 + ", S2=" + s2;
        }
    }

    private final long q;
    private final long alpha;
    private final long privateKeyA; // X_A
    private final long publicKeyA;  // Y_A

    public ElGamal(long q, long alpha) {
        this.q = q;
        this.alpha = alpha;
        long[] keys = generateKeys(q, alpha);
        this.privateKeyA = keys[0];
        this.publicKeyA = keys[1];
    }

    public ElGamal(long q) {
        this(q, findPrimitiveRoot(q));
    }

    public ElGamal(long q, long alpha, long privateKeyA) {
        this.q = q;
        this.alpha = alpha;
        this.privateKeyA = privateKeyA;
        this.publicKeyA = modPow(alpha, privateKeyA, q);
    }

    public long getQ() {
        return q;
    }

    public long getAlpha() {
        return alpha;
    }

    public long getPrivateKeyA() {
        return privateKeyA;
    }

    public long getPublicKeyA() {
        return publicKeyA;
    }

    // Extended Euclidean algorithm modular inverse
    public static long modInverse(long a, long m) {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(m)).longValue();
    }

    // Modular exponentiation: (base^exp) mod mod
    public static long modPow(long base, long exp, long mod) {
        return BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).longValue();
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Generate ElGamal key pair: returns [privateKey (X_A), publicKey (Y_A)]
    public static long[] generateKeys(long q, long alpha) {
        long X_A = 2 + (long) (Math.random() * (q - 3));
        long Y_A = modPow(alpha, X_A, q);
        return new long[]{X_A, Y_A};
    }

    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static Set<Long> primeFactors(long n) {
        Set<Long> factors = new HashSet<>();
        while (n % 2 == 0) {
            factors.add(2L);
            n /= 2;
        }
        for (long f = 3; f * f <= n; f += 2) {
            while (n % f == 0) {
                factors.add(f);
                n /= f;
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

    public static boolean isPrimitiveRoot(long alpha, long q) {
        if (!isPrime(q)) return false;
        long phi = q - 1;
        for (long p : primeFactors(phi)) {
            if (modPow(alpha, phi / p, q) == 1) return false;
        }
        return true;
    }

    public static long findPrimitiveRoot(long q) {
        if (!isPrime(q)) throw new IllegalArgumentException("q must be prime");
        for (long alpha = 2; alpha < q; alpha++) {
            if (isPrimitiveRoot(alpha, q)) return alpha;
        }
        throw new IllegalArgumentException("No primitive root found");
    }

    private static long hashMessage(String message, long q) {
        long m = 0;
        for (char c : message.toCharArray()) m += c;
        m %= q;
        return m == 0 ? 1 : m;
    }

    // Sign a message using instance keys
    public Signature sign(String message) {
        return sign(message, this.privateKeyA, this.q, this.alpha);
    }

    // Sign a message (static)
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

    // Verify signature using instance parameters
    public boolean verify(String message, Signature signature) {
        return verify(message, signature, this.q, this.alpha, this.publicKeyA);
    }

    // Verify signature (static)
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
        long alpha = findPrimitiveRoot(q);

        System.out.println("ELGAMAL DIGITAL SIGNATURE");
        System.out.println("--------------------------------------------------");
        System.out.println("Prime q: " + q);
        System.out.println("Primitive root alpha: " + alpha);
        System.out.println();

        // Generate keys
        ElGamal elGamal = new ElGamal(q, alpha);
        System.out.println("Private key (X_A): " + elGamal.getPrivateKeyA());
        System.out.println("Public key (Y_A): " + elGamal.getPublicKeyA());
        System.out.println();

        // Sign a message
        String message = "Hello World";
        System.out.println("Message: " + message);

        Signature signature = elGamal.sign(message);
        System.out.println("Signature: S1=" + signature.getS1());
        System.out.println("           S2=" + signature.getS2());
        System.out.println();

        // Verify signature
        boolean valid = elGamal.verify(message, signature);
        System.out.println("Signature valid? " + valid);
        System.out.println();

        // Test with tampered message
        String tampered = "Hello World!";
        System.out.println("Tampered message: " + tampered);
        boolean tamperedValid = elGamal.verify(tampered, signature);
        System.out.println("Tampered signature valid? " + tamperedValid);
    }
}
