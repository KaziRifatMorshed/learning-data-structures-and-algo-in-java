package DataStracture.LinkedLists;

class CircularLinkedList_labClass {
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
            return STR."\{this.data}";
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
            return STR."\{data} ";
        }
    }

    private data_CLL data;
    private Node_CLL tail = null;
    private int size;

    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    data_CLL getFirst() {
        if (!isEmpty()) {
            return tail.getNext().getData();
        }
        return null;
    } // completed

    data_CLL getLast() {
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
    } // completed

    void addLast() {
    }

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
    }

    public static void main(String[] args) {

    }
}
