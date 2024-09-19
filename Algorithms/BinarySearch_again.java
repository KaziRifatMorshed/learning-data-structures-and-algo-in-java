package Algorithms;

public class BinarySearch_again {

    static boolean BinarySearch(int[] a, int key, int start, int end) {
        boolean result = false;
        int mid = (start + end) / 2;
        if (key == a[mid]) {
            result = true;
        }
        if (start < end) { // this is necessary to stop infinite recursion // aka BASE CASE
            if (key <= a[mid]) {
                result = BinarySearch(a, key, start, mid);
            } else if (key > a[mid]) {
                result = BinarySearch(a, key, mid + 1, end);
            }
        }
        return result;
    }

    static void main(String[] args) {
        int[] arr = {-5, 0, 1, 3, 4, 5, 7, 9, 19};
        boolean is_found = BinarySearch(arr, 0, 0, arr.length);
        System.out.println(is_found);
    }
}
