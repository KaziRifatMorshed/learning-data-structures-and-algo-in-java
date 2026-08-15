package LeetcodeSolving.leetcode.editor.en;

/**
 * Given an integer array nums, handle multiple queries of the following type:
 * <p>
 * <p>
 * Calculate the sum of the elements of nums between indices left and right
 * inclusive where left <= right.
 * <p>
 * <p>
 * Implement the NumArray class:
 * <p>
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums
 * between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... +
 * nums[right]).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁴
 * -10⁵ <= nums[i] <= 10⁵
 * 0 <= left <= right < nums.length
 * At most 10⁴ calls will be made to sumRange.
 * <p>
 * <p>
 * Related Topics Array Design Prefix Sum 👍 3919 👎 2024
 */

//leetcode submit region begin(Prohibit modification and deletion)
class NumArray {

    private int[] arr = null;

    private int[] prefixSumInSpace(int[] inputArr) {
        for (int i = 1; i < inputArr.length; i++) {
            inputArr[i] += inputArr[i - 1];
        }
        return inputArr;
    }

    public NumArray(int[] nums) {
        arr = prefixSumInSpace(nums);
    }

    public int sumRange(int left, int right) {
        if (left == 0) return arr[right];
        return arr[right] - arr[left - 1];
    }
} // DONE

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)
