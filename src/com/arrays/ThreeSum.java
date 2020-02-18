package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    /**
     * Write a function that takes in a non-empty array of distinct integers and an integer representing a target sum.
     * The function should find all triplets in the array that sum up to the target sum and return a two-dimensional array of all these triplets.
     * The numbers in each triplet should be ordered in ascending order, and the triplets themselves should be ordered in ascending order with respect to the numbers they hold.
     * If no three numbers sum up to the target sum, the function should return an empty array.
     * Sample input: [12, 3, 1, 2, -6, 5, -8, 6], 0
     * Sample output: [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
     */

    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        List<Integer[]> result = new ArrayList<>();
        Arrays.sort(array);
        for(int i = 0; i <= array.length - 3; i++) {
            int curr = i;
            int left = curr + 1;
            int right = array.length - 1;
            while(left < right) {
                int sum = array[curr] + array[left] + array[right];
                if(sum < targetSum) {
                    left++;
                    continue;
                }
                if(sum > targetSum) {
                    right--;
                    continue;
                }
                Integer [] sol = {array[curr], array[left], array[right]};
                result.add(sol);
                left++;
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {12, 3, 1, 2, -6, 5, -8, 6};
        List<Integer[]> integers = threeNumberSum(input, 0);
        integers.forEach(integer -> {
            Arrays.stream(integer).forEach(System.out::print);
            System.out.println("");
        });
    }

}
