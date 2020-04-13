package org.arijit.graph.sort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {

	public static void main(String args[]) {
		Vertex vA = new Vertex('A');
		Vertex vB = new Vertex('B');
		Vertex vC = new Vertex('C');
		Vertex vD = new Vertex('D');
		Vertex vE = new Vertex('E');
		Vertex vF = new Vertex('F');
		Vertex vG = new Vertex('G');
		Vertex vH = new Vertex('H');

		Graph g = new Graph();
		g.addDirectedEdge(vA, vC);
		g.addDirectedEdge(vB, vC);
		g.addDirectedEdge(vB, vD);
		g.addDirectedEdge(vC, vE);
		g.addDirectedEdge(vD, vF);
		g.addDirectedEdge(vE, vF);
		g.addDirectedEdge(vE, vH);
		g.addDirectedEdge(vF, vG);

		topologicalSort(g);

	}

	public static class Vertex {
		char val;

		public Vertex(char val) {
			this.val = val;
		}

		public char getVal() {
			return val;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + val;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (val != other.val)
				return false;
			return true;
		}
	}

	public static class Graph {
		private Map<Vertex, List<Vertex>> adjMap;

		public Graph() {
			adjMap = new HashMap<>();
		}

		public void addDirectedEdge(Vertex v1, Vertex v2) {
			if (!adjMap.containsKey(v1)) {
				List<Vertex> l = new LinkedList<Vertex>();
				adjMap.put(v1, l);
			}
			adjMap.get(v1).add(v2);
		}

		public Map<Vertex, List<Vertex>> getAdjMap() {
			return adjMap;
		}
	}

	public static void topologicalSort(Graph g) {
		Map<Vertex, List<Vertex>> adjMap = g.getAdjMap();
		if (adjMap.isEmpty())
			return;
		Set<Vertex> visitedSet = new HashSet<>();
		Stack<Vertex> stk = new Stack<>();
		Set<Vertex> keySet = adjMap.keySet();
		for (Vertex v : keySet) {
			if (visitedSet.contains(v))
				continue;
			topologicalSortUtil(adjMap, v, visitedSet, stk);

		}

		if (stk.isEmpty())
			return;
		while (!stk.isEmpty()) {
			System.out.println(stk.pop().getVal());
		}

	}

	private static void topologicalSortUtil(Map<Vertex, List<Vertex>> adjMap, Vertex v, Set<Vertex> visitedSet,
			Stack<Vertex> stk) {
		visitedSet.add(v);
		List<Vertex> adjList = adjMap.get(v);

		if (adjList == null || adjList.isEmpty()) {
			stk.add(v);// vertex v does not have any child. so add in stack
			return;
		}
		for (Vertex v1 : adjList) {
			if (visitedSet.contains(v1))
				continue;
			topologicalSortUtil(adjMap, v1, visitedSet, stk);

		}
		stk.add(v); // all child of v is been traveresed
	}

}
