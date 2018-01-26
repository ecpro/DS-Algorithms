package com.graphs;

/**
 * Created by eccspro on 22/01/18.
 */
public class ConnectedComponent {
    private final boolean [] visited;
    private final int [] id;
    private int count = 0;

    public ConnectedComponent(Graph g) {
        if(!(g instanceof UndirectedGraph)) throw new UnsupportedOperationException("this method only works on " + UndirectedGraph.class);
        visited = new boolean[g.getNumVertices()];
        id = new int[g.getNumVertices()];
        for(int v = 0; v < visited.length; v++) {
            if(!visited[v]) {
                bfs(g, v);
                this.count++;
            }
        }
    }

    private void bfs(Graph g, int v) {
        visited[v] = true;
        id[v] = this.count;
        for(int adjV : g.adjVertices(v)) {
            if(!visited[adjV]) {
                this.id[adjV] = this.count;
                bfs(g, adjV);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++) {
            sb.append("Connected component " + i + " [ ");
            for(int v = 0; v < id.length; v++) {
                if(id[v] == i) {
                    sb.append(v + " ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
