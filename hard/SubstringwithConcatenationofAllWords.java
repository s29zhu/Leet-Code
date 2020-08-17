package leetcode.hard;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * You are given a string, s, and a list of words, words, that are all of the same length. 
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words
 * exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
 */
public class SubstringwithConcatenationofAllWords {
	/*
	 * intuition
	 * 1. get every substring from string with length words.length*words[0]
	 * 2. tell if the substring is concatenation of words
	 */
	public static boolean helper(String s, HashMap<String, Integer> map, int wl) {	
		if(s.isEmpty() && map.keySet().isEmpty()) return true;
		String str=s.substring(0,wl);
		boolean res=false;
		if(map.keySet().contains(str)) 
		{
			s=s.substring(wl);
			int c=map.get(str);
			if(c==1) map.remove(str);
			else map.put(str, c-1);
			res=helper(s,map, wl);
		}
		return res;
	}
	
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res=new ArrayList<Integer>();
        if(words.length == 0 && !s.isEmpty()) return res;
        int sl=words.length * words[0].length();
		if(sl==0) return res;
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		for(int i=0; i<words.length; i++) map.put(words[i], map.getOrDefault(words[i], 0)+1);
        for(int i=0; i+sl<=s.length(); i++) {
        	String str=s.substring(i, i+sl);
        	HashMap<String, Integer> t_map=new HashMap<String, Integer>();
        	for(String key: map.keySet())  t_map.put(key, map.get(key));
        	if(helper(str, t_map, words[0].length()))  res.add(i);
        }
        return res;
    }
    
    public static void main(String args[]) {
    	String []words={"good","good","best","word"};
    	String s="wordgoodgoodgoodbestword";
    	findSubstring(s, words);
    }
}
