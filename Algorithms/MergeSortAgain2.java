package Algorithms;

import java.util.Arrays;

public class MergeSortAgain2 {

    public static void main(String[] args) {
//        int[] data = new int[]{-5, 20, 3, 9, 4, -4, 7, 2, 0, 45, 5, 8, 14, 5, 8, 4, 52};
        System.out.println("Merge Sort AGAIN_2");
        int[] data = new int[]{-5, 20, 3, 9, 4, -4, 0};
        System.out.println(STR."Given = \{Arrays.toString(data)}");
        mergeSort(data, 0, data.length - 1);
        System.out.println(STR."After = \{Arrays.toString(data)}");
    }

    public static void mergeSort(int[] data, int start, int end) {
        if (start < end) { // REMEMBER
            int mid = start + (end - start) / 2;
            mergeSort(data, start, mid);
            mergeSort(data, mid + 1, end);
            merge(data, start, mid, end);
        }
    }

    public static void merge(int[] data, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int i = start, j = mid + 1, k = 0;
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
        while (j <= end) {
            temp[k++] = data[j++];
        }

//        if (end + 1 - start >= 0) System.arraycopy(temp, start - start, data, start, end + 1 - start);
        for (int p = start; p <= end; p++) {
            data[p] = temp[p - start];
        }
    }
} // CORRECT & WORKS
