package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */
public class WordBreak {
	
	//BST
	/*
	 * 
	 */
	public static boolean wordBreakI(String s, List<String> wordDict) {
		boolean res=false;
		int length=s.length();
		LinkedList<Integer> que=new LinkedList<Integer>();
		if(length==0) return true;
		
		for(String w: wordDict) {
			int l=w.length();
			if(s.length()<l) continue;
			String str=s.substring(0,l);
			if(str.equals(w)) {
				res=wordBreakI(s.substring(l), wordDict);
				if(res==true) return res;
			}
		}
		return res;
	}
	
	//Dynamic programming
    public static boolean wordBreak(String s, List<String> wordDict) {
    	int n=s.length();
    	boolean []res=new boolean[n+1];
    	res[0]=true;
    	for(int i=1;i<n+1;i++) {
    		res[i]=false;
    		for(String w: wordDict) {
    			int l=w.length();
    			if(i-l>=0) {
    				String sub=s.substring(i-l,i);
    				if(res[i-l] && sub.equals(w)) {res[i]=true; break;}
    			}
    		}
    	}
    	return res[n];
    }
    
    public static void main(String args[]) {
    	String  s = "cars";
    	List<String> wordDict = new ArrayList<String>();
    	wordDict.add("ca");
    	wordDict.add("rs");
    	wordDict.add("version");
    	
    	//System.out.println("leetcode".equals(s));
    	System.out.print(wordBreakI(s, wordDict));
    }
}