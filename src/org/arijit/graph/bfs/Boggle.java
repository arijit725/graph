package org.arijit.graph.bfs;

import java.util.HashMap;

public class Boggle {

	public static void main(String args[]) {

		String dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
	    char  boggle[][]   = {{'G','I','Z'},
	                       {'U','E','K'},
	                       {'Q','S','E'}};
	    
	    checkWordDriver(boggle, dictionary);
	}

	public static void checkWordDriver(char[][] mat, String[] dict) {
		TrieNode root = createTrie(dict);
		if (root == null)
			return;
		boolean visited[][] = new boolean[mat.length][mat[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				checkWord(mat, i, j, root, visited);
			}
		}
	}
	
	private static int[] xAxis = { 0, 1, 1, 1, 0, -1, -1, -1 };
	private static int[] yAxis = { 1, 1, 0, -1, -1, -1, 0, 1 };

	public static void checkWord(char[][] mat, int x, int y, TrieNode ref, boolean visited[][]) {
		if(x<0 || y<0 || x>=mat.length || y>=mat[0].length) // make sure you always check whether (x,y) exceeds matrix boundary
			return;
		TrieNode node = getNodeFromTrie(ref, mat[x][y]);
		if (node == null)
			return; // there is no path from here.			
		if(visited[x][y])
			return;  //already visited on this path.
		visited[x][y] = true;
		if (node.isEnd) {
			System.out.println(node.word);
			// not returning from this point to make sure we are not losing any consecutive			
			// word
			
		}
		for (int i = 0; i < xAxis.length; i++) {
			int newX = x + xAxis[i];
			int newY = y + yAxis[i];				
			checkWord(mat, newX, newY, node,visited);
		}
		visited[x][y] = false; // making false so that from other path is position can be picked
	}
	
		private static class TrieNode {
			HashMap<Character, TrieNode> children;
			char value;
			String word;
			boolean isEnd;

			public TrieNode(){
					//default constructor
				}

			public TrieNode(char value){
					this.value = value;
				}

			@Override
			public String toString() {
				return "TrieNode [value=" + value + ", word=" + word + "]";
			}
			
			
		}

		public static void insertTrie(TrieNode root, String str){
			char[] arr = str.toCharArray();
			TrieNode tmpRoot=root;
			int i =0;
			while(i<arr.length){
				if(tmpRoot.children==null) {
					tmpRoot.children = new HashMap<>(); //creating only once if map is not there.
				}
				if(!tmpRoot.children.containsKey(arr[i])){
							TrieNode tmp = new TrieNode(arr[i]);
							tmpRoot.children.put(arr[i],tmp);
							tmpRoot = tmp;
						}
				else
					tmpRoot = tmpRoot.children.get(arr[i]);
					i++;
			}
			tmpRoot.word = str;
			tmpRoot.isEnd = true;
		}

		public static TrieNode createTrie(String[] dict) {
			TrieNode root = new TrieNode();
			if (dict.length == 0)
				return null;
			int i = 0;
			while (i < dict.length) {
				insertTrie(root, dict[i]);
				i++;
			}
			return root;
		}

		public static TrieNode getNodeFromTrie(TrieNode root, Character ch) {
			if(root.children==null)
				return null;
			return root.children.get(ch);
		}

		

	
	
}
