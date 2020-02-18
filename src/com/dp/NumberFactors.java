package com.dp;

public class NumberFactors {

    public static int numFactor(int N, int [] factors) {
        int [] dp = new int [N + 1];
        dp[0] = 1;

        for(int i = 1; i <=N; i++) {
            for(int j = 0; j < factors.length; j++) {
                if(factors[j] <= i) {
                    dp[i] += dp[i - factors[j]];
                }
            }
        }
        return dp[N];
    }

    public static void main(String[] args) {
        int [] factors = {1,3,4};
        System.out.println(numFactor(4, factors));
    }
}
