package com.dp;

public class LongestPalindromeString {

    public static int lpsLengthRecursive(String input, int start, int end) {
        if (start > end) return 0;
        if (start == end) return 1;
        if (input.charAt(start) == input.charAt(end)) {
            int remainingSize = end - start - 1;
            if (lpsLengthRecursive(input, start + 1, end - 1) == remainingSize)
                return 2 + remainingSize;
        }
        return Math.max(lpsLengthRecursive(input, start+1, end),
                lpsLengthRecursive(input, start, end-1));
    }

    public static int lpsLength(String input) {
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
                    }
                } else {
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }
        }
        return dp[0][input.length() - 1];
    }

    public static void main(String[] args) {
        //System.out.println(lpsLengthRecursive("abdbca", 0, 5));
        //System.out.println(lpsLengthRecursive("abdba", 0, 4));
        System.out.println(lpsLength("abddbaeew"));
    }
}
