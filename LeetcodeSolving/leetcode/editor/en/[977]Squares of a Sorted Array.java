package LeetcodeSolving.leetcode.editor.en;//Given an integer array nums sorted in non-decreasing order, return an array of
//the squares of each number sorted in non-decreasing order.
//
//
// Example 1:
//
//
//Input: nums = [-4,-1,0,3,10]
//Output: [0,1,9,16,100]
//Explanation: After squaring, the array becomes [16,1,0,9,100].
//After sorting, it becomes [0,1,9,16,100].
//
//
// Example 2:
//
//
//Input: nums = [-7,-3,2,3,11]
//Output: [4,9,9,49,121]
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10⁴
// -10⁴ <= nums[i] <= 10⁴
// nums is sorted in non-decreasing order.
//
//
//
//Follow up: Squaring each element and sorting the new array is very trivial,
//could you find an O(n) solution using a different approach?
//
// Related Topics Array Two Pointers Sorting 👍 9482 👎 245


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionsortedSquares {
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int a = 0, b = len - 1;
             a >= b; ) {
            if (Math.abs(nums[a]) < Math.abs(nums[b])) {
                arrayList.add((int) Math.pow(nums[a], 2));
                a++;
            } else {
                arrayList.add((int) Math.pow(nums[b], 2));
                b--;
            }
        }

        int[] answer = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            answer[i] = arrayList.get(i);
        }
        return answer;
    } // INCOMPLETE
}
//leetcode submit region end(Prohibit modification and deletion)
