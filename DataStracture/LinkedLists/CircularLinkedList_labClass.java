package DataStracture.LinkedLists;

import java.util.prefs.NodeChangeEvent;

class CircularLinkedList_labClass implements Cloneable {
    private static class data_CLL {
        private int data;

        public data_CLL(int data) {
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
            return this.data + "";
        }
    }

    private static class Node_CLL {
        private data_CLL data;
        private Node_CLL next;

        public Node_CLL(data_CLL data, Node_CLL next) {
            this.data = data;
            this.next = next;
        }

        public Node_CLL(data_CLL data) {
            this.data = data;
        }

        public data_CLL getData() {
            return data;
        }

        public void setData(data_CLL data) {
            this.data = data;
        }

        public Node_CLL getNext() {
            return next;
        }

        public void setNext(Node_CLL next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return data + " ";
        }
    }

    private data_CLL data;
    private Node_CLL tail;
    private int size;

    CircularLinkedList_labClass() {
        tail = null;
        size = 0;
    }

    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    data_CLL getFirstData() {
        if (!isEmpty()) {
            return tail.getNext().getData();
        }
        return null;
    } // completed

    data_CLL getLastData() {
        if (!isEmpty()) {
            return tail.getData();
        }
        return null;
    } // completed

    void addFirst(data_CLL d) {
        if (isEmpty()) {
            tail = new Node_CLL(d, null);
            tail.setNext(tail);
        } else {
            Node_CLL new_node = new Node_CLL(d, tail.getNext());
            tail.setNext(new_node);
        }
        size++;
    } // completed

    void addLast(data_CLL d) {
        if (isEmpty()) {
            tail = new Node_CLL(d, null);
            tail.setNext(tail);
        } else {
            Node_CLL new_node = new Node_CLL(d, tail.getNext());
            tail.setNext(new_node);
            tail = new_node;
        }
        size++; // --------------------- size barai nai bole totally kam kortesilo na isEmpty te terminate houar jonno
    } // should work

    data_CLL removeFirst() {
        data_CLL temp = null;
        if (isEmpty()) {
            return null;
        } else if (getSize() == 1) { // only tail exists
            size--;
            temp = tail.getData();
            tail = null; // only one element exist, after removing only node, the list will be empty
            return temp;
        } else {
            size--;
            temp = tail.next.getData(); // head
            tail.setNext(tail.getNext().getNext());
            return temp;
        }
    } // completed

    data_CLL removeLast() {
        if (isEmpty()) {
            return null;
        }
        data_CLL temp = tail.getData();

        size--;
        return temp;
    } // baki ache

    void rotate() {
        if (!isEmpty()) {
            tail = tail.next;
        }
    } // DONE

    void printList() {
        if (!isEmpty()) {
            for (Node_CLL t = tail.getNext(); ;
                 t = t.getNext()) {

                System.out.print(t);
                if (t == tail) {
                    break;
                }
            }
        } else {
            System.out.println("List is empty");
        }
    } // should word

    void print_list_ten_elements() {
        if (!isEmpty()) {
            int i = 1;
            for (Node_CLL t = tail.getNext(); ;
                 t = t.getNext(), i++) {

                System.out.print(t);
                if (i == 12) {
                    return;
                }
            }
        } else {
            System.out.println("List is empty");
        }
    } // done

    CircularLinkedList_labClass Clone_CLL() throws CloneNotSupportedException {

        CircularLinkedList_labClass other = (CircularLinkedList_labClass) super.clone();
        if (!isEmpty()) {
            return null;
        } else {

            // Re-initialize the new list so it starts as an empty list
            other.tail = null;

            for (Node_CLL current_main = tail.getNext(); ;
                 current_main = current_main.getNext()) {

                other.addLast(current_main.getData());
                if (current_main == tail) {
                    break;
                }
            }

            return other;
        }
    } // should work // but not working

    public static void main(String[] args) throws CloneNotSupportedException {
        CircularLinkedList_labClass list = new CircularLinkedList_labClass();
        for (int i = 1; i < 6; i++) {
//            list.addFirst(new data_CLL(i));
            list.addLast(new data_CLL(i));
        }
        list.printList();
        System.out.println();
        list.print_list_ten_elements();

        CircularLinkedList_labClass list2 = list.Clone_CLL();
        list2.print_list_ten_elements();

    }
}
