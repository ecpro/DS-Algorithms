package com.java8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Java8Feature {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("Piyush", "Mayank", "Yash");

		Consumer<String> con = new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		};

		names.forEach(con);

		names.forEach((name) -> {
			System.out.println(name);
		});
		
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(23, "abc", 34));
		products.add(new Product(24, "def", 21));
		products.add(new Product(25, "ijk", 12));
		
		Stream<Product> filter = products.stream().filter(p -> p.id >= 24);
		filter.forEach(p -> System.out.println(p));
	}
	
}

class Product {

	public int id;
	public String name;
	public float price;

	Product(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "id " + id + " price " + price + " name ";
	}

}
