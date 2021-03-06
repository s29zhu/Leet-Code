package leetcode.easy;

import java.util.HashMap;

/*
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
 */
public class FindUniqueCharacterinAString {
    //O(n^2)
	public static int firstUniqCharI(String s) {
        int res=-1;
        StringBuilder str=new StringBuilder(s);
        int l=str.length();
        for(int i=0;i<l;i++) {
        	String temp=String.valueOf(str.charAt(i));
        	if(str.lastIndexOf(temp)==i && str.indexOf(temp)==i) return i;
        }
        return res;
    }
    //O(n)
    public static int firstUniqCharII(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) 
                return i;
        }
        return -1;
    }
    public static void main(String args[]) {
    	String s="1231234";
    	System.out.print(firstUniqCharII(s));
    }
}
