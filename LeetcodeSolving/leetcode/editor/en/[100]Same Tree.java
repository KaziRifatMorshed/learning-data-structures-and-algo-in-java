package LeetcodeSolving.leetcode.editor.en;

/**
 * Given the roots of two binary trees p and q, write a function to check if they
 * are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical,
 * and the nodes have the same value.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in both trees is in the range [0, 100].
 * -10⁴ <= Node.val <= 10⁴
 * <p>
 * <p>
 * Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 1184
 * 0 👎 255
 */

//leetcode submit region begin(Prohibit modification and deletion)

class isSameTree {

    static class TreeNode {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if ((p == null || q == null)) {
            return p == q;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)