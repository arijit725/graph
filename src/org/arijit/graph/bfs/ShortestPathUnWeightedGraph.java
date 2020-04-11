package org.arijit.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathUnWeightedGraph {

	public static void main(String args[]) {
		int src = 0;
		int dest = 7;
		createMatrix(8);
		addEdge(1, 0);
		addEdge(1, 2);
		addEdge(3, 0);
		addEdge(3, 7);
		addEdge(3, 4);
		addEdge(4, 7);
		addEdge(4, 6);
		addEdge(4, 5);
		addEdge(7, 6);
		addEdge(6, 5);
		calculateShortestPath(mat, src, dest);
	}

	private static int mat[][];

	public static void createMatrix(int edgeCount) {
		mat = new int[edgeCount][edgeCount];
	}

	public static void addEdge(int src, int dest) {
		mat[src][dest] = 1;
		mat[dest][src] = 1;
	}

	private static class Node {
		private int src;
		private int dest;
		private int dist;

		public Node(int src, int dest, int dist) {
			this.src = src;
			this.dest = dest;
			this.dist = dist;
		}

		public int getSrc() {
			return src;
		}

		public int getDest() {
			return dest;
		}

		public int getDist() {
			return dist;
		}
	}

	public static void calculateShortestPath(int[][] adjMat, int src, int dest) {
		boolean visited[] = new boolean[adjMat.length];
		Queue<Node> q = new LinkedList<>();
		Node pred[] = new Node[adjMat.length];

		for (int i = 0; i < adjMat.length; i++) {
			if (adjMat[src][i] == 1) {
				// path exist
				Node node = new Node(src, i, 1);
				q.add(node);
				if (pred[i] == null)
					pred[i] = node;
			}
		}
		visited[src] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.getDest() == dest) {
				System.out.println("Shortest Distance: " + node.getDist());
				int k = dest;
				while (k >= 0) {
					node = pred[k];
					System.out.print(" " + node.getSrc());
					k = node.getSrc();
					if (k == src)
						break;
				}
				return;
			}
			int newSrc = node.getDest();
			for (int i = 0; i < adjMat.length; i++) {
				if (adjMat[newSrc][i] == 0)
					continue;
				// as soon as you visit new node, mark it as visited. This way it will help to
				// avoid same node insertion in queue in case same node is reached from two
				// different nodes.
				if (visited[i])
					continue;
				Node node1 = new Node(newSrc, i, node.getDist() + 1);
				q.add(node1);
				visited[i] = true;
				if (pred[i] == null)
					pred[i] = node1;
				else {
					if (pred[i].getDist() > node1.getDist())
						pred[i] = node1;
				}

			}
		}
	}
}
