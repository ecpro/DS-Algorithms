package com.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void sort(Comparable[] input) {
        mergeSort(input, 0, input.length - 1);
    }

    private static void mergeSort(Comparable[] input, int startIdx, int endIdx) {
        if(startIdx < endIdx) {
            int midIdx = (endIdx - startIdx) / 2 + startIdx;
            mergeSort(input, startIdx, midIdx);
            mergeSort(input, midIdx + 1, endIdx);
            merge(input, startIdx, midIdx, endIdx);
        }
    }

    private static void merge(Comparable[] input, int startIdx, int midIdx, int endIdx) {
        Comparable[] copy = Arrays.copyOf(input, input.length);
        int i = startIdx;
        int j = midIdx + 1;
        for(int k = startIdx; k <= endIdx; k++) {
            if(i > midIdx) input[k] = copy[j++];
            else if(j > endIdx) input[k] = copy[i++];
            else if(SortingUtils.less(copy[i], copy[j])) input[k] = copy[i++];
            else input[k] = copy[j++];
        }
    }

    public static void main(String[] args) {
        Integer[] input = {4,3,2,0,9,1,10};
        sort(input);
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
    }
}