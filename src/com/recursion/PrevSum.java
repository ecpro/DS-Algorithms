package com.recursion;

import com.stack.BracketValidator;

public class PrevSum {

	public static int prevSum(int arr[], int n) {
		if(n == 0) return arr[n];
		arr[n] = arr[n] + prevSum(arr, n-1);
		return arr[n];
	}
	
	public static void main(String[] args) {
		int a [] = {1,2,3,4,5,6};
		prevSum(a, a.length - 1);
		for(int x: a) {
			System.out.print(x + "\t");
			
		}

		BracketValidator bv = new BracketValidator();

	}
}
