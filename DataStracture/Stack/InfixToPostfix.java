package DataStracture.Stack;

public class InfixToPostfix {
    public static String convertInfixToPostfix(String exp) {
        String postfix = "";
        StackArray<Character> stack = new StackArray<Character>();
        final String opening = "({[";
        final String closing = ")}]";
        final String operators = "+-*/";
        final String operators2 = "-+/*"; // why ???

        for (char c : exp.toCharArray()) {
            // CASE 1: b. if the symbol is an opening parenthesis, push it on to the stack
            if (opening.indexOf(c) != -1) {
                stack.push(c);
            }

            // CASE 2: c. if the symbol is an operator, then check the top of the stack
            else if (operators.indexOf(c) != -1) {
                // CASE 2.1: c. i. if the precedence of the operator at the top of the stack is higher or the same as the current operator then repeatedly it is popped and added to the postfix expression.
                while (!stack.isEmpty() &&
                        opening.indexOf(stack.getTop()) != -1 &&
                        (
                                operators.indexOf(c) <= operators.indexOf(stack.getTop()) ||
                                        operators2.indexOf(c) <= operators2.indexOf(stack.getTop())
                        )
                ) {
                    postfix += stack.pop();
                } // while end
                // CASE 2.2 : c. ii. otherwise, it is pushed onto the stack.
                stack.push(c);
            }

            // CASE 3: d. If the symbol is a closing parenthesis, then
            else if (closing.indexOf(c) != -1) {
                // d. i. Repeatedly pop from the stack and add each operator to the postfix expression until the corresponding opening parenthesis is encountered.
                while (closing.indexOf(c) != opening.indexOf(stack.getTop())) {
                    postfix += stack.pop();
                }
                // d. ii. Remove the opening parenthesis from the stack.
                stack.pop();
            }

            // CASE 4: a. if the symbol is an operand, add it to the postfix expression.
            else {
                postfix += c;
            }
        }

        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix;
    }

    public static void main(String[] args) {
        String expression = "5*(6+2)-(12/4)";
        System.out.println("PostFix: " + convertInfixToPostfix(expression));
    }
}

///////////////////////////////////////////////////////////////////////////

class StackArray<E> {
    private E[] stack;
    //    private int size = 100; /*stack_capacity; default 100*/
    private static final int size = 100; /*stack_capacity; default 100*/
    private int top_idx = -1; /*index of the top element*/

    StackArray(int stack_capacity) {
//        size = stack_capacity; // omitted for applying farhan sir's method
        stack = (E[]) new Object[stack_capacity];
    }

    StackArray() {
        this(size);
    }


    boolean isEmpty() {
        return top_idx < 0; /*zero can be an index of any array*/
    }

    int getCapacity() { /*get the max capacity of the stack*/ // ------------ CAREFUL; dont mix with getSize
        return stack.length;
    }

    int getSize() { /*get the total number of elements*/ // ---------------------- CAREFUL
        return top_idx + 1;
    }

    void push(E d) { // add last
        if (top_idx >= getCapacity()) {
//            System.out.println("Stack Overflow !!! int " + d + " failed to add");
        } else {
            top_idx++;
            stack[top_idx] = d;
//            System.out.println("INSERTION: data " + d + " has been pushed to stack");
        }
    } //

    E pop() { // remove last
        if (isEmpty()) {
//            System.out.println("DELETION: Stack is empty");
            return null;
        } else {
            E return_value = stack[top_idx];
            stack[top_idx] = null;
            top_idx--;
//            System.out.println("DELETION: data " + return_value + " has been popped");
            return return_value;
        }
    }

    E getTop() { // remove last
        if (isEmpty()) {
            return null;
        } else {
            E return_value = stack[top_idx];
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


} // WORKING
