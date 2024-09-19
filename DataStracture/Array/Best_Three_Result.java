package DataStracture.Array;

// There are 40 students in a class. They have 4 class tests for each student.
// Find the avg of the best 3 class tests for each student.

public class Best_Three_Result {
    public static void main(String[] args) {
        int[][] array = new int[40][4];
        for (int row_stu = 0; row_stu < array.length; row_stu++) { // traverse all students
            int sum = 0, min = array[row_stu][0];
            for (int mark_col = 0; mark_col < array[0].length; mark_col++) { // traverse all marks
                sum += array[row_stu][mark_col];
                if (min > array[row_stu][mark_col]) {
                    min = array[row_stu][mark_col];
                }
            }
            float avg = (float) (sum - min) / 3;
            System.out.println(STR."Student no :\{row_stu}, avg marks : \{avg}");
        }
    }
} // DONE