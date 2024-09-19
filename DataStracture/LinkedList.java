package DataStracture;

import static DataStracture.Node.*;

// NOTE: Sort_Singly_Linked_List() is incomplete

class Node {
    private int value;
    private Node next = null;

    Node(int v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return STR."Node{value=\{value}, next=\{next}\{'}'}";
    }

//    public Node(int v, Node n) { // THIS IS A BAD IDEA COZ WE DON.T KNOW WHO WILL BE NEXT in future

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    Node getNext() {
        return next;
    }

    void setNext(Node next) {
        this.next = next;
    }

    static void PrintWholeLinkedList(Node p) {
//        while (p.next != null) { // p.next হবে না । কারণ লুপের লাস্টে ইঙ্ক্রিমেন্ট করে নেক্সট কে ভ্যারিএবলে নিয়ে নেওয়ার আমাকে লুপ-স্টার্টিং কন্ডিশন চেকিং এ p.next চেক করা লাগবে না, p নাল কি না চেক করলেই হবে ।
        System.out.print("Printing Whole Linked List: ");
        while (p != null) {
            System.out.print(STR."\{p.value} ");
            p = p.next;
        }
        System.out.println();
    } // DONE

    static void PrintWholeLinkedListREVERSELY(Node p) {
        if (p == null) {
            return;
        }
        PrintWholeLinkedListREVERSELY(p.next);
        System.out.println(p.value);
    } // DONE

    // নিচের এটা পসিবল কি না ভবিষ্যতে ট্রাই করে দেখব
//    public static void REVERSE_a_Linked_List(Node p) { // Self Try
//        if (p == null || p.next == null) {
//            return;
//        }
//        REVERSE_a_Linked_List(p.next.next); // ???
//        (p.next).next = p.next;
//        // নতুন হেড হারিয়ে ফেলেছি
//    }

    static Node REVERSE_a_Linked_List(Node p) { // কম কম বুঝেছি
        if (p == null || p.next == null) {
//            return null; // না, নাল রিটার্ন করলে লাস্ট নোড কে হারিয়ে ফেললাম
            return p;
        }
        Node q = REVERSE_a_Linked_List(p.next); // ???
        (p.next).next = p; // পরবর্তী নোডের এর নেক্সট এ নিজেকে রাখবে
        (p.next) = null; // পরবর্তী এর নেক্সটে নিজেকে রেখে নিজের ভবিষ্যতকে নাল করে দিবে
        return q; // return of new head
    }

    static Node SortedMergeLinkedList(Node A, Node B) {
        // difficult
        // BASE CASE
        // রিকার্সিভ কল করে যাকে পাস করেছি তাকে চেক করব বেইস কেস এ
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        // Mechanism
        if (A.value < B.value) { // A ছোট
            // যে ছোট তাকে ধরে তার নেক্সট কে খুজব
            A.next = SortedMergeLinkedList(A.next, B);
            return A;
        } else {
            B.next = SortedMergeLinkedList(A, B.next);
            return B;
        }
    } // WORKING

    static Node Sort_Singly_Linked_List(Node head) {
/*
//                    { // changing value only // EASY
//                        int temp = p.value;
//                        p.value = f.value;
//                        f.value = temp;
//                        return head;
//                    } // WORKING
* */

        Node p = head;
        if (p.value > (p.next).value) {
            (p.next) = (p.next).next;
            (p.next).next = p;
        }

        for (; p != null; p = p.next) {
            for (Node f = p.next; f != null; f = f.next) {
                if (p.value > f.value) {

                    { // changing nodes
                        if (f.next == null) { // for last two pair

                        }
                    }
                }
            }
        }
        return head;
    }  // PENDING

    static Node Delete_a_node_from_Singly_linked_list(Node head, int key) {
        if (head.value == key) { // edge case : first node
            Node temp = head;
            head = head.next;
            temp.next = null; // will get deleted automatically by Garbage Collector
            return head;
        }
        for (Node p = head; p.next != null; p = p.next) { // loop for searching
            if ((p.next).next == null && (p.next).value == key) {
                p.next = null;
                return head;
            }
            if (p.next.value == key) {
                p.next = p.next.next;
                return head;
            }
        }
        return head;
    }

    static void DeleteLast(Node n) {
        Node previous = n, current = n;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
//        tail = previous;
    }

    static Node Search_Singly_Linked_List(Node p, int key) {

        for (; p != null; p = p.next) {
            if (p.value == key) {
                return p;
            }
        }
        return null;
    }

    static Node Search_Previous(Node n, int key) {
        Node prev = n;
        for (; n != null; prev = n, n = n.next) {
            if (n.value == key) {
                return prev;
            }
        }
        return null;
    }

} // WORKING WELL

class LinkedList {
    static void main(String[] args) {
        // Linked List Creation
        Node head1 = null;
        Node head2 = null;
        Node p = null;
        for (int i = 1; i <= 10; i++) {
//            System.out.print(STR."Loop's i = \{i}, ");
            if (i == 1) {
                p = head1 = new Node(i);
            } else {
                Node new_node = new Node(i);
                p.setNext(new_node);
                p = p.getNext();
            }
        }
        for (int i = 1; i <= 10; i++) {
//            System.out.println(STR."Loop's i = \{i}, ");
            int randInt = (int) (Math.random() * 100);
            if (i == 1) {
                p = head2 = new Node(randInt);
            } else {
                Node new_node = new Node(randInt);
                p.setNext(new_node);
                p = new_node;
            }
        }
//----------------------------------------------------------
// ============ Print Whole List ============
        PrintWholeLinkedList(head2); // DONE
//----------------------------------------------------------
// ============ Print whole list Reversely ============
//        PrintWholeLinkedListREVERSELY(head); // DONE
//----------------------------------------------------------
// ============ Reversed Link List Printing ============
//        head2 = REVERSE_a_Linked_List(head2);
//        PrintWholeLinkedList(head2);
        // DONE
//----------------------------------------------------------
// ============= Shorted Merged Linked List ==============
//        SortedMergeLinkedList(head1, head2);
//        PrintWholeLinkedList(head1);
//        PrintWholeLinkedList(head2);
        // DONE
///---------------------------------------------------------
// ============= Insert a node ==============
///---------------------------------------------------------
// ============= delete a node ==============
//        head1 = Delete_a_node_from_Singly_linked_list(head1, 12);
//        PrintWholeLinkedList(head1);
///---------------------------------------------------------
// ============= Sort a singly linked list ==============
//        PENDING
//        head2 = Sort_Singly_Linked_List(head2);
//        PrintWholeLinkedList(head2);
///---------------------------------------------------------
// ============= Delete Last ==============
        DeleteLast(head2);
        PrintWholeLinkedList(head2);
///---------------------------------------------------------


    }
}
