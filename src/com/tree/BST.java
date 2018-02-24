package com.tree;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	public BST() {

	}

	public BST(BST tree) {
		this.root = tree.getRoot();
	}

	public void put(Key key, Value value) {
		if(key == null) throw new IllegalArgumentException("Key cannot be null");  
		Node temp = new Node(key, value);
		if(root == null)  {root = temp; return; }
		Node curr = this.root;
		Node parent = null;
		while (curr != null) {
			parent = curr;
			if (key.compareTo(curr.key) < 0) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		if(key.compareTo(parent.key) < 0) {
			parent.left = temp;
		}
		else {
			parent.right = temp;
		}
	}

	public Node getRoot() {
		return this.root;
	}

	public Value get(Key key) {
		if(key == null) throw new IllegalArgumentException("Key cannot be null");
		if(this.root == null) new NoSuchElementException("Tree is empty");
		Node curr = this.root;
		while(curr != null) {
			if(key.compareTo(curr.key) == 0) {
				return curr.value;
			}
			if(key.compareTo(curr.key) < 0) {
				curr = curr.left;
			}
			else {
				curr = curr.right;
			}
		}
		return null;
	}

	class Node {
		
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		
		Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}

		public Key getKey() {
			return key;
		}

		public Value getValue() {
			return value;
		}
	}
	
	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.put(5, "Five");
		bst.put(3, "Three");
		bst.put(4, "Six");
		bst.put(2, "Two");
		bst.put(8, "Eight");
		bst.put(1, "One");
		
		System.out.println(bst.get(1));
		System.out.println(bst.get(4));
		System.out.println(bst.get(2));
		System.out.println(bst.get(3));
		
	}
}
