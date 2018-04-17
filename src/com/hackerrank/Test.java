package com.hackerrank;

public class Test {


    public void usePrint() {
        print("hello");
        print(1);
    }

    public final void print(int x) {
        System.out.println(x);
    }

    private void print(String x) {
        System.out.println(x);
    }

    public static void main(String args[]) {
        Test t = new Test();
        t.usePrint();
    }
}