package org.arijit.graph;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graph<T> {
	
	private Map<T,List<T>> adjMap;
	private Graph() {
		adjMap = new LinkedHashMap<T, List<T>>();
	}
	
	/**
	 * creating Graph where vertices type T
	 * @param <T>
	 * @return
	 */
	public static <T> Graph<T> create(){
		return new Graph<T>();
	}
	
	public void addEdge(T u, T v) {
		if(!adjMap.containsKey(u)) {
			List<T> adjList = new LinkedList<T>();
			adjMap.put(u, adjList);
		}
		adjMap.get(u).add(v);
	}
	
	public void printAdjMap() {
		Iterator<Entry<T, List<T>>> eIt = adjMap.entrySet().iterator();
		while(eIt.hasNext()) {
			Entry<T, List<T>> tmp = eIt.next();
			T key = tmp.getKey();
			List<T> adjList = tmp.getValue();
			System.out.println(key+"=>"+adjList);
		}
	}
	
	public Map<T, List<T>> getAdjMap() {
		return adjMap;
	}
}
