package Algorithms;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] data = new int[]{-5, 20, 3, 9, 4, -4, 7, 2, 0, 45, 5, 8, 14, 5, 8, 4, 52};
        int[] data = new int[]{-5, 20, 0, -999, 3, 9, 4, -4};
        System.out.println(STR."Before = \{Arrays.toString(data)}");
        quickSort(data, 0, data.length - 1); // remember, passing INDEX
        System.out.println(STR."After = \{Arrays.toString(data)}");
    }

    private static void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int p = position(data, low, high);
            quickSort(data, low, p - 1);
            quickSort(data, p + 1, high);
        }
    }

    private static int position(int[] data, int low, int high) {
        int i = low, j = low;
        int pivot_value = data[high];
        while (i <= high) {
            if (data[i] <= pivot_value) {
                swap(data, i, j);
                j++;
            }
            i++;
        }
        return j - 1; // return pivot position
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
} // DONE
