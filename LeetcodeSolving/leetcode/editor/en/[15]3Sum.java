package LeetcodeSolving.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

 Notice that the solution set must not contain duplicate triplets.


 Example 1:


Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not
matter.


 Example 2:


Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.


 Example 3:


Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.



 Constraints:


 3 <= nums.length <= 3000
 -10⁵ <= nums[i] <= 10⁵


 Related Topics Array Two Pointers Sorting 👍 34659 👎 3221

*/

//leetcode submit region begin(Prohibit modification and deletion)
class threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answers = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // i < (n-2) ???
            // nums[i] <= 0 since sorted
            if (i > 0 && nums[i] == nums[i - 1]) continue; // consecutive i idx content duplicate

            int lp = i + 1; // left pointer
            int rp = nums.length - 1; // right pointer
            while (lp < rp) {
                int sum = nums[i] + nums[lp] + nums[rp];
                // based on p, move two pointers
                if (sum > 0) rp--;
                else if (sum < 0) lp++;
                else if (sum == 0) {
                    answers.add(Arrays.asList(nums[i], nums[lp], nums[rp]));

                    while (lp < rp && lp < nums.length - 1 && nums[lp] == nums[lp + 1]) lp++;
                    while (lp < rp && rp > 0 && nums[rp] == nums[rp - 1]) rp--;
                    lp++;
                    rp--;
                }
            }
        }
        return answers;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
