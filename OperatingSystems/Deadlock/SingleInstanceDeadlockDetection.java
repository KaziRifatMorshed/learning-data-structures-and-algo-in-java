package OperatingSystems.Deadlock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class SingleInstanceDeadlockDetection {

    static class Edge {
        char source, destination;

        public Edge(char source, char destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    static int MAX_CAPACITY = 100;
    static ArrayList<Edge> edgeList = new ArrayList<>();
    static ArrayList<Character> visited = new ArrayList<>(MAX_CAPACITY);
    static ArrayList<Character> parent = new ArrayList<>(Collections.nCopies(MAX_CAPACITY, null));
    static ArrayList<Character>[] graph = new ArrayList[MAX_CAPACITY]; // syntax

    static void initGraph() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static boolean hasCycle(ArrayList<Character>[] graph, char currentVertex) {
        visited.add(currentVertex); // visit korbo
        for (char adjacentVertex : graph[currentVertex]) { // adjacent traverse korbo
            if (!visited.contains(adjacentVertex)) {
                // parent.set(currentVertex, adjacentVertex); // ------------------ WRONG, ulta
                parent.set(adjacentVertex, currentVertex);
//                hasCycle(graph, adjacentVertex); // ------ ei line bad cole gesilo
                if (hasCycle(graph, adjacentVertex)) {
                    return true;
                }
            } else if (adjacentVertex != parent.get(currentVertex))
                return true;
        }
        return false;
    }

    void readFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        initGraph();
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            char a = nextLine.charAt(0);
            char b = nextLine.charAt(2);
            edgeList.add(new Edge(a, b));
        }
    }

    void exec() {
        for (Edge e : edgeList) {
            graph[e.source].add(e.destination);
        }

        LinkedHashSet<Character> nodes = new LinkedHashSet<>();
        for (Edge e : edgeList) {
            nodes.add(e.source);
            nodes.add(e.destination);
        }

        boolean deadlockFound = false;
        for (char start : nodes) {
            if (!visited.contains(start)) {
                parent.set(start, (char) 0);
                if (hasCycle(graph, start)) {
                    System.out.println("Deadlock detected: true");
                    deadlockFound = true;
                    break;
                }
            }
        }

        if (!deadlockFound) {
            System.out.println("Deadlock detected: false");
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        SingleInstanceDeadlockDetection algo = new SingleInstanceDeadlockDetection();
        String path = "OperatingSystems/Deadlock/SingleInstanceDeadlockDetection.txt";
        algo.readFromFile(path);
        algo.exec();
    }
}