package com.dp;

public class KnacpsackProblem {

    static public int solveKnackSack(int [] weights, int [] profits, int capacity) {
        return solveKnackSackRecursive(weights, profits, capacity, 0);
    }

    static private int solveKnackSackRecursive(int[] weights, int[] profits, int capacity, int index) {
        if(index >= weights.length || weights[index] > capacity) {
            return 0;
        }
        int profitInclusive = profits[index] +
                solveKnackSackRecursive(weights, profits, capacity - weights[index], index+1);
        int profitExclusive = solveKnackSackRecursive(weights, profits, capacity, index+1);
        return Math.max(profitInclusive, profitExclusive);
    }

    static private int solveKnackSackDP(int [] weights, int [] profits, int capacity) {
        int [][] dp = new int[weights.length][capacity+1];

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }

        for(int wIndex = 1; wIndex < weights.length; wIndex++) {
            for(int currCapacity = 1; currCapacity < dp[0].length; currCapacity++) {
                if(weights[wIndex] <= currCapacity) {
                    dp[wIndex][currCapacity] = Math.max(profits[wIndex] + dp[wIndex - 1][currCapacity - weights[wIndex]], dp[wIndex - 1][currCapacity]);
                }
                else {
                    dp[wIndex][currCapacity] = dp[wIndex - 1][currCapacity];
                }
            }
        }
        return dp[weights.length -1][capacity];
    }

    public static void main(String[] args) {
        int [] weights = {1,2,3,5};
        int [] profits = {1,6,10,16};
        //int maxProfit = solveKnackSackDP(weights, profits, 7);
        int maxProfit = solveKnackSack(weights, profits, 7);
        System.out.println(maxProfit);
    }

}
