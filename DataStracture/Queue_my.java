package DataStracture;

class Queue_my { // inefficient
    private int size_of_array = -1;
    private int occupied_count = 0;
    //    private int start_idx = 0, end_idx = 0;
    private int[] queue_array;

    public Queue_my(int size_of_array) {
        this.size_of_array = size_of_array;
        queue_array = new int[size_of_array];
    }

    private boolean isEmpty() {
        return occupied_count == 0;
    }

    private boolean isFull() {
        return occupied_count == size_of_array;
    }

    private void enQueue(int new_data) {
        if (isFull()) {
            System.out.println("Queue is full ! Can not add more !!!");
        } else {
            queue_array[occupied_count] = new_data;
            occupied_count++;
            System.out.println(STR."Added \{new_data} to queue");
        }
    }

    private int deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty !!! Can not delete first member from queue !!!");
            return -99999; // fix issue
        } else {
            int value_to_return = queue_array[0];
            for (int i = 0; i < occupied_count - 1; i++) {
                queue_array[i] = queue_array[i + 1];
            }
            occupied_count--; // UPDATE
            System.out.println(STR."Removed \{value_to_return} from queue");
            return value_to_return;
        }
    }

    private void print_whole_queue() {
        System.out.print(STR."Printing Whole Queue : [ ");
        for (int i = 0; i < occupied_count; i++) {
            System.out.print(STR."\{queue_array[i]} ");
        }
        System.out.println("]");
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        Queue_my myQueue = new Queue_my(5);
        myQueue.enQueue(1);
        myQueue.enQueue(2);
        myQueue.enQueue(3);
        myQueue.enQueue(4);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(7);
        myQueue.print_whole_queue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.print_whole_queue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.print_whole_queue();
    }
} // done
