package InformationSystemLab.lab1;

import java.io.File;
import java.io.FileNotFoundException;
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

    private String keyword, msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
        if (c == 'J') return findIdx('I');
        for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) if (matrix[i][j] == c) return new Pair<>(i, j);
        return null;
    }

    private Pair<Character, Character> newCharSet(Pair<Integer, Integer> firstLocation,
                                                  Pair<Integer, Integer> secondLocation,
                                                  String direction) {
        int r1 = firstLocation.getFirst(), c1 = firstLocation.getSecond();
        int r2 = secondLocation.getFirst(), c2 = secondLocation.getSecond();
        char f, s;

        if (direction.equals("right")) {
            f = matrix[r1][(c1 + 1) % 5];
            s = matrix[r2][(c2 + 1) % 5];
        } else if (direction.equals("left")) {
            f = matrix[r1][(c1 + 4) % 5];
            s = matrix[r2][(c2 + 4) % 5];
        } else if (direction.equals("down")) {
            f = matrix[(r1 + 1) % 5][c1];
            s = matrix[(r2 + 1) % 5][c2];
        } else if (direction.equals("up")) {
            f = matrix[(r1 + 4) % 5][c1];
            s = matrix[(r2 + 4) % 5][c2];
        } else { // diagonal
            f = matrix[r1][c2];
            s = matrix[r2][c1];
        }
        return new Pair<>(f, s);
    }

    public String encode(String inputMsg) {
        StringBuilder encodedMsg = new StringBuilder();
        inputMsg = inputMsg.toUpperCase();
        inputMsg = inputMsg.replaceAll(" ", "");
        int inputMsgLen = inputMsg.length();
        StringBuilder newInputMsg = new StringBuilder();

        int i = 0;
        while (i < inputMsgLen) {
            char firstChar = inputMsg.charAt(i);
            if (i == (inputMsgLen - 1)) {
                newInputMsg.append(firstChar).append("X");
                i++;
            } else {
                char secondChar = inputMsg.charAt(i + 1);
                if (firstChar == secondChar) {
                    newInputMsg.append(firstChar).append("X");
                    i++;
                } else {
                    newInputMsg.append(firstChar).append(secondChar);
                    i += 2;
                }
            }
        }

        i = 0;
        int newInputMsgLen = newInputMsg.length(); // this must be even

        while (i < newInputMsgLen) {
            Pair<Integer, Integer> firstCharLocation = findIdx(newInputMsg.charAt(i));
            Pair<Integer, Integer> secondCharLocation = findIdx(newInputMsg.charAt(i + 1));
            int r1 = firstCharLocation.getFirst(), c1 = firstCharLocation.getSecond();
            int r2 = secondCharLocation.getFirst(), c2 = secondCharLocation.getSecond();

            Pair<Character, Character> newChar = null;
            // case 1: row same: right
            if (r1 == r2) {
                newChar = newCharSet(firstCharLocation, secondCharLocation, "right");
            }
            // case 2: col same: left
            else if (c1 == c2) {
                newChar = newCharSet(firstCharLocation, secondCharLocation, "down");
            }
            // else: diagonal: other pair
            else {
                newChar = newCharSet(firstCharLocation, secondCharLocation, "diagonal");
            }
            encodedMsg.append(newChar.getFirst()).append(newChar.getSecond());
            i += 2;
        }
        return new String(encodedMsg);
    }

    public String decode(String inputMsg) {
        StringBuilder decodedMsg = new StringBuilder();
        inputMsg = inputMsg.toUpperCase();
        inputMsg = inputMsg.replaceAll(" ", "");

        int i = 0;
        int inputMsgLen = inputMsg.length(); // this must be even

        while (i < inputMsgLen) {
            Pair<Integer, Integer> firstCharLocation = findIdx(inputMsg.charAt(i));
            Pair<Integer, Integer> secondCharLocation = findIdx(inputMsg.charAt(i + 1));
            int r1 = firstCharLocation.getFirst(), c1 = firstCharLocation.getSecond();
            int r2 = secondCharLocation.getFirst(), c2 = secondCharLocation.getSecond();

            Pair<Character, Character> newChar = null;
            // case 1: row same: right
            if (r1 == r2) {
                newChar = newCharSet(firstCharLocation, secondCharLocation, "left");
            }
            // case 2: col same: left
            else if (c1 == c2) {
                newChar = newCharSet(firstCharLocation, secondCharLocation, "up");
            }
            // else: diagonal: other pair
            else {
                newChar = newCharSet(firstCharLocation, secondCharLocation, "diagonal");
            }
            decodedMsg.append(newChar.getFirst()).append(newChar.getSecond());
            i += 2;
        }
        return new String(decodedMsg);
    }

    void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
//            this.keyword = scanner.nextLine();
            this.msg = scanner.nextLine();
        }
    }

    static void main() throws FileNotFoundException {
        PlayFairCipher playFairCipher = new PlayFairCipher("COMPUTER");
        playFairCipher.printMatrix();
        playFairCipher.readFromFile("InformationSystemLab/lab1/input.txt");

        String encoded = playFairCipher.encode(playFairCipher.getMsg());
        System.out.println("Encoded: " + encoded);
        String decoded = playFairCipher.decode(encoded);
        System.out.println("Decoded: " + decoded);
    }
}
/*
CLI Output:

C O M P U
T E R A B
D F G H I
K L N Q S
V W X Y Z
Encoded: EARWBFNIARRL
Decoded: TREXEISGREEN
*/