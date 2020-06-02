package com.algorithmicToolBox.week3;

import java.util.ArrayList;
import java.util.List;

public class MaximumNumberOfPrizes {

    public static List<Integer> findDistinctNumber(int n) {
        int l = 1;
        List<Integer> nums = new ArrayList<>();
        while (l < n - l) {
            nums.add(l);
            n = n - l;
            l++;
        }
        nums.add(n);
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(findDistinctNumber(6));
        System.out.println(findDistinctNumber(8));
        System.out.println(findDistinctNumber(2));
        System.out.println(findDistinctNumber(5));
        System.out.println(findDistinctNumber(21));
        System.out.println(findDistinctNumber(27));
        System.out.println(findDistinctNumber(28));
        System.out.println(findDistinctNumber(53));
    }
}
