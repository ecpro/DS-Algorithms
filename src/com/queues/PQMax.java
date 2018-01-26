package com.queues;

import edu.princeton.cs.algs4.Graph;

public class PQMax<T extends Comparable<T>> {
	private final T[] key;
	private int N;

	@SuppressWarnings("unchecked")
	public PQMax(int capacity) {
		key = (T[]) new Comparable[capacity];
		N = 0;
	}

	public void insert(T key) {
		this.key[N++] = key;
		swim(N);
	}

	private void swim(int k) {
		while (k > 1 && less(this.key[k / 2], this.key[k])) {
			exchange(this.key[k / 2], this.key[k]);
			k = k / 2;
		}
	}

	private void sink(int k) {
		
	}

	private void exchange(T x, T y) {
		T temp = x;
		x = y;
		y = temp;
	}

	private boolean less(T x, T y) {
		return x.compareTo(y) < 0 ? true : false;
	}

	public static void main(String[] args) {
		PQMax<Integer> pq = new PQMax<>(16);
	}

}
