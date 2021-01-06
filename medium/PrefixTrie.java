package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */
public class PrefixTrie {
	class TrieNode{
		public char val;
		public List<TrieNode> children;
		public boolean end;
		public TrieNode(char c) {
			val=c;
			children=new ArrayList<TrieNode>();
			end=false;
		}
	}
	TrieNode root;
    /** Initialize your data structure here. */
    public PrefixTrie() {
    	root=new TrieNode(' ');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode t=root;
        int l=word.length();
        if(l==0) return;
        for(int i=0; i<l; i++) {
        	char c=word.charAt(i);
        	TrieNode n=isChild(t,c);
        	if(n==null) {
        		n=new TrieNode(c);
        		t.children.add(n);
        	}
        	t=n;
        }
        t.end=true;
    }
    
    public TrieNode isChild(TrieNode t, char c) {
    	if(t==null || t.children==null) return null;
    	for(TrieNode item: t.children) {
    		if(item.val==c) {
    			return item;
    		}
    	} 
    	return null;
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int l=word.length();
        if(l==0) return true;
        TrieNode t=root;
        for(int i=0; i<l; i++) {
        	char c=word.charAt(i);
        	t=isChild(t,c);
        	if(t==null) return false;
        }
        return t.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int l=prefix.length();
        if(l==0) return true;
        TrieNode t=root;
        for(int i=0; i<l; i++) {
        	char c=prefix.charAt(i);
        	t=isChild(t,c);
        	if(t==null) return false;
        }
        return true;
    }
    
    public static void main(String args[]) {
    	PrefixTrie trie=new PrefixTrie();
    	trie.insert("appple");
    	trie.search("app");
    	trie.startsWith("app");
    	trie.insert("app");
    }
}
