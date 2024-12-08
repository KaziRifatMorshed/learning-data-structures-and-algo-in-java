package LeetcodeSolving.leetcode.editor.en;

/**
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 * <p>
 * Remove list1's nodes from the aᵗʰ node to the bᵗʰ node, and put list2 in their
 * place.
 * <p>
 * The blue edges and nodes in the following figure indicate the result:
 * <p>
 * Build the result list and return its head.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * Output: [10,1,13,1000000,1000001,1000002,5]
 * Explanation: We remove the nodes 3 and 4 and put the entire list2 in their
 * place. The blue edges and nodes in the above figure indicate the result.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1
 * 000003,1000004]
 * Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
 * Explanation: The blue edges and nodes in the above figure indicate the result.
 * <p>
 * Constraints:
 * 3 <= list1.length <= 10⁴
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 10⁴
 * <p>
 * Related Topics Linked List 👍 2127 👎 223
 */

//leetcode submit region begin(Prohibit modification and deletion)

class mergeInBetween { // DONE
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        ListNode first = null, second = null, temp = list1, list1Head = list1;
        for (int i = 0; temp != null; temp = temp.next, i++) {

            if (i == a - 1) {
                first = temp;
            }
            if (i == b + 1) {
                second = temp;
                break;
            }
        }

        temp = list2;
        while (temp.next != null) {
            // যদি temp != next থাকত তাহলে, লাস্টে null পেতাম, যেটা লাস্ট নোডের নেক্সটের ভ্যাল্যু
            // কিন্তু আমি চাচ্ছি লাস্ট নোড এর লোকেশন, তাই, temo.next != null করতে হবে যাতে temp ভ্যারিয়েবল কোনোভাবেই null value পিক করতে না পারে
            temp = temp.next;
        }

        if (first != null) {
            first.next = list2;
        } else {
            list1Head = list2;
        }
        if (second != null) {
            temp.next = second;
        }

        return list1Head;
    }
} // Hurrah
//leetcode submit region end(Prohibit modification and deletion)
