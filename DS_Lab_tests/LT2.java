package DS_Lab_tests;

// Kazi Rifat Morshed // 230220
// 2nd Data Structure Lab test // topic: Linked List // 01/Oct/2024

import java.nio.file.NotDirectoryException;

class SinglyLinkedList {
    private static class DATA {
        private int data;

        public DATA(int data) {
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

    private static class NODE {
        private DATA data;
        private NODE next;

        public NODE(DATA data, NODE next) {
            this.data = data;
            this.next = next;
        }

        public NODE(DATA data) {
            this.data = data;
        }

        public DATA getDataObj() {
            return data;
        }

        public void setData(DATA data) {
            this.data = data;
        }

        public NODE getNext() {
            return next;
        }

        public void setNext(NODE next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return STR."\{data} ";
        }
    }

    private NODE head;
    private int size;

    SinglyLinkedList() {
        head = null;
        this.size = 0;
    }


    boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    void addFirst(DATA d) {
        if (head == null) { // nothing in the list
            head = new NODE(d, null);
        } else {
            NODE new_node = new NODE(d, head);
            head = new_node;
        }
        size++;
    }

    NODE getLast() {
        if (head == null) {
            return null;
        }
        NODE current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    } // completed

    NODE removeFirst() {
        if (!isEmpty()) {
            NODE temp = head;
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

    NODE removeLast() {
        if (!isEmpty()) {
            NODE current = head, previous = head;
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


    void addLast(DATA d) {
        NODE new_node = new NODE(d, null);
        if (head == null) {
            head = new_node;
        } else {
            NODE last_node = getLast();
            last_node.next = new_node;
        }
        size++;
    } // completed

    void printList() {
        if (head == null) {
            return;
        }
        NODE temp = head;
        while (temp != null) {
            System.out.print(temp);
            temp = temp.next;
        }
        System.out.println();
    } // completed

    void concatSinglyLinkedList(SinglyLinkedList new_head) {
        NODE tail = getLast();
        if (tail.next == null) {
            tail.setNext(new_head.head); // new_head.head
        }
    }

    NODE search_return_previous(int integer_data) {
        if (head.next == null) {
            return null;  // ----------------------------------------------- seems vulnerable
        }
        for (NODE p = head; p.next != null; p = p.getNext()) {
            if (p.next.getDataObj().getData() == integer_data) {
                return p;
            }
        }
        return null;
    } // working

    // ================================================================

    NODE search(int integer_data) {
        for (NODE p = head; p != null; p = p.getNext()) {
            if (p.getDataObj().getData() == integer_data) {
                return p;
            }
        }
        return null;
    } // done

    void merge(int key, SinglyLinkedList list2) {
        // search key node in list1
        NODE target_in_list1 = search(key);

        // store list2's last node pointer
        NODE last_node_of_list2 = list2.getLast();

        // merge list2 to the key node of list1
        last_node_of_list2.setNext(target_in_list1);
    }

    DATA mergingPoint(SinglyLinkedList list2) {
        // will return ELEMENT (here element itself is an object of DATA class
        if (!isEmpty() && !list2.isEmpty()) {

            for (NODE e1 = this.head;
                 e1 != null;
                 e1 = e1.getNext()) {

                for (NODE e2 = list2.head;
                     e2 != null;
                     e2 = e2.getNext()) {

                    if (e1 == e2) { // same node, same pointer or same location
                        System.out.println(STR."Merging element: \{e1.getDataObj()}");
                        return e1.getDataObj();
                    }

                }
            }
        }
        System.out.println("List1 and List2 did not merge");
        return null;
    }

    // ================================================================

    public static void main(String[] args) {
        SinglyLinkedList list1 = new SinglyLinkedList();
        SinglyLinkedList list2 = new SinglyLinkedList();

        int[] arr1 = {10, 20, 30, 40};
        int[] arr2 = {50, 60, 70};
        int mergingPoint = 40;

        for (int i = 0; i < arr1.length; i++) {
            list1.addLast(new DATA(arr1[i]));
        }
        for (int i = 0; i < arr2.length; i++) {
            list2.addLast(new DATA(arr2[i]));
        }
//        System.out.println("Before:");
//        System.out.print("First list: ");
//        list1.printList();
//        System.out.print("Second list: ");
//        list2.printList();

//        System.out.println("After:");
        list1.merge(mergingPoint, list2);
        System.out.print("First list: ");
        list1.printList();
        System.out.print("Second list: ");
        list2.printList();

        list1.mergingPoint(list2);

    }
}
