package Algorithms;

class ShortestPath_Matrix {
    final static int inf = 100000;

    static int minimumIndex(int[] distArr, boolean[] hasVisited) {
        int minIdx = 0, min = inf;
        for (int i = 0; i < distArr.length; i++) {
            if (!hasVisited[i] && min > distArr[i]) {
                min = distArr[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    static void findShortestPaths(int source, int[][] cost, int[] distance_arr, int numNodes) {
        boolean[] hasVisited = new boolean[numNodes];

        for (int i = 0; i < numNodes; i++) {
            hasVisited[i] = false; // init
            distance_arr[i] = cost[source][i];
        }

        hasVisited[source] = true;
        distance_arr[source] = 0;

        for (int i = 1; i < numNodes - 1; i++) {
            int nearestNode = minimumIndex(distance_arr, hasVisited);
            hasVisited[nearestNode] = true;
            for (int j = 0; j < numNodes; j++) {
                if (!hasVisited[j] && distance_arr[j] > distance_arr[nearestNode] + cost[nearestNode][j]) {
                    distance_arr[j] = distance_arr[nearestNode] + cost[nearestNode][j];
                }
            }
        }
    }

    static void main(String[] args) {
        int[][] cost = {
                {0, inf, inf, inf, inf, inf, inf, inf},
                {300, 0, inf, inf, inf, inf, inf, inf},
                {100, 800, 0, inf, inf, inf, inf, inf},
                {inf, inf, 1200, 0, inf, inf, inf, inf},
                {inf, inf, inf, 1500, 0, 250, inf, inf},
                {inf, inf, inf, 1000, inf, 0, 900, 1400},
                {inf, inf, inf, inf, inf, inf, 0, 1000},
                {1700, inf, inf, inf, inf, inf, inf, 0}};
        int n = cost[0].length;
        int[] dist = new int[n];
        findShortestPaths(4, cost, dist, n);
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }
} // DONE