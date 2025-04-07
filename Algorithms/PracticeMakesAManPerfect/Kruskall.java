package Algorithms.PracticeMakesAManPerfect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Kruskall {
    static int MAX_VERTEX = 1000;
    static ArrayList<Edge> edges = new ArrayList<>(MAX_VERTEX);
    static ArrayList<Integer>[] graph = new ArrayList[MAX_VERTEX];
    static ArrayList<Integer> visited = new ArrayList<>();
    static ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(MAX_VERTEX, 0));

    static class Edge {
        int source, dest, weight;

        public Edge(int source, int dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Scanner scanner1 = new Scanner(s);
            int source = scanner1.nextInt();
            int dest = scanner1.nextInt();
            int weight = scanner1.nextInt();
            edges.add(new Edge(source, dest, weight));
        }
    }

    static boolean hasCycle(int currentVertex) {
        visited.add(currentVertex); // visit korbo
        for (int adjacentVertex : graph[currentVertex]) { // adjacent traverse korbo
            if (!visited.contains(adjacentVertex)) {
                parent.set(adjacentVertex, currentVertex);
                hasCycle(adjacentVertex); // ------ ei line bad cole gesilo
            } else if (adjacentVertex != parent.get(currentVertex)) return true;
        }
        return false;
    }

    static void initGraph() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static int kruskal() {
        int totalWeight = 0;
        initGraph();
        edges.sort(Comparator.comparingInt(edge -> edge.weight));

        for (Edge edge : edges) {
            graph[edge.source].add(edge.dest);
            graph[edge.dest].add(edge.source);
            visited.clear();
            if (hasCycle(edge.source)) {
                graph[edge.source].removeLast();
                graph[edge.dest].removeLast();
            } else {
                totalWeight += edge.weight;
            }
        }
        return totalWeight;
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFromFile("./Algorithms/KruskalGraph.txt");
        int totalWeight = kruskal();
        System.out.println(totalWeight);
    }
}
