package InformationSystemLab.lab2;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA2 {
    private BigInteger p, q, n, phi, e, d;

    public RSA2() {
        this(512);
    }

    public RSA2(int bitLength) {
        SecureRandom random = new SecureRandom();

        // 1. Generate two distinct random prime numbers p and q
        do {
            this.p = BigInteger.probablePrime(bitLength / 2, random);
            this.q = BigInteger.probablePrime(bitLength / 2, random);
        } while (this.p.equals(this.q));

        // 2. Compute modulus n = p * q
        this.n = this.p.multiply(this.q);

        // 3. Compute Euler's totient function phi = (p - 1) * (q - 1)
        this.phi = this.p.subtract(BigInteger.ONE)
                .multiply(this.q.subtract(BigInteger.ONE));

        // 4. Choose public exponent e such that 1 < e < phi and gcd(e, phi) == 1
        do {
            this.e = BigInteger.probablePrime(bitLength / 2, random);
        } while (this.e.compareTo(BigInteger.ONE) <= 0
                || this.e.compareTo(this.phi) >= 0
                || !this.e.gcd(this.phi).equals(BigInteger.ONE));

        // 5. Calculate private exponent d = e^-1 mod phi
        this.d = calculatePrivateKey(this.e, this.phi);
    }

    // Constructor for explicit key specification (e.g. for testing)
    public RSA2(long p, long q, long e) {
        this(BigInteger.valueOf(p), BigInteger.valueOf(q), BigInteger.valueOf(e));
    }

    public RSA2(BigInteger p, BigInteger q, BigInteger e) {
        this.p = p;
        this.q = q;
        this.e = e;

        this.n = this.p.multiply(this.q);
        this.phi = this.p.subtract(BigInteger.ONE)
                .multiply(this.q.subtract(BigInteger.ONE));
        this.d = calculatePrivateKey(this.e, this.phi);
    }

    private BigInteger calculatePrivateKey(BigInteger e, BigInteger phi) {
        BigInteger t = BigInteger.ZERO;
        BigInteger newT = BigInteger.ONE;
        BigInteger r = phi;
        BigInteger newR = e;

        while (!newR.equals(BigInteger.ZERO)) {
            BigInteger quotient = r.divide(newR);

            BigInteger tempT = t.subtract(quotient.multiply(newT));
            t = newT;
            newT = tempT;

            BigInteger tempR = r.subtract(quotient.multiply(newR));
            r = newR;
            newR = tempR;
        }

        if (t.compareTo(BigInteger.ZERO) < 0) {
            t = t.add(phi);
        }
        return t;
    }

    public BigInteger encrypt(BigInteger message) {
        return modPow(message, this.e, this.n);
    }

    public BigInteger decrypt(BigInteger cipherText) {
        return modPow(cipherText, this.d, this.n);
    }

    private BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger modulus) {
        BigInteger result = BigInteger.ONE;
        base = base.mod(modulus);

        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.testBit(0)) {
                result = result.multiply(base).mod(modulus);
            }
            exponent = exponent.shiftRight(1);
            base = base.multiply(base).mod(modulus);
        }
        return result;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getPhi() {
        return phi;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }

    public static void main(String[] args) {
        RSA2 rsa = new RSA2(512);

        BigInteger message = BigInteger.valueOf(123456789);

        BigInteger encrypted = rsa.encrypt(message);
        System.out.println("Original Message : " + message);
        System.out.println("Encrypted Message: " + encrypted);

        BigInteger decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted Message: " + decrypted);
    }
}

