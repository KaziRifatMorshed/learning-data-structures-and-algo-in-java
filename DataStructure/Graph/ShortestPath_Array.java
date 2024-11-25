package DataStructure.Graph;

class ShortestPath_Array {
    final static int infinite = 10000;  // A value bigger than all the costs of the graph

    public static void findShortestPaths(int starting_node, int[][] cost, int[] distance_array, int n) {

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            distance_array[i] = cost[starting_node][i]; // starting node theke onno sobar cost ei distance array te rakho
        }

        visited[starting_node] = true;
        distance_array[starting_node] = 0;

        for (int num = 1; num < (n - 1); num++) { // --------- n - 1 porjonto keno ?
            // joto gulo node, tar theke ek kom bar calculation hobe
            // khatay korle jemon ekta 2D matrix hoto, sei matrix er ek ekta row ei for loop er kaj


            int currently_minimum = 0; // ------------------ why zero ?? jodi nicher for loop er vitor e update e na hoy
            int min = infinite; // present row te minimum track korar jonno variable
            for (int i = 0; i < n; i++) { // search, present row te minimum ke ache
                if (!visited[i] && distance_array[i] < min) {
                    currently_minimum = i; // track minimum index
                    min = distance_array[i]; // update minimum value
                }
            }

            // ebar minimum ke visit korbo
            visited[currently_minimum] = true;

            // Relaxation
            for (int w = 0; w < n; w++) { // kom kom bujhlam
                if (!visited[w]
                        &&
                        distance_array[w] > distance_array[currently_minimum] + cost[currently_minimum][w])
                //
                {
                    distance_array[w] = distance_array[currently_minimum] + cost[currently_minimum][w];
                }
            }
        }
    } // WORKS

    public static void main(String[] args) {
        int[][] cost_matrix = {{0, infinite, infinite, infinite, infinite, infinite, infinite, infinite},
                {300, 0, infinite, infinite, infinite, infinite, infinite, infinite},
                {100, 800, 0, infinite, infinite, infinite, infinite, infinite},
                {infinite, infinite, 1200, 0, infinite, infinite, infinite, infinite},
                {infinite, infinite, infinite, 1500, 0, 250, infinite, infinite},
                {infinite, infinite, infinite, 1000, infinite, 0, 900, 1400},
                {infinite, infinite, infinite, infinite, infinite, infinite, 0, 1000},
                {1700, infinite, infinite, infinite, infinite, infinite, infinite, 0}};
        int n = cost_matrix[0].length;
        int[] distance_array = new int[n];

        int target = 4;
        findShortestPaths(target, cost_matrix, distance_array, n);

        for (int i = 0; i < distance_array.length; i++) {
            System.out.println("Shortest Distance from " + i + " to " + target + " : " + distance_array[i] + " ");
        }
    }
} // DONE
