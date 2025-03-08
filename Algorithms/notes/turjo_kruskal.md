```cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdint>
#include <queue>

#define int long long
#define all(x) (x).begin(), (x).end()

using namespace std;

bool comp(vector<int> a, vector<int> b) {
    return a.back() < b.back();
}

const int N = 1e4;
vector<vector<int>> adj;
// vector<int> adj[N];
vector<int> d(N), p(N);
vector<bool> visited(N, 0);
int dfs(vector<int> *temp, int u) {
    // cout << u << " ";
    visited[u] = 1;

    for(auto v : temp[u]) {
        if(!visited[v]) {
            p[v] = u;
            d[v] = d[u] + 1;
            if(dfs(temp, v))
                return true;
        } else if(v != p[u]) 
            return true;
    }
    return false;
}

void solve() {
    int ed, v; cin >> ed >> v;
    int a, b, wt;
    for (int i = 0; i < ed; ++i) {
        cin >> a >> b >> wt;
        adj.push_back({a, b, wt});
        // adj[a].push_back(b);
        // adj[b].push_back(a);
    }
    sort(all(adj), comp);

    vector<int> res[N];
    int ans = 0;
    for (int i = 0; i < ed; ++i) {
        a = adj[i][0], b = adj[i][1], wt = adj[i][2];
        res[a].push_back(b);
        res[b].push_back(a);
        visited.assign(N, 0);
        if(dfs(res, a)) {
            res[a].pop_back();
            res[b].pop_back();
        } else {
            ans += wt;
        }
    }
    cout << ans;
}


int32_t main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    // freopen("input.txt", "r" , stdin);
    // freopen("output.txt", "w", stdout);
    // cout << fixed << setprecision(20);
    
    solve();
    
    return 0;
}
```

# Here's the Java conversion of your code using ArrayList:

```java
import java.util.*;

public class Main {
    static final int N = 10000;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static ArrayList<Integer> d = new ArrayList<>(Collections.nCopies(N, 0));
    static ArrayList<Integer> p = new ArrayList<>(Collections.nCopies(N, 0));
    static ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(N, false));

    static boolean dfs(ArrayList<Integer>[] temp, int u) {
        visited.set(u, true);

        for (int v : temp[u]) {
            if (!visited.get(v)) {
                p.set(v, u);
                d.set(v, d.get(u) + 1);
                if (dfs(temp, v))
                    return true;
            } else if (v != p.get(u))
                return true;
        }
        return false;
    }

    static void solve(Scanner sc) {
        int ed = sc.nextInt();
        int v = sc.nextInt();
        int a, b, wt;

        for (int i = 0; i < ed; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            wt = sc.nextInt();
            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(a);
            edge.add(b);
            edge.add(wt);
            adj.add(edge);
        }

        // Sort based on weight (last element of each inner list)
        adj.sort((x, y) -> x.get(2).compareTo(y.get(2)));

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] res = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            res[i] = new ArrayList<>();
        }

        long ans = 0;
        for (int i = 0; i < ed; i++) {
            a = adj.get(i).get(0);
            b = adj.get(i).get(1);
            wt = adj.get(i).get(2);

            res[a].add(b);
            res[b].add(a);

            // Reset visited array
            Collections.fill(visited, false);

            if (dfs(res, a)) {
                res[a].remove(res[a].size() - 1);
                res[b].remove(res[b].size() - 1);
            } else {
                ans += wt;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
```

Key changes made in the Java version:

1. Used `ArrayList` instead of C++ vectors
2. Changed the vector of vectors to `ArrayList<ArrayList<Integer>>`
3. Initialized ArrayLists with proper size using `Collections.nCopies`
4. Changed array syntax to Java array syntax
5. Used `Scanner` for input instead of cin
6. Changed sorting implementation to use Java's lambda expression
7. Changed vector's `push_back` to ArrayList's `add`
8. Changed vector's `pop_back` to ArrayList's `remove(size() - 1)`
9. Used primitive `long` instead of `int64_t`
10. Removed C++ specific preprocessor directives

