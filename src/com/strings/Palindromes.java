package com.strings;

public class Palindromes {
	
	public static boolean palindromePermutation(String input) {
		
		// assuming ascii characters : total chars 128
		// 1. build char freq map
		int [] freqMap = new int[128];
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) != ' ') {
				freqMap[input.charAt(i)]++; 
			}
		}
		
		//2. numOdd > 1 means it cannot form palindrome
		int numOdd = 0;
		for(int i = 0; i < freqMap.length; i++) {
			if(freqMap[i] % 2 != 0) {
				numOdd++;
			}
		}
		
		return numOdd > 1 ? false : true;
	}
	
	public static void main(String[] args) {
		System.out.println(palindromePermutation("aab"));
		System.out.println(palindromePermutation("aabcc bb"));
		System.out.println(palindromePermutation("taco cat"));
		System.out.println(palindromePermutation("aab aaa"));
		replaceit("hi   how  are you");
	}
	
	public static void replaceit(String x) {
		System.out.println(x.replaceAll("\\s+", " "));
	}
}
