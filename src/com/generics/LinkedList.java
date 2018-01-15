package com.generics;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {

	private Node head;
	private int count = 0;
	private int total = 0;
	private int target = 0;
	private boolean isPalindrome = true;

	public void add(int key) {
		Node node = new Node(key, null);
		if (head == null) {
			head = node;
			return;
		} else {
			Node temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(new Node(key, null));
		}

	}

	public void removeNext(Node node) {
		if (node != null) {
			node.setNext(node.getNext().getNext());
			node.getNext().setNext(null);
		}
	}

	public void remove(int key) {
		if (this.head == null)
			throw new NullPointerException("Cannot delete from empty list");
		if (this.head.getKey() == key) {
			this.head = head.getNext();
			return;
		}
		Node curr = this.head.getNext();
		Node prev = this.head;
		
		// traverse pointer to required node
		while (curr.getNext() != null && curr.getKey() != key) {
			prev = curr;
			curr = curr.getNext();
		}
		
		if(curr.getKey() == key) {
			prev.setNext(curr.getNext());
			curr.setNext(null);
		}
		
	}

	public void removeDupWithoutAdditionalDS() {
		Node p1 = head.getNext();
		while (p1.getNext() != null) {
			Node p2 = p1;
			while (p2.getNext() != null) {
				if (p2.getNext().getKey() == p1.getKey()) {
					p2.setNext(p2.getNext().getNext());
				} else {
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

		while (p1 != null) {
			if (!set.contains(p1.getKey())) {
				set.add(p1.getKey());
				p1 = p1.getNext();
				p2 = p2.getNext();
			} else {
				Node deleted = p1;
				p1 = p1.getNext();
				p2.setNext(p1);
				deleted = null;
			}
		}
	}

	public void kthToLastNode(Integer k) {
		kthtoLastNode(this.head, 3);
	}

	private void kthtoLastNode(Node node, Integer k) {
		if (node == null) {
			this.count++;
			return;
		}
		kthtoLastNode(node.getNext(), k);
		if (count == k) {
			System.out.println("Kth node is " + node.getKey() + " where k is " + k);
		}
		count++;
		return;
	}

	public void removeMiddle() {
		removeMiddle(this.head.getNext());
	}

	private void removeMiddle(Node node) {
		if (node.getNext() == null) {
			this.total++;
			this.target = (int) Math.ceil(this.total / 2);
			return;
		}
		this.total++;
		removeMiddle(node.getNext());
		this.count++;
		if (this.total - this.count == this.target - 1) {
			node.setNext(node.getNext().getNext());
		}
	}

	public void pivotAroundX(int x) {
		// find pivot node
		Node p1 = this.head.getNext();
		while (p1.getKey() < x) {
			p1 = p1.getNext();
		}

		if (p1.getNext() != null) {
			Node p2 = p1;
			while (p1 != null) {
				if (p1.getKey() < p2.getKey()) {
					int temp = p1.getKey();
					p1.setKey(p2.getKey());
					p2.setKey(temp);
					p2 = p2.getNext();
				}
				p1 = p1.getNext();
			}
		}
	}

	public void isLinkedListAPalindrome() {
		isLinkedListPalindrome(this.head.getNext(), this.head.getNext());
	}

	private void isLinkedListPalindrome(Node end, Node start) {
		if (end == null) {
			return;
		}
		isLinkedListPalindrome(end.getNext(), start);
		if (this.isPalindrome && end.getKey() != start.getKey()) {
			this.isPalindrome = false;
		}
		start = start.getNext();
	}

	private Node reverseLL(Node node) {
		if (node.getNext() == null) {
			Node temp = node;
			return temp;
		}
		Node temp = reverseLL(node.getNext());
		node.getNext().setNext(node);
		node.setNext(null);
		return temp;
	}
	
	public  void reverseLL() {
		this.head = this.reverseLL(this.head);
	}

	private void print(Node node) {
		while (node != null) {
			System.out.print(node.getKey() + "==>");
			node = node.getNext();
		}
		System.out.print("NULL\n");
	}
	
	public void print() {
		this.print(this.head);
	}

	public Node getHead() {
		return this.head;
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(2);
		list.add(4);
		list.add(6);
		list.add(4);
		list.add(10);
		list.print();
		//list.remove(6);
		list.reverseLL();
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
