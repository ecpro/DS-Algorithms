package com.graphs;


import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eccspro on 04/02/18.
 */
public final class SAP {

    private final Digraph digraph;
    private final Map<Integer, Map<Integer, Integer>> ancestors;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        digraph = G;
        ancestors = new HashMap<>();
        findAllAncestors();
    }
    
    private void findAllAncestors() {
    	for(int v = 0; v < digraph.V(); v++) {
    		ancestors.put(v, this.allAncestorDistances(v));
    	}
    }
    
    private Map<Integer, Integer> getAncestors(int vertex) {
    	return this.ancestors.get(vertex);
    }

    private Map<Integer, Integer> allAncestorDistances(int vertex) {
        validateVertex(vertex);
        Map<Integer, Integer> ancestorDistanceMap = new HashMap<>();
        ancestorDistanceMap.put(vertex, 0);
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(vertex);
        while(!queue.isEmpty()) {
            int currV = queue.dequeue();
            int currD = ancestorDistanceMap.get(currV);
            for(int neighbour: digraph.adj(currV)) {
                queue.enqueue(neighbour);
                if(ancestorDistanceMap.containsKey(neighbour)) {
                    ancestorDistanceMap.put(neighbour, Integer.min(ancestorDistanceMap.get(neighbour), currD+1));
                }
                else {
                    ancestorDistanceMap.put(neighbour, currD+1);
                }
            }
        }
       return ancestorDistanceMap;
    } 

    private void validateVertex(int vertex) {
        if(vertex < 0) throw new IllegalArgumentException("Vertex must not be negative");
        if(vertex >= digraph.V()) throw new IllegalArgumentException("Vertex not present in the graph");
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
    	this.validateVertex(v);
    	this.validateVertex(w);
        Map<Integer, Integer> ancestorsV = this.getAncestors(v);
        Map<Integer, Integer> ancestorsW = this.getAncestors(w);
        int shortestLength = Integer.MAX_VALUE;
        for(int ancestorV : ancestorsV.keySet()) {
            if(ancestorsW.containsKey(ancestorV)) {
                shortestLength = Integer.min(shortestLength, ancestorsV.get(ancestorV) + ancestorsW.get(ancestorV));
            }
        }
        return shortestLength == Integer.MAX_VALUE ? -1 : shortestLength;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
    	this.validateVertex(v);
    	this.validateVertex(w);
        Map<Integer, Integer> ancestorsV = this.getAncestors(v);
        Map<Integer, Integer> ancestorsW = this.getAncestors(w);
        int shortestLength = Integer.MAX_VALUE;
        int nearestAncestor = Integer.MAX_VALUE;
        for(int ancestorV : ancestorsV.keySet()) {
            if(ancestorsW.containsKey(ancestorV)) {
                int currentLength = ancestorsV.get(ancestorV) + ancestorsW.get(ancestorV);
                if(currentLength < shortestLength) {
                    shortestLength = currentLength;
                    nearestAncestor = ancestorV;
                }
            }
        }
        return nearestAncestor == Integer.MAX_VALUE ? -1 : nearestAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int shortestLength = Integer.MAX_VALUE;
        for(int from : v) {
            this.validateVertex(from);
            for(int to : w) {
                this.validateVertex(to);
                int currLen = this.length(from, to);
                if(currLen != -1 && currLen < shortestLength) shortestLength = currLen;
            }
        }
        return shortestLength == Integer.MAX_VALUE ? -1 : shortestLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int nearestCommonAncestor = Integer.MAX_VALUE;
        int shortestLength = Integer.MAX_VALUE;
        for(int from : v) {
            this.validateVertex(from);
            for(int to : w) {
                this.validateVertex(to);
                int currLength = this.length(from, to);
                if(currLength != -1 && currLength < shortestLength) {
                    shortestLength = currLength;
                    nearestCommonAncestor = this.ancestor(from ,to);
                }
            }
        }
        return nearestCommonAncestor == Integer.MAX_VALUE ? -1 : nearestCommonAncestor;
    }
}
