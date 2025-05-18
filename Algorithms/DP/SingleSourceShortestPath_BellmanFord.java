package Algorithms.DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class SingleSourceShortestPath_BellmanFord {

    static int INF = 999;
    static int n;
    static int[] dist;
    static int[][] cost;

    public SingleSourceShortestPath_BellmanFord(int nn) {
        n = nn;
        dist = new int[n];
        cost = new int[n][n];
    }

    static void initCost() {
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost.length; j++) {
                if (i == j) cost[i][j] = 0;
                else cost[i][j] = INF;
            }
        }
    } // should work

    void readFromFile(String path) throws FileNotFoundException {
        initCost();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Scanner scanner1 = new Scanner(s);
            int a = scanner1.nextInt();
            int b = scanner1.nextInt();
            int w = scanner1.nextInt();
            if (a == b && b == w && w == 0) return;
            cost[a - 1][b - 1] = w;
        }
    }

    /**
     * Runs Bellman-Ford algorithm on an adjacency matrix.
     *
     * @param cost The adjacency matrix (cost[i][j] is the cost from i to j, or INF if no edge)
     * @param v    The source vertex (0-indexed)
     * @return The shortest distance from v to each vertex; INF if unreachable.
     */

    int[] bellmanFord(int[][] cost, int v) {
        int n = cost.length;


        // Step 1: Initialize distances from source
        for (int i = 0; i < n; i++) {
            dist[i] = cost[v][i];
        }
        dist[v] = 0; // Distance to itself is 0

        // Step 2: Relax edges n-2 times
        for (int k = 2; k <= n - 1; k++) {
            for (int u = 0; u < n; u++) {
                if (u == v) continue;

                // Check if u has at least one incoming edge
                // u er incoming edge : onno node theke u te jabo
                boolean hasIncoming = false;
                for (int i = 0; i < n; i++) {
                    if (cost[i][u] < INF) {
                        hasIncoming = true;
                        break;
                    }
                }
                if (!hasIncoming) continue;

                // For each (i, u) where there's an edge
                for (int i = 0; i < n; i++) {
                    if (dist[i] < INF && cost[i][u] < INF) { // careful
                        if (dist[u] > dist[i] + cost[i][u]) { // dist[u]
                            dist[u] = dist[i] + cost[i][u];
                        }
                    }
                }
            }
        }

        return dist;
    }

    // Example usage
    public static void main(String[] args) throws FileNotFoundException {
        int src = 0;
        SingleSourceShortestPath_BellmanFord prob = new SingleSourceShortestPath_BellmanFord(7);
        prob.readFromFile("./Algorithms/DP/BellmanFord.txt");
        int[] dist = prob.bellmanFord(cost, src);
        System.out.println(Arrays.toString(dist));

//        for (int i = 0; i < dist.length; i++) {
//            System.out.println("Distance from " + src + " to " + i + ": " +
//                    (dist[i] == INF ? "INF" : dist[i]));
//        }
    }
}