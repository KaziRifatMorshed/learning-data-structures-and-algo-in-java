package LeetcodeSolving.leetcode.editor.en;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "()"
 * <p>
 * <p>
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "()[]{}"
 * <p>
 * <p>
 * Output: true
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: s = "(]"
 * <p>
 * <p>
 * Output: false
 * <p>
 * Example 4:
 * <p>
 * <p>
 * Input: s = "([])"
 * <p>
 * <p>
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 10⁴
 * s consists of parentheses only '()[]{}'.
 * <p>
 * <p>
 * Related Topics String Stack 👍 24584 👎 1812
 */

//leetcode submit region begin(Prohibit modification and deletion)
class ValidParenthesis {
    public boolean isValid(String equation) {
        Stack<Character> stack_of_braces = new Stack<Character>();
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if (ch == '[' || ch == '{' || ch == '(') { /*opening, pushing*/
                stack_of_braces.push(ch);
                continue;
            } else if (ch == ']' || ch == '}' || ch == ')') {
                if (stack_of_braces.isEmpty()) {
                    return false;
                }
                char top_ch = stack_of_braces.peek(); // --------- age empty checking, ta por baki kaj
                if ((top_ch == '[' && ch == ']') || (top_ch == '{' && ch == '}') || (top_ch == '(' && ch == ')')) {
                    stack_of_braces.pop();
                } else {
                    return false;
                }
            }
        }
        return stack_of_braces.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
