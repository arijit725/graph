package org.arijit.graph.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * transfrom from K to X. Operation allowed: P+D1, P+D2, P-D1, P-D2
 * 
 * @author arijit
 *
 */
public class MinJumpCountOperator {

	public static void main(String args[]) {
		int k = 10, d1 = 4, d2 = 6, x = 8;
		int l = findJumps(k, d1, d2, x);
		System.out.println(l);
	}

	public static class Node {
		int val;
		int from;
		int jumpCount;

		public Node(int val, int from, int jumpCount) {
			this.val = val;
			this.from = from;
			this.jumpCount = jumpCount;
		}
	}

	public static int findJumps(int k, int d1, int d2, int x) {
		LinkedList<Node> q = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<Integer>();
		HashMap<Integer, Integer> path = new HashMap<>();
		q.add(new Node(k, -1, 0));
		visited.add(k);
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			if (tmp.val == x) {
				printPath(path, k, x);
				return tmp.jumpCount;
			}

			int tempVal = tmp.val + d1;
			if (visited.contains(tempVal))
				continue;
			q.add(new Node(tempVal, tmp.val, tmp.jumpCount + 1));
			path.put(tempVal, tmp.val);
			visited.add(tempVal);

			tempVal = tmp.val - d1;
			if (visited.contains(tempVal))
				continue;
			q.add(new Node(tempVal, tmp.val, tmp.jumpCount + 1));
			path.put(tempVal, tmp.val);
			visited.add(tempVal);

			tempVal = tmp.val + d2;
			if (visited.contains(tempVal))
				continue;
			q.add(new Node(tempVal, tmp.val, tmp.jumpCount + 1));
			path.put(tempVal, tmp.val);
			visited.add(tempVal);

			tempVal = tmp.val - d2;
			if (visited.contains(tempVal))
				continue;
			q.add(new Node(tempVal, tmp.val, tmp.jumpCount + 1));
			path.put(tempVal, tmp.val);
			visited.add(tempVal);
		}

		return -1;
	}

	public static void printPath(Map<Integer, Integer> path, int k, int x) {
		System.out.println(path);
		int l = x;
		while (l != k) {
			System.out.println(l);
			l = path.get(l);
		}

	}

}
