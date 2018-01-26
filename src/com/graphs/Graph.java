package com.graphs;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by eccspro on 21/01/18.
 */
public class Graph {

    private final Bag<Integer> [] adjList;
    private final int numVertices;
    private int numEdges;

    @SuppressWarnings("unchecked")
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

    public Iterable<Integer> adjVertices(int v) {
        validateVertex(v);
        return adjList[v];
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

    public static void main(String args []) {
        Graph graph = new Graph(10);
        graph.addEdge(0,1);
        graph.addEdge(0,5);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(3,4);
        graph.addEdge(5,6);
        graph.addEdge(7,8);
        graph.addEdge(7,9);
        System.out.println(graph.toString());

        BreathFirstSearch bfs = new BreathFirstSearch(graph, 1);
        
        System.out.println(bfs);

        System.out.println(0 + " has path to 4 : " + bfs.hasPathTo(4));
        for(int x : bfs.pathTo(4)) {
            System.out.print(x + " ");
        }
        
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0, DepthFirstSearch.DFSType.NON_RECURSIVE);
        
        System.out.println(dfs);

        System.out.println(0 + " has path to 4 : " + dfs.hasPathTo(4));
        for(int x : dfs.pathTo(4)) {
            System.out.print(x + " ");
        }

        ConnectedComponent cc = new ConnectedComponent(graph);
        System.out.println(cc.toString());
    }
}
