package LeetcodeSolving.leetcode.editor.en;

// * Given an array of strings words, return the first palindromic string in the
// * array. If there is no such string, return an empty string "".
// * <p>
// * A string is palindromic if it reads the same forward and backward.
// * <p>
// * <p>
// * Example 1:
// * <p>
// * <p>
// * Input: words = ["abc","car","ada","racecar","cool"]
// * Output: "ada"
// * Explanation: The first string that is palindromic is "ada".
// * Note that "racecar" is also palindromic, but it is not the first.
// * <p>
// * <p>
// * Example 2:
// * <p>
// * <p>
// * Input: words = ["notapalindrome","racecar"]
// * Output: "racecar"
// * Explanation: The first and only string that is palindromic is "racecar".
// * <p>
// * <p>
// * Example 3:
// * <p>
// * <p>
// * Input: words = ["def","ghi"]
// * Output: ""
// * Explanation: There are no palindromic strings, so the empty string is returned.
// * <p>
// * <p>
// * <p>
// * Constraints:
// * <p>
// * <p>
// * 1 <= words.length <= 100
// * 1 <= words[i].length <= 100
// * words[i] consists only of lowercase English letters.
// * <p>
// * <p>
// * Related Topics Array Two Pointers String 👍 1555 👎 55
//

//leetcode submit region begin(Prohibit modification and deletion)
class firstPalindrome {

    private boolean isPalindromic(String s) {
        for (int i = 0; i < (s.length() / 2); i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    } // DONE

    public String firstPalindrome(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (isPalindromic(words[i])) return words[i];
        }
        return "";
    }
} // DONE
//leetcode submit region end(Prohibit modification and deletion)
