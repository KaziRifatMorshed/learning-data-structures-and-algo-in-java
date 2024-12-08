package LeetcodeSolving.leetcode.editor.en;

//Given an integer num, repeatedly add all its digits until the result has only
//one digit, and return it.
//
//
// Example 1:
//
//
//Input: num = 38
//Output: 2
//Explanation: The process is
//38 --> 3 + 8 --> 11
//11 --> 1 + 1 --> 2
//Since 2 has only one digit, return it.
//
//
// Example 2:
//
//
//Input: num = 0
//Output: 0
//
//
//
// Constraints:
//
//
// 0 <= num <= 2³¹ - 1
//
//
//
// Follow up: Could you do it without any loop/recursion in O(1) runtime?
//
// Related Topics Math Simulation Number Theory 👍 4943 👎 1945


//leetcode submit region begin(Prohibit modification and deletion)
class addDigits { // DONE
    public int addDigits(int num) {
        int result = 0;
        while (true) {
            result += num % 10;
            num /= 10;
//            System.out.println("result = " + result + " num = " + num);
            if (num == 0 && 0 <= result && result <= 9) {
                return result;
            }
            if (num == 0) {
                num = result;
                result = 0;
            }
        }
    } // DONE
}
//leetcode submit region end(Prohibit modification and deletion)
