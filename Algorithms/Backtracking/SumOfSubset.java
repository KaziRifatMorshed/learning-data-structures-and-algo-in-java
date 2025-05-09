package Algorithms.Backtracking;

import java.util.Arrays;

class SumOfSubset {
    private int n;
    private int target;
    private int[] w;
    private int[] x;

    SumOfSubset(int target, int nn, int[] ww) {
        this.target = target;
        n = nn;
        x = new int[n];
        w = ww;
        Arrays.fill(x, 0);
    }

    void sumOfSubset(int sum, int idx, int remain) {
        if (idx == n) return;
        // left child
        x[idx] = 1;
        if (sum + w[idx] == target) {
            System.out.println(Arrays.toString(x));
        } else if (sum + w[idx] < target) {
            sumOfSubset(sum + w[idx],
                    idx + 1,
                    remain - w[idx]);
        }
        // right child
        x[idx] = 0;
        if ((sum + remain - w[idx] >= target)
//                && (sum + w[idx + 1] <= target)
        ) {
            sumOfSubset(sum, idx + 1,
                    remain - w[idx]);
        }

    }

    public static void main(String[] args) {
        int[] w = {5, 10, 12, 13, 15, 18};
        SumOfSubset sumOfSubset = new SumOfSubset(30, w.length, w);
        sumOfSubset.sumOfSubset(0, 0, Arrays.stream(w).sum());
//        System.out.println(Arrays.toString(w));
    }
}
/*
[1, 1, 0, 0, 1, 0]
[1, 0, 1, 1, 0, 0]
[0, 0, 1, 0, 0, 1]
* */