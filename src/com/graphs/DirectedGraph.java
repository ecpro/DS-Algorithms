package com.graphs;

import edu.princeton.cs.algs4.Bag;

public class DirectedGraph extends Graph {

	public DirectedGraph(int numVertices) {
		super(numVertices);
	}

	@Override
	public void addEdge(int fromV, int toV) {
		super.validateVertex(fromV);
		super.validateVertex(toV);
		super.getAdjList()[fromV].add(toV);
		super.incrementNumEdges();
	}

	public int outDegree(int vertex) {
		super.validateVertex(vertex);
		return super.getAdjList()[vertex].size();
	}

	public int inDegree(int vertex) {
		super.validateVertex(vertex);
		int inDegree = 0;
		for(Bag<Integer> edges : super.getAdjList()) {
			for(int edge : edges) {
				if(edge == vertex) inDegree++;
			}
		}
		return inDegree;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static void main(String args []) {
		Graph dig = new DirectedGraph(7);
		dig.addEdge(0,1);
		dig.addEdge(0,5);
		dig.addEdge(0,2);
		dig.addEdge(1,4);
		dig.addEdge(3,2);
		dig.addEdge(3,4);
		dig.addEdge(3,5);
		dig.addEdge(3,6);
		dig.addEdge(5,2);
		dig.addEdge(6,4);
		dig.addEdge(6,0);

		BreathFirstSearch bfs = new BreathFirstSearch(dig, 3);

		System.out.println(dig);
		System.out.println(bfs);

		System.out.println(3 + " has path to 0 : " + bfs.hasPathTo(0));
		if(bfs.hasPathTo(0)) {
			for(int x : bfs.pathTo(0)) {
				System.out.print(x + " ");
			}
		}
		else {
			System.out.println("No path available");
		}

		//System.out.println(dig);
		//ConnectedComponent connectedComponent = new ConnectedComponent(dig);

	}
}
