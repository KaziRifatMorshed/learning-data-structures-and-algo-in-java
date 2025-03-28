package LeetcodeSolving.leetcode.editor.en;

/*
Given a binary tree, find its minimum depth. 

 The minimum depth is the number of nodes along the shortest path from the root 
node down to the nearest leaf node. 

 Note: A leaf is a node with no children. 

 
 Example 1: 
 
 
Input: root = [3,9,20,null,null,15,7]
Output: 2
 

 Example 2: 

 
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 10⁵]. 
 -1000 <= Node.val <= 1000 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 7424
 👎 1320

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
class minDepth {
    public int minDepth(TreeNode p) {
        if (p == null) {
            return 0;
        } else if (p.left == null && p.right == null) {
            return 1; // recursion have crossed one step, so, return 1
        } else if (p.left != null && p.right != null) {
            return 1 + Math.min(minDepth(p.left), minDepth(p.right));
        } else if (p.left == null && p.right != null) {
            return 1 + minDepth(p.right);
        } else { // (p.left != null && p.right == null)
            return 1 + minDepth(p.left);
        }
// DONE, similar as MaxDepth one
    }
}
//leetcode submit region end(Prohibit modification and deletion)
