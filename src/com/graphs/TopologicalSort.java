package com.graphs;

import edu.princeton.cs.algs4.Stack;

public class TopologicalSort {
	
	private final boolean [] visited;
	private final Stack<Integer> stack;
	
	public TopologicalSort(DirectedGraph diGraph) {
		visited = new boolean[diGraph.getNumVertices()];
		stack = new Stack<Integer>();
		
		for(int i = 0; i < visited.length; i++) {
			if(!visited[i]) {
				this.dfs(diGraph, i);
			}
		}
	}
	
	private void dfs(Graph g, int vertex) {
		if(!visited[vertex]) {
			for(int neighbour : g.adjVertices(vertex)) {
				dfs(g, neighbour);
			}
            visited[vertex] = true;
            stack.push(vertex);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		sb.append("\n");
		return sb.toString();
	}

	public static void main(String args[]) {
	 DirectedGraph dig = new DirectedGraph(7);
	 dig.addEdge(0,1);
	 dig.addEdge(0,2);
	 dig.addEdge(0,5);
	 dig.addEdge(1,4);
	 dig.addEdge(3,2);
	 dig.addEdge(3,5);
	 dig.addEdge(3,4);
	 dig.addEdge(3,6);
	 dig.addEdge(6,0);
	 dig.addEdge(6,4);

	 TopologicalSort ts = new TopologicalSort(dig);
	 System.out.println(ts);
    }
}
