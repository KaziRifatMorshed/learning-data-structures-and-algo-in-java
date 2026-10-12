package InformationSecurityLab.lab6;

import java.nio.charset.StandardCharsets;

public class SHA512 {

    private static final long[] INITIAL_H = {
            0x6a09e667f3bcc908L,
            0xbb67ae8584caa73bL,
            0x3c6ef372fe94f82bL,
            0xa54ff53a5f1d36f1L,
            0x510e527fade682d1L,
            0x9b05688c2b3e6c1fL,
            0x1f83d9abfb41bd6bL,
            0x5be0cd19137e2179L
    };

    private static final long[] K = {
            0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL,
            0x3956c25bf348b538L, 0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L,
            0xd807aa98a3030242L, 0x12835b0145706fbeL, 0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L,
            0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L, 0xc19bf174cf692694L,
            0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L,
            0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L,
            0x983e5152ee66dfabL, 0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L,
            0xc6e00bf33da88fc2L, 0xd5a79147930aa725L, 0x06ca6351e003826fL, 0x142929670a0e6e70L,
            0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL, 0x53380d139d95b3dfL,
            0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL,
            0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L,
            0xd192e819d6ef5218L, 0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L,
            0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L, 0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L,
            0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L, 0x682e6ff3d6b2b8a3L,
            0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL,
            0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL,
            0xca273eceea26619cL, 0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L,
            0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L, 0x113f9804bef90daeL, 0x1b710b35131c471bL,
            0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL, 0x431d67c49c100d4cL,
            0x4cc5d4becb3e42b6L, 0x597f299cfc657e2aL, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L
    };

    private static long rotr(long x, int n) {
        return (x >>> n) | (x << (64 - n));
    }

    private static long shr(long x, int n) {
        return x >>> n;
    }

    private static long ch(long x, long y, long z) {
        return (x & y) ^ (~x & z);
    }

    private static long maj(long x, long y, long z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    private static long Sigma0(long x) {
        return rotr(x, 28) ^ rotr(x, 34) ^ rotr(x, 39);
    }

    private static long Sigma1(long x) {
        return rotr(x, 14) ^ rotr(x, 18) ^ rotr(x, 41);
    }

    private static long sigma0(long x) {
        return rotr(x, 1) ^ rotr(x, 8) ^ shr(x, 7);
    }

    private static long sigma1(long x) {
        return rotr(x, 19) ^ rotr(x, 61) ^ shr(x, 6);
    }

    private static long bytesToLong(byte[] data, int offset) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value = (value << 8) | (data[offset + i] & 0xFFL);
        }
        return value;
    }

    private static byte[] padMessage(byte[] message) {
        int length = message.length;
        int totalLength = length + 1 + 16;
        int remainder = totalLength % 128;
        int paddedLength = (remainder == 0) ? totalLength : totalLength + (128 - remainder);

        byte[] padded = new byte[paddedLength];
        System.arraycopy(message, 0, padded, 0, length);

        padded[length] = (byte) 0x80;
        long bitLength = (long) length * 8;
        for (int i = 0; i < 8; i++) {
            padded[paddedLength - 1 - i] = (byte) (bitLength >>> (8 * i));
        }
        return padded;
    }

    public static String hash(String message) {
        return hash(message.getBytes(StandardCharsets.UTF_8));
    }

    public static String hash(byte[] message) {
        byte[] padded = padMessage(message);

        long[] H = new long[8];
        System.arraycopy(INITIAL_H, 0, H, 0, 8);

        int totalBlocks = padded.length / 128;
        long[] W = new long[80];

        for (int block = 0; block < totalBlocks; block++) {
            int blockOffset = block * 128;

            for (int t = 0; t < 16; t++) {
                W[t] = bytesToLong(padded, blockOffset + (t * 8));
            }

            for (int t = 16; t < 80; t++) {
                W[t] = sigma1(W[t - 2]) + W[t - 7] + sigma0(W[t - 15]) + W[t - 16];
            }

            long a = H[0];
            long b = H[1];
            long c = H[2];
            long d = H[3];
            long e = H[4];
            long f = H[5];
            long g = H[6];
            long h = H[7];

            for (int t = 0; t < 80; t++) {
                long T1 = h + Sigma1(e) + ch(e, f, g) + K[t] + W[t];
                long T2 = Sigma0(a) + maj(a, b, c);

                h = g;
                g = f;
                f = e;
                e = d + T1;
                d = c;
                c = b;
                b = a;
                a = T1 + T2;
            }

            H[0] += a;
            H[1] += b;
            H[2] += c;
            H[3] += d;
            H[4] += e;
            H[5] += f;
            H[6] += g;
            H[7] += h;
        }

        StringBuilder hexString = new StringBuilder();
        for (long val : H) {
            hexString.append(String.format("%016x", val));
        }

        return hexString.toString();
    }


    public static void main(String[] args) {
        String msg1 = "Rifat";
        System.out.println("Message: " + msg1);
        System.out.println("SHA-512: " + hash(msg1));
        System.out.println();

        String msg2 = "rifat";
        System.out.println("Tampered Message: " + msg2);
        System.out.println("SHA-512: " + hash(msg2));
        System.out.println();
    }
}
/*
Message: Rifat
SHA-512: fedab50babdfb8d8cb5b524721e0fbd83c9d619dc247fb75830bc9af8d61bb458f3e080864833f24cbff30aa0a09e3bfc1cc6689c120ed6e1e5d046dde082197

Tampered Message: rifat
SHA-512: 420150f5a9b240617990a8ad2ad90ad240c92d10202bd67962c47c8a548d8364b2deb1dbe987d533945932eed5dd319e3538c1c644ca8062c675859de735d22f
*/
