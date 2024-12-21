package LeetcodeSolving.leetcode.editor.en;

//Implement a last-in-first-out (LIFO) stack using only two queues. The
//implemented stack should support all the functions of a normal stack (push, top, pop, and
//empty).
//
// Implement the MyStack class:
//
//
// void push(int x) Pushes element x to the top of the stack.
// int pop() Removes the element on the top of the stack and returns it.
// int top() Returns the element on the top of the stack.
// boolean empty() Returns true if the stack is empty, false otherwise.
//
//
// Notes:
//
//
// You must use only standard operations of a queue, which means that only push
//to back, peek/pop from front, size and is empty operations are valid.
// Depending on your language, the queue may not be supported natively. You may
//simulate a queue using a list or deque (double-ended queue) as long as you use
//only a queue's standard operations.
//
//
//
// Example 1:
//
//
//Input
//["MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//Output
//[null, null, null, 2, 2, false]
//
//Explanation
//MyStack myStack = new MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // return 2
//myStack.pop(); // return 2
//myStack.empty(); // return False
//
//
//
// Constraints:
//
//
// 1 <= x <= 9
// At most 100 calls will be made to push, pop, top, and empty.
// All the calls to pop and top are valid.
//
//
//
// Follow-up: Can you implement the stack using only one queue?
//
// Related Topics Stack Design Queue 👍 6257 👎 1226

//leetcode submit region begin(Prohibit modification and deletion)
class StackUsingQueue {
    Queue_Array<Integer> queue;
    private int stackSize;

    public StackUsingQueue() {
        stackSize = 0;
        queue = new Queue_Array<>(110);
    }

    public void push(int x) {
        queue.enQueue(x);
        stackSize++;
    }

    public int pop() {
        if (empty()) return 0;
        int toBeReturned = 0;
        for (int i = 1; i <= (stackSize - 1); i++) { // i <= (stackSize - 1)
            int temp = queue.deQueue();
            queue.enQueue(temp);
        }
        if (!queue.isEmpty()) {
            toBeReturned = queue.deQueue();
            stackSize--;
        }
        System.out.println("pop = " + toBeReturned);
        return toBeReturned;
    }

    public int top() {
        if (empty()) return 0;
        int toBeReturned = this.pop();
        System.out.println("top = " + toBeReturned);
        queue.enQueue(toBeReturned);
        stackSize++;
        return toBeReturned;
    }

    public boolean empty() {
        return stackSize == 0;
    } // DONE

    private static class Queue_Array<T> {
        int capacity = 0, start_idx = -1, end_idx = -1, current_size = 0;
        T[] queue;

        Queue_Array(int capacity) {
            this.capacity = capacity;
            // noinspection unchecked
            queue = (T[]) new Object[this.capacity];
        }

        void reset() {
            start_idx = end_idx = -1;
            current_size = 0;
            queue = (T[]) new Object[capacity];
        }

        boolean isEmpty() {
            return current_size == 0;
        }

        boolean isFull() {
            return current_size == capacity;
        }

        void enQueue(T e) {
            if (isFull()) {
                System.out.println("Queue capacity is FULL!");
                return;
            }
            if (isEmpty()) {
                start_idx = end_idx = 0;
            } else {
                end_idx = (end_idx + 1) % capacity;
            }
            queue[end_idx] = e;
            current_size++;
        }

        T deQueue() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return null;
            }
            T to_be_returned = queue[start_idx];
            queue[start_idx] = null;
            start_idx = (start_idx + 1) % capacity;
            current_size--;
            if (current_size == 0) {
                start_idx = end_idx = -1;
            }
            return to_be_returned;
        }

        T getFirst() {
            if (isEmpty()) {
                return null;
            }
            return queue[start_idx];
        }

        T getLast() {
            if (isEmpty()) {
                return null;
            }
            return queue[end_idx];
        }

        void printWholeQueue() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return;
            }
            int idx = start_idx;
            for (int i = 0; i < current_size; i++) {
                System.out.print(queue[idx] + " ");
                idx = (idx + 1) % capacity;
            }
            System.out.println();
        }
    }
} // DONE

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)
