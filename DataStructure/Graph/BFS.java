package DataStructure.Graph;

class BFS {

    static class ArrayQueue {
        public static final int CAPACITY = 100;
        private int[] data;
        private int f = 0;
        private int sz = 0;

        public ArrayQueue() {
            this(CAPACITY);
        }

        public ArrayQueue(int capacity) {
            data = new int[capacity];
        }

        public int size() {
            return sz;
        }

        public boolean isEmpty() {
            return (sz == 0);
        }

        public void enqueue(int e) throws IllegalStateException {
            if (sz == data.length) {
                throw new IllegalStateException("Queue is full.");
            }
            int avail = (f + sz) % data.length;
            data[avail] = e;
            sz++;
        }

        public int first() {
            if (isEmpty()) {
                return -1; // a garbaze value.
            }
            return data[f];
        }

        public int dequeue() {
            if (isEmpty()) {
                return -1; // a garbaze value.
            }
            int answer = data[f];
            data[f] = 0;
            f = (f + 1) % data.length;
            sz--;
            return answer;
        }
    }

    public static void traverseUsingBFS(int[][] G, int startingNode) {
        // Necessary for operation
        int node_count = G.length;
        // int node_count = G[0].length;
        ArrayQueue queue = new ArrayQueue();
        boolean[] visited = new boolean[node_count];

        // Algorithm
        queue.enqueue(startingNode);
        visited[startingNode] = true; // --------------------------------------------shit, ekhan e false diye rekhesilam

        while (!queue.isEmpty()) {
            int currentNode = queue.dequeue();
//            visited[currentNode] = true; // ------------------------------------------------- Eta ekhan e hobe na
            System.out.print(currentNode + " "); // ------------------------------------- Node print korlei vule gesilam

            for (int i = 0; i < node_count; i++) {
                if (G[currentNode][i] > 0 // i er sathe kar kar somporrko ache
                        && visited[i] == false) // i visited ki na
                { // for finding all unvisited nodes
                    queue.enqueue(i);
                    visited[i] = true;
                }

            }
        }

    } // WORKS

    public static void main(String[] args) {
        int[][] G =
                {{0, 1, 1, 0, 0, 0, 0, 0},
                        {1, 0, 0, 1, 1, 0, 0, 0},
                        {1, 0, 0, 0, 0, 1, 1, 0},
                        {0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 0, 1, 0, 0, 0, 0, 1},
                        {0, 0, 1, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 1, 1, 1, 0}};
        traverseUsingBFS(G, 0);
        System.out.println();
        traverseUsingBFS(G, 1);
    }
}
