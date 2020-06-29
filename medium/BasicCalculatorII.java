package leetcode.medium;

import java.util.Stack;

/*
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
public class BasicCalculatorII {
	/*
	 * Intuition:
	 * 1. For every number, first add it into the stack
	 * 2. if the next operator is * or /, remove it from stack and recalculate and push in again
	 * 3. if the next operator is + or -, directly add
	 */
    public static int calculate(String s) {
    	Stack<Integer> stack=new Stack<Integer>();
    	int res=0, num=0;
    	char lastSign='+';
    	char []cArray=s.toCharArray();
    	
    	for(int i=0; i<cArray.length; i++) {
    		char c=cArray[i];
    		if(c<='9' && c>='0') num=num*10+c-'0';
    		if(c=='+' || c=='-' || c=='*' || c=='/' || i==cArray.length-1) {
    			if(lastSign=='+' || lastSign=='-') {
    				int temp=(lastSign=='+')?num:-num;
    				stack.push(temp);
    				res+=temp; //No matter what, add into the res
    			}
    			
    			if(lastSign=='*' || lastSign=='/') {
    				res-=stack.peek(); // minus the last added number, because * or / has higher priority
    				int temp=(lastSign=='*')?stack.pop()*num:stack.pop()/num;
    				stack.push(temp);
    				res+=temp;
    			}
    			lastSign=c;
    			num=0;
    		}
    	}
    	return res;
    }
    
    public static void main(String args[]) {
    	String s="2+2*3-3*4";
    	System.out.println(calculate(s));
    }
}
