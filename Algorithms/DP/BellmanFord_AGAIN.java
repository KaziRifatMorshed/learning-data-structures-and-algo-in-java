package Algorithms.DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BellmanFord_AGAIN {
    static int n;
    static int[] distance_arr;  // Renamed dist to distance_arr
    static int[] parent;        // Parent array to track the shortest path tree
    static int[][] cost;
    static final int inf = 999; // Renamed INF to inf
    static boolean debug = true;

    public BellmanFord_AGAIN(int n) {
        this.n = n;
        distance_arr = new int[n];
        parent = new int[n];
        cost = new int[n][n];
    }

    void initCost() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) cost[i][j] = 0;
                else cost[i][j] = inf;
            }
        }
    }

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

    int[] bellmanFord(int src) {
        // Initialize distance and parent arrays
        for (int i = 0; i < n; i++) {
            distance_arr[i] = cost[src][i];
            // Initialize parent array
            if (i == src) {
                parent[i] = -1; // Source has no parent
            } else if (cost[src][i] < inf) { // source theke jodi i te jaoar rasta thake
                parent[i] = src; // then i er parent holo source
            } else {
                parent[i] = -1; // No path known yet
            }
        }

        for (int k = 1; k < n - 1; k++) { // k = 1 hobe (algo er indexing toh 1 theke silo bole okhane 2 deoa silo
            if (debug){
                System.out.println("\n----------------------\nAttempt : k = " + k);
            }
            for (int u = 0; u < n; u++) {
                if (u == src) continue;

                boolean hasIncoming = false;
                for (int t = 0; t < n; t++) {
                    if (cost[t][u] < inf) {
                        hasIncoming = true;
                        break;
                    }
                }
                if (!hasIncoming) continue;

                for (int i = 0; i < n; i++) {
                    if (distance_arr[i] < inf && cost[i][u] < inf) { // careful // nicher RHS
                        if (distance_arr[u] > distance_arr[i] + cost[i][u]) {
                            if (debug){
                                System.out.println("\nrelaxing vertex u = " + u + " with " + i);
                                System.out.println(Arrays.toString(distance_arr));
                            }
                            distance_arr[u] = distance_arr[i] + cost[i][u];
                            if (debug) System.out.println(Arrays.toString(distance_arr));
                            parent[u] = i; // Update parent when finding a better path
                        }
                    }
                }
            }

//            if (debug){
//                System.out.println("\nAfter Attempt : k = " + k );
//                System.out.println(Arrays.toString(distance_arr));
//            }

        }
        return distance_arr;
    }

    static void printShortestPaths(int source) {
        source--; // Adjust for 0-based indexing
        System.out.println("\nShortest paths from node " + (source + 1) + ":");

        for (int i = 0; i < n; i++) {
            if (i == source) continue; // Skip source node

            StringBuilder sb = new StringBuilder();
            sb.append("Path to node ").append(i + 1)
                    .append(" (distance = ").append(distance_arr[i]).append("): ");

            if (distance_arr[i] == inf) {
                sb.append("No path exists");
                System.out.println(sb.toString());
                continue;
            }

            // Reconstruct path using parent array
            List<Integer> path = new ArrayList<>();
            int current = i;
            while (current != -1) {
                path.add(current + 1); // +1 to convert back to 1-based indexing
                current = parent[current];
            }
            Collections.reverse(path); // Reverse to get path from source to destination

            // Build the path string
            for (int j = 0; j < path.size(); j++) {
                sb.append(path.get(j));
                if (j < path.size() - 1) {
                    sb.append(" → ");
                }
            }

            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        BellmanFord_AGAIN prob = new BellmanFord_AGAIN(5);
        prob.readFromFile("./Algorithms/DP/BellmanFord.txt");
        int[] solu = prob.bellmanFord(0);

        System.out.println("Distances: " + Arrays.toString(solu));
        System.out.println("Parents: " + Arrays.toString(parent));

        // Print all paths from the source vertex (using 1-based indexing for the method call)
        printShortestPaths(1); // Source is vertex 1 (0-indexed in the code, 1-indexed for display)
    }
}