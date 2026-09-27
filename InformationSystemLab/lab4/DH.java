package InformationSystemLab.lab4;

import java.math.BigInteger;

public class DH {
    public static final int prime = 17, alpha = 5;

    static class Person {
        private int privateKey, secretKey;
        public int publicKey;

        public Person() {
            generatePrivateKey();
            calculatePublicKey();
        }

        private void generatePrivateKey() {
            this.privateKey = (int) (Math.random() * (prime - 1)) + 1;
            System.out.println("Private Key: " + this.privateKey);
        }

        private void calculatePublicKey() {
            this.publicKey = BigInteger.valueOf(alpha)
                    .modPow(BigInteger.valueOf(this.privateKey), BigInteger.valueOf(prime))
                    .intValue();
        }

        public void calculateSecretKey(int peerPublicKey) {
            this.secretKey = BigInteger.valueOf(peerPublicKey)
                    .modPow(BigInteger.valueOf(this.privateKey), BigInteger.valueOf(prime))
                    .intValue();
        }

        public int getPublicKey() {
            return this.publicKey;
        }

        public int getSecretKey() {
            return this.secretKey;
        }
    }

    public static void main(String[] args) {
        Person alice = new Person();
        Person bob = new Person();

        System.out.println("Public parameters: prime (q) = " + prime + ", alpha (g) = " + alpha);
        System.out.println("Alice's Public Key: " + alice.getPublicKey());
        System.out.println("Bob's Public Key: " + bob.getPublicKey());
        System.out.println("--------------------------------------------------");

        alice.calculateSecretKey(bob.getPublicKey());
        bob.calculateSecretKey(alice.getPublicKey());

        System.out.println("Alice's Computed Secret Key: " + alice.getSecretKey());
        System.out.println("Bob's Computed Secret Key:   " + bob.getSecretKey());

        if (alice.getSecretKey() == bob.getSecretKey()) {
            System.out.println("\nKey exchange successful! Shared secret key: " + alice.getSecretKey());
        } else {
            System.out.println("\nKey exchange failed.");
        }
    }
}