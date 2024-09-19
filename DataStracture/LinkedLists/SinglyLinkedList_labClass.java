package DataStracture.LinkedLists;

class SinglyLinkedList {
    private static class data_SLL {
        private int data;

        public data_SLL(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return STR."\{this.data}";
        }
    }

    private static class Node_SLL {
        private data_SLL data;
        private Node_SLL next;

        public Node_SLL(data_SLL data, Node_SLL next) {
            this.data = data;
            this.next = next;
        }

        public Node_SLL(data_SLL data) {
            this.data = data;
        }

        public data_SLL getDataObj() {
            return data;
        }

        public void setData(data_SLL data) {
            this.data = data;
        }

        public Node_SLL getNext() {
            return next;
        }

        public void setNext(Node_SLL next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return STR."\{data} ";
        }
    }

    private Node_SLL head;
    private int size;

    SinglyLinkedList() {
        head = null;
        this.size = 0;
    }

//    SinglyLinkedList(Node_SLL head) {
//        this.head = head;
//        this.size = 0;
//    }

    boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    void addFirst(data_SLL d) {
        if (head == null) { // nothing in the list
            head = new Node_SLL(d, null);
        } else {
            Node_SLL new_node = new Node_SLL(d, head);
            head = new_node;
        }
        size++;
    }

    Node_SLL getLast() {
        if (head == null) {
            return null; // ------------------------------------------------ I missed this
        }
        Node_SLL current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    } // completed

    Node_SLL removeFirst() {
        if (!isEmpty()) {
            Node_SLL temp = head;
            if (size == 1) {
                head = null;
            } else {
                head = head.getNext();
                temp.setNext(null);
            }
            size--;
            return temp;
        }
        return null;
    }

    Node_SLL removeLast() {
        if (!isEmpty()) {
            Node_SLL current = head, previous = head;
            while (current.next != null) {
                previous = current;
                current = current.next;
            }
            previous.next = null;
            size--; // this function is to get last element, not to remove last one
            return current;
        }
        return null;
    } // completed


////////////// my buggy code ///////////////////
//    void addLast(data_SLL d) {
//        Node_SLL last_node = getLast();
////        System.out.println(STR."add last 119 \{last_node == null}");
//        Node_SLL new_node = new Node_SLL(d, null);
//        last_node.setNext(new_node);


    void addLast(data_SLL d) {
        Node_SLL new_node = new Node_SLL(d, null);
        if (head == null) {
            head = new_node;
        } else {
            Node_SLL last_node = getLast();
            last_node.next = new_node;
        }
        size++;
    } // completed

    void printList() {
        if (head == null) {
            return;
        }
        Node_SLL temp = head;
        while (temp != null) {
            System.out.print(temp);
            temp = temp.next; // ------------------------- sadly i missed this too
        }
        System.out.println();
    } // completed

    void concatSinglyLinkedList(SinglyLinkedList new_head) {
        Node_SLL tail = getLast();
        if (tail.next == null) {
            tail.setNext(new_head.head); // new_head.head
        }
    }

    Node_SLL search(int integer_data) {
        for (Node_SLL p = head; p.next != null; p = p.getNext()) {
            if (p.getDataObj().getData() == integer_data) {
                return p;
            }
        }
        return null;
    } // done

    Node_SLL search_return_previous(int integer_data) {
        if (head.next == null) {
            return null;  // ----------------------------------------------- seems vulnerable
        }
        for (Node_SLL p = head; p.next != null; p = p.getNext()) {
            if (p.next.getDataObj().getData() == integer_data) {
                return p;
            }
        }
        return null;
    } // working

    void deleteNode(int integer_data) {
        /*
         * this method delete one node (only one) from singly linked list if it gets matching in integer
         */
        if (head.getDataObj().getData() == integer_data) {
            //delete head
            removeFirst();
        }
        // instead of else-if, using if is better, as, one case may appear where
        // there are 2 element, and they are same and to be deleted
        // so, I am avoiding all return statements
        if (getLast().getDataObj().getData() == integer_data) {
            // delete last
            removeLast();
        } else {
            // search and delete
            Node_SLL previous = search_return_previous(integer_data);
            Node_SLL target = previous.getNext();
            /*
            target.setNext(null);
            previous.setNext(previous.getNext().getNext()); ultimately catching a null as i am setting null before
            * */
            previous.setNext(previous.getNext().getNext());
            target.setNext(null);
        }
    } // done

