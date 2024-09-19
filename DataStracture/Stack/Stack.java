package DataStracture.Stack;

class Stack_SLL {
    private static class data_Stack {
        private int data;

        public data_Stack(int data) {
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
    } // copied from SLL

    private static class Node_Stack {
        private data_Stack data;
        private Node_Stack next;

        public Node_Stack(data_Stack data, Node_Stack next) {
            this.data = data;
            this.next = next;
        }

        public Node_Stack(data_Stack data) {
            this.data = data;
        }

        public data_Stack getDataObj() {
            return data;
        }

        public void setData(data_Stack data) {
            this.data = data;
        }

        public Node_Stack getNext() {
            return next;
        }

        public void setNext(Node_Stack next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return STR."\{data} ";
        }
    } // copied from SLL

    private Node_Stack head;
    private int size;

    Stack_SLL() {
        head = null;
        this.size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    Node_Stack pop() { // copied from SLL
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else if (getSize() == 1) {
            head = null;
            size = 0;
        } else {
            Node_Stack temp = head, prev = head;

//            for (; temp != null; temp = temp.getNext()) {
            for (; temp.getNext() != null; temp = temp.getNext()) {
                prev = temp;
            }
            prev.setNext(null);

            System.out.println(STR."data \{temp.getDataObj().getData()} has been popped out");
            return temp;
        }
        return null;
    }

    Node_Stack getLast() { // copied from SLL
        if (head == null) {
            return null; // ------------------------------------------------ I missed this
        }
        Node_Stack current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    } // completed

    void push(data_Stack d) { // copied from SLL
        Node_Stack new_node = new Node_Stack(d, null);
        if (head == null) {
            head = new_node;
        } else {
            Node_Stack last_node = getLast();
            last_node.next = new_node;
        }
        size++;
        System.out.println(STR."data \{d.getData()} has been pushed to stack");
    } // completed

    boolean printStack() {
        if (isEmpty()) {
            return false;
        } else {
            System.out.print("Printing stack : ( ");
            for (Node_Stack i = head; i != null; i = i.getNext()) {
                System.out.print(i);
            }
            System.out.println(")");
            return true;
        }
    } // works

    public static void main(String[] args) {
        Stack_SLL list2 = new Stack_SLL();
        int[] arr2 = {1, 2, 3, 4, 5};

        for (int i = 0; i < arr2.length; i++) {
            list2.push(new data_Stack(arr2[i]));
        }

        list2.printStack();

        list2.pop();
        list2.pop();
        list2.pop();
        list2.pop();
        list2.pop();
        list2.pop();
        list2.pop();
        list2.pop();

        list2.printStack();


    }
}


class Stack_Array {
    private static class data_Stack {
        private int data;

        public data_Stack(int data) {
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
    } // copied from SLL


    private data_Stack[] stacks;
    private int limit_of_stack = 100;
    private int size;

    Stack_Array(int stack_size) {
        this.limit_of_stack = stack_size;
        stacks = new data_Stack[this.limit_of_stack];
        this.size = 0;
    }

    Stack_Array() {
//        this(limit_of_stack); // ------------------------------------- kaj kore na keno
        stacks = new data_Stack[this.limit_of_stack];
        this.size = 0;

    }

    boolean isEmpty() {
        return size == 0;
    }

    public int getCurrentSize() {
        return size;
    }

    void push(int d) { // add last
        if (size >= limit_of_stack) {
            System.out.println("Stack Overflow !!! int " + d + " failed to add");
        } else {
            stacks[size++] = new data_Stack(d);
            System.out.println(STR."data \{d} has been pushed to stack");
        }
    } //

    data_Stack pop() { // remove last
        if (!isEmpty()) {
            data_Stack return_value = stacks[size - 1];
            stacks[size - 1] = null;
            size--;
            System.out.println(STR."data \{} has been popped");
            return return_value;
        }
        return null;
    }

    boolean printStack() {
        /*
         * returns true if stack has element,
         * returns false if stack is empty
         **/
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return false;
        } else {
            System.out.print("Printing stack : ( ");
            for (int i = 0; i < size; i++) {
                System.out.print(stacks[i]);
            }
            System.out.println(")");
            return true;
        }
    } // completed

    public static void main(String[] args) {
        Stack_Array list2 = new Stack_Array();
        int[] arr2 = {1, 2, 3, 4, 5, 33};

        for (int i = 0; i < arr2.length; i++) {
            list2.push(arr2[i]);
        }

        list2.printStack();

        list2.pop();

        list2.printStack();


    }
}


class Stack_DLL {

}
