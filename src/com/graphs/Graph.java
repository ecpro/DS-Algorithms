package com.graphs;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by eccspro on 26/01/18.
 */
public abstract class Graph {
    private final Bag<Integer>[] adjList;
    private final int numVertices;
    private int numEdges;

    public  Graph(int numVertices) {
        if(numVertices < 0) throw new IllegalArgumentException("Number of vertices [" + numVertices + "] must not be negative");
        this.numVertices = numVertices;
        this.adjList = (Bag<Integer> []) new Bag[this.numVertices];
        for(int i = 0; i < this.numVertices; i++) {
            this.adjList[i] = new Bag<Integer>();
        }
    }

    protected void validateVertex(int v) {
        if(v < 0 || v >= this.numVertices) throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (numVertices - 1));
    }

    public Iterable<Integer> adjVertices(int v) {
        validateVertex(v);
        return adjList[v];
    }

    public Bag<Integer>[] getAdjList() {
        return this.adjList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(numVertices + " Vertices and " + numEdges + " Edges " + "\n");
        for(int v = 0; v < numVertices; v++) {
            sb.append(v + " : ");
            for(int adj : adjList[v]) {
                sb.append(adj + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    protected void incrementNumEdges() {
        this.numEdges++;
    }

    public abstract  void addEdge(int fromV, int toV);

    public int getNumVertices() {
        return this.numVertices;
    }

    public int getNumEdges() {
        return this.numEdges;
    }
}
