package Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;

class Kruskal {
    static final int MAX_VERTICES = 10000;
    static final ArrayList<Edge> edgeList = new ArrayList<>(); // ------------------------------ SYNTAX
    //    static final ArrayList<Integer> depth = new ArrayList<>(Collections.nCopies(MAX_VERTICES, 0)); // USELESS
    static final ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(MAX_VERTICES, 0)); // // You never initialize parent with default values, causing IndexOutOfBounds.
    // parent's index is child and value is parent
    static final ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(MAX_VERTICES, false));

    static class Edge {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static boolean hasCycle(ArrayList<Integer>[] graph, int currentNode) { // ---------- kom bujhi
        visited.set(currentNode, true);

        for (int adjacentNode : graph[currentNode]) { // --------------------------------- be careful
            if (!visited.get(adjacentNode)) { // if this adjacent is not visited
                // adjacent node er parent set korbo current node ke
                parent.set(adjacentNode, currentNode);
//                depth.set(adjacentNode, 1 + depth.get(currentNode)); // USELESS
                if (hasCycle(graph, adjacentNode)) {
                    return true;
                }
            } else if (adjacentNode != parent.get(currentNode)) { // --------------------- ???
                return true;
            }
        }
//        visited.set(currentNode, false);
        return false;
    }

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
    } // SHOULD WORK

    private static int minimumSpanningTree() {
        int minimumSpanningTreeWeight = 0;

        edgeList.sort(Comparator.comparingInt(edge -> edge.weight)); // ------------------------------ SYNTAX

//        amar kase edge ache, ekhn graph banabo
        ArrayList<Integer>[] graph = new ArrayList[MAX_VERTICES];
        for (int i = 0; i < MAX_VERTICES; i++) {
            graph[i] = new ArrayList<>();
        } // graph has been initialized

        // Kruskal's Algorithm
        for (Edge edge : edgeList) {
            // add edge to graph
            graph[edge.source].add(edge.destination);
            graph[edge.destination].add(edge.source);

            // Reset visited array for each cycle check
            Collections.fill(visited, false); // ------------------------------ SYNTAX
            // visited ke recursive call er agei empty korte hobe

            // checking
            if (hasCycle(graph, edge.source)) { // -------------------------- careful
                // Remove edge if it creates a cycle
                graph[edge.source].removeLast();
                graph[edge.destination].removeLast();
                /* Alternate Way
                graph[edge.source].remove(graph[edge.source].size() - 1);
                graph[edge.destination].remove(graph[edge.destination].size() - 1);
                */
            } else {
                minimumSpanningTreeWeight += edge.weight;
            }
        }
        return minimumSpanningTreeWeight;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        String currentPath = Paths.get("").toAbsolutePath().toString();
//        System.out.println("Current Path: " + currentPath);
        takeInputFromFile("./Algorithms/KruskalGraph.txt");
        int minimumSpanningTreeWeight = minimumSpanningTree();
        System.out.println("minimumSpanningTree Total Weight = " + minimumSpanningTreeWeight);
    }
} // done
