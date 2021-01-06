package leetcode.easy;

import java.util.Stack;

/*
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.
 */
public class ValidParenthesis {
    public static boolean isValid(String s) {
        boolean res=false;
        Stack<Character> stack= new Stack<Character>();
        int l=s.length();
        for(int i=0;i<l;i++) {
        	Character c=s.charAt(i);
        	if(c==' ') continue;
        	else if(c=='(' || c=='{' || c=='[') stack.push(c);
        	else if(c==')' && !stack.isEmpty() && stack.pop()=='(') continue;
        	else if(c==']' && !stack.isEmpty() && stack.pop()=='[') continue;
        	else if(c=='}' && !stack.isEmpty() && stack.pop()=='{') continue;
        	else return res;
        }
        if(stack.isEmpty()) res=true;
        return res;
    }
    public static void main(String args[]) {
    	String s="}";
    	System.out.print(isValid(s));
    }
}
