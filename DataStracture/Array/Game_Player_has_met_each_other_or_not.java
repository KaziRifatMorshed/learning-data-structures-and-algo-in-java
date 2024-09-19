package DataStracture.Array;

class Game_Player_has_met_each_other_or_not {
    public static void main(String[] args) {
    }
}

class ForestGame {
    private int player_count = 0;
    private boolean[][] has_met = null;

    ForestGame(int player_count) {
        this.player_count = player_count;
        has_met = new boolean[player_count][player_count];
        // i want to set all values to false
    }

    void meet(int i, int j) {
        has_met[i][j] = true;
        has_met[j][i] = true;
    }

    boolean has_met(int i, int j) {
        return has_met[i][j];
    }
} // done
