package Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Prim {
    static class Edge {
        int destination, weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "dest=" + destination +
                    ", weight=" + weight +
                    '}';
        }
    }

    static int MAX_CAPACITY = 100;
    static ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(MAX_CAPACITY, false));
    static ArrayList<Edge>[] graph = new ArrayList[MAX_CAPACITY];

    static void takeInputFromFile(String filePath) throws FileNotFoundException {
        Scanner fs = new Scanner(new File(filePath));
        while (fs.hasNext()) {
            String s = fs.nextLine();
            Scanner scanner = new Scanner(s);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int wt = scanner.nextInt();
            if (a == b && b == wt && wt == 0) return;
            // Construct Graph Here
            graph[a].add(new Edge(b, wt));
            graph[b].add(new Edge(a, wt));
        }
    } // WORKS

    static void initGraph() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static int prim(int Source) {
        initGraph();
        int totalWeight = 0;


        return totalWeight;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        String currentPath = Paths.get("").toAbsolutePath().toString();
//        System.out.println("Current Path: " + currentPath);
        takeInputFromFile("./Algorithms/PrimGraph.txt");
        int minimumSpanningTreeWeight = prim(1);
        System.out.println("Total Weight = " + minimumSpanningTreeWeight); // Expected: 77
    }
}
