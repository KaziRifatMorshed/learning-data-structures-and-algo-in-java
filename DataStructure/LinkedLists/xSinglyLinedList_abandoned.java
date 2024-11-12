//package DataStracture.LinkedLists;
//
//class xSinglyLinedList_abandoned<E> {
//    public static class Node<E> {
//        private E element;
//        private Node<E> next;
//
//        public Node(E data) {
//            this.element = data;
//        }
//
//        public Node(E element, Node<E> next) {
//            this.element = element;
//            this.next = next;
//        }
//
//        E getElement() {
//            return element;
//        }
//
//        void setElement(E element) {
//            this.element = element;
//        }
//
//        Node<E> getNext() {
//            return next;
//        }
//
//        void setNext(Node<E> next) {
//            this.next = next;
//        }
//
//        @Override
//        public String toString() {
//            return STR."node data = \{element}";
//        }
//
//    }
//
//    private Node<E> head, tail;
//    protected int size;
//
//    xSinglyLinedList_abandoned() {
//    }
//
//    Node<E> getHead() {
//        return head;
//    }
//
//    void setHead(Node<E> head) {
//        this.head = head;
//    }
//
//    Node<E> getTail() {
//        return tail;
//    }
//
//    void setTail(Node<E> tail) {
//        this.tail = tail;
//    }
//
//    int getSize() {
//        return size;
//    }
//
//    boolean isEmpty() {
//        return size == 0;
//    }
//
//    void setSize(int size) {
//        this.size = size;
//    }
//
//    Node<E> getFirst() {
//        if (isEmpty()) return null;
//        return head;
//    }
//
//    Node<E> getLast() {
//        if (isEmpty()) return null;
//        return tail;
//    }
//
//    void addFirst(E e) {
//        Node<E> new_node = new Node<>(e);
//        new_node.next = head;
//        head = new_node;
//        size++;
//    }
//
//    void addLast(E e) {
//        getLast().next = new Node<E>(e);
//        this.tail = getLast().next;
//    }
//
//    Node<E> removeLast() {
//        Node<E> current = head, previous = null;
//        for (; current.next != null; current = current.next) {
//            previous = current;
//        }
//        if (previous != null) {
//            previous.next = null;
//            this.tail = previous;
//        }
//        return current;
//    }
//
//    Node<E> removeFirst() {
//        Node<E> temp = this.head;
//        this.head = this.head.next;
//        return temp;
//    }
//
//    void concatLinkedList(Node<E> new_head) {
//        if (tail.next == null) {
//            tail.next = new_head;
//        }
//    }
//
//
//}
