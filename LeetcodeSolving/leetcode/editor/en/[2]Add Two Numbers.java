package LeetcodeSolving.leetcode.editor.en;//You are given two non-empty linked lists representing two non-negative integers.

// The digits are stored in reverse order, and each of their nodes contains a
//single digit. Add the two numbers and return the sum as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the
//number 0 itself.
//
//
// Example 1:
//
//
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
//
//
// Example 2:
//
//
//Input: l1 = [0], l2 = [0]
//Output: [0]
//
//
// Example 3:
//
//
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
//
//
//
// Constraints:
//
//
// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have leading
//zeros.
//
//
// Related Topics Linked List Math Recursion 👍 32311 👎 6480
//
//class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode() {
//    }
//
//    ListNode(int val) {
//        this.val = val;
//    }
//
//    ListNode(int val, ListNode next) {
//        this.val = val;
//        this.next = next;
//    }
//}

//leetcode submit region begin(Prohibit modification and deletion)


class Solution {

    private ListNode addTwoNum(ListNode l1, ListNode l2, ListNode sum, int carry) {
        int a, b;

        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null && l2 != null) {
            a = 0;
            b = l2.val;

            l2 = l2.next;
        } else if (l1 != null && l2 == null) {
            a = l1.val;
            b = 0;

            l1 = l1.next;
        } else {
            a = l1.val;
            b = l2.val;

            l1 = l1.next;
            l2 = l2.next;
        }

        int result = (a + b + carry);
        ListNode newNode = new ListNode((result % 10), null);
        if (sum != null) sum.next = newNode;
        else if (sum == null) sum = newNode;

        addTwoNum(l1, l2, newNode, (result / 10));
        return newNode;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNum(l1, l2, null, 0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
