package org.arijit.graph.misleneous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class AlienDictCharOrder {
	private Map<Character, List<Character>> adjMap;

	public void findCharOrder(String[] words) {
		if (words == null || words.length == 0)
			return;
		createGraph(words);
		for (int i = 1; i < words.length; i++) {
			char w1[] = words[i - 1].toCharArray();
			char w2[] = words[i].toCharArray();
			int j = 0, k = 0;
			while (j < w1.length && k < w2.length) {
				if (w1[j] != w2[k])
					break; // first mismatch point
				j++;
				k++;
			}
			// now add from j to k edge
			addEdge(w1[j], w2[k]);

		}
		Iterator<Entry<Character, List<Character>>> it = adjMap.entrySet().iterator();
		Set<Character> visitedSet = new HashSet<Character>();
		Stack<Character> stk = new Stack<Character>();
		while (it.hasNext()) {
			Entry<Character, List<Character>> entry = it.next();
			topologicalSort(entry.getKey(), visitedSet, stk);
		}
		while (!stk.isEmpty()) {
			System.out.println("Order of character: " + stk.pop());
		}
	}

	public void topologicalSort(char ch, Set<Character> visitedSet, Stack<Character> stk) {
		if (visitedSet.contains(ch))
			// already traversed;
			return;
		visitedSet.add(ch);
		List<Character> adjList = adjMap.get(ch);
		if (adjList == null || adjList.isEmpty()) {
			// there is no more children present for this node.
			// add this node to stack.
			stk.push(ch);
			return;
		}
		Iterator<Character> it = adjList.iterator();
		while (it.hasNext()) {
			char ch1 = it.next();
			topologicalSort(ch1, visitedSet, stk);
		}
		// all children are traversed. so add the character in stack.
		stk.push(ch);
	}	

	public void createGraph(String words[]) {
		adjMap = new HashMap<Character, List<Character>>();

	}

	public void addEdge(char x, char y) {
		if (!adjMap.containsKey(x))
			adjMap.put(x, new ArrayList<Character>());
		adjMap.get(x).add(y);
	}
	
	public static void main(String args[]) {
		String words[] = {"baa", "abcd", "abca", "cab", "cad"};
		AlienDictCharOrder alienDictCharOrder = new AlienDictCharOrder();
		alienDictCharOrder.findCharOrder(words);
	}
}
