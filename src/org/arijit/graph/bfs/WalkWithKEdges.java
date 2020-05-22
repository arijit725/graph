package org.arijit.graph.bfs;

import java.util.LinkedList;

/**
 * This is a BFS approah for counting only .
 * @author arijit
 *
 */
public class WalkWithKEdges {
	
	public static void main(String args[]) {
	
		int graph[][] =new int[][]{{0, 1, 1, 1}, 
            					   {0, 0, 0, 1}, 
            					   {1, 1, 0, 1}, 
            					   {0, 0, 0, 0} 
           							}; 
         System.out.println(findPathCount(graph, 0, 3, 2));  
	}

	private static class Node{
		int src;
		int dest;
		int walk;
		public Node(int src, int dest, int walk){
			this.src = src;
			this.dest = dest;
			this.walk = walk;
		}
}
public static int findPathCount(int[][] adjMat, int src, int dest, int walk){
	boolean visited[] = new boolean[adjMat.length]; // this will be reset once we reach dest as we may encounter the same vertex from different path.
												    // here visited[] will ensure that if there is a cycle in path, we do not fall in trap.
	LinkedList<Node> q = new LinkedList<Node>();
	for(int i=0;i<adjMat[0].length;i++){
		if(adjMat[src][i]==1){
			q.add(new Node(src, i, 1));
			visited[src]=true;
		}				
	}
	int count=0;
	while(!q.isEmpty()){
		Node tmp = q.poll();
		if(tmp.dest==dest && tmp.walk==walk){
			count++;
			visited = new boolean[adjMat.length];
			continue;
		}
		int newSrc = tmp.dest;
		for(int i=0;i<adjMat[0].length;i++){
			if(adjMat[newSrc][i]==1 && !visited[i])
					q.add(new Node(newSrc, i, tmp.walk+1));
		}
	}
	return count;
}
}
