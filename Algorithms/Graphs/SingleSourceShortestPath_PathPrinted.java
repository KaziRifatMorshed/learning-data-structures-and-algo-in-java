package Algorithms.Graphs;

// GEMINI 2.5 PRO for learning

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList; // Added for path reconstruction
import java.util.Collections; // Added for path reconstruction
import java.util.List;       // Added for path reconstruction

class SingleSourceShortestPathWithPath { // Renamed class for clarity

    final static int inf = 100000;
    static int n = 0;
    static int[][] cost;
    static int[] distance_arr;
    static int[] parent; // <-- Added parent array

    static int minimumIndex(int[] distArr, boolean[] hasVisited) {
        int minIdx = -1; // Initialize to -1 to handle disconnected components
        int min = inf;
        for (int i = 0; i < distArr.length; i++) {
            if (!hasVisited[i] && distArr[i] < min) { // Use < min, not <= min to prioritize earlier nodes if distances are equal
                min = distArr[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    static void findShortestPaths(int source, int[][] cost, int numNodes) {
        // Adjust source to be 0-based index
        int sourceIndex = source - 1;
        if (sourceIndex < 0 || sourceIndex >= numNodes) {
            System.out.println("Invalid source node.");
            return;
        }

        boolean[] hasVisited = new boolean[numNodes];
        // Initialize parent array
        parent = new int[numNodes]; // <-- Initialize parent array
        Arrays.fill(parent, -1);    // <-- Set all parents to -1 initially

        for (int i = 0; i < numNodes; i++) {
            hasVisited[i] = false; // init visited status
            distance_arr[i] = cost[sourceIndex][i]; // init distance from source
            if (distance_arr[i] != inf && i != sourceIndex) {
                parent[i] = sourceIndex; // If directly connected, source is the initial parent
            }
        }

        // Source node specifics
        hasVisited[sourceIndex] = true;
        distance_arr[sourceIndex] = 0;
        parent[sourceIndex] = -1; // Source has no parent

        // Main Dijkstra loop
        for (int i = 1; i < numNodes; i++) {
            int nearestNode = minimumIndex(distance_arr, hasVisited);

            // If minimumIndex returns -1, it means remaining nodes are unreachable
            if (nearestNode == -1) {
                break;
            }

            hasVisited[nearestNode] = true;

            // Relaxation step
            for (int j = 0; j < numNodes; j++) {
                if (!hasVisited[j] && cost[nearestNode][j] != inf // Check for edge existence
                        && distance_arr[nearestNode] != inf // Ensure nearestNode is reachable
                        && distance_arr[j] > distance_arr[nearestNode] + cost[nearestNode][j])
                {
                    distance_arr[j] = distance_arr[nearestNode] + cost[nearestNode][j];
                    parent[j] = nearestNode; // <-- Update parent when a shorter path is found
                }
            }
            // Optional: Print distances at each step (can be removed for final output)
            //System.out.println("Distances after visiting node " + (nearestNode + 1) + ": " + Arrays.toString(distance_arr));
            //System.out.println("Parents after visiting node " + (nearestNode + 1) + ": " + Arrays.toString(parent));
        }

        // After the algorithm, print the results
        printResults(sourceIndex, numNodes);
    }

    // Helper method to reconstruct and print the path
    static void printPath(int currentNodeIndex, int sourceIndex) {
        if (parent[currentNodeIndex] == -1 && currentNodeIndex != sourceIndex) {
            // Node is unreachable (or is the source with no path needed)
            // Checking distance_arr[currentNodeIndex] == inf handles unreachability more reliably
            return;
        }

        List<Integer> path = new ArrayList<>();
        int tempNode = currentNodeIndex;
        while(tempNode != -1) {
            path.add(tempNode);
            tempNode = parent[tempNode];
            // Basic check to prevent infinite loops in case of error/cycle issues
            if (path.size() > n) {
                System.err.print("Error: Path reconstruction seems stuck in a loop for node " + (currentNodeIndex+1));
                return;
            }
        }
        Collections.reverse(path); // Reverse to get path from source to destination

        System.out.print("Path: ");
        for(int i = 0; i < path.size(); i++) {
            System.out.print((path.get(i) + 1)); // Print 1-based node index
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
    }

    // Method to print final distances and paths
    static void printResults(int sourceIndex, int numNodes) {
        System.out.println("\nShortest paths from source node " + (sourceIndex + 1) + ":");
        for (int i = 0; i < numNodes; i++) {
            System.out.print("Node " + (i + 1) + ": ");
            if (distance_arr[i] == inf) {
                System.out.println("Distance: INFINITY, Path: Unreachable");
            } else {
                System.out.print("Distance: " + distance_arr[i] + ", ");
                if (i == sourceIndex) {
                    System.out.print("Path: " + (sourceIndex+1));
                } else {
                    printPath(i, sourceIndex);
                }
                System.out.println(); // Newline after each node's info
            }
        }
    }


    static void initData() {
        cost = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i == j) cost[i][j] = 0;
                else
                    cost[i][j] = inf;
        distance_arr = new int[n];
        // No need to initialize distance_arr here, it's done in findShortestPaths
        // parent array will be initialized in findShortestPaths
    }


    static void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        n = scanner.nextInt();
        initData(); // Initializes cost matrix and allocates distance_arr
        while (scanner.hasNextInt()) { // Check specifically for integers
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            // Consider adding a check for termination condition if needed,
            // but relying on hasNextInt is usually safer.
            // if (a == 0 && b == 0 && w == 0) return; // Example termination condition

            // Add bounds check for safety
            if (a > 0 && a <= n && b > 0 && b <= n) {
                // Assuming non-negative weights for standard Dijkstra
                if (w < 0) {
                    System.err.println("Warning: Negative weight detected (" + a + " -> " + b + ": " + w + "). Dijkstra may not work correctly.");
                }
                // Store the minimum cost if multiple edges exist (optional, depends on problem)
                cost[a - 1][b - 1] = Math.min(cost[a - 1][b - 1], w);
                // If undirected graph, uncomment the line below:
                // cost[b - 1][a - 1] = Math.min(cost[b - 1][a - 1], w);
            } else {
                System.err.println("Warning: Invalid edge data read: " + a + ", " + b + ", " + w);
            }
        }
        scanner.close(); // Close the scanner
    }

    public static void main(String[] args) {
        try {
            // Make sure the path is correct relative to your execution context
            readFromFile("./Algorithms/Graphs/SingleSourceShortestPath.txt");
            if (n > 0) { // Check if data was loaded successfully
                int sourceNode = 1; // Remember to input/set the desired source node (1-based)
                findShortestPaths(sourceNode, cost, n);
            } else {
                System.out.println("No graph data loaded (n=0). Check file path and content.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Input file not found. Please ensure 'SingleSourceShortestPath.txt' exists at the correct path.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }
}