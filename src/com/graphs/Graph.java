package com.graphs;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by eccspro on 21/01/18.
 */
public class Graph {

    private final Bag<Integer> [] adjList;
    private final int numVertices;
    private int numEdges;

    public Graph(int numVertices) {
        if(numVertices < 0) throw new IllegalArgumentException("Number of vertices " + numVertices + " cannot be negative");
        this.numVertices = numVertices;
        this.numEdges = 0;
        adjList = (Bag<Integer> []) new Bag[this.numVertices];
        for(int i = 0; i < this.numVertices; i++) adjList[i] = new Bag<>();
    }

    public void addEdge(int w, int v) {
        validateVertex(w);
        validateVertex(v);
        adjList[w].add(v);
        adjList[v].add(w);
        numEdges++;
    }

    private void validateVertex(int v) {
        if(v < 0 || v >= this.numVertices) throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (numVertices - 1));
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public int getNumEdges() {
        return this.numEdges;
    }

    public int degree(int v) {
        validateVertex(v);
        return adjList[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(numVertices + " Vertices and " + numEdges + " Edges " + "\n");
        for(int v = 0; v < numVertices; v++) {
            sb.append(v + " : ");
            for(Integer w : adjList[v]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
