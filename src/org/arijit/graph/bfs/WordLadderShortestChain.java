package org.arijit.graph.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class WordLadderShortestChain {

	public static void main(String args[]) {
		String[] dict = {"POON","PLEE", "SAME", "POIE", "PLEA", "PLIE", "POIN"};
		String startWord = "TOON";
		String targetWord = "PLEA";
//		String[] dict = {"hot","dot","dog","lot","log","cog"};
//		String startWord = "hit";
//		String targetWord = "cog";
//		String[] dict = {"a","b","c"};
//		String startWord = "a";
//		String targetWord = "c";

//		String[] dict = { "ymann", "yycrj", "oecij", "ymcnj", "yzcrj", "yycij", "xecij", "yecij", "ymanj", "yzcnj",
//				"ymain" };
//		String startWord = "ymain";
//		String targetWord = "oecij";
		HashSet<String> wordSet = new HashSet<String>(Arrays.asList(dict));
//		String startWord = "hit";
//		String targetWord = "cog";
		int count = countChanges(wordSet, startWord, targetWord);
		System.out.println("Chain : " + count);
		/*
		 * TOON BOON POON POIN POIE PLIE PLEE PLEA Chain : 7
		 */

	}

	public static class Node {
		String srcWord;
		int changeCount;

		public Node(String srcWord, int changeCount) {
			this.srcWord = srcWord;
			this.changeCount = changeCount;
		}
	}

	public static int countChanges(HashSet<String> wordSet, String startWord, String targetWord) {
		LinkedList<Node> q = new LinkedList<Node>();
		HashSet<String> visited = new HashSet<>();
		q.add(new Node(startWord, 0));
		visited.add(startWord);
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			if (tmp.srcWord.contentEquals(targetWord))
				return tmp.changeCount + 1; // +1 is for including starting word as well
			String word = tmp.srcWord;			
			for (int j = 0; j < word.length(); j++) {
				char[] str = word.toCharArray();
				for (int i = 'A'; i <= 'Z'; i++) { // this part is always constant - 26
					str[j] = (char) i;
					String tmp1 = new String(str);					
					if (visited.contains(tmp1))
						continue;
					if (wordSet.contains(tmp1)) { // we will add to queue only if it is a valid word present in
													// dictionary
						System.out.println(tmp1);
						q.add(new Node(tmp1, tmp.changeCount + 1));
						visited.add(tmp1);// to avoid getting same word picked
					}
				}
			}
		}
		return 0; //no targetword found
	}
}
