package org.arijit.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * Below is a recursive implementation of topological sort. Topological sort is
 * useful in case of build system. Say you have a couple of java packages and
 * you want to build them in order so that dependency never breakes during
 * compile time. Topological sorting is the one to find the order of dependency
 * in those cases.
 * 
 * Topological sort only works in directed acyclic graph
 * 
 * @author arijit
 *
 */

public class TopologicalSort {

	public static void main(String args[]) {

		Graph<Character> graph = Graph.create();

		graph.addEdge('E', 'H');
		graph.addEdge('F', 'G');
		graph.addEdge('E', 'F');
		graph.addEdge('B', 'C');
		graph.addEdge('B', 'D');
		graph.addEdge('C', 'E');
		graph.addEdge('D', 'F');
		graph.addEdge('A', 'C');

		graph.printAdjMap();
		topologicalSort(graph);
	}

	private static void topologicalSort(Graph<Character> graph) {
		if (graph.getAdjMap().isEmpty())
			return;
		// visitedSet will keep track of the nodes already visited
		Set<Character> visitedSet = new HashSet<Character>();
		// sortedStk will hold nodes which does not have any children or all children
		// are visited.
		Stack<Character> sortedStk = new Stack<Character>();

		Iterator<Entry<Character, List<Character>>> eIt = graph.getAdjMap().entrySet().iterator();

		while (eIt.hasNext()) {
			Entry<Character, List<Character>> entry = eIt.next();
			char u = entry.getKey();
			if (visitedSet.contains(u))
				continue;
			topologicalSortRec(u, graph, visitedSet, sortedStk);
		}
		// reverse the stack to get topologicalsortedlist
		List<Character> topologicalSortedList = new ArrayList<Character>(sortedStk.size());
		while (!sortedStk.isEmpty()) {
			topologicalSortedList.add(sortedStk.pop());
		}
		System.out.println("Sorted: " + topologicalSortedList);
	}

	/**
	 * This is a recursive call where first all the children of a vertix will be
	 * explored and at the end the vertix will be added to sorted set. In this
	 * manner we can make sure that child verties are processed before processing
	 * parent vertices.
	 * 
	 * @param u
	 * @param graph
	 * @param visitedSet
	 * @param sortedStk
	 */
	private static void topologicalSortRec(char u, Graph<Character> graph, Set<Character> visitedSet,
			Stack<Character> sortedStk) {
		visitedSet.add(u);
		List<Character> adjList = graph.getAdjMap().get(u);
		if (adjList == null || adjList.isEmpty()) {
			sortedStk.add(u);
			return;
		}
		for (char v : adjList) {
			if (visitedSet.contains(v)) {
				continue;
			}
			topologicalSortRec(v, graph, visitedSet, sortedStk);
		}
		// at this point all the child are explored and inserted into stack, so add
		// parent into stack
		sortedStk.push(u);

	}

}
