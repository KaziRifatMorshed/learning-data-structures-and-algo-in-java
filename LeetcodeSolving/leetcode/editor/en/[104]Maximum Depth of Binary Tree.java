package LeetcodeSolving.leetcode.editor.en;

/*
Given the root of a binary tree, return its maximum depth. 

 A binary tree's maximum depth is the number of nodes along the longest path 
from the root node down to the farthest leaf node. 

 
 Example 1: 
 
 
Input: root = [3,9,20,null,null,15,7]
Output: 3
 

 Example 2: 

 
Input: root = [1,null,2]
Output: 2
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 10⁴]. 
 -100 <= Node.val <= 100 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 1319
5 👎 248

*/

//leetcode submit region begin(Prohibit modification and deletion)
/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class maxDepth {
    public int maxDepth(TreeNode p) {
        if (p == null) {
            return 0;
        } else if (p.left == null && p.right == null) {
            return 1; // recursion have crossed one step, so, return 1
        } else if (p.left != null && p.right != null) {
            return 1 + Math.max(maxDepth(p.left), maxDepth(p.right));
        } else if (p.left == null && p.right != null) {
            return 1 + maxDepth(p.right);
        } else { // (p.left != null && p.right == null)
            return 1 + maxDepth(p.left);
        }
    } // DONE
} // DONE
//leetcode submit region end(Prohibit modification and deletion)
