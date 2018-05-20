package com.sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void sort(Comparable[] input) {

        if(input == null || input.length == 0) throw new IllegalArgumentException("input cannot be null or empty");

        for(int i = 0; i < input.length; i++) {
            int minIndex = i;
            for(int j = i+1; j < input.length; j++) {
                if(SortingUtils.less(input[j], input[minIndex])) {
                    minIndex = j;
                }
            }
            SortingUtils.swap(input, i, minIndex);
        }
    }

    public static void main(String[] args) {
        Integer[] input = {4,3,2,0,9,1,10};
        sort(input);
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));

    }

}
