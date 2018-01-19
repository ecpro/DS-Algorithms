package com.stack;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
	
	private Node head;
	private Node tail;
	
	private class Node<Item> {
		private Item item;
		private Node next;
		private Node prev;
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Deque() {
		this.head = null;
		this.tail = null;
	}
	   public boolean isEmpty()                 // is the deque empty?
	   public int size()                        // return the number of items on the deque
	   public void addFirst(Item item)          // add the item to the front
	   public void addLast(Item item)           // add the item to the end
	   public Item removeFirst()                // remove and return the item from the front
	   public Item removeLast()                 // remove and return the item from the end
	   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	   public static void main(String[] args)   // unit testing (optional)

}
