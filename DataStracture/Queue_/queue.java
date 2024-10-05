package DataStracture.Queue_;

class Queue_Array<T> {
    static class DATA<T> {
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

    int capacity = 0, start_idx = -1, end_idx = -1, current_size = 0;
    DATA<T>[] queue;

    Queue_Array(int capacity) {
        this.capacity = capacity;
//        noinspection unchecked
        queue = (DATA<T>[]) new Object[this.capacity];
    } // completed

    void reset() {
        start_idx = end_idx = -1;
        current_size = 0;
        //noinspection unchecked
        queue = (DATA<T>[]) new Object[capacity];
    }

    boolean isEmpty() {
        return current_size == 0;
    } // completed

    boolean isFull() {
        return current_size == capacity;
    } // completed

    void enQueue(DATA<T> e) {
        if (isFull()) { /*case 1 : Queue Overflow*/
            System.out.println("Queue capacity is FULL !");
            return; // should terminate here, OTHERWISE IT WILL INCREMENT current_size VALUE
        } else if (isEmpty()) { /*case 2 : totally empty*/
            start_idx = end_idx = 0;
            queue[0] = (DATA<T>) e;
        } else { /*case 3 : partial empty*/
            /*first check; then change end_index; then assign element*/
            if (end_idx == (capacity - 1)) {
                end_idx = 0;
            } else {
                end_idx++;
            }
            queue[end_idx] = (DATA<T>) e;
        }
        current_size++;
    }

    DATA<T> deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            /*first receive and store; then check where is next index*/
            DATA<T> to_be_returned = queue[start_idx];
            queue[start_idx] = null;
            if (start_idx == (capacity - 1)) {
                start_idx = 0;
            } else {
                start_idx++;
            }
            current_size--;
            return to_be_returned;
        }
    }

    DATA<T> getFirst() {

    }

    DATA<T> getLast() {

    }
}
