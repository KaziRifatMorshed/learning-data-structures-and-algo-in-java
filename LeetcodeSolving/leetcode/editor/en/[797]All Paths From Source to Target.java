package LeetcodeSolving.leetcode.editor.en;/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find 
all possible paths from node 0 to node n - 1 and return them in any order. 

 The graph is given as follows: graph[i] is a list of all nodes you can visit 
from node i (i.e., there is a directed edge from node i to node graph[i][j]). 

 
 Example 1: 
 
 
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 

 Example 2: 
 
 
Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 

 
 Constraints: 

 
 n == graph.length 
 2 <= n <= 15 
 0 <= graph[i][j] < n 
 graph[i][j] != i (i.e., there will be no self-loops). 
 All the elements of graph[i] are unique. 
 The input graph is guaranteed to be a DAG. 
 

 Related Topics Backtracking Depth-First Search Breadth-First Search Graph 👍 73
81 👎 149

*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class allPathsSourceTarget {

    static void DFS(int[][] graph, int node, int dest, List<Integer> aPath, List<List<Integer>> paths) {
        aPath.add(node);
        if (node == dest) {
            paths.add(new ArrayList<>(aPath));
//            aPath.removeLast();
        } else {
            for (Integer adjacent : graph[node]) {
                DFS(graph, adjacent, dest, aPath, paths);
                aPath.removeLast();
            }
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        int n = graph.length;
        DFS(graph, 0, n - 1, new ArrayList<Integer>(), paths);
        return paths;
    }
} // DONE
//leetcode submit region end(Prohibit modification and deletion)
