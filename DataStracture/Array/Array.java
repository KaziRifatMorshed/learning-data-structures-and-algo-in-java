package DataStracture.Array;

import java.util.Arrays;
import java.util.Random;

class Learning_one_dimension_array {
    private int[] one_dimension_array;

    public Learning_one_dimension_array(int n) {
        this.one_dimension_array = new int[n];
    }

    public Learning_one_dimension_array(int[] arr) {
        this.one_dimension_array = arr;
    }

    void use_sample_array() {
//        new Learning_one_dimension_array(9);
        this.one_dimension_array = new int[]{12, 45, 56, 88, 12, 45, 36, 25, 84};
    }

    void shuffle() {
        Random random = new Random();
        for (int i = 0; i < one_dimension_array.length; i++) {
            int j = random.nextInt(one_dimension_array.length);
            while (i == j) {
                j = random.nextInt(one_dimension_array.length);
            }
            { // swapping elements
                int temp = one_dimension_array[i];
                one_dimension_array[i] = one_dimension_array[j];
                one_dimension_array[j] = temp;
            }
        }
    } // done

    int getLength() {
        return this.one_dimension_array.length;
    }

    void print_arr() {
        System.out.println(Arrays.toString(this.one_dimension_array));
    }

    public static void main(String[] args) {
//        Learning_one_dimension_array array = new Learning_one_dimension_array(9);
        Learning_one_dimension_array array = new Learning_one_dimension_array(new int[]{1, 2, 3, 4, 5});
//        array.use_sample_array();
        array.print_arr();
        array.shuffle();
        array.print_arr();
    }
}

// =====================================================================================================================

class Learning_two_dimension_array {
    private int[][] two_dimension_array;

    Learning_two_dimension_array(int n, int m) {
        this.two_dimension_array = new int[n][m];
    }

    void use_sample_array() {
        this.two_dimension_array = new int[][]{{12, 45, 56}, {88, 12, 45}, {36, 25, 84}};
    }

    void print_arr() {
        System.out.println(Arrays.deepToString(this.two_dimension_array));
    }

    int getLength() {
        return this.two_dimension_array.length;
    }

    int calculate_sum_of_diagonal_elements() {
        int sum = 0;
        for (int i = 0; i < this.two_dimension_array.length; i++) {
            for (int j = 0; j < this.two_dimension_array[0].length; j++) {
                if (i == j || (this.two_dimension_array.length - 1) == (i + j)) {
                    sum += this.two_dimension_array[i][j];
                }
            }
        }
        return sum;
    } // DONE

    static int calculate_sum_of_corner_elements(int[][] matrix) {
        int num_of_rows = matrix.length, num_of_col = matrix[0].length, sum = 0;
        for (int i = 0; i < num_of_rows; i++) {
            for (int j = 0; j < num_of_col; j++) {
                if (i == 0 || j == 0 || i == num_of_rows - 1 || j == num_of_col - 1) {
                    sum += matrix[i][j];
                }
            }
        }
        return sum;
    } // done


    public static void main(String[] args) {
        Learning_two_dimension_array array = new Learning_two_dimension_array(3, 3);
        array.use_sample_array();
        array.print_arr();
        System.out.println(STR."Sum of diagonal element is = \{array.calculate_sum_of_diagonal_elements()}");
    }
}

// =====================================================================================================================

class Learning_three_dimension_array {
    private int[][][] three_dimension_array;

    Learning_three_dimension_array(int n, int m, int p) {
        this.three_dimension_array = new int[n][m][p];
    }

    Learning_three_dimension_array(int[][][] arr) {
        this.three_dimension_array = arr;
    }

    int[][][] add_two_3d_arrays(int[][][] a, int[][][] b) throws ArrayIndexOutOfBoundsException {
        // Checking if arrays are of the same size in all dimensions
        if ((a.length != b.length) || (a[0].length != b[0].length) || (a[0][0].length != b[0][0].length)) {
            System.out.println("3d array a and b are not of same size");
            throw new ArrayIndexOutOfBoundsException("3D arrays a and b are not of same size");
        }
        int[][][] sum = new int[a.length][a[0].length][a[0][0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int k = 0; k < a[0][0].length; k++) {
                    sum[i][j][k] = a[i][j][k] + b[i][j][k];
                }
            }
        }
        return sum;
    } // should work

    void print_arr() {
        System.out.println(Arrays.deepToString(this.three_dimension_array));
    }

}
