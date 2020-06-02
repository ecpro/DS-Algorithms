package com.algorithmicToolBox.week2;

public class HugeFibonacciModM {

    public static long findPisanoPeriod(Long m) {
        long F0 = 0;
        long F1 = 1;
        for (long i = 2; i <= m * m; i++) {
            long F2 = (F0 + F1) % m;
            if (F2 == 1 && F1 == 0) {
                return i - 1;
            }
            F0 = F1;
            F1 = F2;
        }
        return m;
    }

    public static long findFib(Long n, Long mod) {
        long F0 = 0;
        long F1 = 1;
        for (long i = 2; i <= n; i++) {
            long F2 = (F0 + F1) % mod;
            F0 = F1;
            F1 = F2;
        }
        return F1;
    }

    public static long findMod(Long input, Long devisor) {
        return input % devisor;
    }

    public static void main(String[] args) {
        /*long cycle = findPisanoPeriod(1000L);
        long modulo = findMod(239L, cycle);
        long fib = findFib(modulo, 1000);
        System.out.println(fib);*/

        long cycle = findPisanoPeriod(10L);
        long modulo = findMod(2816213588L, cycle);
        long fib = findFib(modulo, 30524L);
        System.out.println(fib);
    }


}
