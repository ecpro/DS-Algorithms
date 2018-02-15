package com.graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by eccspro on 07/02/18.
 */
public class DirectedCycle {
    private final Set<Integer> currDfs;
    private final boolean [] visited;
    private final Graph G;

    public DirectedCycle(DirectedGraph G) {
        this.G = G;
        visited = new boolean[this.G.getNumVertices()];
        currDfs = new LinkedHashSet<>();
    }

    public void findCycles(Integer V) {
        for(int i = 0; i < this.G.getNumVertices(); i++) {
            if(!visited[i]) {
                dfs(this.G, V);
            }
        }
    }

    private void dfs(Graph g, Integer v) {
        visited[v] = true;
        this.currDfs.add(v);
        for(int w : g.adjVertices(v)) {
            if(!visited[w]) {
                this.currDfs.add(w);
                dfs(g, w);
            }
            else {
                if(this.currDfs.contains(w)) {
                    System.out.println("Cycle Detected");
                    Iterator<Integer> iterator = currDfs.iterator();
                    int x = iterator.hasNext()==true? iterator.next() : Integer.MAX_VALUE;
                    while(x != w) {
                        x = iterator.next();
                    }
                    while(iterator.hasNext()) {
                        System.out.print(iterator.next() + " ");
                    }
                    System.out.print(w);
                }
            }
        }
        currDfs.remove(v);
    }

    public static void main(String args[]) {

        DirectedGraph G = new DirectedGraph(8);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(1,3);
        G.addEdge(3,4);
        G.addEdge(4,5);
        G.addEdge(5,7);
        G.addEdge(4,1);
        G.addEdge(6,0);
        G.addEdge(2,6);

        DirectedCycle dc = new DirectedCycle(G);
        dc.findCycles(0);
    }
}
