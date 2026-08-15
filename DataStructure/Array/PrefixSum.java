package DataStructure.Array;

public class PrefixSum {
    private int[] prefixSumInSpace(int[] inputArr) {
        for (int i = 1; i < inputArr.length; i++) {
            inputArr[i] += inputArr[i - 1];
        }
        return inputArr;
    }

    private int[] prefixSum(int[] inputArr) {
        int[] prefixSumArr = new int[inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            if (i == 0) prefixSumArr[i] = inputArr[i];
            else {
                prefixSumArr[i] = inputArr[i] + prefixSumArr[i - 1];
            }
        }
        return prefixSumArr;
    }
}
