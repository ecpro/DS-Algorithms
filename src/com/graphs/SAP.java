package com.graphs;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * Created by eccspro on 04/02/18.
 */
public class SAP {

    private final Digraph digraph;
    private final Map<Integer, Map<Integer, Integer>> ancestors;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        digraph = G;
        ancestors = new HashMap<Integer, Map<Integer,Integer>>();
        findAllAncesstors();
    }
    
    private void findAllAncesstors() {
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
    
    public void printAncesstors() {
    	System.out.println(this.ancestors);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        //In in = new In("/Users/eccspro/Documents/version-control/DS-Algorithms/Resources/wordnet/digraph1.txt");
    	In in = new In("C:\\Users\\RaviPiyu\\Desktop\\DS-Algorithms\\Resources\\wordnet\\digraph2.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        sap.printAncesstors();

/*        List<Integer> Vs  = Arrays.asList(12,7,4,0);
        List<Integer> Ws  = Arrays.asList(2,9,8,3);
        System.out.println("shortest common ancestor " + sap.ancestor(Vs, Ws) + " with length " + sap.length(Vs, Ws));
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
*/    }
}
