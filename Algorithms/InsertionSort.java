package Algorithms;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
//        int[] data = new int[]{-5, 20, 3, 9, 4, -4, 7, 2, 0, 45, 5, 8, 14, 5, 8, 4, 52};
        int[] data = new int[]{-5, 20, 0, Integer.MAX_VALUE, -999,0, 3, 9, Integer.MIN_VALUE, 4, -4};
        System.out.println("Before = " + Arrays.toString(data));
        insertionSort3(data);
        System.out.println("After = " + Arrays.toString(data));
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

//    private static void insertionSort(int[] data) { // Goodrich book
//        int n = data.length;
//        for (int k = 1; k < n; k++) {
//            int curr = data[k];
//            int i = k;
//            for (; i > 0 && data[i - 1] > curr; i--) {
//                data[i] = data[i - 1];
//            }
//            data[i] = curr;
//        }
//    } // Done

    private static void insertionSort2(int[] data) { // feels better
        for (int i = 01; i <= data.length; i++) { //-------------- i <= data.length
            for (int j = i - 1; j > 0; j--) {
                if (data[j - 1] > data[j]) {
                    swap(data, j, j - 1); // https://youtu.be/Q1JdRUh1_98?si=U_GtpSOpPs__Sizu
                }
            }
        }
    }

    private static void insertionSort3(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                swap(arr, j - 1, j);
            }
        }
    }
}// DONE
