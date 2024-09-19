package DataStracture.LinkedLists;

class CircularLinkedList<E> { // remember to <E>
    public static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E data) {
            this.element = data;
        }

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        E getElement() {
            return element;
        }

        void setElement(E element) {
            this.element = element;
        }

        Node<E> getNext() {
            return next;
        }

        void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return STR."node data = \{element}";
        }

    }

    //    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    boolean isEmpty() {
        return size == 0;
    }

    E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return tail.getNext().getElement();
    }

    E getLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    void rotate() {
        if (!isEmpty()) {
            tail = tail.next;
        }
    }

    void addFirst(E e) {
        if (isEmpty()) {
            tail = new Node<>(e, null);
            tail.next = tail;
        } else {
            Node<E> new_node = new Node<E>(e, tail.next); // CAREFUL
            tail.next = new_node; // ----------------------------------------- REMINDER
        }
        size++; // REMINDER
    }

    void addLast(E e) {
        addFirst(e);
        tail = tail.next;
    }

    E removeFirst() {
        if (isEmpty() || size == 1) {
            return null;
        } else {
            E to_be_returned = tail.next.getElement();
            tail.next = tail.next.next;
            size--;
            return to_be_returned;
        }
    }

    void show_whole_list_ten_times() {
        Node<E> linkedList = this.tail.next;
        for (int i = 0; i < 10; i++, linkedList = linkedList.next) {
            System.out.println(linkedList);
        }
    }

    /*
    Suppose you are given two circularly linked lists, L and M. Describe an algorithm
    for telling if L and M store the same sequence of elements (but perhaps with
    different starting points).
     */
//    boolean has_same_sequence(Node<E> second_tail) {
//        boolean result = false;
//        Node<E> THIS = this.tail, THAT = second_tail;
//
//        for (int i = 0; ; i++) {
//            if (THIS.element.equals(THAT.element)) {
//                return check_rest_sequence(THIS.element, THAT.element);
//            }
//            if (i % 2 == 0) {
//                THIS = THAT.next;
//            } else {
//                THAT = THAT.next;
//            }
//            if (THAT == this.tail || THAT == second_tail) {
//                break;
//            }
//        }
//
//        return result;
//    }

//    static boolean check_rest_sequence(Node<E> first_tail, Node<E> second_tail) {
//        boolean result = true;
//        for (Node a = first_tail.next, b = second_tail.next; ; a = a.next, b = b.next) {
//            if (second_tail.element != first_tail.element) {
//                return false;
//            }
//            if (a == first_tail && b == second_tail) {
//                return true;
//            }
//        }
//        return result;
//    }

    /*
    I tried and failed, then GPT

    boolean has_same_sequence(Node<E> second_tail) {
    Node<E> THIS = this.tail;
    Node<E> THAT = second_tail;

    // Traverse both lists starting from tail
    do {
        if (!THIS.getElement().equals(THAT.getElement())) {
            return false; // If any elements don't match, return false
        }
        THIS = THIS.getNext();
        THAT = THAT.getNext();
    } while (THIS != this.tail && THAT != second_tail);

    // Check if both lists completed traversal together
    return (THIS == this.tail && THAT == second_tail);
}

static boolean check_rest_sequence(Node<E> first_tail, Node<E> second_tail) {
    Node<E> a = first_tail.getNext();
    Node<E> b = second_tail.getNext();

    // Traverse the remaining sequence
    do {
        if (!a.getElement().equals(b.getElement())) {
            return false; // If any element doesn't match, return false
        }
        a = a.getNext();
        b = b.getNext();
    } while (a != first_tail && b != second_tail);

    // Check if both lists reached the tail at the same time
    return (a == first_tail && b == second_tail);
}

     */


    public void main(String[] args) {
        CircularLinkedList<Integer> head = new CircularLinkedList<>();

        for (int i = 1; i < 4; i++) {
            head.addLast(i);
        }

        head.show_whole_list_ten_times();

    }
}
