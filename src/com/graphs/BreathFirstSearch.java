package com.graphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreathFirstSearch {
	private final boolean [] visited;
	private final int [] edgeTo;
	private final int start;
	private final StringBuilder traceRoute;
	
	public BreathFirstSearch(UndirectedGraph g, int startIndex) {
		this.start = startIndex;
		this.traceRoute = new StringBuilder("Parent node to child node\n");
		this.visited = new boolean[g.getNumVertices()];
		this.edgeTo = new int[g.getNumVertices()];
		for(int i = 0; i < this.visited.length; i++) {
			this.visited[i] = false;
		}
		this.bfs(g, start);
	}
	
	private void bfs(UndirectedGraph g, int startIndex) {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(startIndex);
		while(!queue.isEmpty()) {
			Integer parentVertex = queue.dequeue();
			for(int vertex : g.adjVertices(parentVertex)) {
				if(!visited[vertex]) {
					visited[vertex] = true;
					this.traceRoute.append(vertex + " --> " + parentVertex + "\n");
					queue.enqueue(vertex);
					edgeTo[vertex] = parentVertex;
				}
			}
		}
	}
	
	public boolean hasPathTo(int vertex) {
		return visited[vertex];
	}
	
	public Iterable<Integer> pathTo(int destination) {
		if(!this.hasPathTo(destination)) return null;
		Stack<Integer> stack = new Stack<Integer>();
		while(destination != this.start) {
			stack.push(destination);
			destination = this.edgeTo[destination];
		}
		stack.push(this.start);
		return stack;
	}
	
	@Override
	public String toString() {
		return this.traceRoute.toString();
	}
}
