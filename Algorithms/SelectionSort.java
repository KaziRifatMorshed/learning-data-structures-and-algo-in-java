package Algorithms;

import java.util.Arrays;

public class SelectionSort {

    static void main(String[] args) {
//        int[] data = new int[]{-5, 20, 3, 9, 4, -4, 7, 2, 0, 45, 5, 8, 14, 5, 8, 4, 52};
        int[] data = new int[]{-5, 20, 3, 9, 4, -4};
        System.out.println(STR."Before = \{Arrays.toString(data)}");
        selectionSort(data);
        System.out.println(STR."After = \{Arrays.toString(data)}");
    }

    private static void selectionSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int minimum_values_index = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < minimum_values_index) {
                    minimum_values_index = j;
                }
            }
            int temp = data[minimum_values_index];
            data[minimum_values_index] = data[i];
            data[i] = temp;
        }
    }
} // DONE
