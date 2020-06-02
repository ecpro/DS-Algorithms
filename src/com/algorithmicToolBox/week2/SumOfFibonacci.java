package com.algorithmicToolBox.week2;

public class SumOfFibonacci {


    public static long sum(long input) {
        long effectiveN = input % 60; // Pisano period of Fib % 10 is 60
        return HugeFibonacciModM.findFib(effectiveN + 2, 10L) - 1;
    }

    public static long partialSum(long m, long n) {
        /*long effectiveM = m % 60;
        long effectiveN = n % 60;
        return HugeFibonacciModM.findFib(effectiveN + 2, 10L) -
                HugeFibonacciModM.findFib(effectiveM + 1, 10L) - 1;*/
        return sum(n) - sum(m-1);
    }

    public static void main(String[] args) {
        System.out.println(sum(3));
        System.out.println(sum(4));
        System.out.println(sum(5));
        System.out.println(sum(6));
        System.out.println(sum(100));

        System.out.println(partialSum(2,7));
        System.out.println(partialSum(10,200));
    }
}
