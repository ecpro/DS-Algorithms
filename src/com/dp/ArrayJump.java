package com.dp;

public class ArrayJump {

    public static int findMinJumpRecursive(int [] jump, int index) {
        if(index > jump.length - 1) return Integer.MAX_VALUE;
        if(index == jump.length - 1) return 0;
        if(jump[index] == 0) return Integer.MAX_VALUE;

        int currJumps = jump[index];
        int totalJumps = Integer.MAX_VALUE;
        for(int i = 1; i <= currJumps; i++) {
            int minJumps = findMinJumpRecursive(jump, index+i);
            if(minJumps != Integer.MAX_VALUE) {
                totalJumps = Math.min(totalJumps, minJumps+1);
            }
        }
        return totalJumps;
    }

    public static int findMinJumps(int [] jumps) {
        int [] dp = new int[jumps.length];
        dp[0] = 0;
        for(int i = 1; i < jumps.length; i++) dp[i] = Integer.MAX_VALUE;
        for(int i = 0; i < jumps.length; i++) {
            int currMaxJump = jumps[i];
            for(int j = 1; j <= currMaxJump; j++) {
                if(i + j < jumps.length) {
                    dp[i+j] = Math.min(dp[i+j], dp[i] + 1);
                }
                if(i + j == jumps.length - 1){
                    return dp[i+j];
                }
            }
        }
        return dp[jumps.length - 1];
    }

    public static void main(String[] args) {
        int [] jumps = {1,1,3,6,9,3,0,1,3};
        int minJump = findMinJumps(jumps);
        System.out.println(minJump);
    }
}
