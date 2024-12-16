package Algorithms;

import java.util.Scanner;
import java.util.Stack;

class Dijkstra {
    public int n, m;
    public int[][] graph;
    public boolean[] visited;
    public int[] distance;
    public Stack<Integer> stack = new Stack<Integer>();

    Dijkstra() {
        // build graph and take input
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // num vertex
        m = scanner.nextInt(); // num edge
        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        distance = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            graph[a][b] = graph[b][a] = w;
        }

    }

    public static void main(String[] args) {
    }

    public int[] dijkstra(int starting) {
        int[] shortestPath = null;
        distance[starting] = 0;
        // visited[starting] = true;

        for (int z = 0; z < n; z++) {
            int minimum = findMinimum();
            visited[minimum] = true;
            stack.push(minimum);

            findAdjacentAndFill(minimum);


        }

        return shortestPath;
    }

    public int findMinimum() {
        int min = 0;
        for (int i = 0; i <= n; i++) {
            if ((distance[i] < distance[min]) && !visited[i]) {
                min = i;
            }
        }
        return min;
    }

    public void findAdjacentAndFill(int k) {
        for (int i = 0; i <= n; i++) {
            if ((graph[k][i] > 0) && !visited[i]) {
                distance[i] = graph[k][i] + distance[k]; // k age visited korte hobe
            }
        }
    }

    public boolean hasConnectionBetween(int[][] graph, int a, int b) {

        return false;
    }

}
