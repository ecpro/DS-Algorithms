package com.arrays;

import com.stack.RandomizedQueue;
import edu.princeton.cs.algs4.StdIn;

/**
 * Created by eccspro on 20/01/18.
 */
public class Permutation {

    public static void main(String args []) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        for(int i = 0; i < k; i++) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }

        for(int i = 0; i < k && !rq.isEmpty(); i++) {
            System.out.println(rq.dequeue());
        }
    }
}
