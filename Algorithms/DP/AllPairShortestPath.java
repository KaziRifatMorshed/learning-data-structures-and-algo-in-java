package Algorithms.DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class AllPairShortestPath {

    static int INF = 999;
    static int n = 3 + 1;
    static int[][] cost = new int[n][n];
    static int[][] A = new int[n][n];
    static int[][] next = new int[n][n]; // To reconstruct paths

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

    // Reconstruct shortest path from u to v
    static String getPath(int u, int v) {
        if (next[u][v] == -1) return "no path";
        StringBuilder path = new StringBuilder();
        path.append(u);
        // Prevent infinite loop if next[u][v] is corrupted or forms a cycle
        int steps = 0;
        final int MAX_STEPS = n * n; // reasonable bound for a graph of this size
        while (u != v) {
            u = next[u][v];
            steps++;
            if (u == -1 || steps > MAX_STEPS) return "no path";
            path.append(" -> ").append(u);
        }
        return path.toString();
    }

    static void AllPairShortestPaths() {
        // init A and next
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = cost[i][j];
                if (i == j || cost[i][j] == INF) {
                    next[i][j] = -1; // No path
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
                        next[i][j] = next[i][k]; // Update next step on path
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

        System.out.println("\nShortest paths between each pair of vertices:");
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i != j) {
                    System.out.print("Shortest path from " + i + " to " + j + ": ");
                    String path = getPath(i, j);
                    System.out.println(path + " (Cost: " + (A[i][j] == INF ? "INF" : A[i][j]) + ")");
                }
            }
        }
    }
}

/*
Distance Matrix:
[0, 1, 2, 3]
[1, 0, 4, 6]
[2, 5, 0, 2]
[3, 3, 7, 0]

Shortest paths between each pair of vertices:
Shortest path from 1 to 2: 1 -> 2 (Cost: 4)
Shortest path from 1 to 3: 1 -> 2 -> 3 (Cost: 6)
Shortest path from 2 to 1: 2 -> 3 -> 1 (Cost: 5)
Shortest path from 2 to 3: 2 -> 3 (Cost: 2)
Shortest path from 3 to 1: 3 -> 1 (Cost: 3)
Shortest path from 3 to 2: 3 -> 1 -> 2 (Cost: 7)
* */