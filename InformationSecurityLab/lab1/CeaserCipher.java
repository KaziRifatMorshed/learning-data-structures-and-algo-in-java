package InformationSystemLab.lab1;

import java.util.Scanner;

public class CeaserCipher {
    private int round;
    private char[] encoder = new char[26], decoder = new char[26];

    public CeaserCipher(int rounding) {
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
            if (Character.isUpperCase(msg[i]))
                msg[i] = code[(int) (msg[i] - 'A')];
            else
                msg[i] = code[(int) (msg[i] - 'a')];
        }
        return new String(msg);
    }

    public static void main(String[] args) {
        CeaserCipher ceaserCypher = new CeaserCipher(3);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input a msg: ");
        String user_input = scanner.nextLine();

        System.out.println("Original user inputted msg : " + user_input);
        String encodedMsg = ceaserCypher.encode(user_input);
        System.out.println("After encryption, the msg is : " + encodedMsg);
        String decodedMsg = ceaserCypher.decode(encodedMsg);
        System.out.println("After decryption, the msg is : " + decodedMsg);
    }
} // DONE

/*
CLI Input and Output

Input a msg: AbCd
Original user inputted msg : AbCd
After encryption, the msg is : DEFG
After decryption, the msg is : ABCD
*/