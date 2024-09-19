package Algorithms;

public class BinarySearchRecursive { // RECURSIVE BinarySearch
    public static int BinarySearch(int[] arr, int left, int right, int key) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] == key) {
            return mid;                // returning position
        }
        if (key < arr[mid]) {
            return BinarySearch(arr, left, mid - 1, key);
        } else {
            return BinarySearch(arr, mid + 1, right, key);
        }

    }

    public static void main(String[] args) {
        System.out.println("Hello World\nI am going to implement binary search");
        int[] arr =
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 20, 50, 40, 80, 99,
                        101};
        System.out.println(STR."number 20 is in position: \{BinarySearch(arr, 0, arr.length, 20)}"); // arr.length
    }
}
