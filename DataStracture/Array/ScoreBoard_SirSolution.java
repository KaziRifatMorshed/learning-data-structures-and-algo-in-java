package DataStracture.Array;

import java.security.PrivateKey;
import java.util.Scanner;

// Sir Solution

class Game_Entry {
    private String name;
    private int score;

    public Game_Entry(String n, int s) {
        name = n;
        score = s;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return "(" + name + ", " + score + ")";
    }
}

class Scoreboard {
    private int numEntries = 0;
    private Game_Entry[] board;

    public Scoreboard(int capacity) {
        board = new Game_Entry[capacity];
    }

    public void add(Game_Entry e) {
        int newScore = e.getScore();
        if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
            if (numEntries < board.length) {
                numEntries++;
            }
            int j = numEntries - 1;
            while (j > 0 && board[j - 1].getScore() < newScore) {
                board[j] = board[j - 1];
                j--;
            }
            board[j] = e;
        }
    }

    public Game_Entry remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= numEntries) {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
        Game_Entry temp = board[i];
        for (int j = i; j < numEntries - 1; j++) {
            board[j] = board[j + 1];
        }
        board[numEntries - 1] = null;
        numEntries--;
        return temp;
    }

    public void show() {
        for (int i = 0; i < numEntries; i++) {
            System.out.println(i + 1 + ". Name: " + board[i].getName() + ", Score: " + board[i].getScore());
        }
    }
}

class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scoreboard scoreboard = new Scoreboard(3);
        Game_Entry Game_Entry;
        String name;
        int score;
        System.out.println("Game Start");
        while (true) {
            System.out.println("Enter name (without space) and score (enter over to stop the game)");
            name = sc.next(); // careful
            if (name.equals("over"))
                break;
            score = sc.nextInt();
            Game_Entry = new Game_Entry(name, score);
            scoreboard.add(Game_Entry);
            System.out.println("Current scoreboard");
            scoreboard.show();
            System.out.println();
        }
        System.out.println("Game over");
        sc.close();
    }

}