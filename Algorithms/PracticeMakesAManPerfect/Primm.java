package Algorithms.PracticeMakesAManPerfect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Primm {
    static class Edge {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static int MAX_VERTEX = 1000;
    static ArrayList<Edge>[] graph = new ArrayList[MAX_VERTEX];
    static ArrayList<Integer> visited = new ArrayList<>();

    static void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Scanner scanner1 = new Scanner(s);
            int source = scanner1.nextInt();
            int dest = scanner1.nextInt();
            int weight = scanner1.nextInt();
            graph[source].add(new Edge(dest, weight));
            graph[dest].add(new Edge(source, weight));
        }
    }

    static void initGraph() {
//        for (ArrayList<Edge> arrayList : graph) arrayList = new ArrayList<>(); // USELESS as nothing exists
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
    }

    static int prim(int source) {
        int totalWeight = 0;
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparing(edge -> edge.weight));
        minHeap.add(new Edge(source, 0));
        while (!minHeap.isEmpty()) {
            Edge currentEdge = minHeap.poll();
            int currentNode = currentEdge.dest;
            if (visited.contains(currentNode)) continue;
            else {
                visited.add(currentNode);
                totalWeight += currentEdge.weight;
                for (Edge adjacentEdges : graph[currentNode]) {
                    if (!visited.contains(adjacentEdges.dest))
                        minHeap.add(adjacentEdges);
                }
            }
        }
        return totalWeight;
    }

    public static void main(String[] args) throws FileNotFoundException {
        initGraph();
        readFromFile("./Algorithms/PrimGraph.txt");
        int totalWeight = prim(1);
        System.out.println(totalWeight);
    } // 77
}
