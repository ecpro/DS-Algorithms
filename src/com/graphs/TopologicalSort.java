package com.graphs;

import edu.princeton.cs.algs4.Stack;

public class TopologicalSort {
	
	public final boolean [] visited;
	public final Stack<Integer> stack;
	
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
				visited[neighbour] = true;
				stack.push(neighbour);
			}
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
}
