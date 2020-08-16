package leetcode.hard;

import java.util.HashMap;
import java.util.Stack;

/*
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses {
	/*
	 * intuition:
	 * 1. use 2 stack, one to save the indexes of (, one to save '('
	 * 2. pre-index initiated with -1, whenever encounter ) and the stack of character is empty, 
	 * set pre-index=-1
	 * ()((())()
	 * ()()()
	 */
    public  static int longestValidParentheses(String s) {
    	Stack<Integer> is=new Stack<Integer>();
    	Stack<Character> cs=new Stack<Character>();
    	HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
    	int res=0;
    	for(int i=0; i<s.length(); i++) {
    		char c=s.charAt(i);
    		if(c=='(') {
    			is.push(i);
    			cs.push(c);
    		}else if (c==')' && !cs.isEmpty()) {
    			cs.pop();
    			int index=is.pop();
    			res=Math.max(res, i-index+1);
    			index=Math.min(index,map.getOrDefault(i-1, index));
    			res=Math.max(res, i-index+1);
    			index=Math.min(index, map.getOrDefault(index-1, index));
    			res=Math.max(res, i-index+1);
    			map.put(i, index);
    		}
    	}    		
        return res;
    }
    
    public static void main(String args[]) {
    	System.out.println(longestValidParentheses("((()))"));
    }
}
