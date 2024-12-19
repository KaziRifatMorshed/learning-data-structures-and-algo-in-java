package LeetcodeSolving.leetcode.editor.en;

/**
 * You are given a string s. The score of a string is defined as the sum of the
 * absolute difference between the ASCII values of adjacent characters.
 * <p>
 * Return the score of s.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "hello"
 * <p>
 * <p>
 * Output: 13
 * <p>
 * Explanation:
 * <p>
 * The ASCII values of the characters in s are: 'h' = 104, 'e' = 101, 'l' = 108,
 * 'o' = 111. So, the score of s would be |104 - 101| + |101 - 108| + |108 - 108| +
 * |108 - 111| = 3 + 7 + 0 + 3 = 13.
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "zaz"
 * <p>
 * <p>
 * Output: 50
 * <p>
 * Explanation:
 * <p>
 * The ASCII values of the characters in s are: 'z' = 122, 'a' = 97. So, the
 * score of s would be |122 - 97| + |97 - 122| = 25 + 25 = 50.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 2 <= s.length <= 100
 * s consists only of lowercase English letters.
 * <p>
 * <p>
 * Related Topics String 👍 636 👎 41
 */

//leetcode submit region begin(Prohibit modification and deletion)
class scoreOfString {
    static int scoreOfStringRecursive(String s, int a) {
        if (a >= s.length()) {
            return 0;
        }
        return Math.abs(s.charAt(a) - s.charAt(a - 1)) + scoreOfStringRecursive(s, a + 1);
    }

    public int scoreOfString(String s) {
        return scoreOfStringRecursive(s, 1);
    }
} // DONE
//leetcode submit region end(Prohibit modification and deletion)
