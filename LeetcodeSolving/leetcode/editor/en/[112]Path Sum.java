package LeetcodeSolving.leetcode.editor.en;/*
Given the root of a binary tree and an integer targetSum, return true if the 
tree has a root-to-leaf path such that adding up all the values along the path 
equals targetSum. 

 A leaf is a node with no children. 

 
 Example 1: 
 
 
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.
 

 Example 2: 
 
 
Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There are two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.
 

 Example 3: 

 
Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 5000]. 
 -1000 <= Node.val <= 1000 
 -1000 <= targetSum <= 1000 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 9997
 👎 1150

*/

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
class hasPathSum {
    private boolean hasPathSumRecursive(TreeNode node, int targetSum, int currentSum) {
        if (node == null) return false;
        if (node.left == null && node.right == null) {
            if (node.val + currentSum == targetSum) return true;
        }
        return hasPathSumRecursive(node.left, targetSum, node.val + currentSum) |
                hasPathSumRecursive(node.right, targetSum, node.val + currentSum);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSumRecursive(root, targetSum, 0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
