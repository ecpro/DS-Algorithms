package com.sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(Comparable[] input) {
        for(int i = 1; i < input.length; i++) {
            for(int x = i; x >=1; x--) {
                if(!SortingUtils.less(input[x], input[x-1])) {
                    break;
                }
                else {
                    SortingUtils.swap(input, x, x-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] input = {4,3,2,0,9,1,10};
        sort(input);
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
    }
}
