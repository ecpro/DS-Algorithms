package com.algorithmicToolBox.week2;

public class LastDigitFibonacci {

    public static int findingFibModuloTen(int n) {
        int fibNMinus2 = 0;
        int fibNMinus1 = 1;
        for (int i = 2; i <= n; i++) {
            int fibI = (fibNMinus1 + fibNMinus2) % 10;
            fibNMinus2 = fibNMinus1;
            fibNMinus1 = fibI;
        }
        return fibNMinus1;
    }

    public static void main(String[] args) {
        System.out.println(findingFibModuloTen(2));
        System.out.println(findingFibModuloTen(3));
        System.out.println(findingFibModuloTen(4));
        System.out.println(findingFibModuloTen(5));
        System.out.println(findingFibModuloTen(327305));
        System.out.println(findingFibModuloTen(331));
    }
}
