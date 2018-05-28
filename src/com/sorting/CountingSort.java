package com.sorting;

import java.util.Arrays;

public class CountingSort {

    private CountingSort(){}

    public static void sort(int [] input, int maxValue) {
        final int[] freq = new int[maxValue + 1];

        Arrays.stream(input).forEach(value -> freq[value] = freq[value] + 1);

        for(int i = 1; i < freq.length; i++) {
            freq[i] = freq[i] + freq[i - 1];
        }

        int[] aux = Arrays.copyOf(input, input.length);

        Arrays.stream(aux).forEach(value -> {
            int index = freq[value] - 1;
            input[index] = value;
            freq[value] = freq[value] - 1;
        });

    }

    public static void main(String[] args) {
        int[] a = {1,4,2,2,9,1,4,6,9,1,2,1};
        int max = 9;
        sort(a, max);

        Arrays.stream(a).forEach(value -> System.out.print(value + " "));

    }

}
