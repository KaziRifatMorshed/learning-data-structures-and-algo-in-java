package LeetcodeSolving.leetcode.editor.en;/*
Given a signed 32-bit integer x, return x with its digits reversed. If 
reversing x causes the value to go outside the signed 32-bit integer range [-2³¹, 2³¹ - 1
], then return 0. 

 Assume the environment does not allow you to store 64-bit integers (signed or 
unsigned). 

 
 Example 1:
Input: x = 123
Output: 321
 

 Example 2:
Input: x = -123
Output: -321
 

 Example 3:
Input: x = 120
Output: 21
 

 
 Constraints:
 -2³¹ <= x <= 2³¹ - 1 
 

 Related Topics Math 👍 13703 👎 13704

*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solutions {
    //    2^31 = 2147483648
    public static int reverse(int x) {
        int result = 0;
        int maxInt = Integer.MAX_VALUE;
        int minInt = Integer.MIN_VALUE;
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x *= -1;
        }
        while (x != 0) {

            result = result * 10 + x % 10;
            x /= 10;
            System.out.println("result = " + result + " , x = " + x);
        }
        if (isNegative) result *= -1;
        return result;
    }

    public static void main(String[] args) {
        reverse(1534236469);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*
2147483648
964632435
* */