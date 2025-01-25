package LeetcodeSolving.leetcode.editor.en;/*
Given the root of a binary tree and an integer targetSum, return all root-to-
leaf paths where the sum of the node values in the path equals targetSum. Each 
path should be returned as a list of the node values, not node references. 

 A root-to-leaf path is a path starting from the root and ending at any leaf 
node. A leaf is a node with no children. 

 
 Example 1: 
 
 
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
 

 Example 2: 
 
 
Input: root = [1,2,3], targetSum = 5
Output: []
 

 Example 3: 

 
Input: root = [1,2], targetSum = 0
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 5000]. 
 -1000 <= Node.val <= 1000 
 -1000 <= targetSum <= 1000 
 

 Related Topics Backtracking Tree Depth-First Search Binary Tree 👍 8175 👎 160

[5,4,8,11,null,13,4,7,2,null,null,5,1]
22
[5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1]
22
[1,2,3]
5
[1,2]
0

*/

import java.util.ArrayList;
import java.util.List;
//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    private void hasPathSumRecursive(TreeNode node, List<List<Integer>> listOfResults, List<Integer> currentPath, int targetSum, int currentSum) {
        if (node == null) return;
        currentPath.add(node.val);
        if (node.left == null && node.right == null) {
            if (node.val + currentSum == targetSum) {
                List<Integer> temp = new ArrayList<>(currentPath);
                listOfResults.add(temp);
                return;
            }
        }
        hasPathSumRecursive(node.left, listOfResults, currentPath, targetSum, node.val + currentSum);
//        if (!currentPath.isEmpty()) currentPath.removeLast();
        hasPathSumRecursive(node.right, listOfResults, currentPath, targetSum, node.val + currentSum);
        if (!currentPath.isEmpty()) currentPath.removeLast();
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> listOfResults = new ArrayList<>();
        List<Integer> singlePath = new ArrayList<>();
        hasPathSumRecursive(root, listOfResults, singlePath, targetSum, 0);
        return listOfResults;
    }

    public static void main(String[] args) {

    }
}
//leetcode submit region end(Prohibit modification and deletion)
