package com.dp;

public class StairCase {

    public static int countWays(int N) {
        int [] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[N];
    }

    public static void main(String[] args) {
        System.out.println(countWays(3));
    }
}
