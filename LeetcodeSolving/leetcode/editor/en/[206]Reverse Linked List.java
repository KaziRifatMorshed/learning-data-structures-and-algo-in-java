package LeetcodeSolving.leetcode.editor.en;


import java.util.Stack;

/**
 * Given the head of a singly linked list, reverse the list, and return the
 * reversed list.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2]
 * Output: [2,1]
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 * <p>
 * <p>
 * <p>
 * Follow up: A linked list can be reversed either iteratively or recursively.
 * Could you implement both?
 * <p>
 * Related Topics Linked List Recursion 👍 22135 👎 474
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class reverseList {


    private ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;

        // age deep e jabe, tar por linking er kaj hobe
        ListNode q = reverseList(head.next); // q er kichu kora hocche na, q ke bar bar pass kroe deoa hocche
        (head.next).next = head;
        (head.next) = null;

        return q;
    }


    public ListNode reverseListIterative(ListNode head) {
        ListNode newHead = null;
//        Stack<ListNode> stack = new Stack<>();

        ListNode currentNode = head;
        for (ListNode nextNode = null
             ; currentNode != null || currentNode.next != null;
             currentNode = nextNode) {

            nextNode = currentNode.next;
            newHead = currentNode;
            currentNode.next = null;
//            if (nextNode != null) {
//                nextNode.next = currentNode;
//            }

        }
        return newHead;
    } // INCOMPLETE

    public ListNode reverseList(ListNode head) {
        return reverseListRecursive(head);
//        return reverseListIterative(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
