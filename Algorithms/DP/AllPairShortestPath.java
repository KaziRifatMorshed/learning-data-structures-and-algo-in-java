package Algorithms.DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class AllPairShortestPath {
    static int n = 3 + 1;
    static int[][] cost = new int[n][n];
    static int[][] A = new int[n][n];

    static void initCost() {
        for (int i = 0; i < cost.length; i++)
            for (int j = 0; j < cost[i].length; j++)
                if (i == j) cost[i][j] = 0;
                else if (i == 0) cost[i][j] = j; // CAREFUL
                else if (j == 0) cost[i][j] = i; // CAREFUL
                else
                    cost[i][j] = 999;
    }

    static void readFromFile(String path) throws FileNotFoundException {
        initCost();
        Scanner scanner = new Scanner(new File(path));
//        n += Integer.parseInt(scanner.nextLine());
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Scanner scanner1 = new Scanner(s);
            int a = scanner1.nextInt();
            int b = scanner1.nextInt();
            int w = scanner1.nextInt();
            if (a == b && b == w && w == 0) return;
            cost[a][b] = w;
        }
    }

    static void AllPairShortestPaths() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = cost[i][j];
            }
        }

        for (int k = 1; k < n; k++) { // careful // first row, first column useless
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    A[i][j] = Math.min(
                            A[i][j],
                            A[i][k] + A[k][j]
                    );
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFromFile("./Algorithms/DP/AllPairShortestPath.txt");
        AllPairShortestPaths();
        for (int[] row : A)
            System.out.println(Arrays.toString(row));
    }
}

/*
[0, 1, 2, 3, 4, 5]
[1, 0, 3, 4, 5, 6]
[2, 3, 0, 5, 4, 5]
[3, 3, 5, 0, 7, 8]
[4, 5, 6, 5, 0, 1]
[5, 6, 7, 8, 9, 0]
 */
