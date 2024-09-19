package DataStracture.Array;

public class TicTacToe {
    private static final int X = -1, O = 1, EMPTY = 0;

    private int[][] board = new int[3][3];
    private int current_player;

    public TicTacToe() {
        clearBoard();
    }

    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
        current_player = X; //  X will start game
    }

    private void switchPlayer() {
        current_player = -current_player;
    }

    public void putMark(int i, int j) {
        if (i < 0 || i >= 3 || j < 0 || j >= 3) {
            throw new IllegalArgumentException("Invalid Board Position");
        }
        if (board[i][j] != EMPTY) {
            throw new IllegalArgumentException(STR."Board position (\{i},\{j}) is already taken! (index started from zero)");
        }
        board[i][j] = current_player;
        switchPlayer();
    }

    private boolean isWin(int identifier) {
        return (board[0][0] + board[0][1] + board[0][2] == 3 * identifier) || // ROW 0
                (board[1][0] + board[1][1] + board[1][2] == 3 * identifier) || // ROW 1
                (board[2][0] + board[2][1] + board[2][2] == 3 * identifier) || // ROW 2

                (board[0][0] + board[1][0] + board[2][0] == 3 * identifier) || // COL 0
                (board[0][1] + board[1][1] + board[2][1] == 3 * identifier) || // COL 1
                (board[0][2] + board[1][2] + board[2][2] == 3 * identifier) || // COL 2

                (board[0][0] + board[1][1] + board[2][2] == 3 * identifier) || // DIA \
                (board[2][0] + board[1][1] + board[0][2] == 3 * identifier) // DIA /
                ;
    }

    public int winner() {
        if (isWin(X)) {
            return X;
        } else if (isWin(O)) {
            return O;
        } else {
            return EMPTY;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case X: {
                        stringBuilder.append("X");
                        break;
                    }
                    case O: {
                        stringBuilder.append("O");
                        break;
                    }
                    case EMPTY: {
                        stringBuilder.append(" ");
                        break;
                    }
                }
                if (j < 2) stringBuilder.append("|");
            }
            if (i < 2) stringBuilder.append("\n");
//            if (i < 2) stringBuilder.append("\n------\n");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        game.putMark(1, 1); // x
        game.putMark(0, 2); // o
        game.putMark(2, 2); // x
        game.putMark(0, 0); // o
        game.putMark(0, 1); // x
        game.putMark(2, 1); // o
        game.putMark(1, 2); // x
        game.putMark(1, 0); // o
        game.putMark(2, 0); // x

        System.out.println(game);

        String[] outcome = {"O wins", "Tie", "X wins"};
        System.out.println(STR."Result: \{outcome[1 + game.winner()]}");
    }
} // DONE
