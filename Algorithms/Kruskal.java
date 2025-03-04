package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Kruskal {
    static class node {
        int a, b, cost;

        public node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int kruskal2(ArrayList<node> nodes) {
        int totalCost = 0;
        // sort arraylist on basis of cost
        Collections.sort(nodes, );

        return totalCost;
    }

//    static int kruskal(int[][] graph) {
//        int totalCost = 0;
//        int n = graph.length;
//        int[][] MST = new int[n][n];
//        // finding minimum edge
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                int minimumCost = graph[i][j];

    /// /                if (minimumCost < graph[]){}
//            }
//        }
//        return totalCost;
//    }
//
//    static void buildMatrix(String[] inputs, int[][] matrix) {
//        for (int i = 0; i < inputs.length; i++) {
//            Scanner scanner = new Scanner(inputs[i]);
//            int a = scanner.nextInt();
//            int b = scanner.nextInt();
//            int cost = scanner.nextInt();
//            matrix[a][b] = cost;
//            matrix[b][a] = cost;
//        }
//    }
    static void takeInput(String[] inputs, ArrayList<node> nodes) {
        for (int i = 0; i < inputs.length; i++) {
            Scanner scanner = new Scanner(inputs[i]);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int cost = scanner.nextInt();
            node new_node = new node(a, b, cost);
            nodes.add(new_node);
        }
    }

    public static void main(String[] args) {

        int node_count = 8;
//        int[][] cost_matrix = new int[node_count + 1][node_count + 1];
        ArrayList<node> nodes = new ArrayList<>();
        String input[] = {"1 3 13", "1 2 11", "2 1 11", "2 3 15", "2 4 8", "2 5 12", "5 4 14", "0 0 0"};
//        buildMatrix(input, cost_matrix);
        takeInput(input, nodes);
    }
}
