package com.sorting;

public class SortingUtils {

    private SortingUtils(){}

    public static void swap(Comparable[] input, int i, int j) {
        Comparable temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static boolean less(Comparable a, Comparable b, boolean reverse) {
        return b.compareTo(a) < 0;
    }
}
