package leetcode.medium;

import java.util.HashMap;

/*
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 

Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.
 */
public class CustomSortString {
	/*
	 * Intuition
	 * 1. go through String S, and map the char into indexed String array
	 * 2. go through T, get the char index and build the string array
	 * 3. merge String array
	 * 
	 * Time complexity: O(n)
	 */
    public static String customSortString(String S, String T) {
        HashMap<Character, Integer>map=new HashMap<Character, Integer>();
        String res="";
        String []sort=new String[S.length()+1];
        for(int i=0; i<S.length(); i++) {
        	map.put(S.charAt(i), i);
        }
        for(int i=0; i<T.length(); i++) {
        	char c=T.charAt(i);
        	int index=map.getOrDefault(c, S.length());
        	sort[index]+=T.substring(i,i+1);
        }
        for(int i=0; i<S.length()+1; i++) {
        	res+=sort[i];
        }
        return res;
    }
    
    public static void main(String args[]) {
    	
    }
}
