package DataStracture;

class my_StackOverflowException extends Exception {
    public my_StackOverflowException() {
        System.out.println("Queue is full ! Can not add more !!!");
    }
}

class my_StackUnderflowException extends Exception {
    public my_StackUnderflowException() {
        System.out.println("Queue is Empty !!! Can not delete first member from Queue !!!");
    }
}

class Queue {
    private int capacity = -1, occupied_count = 0;
    private int start_idx = -1, end_idx = -1;
    private int[] Queue_array;

    private void enQueue(int new_data) throws my_StackOverflowException {
        if (isFull()) {
            throw new my_StackOverflowException();
        } else {
//            if (occupied_count == 0) { // DO NOT PUT THIS HERE
//                start_idx = end_idx = 0;
//            }
            if (end_idx == capacity - 1) {
                end_idx = 0;
            } else {
                end_idx++;
            }
            if (occupied_count == 0) { // DO NOT FORGET THIS
                start_idx = end_idx = 0;
            }
            Queue_array[end_idx] = new_data;
            occupied_count++;
            System.out.println(STR."Added \{new_data} to Queue");
        }
    }

    private int deQueue() throws my_StackUnderflowException {
        if (isEmpty()) {
            throw new my_StackUnderflowException();
        } else {
            int value_to_return = Queue_array[start_idx++];
            if (start_idx == capacity) { // REMINDER
                start_idx = 0;
            }
            occupied_count--; // UPDATE
            System.out.println(STR."Removed \{value_to_return} from Queue");
            return value_to_return;
        }
    }

    private void print_whole_Queue() {
//        System.out.println(STR."Start IDX = \{start_idx}");
        System.out.print(STR."Printing Whole Queue : [ ");
        for (int i = start_idx, c = 0; c < occupied_count; i = (i + 1) % capacity, c++) { // i = (i + 1) % capacity GOOD IDEA
            System.out.print(STR."\{Queue_array[i]} ");
        }
        System.out.println("]");
    }

    public Queue(int capacity) {
        this.capacity = capacity;
        Queue_array = new int[this.capacity];
    }

    private boolean isEmpty() {
        return occupied_count == 0;
//        return start_idx == end_idx;
    }

    private boolean isFull() {
        return occupied_count == capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupied_count() {
        return occupied_count;
    }

    public int getEnd_idx() {
        return end_idx;
    }

    public int getStart_idx() {
        return start_idx;
    }


    public static void main(String[] args) throws my_StackOverflowException, my_StackUnderflowException {
        Queue myQueue = new Queue(5);
//        try {
        myQueue.enQueue(1);
        myQueue.enQueue(2);
        myQueue.enQueue(3);
        myQueue.enQueue(4);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(7);
        myQueue.print_whole_Queue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.print_whole_Queue();
        myQueue.deQueue();
        myQueue.deQueue();
//        myQueue.deQueue();
//        myQueue.deQueue();
//            myQueue.print_whole_Queue();
//        } catch (Exception e) {
//        }
    }
} // done
