package LeetcodeSolving.leetcode.editor.en;//Given an integer num, return the number of digits in num that divide num.
//
// An integer val divides nums if nums % val == 0.
//
//
// Example 1:
//
//
//Input: num = 7
//Output: 1
//Explanation: 7 divides itself, hence the answer is 1.
//
//
// Example 2:
//
//
//Input: num = 121
//Output: 2
//Explanation: 121 is divisible by 1, but not 2. Since 1 occurs twice as a digit,
//we return 2.
//
//
// Example 3:
//
//
//Input: num = 1248
//Output: 4
//Explanation: 1248 is divisible by all of its digits, hence the answer is 4.
//
// Constraints:
// 1 <= num <= 10⁹
// num does not contain 0 as one of its digits.
// Related Topics Math 👍 535 👎 33


//leetcode submit region begin(Prohibit modification and deletion)
class countDigits {

    static int countDigitsRecursive(int num, int k) {
        if (k == 0) {
            return 0;
        }
        int j = k % 10;
        if (num % j == 0) {
            return 1 + countDigitsRecursive(num, k / 10);
        } else {
            return 0 + countDigitsRecursive(num, k / 10);
        }
    } // done

    public int countDigits(int num) {
        return countDigitsRecursive(num, num);
    } // done
}
//leetcode submit region end(Prohibit modification and deletion)
