package DS_Lab_tests;


import java.util.Scanner;


class gameEntry {
    private String name;
    private int score;

    public gameEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return STR."\{name} \{score}";
    }
}

class ScoreBoard {
    private int numEntry = 0, board_len = 0;
    private gameEntry[] board;

    public ScoreBoard(int board_len) {
        this.board_len = board_len;
        board = new gameEntry[board_len];
    }

    void add_to_board(gameEntry e) {
        int newScore = e.getScore();
        if (numEntry < board.length || newScore > board[numEntry - 1].getScore()) {
            if (numEntry < board.length) {
                this.numEntry++;
            }
            int j = numEntry - 1;
            for (; j > 0 && board[j - 1].getScore() < newScore; j--) {
                board[j] = board[j - 1];
            }
            board[j] = e;
        }
    }

    gameEntry remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > board.length) {
            throw new IndexOutOfBoundsException(STR."Invalid Index \{index} has been inputted");
        }
        gameEntry to_bre_returned = board[index];
        int i = index;
        for (; i < numEntry - 1; i++) { // j < numEntries - 1
            board[i] = board[i + 1];
        }
        board[numEntry - 1] = null;
        numEntry--;
        return to_bre_returned;
    }

    void disqualify(String s) {
        for (int i = 0; i < board_len; i++) {
            if (board[i] != null) {
                if (s.equals(board[i].getName())) {
                    remove(i);
                    i = -1; // after incrementing it will become 0
                }
            }
        }
    } // done

    void showScoreboard() {
        System.out.println();
        for (int i = 0; i < numEntry; i++) {
            System.out.println(this.board[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ScoreBoard board = new ScoreBoard(50);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String name = scanner.next();
            if (name.equals("over")) {
                break;
            }
            if (name.equals("disqualify")) {
                String disqualified_name = scanner.next();
                board.disqualify(disqualified_name);
                board.showScoreboard();
                continue;
            }
            int score = scanner.nextInt();
            gameEntry n = new gameEntry(name, score);
            board.add_to_board(n);
            board.showScoreboard();
        }
    } // done
}
