package InformationSystemLab.lab4;

import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {
    private BigInteger p;             // Public prime number
    private BigInteger g;             // Public primitive root (generator)
    private BigInteger privateKeyA;   // Alice's private key (a)
    private BigInteger privateKeyB;   // Bob's private key (b)
    private BigInteger publicKeyA;    // Alice's public key (A = g^a mod p)
    private BigInteger publicKeyB;    // Bob's public key (B = g^b mod p)
    private BigInteger secretKeyA;    // Alice's computed shared secret key (B^a mod p)
    private BigInteger secretKeyB;    // Bob's computed shared secret key (A^b mod p)
    private BigInteger sharedKey;     // Agreed shared secret key (K)

    public DiffieHellman(long p, long g, long privateKeyA, long privateKeyB) {
        this(BigInteger.valueOf(p), BigInteger.valueOf(g),
                BigInteger.valueOf(privateKeyA), BigInteger.valueOf(privateKeyB));
    }

    public DiffieHellman(BigInteger p, BigInteger g, BigInteger privateKeyA, BigInteger privateKeyB) {
        this.p = p;
        this.g = g;
        this.privateKeyA = privateKeyA;
        this.privateKeyB = privateKeyB;

        // Generate public keys: A = g^a mod p, B = g^b mod p
        this.publicKeyA = modPow(this.g, this.privateKeyA, this.p);
        this.publicKeyB = modPow(this.g, this.privateKeyB, this.p);

        // Compute shared secret key: K_A = B^a mod p, K_B = A^b mod p
        this.secretKeyA = modPow(this.publicKeyB, this.privateKeyA, this.p);
        this.secretKeyB = modPow(this.publicKeyA, this.privateKeyB, this.p);

        // Both shared secrets must match in Diffie-Hellman
        if (!this.secretKeyA.equals(this.secretKeyB)) {
            throw new IllegalStateException("Shared secret keys do not match!");
        }

        this.sharedKey = this.secretKeyA;
    }

    // Modular exponentiation: (base^exponent) mod modulus
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

    // Modular multiplicative inverse using Extended Euclidean Algorithm
    private BigInteger calculateInverse(BigInteger key, BigInteger modulus) {
        BigInteger t = BigInteger.ZERO;
        BigInteger newT = BigInteger.ONE;
        BigInteger r = modulus;
        BigInteger newR = key;

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
            t = t.add(modulus);
        }
        return t;
    }

    // Primary encryption (ElGamal / Multiplicative Cipher mod p): C = (M * K) mod p
    public BigInteger encrypt(BigInteger message) {
        return message.multiply(this.sharedKey).mod(this.p);
    }

    // Primary decryption (ElGamal / Multiplicative Cipher mod p): M = (C * K^(-1)) mod p
    public BigInteger decrypt(BigInteger cipherText) {
        BigInteger keyInverse = calculateInverse(this.sharedKey, this.p);
        return cipherText.multiply(keyInverse).mod(this.p);
    }

    // Additive encryption (Caesar-cipher style mod p): C = (M + K) mod p
    public BigInteger encryptAdditive(BigInteger message) {
        return message.add(this.sharedKey).mod(this.p);
    }

    // Additive decryption (Caesar-cipher style mod p): M = (C - K + p) mod p
    public BigInteger decryptAdditive(BigInteger cipherText) {
        return cipherText.subtract(this.sharedKey).mod(this.p).add(this.p).mod(this.p);
    }

    // Convenience overloads for long primitives
    public long encrypt(long message) {
        return encrypt(BigInteger.valueOf(message)).longValue();
    }

    public long decrypt(long cipherText) {
        return decrypt(BigInteger.valueOf(cipherText)).longValue();
    }

    // Getters
    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getPrivateKeyA() {
        return privateKeyA;
    }

    public BigInteger getPrivateKeyB() {
        return privateKeyB;
    }

    public BigInteger getPublicKeyA() {
        return publicKeyA;
    }

    public BigInteger getPublicKeyB() {
        return publicKeyB;
    }

    public BigInteger getSecretKeyA() {
        return secretKeyA;
    }

    public BigInteger getSecretKeyB() {
        return secretKeyB;
    }

    public BigInteger getSharedKey() {
        return sharedKey;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter prime number (p): ");
        BigInteger p = scanner.nextBigInteger();

        System.out.print("Enter primitive root (g): ");
        BigInteger g = scanner.nextBigInteger();

        System.out.print("Enter Alice's private key (a): ");
        BigInteger a = scanner.nextBigInteger();

        System.out.print("Enter Bob's private key (b): ");
        BigInteger b = scanner.nextBigInteger();

        DiffieHellman diffieHellman = new DiffieHellman(p, g, a, b);

        System.out.println("Alice's Public Key (A) : " + diffieHellman.getPublicKeyA());
        System.out.println("Bob's Public Key (B)   : " + diffieHellman.getPublicKeyB());
        System.out.println("Shared Secret Key (K)  : " + diffieHellman.getSharedKey());

        System.out.print("Enter an input number: ");
        BigInteger user_input = scanner.nextBigInteger();

        System.out.println("Original user inputted number : " + user_input);
        BigInteger encryptedNumber = diffieHellman.encrypt(user_input);
        System.out.println("After encryption, the number is : " + encryptedNumber);
        BigInteger decryptedNumber = diffieHellman.decrypt(encryptedNumber);
        System.out.println("After decryption, the number is : " + decryptedNumber);
    }
} // DONE

/*
CLI Input and Output

Enter prime number (p): 23
Enter primitive root (g): 5
Enter Alice's private key (a): 4
Enter Bob's private key (b): 3
Alice's Public Key (A) : 4
Bob's Public Key (B)   : 10
Shared Secret Key (K)  : 18
Enter an input number: 15
Original user inputted number : 15
After encryption, the number is : 17
After decryption, the number is : 15
*/
