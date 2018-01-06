package com.generics;

import java.io.IOException;

public class Parent {
	
	public int a() {
		try {
			return 0;
		} catch (ArithmeticException e) {
			
		}
		catch (Exception e) {
			
		}
		return 23;
	}
	
	public void print() throws IOException{
		System.out.println("Print ABC");
	}
}
