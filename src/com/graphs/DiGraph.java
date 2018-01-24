package com.graphs;

import edu.princeton.cs.algs4.Bag;

public class DiGraph {
	private final Bag<Integer> [] adjList;
	private final int numVertices;
	
	@SuppressWarnings("unchecked")
	public DiGraph(int numVertices) {
		if(numVertices < 0) throw new IllegalArgumentException("Number of vertices [" + numVertices + "] must not be negative");
		this.numVertices = numVertices;
		this.adjList = (Bag<Integer> []) new Bag[this.numVertices];
		for(int i = 0; i < this.numVertices; i++) {
			this.adjList[i] = new Bag<Integer>();
		}
	}
}
