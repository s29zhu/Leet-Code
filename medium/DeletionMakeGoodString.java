package leetcode.medium;
/*
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 * A string s is called good if there are no two different characters in s that have the same frequency.

Given a string s, return the minimum number of characters you need to delete to make s good.

The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

 

Example 1:

Input: s = "aab"
Output: 0
Explanation: s is already good.
Example 2:

Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
Example 3:

Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

/*
 * thoughts
 * 1. iterate through string s, use hashmap to store the characters and frequency mapping
 * 2. Get the MAX value in the map, and put 0-MAX into set, iterate through the map value, 
 * 2.1 if it exits and it is not 0, delete the value from set
 * 2.2 if it doesn't exit in set, value--, res++, go to 2.1
 * 2.3 if the value is 0, keep it in the set
 * 
 * time complexity
 * 
 */
public class DeletionMakeGoodString {

    static public int minDeletions(String s) {
        int res=0;
        int l=s.length();
        HashMap<Character, Integer> map=new HashMap<Character, Integer>();
        int max=0;
        HashSet<Integer> set=new HashSet<Integer>();
        
        for(int i=0; i<l; i++) {
        	char c=s.charAt(i);
        	int value=map.getOrDefault(c, 0);
        	map.put(c, value+1);
        	if(max<value+1) max=value+1;
        }
        for(int i=0; i<=max; i++) set.add(i);
        
        for(char key: map.keySet()) {
        	int value=map.get(key);
        	while(value>0) {
	        	if(set.contains(value)) {
	        		set.remove(value);
	        		break;
	        	}else {
	        		value--;
	        		res++;
	        	}
        	}
        }
        return res;
    }
    public static void main(String args[]) {
    	String s="bbcebab";
    	System.out.print(minDeletions(s));
    }
}
