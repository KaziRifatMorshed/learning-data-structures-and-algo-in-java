package Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Prim {
    static class Edge {
        int destination, weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static int MAX_CAPACITY = 100;
    static ArrayList<Edge>[] graph = new ArrayList[MAX_CAPACITY];
    static boolean[] visited = new boolean[MAX_CAPACITY];


    static void takeInputFromFile(String filePath) throws FileNotFoundException {
        Scanner fs = new Scanner(new File(filePath));
        while (fs.hasNext()) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int wt = fs.nextInt();
//            if (a == 0 && b == 0 && wt == 0) break; // Stop reading if sentinel value is found // USELESS
            graph[a].add(new Edge(b, wt));
            graph[b].add(new Edge(a, wt));
        }
        fs.close();
    }

    static void initGraph() {
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
    }

    static int prim(int source) {
        int totalWeight = 0;
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        minHeap.add(new Edge(source, 0)); // first node for initialization, zero for avoiding it to be summed

        while (!minHeap.isEmpty()) {
            Edge currentEdge = minHeap.poll();
            int currentNode = currentEdge.destination; // dest
            if (visited[currentNode]) continue;
            else {
                visited[currentNode] = true;
                totalWeight += currentEdge.weight;
                for (Edge adjacentEdge : graph[currentNode]) {
                    if (!visited[adjacentEdge.destination]) {
                        minHeap.add(adjacentEdge);
                    }
                }
            }
        }
        return totalWeight;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        for (int i = 0; i < MAX_CAPACITY; i++) {
//            graph[i] = new ArrayList<>();
//        }
        initGraph();
        takeInputFromFile("./Algorithms/PrimGraph.txt");

        int minimumSpanningTreeWeight = prim(1);
        System.out.println("Total Weight = " + minimumSpanningTreeWeight); // Expected: 77
    }
} // DONE

