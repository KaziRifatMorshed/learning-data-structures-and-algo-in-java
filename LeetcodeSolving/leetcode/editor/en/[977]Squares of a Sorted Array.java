package LeetcodeSolving.leetcode.editor.en;
//Given an integer array nums sorted in non-decreasing order, return an array of
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
//could you find an O(n) solution using left different approach?
//
// Related Topics Array Two Pointers Sorting 👍 9482 👎 245


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionsortedSquares {
    public static void main(String[] args) {
        int[] arr = {1, 2};
        int[] result = sortedSquares(arr);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] answer = new int[len];
        int right = 0, index = 0;

        for (int i = 0; i < len; i++) {
            right = i;
            if (nums[i] > 0) {
                break;
            }
        }

        int left = right - 1;
        if (left < 0) left = 0;

        System.out.println(left + " " + right);
        while (left >= 0 && right < len) {
            int leftElement = (int) (nums[left] * nums[left]);
            int rightElement = (int) (nums[right] * nums[right]);

            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                answer[index++] = leftElement;
                left--;
            } else {
                answer[index++] = rightElement;
                right++;
                if (len == 1) left--;
            }
        }

        System.out.println(left + " " + right);
        while (left >= 0 && index < len) {
            answer[index++] = (int) (nums[left] * nums[left]);
            left--;
        }

        while (right < len && index < len) {
            answer[index++] = (int) (nums[right] * nums[right]);
            right++;
        }


        return answer;
    } // INCOMPLETE
}
//leetcode submit region end(Prohibit modification and deletion)
