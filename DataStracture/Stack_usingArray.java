package DataStracture;

class StackUnderflowException extends Throwable {
}

class StackOverflowException extends Throwable {
}

class Stack_usingArray {
    private int[] stack = null;
    private int stack_Capacity = 0; // it is the count of the total number of elements
    private int stack_top = -1; // it is the index of the top element

    Stack_usingArray(int stack_Capacity) {
        this.stack_Capacity = stack_Capacity;
        this.stack = new int[stack_Capacity];
        this.stack_top = -1;
    }

    boolean isEmpty() {
        if (stack_top < 0) return true;
        else return false;
    }

    boolean isFull() {
        if (stack_top >= stack_Capacity - 1) return true; // stack_top ">=" stack_Capacity - 1
        else return false;
    }

    void Push(int new_data) throws StackOverflowException {
        if (stack_top < stack_Capacity - 1) {
            // first, go to next index and then set data
            stack[++stack_top] = new_data;
        } else {
            throw new StackOverflowException();
        }
    }

    int Pop() throws StackUnderflowException {
        if (stack_top >= 0) { // tracking index
            return stack[stack_top--];
        } else {
            throw new StackUnderflowException();
        }
    }

} // DONE

class StackApp {
    static void main(String[] args) throws StackOverflowException, StackUnderflowException { // throws StackOverflowException ???????????????
        Stack_usingArray my_stack = new Stack_usingArray(50);

        for (int i = 10; i < 100; i += 10) {
            my_stack.Push(i);
        }

        while (!my_stack.isEmpty()) {
            int temp = my_stack.Pop();
            System.out.println(temp);
        }
    }
} // DONE