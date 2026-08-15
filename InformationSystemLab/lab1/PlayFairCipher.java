package InformationSystemLab.lab1;

import java.util.PriorityQueue;
import java.util.Queue;

public class PlayFairCipher {
    private String keyword;
    private char[][] matrix = new char[5][5];

    public PlayFairCipher(String keyword) {
        this.keyword = keyword.toUpperCase();
        Queue<Character> charQueue = new PriorityQueue<>();
        for (Character c : this.keyword.toCharArray()) {
//            System.out.println("added char: " + c);
            charQueue.add(c);
        }

        int i = 0;
        // first e keyword er sob char add korbo
        for (; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!charQueue.isEmpty()) matrix[i][j] = charQueue.remove();
                else break;
            }
            if (charQueue.isEmpty()) break;
        }

        //ebar keyword e na thaka char gulo add korbo
        int k = (int) 'A';
        for (; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyword.contains("" + k) || k == (int) 'J') continue;
                else matrix[i][j] = (char) k;
                k++;
            }
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

    static void main() {
        PlayFairCipher playFairCipher = new PlayFairCipher("COMPUTER");
        playFairCipher.printMatrix();

    }
}
