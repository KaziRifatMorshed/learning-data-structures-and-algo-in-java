package DataStructure.Stack;

import java.util.Stack;

class Stack_SLL<E> {
    private static class Data<E> {
        private E data;

        public Data(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(this.data);
        }
    } // copied from SLL

    private static class Node_Stack<E> {
        private Data<E> data;
        private Node_Stack<E> next;

        public Node_Stack(Data<E> data, Node_Stack<E> next) {
            this.data = data;
            this.next = next;
        }

        public Node_Stack(Data<E> data) {
            this.data = data;
        }

        public Data getDataObj() {
            return data;
        }

        public void setData(Data data) {
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
            return data + " ";
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

    Node_Stack<E> pop() { // copied from SLL
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else if (getSize() == 1) {
            head = null;
            size = 0;
        } else {
            Node_Stack<E> temp = head, prev = head;

//            for (; temp != null; temp = temp.getNext()) {
            for (; temp.getNext() != null; temp = temp.getNext()) {
                prev = temp;
            }
            prev.setNext(null);

            System.out.println("data " + temp.getDataObj().getData() + " has been popped out");
            return temp;
        }
        return null;
    }

    Node_Stack<E> getLast() { // copied from SLL
        if (head == null) {
            return null; // ------------------------------------------------ I missed this
        }
        Node_Stack<E> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    } // completed

    void push(Data d) { // copied from SLL
        Node_Stack new_node = new Node_Stack(d, null);
        if (head == null) {
            head = new_node;
        } else {
            Node_Stack last_node = getLast();
            last_node.next = new_node;
        }
        size++;
        System.out.println("data " + d.getData() + " has been pushed to stack");
    } // completed

    boolean printStack() {
        if (isEmpty()) {
            return false;
        } else {
            System.out.print("Printing stack : ( ");
            for (Node_Stack<E> i = head; i != null; i = i.getNext()) {
                System.out.print(i);
            }
            System.out.println(")");
            return true;
        }
    } // works

    public static void main(String[] args) {
        Stack_SLL<Integer> list2 = new Stack_SLL<>();
        int[] arr2 = {1, 2, 3, 4, 5};

        for (int i = 0; i < arr2.length; i++) {
            list2.push(new Data<>(arr2[i]));
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
    private static class Data {
        //        private int data;
        private char data;

        //        public Data(int data) {
//            this.data = data;
//        }
        public Data(char data) {
            this.data = data;
        }

        public char getData() {
            return data;
        }

        public void setData(char data) {
            this.data = data;
        }
//        public void setData(int data) {
//            this.data = data;
//        }

        @Override
        public String toString() {
            return this.data + " ";
        }
    } // copied from SLL

    private Data[] stack;
    //    private int size = 100; /*stack_capacity; default 100*/
    private static final int size = 100; /*stack_capacity; default 100*/
    private int top_idx = -1; /*index of the top element*/

    Stack_Array(int stack_capacity) {
//        size = stack_capacity; // omitted for applying farhan sir's method
        stack = new Data[stack_capacity];
    }

    Stack_Array() {
        this(size);
    }

    /*
    Stack_Array() {
        stack = new Data[size];
    }
    */

    boolean isEmpty() {
        return top_idx < 0; /*zero can be an index of any array*/
    }

    int getCapacity() { /*get the max capacity of the stack*/ // ------------ CAREFUL; dont mix with getSize
        return stack.length;
    }

    int getSize() { /*get the total number of elements*/ // ---------------------- CAREFUL
        return top_idx + 1;
    }

    void push(Data d) { // add last
        if (top_idx >= getCapacity()) {
            System.out.println("Stack Overflow !!! int " + d + " failed to add");
        } else {
            top_idx++;
            stack[top_idx] = d;
            System.out.println("INSERTION: data " + d + " has been pushed to stack");
        }
    } //

    Data pop() { // remove last
        if (isEmpty()) {
            System.out.println("DELETION: Stack is empty");
            return null;
        } else {
            Data return_value = stack[top_idx];
            stack[top_idx] = null;
            top_idx--;
            System.out.println("DELETION: data " + return_value + " has been popped");
            return return_value;
        }
    }

    Data getTop() { // remove last
        if (isEmpty()) {
            return null;
        } else {
            Data return_value = stack[top_idx];
            return return_value;
        }
    }

    void printStack() {
        /*
         * returns true if stack has element,
         * returns false if stack is empty
         **/
        if (isEmpty()) {
            System.out.println("PRINTING: Stack is empty");
        } else {
            System.out.print("PRINTING: ( ");
            for (int i = 0; i <= top_idx; i++) {
                System.out.print(stack[i]);
            }
            System.out.println(")");
        }
    } // completed

    static boolean checkEquationValidity(String equation) {
        Stack_Array stack_of_braces = new Stack_Array();
//        for (int i = 0; equation.charAt(i) != '\0'; i++) {
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if (ch == '[' || ch == '{' || ch == '(') { /*opening, pushing*/
                stack_of_braces.push(new Data(ch));
                continue;
            } else if (ch == ']' || ch == '}' || ch == ')') {
                char top_ch = stack_of_braces.getTop().getData();
                if (stack_of_braces.isEmpty()) {
                    return false; // ----------------------- I forgot this :(
                }
                if ((top_ch == '[' && ch == ']') || (top_ch == '{' && ch == '}') || (top_ch == '(' && ch == ')')) {
                    stack_of_braces.pop();
                } else {
                    System.out.println("Invalid Equation");
                    return false;
                }
            }
        }
        System.out.println("Valid Equation");
        return true;
    } // DONE  // Hurrah !!!

    static boolean checkEquationValidity_BOOK(String equation) {
        final String opBraces = "({[";
        final String closeBraces = ")}]";

        java.util.Stack<Character> buffer = new Stack<Character>();

        for (char c : equation.toCharArray()) {
            if (opBraces.indexOf(c) != -1) {
                buffer.push(c);
            } else if (closeBraces.indexOf(c) != -1) {
                if (buffer.isEmpty()) {
                    return false;
                }
                if (opBraces.indexOf(buffer.pop()) != closeBraces.indexOf(c)) {
                    return false;
                }
            }
        }
        return buffer.isEmpty(); // ------------------------------------------- CAREFUL
    }

    static boolean check_HTML_Validity_BOOK(String html) {
        final char op = '<';
        final char close = '>';

        java.util.Stack<String> buffer = new Stack<String>();

        for (int j = html.indexOf(op);
             j != -1; /*termination condition*/
             j = html.indexOf(op, j + 1)) {

            int k = html.indexOf(close, j + 1);
            if (k == -1) {
                return false;
            }

            String tag = html.substring(j + 1, k);

//            if (!tag.charAt(0) == '/'){ // opening tag
            if (!tag.startsWith("/")) { // opening tag
                buffer.push(tag);

            } else { // closing tag

                if (buffer.isEmpty()) {
                    return false;
                }
                if (!buffer.pop().equals(tag)) {
                    return false;
                }
            }
        }
        return buffer.isEmpty(); // ------------------------------------------- CAREFUL
    }

    static int evaluatePostfixExpression(String equation) {
        Stack_Array stack_of_num = new Stack_Array();
        for (int i = 0; equation.charAt(i) != '\0'; i++) {
            char ch = equation.charAt(i);
            if (48 <= (int) ch && (int) ch <= 57) { /*Digits*/
                stack_of_num.push(new Data(ch));
                continue;
            }
            int value = 0;
            if (ch == '+') {
                value = (stack_of_num.pop().getData() - '0') + (stack_of_num.pop().getData() - '0');
            } else if (ch == '-') {
                value = (stack_of_num.pop().getData() - '0') - (stack_of_num.pop().getData() - '0');
            } else if (ch == '*') {
                value = (stack_of_num.pop().getData() - '0') * (stack_of_num.pop().getData() - '0');
            } else if (ch == '/') {
                value = (stack_of_num.pop().getData() - '0') / (stack_of_num.pop().getData() - '0');
            }
            stack_of_num.push(new Data((char) (value + '0')));

        }

        return stack_of_num.pop().getData();
    } // incomplete

    static boolean infixToPostfix(String equation) {
        Stack_Array stack_of_sign = new Stack_Array();
        Stack_Array stack_postfix = new Stack_Array();

        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);

            if (ch == '[' || ch == '{' || ch == '(') { /*opening, pushing*/
                stack_of_sign.push(new Data(ch));
                continue;

            } else if (ch == ']' || ch == '}' || ch == ')') {
                char top_ch = stack_of_sign.getTop().getData();
                if ((top_ch == '[' && ch == ']') || (top_ch == '{' && ch == '}') || (top_ch == '(' && ch == ')')) {
                    stack_of_sign.pop();
                } else {
                    System.out.println("Invalid Equation");
                    return false;
                }
            }

        }
        System.out.println("Valid Equation");
        return true;
    } // incomplete


    public static void main(String[] args) {
        Stack_Array list1 = new Stack_Array();
        Stack_Array list2 = new Stack_Array(50);
        int[] arr2 = {1, 2, 3, 4, 5, 33};

//        for (int i = 0; i < arr2.length; i++) {
////            list1.push(arr2[i]);
//            list2.push(arr2[i]);
//        }
//        String s = "[(A + B) - {C - D}] - [F - G]";
//        String s = "[(A +} B) - {C - D}] - [F - G]";
//        boolean b = checkEquationValidity(s);
        String s = "562+*74/-";
        int b = evaluatePostfixExpression(s);
        System.out.println(b);

    }
} // WORKING


class Stack_DLL {

}
