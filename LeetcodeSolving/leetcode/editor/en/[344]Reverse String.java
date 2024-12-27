package LeetcodeSolving.leetcode.editor.en;/*
Write a function that reverses a string. The input string is given as an array 
of characters s. 

 You must do this by modifying the input array in-place with O(1) extra memory. 


 
 Example 1: 
 Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
 
 Example 2: 
 Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
 
 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s[i] is a printable ascii character. 
 

 Related Topics Two Pointers String 👍 8814 👎 1186

*/

//leetcode submit region begin(Prohibit modification and deletion)
class reverseString {
    public void reverseString(char[] s) {
        for (int i = 0; i < (s.length / 2); i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    } // DONE
}
//leetcode submit region end(Prohibit modification and deletion)
