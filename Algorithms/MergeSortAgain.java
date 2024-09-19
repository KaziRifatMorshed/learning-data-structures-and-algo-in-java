package Algorithms;

import java.util.Arrays;

public class MergeSortAgain {

    // এই কোডে আমি টেম্প এ আগে কপি করে টেম্প থেকে i, j রান করে জাজ করে মেইন ডাটা নামক এরে তে মার্জড ইন্টিজার ওভাররাইট করেছি

    public static void main(String[] args) {
//        int[] data = new int[]{-5, 20, 3, 9, 4, -4, 7, 2, 0, 45, 5, 8, 14, 5, 8, 4, 52};
        System.out.println("Merge Sort AGAIN");
        int[] data = new int[]{-5, 20, 3, 0, 9, 4, -4, 999};
        System.out.println(STR."Given = \{Arrays.toString(data)}");
        mergeSort(data, 0, data.length - 1);
        System.out.println(STR."After = \{Arrays.toString(data)}");
    }

    public static void mergeSort(int[] data, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(data, start, mid);
            mergeSort(data, mid + 1, end);
            merge(data, start, mid, end);
        }
    }

    public static void merge(int[] data, int start, int mid, int end) {

        int[] temp = new int[end - start + 1]; // new int[data.length] MISTAKE
//        for (int i = 0; i < data.length; i++) {
        for (int i = start; i <= end; i++) { // i <= end
            temp[i - start] = data[i];
        }
//        System.out.println(Arrays.toString(temp));


        int i = 0, temp_mid = (temp.length / 2), j = temp_mid + 1, k = start;

        while (i <= temp_mid && j < temp.length) { // NOT j <= temp.length ;  this "<" saved me !!!
            if (temp[i] <= temp[j]) { // temp[i] <= temp[j]
                data[k++] = temp[i++];
            } else {
                data[k++] = temp[j++];
            }
        }
        while (i <= temp_mid) {
            data[k++] = temp[i++];
        }

        // Copy any remaining elements from the second half
        while (j < temp.length) {
            data[k++] = temp[j++];
        }
    }
} // DONE
