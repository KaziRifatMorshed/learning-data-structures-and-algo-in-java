package Algorithms;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
//        int[] data = new int[]{-5, 20, 3, 9, 4, -4, 7, 2, 0, 45, 5, 8, 14, 5, 8, 4, 52};
        int[] data = new int[]{-5, 20, 3, 9, 4, -4};
        System.out.println(Arrays.toString(data));
        mergeSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    public static void mergeSort(int[] data, int start, int end) {
        if (start < end) { // start < end
//            int mid = (start + end) / 2;
            int mid = start + (end - start) / 2;
            mergeSort(data, start, mid);
            mergeSort(data, mid + 1, end);
            merge(data, start, mid, end);
        }
    }

    public static void merge(int[] data, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0; // j = mid + 1

        while (i <= mid && j <= end) {
            if (data[i] <= data[j]) {
                temp[k++] = data[i++];
            } else {
                temp[k++] = data[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = data[i++];
        }
        while (j <= end) { // j <= end
            temp[k++] = data[j++];
        }
        for (i = start; i <= end; i++) {
            data[i] = temp[i - start];
        }
    }

} // done