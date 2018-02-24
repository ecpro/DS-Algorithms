package com.stack;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by eccspro on 20/01/18.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node<Item> head;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        if(item == null) throw new IllegalArgumentException("Invalid Argument type");
        Node<Item> node = new Node<>(item);
        if(isEmpty()) {
            head = node;
        }
        else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Cannot dequeue from empty queue");
        Item retVal  = null;
        int position = StdRandom.uniform(size);
        if(position == 0) {
            retVal = head.item;
            head = head.next;
        }
        else {
            Node<Item> temp = head;
            for(int i = 0; i < position - 1; i++) {
                temp = temp.next;
            }
            retVal = temp.next.item;
            temp.next = temp.next.next;
        }
        size--;
        return retVal;
    }

    public int size() {
        return size;
    }

    public Item sample() {
        if(isEmpty()) throw new NoSuchElementException("Queue is empty");
        int position = StdRandom.uniform(size);
        Node<Item> node = head;
        for(int i = 0; i < position; i++) {
            node = node.next;
        }
        return node.item;
    }

    private class Node<Item> {
        private final Item item;
        private Node<Item> next;

        public Node(Item item) {
            this.item = item;
            next = null;
        }
    }

    private class MyIterator implements Iterator<Item> {

        private final Item [] curr;
        private int iteratorPosition = 0;

        public MyIterator() {
            curr = (Item [])  new Object[size];
            int index = 0;
            Node<Item> temp = head;
            while(temp != null) {
                curr[index] = temp.item;
                temp = temp.next;
                index++;
            }
            StdRandom.shuffle(curr);
        }

        @Override
        public boolean hasNext() {
            return iteratorPosition < size;
        }

        @Override
        public Item next() {
            if(iteratorPosition >= curr.length) throw new NoSuchElementException("No more items to return");
            Item item = curr[iteratorPosition];
            iteratorPosition++;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    public static void main(String args []) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        System.out.println(queue.size());

        System.out.println(queue.sample());

        Iterator<Integer> itr1 = queue.iterator();

        Iterator<Integer> itr2 = queue.iterator();

        while(itr1.hasNext()) {
           // System.out.println("itr1 " + itr1.next() + " itr2 " + itr2.next());
        }
    }
}
