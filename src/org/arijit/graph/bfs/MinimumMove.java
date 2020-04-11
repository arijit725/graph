package org.arijit.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumMove {

	public static void main(String args[]) {
			
		int M[][] = { { 3, 3, 1, 0 }, 
					  { 3, 0, 3, 3 }, 
					  { 2, 3, 0, 3 }, 
					  { 0, 3, 3, 3 } };

		calculateMoves(M);
	}

	private static class Node {
		private int x;
		private int y;
		private int m;

		public Node(int x, int y, int m) {
			this.x = x;
			this.y = y;
			this.m = m;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getM() {
			return m;
		}
	}

	private static int[] xAxis = { 0, 1, 0, -1 };
	private static int[] yAxis = { 1, 0, -1, 0 };

	public static void calculateMoves(int mat[][]) {
		int srcX = -1, srcY = -1;
		int destX = -1, destY = -1;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 1) {
					srcX = i;
					srcY = j;
				} else if (mat[i][j] == 2) {
					destX = i;
					destY = j;
				}
			}
		}

		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[mat.length][mat[0].length];

		Node node = new Node(srcX, srcY, 0);
		q.add(node);
		visited[srcX][srcY] = true;
		while (!q.isEmpty()) {
			node = q.poll();
			if (node.getX() == destX && node.getY() == destY) {
				System.out.println("Minimum Moves: " + node.getM());
				return;
			}
			for (int i = 0; i < xAxis.length; i++) {
				int newX = node.getX() + xAxis[i];
				int newY = node.getY() + yAxis[i];
				if (newX < 0 || newX >= mat.length)
					continue;
				if (newY < 0 || newY >= mat.length)
					continue;
				if (visited[newX][newY])
					continue;
				if (mat[newX][newY] == 0)
					continue;
				Node tmpNode = new Node(newX, newY, node.getM() + 1);
				q.add(tmpNode);
				visited[newX][newY] = true;
			}
		}

	}

}
