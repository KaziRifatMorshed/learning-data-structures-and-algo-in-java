package LeetcodeSolving.leetcode.editor.en;//A pangram is a sentence where every letter of the English alphabet appears at
//least once. 
//
// Given a string sentence containing only lowercase English letters, return 
//true if sentence is a pangram, or false otherwise. 
//
// 
// Example 1: 
//
// 
//Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
//Output: true
//Explanation: sentence contains at least one of every letter of the English 
//alphabet.
// 
//
// Example 2: 
//
// 
//Input: sentence = "leetcode"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= sentence.length <= 1000 
// sentence consists of lowercase English letters. 
// 
//
// Related Topics Hash Table String 👍 2813 👎 58


//leetcode submit region begin(Prohibit modification and deletion)
class checkIfPangram {
    public boolean checkIfPangram(String sentence) {
        int[] freqMap = new int[26];
        int count = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            int c = ch - 'a';
            freqMap[c]++;
            if (freqMap[c] == 1) {
                count++;
            }
            if (count == 26) return true;
        }
        return false;
    } // DONE
}
//leetcode submit region end(Prohibit modification and deletion)
