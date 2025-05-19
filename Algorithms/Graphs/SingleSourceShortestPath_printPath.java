package Algorithms.Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class SingleSourceShortestPath_printPath {

    final static int inf = 100000;
    static int n = 0;
    static int[][] cost;
    static int[] distance_arr;
    static int[] parent; // Array to store the parent node of each vertex

    static int minimumIndex(boolean[] hasVisited) {
        int minIdx = 0, min = inf;
        for (int i = 0; i < distance_arr.length; i++) {
            if (!hasVisited[i] && min > distance_arr[i]) {
                min = distance_arr[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    static void findShortestPaths(int source, int[][] cost, int numNodes) {
        source--;
        boolean[] hasVisited = new boolean[numNodes];

        for (int i = 0; i < numNodes; i++) {
            hasVisited[i] = false; // init
            distance_arr[i] = cost[source][i];
            // Initialize parent array: if directly connected to source, parent is source
            // otherwise, parent is -1 (no parent yet)
            parent[i] = (cost[source][i] != inf) ? source : -1;
        }

        hasVisited[source] = true;
        distance_arr[source] = 0;
        parent[source] = -1; // Source has no parent

        for (int i = 1; i < numNodes; i++) {
            int nearestNode = minimumIndex(hasVisited);
            hasVisited[nearestNode] = true;
            for (int j = 0; j < numNodes; j++) {
                if (!hasVisited[j] &&
                        distance_arr[j] > distance_arr[nearestNode] + cost[nearestNode][j]) {
                    distance_arr[j] = distance_arr[nearestNode] + cost[nearestNode][j];
                    parent[j] = nearestNode; // Update parent for the new shorter path
                }
            }
            System.out.println(Arrays.toString(distance_arr));
        }
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

    static void initData() {
        cost = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i == j) cost[i][j] = 0;
                else
                    cost[i][j] = inf;

        distance_arr = new int[n];
        parent = new int[n]; // Initialize parent array
        for (int i = 0; i < n; i++) {
            distance_arr[i] = inf;
            parent[i] = -1; // -1 indicates no parent assigned yet
        }
    }


    static void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        n = scanner.nextInt();
        initData();
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            if (a == b && b == w && w == 0) return;
            cost[a - 1][b - 1] = w; // directed digraph
//            cost[a - 1][b - 1] = cost[b - 1][a - 1] = w; // undirected graph
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFromFile("./Algorithms/Graphs/SingleSourceShortestPath.txt");
        int sourceNode = 5;
        findShortestPaths(sourceNode, cost, n);
        System.out.println(Arrays.toString(parent));
        printShortestPaths(sourceNode); // Print the shortest paths
    }
}