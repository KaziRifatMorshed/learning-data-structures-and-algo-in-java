package InformationSystemLab.lab1;

import java.util.LinkedHashSet;
import java.util.*;

public class PlayFairCipher {

    static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }
    }

    private String keyword;
    private char[][] matrix = new char[5][5];

    public PlayFairCipher(String keyword) {
        this.keyword = keyword.toUpperCase();
        Set<Character> usedChars = new LinkedHashSet<>();
        for (Character c : this.keyword.toCharArray()) usedChars.add(c);
        for (char c = 'A'; c <= 'Z'; c++) if (c != 'J') usedChars.add(c);

        int i = 0;
        for (char c : usedChars) {
            matrix[i / 5][i % 5] = c;
            i++;
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public void printMatrix() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    private Pair<Integer, Integer> findIdx(char c) {
        for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) if (matrix[i][j] == c) return new Pair<>(i, j);
        return null;
    }

    public String encode(String inputMsg) {
        String encodedMsg = "";
        inputMsg = inputMsg.toUpperCase();
        inputMsg = inputMsg.replaceAll(" ", "");
        int inputMsgLen = inputMsg.length();
        StringBuilder newInputMsg = new StringBuilder();

        int i = 0;
        while (i < inputMsgLen) {
            if (i == (inputMsgLen - 1)) {
                newInputMsg.append(inputMsg.charAt(i)).append("X");
                i++;
            }
            else if (inputMsg.charAt(i) == inputMsg.charAt(i + 1)) {
                newInputMsg.append(inputMsg.charAt(i)).append("X");
                i++;
            } else {
                newInputMsg.append(inputMsg.charAt(i)).append(inputMsg.charAt(i + 1));
                i += 2;
            }
        }


        encodedMsg = new String(newInputMsg);
        return encodedMsg;
    }

    public String decode(String inputMsg) {
        String decodedMsg = "";

        return decodedMsg;
    }

    static void main() {
        PlayFairCipher playFairCipher = new PlayFairCipher("COMPUTER");
        playFairCipher.printMatrix();

        String msg = "TREE IS GREENQ";
        String encoded = playFairCipher.encode(msg);
        System.out.println(encoded);

    }
}
