package Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Prim_V2 {
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
    static ArrayList<Integer> visited = new ArrayList<>();
    static ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(MAX_CAPACITY, 0));
    static ArrayList<Edge>[] graph = new ArrayList[MAX_CAPACITY];
    static ArrayList<Integer>[] mstGraph = new ArrayList[MAX_CAPACITY];


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

    static int getNodeCount() {
        int max = 0;
        for (ArrayList<Edge> t : graph) {
            max = Math.max(max, t.size());
        }
        return max;
    }

    static void initGraph() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static void constructGraph() {
        for (Edge edge : edgeList) {
            graph[edge.source].add(new Edge(edge.source, edge.destination, edge.weight));
            graph[edge.destination].add(new Edge(edge.destination, edge.source, edge.weight));
        }
    }

    static boolean hasCycle(ArrayList<Integer>[] graph, int currentVertex) {
        visited.add(currentVertex); // visit korbo
        for (int adjacentVertex : graph[currentVertex]) { // adjacent traverse korbo
            if (!visited.contains(adjacentVertex)) {
                parent.set(adjacentVertex, currentVertex);
                if (hasCycle(graph, adjacentVertex)) return true;
            } else if (adjacentVertex != parent.get(currentVertex)) return true;
        }
        return false;
    }

    static int prim(int Source) {
        int totalWeight = 0;
        initGraph();
        constructGraph();

        int currentlySelectedNode = Source;
        visited.add(currentlySelectedNode);

        int nodeCount = getNodeCount();
        for (int i = 0; i < nodeCount; i++) { // --------- ?????????
            ArrayList<Edge> allAvailableEdges = new ArrayList<>();
            for (int v : visited) { // visited e joto gulo int visited note ache
                for (Edge e : graph[v]) { // sei sob node er sob edge gulo ke add korbo
                    allAvailableEdges.add(e);
                }
            }
            // edge gulo ke sort korbo
            allAvailableEdges.sort(Comparator.comparingInt(edge -> edge.weight));

            for (Edge e : allAvailableEdges) {
                mstGraph[e.source].add(e.destination);
                mstGraph[e.destination].add(e.source);
                if (hasCycle(mstGraph, e.source)) {
                    mstGraph[e.source].removeLast();
                    mstGraph[e.destination].removeLast();
                } else {
                    totalWeight += e.weight;
                    visited.add(e.source);
                    break;
                }
            }
        }


        return totalWeight;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        String currentPath = Paths.get("").toAbsolutePath().toString();
//        System.out.println("Current Path: " + currentPath);
        takeInputFromFile("./Algorithms/PrimGraph.txt");
        int minimumSpanningTreeWeight = prim(1);
        System.out.println("Total Weight = " + minimumSpanningTreeWeight); // 77
    }
}
