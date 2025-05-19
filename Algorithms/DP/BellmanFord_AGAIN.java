package Algorithms.DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BellmanFord_AGAIN {
    int n;
    int[] dist;
    int[] parent; // Added parent array to track the predecessor of each vertex
    int[][] cost;
    static final int INF = 999;

    public BellmanFord_AGAIN(int n) {
        this.n = n;
        dist = new int[n];
        parent = new int[n]; // Initialize parent array
        cost = new int[n][n];
    }

    void initCost() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) cost[i][j] = 0;
                else cost[i][j] = INF;
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
            cost[a - 1][b - 1] = w; // directed graph
        }
    }

    int[] bellmanFord(int src) {
        // Initialize distance and parent arrays
        for (int i = 0; i < n; i++) {
            dist[i] = cost[src][i];
            // Set parent for directly connected vertices
            if (cost[src][i] < INF && // jodi source and i connected thake
                    src != i) {
                parent[i] = src;
            } else {
                parent[i] = -1; // -1 means no parent
            }
        }

        // Set source vertex's parent as itself
        parent[src] = src;

        for (int k = 2; k < n - 1; k++) { // 2 to n-1
            for (int u = 0; u < n; u++) {
                if (u == src) continue;

                boolean hasIncoming = false;
                for (int t = 0; t < n; t++) {
                    if (cost[t][u] < INF) {
                        hasIncoming = true;
                        break;
                    }
                }
                if (!hasIncoming) continue;

                for (int i = 0; i < n; i++) {
                    if (dist[i] < INF && cost[i][u] < INF) {
                        if (dist[u] > dist[i] + cost[i][u]) {
                            dist[u] = dist[i] + cost[i][u];
                            parent[u] = i; // Update parent when finding a better path
                        }
                    }
                }
            }
        }
        return dist;
    }

    // Function to print the path from source to destination using parent array
    void printPath(int dest) {
        if (parent[dest] == -1) {
            System.out.println("No path exists to vertex " + (dest + 1));
            return;
        }

        List<Integer> path = new ArrayList<>();
        for (int v = dest; v != parent[v]; v = parent[v]) {
            path.add(0, v);
        }
        path.add(0, parent[dest] == dest ? dest : parent[dest]); // Add source vertex

        System.out.print("Path to vertex " + (dest + 1) + ": ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print((path.get(i) + 1) + (i < path.size() - 1 ? " -> " : ""));
        }
        System.out.println(" (Distance: " + dist[dest] + ")");
    }

    // Function to print all paths from the source
    void printAllPaths() {
        System.out.println("Shortest paths from the source vertex " + (parent[0] + 1) + ":");
        for (int i = 0; i < n; i++) {
            if (i != parent[0]) { // Skip the source vertex itself
                printPath(i);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        BellmanFord_AGAIN prob = new BellmanFord_AGAIN(7);
        prob.readFromFile("./Algorithms/DP/BellmanFord.txt");
        int[] solu = prob.bellmanFord(0);

        System.out.println("Distances: " + Arrays.toString(solu));
        System.out.println("Parents: " + Arrays.toString(prob.parent));

        // Print all paths from the source vertex
        prob.printAllPaths();
    }
}