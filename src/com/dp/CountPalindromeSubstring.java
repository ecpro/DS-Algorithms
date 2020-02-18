package com.dp;

public class CountPalindromeSubstring {

    public static int count(String input) {
        int count = input.length();
        int[][] dp = new int[input.length()][input.length()];
        for (int i = 0; i < input.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = 2; i <= input.length(); i++) {
            for (int start = 0; start + i <= input.length(); start++) {
                int end = start + i - 1;
                if (input.charAt(start) == input.charAt(end)) {
                    int remainingSize = end - start - 1;
                    if (dp[start + 1][end - 1] == remainingSize) {
                        dp[start][end] = 2 + remainingSize;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(count("abdbca"));
        System.out.println(count("cddpd"));
        System.out.println(count("pqr"));
    }
}
