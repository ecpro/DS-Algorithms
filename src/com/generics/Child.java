package com.generics;

import java.io.EOFException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Child extends Parent {
	
	@Override
	public void print() throws EOFException{
		System.out.println("child");
		
		if(true) {
			throw new RuntimeException("esfdf");
		}
	}
	
	
	public static void main(String[] args) {
		
		java.util.List<String> asList = Arrays.asList("R", "C", "R", "B", "C", "B");
		
		Collections.sort(asList, new MyComparator());		
		System.out.println(asList);
		
}
}

class MyComparator implements Comparator<String> {
	
	Map<String, Integer> list = new HashMap<String, Integer>();
	
	public MyComparator() {
		list.put("R", 1);
		list.put("C", 2);
		list.put("B", 3);
	}

	@Override
	public int compare(String o1, String o2) {
		return list.get(o1).compareTo(list.get(o2));
	}
	
}