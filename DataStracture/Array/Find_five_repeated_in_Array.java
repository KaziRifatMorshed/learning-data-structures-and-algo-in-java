package DataStracture.Array;

/*
C-3.18
Let B be an array of size n ≥ 6 containing integers from 1 to n − 5 inclusive, five
of which are repeated. Describe an algorithm for finding the five integers in B
that are repeated.
 */

import java.util.Arrays;

public class Find_five_repeated_in_Array {
    private static int[] search_repeated_int(int[] data) {
        int[] repeated_elements = new int[5];
        int repeated_elements_count = 0;
        for (int i = 0; i < data.length; i++) { // do not use for each loop
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] == data[j]) { // repetition found
                    repeated_elements[repeated_elements_count++] = data[j];
                    System.out.println(STR."Repetition found for \{data[j]}");
                }
            }
        }
        return repeated_elements;
    } // DONE

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1, 9, 15, 94, 74, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(search_repeated_int(arr)));
    }
}
