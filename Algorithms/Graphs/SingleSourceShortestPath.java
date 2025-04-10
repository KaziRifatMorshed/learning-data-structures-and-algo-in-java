package Algorithms.Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class SingleSourceShortestPath {

    final static int inf = 100000;
    static int n = 0;
    static int[][] cost;
    static int[] distance_arr;

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
        }

        hasVisited[source] = true;
        distance_arr[source] = 0;

        for (int i = 1; i < numNodes; i++) {
            int nearestNode = minimumIndex(hasVisited);
            hasVisited[nearestNode] = true;
            for (int j = 0; j < numNodes; j++) {
                if (!hasVisited[j] && distance_arr[j] > distance_arr[nearestNode] + cost[nearestNode][j]) {
                    distance_arr[j] = distance_arr[nearestNode] + cost[nearestNode][j];
                }
            }
            System.out.println(Arrays.toString(distance_arr));
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
        for (int i = 0; i < n; i++) distance_arr[i] = inf;
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
//            cost[a - 1][b - 1] = w; // directed digraph
            cost[a - 1][b - 1] = cost[b - 1][a - 1] = w; // undirected graph
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFromFile("./Algorithms/Graphs/SingleSourceShortestPath.txt");
        int sourceNode = 1;
        findShortestPaths(sourceNode, cost, n);
//        remember to input source
    }
}
