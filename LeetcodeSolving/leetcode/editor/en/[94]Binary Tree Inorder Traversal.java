package LeetcodeSolving.leetcode.editor.en; /**
 * Given the root of a binary tree, return the inorder traversal of its nodes'
 * values.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,null,2,3]
 * <p>
 * <p>
 * Output: [1,3,2]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * <p>
 * <p>
 * Output: [4,2,6,5,7,1,3,9,8]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: root = []
 * <p>
 * <p>
 * Output: []
 * <p>
 * Example 4:
 * <p>
 * <p>
 * Input: root = [1]
 * <p>
 * <p>
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * <p>
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * Related Topics Stack Tree Depth-First Search Binary Tree 👍 13684 👎 810
 */

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class inorderTraversal {

    public static void inorderTraversalRecursive(TreeNode n, List<Integer> list) {
        if (n == null) return;
        int value = n.val;
        inorderTraversalRecursive(n.left, list);
        list.add(value);
        inorderTraversalRecursive(n.right, list);
    } // done

    public static List<Integer> inorderTraversal_recursive(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorderTraversalRecursive(root, list);
        return list;
    } // done

    public static List<Integer> inorderTraversal_iterative(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode currentNode = stack.pop();
            list.add(currentNode.val);
//            root = root.right; // incorrect
            root = currentNode.right;
        }
        return list;
    } // DONE
}
//leetcode submit region end(Prohibit modification and deletion)
