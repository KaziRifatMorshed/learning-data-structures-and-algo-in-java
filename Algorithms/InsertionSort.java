package Algorithms;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
//        int[] data = new int[]{-5, 20, 3, 9, 4, -4, 7, 2, 0, 45, 5, 8, 14, 5, 8, 4, 52};
        int[] data = new int[]{-5, 20, 0, -999, 3, 9, 4, -4};
        System.out.println(STR."Before = \{Arrays.toString(data)}");
        insertionSort(data);
        System.out.println(STR."After = \{Arrays.toString(data)}");
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

    private static void insertionSort(int[] data) { // Goodrich book
        int n = data.length;
        for (int k = 1; k < n; k++) {
            int curr = data[k];
            int i = k;
            for (; i > 0 && data[i - 1] > curr; i--) {
//                data[i - 1] = data[i];
                data[i] = data[i - 1];
            }
            data[i] = curr;
        }
    } // Done

    private static void insertionSort2(int[] data) {
        for (int i = 0; i <= data.length; i++) { // i <= data.length
            if (data.length == 1) {
                break;
            }
            for (int j = i - 1; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1); // ??????? swap korle je Bubbe Sort hoye jay !!!!!!!!!!
                }
            }
        }
    }
}// DONE
