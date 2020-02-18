package com.dp;

import java.util.Arrays;

public class EqualSubsetSum {

    static boolean canPartition(int [] set) {
        int sum = Arrays.stream(set).sum();
        if(sum % 2 != 0) return false;
        return canPartitionRecursive(set, sum/2, 0);
    }

    private static boolean canPartitionRecursive(int[] set, int subSetSum, int index) {
        if(subSetSum == 0) return true;
        if(subSetSum < 0 || index >= set.length) return false;
        return canPartitionRecursive(set, subSetSum - set[index], index+1) || canPartitionRecursive(set, subSetSum, index+1);
    }

    static boolean canPartitionDP(int[] set) {
        int sum = Arrays.stream(set).sum();
        if(sum % 2 != 0) return false;
        boolean[][] dp = new boolean[set.length][sum/2 + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp[0].length; i++) {
            int val = set[0];
            if(i <= val) {
                dp[0][i] = true;
            }
        }

        for(int element = 1; element < set.length; element++) {
            for(int currSum = 1; currSum < dp[0].length; currSum++) {
                if(set[element] <= currSum) {
                    dp[element][currSum] = dp[element - 1][currSum - set[element]];
                }
                else {
                    dp[element][currSum] = dp[element - 1][currSum];
                }
            }
        }
        return dp[set.length - 1][sum/2];
    }

    public static void main(String[] args) {
        int [] set = {1,1,3,4,7};
        //System.out.println(canPartition(set));
        System.out.println(canPartitionDP(set));
    }
}
