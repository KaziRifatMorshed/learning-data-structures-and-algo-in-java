package LeetcodeSolving.leetcode.editor.en;
//Given a string s, find the first non-repeating character in it and return its
//index. If it does not exist, return -1. 
//
// Example 1: 
// Input: s = "leetcode"
// Output: 0
// Explanation:
// The character 'l' at index 0 is the first character that does not occur at
//any other index. 
//
// Example 2: 
// Input: s = "loveleetcode"
// Output: 2

// Example 3:
// Input: s = "aabb"
// Output: -1
//
// 
// Constraints: 
// 1 <= s.length <= 10⁵
// s consists of only lowercase English letters. 
// 
//
// Related Topics Hash Table String Queue Counting 👍 9094 👎 304


//leetcode submit region begin(Prohibit modification and deletion)

class firstUniqChar {
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

    public int firstUniqChar(String s) {
        Queue_Array<Integer> queue = new Queue_Array<>(100);
        int result = -1;
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int charNum = (int) (c - 'a');
            arr[charNum]++;
            if (arr[charNum] == 1) {
                queue.enQueue(i);
            }
        }

        while (!queue.isEmpty()) {
            int strIndex = queue.deQueue();
            if (arr[s.charAt(strIndex) - 'a'] == 1) {
                result = strIndex;
                break;
            }
        }
        return result;
    }
} // DONE

//leetcode submit region end(Prohibit modification and deletion)
