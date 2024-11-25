package DataStructure.Graph;

class DFS {

    static class ArrayStack {
        public static final int CAPACITY = 100;
        private int[] data;
        private int t = -1;

        public ArrayStack() {
            this(CAPACITY);
        }

        public ArrayStack(int capacity) {
            data = new int[capacity];
        }

        public int size() {
            return (t + 1);
        }

        public boolean isEmpty() {
            return (t == -1);
        }

        public void push(int e) throws IllegalStateException {
            if (size() == data.length) {
                throw new IllegalStateException("Stack is full");
            }
            data[++t] = e;
        }

        public int top() throws IllegalStateException {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return data[t];
        }

        public int pop() throws IllegalStateException {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            int answer = data[t];
            data[t] = 0;
            t--;
            return answer;
        }
    }

    /*
        public static void traverseUsingDFS(int[][] G, int v) {
        int n = G[0].length;
        boolean[] visited = new boolean[n];
        ArrayStack stack = new ArrayStack();

        stack.push(v);
        visited[v] = true;

        int currentVertex = v;
        System.out.print(currentVertex + " ");

        while (!stack.isEmpty()) {
            int flag = 0;
            for (int w = 0; w < n; w++) {
                if (G[currentVertex][w] > 0 && !visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                    currentVertex = w;
                    System.out.print(currentVertex + " ");
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                currentVertex = stack.pop();
            }
        }
    }
*/

    static void traverseUsingDFS(int[][] G, int starting_node) {
        int node_count = G.length;
        boolean[] visited = new boolean[node_count];
        ArrayStack stack = new ArrayStack();

        stack.push(starting_node);
        visited[starting_node] = true;

        int current_node = starting_node;
        System.out.print(current_node + " ");

        while (!stack.isEmpty()) {
            boolean flag = false;

            for (int i = 0; i < node_count; i++) {
                if (G[current_node][i] > 0 && !visited[current_node]) {
                    // depth e jaoa possible, node ke push korbo,
                    // future e depth e jaoar jonno flag e true rakhbo
                    // ei bolte je
                    stack.push(current_node);
                    visited[current_node] = true;

                    System.out.print(current_node + " ");

                    flag = true;
                    break;
                }
                if (flag == false) {
                    current_node = stack.pop();
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] G = {
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 1, 1, 0}};
        traverseUsingDFS(G, 0);
    }

}
