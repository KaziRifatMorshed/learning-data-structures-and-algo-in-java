package LeetcodeSolving.leetcode.editor.en;/*
You are given a positive integer num. You may swap any two digits of num that 
have the same parity (i.e. both odd digits or both even digits). 

 Return the largest possible value of num after any number of swaps. 

 
 Example 1: 

 
Input: num = 1234
Output: 3412
Explanation: Swap the digit 3 with the digit 1, this results in the number 3214.

Swap the digit 2 with the digit 4, this results in the number 3412.
Note that there may be other sequences of swaps but it can be shown that 3412 
is the largest possible number.
Also note that we may not swap the digit 4 with the digit 1 since they are of 
different parities.
 

 Example 2: 

 
Input: num = 65875
Output: 87655
Explanation: Swap the digit 8 with the digit 6, this results in the number 85675
.
Swap the first digit 5 with the digit 7, this results in the number 87655.
Note that there may be other sequences of swaps but it can be shown that 87655 
is the largest possible number.
 

 
 Constraints: 

 
 1 <= num <= 10⁹ 
 

 Related Topics Sorting Heap (Priority Queue) 👍 644 👎 301

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class largestInteger {
    public static boolean isEven(int i) {
        i -= '0';
        return ((i % 2) == 0);
    }

    public static int largestInteger(int num) { // efficient
        PriorityQueue<Character> evenDigits = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Character> oddDigits = new PriorityQueue<>(Collections.reverseOrder());

        String n = String.valueOf(num);
        for (char c : n.toCharArray()) {
            if (isEven(c)) {
                evenDigits.add(c);
            } else {
                oddDigits.add(c);
            }
        }

//        String result = "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n.length(); i++) {
            if (isEven(n.charAt(i))) {
//                result += evenDigits.poll();
                result.append(evenDigits.poll());
            } else {
//                result += oddDigits.poll();
                result.append(oddDigits.poll());
            }
        }
        return Integer.parseInt(result.toString());
        /*
        Success:
            	Runtime:2 ms, faster than 72.08% of Java online submissions.
            	Memory Usage:40.1 MB, less than 98.79% of Java online submissions.
        */
    }

    public static int largestInteger_My(int num) {
        StringBuilder n = new StringBuilder(Integer.toString(num));
        ArrayList<Character> evenDigits = new ArrayList<>();
        ArrayList<Character> oddDigits = new ArrayList<>();
        String result = "";

        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if (isEven(c)) {
                evenDigits.add(c);
            } else {
                oddDigits.add(c);
            }
        }
        Collections.sort(evenDigits, Collections.reverseOrder());
        Collections.sort(oddDigits, Collections.reverseOrder());
        System.out.println(oddDigits);

        for (int i = 0, evenIdx = 0, oddIdx = 0; i < n.length(); i++) {
            if (isEven(n.charAt(i))) {
                result += evenDigits.get(evenIdx++);
            } else {
                result += oddDigits.get(oddIdx++);
            }
        }
        return Integer.parseInt(result);
        /*
        Success:
            	Runtime:7 ms, faster than 5.91% of Java online submissions.
	            Memory Usage:41 MB, less than 27.52% of Java online submissions.

        * */
    }

    public static void main(String[] args) {
        int result = largestInteger(1234);
        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
