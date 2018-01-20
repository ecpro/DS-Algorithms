package com.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
	
	private Node head;
	private Node tail;
	private int size;
	
	private class Node<Item> {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item item) {
            this.item = item;
            next = prev = null;
        }
    }
	
	public Deque() {
		this.head = null;
		this.tail = null;
	}
    public boolean isEmpty()  {
            return this.head == null;
    }

	   public int size() {
	        return size;
	   }

	   public void addFirst(Item item)  {
	    if(item == null) throw new IllegalArgumentException("Invalid argument type");
	    Node<Item> node = new Node<>(item);
	    if(this.size == 0) {
	        this.head = node;
	        this.tail = node;
	        this.size++;
	        return;
        }

        node.next = head;
	    this.head.prev = node;
	    this.head = node;
        this.size++;

       }

	   public void addLast(Item item)   {
           if(item == null) throw new IllegalArgumentException("Invalid method argument type");
           Node<Item> node = new Node<>(item);
           if(this.size == 0) {
               this.head = node;
               this.tail = node;
               this.size++;
               return;
           }

           node.prev = tail;
           this.tail.next = node;
           this.tail = node;
           this.size++;

       }

	   public Item removeFirst()   {
           if(this.size == 0) throw new NoSuchElementException("Cannot remove from empty queue");
           Item retVal = (Item) head.item;
           if(this.size == 1) {
               this.head = this.tail = null;
               size--;
               return retVal;
           }
           head = head.next;
           head.prev.next = null;
           head.prev = null;
           size--;
           return retVal;
       }

	   public Item removeLast(){
           if(this.size == 0) throw new NoSuchElementException("Cannot remove from empty queue");
           Item retVal = (Item) head.item;
           if(size == 1) {
               head = tail = null;
               size--;
               return retVal;
           }
           tail = tail.prev;
           tail.next.prev = null;
           tail.next = null;
           size--;
           return retVal;
       }
	   public Iterator<Item> iterator() {
           return new MyListIterator();
       }

       private class MyListIterator implements Iterator<Item> {

	       Node<Item> node = head;

           @Override
           public boolean hasNext() {
               return node != null;
           }

           @Override
           public Item next() {
               if(node == null) throw new NoSuchElementException("No more items to return");
                Item retVal = (Item) node.item;
                node = node.next;
                return retVal;
           }

           @Override
           public void remove() {
                throw new UnsupportedOperationException("Remove operation not supported");
           }
       }

	   public static void main(String[] args) {
	       Deque<Integer> deq = new Deque<>();
	       deq.addFirst(23);
	       deq.removeFirst();
	       //deq.removeLast();
	       deq.addFirst(24);
	       deq.addLast(21);
	       deq.addLast(25);

	       Iterator<Integer>  iterator = deq.iterator();

	       //deq.removeFirst();
           iterator.remove();
	       System.out.println(iterator.next());
	       System.out.println(iterator.next());
       }

}
