package LeetcodeSolving.leetcode.editor.en;

/**
 * Given an integer array nums of length n, you want to create an array ans of
 * length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-
 * indexed).
 * <p>
 * Specifically, ans is the concatenation of two nums arrays.
 * <p>
 * Return the array ans.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,1]
 * Output: [1,2,1,1,2,1]
 * Explanation: The array ans is formed as follows:
 * - ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
 * - ans = [1,2,1,1,2,1]
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,3,2,1]
 * Output: [1,3,2,1,1,3,2,1]
 * Explanation: The array ans is formed as follows:
 * - ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
 * - ans = [1,3,2,1,1,3,2,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * n == nums.length
 * 1 <= n <= 1000
 * 1 <= nums[i] <= 1000
 * <p>
 * <p>
 * Related Topics Array Simulation 👍 3430 👎 403
 */

//leetcode submit region begin(Prohibit modification and deletion)
class getConcatenation {
    public int[] getConcatenation(int[] nums) {
        int k = nums.length;
        int[] ans = new int[2 * k];
        for (int i = 0; i < k; i++) {
            ans[i] = ans[i + k] = nums[i];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
