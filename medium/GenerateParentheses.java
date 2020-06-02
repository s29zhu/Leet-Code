package leetcode.medium;

import java.util.List;
import java.util.ArrayList;

/*
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
n=1 ()
n=2 ()() (())
n=3 ()()()  ()(())  (())()  (()())   ((()))
 */
public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> list=new ArrayList<String>(),left=new ArrayList<String>(),right=new ArrayList<String>();
        
        if(n<=0) { list.add(""); return list; }
        for(int i=0; i<n;i++) {
        	left=generateParenthesis(i);
        	right=generateParenthesis(n-i-1);
        	for(String item1: left) {
        		for(String item2: right) {
        			list.add("("+item1+")"+item2);       			
        		}
        	}
        }
        return list;
    }
    public static void main(String args[]) {
    	List<String> list=new ArrayList<String>();
    	list=generateParenthesis(4);
    	for(String item: list) System.out.println(item);
    	System.out.println(list.size());
    }
}
