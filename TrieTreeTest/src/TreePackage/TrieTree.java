package TreePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TrieTree {
	
	ArrayList<listObject> wordsToPrint = new ArrayList<listObject>(); 
	static final int ALPHABET = 26;
	static final int WORDLENGTH = 45;
	
	public class Node{
		boolean isWord;
		Node[] children;
		int counter;
		
		public Node() {
			counter = 0;
			children = new Node[ALPHABET];
			isWord = false;
			Arrays.fill(children, null);
		}
	}
	
	//Used to help store occurrences of a word and that word, for sorting and displaying purposes
	public class listObject{
		
		int count;
		String word;
		
		public listObject(int value, String word) {
			this.count = value;
			this.word = word;
		}
		
	}
	
	//Sorting method for the listObject arrayList
	public class sortByCount implements Comparator<TrieTree.listObject>{

		@Override
		public int compare(listObject first, listObject other) {
			return Integer.compare(first.count, other.count);
		}
		
	}
	
	private Node root;
	
	public void insert(String key) {
		key = key.toLowerCase();
		int level;
		int length = key.length();
		int index;
		if(this.root == null) {
			this.root = new Node();
		}
		Node current = this.root;
		for(level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
			if(index < 0 || index > 26) {
				continue;
			}else if(current.children[index] == null) {
				current.children[index] = new Node();
			}
			current.counter++;
			current = current.children[index];
		}
		current.counter++;
		current.isWord = true;
		
	}
	
	public void delete(String word) {
		word = word.toLowerCase();
		delete(this.root, word, 0);
	}
	
	private boolean delete(Node current, String word, int index) {
		if(index == word.length()) {
			if(!current.isWord) {
				return false;
			}
			current.isWord = false;
			return current.counter == 0;
		}
		char ch = word.charAt(index);
		Node node = current.children[ch - 'a'];
		if(node == null) {
			return false;
		}
		boolean shouldDeleteCurrentNode = delete(node, word, index+1);
		if(shouldDeleteCurrentNode) {
			current.children[ch - 'a'] = null;
			return current.counter == 0;
		}
		return false;
	}
	
	public boolean search(String key) {
		key = key.toLowerCase();
		int level;
		int length = key.length();
		int index;
		Node current = this.root;
		for(level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
			if(index <= 26) {
				if(current.children[index] == null) {
				
				return false;
				}
			}else {
				return false;
			}
			current = current.children[index];
		}
		if(current != null && current.isWord) {
			
			return true;
		}
		return false;
	}
	
	//Creates the dictionary once the text file(TrieDictionaryTrainer contains the parser method) has been parsed into an array
	public void loadTestData(String[] dataArray) {
		for(int i = 0; i < dataArray.length; i++) {
			insert(dataArray[i]);
		}
	}
	
	//Initial execution of word finder once a key has been input(received from TrieMain keyPressed method)
	public void textPredictor(String key) {

		Node curNode = this.root;
		
		for(int i = 0; i < 3; i++) {
			if(curNode.children[key.charAt(i) - 'a'] == null) {
				return;
			}
			curNode = curNode.children[key.charAt(i)-'a'];
		}
		
		trieTraversal(curNode, key);
		printArrayList(key);
	}
	
	//Finds occurrences and stores the associated word in a ListObject(occurrence count, string word)
	public void trieTraversal(Node curNode, String key) {
		
		String originalKey = key;
		if(search(originalKey)) {
			listObject listItem = new listObject(curNode.counter, key);
			wordsToPrint.add(listItem);
			System.out.println("I added a word " + listItem.word);
		}
		
		for(int j = 0; j < 26; j++) {
			if(curNode == null || curNode.children[j] == null) {
				
			}else if(curNode.children[j].counter > 0) {
				
				key = originalKey + Character.toString((char)(j + 'a'));
				trieTraversal(curNode.children[j], key);
			}
		}
	}
	
	//Prints all words in descending order of most occurrences
	public void printArrayList(String key) {
		
		//Sorts based on occurrences
		Collections.sort(wordsToPrint, new sortByCount());
		
		for(int i = 1; wordsToPrint.size() - i >= 0; i++) {
			System.out.println(wordsToPrint.get(wordsToPrint.size() - i).word + " is a word in your document starting with key: " + key);
			System.out.println("	Occurrences: " + wordsToPrint.get(wordsToPrint.size() - i).count);
		}
		wordsToPrint.clear();
	}
}
