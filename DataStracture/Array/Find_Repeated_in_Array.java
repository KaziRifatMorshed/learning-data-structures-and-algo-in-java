package DataStracture.Array;

/*
C-3.17
Let A be an array of size n ≥ 2 containing integers from 1 to n − 1 inclusive, one
of which is repeated. Describe an algorithm for finding the integer in A that is repeated.
 */

import java.lang.reflect.Array;
import java.util.Arrays;

public class Find_Repeated_in_Array {
    private static int search_repeated_int(int[] data) {
//        int[] arr2 = new int[data.length];
        for (int i : data) {
            for (int j = i + 1; j < data.length; j++) { // search first
                if (data[j] == data[i]) { // found
                    return j;
                }
            }
        }
        return -1; // -1 is not a valid index, this will be returned when we do not found the desired one in the array
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 2, 5};
        System.out.println(search_repeated_int(arr));
        System.out.println(Arrays.toString(arr));
    }
} // done
