package DataStructure.Queue_;

/*
THIS QUEUE HAS GOT ISSUE


The code for your `Queue_Array` implementation is almost correct but has a few issues, especially around index handling and operations like `enQueue` and `deQueue`. Here's a cleaned-up version with fixes:

### Key Fixes
1. **`end_idx` Increment Issue in `enQueue`:** You were incrementing `end_idx` incorrectly. It should wrap around the queue's capacity.
2. **`start_idx` Handling in `deQueue`:** Similar issue with `start_idx`, where it needs to wrap around the capacity.
3. **`getLast` Implementation Issue:** `end_idx` in the `getLast` method was returning an incorrect value. It should consider the previous valid index due to the post-increment in `enQueue`.
4. **Printing the Queue:** Added proper handling to print only the valid elements based on `start_idx` and `end_idx`.

Here’s the corrected code:

```java
static class Queue_Array<T> {
    int capacity = 0, start_idx = -1, end_idx = -1, current_size = 0;
    T[] queue;

    // Constructor
    Queue_Array(int capacity) {
        this.capacity = capacity;
        // noinspection unchecked
        queue = (T[]) new Object[this.capacity];
    }

    // Reset the queue
    void reset() {
        start_idx = end_idx = -1;
        current_size = 0;
        // noinspection unchecked
        queue = (T[]) new Object[capacity];
    }

    // Check if the queue is empty
    boolean isEmpty() {
        return current_size == 0;
    }

    // Check if the queue is full
    boolean isFull() {
        return current_size == capacity;
    }

    // Enqueue operation
    void enQueue(T e) {
        if (isFull()) { // Case 1: Queue Overflow
            System.out.println("Queue capacity is FULL!");
            return;
        }
        if (isEmpty()) { // Case 2: Totally empty
            start_idx = end_idx = 0;
        } else { // Case 3: Partial empty
            end_idx = (end_idx + 1) % capacity;
        }
        queue[end_idx] = e; // Assign the element
        current_size++;
    }

    // Dequeue operation
    T deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T to_be_returned = queue[start_idx];
        queue[start_idx] = null; // Clear the slot
        start_idx = (start_idx + 1) % capacity; // Move start index
        current_size--;
        if (current_size == 0) { // Reset indices if the queue becomes empty
            start_idx = end_idx = -1;
        }
        return to_be_returned;
    }

    // Get the first element
    T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return queue[start_idx];
    }

    // Get the last element
    T getLast() {
        if (isEmpty()) {
            return null;
        }
        return queue[end_idx];
    }

    // Print the entire queue
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
```

### Changes Explained
1. **Index Wrapping:**
   - Used modulo operation `(index + 1) % capacity` to ensure `start_idx` and `end_idx` wrap around the queue correctly.
2. **`reset` Method:** Ensures all fields are cleared, and the array is reset.
3. **`printWholeQueue`:** Modified to print only the valid elements in the queue, respecting `start_idx` and `end_idx`.
4. **`deQueue`:** Resets `start_idx` and `end_idx` when the queue becomes empty after the operation.

### Testing
Here's an example of how to test the class:

```java
public static void main(String[] args) {
    Queue_Array<Integer> queue = new Queue_Array<>(5);

    queue.enQueue(10);
    queue.enQueue(20);
    queue.enQueue(30);
    queue.enQueue(40);
    queue.enQueue(50);

    queue.printWholeQueue(); // Output: 10 20 30 40 50

    queue.deQueue();
    queue.deQueue();

    queue.printWholeQueue(); // Output: 30 40 50

    queue.enQueue(60);
    queue.enQueue(70);

    queue.printWholeQueue(); // Output: 30 40 50 60 70

    System.out.println(queue.getFirst()); // Output: 30
    System.out.println(queue.getLast());  // Output: 70
}
```

This ensures the circular queue implementation works as expected!

* */

class Queue_Array<T> {

    int capacity = 0, start_idx = -1, end_idx = -1, current_size = 0;
    T[] queue;

    Queue_Array(int capacity) {
        this.capacity = capacity;
//        noinspection unchecked
        queue = (T[]) new Object[this.capacity];
    } // completed

    void reset() {
        start_idx = end_idx = -1;
        current_size = 0;
        //noinspection unchecked
        queue = (T[]) new Object[capacity];
    }

    boolean isEmpty() {
        return current_size == 0;
    } // completed

    boolean isFull() {
        return current_size == capacity;
    } // completed

    void enQueue(T e) {
        if (isFull()) { /*case 1 : Queue Overflow*/
            System.out.println("Queue capacity is FULL !");
            return; // should terminate here, OTHERWISE IT WILL INCREMENT current_size VALUE
        } else if (isEmpty()) { /*case 2 : totally empty*/
            start_idx = end_idx = 0;
            queue[0] = (T) e;
        } else { /*case 3 : partial empty*/
            /*first check; then change end_index; then assign element*/
            if (end_idx == (capacity - 1)) {
                end_idx = 0;
            }
            // else {
            //     end_idx++; // ------------------------------ UNNECESSARY, eta lage na
            // }
            queue[end_idx++] = (T) e; // --------------------- end_idx++ korte vule giyechilam
        }
        current_size++;
    }

    T deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            /*first receive and store; then check where is next index*/
            T to_be_returned = queue[start_idx];
            queue[start_idx++] = null; // ------------------------------ start_idx++ korte vule gesilam
            if (start_idx == (capacity - 1)) {
                start_idx = 0;
            }
//            else {
//                start_idx++; // -------------------------------------- eta lage na
//            }
            current_size--;
            return to_be_returned;
        }
    }

    T getFirst() {
        if(isEmpty()){
            return null;
        } else {
            return queue[start_idx];
        }
    }

    T getLast() {
        if(isEmpty()){
            return null;
        } else {
            return queue[end_idx];
        }
    }
}

/*
* package DataStracture.Queue_;

class Queue_Array<T> {
    static class T {
        private T data;

        DATA(T data) {
            this.data = data;
        }

        public T getDataValue() {
            return data;
        }

        public void setDataValue(T data) {
            this.data = data;
        }
    }

    private int capacity = 0, start_idx = -1, end_idx = -1, current_size = 0;
    private T[] queue;

    @SuppressWarnings("unchecked")
    Queue_Array(int capacity) {
        this.capacity = capacity;
        queue = (T[]) new DATA[this.capacity];
    }

    void reset() {
        start_idx = end_idx = -1;
        current_size = 0;
        queue = (T[]) new DATA[capacity];
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
        queue[start_idx] = null; // Clear the dequeued position
        start_idx = (start_idx + 1) % capacity;
        current_size--;

        if (current_size == 0) { // Reset indices if queue becomes empty
            start_idx = end_idx = -1;
        }

        return to_be_returned;
    }

    T getFirst() {
        return isEmpty() ? null : queue[start_idx];
    }

    T getLast() {
        return isEmpty() ? null : queue[end_idx];
    }
}

* */
