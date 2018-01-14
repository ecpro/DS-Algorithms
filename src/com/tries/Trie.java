package com.tries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie {
	
	private TrieNode rootNode;
	
	public Trie() {
		rootNode = new TrieNode();
	}
	
	public void constructTrie(List<String> patterns) {
		int position = 0;
		// 1. get each pattern one by one
		for(String pattern : patterns) {
			// 2. insert each pattern token into trie Node
			TrieNode curr = this.rootNode;
			for(int i = 0; i < pattern.length(); i++) {
				char symbol = pattern.charAt(i);
				if(!curr.hasSymbol(symbol)) {
					curr.addSymbol(symbol);
					//System.out.println(curr.nodePosition + ":" + symbol);
				}
				if(i == pattern.length() - 1) {
					curr.setHasWords(true);
					curr.addWords(pattern);
					//System.out.println(curr.getWords());
				}
				curr = curr.nextNode(symbol);
			}
		}
	}
	
	
	public  Map<String, Set<Integer>> trieMatching(String text) {
		StringBuilder sb = new  StringBuilder(text);
		Map<String, Set<Integer>> matches = new HashMap<String, Set<Integer>>();
		int start = 0;
		while(sb.length() != 0) {
			this.prefixTrieMatching(sb.toString(), matches, start);
			sb.deleteCharAt(0);
			start++;
		}

		return matches;
	}
	
	private void prefixTrieMatching(String text, Map<String, Set<Integer>> matches, int start) {
		StringBuilder sb = new StringBuilder();
		TrieNode curr = this.rootNode;
		int index = 0;
		char symbol = text.charAt(index);
		sb.append(symbol);
		while(curr.hasSymbol(symbol)) {
			if(curr.hasWords() && curr.getWords().contains(sb.toString())) {
				//matches.add(sb.toString());
				if(!matches.containsKey(sb.toString())) {
					Set<Integer> loc = new HashSet<Integer>();
					loc.add(start);
					matches.put(sb.toString(), loc);
				}
				else {
					matches.get(sb.toString()).add(start);
				}
			}
			curr = curr.nextNode(symbol);
			index++;
			if(index < text.length()) {
				symbol = text.charAt(index);
				sb.append(symbol);
			}
		}
	}
	
	
	
	class TrieNode {
		
		private HashMap<Character, TrieNode> node;
		private Set<String> words;
		private boolean hasWords;
		
		public TrieNode() {
			this.node = new HashMap<>();
			this.words = new HashSet<>();
		}
		
		public boolean hasSymbol(char c) {
			return node.containsKey(c);
		}
		
		public void addSymbol(char c) {
			node.put(c, new TrieNode());
		}
		
		public TrieNode nextNode(char c) {
			if(node.containsKey(c)) return node.get(c);
			return null;
		}
		
		public Set<String> getWords() {
			return this.words;
		}
		
		public void addWords(String pattern) {
			this.words.add(pattern);
		}
		
		public boolean hasWords() {
			return this.hasWords;
		}
		
		public void setHasWords(boolean isWord) {
			this.hasWords = isWord;
		}
		
		
		@Override
		public String toString() {
			return this.words.toString();
		}
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		//trie.constructTrie(Arrays.asList("panama", "bana", "ananas", "nana", "nanak"));
		//Map<String, Set<Integer>> trieMatching = trie.trieMatching("panamabanans");
		
		//trie.constructTrie(Arrays.asList("ATCG", "GGGT"));
		//Map<String, Set<Integer>> trieMatching = trie.trieMatching("AATCGGGTTCAATCGGGGT");
		
		//trie.constructTrie(Arrays.asList("ATCG", "GGGT"));
		//Map<String, Set<Integer>> trieMatching = trie.trieMatching("AATCGGGTTCAATCGGGGT");
		
		//trie.constructTrie(Arrays.asList("AT", "A", "AG"));
		//Map<String, Set<Integer>> trieMatching = trie.trieMatching("ACATA");
		
		//trie.constructTrie(Arrays.asList("ATCG", "GGGT"));
		//Map<String, Set<Integer>> trieMatching = trie.trieMatching("AATCGGGTTCAATCGGGGT");
		
		trie.constructTrie(Arrays.asList("ATCG", "GGGT"));
		Map<String, Set<Integer>> trieMatching = trie.trieMatching("AATCGGGTTCAATCGGGGT");
		
		System.out.println(trieMatching);
	}
}


