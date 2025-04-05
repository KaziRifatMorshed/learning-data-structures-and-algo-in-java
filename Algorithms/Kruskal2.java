package Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class
Kruskal2 {
    static class Edge {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static int MAX_CAPACITY = 100;
    static ArrayList<Edge> edgeList = new ArrayList<>();
    static ArrayList<Integer> visited = new ArrayList<>(MAX_CAPACITY);
    static ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(MAX_CAPACITY, 0));
    static ArrayList<Integer>[] graph = new ArrayList[MAX_CAPACITY];

    static void takeInputFromFile(String filePath) throws FileNotFoundException {
        Scanner fs = new Scanner(new File(filePath));
        while (fs.hasNext()) {
            String s = fs.nextLine();
            Scanner scanner = new Scanner(s);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int wt = scanner.nextInt();
            if (a == b && b == wt && wt == 0) return;
            Edge new_node = new Edge(a, b, wt);
            edgeList.add(new_node);
        }
    } // WORKS

    static void initGraph() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static void constructGraph() {
        for (Edge edge : edgeList) {
            graph[edge.source].add(edge.destination);
            graph[edge.destination].add(edge.source);
        }
    } // unnecessary

    static boolean hasCycle(ArrayList<Integer>[] graph, int currentVertex) {
        visited.add(currentVertex); // visit korbo
        for (int adjacentVertex : graph[currentVertex]) { // adjacent traverse korbo
            if (!visited.contains(adjacentVertex)) {
//              parent.set(currentVertex, adjacentVertex); // ------------------ WRONG, ulta
                parent.set(adjacentVertex, currentVertex);
                if (hasCycle(graph, adjacentVertex)) return true;
            } else if (adjacentVertex != parent.get(currentVertex)) return true;
        }
        return false;
    }

    static int kruskal() {
        int totalWeight = 0;
        initGraph();
        // sort all edges according to weight
        edgeList.sort(Comparator.comparingInt(edge -> edge.weight));
        for (Edge edge : edgeList) {
            graph[edge.source].add(edge.destination);
            graph[edge.destination].add(edge.source);
            visited.removeAll(visited);
            if (hasCycle(graph, edge.source)) {
                graph[edge.source].removeLast();
                graph[edge.destination].removeLast();
            } else {
                totalWeight += edge.weight;
            }
        }
        return totalWeight;
    } // ok

    public static void main(String[] args) throws FileNotFoundException {
//        String currentPath = Paths.get("").toAbsolutePath().toString();
//        System.out.println("Current Path: " + currentPath);
        takeInputFromFile("./Algorithms/KruskalGraph.txt");
        int minimumSpanningTreeWeight = kruskal();
        System.out.println("Total Weight = " + minimumSpanningTreeWeight);
    } // 52
} // DONE
