package InformationSystemLab.lab1;

import java.math.BigInteger;

class RSA {
    private BigInteger p, q, n, phi, e, d;

    public RSA(long p, long q, long e) {
        this.p = BigInteger.valueOf(p);
        this.q = BigInteger.valueOf(q);
        this.e = BigInteger.valueOf(e);

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

}

class Main {
    public static void main(String[] args) {
        long p = 11, q = 13, e = 17;
        BigInteger message = BigInteger.valueOf(43);

        RSA rsa = new RSA(p, q, e);

        BigInteger encrypted = rsa.encrypt(message);
        System.out.println("Message: " + message);
        System.out.println("Encrypted: " + encrypted);

        BigInteger decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
