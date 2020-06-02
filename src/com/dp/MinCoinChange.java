package com.dp;

import java.util.Arrays;

public class MinCoinChange {

    public int findMinCoins(int [] coins, int total) {
        Integer [][] dp = new Integer[coins.length][total + 1];
        return helper2(dp,coins, total, 0, 0);
    }

    public int helper2(Integer dp[][], int [] coins, int total, int currIndex, int coinCount) {
        if(total == 0) {
            return 0;
        }
        if(currIndex >= coins.length || coins.length == 0) {
            return Integer.MAX_VALUE;
        }
        if(dp[currIndex][total] == null) {
            int c1 = Integer.MAX_VALUE;
            if(coins[currIndex] <= total) {
                int x = helper2(dp, coins, total - coins[currIndex], currIndex, coinCount + 1);
                if(x != Integer.MAX_VALUE) {
                    c1 = x + 1;
                }
            }
            int c2 = helper2(dp, coins, total, currIndex + 1, coinCount);
            dp[currIndex][total] = Math.min(c1, c2);
        }
        return dp[currIndex][total];
    }


    /*public int helper(int [] coins, int total, int currIndex, int coinCount) {
        if(total == 0) {
            System.out.println(String.format("index %d and coin count %d", currIndex, coinCount));
            return coinCount;
        }
        if(currIndex >= coins.length || coins.length == 0) {
            //System.out.println(String.format("::::: index %d and coin count %d", currIndex, coinCount));
            return Integer.MAX_VALUE;
        }
        int c1 = Integer.MAX_VALUE;
        if(coins[currIndex] <= total) {
            c1 = helper(coins, total - coins[currIndex], currIndex, coinCount + 1);
        }
        int c2 = helper(coins, total, currIndex + 1, coinCount);
        return Math.min(c1, c2);
    }*/


    public int dpSolution(int coins[], int total) {

        // init base cache
        int cache[][] = new int[coins.length][total + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= total; j++)
                cache[i][j] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < coins.length; i++) {
            cache[i][0] = 0;
        }

        // fill remaining
        for (int i = 0; i < coins.length; i++) {
            for (int currTotal = 1; currTotal <= total; currTotal++) {
                if (i > 0) {
                    cache[i][currTotal] = cache[i - 1][currTotal];
                }
                if (coins[i] <= currTotal) {
                    int res = cache[i][currTotal - coins[i]];
                    if(res != Integer.MAX_VALUE) {
                        res = res + 1;
                        cache[i][currTotal] = Math.min(res, cache[i][currTotal]);
                    }
                }
            }
        }
        return cache[coins.length - 1][total] == Integer.MAX_VALUE ? -1 : cache[coins.length - 1][total];
    }

    public static void main(String[] args) {
        int [] num = {1,2,3};
        MinCoinChange minCoinChange = new MinCoinChange();
        //System.out.println(minCoinChange.findMinCoins(num, 5));
        System.out.println(minCoinChange.dpSolution(num, 5));
       // System.out.println(minCoinChange.findMinCoins(num, 11));

    }
}
