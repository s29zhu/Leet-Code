package leetcode.medium;
/*
 * Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

import java.util.Stack;

/*
 Analysis:
 1. extract the number, st-indeof([)
 2. get the string inside the bracket,  indexOf([) and indexOf(]), call decodestring on substring
 3. end points to indexOf(])
 */
public class DecodeString {
    public static String decodeString(String s) {
        int n=0, index=0;
        String res="",sub="";
        if(s.indexOf("[")==-1) return s;
        while((s.charAt(0)<='z' && s.charAt(0)>='a') || (s.charAt(0)>='A' && s.charAt(0)<='Z') ) {
        	res+=s.substring(0,1);
        	s=s.substring(1);
        }
        n=Integer.valueOf(s.substring(0, s.indexOf("[")));
        index=findIndex(s,s.indexOf("["));
        sub=s.substring(s.indexOf("[")+1, index);
        sub=decodeString(sub);
        for(int i=0; i<n;i++) res+=sub;
        s=s.substring(index+1);
        res+=decodeString(s);
        return res;
    }
    public static int findIndex(String s, int i) {
    	Stack<Character> stack=new Stack<Character>();
    	stack.push(s.charAt(i));
    	while(!stack.isEmpty()) {
    		i++;
    		if(s.charAt(i)=='[') stack.push(s.charAt(i));
    		else if(s.charAt(i)==']') stack.pop();
    	}
    	return i;
    }
    public static void main(String args[]) {
    	String s="e2[a2[d]]3[b]";
    	System.out.println(decodeString(s));
    }
}
