package leetcode.medium;

import java.util.Stack;

/*
 * 
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
 */
public class ValidParenthesisString {
	//greedy algorithm from leetcode
    public static boolean checkValidStringI(String s) {
    	//low is to save the number of (
    	//high is to save the number of ( and * 
        int low=0, high=0;
        for(int i=0; i<s.length(); i++){
            char c=s.charAt(i);
            if(c=='(') 
                low++; 
            else 
                low--;
            if(c=='(' || c=='*') 
                high++; 
            else 
                high--;
            if(high<0) break;
            low=Math.max(low,0);
        }
        if(low==0) return true;
        else return false;
    }
	
	// brutal force:time limit exceed
    public static boolean helper(Stack<Character> stack, int index, String s){
        boolean res=false;
        if(index>=s.length() && stack.isEmpty()) return true;
        else if(index>=s.length() && !stack.isEmpty()) return false;
        char c=s.charAt(index);
        
        if(c=='*') {
            //treat * as empty string
        	Stack<Character> stack0=new  Stack<Character>();
            stack0.addAll(stack);
            res|=helper(stack0, index+1, s);
            if(res) return res;
            
            //treat * as '('
            Stack<Character> stack1=new  Stack<Character>();
            stack1.addAll(stack);
            stack1.push('(');
            res|=helper(stack1, index+1, s);
            if(res) return res;
            
            //treat * as ')
            c=')';
        }
        if(c=='(') stack.push(c);
        else if(c==')' && !stack.isEmpty()&& stack.peek()=='(') stack.pop();
        else if(c==')' && stack.isEmpty()) return false;
        
        res|=helper(stack, index+1, s); 
        return res;
    }
    
    public static boolean checkValidString(String s) {
        Stack<Character> stack=new Stack<Character>();
        int l=s.length();
        if(l==0) return true;
        
        return helper(stack, 0, s);
          
    }
    
    public static void main(String args[]) {
    	String s="(*))";
    	System.out.print(checkValidStringI(s));
    }
}
