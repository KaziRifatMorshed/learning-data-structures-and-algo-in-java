```java
package Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Prim_V2 {
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

    // Read graph edges from a file
    static void takeInputFromFile(String filePath) throws FileNotFoundException {
        Scanner fs = new Scanner(new File(filePath));
        while (fs.hasNext()) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int wt = fs.nextInt();
            if (a == 0 && b == 0 && wt == 0) break; // Stop reading if sentinel value is found
            if (graph[a] == null) graph[a] = new ArrayList<>();
            if (graph[b] == null) graph[b] = new ArrayList<>();
            graph[a].add(new Edge(b, wt));
            graph[b].add(new Edge(a, wt));
        }
        fs.close();
    }

    // Prim's algorithm to find MST
    static int prim(int source) {
        int totalWeight = 0;
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        minHeap.add(new Edge(source, 0));

        while (!minHeap.isEmpty()) {
            Edge currentEdge = minHeap.poll();
            int currentNode = currentEdge.destination;

            if (visited[currentNode]) continue; // Skip if already visited

            visited[currentNode] = true; // Mark as visited
            totalWeight += currentEdge.weight; // Add edge weight to total

            // Add all adjacent edges to the priority queue
            for (Edge adjacentEdge : graph[currentNode]) {
                if (!visited[adjacentEdge.destination]) {
                    minHeap.add(adjacentEdge);
                }
            }
        }
        return totalWeight;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Initialize graph
        for (int i = 0; i < MAX_CAPACITY; i++) {
            graph[i] = new ArrayList<>();
        }

        // Read graph from file
        takeInputFromFile("./Algorithms/PrimGraph.txt");

        // Find MST weight
        int minimumSpanningTreeWeight = prim(1);
        System.out.println("Total Weight = " + minimumSpanningTreeWeight); // Expected: 77
    }
}
```