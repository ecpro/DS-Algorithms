package com.graphs;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by eccspro on 21/01/18.
 */
public class UndirectedGraph extends Graph{

    @SuppressWarnings("unchecked")
	public UndirectedGraph(int numVertices) {
        super(numVertices);
    }

    public void addEdge(int w, int v) {
        super.validateVertex(w);
        super.validateVertex(v);
        super.getAdjList()[w].add(v);
        super.getAdjList()[v].add(w);
        super.incrementNumEdges();
    }

    public int degree(int v) {
        super.validateVertex(v);
        return super.getAdjList()[v].size();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String args []) {
        UndirectedGraph undirectedGraph = new UndirectedGraph(10);
        undirectedGraph.addEdge(0,1);
        undirectedGraph.addEdge(0,5);
        undirectedGraph.addEdge(1,2);
        undirectedGraph.addEdge(1,3);
        undirectedGraph.addEdge(3,4);
        undirectedGraph.addEdge(5,6);
        undirectedGraph.addEdge(7,8);
        undirectedGraph.addEdge(7,9);
        System.out.println(undirectedGraph.toString());

        BreathFirstSearch bfs = new BreathFirstSearch(undirectedGraph, 1);
        
        System.out.println(bfs);

        System.out.println(0 + " has path to 4 : " + bfs.hasPathTo(4));
        for(int x : bfs.pathTo(4)) {
            System.out.print(x + " ");
        }
        
        DepthFirstSearch dfs = new DepthFirstSearch(undirectedGraph, 0, DepthFirstSearch.DFSType.NON_RECURSIVE);
        
        System.out.println(dfs);

        System.out.println(0 + " has path to 4 : " + dfs.hasPathTo(4));
        for(int x : dfs.pathTo(4)) {
            System.out.print(x + " ");
        }

        ConnectedComponent cc = new ConnectedComponent(undirectedGraph);
        System.out.println(cc.toString());
    }
}
