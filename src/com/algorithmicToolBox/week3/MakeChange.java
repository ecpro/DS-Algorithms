package com.algorithmicToolBox.week3;

import java.util.Arrays;
import java.util.Comparator;

public class MakeChange {

    public static int makeChange(int val, Integer[] den) {
        int count = 0;
        Arrays.sort(den, Comparator.reverseOrder());
        while (val > 0) {
            for (int i = 0; i < den.length; i++) {
                if(val >= den[i]) {
                    count++;
                    val = val - den[i];
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Integer [] demoninations = {5,1,10};
        int numDenominations = makeChange(2, demoninations);
        System.out.println(numDenominations);
    }

}