The functionality remains the same: it implements a minimum spanning tree algorithm that reads edges with weights and outputs the minimum total weight while avoiding cycles.

To use this program:
1. Input the number of edges and vertices
2. For each edge, input the source vertex, destination vertex, and weight
3. The program will output the minimum total weight of the spanning tree

Note: Make sure to handle large inputs appropriately as Java's Scanner can be slower than C++'s cin for very large inputs. If performance is critical, consider using BufferedReader instead of Scanner.

# Here's the code with more understandable file naming and improved organization:

```java
import java.util.*;

public class MinimumSpanningTree {
    static final int MAX_VERTICES = 10000;
    static ArrayList<Edge> edgeList = new ArrayList<>();
    static ArrayList<Integer> distance = new ArrayList<>(Collections.nCopies(MAX_VERTICES, 0));
    static ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(MAX_VERTICES, 0));
    static ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(MAX_VERTICES, false));

    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static boolean hasCycle(ArrayList<Integer>[] graph, int currentVertex) {
        visited.set(currentVertex, true);

        for (int adjacentVertex : graph[currentVertex]) {
            if (!visited.get(adjacentVertex)) {
                parent.set(adjacentVertex, currentVertex);
                distance.set(adjacentVertex, distance.get(currentVertex) + 1);
                if (hasCycle(graph, adjacentVertex))
                    return true;
            } else if (adjacentVertex != parent.get(currentVertex))
                return true;
        }
        return false;
    }

    static void findMinimumSpanningTree(Scanner scanner) {
        int edgeCount = scanner.nextInt();
        int vertexCount = scanner.nextInt();

        // Read edges
        for (int i = 0; i < edgeCount; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            edgeList.add(new Edge(source, destination, weight));
        }

        // Sort edges by weight
        edgeList.sort(Comparator.comparingInt(e -> e.weight));
//        edgeList.sort((e1, e2) -> Integer.compare(e1.weight, e2.weight));

        // Initialize adjacency lists
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[MAX_VERTICES];
        for (int i = 0; i < MAX_VERTICES; i++) {
            graph[i] = new ArrayList<>();
        }

        long minimumSpanningTreeWeight = 0;
        
        // Kruskal's algorithm implementation
        for (Edge edge : edgeList) {
            graph[edge.source].add(edge.destination);
            graph[edge.destination].add(edge.source);

            // Reset visited array for each cycle check
            Collections.fill(visited, false);

            if (hasCycle(graph, edge.source)) {
                // Remove edge if it creates a cycle
                graph[edge.source].removeLast();
                graph[edge.destination].removeLast();
//                graph[edge.source].remove(graph[edge.source].size() - 1);
//                graph[edge.destination].remove(graph[edge.destination].size() - 1);
            } else {
                minimumSpanningTreeWeight += edge.weight;
            }
        }
        System.out.println(minimumSpanningTreeWeight);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        findMinimumSpanningTree(scanner);
        scanner.close();
    }
}
```

The improvements made include:

1. Renamed the main class to `MinimumSpanningTree` to better reflect its purpose
2. Created an `Edge` class to represent graph edges
3. Used more descriptive variable names:
    - `adj` → `edgeList`
    - `d` → `distance`
    - `p` → `parent`
    - `N` → `MAX_VERTICES`
    - `ed` → `edgeCount`
    - `v` → `vertexCount`
    - `wt` → `weight`
4. Renamed methods for clarity:
    - `solve` → `findMinimumSpanningTree`
    - `dfs` → `hasCycle`
5. Improved variable naming in method parameters and loops
6. Added comments to explain the major steps in the algorithm

These changes make the code more readable and maintainable while preserving the original functionality of finding a minimum spanning tree using Kruskal's algorithm.
