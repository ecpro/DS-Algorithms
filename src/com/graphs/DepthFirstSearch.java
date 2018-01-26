package com.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 * Created by eccspro on 21/01/18.
 */
public class DepthFirstSearch {
    private final boolean [] visiited;
    private final int start;
    private final int [] edgeTo;
    private final StringBuilder routeTrace;
    
    public static enum DFSType {
    	NON_RECURSIVE,
    	RECURSIVE
    }

    public DepthFirstSearch(UndirectedGraph g, int startVertex, DFSType type) {
        this.start = startVertex;
        routeTrace = new StringBuilder("Parent to child node \n" );
        visiited = new boolean[g.getNumVertices()];
        // initial none of the edges are visited, hence all set to false
        for(int i = 0; i < visiited.length; i++) visiited[i] = false;
        edgeTo = new int[g.getNumVertices()];
        
        if(type.equals(DFSType.NON_RECURSIVE)) this.dfsNonRecursive(g, start); 
        else if(type.equals(DFSType.RECURSIVE)) this.dfs(g, start);
    }

    private void dfs(UndirectedGraph g, int startVertex) {
        visiited[startVertex] = true;
        for(int v : g.adjVertices(startVertex)) {
            if(!visiited[v]) {
            	routeTrace.append(startVertex + " --> " + v + "\n");
                edgeTo[v] = startVertex;
                dfs(g, v);
            }
        }
    }
    
    private void dfsNonRecursive(UndirectedGraph g, int startVertex) {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(startVertex);
    	while(!stack.isEmpty()) {
    		int parent = stack.pop();
    		visiited[parent] = true;
    		for(int v : g.adjVertices(parent)) {
    			if(!visiited[v]) {
    				this.edgeTo[v] = parent;
    				stack.push(v);
    			}
    		}
    	}
    }

    public boolean hasPathTo(int vertex) {
        return this.visiited[vertex];
    }

    public Iterable<Integer> pathTo(int destinationVertex) {
        if(!hasPathTo(destinationVertex)) return null;
        Stack<Integer> path = new Stack<>();
        while(destinationVertex != this.start) {
            path.push(destinationVertex);
            destinationVertex = edgeTo[destinationVertex];
        }
        path.push(start);
        return path;
    }
    
    @Override
    public String toString() {
    	return routeTrace.toString();
    }
}
