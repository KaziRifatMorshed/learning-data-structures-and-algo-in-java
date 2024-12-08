package LeetcodeSolving.leetcode.editor.en;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * <p>
 * Each root-to-leaf path in the tree represents a number.
 * <p>
 * <p>
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * <p>
 * <p>
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so
 * that the answer will fit in a 32-bit integer.
 * <p>
 * A leaf node is a node with no children.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [4,9,0,5,1]
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 9
 * The depth of the tree will not exceed 10.
 * <p>
 * <p>
 * Related Topics Tree Depth-First Search Binary Tree 👍 8204 👎 144
 */

//leetcode submit region begin(Prohibit modification and deletion)

class sumNumbers {

//    static class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode() {
//        }
//
//        TreeNode(int val) {
//            this.val = val;
//        }
//
//        TreeNode(int val, TreeNode left, TreeNode right) {
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//    }

    private static double number(TreeNode treeNode, int n) {
        if (treeNode == null) {
            return n;
        }

        int num = 10 * n + treeNode.val;

        if ((treeNode.left != null && treeNode.right == null)) {
            return number(treeNode.left, num);
        }
        if ((treeNode.left == null && treeNode.right != null)) {
            return number(treeNode.right, num);
        }

        return number(treeNode.left, num) + number(treeNode.right, num);
    } // DONE

    public int sumNumbers(TreeNode root) {
        double result = number(root, 0) / 2;
        return (int) result;
    }
} // HURRAH
//leetcode submit region end(Prohibit modification and deletion)
