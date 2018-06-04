package com.sorting;

import java.util.Arrays;

public class HeapSort {

    private static void swim(Comparable[] a, int pos, boolean reverseOrder) {
        int parent = (pos - 1) / 2;
        while(pos > 0 && SortingUtils.less(a[pos], a[parent], reverseOrder)) {
            SortingUtils.swap(a, pos, parent);
            pos = parent;
            parent = (pos - 1) / 2;
        }
    }

    public static void minHeap(Comparable[]a) {
        for(int i = a.length - 1; i >= 0; i--) {
            swim(a, i, false);
        }
    }
}
