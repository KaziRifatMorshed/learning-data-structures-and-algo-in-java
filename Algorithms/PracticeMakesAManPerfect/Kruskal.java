package Algorithms.PracticeMakesAManPerfect;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.classfile.attribute.LineNumberInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Kruskal {
    static class Edge {
        int weight, source, dest;

        public Edge(int source, int dest, int weight) {
            this.weight = weight;
            this.source = source;
            this.dest = dest;
        }
    }

    static int MAX_CAPACITY = 1000;
    static ArrayList<Edge> edges = new ArrayList<>();
    static ArrayList<Integer>[] graph = new ArrayList[MAX_CAPACITY];
    static ArrayList<Integer> visited = new ArrayList<>();
    static ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(MAX_CAPACITY, 0)); // You never initialize parent with default values, causing IndexOutOfBounds.

    static void readFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Scanner scanner1 = new Scanner(s);
            int a = scanner1.nextInt();
            int b = scanner1.nextInt();
            int w = scanner1.nextInt();
            if (a == b && b == w && w == 0) return;
            edges.add(new Edge(a, b, w));
        }
    }

    static void initGraph() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static void constructGraph() {
        for (Edge e : edges) {
            graph[e.source].add(e.dest);
            graph[e.dest].add(e.source);
        }
    }

    static void addEdgeToGraph(Edge e) {
        graph[e.source].add(e.dest);
        graph[e.dest].add(e.source);
    }

    static boolean hasCycle(int currentVertex) {
        visited.add(currentVertex);
        for (int adjacentVertex : graph[currentVertex]) {
            if (!visited.contains(adjacentVertex)) { // if not visited
                parent.set(adjacentVertex, currentVertex);
                if (hasCycle(adjacentVertex)) return true; // MISSED
            } else if (adjacentVertex != parent.get(currentVertex)) { // current er parent jodi adjacent na hoy
                return true;
            }
        }
        return false;
    }

    static int kruskal() {
        int totalWeight = 0;
        initGraph();

        edges.sort(Comparator.comparingInt(edge -> edge.weight));
        for (Edge e : edges) {
            addEdgeToGraph(e);
//            visited.removeAll(visited);
            visited.clear();
            if (hasCycle(e.source)) {
                // remmove last
                graph[e.source].removeLast();
                graph[e.dest].removeLast();
            } else {
                totalWeight += e.weight;
            }
        }
        return totalWeight;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        String currentPath = Paths.get("").toAbsolutePath().toString();
//        System.out.println("Current Path: " + currentPath);
        readFromFile("./Algorithms/KruskalGraph.txt");
        int minimumSpanningTreeWeight = kruskal();
        System.out.println("Total Weight = " + minimumSpanningTreeWeight);
    } // 52

}
