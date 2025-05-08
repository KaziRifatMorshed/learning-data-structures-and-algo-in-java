package Algorithms.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class nQueens {
    private Integer[] x;
    private int n;
    private final ArrayList<Integer[]> solutions = new ArrayList<>();

    nQueens(int n) {
        this.n = n;
        x = new Integer[n];
        Arrays.fill(x, null);
    }

    boolean Place(int ckRow, int ckColumn) { // checker
        for (int traverse_x = 0; traverse_x < ckRow; traverse_x++) {
            if (x[traverse_x] != null) {
                if ((x[traverse_x] == ckColumn) ||
                        (Math.abs(x[traverse_x] - ckColumn) == Math.abs(traverse_x - ckRow))
                ) {
                    return false;
                }
            }
        }
        return true;
    }

    void solve_nQueens(int row) {
        for (int column = 0; column < n; column++) {
            if (Place(row, column)) {
                x[row] = column;
                if (row == n - 1) { // ?
                    solutions.add(x.clone());
//                    print_nQueens();
                }
            } else {
                solve_nQueens(row + 1);
            }
            x[row] = null;
        }
    }

    void solve() {
        solve_nQueens(0);
    }

    void print_nQueens() {
//        System.out.println(Arrays.toString(x));
        Arrays.toString(x);
    }

    void printSolutions() {
        System.out.println(solutions.size());
        for (Integer[] arrayList : solutions) {
            System.out.println(Arrays.toString(arrayList));
        }
    }

    public static void main(String[] args) {
        nQueens nQueens = new nQueens(4);
        nQueens.solve();
        nQueens.printSolutions();
    }

}
