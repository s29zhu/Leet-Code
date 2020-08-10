package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

public class WordLadderII {
	public static List<String> getNextWords(String begin, List<String> list){
		List<String> res=new ArrayList<String>();
		int l=begin.length(), dif=1;
		for(String str: list) {
			dif=1;
			for(int i=0; i<l; i++) {
				if(str.charAt(i)!=begin.charAt(i)) {
					dif--;
					if(dif<0) break;
				}
			}
			if(dif==0) res.add(str);
		}
		return res;
	}
	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res=new ArrayList<List<String>>();
        if(wordList.isEmpty() || !wordList.contains(endWord)) return res;
        int target_level=Integer.MAX_VALUE;
        //BFS search
        Queue<String> q1=new LinkedList<String>();
        // to save the string levels, if the current level is less than the one in the hashmap, construct paths and add to queue,
        //if the current path equals to seen.get(str), only contructs paths, don't add to the queue
        HashMap<String, Integer> seen=new HashMap<String, Integer>();
    	//map<wordX, List<List<String>>>, from beginWord to wordX paths
        HashMap<String, List<List<String>>> map= new HashMap<String, List<List<String>>>();
        
        q1.add(beginWord);
        seen.put(beginWord, 0);
        List<String> list=new ArrayList<String>();
        list.add(beginWord);
        res.add(list);
        map.put(beginWord, res);
        while(!q1.isEmpty()) {
        	//construct the path for next round of words, only add the word into queue once.
        	String str=q1.poll();
        	if(seen.get(str)>=target_level) break;
        	list=getNextWords(str,wordList);
        	for(String s: list) {
        		int s_level=seen.getOrDefault(s,Integer.MAX_VALUE);
        		//construct paths when s was never seen before or s is on the same level as previous one
        		if(s_level>=seen.get(str)+1) {
        			//don't add it to the queue if s is already in seen
        			if(s_level!=seen.get(str)+1) q1.add(s);        		
        			seen.put(s, seen.get(str)+1);
        			if(s.equals(endWord) && seen.get(s)<target_level) target_level=seen.get(s);
            		res=map.getOrDefault(s, new ArrayList<List<String>>());
            		List<List<String>> current=new ArrayList<List<String>>();
            		//shallow copy, we need deep copy
            		for(List<String> _l: map.get(str)) {
            			List<String> l=new ArrayList<String>();
            			l.addAll(_l);
            			l.add(s);
            			current.add(l);
            		}
            		res.addAll(current);
            		map.put(s,res);
        		}
        	}
        }
        return map.getOrDefault(endWord, new ArrayList<List<String>>());
	}
	/*
	 * Understanding of the requirements:
	 * 1. only change one letter at a time
	 * 2. all shortest transformation
	 * 
	 * Intuition: BFS and recursion, time exceed limit
	 */
    public static List<List<String>> findLaddersII(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res=new ArrayList<List<String>>();
        if(wordList.isEmpty()) return res;
        List<String> list=getNextWords(beginWord, wordList);
        if(list.isEmpty()) return res;
        else if(list.contains(endWord)) {
        	List<String> l=new ArrayList<String>();
        	l.add(beginWord);
        	l.add(endWord);
        	res.add(l);
        	return res;
        }
    	List<String> words=new ArrayList<String>();
    	words.addAll(wordList);
        words.remove(beginWord);
        words.removeAll(list);
        for(String next: list) {
        	List<List<String>> temp=findLadders(next, endWord, words);
        	for(List<String> t: temp) {
            	List<String> l=new ArrayList<String>();
            	l.add(beginWord);
            	l.addAll(t);
            	res.add(l);
        	}
        }
		return res;   	
    }
    
    public static void main(String args[]) {
    	//String []word= {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
    	//String []word={"hot","dot","dog","lot","log","cog"};
    	//String []word={"hot","dog"};
    	String []word= {"ted","tex","red","tax","tad","den","rex","pee"};
    	List<String> words=Arrays.asList(word);
    	//"hot","dot","dog","lot","log","cog"
    	List<List<String>> res=findLadders("red", "tax", words);
    	System.out.print("done");
    }
}