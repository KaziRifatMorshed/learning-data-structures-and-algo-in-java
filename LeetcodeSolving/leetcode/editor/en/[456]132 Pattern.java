package LeetcodeSolving.leetcode.editor.en;
/*
Given an array of n integers nums, a 132 pattern is a subsequence of three 
integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < 
nums[j]. 

 Return true if there is a 132 pattern in nums, otherwise, return false. 

 
 Example 1: 

 
Input: nums = [1,2,3,4]
Output: false
Explanation: There is no 132 pattern in the sequence.
 

 Example 2: 

 
Input: nums = [3,1,4,2]
Output: true
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 

 Example 3: 

 
Input: nums = [-1,3,2,0]
Output: true
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0
] and [-1, 2, 0].
 

 
 Constraints: 

 
 n == nums.length 
 1 <= n <= 2 * 10⁵ 
 -10⁹ <= nums[i] <= 10⁹ 
 

 Related Topics Array Binary Search Stack Monotonic Stack Ordered Set 👍 7318 👎
 440

*/

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionbinarySearch {

//    private boolean binarySearch(int[] arr, int startIdx, int endIdx, int key) {
//        if (startIdx < endIdx) {
//            int midIdx = startIdx + (endIdx - startIdx) / 2;
//            if (arr[midIdx] == key) {
//                return true;
//            }
//            return binarySearch(arr, startIdx, midIdx, key) | binarySearch(arr, midIdx + 1, endIdx, key);
//        }
//        return false;
//    }

    private int binarySearch(int[] arr, int startIdx, int endIdx, int key, char comp) {
        if (startIdx < endIdx) {
            int midIdx = startIdx + (endIdx - startIdx) / 2;
            if (comp == '<') {
                if (arr[midIdx] < key) return midIdx;
            } else if (comp == '>') {
                if (arr[midIdx] > key) return midIdx;
            } else if (arr[midIdx] == key) {
                return midIdx;
            }
            return binarySearch(arr, startIdx, midIdx, key, comp) | binarySearch(arr, midIdx + 1, endIdx, key, comp);
        }
        return -1; // not found
    }

    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {

        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
