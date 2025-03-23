package LeetcodeSolving.leetcode.editor.en;/*
Given an integer x, return true if x is a palindrome, and false otherwise. 

 
 Example 1: 

 
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
 

 Example 2: 

 
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 1
21-. Therefore, it is not a palindrome.
 

 Example 3: 

 
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore, it is not a palindrome.
 

 
 Constraints: 

 
 -2³¹ <= x <= 2³¹ - 1 
 

 
Follow up: Could you solve it without converting the integer to a string?

 Related Topics Math 👍 13730 👎 2803

*/

//leetcode submit region begin(Prohibit modification and deletion)
class isPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int n = 0, xx = x;
        while (xx != 0) {
            n = (n * 10) + (xx % 10);
            xx /= 10;
        }
        if (x == n) return true;
        return false;
    }
} // DONE
//leetcode submit region end(Prohibit modification and deletion)
