package com.backtracking;

import java.util.LinkedHashSet;
import java.util.Set;

public class SubsetSum {

    public static void findSubset(int[] arr, int startIdx, Set<Integer> output, int sum, int refSum) {
        if(sum == refSum) {
            System.out.println(output);
            return;
        }
        //if(startIdx >= arr.length) return;
        if(sum > refSum) return;
        for(int i = startIdx; i < arr.length; i++) {
            int localSum = sum + arr[i];
            output.add(arr[i]);
            findSubset(arr, i + 1, output, localSum, refSum);
            output.remove(arr[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,3,1,5,9,7,6,2,10,8};
        //int[] arr = {8};
        findSubset(arr, 0, new LinkedHashSet<>(), 0, 8);
    }
}
