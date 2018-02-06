package com.graphs;


import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

/**
 * Created by eccspro on 04/02/18.
 */
public final class SAP {

    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        digraph = G;
    }

    private void validateVertex(int vertex) {
        if(vertex < 0) throw new IllegalArgumentException("Vertex must not be negative");
        if(vertex >= digraph.V()) throw new IllegalArgumentException("Vertex not present in the graph");
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
    	this.validateVertex(v);
    	this.validateVertex(w);
    	BreadthFirstDirectedPaths bfsForV = new BreadthFirstDirectedPaths(this.digraph, v);
    	BreadthFirstDirectedPaths bfsForW = new BreadthFirstDirectedPaths(this.digraph, w);
        int shortestLength = Integer.MAX_VALUE;
        int nearestAncestor = this.ancestor(v, w);
        if(nearestAncestor > -1) shortestLength = bfsForV.distTo(nearestAncestor) + bfsForW.distTo(nearestAncestor);
        return shortestLength == Integer.MAX_VALUE ? -1 : shortestLength;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
    	this.validateVertex(v);
    	this.validateVertex(w);
        BreadthFirstDirectedPaths bfsForV = new BreadthFirstDirectedPaths(this.digraph, v);
        BreadthFirstDirectedPaths bfsForW = new BreadthFirstDirectedPaths(this.digraph, w);
        int shortestLength = Integer.MAX_VALUE;
        int nearestAncestor = Integer.MAX_VALUE;
        for(int i = 0; i < this.digraph.V(); i++) {
            if(bfsForV.hasPathTo(i) && bfsForW.hasPathTo(i)) {
                int currD = bfsForV.distTo(i) + bfsForW.distTo(i);
                if(currD < shortestLength) {
                    shortestLength = currD;
                    nearestAncestor = i;
                }
            }
        }
        return nearestAncestor == Integer.MAX_VALUE ? -1 : nearestAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.digraph, w);
        int shortestLength = Integer.MAX_VALUE;

        int nearestCommonAncestor = this.ancestor(v, w);
        if(nearestCommonAncestor > -1) shortestLength = bfsV.distTo(nearestCommonAncestor) + bfsW.distTo(nearestCommonAncestor);

        return shortestLength == Integer.MAX_VALUE ? -1 : shortestLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.digraph, w);
        int nearestCommonAncestor = Integer.MAX_VALUE;
        int shortestLength = Integer.MAX_VALUE;
        for(int i = 0; i < this.digraph.V(); i++) {
            if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int currDist = bfsV.distTo(i) + bfsW.distTo(i);
                if(currDist < shortestLength) {
                    shortestLength = currDist;
                    nearestCommonAncestor = i;
                }
            }
        }
        return nearestCommonAncestor == Integer.MAX_VALUE ? -1 : nearestCommonAncestor;
    }
}
