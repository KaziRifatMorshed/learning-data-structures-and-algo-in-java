package Algorithms;

class BubbleSort {

    static void Bubble_Sort(int[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (a[i] > a[j]) { // -5 0 1 3 4 4 7 9 19
//                if (a[i] < a[j]) { // 19 9 7 4 4 3 1 0 -5
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    } // WORKING

    static void main(String[] args) {
        int[] arr = {9, 1, 7, -5, 3, 4, 19, 4, 0};
        Bubble_Sort(arr);
//        for (int i : arr){ // ????? vul kothay ?
        for (int i = 0; i < arr.length; i++) {
            System.out.print(STR."\{arr[i]},");
        }
    }
} // DONE
