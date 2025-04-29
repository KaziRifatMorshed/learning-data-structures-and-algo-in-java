package Algorithms.DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class AllPairShortestPath {
    static int n = 5 + 1;
    static int[][] cost = new int[n][n];
    static int[][] A = new int[n][n];

    static void initCost() {
        for (int i = 0; i < cost.length; i++)
            for (int j = 0; j < cost[i].length; j++)
                if (i == j) cost[i][j] = 0;
                else if (i == 0) cost[i][j] = j;
                else if (j == 0) cost[i][j] = i;
                else
                    cost[i][j] = 999;
    }

    static void readFromFile(String path) throws FileNotFoundException {
        initCost();
        Scanner scanner = new Scanner(new File(path));
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

    static void AllPathShortestPaths() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = cost[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
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
        AllPathShortestPaths();
        for (int[] row : A)
            System.out.println(Arrays.toString(row));
    }
}
