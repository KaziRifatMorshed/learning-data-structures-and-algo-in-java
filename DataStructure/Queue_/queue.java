package DataStructure.Queue_;

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
