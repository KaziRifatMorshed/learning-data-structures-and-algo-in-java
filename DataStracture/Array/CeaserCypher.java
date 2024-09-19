package DataStracture.Array;

import java.util.Scanner;

public class CeaserCypher {
    private int round;
    private char[] encoder = new char[26], decoder = new char[26];

    public CeaserCypher(int rounding) {
        this.round = rounding % 26;
        for (int i = 0; i < 26; i++) {
            encoder[i] = (char) (('A' + ((i + round) % 26)));
            decoder[i] = (char) (('A' + ((i - round + 26) % 26)));
        }
    }

    public String encode(String s) {
        return transform(s, encoder);
    }

    public String decode(String s) {
        return transform(s, decoder);
    }

    public String transform(String original, char[] code) {
        char[] msg = original.toCharArray();
        int len = msg.length;
        for (int i = 0; i < len; i++) {
//            int j = msg[i] - 'A';
//            msg[i] = code[j];
            if (Character.isUpperCase(msg[i]))
                msg[i] = code[(int) (msg[i] - 'A')];
        }
        return new String(msg);
    }

    public int getRound() {
        return round;
    }

    public char[] getEncoder() {
        return encoder;
    }

    public char[] getDecoder() {
        return decoder;
    }

    public static void main(String[] args) {
        CeaserCypher ceaserCypher = new CeaserCypher(3);
        Scanner scanner = new Scanner(System.in);
        String user_input = scanner.nextLine();

        System.out.println(STR."Encoder: \{new String(ceaserCypher.getEncoder())}"); // new String()
        System.out.println(STR."Decoder: \{new String(ceaserCypher.getDecoder())}");
        System.out.println(STR."Original user inputted msg : \{user_input}");
        System.out.println(STR."After encryption, the msg is : \{ceaserCypher.encode(user_input.toUpperCase())}");
    }
} // DONE

// Λορεμ