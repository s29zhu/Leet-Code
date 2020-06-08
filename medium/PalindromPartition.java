package leetcode.medium;
/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 */
import java.util.ArrayList;
import java.util.List;

public class PalindromPartition {
    public static List<List<String>> partition(String s) {
    	List<List<String>> res= new ArrayList<List<String>>();
    	if(s.equals("")) return res;
    	for(int i=0;i<s.length();i++) {
    		String sub=s.substring(0,i+1);
    		if(isP(sub))
    		{
    			List<List<String>> l=new ArrayList<List<String>> ();
    			if(i<s.length()-1) l=partition(s.substring(i+1, s.length()));

    			if(!l.isEmpty()) {
    				for(List<String> item: l) {
    					//make the separated palindromes are still in order
    					List<String> newl=new ArrayList<String>();
    					newl.add(sub);
    					newl.addAll(item);
    					res.add(newl);
    				}
    			}else {
    				List<String> list=new ArrayList<String>();
    				list.add(sub);
    				res.add(list);
    			}
    		}
    	}
    	return res;
    }

    public static boolean isP(String s) {
    	for(int i=0,j=s.length()-1;i<j;i++,j--) {
    		if(s.charAt(i)!=s.charAt(j)) return false;
    	}
    	return true;
    }
    public static void main(String [] args) {
    	String s="aab";
    	partition(s);
    }
}
