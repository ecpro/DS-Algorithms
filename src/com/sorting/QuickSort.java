package com.sorting;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSort {

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo + 1;
        int j = hi;
        while(true) {
            while(SortingUtils.less(a[i], a[lo]) && i < hi) {
                i++;
            }
            while(SortingUtils.less(a[lo], a[j]) && j > lo) {
                j--;
            }
            if(i >= j) break;
            SortingUtils.swap(a, i, j);
        }
        SortingUtils.swap(a, lo, j);
        return j;
    }

    private static void sort(Comparable[] input, int lo, int hi) {
        if(hi > lo) {
            int pivot = partition(input, lo, hi);
            sort(input, lo, pivot - 1);
            sort(input, pivot + 1, hi);
        }
    }

    public static void sort(Comparable[] input) {
        StdRandom.shuffle(input);
        sort(input, 0, input.length - 1);
    }

    public static void main(String[] args) {
        Integer[] input = {4,3,2,0,9,1,10};
        sort(input);
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));

    }
}
