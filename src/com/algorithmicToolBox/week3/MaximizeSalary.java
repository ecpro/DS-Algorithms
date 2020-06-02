package com.algorithmicToolBox.week3;

import java.util.*;

public class MaximizeSalary {

    public static Comparator<String> NUM_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int min = Math.min(o1.length(), o2.length());
            for (int i = 0; i < min; i++) {
                if (o1.charAt(i) != o2.charAt(i)) {
                    return o1.charAt(i) - o2.charAt(i);
                }
            }
            if (o1.length() == o2.length()) {
                return 0;
            }
            int max = Math.max(o1.length(), o2.length());
            for (int i = min; i < max; i++) {
                if (o1.length() > o2.length()) {
                    if(o1.charAt(i) != o2.charAt(0)) {
                        return o1.charAt(i) - o2.charAt(0);
                    }
                } else {
                    if(o2.charAt(i) != o1.charAt(0)) {
                        return o1.charAt(0) - o2.charAt(i);
                    }
                }
            }
            return 0;
        }
    };

    public static Comparator<String> NUM_COMPARATOR_REVERSE = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return NUM_COMPARATOR.compare(o2, o1);
        }
    };

    public static void main(String[] args) {
        System.out.println(NUM_COMPARATOR.compare(String.valueOf(42), String.valueOf(427)));
        System.out.println(NUM_COMPARATOR.compare(String.valueOf(427), String.valueOf(4)));
        System.out.println(NUM_COMPARATOR.compare(String.valueOf(42), String.valueOf(4)));

        //{"23", "39", "92"};
        List<String> input = new ArrayList<>();
        input.add("4");
        input.add("42");
        input.add("46");
        input.add("427");
        input.add("465");
        input.sort(NUM_COMPARATOR_REVERSE);
        System.out.println(input);
    }
}
