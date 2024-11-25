package DataStructure.Graph;

import java.util.Queue;

public class BFS {
    public static void traverseUsingBFS(int[][] G, int v) {
        int n = G[0].length; // num rows // ultimately node count
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = ;
        queue.enqueue(v);
        visited[v] = true;
        while(!queue.isEmpty()) {
            int currentVertex = queue.dequeue();
            System.out.print(currentVertex + " ");
            for(int w = 0; w < n; w++) {
                if(G[currentVertex][w] == 1 && !visited[w]) {
                    queue.enqueue(w);
                    visited[w] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] G =
                {{0,1,1,0,0,0,0,0},
                        {1,0,0,1,1,0,0,0},
                        {1,0,0,0,0,1,1,0},
                        {0,1,0,0,0,0,0,1},
                        {0,1,0,0,0,0,0,1},
                        {0,0,1,0,0,0,0,1},
                        {0,0,1,0,0,0,0,1},
                        {0,0,0,1,1,1,1,0}};
        traverseUsingBFS(G, 0);
    }
}
