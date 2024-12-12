package LeetcodeSolving.leetcode.editor.en;

/**
 * You are given a positive integer array nums.
 * <p>
 * <p>
 * The element sum is the sum of all the elements in nums.
 * The digit sum is the sum of all the digits (not necessarily distinct) that
 * appear in nums.
 * <p>
 * <p>
 * Return the absolute difference between the element sum and digit sum of nums.
 * <p>
 * Note that the absolute difference between two integers x and y is defined as |
 * x - y|.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,15,6,3]
 * Output: 9
 * Explanation:
 * The element sum of nums is 1 + 15 + 6 + 3 = 25.
 * The digit sum of nums is 1 + 1 + 5 + 6 + 3 = 16.
 * The absolute difference between the element sum and digit sum is |25 - 16| = 9.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Explanation:
 * The element sum of nums is 1 + 2 + 3 + 4 = 10.
 * The digit sum of nums is 1 + 2 + 3 + 4 = 10.
 * The absolute difference between the element sum and digit sum is |10 - 10| = 0.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 2000
 * 1 <= nums[i] <= 2000
 * <p>
 * <p>
 * Related Topics Array Math 👍 714 👎 19
 */

//leetcode submit region begin(Prohibit modification and deletion)
class differenceOfSum {
    public static int digitSum(int a) {
        if (a == 0) return 0;
        return (a % 10) + digitSum(a / 10);
    }

    public int differenceOfSum(int[] nums) {
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < nums.length; i++) {
            sum1 += nums[i];
            sum2 += digitSum(nums[i]);
        }
        return Math.abs(sum1 - sum2);
    } // done
}
//leetcode submit region end(Prohibit modification and deletion)
