package Algorithms.DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class BellmanFord_AGAIN {
    int n;
    int[] dist;
    int[][] cost;
    static final int INF = 999;

    public BellmanFord_AGAIN(int n) {
        this.n = n;
        dist = new int[n];
        cost = new int[n][n];
    }

    void initCost() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) cost[i][j] = 0;
                else cost[i][j] = INF;
            }
        }
    }

    void readFromFile(String path) throws FileNotFoundException {
        initCost();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Scanner scanner1 = new Scanner(s);
            int a = scanner1.nextInt();
            int b = scanner1.nextInt();
            int w = scanner1.nextInt();
            if (a == b && b == w && w == 0) return;
            cost[a - 1][b - 1] = w;
        }
    }

    int[] bellmanFord(int src) {
        for (int i = 0; i < n; i++) dist[i] = cost[src][i];

        for (int k = 2; k < n - 1; k++) {
            for (int u = 0; u < n; u++) {
                if (u == src) continue;

                boolean hasIncoming = false;
                for (int t = 0; t < n; t++) {
                    if (cost[t][u] < INF) {
                        hasIncoming = true;
                        break;
                    }
                }
                if (!hasIncoming) continue;

                for (int i = 0; i < n; i++) {
                    if (dist[i] < INF && cost[i][u] < INF) {
                        if (dist[u] > dist[i] + cost[i][u]) {
                            dist[u] = dist[i] + cost[i][u];
                        }
                    }
                }
            }
        }
        return dist;
    }


    public static void main(String[] args) throws FileNotFoundException {
        BellmanFord_AGAIN prob = new BellmanFord_AGAIN(7);
        prob.readFromFile("./Algorithms/DP/BellmanFord.txt");
        int[] solu = prob.bellmanFord(0);
        System.out.println(Arrays.toString(solu));
    }
}
