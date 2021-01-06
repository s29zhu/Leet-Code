package leetcode.hard;
/*
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.

 

Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.

Input: str1 = "aadcca", str2 = "ccdeed"
Output: false

Note:

1 <= str1.length == str2.length <= 10^4
Both str1 and str2 contain only lowercase English letters.
 */

import java.util.HashMap;
import java.util.HashSet;

/*
 * 1. iterate char through str1, mapping the new char to the char in the same position in str2
 * 2. when encounter a char, that has been encountered, before, map it and compare it to the char at str2, if doesn't match return false
 * 
 * Edge case:
 *     	String str1="abcdefghijklmnopqrstuvwxyz",
    		   str2="bcdefghijklmnopqrstuvwxyza";
 */
public class StringTransformsIntoAnotherString {
    public static boolean canConvert(String str1, String str2) {
    	HashMap<Character, Character> map=new HashMap<Character, Character>();
    	HashSet<Character> set=new HashSet<Character>();
    	if(str1.equals(str2)) return true;
    	int l=str1.length();
    	for(int i=0;i<l;i++) {
    		char k=str1.charAt(i);
    		char v1=map.getOrDefault(k, ' '); 
    		char v2=str2.charAt(i);
    		if(v1==' ') {
    			map.put(k,v2);
    			set.add(v2);
    		}else if(v1==v2) {
    			continue;
    		}else {
    			return false;
    		}    		
    	}
        if(set.size()==26 && map.size()==26) {
        	return false;
        }
    	return true;
    }
    
    public static void main(String args[]) {
    	String str1="abcdefghijklmnopqrstuvwxyz",
    		   str2="bcdefghijklmnopqrstuvwxyza";
    	System.out.println(canConvert(str1, str2));
    }
}
