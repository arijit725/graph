package org.arijit.graph.misleneous;

import java.util.LinkedList;
/**
 * This problem here is sovled using BFS. Complexity: o(n)
 * But same problem could be solved using dynamic programming. But time complexity would be o(n^2)
 * 
 * @author arijit
 *
 */
public class SnakeLader {

	public static class Node {
		int src;
		int dest;
		int moves;

		public Node(int src, int dest, int moves) {
			this.src = src;
			this.dest = dest;
			this.moves = moves;
		}
	}

	public static int findMoves(int src, int dest, int board[]) {
		LinkedList<Node> q = new LinkedList<>();
		boolean visited[] = new boolean[board.length];
		q.add(new Node(0, 1, 0));// starting from 0;
		visited[1] = true;
		int diceFace = 6;
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			if (tmp.dest == dest)
				return tmp.moves;
			for (int i = 1; i <= diceFace; i++) {
				int newDest = tmp.dest + i;
				if (newDest >= board.length)
					continue; // out of board range
				if (visited[newDest])
					continue; // already visited
				newDest = board[newDest] == 0 ? newDest : board[newDest];
				Node node = new Node(tmp.dest, newDest, tmp.moves + 1);
				q.add(node);
			}
		}
		return -1; // not able to reach destination
	}

	public static void main(String args[]) {
		int N = 30;
		int board[] = new int[N + 1]; // leaving starting 0 index
		// Ladders
		board[3] = 22;
		board[5] = 8;
		board[11] = 26;
		board[20] = 29;

		// Snakes
		board[27] = 1;
		board[21] = 9;
		board[17] = 4;
		board[19] = 7;

		System.out.println("Min Dice throws required is " + findMoves(1, 30, board));
	}
}