    void deleteNode(Node_SLL n) {
        // currently empty // perhaps it is useless in case of singly linked list
    }


    ///////////////////////////////////////////////////////////////////////////
    // -------------------------- PROBLEM SOLVING -------------------------- //
    ///////////////////////////////////////////////////////////////////////////

    /* Question:                                                     Farhan Sir
    You are given the heads of two 'sorted' singly linked lists list1 and list2.
    Merge the two lists into one sorted list.*/

    void sortedMerge(SinglyLinkedList list1, SinglyLinkedList list2) {
        int len1 = list1.getSize();
        int len2 = list2.getSize();

        Node_SLL e1 = list1.head;
        Node_SLL e2 = list2.head;

        for (int i = 1; i <= (len1 + len2); i++) {

            if (e1 == null) {
                while (e2 != null) {
                    addLast(e2.getDataObj());
                    e2 = e2.getNext();
                }
                return;
            }
            if (e2 == null) {
                while (e1 != null) {
                    addLast(e1.getDataObj());
                    e1 = e1.getNext();
                }
                return;
            }
            if (e1.getDataObj().getData() == e2.getDataObj().getData()) {
                addLast(e1.getDataObj());
                e1 = e1.getNext();
                e2 = e2.getNext();
            }
            if (e1.getDataObj().getData() < e2.getDataObj().getData()) {
                addLast(e1.getDataObj());
                e1 = e1.getNext();
            } else if (e1.getDataObj().getData() > e2.getDataObj().getData()) {
                addLast(e2.getDataObj());
                e2 = e2.getNext();
            }
        }
    } // done


    /* Question:
    Given the head of a 'sorted' singly linked list,
    delete all duplicates such that each element appears only once.*/

    void deleteDuplicate() {
//        for (Node_SLL i = head; i.next != null; i = i.getNext()) {
        for (Node_SLL i = head; i != null; i = i.getNext()) { // i != null
            for (Node_SLL j = i.getNext(); j != null; j = j.getNext()) { // ---------------------------- j er vumika ???
                if (i.getNext() != null &&
                        i.getDataObj().getData() == i.getNext().getDataObj().getData()) {
                    i.setNext(i.getNext().getNext());
                }
                if (i.getNext() != null
                        && i.getDataObj().getData() != i.getNext().getDataObj().getData()) { // since sorted
                    break;
                }
            }
        }
    } // done


    /* Question:
    You are given the head of a linked list.
    Remove every node which has a node with a greater value anywhere to the right side of it.
    * */

    void make_SLL_acceding_by_deleting() {
        for (Node_SLL i = head; i != null; i = i.getNext()) { // i != null
            for (Node_SLL j = i.getNext(); j != null; j = j.getNext()) {
                if (i.getNext() != null &&
                        i.getDataObj().getData() >= i.getNext().getDataObj().getData()) {
                    i.setNext(i.getNext().getNext());
                }
            }
        }
    }

///////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        SinglyLinkedList list1 = new SinglyLinkedList();
        SinglyLinkedList list2 = new SinglyLinkedList();

        int[] arr1 = {9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 20, 30,
                30, 40, 50, 50, 50, 35, 50, 60, 70, 70, 80, 90, 90, 90, 90};
        int[] arr2 = {1, 2, 3, 4, 5, 33};

        for (int i = 0; i < arr1.length; i++) {
            list1.addLast(new data_SLL(arr1[i]));
        }
        for (int i = 0; i < arr2.length; i++) {
            list2.addLast(new data_SLL(arr2[i]));
        }


        list1.printList();
//        list2.printList();

//        SinglyLinkedList sorted_list = new SinglyLinkedList();
//        sorted_list.sortedMerge(list1, list2);
//        sorted_list.printList();
//        System.out.println(list1.search_return_previous(50));

//        list1.deleteNode(30);
//        list1.deleteDuplicate();
//        list1.printList();
        list1.make_SLL_acceding_by_deleting();
        list1.printList();

    }
}
