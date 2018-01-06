package com.generics;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {
	
	private Node head = new Node(Integer.MIN_VALUE, null);
	
	public void add(int key) {
		if(head.getNext() == null) {
			head.setNext(new Node(key, null));
			return;
		}
		else {
			Node temp = head;
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(new Node(key, null));
		}
		
	}
	
	public void removeNext(Node node) {
		if(node != null) {
			node.setNext(node.getNext().getNext());
			node.getNext().setNext(null);	
		}
	}
	
	public void removeKey(int key) {
		Node temp = this.head;
		while(temp.getNext() != null) {
			if(temp.getNext().getKey() != key) {
				temp = temp.getNext();	
			}
			else {
				Node deleted = temp.getNext();
				temp.setNext(deleted.getNext());
				deleted.setNext(null);
				break;
			}
		}
	}
	
	public void removeDupWithoutAdditionalDS() {
		Node p1 = head.getNext();
		while(p1.getNext() != null) {
			Node p2 = p1;
			while(p2.getNext() != null) {
				if(p2.getNext().getKey() == p1.getKey()) {
					p2.setNext(p2.getNext().getNext());
				}
				else {
					p2 = p2.getNext();
				}
			}
			p1 = p1.getNext();
		}
	}
	
	public void removeDuplicates() {
		Node p1 = head.getNext().getNext();
		Node p2 = head.getNext();
		Set<Integer> set = new HashSet<>();
		set.add(p2.getKey());
		
		while(p1 != null) {
			if(!set.contains(p1.getKey())) {
				set.add(p1.getKey());
				p1 = p1.getNext();
				p2 = p2.getNext();
			}
			else {
				Node deleted = p1;
				p1 = p1.getNext();
				p2.setNext(p1);
				deleted = null;
			}
		}
	}
	
	public void print() {
		Node temp = head;
		while(temp.getNext() != null) {
			System.out.print(temp.getNext().getKey() + "==>");
			temp = temp.getNext();
		}
		System.out.print("NULL\n");
	}
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(2);
		list.add(4);
		list.add(6);
		list.add(4);
		list.add(4);
		list.add(4);
		list.add(10);
		list.add(2);
		list.add(2);
		//list.removeDuplicates();
		list.removeDupWithoutAdditionalDS();
		list.print();
		
	}
	
}

class Node {
	
	private int key;
	private Node next;
	
	public Node(int key, Node node) {
		this.setKey(key);
		this.setNext(node);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	
}
