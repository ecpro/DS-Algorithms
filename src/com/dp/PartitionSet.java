package com.dp;

class PartitionSet {

    public int canPartition(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        Integer[][] dp = new Integer[num.length][sum + 1];
        return this.canPartitionRecursive(dp, num, 0, sum, 0);
    }

    private int canPartitionRecursive(Integer[][] dp, int[] num, int currentIndex, int sum, int subsetSum) {
        // base check
        if(currentIndex == num.length) {
            int rem = sum - subsetSum;
            return Math.abs(subsetSum - rem);
        }

        // check if we have not already processed similar problem
        if(dp[currentIndex][subsetSum] == null) {
            // recursive call after including the number at the currentIndex in the first set
            int diff1 = canPartitionRecursive(dp, num, currentIndex + 1, sum, subsetSum + num[currentIndex]);

            // recursive call after including the number at the currentIndex in the second set
            int diff2 = canPartitionRecursive(dp, num, currentIndex + 1, sum, subsetSum);

            dp[currentIndex][subsetSum] = Math.min(diff1, diff2);
        }

        return dp[currentIndex][subsetSum];
    }

    public static void main(String[] args) {
        PartitionSet ps = new PartitionSet();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.canPartition(num));
    }
}
