package org.arijit.graph.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KnightShortestPath {

	public static void main(String args[]) {
		int N = 6;
		int srcX = 1;
		int srcY = 1;
		int destX = 4;
		int destY = 5;

		calculatSteps(srcX, srcY, destX, destY, N);
	}

	private static class Node {
		private int x;
		private int y;
		private int steps;

		public Node(int x, int y, int steps) {
			this.x = x;
			this.y = y;
			this.steps = steps;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getSteps() {
			return steps;
		}
	}

	static int xAxis[] = { -1, 1, 2, 2, 1, -1, -2, -2 };
	static int yAxis[] = { 2, 2, 1, -1, -2, -2, -1, 1 };

	public static void calculatSteps(int srcX, int srcY, int destX, int destY, int N) {
		Queue<Node> q = new LinkedList<Node>();
		boolean visited[][] = new boolean[N + 1][N + 1];
		Node pred[][] = new Node[N + 1][N + 1];

		Node node = new Node(srcX, srcY, 0);
		visited[srcX][srcY] = true;
		q.add(node);
		while (!q.isEmpty()) {
			System.out.println("Q size: "+q.size());	
			node = q.poll();
			if (node.getX() == destX && node.getY() == destY) {
				System.out.println("Minimum Steps: " + node.getSteps());
				return;
			}
			for (int i = 0; i < xAxis.length; i++) {
				int newX = node.getX() + xAxis[i];
				int newY = node.getY() + yAxis[i];
				if(newX<0||newX>N || newY<0||newY>N)
					continue;
				if (visited[newX][newY])
					continue;
				Node tmpNode = new Node(newX, newY, node.getSteps() + 1);
				q.add(tmpNode);
				visited[tmpNode.getX()][tmpNode.getY()] = true;
				if (pred[newX][newY] == null)
					pred[newX][newY] = node;
				else {
					if (pred[newX][newY].getSteps() > tmpNode.getSteps())
						pred[newX][newY] = tmpNode;
				}

			}
//			visited[node.getX()][node.getY()] = true;
			printVisited(visited);
		}
	}
	
	public static void printVisited(boolean[][] visited) {
		for(int i=0;i<visited.length;i++)
		{
			System.out.println(Arrays.toString(visited[i]));
		}
		System.out.println("=============================");
	}
}
