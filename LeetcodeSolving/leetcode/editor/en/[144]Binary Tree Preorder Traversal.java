package LeetcodeSolving.leetcode.editor.en;

/*
Given the root of a binary tree, return the preorder traversal of its nodes' 
values. 

 
 Example 1: 

 
 Input: root = [1,null,2,3] 
 

 Output: [1,2,3] 

 Explanation: 

 

 Example 2: 

 
 Input: root = [1,2,3,4,5,null,8,null,null,6,7,9] 
 

 Output: [1,2,4,5,6,7,3,8,9] 

 Explanation: 

 

 Example 3: 

 
 Input: root = [] 
 

 Output: [] 

 Example 4: 

 
 Input: root = [1] 
 

 Output: [1] 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 100]. 
 -100 <= Node.val <= 100 
 

 
 Follow up: Recursive solution is trivial, could you do it iteratively? 

 Related Topics Stack Tree Depth-First Search Binary Tree 👍 8199 👎 215

*/

import java.util.ArrayList;
import java.util.List;

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
class preorderTraversal {
    private void preorderTraversalRecursive(TreeNode node, List<Integer> list) {
        if (node == null) return;
        int value = node.val;
        list.add(value);
        preorderTraversalRecursive(node.left, list);
        preorderTraversalRecursive(node.right, list);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        preorderTraversalRecursive(root, list);
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
