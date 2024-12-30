package LeetcodeSolving.leetcode.editor.en;

/*
Given an input string s, reverse the order of the words. 

 A word is defined as a sequence of non-space characters. The words in s will 
be separated by at least one space. 

 Return a string of the words in reverse order concatenated by a single space. 

 Note that s may contain leading or trailing spaces or multiple spaces between 
two words. The returned string should only have a single space separating the 
words. Do not include any extra spaces. 

 
 Example 1: 

 
Input: s = "the sky is blue"
Output: "blue is sky the"
 

 Example 2: 

 
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

 

 Example 3: 

 
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single 
space in the reversed string.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 
 There is at least one word in s. 
 

 
 Follow-up: If the string data type is mutable in your language, can you solve 
it in-place with O(1) extra space? 

 Related Topics Two Pointers String 👍 8955 👎 5289

*/

import java.util.Arrays;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class reverseWords {
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        String[] splitted = s.trim().split(" +");

        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].indexOf(' ') == -1) {
                stack.push(splitted[i]);
            }
        }
        System.out.println(Arrays.toString(splitted));

        StringBuilder result = new StringBuilder();
        while (!stack.empty()) {
            result.append(stack.pop());
            if (!stack.isEmpty()) result.append(' ');
        }
        return result.toString();
    } // DONE
}
//leetcode submit region end(Prohibit modification and deletion)
