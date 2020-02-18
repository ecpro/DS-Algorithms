package com.dp;

public class UnboudedKnapSack {

    public static int solveKnapsack(int[] weights, int[] profits, int capacity) {
        return solveKnapsackRecursive(weights, profits, capacity, 0);
    }

    private static int solveKnapsackRecursive(int[] weights, int[] profits, int capacity, int index) {
        if(capacity <= 0 || index >= weights.length) return 0;
        int profit1 =0; int profit2 = 0;
        if(weights[index] <= capacity) {
            profit1 = profits[index] + solveKnapsackRecursive(weights, profits, capacity - weights[index], index);
        }
        profit2 = solveKnapsackRecursive(weights, profits, capacity, index+1);
        return Math.max(profit1, profit2);
    }

    private static int solveKnapsackDP(int[] weights, int [] profits, int capacity) {
        int[][] dp = new int[weights.length][capacity+1];
        for(int i = 0; i < weights.length; i++) {
            dp[i][0] = 0;
        }
        for(int i = 0; i <= capacity; i++) {
            if(weights[0] <= i) {
                dp[0][i] = profits[0];
            }
        }

        for(int i = 1; i < weights.length; i++) {
            for(int j = 1; j <= capacity; j++) {
                if(weights[i] <= j) {
                    dp[i][j] = profits[i] + dp[i][j - weights[i]];
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[weights.length -1][capacity];
    }

    public static void main(String[] args) {
        int[] profits = { 15, 50, 60, 90 };
        int[] weights = { 1, 3, 4, 5 };
        int maxProfit = solveKnapsack(weights, profits, 8);
        int maxProfitDP = solveKnapsackDP(weights, profits, 8);
        System.out.println(maxProfit);
        System.out.println(maxProfitDP);
    }
}
