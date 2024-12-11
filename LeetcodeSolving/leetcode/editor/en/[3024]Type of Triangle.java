package LeetcodeSolving.leetcode.editor.en;//You are given a 0-indexed integer array nums of size 3 which can form the sides
//of a triangle.
//
//
// A triangle is called equilateral if it has all sides of equal length.
// A triangle is called isosceles if it has exactly two sides of equal length.
// A triangle is called scalene if all its sides are of different lengths.
//
//
// Return a string representing the type of triangle that can be formed or "none"
//if it cannot form a triangle.
//
//
// Example 1:
//
//
//Input: nums = [3,3,3]
//Output: "equilateral"
//Explanation: Since all the sides are of equal length, therefore, it will form
//an equilateral triangle.
//
//
// Example 2:
//
//
//Input: nums = [3,4,5]
//Output: "scalene"
//Explanation:
//a + b = 3 + 4 = 7, which is greater than c = 5.
//a + c = 3 + 5 = 8, which is greater than b = 4.
//b + c = 4 + 5 = 9, which is greater than a = 3.
//Since the sum of the two sides is greater than the third side for all three
//cases, therefore, it can form a triangle.
//As all the sides are of different lengths, it will form a scalene triangle.
//
//
//
// Constraints:
//
//
// nums.length == 3
// 1 <= nums[i] <= 100
//
//
// Related Topics Array Math Sorting 👍 108 👎 17


//leetcode submit region begin(Prohibit modification and deletion)
class triangleType {
    public String triangleType(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];

        if (a == b && b == c) {
            return "equilateral";
        } else if (a + b > c && b + c > a && c + a > b) {
            if (a == b || b == c || c == a) return "isosceles";  // ----- pain :(
            return "scalene";
        } else {
            return "none";
        }
    } // DONE
}
//leetcode submit region end(Prohibit modification and deletion)
