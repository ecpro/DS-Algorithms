package com.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringPermutation {

    public static void permute(char[] input, List<char []> output, int depth) {

        if(depth == input.length - 1) {
            output.add(Arrays.copyOf(input, input.length));
        }

        for(int i = depth; i < input.length; i++) {
            swap(input, i, depth);
            permute(input, output, depth+1);
            swap(input, i, depth);
        }
    }

    public static void swap(char[] input, int i, int x) {
        char temp = input[i];
        input[i] = input[x];
        input[x] = temp;
    }

    public static void main(String[] args) {
        char[] input = {'A', 'B', 'C'};
        List<char[]> output = new ArrayList<>();
        permute(input, output, 0);

        for(char[] x : output) {
            StringBuilder sb = new StringBuilder();
            for(char a : x) {
                sb.append(a);
            }
            System.out.println(sb.toString());
        }
    }

}
