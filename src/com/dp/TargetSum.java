package com.dp;

import java.util.Arrays;
import java.util.Comparator;

public class TargetSum {

    public int findTargetSum(int [] num, int targetSum) {
        return targetSumHelper(num, 0, 0, targetSum);
    }

    private int targetSumHelper(int[] num, int currSum, int currIndex, int targetSum) {
        if(currIndex >= num.length) {
            if(currSum == targetSum) return 1;
            else return 0;
        }
        int c1 = targetSumHelper(num, currSum + num[currIndex], currIndex+1, targetSum);
        int c2 = targetSumHelper(num, currSum - num[currIndex], currIndex+1, targetSum);
        return c1 + c2;
    }

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        int[] num = {1, 1, 2, 3};
        System.out.println(ts.findTargetSum(num, 1));
        num = new int[]{1, 2, 7, 1};
        System.out.println(ts.findTargetSum(num, 9));

        int [] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(ts.coinChange(coins, amount));
        coins = new int []{186,419,83,408};
        amount = 6249;
        System.out.println(ts.coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int minCoins = 0;
        int index = coins.length - 1;
        while(amount > 0 && index >= 0) {
            if(coins[index] <= amount) {
                minCoins++;
                amount = amount - coins[index];
            }
            else {
                index--;
            }
        }
        return amount == 0 ? minCoins : - 1;
    }

}
