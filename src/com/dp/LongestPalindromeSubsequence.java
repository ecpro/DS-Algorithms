package com.dp;

public class LongestPalindromeSubsequence {
    public static int LPSLengthRecursive(String input, int startPos, int endPos) {

        if(startPos > endPos) return 0;
        if(startPos == endPos) return 1;

        if(input.charAt(startPos) == input.charAt(endPos)) {
            return 2 + LPSLengthRecursive(input, startPos+1, endPos-1);
        }
        return Math.max(LPSLengthRecursive(input, startPos+1, endPos),
                LPSLengthRecursive(input, startPos, endPos-1));
    }

    public static int LPSLength(String input) {
        int [][] dp = new int[input.length()][input.length()];
        for (int i = 0; i < input.length(); i++) {
            dp[i][i] = 1;
        }
        for(int i = 2; i <= input.length(); i++) {
            for(int start = 0; start + i <= input.length(); start++) {
                int end = start+i - 1;
                if(input.charAt(start) == input.charAt(end)) {
                    dp[start][end] = 2 + dp[start+1][end-1];
                }
                else {
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
            }
        }
        return dp[0][input.length() - 1];
    }

    public static void main(String[] args) {
        /*System.out.println(LPSLengthRecursive("cddpd", 0, 4));
        System.out.println(LPSLengthRecursive("abdbca", 0, 5));*/
        int result = LPSLength("abdbca");
        System.out.println(result);
    }
}
