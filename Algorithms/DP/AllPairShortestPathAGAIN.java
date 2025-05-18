package Algorithms.DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class AllPairShortestPathAGAIN {
    static int INF = 999;
    static int n = 3 + 1;
    static int[][] cost = new int[n][n];
    static int[][] A = new int[n][n];

    static int[][] next = new int[n][n];
    static int MAX_STEPS = n * n;


    static void initCost() {
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost.length; j++) {
                if (i == j) cost[i][j] = 0;
                else if (i == 0) cost[i][j] = j;
                else if (j == 0) cost[i][j] = i;
                else cost[i][j] = INF;
            }
        }
    } // should work

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

    // Reconstruct all shortest path from u to v
    static String getPath(int u, int v) {
        if (next[u][v] == -1) return "no paths.";
        StringBuilder path = new StringBuilder();
        path.append(u);
        int steps = 0;
        while (u != v) {
            u = next[u][v];
            steps++;
            if (u == -1 || steps > MAX_STEPS) return "no paths.";
            path.append(" -> ").append(u);
        }
        return path.toString();
    }

    static void AllPairShortestPaths() {
        // init A and next
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = cost[i][j];

                if (cost[i][j] == INF || i == j) { // condition kheyal koro
                    next[i][j] = -1; // jaoa possible na
                } else {
                    next[i][j] = j;
                }
            }
        }

        for (int k = 1; k < n; k++) {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (A[i][k] + A[k][j] < A[i][j]) {
                        A[i][j] = A[i][k] + A[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFromFile("./Algorithms/DP/AllPairShortestPath.txt");
        AllPairShortestPaths();
        System.out.println("Distance Matrix:");
        for (int[] row : A) System.out.println(Arrays.toString(row));

        System.out.println("\nAll paths:");
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i != j) {
                    String path = getPath(i, j);
                    String c = (A[i][j] == INF) ? "INF" : Integer.toString(A[i][j]);
                    System.out.println("Shortest path from " + i + " to " + j + " : " + path + " (Cost: " + c + ")");
                }
            }
        }
    }
} // DONE