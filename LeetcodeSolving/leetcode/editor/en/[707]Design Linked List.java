package LeetcodeSolving.leetcode.editor.en;/*
Design your implementation of the linked list. You can choose to use a singly 
or doubly linked list. A node in a singly linked list should have two attributes: 
val and next. val is the value of the current node, and next is a pointer/
reference to the next node. If you want to use the doubly linked list, you will need 
one more attribute prev to indicate the previous node in the linked list. Assume 
all nodes in the linked list are 0-indexed. 

 Implement the MyLinkedList class: 

 
 MyLinkedList() Initializes the MyLinkedList object. 
 int get(int index) Get the value of the indexᵗʰ node in the linked list. If 
the index is invalid, return -1. 
 void addAtHead(int val) Add a node of value val before the first element of 
the linked list. After the insertion, the new node will be the first node of the 
linked list. 
 void addAtTail(int val) Append a node of value val as the last element of the 
linked list. 
 void addAtIndex(int index, int val) Add a node of value val before the indexᵗʰ 
node in the linked list. If index equals the length of the linked list, the 
node will be appended to the end of the linked list. If index is greater than the 
length, the node will not be inserted. 
 void deleteAtIndex(int index) Delete the indexᵗʰ node in the linked list, if 
the index is valid. 
 

 
 Example 1: 

 
Input
["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex",
 "get"]
[[], [1], [3], [1, 2], [1], [1], [1]]
Output
[null, null, null, null, 2, null, 3]

Explanation
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
myLinkedList.get(1);              // return 2
myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
myLinkedList.get(1);              // return 3
 

 
 Constraints: 

 
 0 <= index, val <= 1000 
 Please do not use the built-in LinkedList library. 
 At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and 
deleteAtIndex. 
 

 Related Topics Linked List Design 👍 2732 👎 1642

*/

//leetcode submit region begin(Prohibit modification and deletion)
class MyLinkedList {

    static class Node {
        int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(int value) {
            this.value = value;
            this.next = null;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head = null, tail = null;
    private int size = 0;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void updateTail() {
        Node p = this.head;
        for (; p != null; p = p.next) {
            if (p.next == null) tail = p;
        }
    } // seems vulnerable

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void printWholeLinkedList() {
        for (Node p = head; p != null; p = p.getNext()) {
            System.out.print(p.getValue() + " ");
        }
        System.out.println();
    }

    public int get(int index) {
        printWholeLinkedList();
        if (isEmpty() || index < 0 || index >= size) return -1;
        Node p = head;
        for (int i = 0; p != null; i++, p = p.next) {
            if (i == index) return p.getValue();
        }
        return -1;
    } // seems OK but confused

    public void addAtHead(int val) {
        if (isEmpty()) {
            head = tail = new Node(val);
        } else {
            Node newNode = new Node(val, head);
            head = newNode;
        }
        size++;
    } // seems OK

    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    } // seems OK

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        Node newNode = new Node(val);
        if (index == 0) { // add at head
            addAtHead(val);
            return;
        } else if (index == size) { // add tail
            addAtTail(val);
            return; // tail contains size increment so return here
        } else {
            Node prev = this.head, curr = this.head;
            for (int i = 0; i < size && curr != null; i++, curr = curr.getNext()) {
                if (i == index) {
                    newNode.setNext(curr);
                    prev.setNext(newNode);
                    break;
                }
                prev = curr;
            }
        }
        size++;
        printWholeLinkedList();
    } // seems ok

    public void deleteAtIndex(int index) {
        if (index >= 0 && index < size && !isEmpty()) {
            if (index == 0) { // delete head
                this.head = head.getNext();
            } else {
                Node prev = this.head, curr = this.head;
                for (int i = 0; i < size && curr != null; i++, curr = curr.getNext()) {
                    if (i == index) {
                        prev.setNext(curr.getNext());
                        break;
                    }
                    prev = curr;
                }
            }
            size--;
        }
        printWholeLinkedList();
    } // seems OK
}

/*
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
//leetcode submit region end(Prohibit modification and deletion)
