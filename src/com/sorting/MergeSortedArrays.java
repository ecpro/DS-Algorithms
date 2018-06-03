package com.sorting;

import java.util.Arrays;

public class MergeSortedArrays {

    public static void merge(Integer[] a, Integer[] b, int m, int n) {
        int x = m + n - 1;
        m = m - 1;
        n = n - 1;

        while (x >= 0) {
            if(m < 0 || ( n >= 0 && b[n] > a[m])) {
                a[x] = b[n];
                n--;
            }
            else {
                a[x] = a[m];
                m--;
            }
            x--;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {2,4,9,10,11,null,null,null,null};
        Integer[] b = {3,5,6,7};

        merge(a, b, 5, 4);

        Arrays.stream(a).forEach((x)->System.out.print(x + " "));
    }
}
