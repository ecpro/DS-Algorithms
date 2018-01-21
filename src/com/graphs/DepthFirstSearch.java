package com.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 * Created by eccspro on 21/01/18.
 */
public class DepthFirstSearch {
    private final boolean [] visiited;
    private final int start;
    private final int [] edgeTo;

    public DepthFirstSearch(Graph g, int startVertex) {
        this.start = startVertex;
        visiited = new boolean[g.getNumVertices()];
        // initial none of the edges are visited, hence all set to false
        for(int i = 0; i < visiited.length; i++) visiited[i] = false;
        edgeTo = new int[g.getNumVertices()];
    }

    private void dfs(Graph g, int startVertex) {
        visiited[startVertex] = true;
        for(int v : g.adjVertices(startVertex)) {
            if(!visiited[v]) {
                edgeTo[v] = startVertex;
                dfs(g, v);
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return this.visiited[vertex];
    }

    public Iterable<Integer> pathTo(int destinationVertex) {
        if(!hasPathTo(destinationVertex)) return null;
        Stack<Integer> path = new Stack<>();
        while(edgeTo[destinationVertex] != this.start) {
            path.push(destinationVertex);
            destinationVertex = edgeTo[destinationVertex];
        }
        path.push(start);
        return path;
    }
}
